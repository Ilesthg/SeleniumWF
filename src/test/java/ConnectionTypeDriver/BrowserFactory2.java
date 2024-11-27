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

public final class BrowserFactory2 {

    public WebDriver setupDriverReturn(String browser) {//String browser, Object[] data
        String runMode = "no";

        if (runMode.equalsIgnoreCase("yes")) {
            return RemoteDriver.getDriver(browser);
        } else {
            return LocalDriver.getDriver(browser);
        }
    }
}
