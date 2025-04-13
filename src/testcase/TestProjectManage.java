package testcase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AccountInformation;
import commons.BaseTest;
import intefaces.ManagePageUIs;
import pageObjects.LoginPage;
import pageObjects.ManagePage;
import pageObjects.PageGeneratorManager;
import pageObjects.ProjectsManagePage;

public class TestProjectManage extends BaseTest{
	WebDriver driver;
	LoginPage loginPage;
	ManagePage managePage;
	ProjectsManagePage projectPage;
	String project_add = "Test project new" + getRandomNumber();
	String project_edit = "Test project edit" + getRandomNumber();
	String project_address = "Address Test";
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		managePage = PageGeneratorManager.getManagePage(driver);
		projectPage = PageGeneratorManager.getProjectsManagePage(driver);
		
		log.info("Precondition: Login page");
		loginPage.loginPage(AccountInformation.USERNAME, AccountInformation.PASSWORD);
		managePage.verifyLogoIsDisplayed();
	}
	
//	Verify user can add project
	@Test
	public void Project_TC_01() {
		log.info("Hover on 'Quản lý hạ tầng'");
		managePage.hoverOnLeftMenuItem(ManagePageUIs.MENU_INFRASTRUCTURE_MANAGEMENT);
		
		log.info("Click on 'Quản lý dự án'");
		managePage.clickOnLeftMenuItem(ManagePageUIs.MENU_PROJECT_MANAGEEMENT);
		
		log.info("Click on 'Thêm mới' button");
		managePage.clickOnAddNewButton();
		
		log.info("Verify 'Nhập thông tin dự án' popup is displayed");
		projectPage.verifyProjectInfoPopupDisplayed();
		
		log.info("Enter project name: " + project_add);
		projectPage.enterProjectName(project_add);
		
		log.info("Enter project address: " + project_address);
		projectPage.enterProjectAddress(project_address);
		
		log.info("Click on 'Lưu' button");
		projectPage.clickOnSaveButton();
		
		log.info("Verify 'Lưu thành công' message is displayed");
		managePage.verifySaveSucessMsgDisplayed();
	}
	
//	Verify user can edit project name
	@Test
	public void Project_TC_02() {
		log.info("Click on Edit button of " + project_add);
		projectPage.clickOnEditButtonOfProjectName(project_add);
		
		log.info("Verify 'Nhập thông tin dự án' popup is displayed");
		projectPage.verifyProjectInfoPopupDisplayed();
		
		log.info("Enter project name: " + project_edit);
		projectPage.enterProjectName(project_edit);
		
		log.info("Click on 'Lưu' button");
		projectPage.clickOnSaveButton();
		
		log.info("Verify 'Cập nhật thành công' message is displayed");
		managePage.verifyUpdateSucessMsgDisplayed();
	}
	
	
//	Verify user can edit project name
	@Test
	public void Project_TC_03() {
		log.info("Click on Delete button of " + project_edit);
		projectPage.clickOnDeleteButtonOfProjectName(project_edit);
		
		log.info("Verify delete alert is displayed");
		projectPage.verifyDeleteAlertDisplayed();
		
		log.info("Click on OK button on alert");
		projectPage.acceptDeleteAlert();
		
		log.info("Verify 'Xóa thành công' message is displayed");
		managePage.verifyDeleteSucessMsgDisplayed();
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
