package utilities.Listeners;

import base.ExtentReportNG;
import base.Singleton_ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ScreenShotsForTests;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class ListenerThread implements ITestListener, IAnnotationTransformer {
    private ExtentReports extentR;
    private ExtentTest logger;


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("init test from Listener and enter OnTestStart");
        ITestListener.super.onTestStart(result);

        //logger = Singleton_ExtentTest.getInstance().getExtentTest();
        //  logger = ExtentReportNG.getLoggerFromStaticMethod();


        logger = extentR.createTest(result.getMethod().getMethodName());//******Declared in BaseTestParallel*********//
        Singleton_ExtentTest.getInstance().setExtentTest(logger);//********Declared in BaseTestParallel*********//
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Entre a OntestSuccess");
        ITestListener.super.onTestSuccess(result);
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Entre a IF OntestSuccess");
            //logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test case SUCCESS", ExtentColor.GREEN));


            ScreenShotsForTests.successTestWithSS(result.getName() + " - Test case SUCCESS", true);

        }

        // Singleton_ExtentTest.getInstance().removeExtentObject();//*****************//


    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println("Entre a OntestFailure");
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Entre a IF OntestFailure");

            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test case failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable().toString() + " - Test case failed", ExtentColor.RED));
           // logger.log(Status.FAIL, MarkupHelper.createLabel(methodCustomizeLogs(result), ExtentColor.GREY)); // Custom logs
            logger.log(Status.FAIL, MarkupHelper.createLabel(Arrays.toString(result.getThrowable().getStackTrace()) + " - Test case failed", ExtentColor.RED));


            ScreenShotsForTests.failedTestWithSS(result.getName() + " - Test case failed", true);

        }

        //   Singleton_ExtentTest.getInstance().removeExtentObject();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Entre a Skipped");
        ITestListener.super.onTestSkipped(result);
        if (result.getStatus() == ITestResult.SKIP) {
            System.out.println("Entre a IF Skipped");
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test case Skipped", ExtentColor.ORANGE));

            ScreenShotsForTests.skipTestWithSS(result.getTestName(), true);


        }
        //   Singleton_ExtentTest.getInstance().removeExtentObject();//*****************//
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
        extentR = ExtentReportNG.setUpExtentReports();//******Declared in BaseTestParallel*********//
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        if (Objects.nonNull(extentR)) {//******Declared in BaseTestParallel*********//
            extentR.flush();
        }
        try {
            Desktop.getDesktop().browse(new File(System.getProperty("user.dir") + "/src/test/resources/reports/SDTE/index.html").toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Singleton_ExtentTest.getInstance().removeExtentObject();

    }

    private String methodCustomizeLogs(ITestResult result) {
        Throwable throwable = result.getThrowable();
        String stackTrace = Arrays.toString(throwable.getStackTrace())
                .replace(",", "<br>") // Replace comma with a line break for better readability
                .replace("[", "")     // Remove the square brackets
                .replace("]", "");    // Remove the square brackets

// Create a formatted error message
         String errorMessage = "<b>Test case failed:</b> " + throwable.getMessage() + "<br>" +
                "<b>Stack Trace:</b><br>" + stackTrace;
        return errorMessage;
    }
}
