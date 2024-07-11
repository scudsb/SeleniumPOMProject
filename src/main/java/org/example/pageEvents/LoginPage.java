package org.example.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utilities.SetUpDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends SetUpDriver {
    public static Logger logger = LogManager.getLogger(LoginPage.class);
    WebDriver webDriver;
    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//div[contains(text(),'Login') and contains(@class,'button')]")
    WebElement finalLoginIn;

    public LoginPage(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }
    public void verifyIfPageIsLoaded(){
        logger.debug("Inside verifyIfPageIsLoaded");
        try {
            FluentWait fluentWait = new FluentWait(webDriver).withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
            fluentWait.until(ExpectedConditions.visibilityOf(finalLoginIn));
            Assert.assertTrue(true, "Login page loaded");
        }catch (WebDriverException ex){
            Assert.fail( "Failure in loading login page");
            System.out.println("Exception caught - " + ex.getMessage());
        }
    }

    public void enterEmail(String strEmail){
        logger.debug("Inside enterEmail");
        email.sendKeys(strEmail);
    }

    public void enterPassword(String strPassword){
        logger.debug("Inside enterPassword");
        password.sendKeys(strPassword);
    }

    public void loginToApplication(){
        logger.debug("Inside loginToApplication");
        finalLoginIn.click();
    }
}
