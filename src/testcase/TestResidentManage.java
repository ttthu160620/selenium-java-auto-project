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
import pageObjects.ResidentManagePage;

public class TestResidentManage extends BaseTest{
	WebDriver driver;
	LoginPage loginPage;
	ManagePage managePage;
	ResidentManagePage residentPage;
	String project = "AN PHÚ";
	String residentFamily = "Cư dân";
	String residentName = "Tên " + getRandomNumber();
	String relationship = "Cháu";
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		managePage = PageGeneratorManager.getManagePage(driver);
		residentPage = PageGeneratorManager.getResidentManagePage(driver);
		
		log.info("Precondition: Login page");
		loginPage.loginPage(AccountInformation.USERNAME, AccountInformation.PASSWORD);
		managePage.verifyLogoIsDisplayed();
	}
	
//	Verify user can add income item
	@Test
	public void Resident_TC_01() {
		
		log.info("Click on 'Quản lý cư dân'");
		managePage.clickOnLeftMenuItem(ManagePageUIs.MENU_RESIDENT_MANAGEMENT);
		
		log.info("Click on 'Thêm mới' button");
		managePage.clickOnAddNewButton();
		
		log.info("Verify 'Nhập thông tin cư dân' popup is displayed");
		residentPage.verifyResidentInfoPopupDisplayed();
		
		log.info("Select project name: " + project);
		residentPage.selectProjectOption(project);
		
		log.info("Enter resident famiy: " + residentFamily);
		residentPage.enterResidentFamily(residentFamily);
		
		log.info("Enter resident name: " + residentName);
		residentPage.enterResidentName(residentName);
		
		log.info("Select relationship option: " + relationship);
		residentPage.selectRelationshipOption(relationship);
		
		log.info("Click on 'Lưu' button");
		residentPage.clickOnSaveButton();
		
		log.info("Verify 'Lưu thành công' message is displayed");
		managePage.verifySaveSucessMsgDisplayed();
	}
	
//	Verify user can edit icome item
	@Test
	public void Resident_TC_02() {
		log.info("Click on Edit button on row 5");
		residentPage.clickOnEditButtonOfResident("5");
		
		log.info("Verify 'Nhập thông tin dự án' popup is displayed");
		residentPage.verifyResidentInfoPopupDisplayed();
		
		log.info("Enter resident name: " + residentName + " edit");
		residentPage.enterResidentName(residentName + " edit");
		
		log.info("Click on 'Lưu' button");
		residentPage.clickOnSaveButton();
		
		log.info("Verify 'Cập nhật thành công' message is displayed");
		managePage.verifyUpdateSucessMsgDisplayed();
	}
	
	
//	Verify user can edit project name
	@Test
	public void Resident_TC_03() {
		log.info("Click on Delete button on row 5 ");
		residentPage.clickOnDeleteButtonOfResident("5");
		
		log.info("Verify delete alert is displayed");
		residentPage.verifyDeleteAlertDisplayed();
		
		log.info("Click on OK button on alert");
		residentPage.acceptDeleteAlert();
		
		log.info("Verify 'Xóa thành công' message is displayed");
		managePage.verifyDeleteSucessMsgDisplayed();
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
