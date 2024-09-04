package pageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import testBase.TestBase;

public class CDW_MicroDeposit_Page extends TestBase {

	By depositamount = By.id("DepositAmount");
	By withdrowAmount = By.id("webWithDrawlAmount");
	By btnAmount = By.id("btnAmount");

	public void Enterdepositamount(String deposit) {
		driver.findElement(depositamount).sendKeys(deposit);
	}

	public void Enterwithdrowamount(String withdrow) {
		driver.findElement(withdrowAmount).sendKeys(withdrow);
	}

	public void submitCDWamount() {
		driver.findElement(btnAmount).click();
	}
	
	public static void completeloginAndNavigateToMIcrodepositPage(Map<String, String> loginData) {
		driver.navigate().refresh();
		WelcomePageObject.clickSignIn();
		SignInpageObject.enterLoginEmail(loginData.get("email"));
		SignInpageObject.enterLoginPin(loginData.get("pin"));
		SignInpageObject.submitLogin();
		waitElementToAppear_custom(WelcomeBackPageObject.waitWelcomeBackHeaderDisplay(),"Wait for Welcome Back Page appear");
		WelcomeBackPageObject.clickOnWelcomeBackContinueButton();
	}

	public static void completeMicroDeposit() {
		CDW_MicroDeposit_PageObject.Enterdepositamount("0.09");
		CDW_MicroDeposit_PageObject.Enterwithdrowamount("0.08");
		CDW_MicroDeposit_PageObject.submitCDWamount();

	}
}
