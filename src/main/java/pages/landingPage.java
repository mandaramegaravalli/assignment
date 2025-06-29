package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import runner.TestRunner;

import org.testng.log4testng.Logger;
import utils.TestUtils;

import java.util.List;

public class landingPage extends TestRunner {
    private Logger log = Logger.getLogger(landingPage.class);
    private TestUtils utils = new TestUtils();


    private By searchBar = By.xpath("//input[@id='twotabsearchtextbox']");
    private By searchIcon = By.xpath("//input[@id='nav-search-submit-button']");
    private By priceFilter = By.xpath("//span[text()='Price']");
    private By minRange = By.xpath("//input[@id='p_36/range-slider_slider-item_lower-bound-slider']");
    private By maxRange = By.xpath("//input[@id='p_36/range-slider_slider-item_upper-bound-slider']");
    private By priceSubmitButton = By.xpath("//input[contains(@aria-label,'Go') and @class='a-button-input']");
    private By sortDropDown = By.xpath("//select[@id='s-result-sort-select']");

    public landingPage(WebDriver driver) {
        this.driver = driver;
    }


    public void sendkeysToSearchBar(String searchText) throws InterruptedException {
        utils.waitTimeInSeconds(searchBar);
        driver.findElement(searchBar).clear();
        Thread.sleep(5000);
        driver.findElement(searchBar).sendKeys(searchText);
    }

    public void clickOnSearchIcon() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(searchIcon).click();
    }

    public void selectBrand(String brand) {
        try {
            WebElement brandCheckbox = driver.findElement(By.xpath(
                    "//span[text()='" + brand + "']/preceding-sibling::div//input[@type='checkbox']"
            ));
            Thread.sleep(3000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", brandCheckbox);

            Thread.sleep(8000); // Wait for page refresh
        } catch (Exception e) {
            System.out.println("Brand not found or not clickable: " + brand);
        }
    }

    public void filterPriceRange(String min, String max) {
        try {
            utils.waitTimeInSeconds(priceFilter);
            Thread.sleep(1000);
            utils.waitTimeInSeconds(maxRange);
            WebElement minPrice = driver.findElement(minRange);
            WebElement maxPrice = driver.findElement(maxRange);
            WebElement submit = driver.findElement(priceSubmitButton);
            Thread.sleep(2000);

            Actions actions = new Actions(driver);
            actions.moveToElement(minPrice).click().sendKeys(min).build().perform();

            Thread.sleep(2000);

            actions.moveToElement(maxPrice).click().sendKeys(max).build().perform();

            Thread.sleep(5000);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Price filter error");
        }
    }

    public List<WebElement> getProductPrices() {
        return driver.findElements(By.cssSelector("span.a-price > span.a-offscreen"));
    }

    public void sortHighToLow() {
        try {
            utils.waitTimeInSeconds(sortDropDown);
            Select s = new Select(driver.findElement(sortDropDown));
            s.selectByVisibleText("Price: High to Low");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Failed to sort " + e.getMessage());
        }
    }

    public void userOpensProductInNewWindow() {
        try {
            Thread.sleep(2000);
            WebElement firstProductLink = driver.findElement(By.xpath("(//div[@data-component-type='s-search-result'])[1]//h2"));

            Thread.sleep(2000);
            Actions a = new Actions(driver);
            a.keyDown(Keys.CONTROL).click(firstProductLink).keyUp(Keys.CONTROL).build().perform();

            System.out.println("Opened first product in new tab: " + firstProductLink);
        } catch (Exception e) {
            System.out.println("❌ Could not open first product: " + e.getMessage());
        }

    }

}

