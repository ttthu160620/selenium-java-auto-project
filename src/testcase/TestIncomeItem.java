package testcase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AccountInformation;
import commons.BaseTest;
import intefaces.ManagePageUIs;
import pageObjects.IncomeItemPage;
import pageObjects.LoginPage;
import pageObjects.ManagePage;
import pageObjects.PageGeneratorManager;

public class TestIncomeItem extends BaseTest{
	WebDriver driver;
	LoginPage loginPage;
	ManagePage managePage;
	IncomeItemPage incomePage;
	String project = "AN PHÚ";
	String income_add = "Income new " + getRandomNumber();
	String income_edit = "Income edit " + getRandomNumber();
	String status = "Có hiệu lực";
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		managePage = PageGeneratorManager.getManagePage(driver);
		incomePage = PageGeneratorManager.getIncomeItemPage(driver);
		
		log.info("Precondition: Login page");
		loginPage.loginPage(AccountInformation.USERNAME, AccountInformation.PASSWORD);
		managePage.verifyLogoIsDisplayed();
	}
	
//	Verify user can add income item
	@Test
	public void IncomeItem_TC_01() {
		log.info("Hover on 'Quản lý thu chi'");
		managePage.hoverOnLeftMenuItem(ManagePageUIs.MENU_BUDGET_MANAGEMENT);
		
		log.info("Click on 'Đề mục thu'");
		managePage.clickOnLeftMenuItem(ManagePageUIs.MENU_INNCOME_ITEM_MANAGEMENT);
		
		log.info("Click on 'Thêm mới' button");
		managePage.clickOnAddNewButton();
		
		log.info("Verify 'Nhập thông tin dự án' popup is displayed");
		incomePage.verifyIncomeInfoPopupDisplayed();
		
		log.info("Select project name: " + project);
		incomePage.selectProjectOption(project);
		
		log.info("Enter income item name: " + income_add);
		incomePage.enterIncomeItemNname(income_add);
		
		log.info("Select status option: " + status);
		incomePage.selectStatusOption(status);
		
		log.info("Click on 'Lưu' button");
		incomePage.clickOnSaveButton();
		
		log.info("Verify 'Lưu thành công' message is displayed");
		managePage.verifySaveSucessMsgDisplayed();
	}
	
//	Verify user can edit icome item
	@Test
	public void IncomeItem_TC_02() {
		log.info("Click on Edit button of " + income_add);
		incomePage.clickOnEditButtonOfIncome(income_add);
		
		log.info("Verify 'Nhập thông tin dự án' popup is displayed");
		incomePage.verifyIncomeInfoPopupDisplayed();
		
		log.info("Enter income item name: " + income_edit);
		incomePage.enterIncomeItemNname(income_edit);
		
		log.info("Click on 'Lưu' button");
		incomePage.clickOnSaveButton();
		
		log.info("Verify 'Cập nhật thành công' message is displayed");
		managePage.verifyUpdateSucessMsgDisplayed();
	}
	
	
//	Verify user can edit project name
	@Test
	public void IncomeItem_TC_03() {
		log.info("Click on Delete button of " + income_edit);
		incomePage.clickOnDeleteButtonOfIncome(income_edit);
		
		log.info("Verify delete alert is displayed");
		incomePage.verifyDeleteAlertDisplayed();
		
		log.info("Click on OK button on alert");
		incomePage.acceptDeleteAlert();
		
		log.info("Verify 'Xóa thành công' message is displayed");
		managePage.verifyDeleteSucessMsgDisplayed();
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
