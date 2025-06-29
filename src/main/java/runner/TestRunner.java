package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import utils.TestUtils;
import org.testng.annotations.DataProvider;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


@CucumberOptions(

        features = "src/test/java/features",
        glue= {"stepDefinitions"},
        tags = "@AddCart",
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json",
        "rerun:target/failedrerun.txt"},
        monochrome = true,
        publish = true
        )


public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider()
    public Object[][] scenarios(){
        return super.scenarios();
    }
    public static WebDriver driver;
    public static Properties prop;
    public static String browser;



    @BeforeClass
    public static void before() {
        System.out.println("Before - "+System.currentTimeMillis());
    }

    @AfterClass
    public static void after() {
        System.out.println("After - "+System.currentTimeMillis());
    }


    @BeforeTest

    public static void initialise(){
        try{
            prop= new Properties();
            FileInputStream FIS= new FileInputStream("src/main/java/config/config.properties");
            prop.load(FIS);
            browser = (String) prop.get("browser");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")){

        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.IMPLICIT_WAIT));

        driver.get(prop.getProperty("url"));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtils.PAGE_LOAD_TIMEOUT));

    }

    @AfterTest(alwaysRun = true)
    public static void closeBrowser(){
        driver.quit();
    }



}
