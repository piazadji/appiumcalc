package steps;

import java.io.IOException;
import java.net.URL;

import infra.Configuration;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContextSteps {
    protected static AndroidDriver<MobileElement> driver;
    private static WebDriverWait wait;

    public AndroidDriver<MobileElement> getDriver() {
        return driver;
    }

    static void initDriver() throws IOException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        caps.setCapability("deviceName", Configuration.getDeviceName());
        caps.setCapability("skipUnlock", Configuration.skipUnlock());
        caps.setCapability("appPackage", Configuration.getAppPackage());
        caps.setCapability("appActivity", Configuration.getAppActivity());
        caps.setCapability("noReset", Configuration.noReset());
        driver = new AndroidDriver<MobileElement>(new URL(Configuration.getServerUrl()), caps);
        wait = new WebDriverWait(driver, 10);
    }
}
