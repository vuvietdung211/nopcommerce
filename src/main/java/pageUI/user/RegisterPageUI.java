package pageUI.user;

public class RegisterPageUI {
	
	
	public static final String PASSWORD_BUTTON = "";
	public static final String LOGIN_BUTTON = "";
	public static final String REMEMBER_ME_CHECKBOX = "";
	public static final String REGISTER_LINK = "";		
	public static final String REGISTER_BUTTON = "";
	public static final String SUCCESS_REGISTER_TEXT = "Xpath=//div[@class='page-body']//div";
	public static final String EXISTING_EMAIL_ERROR_MESSAGE = "css=div.message-error ul li";

	
	
	// dynamic locator
	public static final String TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String RADIO_BUTTON_BY_NAME = "Xpath=//label[contains(text(),'%s')]";
	public static final String TEXTBOX_BY_LABEL = "Xpath=//input//preceding-sibling::label[contains(text(),'%s')]";
	public static final String ERROR_MESSAGE_REQUIRED = "Xpath=//span[contains(text(), '%s')]";
	public static final String CONTINUE_BUTTON = "Xpath=//a[(text()='Continue')]";
}
 