package base;

//Thread Local--> each thread maintains his own copy of data(In HashMap Thread Local Map)

import org.openqa.selenium.WebDriver;

public class DriverFatory_Logic_Thread {
    private DriverFatory_Logic_Thread() {
    }

    private static final DriverFatory_Logic_Thread instanceFactoryOfDrivers = new DriverFatory_Logic_Thread();

    public static DriverFatory_Logic_Thread getInstance() {
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
