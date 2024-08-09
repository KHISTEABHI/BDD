package selenium;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import report.Report_Extent;
import utility.PropertiesFileUtility;

public class SeleniumReusable extends Report_Extent{

	public static String testCaseId ;

	public static WebElement getElement(By locator) {
		return getDriver().findElement(locator);
	}

	// Check if file is downloaded
	public static boolean isFileDownloaded(String fileName, Duration ofMinutes, Duration pollingTime) {
		String downloadPath = PropertiesFileUtility.getValue("DownloadPath");
		File file = new File(downloadPath, fileName);

		FluentWait<File> fluentWait = new FluentWait<File>(file).withTimeout(ofMinutes).pollingEvery(pollingTime)
				.ignoring(FileNotFoundException.class).ignoring(IOException.class);

		boolean isFileDownloaded = fluentWait.until(new Function<File, Boolean>() {
			@Override
			public Boolean apply(File f) {
				return f.canRead();
			}
		});

		if (isFileDownloaded)
			return true;
		else
			return false;
	}
	
	public static boolean isElementDisplayed(By locator) {
		boolean isElementDisplayed = getElement(locator).isDisplayed()?true:false;
		return isElementDisplayed;
	}

	// Clicks on the provided web element
	public static void clickElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	// Sends keys to the provided text input element
	public static void sendKeysToElement(WebElement element, String text) {
		element.sendKeys(text);
	}

	// Retrieves text from the provided web element
	public static String getTextOfElement(WebElement element) {
		return element.getText();
	}

	// Waits until the provided element is visible
	public static void waitForElementVisible(WebElement element, Duration timeout) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Opens a URL in the browser
	public static void openURL(String url) {
		getDriver().get(url);
	}

	// Closes the current browser window
	public static void closeBrowser() {
		getDriver().quit();
	}

	// Switches to the provided frame within the page
	public static void switchToFrame(WebElement frame) {
		getDriver().switchTo().frame(frame);
	}

	// Switches back to the default content
	public static void switchToDefaultContent() {
		getDriver().switchTo().defaultContent();
	}

	// Captures a screenshot of the current browser window
	public static void captureScreenshot(String fileName) {
		// Implementation to capture screenshot goes here
	}

	// Asserts that the provided element is present on the page
	public static void assertElementPresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Asserts that the page title matches the expected title
	public static void assertTitle(String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleIs(expectedTitle));
	}

	// Switches to a window with the provided handle
	public static void switchToWindow(String windowHandle) {
		getDriver().switchTo().window(windowHandle);
	}

	// Closes all windows except the main window
	public static void closeAllWindowsExceptMain() {
		String mainWindowHandle = getDriver().getWindowHandle();
		Set<String> windowHandles = getDriver().getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(mainWindowHandle)) {
				getDriver().switchTo().window(handle);
				getDriver().close();
			}
		}
		getDriver().switchTo().window(mainWindowHandle);
	}

	// Navigates back to the previous page
	public static void navigateBack() {
		getDriver().navigate().back();
	}

	// Refreshes the current page
	public static void refreshPage() {
		getDriver().navigate().refresh();
	}

	// Executes JavaScript code in the current context
	public static void executeJavaScript(String script) {
		((JavascriptExecutor) getDriver()).executeScript(script);
	}

	public static void assertElementPresent(By locator) {
		Assert.assertTrue(DriverFactory.getDriver().findElements(locator).size() > 0, "Element not found!");
	}

	public static void assertElementText(By locator, String expectedText) {
		String actualText = DriverFactory.getDriver().findElement(locator).getText();
		Assert.assertEquals("Element text does not match", expectedText, actualText);
	}

	public static void assertElementContainsText(By locator, String expectedText) {
		String actualText = DriverFactory.getDriver().findElement(locator).getText();
		Assert.assertTrue(actualText.contains(expectedText), "Element text does not contain expected text");
	}

	public static void assertElementAttributeContains(By locator, String attributeName, String expectedValue) {
		String actualValue = DriverFactory.getDriver().findElement(locator).getAttribute(attributeName);
		Assert.assertTrue(actualValue.contains(expectedValue),
				"Element attribute '" + attributeName + "' does not contain expected value");
	}


	public static void clickElement(By locator, String nameOfElement) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		logStatusReportAsInfo("Click on '"+nameOfElement+"'");
	}
	
	public static void moveToElement(By locator) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(locator)).build().perform();
	}

	public static void clickElementAction(By locator) {
		SeleniumReusable.waitForElementVisible(SeleniumReusable.getElement(locator), Duration.ofSeconds(5));
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(locator)).build().perform();
		actions.click(getDriver().findElement(locator)).build().perform();
	}

	public static void clickAndHoldElement(By locator) {
		Actions actions = new Actions(getDriver());
		actions.clickAndHold(getDriver().findElement(locator)).build().perform();
	}

	public static void releaseElement(By locator) {
		Actions actions = new Actions(getDriver());
		actions.release(getDriver().findElement(locator)).build().perform();
	}

	public static void doubleClickElement(By locator) {
		Actions actions = new Actions(getDriver());
		actions.doubleClick(getDriver().findElement(locator)).build().perform();
	}

	public static void contextClickElement(By locator) {
		Actions actions = new Actions(getDriver());
		actions.contextClick(getDriver().findElement(locator)).build().perform();
	}

	public static void dragAndDropElement(By sourceLocator, By targetLocator) {
		Actions actions = new Actions(getDriver());
		actions.dragAndDrop(getDriver().findElement(sourceLocator), getDriver().findElement(targetLocator)).build().perform();
	}

	public static void sendKeysToElement(By locator, String text, String nameOfElement) {
		//Actions actions = new Actions(getDriver());
//		actions.sendKeys(getDriver().findElement(locator), text).build().perform();
		getDriver().findElement(locator).clear();
		getDriver().findElement(locator).sendKeys(text);
		logStatusReportAsInfo("Enter "+"'"+text+"'"+" in "+nameOfElement);
	}
}
