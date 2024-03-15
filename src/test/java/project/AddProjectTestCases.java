package project;

import org.testng.annotations.Test;

import common.CommonActions;
import common.CommonBrowser;
import constants.WebInfo;
import pom.AddProjectPage;
import pom.DashboardPage;
import pom.LoginPage;
import pom.ProjectPage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class AddProjectTestCases {
	WebDriver driver;
	CommonBrowser commonBrowser;
	CommonActions action;

	@Test
	@Ignore
	/*
	 * Tạo project mới với đầy đủ các trường các trường bắt buộc
	 */
	public void TC_ADDPRO_001() {
		String titleName = " project codestar";
		LoginPage login = new LoginPage(driver);
		login.clickLogin("admin@demo.com", "riseDemo");
		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.clickProjecLink();
		ProjectPage project = new ProjectPage(driver);
		project.clickAddProjectButton();
		AddProjectPage addProjectPage = new AddProjectPage(driver);
		addProjectPage.saveNewProject(titleName);
		addProjectPage.closeForm();
	}

	@Test
	@Ignore
	/*
	 * Kiem tra truong hop hien thi message khi bo trong tat ca cac truong
	 */
	public void TC_ADDPRO_002() {
		String titleName = "";

		ProjectPage project = new ProjectPage(driver);
		project.clickAddProjectButton();
		AddProjectPage addProjectPage = new AddProjectPage(driver);
		addProjectPage.saveNewProject(titleName);
		// check errror message co hien thi khoong
		addProjectPage.checkRequireFields("This field is required.");
	}

	@Test
	/*
	 * Kiem tra truong hop hien thi message khi bo trong tat ca cac truong
	 */
	@Ignore
	public void TC_ADDPRO_003() {
		String titleName = "Add full field";
		String description ="This is description";
		String startDate = "26-02-2024";
		String deadLine ="01-03-2024";
		String price ="10000";
		String label ="Urgent";
		
		LoginPage login = new LoginPage(driver);
		login.clickLogin("admin@demo.com", "riseDemo");
		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.clickProjecLink();
		ProjectPage project = new ProjectPage(driver);
		project.clickAddProjectButton();
		AddProjectPage addProjectPage = new AddProjectPage(driver);
		addProjectPage.saveFullField(titleName,description,startDate,deadLine,price,label);		
	}
	
	@Test
	public void TC_ADDPRO_004() {
		String titleName = "Add full field";
		String description ="This is description";
		String startDate = "28-02-2024";
		String deadLine ="01-03-2024";
		String price ="10000";
		String label ="Urgent";
		
		LoginPage login = new LoginPage(driver);
		login.clickLogin("admin@demo.com", "riseDemo");
		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.clickProjecLink();
		ProjectPage project = new ProjectPage(driver);
		project.clickAddProjectButton();
		AddProjectPage addProjectPage = new AddProjectPage(driver);
		addProjectPage.saveFullField2(titleName,description,startDate,deadLine,price,label);		
		addProjectPage.closeForm();
	}

//	@BeforeTest
//	public void beforeTest() {
//		commonBrowser = new CommonBrowser();
//		action = new CommonActions();
//		driver = commonBrowser.initEdgeDriver(WebInfo.test_URL);
//	}
	@BeforeTest
	@Parameters("browser")
	public void beforeTest(@Optional("chrome") String browser) {
		commonBrowser = new CommonBrowser();
		action = new CommonActions();
		driver = commonBrowser.setupDriver(browser, WebInfo.test_URL);
		commonBrowser.pause(4000);
	}

	@AfterTest
	public void afterTest() {
		commonBrowser.quitDriver(driver);
	}

}
