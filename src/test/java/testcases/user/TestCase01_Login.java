package testcases.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
	String email, password, firstName, lastName, errorFirstname, errorLastName, errorEmail, errorPassword, wrongEmail;
	WebDriver driver;

	@BeforeClass()
	@Parameters("browser")
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHome = PageGeneratorManager.getUserHomePage(driver);
		email = "asvsbs@gmail.com";
		password = "123456";
		firstName = "asdsad";
		lastName = "asdas";
		errorFirstname = "First name is required.";
		errorLastName = "Last name is required.";
		errorEmail = "Email is required.";
		errorPassword = "Password is required.";
		 = "Wrong email";
	}
	
	@Test
	public void User_01_Register_Empty() {
		userRegister.clickToButtonByText(driver, "Register");
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "FirstName"), errorFirstname);
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "LastName"), errorLastName);
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "Email"), errorEmail);
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "Password"), errorPassword);
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "ConfirmPassword"), errorPassword);




	}
	
	public void User_02_Register_Wrong_Email() {
		userRegister.inputToTextboxByID(driver, "sajdk", "email");
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "Email"), wrongEmail);

		



	}
}
