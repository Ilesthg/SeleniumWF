package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Locators;
import utilities.PropertiesReader;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//Leaving as public so we can extend from test clases
public class BaseTest {

    //Making constructor protected, so only child class can see constructor
    protected BaseTest() {
    }


    private WebDriver driver;
    private String browser, url;
    private BrowserFactory bf = new BrowserFactory();
    private final String routeDir = System.getProperty("user.dir");

    //Use 1 time
    private ExtentReports extent;
    //Use n time
    public static ExtentTest logger;
    //  protected Locators loc;


    @BeforeSuite
    public void initDriver() throws Exception {
        System.out.println("Reading properties file");

        browser = PropertiesReader.giveKeyValueFromProperties("browser");
        url = PropertiesReader.giveKeyValueFromProperties("url");
    }


    @BeforeTest
    public void beforeTestM() {

        extent = ExtentReportNG.setUpExtentReports();

    }


    @BeforeMethod
    public void beforeMethod(Method testMethod) throws Exception {
        System.out.println("Initialazing Driver");

        logger = extent.createTest(testMethod.getName());
        ExtentTestFactoryParallel.getInstance().setExtentTest(logger);

        if (Objects.isNull(driver)) {
            driver = bf.setupDriverReturn(browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.navigate().to(url);

        // Initialize Locators instance
        //   loc = new Locators(driver);

    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestFactoryParallel.getInstance().getExtentTest().log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test case failed", ExtentColor.RED));
            ExtentTestFactoryParallel.getInstance().getExtentTest().log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test case failed", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestFactoryParallel.getInstance().getExtentTest().log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestFactoryParallel.getInstance().getExtentTest().log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test case SUCCESS", ExtentColor.GREEN));
            //logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test case SUCCESS", ExtentColor.GREEN));
        }


    }


    @AfterTest
    public void afterTest() {
        extent.flush();
    }


    @AfterSuite//or AfterSuite
    public void closeDriver() {
        if (Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }

    }

    //TO GET DRIVER
    public WebDriver getDriverBT() {
        return driver;
    }


}
