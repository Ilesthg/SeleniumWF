package pages;

import base.DriverFactoryParallel;
import objects.FaceLoginObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.LocatorsFindByThread;

public class Logic {
  private static WebDriver driver;

    public static void loginFacebookMv1() {
         driver = DriverFactoryParallel.getInstance().getDriver();
        System.out.println("Test Case 1 olvidaste tu constraseña click ");
        LocatorsFindByThread lgf = new LocatorsFindByThread(driver);
        lgf.findElement(lgf.getElements().getPasswordField()).click();
      //  driver.findElement(By.xpath("//a[normalize-space()='¿Olvidaste tu contraseña?']")).click();
    }
    public static void loginFacebookMove() {
         driver = DriverFactoryParallel.getInstance().getDriver();
        System.out.println("Send data to mail Hola");
        driver.findElement(By.xpath(FaceLoginObjects.emailInputField)).sendKeys("Hola");
    }
    public static void loginFacebookMove2() {
         driver = DriverFactoryParallel.getInstance().getDriver();
        System.out.println("Click on France lenguage");
        driver.findElement(By.xpath("//a[normalize-space()='Français (France)']")).click();
    }
}
