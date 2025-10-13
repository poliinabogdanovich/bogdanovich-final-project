package api;

import by.dominos.api.CartService;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartServiceTest {

    private static final Logger logger = LogManager.getLogger(CartServiceTest.class);
    private CartService cartService;
    private String baseUrl = "https://dominos.by/api/v1/cart/";
    private static String cartId; // может использоваться для отслеживания корзины
    private static String productId = "102"; // тестовый ID продукта (пример)

    @BeforeEach
    public void setUp() {
        cartService = new CartService();
    }

    @Test
    @Order(1)
    @DisplayName("Добавить товар в корзину")
    public void testAddProductToCart() {
        logger.info("Отправка запроса на добавление товара в корзину");

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"product_id\": \"" + productId + "\", \"quantity\": 1}")
                .when()
                .post(baseUrl + "add/")
                .then()
                .extract().response();

        logger.info("Ответ API: {}", response.asString());

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode, "Ожидался статус 200 при добавлении товара");

        JsonPath json = response.jsonPath();
        assertTrue(json.getString("cart") != null, "Ожидалось наличие объекта корзины в ответе");

        cartId = json.getString("cart.id");
        logger.info("Текущий ID корзины: {}", cartId);
    }

    @Test
    @Order(2)
    @DisplayName("Увеличить количество товаров в корзине")
    public void testIncreaseProductQuantity() {
        assumeTrue(cartId != null, "Корзина должна существовать после добавления товара");

        logger.info("Отправка запроса на увеличение количества товара");

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"product_id\": \"" + productId + "\", \"quantity\": 2}")
                .when()
                .post(baseUrl + "update/")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        logger.info("Ответ API: {}", response.asString());
        assertEquals(200, statusCode, "Ожидался статус 200 при увеличении количества");

        int quantity = response.jsonPath().getInt("cart.items[0].quantity");
        assertEquals(2, quantity, "Количество товара должно увеличиться до 2");
    }

    @Test
    @Order(3)
    @DisplayName("Уменьшить количество товаров в корзине")
    public void testDecreaseProductQuantity() {
        assumeTrue(cartId != null, "Корзина должна существовать");

        logger.info("Отправка запроса на уменьшение количества товара");

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"product_id\": \"" + productId + "\", \"quantity\": 1}")
                .when()
                .post(baseUrl + "update/")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode, "Ожидался статус 200 при уменьшении количества товара");

        int quantity = response.jsonPath().getInt("cart.items[0].quantity");
        assertEquals(1, quantity, "Количество товара должно уменьшиться до 1");
    }

    @Test
    @Order(4)
    @DisplayName("Удаление товара из корзины")
    public void testDeleteProductFromCart() {
        assumeTrue(cartId != null, "Корзина должна существовать");

        logger.info("Отправка запроса на удаление товара из корзины");

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"product_id\": \"" + productId + "\"}")
                .when()
                .post(baseUrl + "remove/")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        logger.info("Ответ API: {}", response.asString());
        assertEquals(200, statusCode, "Ожидался статус 200 при удалении товара");

        String items = response.jsonPath().getString("cart.items");
        assertTrue(items == null || items.equals("[]"), "Корзина должна быть пуста после удаления товара");
    }

    @Test
    @Order(5)
    @DisplayName("Проверить стоимость заказа")
    public void testCheckOrderPrice() {
        logger.info("Отправка запроса на проверку стоимости заказа");

        // Сначала добавим товар
        given()
                .header("Content-Type", "application/json")
                .body("{\"product_id\": \"" + productId + "\", \"quantity\": 2}")
                .when()
                .post(baseUrl + "add/");

        // Затем получим корзину
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(baseUrl)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode, "Ожидался статус 200 при получении корзины");

        JsonPath json = response.jsonPath();
        double totalPrice = json.getDouble("cart.total_price");
        logger.info("Итоговая стоимость корзины: {}", totalPrice);

        assertTrue(totalPrice > 0, "Итоговая стоимость должна быть больше 0");
    }

    @AfterEach
    public void tearDown() {
        logger.info("Тест завершен");
    }
}
