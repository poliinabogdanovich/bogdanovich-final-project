package by.dominos.ui;

import by.dominos.singleton.WebDriverSingleton;

public class HomePage {
    private final String URL = "https://dominos.by/";
    private final String BUTTON_ACCEPT_COOKIES = "//div[@class='CookieButtons']/button[@class='base-tab-button selected']";
    private final String BUTTON_USER_ACCOUNT = "//button[contains(text(), 'Войти')]";
    private final String BUTTON_MORE = "//div[@class='burger-menu']//div[@class='hamburger']";
    private final String LINK_DELIVERY_CONDITIONS = "//div[@class='side-menu']//a[@href='https://dominos.by/ru/minsk/delivery_conditions/']";
    private final String BUTTON_TO_CART = "//button[@class='red-button']//div[contains(text(), 'В корзину')]";
    private final String LINK_ORDER = "//a[@class='header-cart__order-btn']";

    public void openSite() {
        WebDriverSingleton.getDriver().navigate().to(URL);
    }

    public void clickAcceptCookies() {
        WebDriverSingleton.clickElement(BUTTON_ACCEPT_COOKIES);
    }

    public void clickUserAccount() {
        WebDriverSingleton.clickElement(BUTTON_USER_ACCOUNT);
    }

    public void clickMore() {
        WebDriverSingleton.clickElement(BUTTON_MORE);
    }

    public void clickDeliveryConditions() {
        WebDriverSingleton.clickElement(LINK_DELIVERY_CONDITIONS);
    }

    public void clickToCart() {
        WebDriverSingleton.clickElement(BUTTON_TO_CART);
    }

    public void clickOrder() {
        WebDriverSingleton.clickElement(LINK_ORDER);
    }
}
