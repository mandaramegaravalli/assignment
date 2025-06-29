package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;
import utils.TestUtils;

import java.time.Duration;
import runner.TestRunner;

public class windowsPage extends TestRunner {

    private Logger log = Logger.getLogger(windowsPage.class);
    private TestUtils utils = new TestUtils();
    WebDriverWait wait;

    public windowsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private By addToCart = By.xpath("(//*[@id='add-to-cart-button'])[2]");

    public void clickOnAddToCartBuuton() {
        try {
            // Scroll to the button to ensure it's in view
            WebElement addToCartBtn = wait.until(ExpectedConditions.presenceOfElementLocated(addToCart));
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);


            // Wait until the button is actually clickable
            Thread.sleep(5000);
            addToCartBtn.click();

            System.out.println("Product added to cart");
        } catch (ElementClickInterceptedException e) {
            System.out.println("Add to cart button was intercepted. Retrying with JS click");
            WebElement addToCartBtn = driver.findElement(addToCart);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
        } catch (Exception e) {
            System.out.println("Could not click 'Add to Cart' button: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
