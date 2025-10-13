package by.dominos.ui;

import by.dominos.singleton.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public HomePage() {
    }

    private final String URL = "https://dominos.by/";
    private final String BUTTON_ACCEPT_COOKIES = "//div[@class='CookieButtons']/button[@class='base-tab-button selected']";
    private final String BUTTON_USER_ACCOUNT = "//button[contains(text(), 'Войти')]";
    private final String BUTTON_MORE = "//div[@class='header__burger-menu']";
    private final String LINK_DELIVERY_CONDITIONS = "//div[@class='side-menu']//a[@href='https://dominos.by/ru/minsk/delivery_conditions/']";
    private final String BUTTON_TO_CART = "//button[@class='red-button']//div[contains(text(), 'В корзину')]";
    private final String LINK_ORDER = "//a[@class='header-cart__order-btn']";
    private final String BUTTON_DELETE_PRODUCT = "(//div[@class='counter__icon'])[1]";
    private final String BUTTON_ADD_PRODUCT = "(//div[@class='counter__icon'])[2]";

    private final Duration WAIT_TIMEOUT = Duration.ofSeconds(8);

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
        if (!WebDriverSingleton.findElements(BUTTON_MORE).isEmpty()) {
            WebDriverSingleton.clickElement(BUTTON_MORE);
        }
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

    public void clickDeleteProduct() {
        WebDriverSingleton.clickElement(BUTTON_DELETE_PRODUCT);
    }

    public void clickAddProduct() {
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getDriver(), WAIT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_ADD_PRODUCT))).click();
    }
}
