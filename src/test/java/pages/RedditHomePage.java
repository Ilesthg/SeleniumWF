package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver.GetDriver;

import java.time.Duration;
import java.util.List;

public class RedditHomePage {
    private WebDriver driver = GetDriver.getDriverFromStaticMethod();
    By elementAboveShadow1 = By.xpath("//*[@id = 'shreddit-skip-link']//following-sibling::shreddit-app/descendant::reddit-header-large/descendant::reddit-search-large");
    By elementAboveShadow2 = By.cssSelector("faceplate-search-input");
    By letsee = By.cssSelector("reddit-search-large");
    public RedditHomePage searchBar() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Esperar la presencia de elementAboveShadow1 en el DOM principal
        WebElement firstElement = wait.until(ExpectedConditions.presenceOfElementLocated(elementAboveShadow1));

        // Acceder al Shadow Root del primer elemento
        SearchContext firstShadowRoot = firstElement.getShadowRoot();

        // Esperar y acceder al segundo elemento en el Shadow Root
        WebElement second2 = waitUntilElementPresentInShadow(firstShadowRoot, elementAboveShadow2);
        SearchContext searchContext2 = second2.getShadowRoot();

        // Buscar el campo de entrada dentro del segundo Shadow Root
        WebElement input = searchContext2.findElement(By.cssSelector("input[name='q']"));
        input.sendKeys("/mexicofinanciero");

        // Esperar la presencia del tercer Shadow Root 'reddit-search-large'
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(letsee));
        SearchContext shadow4 = element.getShadowRoot();

        // Esperar a que el enlace esté disponible y sea clickeable
        WebElement element5 = waitUntilElementClickableInShadow(shadow4, By.cssSelector("a[href=\"/r/MexicoFinanciero/\"]"));
        element5.click();

        return new RedditHomePage();
    }

    public void selectDynamicFrame(){
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@src='frame_5.html']")));
        driver.findElement(By.xpath("//input[@name='mytext5']")).sendKeys("sds");
        driver.findElement(By.xpath("//a[normalize-space()='https://a9t9.com']")).click();
        driver.findElement(By.xpath("//a[@class='get-button']")).click();
    }

    // Método para esperar a que un elemento esté presente dentro del Shadow DOM
    private WebElement waitUntilElementPresentInShadow(SearchContext shadowRoot, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(driver -> shadowRoot.findElement(by));
    }

    // Método para esperar a que un elemento sea clickeable dentro del Shadow DOM
    private WebElement waitUntilElementClickableInShadow(SearchContext shadowRoot, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(driver ->  {
            WebElement element = shadowRoot.findElement(by);
            return element.isDisplayed() && element.isEnabled() ? element : null;
        });
}
     }
