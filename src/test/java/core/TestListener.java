package core;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import api.utils.TokenManager;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("=== STARTING TEST: " + result.getName() + " ===");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("=== PASSED TEST: " + result.getName() + " ===");
        attachResponseEvidence(result);
        logTokenStatus(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("=== FAILED TEST: " + result.getName() + " ===");
        attachResponseEvidence(result);
        logTokenStatus(result);

        // Attach UI evidence (screenshot) kalau driver aktif
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            File screenshotFile = TestUtils.takeScreenshot(driver, result.getName());
            if (screenshotFile != null) {
                try {
                    Allure.addAttachment("Screenshot", new FileInputStream(screenshotFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("=== SKIPPED TEST: " + result.getName() + " ===");
        attachResponseEvidence(result);

        // Tambahkan skip evidence ke Allure
        String skipMessage = "Test skipped: " + result.getName();
        Allure.addAttachment("Skip Evidence", skipMessage);
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("=== STARTING SUITE: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("=== FINISHED SUITE: " + context.getName() + " ===");
    }

    private void attachResponseEvidence(ITestResult result) {
        Object statusCodeObj = result.getAttribute("statusCode");
        Object responseObj = result.getAttribute("response");
        Object bugNoteObj = result.getAttribute("bugNote");

        if (statusCodeObj != null) {
            Allure.addAttachment("Status Code", statusCodeObj.toString());
        }

        if (responseObj != null) {
            String body = responseObj.toString();
            String limitedBody = body.length() > 500 ? body.substring(0, 500) + "..." : body;
            Allure.addAttachment("Response Body (limited)", limitedBody);
        }

        if (bugNoteObj != null) {
            Allure.addAttachment("Bug Note", bugNoteObj.toString());
        }
    }

    private void logTokenStatus(ITestResult result) {
        String token = TokenManager.getToken();
        if (token != null) {
            logger.info("[TOKEN] Test: " + result.getName() + " menggunakan token: " + token);
        } else {
            logger.warn("[TOKEN] Test: " + result.getName() + " BELUM ada token tersimpan!");
        }
    }
}
