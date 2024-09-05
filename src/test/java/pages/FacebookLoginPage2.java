package pages;

import base.*;
import locators.FaceLoginLocator;
import org.testng.annotations.Test;
import utilities.Locators;

public class FacebookLoginPage2 extends BaseTest2Continue {
    Locators lc;

    @Test
    public void testSomething() {
        System.out.println("testSomething111");
        lc = new Locators(getDriver());

        lc.findElement("Xpath", FaceLoginLocator.emailInputField).sendKeys("ssssssssssss");
        lc.findElement("Xpath", FaceLoginLocator.passwordInputField).sendKeys("aaaaaaaaaaaaaaaaaaas");

    }

    @Test
    public void testSomething2() {
        System.out.println("testSomething22");
        lc = new Locators(getDriver());
        lc.findElement("Xpath", FaceLoginLocator.emailInputField).sendKeys("aaaaaaaaaaaaaaaaa");
        lc.findElement("Xpath", FaceLoginLocator.passwordInputField).sendKeys("bbbbbbbbbbbbbbbbb");
    }



}
