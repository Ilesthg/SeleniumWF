package utilities;


import locators.FacebookFindByLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LocatorsFindByThread {

    private WebDriverWait wait;

    private FacebookFindByLocators elements;

    public FacebookFindByLocators getElements() {
        return elements;
    }

    public LocatorsFindByThread(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.elements = new FacebookFindByLocators(driver);
    }

    public WebElement findElement(WebElement element) {
        return element;
    }

    public WebElement findElementwithWait(WebElement element) throws IllegalAccessException {
        By by = LocatorSpecified___FindBy.getByFromWebElement(this.elements, element);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }


}