package api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartServiceTest {

    private static final Logger logger = LogManager.getLogger(CartServiceTest.class);
    private final String baseUrl = "https://dominos.by/api/v1/cart/";

    @Test
    @Order(1)
    @DisplayName("Получить пустую корзину (API cart)")
    public void testGetEmptyCart() {
        logger.info("Отправка запроса на получение корзины");

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(baseUrl)
                .then()
                .extract().response();

        logger.info("Ответ API: {}", response.asString());

        assertEquals(200, response.getStatusCode(), "Ожидался статус 200 при получении корзины");

        JsonPath json = response.jsonPath();

        assertEquals(0.0f, json.getFloat("total"), 0.01, "Ожидалась сумма total = 0.0");
        assertEquals(0, json.getInt("bonuses"), "Ожидалось значение bonuses = 0");
        assertTrue(json.getList("products").isEmpty(), "Список products должен быть пустым");

        assertEquals("Заказать", json.getString("text.checkout"), "Проверка текстового значения checkout");
        assertTrue(json.getString("text.empty_cart").contains("пуста"), "Ожидалось сообщение 'корзина пуста'");

        assertEquals(24.99f, json.getFloat("min_order_amount"), 0.01, "Проверка минимальной суммы заказа");
        assertTrue(json.getString("text.min_order_text").contains("24.99"), "Текст должен содержать '24.99'");
    }

    @Test
    @Order(2)
    @DisplayName("Проверить, что корзина пустая и элементы товаров не присутствуют")
    public void testCartContentStructure() {
        logger.info("Проверка структуры пустой корзины");

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(baseUrl)
                .then()
                .extract().response();

        JsonPath json = response.jsonPath();

        assertTrue(json.getList("products").isEmpty(), "Ожидалось отсутствие products");
        assertTrue(json.getList("pizza_half").isEmpty(), "Ожидалось отсутствие pizza_half");
        assertTrue(json.getList("combo_menu").isEmpty(), "Ожидалось отсутствие combo_menu");
    }
}
