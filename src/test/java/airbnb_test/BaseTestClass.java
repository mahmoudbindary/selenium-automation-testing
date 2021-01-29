package airbnb_test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageclasses.HomePageClass;
import pageclasses.ResultsPageClass;
import pageclasses.SelectedPropertyPageClass;

/*
 * This class is used for setup and tear down test cases using @Before, @After and utility methods
 * 
 * @author Mahmoud Bindary
 */
public class BaseTestClass {
	WebDriver driver;
	String url;
	String originalWindowHandle;
	SoftAssert softAssert;
	HomePageClass homePage;
	ResultsPageClass resultsPage;
	SelectedPropertyPageClass selectedPropertyPage;
	ExtentReports report;
	ExtentTest test;

	private String fullLocation = "Rome, Italy";
	private int checkInDays = 7;
	private int checkOutDays = 14;
	private int adultsNumber = 2;
	private int childrenNumber = 1;
	private int numberOfBedrooms = 5;

	@BeforeClass
	public void setUp() throws Exception {
		url = "https://www.airbnb.com/";
		// Only allowed browsers are chrome and firefox
		driver = initDriver("chrome", url);
		// Generate report in project's main directory
		report = new ExtentReports(System.getProperty("user.dir") + "//TestReport.html");
	}

	@BeforeMethod
	public void beforeEachTest(Method method) {
		test = report.startTest(method.getName());
		test.log(LogStatus.INFO, "Test Case Started");
		driver.get(url);
		sleep(1);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		originalWindowHandle = driver.getWindowHandle();
		softAssert = new SoftAssert();

		homePage = new HomePageClass(driver, test);
		homePage.setLocation(fullLocation);
		homePage.pickCheckInDateAfter(checkInDays);
		homePage.pickCheckOutDateAfter(checkOutDays);
		homePage.setGuestsData(adultsNumber, childrenNumber);
		homePage.clickOnSearchButton();
	}

	@AfterMethod
	public void afterEachTest(ITestResult iTestResult) {
		if (iTestResult.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Test Case Passed");
		} else if (iTestResult.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test Case Skipped");
		} else if (iTestResult.getStatus() == ITestResult.FAILURE) {
			String path = takeScreenshot(iTestResult.getName());
			String imagePath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL, "Test Case Failed", imagePath);
		}
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalWindowHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
		driver.switchTo().window(originalWindowHandle);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}

	public WebDriver initDriver(String driverType, String url) throws Exception {
		if (driverType.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (driverType.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			throw new Exception("Driver Type is not allowed!");
		}
		driver.manage().window().maximize();
		return driver;
	}

	public String takeScreenshot(String fileName) {
		fileName = fileName + ".png";
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sourceFile, new File(System.getProperty("user.dir") + "//Screenshots//" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String destination = System.getProperty("user.dir") + "//Screenshots//" + fileName;
		return destination;
	}

	public String getLocation() {
		String location = fullLocation.split(",")[0];
		return location;
	}

	public int getNumberOfGuests() {
		int numberOfGuests = adultsNumber + childrenNumber;
		return numberOfGuests;
	}

	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	public String getCheckInDate() {
		return formatDates()[0];
	}

	public String getCheckOutDate() {
		return formatDates()[1];
	}

	public String[] formatDates() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);

		cal.add(Calendar.DATE, checkInDays);
		int checkInMonth = cal.get(Calendar.MONTH);
		int checkInYear = cal.get(Calendar.YEAR);
		String checkInDate = dateFormat.format(cal.getTime());

		cal.add(Calendar.DATE, (checkOutDays - checkInDays));
		int checkOutMonth = cal.get(Calendar.MONTH);
		int checkOutYear = cal.get(Calendar.YEAR);
		String checkOutDate = dateFormat.format(cal.getTime());

		if (checkInYear == checkOutYear) {
			checkInDate = checkInDate.split(",")[0];
		}
		if (checkInMonth == checkOutMonth) {
			checkOutDate = checkOutDate.split(" ")[1] + " " + checkOutDate.split(" ")[2];
		}
		if (currentYear == checkOutYear) {
			checkOutDate = checkOutDate.split(",")[0];
		}
		String[] dates = { checkInDate, checkOutDate };
		return dates;
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
