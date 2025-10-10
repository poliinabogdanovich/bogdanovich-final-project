package by.dominos.api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginService {
    private static final Logger logger = LogManager.getLogger(LoginService.class);
    private Response response;

    public void doRequest(String phone, String recaptcha) {
        logger.info("Выполнение запроса логина для номера телефона: " + phone);
        String URL = "https://dominos.by/api/v1/auth/login/phone/";

        Map<String, String> headers = getHeaders();

        String jsonBody = String.format("{\"recaptcha\":\"%s\", \"phone\":\"%s\"}", recaptcha, phone);

        response = given()
                .headers(headers)
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

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json, text/plain, */*");
        headers.put("Accept-Language", "ru");
        headers.put("Connection", "keep-alive");
        headers.put("Content-Type", "application/json");
        headers.put("Origin", "https://dominos.by");
        headers.put("Referer", "https://dominos.by/ru/minsk/");
        headers.put("X-CSRFToken", "b5zvZcixCbSXaTcKKebnoSLsCrWyGBHSW9v77YEdBibd453GHrF9h8WJDnxx221Q");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36");
        headers.put("sec-ch-ua", "\"Google Chrome\";v=\"141\", \"Not?A_Brand\";v=\"8\", \"Chromium\";v=\"141\"");
        headers.put("sec-ch-ua-mobile", "?0");
        headers.put("sec-ch-ua-platform", "\"Windows\"");
        return headers;
    }
}
