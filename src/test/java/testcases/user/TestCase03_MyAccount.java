package testcases.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.DataHelper;
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
	, updateCompany, successCustomerInfoNotificationBarText ;
	String addressFirstName, addressLastName, addressEmail, addressCompany, addressCountry,
	addressState, addressCity, address1, address2, addressZipCode, addressPhoneNumber, addressFaxNumber, successAddressesAdded ;
	String newChangedPassword;
	DataHelper data;
	
	@BeforeClass
	@Parameters({"browser", "url"})
	public void beforeClass(String browser, String url) {
		
		data = DataHelper.getData();
		driver = getBrowserdriver(browser, url);
		userHome = PageGeneratorManager.getUserHomePage(driver);
		registeredEmail = "takumi11221@gmail.com";
		notRegisteredEmail = "takumi1221@gmail.com";
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
		month = "2";
		year = "1989";
		successMessage = "Your registration completed";
		invalidEmail = "sadsadsad";
		invalidPassword = "123";
		welcomeToOurStoreText = "Welcome to our store";
		
		// Update customer info
		updateGender = "gender-female";
		updateFirstName = "Automation";
		updateLastName = "Automation";
		updateDate = "11";
		updateMonth = "1";
		updateYear = "1996";
		updateEmail = data.getEmail();
		updateCompany = "Automation FC";	
		successCustomerInfoNotificationBarText = "The customer info has been updated successfully.";
		
		//Address information
		addressFirstName = "Automation";
		addressLastName = "FC";
		addressEmail = data.getEmail();
		addressCompany = "PGG";
		addressCountry = "82";
		addressState = "Thanh Xuan";
		addressCity = "Ha noi";
		address1 = "111/33 Nguyen Trai";
		address2 = "219 Trung Kinh";
		addressZipCode = "00084";
		addressPhoneNumber = "0988766443";
		addressFaxNumber = "133789044";
		successAddressesAdded = "The new address has been added successfully.";
		
		newChangedPassword = "13378902";
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

		userLogin.inputToTextboxByID(driver, registeredEmail, "Email");
		
		userLogin.inputToTextboxByID(driver, registeredPassword, "Password");

		userLogin.clickToButtonByText(driver, "Log in");
	}
	
	@Test
	public void User_01_My_Account_Update_Customer() {
		
		userHome = PageGeneratorManager.getUserHomePage(driver);
				
		userHome.clickToHeaderLinkByName(driver, "My account");
		
		userMyAccount = PageGeneratorManager.getUserMyAccountPage(driver);
		
		userMyAccount.clickToGenderRadioButtonByID(driver, "gender-female");
		
		userMyAccount.inputToTextboxByID(driver, updateFirstName, "FirstName");
		
		userMyAccount.inputToTextboxByID(driver, updateFirstName, "LastName");
		
		userMyAccount.selectDropDownByName(driver, "DateOfBirthDay" , updateDate);

		userMyAccount.selectDropDownByName(driver, "DateOfBirthMonth" , updateMonth);

		userMyAccount.selectDropDownByName(driver, "DateOfBirthYear" , updateYear);
		
		userMyAccount.inputToTextboxByID(driver, updateEmail, "Email");
		
		userMyAccount.inputToTextboxByID(driver, updateCompany, "Company");
		
		userMyAccount.clickToButtonByText(driver, "Save");
		
		verifyEquals(userMyAccount.getNotificationBarText(), successCustomerInfoNotificationBarText);
		
		verifyTrue(userMyAccount.isGenderRadioButtonChecked(driver, "gender-female"));
		
		verifyEquals(userMyAccount.getTextValueOfTextbox(driver, "FirstName"), updateFirstName);
		
		verifyEquals(userMyAccount.getTextValueOfTextbox(driver, "LastName"), updateLastName);

		verifyEquals(userMyAccount.getFirstSelectedInDropDownByName(driver, "DateOfBirthDay"), updateDate);
		
		verifyEquals(userMyAccount.getFirstSelectedInDropDownByName(driver, "DateOfBirthMonth"), updateMonth);
		
		verifyEquals(userMyAccount.getFirstSelectedInDropDownByName(driver, "DateOfBirthYear"), updateYear);
		
		verifyEquals(userMyAccount.getTextValueOfTextbox(driver, "Company"), updateCompany);

	}
	
	@Test
	public void User_02_My_Account_Addresses() {
		
		userMyAccount.clickToMyAccountTabByText("Addresses");
		
		userMyAccount.clickToButtonByText(driver, "Add new");
		
		userMyAccount.inputToTextboxByID(driver, addressFirstName, "Address_FirstName");

		userMyAccount.inputToTextboxByID(driver, addressLastName, "Address_LastName");
		
		userMyAccount.inputToTextboxByID(driver, addressEmail, "Address_Email");
		
		userMyAccount.inputToTextboxByID(driver, addressCompany, "Address_Company");
		
		userMyAccount.selectDropDownByName(driver, "Address.CountryId", addressCountry);
		
		userMyAccount.inputToTextboxByID(driver, addressCity, "Address_City");
		
		userMyAccount.inputToTextboxByID(driver, address1, "Address_Address1");
		
		userMyAccount.inputToTextboxByID(driver, address2, "Address_Address2");

		userMyAccount.inputToTextboxByID(driver, addressZipCode, "Address_ZipPostalCode");

		userMyAccount.inputToTextboxByID(driver, addressPhoneNumber, "Address_PhoneNumber");
		
		userMyAccount.inputToTextboxByID(driver, addressFaxNumber, "Address_FaxNumber");
		
		userMyAccount.clickToButtonByText(driver, "Save");

		verifyEquals(userMyAccount.getNotificationBarText(), successAddressesAdded);

		verifyEquals(userMyAccount.getAdrressAddedInfoByClass("name"), addressFirstName + " " + addressLastName);
		
		verifyEquals(userMyAccount.getAdrressAddedInfoByClass("email"), addressEmail);

		verifyEquals(userMyAccount.getAdrressAddedInfoByClass("phone"), addressPhoneNumber);
		
		verifyEquals(userMyAccount.getAdrressAddedInfoByClass("fax"), addressFaxNumber);

		verifyEquals(userMyAccount.getAdrressAddedInfoByClass("address1"), address2);

		verifyEquals(userMyAccount.getAdrressAddedInfoByClass("address2"), address1);

		verifyEquals(userMyAccount.getAdrressAddedInfoByClass("city-state-zip"), addressCity + ", " + addressZipCode);

		verifyEquals(userMyAccount.getAdrressAddedInfoByClass("country"), addressCountry);

	}
	
	@Test
	public void User_03_My_Account_Change_Password() {
		
		userMyAccount.clickToMyAccountTabByText("Change password");
		
		userMyAccount.inputToTextboxByID(driver, registeredPassword, "OldPassword");
		
		userMyAccount.inputToTextboxByID(driver, newChangedPassword, "NewPassword");

		userMyAccount.inputToTextboxByID(driver, newChangedPassword, "ConfirmNewPassword");
		
		userMyAccount.clickToButtonByText(driver, "Change password");

		verifyEquals(userMyAccount.getNotificationBarText(), "Password was changed");

		userMyAccount.clickToHeaderLinkByName(driver, "Log out");
		
		userLogin = PageGeneratorManager.getUserLoginPage(driver);
		
		userLogin.inputToTextboxByID(driver, registeredEmail, "Email");
		
		userLogin.inputToTextboxByID(driver, registeredPassword, "Password");
		
		userLogin.clickToButtonByText(driver, "Log in");
		
		verifyEquals(userLogin.getErrorMessageValidation(driver), errorCredentailIncorrect);
		
		userLogin.inputToTextboxByID(driver, registeredEmail, "Email");
		
		userLogin.inputToTextboxByID(driver, newChangedPassword, "Password");
		
		userLogin.clickToButtonByText(driver, "Log in");
		
		userHome = PageGeneratorManager.getUserHomePage(driver);
		
		verifyEquals(userHome.getWelcomeTextOnHomePage(), welcomeToOurStoreText);
	}
	
	@Test
	public void User_04_Login_Blank_Password() {
		
				
	}
	
	@Test
	public void User_05_Login_Invalid_Password() {
		
		
		
	}
	
	@AfterClass()
	public void afterClass() {
		driver.quit();
	}
}
