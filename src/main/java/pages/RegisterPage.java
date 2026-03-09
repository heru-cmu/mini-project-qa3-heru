package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage {
    private BasePage basePage;

    public RegisterPage(WebDriver driver) {
        this.basePage = new BasePage(driver);
    }

    @FindBy(id = "input-name-register")
    private WebElement nameField;

    @FindBy(id = "input-email-register")
    private WebElement emailField;

    @FindBy(id = "input-password-register")
    private WebElement passwordField;

    @FindBy(id = "btn-register")
    private WebElement registerButton;

    @FindBy(id = "link-login")
    private WebElement loginLink;

    public void enterName(String name) {
        basePage.type(nameField, name);
    }

    public void enterEmail(String email) {
        basePage.type(emailField, email);
    }

    public void enterPassword(String password) {
        basePage.type(passwordField, password);
    }

    public void clickRegister() {
        basePage.click(registerButton);
    }

    public void clickLoginLink() {
        basePage.click(loginLink);
    }

    public boolean isRegisterSuccessToastVisible() {
        return basePage.isToastVisible("toast-1");
    }

    public boolean isShortNameErrorToastVisible() {
        return basePage.isToastVisible("toast-4");
    }
}
