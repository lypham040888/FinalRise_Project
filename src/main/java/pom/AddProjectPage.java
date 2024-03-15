package pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class AddProjectPage extends BasePage {

	public AddProjectPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(name = "title")
	WebElement txtTitle;
	@FindBy(id = "save-and-continue-button")
	WebElement btnSave;
	@FindBy(xpath = "//button[contains(text(),' Close')]")
	WebElement btnClose;
	@FindBy(id = "title-error")
	WebElement errorMessage;
	@FindBy(xpath =  "//label[text()='Project type']/following-sibling::div/div")
	WebElement divProjectType;
	@FindBy(xpath = "//div[@class='select2-result-label' and contains(text(),'Internal Project')]")
	WebElement ddlInternalProjectType;
	@FindBy(xpath = "//label[text()='Client']/following-sibling::div/div")
	WebElement divClient;
	@FindBy(xpath = "//div[@class='select2-result-label' and contains(text(),'Edd Leffler')]")
	WebElement divClientOptional;
	@FindBy(xpath = "(//span[@class='select2-chosen'])[3]")
	WebElement currentProjectType;
	
			
	@FindBy(xpath = "//label[text()='Description']/following-sibling::div")
	WebElement divDescription;
	@FindBy(className ="note-editable")
	WebElement txtDescription;
	@FindBy(name = "start_date")
	WebElement startDate;
	@FindBy(name = "deadline")
	WebElement deadline;
	@FindBy(name = "price")
	WebElement price;
	@FindBy(xpath ="//label[text()='Labels']/following-sibling::div/div")
	WebElement divLabel;
	
	@FindBy(xpath ="//label[text()='Labels']/following-sibling::div//div//input")
	WebElement inputLabel;
	
	@FindBy(xpath ="//label[text()='Deadline']/following-sibling::div/input")
	WebElement inputDeadline;
	
	
	@FindBy(name =  "description")
	WebElement textAreaDescription;

	public void saveNewProject(String titleName) {
		wait.until(ExpectedConditions.visibilityOf(txtTitle));
		commonAction.sendKeys(txtTitle, titleName);
		commonAction.click(btnSave, driver);
		commonAction.pause(3000);
	}
	public void saveFullField(String titleName, String description, String startdate, String enddate, String priceValue, String labelValue) {
		//wait.until(ExpectedConditions.visibilityOf(txtTitle));
		commonAction.sendKeys(txtTitle, titleName);
		commonAction.click(divClient,driver);
		commonAction.click(divClientOptional, driver);
		commonAction.pause(2000);
		Actions action = new Actions(driver);
		action.moveToElement(divDescription).doubleClick().build().perform();
		commonAction.pause(2000);
		commonAction.sendKeys(txtDescription, description);
		scriptExcutor.executeScript(
                "arguments[0].removeAttribute('autocomplete','autocomplete');",startDate);
		commonAction.sendKeys(startDate, startdate);
		commonAction.pause(2000);
		startDate.sendKeys(Keys.ENTER);
		//commonAction.click(divDeadline, driver);
		scriptExcutor.executeScript(
               "arguments[0].removeAttribute('autocomplete','autocomplete')",inputDeadline);
		commonAction.sendKeys(inputDeadline, enddate);
		commonAction.pause(2000);
		inputDeadline.sendKeys(Keys.ENTER);
		
		commonAction.sendKeys(price, priceValue);
		//commonAction.sendKeys(divLabel, labelValue);
		
		commonAction.click(btnSave, driver);
		commonAction.pause(3000);
	}
	public void saveFullField2(String titleName, String descriptionValue, String startdate, String enddate, String priceValue, String labelValue) {
		commonAction.sendKeys(txtTitle, titleName);
		commonAction.click(divProjectType, driver);
		commonAction.pause(1000);
		commonAction.click(ddlInternalProjectType, driver);
		commonAction.pause(2000);
		commonAction.click(divDescription,driver);
		commonAction.pause(1000);
		commonAction.sendKeys(txtDescription,descriptionValue);
		commonAction.pause(1000);
		String selectedProjectType = currentProjectType.getText();
		
		if(selectedProjectType.contains("Client Project"))
		{
			commonAction.click(divClient, driver);
			commonAction.pause(2000);
			commonAction.click(divClientOptional, driver);
		}
		commonAction.sendKeys(startDate, startdate);
		startDate.sendKeys(Keys.ENTER);
		commonAction.sendKeys(inputDeadline, enddate);
		inputDeadline.sendKeys(Keys.ENTER);
		commonAction.sendKeys(price, priceValue);
		
		commonAction.click(divLabel, driver);
		commonAction.sendKeys(inputLabel, labelValue);
		inputLabel.sendKeys(Keys.ENTER);
		
		commonAction.click(btnSave, driver);
		commonAction.pause(3000);
	}

	public void closeForm() {
		commonAction.click(btnClose, driver);

	}

	public void checkRequireFields(String message) {
		assertEquals(errorMessage.isDisplayed(), true);
		assertEquals(errorMessage.getText(), message);
	}
}
