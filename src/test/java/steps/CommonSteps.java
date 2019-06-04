package steps;

import java.time.Duration;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import infra.AppiumServer;
import infra.TestContext;

public class CommonSteps {

    private AppiumServer appiumServer = new AppiumServer();
    private TestContext context;

    public CommonSteps(TestContext context) {
        this.context = context;
    }

    @Before
    public void beforeScenario() {
        if (!appiumServer.checkIsServerRunning(Duration.ofMillis(2000))) {
            appiumServer.startServer();
        }
        context.initDriver();
    }

    @After
    public void afterScenario() {
        if (context.getDriver() != null) {
            context.getDriver().quit();
        }
    }
}
