package ui;

import by.dominos.ui.HomePage;
import by.dominos.ui.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

public class LoginTest extends BaseTest {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    public void openLoginForm() {
        homePage.openSite();
        homePage.clickAcceptCookies();
        homePage.clickUserAccount();

        loginPage.clickForClients();
    }

    @Test
    @DisplayName("Логин с корректным номером телефона")
    public void testValidData() {
        String phoneNumber = "375293438185";
        logger.info("Ввод корректного номера телефона");
        loginPage.sendKeysPhoneNumber(phoneNumber);
        loginPage.clickJoin();
        logger.info("Ввод корректного кода");
        loginPage.sendKeysCode("839949");
    }

    @Test
    @DisplayName("Логин с пустым номером телефона")
    public void testEmptyPhoneNumber() {
        String phoneNumber = "";
        logger.info("Ввод пустого значения");
        loginPage.sendKeysPhoneNumber(phoneNumber);
    }

    @Test
    @DisplayName("Логин с некорректным номером телефона")
    public void testInvalidPhoneNumber() {
        String phoneNumber = "12345";
        logger.info("Ввод некорректного номера телефона");
        loginPage.sendKeysPhoneNumber(phoneNumber);

        logger.info("Ошибка некорректного ввода номера телефона");
        Assertions.assertEquals("Введите корректный номер телефона.", loginPage.getTextInvalidPhoneNumber());
    }

    @Test
    @DisplayName("Логин с пустым кодом")
    public void testEmptyCode() {
        String phoneNumber = "375293438185";
        logger.info("Ввод номера телефона");
        loginPage.sendKeysPhoneNumber(phoneNumber);
        loginPage.clickJoin();
        logger.info("Ввод пустого кода");
        loginPage.sendKeysCode("");
    }

    @Test
    @DisplayName("Логин с некорректным кодом")
    public void testInvalidCode() {
        String phoneNumber = "375293438185";
        loginPage.sendKeysPhoneNumber(phoneNumber);
        loginPage.clickJoin();
        logger.info("Ввод некорректного кода");
        loginPage.sendKeysCode("ghhfjd");

        logger.info("Ошибка некорректного ввода кода");
        Assertions.assertEquals("Неверный код верификации", loginPage.getTextInvalidCode());
    }

    @AfterEach
    public void loginToSite() {
        loginPage.clickJoin();
    }
}
