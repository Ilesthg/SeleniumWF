package pages;

import objects.FaceLoginObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.*;
import utilities.Locators.Locators;
import utilities.Locators.LocatorsFindBy;

import java.io.IOException;
import java.util.HashMap;


public final class FacebookLoginPage  extends SeleniumMethodsCust {

    private final By bttnInciarSesion = By.xpath("//button[@id='u_0_5_VH']");
    private final By inputEmail = By.xpath("//input[@id='email']");
    private final By inputPassword = By.xpath("//input[@id='pass']");

    private WebDriver driver;

    //contructor initializating the driver
    public FacebookLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private Locators loc;

    public void loginFacebookTest1() {
        loc = new Locators(this.driver); // Cannot move to top, will generate NullPointerExcp
        loc.findElement("Xpath", FaceLoginObjects.emailInputField).sendKeys("HolaCrayola");
    }

    public void loginFacebookTest2() {
        this.driver.findElement(inputEmail).sendKeys("Hola");

    }

    public void locatorNewStrategyFindBy() throws IllegalAccessException {
        LocatorsFindBy lfby = new LocatorsFindBy(this.driver);
        lfby.findElementBy(lfby.getElements().getEmailTXT()).sendKeys("Hola");


        sendKeys(lfby.findElementwithWait(lfby.getElements().getEmailTXT()), " Email Input Field ", " VHGM@hotmail.com ");
    }
    //--------------------------------------------EXCEL TESTS-------------------------------

    ///  @Factory(dataProviderClass = ReaderExcelFiles.class, dataProvider = "ExcelData")
    public void Sheet1(String username, String password)  {
        this.driver.findElement(inputEmail).sendKeys(username);
        this.driver.findElement(inputPassword).sendKeys(password);

    }

    public void Sheet2(String username, String password) {
      sendKeys(this.driver.findElement(inputEmail), "Input Email", username);
      sendKeys(this.driver.findElement(inputPassword), "Input Password", password);
    }


    public void excelTest(Object obj) {
        HashMap<String, String> hm = (HashMap<String, String>) obj;


       sendKeys (driver.findElement(inputEmail), "Email", hm.get("Username"));
      sendKeys(driver.findElement(inputPassword), "Password", hm.get("Password"));

    }

    public void excelTest2(Object obj) {
        HashMap<String, String> hm = (HashMap<String, String>) obj;
     sendKeys(driver.findElement(inputEmail), "Email", hm.get("Username"));
       sendKeys(driver.findElement(inputPassword), "Password", hm.get("Password"));

    }


}




