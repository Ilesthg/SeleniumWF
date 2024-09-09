package base;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class TestContext {


        private WebDriver driver;
        private ExtentTest logger;

        // Constructor vacío o inicialización de los valores
        public TestContext() {
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

