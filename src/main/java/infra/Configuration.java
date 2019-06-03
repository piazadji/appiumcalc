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
        if (config.size() == 0) {
            config = initConfiguration();
        }
        return config;
    }

    private static Properties initConfiguration() throws IOException {
        InputStream iStream = null;
        try {
            FileInputStream inputStream = new FileInputStream("src/test/resources/config/config.properties");
            config.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.severe("File doesn't exists");
        }
        return config;
    }

    public static String getDeviceName() throws IOException {
        return getConfig().getProperty("deviceName");
    }

    private void setDeviceName(String name) throws IOException {
        getConfig().setProperty("deviceName", name);
    }

    public static String getudid() throws IOException {
        return getConfig().getProperty("udid");
    }

    private void setudid(String id) throws IOException {
        getConfig().setProperty("udid", id);
    }

    public static String getPlatformName() throws IOException {
        return getConfig().getProperty("platformName");
    }

    private void setPlatformName(String name) throws IOException {
        getConfig().setProperty("platformName", name);
    }

    public static String getPlatformVersion() throws IOException {
        return getConfig().getProperty("platformVersion");
    }

    private void setPlatformVersion(String version) throws IOException {
        getConfig().setProperty("platformVersion", version);
    }

    public static String getServerUrl() throws IOException {
        String url = String.format("http://%s:%s/wd/hub",
                getServerIp(), getServerPort());
        return url;
    }

    public static String getServerIp() throws IOException {
        return getConfig().getProperty("serverIP");
    }

    public static String getServerPort() throws IOException {
        return getConfig().getProperty("serverPort");
    }


    public static String getAppPackage() throws IOException {
        return getConfig().getProperty("appPackage");
    }

    public static String getAppActivity() throws IOException {
        return getConfig().getProperty("appActivity");
    }

    public static String skipUnlock() throws IOException {
        return getConfig().getProperty("skipUnlock");
    }

    public static String noReset() throws IOException {
        return getConfig().getProperty("noReset");
    }
}
