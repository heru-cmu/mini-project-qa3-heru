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

        String toastMessage = resetPage.captureToastMessage();
        softAssert.assertTrue(resetPage.isToastVisible(), "Toast tidak muncul");
        softAssert.assertEquals(toastMessage, "Email is required", "Pesan toast tidak sesuai");
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

        String toastMessage = resetPage.captureToastMessage();
        softAssert.assertTrue(resetPage.isToastVisible(), "Toast tidak muncul");
        softAssert.assertEquals(toastMessage, "Invalid email format", "Pesan toast tidak sesuai");
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

        String toastMessage = resetPage.captureToastMessage();
        softAssert.assertTrue(resetPage.isToastVisible(), "Toast tidak muncul");
        softAssert.assertEquals(toastMessage, "Email Verifikasi Terkirim!", "Pesan toast tidak sesuai");
        softAssert.assertAll();
    }
}
