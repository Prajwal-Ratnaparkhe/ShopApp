package Abstract;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {
    WebDriver driver;
    @FindBy(xpath = "//a[@class='ico-register']")
    WebElement register;
    @FindBy(xpath = "//a[text()='Log in']")
    WebElement login;
    @FindBy(xpath = "//a[@class='ico-logout']")
    WebElement logout;
    @FindBy(xpath = "(//span [@class='cart-label'])[1]")
    WebElement clickCart1;
    @FindBy(css = ".page.shopping-cart-page")
    WebElement cartBody;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickRegister() {
        register.click();
    }

    public void clickLogin() {
        login.click();
    }

    public void setLogout() {
        logout.click();
    }

    public void clickCart() {
        clickCart1.click();
        waituntilWebElementVisible(cartBody);
    }

    public void waituntilWebElementVisible(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));

    }

    public void waituntilDisapper(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }


}
