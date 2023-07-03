package pageObject;

import Abstract.AbstractComponents;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.ExtentTestManager;

public class CartPage extends AbstractComponents {
    WebDriver driver;
    @FindBy(id = "termsofservice")
    WebElement termSer;
    @FindBy(id = "checkout")
    WebElement checkOutBtn;
    @FindBy(css = " .page.checkout-page")
    WebElement checkOutPage;


    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void gotoCheckout(String productName) {
        ExtentTestManager.getTest().log(Status.PASS, "Land on cart page ");
        termSer.click();
        checkOutBtn.click();
        waituntilWebElementVisible(checkOutPage);

    }

    public CheckoutPage gotoCheckoutPage() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }


}
