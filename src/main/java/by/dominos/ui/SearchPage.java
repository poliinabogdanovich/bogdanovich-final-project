package by.dominos.ui;

import by.dominos.singleton.WebDriverSingleton;

public class SearchPage {
    public SearchPage() {}

    private final String INPUT_STREET = "//input[@class='rect-input__input with-right-icon']";
    private final String INPUT_HOUSE_NUMBER = "//input[@class='rect-input__input']";
    private final String BUTTON_CHECK_DELIVERY_ADDRESS = "//button[@class='pizza-map-check-address__button']";
    private final String MESSAGE_ERROR_EMPTY_STREET = "//span[@class='rect-input__error']";
    private final String MESSAGE_ERROR_EMPTY_HOUSE_NUMBER = "//span[@class='rect-input__error']";
    private final String MESSAGE_ERROR_NOT_DELIVERY_ZONE = "//p[@class='check-address-modal__text']";

    public void sendKeysStreet(String street) {
        WebDriverSingleton.sendKeys(INPUT_STREET, street);
    }

    public void sendKeysHouseNumber(String houseNumber) {
        WebDriverSingleton.sendKeys(INPUT_HOUSE_NUMBER, houseNumber);
    }

    public void clickDeliveryAddress() {
        WebDriverSingleton.clickElement(BUTTON_CHECK_DELIVERY_ADDRESS);
    }

    public String getTextEmptyStreet() {
        return WebDriverSingleton.findElement(MESSAGE_ERROR_EMPTY_STREET).getText();
    }

    public String getTextEmptyHouseNumber() {
        return WebDriverSingleton.findElement(MESSAGE_ERROR_EMPTY_HOUSE_NUMBER).getText();
    }

    public String getTextNotDeliveryZone() {
        return WebDriverSingleton.findElement(MESSAGE_ERROR_NOT_DELIVERY_ZONE).getText();
    }
}
