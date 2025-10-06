package by.dominos.ui;

import by.dominos.singleton.WebDriverSingleton;

public class HomePage {
    private final String URL = "https://dominos.by/";
    private final String BUTTON_ACCEPT_COOKIES = "//div[@class='CookieButtons']/button[@class='base-tab-button selected']";
    private final String BUTTON_USER_ACCOUNT = "//button[contains(text(), 'Войти')]";

    public void openSite() {
        WebDriverSingleton.getDriver().navigate().to(URL);
    }

    public void acceptCookies() {
        WebDriverSingleton.clickElement(BUTTON_ACCEPT_COOKIES);
    }

    public void clickUserAccount() {
        WebDriverSingleton.clickElement(BUTTON_USER_ACCOUNT);
    }
}
