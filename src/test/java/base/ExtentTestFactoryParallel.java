package base;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestFactoryParallel
{
    private ExtentTestFactoryParallel() {
    }

    private static final ExtentTestFactoryParallel instance = new ExtentTestFactoryParallel();

    public static ExtentTestFactoryParallel getInstance() {
        return instance;
    }

    private static  ThreadLocal<ExtentTest> loggerTest = new ThreadLocal<ExtentTest>();


    public void setExtentTest(ExtentTest loggerT) {

        loggerTest.set(loggerT);
    }

    public ExtentTest getExtentTest() {
        return loggerTest.get();
    }
    public void removeExtentObject(){
        loggerTest.remove();
    }



}
