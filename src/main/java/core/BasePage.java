package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Ketik teks ke field
    public void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    // Klik tombol/link
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // Cek apakah toast muncul
    public boolean isToastVisible() {
        try {
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("chakra-toast") // sesuaikan locator toast kamu
            ));
            return toast.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Ambil isi pesan toast
    public String captureToastText() {
        try {
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("chakra-toast") // sesuaikan locator toast kamu
            ));
            return toast.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
