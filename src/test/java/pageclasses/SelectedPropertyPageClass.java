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

	@FindBy(xpath = "//div[@class='_19xnuo97']/div/div[1]")
	List<WebElement> allAvailableAmenities;

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
				// action.moveToElement(allAvailableAmenities.get(i)).build().perform();
				poolDisplayed = true;
			}
		}
		logCheckStatus(poolDisplayed, "Check pool is diplayed on amenities popup: ");
		return poolDisplayed;
	}

	private void logCheckStatus(boolean pass, String description) {
		if (pass) {
			test.log(LogStatus.PASS, description + "PASSED");
		} else {
			test.log(LogStatus.FAIL, description + "FAILED");
		}
	}
}
