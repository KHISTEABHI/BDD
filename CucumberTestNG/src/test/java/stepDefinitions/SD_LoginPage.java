package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageMethods.PM_LoginPage;
import selenium.SeleniumReusable;
import utility.ExcelUtility;

public class SD_LoginPage extends PM_LoginPage{

	ExcelUtility excelUtility = new ExcelUtility();
	
	@Given("User login to the application")
	public void user_have_code_and_feature_file() {
		clickOnLoginLink();
		entersUserName(excelUtility.getDataFromTestDataSheetForCurrentTestCase("Username"));
		entersPassword(excelUtility.getDataFromTestDataSheetForCurrentTestCase("Password"));
		clicksOnLoginButton();
	}
	@When("user clicks on run button")
	public void user_clicks_on_run_button() {
		System.out.println("In : user clicks on run button");

	}
	@Then("the program will execute")
	public void the_program_will_execute() {
		System.out.println("In : the program will execute");
	}
}
