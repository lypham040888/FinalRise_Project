package pom;

import java.time.Duration;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonActions;

public class BasePage {
	WebDriver driver;
	CommonActions commonAction;
	WebDriverWait wait;
	JavascriptExecutor scriptExcutor;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		commonAction = new CommonActions();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		scriptExcutor =(JavascriptExecutor)driver;

	}
}
