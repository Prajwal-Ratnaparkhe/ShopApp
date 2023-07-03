package pageObject;

import Abstract.AbstractComponents;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import resources.ExtentTestManager;

public class CheckoutPage extends AbstractComponents {
    WebDriver driver;
    @FindBy(id = "billing-address-select")
    WebElement billAddressClick;
    @FindBy(id = "BillingNewAddress_CountryId")
    WebElement billCountry;
    @FindBy(id = "BillingNewAddress_City")
    WebElement billCity;
    @FindBy(id = "BillingNewAddress_Address1")
    WebElement billAdd1;
    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    WebElement billZip;
    @FindBy(id = "BillingNewAddress_PhoneNumber")
    WebElement billPhone;
    @FindBy(css = ".button-1.new-address-next-step-button:first-child")
    WebElement billNext1;
    @FindBy(id = "billing-please-wait")
    WebElement billAddressSpinner;
    @FindBy(id = "checkout-step-shipping")
    WebElement shippingvisible;
    @FindBy(id = "shipping-address-select")
    WebElement shipAddressClick;

    // Shipping address
    @FindBy(id = "ShippingNewAddress_CountryId")
    WebElement shipCountry;
    @FindBy(id = "ShippingNewAddress_City")
    WebElement shipCity;
    @FindBy(id = "ShippingNewAddress_Address1")
    WebElement shipAdd1;
    @FindBy(id = "ShippingNewAddress_ZipPostalCode")
    WebElement shipZip;
    @FindBy(id = "ShippingNewAddress_PhoneNumber")
    WebElement shipPhone;
    @FindBy(css = "input[onclick='Shipping.save()']")
    WebElement shipNext;
    @FindBy(id = "shipping-please-wait")
    WebElement shipAddressSpinner;
    @FindBy(id = "checkout-step-shipping-method")
    WebElement shipMethodVisible;

    //Shipping Method
    @FindBy(id = "shippingoption_0")
    WebElement shipMethodGround;
    @FindBy(css = "input[class='button-1 shipping-method-next-step-button']")
    WebElement shipMethodNext;
    @FindBy(id = "shipping-method-please-wait")
    WebElement shipMethodSpinner;
    // Payment method
    @FindBy(id = "checkout-step-payment-method")
    WebElement paymentMethodVisible;
    @FindBy(id = "paymentmethod_0")
    WebElement cod;
    @FindBy(css = "input[class='button-1 payment-method-next-step-button']")
    WebElement paymentMethodNext;
    @FindBy(id = "payment-method-please-wait")
    WebElement paymentSpinner;
    //Payment info method
    @FindBy(id = "checkout-step-payment-info")
    WebElement payInfoVisible;
    @FindBy(css = "input[class='button-1 payment-info-next-step-button']")
    WebElement payInfoNext;
    @FindBy(id = "payment-info-please-wait")
    WebElement paymentInfoSpinner;
    @FindBy(id = "checkout-step-confirm-order")
    WebElement finalVisible;

    // Final Done
    @FindBy(css = "input[value='Confirm']")
    WebElement finalBtn;
    @FindBy(id = "confirm-order-please-wait")
    WebElement finalSpinner;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void billAddress(String productName) {
        ExtentTestManager.getTest().log(Status.PASS, "Land on product checkout page ");
        Select select = new Select(billAddressClick);
        select.selectByVisibleText("New Address");

        // Add new billing address

        Select sele1 = new Select(billCountry);
        sele1.selectByValue("41");
        billCity.sendKeys("Nanded");
        billAdd1.sendKeys("Nanded");
        billZip.sendKeys("431605");
        billPhone.sendKeys("9422123456");
        billNext1.click();

        waituntilDisapper(billAddressSpinner);

        if (productName.equalsIgnoreCase("$25 Virtual Gift Card")) {
            paymentMethod();
        } else {
            shippingAddress();
        }

    }


    public void shippingAddress() {
        waituntilWebElementVisible(shippingvisible);

        Select select = new Select(shipAddressClick);
        select.selectByVisibleText("New Address");

        // Add new shipping address

        Select sele1 = new Select(shipCountry);
        sele1.selectByValue("41");
        shipCity.sendKeys("Nanded");
        shipAdd1.sendKeys("Nanded");
        shipZip.sendKeys("431605");
        shipPhone.sendKeys("9422123456");
        shipNext.click();

        waituntilDisapper(shipAddressSpinner);
        shippingMethod();
    }

    public void shippingMethod() {
        waituntilWebElementVisible(shipMethodVisible);
        shipMethodGround.click();
        shipMethodNext.click();
        waituntilDisapper(shipMethodSpinner);
        ExtentTestManager.getTest().log(Status.PASS, "shipping method selected");
        paymentMethod();
    }

    public void paymentMethod() {
        waituntilWebElementVisible(paymentMethodVisible);
        cod.click();
        paymentMethodNext.click();
        waituntilDisapper(paymentSpinner);
        ExtentTestManager.getTest().log(Status.PASS, " Payment method selected ");
        paymentInfo();
    }


    public void paymentInfo() {
        waituntilWebElementVisible(payInfoVisible);
        payInfoNext.click();
        waituntilDisapper(paymentInfoSpinner);
        ExtentTestManager.getTest().log(Status.PASS, " Payment done ");
        finalDone();
    }

    public void finalDone() {
        waituntilWebElementVisible(finalVisible);
        finalBtn.click();
        waituntilDisapper(finalSpinner);

    }

    public AfterOrder gotoAfterOrder() {
        AfterOrder afterOrder = new AfterOrder(driver);
        return afterOrder;
    }


}
