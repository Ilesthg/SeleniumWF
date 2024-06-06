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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;
    FileReader fr;
    Properties prop = new Properties();

    private String routeDir;
    private String browser, url;

    @BeforeSuite
    public void initDriver() throws IOException {

        routeDir = System.getProperty("user.dir");
        System.out.println("Initialazing Driver");
        if (driver == null) {
            fr = new FileReader(routeDir + "/src/test/resources/configFiles/data.properties");
            prop.load(fr);
        }

        browser = prop.getProperty("browser");
        url = prop.getProperty("url");


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


    @AfterSuite
    public void closeDriver() {
        System.out.println("Closing Driver");
     //   driver.close();
    }
}
