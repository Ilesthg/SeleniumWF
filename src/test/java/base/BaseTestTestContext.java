package base;

import ConfigFiles.ConfigProperties;
import com.aventstack.extentreports.ExtentReports;
import org.testng.annotations.*;
import utilities.BrowserFactory;
import utilities.GetData.PropertiesReader;
import utilities.Driver.TestContext;

import java.lang.reflect.Method;
import java.util.Objects;

//Leaving as public so we can extend from test clases
public class BaseTestTestContext {//It  does not support parallel testing, if thread count is 2 and you have 3 test.
    // first two test will create thread for each, and one driver for each, then the thrid one will find that

    //Making constructor protected, so only child class can see constructor
    protected BaseTestTestContext() {
    }


    // private WebDriver driver;
    private String browser, url;
    // private BrowserFactory bf = new BrowserFactory();
    //protected TestContext tc;




    private ExtentReports extent;


    @BeforeSuite
    public void initDriver() throws Exception {
        System.out.println(Thread.currentThread().getName());

        System.out.println("Reading properties file");
        browser = PropertiesReader.giveKeyValueFromProperties(ConfigProperties.BROWSER);
        url = PropertiesReader.giveKeyValueFromProperties(ConfigProperties.URL);

        extent = ExtentReportNG.setUpExtentReports();//OK
    }


    @BeforeMethod
    public void beforeMethod(Method testMethod) {


        BrowserFactory bf = new BrowserFactory();
       // tc = new TestContext();


        System.out.println(Thread.currentThread().getId() + " : " + TestContext.returnTestContextInstance().getDriver());
        // Seteamos el WebDriver en el TestContext

        if (Objects.isNull(TestContext.returnTestContextInstance().getDriver())) {
          // TestContext.returnTestContextInstance().setDriver(bf.setupDriverReturn(browser));
        }

        if (Objects.isNull(TestContext.returnTestContextInstance().getLogger())) {
            TestContext.returnTestContextInstance().setLogger(extent.createTest(testMethod.getName()));
        }


        TestContext.returnTestContextInstance().getDriver().manage().window().maximize();
       // TestContext.returnTestContextInstance().getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        TestContext.returnTestContextInstance().getDriver().navigate().to(url);
    }

    @AfterMethod//or AfterSuite
    public void closeDriver() {
        if (Objects.nonNull(TestContext.returnTestContextInstance().getDriver())) {
            TestContext.returnTestContextInstance().getDriver().quit();
            TestContext.returnTestContextInstance().setDriver(null);
        }
    }

    @AfterTest
    public void afterTest() {
        extent.flush();
    }


}
