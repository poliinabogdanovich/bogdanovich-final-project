package by.dominos.ui;

import by.dominos.singleton.WebDriverSingleton;

public class SearchPage {
    public SearchPage() {
    }

    private final String INPUT_STREET = "//input[@class='rect-input__input with-right-icon']";
    private final String INPUT_HOUSE_NUMBER = "//input[@class='rect-input__input']";
    private final String BUTTON_CHECK_DELIVERY_ADDRESS = "//button[@class='pizza-map-check-address__button']";

    public void sendKeysStreet(String street) {
        WebDriverSingleton.sendKeys(INPUT_STREET, street);
    }

    public void sendKeysHouseNumber(String houseNumber) {
        WebDriverSingleton.sendKeys(INPUT_HOUSE_NUMBER, houseNumber);
    }

    public void clickCheckDeliveryAddress() {
        WebDriverSingleton.clickElement(BUTTON_CHECK_DELIVERY_ADDRESS);
    }
}
