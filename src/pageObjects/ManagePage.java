package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;

public class ManagePage extends BasePage{
	private final String TXT_SSPM_LOGO = "xpath=//a[@id='logo1']/span[@class='logotitle']"; // txt: text
	private final String ACCOUNT_INFO = "xpath=//div[@class='right-header']";
	private final String HPL_LEFT_MENU_ITEM = "xpath=//div[@class='content']//a[.='%s']"; // hpl: hyperlink
	private final String BTN_ADD_NEW = "xpath=//a[contains(.,'Thêm mới')]";
	private final String TB_SEARCH = "xpath=//input[@id='searchCH']";
	private final String BTN_SEARCH = "xpath=//a[@id='btnSearch']";
	
	private WebDriver driver;
	public ManagePage(WebDriver driver) {
		this.driver = driver;
	}
	
//	===========ACTION===========
	
	public void rightClickOnAccountInfo() {
		waitForElementVisible(driver, ACCOUNT_INFO);
		rightClickOnElement(driver, ACCOUNT_INFO);
	}
	
	public void clickOnLeftMenuItem(String menu) {
		waitForElementVisible(driver, HPL_LEFT_MENU_ITEM, menu);
		clickToElement(driver, HPL_LEFT_MENU_ITEM, menu);
	}
	
	public void clickOnAddNewButton() {
		waitForClickable(driver, BTN_ADD_NEW);
		clickToElement(driver, BTN_ADD_NEW);
	}
	
	public void enterToSearchTextbox(String searchValue) {
		waitForElementVisible(driver, TB_SEARCH);
		sendkeyToElement(driver, TB_SEARCH, searchValue);
	}
	
	public void clickOnSearchButton() {
		waitForClickable(driver, BTN_SEARCH);
		clickToElement(driver, BTN_SEARCH);
	}
	
//	===========VERIFY===========
	
	public void verifyLogoIsDisplayed() {
		waitForElementVisible(driver, TXT_SSPM_LOGO);
		Assert.assertTrue(isElementDisplayed(driver, TXT_SSPM_LOGO));
	}
	
	
}
