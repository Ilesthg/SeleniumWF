package base;

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
import utilities.PropertiesReader;

import java.util.concurrent.TimeUnit;

public class BaseTestParallel {
    protected  BaseTestParallel(){

    }


    private static String browser,url;
  private   BrowserFactory bf = new BrowserFactory();

    @BeforeMethod
    public void beforeMethod() throws Exception {

         browser = PropertiesReader.giveKeyValueFromProperties("browser");
         url = PropertiesReader.giveKeyValueFromProperties("url");


        if (DriverFactoryParallel.getInstance().getDriver() == null) {
            DriverFactoryParallel.getInstance().setDriver(bf.setupDriverReturn(browser));
        }


        WebDriver driver = DriverFactoryParallel.getInstance().getDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(url);

    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        DriverFactoryParallel.getInstance().closeDrivers();
    }


}
