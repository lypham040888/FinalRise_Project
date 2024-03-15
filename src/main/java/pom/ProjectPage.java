package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectPage extends BasePage {

	public ProjectPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// Properties
	// xpath of Add Button
	@FindBy(xpath = "//a[@title='Add project']")
	WebElement btnAdd;

	// Action
	// click to Add Button
	public void clickAddProjectButton() {
		wait.until(ExpectedConditions.visibilityOf(btnAdd));
		wait.until(ExpectedConditions.elementToBeClickable(btnAdd));
		commonAction.click(btnAdd, driver);
		commonAction.pause(3000);

	}

}
