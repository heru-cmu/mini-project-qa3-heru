package tests;

import core.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        driver.get("https://resonance.dibimbing.id/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("work.herupenyu@gmail.com");
        loginPage.enterPassword("ureh");
        loginPage.clickLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.getCurrentUrl().contains("/dashboard"),
                "Dashboard tidak tampil setelah login valid");
        softAssert.assertAll();
    }

    @Test
    public void testInvalidLogin() {
        driver.get("https://resonance.dibimbing.id/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("wronguser@dibimbing.id");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.isInvalidLoginToastVisible(),
                "Toast error invalid login tidak muncul");
        softAssert.assertAll();
    }

    @Test
    public void testEmptyFields() {
        driver.get("https://resonance.dibimbing.id/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.isEmptyFieldToastVisible(),
                "Toast validasi empty field tidak muncul");
        softAssert.assertAll();
    }
}
