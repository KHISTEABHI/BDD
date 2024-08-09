package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import report.Report_Extent;
import selenium.SeleniumReusable;

public class CommonSteps extends Report_Extent{
	
	@Before
	public void startSession(Scenario scenario) {
		String tag = scenario.getName();
		SeleniumReusable.testCaseId = tag.substring(tag.indexOf('#')+1, tag.length());
		launchBrowser();
		initializeReport();
	}
	
	@After
	public void endSession() {
		quitDriver();
		closeTestReport();
	}

}
