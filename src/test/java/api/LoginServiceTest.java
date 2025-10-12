package api;

import by.dominos.api.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginServiceTest {
    private static final Logger logger = LogManager.getLogger(LoginServiceTest.class);
    private LoginService loginService;

    @BeforeEach
    public void setUp() {
        loginService = new LoginService();
    }

    @Test
    @Order(1)
    @DisplayName("Логин с корректными данными")
    public void testValidData() {
        String validPhone = "+375293438185";
        String validRecaptcha = "0cAFcWeA6P4mexJd2yG6KEnkt1f8LgX2Pz7OK5r9-6pZmP5Wx6R0-LDanquTouaMeG7vikCz0evIhkdHF6HS-0NeBVKMVVvi1KavncMkUmg5vFhwih6U4ynttLDwNUnjp4oRR72QGzZ5TkjbJ6Kivdu9NSqdfFI2k1T2ThpXce-gJKPseMN0SAeCzNbocqZ1S6P9OmkfAavduzAM89vv20wlkVItdlBzp7z2DZdhRdanw_HQXIQqEcvfA2QabMbV4p63vaGgLkXe6CrfWBI1x_b30dgIySGbIMt91FlcSDTqaFz0FIQ8r6t8BB09jnKIs5A_ZsDEgPUDSCe_SeYgKnVvv1N-82eRuy6FmHAvjqTa9RlZZPT2FzgZAzHCaiuyxCyLAcwZqtbBIYmm-5P9tADSHA6JpZ8efq21nZByKcq5ImmC9Aas0uRys5E5U6buT5Ds6E9QoEq-oLJGoJ8xg8aV-y4Q-OfWoFIiXJIrW1tCfsiCfAUwganOV94p0BcgfuD6R8pWwteL8k3WpsdwxBbRdQKjQiEInxrHRcNODxOYhvhdT4sB8gAdrK35gWhs0XGeFlyULKlbxnXOZk499vMGffb_oxFJVCrEkGH_1lYvbLW2neXqhOInLpTOZcKoaWJPZGApgvSo3j9y9bm-hhKA8vi-eVXDb9r_KNC3AldEjjLh_EOdkQkaTjdpSenfU_nvlmVt_7rwkT0TwinGsTasaRDA8a5Ux3LAH3Nf896f-3fkfUWLmKvuBI0nQ6733Zp_nJLR9CPEdw0h3ZyJan6d_BNTahyEJgVg";

        logger.info("Отправка корректных данных: {}", validPhone);
        loginService.doRequest(validPhone, validRecaptcha);

        int status = loginService.getStatusCode();
        String body = loginService.getResponseBody();

        logger.info("Ответ API: {}", body);

        assertEquals(200, status, "Ожидался статус 200 при корректных данных");
    }

    @Test
    @Order(2)
    @DisplayName("Логин с пустым номером телефона")
    public void testEmptyPhoneNumber() {
        logger.info("Отправка пустого номера телефона");
        loginService.doRequest("", "test-recaptcha");

        int status = loginService.getStatusCode();
        String body = loginService.getResponseBody();

        logger.info("Ответ API: {}", body);

        assertTrue(status == 400 || status == 422,
                "Ожидался статус ошибки при пустом номере телефона");
        assertTrue(body.contains("phone") || body.contains("Номер телефона"),
                "Ответ должен содержать сообщение об ошибке по полю phone");
    }

    @Test
    @Order(3)
    @DisplayName("Логин с некорректным номером телефона")
    public void testInvalidPhoneNumber() {
        logger.info("Отправка некорректного номера телефона");
        loginService.doRequest("12345", "test-recaptcha");

        int status = loginService.getStatusCode();
        String body = loginService.getResponseBody();

        logger.info("Ответ API: {}", body);

        assertTrue(status == 400 || status == 422,
                "Ожидался статус ошибки при некорректном номере телефона");
        assertTrue(body.contains("Введите корректный номер телефона")
                        || body.contains("invalid phone")
                        || body.contains("error"),
                "Ответ должен содержать сообщение об ошибке номера телефона");
    }

    @Test
    @Order(4)
    @DisplayName("Логин с пустым кодом подтверждения")
    public void testEmptyCode() {
        logger.info("Отправка запроса с пустым кодом");
        loginService.doRequest("+375293438185", "");

        int status = loginService.getStatusCode();
        String body = loginService.getResponseBody();

        logger.info("Ответ API: {}", body);

        assertTrue(status == 400 || status == 422,
                "Ожидался статус ошибки при пустом коде");
        assertTrue(body.contains("recaptcha") || body.contains("code") || body.contains("Неверный код"),
                "Ответ должен содержать сообщение об ошибке пустого кода");
    }

    @Test
    @Order(5)
    @DisplayName("Логин с некорректным кодом подтверждения")
    public void testInvalidCode() {
        logger.info("Отправка запроса с некорректным кодом подтверждения");
        loginService.doRequest("+375293438185", "ghhfjd");

        int status = loginService.getStatusCode();
        String body = loginService.getResponseBody();

        logger.info("Ответ API: {}", body);

        assertTrue(status == 400 || status == 422 || status == 401,
                "Ожидался статус ошибки при некорректном коде");
        assertTrue(body.contains("Неверный код")
                        || body.contains("invalid")
                        || body.contains("error"),
                "Ответ должен содержать сообщение об ошибке неверного кода");
    }

    @AfterEach
    public void tearDown() {
        logger.info("Тест завершен");
    }
}
