package pageObjects;

import java.util.Map;

import org.openqa.selenium.By;

import reusableComponents.ReadExcelData;
import testBase.TestBase;

public class CDW_ABA_DDA_Page extends TestBase {

	private By enteraba = By.id("RoutingNumber");
	private By enterdda = By.id("AccountNumber");
	private static By CDWbutton = By.id("btnCDW");
	private By CDWBodyHeaderText = By.xpath("//div[@id='CDW']//p[@class='card-title-desc']");
	private By ABAtextBoxLabel = By.xpath("	//label[@for='RoutingNumber']");
	private By DDAtextBoxLabel = By.xpath("	//label[@for='AccountNumber']");
	private By TextAbouveImageGuide = By.xpath("//p[@class='card-title-desc mb-3 text-center']");
	private By TextBelowImageGuide = By.id("banking-request--body");
	private By RequiredTextRouting = By.id("RoutingNumber-error");
	private By RequiredTextAccount = By.id("AccountNumber-error");
	private By redBoxErrorMessage = By.id("errorMessage");
	private By redBoxErrorMessageclose = By.id("btn-close--alert");

	public static void completeCDWPending(boolean isblocked) {
		WaitUntilABADDAPageDisplay();
		Map<String, String> data = !isblocked ? ReadExcelData.Getdatafromexcel("ABA_DDA_Value's", "Valiid_ABA/DDA")
				: ReadExcelData.Getdatafromexcel("ABA_DDA_Value's", "Blocked_ABA/DDA");
		CDWpendingPageObject.enterABA(data.get("Routing_number"));
		CDWpendingPageObject.enterDDA(data.get("Account_number"));
		CDWpendingPageObject.clickSubmitabadda();

	}

	public void enterABA(String aba) {
		clear_custom(driver.findElement(enteraba), "Clear ABA Field");
		sendKeys_custom(driver.findElement(enteraba), "ABA", aba);
	}

	public String getRoutingNumber() {

		return getTextFromTheInputBox_custom(driver.findElement(enteraba), "Routing Number");
	}

	public void enterDDA(String dda) {
		clear_custom(driver.findElement(enterdda), "Clear DDA Field");
		sendKeys_custom(driver.findElement(enterdda), "DDA", dda);
	}

	public void clickSubmitabadda() {
		click_custom(driver.findElement(CDWbutton), "Submit ABA and DDA");
	}

	public String getButtonText() {
		return getText_custom(driver.findElement(CDWbutton), "CDW ABA/DDA Submit Button Text");
	}

	public static void WaitUntilABADDAPageDisplay() {
		waitElementToAppear_custom(driver.findElement(CDWbutton), "Wait for ABA/DDA Page appear");
	}

	public String getCDWBodyHeaderText() {
		return getText_custom(driver.findElement(CDWBodyHeaderText), "CDW Body Header Text");
	}

	public String getAbaTextBoxLabelText() {
		return getText_custom(driver.findElement(ABAtextBoxLabel), "ABA Text Box Label Text");
	}

	public String getDdaTextBoxLabelText() {
		return getText_custom(driver.findElement(DDAtextBoxLabel), "DDA Text Box Label Text");
	}

	public String getAbaPlaceHolderText() {
		return driver.findElement(enteraba).getAttribute("placeholder");
	}

	public String getDDAPlaceHolderText() {
		return driver.findElement(enterdda).getAttribute("placeholder");
	}

	public String getAboveImageGuideText() {
		return getText_custom(driver.findElement(TextAbouveImageGuide), "Guiding Text above image");
	}

	public String getbelowImageGuideText() {
		return getText_custom(driver.findElement(TextBelowImageGuide), "Guiding Text below image");

	}

	public String getRequiredRoutingtext() {

		return getText_custom(driver.findElement(RequiredTextRouting), "Routing number Text box Validation message");
	}

	public String getRequiredAccounttext() {

		return getText_custom(driver.findElement(RequiredTextAccount), "Account number Text box Validation message");
	}

	public String getRedBoxErrorMessage() {
		waitElementToAppear_custom(driver.findElement(redBoxErrorMessage), "Wait for Red Box Error message to display");
		return getText_custom(driver.findElement(redBoxErrorMessage), "Get Error Message");

	}

	public void CloseRedBoxMessage() {
		click_custom(driver.findElement(redBoxErrorMessageclose), "Close red box Error Message");
	}

}
