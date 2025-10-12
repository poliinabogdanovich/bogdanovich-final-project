package ui;

import by.dominos.singleton.WebDriverSingleton;
import by.dominos.ui.DeliveryPage;
import by.dominos.ui.HomePage;
import by.dominos.ui.SearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchTest extends BaseTest {
    HomePage homePage = new HomePage();
    DeliveryPage deliveryPage = new DeliveryPage();
    SearchPage searchPage = new SearchPage();
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    public void openSearchForm() {
        homePage.openSite();

        try {
            homePage.clickAcceptCookies();
        } catch (Exception ignored) {
        }

        if (WebDriverSingleton.findElements("//div[@class='header__burger-menu']").size() > 0) {
            homePage.clickMore();
        }

        homePage.clickDeliveryConditions();
        deliveryPage.clickCheckDeliveryAddress();
    }

    @Test
    @DisplayName("Поиск по корректному адресу")
    public void testValidAddress() {
        logger.info("Ввод корректной улицы");
        searchPage.sendKeysStreet("Платонова");
        logger.info("Ввод корректного номера дома");
        searchPage.sendKeysHouseNumber("23");
        searchPage.clickDeliveryAddress();
    }

    @Test
    @DisplayName("Поиск по пустой улице")
    public void testEmptyStreet() {
        logger.info("Ввод пустой улицы");
        searchPage.sendKeysStreet("");
        logger.info("Ввод номера дома");
        searchPage.sendKeysHouseNumber("23");
        searchPage.clickDeliveryAddress();

        logger.info("Ошибка обязательности поля Улица");
        Assertions.assertEquals("Это поле обязательно.", searchPage.getTextEmptyStreet());
    }

    @Test
    @DisplayName("Поиск по пустому номеру дома")
    public void testEmptyHouseNumber() {
        logger.info("Ввод улицы");
        searchPage.sendKeysStreet("Платонова");
        logger.info("Ввод пустого номера дома");
        searchPage.sendKeysHouseNumber("");
        searchPage.clickDeliveryAddress();

        logger.info("Ошибка обязательности поля Номер дома");
        Assertions.assertEquals("Это поле обязательно.", searchPage.getTextEmptyHouseNumber());
    }

    @Test
    @DisplayName("Поиск по некорректной улице")
    public void testInvalidStreet() {
        logger.info("Ввод некорректной улицы");
        searchPage.sendKeysStreet("12345");
        logger.info("Ввод значения в поле Номер дома");
        searchPage.sendKeysHouseNumber("23");
        searchPage.clickDeliveryAddress();

        logger.info("Ошибка некорректного ввода улицы");
        Assertions.assertEquals("Ваш адрес не входит в зону доставки :(", searchPage.getTextNotDeliveryZone());
    }

    @Test
    @DisplayName("Поиск по некорректному номеру дома")
    public void testInvalidHouseNumber() {
        logger.info("Ввод значения в поле Улица");
        searchPage.sendKeysStreet("Платонова");
        logger.info("Ввод некорректного номера дома");
        searchPage.sendKeysHouseNumber("fghj");
        searchPage.clickDeliveryAddress();

        logger.info("Ошибка некорректного ввода номера дома");
        Assertions.assertEquals("Ваш адрес не входит в зону доставки :(", searchPage.getTextNotDeliveryZone());
    }
}
