package pages;

import base.BaseTest;
import objects.FaceLoginObjects;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class FaceLoginPage extends BaseTest{
    private final By bttnInciarSesion = By.xpath("//button[@id='u_0_5_VH']") ;
    private final By inputEmail = By.xpath("//input[@id='email']") ;
    private final By inputPassword = By.xpath("//input[@id='pass']") ;


    public  void loginFacebook() {
        getDriverBT().findElement(inputEmail).sendKeys("HolaCrayola");

    }

}
