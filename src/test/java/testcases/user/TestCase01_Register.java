package testcases.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.user.PageGeneratorManager;
import pageObject.user.UserHomePO;
import pageObject.user.UserLoginPO;
import pageObject.user.UserRegisterPO;

public class TestCase01_Register extends BaseTest {
	UserHomePO userHome;
	UserLoginPO userLogin;
	UserRegisterPO userRegister;
	String email, password, firstName, lastName, errorFirstnameMessage, errorLastNameMessage, 
	errorEmailMessage, errorPasswordMessage, wrongEmail, gender, day, month, year, successMessage, 
	invalidPassword, errorLowerPassword, errorConfirmPassword;

	WebDriver driver;

	@BeforeClass()
	@Parameters({"browser", "url"})
	public void beforeClass(String browserName, String url) {
		driver = getBrowserdriver(browserName, url);
		userHome = PageGeneratorManager.getUserHomePage(driver);
		email = "asvsbs@gmail.com";
		password = "123456";
		firstName = "asdsad";
		lastName = "asdas";
		errorFirstnameMessage = "First name is required.";
		errorLastNameMessage = "Last name is required.";
		errorEmailMessage = "Email is required.";
		errorPasswordMessage = "Password is required.";
		errorLowerPassword = "Password must meet the following rules:\n must have at least 6 characters";
		errorConfirmPassword = "The password and confirmation password do not match.";
		wrongEmail = "Wrong email";
		gender = "Male";
		day = "20";
		month = "1";
		year = "1989";
		successMessage = "Your registration completed";
		invalidPassword = "123";
	}
	
	@Test
	public void User_01_Register_Empty() {
		userHome.clickToHeaderLinkByName(driver, "Register");
		
		userRegister = PageGeneratorManager.getUserRegisterPage(driver);
		
		userRegister.clickToButtonByText(driver, "Register");
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "FirstName"), errorFirstnameMessage);
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "LastName"), errorLastNameMessage);
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "Email"), errorEmailMessage);
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "Password"), errorPasswordMessage);
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "ConfirmPassword"), errorPasswordMessage);


	}
	
	@Test
	public void User_02_Register_Wrong_Email() {
		userRegister.inputToTextboxByID(driver, "sajdk", "Email");
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "Email"), wrongEmail);

	}
	
	@Test
	public void User_03_Register_Success() {
		
		userRegister.clickToGenderRadioButtonByID(driver, "gender-male");
		
		userRegister.inputToTextboxByID(driver, firstName, "FirstName");
		
		userRegister.inputToTextboxByID(driver, firstName, "LastName");
		
		userRegister.selectDropDownByName(driver, "DateOfBirthDay" , day);

		userRegister.selectDropDownByName(driver, "DateOfBirthMonth" , month);

		userRegister.selectDropDownByName(driver, "DateOfBirthYear" , year);
		
		userRegister.inputToTextboxByID(driver, email, "Email");

		userRegister.inputToTextboxByID(driver, password, "Password");

		userRegister.inputToTextboxByID(driver, password, "ConfirmPassword");

		userRegister.clickToButtonByText(driver, "Register");

		verifyEquals(userRegister.getSuccessRegister(), successMessage);
	}
	
	@Test
	public void User_04_Register_Existing_Email() {
		userRegister.clickToHeaderLinkByName(driver, "Register");
		
		userRegister.inputToTextboxByID(driver, firstName, "FirstName");
		
		userRegister.inputToTextboxByID(driver, firstName, "LastName");
		
		userRegister.selectDropDownByName(driver, "DateOfBirthDay" , day);

		userRegister.selectDropDownByName(driver, "DateOfBirthMonth" , month);

		userRegister.selectDropDownByName(driver, "DateOfBirthYear" , year);
		
		userRegister.inputToTextboxByID(driver, email, "Email");

		userRegister.inputToTextboxByID(driver, password, "Password");

		userRegister.inputToTextboxByID(driver, password, "ConfirmPassword");

		userRegister.clickToButtonByText(driver, "Register");
		
		verifyEquals(userRegister.getExistingEmailErrorMessage(), "The specified email already exists");
		
	}
	
	@Test
	public void User_05_Register_Invalid_Password() {
		
		userRegister.inputToTextboxByID(driver, invalidPassword, "Password");
		
		userRegister.inputToTextboxByID(driver, "2", "ConfirmPassword");
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "Password"), errorLowerPassword);

	}
	
	@Test
	public void User_06_Register_Confirm_Password_Not_Match() {

		userRegister.clickToButtonByText(driver, "Register");
		
		verifyEquals(userRegister.getErrorMessageByName(driver, "ConfirmPassword"), errorConfirmPassword);

	}
	
	@AfterClass()
	public void afterClass() {
		driver.quit();
	}
	
}
