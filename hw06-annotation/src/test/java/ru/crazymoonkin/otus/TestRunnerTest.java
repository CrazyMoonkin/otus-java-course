package ru.crazymoonkin.otus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestRunnerTest {

    @Test
    void runSuccessfulTests() {
        TestResult run = TestRunner.run("ru.crazymoonkin.otus.test.SuccessfulTest");

        assertEquals(3, run.runTestCount);
        assertEquals(3, run.successfulTestCount);
        assertEquals(0, run.failedTestCount);
    }

    @Test
    void runTestsWithException() {
        TestResult run = TestRunner.run("ru.crazymoonkin.otus.test.TestWithException");

        assertEquals(3, run.runTestCount);
        assertEquals(2, run.successfulTestCount);
        assertEquals(1, run.failedTestCount);
    }


    @Test
    void beforeWithException() {
        TestResult run = TestRunner.run("ru.crazymoonkin.otus.test.BeforeWithExceptionTest");

        assertEquals(0, run.runTestCount);
        assertEquals(0, run.successfulTestCount);
        assertEquals(0, run.failedTestCount);
    }

    @Test
    void afterWithException() {
        TestResult run = TestRunner.run("ru.crazymoonkin.otus.test.AfterWithExceptionTest");

        assertEquals(3, run.runTestCount);
        assertEquals(3, run.successfulTestCount);
        assertEquals(0, run.failedTestCount);
    }
}