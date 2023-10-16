package com.eaapp.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.eaapp.utilities.BrowserUtils;
import com.eaapp.utilities.ConfigurationReader;
import com.eaapp.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions actions;

    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;


    @BeforeMethod
    public void setUp(){
        driver= Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        webDriverWait= new WebDriverWait(driver, 7);
        actions= new Actions(driver);
        driver.get(ConfigurationReader.get("url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException, InterruptedException {
        //if test fails
        if(result.getStatus() == ITestResult.FAILURE){
            //record the name of failed test
            extentLogger.fail(result.getName());
            //take the screen shot and return the location of screenshot
            String screenShotPath = BrowserUtils.getScreenshot(result.getName());
            //add the screenshot to the report
            extentLogger.addScreenCaptureFromPath(screenShotPath);
            //capture the exception and put inside the report
            extentLogger.fail(result.getThrowable());
        }
        Driver.closeDriver();
    }

    @BeforeTest
    public void setUpTest(){
        //initialize the class
        report = new ExtentReports();

        //create a report path
        String projectPath = System.getProperty("user.dir");
        //to take dynamic report
        // String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // String reportPath = projectPath + "/test-output/report"+date+".html";
        String reportPath = projectPath + "/test-output/report.html";

        //initialize the html report with the reportPath
        htmlReporter = new ExtentHtmlReporter(reportPath);

        //attach the html report to the report object
        report.attachReporter(htmlReporter);

        //title in report
        htmlReporter.config().setReportName("Modmed Apply Test");

        report.setSystemInfo("Environment", "Test");
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));
        report.setSystemInfo("Test Engineer", "Olcay");
    }

    @AfterTest
    public void tearDownTest(){
        report.flush();
    }

}
