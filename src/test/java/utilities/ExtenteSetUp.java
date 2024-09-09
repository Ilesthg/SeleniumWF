package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtenteSetUp {
    private final String routeDir = System.getProperty("user.dir");

    private ExtentReports extent;
    //Use n time
    private  ExtentTest logger;

    protected ExtenteSetUp(){

    }
    protected ExtentTest returnLogger() {

        Method testMethod = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        Date date = new Date();
        String ssDate = format.format(date);

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(routeDir + "/src/test/resources/reports/SDTE" + ssDate + ".html");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);


        extent.setSystemInfo("Hostname", "RHEL8");
        extent.setSystemInfo("Username", "root");
        extent.setSystemInfo("Executed By User: ", System.getProperty("user.name"));

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Test results");

        logger = extent.createTest(testMethod.getName());
        return  logger;
    }
}
