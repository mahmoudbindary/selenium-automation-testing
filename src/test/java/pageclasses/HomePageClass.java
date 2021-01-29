package pageclasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageClass {
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	// Locators
	@FindBy(id = "bigsearch-query-detached-query")
	WebElement textboxLocation;

	@FindBy(xpath = "//div[contains(@data-testid,'split-dates-0')]")
	WebElement btnCheckIn;

	@FindBy(xpath = "//div[contains(@data-testid,'split-dates-1')]")
	WebElement btnCheckOut;

	@FindBy(xpath = "//div[contains(@data-testid,'guests-button')]")
	WebElement btnGuests;

	@FindBy(xpath = "//button[contains(@data-testid,'search-button')]")
	WebElement btnSearch;

	@FindBy(xpath = "//*[contains(@data-testid,'structured-search-input')]")
	List<WebElement> allSearchFilterBtns;

	@FindBy(xpath = "//div[@aria-label='Calendar']")
	List<WebElement> calenderPanels;

	@FindBy(xpath = "//td[contains(@aria-label,'Choose')]")
	List<WebElement> allValidDates;

	@FindBy(xpath = "//div[contains(@data-testid, 'guests-panel')]")
	List<WebElement> guestsPanels;

	@FindBy(xpath = "//button[contains(@data-testid,'adults-increase')]")
	WebElement btnAdultsIncrease;

	@FindBy(xpath = "//button[contains(@data-testid,'children-increase')]")
	WebElement btnChildrenIncrease;

	@FindBy(xpath = "//button[contains(@data-testid,'infants-increase')]")
	WebElement btnInfantsIncrease;

	// Constructor
	public HomePageClass(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		wait = new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}

	// Methods
	public void setLocation(String location) {
		textboxLocation.sendKeys(location);
		test.log(LogStatus.INFO, "Location is set to: " + location);
		sleep(0.2);
	}

	public void pickCheckInDateAfter(int days) {
		btnCheckIn.click();
		sleep(0.2);
		allValidDates.get(days).click();
		test.log(LogStatus.INFO, "Check in date is picked after: " + days + " days");
	}

	public void pickCheckOutDateAfter(int days) {
		// Note that the picked check-in date is not in allValidDates anymore
		sleep(0.2);
		allValidDates.get(days - 1).click();
		test.log(LogStatus.INFO, "Check out date is picked after: " + days + " days");
	}

	public void setGuestsData(int adultsNum, int childrenNum) {
		btnGuests.click();
		for (int i = 0; i < adultsNum; i++) {
			btnAdultsIncrease.click();
			sleep(0.2);
		}
		for (int i = 0; i < childrenNum; i++) {
			btnChildrenIncrease.click();
			sleep(0.2);
		}
		int guestsNum = adultsNum + childrenNum;
		test.log(LogStatus.INFO, "Number of guests is set to: " + guestsNum + " guests");
	}

	public void clickOnSearchButton() {
		btnSearch.click();
		test.log(LogStatus.INFO, "Search button is clicked");
		sleep(1);
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
