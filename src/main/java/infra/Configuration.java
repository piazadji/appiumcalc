package infra;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Configuration {

    private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());
    private static Properties config = new Properties();

    private Configuration() {
        throw new AssertionError("This class should not have instances");
    }

    private static Properties getConfig() {
        if (config.size() == 0) {
            config = initConfiguration();
        }
        return config;
    }

    private static Properties initConfiguration() {
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/config/config.properties")) {
            config.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.severe("File doesn't exists");
            throw new UncheckedIOException("Properties file read problems", e);
        }
        return config;
    }

    public static String getAppiumPath() {
        return getConfig().getProperty("appiumPath");
    }

    public static String getDeviceName() {
        return getConfig().getProperty("deviceName");
    }

    private void setDeviceName(String name) {
        getConfig().setProperty("deviceName", name);
    }

    public static String getudid() {
        return getConfig().getProperty("udid");
    }

    private void setudid(String id) {
        getConfig().setProperty("udid", id);
    }

    public static String getPlatformName() {
        return getConfig().getProperty("platformName");
    }

    private void setPlatformName(String name) {
        getConfig().setProperty("platformName", name);
    }

    public static String getPlatformVersion() {
        return getConfig().getProperty("platformVersion");
    }

    private void setPlatformVersion(String version) {
        getConfig().setProperty("platformVersion", version);
    }

    public static String getServerUrl() {
        return String.format("http://%s:%s/wd/hub",
                getServerIp(), getServerPort());
    }

    static String getServerIp() {
        return getConfig().getProperty("serverIP");
    }

    static int getServerPort() {
        return Integer.parseInt(getConfig().getProperty("serverPort"));
    }


    public static String getAppPackage() {
        return getConfig().getProperty("appPackage");
    }

    public static String getAppActivity() {
        return getConfig().getProperty("appActivity");
    }

    public static String skipUnlock() {
        return getConfig().getProperty("skipUnlock");
    }

    public static String noReset() {
        return getConfig().getProperty("noReset");
    }
}
