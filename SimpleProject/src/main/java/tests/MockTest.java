package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class MockTest extends EnvironmentSetup {

	@Test(testName = "Launch Google & Search some text", description = "This is a mock test to verify the framework")
	public void LaunchGoogle() throws InterruptedException {
		
		browser.get("https://www.google.co.in");
		
		if (browser.getTitle().equalsIgnoreCase("google")){
			testReporter.log(LogStatus.PASS, "Google is launched");
			testReporter.log(LogStatus.INFO, "WebDriver launched the url https://www.google.co.in");
		}else{
			testReporter.log(LogStatus.FAIL, "Google is not launched" + testReporter.addScreenCapture(SS.GetScreenshot(browser)));
			testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(SS.GetScreenshot(browser)));
			testReporter.log(LogStatus.INFO, "WebDriver failed to launch the url https://www.google.co.in");
		}	
		
		browser.findElement(By.id("lst-ib")).sendKeys("TestNG is the best!!!");
		testReporter.log(LogStatus.INFO, "Typing the text TestNG is the best");
		
		browser.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		testReporter.log(LogStatus.INFO, "Pressing Enter button");
		
		Thread.sleep(3000);
		
		if (browser.getTitle().equalsIgnoreCase("TestNG is the best!!! - Google Search")){
			testReporter.log(LogStatus.PASS, "Search is successful");
			testReporter.log(LogStatus.INFO, "Search functionality is working as expected");
		}else{
			testReporter.log(LogStatus.FAIL, "Search is not successful " + testReporter.addScreenCapture(SS.GetScreenshot(browser)));
			testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(SS.GetScreenshot(browser)));
			testReporter.log(LogStatus.INFO, "Search functionality is not working as expected");
		}	
		
	}
}
