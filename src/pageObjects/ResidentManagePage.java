package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;

public class ResidentManagePage extends BasePage{
	private final String POPUP_INCOME_ITEM_INFO = "xpath=//div[contains(@class,'ui-dialog-titlebar')]";
	private final String DD_PROJECT_NAME = "xpath=//select[@id='dropDuAn']"; //dropdown
	private final String DD_BUILDING_NAME = "xpath=//select[@id='dropKhoiNha']"; //dropdown
	private final String DD_FLOOR_NAME = "xpath=//select[@id='dropTang']"; //dropdown
	private final String DD_APPARTMENT_NAME = "xpath=//select[@id='dropCanHo']"; //dropdown
	private final String DD_RELATIONSHIP = "xpath=//select[@id='dropQuanHeGiaDinh']"; //dropdown
	private final String TB_RESIDENT_FAMILY = "xpath=//input[@id='HoTenDem']";
	private final String TB_RESIDENT_NAME = "xpath=//input[@id='TenCuDan']";
	private final String BTN_SAVE = "xpath=//input[@type='submit']";
	private final String BTN_EDIT = "xpath=//table//tr[%s]//a[@title='Sửa']";
	private final String BTN_DELETE = "xpath=//table//tr[%s]//a[@title='Xóa']";
	
	
	private WebDriver driver;
	public ResidentManagePage(WebDriver driver) {
		this.driver = driver;
	}
	
//	=============ACTION===================
	
	public void selectProjectOption(String project) {
		selectItemInDefaultDropdown(driver, DD_PROJECT_NAME, project);
	}
	
	public void selectBuildingOption(String building) {
		selectItemInDefaultDropdown(driver, DD_BUILDING_NAME, building);
	}
	
	public void selectFloorOption(String floor) {
		selectItemInDefaultDropdown(driver, DD_FLOOR_NAME, floor);
	}
	
	public void selectAppartmentOption(String appartment) {
		selectItemInDefaultDropdown(driver, DD_APPARTMENT_NAME, appartment);
	}
	
	public void selectRelationshipOption(String relationship) {
		selectItemInDefaultDropdown(driver, DD_RELATIONSHIP, relationship);
	}
	
	public void enterResidentName(String residentName) {
		waitForElementVisible(driver, TB_RESIDENT_NAME);
		sendkeyToElement(driver, TB_RESIDENT_NAME, residentName);
	}
	
	public void enterResidentFamily(String residentFamily) {
		waitForElementVisible(driver, TB_RESIDENT_FAMILY);
		sendkeyToElement(driver, TB_RESIDENT_FAMILY, residentFamily);
	}
	
	public void clickOnSaveButton() {
		scrollToElementOnDown(driver, BTN_SAVE);
		waitForClickable(driver, BTN_SAVE);
		clickToElement(driver, BTN_SAVE);
	}
	
	public void clickOnEditButtonOfResident(String rowIndex) {
		waitForClickable(driver, BTN_EDIT, rowIndex);
		clickToElement(driver, BTN_EDIT, rowIndex);
	}
	
	public void clickOnDeleteButtonOfResident(String rowIndex) {
		waitForClickable(driver, BTN_DELETE, rowIndex);
		clickToElement(driver, BTN_DELETE, rowIndex);
	}
	
	public void acceptDeleteAlert() {
		waitForAlertPresence(driver);
		acceptAlert(driver);
	}
	
//	==============VERIFY=================
	
	public void verifyResidentInfoPopupDisplayed() {
		waitForElementVisible(driver, POPUP_INCOME_ITEM_INFO);
		Assert.assertTrue(isElementDisplayed(driver, POPUP_INCOME_ITEM_INFO), "Resident information popup is not displayed");
	}
	
	public void verifyDeleteAlertDisplayed() {
		Assert.assertEquals(getAlertText(driver), "Bạn có chắc chắn xóa cư dân này không ?");
	}
}
