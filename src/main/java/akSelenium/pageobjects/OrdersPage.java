package akSelenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import akSelenium.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orders;
	
	By deleteOrder = By.cssSelector("tr td:nth-child(7)");

	public Boolean verifyOrdersDisplay(String productName) {
		Boolean match = orders.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public WebElement getItem(String productName) {
		WebElement prod=(WebElement) orders.stream().filter(product->product.getText().equalsIgnoreCase(productName));
		return prod;
	}
	
	public void deleteItem(String productName) {
		WebElement prod=getItem(productName);
		prod.findElement(deleteOrder);
	}
}
	
	

