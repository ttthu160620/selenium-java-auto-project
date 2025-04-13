package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static LoginPage getLoginPage(WebDriver driver) {
		return new LoginPage(driver);
	}
	
	public static ManagePage getManagePage(WebDriver driver) {
		return new ManagePage(driver);
	}
	
	public static ProjectsManagePage getProjectsManagePage(WebDriver driver) {
		return new ProjectsManagePage(driver);
	}
	
	public static IncomeItemPage getIncomeItemPage(WebDriver driver) {
		return new IncomeItemPage(driver);
	}
	
	public static BuildingBlockManagePage getBuildingBlockPage(WebDriver driver) {
		return new BuildingBlockManagePage(driver);
	}
	
	public static ResidentManagePage getResidentManagePage(WebDriver driver) {
		return new ResidentManagePage(driver);
	}
}
