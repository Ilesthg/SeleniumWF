package base;

//Thread Local--> each thread maintains his own copy of data(In HashMap Thread Local Map)

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public final class DriverFactoryParallel {
    private DriverFactoryParallel() {}

    private static final DriverFactoryParallel instanceFactoryOfDrivers = new DriverFactoryParallel();
    private static  ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static DriverFactoryParallel getInstance() {return instanceFactoryOfDrivers;}
    public void setDriver(WebDriver driversParam) {driver.set(driversParam);}
    public WebDriver getDriver() {
        return driver.get();
    }

    public void closeDrivers() {

        if (Objects.nonNull(driver)) {
            driver.get().close();
            driver.remove();
        }

    }

}
