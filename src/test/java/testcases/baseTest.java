package testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.utilities.LoadConfigFile;
import org.example.utilities.SetUpDriver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;
import org.apache.logging.log4j.*;


public class baseTest {
    public static Properties config;
    public static ExtentReports extentReport = new ExtentReports();
    public static ExtentSparkReporter sparkReporter;
    public static ExtentTest testLogger;
    public static WebDriver webDriver;
    public static String screenshotFile;
    public static Logger logger = LogManager.getLogger(baseTest.class);;

    /**
     * Setting up the reporter and reporting configuration using spark reporter and reading values from config file
     */
    @BeforeTest
    public static void beforeTest(){
        try {
            logger.info("Inside method - beforeTest");
            config = LoadConfigFile.loadConfigProperties();
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + config.getProperty("report_folder") + config.getProperty("report_name"));
            extentReport.attachReporter(sparkReporter);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle(config.getProperty("document_title"));
            sparkReporter.config().setReportName(config.getProperty("report_name"));
        }catch(Exception ex){
            logger.error("Exception caught - " + Arrays.toString(ex.getStackTrace()));
        }
    }

    /**
     * Method to create an extent test instance and set up the driver
     * @param testMethod
     */
    @BeforeMethod
    public static void beforeMethod(Method testMethod) {
        try {
            logger.info("Inside method - beforeTest");
            testLogger = extentReport.createTest(testMethod.getName());
            webDriver = SetUpDriver.setUpDriver();
            screenshotFile = System.getProperty("user.dir") + config.getProperty("screenshot_folder") +
                    testMethod.getName() + ".png";
        } catch (Exception ex) {
            logger.error("Exception caught - " + Arrays.toString(ex.getStackTrace()));
        }
    }


    /**
     * Method to log the test status after the test case execution using extent test
     * @param testResult
     */
    @AfterMethod
    public static void afterMethod(ITestResult testResult){
        try {
            logger.info("Inside method - afterMethod");
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS:
                    logger.debug("Test status is success");
                    testLogger.log(Status.PASS, MarkupHelper.createLabel(testResult.getName() + " - Test Case Passed", ExtentColor.GREEN));
                    break;
                case ITestResult.FAILURE:
                    logger.debug("Test status is failure");
                    testLogger.log(Status.FAIL, MarkupHelper.createLabel(testResult.getName() + " - Test Case Failed", ExtentColor.RED));
                    System.out.println(screenshotFile);
                    testLogger.log(Status.FAIL, MarkupHelper.createLabel(testResult.getThrowable().getMessage(), ExtentColor.RED),
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotFile).build());
                    break;
                case ITestResult.SKIP:
                    logger.debug("Test status is skip");
                    testLogger.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName() + " - Test Case Failed", ExtentColor.AMBER));
                    break;
                default:
                    logger.debug("Test status is incorrect");
                    break;
            }
            webDriver.close();
            webDriver.quit();
        } catch (Exception ex) {
            logger.error("Exception caught - " + Arrays.toString(ex.getStackTrace()));
        }
    }

    /**
     * Method to close & quite driver instances after all test cases are complete and flush out the extent test
     */
    @AfterTest
    public static void afterTest(){
        logger.info("Inside method - afterTest");
        extentReport.flush();
        webDriver.quit();
    }
}
