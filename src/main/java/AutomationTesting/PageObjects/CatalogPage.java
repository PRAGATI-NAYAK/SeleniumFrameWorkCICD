package AutomationTesting.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.Utility.Utility;

public class CatalogPage extends Utility{
WebDriver driver;
	public CatalogPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	By productlist = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastcontainer = By.id("toast-container");
	By spinner = By.tagName("ngx-spinner");
	public List<WebElement> getProductList() {
		waitForVisibilityOfElement(productlist);
		return products;
	}
	
public WebElement getProductName(String productName) {

    waitForClickabilityOfElement(addToCart);
	//Thread.sleep(2000);
	WebElement productToBeAdded = getProductList().stream()
			.filter(product -> product.findElement(By.xpath("//div/h5/b")).getText().equals(productName))
			.findFirst().orElse(null);
	return productToBeAdded;
}
	

public void addToCart(String productToBeAdded) {
	WebElement prod = getProductName(productToBeAdded);

    waitForClickabilityOfElement(addToCart);
   // Thread.sleep(2000);
	prod.findElement(addToCart).click();//cannot add page factory design pattern for webelement
	waitForVisibilityOfElement(toastcontainer);
	waitForInvisibilityOfElement(spinner);
}


}