package utilities;

import base.DriverFactoryParallel;
import base.ExtentReportNG;
import base.ExtentTestFactoryParallel;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListenerThread implements ITestListener {
    private static ExtentReports extReport;
    private static ExtentTest logger;


    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);

       logger= extReport.createTest(result.getMethod().getMethodName());
       System.out.println("init driver");
        ExtentTestFactoryParallel.getInstance().setExtentTest(logger);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestFactoryParallel.getInstance().getExtentTest().log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test case SUCCESS", ExtentColor.GREEN));
        }
        ExtentTestFactoryParallel.getInstance().removeExtentObject();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestFactoryParallel.getInstance().getExtentTest().log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test case failed", ExtentColor.RED));
            ExtentTestFactoryParallel.getInstance().getExtentTest().log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test case failed", ExtentColor.RED));

        }
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        Date date = new Date();

        String ssDate = format.format(date);
        String FileName = System.getProperty("user.dir") + "/src/test/resources/screenshots/"  +ssDate +"_"+result.getName()+".png";
        File ss = ((TakesScreenshot) DriverFactoryParallel.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(ss, new File(FileName));
            ExtentTestFactoryParallel.getInstance().removeExtentObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
         if (result.getStatus() == ITestResult.SKIP) {
             ExtentTestFactoryParallel.getInstance().getExtentTest().log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test case Skipped", ExtentColor.ORANGE));
            ExtentTestFactoryParallel.getInstance().removeExtentObject();
         }
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
        extReport = ExtentReportNG.setUpExtentReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extReport.flush();
    }
}
