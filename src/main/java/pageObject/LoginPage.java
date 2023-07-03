package pageObject;

import Abstract.AbstractComponents;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.ExtentTestManager;

import java.util.Arrays;

public class LoginPage extends AbstractComponents {
    WebDriver driver;
    @FindBy(css = ".page-title")
    WebElement loginMsg;
    @FindBy(id = "Email")
    WebElement loginEmail;
    @FindBy(id = "Password")
    WebElement loginPassword;
    @FindBy(id = "RememberMe")
    WebElement rememberMe;
    @FindBy(xpath = "//input [@value='Log in']")
    WebElement loginBtn;
    @FindBy(css = ".item-box")
    WebElement boxItems;
    @FindBy(css = ".validation-summary-errors")
    WebElement errorWait;
    @FindBy(css = "div[class='validation-summary-errors'] span")
    WebElement loginError;
    @FindBy(css = "div[class='validation-summary-errors'] ul li")
    WebElement specificError;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String loginMsg() {
        return loginMsg.getText();
    }

    public ProductCatalogue loginForm(String email, String password) {
        loginEmail.sendKeys(email);
        ExtentTestManager.getTest().log(Status.INFO, "Entered email is : " + email);
        loginPassword.sendKeys(password);

        rememberMe.click();
        loginBtn.click();
        waituntilWebElementVisible(boxItems);
        ExtentTestManager.getTest().log(Status.PASS, "Login successfully");
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }


    public String[] loginErrorValidation(String email, String password) {
        String[] strArr = new String[2];

        loginEmail.sendKeys(email);
        ExtentTestManager.getTest().log(Status.INFO, "Entered email is : " + email);
        loginPassword.sendKeys(password);
        rememberMe.click();
        loginBtn.click();
        waituntilWebElementVisible(errorWait);
        strArr[0] = loginError.getText();
        strArr[1] = specificError.getText();
        String stringArr = Arrays.toString(strArr);
        ExtentTestManager.getTest().log(Status.INFO, "Wrong info " + stringArr);
        return strArr;
    }


}
