package api;

import by.dominos.api.SearchService;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchServiceTest {
    private static final Logger logger = LogManager.getLogger(SearchServiceTest.class);
    private SearchService searchService;

    @BeforeEach
    public void setUp() {
        searchService = new SearchService();
    }

    @Test
    @Order(1)
    @DisplayName("Поиск по корректному адресу (API check_delivery_zone)")
    public void testValidAddress() {
        logger.info("Отправка корректных данных: существующая улица и номер дома");

        String validStreetId = "206dceeb-cb7f-4723-ac99-bb6b0f8c0aa8";
        String validHouseNumber = "39";

        searchService.doRequest(validStreetId, validHouseNumber);

        int status = searchService.getStatusCode();
        String body = searchService.getResponseBody();

        logger.info("Статус ответа: {}", status);
        logger.info("Тело ответа: {}", body);

        assertEquals(200, status, "Ожидался статус 200 при корректном адресе");

        JsonPath json = new JsonPath(body);
        assertTrue(json.getBoolean("is_in_delivery_zone"), "Ожидалось, что адрес входит в зону доставки");
        assertEquals(29.99f, json.getFloat("minimal_delivery_price"), 0.01, "Проверка минимальной цены доставки");
        assertTrue(json.getString("warning_text").contains("Минимальная цена заказа"),
                "Ожидалось наличие предупреждения о минимальной цене");
    }

    @Test
    @Order(2)
    @DisplayName("Поиск по пустой улице")
    public void testEmptyStreet() {
        logger.info("Отправка запроса с пустым значением улицы");
        searchService.doRequest("", "39");

        int status = searchService.getStatusCode();
        String body = searchService.getResponseBody();

        logger.info("Статус ответа: {}", status);
        logger.info("Тело ответа: {}", body);

        assertEquals(400, status, "Must be a valid UUID.");
        assertTrue(body.contains("id") || body.contains("street") || body.contains("обязательно"),
                "Ответ должен содержать сообщение об обязательности поля улицы");
    }

    @Test
    @Order(3)
    @DisplayName("Поиск по пустому номеру дома")
    public void testEmptyHouseNumber() {
        logger.info("Отправка запроса с пустым номером дома");
        searchService.doRequest("206dceeb-cb7f-4723-ac99-bb6b0f8c0aa8", "");

        int status = searchService.getStatusCode();
        String body = searchService.getResponseBody();

        logger.info("Статус ответа: {}", status);
        logger.info("Тело ответа: {}", body);

        assertTrue(status == 400 || status == 422,
                "Ожидался статус ошибки при пустом номере дома");
        assertTrue(body.contains("street_number") || body.contains("обязательно"),
                "Ответ должен содержать сообщение об обязательности поля номера дома");
    }

    @Test
    @Order(4)
    @DisplayName("Поиск по некорректной улице")
    public void testInvalidStreet() {
        logger.info("Отправка запроса с некорректным ID улицы");
        searchService.doRequest("999999", "39");

        int status = searchService.getStatusCode();
        String body = searchService.getResponseBody();

        logger.info("Статус ответа: {}", status);
        logger.info("Тело ответа: {}", body);

        assertTrue(status == 400 || status == 404 || status == 422,
                "Ожидался статус ошибки при некорректной улице");
        assertTrue(body.contains("не входит в зону доставки")
                        || body.contains("not found")
                        || body.contains("error"),
                "Ответ должен содержать сообщение об ошибке некорректной улицы");
    }

    @Test
    @Order(5)
    @DisplayName("Поиск по некорректному номеру дома")
    public void testInvalidHouseNumber() {
        logger.info("Отправка запроса с некорректным номером дома");
        searchService.doRequest("206dceeb-cb7f-4723-ac99-bb6b0f8c0aa8", "abc");

        int status = searchService.getStatusCode();
        String body = searchService.getResponseBody();

        logger.info("Статус ответа: {}", status);
        logger.info("Тело ответа: {}", body);

        assertTrue(status == 400 || status == 422 || status == 404,
                "Ожидался статус ошибки при некорректном номере дома");
        assertTrue(body.contains("не входит в зону доставки")
                        || body.contains("error")
                        || body.contains("invalid"),
                "Ответ должен содержать сообщение об ошибке некорректного номера дома");
    }

    @AfterEach
    public void tearDown() {
        logger.info("Тест завершен");
    }
}
