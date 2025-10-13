package ui;

import by.dominos.ui.CartPage;
import by.dominos.ui.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartTest extends BaseTest {
    HomePage homePage = new HomePage();
    CartPage cartPage = new CartPage();
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    public void openSite() {
        homePage.openSite();
        homePage.clickAcceptCookies();
        homePage.clickToCart();
    }

    @Test
    @DisplayName("Добавить товар в корзину")
    public void addProduct() {
        homePage.clickOrder();

        cartPage.clickClose();
    }

    @Test
    @DisplayName("Удаление товара из корзины")
    public void deleteProduct() {
        homePage.clickDeleteProduct();
    }

    @Test
    @DisplayName("Увеличить количество товаров в корзине")
    public void increaseProduct() {
        homePage.clickAddProduct();
    }

    @Test
    @DisplayName("Уменьшить количество товаров в корзине")
    public void decreaseProduct() {
        homePage.clickAddProduct();
        homePage.clickDeleteProduct();
    }

    @Test
    @DisplayName("Проверить стоимость заказа")
    public void checkOrderPrice() {
        homePage.clickAddProduct();
        homePage.clickOrder();

        cartPage.clickClose();
        cartPage.waitForCartToLoad();

        logger.info("Проверка итоговой стоимости");
        Assertions.assertEquals("Всего\n" + "51.98 руб.", cartPage.getTextOrderPrice());
    }
}
