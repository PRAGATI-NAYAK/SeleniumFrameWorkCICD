package AutomationTesting.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.Utility.Utility;

public class LandingPage extends Utility {
WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errmessage;
	
	By error = By.cssSelector("[class*='flyInOut']");
	
	public CatalogPage login(String email,String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		//Thread.sleep(4000);
		login.click();
		return new CatalogPage(driver);
	}
	
	public void getUrl() {

		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrMessage() {
	    waitForVisibilityOfElement(error);	
		return errmessage.getText();
	}
	
}