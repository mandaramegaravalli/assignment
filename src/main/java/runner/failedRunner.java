package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(

        features = {"@target/features"},
        glue= {"stepDefinitions"},

        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json",
                "rerun:target/failedrerun.txt"},
        monochrome = true,
        publish = true
)

public class failedRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider()
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
