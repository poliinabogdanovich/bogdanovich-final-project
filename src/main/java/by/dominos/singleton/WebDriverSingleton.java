package by.dominos.singleton;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class WebDriverSingleton {
    private static WebDriver driver;

    private WebDriverSingleton() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            synchronized (WebDriverSingleton.class) {
                if (driver == null) {
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
                }
            }
        }
        return driver;
    }

    public static WebElement findElement(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    public static List<WebElement> findElements(String xpath) {
        return getDriver().findElements(By.xpath(xpath));
    }

    public static WebElement waitForElementVisible(String xpath, int seconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static void clickElement(String xpath) {
        waitForElementVisible(xpath, 5).click();
    }

    public static void sendKeys(String xpath, String text) {
        WebElement element = waitForElementVisible(xpath, 5);
        element.clear();
        element.sendKeys(text);
    }

    public static String getTextFromElement(String xpath) {
        return findElement(xpath).getText();
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
