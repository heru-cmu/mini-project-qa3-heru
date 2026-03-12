package core;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("=== STARTING TEST: " + result.getName() + " ===");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("=== PASSED TEST: " + result.getName() + " ===");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("=== FAILED TEST: " + result.getName() + " ===");
        TestUtils.takeScreenshot(DriverManager.getDriver(), result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("=== SKIPPED TEST: " + result.getName() + " ===");
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("=== STARTING SUITE: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("=== FINISHED SUITE: " + context.getName() + " ===");
    }
}
