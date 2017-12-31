package com.taryn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.taryn.common.WebDriverUtility;
import com.taryn.items.CreditCard;
import com.taryn.items.Items;
import com.taryn.items.User;

public class PDP extends WebDriverUtility {

	WebDriver driver;
	Object value = "5";

	// Interface:
	String utag = "utag.js";
	String analytics = "analytics.js";
	String titleOfPageWhereChecking = "Styles";
	By randomSize = By.cssSelector(".product-add-form .swatch-option.text");
	By colorAttribute = By.cssSelector(
			"[class='swatch-attribute color_name'] [class='swatch-attribute-options clearfix'] .swatch-option");
	By addToCart = By.cssSelector("#product-addtocart-button");
	By successMessage = By.cssSelector(".message-success.success.message>div");
	String successMessage2 = "You added";

	By cartIcon = By.cssSelector(".minicart-wrapper .action .text");
	// By cartIcon = By.cssSelector(".action.showcart.active");

	// Constructor - driver initialization.
	public PDP(WebDriver driver) {
		this.driver = driver;
	}

	// Method Login:
	public PDP login(User user) throws InterruptedException {
		// click(this.driver, signinBtn);
		// type(this.driver, email, user.email);
		// type(this.driver, password, user.password);
		// click(this.driver, signSubmit);

		return new PDP(driver);
	}

	public PDP addToCart() throws InterruptedException {
		// click(this.driver, colorAttribute);
		getScriptOnPage(driver, analytics);
		getScriptOnPage(driver, utag);
		selectRandomItems(driver, randomSize);
		selectRandomItems(driver, colorAttribute);

		click(this.driver, addToCart);
		// findSuccessElementOnPage(driver, successMessage);
		compareElement(driver, successMessage, successMessage2);
		click(this.driver, cartIcon);

		return new PDP(driver);
	}

	public PDP payByCreditCard(CreditCard crediCard) throws InterruptedException {
		// type(this.driver, creditCard, crediCard.cardNumber);
		// type(this.driver, expiration, crediCard.expiration);
		// type(this.driver, cvv, crediCard.cvv);
		// type(this.driver, nameOnCard, crediCard.nameOnCard);
		// click(this.driver, completeName);
		// type(this.driver, completeName, crediCard.completeName);
		// click(this.driver, shipAddress);
		// type(this.driver, shipAddress, crediCard.shipAddress);
		// click(this.driver, state);
		// type(this.driver, state, crediCard.state);
		// type(this.driver, shipCiity, crediCard.shipCiity);
		// type(this.driver, phone, crediCard.phone);
		// click(this.driver, completeOrder);

		return new PDP(driver);

	}
}
