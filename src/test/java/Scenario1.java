import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Scenario1 extends BasePage {

    public String url = "https://www.channelnewsasia.com/";

    @BeforeClass
    public void openBrowser() {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void test_Scenario1() {
        String expectedPageTitle = null;
        try {
            expectedPageTitle = Utility.readProperty("mainPageTile");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
        waitForPageLoaded();
        String s = driver.getCurrentUrl();

        if (s.equals(url)) {
            System.out.println("Page Loaded");
        } else {
            System.out.println("Page not Loaded");
        }
    }

    @Test
    public void test_Scenario2() {
        String fistTitle = driver.findElement(By.xpath("//h1/a[contains(@class,'feature-card__heading-link feature-card__heading-link')]")).getText();
        driver.findElement(By.xpath("//h1/a[contains(@class,'feature-card__heading-link feature-card__heading-link')]")).click();
        Assert.assertEquals(true, driver.getTitle().contains(fistTitle));
    }

    @Test
    public void test_Scenario3() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String secondTitle = driver.findElement(By.xpath("//section[@id='block-mc-cna-theme-mainpagecontent']/article[@role='article'][2]//h1[@class='h1 h1--page-title']")).getText();
        driver.findElement(By.xpath("//section[@id='block-mc-cna-theme-mainpagecontent']/article[@role='article'][2]//h1[@class='h1 h1--page-title']")).click();
        Assert.assertEquals(true, driver.getTitle().contains(secondTitle));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//h1[@class='h1 h1--page-title'][contains(text(),\"" + secondTitle + "\")]")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @AfterClass
    public void closeBrowser() {
        driver.close();
        driver.quit();
    }
}
