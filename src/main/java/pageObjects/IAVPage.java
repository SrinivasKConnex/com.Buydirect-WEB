package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testBase.TestBase;

public class IAVPage extends TestBase {

	By clickOnCDWLink = By.id("ashowbank");
	By waitUntilLinkDisplay = By.id("ashowbank");

	public WebElement waitUntilIAVDisplay() {
		return driver.findElement(waitUntilLinkDisplay);
	}

	public void selectCDW() {
		driver.findElement(clickOnCDWLink).click();
	}
}
