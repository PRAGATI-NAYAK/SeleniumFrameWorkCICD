package AutomationTesting.TestCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneProject {
//just adding a comment line to check the cicd 
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		String productName = "ZARA COAT 3";
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("automationtestengineer@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Automationtestengineer@123");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		wait.until(ExpectedConditions.visibilityOfAllElements(products));
		WebElement productToBeAdded = products.stream()
				.filter(product -> product.findElement(By.xpath("//div/h5/b")).getText().equals(productName))
				.findFirst().orElse(null).findElement(By.cssSelector(".card-body button:last-of-type"));

		productToBeAdded.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("ngx-spinner")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cardProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cardProducts.stream()
				.anyMatch(cardProduct -> cardProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "Ind").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='ta-results']")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));

		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).doubleClick().build().perform();
		
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
        System.out.println(confirmationMessage);
		driver.close();

	}

}
