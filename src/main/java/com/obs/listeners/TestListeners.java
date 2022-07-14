package com.obs.listeners;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.obs.actions.WebActionHelper;



public class TestListeners implements ITestListener {

	ExtentReports extent;
    static ExtentTest test;

    String testName;
    String reportPath = System.getProperty("user.dir") + "/Reports/";

  
    public void onTestStart(ITestResult result) {
        try {
            Object currentClass = result.getInstance();
            WebDriver driver = WebActionHelper.getDriver();
            Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
            extent.setSystemInfo("Browser", cap.getBrowserName());
            extent.setSystemInfo("BrowserVersion", cap.getBrowserVersion());

            testName = result.getMethod().getMethodName();
            test = extent.createTest(testName);
            test.assignCategory(result.getTestClass().getRealClass().getSimpleName());

            System.out.println("==============================================TEST CASE : " + testName
                    + "STARTED==============================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void onTestSuccess(ITestResult result) {
        System.out.println("==============================================TEST CASE : " + testName
                + ":PASSED==============================");
        test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED", ExtentColor.GREEN));
        test.pass("Test Passed");
    }

    
    public void onTestFailure(ITestResult result) {
        System.out.println("==============================================TEST CASE : " + testName
                + ":FAILED==============================");
        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED!!", ExtentColor.RED));
        String path = System.getProperty("user.dir") + "/test-output/" + System.currentTimeMillis() + ".png";
        File scrFile = ((TakesScreenshot) ((WebActionHelper) result.getInstance()).getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        test.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }

   
    public void onTestSkipped(ITestResult result) {
        try {
            System.out.println("==============================================TEST CASE : " + testName
                    + ":SKIPPED==============================");
            test.log(Status.SKIP,
                    MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
            System.out.println("********************************************");
            System.out.println("TEST CASE: " + result.getName() + " IS SKIPPED");
            System.out.println("********************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }


    public void onStart(ITestContext context) {
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        spark.config().setDocumentTitle(" AUTOMATION");
        spark.config().setReportName(" AUTOMATION REPORT");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

    }

    
    public void onFinish(ITestContext context) {

        try {

            extent.flush();
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
            Date date = new Date();
            String filePathdate = dateFormat.format(date).toString();
            String actualReportPath = reportPath + "index.html";
            new File(actualReportPath).renameTo(new File(
                    System.getProperty("user.dir") + "/Reports/" + "Automation_Report_" + filePathdate + ".html"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stepLog(String log) {
        test.log(Status.INFO, log);

    }

    public void warningLog(String log) {
        test.log(Status.WARNING, MarkupHelper.createLabel(log, ExtentColor.PURPLE));

    }
}
