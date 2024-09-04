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
    BrowserFactory bf = new BrowserFactory();

    private static String browser,url;

    @BeforeMethod
    public void beforeMethod() throws Exception {

         browser = PropertiesReader.giveKeyValueFromProperties("browser");
         url = PropertiesReader.giveKeyValueFromProperties("url");


        if (DriverFactoryParallel.getInstance().getDriver() == null) {
            DriverFactoryParallel.getInstance().setDriver(setupDriver());
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
    public WebDriver setupDriver() {
        WebDriver driver =null;
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--incognito");
            driver = new ChromeDriver(chromeOptions);
            //Redirecting to URL
            // driver.get(url);

        } else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("-private");
         driver = new FirefoxDriver();
            //   driver.get(url);
        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();
            //edgeOptions.addArguments();
             driver = new EdgeDriver();
            //   driver.get(url);
        }
        return driver;
    }

}
