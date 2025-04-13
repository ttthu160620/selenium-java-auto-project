package testcase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AccountInformation;
import commons.BaseTest;
import intefaces.ManagePageUIs;
import pageObjects.BuildingBlockManagePage;
import pageObjects.LoginPage;
import pageObjects.ManagePage;
import pageObjects.PageGeneratorManager;

public class TestBuildingBlockPage extends BaseTest{
	WebDriver driver;
	LoginPage loginPage;
	ManagePage managePage;
	BuildingBlockManagePage buildingBlockPage;
	String project = "AN PHÚ";
	String building_add = "Building new " + getRandomNumber();
	String building_edit = "Building edit " + getRandomNumber();
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		managePage = PageGeneratorManager.getManagePage(driver);
		buildingBlockPage = PageGeneratorManager.getBuildingBlockPage(driver);
		
		log.info("Precondition: Login page");
		loginPage.loginPage(AccountInformation.USERNAME, AccountInformation.PASSWORD);
		managePage.verifyLogoIsDisplayed();
	}
	
//	Verify user can add building block
	@Test
	public void Building_TC_01() {
		log.info("Hover on 'Quản lý hạ tầng'");
		managePage.hoverOnLeftMenuItem(ManagePageUIs.MENU_INFRASTRUCTURE_MANAGEMENT);
		
		log.info("Click on 'Quản lý khối nhà'");
		managePage.clickOnLeftMenuItem(ManagePageUIs.MENU_BUILDING_BLOCK_MANAGEMENT);
		
		log.info("Click on 'Thêm mới' button");
		managePage.clickOnAddNewButton();
		
		log.info("Verify 'Nhập thông tin khối nhà' popup is displayed");
		buildingBlockPage.verifyBuildingInfoPopupDisplayed();
		
		log.info("Select project name: " + project);
		buildingBlockPage.selectProjectOption(project);
		
		log.info("Enter buildig block name: " + building_add);
		buildingBlockPage.enterBuildingBlockNname(building_add);
		
		log.info("Click on 'Lưu' button");
		buildingBlockPage.clickOnSaveButton();
		
		log.info("Verify 'Lưu thành công' message is displayed");
		managePage.verifySaveSucessMsgDisplayed();
	}
	
//	Verify user can edit building block
	@Test
	public void Building_TC_02() {
		log.info("Click on Edit button of " + building_add);
		buildingBlockPage.clickOnEditButtonOfBuilding(building_add);
		
		log.info("Verify 'Nhập thông tin khối nhà' popup is displayed");
		buildingBlockPage.verifyBuildingInfoPopupDisplayed();
		
		log.info("Enter buildig block name: " + building_edit);
		buildingBlockPage.enterBuildingBlockNname(building_edit);
		
		log.info("Click on 'Lưu' button");
		buildingBlockPage.clickOnSaveButton();
		
		log.info("Verify 'Cập nhật thành công' message is displayed");
		managePage.verifyUpdateSucessMsgDisplayed();
	}
	
	
//	Verify user can edit project name
	@Test
	public void Building_TC_03() {
		log.info("Click on Delete button of " + building_edit);
		buildingBlockPage.clickOnDeleteButtonOfBuilding(building_edit);
		
		log.info("Verify delete alert is displayed");
		buildingBlockPage.verifyDeleteAlertDisplayed();
		
		log.info("Click on OK button on alert");
		buildingBlockPage.acceptDeleteAlert();
		
		log.info("Verify 'Xóa thành công' message is displayed");
		managePage.verifyDeleteSucessMsgDisplayed();
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
