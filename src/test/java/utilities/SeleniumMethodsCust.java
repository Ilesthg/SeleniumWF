package utilities;


import base.DriverFactoryParallel;
import base.ExtentReportNG;
import ConfigFiles.FrameWorkConstants;
import ConfigFiles.WaitStrategy;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumMethodsCust {


    // private ExtentTest logger = ExtentTestFactoryParallel.getInstance().getExtentTest();
    //  private WebDriver driver = DriverFactoryParallel.getInstance().GetDriver();

    private WebDriver driver = GetDriver.getDriverFromStaticMethod();
    private ExtentTest logger = ExtentReportNG.getLoggerFromStaticMethod();


    //private WebDriver driverConstructor;
    //private ExtentTest loggerConstructor;

//Usando BaseTestContext pasamos los datos por el constructor
  /*    public SeleniumMethodsCust(WebDriver driver, ExtentTest extent) {
        this.loggerConstructor = extent;
        this.driverConstructor = driver;
}*/


    protected void sendKeys(WebElement webElement, String elementName, String valueToSend) {

        try {

            System.out.println("Entre al try de sendKeys");
            webElement.sendKeys(valueToSend);
            logger.log(Status.PASS, MarkupHelper.createLabel("Send Keys Method SUCCESS, able to send key on element: ->" + elementName + " with value: ->" + valueToSend, ExtentColor.GREEN));

        } catch (Exception e) {
            System.out.println("Entre al catch de sendKeys");
            logger.log(Status.FAIL, MarkupHelper.createLabel("Send Keys Method FAILED, failure to send key on element: " + elementName + " with value: ->" + valueToSend + " due to exception: ->" + e, ExtentColor.RED));
        }
    }

    protected void sendKeysBy(By by, String elementName, String valueToSend, WaitStrategy waitStrategy) {

        try {
            System.out.println("Entre al try de sendKeysBY");
            waitStrategy(by, waitStrategy).sendKeys(valueToSend);//----------------------------*********----------

            logger.log(Status.PASS, MarkupHelper.createLabel("Send Keys Method SUCCESS, able to send key on element: ->" + elementName + " with value: ->" + valueToSend, ExtentColor.GREEN));

            //driver.findElement(by).sendKeys(valueToSend);


            // logger.log(Status.PASS, MarkupHelper.createLabel("Send Keys Method SUCCESS, able to send key on element: ->" + elementName + " with value: ->" + valueToSend, ExtentColor.GREEN));
        } catch (Exception e) {
            System.out.println("Entre al catch de sendKeysby");
            logger.log(Status.FAIL, MarkupHelper.createLabel("Send Keys Method FAILED, failure to send key on element: " + elementName + " with value: ->" + valueToSend + " due to exception: ->" + e, ExtentColor.RED));
        }
    }

    protected void clickMethod(WebElement webElement, String elementName) {
        try {

            webElement.click();
            logger.log(Status.PASS, MarkupHelper.createLabel("Click method SUCCESS, able to click on element: " + elementName, ExtentColor.GREEN));
        } catch (Exception e) {
            logger.log(Status.FAIL, MarkupHelper.createLabel("Click method  FAILED, failure to click on element: " + elementName + " due to exception: " + e, ExtentColor.RED));
        }
    }

    protected void clearMethod(WebElement webElement, String elementName) {
        try {
            webElement.clear();
            logger.log(Status.PASS, MarkupHelper.createLabel("Clear method SUCCESS, able to clear element: " + elementName, ExtentColor.GREEN));
        } catch (Exception e) {
            logger.log(Status.FAIL, MarkupHelper.createLabel("Clear method  FAILED, failure to clear element: " + elementName + " due to exception: " + e, ExtentColor.RED));
        }
    }

    protected void moveToElement(WebElement webElement, String elementName) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) DriverFactoryParallel.getInstance().getDriver();
            jse.executeScript("argument[0].scrollIntoView(true);", webElement);
            Actions actions = new Actions(DriverFactoryParallel.getInstance().getDriver());
            actions.moveToElement(webElement).build().perform();
            logger.log(Status.PASS, MarkupHelper.createLabel("Move to Element method SUCCESS,: " + elementName, ExtentColor.GREEN));
        } catch (Exception e) {
            logger.log(Status.FAIL, MarkupHelper.createLabel("Move to Element method FAILED,: " + elementName + " due to exception: " + e, ExtentColor.RED));
        }
    }

    protected void selectDropDownByVisibleText(WebElement webElement, String elementName, String visibleText) {
        try {
            Select s = new Select(webElement);
            s.selectByVisibleText(visibleText);
            logger.log(Status.PASS, MarkupHelper.createLabel("Select DD by Visible Text method SUCCESS,: " + elementName, ExtentColor.GREEN));
        } catch (Exception e) {
            logger.log(Status.FAIL, MarkupHelper.createLabel("Select DD by Visible Text method FAILED,: " + elementName + " due to exception: " + e, ExtentColor.RED));
        }
    }

    protected Boolean isWebElementPresent(WebElement webElement, String elementName) {
        boolean flag2 = false;
        // flag2 = new WebDriverWait(driver, FrameWorkConstants.getTimeToWait()).until(E);

        boolean flag = false;
        try {
            flag = webElement.isDisplayed();
            logger.log(Status.PASS, MarkupHelper.createLabel(elementName + "-->Presence of Element is: " + flag, ExtentColor.GREEN));
            return flag;
        } catch (Exception e) {
            logger.log(Status.PASS, MarkupHelper.createLabel(elementName + "-->Presence of Element is: " + flag, ExtentColor.RED));
            return flag;
        }

    }

    private WebElement waitStrategy(By by, WaitStrategy waitStrategy) {
        WebElement element = null;
        if (waitStrategy == WaitStrategy.CLICKABLE) {
            return new WebDriverWait(driver, FrameWorkConstants.getTimeToWait()).until(ExpectedConditions.elementToBeClickable(by)); //Since this is returning WEBELEMENT you can perform click or any other operation
        } else if (waitStrategy == WaitStrategy.PRESENT) {
            return new WebDriverWait(driver, FrameWorkConstants.getTimeToWait()).until(ExpectedConditions.presenceOfElementLocated(by));
        } else if (waitStrategy == WaitStrategy.VISIBLE) {
            return new WebDriverWait(driver, FrameWorkConstants.getTimeToWait()).until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        return element;

    }




     /* protected void sendKeysBy(By by, String elementName, String valueToSend, ExtentTest logger, WebDriver driver) {

        try {
            System.out.println("Entre al try");
            driver.findElement(by).sendKeys(valueToSend);
            logger.log(Status.PASS, MarkupHelper.createLabel("Send Keys Method SUCCESS, able to send key on element: ->" + elementName + " with value: ->" + valueToSend, ExtentColor.GREEN));
            //driverConstructor.findElement(by).sendKeys(valueToSend);
            //loggerConstructor.log(Status.PASS, ("Send Keys Method Success, able to send key on element: " + elementName + " with value" + valueToSend ));


        } catch (Exception e) {
            System.out.println("Entre al catch");
            logger.log(Status.FAIL, MarkupHelper.createLabel("Send Keys Method FAILED, failure to send key on element: " + elementName + " with value: ->" + valueToSend + " due to exception: ->" + e, ExtentColor.RED));

        }
    }
*/


}
