import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.Date;

public class ExtentReportGeneration {
    public static ExtentReports extentReports;
    public static ExtentHtmlReporter htmlReporter;

    public static ExtentReports extentReportSetup() {
        String fileName=getReportName();
        String directory=System.getProperty("user.dir") +"/reports/";
        new File(directory).mkdirs();
        String path= directory + fileName;
        htmlReporter = new ExtentHtmlReporter(path);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        htmlReporter.config().setDocumentTitle("Amazon Sample"); // Tittle of Report
        htmlReporter.config().setReportName("Smoke Test"); // Name of the report
        htmlReporter.config().setTheme(Theme.DARK);//Default Theme of Report
        extentReports.setSystemInfo("Application Name", "Amazon Assignment");
        return extentReports;
    }

    public void endReport() {
        extentReports.flush();
    }

    public static String getReportName(){
        Date d=new Date();
        String fileName="TestReport"+"-"+d.toString().replace(" ","").replace(":","")+".html";
        return fileName;
    }
}

