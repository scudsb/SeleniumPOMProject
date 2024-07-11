package org.example.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class LoadConfigFile {
    static Properties properties = new Properties();
    static FileReader fileReader;
    public static Properties loadConfigProperties() {
        try {
            String userDir = System.getProperty("user.dir");
            fileReader = new FileReader(userDir + "/src/main/resources/config.properties");
            properties.load(fileReader);
        } catch (IOException ex) {
            System.out.println("Exception Caught - " + ex.getMessage());
            System.out.println("Stack Trace - " + Arrays.toString(ex.getStackTrace()));
        }
        return properties;
    }
}
