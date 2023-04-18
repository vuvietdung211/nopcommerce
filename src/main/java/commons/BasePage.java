package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import commons.UserBasePageUI;

public class BasePage {
	protected void sleepInSecond(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getCurrentUrl(WebDriver driver, String url) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwradTopage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	// switch to alert
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void aceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String text) {
		waitForAlertPresence(driver).sendKeys(text);
	}

	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (window.equals(windowID)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}

	public void switchToWindowByTitleID(WebDriver driver, String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			sleepInSecond(1);
			String actualTitleID = driver.getTitle().trim();
			if (actualTitleID.equals(expectedTitle)) {
				break;
			}
		}

	}

	public void closeAllWindowByPageWithoutParent(WebDriver driver, String parentPageID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentPageID)) {
				driver.switchTo().window(window);
				driver.close();
			}
		}
		driver.switchTo().window(parentPageID);
		sleepInSecond(1);
	}

	public String getDynamicXpath(String locator, String... values) {
		if (locator.startsWith("xpath=") || locator.startsWith("XPATH=") || locator.startsWith("Xpath=")
				|| locator.startsWith("XPath=")) {
			locator = String.format(locator, (Object[]) values);
		}
		return locator;
	}

	private By getByLocator(String locator) {
		By by = null;
		if (locator.startsWith("id=") || locator.startsWith("ID=") || locator.startsWith("Id=")) {
			by = By.id(locator.substring(3));
		} else if (locator.startsWith("class=") || locator.startsWith("CLASS=") || locator.startsWith("Class=")) {
			by = By.className(locator.substring(6));
		} else if (locator.startsWith("name=") || locator.startsWith("NAME=") || locator.startsWith("Name=")) {
			by = By.name(locator.substring(5));
		} else if (locator.startsWith("xpath=") || locator.startsWith("XPATH=") || locator.startsWith("Xpath=")
				|| locator.startsWith("XPath=")) {
			by = By.xpath(locator.substring(6));
		} else if (locator.startsWith("css=") || locator.startsWith("CSS=") || locator.startsWith("Css=")) {
			by = By.cssSelector(locator.substring(4));
		} else {
			throw new RuntimeException("Locator type is not supported");
		}
		return by;
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	private WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	private WebElement getWebElement(WebDriver driver, String locator, String... dynamicValues) {
		return driver.findElement(getByLocator(getDynamicXpath(locator, dynamicValues)));
	}

	private List<WebElement> getListWebElements(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locator, dynamicValues)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String textValue) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendkeyToElement(WebDriver driver, String locator, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locator, dynamicValues)), key).perform();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String textItem) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByValue(textItem);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String textItem, String... dynamicValue) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locator, dynamicValue)));
		select.selectByValue(textItem);
	}

	public String getFirstSelectedInDefaultDropdown(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public String getFirstSelectedInDefaultDropdown(WebDriver driver, String locator, String... dynamicValue) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locator, dynamicValue)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childItemXpath,
			String expectedItem) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, 15);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemXpath)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getElementAtribute(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementAtribute(WebDriver driver, String locator, String attributeName, String... dynamicValue) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValue)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText().trim();
	}

	public String getElementText(WebDriver driver, String locator, String... dynamicValue) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValue)).getText().trim();
	}

	public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}

	public String getElementCssValue(WebDriver driver, String locator, String propertyName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String locator) {
		return getListWebElements(driver, locator).size();
	}

	public int getElementSize(WebDriver driver, String locator, String... dynamicValues) {
		return getListWebElements(driver, getDynamicXpath(locator, dynamicValues)).size();
	}

	public void checkToDefaultCheckBoxRadio(WebDriver driver, String locator) {
		WebElement checkBox = getWebElement(driver, locator);
		if (!checkBox.isSelected()) {
			checkBox.click();
		}
	}

	public void checkToDefaultCheckBoxRadio(WebDriver driver, String locator, String... dynamicValue) {
		WebElement checkBox = getWebElement(driver, getDynamicXpath(locator, dynamicValue));
		if (!checkBox.isSelected()) {
			checkBox.click();
		}
	}

	public void unCheckToDefaultCheckBox(WebDriver driver, String locator) {
		WebElement checkBox = getWebElement(driver, locator);
		if (checkBox.isSelected()) {
			checkBox.click();
		}
	}

	public void unCheckToDefaultCheckBox(WebDriver driver, String locator, String... dynamicValue) {
		WebElement checkBox = getWebElement(driver, getDynamicXpath(locator, dynamicValue));
		if (checkBox.isSelected()) {
			checkBox.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			// Displayed: Visible on UI + in DOM
			// Undisplayed: Invisible on UI + in DOM
			return getWebElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			// Undisplayed: Invisible on UI + Not in DOM
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicValue) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValue)).isDisplayed();
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListWebElements(driver, locator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String locator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locator, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locator, dynamicValues))).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void highlightElement(WebDriver driver, String locator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void clickToElementByJS(WebDriver driver, String locator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();",
				getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locator));
	}

	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		WebDriverWait explicit = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {

				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicit.until(jQueryLoad);
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}

	public void waitForAllElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
	}

	public void waitForAllElementVisible(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}

	public void waitForAllElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locator)));
	}

	public void waitForAllElementInvisible(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(getListWebElements(driver, getDynamicXpath(locator, dynamicValues))));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait
				.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}

	// common nopcommerce
	@Step("Input to Textbox by ID")
	public void inputToTextboxByID(WebDriver driver, String valueTextbox, String textboxID) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, valueTextbox, textboxID);
	}

	@Step("Select Dropdown by name")
	public void selectDropDownByName(WebDriver driver, String dropdownName, String itemText) {
		selectItemInDefaultDropdown(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemText, dropdownName);
	}

	@Step("Check radio button by ID")
	public void clickToGenderRadioButtonByID(WebDriver driver, String valueRadioButtonID) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_ID, valueRadioButtonID);
		clickToElement(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_ID, valueRadioButtonID);
	}

	@Step("Click to button by text")
	public void clickToButtonByText(WebDriver driver, String valueButtonText) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, valueButtonText);
		clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, valueButtonText);
	}

	@Step("Get error message by name")
	public String getErrorMessageByName(WebDriver driver, String valueErrornameText) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_ERROR_MESSAGE_BY_NAME_ID, valueErrornameText);
		return getElementText(driver, UserBasePageUI.DYNAMIC_ERROR_MESSAGE_BY_NAME_ID, valueErrornameText);
	}
	
	@Step("Click to link by header name")
	public void clickToHeaderLinkByName(WebDriver driver,String headerName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, headerName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, headerName);
	}

}
