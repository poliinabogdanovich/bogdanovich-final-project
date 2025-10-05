package by.dominos.ui;

import by.dominos.singleton.WebDriver;
import org.openqa.selenium.By;

public class LoginPage {
    private final String BUTTON_FOR_CLIENTS = "//button[@class='red-button auth-modal__button-active']";
    private final String INPUT_PHONE_NUMBER = "//input[@class='input__component  auth-modal__input']";
    private final String BUTTON_JOIN = "//button[@class='red-button auth-modal__submit-button']";

    public void clickForClients() {
        WebDriver.clickElement(BUTTON_FOR_CLIENTS);
    }

    public void sendKeysPhoneNumber(String login) {
        WebDriver.findElement(By.xpath(INPUT_PHONE_NUMBER)).sendKeys(login);
    }

    public void clickJoin() {
        WebDriver.clickElement(BUTTON_JOIN);
    }
}
