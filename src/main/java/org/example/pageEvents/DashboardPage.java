package org.example.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;

public class DashboardPage {
    public static Logger logger = LogManager.getLogger(DashboardPage.class);
    WebDriver webDriver;
    @FindBy(xpath = "//b[normalize-space()='Home']")
    WebElement homeText;

    public DashboardPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void verifyDashboardIsLoaded(){
        logger.debug("Inside verifyDashboardIsLoaded");
        try {
            FluentWait fluentWait = new FluentWait(webDriver).withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
            fluentWait.until(ExpectedConditions.visibilityOf(homeText));
            String text = homeText.getText();
            Assert.assertEquals(text, "Home");
        }catch (WebDriverException ex){
            Assert.fail( "Failure in loading dashboard page");
            System.out.println("Exception caught - " + ex.getMessage());
        }
    }
}
