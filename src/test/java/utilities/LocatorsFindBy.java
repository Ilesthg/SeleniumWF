package utilities;


import base.BaseTest;
import locators.FacebookFindByLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LocatorsFindBy extends BaseTest {




    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    //Initi Elements from FindByLocators, else you can init on Constructor here
    private FacebookFindByLocators elements = new FacebookFindByLocators(driver);
    public FacebookFindByLocators getElements() {
        return elements;
    }


   /* public LocatorsFindBy(FindByLocators elements) {
        this.elements = elements;
     //   this.wait = wait;
    }*/
    public WebElement findElementBy(WebElement element) {return element;}

    public WebElement findElementwithWait(WebElement element) throws IllegalAccessException {
        By by = LocatorSpecified___FindBy.getByFromWebElement(this.elements, element);
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }



    public static List<WebElement> findElementS(WebElement elementValue) {
        return new ArrayList<WebElement>();
    }

    public static List<WebElement> findElementsWithWait(String elementType, String elementValue) {return null;}



}