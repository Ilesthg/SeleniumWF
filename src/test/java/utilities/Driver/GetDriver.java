package utilities.Driver;

import base.DriverFactoryParallel;
import org.openqa.selenium.WebDriver;

public final class GetDriver {

    private GetDriver() {
    }


    public static WebDriver getDriverFromStaticMethod() {
            return DriverFactoryParallel.getInstance().getDriver();

    }


}
