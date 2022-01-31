import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

public class TestListener implements ITestListener {
    private static ExtentReports extent=ExtentReportGeneration.extentReportSetup();
    private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
    public WebDriver driver;

    public void onTestStart(ITestResult result) {
        ExtentTest test=extent.createTest(result.getTestClass().getName()+"::"+result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        String logText="<b> Test Method " + result.getMethod().getMethodName() + " Successful </b>";
        Markup m= MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS,m);
    }

    public void onTestFailure(ITestResult result) {
        String methodName=result.getMethod().getMethodName();
        String exceptionMessage= Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details><summary><b><font color=red>"+"Exception occurred Click to see details"+"</font></b></summary>"
        +exceptionMessage.replaceAll(",","<br>") + "</details> \n");

        WebDriver driver=((BasePage)result.getInstance()).driver;
        String path=takeScreenShot(driver,result.getMethod().getMethodName());
        String logText= "<b> Test Method " + methodName + "Failed</b>";
        Markup m= MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL,m);
    }

    public void onTestSkipped(ITestResult result) {
        String logText="<b> Test Method " + result.getMethod().getMethodName() + " Successful </b>";
        Markup m= MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP,m);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        if(extent!=null){
            extent.flush();
        }
    }

    public String takeScreenShot(WebDriver driver, String methodName){
        String fileName=getScreenshotName(methodName);
        String directory=System.getProperty("user.dir") +"/screenshots/";
        new File(directory).mkdirs();
        String path= directory + fileName;
        try{
            File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot,new File(path));
            System.out.println("***********************");
            System.out.println("Path of Screen shot " +path);
            System.out.println("***********************");
        }catch (Exception e){
            e.printStackTrace();
        }return path;
    }

    public static String getScreenshotName(String methodName){
        Date date=new Date();
        String fileName=methodName+"-"+date.toString().replace(" ","").replace(":","")+".png";
        return fileName;
    }
}
