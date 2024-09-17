package utilities.Locators;


import objects.FacebookLoginPage__PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LocatorsFindBy {


    private WebDriverWait wait;
     private WebDriver driver ;

    //Initi Elements from FindByLocators, else you can init on Constructor here
    private FacebookLoginPage__PageFactory elements;
    public FacebookLoginPage__PageFactory getElements() {
        return elements;
    }// to get elements from objects

    //Constructor
     public LocatorsFindBy(WebDriver driver) {
         super();
         this.driver = driver;
         this.elements =  new FacebookLoginPage__PageFactory(driver);
          this.wait =  new WebDriverWait(driver, Duration.ofSeconds(5));
     }

     /*
     *Methods
     *
     *
     *
      */
    public WebElement findElementBy(WebElement element) throws IllegalAccessException {
        //  return element; //Ya esta establecida el locator el la Class FindBy Objects
        By by = LocatorSpecified___FindBy.getByFromWebElement(elements, element);
        return driver.findElement(by);

    }

    public List<WebElement> findElementS(WebElement element) throws IllegalAccessException {
        By by = LocatorSpecified___FindBy.getByFromWebElement(elements, element);
        return driver.findElements(by);
    }

    public WebElement findElementwithWait(WebElement element) throws IllegalAccessException {
        By by = LocatorSpecified___FindBy.getByFromWebElement(elements, element);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public  List<WebElement> findElementSWithWait(WebElement element) throws IllegalAccessException{
        By by = LocatorSpecified___FindBy.getByFromWebElement(elements, element);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }


}