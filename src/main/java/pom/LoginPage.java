package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	// Properties

	@FindBy(name = "email")
	@CacheLookup
	WebElement username;
	@FindBy(name = "password")
	@CacheLookup
	WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnLogin;
	@FindBy(xpath = "//div[@class='alert alert-danger']")
	WebElement divAlert;

	// clickLogin method
	public void clickLogin(String user_name, String pass_word) {
		commonAction.sendKeys(username, user_name);
		commonAction.sendKeys(password, pass_word);
		commonAction.click(btnLogin, driver);
		commonAction.pause(1000);
		//wait.until(ExpectedConditions.invisibilityOf(btnLogin));

	}

}
