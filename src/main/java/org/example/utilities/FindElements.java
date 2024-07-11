package org.example.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FindElements extends SetUpDriver {
    public enum LocatorType {ID, XPATH, LINKTEXT, CSSSELECTOR, CLASSNAME};
    public static WebElement waitForElement(LocatorType locatorType, String locatorKey) {
        WebElement webElement = null;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        try {
            switch (locatorType) {
                case ID:
                    webElement = webDriver.findElement(By.id(locators.getProperty(locatorKey)));
                    WebElement finalWebElementID = webElement;
                    wait.until(driver -> finalWebElementID.isDisplayed());
                    break;
                case XPATH:
                    webElement = webDriver.findElement(By.xpath(locators.getProperty(locatorKey)));
                    WebElement finalWebElementXPATH = webElement;
                    wait.until(driver -> finalWebElementXPATH.isDisplayed());
                    break;
                case LINKTEXT:
                    webElement = webDriver.findElement(By.linkText(locators.getProperty(locatorKey)));
                    WebElement finalWebElementLINKTEXT = webElement;
                    wait.until(driver -> finalWebElementLINKTEXT.isDisplayed());
                    break;
                case CSSSELECTOR:
                    webElement = webDriver.findElement(By.cssSelector(locators.getProperty(locatorKey)));
                    WebElement finalWebElementCSS = webElement;
                    wait.until(driver -> finalWebElementCSS.isDisplayed());
                    break;
                case CLASSNAME:
                    webElement = webDriver.findElement(By.className(locators.getProperty(locatorKey)));
                    WebElement finalWebElementCLASSNAME = webElement;
                    wait.until(driver -> finalWebElementCLASSNAME.isDisplayed());
                    break;
                default:
                    System.out.println("Incorrect locator type is provided.");
                    throw new WebDriverException("Incorrect Locator Type provided - " + locatorType);
            }
        } catch (WebDriverException ex) {
            System.out.println("Exception caught - " + ex.getMessage());
            throw new WebDriverException("No web element found for locator - " + locatorKey +
                    "; locator type - " + locatorType);
        }
        return webElement;
    }

}
