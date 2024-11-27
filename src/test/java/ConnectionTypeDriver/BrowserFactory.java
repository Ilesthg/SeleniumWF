package ConnectionTypeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v115.browser.Browser;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import utilities.Driver.GetDriver;
import utilities.GetData.FromExcel;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static utilities.GetData.FromExcel.returnListofHashMap;

public final class BrowserFactory {

    public WebDriver setupDriverReturn(String browser) {//String browser, Object[] data
        String runMode = "no";

       /* List<HashMap<String, String>>  hashMapList = returnListofHashMap("Data");
        Map<String, String> map = (Map<String, String>) data[0];
        System.out.println(map.get("Browser") + " From data");*/


        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();


            if (runMode.equalsIgnoreCase("yes")) {
                // System.setProperty("webdriver.chrome.driver", "C:/Drivers/chromedriver.exe");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", browser);//map.get("Browser")
                capabilities.setCapability("browserVersion", "114.0");   // Usa "browserVersion" en lugar de "chromeVersion"
                capabilities.setCapability("acceptInsecureCerts", true);
                //capabilities.setCapability("platformName", "WINDOWS");
        /*        capabilities.setCapability("goog:chromeOptions", new HashMap<String, Object>() {{
                    put("args", Arrays.asList("--headless", "--incognito"));
                }});
*/
                try {
                    return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

            } else {
                return new ChromeDriver();

            }
        } else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("-private");
            return new FirefoxDriver(firefoxOptions);

        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();
            //edgeOptions.addArguments();
            return new EdgeDriver(edgeOptions);

        }
        return null;
    }
}
