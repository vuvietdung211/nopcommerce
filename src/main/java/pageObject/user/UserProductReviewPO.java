package pageObject.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pageUI.*;
import pageUI.user.CategoriesPageUI;
import pageUI.user.RegisterPageUI;
import commons.BasePage;
import io.qameta.allure.Step;

public class UserProductReviewPO extends BasePage{

	WebDriver driver;

	public UserProductReviewPO(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToReviewTextFields(WebDriver driver, String textValue , String textboxName) {
		waitForAllElementVisible(driver, CategoriesPageUI.TEXT_BOX_BY_NAME, textValue);
	}
	

	
	
	

	
		
	
}
