package by.dominos.ui;

import by.dominos.singleton.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {
    private static final Logger logger = LogManager.getLogger();

    private final String BUTTON_FOR_CLIENTS = "//button[contains(@class,'auth-modal__button-active')]";
    private final String INPUT_PHONE_NUMBER = "//input[@type='tel']";
    private final String BUTTON_JOIN = "//button[contains(@class,'auth-modal__submit-button')]";
    private final String INPUT_CODE = "//input[contains(@class,'auth-modal__input')]";
    private final String MESSAGE_INVALID_PHONE_NUMBER = "//p[contains(@class,'input__error-message') and contains(normalize-space(.),'Введите корректный номер телефона')]";
    private final String MESSAGE_INVALID_CODE = "//p[contains(@class,'input__error-message') and contains(normalize-space(.),'Неверный код верификации')]";

    private final WebDriver driver = WebDriverSingleton.getDriver();
    private final Duration WAIT_TIMEOUT = Duration.ofSeconds(6);

    public LoginPage() {
    }

    public void clickForClients() {
        WebDriverSingleton.clickElement(BUTTON_FOR_CLIENTS);
    }

    public void sendKeysPhoneNumber(String login) {
        WebDriverSingleton.sendKeys(INPUT_PHONE_NUMBER, login);
    }

    public void clickJoin() {
        WebDriverSingleton.clickElement(BUTTON_JOIN);
    }

    public void sendKeysCode(String code) {
        WebDriverSingleton.sendKeys(INPUT_CODE, code);
    }

    public String getTextInvalidPhoneNumber() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MESSAGE_INVALID_PHONE_NUMBER)));
            return WebDriverSingleton.findElement(MESSAGE_INVALID_PHONE_NUMBER).getText().trim();
        } catch (Exception e) {
            logger.warn("Сообщение об ошибке номера телефона не появилось: " + e.getMessage());
            return "";
        }
    }

    public String getTextInvalidCode() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MESSAGE_INVALID_CODE)));
            return WebDriverSingleton.findElement(MESSAGE_INVALID_CODE).getText().trim();
        } catch (Exception e) {
            logger.warn("Сообщение об ошибке кода не появилось: " + e.getMessage());
            return "";
        }
    }
}
