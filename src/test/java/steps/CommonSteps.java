package steps;

import java.io.IOException;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CommonSteps extends ContextSteps {

private ContextSteps context;

public CommonSteps(ContextSteps context) {
    this.context = context;
}

    @Before
    public void beforeScenario() throws IOException {
        driver = new ContextSteps().getDriver();
        initDriver();
    }

    @After
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
        }
    }
}
