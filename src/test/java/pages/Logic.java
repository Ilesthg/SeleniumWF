package pages;

import base.DriverFactoryThread;
import base.DriverFatory_Logic_Thread;
import locators.FaceLoginLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Logic {
  private static WebDriver driver;

    public static void loginFacebookMv1() {
         driver = DriverFatory_Logic_Thread.getInstance().getDriver();
        System.out.println("Test Case 1 olvidaste tu constraseña click ");
        driver.findElement(By.xpath("//a[normalize-space()='¿Olvidaste tu contraseña?']")).click();
    }
    public static void loginFacebookMove() {
         driver = DriverFatory_Logic_Thread.getInstance().getDriver();
        System.out.println("Send data to mail Hola");
        driver.findElement(By.xpath(FaceLoginLocator.emailInputField)).sendKeys("Hola");
    }
    public static void loginFacebookMove2() {
         driver = DriverFatory_Logic_Thread.getInstance().getDriver();
        System.out.println("Click on France lenguage");
        driver.findElement(By.xpath("//a[normalize-space()='Français (France)']")).click();
    }
}
