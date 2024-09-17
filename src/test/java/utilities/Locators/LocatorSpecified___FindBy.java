package utilities.Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;

public class LocatorSpecified___FindBy {
     public static By getByFromWebElement(Object pageObject, WebElement element) throws IllegalAccessException {
        // Use reflection to get the declared fields of the class
        Field[] fields = pageObject.getClass().getDeclaredFields();

         for (Field field : fields) {
             if (field.isAnnotationPresent(FindBy.class)) {
                 field.setAccessible(true);
                 if (field.get(pageObject).equals(element)) {
                     FindBy findBy = field.getAnnotation(FindBy.class);
                     return buildByFromFindBy(findBy);
                 }
             }
         }
        throw new IllegalArgumentException("Element not found with @FindBy annotation");
    }

    public static By buildByFromFindBy(FindBy findBy) {
        if (!findBy.id().isEmpty()) {
            return By.id(findBy.id());
        } else if (!findBy.name().isEmpty()) {
            return By.name(findBy.name());
        } else if (!findBy.className().isEmpty()) {
            return By.className(findBy.className());
        } else if (!findBy.css().isEmpty()) {
            return By.cssSelector(findBy.css());
        } else if (!findBy.tagName().isEmpty()) {
            return By.tagName(findBy.tagName());
        } else if (!findBy.linkText().isEmpty()) {
            return By.linkText(findBy.linkText());
        } else if (!findBy.partialLinkText().isEmpty()) {
            return By.partialLinkText(findBy.partialLinkText());
        } else if (!findBy.xpath().isEmpty()) {
            return By.xpath(findBy.xpath());
        }
        throw new IllegalArgumentException("No valid locator found in @FindBy annotation");
    }
}
