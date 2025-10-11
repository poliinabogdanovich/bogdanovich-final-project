package by.dominos.ui;

import by.dominos.singleton.WebDriverSingleton;
import org.openqa.selenium.By;

public class LoginPage {
    public LoginPage() {
    }

    private final String BUTTON_FOR_CLIENTS = "//button[@class='red-button auth-modal__button-active']";
    private final String INPUT_PHONE_NUMBER = "//input[@type='tel']";
    private final String BUTTON_JOIN = "//button[@class='red-button auth-modal__submit-button']";
    private final String INPUT_CODE = "//input[@class='input__component  auth-modal__input ']";
    private final String MESSAGE_INVALID_PHONE_NUMBER = "//div[@class='input input_with-error']//p[@class='input__error-message']";
    private final String MESSAGE_INVALID_CODE = "//p[@class='input__error-message']";

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
       return WebDriverSingleton.findElement(MESSAGE_INVALID_PHONE_NUMBER).getText();
    }

    public String getTextInvalidCode() {
        return WebDriverSingleton.findElement(MESSAGE_INVALID_CODE).getText();
    }
}
