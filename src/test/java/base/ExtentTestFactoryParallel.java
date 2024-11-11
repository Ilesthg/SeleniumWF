package base;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestFactoryParallel
{
    //Singleton Patter -->
    /*Singleton Pattern:

    In your design, you're using the getInstance() method to enforce that there is only one instance of ExtentTestFactoryParallel via a Singleton pattern.
    The Singleton guarantees that there will only be one instance of ExtentTestFactoryParallel shared across your entire test suite.
    --------------------------
    You only need ThreadLocal to be static if multiple instances of the class (i.e., ExtentTestFactoryParallel) are created
    and you want to ensure that each instance shares the same ThreadLocal variable. Since your design uses a Singleton, this is not the case.
    ------------------
    If NOT using Singleton and  REMOVING STATIC for ThreadLocal will cause the ThreadLocal<ExtentTest> to become instance-specific instead of class-specific.
    tHIS means -> If multiple instances of the class are created, every instance would have its own ThreadLocal<ExtentTest>.ThreadLocal would be re-initialized, and tests may not get the correct ExtentTest
    */
    private ExtentTestFactoryParallel() {
    }

    private static final ExtentTestFactoryParallel instance = new ExtentTestFactoryParallel();

    public static ExtentTestFactoryParallel getInstance() {
        return instance;
    }

    private   ThreadLocal<ExtentTest> loggerTest = new ThreadLocal<ExtentTest>();


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
