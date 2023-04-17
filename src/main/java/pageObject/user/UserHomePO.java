package pageObject.user;

import org.openqa.selenium.WebDriver;

import pageUI.*;
import pageUI.user.LoginPageUI;
import commons.BasePage;
import io.qameta.allure.Step;

public class UserHomePO extends BasePage{
	WebDriver driver;

	public UserHomePO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to Login link")
	public UserLoginPO clickToLoginLink() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_LINK);
		clickToElement(driver, LoginPageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}
	
	
	
	
}
