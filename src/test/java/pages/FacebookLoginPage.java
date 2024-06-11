package pages;

import base.BaseTest;
import locators.FaceLoginLocator;
import utilities.Locators;
import utilities.ReaderExcelFiles;

import java.io.IOException;

public class FacebookLoginPage extends BaseTest {

    public static void loginFacebook(){
        Locators.findElement("Xpath", FaceLoginLocator.emailInputField).sendKeys("Hola");
    }

    public void readDataExcel_andUser(String username, String password) throws IOException {
        ReaderExcelFiles.readExcel();
    }

}
