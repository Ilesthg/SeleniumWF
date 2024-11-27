package base;

import ConfigFiles.ConfigProperties;
import ConnectionTypeDriver.BrowserFactory2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ConnectionTypeDriver.BrowserFactory;
import utilities.GetData.PropertiesReader;
import utilities.Driver.GetDriver;

import java.util.*;

public class BaseTestParallel {
    protected BaseTestParallel() {

    }


    private String browser, url;


    private BrowserFactory bf = new BrowserFactory();
    private BrowserFactory2 bf2 = new BrowserFactory2();

    //Need to analyze if this code is being triggered multiple times
    public void initExtentReports() {
        try {
            browser = PropertiesReader.giveKeyValueFromProperties(ConfigProperties.BROWSER);
            url = PropertiesReader.giveKeyValueFromProperties(ConfigProperties.URLFACEBOOK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*    @Parameters({"browser"})*/
    @BeforeMethod()
    public void beforeMethod(Object[] data) { //public void beforeMethod( Object[] data) {
       /* System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        System.setProperty("webdriver.chrome.silentOutput", "true");*/
        url = PropertiesReader.giveKeyValueFromProperties(ConfigProperties.URLFACEBOOK);

        //initExtentReports();//Need to analyze if this code is being triggered multiple times


        Map<String, String> map = (Map<String, String>) data[0]; // map, will have all data contained in Excel sheet defined as Data Provider, due to reflection

        if (Objects.isNull(DriverFactoryParallel.getInstance().getDriver())) {
            DriverFactoryParallel.getInstance().setDriver(bf2.setupDriverReturn(map.get("Browser"))); //data
        }

        WebDriver driver = GetDriver.getDriverFromStaticMethod();//This is OK,get driver from calling static method
        driver.manage().window().maximize();
        driver.navigate().to(url);


    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        DriverFactoryParallel.getInstance().closeDrivers();// Validation for notNull done on method closeDrivers
    }


}
