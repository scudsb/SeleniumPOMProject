package org.example.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Properties;

public class SetUpDriver {
    public static WebDriver webDriver;
    public static Properties config;
    public static Properties locators;

    /**
     * Method to set up the driver based on the browser mentioned in the config file
     */
    public static WebDriver setUpDriver() {
        System.out.println("Inside setUpDriver");
        if (config == null) {
            config = LoadConfigFile.loadConfigProperties();
        }
        switch (config.getProperty("browser").toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            default:
                System.out.println("Incorrect browser specified. Initiating chrome.");
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
        }
        webDriver.get(config.getProperty("test_url"));
        webDriver.manage().window().maximize();
        return webDriver;
    }
}
