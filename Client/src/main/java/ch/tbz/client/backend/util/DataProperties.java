package ch.tbz.client.backend.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataProperties {
    private static boolean isDarkmode;
    private static boolean isFullscreen;

    public static void loadData() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data.properties"));
        } catch (IOException ignored) {}
        isDarkmode = Boolean.parseBoolean(properties.getProperty("isDarkmode"));
        isFullscreen = Boolean.parseBoolean(properties.getProperty("isFullscreen"));
    }

    public static boolean isIsDarkmode() {
        return isDarkmode;
    }

    public static boolean isIsFullscreen() {
        return isFullscreen;
    }

    public static void setIsDarkmode(boolean isDarkmode) {
        DataProperties.isDarkmode = isDarkmode;
    }

    public static void setIsFullscreen(boolean isFullscreen) {
        DataProperties.isFullscreen = isFullscreen;
    }
}
