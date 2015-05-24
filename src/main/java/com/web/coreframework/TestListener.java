package com.web.coreframework;

import org.testng.*;

/**
 * Class that determines what to do for tests that Pass, Fail, or are Skipped
 */
public class TestListener extends TestListenerAdapter {

    private int sCount = 1;

    @Override
    public void onTestFailure(final ITestResult tr) {
        super.onTestFailure(tr);
        Reporter.setCurrentTestResult(tr);
        final ITestNGMethod method = tr.getMethod();
        final IRetryAnalyzer retryAnalyzer = method.getRetryAnalyzer();
        if (null != retryAnalyzer && retryAnalyzer.retry(tr)) {
            // sCount++;
            tr.setStatus(ITestResult.SKIP);
        } else {
            // sCount = 0;
        }
        log("FAILED", tr);
        Reporter.setCurrentTestResult(null);
    }

    @Override
    public void onTestSkipped(final ITestResult tr) {
        super.onTestSkipped(tr);
        Reporter.setCurrentTestResult(tr);
        log("SKIPPED", tr);
        Reporter.setCurrentTestResult(null);
    }

    @Override
    public void onTestSuccess(final ITestResult tr) {
        super.onTestSuccess(tr);
        Reporter.setCurrentTestResult(tr);
        log("PASSED", tr);
        Reporter.setCurrentTestResult(null);
    }

    private void log(final String testStatus, final ITestResult tr) {
        String log = String.format("Test Script #%d | %s - %s\n", sCount++, tr.getMethod().getMethodName(), testStatus);
        System.out.println(log);
    }
}
