package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;

public class LoginPage extends BasePage{
	private final String TXT_LOGIN_TITLE = "xpath=//h1[text()='Đăng Nhập']";
	private final String TB_USERNAME = "xpath=//input[@name='Username']";
	private final String TB_PASSWORD = "xpath=//input[@name='Password']";
	private final String BTN_LOGIN = "xpath=//input[@type='submit']";
	private final String TXT_FILL_INFORMATION = "xpath=//h2[text()='Điền thông tin đăng nhập']";
	private final String TXT_INCORRECT_ACCOUNT_MSG = "xpath=//div[text()='Tài khoản không hợp lệ!']";
	private final String LB_SAVE_PASSWORD = "xpath=//label[contains(.,'Lưu mật khẩu')]";
	private final String LB_FORGOT_PASSWORD = "xpath=//label[contains(.,'Quên mật khẩu?')]";
	private final String IC_USERNAME = "xpath=//input[@name='Username']/following-sibling::div/i[contains(@class,'fa-user')]";
	private final String IC_PASSWORD = "xpath=//input[@name='Password']/following-sibling::div/i";
			
	
	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
//	===========ACTION===========
	
	public void enterUsername(String username) {
		waitForElementVisible(driver, TB_USERNAME);
		sendkeyToElement(driver, TB_USERNAME, username);
	}
	
	public void enterPassword(String password) {
		waitForElementVisible(driver, TB_PASSWORD);
		sendkeyToElement(driver, TB_PASSWORD, password);
	}
	
	public void clickToLoginButton() {
		waitForClickable(driver, BTN_LOGIN);
		clickToElement(driver, BTN_LOGIN);
		isjQueryAndPageLoadSuccess(driver);
	}
	
//	===========VERIFY===========
	
	public void verifyLoginTitleIsDisplayed() {
		waitForElementVisible(driver, TXT_LOGIN_TITLE);
		Assert.assertTrue(isElementDisplayed(driver, TXT_LOGIN_TITLE), "'Đăng nhập' is not displayed");
	}
	
	public void verifyFillInformationMsgDisplayed() {
		waitForElementVisible(driver, TXT_FILL_INFORMATION);
		Assert.assertTrue(isElementDisplayed(driver, TXT_FILL_INFORMATION), "'Điền thông tin đăng nhập' is not displayed");
	}
	
	public void verifyInccorectAccountMsgDisplayed() {
		waitForElementVisible(driver, TXT_INCORRECT_ACCOUNT_MSG);
		Assert.assertTrue(isElementDisplayed(driver, TXT_INCORRECT_ACCOUNT_MSG), "'Tài khoản không hợp lệ!' is not displayed");
	}
	
	public void verifySavePasswordLabelDisplayed() {
		waitForElementVisible(driver, LB_SAVE_PASSWORD);
		Assert.assertTrue(isElementDisplayed(driver, LB_SAVE_PASSWORD), "'Lưu mật khẩu' label is not displayed");
	}
	
	public void verifyForgotPasswordLabelDisplayed() {
		waitForElementVisible(driver, LB_FORGOT_PASSWORD);
		Assert.assertTrue(isElementDisplayed(driver, LB_FORGOT_PASSWORD), "'Quên mật khẩu' label is not displayed");
	}

	public void verifyUsernameIconDisplayed() {
		waitForElementVisible(driver, IC_USERNAME);
		Assert.assertTrue(isElementDisplayed(driver, IC_USERNAME), "Username icon is not displayed");
	}
	
	public void verifyPasswordIconDisplayed() {
		waitForElementVisible(driver, IC_PASSWORD);
		Assert.assertTrue(isElementDisplayed(driver, IC_PASSWORD), "Password icon is not displayed");
	}
	
	public void verifyUsernameTextboxDisplayed() {
		waitForElementVisible(driver, TB_USERNAME);
		Assert.assertTrue(isElementDisplayed(driver, TB_USERNAME), "Username textbox is not displayed");
	}
	
	
	public void verifyPasswordTextboxDisplayed() {
		waitForElementVisible(driver, TB_PASSWORD);
		Assert.assertTrue(isElementDisplayed(driver, TB_PASSWORD), "Password textbox is not displayed");
	}
	
	public void verifyLoginButtonDisplayed() {
		waitForElementVisible(driver, BTN_LOGIN);
		Assert.assertTrue(isElementDisplayed(driver, BTN_LOGIN), "TestLogin button is not displayed");
	}
	
//	============ FLOW ==========================
	public void loginPage(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickToLoginButton();
	}
	
}
