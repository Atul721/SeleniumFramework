package akSelenium.tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import akSelenium.TestComponent.BaseTest;
import akSelenium.pageobjects.CartPage;
import akSelenium.pageobjects.ProductCataloguePage;

public class ErrorValidationsTest extends BaseTest {

		@Test(groups= {"ErrorHandling"})
		public void VerifyError() throws IOException, InterruptedException 
		{
			
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		landingPage.login("atul721@gmail.com", "Test@123");
		Assert.assertEquals("Incorrect email  password.",landingPage.verifyErrorMessage());
		
	}
		@Test
		public void cartErrorValidation() throws InterruptedException
		{
		String productName = "ZARA COAT 3";
		ProductCataloguePage productCatalogue = landingPage.login("daksha@gmail.com", "Test@1234");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage= productCatalogue.goToCart();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		}

}
