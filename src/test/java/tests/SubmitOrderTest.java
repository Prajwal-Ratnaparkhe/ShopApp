package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.*;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "allData")
    public void submitOrder(HashMap<String, String> data) throws IOException {
        // Data provider
        // Goto landing page
        LandingPage landingPage = gotoLandingPage();
        // Goto login page
        LoginPage loginPage = landingPage.goTo();
        String msg = loginPage.loginMsg();
        Assert.assertEquals(data.get("signMsg"), msg); // Check login msg
        // Goto login form and perform log in operation and product catalogue
        ProductCatalogue productCatalogue = loginPage.loginForm(data.get("email"), data.get("password"));
        productCatalogue.goToProduct(data.get("productName"));
        String cartMsg1 = productCatalogue.cartMsg();
        Assert.assertEquals(data.get("cartMsg"), cartMsg1);
        // Create cart page and goto
        CartPage cartPage = productCatalogue.gotoCartPage();
        cartPage.gotoCheckout(data.get("productName"));
        CheckoutPage checkoutPage = cartPage.gotoCheckoutPage();
        checkoutPage.billAddress(data.get("productName"));
        // Goto after product
        AfterOrder afterOrder = checkoutPage.gotoAfterOrder();
        afterOrder.thankYouPage();
        String orderMsg = afterOrder.orderMsg();
        Assert.assertEquals(data.get("orderSuccessfully"), orderMsg); // check product status
        System.out.println(afterOrder.orderNumber());
        afterOrder.gotoHomePage();

    }


    @DataProvider
    public Object[] allData() {

        HashMap<String, String> map = new HashMap<>();
        map.put("email", "prajwal12345@gmail.com");
        map.put("password", "test@prajwal");
        map.put("signMsg", "Welcome, Please Sign In!");

        map.put("productName", "14.1-inch Laptop");

        map.put("cartMsg", "The product has been added to your shopping cart");
        map.put("orderSuccessfully", "Your order has been successfully processed!");

        return new Object[]{map};

    }


}
