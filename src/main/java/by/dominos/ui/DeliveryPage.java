package by.dominos.ui;

import by.dominos.singleton.WebDriverSingleton;

public class DeliveryPage {
    public DeliveryPage() {
    }

    private final String LINK_CHECK_DELIVERY_ADDRESS = "//a[@class='delivery-conditions-map__link']";

    public void clickCheckDeliveryAddress() {
        WebDriverSingleton.clickElement(LINK_CHECK_DELIVERY_ADDRESS);
    }
}
