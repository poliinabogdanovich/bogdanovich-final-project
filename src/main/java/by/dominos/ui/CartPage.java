package by.dominos.ui;

import by.dominos.singleton.WebDriverSingleton;

public class CartPage {
    private final String BUTTON_CLOSE = "//button[@class='base-tab-button close-icon-button app-modal__close']";

    public void clickClose() {
        WebDriverSingleton.clickElement(BUTTON_CLOSE);
    }
}
