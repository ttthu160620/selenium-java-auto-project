package testcase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AccountInformation;
import commons.BaseTest;
import pageObjects.LoginPage;
import pageObjects.ManagePage;
import pageObjects.PageGeneratorManager;

public class TestLogin extends BaseTest{
	WebDriver driver;
	LoginPage loginPage;
	ManagePage managePage;
	String invalidUsername = "admin";
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		managePage = PageGeneratorManager.getManagePage(driver);
		
	}
	
//	Verify all element in the Login page is displayed correctly
	@Test
	public void Login_TC_01() {
		log.info("Verify Login title is displayed: 'Đăng nhập'");
		loginPage.verifyLoginTitleIsDisplayed();
		
		log.info("Verify username textbox is displayed");
		loginPage.verifyUsernameTextboxDisplayed();
		
		log.info("Verify password textbox is displayed");
		loginPage.verifyPasswordTextboxDisplayed();
		
		log.info("Verify user icon is displayed next to username textbox");
		loginPage.verifyUsernameIconDisplayed();
		
		log.info("Verify password icon is displayed next to password textbox");
		loginPage.verifyPasswordIconDisplayed();
		
		log.info("Verify 'Lưu mật khẩu' is displayed");
		loginPage.verifySavePasswordLabelDisplayed();
		
		log.info("Verify 'Quên mật khẩu' is displayed");
		loginPage.verifyForgotPasswordLabelDisplayed();
		
		log.info("Verify 'Đăng nhập' button is displayed");
		loginPage.verifyLoginButtonDisplayed();
	}
	
//	Verify user cannot login with the invalid username
	@Test
	public void Login_TC_02() {
		log.info("Enter to username textbox: " + invalidUsername);
		loginPage.enterUsername(invalidUsername);
		
		sleepInSecond(1);
		
		log.info("Enter to password textbox: " + AccountInformation.PASSWORD);
		loginPage.enterPassword(AccountInformation.PASSWORD);
		
		log.info("Click on 'Đăng nhập' button");
		loginPage.clickToLoginButton();
		
		log.info("Verify the error message is displayed: " );
		loginPage.verifyInccorectAccountMsgDisplayed();
	}
	
//	Verify user can successfully login with valid username + password
	@Test
	public void Login_TC_03() {
		log.info("Enter to username textbox: " + AccountInformation.USERNAME);
		loginPage.enterUsername(AccountInformation.USERNAME);
		
		sleepInSecond(1);
		
		log.info("Enter to password textbox: " + AccountInformation.PASSWORD);
		loginPage.enterPassword(AccountInformation.PASSWORD);
		
		log.info("Click on 'Đăng nhập' button");
		loginPage.clickToLoginButton();
		
		log.info("Verify login successfully - Verify admin page is displayed");
		managePage.verifyLogoIsDisplayed();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
