package utilities.Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int counter =0;
    int retryCounter = 1;


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
