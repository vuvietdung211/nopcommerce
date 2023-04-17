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
	
	
	@Step("Verify success message registration")
	public void verifySuccessRegister(String successMessage) {
		String actualMeassage = getElementText(driver, RegisterPageUI.SUCCESS_REGISTER_TEXT);
		Assert.assertEquals(actualMeassage, successMessage);
	}
	
	@Step("Click to continue button")
	public void clickToContinueButton() {
		waitForElementClickable(driver, RegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
	}


		
	}
}
