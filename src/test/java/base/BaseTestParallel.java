package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTestParallel {
    private String browser;
    private String url;

    FileReader fr;
    Properties prop = new Properties();

    @BeforeSuite
    public void initProperties() throws IOException {
        fr = new FileReader(System.getProperty("user.dir") + "/src/test/resources/configFiles/data.properties");
        prop.load(fr);
        browser = prop.getProperty("browser");
        url = prop.getProperty("url");
    }

    @BeforeMethod
    public void LaunchApplication() throws Exception {

        if (Objects.isNull(DriverFactoryParallel.getInstance().getDriver())) {
            WebDriverManager.chromedriver().setup();
            DriverFactoryParallel.getInstance().setDriver(new ChromeDriver());

            DriverFactoryParallel.getInstance().getDriver().manage().window().maximize();
            DriverFactoryParallel.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            DriverFactoryParallel.getInstance().getDriver().navigate().to(url);

        }


    }

    @AfterMethod
    public void tearDown() {
        if (Objects.nonNull(DriverFactoryParallel.getInstance().getDriver())) {
            DriverFactoryParallel.getInstance().getDriver().quit();
            DriverFactoryParallel.getInstance().closeDriver();

        }

    }

//Falta el caso que asegura si el driver es null

    public WebDriver setupDriver() {
        WebDriver driver;
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            return driver;
            //Redirecting to URL


        } else if (prop.getProperty("browser").equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            return driver;

        } else if (prop.getProperty("browser").equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            return driver;

        }
        return null;
    }
}
