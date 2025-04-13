package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;

public class BuildingBlockManagePage extends BasePage{
	private final String POPUP_INCOME_ITEM_INFO = "xpath=//div[contains(@class,'ui-dialog-titlebar')]";
	private final String DD_PROJECT_NAME = "xpath=//select[@id='dropDuAn']"; //dropdown
	private final String TB_BUILDING_NAME = "xpath=//input[@id='TenKhoiNha']";
	private final String BTN_SAVE = "xpath=//input[@type='submit']";
	private final String TXT_BUILDING_NAME = "xpath=//table//td[text()='%s']";
	private final String BTN_EDIT = "xpath=//table//td[text()='%s']/..//a[@title='Sửa']"; // edit following to project name
	private final String BTN_DELETE = "xpath=//table//td[text()='%s']/..//a[@title='Xóa']";
	private final String BTN_NEXT_PAGE = "xpath=//a[text()='Sau']";
	private final String TXT_LAST_PAGE_INDEX = "xpath=//li[@class='paginate_button '][last()]//a";
	
	
	private WebDriver driver;
	public BuildingBlockManagePage(WebDriver driver) {
		this.driver = driver;
	}
	
//	=============ACTION===================
	
	public void selectProjectOption(String project) {
		selectItemInDefaultDropdown(driver, DD_PROJECT_NAME, project);
	}
	
	public void enterBuildingBlockNname(String buildingName) {
		waitForElementVisible(driver, TB_BUILDING_NAME);
		sendkeyToElement(driver, TB_BUILDING_NAME, buildingName);
	}
	
	public void clickOnSaveButton() {
		waitForClickable(driver, BTN_SAVE);
		clickToElement(driver, BTN_SAVE);
	}
	
	public int getTotalPagination() {
		return Integer.valueOf(getElementText(driver, TXT_LAST_PAGE_INDEX));
	}
	
	public void clickOnEditButtonOfBuilding(String building) {
		int totalPage = getTotalPagination();
				for(int i = 1; i <= totalPage; i++) {
			if(isElementUndisplayed(driver, TXT_BUILDING_NAME, building) == false) {
				waitForClickable(driver, BTN_EDIT, building);
				clickToElement(driver, BTN_EDIT, building);
				return;
			}
			else {
				clickOnNextPageButton();
			}
		}
	}
	
	public void clickOnDeleteButtonOfBuilding(String building) {
		int totalPage = getTotalPagination();
		for(int i = 1; i <= totalPage; i++) {
			if(isElementUndisplayed(driver, TXT_BUILDING_NAME, building) == false) {
				waitForClickable(driver, BTN_DELETE, building);
				clickToElement(driver, BTN_DELETE, building);
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
	
	public void verifyBuildingInfoPopupDisplayed() {
		waitForElementVisible(driver, POPUP_INCOME_ITEM_INFO);
		Assert.assertTrue(isElementDisplayed(driver, POPUP_INCOME_ITEM_INFO), "Project information popup is not displayed");
	}
	
	public void verifyDeleteAlertDisplayed() {
		Assert.assertEquals(getAlertText(driver), "Bạn có chắc chắn xóa khối nhà này không ?");
	}
	
}
