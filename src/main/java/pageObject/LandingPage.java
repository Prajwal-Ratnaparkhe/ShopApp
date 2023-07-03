package pageObject;

import Abstract.AbstractComponents;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import resources.ExtentTestManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LandingPage extends AbstractComponents {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public LoginPage goTo() throws IOException {
        ExtentTestManager.getTest().log(Status.INFO, "Web Driver initialize" );
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties");
        prop.load(fis);
        String url = prop.getProperty("url");
        ExtentTestManager.getTest().log(Status.PASS, "Your URL is " + url);
        driver.get(url);
        clickLogin();
        ExtentTestManager.getTest().log(Status.PASS, "Login button click successfully");
        return new LoginPage(driver);
    }


}
