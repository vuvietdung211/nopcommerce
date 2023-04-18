package pageObject.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pageUI.*;
import pageUI.user.RegisterPageUI;
import commons.BasePage;
import io.qameta.allure.Step;

public class UserRegisterPO extends BasePage{

	WebDriver driver;

	public UserRegisterPO(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("CLick to Register button")
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}
	
	
	@Step("Get success message registration")
	public String getSuccessRegister() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_REGISTER_TEXT);
		return getElementText(driver, RegisterPageUI.SUCCESS_REGISTER_TEXT);
	}
	
	@Step("Click to continue button")
	public void clickToContinueButton() {
		waitForElementClickable(driver, RegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
	}

	public String getExistingEmailErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}


		
	
}
