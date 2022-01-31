import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Scenario2 extends BasePage {

    public String url="https://www.channelnewsasia.com/";
    @BeforeClass
    public void openBrowser() {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void test_ClickAllSections(){
        driver.findElement(By.xpath("//span[@class='all-section-menu main-menu__link'][contains(text(),'All Sections')]")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void test_Scenario1(){
        try {
            String topStoriesLabelName=Utility.readProperty("topStoriesSingapore");
            Actions actions = new Actions(driver);
            WebElement source = driver.findElement(By.linkText(topStoriesLabelName));
            actions.click(source).build().perform();
            String labelName=driver.findElement(By.xpath("//h1[@class='h1 h1--block-heading block block-video-heading block-layout-builder block-field-blocknodelanding-pagetitle clearfix']/span")).getText();
            Assert.assertEquals(topStoriesLabelName,labelName);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    @Test
    public void test_Scenario2(){
        String firstText=driver.findElement(By.xpath("//a[@class='h3__link h3__link-- list-object__heading-link']")).getText();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@class='h3__link h3__link-- list-object__heading-link']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String fistHeader = driver.findElement(By.xpath("//h1[@class='h1 h1--page-title']")).getText();
        Assert.assertEquals(true, firstText.contains(fistHeader));
    }

    @Test
    public void test_Scenario3(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Boolean te=driver.findElement(By.xpath("//section[@id='block-mc-cna-theme-mainpagecontent']/article[@role='article'][2]//h1[@class='h1 h1--page-title']")).isDisplayed();
        String secondTitle=driver.findElement(By.xpath("//section[@id='block-mc-cna-theme-mainpagecontent']/article[@role='article'][2]//h1[@class='h1 h1--page-title']")).getText();
        driver.findElement(By.xpath("//section[@id='block-mc-cna-theme-mainpagecontent']/article[@role='article'][2]//h1[@class='h1 h1--page-title']")).click();
        Assert.assertEquals(true, driver.getTitle().contains(secondTitle));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//h1[@class='h1 h1--page-title'][contains(text(),'"+secondTitle+"')]")).click();
    }

    @AfterClass
    public void closeBrowser(){
        driver.close();
        driver.quit();
    }
}
