package core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        String baseUrl = ConfigReader.getProperty("base.url");
        String loginPath = ConfigReader.getProperty("login.path");
        driver.get(baseUrl + loginPath);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
