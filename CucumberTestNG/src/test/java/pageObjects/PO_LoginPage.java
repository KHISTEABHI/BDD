package pageObjects;

import org.openqa.selenium.By;

public interface PO_LoginPage {
	
	By login_link = By.id("login2");
	
	By userName_textField = By.id("loginusername");
	
	By password_textField = By.id("loginpassword");
	
	By login_button = By.xpath("//button[@onclick='logIn()']");

}
