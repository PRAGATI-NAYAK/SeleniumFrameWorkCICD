package AutomationTesting.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTesting.PageObjects.CartPage;
import AutomationTesting.PageObjects.CatalogPage;
import AutomationTesting.TestingComponents.Base;
import AutomationTesting.TestingComponents.Retry;

public class Validation extends Base{

	
	@Test(groups= {"ErrorValidation"},retryAnalyzer=Retry.class)
	public void errorvalidation() throws InterruptedException {

		String email = "automationtestengineer@gmail.com";
		String password = "Automationtestengineer@1234";
		lp.login(email, password);
		Assert.assertEquals("Incorrect email or password.",lp.getErrMessage());
		
		
		
	}
	
	
	@Test(dependsOnMethods= {"errorvalidation"})
	public void checkProduct() throws InterruptedException {
		String email = "automationtestengineer@gmail.com";
		String password = "Automationtestengineer@123";
		String productName = "ZARA COAT 3";
		CatalogPage cp = lp.login(email, password);	
		
		cp.addToCart(productName);
		CartPage cart = cp.goToCart();
		Boolean match =cart.checkProductinCart("ZARA COAT 3");
		Assert.assertTrue(match);
		
		
		
	}
	
}
