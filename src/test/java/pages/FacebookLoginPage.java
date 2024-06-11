package pages;

import base.BaseTest;
import locators.FaceLoginLocator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import utilities.Locators;
import utilities.ReaderExcelFiles;

import java.io.IOException;


public class FacebookLoginPage extends BaseTest{
    static WebDriver driver;

    public static void loginFacebook() {
        Locators.findElement("Xpath", FaceLoginLocator.emailInputField).sendKeys("Hola");
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


}




