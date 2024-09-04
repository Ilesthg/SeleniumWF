package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FacebookLoginPage__PageFactory { //PAGE FACTORY
   /* WebDriver driver;*/
    @FindBy(xpath = "//input[@id='email']")
    private   WebElement emailTXT;
    @FindBy(xpath = "//input[@id='pass']")
    private  WebElement passwordField;

    @FindBy(name = "login")
    private  WebElement iniciarSesionButton;


    //Getters
    public WebElement getEmailTXT() {
        return emailTXT;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getIniciarSesionButton() {
        return iniciarSesionButton;
    }



//Constructor
    public FacebookLoginPage__PageFactory(WebDriver driver) {
       // this.driver = driver;
        //wait = new WebDriverWait(driver, Duration.ofSeconds(5));;
        PageFactory.initElements(driver, this);
    }


}
