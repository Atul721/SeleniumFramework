package akSelenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import akSelenium.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;

	
	@FindBy(css=".cartSection button:first-of-type")
	WebElement checkout;
	
	By cartItems = By.cssSelector(".cartSection h3");
	By deleteCartItem = By.cssSelector(".btn-danger");

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkout.click();
		CheckoutPage checkoutPage=new CheckoutPage(driver);
		return checkoutPage;
	}
	
    
    public WebElement getCartProd(String productName) {
		WebElement cartProd = (WebElement) cartProducts.stream().filter(Product->Product.getText().equalsIgnoreCase(productName));
		return cartProd;
	}
    
    public void deleteProductfromCart(String productName) throws InterruptedException {
    	WebElement prod = getCartProd(productName);
		prod.findElement(deleteCartItem).click();
		
	}
    
    
    

	
	
}
