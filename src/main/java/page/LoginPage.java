package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private BasePage basePage;
    private WebDriver driver;

    @FindBy(id = "input-email-login")
    private WebElement emailField;

    @FindBy(id = "input-password-login")
    private WebElement passwordField;

    @FindBy(id = "btn-login")
    private WebElement loginButton;

    @FindBy(id="link-register")
    private WebElement registerLink;

    @FindBy(id="link-reset-password")
    private WebElement resetPasswordLink;

    public LoginPage(WebDriver driver) {
        this.basePage = new BasePage(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        basePage.type(emailField, email);
    }

    public void enterPassword(String password) {
        basePage.type(passwordField, password);
    }

    public void clickLogin() {
        basePage.click(loginButton);
    }

    public void clinkLinkDaftar(){
        basePage.click(registerLink);
    }

    public void clickLinkResetPassword(){
        basePage.click(resetPasswordLink);
    }

    public boolean isToastVisible() {
        return basePage.isToastVisible();
    }

    public String captureToastMessage() {
        return basePage.captureToastText();
    }

    //dashboard check
    public boolean isDashboardVisible() {
        try {
            WebElement dashboardElement = driver.findElement(
                    By.xpath("//p[text()='Active Issue']")
            );
            return dashboardElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
