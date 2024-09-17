package utilities;

import base.ExtentReportNG;
import base.ExtentTestFactoryParallel;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ListenerThread implements ITestListener {
    private ExtentReports extentR;
    private ExtentTest logger;


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("init test from Listener and enter OnTestStart");
        ITestListener.super.onTestStart(result);

        //logger = ExtentTestFactoryParallel.getInstance().getExtentTest();
        logger = ExtentReportNG.getLoggerFromStaticMethod();


        // logger = extentR.createTest(result.getMethod().getMethodName());//******Declared in BaseTestParallel*********//
        // ExtentTestFactoryParallel.getInstance().setExtentTest(logger);//********Declared in BaseTestParallel*********//
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Entre a OntestSuccess");
        ITestListener.super.onTestSuccess(result);
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Entre a IF OntestSuccess");
            //logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test case SUCCESS", ExtentColor.GREEN));

            try {
                ScreenShotsForTests.successTestWithSS(result.getName() + " - Test case SUCCESS" , true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        ExtentTestFactoryParallel.getInstance().removeExtentObject();//*****************//


     /*   String ssDate = format.format(date);
        String FileName = System.getProperty("user.dir") + "/src/test/resources/screenshots/" + ssDate + "_" + result.getName() + ".png";
        File ss = ((TakesScreenshot) DriverFactoryParallel.getInstance().GetDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(ss, new File(FileName));
            ExtentTestFactoryParallel.getInstance().removeExtentObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println("Entre a OntestFailure");
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Entre a IF OntestFailure");
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test case failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable().toString() + " - Test case failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(Arrays.toString(result.getThrowable().getStackTrace()) + " - Test case failed", ExtentColor.RED));


            try {
                ScreenShotsForTests.failedTestWithSS(result.getName() + " - Test case failed",true);
            } catch (Exception e) {throw new RuntimeException(e);}
        }

        ExtentTestFactoryParallel.getInstance().removeExtentObject();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Entre a Skipped");
        ITestListener.super.onTestSkipped(result);
        if (result.getStatus() == ITestResult.SKIP) {
            System.out.println("Entre a IF Skipped");
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test case Skipped", ExtentColor.ORANGE));
            try {
                ScreenShotsForTests.skipTestWithSS(result.getTestName(), true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        ExtentTestFactoryParallel.getInstance().removeExtentObject();//*****************//
    }









    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }


    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        //  extentR = ExtentReportNG.setUpExtentReports();//******Declared in BaseTestParallel*********//
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        // extentR.flush(); //******Declared in BaseTestParallel*********//
    }
}
