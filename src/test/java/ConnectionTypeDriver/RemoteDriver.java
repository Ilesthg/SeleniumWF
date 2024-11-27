package ConnectionTypeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriver {
    public static WebDriver getDriver(String browser) {//String browser, Object[] data


       /* List<HashMap<String, String>>  hashMapList = returnListofHashMap("Data");
        Map<String, String> map = (Map<String, String>) data[0];
        System.out.println(map.get("Browser") + " From data");*/


        if (browser.equalsIgnoreCase("Chrome")) {

            WebDriverManager.chromedriver().setup();


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
        } else if (browser.equalsIgnoreCase("Firefox")) {
           /*

            */

        } else if (browser.equalsIgnoreCase("Edge")) {
            /*

             */

        }
        return null;
    }
}
