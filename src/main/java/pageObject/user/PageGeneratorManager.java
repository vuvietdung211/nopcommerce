package pageObject.user;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static UserHomePO getUserHomePage(WebDriver driver) {
		return new UserHomePO(driver);
	}
	
	public static UserLoginPO getUserLoginPage(WebDriver driver) {
		return new UserLoginPO(driver);
	}
	
	public static UserRegisterPO getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPO(driver);
	}

	public static UserMyAccountPO getUserMyAccountPage(WebDriver driver) {
		return new UserMyAccountPO(driver);
	}

	public static UserCategoriesPO getUserCategoriesPage(WebDriver driver) {
		return new UserCategoriesPO(driver);
	}
	
	public static UserProductReviewPO getUserProductReview(WebDriver driver) {
		return new UserProductReviewPO(driver);
	}
}
