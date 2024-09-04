package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import reusableComponents.Helper;
import testBase.TestBase;

public class BankIntroPage extends TestBase {

	private By selectCDWLink = By.xpath("//label[@for='btn-banking-intro-mob__cdw']");
	private By selectIAV = By.id("btn-banking-intro-mob__iav");
	private By clickContinueButton = By.id("btn-banking-route");
	private By introPageHeader = By.id("banking-intro");
	private By bankIntroFooter = By.id("banking-intro-footer");
	private By headerText = By.id("headerBank");
	private By subTitleText = By.xpath("//div[@class='col-12']//h1");
	private By bodyText = By.id("banking-intro__body");
	private By selectCDW = By.id("btn-banking-intro-mob__cdw");
	private By plaidImage = By.xpath("//img[@alt='plaid logo']");
	private By cdwImage = By.xpath("//img[@alt='no bank logo']");
	private By iavHeaderlabel = By
			.xpath("//label[@for='btn-banking-intro-mob__iav']//span[@class='banking-intro__header']");
	private By cdwHeaderlabel = By
			.xpath("//label[@for='btn-banking-intro-mob__cdw']//span[@class='banking-intro__header']");
	private By iavbankingintroheaderbodyText = By.xpath("(//span[@class='zulu-custom-font text-start mb-0'])[1]");
	private By cdwbankingintroheaderbodyText = By.xpath("(//span[@class='zulu-custom-font text-start mb-0'])[2]");
	private By SecurityInfoText = By.xpath("//p[@class='lh-1 mb-0']");
	private By SecurityInfoIcon = By.xpath("(//*[name()='svg'])[3]");
	private By BankIntroPageButtonElement = By.xpath("//button[@id='btn-banking-route']");

	public void selectCDW() {
		driver.findElement(selectCDWLink).click();
	}

	public void selectIAV() {
		driver.findElement(selectIAV).click();
	}

	public void clickContinueButton() {
		driver.findElement(clickContinueButton).click();
	}

	public WebElement waitUntilBankIntroPageDisplays() {
		return driver.findElement(introPageHeader);
	}

	public String getBankIntroPageFooterText() {

		return getText_custom(driver.findElement(bankIntroFooter), "Bank Intro Page Footer Text");
	}

	public String getHeaderText() {
		waitElementToAppear_custom(driver.findElement(headerText), "Wait for header to display");
		return driver.findElement(headerText).getText();
	}

	public String getSubTitleText() {
		return driver.findElement(subTitleText).getText();
	}

	public String getBodyText() {
		waitElementToAppear_custom(driver.findElement(bodyText), "Wait for Bank intro Page appear");
		return driver.findElement(bodyText).getText();

	}

	public void waitelementappear() {
		waitElementToAppear_custom(driver.findElement(bodyText), "Wait for Bank intro Page appear");
	}

	public boolean isIAVSelected() {
		return driver.findElement(selectIAV).isSelected();
	}

	public boolean isCDWSelected() {
		return driver.findElement(selectCDW).isSelected();
	}

	public boolean isPlaidImagedisplay() {

		return isElementPresent_custom(driver.findElement(plaidImage), "Plaid Image display");
	}

	public boolean isCDWImagedisplay() {
		return isElementPresent_custom(driver.findElement(cdwImage), "CDW Image display");

	}

	public String getIAVBankintroHeaderLabel() {
		return Helper.removenewLineChar(getText_custom(driver.findElement(iavHeaderlabel), "Iav Header label"));

	}

	public String getCDWBankintroHeaderLabel() {

		return Helper.removenewLineChar(getText_custom(driver.findElement(cdwHeaderlabel), "CDW Header label"));

	}

	public String getIAVBankintrobodytext() {

		return getText_custom(driver.findElement(iavbankingintroheaderbodyText), "IAV body text");

	}

	public String getCDWBankintrobodytext() {

		return getText_custom(driver.findElement(cdwbankingintroheaderbodyText), "CDW body text");

	}

	public String getSecurityInfotext() {

		return getText_custom(driver.findElement(SecurityInfoText), "Security Info text");

	}

	public boolean getSecurityIcon() {

		return isElementPresent_custom(driver.findElement(SecurityInfoIcon), "Security Info Icon");

	}

	public String getBankIntroPageText() {

		return getText_custom(driver.findElement(BankIntroPageButtonElement), "Button Text");

	}
}
