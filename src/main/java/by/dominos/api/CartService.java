package by.dominos.api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class CartService {
    private static final Logger logger = LogManager.getLogger(CartService.class);
    private Response response;

    public void doRequest() {
        logger.info("Выполнение запроса для получения содержимого корзины пользователя");

        String URL = "https://dominos.by/api/v1/cart/";

        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(URL);

        logger.info("Получен ответ от API корзины. Статус код: " + response.getStatusCode());
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }
}
