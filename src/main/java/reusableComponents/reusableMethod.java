package reusableComponents;

import testBase.TestBase;

public class reusableMethod extends TestBase{

	public static void checkPartnerselectedBankIntroPage() {
		if (helper.checkPartnerSelectBankIntoPageOrNot()) {
			BankintroPageobject.waitelementappear();;
			BankintroPageobject.selectCDW();
			BankintroPageobject.clickContinueButton();
		} else {
			IAVPageObject.waitUntilIAVDisplay();
			IAVPageObject.selectCDW();
		}
	}
	
	public static void checkPartnerselectedPlasticindicater() {
		if (helper.checkPartnerSelectPlasticindicatorornot()) {
			//waitElementToAppear_custom(PlasticIndicatorPageObject.getHeaderElement(), "wait for Plastic Page appear");
			PlasticIndicatorPageObject.clickPlasticRequest();
		}
	}
}
