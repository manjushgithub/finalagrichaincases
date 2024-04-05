package com.agrichain.utility;

public class Listener {
	Action action = new Action();

    public Listener() {
    }

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == 1) {
            test.log(Status.PASS, "Pass Test case is: " + result.getName());
        }

    }

    public void onTestFailure(ITestResult var1) {
        throw new Error("Unresolved compilation problem: \n\tThe method driver() is undefined for the type BaseClass\n");
    }

    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == 3) {
            test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
        }

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
    }
}
