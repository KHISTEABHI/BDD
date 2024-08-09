package pageMethods;

import pageObjects.PO_LoginPage;
import report.Report_Extent;
import selenium.SeleniumReusable;

public class PM_LoginPage extends Report_Extent implements PO_LoginPage{

	public void clickOnLoginLink() {
		SeleniumReusable.clickElement(login_link, "login link");
	}
	
	public void entersUserName(String userName) {
		SeleniumReusable.sendKeysToElement(userName_textField, userName, "username text field");
	}
	
	public void entersPassword(String password) {
		SeleniumReusable.sendKeysToElement(password_textField, password, "password text field");
	}
	
	public void clicksOnLoginButton() {
		SeleniumReusable.clickElement(login_button, "login button");
	}
}
