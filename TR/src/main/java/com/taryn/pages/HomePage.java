package com.taryn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.taryn.common.WebDriverUtility;
import com.taryn.items.User;

public class HomePage extends WebDriverUtility {

	WebDriver driver;
	WebElement element;
	// Interface:
	String titleOfPageWhereChecking = "Home page";
	String utag = "utag.js";
	String analytics = "analytics.js";
	By mules = By.xpath("//*[contains(text(),'Mules')]");
	public By modal = By.xpath("html/body/div[2]/aside[1]/div[2]/header/button");
	// By mules = By.cssSelector(".level1.nav-2-4.gor-active>a>span");
	// By styles = By.cssSelector(".level-top[data='Styles']");

	By styles = By.cssSelector(".level0.nav-2 .level-top > span");
	By randomProduct = By.cssSelector(".product.photo.product-item-photo.has-secondary");

	// Constructor - driver initialization.
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage SelectProduct() throws InterruptedException {

		// clickInvisibleElement(driver, styles, mules);
		getScriptOnPage(driver, utag);
		getScriptOnPage(driver, analytics);
		click(driver, styles);
		selectRandomItems(driver, randomProduct);

		return new HomePage(driver);
	}

}
