package testComponents;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pageObject.LandingPage;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;

    @Parameters("browserName")
    @BeforeMethod(alwaysRun = true)
    public void launchApplication(String browserName) throws IOException {
        driver = initialDriver(browserName);
    }

    public WebDriver initialDriver(String browserName) throws IOException {
//        Properties prop = new Properties();
//        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties");
//        prop.load(fis);
//        String browserName = prop.getProperty("browser");
//
//
//        if (browserName.equalsIgnoreCase("chrome")) {
//
//            driver = new ChromeDriver();
//
//
//        } else if (browserName.equalsIgnoreCase("firefox")) {
//            driver = new FirefoxDriver();
//
//        } else if (browserName.equalsIgnoreCase("edge")) {
//            driver = new EdgeDriver();
//
//        }


        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions = new EdgeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();


        if (browserName.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println("Chrome browser launched");


        } else if (browserName.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            System.out.println("Firefox browser launched");

        } else if (browserName.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            System.out.println("Edge browser launched");

        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }


    public LandingPage gotoLandingPage() {
        return new LandingPage(driver);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

}