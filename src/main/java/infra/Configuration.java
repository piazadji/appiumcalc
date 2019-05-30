package infra;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Configuration {

    private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());
    private static Properties config = new Properties();

    public Configuration() {
    }

    private static Properties getConfig() throws IOException {
        if (config == null) {
            config = initConfiguration();
        }
        return config;
    }

    private static Properties initConfiguration() throws IOException {
        InputStream iStream = null;
        try {
            FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties");
            config.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.severe("File doesn't exists");
        }
        return config;
    }

    static String getDeviceName() throws IOException {
        return getConfig().getProperty("deviceName");
    }

    private void setDeviceName(String name) throws IOException {
        getConfig().setProperty("deviceName", name);
    }

    static String getudid() throws IOException {
        return getConfig().getProperty("udid");
    }

    private void setudid(String id) throws IOException {
        getConfig().setProperty("udid", id);
    }

    static String getPlatformName() throws IOException {
        return getConfig().getProperty("platformName");
    }

    private void setPlatformName(String name) throws IOException {
        getConfig().setProperty("platformName", name);
    }

    static String getPlatformVersion() throws IOException {
        return getConfig().getProperty("platformVersion");
    }

    private void setPlatformVersion(String version) throws IOException {
        getConfig().setProperty("platformVersion", version);
    }

    static String getServerUrl() throws IOException {
        return getConfig().getProperty("serverUrl");
    }

    static String getAppPackage() throws IOException {
        return getConfig().getProperty("appPackage");
    }

    static String skipUnlock() throws IOException {
        return getConfig().getProperty("skipUnlock");
    }

    static String noReset() throws IOException {
        return getConfig().getProperty("noReset");
    }
}
