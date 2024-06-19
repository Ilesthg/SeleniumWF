package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int counter =0;
    int retryCounter = 2;


    @Override
    public boolean retry(ITestResult result) {

    while (counter < retryCounter)
    {
        counter++;
        return  true;
    }
        return false;
    }
}
