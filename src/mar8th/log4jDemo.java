package mar8th;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class log4jDemo {
	WebDriver driver;
	Logger log;

	@Test
	public void verifyLogin() throws InterruptedException {
		log = Logger.getLogger(getClass());
		driver = new ChromeDriver();
		driver.get("");
		log.info("Launch url" + driver.getCurrentUrl());
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		log.info("click on reset button");
		driver.findElement(By.name("")).click();
		log.info("enter username");
		driver.findElement(By.name("")).sendKeys("admin");
		log.info("enter passoward");
		driver.findElement(By.name("")).sendKeys("master");
		log.info("enter login button");
		driver.findElement(By.name("")).submit();
		Thread.sleep(5000);
		String expected = "Dashboard « Stock";
		String actual = driver.getTitle();
		if (actual.contains(expected)) {
			Reporter.log("Login success");
			log.info("Login success::" + expected + "     " + actual);
		} else {
			Reporter.log("Login Fail");
			log.info("Login Fail::" + expected + "     " + actual);
		}

		driver.close();
	}

}
