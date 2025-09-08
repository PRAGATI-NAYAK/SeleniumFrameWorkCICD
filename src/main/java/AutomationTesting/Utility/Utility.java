package AutomationTesting.Utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationTesting.PageObjects.CartPage;

public class Utility {
WebDriver driver;
	
	public Utility(WebDriver driver) {
	this.driver = driver;
	}
	
	
	@FindBy(css="[routerlink*='cart']")
	WebElement Cart;

	By spinner = By.tagName("ngx-spinner");
	
	public CartPage goToCart() {
		waitForInvisibilityOfElement(spinner);
		Cart.click();
		return new CartPage(driver);
	}
	
	public void waitForVisibilityOfElement(By findby) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
		
	}
public void waitForClickabilityOfElement(By findby) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(findby));
		
	}
	
	public void waitForInvisibilityOfElement(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findby));
		
		
	}
	
	public void scrollTo() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
	}

}
