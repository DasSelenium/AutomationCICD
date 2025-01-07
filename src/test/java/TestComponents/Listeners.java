package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.ExtentReportDemo;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReportDemo.config();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //thread safe

    public void onTestStart(ITestResult result) {

       test= extent.createTest(result.getMethod().getMethodName());
       extentTest.set(test); //sets an unique thread id of the Test Case it is running
    }

    public void onTestSuccess(ITestResult result) {

        extentTest.get().log(Status.PASS,"Test Pass");
    }

    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(result.getThrowable());

        try {
            driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName() , driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath , result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext context) {


        extent.flush();
    }


}
