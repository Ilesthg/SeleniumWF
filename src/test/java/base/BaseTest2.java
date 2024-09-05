package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest2 {
    private   WebDriver driver;

    private final String routeDir = System.getProperty("user.dir");
    private String browser, url;

    FileReader fr;
    Properties prop = new Properties();
    protected void IniciaDriver() throws IOException {
        System.out.println("Initialazing Driver and Reading properties file");

        if (driver == null) {
            fr = new FileReader(routeDir + "/src/test/resources/configFiles/data.properties");
            prop.load(fr);
        }
        browser = prop.getProperty("browser");
        url = prop.getProperty("url");
    }

    protected void BeforeMethod()  {
        setupDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }
    public WebDriver getDriverfromSS() {
        return driver;
    }
    private void setupDriver() {
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
