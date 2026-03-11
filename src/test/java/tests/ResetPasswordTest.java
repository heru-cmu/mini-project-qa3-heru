package tests;

import core.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ResetPasswordPage;

public class ResetPasswordTest extends BaseTest {

    @Test
    public void testEmptyEmail() {
        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.clickLinkResetPassword();
        ResetPasswordPage resetPage = new ResetPasswordPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resetPage.isOnResetPage(), "Tidak berada di halaman reset password");

        resetPage.clickReset();

        softAssert.assertTrue(resetPage.isToastVisible(), "Toast tidak muncul");

        String toastMessage = resetPage.captureToastMessage();
        System.out.println("Isi toast: " + toastMessage);

        softAssert.assertAll();
    }

    @Test
    public void testInvalidEmail() {
        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.clickLinkResetPassword();
        ResetPasswordPage resetPage = new ResetPasswordPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resetPage.isOnResetPage(), "Tidak berada di halaman reset password");

        resetPage.enterEmail("invalid-email");
        resetPage.clickReset();

        softAssert.assertTrue(resetPage.isToastVisible(), "Toast tidak muncul");

        String toastMessage = resetPage.captureToastMessage();
        System.out.println("Isi toast: " + toastMessage);

        softAssert.assertAll();
    }

    @Test
    public void testResetSuccess() {
        pages.LoginPage loginPage = new pages.LoginPage(driver);
        loginPage.clickLinkResetPassword();
        ResetPasswordPage resetPage = new ResetPasswordPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resetPage.isOnResetPage(), "Tidak berada di halaman reset password");

        resetPage.enterEmail("work.herupenyu@gmail.com");
        resetPage.clickReset();

        softAssert.assertTrue(resetPage.isToastVisible(), "Toast tidak muncul");

        String toastMessage = resetPage.captureToastMessage();
        System.out.println("Isi toast: " + toastMessage);

        softAssert.assertAll();
    }
}
