package pageObject.user;

import org.openqa.selenium.WebDriver;

import pageUI.*;
import pageUI.user.HomePageUI;
import pageUI.user.LoginPageUI;
import pageUI.user.RegisterPageUI;
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
	
	@Step("Get welcome text")
	public String getWelcomeTextOnHomePage() {
		waitForElementVisible(driver, HomePageUI.WELCOME_OUR_STORE_TEXT);
		return getElementText(driver, HomePageUI.WELCOME_OUR_STORE_TEXT);
	}
	
	
}
