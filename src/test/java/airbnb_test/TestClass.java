package airbnb_test;

import org.testng.annotations.Test;

import pageclasses.ResultsPageClass;
import pageclasses.SelectedPropertyPageClass;

/*
 * This is the main class used for running the test cases
 * 
 * @author Mahmoud Bindary
 */

public class TestClass extends BaseTestClass {

	@Test(priority = 0)
	public void verifyAppliedFilters() {
		resultsPage = new ResultsPageClass(driver, test);
		softAssert.assertTrue(resultsPage.isAppliedLocationCorrect(getLocation()));
		softAssert.assertTrue(resultsPage.isAppliedCheckInDateCorrect(getCheckInDate()));
		softAssert.assertTrue(resultsPage.isAppliedCheckOutDateCorrect(getCheckOutDate()));
		softAssert.assertTrue(resultsPage.isAppliedNumberOfGuestsCorrect(getNumberOfGuests()));
		softAssert.assertTrue(resultsPage.isNumberOfGuestsOnFirstPageCorrect(getNumberOfGuests()));
		softAssert.assertAll();
	}

	@Test(priority = 1)
	public void verifyResultsMatchExtraFilters() throws InterruptedException {
		resultsPage = new ResultsPageClass(driver, test);
		resultsPage.clickOnMoreFilters();
		resultsPage.setNumberOfBedrooms(getNumberOfBedrooms());
		resultsPage.setPoolFacility();
		resultsPage.clickOnShowStaysButton();
		softAssert.assertTrue(resultsPage.isNumberOfBedroomsOnFirstPageCorrect(getNumberOfBedrooms()));
		resultsPage.openFirstPropertyDetails();
		resultsPage.switchToFirstPropertyPage();
		selectedPropertyPage = new SelectedPropertyPageClass(driver, test);
		softAssert.assertTrue(selectedPropertyPage.isPoolDisplayed());
		softAssert.assertAll();
	}

	@Test(priority = 2)
	public void verifyPropertyDisplayedOnMap() {
		resultsPage = new ResultsPageClass(driver, test);
		resultsPage.hoverOverFirstProperty();
		softAssert.assertTrue(resultsPage.isPropertyDisplayedOnMap());
		softAssert.assertTrue(resultsPage.isPropertyColorChangedOnMap());
		resultsPage.clickOnFirstPropertyOnMap();
		softAssert.assertTrue(resultsPage.areTypeAndCitySameOnSearchAndMap());
		softAssert.assertTrue(resultsPage.isNameSameOnSearchAndMap());
//		softAssert.assertTrue(resultsPage.isPriceSameOnSearchAndMap());
		softAssert.assertAll();
	}

	@Test(priority = 3)
	public void verifyPropertyWithLowestPrice() {
		resultsPage = new ResultsPageClass(driver, test);
		resultsPage.hoverOverPropertyWithLowestPrice();
		String name = resultsPage.getLowestPricePropertyName();
		String price = resultsPage.getLowestPricePropertyPrice();
		resultsPage.clickOnPropertyWithLowestPriceOnMap();
		resultsPage.openLowestPropertyDetailsFromMap();
		resultsPage.switchToPropertyWithLowestPricePage();
		selectedPropertyPage = new SelectedPropertyPageClass(driver, test);
		softAssert.assertTrue(selectedPropertyPage.isNameCorrectOnPropertyPage(name));
		softAssert.assertTrue(selectedPropertyPage.isPriceCorrectOnPropertyPage(price));
		softAssert.assertAll();
	}

}
