package base;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class DriverFactoryParallel {
    private DriverFactoryParallel(){

    }
    private static   DriverFactoryParallel instance = new DriverFactoryParallel();



    public static DriverFactoryParallel getInstance(){
        return instance;
    }


    ThreadLocal<WebDriver> webDriverParallel = new ThreadLocal<>();

    public WebDriver getDriver(){
        return webDriverParallel.get();
    }


    public void setDriver(WebDriver driver) {
        webDriverParallel.set(driver);
    }
    public void closeDriver () {
        webDriverParallel.remove();


    }
}
