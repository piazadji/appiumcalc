package infra;

import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestContext {
    private AndroidDriver<MobileElement> driver;

    public AndroidDriver<MobileElement> getDriver() {
        return driver;
    }

    public void initDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        caps.setCapability("deviceName", Configuration.getDeviceName());
        caps.setCapability("skipUnlock", Configuration.skipUnlock());
        caps.setCapability("appPackage", Configuration.getAppPackage());
        caps.setCapability("appActivity", Configuration.getAppActivity());
        caps.setCapability("noReset", Configuration.noReset());
        try {
            driver = new AndroidDriver<>(new URL(Configuration.getServerUrl()), caps);
        } catch (MalformedURLException e) {
            throw new UncheckedIOException("Can't parse URL", e);
        }
    }
}
