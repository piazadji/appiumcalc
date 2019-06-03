package steps;

import java.io.IOException;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import infra.AppiumServer;
import infra.Configuration;

public class CommonSteps extends ContextSteps {

    AppiumServer appiumServer = new AppiumServer();
    private ContextSteps context;

    public CommonSteps(ContextSteps context) {
        this.context = context;
    }

    @Before
    public void beforeScenario() throws IOException {
        if (!appiumServer.checkIfServerIsRunning(Integer.parseInt(Configuration.getServerPort()))) {
            appiumServer.startServer();
        }
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
