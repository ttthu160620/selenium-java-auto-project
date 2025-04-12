package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import commons.BasePage;
import intefaces.GooglePageUIs;

public class GoogleSearchPageObject extends BasePage{
	private WebDriver driver;

	public GoogleSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToSearchTextbox(String searchValue) {
		waitForElementVisible(driver, GooglePageUIs.SEARCH_TEXTBOX);
		sendkeyToElement(driver, GooglePageUIs.SEARCH_TEXTBOX, searchValue);
	}
	
//	public void enterToSearchTextbox(Keys control) {
//		waitForElementVisible(driver, GooglePageUIs.SEARCH_TEXTBOX);
//		senkeyToElement(driver, GooglePageUIs.SEARCH_TEXTBOX, control);
//	}
	
	public void pressEnter() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.ENTER).perform();
	}
	
	public List<String> getSearchResultTitle(){
		isLoadPageSuccess(driver);
		ArrayList<String> listResultTitleText = new ArrayList<String>();
		List<WebElement> list = getListWebElements(driver, GooglePageUIs.SEARCH_TITLE);
		for(WebElement title : list) {
			listResultTitleText.add(title.getText());
			System.out.println("Title:" + title.getText());
		}
		return listResultTitleText;
	}
	
	public List<String> getSearchResultVideo(){
		isLoadPageSuccess(driver);
		ArrayList<String> listResultTitleVideo = new ArrayList<String>();
		List<WebElement> list = getListWebElements(driver, GooglePageUIs.SEARCH_VIDEO);
		for(WebElement title : list) {
			listResultTitleVideo.add(title.getText());
			System.out.println("Title Video:" + title.getText());
		}
		return listResultTitleVideo;
	}
	
	public int getResult() {
		waitForElementVisible(driver, GooglePageUIs.RESULT);
		return Integer.parseInt(getElementText(driver, GooglePageUIs.RESULT));
	}
	
	public int sum(int n) {
		int sum = (-1) * n/2;
		return sum;
	}
	
	public String data() {
		//1-2+3-4+5-6+7-8
		String data1 = null;
		String data2 = null;
		String data = null;
		for(int i = 1; i <= 100; i++) {
			if(i % 2 == 1) {
				data1 = String.valueOf(+i);
			} else {
				data2 = String.valueOf(-i);
			}
			data += data1 + data2;
		}
		return data;
	}
}
