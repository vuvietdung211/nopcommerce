package pageObject.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUI.user.CategoriesPageUI;

public class UserCategoriesPO extends BasePage{

	WebDriver driver;

	public UserCategoriesPO(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click to product by product name")
	public void clickToProductByProductName(String textValue) {
		waitForElementVisible(driver, CategoriesPageUI.REVIEW_TEXT_BOX, textValue);
		clickToElement(driver, CategoriesPageUI.REVIEW_TEXT_BOX, textValue);
	}
	
	
	


	
		
	
}
