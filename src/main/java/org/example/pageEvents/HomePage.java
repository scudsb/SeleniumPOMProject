package org.example.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
    public static Logger logger = LogManager.getLogger(HomePage.class);
    WebDriver webDriver;
    @FindBy(xpath = "(//a[@class=\"brand-name\"])[3]")
    WebElement title;

    @FindBy(xpath = "//a[contains(.,\"Log In\")]")
    WebElement logIn;

    public HomePage(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }
    public void verifySite(){
        logger.debug("Inside verifySite");
        String actualTitle = webDriver.getTitle();
        String expectedTitle = "#1 Free CRM Power Up your Entire Business Free Forever customer relationship management";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    public void initialLogIn(){
        logger.debug("Inside initialLogIn");
        logIn.click();
    }
}
