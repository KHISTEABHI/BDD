package selenium;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import report.Report_Extent;
import utility.PropertiesFileUtility;

public class DriverFactory {

    private static WebDriver driver;
    private static final int IMPLICIT_WAIT = Integer.parseInt(PropertiesFileUtility.getValue("ImplicitWait"));
    private static final String URL = PropertiesFileUtility.getValue(PropertiesFileUtility.getValue("URL"));

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver launchBrowser() {
        if (driver == null) {
            String browser = PropertiesFileUtility.getValue("browser").toLowerCase();
            driver = createDriver(browser);
            configureDriver();
            driver.get(URL);
        }
        return driver;
    }

    private static WebDriver createDriver(String browser) {
        WebDriver driverInstance;
        switch (browser) {
            case "chrome":
                driverInstance = new ChromeDriver();
                break;
            case "firefox":
                driverInstance = new FirefoxDriver();
                break;
            case "edge":
                handleEdgeBrowser();
                driverInstance = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driverInstance;
    }

    private static void configureDriver() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
            driver.manage().window().maximize();
        }
    }

    private static void handleEdgeBrowser() {
        if (PropertiesFileUtility.getValue("ClosePreviousEdgeBrowserInstance").equalsIgnoreCase("Yes")) {
            closeEdgeBrowser();
        }
    }

    private static void closeEdgeBrowser() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM msedge.exe /T");
            Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe /T");
            System.out.println("All Edge browser instances closed successfully.");
            Thread.sleep(7000);
            Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"del /s/f/q %temp%\\*\"");
            System.out.println("Deleted temp files");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}








//public static WebDriver driver;
//public static int implicitWaitInSeconds = Integer.parseInt(PropertiesFileUtility.getValue("ImplicitWait"));
//public static String url = PropertiesFileUtility.getValue(PropertiesFileUtility.getValue("URL"));
//
//public static WebDriver getDriver() {
//	return driver;
//}
//
//public static WebDriver launchBrowser() {
//	String browser = PropertiesFileUtility.getValue("browser");
//	if (driver == null) {
//		switch (browser) {
//		case "chrome":
//			driver = new ChromeDriver();
//			driver.manage().deleteAllCookies();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitInSeconds));
//			driver.manage().window().maximize();
//			driver.get(url);
//			break;
//		case "fireFox":
//			driver = new FirefoxDriver();
//			driver.manage().deleteAllCookies();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitInSeconds));
//			driver.manage().window().maximize();
//			driver.get(url);
//			break;
//		case "edge":
//			if (PropertiesFileUtility.getValue("ClosePreviousEdgeBrowserInstance").equalsIgnoreCase("Yes")) {
//				closeEdgeBrowser();
//			}
//			driver = new EdgeDriver();
//			if (PropertiesFileUtility.getValue("DeleteCookies").equalsIgnoreCase("Yes"))
//				driver.manage().deleteAllCookies();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitInSeconds));
//			driver.manage().window().maximize();
//			driver.get(url);
//			try {
//				Thread.sleep(4000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			SeleniumReusable.refreshPage();
//			break;
//		default:
//			throw new IllegalArgumentException("Unsupported browser: " + browser);
//		}
//	}
//	return driver;
//}
//
//public static void closeEdgeBrowser() {
//	try {
//		Runtime.getRuntime().exec("taskkill /F /IM msedge.exe /T");
//		Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe /T");
//		System.out.println("All Edge browser instances closed successfully.");
//		Thread.sleep(7000);
//		Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"del /s/f/q %temp%\\*\"");
//		System.out.println("Deleted temp files");
//	} catch (IOException e) {
//		e.printStackTrace();
//	} catch (InterruptedException e) {
//		e.printStackTrace();
//	}
//}
//
//public static void quitDriver() {
//	if (driver != null) {
//		driver.quit();
//		driver = null;
//	}
//}