package pages;


import ConfigFiles.WaitStrategy;
import utilities.GetDriver;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.SeleniumMethodsCust;

import java.util.HashMap;


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

    public FaceLoginPage loginFacebook() {
        sendKeysBy(inputEmail, "Input Email ", "Victor Hugo", WaitStrategy.PRESENT);
        sendKeysBy(inputPassword, "Input Password", "Admin123", WaitStrategy.PRESENT);
        return this;

    }
    public FaceLoginPage loginFacebook3() {
        sendKeysBy(inputEmail, "Input Email ", "Victor v", WaitStrategy.PRESENT);
        sendKeysBy(inputPassword, "Input Password", "v", WaitStrategy.PRESENT);
        return this;

    }
    public void newExceltestData2(Object obj) {
        HashMap<String, String> hm = (HashMap<String, String>) obj;
        sendKeysBy(inputEmail, "InputEmail", hm.get("nose"), WaitStrategy.PRESENT);
        sendKeysBy(inputPassword, "InputPassword", hm.get("testDescription"), WaitStrategy.PRESENT);

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
