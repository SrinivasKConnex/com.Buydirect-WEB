package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testBase.TestBase;

public class ABADDACompleteMessagePage extends TestBase {

    
	private By messageBodyElement = By.id("cdw--pending-body");

    public WebElement messageBodyText() {
        return driver.findElement(messageBodyElement);
    }
    public String getmessageBodyText() {
        return getText_custom(driver.findElement(messageBodyElement),"CDW Complete message Body text");
    }
}
