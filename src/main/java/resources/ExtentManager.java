package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;
    private static String path = System.getProperty("user.dir") + "\\reports\\index.html";

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        // htmlReporter.config().setChartVisibilityOnOpen(true);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("My test result");
        reporter.config().setEncoding("utf-8");
        reporter.config().setReportName("Web automation report");
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        //Set environment details
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Mentor", "Ayyaz Attar");
        extent.setSystemInfo("Tester", "Prajwal Ratnaparkhe");

        return extent;
    }

}
