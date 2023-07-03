package pageObject;

import Abstract.AbstractComponents;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.ExtentTestManager;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {
    WebDriver driver;
    @FindBy(css = ".item-box .product-item .details ")
    List<WebElement> allProducts;
    @FindBy(css = ".ajax-loading-block-window .loading-image")
    WebElement spinner;
    @FindBy(id = "giftcard_2_RecipientName")
    WebElement gCardReName;
    @FindBy(id = "giftcard_2_RecipientEmail")
    WebElement gCardReEmail;
    @FindBy(id = "add-to-cart-button-2")
    WebElement giftAddtoCart;
    @FindBy(xpath = " (//input[@class='button-2 product-box-add-to-cart-button'])[2]")
    WebElement laptop14;
    @FindBy(id = "product_attribute_72_8_30_93")
    WebElement software1;
    @FindBy(id = "add-to-cart-button-72")
    WebElement cheapLaptopAdd;
    @FindBy(id = "product_attribute_16_3_6_19")
    WebElement couHDD;
    @FindBy(id = "add-to-cart-button-16")
    WebElement couAddToCart;
    @FindBy(id = "product_attribute_74_8_29_88")
    WebElement exeHDD;
    @FindBy(id = "add-to-cart-button-74")
    WebElement exeAddToCart;
    @FindBy(id = "product_attribute_75_5_31_96")
    WebElement slowCou;
    @FindBy(id = "add-to-cart-button-75")
    WebElement slowAddToCart;
    @FindBy(css = ".content")
    WebElement cartMsg;
    By productTitle = By.cssSelector(".product-title");
    By addButton = By.cssSelector(" .add-info .buttons");
    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProductList() {
        return allProducts;
    }

    public void goToProduct(String productName) {
        WebElement productSingle = allProducts.stream().filter(product -> product.findElement(productTitle).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        productSingle.findElement(addButton).click();
        waituntilDisapper(spinner);
        ExtentTestManager.getTest().log(Status.PASS, "Land on product catalogue page ");
        addToProduct(productName);
    }


    public void addToProduct(String productName) {
        if (productName.equalsIgnoreCase("$25 Virtual Gift Card")) {
            gCardReName.sendKeys("AFourTech");
            gCardReEmail.sendKeys("a4@gmail.com");
            giftAddtoCart.click();
        } else if (productName.equalsIgnoreCase("14.1-inch Laptop")) {

            laptop14.click();
        } else if (productName.equalsIgnoreCase("Build your own cheap computer")) {
            software1.click();
            cheapLaptopAdd.click();
        } else if (productName.equalsIgnoreCase("Build your own computer")) {

            couHDD.click();
            couAddToCart.click();
        } else if (productName.equalsIgnoreCase("Build your own expensive computer")) {

            exeHDD.click();
            exeAddToCart.click();
        } else if (productName.equalsIgnoreCase("Simple Computer")) {

            slowCou.click();
            slowAddToCart.click();
        } else {
            System.out.println("Item Not Found");
        }

        ExtentTestManager.getTest().log(Status.PASS, productName+" Added in cart");
    }

    public String cartMsg() {
        String msgC = cartMsg.getText();
        waituntilDisapper(cartMsg);

        AbstractComponents abs = new AbstractComponents(driver);
        abs.clickCart();
        return msgC;
    }

    public CartPage gotoCartPage() {
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }


}
