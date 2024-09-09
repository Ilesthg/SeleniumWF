package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Locators;
import utilities.PropertiesReader;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

//Leaving as public so we can extend from test clases
public class BaseTestC {

    //Making constructor protected, so only child class can see constructor
    protected BaseTestC() {
    }


    // private WebDriver driver;
    private String browser, url;
    // private BrowserFactory bf = new BrowserFactory();
    protected TestContext tc;

    private  ExtentReports extent;


    //Use n time


    @BeforeSuite
    public void initDriver() throws Exception {
        System.out.println("Reading properties file");
        browser = PropertiesReader.giveKeyValueFromProperties("browser");
        url = PropertiesReader.giveKeyValueFromProperties("url");
        BrowserFactory bf = new BrowserFactory();

        tc = new TestContext();




        WebDriver driver = bf.setupDriverReturn(browser);

        // Seteamos el WebDriver en el TestContext
        tc.setDriver(driver);



    }


    @BeforeMethod
    public void beforeMethod(Method testMethod) {
        System.out.println("Initialazing Driver");


         extent = ExtentReportNG.setUpExtentReports();
        ExtentTest logger = extent.createTest(testMethod.getName());
        tc.setLogger(logger);


        tc.getDriver().manage().window().maximize();
        tc.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        tc.getDriver().navigate().to(url);
    }

    @AfterMethod//or AfterSuite
    public void closeDriver() {
        if (Objects.nonNull(tc.getDriver())) {
            tc.getDriver().quit();
            tc.setDriver(null);
        }
    }

    @AfterTest
    public void afterTest() {
        extent.flush();
    }


}
