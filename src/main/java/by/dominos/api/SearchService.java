package by.dominos.api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class SearchService {
    private static final Logger logger = LogManager.getLogger(SearchService.class);
    private Response response;

    public void doRequest(String id, String streetNumber) {
        logger.info("Выполнение запроса проверки зоны доставки для улицы с ID: " + id + " и номером: " + streetNumber);

        String URL = "https://dominos.by/api/v1/streets/check_delivery_zone/";
        String jsonBody = String.format("{\"id\":\"%s\", \"street_number\":\"%s\"}", id, streetNumber);

        response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post(URL);

        logger.info("Получен ответ от API проверки зоны доставки. Статус код: " + response.getStatusCode());
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }
}
