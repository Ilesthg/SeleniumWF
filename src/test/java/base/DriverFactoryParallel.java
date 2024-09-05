package base;

//Thread Local--> each thread maintains his own copy of data(In HashMap Thread Local Map)

import org.openqa.selenium.WebDriver;

public class DriverFactoryParallel {
    private DriverFactoryParallel() {
    }

    private static final DriverFactoryParallel instanceFactoryOfDrivers = new DriverFactoryParallel();

    public static DriverFactoryParallel getInstance() {
        return instanceFactoryOfDrivers;
    }

    private static  ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public void setDriver(WebDriver driversParam) {

        driver.set(driversParam);
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void closeDrivers() {
        driver.get().close();
        driver.remove();

    }

}
