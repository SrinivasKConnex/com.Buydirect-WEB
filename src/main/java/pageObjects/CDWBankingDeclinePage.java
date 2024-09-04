package pageObjects;

import org.openqa.selenium.By;

import testBase.TestBase;

public class CDWBankingDeclinePage extends TestBase {

	private By errormessage=By.id("errorMessage");
	
	public void WaitErrorMessage()
	{
		waitElementToAppear_custom(driver.findElement(errormessage), "Wait for Decline Page error message appear");
	}
	
	public String getErrorMessage()
	{
		return getText_custom(driver.findElement(errormessage), "Red Error Message");
	}
}
