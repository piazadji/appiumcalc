package infra;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.logging.Logger;

public class DriverHelper {
    private static AndroidDriver<MobileElement> driver;
    private static WebDriverWait wait;

    private static final Logger LOGGER = Logger.getLogger(DriverHelper.class.getName());

    public DriverHelper() {
    }

    public static void initDriver() throws IOException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", Configuration.getDeviceName());
        caps.setCapability("udid", Configuration.getudid());
        caps.setCapability("platformName", Configuration.getPlatformName());
        caps.setCapability("platformVersion", Configuration.getPlatformVersion());
        caps.setCapability("skipUnlock", Configuration.skipUnlock());
        caps.setCapability("appPackage", Configuration.getAppPackage());
        caps.setCapability("noReset", Configuration.noReset());
        driver = new AndroidDriver<MobileElement>(new URL(Configuration.getServerUrl()), caps);
        wait = new WebDriverWait(driver, 10);
    }

    public static void waitForClickableElement(AndroidElement element, int timeoutInSeconds) {
        long finalTimeout = (long) (timeoutInSeconds * 1000);
        Wait wait = (new FluentWait(driver)).withTimeout(Duration.ofMillis(finalTimeout)).pollingEvery(Duration.ofMillis(500L)).ignoring(NoSuchElementException.class);
        Object ret = null;

        try {
            ret = wait.until(ExpectedConditions.elementToBeClickable(element));
        } finally {
            if (ret instanceof WebElement) {
                LOGGER.info("Element was clickable.");
            } else {
                LOGGER.severe("Element wasn't clickable after " + timeoutInSeconds + " seconds!");
            }

        }

    }
}
