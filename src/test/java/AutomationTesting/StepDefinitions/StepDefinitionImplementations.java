package AutomationTesting.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import AutomationTesting.PageObjects.CartPage;
import AutomationTesting.PageObjects.CatalogPage;
import AutomationTesting.PageObjects.CheckOutPage;
import AutomationTesting.PageObjects.LandingPage;
import AutomationTesting.PageObjects.OrderConfirmationPage;
import AutomationTesting.TestingComponents.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementations extends Base {
	LandingPage lp;
	CatalogPage cp;
	CartPage cart;
	CheckOutPage checkout;
	OrderConfirmationPage order;

	@Given("I landed on the URL")
	public void I_landed_on_the_URL() throws IOException {
		lp = launchApplication();
	}

	@When("^Logged in with (.+) and (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		cp = lp.login(username, password);
	}

	@Then("^\"([^\"]*)\" message should display$")

	public void error_message_should_display(String string) {

		Assert.assertEquals(string, lp.getErrMessage());
		driver.close();
	}

	@When("^I added (.+) to cart$")
	public void I_added_product_to_cart(String product) {
		cp.getProductList();
		cp.addToCart(product);
	}

	@And("^check the (.+) in cart and submit order$")
	public void check_the_product_in_cart_and_submit_order(String product) {
		cart = cp.goToCart();
		Boolean match = cart.checkProductinCart(product);
		Assert.assertTrue(match);
	}

	@Then("display success message {string} on order confirmation")
	public void display_success_message_on_order_confirmation(String message) {
		checkout = cart.goToCheckOutPage();
		order = checkout.addDetailsinCheckOutPage();
		String confirmMessage = order.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println(confirmMessage);

	}
}
