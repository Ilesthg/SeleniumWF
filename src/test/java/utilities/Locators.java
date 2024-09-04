package utilities;


import base.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Locators {

//To get the driver we can use @Beforemethod annotation to call the driver
 /* @BeforeClass
    public void setUp() throws Exception {
        initDriver(); // Initialize the WebDriver
        locators = new Locators(); // Initialize the Locators instance after driver initialization
    }*/
// Use lazy init   private void initializeDriver() {
  /*         if (this.driver == null) {
                this.driver = driverFromBaseTest();
               this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            }
        }

        public WebElement findElement(String elementType, String elementValue) {
            initializeDriver();  // Ensure driver and wait are initialized before use*/


    ////To use the driver protected from BASETEST, Note driver will be accessible from BaseTest cause its PROTECTED
    // /*   public static WebDriverWait createWait(WebDriver driver, int seconds) {
    //        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    //    }
    //
    //    public static WebElement findElement(WebDriver driver, String elementType, String elementValue) {
    //        if (elementType.equalsIgnoreCase("Xpath")) {
    //            return driver.findElement(By.xpath(elementValue));
    //
    //
    //
    //            and on Test class
    //            public class YourTestClass extends BaseTest {
    //             @Test
    //    public void yourTestMethod() {
    //        WebElement element = Locators.findElement(driver, "Xpath", "//div[@id='example']");
    //        // Perform your test actions using 'element'
    //    }
    //    }
    //        }*/

    //Or Insitate the Locator class in BaseTestClass

    private WebDriver driver;
    private WebDriverWait wait;

    public Locators(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }






    public WebElement findElement(String elementType, String elementValue) {

        if (elementType.equalsIgnoreCase("Xpath")) {
            return driver.findElement(By.xpath(elementValue));
        } else if (elementType.equalsIgnoreCase("Css")) {
            return driver.findElement(By.cssSelector(elementValue));
        } else if (elementType.equalsIgnoreCase("ID")) {
            return driver.findElement(By.id(elementValue));
        } else if (elementType.equalsIgnoreCase("ClassName")) {
            return driver.findElement(By.className(elementValue));
        } else if (elementType.equalsIgnoreCase("LinkText")) {
            return driver.findElement(By.linkText(elementValue));
        } else if (elementType.equalsIgnoreCase("PartialLinkText")) {
            return driver.findElement(By.partialLinkText(elementValue));
        } else {
            System.out.println("No Locator");
            return null;
        }
    }

    public List<WebElement> findElementS(String elementType, String elementValue) {
        if (elementType.equalsIgnoreCase("Xpath")) {
            return driver.findElements(By.xpath(elementValue));
        } else if (elementType.equalsIgnoreCase("Css")) {
            return driver.findElements(By.cssSelector(elementValue));
        } else if (elementType.equalsIgnoreCase("ID")) {
            return driver.findElements(By.id(elementValue));
        } else if (elementType.equalsIgnoreCase("ClassName")) {
            return driver.findElements(By.className(elementValue));
        } else if (elementType.equalsIgnoreCase("LinkText")) {
            return driver.findElements(By.linkText(elementValue));
        } else if (elementType.equalsIgnoreCase("PartialLinkText")) {
            return driver.findElements(By.partialLinkText(elementValue));
        } else {
            System.out.println("No Locator");
            return null;
        }
    }

    public WebElement findElementWithWait(String elementType, String elementValue) {

        if (elementType.equalsIgnoreCase("Xpath")) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementValue)));
        } else if (elementType.equalsIgnoreCase("Css")) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementValue)));
        } else if (elementType.equalsIgnoreCase("ID")) {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementValue)));
        } else if (elementType.equalsIgnoreCase("ClassName")) {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.className(elementValue)));
        } else if (elementType.equalsIgnoreCase("LinkText")) {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(elementValue)));
        } else if (elementType.equalsIgnoreCase("PartialLinkText")) {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(elementValue)));
        } else {
            System.out.println("No Locator");
            return null;
        }


    }

    public List<WebElement> findElementsWithWait(String elementType, String elementValue) {
        if (elementType.equalsIgnoreCase("Xpath")) {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(elementValue)));
        } else if (elementType.equalsIgnoreCase("Css")) {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(elementValue)));
        } else if (elementType.equalsIgnoreCase("ID")) {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(elementValue)));
        } else if (elementType.equalsIgnoreCase("ClassName")) {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(elementValue)));
        } else if (elementType.equalsIgnoreCase("LinkText")) {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(elementValue)));
        } else if (elementType.equalsIgnoreCase("PartialLinkText")) {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText(elementValue)));
        } else {
            System.out.println("No Locator");
            return null;
        }


    }
}