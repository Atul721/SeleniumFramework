package akSelenium.tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import akSelenium.TestComponent.BaseTest;
import akSelenium.pageobjects.CartPage;
import akSelenium.pageobjects.CheckoutPage;
import akSelenium.pageobjects.ConfirmationPage;
import akSelenium.pageobjects.LandingPage;
import akSelenium.pageobjects.OrdersPage;
import akSelenium.pageobjects.ProductCataloguePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
		
		 String productName = "ZARA COAT 3";
		@Test(dataProvider="getData", groups= {"Purchase"})
		public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException 
		{
			
		// TODO Auto-generated method stub
		ProductCataloguePage productCatalogue = landingPage.login(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage= productCatalogue.goToCart();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submit();
		String confirmationMessgae = confirmationPage.getConfirmation();
		Assert.assertTrue(confirmationMessgae.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
		@Test(dependsOnMethods= {"SubmitOrder"})
		public void OrderHistory() {
			ProductCataloguePage productCatalogue = landingPage.login("atul721@gmail.com", "Test@1234");
			OrdersPage ordersPage = productCatalogue.goToOrders();
			Assert.assertTrue(ordersPage.verifyOrdersDisplay(productName)); 
			
		}
		
		@Test(dependsOnMethods= {"SubmitOrder"})
		public void deleteOrder() {
			ProductCataloguePage productCatalogue = landingPage.login("atul721@gmail.com", "Test@1234");
			OrdersPage ordersPage = productCatalogue.goToOrders();
			ordersPage.deleteItem(productName);
		}
		
		
		
		@DataProvider
		public Object[][] getData() throws IOException {

		List<HashMap<String,String>> data = getJsonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\akSelenium\\data\\PurcahseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		}

}
