package utilities.Locators;


import objects.FacebookLoginPage__PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class LocatorsFindByThread {

    private WebDriverWait wait;

    private FacebookLoginPage__PageFactory elements;

    public FacebookLoginPage__PageFactory getElements() {
        return elements;
    }

    public LocatorsFindByThread(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.elements = new FacebookLoginPage__PageFactory(driver);
    }

    public WebElement findElement(WebElement element) {
        return element;
    }

    public WebElement findElementwithWait(WebElement element) throws IllegalAccessException {
        By by = LocatorSpecified___FindBy.getByFromWebElement(this.elements, element);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }


}