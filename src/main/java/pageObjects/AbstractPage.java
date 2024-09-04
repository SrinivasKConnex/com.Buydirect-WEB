package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testBase.DriverFactory;
import testBase.TestBase;

public class AbstractPage extends TestBase {

	// Using By to locate elements
	private By buydirectLogo = By.id("buydirectMobileLogo");
	private By imageElement = By.id("buydirectMobileLogo");
	private By tenderName = By.xpath("//h5[@class='text-center mb-3']");
	private By processIndicator = By.xpath("//div[@class='pace pace-active']");
	private By footerText = By.id("main-footer");
	private static By themeColor = By.cssSelector("div[id='WelcomeStart'] a[class='text-primary fw-semibold link-sign-in']");


	public String getTheamColor() {
		String actualColor = null;
		try {
			actualColor = DriverFactory.getInstance().getDriver().findElement(themeColor).getCssValue("color");
			actualColor = actualColor.replace("rgba", "rgb").trim();
			actualColor = actualColor.replaceAll(",\\s*\\d*\\s*\\)", ")");
			
		}catch(Exception ex)
		{
			ex.getMessage();
		}
		return actualColor;

	}

	public WebElement prosessIndicater() {

		return DriverFactory.getInstance().getDriver().findElement(processIndicator);

	}

	public String getLogoUrl() {
		String ss = DriverFactory.getInstance().getDriver().findElement(buydirectLogo).getAttribute("src");
		return ss;
	}

	public String getTenderName() {
	
		return getText_custom(DriverFactory.getInstance().getDriver().findElement(tenderName), "Get Tender Name:");
	}

	public WebElement getLogoBroken() {
		return DriverFactory.getInstance().getDriver().findElement(imageElement);
	}

	public String getFooterText() {
		return getText_custom(DriverFactory.getInstance().getDriver().findElement(footerText),"Get Welcome Page Footer Text:");

	}
	
	
	


}
