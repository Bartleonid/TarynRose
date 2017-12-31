package com.taryn.common;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	protected WebDriver driver;

	// @Test(invocationCount = 5)
	public void start() {
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		options.addArguments("disable-extensions");
		prefs.put("credentials_enable_service", false);
		prefs.put("password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("chrome.switches", "--disable-extensions");
		options.addArguments("--test-type");
		options.addArguments("disable-infobars");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		cap.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\lbartashuk\\workspace\\CHCH\\Chromedriver\\chromedriver.exe");
		driver = new ChromeDriver(cap);
		driver.get("https://tarynrose-qa.mage.onestop.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}

	private final static int TIMEOUT = 30000;

	public static WebElement findElement(WebDriver driver, By locator) {
		By cssLocator = locator;
		waitForElement(driver, cssLocator, TIMEOUT);
		WebElement element = driver.findElement(cssLocator);
		return element;
	}

	public void testWebAlert() throws InterruptedException {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {

		}
	}

	public void compareTitle(WebDriver driver, String expectedTitle) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains(expectedTitle));
		assertEquals(driver.getTitle(), expectedTitle);
		System.out.println("actualTitle:  " + driver.getTitle());
		System.out.println("expectedTitle:  " + expectedTitle);

	}

	public void compareElement(WebDriver driver, By locator, String expectedElement) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedElement));
		System.out.println("element   " + expectedElement);
		String actualElement = driver.findElement(locator).getText().substring(0, 9);
		System.out.println("element   " + actualElement);
		assertEquals(actualElement, expectedElement);
	}

	public void getScriptOnPage(WebDriver driver, String tag) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains(driver.getTitle()));
		boolean pageSource = driver.getPageSource().contains(tag);
		assertTrue(pageSource);
		System.out.println("pageSource:  " + pageSource);

	}

	public void findSuccessElementOnPage(WebDriver driver, By expectedElement) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(expectedElement));
		System.out.println("element   " + expectedElement);
		String actualElement = driver.findElement(expectedElement).getText().substring(0);
		System.out.println("element   " + actualElement);
		assertEquals(actualElement, expectedElement);
	}

	public void closeModalPopUp(By closeModal) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(closeModal));
		System.out.println("closeModal   " + closeModal);
		if (driver.findElement(closeModal) != null) {
			click(this.driver, closeModal);

		}

	}

	public void selectRandomItems(WebDriver driver, By randomElement) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(randomElement));
		List<WebElement> listings = driver.findElements(randomElement);
		Random random = new Random();
		int randomValue = random.nextInt(listings.size());
		listings.get(randomValue).click();
	}

	protected static void waitForPageLoaded(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		for (int i = 0; i < tabs.size(); i++) {
			driver.switchTo().window(tabs.get(i));
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			org.openqa.selenium.support.ui.Wait<WebDriver> wait = new WebDriverWait(driver, 30);
			try {
				wait.until(expectation);
			} catch (Throwable error) {
			}
		}
	}

	protected WebElement getElement(WebDriver driver, Object obj) {
		// System.out.println(driver + " driver");
		// System.out.println(obj + " object");
		if (obj.getClass().getName().contains(By.class.getName())) {
			return waitForElement(driver, (By) obj, 30);
		}
		return (WebElement) obj;
	}

	protected void click(WebDriver driver, By by) throws InterruptedException {
		WebElement element = waitForElement(driver, by, TIMEOUT);

		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			action.click(element).build().perform();
			System.out.println("element is clicked  " + by);
			waitForPageLoaded(driver);
		} catch (Exception e) {
		}
	}

	protected void clickInvisibleSelectElement(WebDriver driver, By hover, By clickable) throws InterruptedException {

		try {
			WebElement hoverElement = driver.findElement(hover);
			Actions action = new Actions(driver);
			action.moveToElement(hoverElement).build().perform();
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(clickable));
			driver.findElement(clickable).click();
			System.out.println("element is clicked  " + clickable);
			waitForPageLoaded(driver);
		} catch (Exception e) {
		}
	}

	protected void type(WebDriver driver, By by, Object text) {
		text = String.valueOf(text);
		System.out.println(
				"Filling a text '" + text + "' into the field '" + by + "' on page '" + driver.getCurrentUrl() + "'");
		WebElement element = waitForElement(driver, by, TIMEOUT);
		try {
			element.clear();
			element.sendKeys((String) text);
		} catch (Exception e) {
		}
	}

	protected void selectOptionFromDropDownList(WebDriver driver, By quantity, String value)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(quantity));
		Select select = new Select(driver.findElement(quantity));
		// select.deselectAll();
		select.selectByVisibleText(value);
	}

	public static WebElement waitForElement(WebDriver driver, final By by, int timeout) {
		org.openqa.selenium.support.ui.Wait<WebDriver> wait = new WebDriverWait(driver, timeout);

		driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

		ExpectedCondition<WebElement> expectation = new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		};

		try {
			return wait.until(expectation);
		} catch (Throwable error) {
			return null;
		}
	}

}
