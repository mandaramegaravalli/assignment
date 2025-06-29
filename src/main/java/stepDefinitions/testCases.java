package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.log4testng.Logger;
import pages.windowsPage;
import runner.TestRunner;
import pages.landingPage;

import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class testCases extends TestRunner {
    private Logger log = Logger.getLogger(testCases.class);

    private landingPage lp = new landingPage(driver);
    private windowsPage wp = new windowsPage(driver);
    public String parentId;
    public String childId;


    @Then("verify the title of the page")
    public void verifyTitleOfThePage() {
        log.info("Start: verifyTitleOfThePage");
        System.out.println("Start: verifyTitleOfThePage");
        String actualTitle = driver.getTitle();
        System.out.println("actual tile: " + actualTitle);
        String expectedTitle = "Amazon.in";
        Assert.assertTrue(actualTitle.contains(expectedTitle), "titles don't match");
        System.out.println("End: verifyTitleOfThePage");
        log.info("End: verifyTitleOfThePage");
    }

    @Given("user types smartwathces in search bar")
    public void userTypesSmartwatchesInSearchBar() {
        log.info("Start: userTypesSmartwatchesInSearchBar()");
        System.out.println("Start: userTypesSmartwatchesInSearchBar()");
        try {
            lp.sendkeysToSearchBar("smartwatches");
        } catch (Exception e) {
            log.error(e);
        }
        System.out.println("End: userTypesSmartwatchesInSearchBar()");
        log.info("End: userTypesSmartwatchesInSearchBar()");
    }

    @And("user clicks on search icon")
    public void userClicksOnSearchIcon() {
        log.info("Start: userClicksOnSearchIcon()");
        System.out.println("Start: userClicksOnSearchIcon()");
        try {
            lp.clickOnSearchIcon();
        } catch (Exception e) {
            log.error(e);
        }
        System.out.println("End: userClicksOnSearchIcon()");
        log.info("End: userClicksOnSearchIcon()");

    }

    @And("user selects the {string}")
    public void userSelectsTheBrand(String brand) {
        log.info("Start: userSelectsTheBrand()");
        System.out.println("Start: userSelectsTheBrand()");
        lp.selectBrand(brand);
        System.out.println("End: userSelectsTheBrand()");
        log.info("End: userSelectsTheBrand()");
    }

    @And("user filters {string} and {string}")
    public void userFiltersPrice(String min, String max) {
        log.info("Start: userFiltersPrice()");
        System.out.println("Start: userFiltersPrice()");
        lp.filterPriceRange(min, max);
        System.out.println("End: userFiltersPrice()");
        log.info("End: userFiltersPrice()");
    }

    @Then("verify that results are in {string} and {string} range")
    public void verifyThatResultsAreInPriceRange(String minPrice, String maxPrice) {
        log.info("Start: verifyThatResultsAreInPriceRange()");
        System.out.println("Start: verifyThatResultsAreInPriceRange()");
        int min = Integer.parseInt(minPrice);
        int max = Integer.parseInt(maxPrice);
        List<WebElement> prices = lp.getProductPrices();

        Assert.assertTrue(prices.size() > 0, "No prices found in results");

        for (WebElement priceElement : prices) {
            try {
                String priceText = priceElement.getText().replaceAll("[^\\d]", "");
                int price = Integer.parseInt(priceText);
                System.out.println("Product Price: ₹" + price);
                Assert.assertTrue(price >= min && price <= max, "Price not in range: " + price);
            } catch (Exception e) {
                System.out.println("Skipping unreadable price: " + priceElement.getText());
            }
        }
        System.out.println("End: verifyThatResultsAreInPriceRange()");
        log.info("End: verifyThatResultsAreInPriceRange()");
    }

    @And("user sorts them from high to low")
    public void userSortsHighToLow() {
        log.info("Start: userSortsHighToLow()");
        System.out.println("Start: userSortsHighToLow()");
        lp.sortHighToLow();
        System.out.println("End: userSortsHighToLow()");
        log.info("End: userSortsHighToLow()");
    }

    @When("user opens the product with high price in new window")
    public void userOpensTheProductWithHighPriceInNewWindow() {
        log.info("Start: userOpensTheProductWithHighPriceInNewWindow()");
        System.out.println("Start: userOpensTheProductWithHighPriceInNewWindow()");
        lp.userOpensProductInNewWindow();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> IT = windows.iterator();
        parentId = IT.next();
        childId = IT.next();
        driver.switchTo().window(childId);
        System.out.println("End: userOpensTheProductWithHighPriceInNewWindow()");
        log.info("End: userOpensTheProductWithHighPriceInNewWindow()");

    }

    @Then("add product to cart")
    public void addProductToCart() {
        log.info("Start: addProductToCart()");
        System.out.println("Start: addProductToCart()");
        wp.clickOnAddToCartBuuton();
        System.out.println("End: addProductToCart()");
        log.info("End: addProductToCart()");

    }

    @And("user closes the new window")
    public void userClosesTheNewWindow() {
        log.info("Start: addProductToCart()");
        System.out.println("Start: addProductToCart()");
        String current = driver.getWindowHandle();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
        }
        System.out.println("End: addProductToCart()");
        log.info("End: addProductToCart()");
    }
}


