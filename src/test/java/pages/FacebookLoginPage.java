package pages;

import base.BaseTest;
import objects.FaceLoginObjects;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utilities.*;

import java.io.IOException;
import java.util.HashMap;


public final class FacebookLoginPage extends BaseTest{
    private final By bttnInciarSesion = By.xpath("//button[@id='u_0_5_VH']") ;
    private final By inputEmail = By.xpath("//input[@id='email']") ;
    private final By inputPassword = By.xpath("//input[@id='pass']") ;

    @Test
  public  void loginFacebook() {
        loc.findElement("Xpath", FaceLoginObjects.emailInputField).sendKeys("HolaCrayola");
    }
  /*  @Test
    public  void loginFacebook2() {
        loc = new Locators(getDriverBT());
        loc.findElement("Xpath", FaceLoginObjects.emailInputField).sendKeys("xx");
    }
    @Test
    public  void locatorNewStrategyFindBy() throws IllegalAccessException {
        // FacebookFindByLocators f = new FacebookFindByLocators(driver);
        // lfby.findElementBy(f.getPasswordField()).sendKeys("Holaaaaaaa");

        LocatorsFindBy lfby = new LocatorsFindBy(getDriverBT());
        lfby.findElementBy(lfby.getElements().getEmailTXT()).sendKeys("Hola");

       SeleniumMethodsCust.sendKeys(lfby.findElementwithWait(lfby.getElements().getEmailTXT()), "Email Input Field", "VHGM@hotmail.com");
    }*/
    //--------------------------------------------EXCEL TESTS-------------------------------

    ///  @Factory(dataProviderClass = ReaderExcelFiles.class, dataProvider = "ExcelData")
   /* public static void Sheet1(String username, String password) throws IOException {
        loc.findElement("Xpath", FaceLoginObjects.emailInputField).sendKeys(username);
        loc.findElement("Xpath", FaceLoginObjects.passwordInputField).sendKeys(password);

    }

    public static void Sheet2(String username, String password) {
        loc.findElement("Xpath", FaceLoginObjects.emailInputField).sendKeys(username);
        loc.findElement("Xpath", FaceLoginObjects.passwordInputField).sendKeys(password);
    }



    public static void excelTest(Object obj) {
        HashMap<String, String> hm = (HashMap<String, String>) obj;

        loc.findElement("Xpath", FaceLoginObjects.emailInputField).sendKeys(hm.get("Username"));
        loc.findElement("Xpath", FaceLoginObjects.passwordInputField).sendKeys(hm.get("Password"));

    }

    public static void excelTest2(Object obj) {
        HashMap<String, String> hm = (HashMap<String, String>) obj;

        loc.findElement("Xpath", FaceLoginObjects.emailInputField).sendKeys(hm.get("Username"));
        loc.findElement("Xpath", FaceLoginObjects.passwordInputField).sendKeys(hm.get("Password"));

    }*/


}




