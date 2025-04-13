package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;

public class IncomeItemPage extends BasePage{
	private final String POPUP_INCOME_ITEM_INFO = "xpath=//div[contains(@class,'ui-dialog-titlebar')]";
	private final String DD_PROJECT_NAME = "xpath=//select[@id='dropDuAn']"; //dropdown
	private final String TB_ITEM_NAME = "xpath=//input[@id='TenDeMuc']"; // tên đề mục
	private final String DD_STATUS = "xpath=//select[@id='dropTrangThai']"; // tên đề mục
	private final String BTN_SAVE = "xpath=//input[@type='submit']";
	private final String TXT_INCOME_NAME = "xpath=//table//td[text()='%s']";
	private final String BTN_EDIT = "xpath=//table//td[text()='%s']/..//a[@title='Sửa']"; // edit following to project name
	private final String BTN_DELETE = "xpath=//table//td[text()='%s']/..//a[@title='Xóa']";
	private final String BTN_NEXT_PAGE = "xpath=//a[text()='Sau']";
	
	
	private WebDriver driver;
	public IncomeItemPage(WebDriver driver) {
		this.driver = driver;
	}
	
//	=============ACTION===================
	
	public void selectProjectOption(String project) {
		selectItemInDefaultDropdown(driver, DD_PROJECT_NAME, project);
	}
	
	public void selectStatusOption(String status) {
		selectItemInDefaultDropdown(driver, DD_STATUS, status);
	}
	
	public void enterIncomeItemNname(String itemName) {
		waitForElementVisible(driver, TB_ITEM_NAME);
		sendkeyToElement(driver, TB_ITEM_NAME, itemName);
	}
	
	public void clickOnSaveButton() {
		waitForClickable(driver, BTN_SAVE);
		clickToElement(driver, BTN_SAVE);
	}
	
	public void clickOnEditButtonOfIncome(String projectName) {
		for(int i = 1; i <= 5; i++) {
			if(isElementUndisplayed(driver, TXT_INCOME_NAME, projectName) == false) {
				waitForClickable(driver, BTN_EDIT, projectName);
				clickToElement(driver, BTN_EDIT, projectName);
				return;
			}
			else {
				clickOnNextPageButton();
			}
		}
	}
	
	public void clickOnDeleteButtonOfIncome(String projectName) {
		for(int i = 1; i <= 5; i++) {
			if(isElementUndisplayed(driver, TXT_INCOME_NAME, projectName) == false) {
				waitForClickable(driver, BTN_DELETE, projectName);
				clickToElement(driver, BTN_DELETE, projectName);
				return;
			}
			else {
				clickOnNextPageButton();
			}
		}
	}
	
	public void acceptDeleteAlert() {
		waitForAlertPresence(driver);
		acceptAlert(driver);
	}
	
	public void clickOnNextPageButton() {
		scrollToBottomPage(driver);
		waitForClickable(driver, BTN_NEXT_PAGE);
		clickToElementByJS(driver, BTN_NEXT_PAGE);
	}
	
//	==============VERIFY=================
	
	public void verifyIncomeInfoPopupDisplayed() {
		waitForElementVisible(driver, POPUP_INCOME_ITEM_INFO);
		Assert.assertTrue(isElementDisplayed(driver, POPUP_INCOME_ITEM_INFO), "Project information popup is not displayed");
	}
	
	public void verifyDeleteAlertDisplayed() {
		Assert.assertEquals(getAlertText(driver), "Bạn có chắc chắn xóa đề mục này không ?");
	}
	
}
