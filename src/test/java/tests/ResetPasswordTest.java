package tests;

import core.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ResetPasswordPage;

public class ResetPasswordTest extends BaseTest {

    @Test
    public void testValidResetRequest() {
        driver.get("https://resonance.dibimbing.id/reset-password");
        ResetPasswordPage resetPage = new ResetPasswordPage(driver);
        resetPage.enterEmail("work.herupenyu@gmail.com");
        resetPage.clickSendVerification();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resetPage.isVerificationToastVisible(),
                "Toast sukses kirim verifikasi tidak muncul");
        softAssert.assertAll();
    }

    @Test
    public void testInvalidResetRequest() {
        driver.get("https://resonance.dibimbing.id/reset-password");
        ResetPasswordPage resetPage = new ResetPasswordPage(driver);
        resetPage.enterEmail(""); // kosong atau invalid
        resetPage.clickSendVerification();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resetPage.isInvalidEmailToastVisible(),
                "Toast error email tidak muncul");
        softAssert.assertAll();
    }
}
