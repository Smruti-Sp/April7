package mar3;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PropertyFiles {
	Properties p;
	FileInputStream fi;
	WebDriver driver;

	@BeforeTest
	public void setUp() throws Throwable {

		p = new Properties();
		fi = new FileInputStream("ORproperties");
		p.load(fi);
		if (p.getProperty("Browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.navigate().to(p.getProperty("Url"));
			driver.manage().window().maximize();
			Thread.sleep(5000);
		} else if (p.getProperty("Browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.get(p.getProperty("Url"));
		} else {
			System.out.println("Browser Value is Not Matching");
		}
	}

	@Test
	public void verifyLogin() throws Throwable {
		driver.findElement(By.xpath(p.getProperty("Objresetbtn"))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(p.getProperty("Objuser"))).sendKeys("admin");
		driver.findElement(By.xpath(p.getProperty("Objpass"))).sendKeys("admin");
		driver.findElement(By.xpath(p.getProperty("Objloginbtn"))).submit();
		Thread.sleep(5000);
		String expected = "Dashboard « Stock";
		String actual = driver.getTitle();
		if (actual.contains(expected)) {
			Reporter.log("Login Success::" + expected + "     " + actual, true);
		} else {
			Reporter.log("Login Fail::" + expected + "     " + actual, true);
		}
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
