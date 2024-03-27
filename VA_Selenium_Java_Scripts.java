import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;

public class SoftWearEngineersTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver", "path/to/your/chromedriver.exe");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testScenario() throws Exception {
        // Test Case 1: Open the website and validate it's loaded, then scroll down
        driver.get("https://www.softwearengineers.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");

        // Test Case 2: Scroll up, navigate to the shop, click the first product, take a screenshot, and go back
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("shop-link-id"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".first-product-selector"))).click();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("path/to/save/screenshot.jpg"));
        driver.navigate().back();

        // Test Case 3: Click on the cart, proceed to checkout, and then go back
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart-icon-id"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout-button-id"))).click();
        driver.navigate().back();

        // Test Case 4: Remove an item from the cart and verify notification
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart-icon-id"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-item-id"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-id")));

        // Test Case 5: Quit the browser
        driver.quit();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}