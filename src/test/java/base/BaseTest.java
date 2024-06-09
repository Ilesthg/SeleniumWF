package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest logger;
    private String routeDir = System.getProperty("user.dir");
    private String browser, url;

    FileReader fr;
    Properties prop = new Properties();


    @BeforeSuite
    public void initDriver() throws IOException {
        System.out.println("Initialazing Driver and Reading properties file");

        if (driver == null) {
            fr = new FileReader(routeDir + "/src/test/resources/configFiles/data.properties");
            prop.load(fr);
        }
        browser = prop.getProperty("browser");
        url = prop.getProperty("url");
    }



    @BeforeTest
    public void beforeTestMethod() {
        //System.out.println(System.getProperty("user.dir") + File.separator + "src/test/resources/reports" + File.separator + "SDTE.html");
        //sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "/src/test/resources/reports" + File.separator + "SDTE.html");
        sparkReporter = new ExtentSparkReporter(routeDir + "/src/test/resources/reports/SDTE.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        sparkReporter.config().setTheme(Theme.DARK);
        extent.setSystemInfo("Hostname", "RHEL8");
        extent.setSystemInfo("Username", "root");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Atiomation Test results");
    }



    @BeforeMethod
    public void beforeMethod(Method testMethod) {
        logger = extent.createTest(testMethod.getName());
        setupDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }



    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test case failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test case failed", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test case SUCCESS", ExtentColor.GREEN));
        }
        driver.quit();
    }



    @AfterTest
    public void afterTest() {
        extent.flush();
    }


    @AfterSuite
    public void closeDriver() {
        System.out.println("Closing Driver");
        //   driver.close();
    }


    public void setupDriver() {
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            //Redirecting to URL
            driver.get(url);

        } else if (prop.getProperty("browser").equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get(url);
        } else if (prop.getProperty("browser").equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.get(url);
        }
    }
}
