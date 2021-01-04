package ru.crazymoonkin.otus;

import ru.crazymoonkin.otus.annotation.After;
import ru.crazymoonkin.otus.annotation.Before;
import ru.crazymoonkin.otus.annotation.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class TestRunner {

    public static TestResult run(String className) {
        TestResult testResult = null;
        try {
            Class<?> aClass = Class.forName(className);

            testResult = runTests(aClass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (testResult == null) {
                testResult = new TestResult(0, 0, 0);
            }
            System.out.println("runTestCount=" + testResult.runTestCount);
            System.out.println("successfulTestCount=" + testResult.successfulTestCount);
            System.out.println("failedTestCount=" + testResult.failedTestCount);
        }
        return testResult;
    }

    private static void callAnnotatedMethods(Object instance, Class<? extends Annotation> annotationClass) {
        Arrays.stream(instance.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .forEach(method -> ReflectionHelper.callMethod(instance, method.getName()));
    }

    private static TestResult runTests(Class<?> aClass) {
        AtomicInteger runTestCount = new AtomicInteger(0);
        AtomicInteger failedTestCount = new AtomicInteger(0);
        AtomicInteger successfulTestCount = new AtomicInteger(0);

        Arrays.stream(aClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .forEach(method -> {
                    try {
                        runTestCount.incrementAndGet();
                        Object instance = ReflectionHelper.instantiate(aClass);

                        callAnnotatedMethods(instance, Before.class);
                        ReflectionHelper.callMethod(instance, method.getName());
                        callAnnotatedMethods(instance, After.class);

                        successfulTestCount.incrementAndGet();
                    } catch (Exception e) {
                        failedTestCount.incrementAndGet();
                    }
                });
        return new TestResult(runTestCount.get(), successfulTestCount.get(), failedTestCount.get());
    }
}
