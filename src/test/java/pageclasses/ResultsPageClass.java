package pageclasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ResultsPageClass {
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	ExtentTest test;

	// Locators
	@FindBy(xpath = "//div[@data-testid='little-search']//button[@data-index='0']/div[1]")
	WebElement appliedLocation;

	@FindBy(xpath = "//div[@data-testid='little-search']//button[@data-index='1']/div[1]")
	WebElement appliedDates;

	@FindBy(xpath = "//div[@data-testid='little-search']//button[@data-index='2']/div[1]")
	WebElement appliedNumberOfGuests;

	@FindBy(xpath = "//div[@class='_kqh46o']")
	List<WebElement> allPagesPropertiesRows;

	@FindBy(xpath = "//div[contains(@id,'MoreFilters')]/button")
	WebElement btnMoreFilters;

	@FindBy(xpath = "//button[@data-testid='more-filters-modal-submit-button']")
	WebElement btnShowStays;

	@FindBy(xpath = "//button[contains(@data-testid, 'bedrooms-0-increase')]")
	WebElement btnBedroomsIncrease;

	@FindBy(xpath = "//span[contains(@data-testid, 'bedrooms')]")
	WebElement pickedNumberOfBedrooms;

	@FindBy(xpath = "//input[@name='Pool']")
	WebElement btnPoolToggle;

	@FindBy(xpath = "//a[@class='_gjfol0']")
	List<WebElement> allPropertyLinks;

	@FindBy(xpath = "//div[@aria-label='Map']//button[contains(@aria-label,'$')]")
	List<WebElement> allPropertiesOnMap;

	@FindBy(xpath = "//div[@class='_bzh5lkq']")
	List<WebElement> allPropertiesNames;

	@FindBy(xpath = "//div[@class='_167qordg']")
	List<WebElement> allPropertiesTypesAndCities;

	@FindBy(xpath = "//div[@class='_vaj62s']//span[@class='_10fy1f8']")
	List<WebElement> allPropertiesRatings;

	@FindBy(xpath = "//div[@class='_vaj62s']//span[@class='_a7a5sx']")
	List<WebElement> allPropertiesNumberOfReviewers;

	@FindBy(xpath = "//div[@class='_ls0e43']//span[@class='_1p7iugi']")
	List<WebElement> allPropertiesPrice;

	@FindBy(xpath = "//div[@class='_bwngwx']//button[@data-testid='close']")
	List<WebElement> btnCloseUnwantedSpansOnMap;

	@FindBy(xpath = "//div[@class='_1x0fg6n']//span[@class='_11ry7lz']")
	WebElement propertyRatingOnMap;

	@FindBy(xpath = "//div[@class='_1x0fg6n']//span[@class='_a7a5sx']")
	WebElement propertyNumberOfReviewersOnMap;

	@FindBy(xpath = "//div[@class='_1x0fg6n']//ol[@class='_194e2vt2']/li[1]")
	WebElement propertyTypeOnMap;

	@FindBy(xpath = "//div[@class='_1x0fg6n']//ol[@class='_194e2vt2']/li[2]")
	WebElement propertyCityOnMap;

	@FindBy(xpath = "//div[@class='_1x0fg6n']//div[@class='_1isz8pdq']")
	WebElement propertyNameOnMap;

	@FindBy(xpath = "//div[@class='_1x0fg6n']//span[@class='_1p7iugi']")
	WebElement propertyPriceOnMap;

	// Constructor
	public ResultsPageClass(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		action = new Actions(driver);
		wait = new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}

	// Methods
	public boolean isAppliedLocationCorrect(String location) {
		boolean locationIsCorrect = false;
		if (appliedLocation.getText().equals(location)) {
			locationIsCorrect = true;
		}
		logCheckStatus(locationIsCorrect, "Check applied location is correct: ");
		return locationIsCorrect;
	}

	public boolean isAppliedCheckInDateCorrect(String checkInDate) {
		boolean ckeckInDateIsCorrect = false;
		if (appliedDates.getText().split(" – ")[0].equals(checkInDate)) {
			ckeckInDateIsCorrect = true;
		}
		logCheckStatus(ckeckInDateIsCorrect, "Check check-in date is correct: ");
		return ckeckInDateIsCorrect;
	}

	public boolean isAppliedCheckOutDateCorrect(String checkOutDate) {
		boolean ckeckOutDateIsCorrect = false;
		if (appliedDates.getText().split(" – ")[1].equals(checkOutDate)) {
			ckeckOutDateIsCorrect = true;
		}
		logCheckStatus(ckeckOutDateIsCorrect, "Check check-out date is correct: ");
		return ckeckOutDateIsCorrect;
	}

	public boolean isAppliedNumberOfGuestsCorrect(int numberOfGuests) {
		boolean numberOfGuestsIsCorrect = false;
		if (appliedNumberOfGuests.getText().split(" ")[0].equals(Integer.toString(numberOfGuests))) {
			numberOfGuestsIsCorrect = true;
		}
		logCheckStatus(numberOfGuestsIsCorrect, "Check number of guests is correct: ");
		return numberOfGuestsIsCorrect;
	}

	public boolean isNumberOfGuestsOnFirstPageCorrect(int numberOfGuests) {
		boolean numberOfGuestsOnFirstPageIsCorrect = false;
		int numberOfGuestsOnFirstPage = Integer.parseInt(allPagesPropertiesRows.get(0).getText().split(" ")[0]);
		if (numberOfGuestsOnFirstPage >= numberOfGuests) {
			numberOfGuestsOnFirstPageIsCorrect = true;
		}
		logCheckStatus(numberOfGuestsOnFirstPageIsCorrect, "Check number of guests on first page is correct: ");
		return numberOfGuestsOnFirstPageIsCorrect;
	}

	public boolean isNumberOfBedroomsOnFirstPageCorrect(int numberOfBeds) {
		boolean numberOfBedroomsOnFirstPageIsCorrect = false;
		String[] filters = allPagesPropertiesRows.get(0).getText().split("·");
		int numberOfBedroomsOnFirstPage = Integer.parseInt(filters[1].trim().split(" ")[0]);
		if (numberOfBedroomsOnFirstPage >= numberOfBeds) {
			numberOfBedroomsOnFirstPageIsCorrect = true;
		}
		logCheckStatus(numberOfBedroomsOnFirstPageIsCorrect, "Check number of beds on first page is correct: ");
		return numberOfBedroomsOnFirstPageIsCorrect;
	}

	public void clickOnMoreFilters() {
		btnMoreFilters.click();
		test.log(LogStatus.INFO, "More filters button is clicked");
	}

	public void setNumberOfBedrooms(int bedroomsNum) {
		for (int i = 0; i < bedroomsNum; i++) {
			btnBedroomsIncrease.click();
		}
		test.log(LogStatus.INFO, "Number of bedrooms is set to: " + bedroomsNum + " bedrooms");
	}

	public void setPoolFacility() {
		if (btnPoolToggle.isSelected() != true) {
			btnPoolToggle.click();
		}
		test.log(LogStatus.INFO, "Pool facility is set on");
	}

	public void clickOnShowStaysButton() {
		btnShowStays.click();
		test.log(LogStatus.INFO, "Show stays button is clicked");
		driver.navigate().refresh();
	}

	public void openFirstPropertyDetails() {
		wait.until(ExpectedConditions.elementToBeClickable(allPropertyLinks.get(0)));
		allPropertyLinks.get(0).click();
		test.log(LogStatus.INFO, "First property details is opened");
	}

	public void switchToSelectedPropertyPage() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		String currentTab = driver.getWindowHandle();
		for (int i = 0; i < tabs.size(); i++) {
			if (tabs.get(i) != currentTab) {
				driver.switchTo().window(tabs.get(i));
				driver.navigate().refresh();
			}
		}
		test.log(LogStatus.INFO, "Driver is switched to first property page");
	}

	public void hoverOverFirstProperty() {
		wait.until(ExpectedConditions.visibilityOfAllElements(allPropertyLinks));
		action.moveToElement(allPropertyLinks.get(0)).build().perform();
		test.log(LogStatus.INFO, "Mouse hover over first property");
	}

	public boolean isPropertyDisplayedOnMap() {
		boolean firstPropertyIsDisplayedOnMap = false;
		if (findFirstPropertyOnMap() != null) {
			firstPropertyIsDisplayedOnMap = true;
		}
		logCheckStatus(firstPropertyIsDisplayedOnMap, "Check first property is displayed on map: ");
		return firstPropertyIsDisplayedOnMap;
	}

	public boolean isPropertyColorChangedOnMap() {
		boolean firstPropertyColorChangedOnMap = false;
		if (findFirstPropertyOnMap() != null) {
			String selectedPropertyStyle = findFirstPropertyOnMap().findElement(By.xpath("./div/div"))
					.getAttribute("style");
			if (selectedPropertyStyle.contains("background-color: rgb(34, 34, 34)")
					&& selectedPropertyStyle.contains("color: rgb(255, 255, 255)")) {
				firstPropertyColorChangedOnMap = true;
			}
		}
		logCheckStatus(firstPropertyColorChangedOnMap, "Check first property color changed on map: ");
		return firstPropertyColorChangedOnMap;
	}

	public void clickOnFirstPropertyOnMap() {
		if (btnCloseUnwantedSpansOnMap.size() != 0) {
			btnCloseUnwantedSpansOnMap.get(0).click();
		}
		findFirstPropertyOnMap().click();
		test.log(LogStatus.INFO, "First property on map is clicked");
		wait.until(ExpectedConditions.visibilityOf(propertyRatingOnMap));
	}

	private WebElement findFirstPropertyOnMap() {
		String nameOfFirstPropertyOnLink = allPropertiesNames.get(0).getText().trim();
		for (int i = 0; i < allPropertiesOnMap.size(); i++) {
			String nameOfPropertyOnLink = allPropertiesOnMap.get(i).getAttribute("aria-label");
			nameOfPropertyOnLink = nameOfPropertyOnLink.split("\\$")[0].trim();
			if (nameOfFirstPropertyOnLink.equals(nameOfPropertyOnLink)) {
				return allPropertiesOnMap.get(i);
			}
		}
		return null;
	}

	public boolean isReviewSameOnSearchAndMap() {
		boolean reviewIsSame = false;
		if (allPropertiesRatings.get(0).getText().equals(propertyRatingOnMap.getText())) {
			if (allPropertiesNumberOfReviewers.get(0).getText().equals(propertyNumberOfReviewersOnMap.getText())) {
				reviewIsSame = true;
			}
		}
		logCheckStatus(reviewIsSame, "Check review is same on search and map: ");
		return reviewIsSame;
	}

	public boolean areTypeAndCitySameOnSearchAndMap() {
		boolean typeAndCityAreSame = false;
		String type = allPropertiesTypesAndCities.get(0).getText().split("in")[0].trim();
		String city = allPropertiesTypesAndCities.get(0).getText().split("in")[1].trim();
		if (type.equals(propertyTypeOnMap.getText())) {
			if (city.equals(propertyCityOnMap.getText().split("·")[1].trim())) {
				typeAndCityAreSame = true;
			}
		}
		logCheckStatus(typeAndCityAreSame, "Check type and city are same on search and map: ");
		return typeAndCityAreSame;
	}

	public boolean isNameSameOnSearchAndMap() {
		boolean nameIsSame = false;
		if (allPropertiesNames.get(0).getText().equals(propertyNameOnMap.getText())) {
			nameIsSame = true;
		}
		logCheckStatus(nameIsSame, "Check name is same on search and map: ");
		return nameIsSame;
	}

	public boolean isPriceSameOnSearchAndMap() {
		boolean priceIsSame = false;
		if (allPropertiesPrice.get(0).getText().equals(propertyPriceOnMap.getText())) {
			priceIsSame = true;
		}
		logCheckStatus(priceIsSame, "Check price is same on search and map: ");
		return priceIsSame;
	}

	private void logCheckStatus(boolean pass, String description) {
		if (pass) {
			test.log(LogStatus.PASS, description + "PASSED");
		} else {
			test.log(LogStatus.FAIL, description + "FAILED");
		}
	}

}
