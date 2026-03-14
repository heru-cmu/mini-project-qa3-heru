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


        softAssert.assertTrue(registerPage.isToastVisible(), "Toast tidak muncul");
        System.out.println("Toast: " + registerPage.captureToastMessage());
        softAssert.assertAll();
    }

    @Test
    public void testEmptyEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clinkLinkDaftar();
        RegisterPage registerPage = new RegisterPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.isOnRegisterPage(), "Tidak berada di halaman register");

        registerPage.enterName("Heru");
        registerPage.enterEmail(""); // kosong
        registerPage.clickDaftar();


        softAssert.assertTrue(registerPage.isToastVisible(), "Toast tidak muncul");
        System.out.println("Toast: " + registerPage.captureToastMessage());
        softAssert.assertAll();
    }

    @Test
    public void testEmptyNameAndEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clinkLinkDaftar();
        RegisterPage registerPage = new RegisterPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.isOnRegisterPage(), "Tidak berada di halaman register");
        registerPage.enterName(""); // kosong
        registerPage.enterEmail(""); // kosong
        registerPage.clickDaftar();


        softAssert.assertTrue(registerPage.isToastVisible(), "Toast tidak muncul");
        System.out.println("Toast: " + registerPage.captureToastMessage());
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

        softAssert.assertTrue(registerPage.isToastVisible(), "Toast tidak muncul");
        System.out.println("Toast: " + registerPage.captureToastMessage());
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


        softAssert.assertTrue(registerPage.isToastVisible(), "Toast tidak muncul");
        System.out.println("Toast: " + registerPage.captureToastMessage());
        softAssert.assertAll();
    }

    @Test
    public void testRegisterSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clinkLinkDaftar();

        RegisterPage registerPage = new RegisterPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.isOnRegisterPage(), "Tidak berada di halaman register");
        registerPage.enterName("Heru Baru");
        registerPage.enterEmail("serius.herupenyu@gmail.com"); // email baru
        registerPage.clickDaftar();


        softAssert.assertTrue(registerPage.isToastVisible(), "Toast tidak muncul");
        System.out.println("Toast: " + registerPage.captureToastMessage());
        softAssert.assertAll();
    }


}
