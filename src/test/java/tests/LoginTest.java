package tests;

import core.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testEmptyFields() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.isToastVisible(), "Toast tidak muncul");
        System.out.println("Toast message: " + loginPage.captureToastMessage());
        softAssert.assertAll();
    }

    @Test
    public void testEmptyEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword("ureh");
        loginPage.clickLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.isToastVisible(), "Toast tidak muncul");
        System.out.println("Toast message: " + loginPage.captureToastMessage());
        softAssert.assertAll();
    }

    @Test
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("work.herupenyu@gmail.com");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.isToastVisible(), "Toast tidak muncul");
        System.out.println("Toast message: " + loginPage.captureToastMessage());
        softAssert.assertAll();
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("wrong@example.com");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.isToastVisible(), "Toast tidak muncul");
        System.out.println("Toast message: " + loginPage.captureToastMessage());
        softAssert.assertAll();
    }

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("work.herupenyu@gmail.com");
        loginPage.enterPassword("ureh");
        loginPage.clickLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.isDashboardVisible(), "Dashboard tidak muncul setelah login");
        softAssert.assertAll();
    }
}
