package pageObject.user;

import org.openqa.selenium.WebDriver;

import pageUI.*;
import pageUI.user.LoginPageUI;
import commons.BasePage;
import commons.UserBasePageUI;
import io.qameta.allure.Step;

public class UserLoginPO extends BasePage {
	private WebDriver driver;
	
	public UserLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to Login Button")
	public void clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
	
	@Step("Click to Register link")
	public UserRegisterPO clickToRegisterLink() {
		waitForElementClickable(driver, LoginPageUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}
	
	@Step("CLick to Remember me checkbox")
	public void checkToRememberMeCheckbox() {
		waitForElementClickable(driver, LoginPageUI.REMEMBER_ME_CHECKBOX);
		checkToDefaultCheckBoxRadio(driver, LoginPageUI.REMEMBER_ME_CHECKBOX);
	}
	
	@Step("Click to Forgot password link")
	public void clickToForgotPasswordLink() {
		waitForElementClickable(driver, LoginPageUI.FORGOT_PASSWORD_LINK);
		clickToElement(driver, LoginPageUI.FORGOT_PASSWORD_LINK);
	}
	
	
}
