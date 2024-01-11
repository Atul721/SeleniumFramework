package akSelenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import akSelenium.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css=".form-group input")
	WebElement country;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	By Results = By.cssSelector(".ta-results");
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	public void selectCountry(String CountryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country,CountryName).build().perform();
		waitForElementToAppear(Results);
		selectCountry.click();
	}
	
	public ConfirmationPage submit() {
		alterClick(submit);
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
		
	}

}
