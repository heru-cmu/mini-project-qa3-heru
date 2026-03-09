package tests;

import core.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

    @Test
    public void testValidRegister() {
        driver.get("https://resonance.dibimbing.id/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterName("Heru QA");
        registerPage.enterEmail("heru.qa+" + System.currentTimeMillis() + "@gmail.com");
        registerPage.enterPassword("ureh123");
        registerPage.clickRegister();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.isRegisterSuccessToastVisible(),
                "Toast sukses register tidak muncul");
        softAssert.assertAll();
    }

    @Test
    public void testInvalidRegisterShortName() {
        driver.get("https://resonance.dibimbing.id/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterName("H"); // nama terlalu pendek
        registerPage.enterEmail("heru.shortname@gmail.com");
        registerPage.enterPassword("ureh123");
        registerPage.clickRegister();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.isShortNameErrorToastVisible(),
                "Toast error short name tidak muncul");
        softAssert.assertAll();
    }

    @Test
    public void testInvalidRegisterDuplicateEmail() {
        driver.get("https://resonance.dibimbing.id/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterName("Heru QA");
        registerPage.enterEmail("work.herupenyu@gmail.com"); // email valid sudah dipakai
        registerPage.enterPassword("ureh123");
        registerPage.clickRegister();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.isRegisterSuccessToastVisible(),
                "Toast duplicate email tidak muncul (BUG)");
        softAssert.assertAll();
    }
}
