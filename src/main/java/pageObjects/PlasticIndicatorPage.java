package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.DriverFactory;
import testBase.TestBase;

public class PlasticIndicatorPage extends TestBase {



	private By plasticRequestYes = By.id("plastic-request--yes");
	private By plasticRequestNo = By.id("plastic-request--no");
	private By plasticRequestBtn = By.id("plastic-request--btn");
	private By header = By.id("headerPlasticRequest");
	private By plasticSubtitleText = By.id("plastic-request--subtitle");
	private By plasticBodyText = By.xpath("//div[@class='row zulu-custom-font mb-5']//div[@class='col-12']");

	public void selectPlasticRequestYes() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(plasticRequestYes).click();
	}

	public boolean checkYesRadioOptionSelected() throws InterruptedException {
		Thread.sleep(3000);
		return driver.findElement(plasticRequestYes).isSelected();
	}

	public void selectPlasticRequestNo() {
		driver.findElement(plasticRequestNo).click();
	}

	public boolean checkNoRadioOptionSelected() {
		return driver.findElement(plasticRequestNo).isSelected();
	}

	public void clickPlasticRequest() {
		waitElementToAppear_custom(driver.findElement(plasticRequestBtn), "Wait for Plastic page appear");
		driver.findElement(plasticRequestBtn).click();
	}

	public String getButtonText() {
		return driver.findElement(plasticRequestBtn).getText();
	}

	public WebElement getHeaderElement() {
		return driver.findElement(header);
	}

	public void waitPlasticPageAppear() {

		WebDriverWait ex = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(50));
		ex.until(ExpectedConditions.visibilityOf(driver.findElement(plasticRequestBtn)));
	}

	public String getHeaderText() {
		waitElementToAppear_custom(driver.findElement(header), "");
		return driver.findElement(header).getText();
	}

	public String getPlasticSubtitleText() {
		return driver.findElement(plasticSubtitleText).getText();
	}

	public String getPlasticBodyText() {
		String bodyText = driver.findElement(plasticBodyText).getText();
		return bodyText.replaceAll("\\n", " ");
	}

}
