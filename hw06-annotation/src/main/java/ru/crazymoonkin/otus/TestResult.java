package ru.crazymoonkin.otus;

public class TestResult {
    public TestResult(int runTestCount, int successfulTestCount, int failedTestCount) {
        this.runTestCount = runTestCount;
        this.successfulTestCount = successfulTestCount;
        this.failedTestCount = failedTestCount;
    }

    private TestResult() {
    }

    int runTestCount;
    int failedTestCount;
    int successfulTestCount;
}
