package pages;


import ConfigFiles.ConfigProperties;
import ConfigFiles.WaitStrategy;
import base.TestDataParallel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver.GetDriver;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.GetData.FromExcel;
import utilities.GetData.PropertiesReader;
import utilities.GetData.WhichTestToExecuteExcel;
import utilities.SeleniumMethodsCust;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//This class should never extend the basetest class
public final class FaceLoginPage extends SeleniumMethodsCust {
    private WebDriver driverConstructor;
    private ExtentTest loggerConstructor;


  //private WebDriver driver = DriverFactoryParallel.getInstance().GetDriver();//Using BaseTestParallel

    private WebDriver driver = GetDriver.getDriverFromStaticMethod();


    private final By bttnInciarSesion = By.xpath("//button[@id='u_0_5_VH']");
    private final By inputEmail = By.xpath("//input[@id='email']");
    private final By inputPassword = By.xpath("//input[@id='pass']");

    //Contructor to pass the driver on test class for //BaseTestTestContext
   /* public FaceLoginPage(WebDriver driver *//*ExtentTest logger*//*) {
        this.driverConstructor = driver;
       *//*this.loggerConstructor = logger;*//*
    }*/

    public FaceLoginPage probando1(HashMap<String, String> data) {
        sendKeysBy(inputEmail, "Input Email ", data.get("username"), WaitStrategy.PRESENT);
        sendKeysBy(inputPassword, "Input Password", data.get("password"), WaitStrategy.PRESENT);

        /*Assert.assertTrue(false);*/
        return this;

    }

    public void probando2(HashMap<String, String> data) {
        sendKeysBy(inputEmail, "InputEmail", data.get("username"), WaitStrategy.PRESENT);
        sendKeysBy(inputPassword, "InputPassword",data.get("firstname"), WaitStrategy.PRESENT);
//        Assert.assertTrue(false);
    }
    public void probando3(Object  obj) {
        HashMap<String, String> data = ((HashMap<String, String>) obj);
        sendKeysBy(inputEmail, "InputEmail", data.get("username"), WaitStrategy.PRESENT);
        sendKeysBy(inputPassword, "InputPassword", "dd", WaitStrategy.PRESENT);

    }

    public  void dosome() throws IOException, InvalidFormatException {

     /*  Object [] array = FromExcel.returnExcelSheetInObject();
        HashMap<String, String> data =  (HashMap<String, String>) array[0];



        System.out.println(data.get("username"));
        driver.findElement(inputPassword).sendKeys(data.get("username"));
*/

       /* Map<String,String> dad = (Map<String,String>)data[0];

        for (int i = 0; i < data.length; i++) {

        }
        System.out.println(dad.get("username"));
        driver.findElement(inputPassword).sendKeys(dad.get("username"));*/







       /* for (Map.Entry<String, String> entry : dad.entrySet()) { Iterate over a HashMap
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }*/

    }








      /*  Key Points:
        Thread-Safety: The issue arises because you're mixing sources for the WebDriver. You need to stick to one source to ensure thread safety. Either rely on the ThreadLocal implementation via DriverFactoryParallel.getInstance().GetDriver(), or pass the driver in the constructor and use it consistently across all methods.
        Consistency: For better maintainability and to avoid potential bugs, you should either consistently use DriverFactoryParallel or the constructor-based driver, but not both.

        DriverFactoryParallel.getInstance().GetDriver().findElement(inputEmail).sendKeys("HolaCrayola");
        DriverFactoryParallel.getInstance().GetDriver().findElement(inputPassword).sendKeys("dsddddddddddddddddd");
        Solution:
To maintain consistency and avoid thread-safety issues, you should either:

Use DriverFactoryParallel.getInstance().GetDriver() Consistently:
Remove the driver from the constructor, and always access the WebDriver from DriverFactoryParallel.getInstance().GetDriver() in all methods. This ensures the driver is always obtained in a thread-safe manner.
 DriverFactoryParallel.getInstance().GetDriver().findElement(inputEmail).sendKeys("HolaCrayola");


Use the Constructor-Injected Driver Consistently:
Stick to the driver passed via the constructor and remove calls to DriverFactoryParallel.getInstance().GetDriver(). This way, the driver instance is passed once, and you don't need to rely on the factory again.
this.driver.findElement(inputEmail).sendKeys("HolaCrayola");
*/


}
