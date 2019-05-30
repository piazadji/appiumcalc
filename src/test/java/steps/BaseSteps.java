package steps;

import infra.DriverHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import pages.BasePage;

import java.io.IOException;

public class BaseSteps {
    private AndroidDriver<MobileElement> driver;
    private BasePage basePage;

    public BaseSteps(AndroidDriver driver) {
        this.basePage = new BasePage(driver);
    }

    @Before
    public void beforeScenario() throws IOException {
        DriverHelper.initDriver();
    }

    @After
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
        }
    }
}
