package testcases.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.DataHelper;
import pageObject.user.PageGeneratorManager;
import pageObject.user.UserHomePO;
import pageObject.user.UserLoginPO;
import pageObject.user.UserRegisterPO;

public class TestCase01_Login extends BaseTest {
	UserHomePO userHome;
	UserLoginPO userLogin;
	UserRegisterPO userRegister;
	String email, password, firstName, lastName;
	WebDriver driver;
	DataHelper data;

	@BeforeClass()
	@Parameters("browser")
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHome = PageGeneratorManager.getUserHomePage(driver);
	}
}
