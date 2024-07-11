package testcases;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pageEvents.DashboardPage;
import org.example.pageEvents.HomePage;
import org.example.pageEvents.LoginPage;
import org.testng.annotations.Test;


public class QATests extends baseTest{
    public static Logger logger = LogManager.getLogger(QATests.class);
    HomePage homePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    @Test
    public void enterCredentialsAndLogin(){
        homePage = new HomePage(webDriver);
        loginPage = new LoginPage(webDriver);
        dashboardPage = new DashboardPage(webDriver);
        logger.info("Inside enterCredentialsAndLogin method in testcases.QATests");
        homePage.verifySite();
        logger.info("Site Title Verified");
        homePage.initialLogIn();
        loginPage.verifyIfPageIsLoaded();
        logger.info("Login page loaded successfully");
        loginPage.enterEmail("shamashyam2024@gmail.com");
        loginPage.enterPassword("Charlotte");
        loginPage.loginToApplication();
        dashboardPage.verifyDashboardIsLoaded();
        logger.info("Dashboard page loaded successfully");
        testLogger.log(Status.INFO, "Entered Credentials and Logged in Successfully");
    }
}
