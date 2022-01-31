import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

public class DriverFactory {

    public static WebDriver createLocalInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "E:\\drivers\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            Reporter.log("Used Browser"+browserName);
        } else if (browserName.toLowerCase().contains("firefox")) {
            System.setProperty("webdriver.firefox.driver", "E:\\drivers\\firefoxdriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.toLowerCase().contains("internet explorer")) {
            System.setProperty("webdriver.ie.driver", "E:\\drivers\\iedriver.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;
    }
}
