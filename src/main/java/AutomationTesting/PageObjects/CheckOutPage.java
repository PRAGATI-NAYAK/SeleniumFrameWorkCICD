package AutomationTesting.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.Utility.Utility;

public class CheckOutPage extends Utility{

WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(xpath="//input[@placeholder='Select Country']")
WebElement Country;

@FindBy(css=".ta-item:nth-of-type(2)")
WebElement option;

By Results = By.cssSelector("section[class*='ta-results']");
By Submit = By.cssSelector(".action__submit");

public OrderConfirmationPage addDetailsinCheckOutPage() {
	
	Actions a = new Actions(driver);
	a.sendKeys(Country, "Ind").build().perform();
    waitForVisibilityOfElement(Results);
	option.click();
	scrollTo();
	waitForVisibilityOfElement(Submit);
	a.moveToElement(driver.findElement(Submit)).doubleClick().build().perform();

   return new OrderConfirmationPage(driver);
}
}