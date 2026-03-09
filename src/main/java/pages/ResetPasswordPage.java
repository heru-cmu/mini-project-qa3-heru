package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResetPasswordPage {
    private BasePage basePage;

    public ResetPasswordPage(WebDriver driver) {
        this.basePage = new BasePage(driver);
    }

    @FindBy(id = "input-email-reset")
    private WebElement emailField;

    @FindBy(id = "btn-send-verification")
    private WebElement sendVerificationButton;

    // Action: isi email
    public void enterEmail(String email) {
        basePage.type(emailField, email);
    }

    // Action: klik tombol kirim verifikasi
    public void clickSendVerification() {
        basePage.click(sendVerificationButton);
    }

    // Validasi toast sukses (contoh id toast sukses)
    public boolean isVerificationToastVisible() {
        return basePage.isToastVisible("toast-1");
    }

    // Validasi toast error (contoh id toast error)
    public boolean isInvalidEmailToastVisible() {
        return basePage.isToastVisible("toast-4");
    }
}
