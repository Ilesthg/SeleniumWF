package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTest2Continue extends BaseTest2 {


    @BeforeSuite
    public void initDriver() throws IOException {
        IniciaDriver();
    }


    @BeforeMethod
    public void beforeMethod() {

        BeforeMethod();
    }


    public WebDriver getDriver() {
        return getDriverfromSS();
    }


}
