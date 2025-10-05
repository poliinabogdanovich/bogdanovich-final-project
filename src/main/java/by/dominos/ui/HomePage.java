package by.dominos.ui;

import by.dominos.singleton.WebDriver;

import static by.dominos.singleton.WebDriver.driver;

public class HomePage {
    private final String URL = "https://dominos.by/";
    private final String BUTTON_ACCEPT_COOKIES = "//div[@class='CookieButtons']/button[@class='base-tab-button selected']";
    private final String BUTTON_USER_ACCOUNT = "//button[contains(text(), 'Войти')]";

    public void openSite() {
        driver.navigate().to(URL);
    }

    public void acceptCookies() {
        WebDriver.clickElement(BUTTON_ACCEPT_COOKIES);
    }

    public void clickUserAccount() {
        WebDriver.clickElement(BUTTON_USER_ACCOUNT);
    }
}