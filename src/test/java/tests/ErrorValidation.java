package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.LandingPage;
import pageObject.LoginPage;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;

public class ErrorValidation extends BaseTest {

    @Test(dataProvider = "allData")
    public void LoginErrorValidation(HashMap<String, String> data) throws IOException {


        LandingPage landingPage = gotoLandingPage();
        // Goto login page
        LoginPage loginPage = landingPage.goTo();
        String msg = loginPage.loginMsg();
        Assert.assertEquals(data.get("signMsg"), msg); // Check login msg
        // Goto login form and perform log in operation and product catalogue
        String[] strArr = loginPage.loginErrorValidation(data.get("email"), data.get("password"));

        System.out.println(strArr[0]);
        System.out.println(strArr[1]);

        Assert.assertEquals(data.get("loginErrorMsg"), strArr[0]);
        System.out.println("Login fail");

        if (strArr[1].equalsIgnoreCase("No customer account found")) {
            System.out.println("Email address is not register");
        } else if (strArr[1].equalsIgnoreCase("The credentials provided are incorrect")) {
            System.out.println("Password is wrong");
        }
    }


    @DataProvider
    public Object[][] allData() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "prajwal12345678@gmail.com"); // wrong email
        map.put("password", "test@prajwal");
        map.put("signMsg", "Welcome, ");
        map.put("loginErrorMsg", "Login was unsuccessful. Please correct the errors and try again.");


        HashMap<Object, Object> map1 = new HashMap<>();
        map1.put("email", "prajwal12345@gmail.com");
        map1.put("password", "test12345@prajwal"); // wrong password
        map1.put("signMsg", "Welcome, Please Sign In!");
        map1.put("loginErrorMsg", "Login was unsuccessful. Please correct the errors and try again.");

        return new Object[][]{{map}, {map1}};

    }
}
