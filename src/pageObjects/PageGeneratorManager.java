package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static GoogleSearchPageObject getGooglePage(WebDriver driver) {
		return new GoogleSearchPageObject(driver);
	}
	
	public static LoginPage getLoginPage(WebDriver driver) {
		return new LoginPage(driver);
	}
	
	public static ManagePage getManagePage(WebDriver driver) {
		return new ManagePage(driver);
	}
	
	public static ProjectsManagePage getProjectsManagePage(WebDriver driver) {
		return new ProjectsManagePage(driver);
	}
}
