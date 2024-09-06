package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


//This class should never extend the basetest class
public final class FaceLoginPage {
    private WebDriver driver;

    private final By bttnInciarSesion = By.xpath("//button[@id='u_0_5_VH']");
    private final By inputEmail = By.xpath("//input[@id='email']");
    private final By inputPassword = By.xpath("//input[@id='pass']");

    //Contructor to pass the driver on test class
    public FaceLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public FaceLoginPage loginFacebook() {

        this.driver.findElement(inputEmail).sendKeys("HolaCrayola");
        this.driver.findElement(inputPassword).sendKeys("teApestaLaCola");
        return this;

    }

    public FaceLoginPage loginFacebookUsingLocalThread() {
        this.driver.findElement(inputEmail).sendKeys("HolaCrayola");
        this.driver.findElement(inputPassword).sendKeys("teApestaLaCola");
        return this;
    }




      /*  Key Points:
        Thread-Safety: The issue arises because you're mixing sources for the WebDriver. You need to stick to one source to ensure thread safety. Either rely on the ThreadLocal implementation via DriverFactoryParallel.getInstance().getDriver(), or pass the driver in the constructor and use it consistently across all methods.
        Consistency: For better maintainability and to avoid potential bugs, you should either consistently use DriverFactoryParallel or the constructor-based driver, but not both.

        DriverFactoryParallel.getInstance().getDriver().findElement(inputEmail).sendKeys("HolaCrayola");
        DriverFactoryParallel.getInstance().getDriver().findElement(inputPassword).sendKeys("dsddddddddddddddddd");
        Solution:
To maintain consistency and avoid thread-safety issues, you should either:

Use DriverFactoryParallel.getInstance().getDriver() Consistently:
Remove the driver from the constructor, and always access the WebDriver from DriverFactoryParallel.getInstance().getDriver() in all methods. This ensures the driver is always obtained in a thread-safe manner.
 DriverFactoryParallel.getInstance().getDriver().findElement(inputEmail).sendKeys("HolaCrayola");


Use the Constructor-Injected Driver Consistently:
Stick to the driver passed via the constructor and remove calls to DriverFactoryParallel.getInstance().getDriver(). This way, the driver instance is passed once, and you don't need to rely on the factory again.
this.driver.findElement(inputEmail).sendKeys("HolaCrayola");
*/


}
