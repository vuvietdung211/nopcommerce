package testcases.user;

import org.openqa.selenium.WebDriver;
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
	String email, password, firstName, lastName, errorFirstname, errorLastName, errorEmail, errorPassword, wrongEmail, gender, day, month, year, successMessage;

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
		wrongEmail = "Wrong email";
		gender = "Male";
		day = "20";
		month = "1";
		year = "1989";
		successMessage = "Your registration completed";
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
	
	public void User_03_Register_Success() {
		
		userRegister.clickToGenderRadioButtonByID(driver, gender);
		
		userRegister.inputToTextboxByID(driver, firstName, "FirstName");
		
		userRegister.inputToTextboxByID(driver, firstName, "LastName");
		
		userRegister.selectDropDownByName(driver, "DateOfBirthDay" , day);

		userRegister.selectDropDownByName(driver, "DateOfBirthDay" , month);

		userRegister.selectDropDownByName(driver, "DateOfBirthDay" , year);
		
		userRegister.inputToTextboxByID(driver, email, "Email");

		userRegister.inputToTextboxByID(driver, password, "Password");

		userRegister.inputToTextboxByID(driver, password, "ConfirmPassword");

		userRegister.clickToButtonByText(driver, "Register");

		verifyEquals(userRegister.getSuccessRegister(), successMessage);
	}
	
	public void User_04_Register_Existing_Email() {
		userRegister.clickToHeaderLinkByName(driver, "Register");
		
		userRegister.inputToTextboxByID(driver, firstName, "FirstName");
		
		userRegister.inputToTextboxByID(driver, firstName, "LastName");
		
		userRegister.selectDropDownByName(driver, "DateOfBirthDay" , day);

		userRegister.selectDropDownByName(driver, "DateOfBirthDay" , month);

		userRegister.selectDropDownByName(driver, "DateOfBirthDay" , year);
		
		userRegister.inputToTextboxByID(driver, email, "Email");

		userRegister.inputToTextboxByID(driver, password, "Password");

		userRegister.inputToTextboxByID(driver, password, "ConfirmPassword");

		userRegister.clickToButtonByText(driver, "Register");
		
		verifyEquals(userRegister.getExistingEmailErrorMessage(), "The specified email already exists");
		

	}
	
}
