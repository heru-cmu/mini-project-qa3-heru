package core;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils {

    public static File takeScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // pastikan folder screenshots ada
            Files.createDirectories(Paths.get("screenshots"));

            // buat nama file unik
            String destPath = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            File destFile = new File(destPath);

            // copy file screenshot ke lokasi tujuan
            Files.copy(srcFile.toPath(), destFile.toPath());

            System.out.println("Screenshot saved: " + destPath);
            return destFile; // return File supaya bisa di-attach ke Allure
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateRandomEmail() {
        return "user" + System.currentTimeMillis() + "@example.com";
    }
}
