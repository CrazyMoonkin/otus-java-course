package ru.crazymoonkin.otus;

public class TestResult {
    public TestResult(int runTestCount, int successfulTestCount, int failedTestCount) {
        this.runTestCount = runTestCount;
        this.successfulTestCount = successfulTestCount;
        this.failedTestCount = failedTestCount;
    }

    private TestResult() {
    }

    private int runTestCount;
    private int failedTestCount;
    private int successfulTestCount;

    public int getRunTestCount() {
        return runTestCount;
    }

    public void setRunTestCount(int runTestCount) {
        this.runTestCount = runTestCount;
    }

    public int getFailedTestCount() {
        return failedTestCount;
    }

    public void setFailedTestCount(int failedTestCount) {
        this.failedTestCount = failedTestCount;
    }

    public int getSuccessfulTestCount() {
        return successfulTestCount;
    }

    public void setSuccessfulTestCount(int successfulTestCount) {
        this.successfulTestCount = successfulTestCount;
    }
}
