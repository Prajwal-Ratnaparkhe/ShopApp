package pageObject;

import Abstract.AbstractComponents;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.ExtentTestManager;

public class AfterOrder extends AbstractComponents {
    WebDriver driver;
    @FindBy(css = ".page.checkout-page")
    WebElement thankVisible;
    @FindBy(css = "div[class='title'] strong")
    WebElement orderPlace;
    @FindBy(css = "div[class='master-wrapper-main'] li:nth-child(1)")
    WebElement orderId;
    @FindBy(css = "input[value='Continue']")
    WebElement afterOrderCon;

    public AfterOrder(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void thankYouPage() {
        waituntilWebElementVisible(thankVisible);
    }

    public String orderMsg() {
        ExtentTestManager.getTest().log(Status.PASS, " Product checkout successfully");
        return orderPlace.getText();
    }

    public String orderNumber() {
        ExtentTestManager.getTest().log(Status.INFO, ""+orderId.getText());
        return orderId.getText();
    }

    public void gotoHomePage() {
        afterOrderCon.click();
        setLogout();
        ExtentTestManager.getTest().log(Status.PASS, " Logout successfully");
    }


}
