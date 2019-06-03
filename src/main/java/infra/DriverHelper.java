package infra;

import java.time.Duration;
import java.util.logging.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverHelper {

    private static final Logger LOGGER = Logger.getLogger(DriverHelper.class.getName());

    public DriverHelper() {
    }

    public static void waitForClickableElement(AppiumDriver<MobileElement> driver, WebElement element, int timeoutInSeconds) {
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
