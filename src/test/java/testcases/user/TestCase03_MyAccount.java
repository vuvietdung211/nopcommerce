package testcases.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.user.PageGeneratorManager;
import pageObject.user.UserHomePO;
import pageObject.user.UserLoginPO;
import pageObject.user.UserMyAccountPO;
import pageObject.user.UserRegisterPO;

public class TestCase03_MyAccount extends BaseTest{
	WebDriver driver;
	UserHomePO userHome;
	UserRegisterPO userRegister;
	UserLoginPO userLogin;
	UserMyAccountPO userMyAccount;
	String registeredEmail, registeredPassword, firstName, lastName, errorFirstnameMessage, errorLastNameMessage, 
	errorEmailMessage, errorPasswordMessage, wrongEmail, gender, day, month, year, successMessage, 
	invalidPassword, errorLowerPassword, errorConfirmPassword, invalidEmail, errorWrongEmail
	, notRegisteredEmail, errorNotRegisteredEmail, errorCredentailIncorrect, welcomeToOurStoreText
	, updateGender, updateFirstName, updateLastName, updateDate, updateMonth, updateYear, updateEmail
	, updateCompany ;
	
	
	@BeforeClass
	@Parameters({"browser", "url"})
	public void beforeClass(String browser, String url) {
		driver = getBrowserdriver(browser, url);
		userHome = PageGeneratorManager.getUserHomePage(driver);
		registeredEmail = "asvsbs@gmail.com";
		notRegisteredEmail = "takumi121@gmail.com";
		registeredPassword = "123456";
		firstName = "asdsad";
		lastName = "asdas";
		errorFirstnameMessage = "First name is required.";
		errorLastNameMessage = "Last name is required.";
		errorEmailMessage = "Email is required.";
		errorPasswordMessage = "Password is required.";
		errorLowerPassword = "Password must meet the following rules:\n must have at least 6 characters";
		errorConfirmPassword = "The password and confirmation password do not match.";
		errorWrongEmail = "Wrong email";
		errorNotRegisteredEmail = "Login was unsuccessful. Please correct the errors and try again.\n No customer account found";
		errorCredentailIncorrect = "Login was unsuccessful. Please correct the errors and try again.\n The credentials provided are incorrect";
		wrongEmail = "Wrong email";
		gender = "Male";
		day = "20";
		month = "1";
		year = "1989";
		successMessage = "Your registration completed";
		invalidEmail = "sadsadsad";
		invalidPassword = "123";
		welcomeToOurStoreText = "Welcome to our store";
		
		// Update customer info
		updateGender = "gender-female";
		updateFirstName = "Automation";
		updateLastName = "FC";
		updateDate = "01";
		updateMonth = "01";
		updateYear = "1996";
		updateEmail = "automationfc.vn@gmail.com";
		updateCompany = "Automation FC";		
		
		// Register new account
		userHome.clickToHeaderLinkByName(driver, "Register");
		
		userRegister = PageGeneratorManager.getUserRegisterPage(driver);
		
		userRegister.clickToGenderRadioButtonByID(driver, "gender-male");
		
		userRegister.inputToTextboxByID(driver, firstName, "FirstName");
		
		userRegister.inputToTextboxByID(driver, firstName, "LastName");
		
		userRegister.selectDropDownByName(driver, "DateOfBirthDay" , day);

		userRegister.selectDropDownByName(driver, "DateOfBirthMonth" , month);

		userRegister.selectDropDownByName(driver, "DateOfBirthYear" , year);
		
		userRegister.inputToTextboxByID(driver, registeredEmail, "Email");

		userRegister.inputToTextboxByID(driver, registeredPassword, "Password");

		userRegister.inputToTextboxByID(driver, registeredPassword, "ConfirmPassword");

		userRegister.clickToButtonByText(driver, "Register");

		verifyEquals(userRegister.getSuccessRegister(), successMessage);
		
		userRegister.clickToHeaderLinkByName(driver, "Log in");
		
		userLogin = PageGeneratorManager.getUserLoginPage(driver);

		userLogin.inputToTextboxByID(driver, registeredEmail, url);
		
		userLogin.inputToTextboxByID(driver, registeredPassword, "Password");

		userLogin.clickToButtonByText(driver, "Log in");
	}
	
	@Test
	public void User_01_My_Account_Update_Customer() {
		
		userMyAccount = PageGeneratorManager.getUserMyAccountPage(driver);
		
		userMyAccount.clickToGenderRadioButtonByID(driver, "gender-female");
		
		userMyAccount.inputToTextboxByID(driver, updateFirstName, "FirstName");
		
		userMyAccount.inputToTextboxByID(driver, updateFirstName, "LastName");
		
		userMyAccount.selectDropDownByName(driver, "DateOfBirthDay" , updateDate);

		userMyAccount.selectDropDownByName(driver, "DateOfBirthMonth" , updateMonth);

		userMyAccount.selectDropDownByName(driver, "DateOfBirthYear" , updateYear);
		
		userMyAccount.inputToTextboxByID(driver, "Company", updateCompany);
		
		userMyAccount.clickToButtonByText(driver, "Save");
		
		verifyTrue(userMyAccount.isGenderRadioButtonChecked(driver, "gender-female"));
		
		verifyEquals(userMyAccount.getTextValueOfTextbox(driver, "FirstName"), updateFirstName);
		
		verifyEquals(userMyAccount.getTextValueOfTextbox(driver, "LastName"), updateLastName);

		verifyEquals(userMyAccount.getTextValueOfTextbox(driver, "DateOfBirthDay"), updateDate);

		verifyEquals(userMyAccount.getTextValueOfTextbox(driver, "DateOfBirthMonth"), updateMonth);

		verifyEquals(userMyAccount.getTextValueOfTextbox(driver, "DateOfBirthYear"), updateYear);
		
		verifyEquals(userMyAccount.getTextValueOfTextbox(driver, "Company"), updateCompany);

	}
	
	@Test
	public void User_02_Login_Invalid_Email() {
		
			
	}
	
	@Test
	public void User_03_Login_Invalid_Email() {
		
				
	}
	
	@Test
	public void User_04_Login_Blank_Password() {
		
				
	}
	
	@Test
	public void User_05_Login_Invalid_Password() {
		
		userLogin.inputToTextboxByID(driver, registeredEmail, "Email");
		
		userLogin.inputToTextboxByID(driver, invalidPassword, "Password");
		
		userLogin.clickToButtonByText(driver, "Log in");
		
		verifyEquals(userLogin.getErrorMessageValidation(driver), errorCredentailIncorrect);
		
	}
	
	@Test
	public void User_06_Login_Not_Registered_Email() {
		
		userLogin.inputToTextboxByID(driver, registeredEmail, "Email");
		
		userLogin.inputToTextboxByID(driver, registeredPassword, "Password");
		
		userLogin.clickToButtonByText(driver, "Log in");
		
		userHome = PageGeneratorManager.getUserHomePage(driver);
		
		verifyEquals(userHome.getWelcomeTextOnHomePage(), welcomeToOurStoreText);
		
	}
}
