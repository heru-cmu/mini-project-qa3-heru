package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private BasePage basePage;

    @FindBy(id = "input-name-register")   // sesuaikan dengan HTML asli
    private WebElement nameField;

    @FindBy(id = "input-email-register")
    private WebElement emailField;

    @FindBy(id = "btn-register") // pastikan locator sesuai HTML tombol daftar
    private WebElement daftarButton;

    public RegisterPage(WebDriver driver) {
        this.basePage = new BasePage(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterName(String name) {
        basePage.type(nameField, name);
    }

    public void enterEmail(String email) {
        basePage.type(emailField, email);
    }

    public void clickDaftar() {
        basePage.click(daftarButton);
    }

    public boolean isToastVisible() {
        return basePage.isToastVisible();
    }

    public String captureToastMessage() {
        return basePage.captureToastText();
    }

    public boolean isOnRegisterPage() {
        try {
            return daftarButton.isDisplayed(); // tombol daftar jadi indikator
        } catch (Exception e) {
            return false;
        }
    }

}
