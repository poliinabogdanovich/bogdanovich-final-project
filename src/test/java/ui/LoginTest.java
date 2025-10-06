package ui;

import by.dominos.ui.HomePage;
import by.dominos.ui.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
//    private static LoginPage loginPage;
//    private static HomePage homePage;

    @Test
    public void login(){
        HomePage homePage = new HomePage();
        homePage.openSite();
        homePage.acceptCookies();
        homePage.clickUserAccount();

        LoginPage loginPage = new LoginPage();
        loginPage.clickForClients();
        loginPage.sendKeysPhoneNumber("375291234567");
        loginPage.clickJoin();
    }
}
