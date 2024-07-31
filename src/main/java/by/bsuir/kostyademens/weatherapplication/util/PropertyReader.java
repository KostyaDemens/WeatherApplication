package by.bsuir.kostyademens.weatherapplication.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static Properties fromFile(String filename) {
        Properties properties = new Properties();
        try (InputStream is = PropertyReader.class.getClassLoader().getResourceAsStream(filename)) {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
