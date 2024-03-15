package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// Properties
	// xpath of Projec link in left menu
	@FindBy(xpath = "//a [@href='https://rise.fairsketch.com/projects/all_projects']")
	WebElement projectLink;
	@FindBy(xpath = "//h4 [text()='Dashboard']")
	WebElement dashboardTitle;

	// Action
	// click Project link
	public void clickProjecLink() {
		wait.until(ExpectedConditions.visibilityOf(dashboardTitle));
		
		wait.until(ExpectedConditions.visibilityOf(projectLink));
		commonAction.click(projectLink, driver);
		wait.until(ExpectedConditions.invisibilityOf(dashboardTitle));

	}

}
