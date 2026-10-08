package com.demowebshop.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.driver.DriverManager;
import com.demowebshop.reports.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.getExtentInstance();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.getExtentInstance().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentReportManager.getExtentInstance().createTest(
                result.getTestClass().getRealClass().getSimpleName() + " : " + result.getMethod().getMethodName()
        );
        ExtentReportManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test Passed successfully.");
        ExtentReportManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentReportManager.getTest();
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());
        
        String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
        if (screenshotPath != null) {
            try {
                test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            } catch (Exception e) {
                test.log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
            }
        }
        ExtentReportManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        ExtentReportManager.removeTest();
    }

    private String captureScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            return null;
        }
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filename = testName + "_" + System.currentTimeMillis() + ".png";
        String destPath = System.getProperty("user.dir") + "/reports/screenshots/" + filename;
        File destination = new File(destPath);
        
        try {
            if (!destination.getParentFile().exists()) {
                destination.getParentFile().mkdirs();
            }
            FileHandler.copy(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return "./screenshots/" + filename;
    }
}
