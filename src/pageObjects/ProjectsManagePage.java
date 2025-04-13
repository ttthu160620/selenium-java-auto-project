package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;

public class ProjectsManagePage extends BasePage{
	private final String POPUP_PROJECT_INFO = "xpath=//div[contains(@class,'ui-dialog-titlebar')]";
	private final String TB_PROJECT_NAME = "xpath=//input[@id='TenDuAn']";
	private final String TB_PROJECT_ADDRESS = "xpath=//input[@id='DiaChi']";
	private final String BTN_SAVE = "xpath=//input[@type='submit']";
	private final String TXT_PROJECT_NAME = "xpath=//table//td[text()='%s']";
	private final String BTN_EDIT = "xpath=//table//td[text()='%s']/..//a[@title='Sửa']"; // edit following to project name
	private final String BTN_DELETE = "xpath=//table//td[text()='%s']/..//a[@title='Xóa']";
	private final String BTN_NEXT_PAGE = "xpath=//a[text()='Sau']";
	
	
	private WebDriver driver;
	public ProjectsManagePage(WebDriver driver) {
		this.driver = driver;
	}
	
//	=============ACTION===================
	
	public void enterProjectName(String projectName) {
		waitForElementVisible(driver, TB_PROJECT_NAME);
		sendkeyToElement(driver, TB_PROJECT_NAME, projectName);
	}
	
	public void enterProjectAddress(String projectAddress) {
		waitForElementVisible(driver, TB_PROJECT_ADDRESS);
		sendkeyToElement(driver, TB_PROJECT_ADDRESS, projectAddress);
	}
	
	public void clickOnSaveButton() {
		waitForClickable(driver, BTN_SAVE);
		clickToElement(driver, BTN_SAVE);
	}
	
	public void clickOnEditButtonOfProjectName(String projectName) {
		for(int i = 1; i <= 5; i++) {
			if(isElementUndisplayed(driver, TXT_PROJECT_NAME, projectName) == false) {
				waitForClickable(driver, BTN_EDIT, projectName);
				clickToElement(driver, BTN_EDIT, projectName);
				return;
			}
			else {
				clickOnNextPageButton();
			}
		}
	}
	
	public void clickOnDeleteButtonOfProjectName(String projectName) {
		for(int i = 1; i <= 5; i++) {
			if(isElementUndisplayed(driver, TXT_PROJECT_NAME, projectName) == false) {
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
	
	public void verifyProjectInfoPopupDisplayed() {
		waitForElementVisible(driver, POPUP_PROJECT_INFO);
		Assert.assertTrue(isElementDisplayed(driver, POPUP_PROJECT_INFO), "Project information popup is not displayed");
	}
	
	public void verifyDeleteAlertDisplayed() {
		Assert.assertEquals(getAlertText(driver), "Bạn có chắc chắn xóa dự án này không ?");
	}
}
