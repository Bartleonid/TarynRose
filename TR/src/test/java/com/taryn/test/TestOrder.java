package com.taryn.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.taryn.common.WebDriverUtility;
import com.taryn.items.CreditCard;
import com.taryn.items.Items;
import com.taryn.items.User;
import com.taryn.pages.Checkout;
import com.taryn.pages.HomePage;
import com.taryn.pages.PDP;

public class TestOrder extends WebDriverUtility {

	String successPage = new String("Success Page");
	User visitor = new User("lbartshchuk@gorillagroup.com", "Test123123");
	Items items = new Items(10);
	CreditCard creditCard = new CreditCard("4242424242424242", "01/19", "123", "TestUser", "Lenja Test",
			"United States", "Texas", "Houston", "+1 713-798-4857");

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { visitor } };
	}

	@Test(dataProvider = "getData", invocationCount = 5)
	public void PlaceOrderCreditCardBraintree(User user) throws InterruptedException {
		start();

		// Initialize page
		HomePage homePage = new HomePage(driver);
		PDP pdp = new PDP(driver);
		Checkout check = new Checkout(driver);

		closeModalPopUp(homePage.modal);
		homePage.SelectProduct();
		pdp.addToCart();
		check.checkoutActions();
		check.compareTitle(driver, successPage);

		driver.quit();
	}

	@Test(dataProvider = "getData", invocationCount = 0)
	public void PayPalExpress(User user) throws InterruptedException {
		start();

		// Initialize page
		HomePage homePage = new HomePage(driver);
		PDP pdp = new PDP(driver);
		Checkout check = new Checkout(driver);

		closeModalPopUp(homePage.modal);
		homePage.SelectProduct();
		pdp.addToCart();
		check.checkoutExpressPayPal();
		check.compareTitle(driver, successPage);

		driver.quit();
	}
}