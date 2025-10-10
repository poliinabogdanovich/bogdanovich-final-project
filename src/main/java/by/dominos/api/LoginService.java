package by.dominos.api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class LoginService {
    private static final Logger logger = LogManager.getLogger(LoginService.class);
    private Response response;

    public void doRequest(String phone, String recaptcha) {
        logger.info("Выполнение запроса логина для номера телефона: " + phone);

        String URL = "https://dominos.by/api/v1/auth/login/phone/";
        String jsonBody = String.format("{\"recaptcha\":\"%s\", \"phone\":\"%s\"}", recaptcha, phone);

        response = given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(URL);

        logger.info("Получен ответ от API логина. Статус код: " + response.getStatusCode());
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }
}
