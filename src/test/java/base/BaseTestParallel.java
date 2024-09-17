package base;

import ConfigFiles.ConfigProperties;
import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.BrowserFactory;
import utilities.PropertiesReader;
import utilities.GetDriver;

import java.lang.reflect.Method;
import java.util.Objects;

public class BaseTestParallel {
    protected BaseTestParallel() {

    }


    private String browser, url;
    private ExtentReports extent;
    //Use n time
    // private  ExtentTest logger;
    private BrowserFactory bf = new BrowserFactory();

    @BeforeSuite
    public void initExtentReports() {
        try {
            browser = PropertiesReader.giveKeyValueFromProperties(ConfigProperties.BROWSER);
            url = PropertiesReader.giveKeyValueFromProperties(ConfigProperties.URL);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        extent = ExtentReportNG.setUpExtentReports();//OK


    }

    @BeforeMethod
    public void beforeMethod(Method testMethod) {


        if (Objects.isNull(DriverFactoryParallel.getInstance().getDriver())) {
            DriverFactoryParallel.getInstance().setDriver(bf.setupDriverReturn(browser));
        }

        if (Objects.isNull(ExtentTestFactoryParallel.getInstance().getExtentTest())) {
            ExtentTestFactoryParallel.getInstance().setExtentTest(extent.createTest(testMethod.getName()));
        }


        //WebDriver driver = DriverFactoryParallel.getInstance().GetDriver();
        WebDriver driver = GetDriver.getDriverFromStaticMethod();//This is OK, but is optimal?
        driver.manage().window().maximize();
        driver.navigate().to(url);


    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

            DriverFactoryParallel.getInstance().closeDrivers();


    }

    @AfterTest
    public void afterTest() {

        if (Objects.nonNull(extent)) {
            extent.flush();
        }


        // SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        //Date date = new Date();
        // String ssDate = format.format(date);
        //Not working Desktop.getDesktop().browse(new File("C:/Users/vhgm/IdeaProjects/SeleniumWF/src/test/resources/reports/SDTE " + ssDate + ".html").toURI());
    }

}
