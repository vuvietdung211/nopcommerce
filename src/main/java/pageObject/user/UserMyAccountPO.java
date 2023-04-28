package pageObject.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.MyAccountPageUI;

public class UserMyAccountPO extends BasePage{

	WebDriver driver;
	
	public UserMyAccountPO(WebDriver driver) {
		this.driver = driver;
	}

	
	public String getNotificationBarText() {
		waitForElementVisible(driver, MyAccountPageUI.NOTIFICATION_BAR);
		return getElementText(driver, MyAccountPageUI.NOTIFICATION_BAR);
	}
	
	public void clickToMyAccountTabByText(String tabName) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_TAB_BY_TEXT, tabName);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_TAB_BY_TEXT, tabName);
	}
	
	public String getAdrressAddedInfoByClass(String className) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_ADRESS_INFOR_BY_CLASS, className);
		return getElementText(driver, MyAccountPageUI.DYNAMIC_ADRESS_INFOR_BY_CLASS, className);
	}

}
