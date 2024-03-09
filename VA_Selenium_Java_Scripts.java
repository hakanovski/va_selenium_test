import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class VATestSuite {
    private WebDriver driver;
    private WebDriverWait wait;

    public void setup() {
        System.setProperty("webdriver.chrome.driver", "YOUR_CHROMEDRIVER_PATH");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void testCase1() {
        driver.get("https://www.va.gov");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("About VA"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Veterans Health Administration"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Wellness Programs"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Nutrition"))).click();
        driver.findElement(By.cssSelector("a[href='https://www.va.gov']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homepage")));
    }

    public void testCase2() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Find a VA Location"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city-state"))).sendKeys("75071");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("facility-type"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("option[value='health']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-button"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results")));
        driver.findElement(By.cssSelector("a[href='https://www.va.gov']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homepage")));
    }

    public void testCase3() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Contact Us"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Contact Us Online through Ask VA"))).click();
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homepage")));
    }

    public void testCase4() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("query"))).sendKeys("Hakan");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results")));
        driver.findElement(By.cssSelector("a[href='https://www.va.gov']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homepage")));
    }

    public void testCase5() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crisis-line"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal"))).isDisplayed();
        TimeUnit.SECONDS.sleep(3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.close"))).click();
        TimeUnit.SECONDS.sleep(3);
        driver.findElement(By.cssSelector("a[href='https://www.va.gov']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homepage")));
    }

    public void cleanUp() {
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        VATestSuite vaTestSuite = new VATestSuite();
        vaTestSuite.setup();
        
        vaTestSuite.testCase1();
        vaTestSuite.testCase2();
        vaTestSuite.testCase3();
        vaTestSuite.testCase4();
        vaTestSuite.testCase5();
        
        vaTestSuite.cleanUp();
    }
}