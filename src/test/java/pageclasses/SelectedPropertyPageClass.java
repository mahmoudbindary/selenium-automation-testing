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

	@FindBy(xpath = "//h1[@class='_14i3z6h']")
	WebElement selectedPropertyName;

	@FindBy(xpath = "//span[@class='_1jpdmc0']")
	WebElement selectedPropertyRating;

	@FindBy(xpath = "(//span[@class='_pgfqnw'])[2]")
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

	public boolean isNameSame(String name) {
		if (selectedPropertyName.getText().equals(name)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPriceSame(String price) {
		if (selectedPropertyPrice.getText().equals(price)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isReviewSame(String review) {
		if (selectedPropertyRating.getText().equals(review)) {
			return true;
		} else {
			return false;
		}
	}
}
