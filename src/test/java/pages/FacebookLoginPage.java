package pages;

import base.BaseTest;
import locators.FaceLoginLocator;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.xml.sax.Locator;
import utilities.Locators;

public class FacebookLoginPage extends BaseTest {

    public static void loginFacebook(){
        Locators.findElement("Xpath", FaceLoginLocator.emailInputField).sendKeys("Hola");

    }

}
