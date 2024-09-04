/**
 * Author: Srinivas
 */
package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	public static WebDriver createBrowserInstance(String browser) {

		// WebDriver driver = null;
		RemoteWebDriver driver = null;

		if (browser.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.silentOutput", "true");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
			options.addArguments("--window-size=1000,1000");
			options.addArguments("--disable-extensions");
			options.addArguments("--incognito");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			// Enable headless mode

			options.addArguments("--disable-gpu"); // Optional: Disable GPU usage (usefulon Windows)
			options.addArguments("--window-size=1920,1080");

			// Helper.zoomOutChromeWindow(driver);

		} else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
		}
		if (browser.equalsIgnoreCase("ie")) {

			WebDriverManager.iedriver().setup();
			InternetExplorerOptions iOptions = new InternetExplorerOptions();
			iOptions.addCommandSwitches("-private");

			driver = new InternetExplorerDriver(iOptions);
		}
		return driver;
	}

}
