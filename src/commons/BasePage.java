package commons;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver){
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies ) {
		for(Cookie cookie : cookies) {
			  driver.manage().addCookie(cookie);
		  }
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);
		alert.accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);
		alert.dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void senkeyAlert(WebDriver driver, String textValue) {
		Alert alert = waitForAlertPresence(driver);
		alert.sendKeys(textValue);
	}
	
	public void switchWindowByID(WebDriver driver, String parentWindowID) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String id : allWindows) {
			if(!id.equals(parentWindowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	public void swichWindowByTitle(WebDriver driver, String parentWindowID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String actual = driver.getTitle();
			if(runWindows.equals(actual)) {
				break;
			}
		}
	}
	
	public boolean closeAllWindowsWithoutParentWindow(WebDriver driver, String parentID) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for(String id : allWindowsID) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if(driver.getWindowHandles().size() == 1) {
			return true;
		}
		else {
			return true;
		}
	}
	
	private By getByLocator(String locatorType) {
		By by = null;
		if(locatorType.startsWith("id=")) {
			 by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not defined");
		}
		return by;
	}
	
	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	public List<WebElement> getListWebElements(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	
	public List<WebElement> getListWebElements(WebDriver driver, String locatorType, String... dynamicLocator) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicLocator)));
	}
	
	public int getListElementSize(WebDriver driver, String locatorType) {
		List<WebElement> listElement = getListWebElements( driver, locatorType);
		return listElement.size();
	}
	
	public int getListElementSize(WebDriver driver, String locatorType, String dynamicLocator) {
		List<WebElement> listElement = getListWebElements( driver, locatorType, dynamicLocator);
		return listElement.size();
	}
	
	private String getDynamicXpath(String locatorType, String... values) {
		if(locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") ||locatorType.startsWith("XPATH=")) {
			locatorType = String.format(locatorType, (Object[]) values);
		}
		return locatorType;
	}
	
	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}
	
	public void clickToElement(WebDriver driver, String locatorType ,String... dynamicLocator) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator)).click();
	}
	
	public void rightClickOnElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, locatorType)).perform();
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicLocator) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	
	public String getElementText(WebDriver driver, String locatorType, String... dynamicLocator) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator)).getText();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicLocator) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator)));
		select.selectByVisibleText(textItem);
	}
	
	public String getFirstItemIsSelectedDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getFirstItemIsSelectedDropdown(WebDriver driver, String locatorType, String... dynamicLocator) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator)));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getFirstSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	public Boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdownList(WebDriver driver, String parentXpath, String childXpath, String expectedTexItem) {
		clickToElement(driver, parentXpath);
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		
		List<WebElement> listItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		for(WebElement item : listItem) {
			String actualText = item.getText();
			System.out.println("Actual Text = " + actualText);		
			
			if(actualText.equals(expectedTexItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	
	public String getElementAttributeValue(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	
	public String getElementAttributeValue(WebDriver driver, String locatorType, String attributeName, String... dynamicLocator) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator)).getAttribute(attributeName);
	}
	
	public void sleepInSecond (long second) {
		try {
			Thread.sleep( second * 1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public int getRandomNumber() {
		Random ran = new Random();
		return ran.nextInt(999);
	}
	
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElements(driver, locatorType).size();
	}
	
	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicLocator) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator));
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicLocator) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator));
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}
	
	public void overrideImplicitTimeOut(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideImplicitTimeOut(driver, sortTimeout);
		List<WebElement> listElement= getListWebElements(driver, locatorType);
		overrideImplicitTimeOut(driver, longTimeout);
		if(listElement.size() == 0) {
			System.out.println("Element not in DOM");
			return true;
		}else if(listElement.size() > 0 && !listElement.get(0).isDisplayed()) {
			System.out.println("Element in DOM but undisplay");
			return true;
		}else {
			System.out.println("Element in DOM and display");
			return false;
		}
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicLocator) {
		overrideImplicitTimeOut(driver, sortTimeout);
		List<WebElement> listElement= getListWebElements(driver, getDynamicXpath(locatorType, dynamicLocator));
		overrideImplicitTimeOut(driver, longTimeout);
		if(listElement.size()==0) {
			System.out.println("Element not in DOM");
			return true;
		}else if(listElement.size()>0 && !listElement.get(0).isDisplayed()) {
			System.out.println("Element in DOM but undisplay");
			return true;
		}else {
			System.out.println("Element in DOM and display");
			return false;
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicLocator) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator)).isDisplayed();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicLocator) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator)).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicLocator) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator)).isDisplayed();
	}
	
	public void switchToFrame(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator))).perform();
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void hightlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}
	
	public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicLocator)));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void scrollToElementOnDown(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, By byLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(byLocator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
		if (status) {
			return true;
		}
		return false;
	}
	
	public boolean isJQueryLoadSuccess(WebDriver driver) {
		WebDriverWait explicitWait =new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad= new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return (Boolean) jsExecutor.executeScript("return (window.jQuery!=null) && (jQuery.active===0);");
			}
			
		};
		return explicitWait.until(jQueryLoad);
	}
	public boolean isjQueryAndPageLoadSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad= new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				try {
					return  ((Long) jsExecutor.executeScript("return jQuery.active;")==0);
				}catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> PageLoadSuccess= new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return  (Boolean) jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(PageLoadSuccess);
	}
	
	public boolean isLoadPageSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				// TODO Auto-generated method stub
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active == 0)");
			}	
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				// TODO Auto-generated method stub
				return (Boolean) jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}	
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicLocator))));
	}
	
	public void waitForAllElementsVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	public void waitForAllElementsVisible(WebDriver driver, String locatorType, String... dynamicLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicLocator))));
	}
	
	public void waitForElementInVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, sortTimeout);
		overrideImplicitTimeOut(driver, sortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeOut(driver, longTimeout);
	}
	
	public void waitForElementInVisible(WebDriver driver, String locatorType, String... dynamicLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, sortTimeout);
		overrideImplicitTimeOut(driver, sortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicLocator))));
		overrideImplicitTimeOut(driver, longTimeout);
	}
	
	public void waitForAllElementsInVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locatorType)));
	}
	
	public void waitForAllElementsInVisible(WebDriver driver, String locatorType, String... dynamicLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, getDynamicXpath(locatorType, dynamicLocator))));
	}
	
	public void waitForClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	public void waitForClickable(WebDriver driver, String locatorType, String... dynamicValues ) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	private long longTimeout = 15;
	private long sortTimeout = 3;
}
