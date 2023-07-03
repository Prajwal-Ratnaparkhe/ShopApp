package resources;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenShot {

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {


        String screenshotPath = null;
        try {
            //take screenshot and save it in a file
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //copy the file to the required path
            File destinationFile = new File(System.getProperty("user.dir") + "/reports/FailedTestSS/" + testCaseName + ".png");
            FileHandler.copy(sourceFile, destinationFile);
            String[] relatvePath = destinationFile.toString().split("reports");
            screenshotPath = ".\\" + relatvePath[1];
        } catch (Exception e) {
            System.out.println("Failure to take screenshot " + e);
        }
        return screenshotPath;

    }
}
