package common;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*
 * Author: Tran Hoan, mobile/zalo: +84-979535822
 * Initiate some common methods to testing purpose using relative wait
 * This class can be use after Day16 of my course to make more effective and stable test script
 * To handle Flaky test appearing due to some other Selenium Exception types
 * feel free contact HoanTran to get more detail strategies.
 */
public class CommonBase {
	public WebDriver driver;
	public int initWaitTime = 10;

	public WebDriver initChromeDriver(String URL) {
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		return driver;
	}

	public void inputTextJavaScriptInnerHTML(By inputElement, String companyName) {
		WebElement element = driver.findElement(inputElement);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = '" + companyName + "'", element);
		} catch (StaleElementReferenceException ex) {
			pause(1000);
			inputTextJavaScriptInnerHTML(inputElement, companyName);
		}
	}

	public void inputTextJavaScriptValue(By locator, String value) {
		WebElement element = getElementPresentDOM(locator);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + value + "'", element);
		} catch (StaleElementReferenceException ex) {
			pause(1000);
			inputTextJavaScriptValue(locator, value);
		}
	}

	public void scrollToElement(By locator) {
		WebElement element = getElementPresentDOM(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public WebElement getElementPresentDOM(By locator) {
		//WebDriverWait wait = new WebDriverWait(driver, initWaitTime);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}

	public boolean isElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));
		//WebDriverWait wait = new WebDriverWait(driver, initWaitTime);

		wait.until(ExpectedConditions.visibilityOf(getElementPresentDOM(locator)));
		return getElementPresentDOM(locator).isDisplayed();
	}

	public void click(By locator) {
		WebElement element = getElementPresentDOM(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));
		//WebDriverWait wait = new WebDriverWait(driver, initWaitTime);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public void type(By locator, String value) {
		WebElement element = getElementPresentDOM(locator);
		element.sendKeys(value);
	}

	/**
	 * pause driver in timeInMillis
	 * 
	 * @param timeInMillis
	 */
	public void pause(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get absolute path of file
	 * 
	 * @param relativeFilePath
	 * @return
	 */
	public String getAbsoluteFilePath(String relativeFilePath) {
		String curDir = System.getProperty("user.dir");
		String absolutePath = curDir + relativeFilePath;
		return absolutePath;
	}

	public void quitDriver(WebDriver dr) {
		if (dr.toString().contains("null")) {
			System.out.print("All Browser windows are closed ");
		} else {
			dr.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			dr.manage().deleteAllCookies();
			dr.close();
		}
	}
}