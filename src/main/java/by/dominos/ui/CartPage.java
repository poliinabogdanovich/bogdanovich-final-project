package by.dominos.ui;

import by.dominos.singleton.WebDriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    public CartPage() {
    }

    private static final Logger logger = LogManager.getLogger();

    private final String BUTTON_CLOSE = "//button[@class='base-tab-button close-icon-button app-modal__close']";
    private final String MESSAGE_ORDER_PRICE = "//div[@class='cart-order-total-price']";

    private final WebDriver driver = WebDriverSingleton.getDriver();
    private final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);

    public void clickClose() {
        WebDriverSingleton.clickElement(BUTTON_CLOSE);
    }

    public void waitForCartToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MESSAGE_ORDER_PRICE)));
            logger.info("Корзина успешно загрузилась.");
        } catch (Exception e) {
            logger.warn("Корзина не прогрузилась вовремя: " + e.getMessage());
        }
    }

    public String getTextOrderPrice() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MESSAGE_ORDER_PRICE)));
            return WebDriverSingleton.findElement(MESSAGE_ORDER_PRICE).getText().trim();
        } catch (Exception e) {
            logger.warn("Сообщение об ошибке номера телефона не появилось: " + e.getMessage());
            return "";
        }
    }
}
