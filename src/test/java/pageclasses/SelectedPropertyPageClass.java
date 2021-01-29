package pageclasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SelectedPropertyPageClass {
	WebDriver driver;
	ExtentTest test;
	Actions action;

	@FindBy(xpath = "//div[@data-plugin-in-point-id='AMENITIES_DEFAULT']/div[2]/div/div/div[1]")
	List<WebElement> allAvailableAmenities;

	@FindBy(xpath = "//div[@data-section-id='TITLE_DEFAULT']//h1")
	WebElement selectedPropertyName;

	@FindBy(xpath = "(//div[contains(@data-testid,'book')]//span[contains(text(),'/ night')]/parent::span/span[1])[2]")
	WebElement selectedPropertyPrice;

	public SelectedPropertyPageClass(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isPoolDisplayed() {
		boolean poolDisplayed = false;
		for (int i = 0; i < allAvailableAmenities.size(); i++) {
			if (allAvailableAmenities.get(i).getText().equalsIgnoreCase("Pool")) {
				// Only used for presentation purpose
				action.moveToElement(allAvailableAmenities.get(i)).build().perform();
				sleep(0.2);
				poolDisplayed = true;
			}
		}
		logCheckStatus(poolDisplayed, "Check pool is diplayed on amenities popup: ");
		return poolDisplayed;
	}

	public boolean isNameCorrectOnPropertyPage(String name) {
		boolean nameCorrectOnPropertyPage = false;
		if (selectedPropertyName.getText().equals(name)) {
			nameCorrectOnPropertyPage = true;
		}
		logCheckStatus(nameCorrectOnPropertyPage, "Check Name is correct on property page: ");
		return nameCorrectOnPropertyPage;
	}

	public boolean isPriceCorrectOnPropertyPage(String price) {
		boolean priceCorrectOnPropertyPage = false;
		int priceOnSelectedPage = Integer.parseInt(selectedPropertyPrice.getText().substring(1));
		if (priceOnSelectedPage - 5 <= Integer.parseInt(price) && Integer.parseInt(price) <= priceOnSelectedPage + 5) {
			priceCorrectOnPropertyPage = true;
		}
		logCheckStatus(priceCorrectOnPropertyPage, "Check Price is correct on property page: ");
		return priceCorrectOnPropertyPage;
	}

	private void logCheckStatus(boolean pass, String description) {
		if (pass) {
			test.log(LogStatus.PASS, description + "PASSED");
		} else {
			test.log(LogStatus.FAIL, description + "FAILED");
		}
	}

	public void sleep(double timeInSeconds) {
		try {
			int timeInMilliseconds = (int) (timeInSeconds * 1000);
			Thread.sleep(timeInMilliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
