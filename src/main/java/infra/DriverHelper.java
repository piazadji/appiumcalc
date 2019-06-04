package infra;

import java.time.Duration;
import java.util.logging.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class DriverHelper {

    private static final Logger LOGGER = Logger.getLogger(DriverHelper.class.getName());

    private DriverHelper() {
        throw new AssertionError("Instance of this class should not be created");
    }

    public static void waitForClickableElement(AppiumDriver<MobileElement> driver, MobileElement element, Duration timeout) {
        new FluentWait<>(driver).
                withTimeout(timeout).
                pollingEvery(Duration.ofMillis(500L)).
                ignoring(NoSuchElementException.class).
                until(ExpectedConditions.elementToBeClickable(element));
    }
}
