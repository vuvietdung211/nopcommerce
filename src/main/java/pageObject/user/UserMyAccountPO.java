package pageObject.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.MyAccountPageUI;

public class UserMyAccountPO extends BasePage{

	WebDriver driver;
	
	public UserMyAccountPO() {
		this.driver = driver;
	}

	
	public String getNotificationBarText() {
		waitForElementVisible(driver, MyAccountPageUI.NOTIFICATION_BAR);
		return getElementText(driver, MyAccountPageUI.NOTIFICATION_BAR);
	}
	
	
	

}
