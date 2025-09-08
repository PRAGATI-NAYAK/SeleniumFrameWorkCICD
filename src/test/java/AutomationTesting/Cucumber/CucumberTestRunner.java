package AutomationTesting.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/AutomationTesting/Cucumber",glue="AutomationTesting.StepDefinitions"
,monochrome=true,tags="@Regression",plugin= {"html:target/cucumber-reports/cucumber.html"})
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}
