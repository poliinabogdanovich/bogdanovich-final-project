package by.dominos.ui;

import by.dominos.singleton.WebDriverSingleton;

public class LoginPage {
    private final String BUTTON_FOR_CLIENTS = "//button[@class='red-button auth-modal__button-active']";
    private final String INPUT_PHONE_NUMBER = "//input[@type='tel']";
    private final String BUTTON_JOIN = "//button[@class='red-button auth-modal__submit-button']";
    private final String INPUT_CODE = "//input[@class='input__component  auth-modal__input']";

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
}
