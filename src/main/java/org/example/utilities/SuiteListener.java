package org.example.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class SuiteListener extends SetUpDriver implements ITestListener, IAnnotationTransformer {
    @Override
    public void onTestFailure(ITestResult result) {
       try{
           String fileName = System.getProperty("user.dir") + config.getProperty("screenshot_folder") +
                   result.getMethod().getMethodName() + ".png";
           File screenshotFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
           FileUtils.copyFile(screenshotFile, new File(fileName));
        }catch (IOException ex){
            System.out.println("Exception Caught - " + ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyser.class);
    }
}
