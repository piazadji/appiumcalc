package infra;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {

    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities cap;

    public void startServer() throws IOException {
        cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");

        builder = new AppiumServiceBuilder();
        builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));
        builder.withIPAddress(Configuration.getServerIp());
        builder.usingPort(Integer.parseInt(Configuration.getServerPort()));
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public void stopServer() {
        service.stop();
    }

    public boolean checkIfServerIsRunning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

}