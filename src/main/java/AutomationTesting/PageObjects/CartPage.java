package AutomationTesting.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
@FindBy(css=".cartSection h3")
List<WebElement> cartProducts;

@FindBy(css=".totalRow button")
WebElement checkout;
	
public Boolean checkProductinCart(String productName) {
	Boolean match = cartProducts.stream()
			.anyMatch(cardProduct -> cardProduct.getText().equalsIgnoreCase(productName));
	return match;
	
}
public CheckOutPage goToCheckOutPage() {
	checkout.click();

	return new CheckOutPage(driver);
}
	
}
