package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    private BasePage basePage;

    public LoginPage(WebDriver driver) {
        this.basePage = new BasePage(driver);
    }

    @FindBy(id = "input-email-login")
    private WebElement emailField;

    @FindBy(id = "input-password-login")
    private WebElement passwordField;

    @FindBy(id = "btn-login")
    private WebElement loginButton;

    @FindBy(id = "link-register")
    private WebElement registerLink;

    @FindBy(id = "link-reset-password")
    private WebElement resetPasswordLink;

    public void enterEmail(String email) {
        basePage.type(emailField, email);
    }

    public void enterPassword(String password) {
        basePage.type(passwordField, password);
    }

    public void clickLogin() {
        basePage.click(loginButton);
    }

    public void clickRegisterLink() {
        basePage.click(registerLink);
    }

    public void clickResetPasswordLink() {
        basePage.click(resetPasswordLink);
    }

    public boolean isInvalidLoginToastVisible() {
        return basePage.isToastVisible("toast-3-title");
    }

    public boolean isEmptyFieldToastVisible() {
        return basePage.isToastVisible("toast-5-title");
    }
}
