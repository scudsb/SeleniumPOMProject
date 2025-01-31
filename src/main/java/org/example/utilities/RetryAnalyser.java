package org.example.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {

    int count = 0;
    int retryCount = 1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < retryCount){
            count++;
            return true;
        }
        return false;
    }
}
