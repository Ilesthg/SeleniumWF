package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public final class FacebookLoginPage__PageFactory { //PAGE FACTORY


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



//Constructor and initelement for PageFactory
    public FacebookLoginPage__PageFactory(WebDriver driver) {
        //wait = new WebDriverWait(driver, Duration.ofSeconds(5));;
        PageFactory.initElements(driver, this);
    }


}
