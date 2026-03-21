package tests;

import core.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

    @Test
    public void testEmptyName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clinkLinkDaftar();
        RegisterPage registerPage = new RegisterPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.isOnRegisterPage(), "Tidak berada di halaman register");

        registerPage.enterName(""); // kosong
        registerPage.enterEmail("work.herupenyu@gmail.com");
        registerPage.clickDaftar();

        String toastMessage = registerPage.captureToastMessage();
        softAssert.assertTrue(registerPage.isToastVisible(), "Toast tidak muncul");
        softAssert.assertEquals(toastMessage, "Name is required", "Pesan toast tidak sesuai");
        softAssert.assertAll();
    }

    @Test
    public void testInvalidEmailFormat() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clinkLinkDaftar();
        RegisterPage registerPage = new RegisterPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.isOnRegisterPage(), "Tidak berada di halaman register");

        registerPage.enterName("Heru");
        registerPage.enterEmail("heru@invalid"); // format salah
        registerPage.clickDaftar();

        String toastMessage = registerPage.captureToastMessage();
        softAssert.assertTrue(registerPage.isToastVisible(), "Toast tidak muncul");
        softAssert.assertEquals(toastMessage, "Invalid email format", "Pesan toast tidak sesuai");
        softAssert.assertAll();
    }

    @Test
    public void testDuplicateEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clinkLinkDaftar();
        RegisterPage registerPage = new RegisterPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.isOnRegisterPage(), "Tidak berada di halaman register");

        registerPage.enterName("Heru");
        registerPage.enterEmail("work.herupenyu@gmail.com"); // sudah terdaftar
        registerPage.clickDaftar();

        String toastMessage = registerPage.captureToastMessage();
        softAssert.assertTrue(registerPage.isToastVisible(), "Toast tidak muncul");
        softAssert.assertEquals(toastMessage, "Email already exists", "Pesan toast tidak sesuai");
        softAssert.assertAll();
    }

    @Test
    public void testRegisterSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clinkLinkDaftar();
        RegisterPage registerPage = new RegisterPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.isOnRegisterPage(), "Tidak berada di halaman register");

        // generate email unik
        String uniqueEmail = "serius.herupenyu@gmail.com";

        registerPage.enterName("Heru Baru");
        registerPage.enterEmail(uniqueEmail); // email generate
        registerPage.clickDaftar();

        String toastMessage = registerPage.captureToastMessage();
        softAssert.assertTrue(registerPage.isToastVisible(), "Toast tidak muncul");
        softAssert.assertEquals(toastMessage, "Berhasil Membuat User\n" +
                "Email verifikasi akan dikirim.", "Pesan toast tidak sesuai");
        softAssert.assertAll();
    }
}
