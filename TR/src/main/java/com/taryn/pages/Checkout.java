package com.taryn.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.taryn.common.WebDriverUtility;
import com.taryn.items.User;

public class Checkout extends WebDriverUtility {

	WebDriver driver;
	WebElement element;
	String successPage = new String("Success Page");
	// Interface:

	By hover = By.cssSelector(".field #shipping-method");
	By clickable = By.cssSelector(".field #shipping-method [value='flatrate_flatrate']");
	String utag = "utag.js";
	String analytics = "analytics.js";
	By checkoutButton = By.cssSelector(".button.button-alternate.primary.checkout");
	By emailAddress = By.cssSelector("#customer-email");
	Object inputEmailAddress = "lbartshchuk@gorillagroup.com";
	By password = By.cssSelector("#customer-password");
	Object inputPassword = "Test123123";
	By loginButton = By.cssSelector(".action.login.primary");
	By nextButton = By.cssSelector(".primary .button.action.continue.primary");
	By nextButtonPayPal = By.cssSelector(".form .primary .button.button-alternate.continue.primary");

	By payPalBraintree = By.cssSelector(".checkout .paypal .button");
	By creditCardBraintree = By.cssSelector(".form .payment-method [for='braintree']");
	By visaCard1 = By.cssSelector(".payment-method-title [id='braintree_cc_vault_3']");
	By visaCard2 = By.cssSelector(".form .payment-method [for='braintree_cc_vault_3']");
	// By visaCard = By.cssSelector(".payment-method-title
	// [for='braintree_cc_vault_3']");

	By continueToPaypal = By.cssSelector(".primary #braintree_paypal_continue_to");
	By proceedPayPal = By.cssSelector(".content [class='button button--jumbo button--primary loginButton']");
	// By placeOrderVisa =
	// By.xpath(".//*[@id='checkout-payment-method-load']/div/div[1]/div[2]/div[2]/div[3]/div/button");
	// By placeOrderBraintree = By.cssSelector(".primary
	// #braintree_paypal_place_order");
	By expressPayPal = By.cssSelector(".checkout .item .paypal .button");
	By placeOrderExpressPayPal = By.cssSelector(".primary #review-button");

	By creditCardNumber = By.cssSelector("#credit-card-number");
	String card = "4111111111111111";
	By expirationMonth = By.cssSelector("#expiration-month");
	String month = "11";
	By expirationYear = By.cssSelector("#expiration-year");
	String year = "20";
	By cvv = By.cssSelector("#cvv");
	String cvvNumber = "333";
	By placeOrderCreditCard = By
			.xpath(".//*[@id='checkout-payment-method-load']/div/div[2]/div[3]/div[2]/div[4]/div/button");
	By placeOrderVisa = By.cssSelector("[class='payment-method _active'] .payment-method-content .primary .action");

	// Constructor - driver initialization.
	public Checkout(WebDriver driver) {
		this.driver = driver;
	}

	// Method Login to adsy.com:
	public Checkout checkoutActions() throws InterruptedException {
		getScriptOnPage(driver, utag);
		getScriptOnPage(driver, analytics);
		click(this.driver, checkoutButton);
		type(this.driver, emailAddress, inputEmailAddress);
		click(this.driver, emailAddress);
		click(this.driver, password);
		type(this.driver, password, inputPassword);
		click(this.driver, loginButton);
		click(this.driver, nextButtonPayPal);
		click(this.driver, visaCard1);
		click(this.driver, visaCard2);
		click(driver, placeOrderVisa);

		return new Checkout(driver);

	}

	public Checkout inputCreditCardData() throws InterruptedException {
		click(this.driver, creditCardNumber);
		type(driver, creditCardNumber, card);
		click(this.driver, expirationMonth);
		type(driver, expirationMonth, month);
		click(this.driver, expirationYear);
		type(driver, expirationYear, year);
		click(this.driver, cvv);
		type(driver, cvv, cvvNumber);
		click(driver, placeOrderCreditCard);

		return new Checkout(driver);

	}

	public Checkout checkoutExpressPayPal() throws InterruptedException {

		getScriptOnPage(driver, utag);
		getScriptOnPage(driver, analytics);
		click(this.driver, expressPayPal);
		click(this.driver, proceedPayPal);
		clickInvisibleSelectElement(driver, hover, clickable);
		click(this.driver, placeOrderExpressPayPal);

		return new Checkout(driver);
	}

	public void compareTitle(WebDriver driver, String expectedTitle) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.titleContains(successPage));
		assertEquals(driver.getTitle(), expectedTitle);
		System.out.println("actualTitle  " + driver.getTitle());
		System.out.println("expectedTitle  " + expectedTitle);

	}

}
