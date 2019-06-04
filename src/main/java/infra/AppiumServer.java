package infra;

import java.io.File;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.net.UrlChecker;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumServer {

    private AppiumDriverLocalService service;

    public void startServer() {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");

        AppiumServiceBuilder builder = new AppiumServiceBuilder();

        builder.withAppiumJS(new File(Configuration.getAppiumPath()));
        builder.withIPAddress(Configuration.getServerIp());
        builder.usingPort(Configuration.getServerPort());
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public void stopServer() {
        service.stop();
    }

    public boolean checkIsServerRunning(Duration timeout) {
        try {
            URL status = new URL(Configuration.getServerUrl() + "/sessions");
            new UrlChecker().waitUntilAvailable(timeout.toMillis(), TimeUnit.MILLISECONDS, status);
            return true;
        } catch (UrlChecker.TimeoutException e) {
            return false;
        } catch (MalformedURLException e) {
            throw new UncheckedIOException("Can't parse URL", e);
        }
    }

}