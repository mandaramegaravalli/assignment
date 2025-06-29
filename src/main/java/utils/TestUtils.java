package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;


public class TestUtils {

    public static long PAGE_LOAD_TIMEOUT = 50;
    public static long IMPLICIT_WAIT = 20;
    private WebDriverWait wait;
    private Logger log = Logger.getLogger(TestUtils.class);

    public void waitTimeInSeconds(By element) {
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe((By) element, 1));
        } catch (Exception e) {
            log.error(e);
        }

    }
}
