package ru.crazymoonkin.otus;

import ru.crazymoonkin.otus.annotation.After;
import ru.crazymoonkin.otus.annotation.Before;
import ru.crazymoonkin.otus.annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class TestRunner {

    public static TestResult run(String className) {
        TestResult testResult = new TestResult(0, 0, 0);
        try {
            Class<?> aClass = Class.forName(className);
            Object instance = ReflectionHelper.instantiate(aClass);

            Method[] declaredMethods = aClass.getDeclaredMethods();
            callAnnotatedMethods(instance, declaredMethods, Before.class);

            runTests(instance, testResult);

            callAnnotatedMethods(instance, declaredMethods, After.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("runTestCount=" + testResult.runTestCount);
            System.out.println("successfulTestCount= " + testResult.successfulTestCount);
            System.out.println("failedTestCount=" + testResult.failedTestCount);
        }
        return testResult;
    }

    private static void callAnnotatedMethods(
            Object instance, Method[] declaredMethods,
            Class<? extends Annotation> annotationClass
    ) {
        Arrays.stream(declaredMethods)
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .forEach(method -> ReflectionHelper.callMethod(instance, method.getName()));
    }

    private static void runTests(Object instance, TestResult testResult) {
        AtomicInteger runTestCount = new AtomicInteger(testResult.runTestCount);
        AtomicInteger failedTestCount = new AtomicInteger(testResult.failedTestCount);
        AtomicInteger successfulTestCount = new AtomicInteger(testResult.successfulTestCount);

        Arrays.stream(instance.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .forEach(method -> {
                    try {
                        runTestCount.getAndSet(runTestCount.get() + 1);
                        ReflectionHelper.callMethod(instance, method.getName());
                        successfulTestCount.getAndSet(successfulTestCount.get() + 1);
                    } catch (Exception e) {
                        failedTestCount.getAndSet(failedTestCount.get() + 1);
                    }
                });
        testResult.runTestCount = runTestCount.get();
        testResult.successfulTestCount = successfulTestCount.get();
        testResult.failedTestCount = failedTestCount.get();
    }
}
