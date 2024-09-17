package base;

import ConfigFiles.ConfigProperties;
import ConfigFiles.FrameWorkConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportNG {
   // private static ExtentReports extent;
   // private static final String routeDir = System.getProperty("user.dir");

    public static ExtentReports setUpExtentReports() {
/*

        Date date = new Date();
        String reportDate =  date.toString().replace(" ", "-").replace(":","-");

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(routeDir + "/src/test/resources/reports/SDTE  "+reportDate+".html");
*/



        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy HH/mm/ss");
        Date date = new Date();
        String ssDate = format.format(date);


        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(FrameWorkConstants.decideIfDynamicReport(ConfigProperties.OVERRIDEREPORTS));

        ExtentReports extent;
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);


        extent.setSystemInfo("Hostname", "RHEL8");
        extent.setSystemInfo("Username", "root");
        extent.setSystemInfo("Executed By User: ", System.getProperty("user.name"));

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Test results");


        return extent;
    }
    public static ExtentTest getLoggerFromStaticMethod(){
        return  ExtentTestFactoryParallel.getInstance().getExtentTest();

    }
}
