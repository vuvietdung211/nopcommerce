package commons;

public class UserBasePageUI {
	
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_PAGE_HEADER = "xpath=//div[@class='header']//a[text()='%s']";
	public static final String DYNAMIC_PAGE_FOOTER = "xpath=//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE_BY_NAME_ID = "xpath=//span[@id='%s-error']";
	public static final String ERROR_MESSAGE_VALIDATION = "css=div.message-error"; 
	public static final String HEADER_POPUP_CLOSE_BUTTON = "css=span.close";
	public static final String DYNAMIC_MAIN_HEADER_MENU_BY_NAME = "xpath=//ul[contains(@class,'notmobile')]//a[contains(text(),'%s')]";
	public static final String DYNAMIC_CHILD_HEADER_MENU_BY_NAME = "xpath=//ul[contains(@class,'notmobile')]//a[contains(text(),'%s')]";
	public static final String ADD_YOUR_REVIEW_BUTTON = "xpath=//a[text()='%s']"; 



}
