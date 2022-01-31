import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    public WebDriver driver;

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

      public void initializeDriver(String browserName) {
        driver = DriverFactory.createLocalInstance(browserName);
        DriverManager.setWebDriver(driver);
    }
}
