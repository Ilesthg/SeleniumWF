package utilities;


import base.DriverFactoryParallel;
import base.ExtentTestFactoryParallel;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumMethodsCust {
    private ExtentTest logger = ExtentTestFactoryParallel.getInstance().getExtentTest();
    private ExtentTest logger2;
    private WebDriver driver;

  /*  public SeleniumMethodsCust(WebDriver driver, ExtentTest extent) {
        this.logger2 = extent;
        this.driver = driver;
    }
*/

    protected void sendKeysBy(By by, String elementName, String valueToSend) {

        try {
            driver.findElement(by).sendKeys(valueToSend);
            // System.out.println("Send Keys Method Success, able to send key on element: " + elementName + " with value" + valueToSend);
            //  logger.log(Status.PASS, ("Send Keys Method Success, able to send key on element: " + elementName + " with value" + valueToSend ));
            logger2.log(Status.PASS, MarkupHelper.createLabel("Send Keys Method SUCCESS, able to send key on element: ->" + elementName + " with value: ->" + valueToSend, ExtentColor.GREEN));
        } catch (Exception e) {
            logger2.log(Status.FAIL, MarkupHelper.createLabel("Send Keys Method FAILED, failure to send key on element: " + elementName + " with value: ->" + valueToSend + " due to exception: ->" + e, ExtentColor.RED));
            logger2.log(Status.FAIL, MarkupHelper.createLabel("Send Keys Method FAILED, failure to send key on element: " + elementName + " with value: ->" + valueToSend + " due to exception: ->" + e, ExtentColor.RED));
        }
    }

    protected void sendKeys(WebElement webElement, String elementName, String valueToSend) {

        try {
            webElement.sendKeys(valueToSend);
            // System.out.println("Send Keys Method Success, able to send key on element: " + elementName + " with value" + valueToSend);
            //  logger.log(Status.PASS, ("Send Keys Method Success, able to send key on element: " + elementName + " with value" + valueToSend ));
            logger.log(Status.PASS, MarkupHelper.createLabel("Send Keys Method SUCCESS, able to send key on element: ->" + elementName + " with value: ->" + valueToSend, ExtentColor.GREEN));
        } catch (Exception e) {
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


    private void explicitWaitUntilClickable() {

    }

    private void explicitWaitUntilPresent() {

    }


}
