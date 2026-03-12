package core;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils {

    public static void takeScreenshot(WebDriver driver, String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destPath = "screenshots/" + testName + ".png";
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(destPath));
            System.out.println("Screenshot saved: " + destPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomEmail() {
        return "user" + System.currentTimeMillis() + "@example.com";
    }
}
