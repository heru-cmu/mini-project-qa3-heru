package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordPage {
    private BasePage basePage;

    @FindBy(id = "input-email-reset")
    private WebElement emailField;

    @FindBy(id = "btn-send-verification")
    private WebElement resetButton;

    public ResetPasswordPage(WebDriver driver) {
        this.basePage = new BasePage(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        basePage.type(emailField, email);
    }

    public void clickReset() {
        basePage.click(resetButton);
    }

    // Toast check
    public boolean isToastVisible() {
        return basePage.isToastVisible();
    }

    public boolean isOnResetPage() {
        try {
            return resetButton.isDisplayed(); // tombol send verification jadi indikator
        } catch (Exception e) {
            return false;
        }
    }

    public String captureToastMessage() {
        return basePage.captureToastText();
    }
}
