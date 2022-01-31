import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Scenario3 extends BasePage {
    public String url = "https://www.channelnewsasia.com/";

    @BeforeClass
    public void openBrowser() {
        String newUrl = url + "/news/international";
        driver.get(newUrl);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void test_ClickAllSections() {
        driver.findElement(By.xpath("//span[@class='all-section-menu main-menu__link'][contains(text(),'All Sections')]")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void test_Scenario01() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement clickWeather = driver.findElement(By.xpath("//section[@id='block-mc-cna-theme-secondarymenu']//li[@class='hamburger-menu__item']//a[contains(text(),'Weather')]"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", clickWeather);
    }

    @Test
    public void test_Scenario02() {
        driver.findElement(By.xpath("//div[@class='tabular-list__view--city'][contains(text(),'kuala lumpur')]")).getText();
        String maxValue=driver.findElement(By.xpath("//div/div[@class='tabular-list__view--city'][contains(text(),'kuala lumpur')]//following::div[@class='tabular-list__view--temp'][1]/span[@class='tabular-list__view--temp--max']")).getText();
        String minValue=driver.findElement(By.xpath("//div/div[@class='tabular-list__view--city'][contains(text(),'kuala lumpur')]//following::div[@class='tabular-list__view--temp'][1]/span[@class='tabular-list__view--temp--min']")).getText();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Reporter.log("Weather Maximum value");
        Reporter.log(maxValue);
        Reporter.log("Weather Minimum value");
        Reporter.log(minValue);
    }

    @AfterClass
    public void closeBrowser() {
        driver.close();
        driver.quit();
    }
}
