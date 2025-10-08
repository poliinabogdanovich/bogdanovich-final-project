package ui;

import by.dominos.ui.HomePage;
import by.dominos.ui.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    private static final Logger logger = LogManager.getLogger();

    @Test
    @DisplayName("Логин с корректным номером телефона")
    public void test1(){
        homePage.openSite();
        homePage.acceptCookies();
        homePage.clickUserAccount();

        loginPage.clickForClients();
        String phoneNumber = "375293438185";
        logger.info("Ввод номера телефона");
        loginPage.sendKeysPhoneNumber(phoneNumber);
        loginPage.clickJoin();
        loginPage.sendKeysCode("839949");
        loginPage.clickJoin();
    }
}
