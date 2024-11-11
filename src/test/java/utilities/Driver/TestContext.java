package utilities.Driver;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public final class TestContext {


    private WebDriver driver;
    private ExtentTest logger;


    private TestContext() { // Constructor vacío o inicialización de los valores
    }

    private static TestContext testContextInstance = new TestContext();

    public static  TestContext returnTestContextInstance() {
        return testContextInstance;
    }


    // Métodos para obtener y setear el WebDriver
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    // Métodos para obtener y setear el ExtentTest
    public ExtentTest getLogger() {
        return logger;
    }

    public void setLogger(ExtentTest logger) {
        this.logger = logger;
    }
}

