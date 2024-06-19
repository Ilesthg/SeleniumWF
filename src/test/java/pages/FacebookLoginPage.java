package pages;

import base.BaseTest;
import locators.FaceLoginLocator;
import locators.FacebookFindByLocators;

import utilities.Locators;
import utilities.LocatorsFindBy;

import java.io.IOException;


public class FacebookLoginPage extends  BaseTest{


  public static void loginFacebook() {
        Locators.findElement("Xpath", FaceLoginLocator.emailInputField).sendKeys("HolaCrayola");
    }


  //  @Factory(dataProviderClass = ReaderExcelFiles.class, dataProvider = "ExcelData")
    public static void Sheet1(String  username, String password) throws IOException {
        Locators.findElement("Xpath", FaceLoginLocator.emailInputField).sendKeys(username);
        Locators.findElement("Xpath", FaceLoginLocator.passwordInputField).sendKeys(password);

    }

    public static void Sheet2(String  username, String password){
        Locators.findElement("Xpath", FaceLoginLocator.emailInputField).sendKeys(username);
        Locators.findElement("Xpath", FaceLoginLocator.passwordInputField).sendKeys(password);
    }
    public static void locatorNewStrategyFindBy() throws IllegalAccessException {
        FacebookFindByLocators f = new FacebookFindByLocators(driver);
        LocatorsFindBy lfby = new LocatorsFindBy();

       lfby.findElementBy(f.getPasswordField()).sendKeys("Holaaaaaaa");
        lfby.findElementwithWait(lfby.getElements().getEmailTXT()).sendKeys("Hola");
    }


}




