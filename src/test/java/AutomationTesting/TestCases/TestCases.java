
package AutomationTesting.TestCases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationTesting.PageObjects.CartPage;
import AutomationTesting.PageObjects.CatalogPage;
import AutomationTesting.PageObjects.CheckOutPage;
import AutomationTesting.PageObjects.OrderConfirmationPage;
import AutomationTesting.TestingComponents.Base;

public class TestCases extends Base{

@Test(dataProvider="getdata",groups={"Data"})
	public void test(HashMap<String,String> input) throws IOException, InterruptedException{
//public void test(String email, String password, String productName){		
		CatalogPage cp = lp.login(input.get("email"), input.get("password"));
		cp.getProductList();
		cp.addToCart(input.get("productName"));
		CartPage cart = cp.goToCart();
		Boolean match = cart.checkProductinCart(input.get("productName"));
		Assert.assertTrue(match);//validations should be only in test case file

		CheckOutPage checkout = cart.goToCheckOutPage();

		OrderConfirmationPage order = checkout.addDetailsinCheckOutPage();
		String confirmMessage = order.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	    System.out.println(confirmMessage);
		

	}


@DataProvider
public Object[][] getdata() throws IOException {
	/*HashMap<String,String> map = new HashMap<String,String>();
	map.put("email","automationtestengineer@gmail.com");
	map.put("password","Automationtestengineer@123");
	map.put("productName","ZARA COAT 3");
	
	HashMap<String,String> map1 = new HashMap<String,String>();
	map1.put("email","qualityassurranceengineer@gmail.com");
	map1.put("password","Qualityassurrance@123");
	map1.put("productName","ADIDAS ORIGINAL");*/
	File src = new File(System.getProperty("user.dir")+"//src//test//java//AutomationTesting//Data//HashMapData.json");
	List<HashMap<String,String>> data = getJSONDataToMap(src);
	return new Object[][] {{data.get(0)},{data.get(1)}};
	
}

/*
@DataProvider
public Object[][] getdata() {
	return new Object[][] {{"automationtestengineer@gmail.com","Automationtestengineer@123","ZARA COAT 3"},{"qualityassurranceengineer@gmail.com","Qualityassurrance@123","ADIDAS ORIGINAL"}};
}*/




}
