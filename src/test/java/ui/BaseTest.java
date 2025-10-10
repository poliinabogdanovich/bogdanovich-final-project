package ui;

import by.dominos.singleton.WebDriverSingleton;
import org.junit.jupiter.api.AfterEach;

public class BaseTest {

    @AfterEach
    public void tearDown() {
        WebDriverSingleton.quit();
    }
}
