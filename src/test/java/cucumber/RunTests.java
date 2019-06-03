package cucumber;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/html/", "json:target/cucumber-report/cucumber.json"},
        features = {"src/test/resources/features"}, glue = {"steps"}, tags = {"@Run"})
public class RunTests {

}
