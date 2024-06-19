package base;

import locators.FaceLoginLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.LocatorsFindByThread;
import utilities.PropertiesReader;

import java.util.concurrent.TimeUnit;

public class DriverFactoryThread {
    BrowserFactory bf = new BrowserFactory();

    @BeforeMethod
    public void beforeMethod() throws Exception {

        String browser = PropertiesReader.giveKeyValueFromProperties("browser");
        String url = PropertiesReader.giveKeyValueFromProperties("url");

        WebDriver driverInstance = bf.setupDriverReturn(browser);

        DriverFatory_Logic_Thread.getInstance().setDriver(driverInstance);
       WebDriver  driver = DriverFatory_Logic_Thread.getInstance().getDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(url);

    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

        DriverFatory_Logic_Thread.getInstance().closeDrivers();

    }



}
