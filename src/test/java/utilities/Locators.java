package utilities;


import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Locators extends BaseTest {

    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    public static WebElement findElement(String elementType, String elementValue) {
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

    public static List<WebElement> findElementS(String elementType, String elementValue) {
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

    public static WebElement findElementWithWait(String elementType, String elementValue) {

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

    public static List<WebElement> findElementsWithWait(String elementType, String elementValue) {
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