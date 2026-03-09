package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // type langsung ke WebElement
    public void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    // click langsung ke WebElement
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // cek toast by id
    public boolean isToastVisible(String toastId) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(toastId)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // cek toast by xpath
    public boolean isToastVisibleByXpath(String xpath) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
