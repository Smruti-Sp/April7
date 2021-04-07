package mar4;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AdvancedReporting {
	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;

	@BeforeTest
	public void setUp() throws Throwable {
		// define path of extentreports
		report = new ExtentReports("./ExtentReports/Demo.html");

	}

	@BeforeMethod
	public void generate() throws Throwable {
		driver = new ChromeDriver();
		driver.get("https://google.com");
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}

	@Test(enabled = false)
	public void passTest() {
		logger = report.startTest("Pass Test");
		logger.assignAuthor("Ranga");
		String expected = "Google";
		String actual = driver.getTitle();
		if (actual.equalsIgnoreCase(expected)) {
			Reporter.log("Title is Matching::" + expected + "   " + actual, true);
			logger.log(LogStatus.PASS, "Test Case Pass:::" + expected + "   " + actual);
		} else {
			Reporter.log("Title is Not Matching::" + expected + "   " + actual, true);
			logger.log(LogStatus.FAIL, "Test Case Fail:::" + expected + "   " + actual);
		}
	}

	@Test
	public void failTest() throws Throwable {
		logger = report.startTest("Fail Test");
		String expected = "Gmail";
		String actual = driver.getTitle();
		if (actual.equalsIgnoreCase(expected)) {
			Reporter.log("Title is Matching::" + expected + "   " + actual, true);
			logger.log(LogStatus.PASS, "Test Case Pass:::" + expected + "   " + actual);
		} else {
			File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("D:/OJTProjectTesting/SeleniumPractise/Screens/Homepage.png"));
			Reporter.log("Title is Not Matching::" + expected + "   " + actual, true);
			logger.log(LogStatus.FAIL, "Test Case Fail:::" + expected + "   " + actual);
			String image = logger.addScreenCapture("D:/OJTProjectTesting/SeleniumPractise/Screens/Homepage.png");
			logger.log(LogStatus.FAIL, image);
		}
	}

	@AfterMethod
	public void tearDown() {
		report.endTest(logger);
		report.flush();
		driver.close();
	}
}
