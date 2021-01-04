package ru.crazymoonkin.otus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestRunnerTest {

    @Test
    void runSuccessfulTests() {
        TestResult run = TestRunner.run("ru.crazymoonkin.otus.test.SuccessfulTest");

        assertEquals(3, run.getRunTestCount());
        assertEquals(3, run.getSuccessfulTestCount());
        assertEquals(0, run.getFailedTestCount());
    }

    @Test
    void runTestsWithException() {
        TestResult run = TestRunner.run("ru.crazymoonkin.otus.test.TestWithException");

        assertEquals(3, run.getRunTestCount());
        assertEquals(2, run.getSuccessfulTestCount());
        assertEquals(1, run.getFailedTestCount());
    }


    @Test
    void beforeWithException() {
        TestResult run = TestRunner.run("ru.crazymoonkin.otus.test.BeforeWithExceptionTest");

        assertEquals(3, run.getRunTestCount());
        assertEquals(0, run.getSuccessfulTestCount());
        assertEquals(3, run.getFailedTestCount());
    }

    @Test
    void afterWithException() {
        TestResult run = TestRunner.run("ru.crazymoonkin.otus.test.AfterWithExceptionTest");

        assertEquals(3, run.getRunTestCount());
        assertEquals(0, run.getSuccessfulTestCount());
        assertEquals(3, run.getFailedTestCount());
    }
}