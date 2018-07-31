package tests;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import utils.ConfigurationReader;
import utils.SSInWord;
import utils.ScreenCapture;
import utils.updateqc;
import interfaces.ITestReporter;
import reporting.Reporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EnvironmentSetup {
	public static String scrshot = "Yes"; 
	protected ITestReporter testReporter;
	WebDriver browser;
	ConfigurationReader Config = new ConfigurationReader();
	ScreenCapture SS = new ScreenCapture();
	
	@Parameters({"browser"})
	@BeforeTest
	public void classSetUp(String browserName)
	{
		testReporter = Reporter.getTestReporter();
		
		if (browserName.equalsIgnoreCase("firefox")){
			System.out.println(System.getProperty("user.dir")+"/"+Config.getFirefoxDriver());
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/"+Config.getFirefoxDriver());
			browser = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/"+Config.getChromeDriver());
			browser = new ChromeDriver();			
		}else if (browserName.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/"+Config.getIEDriver());
			browser = new InternetExplorerDriver();
			//System.setProperty("webdriver.ie.driver", Settings.DriverLocation);					
			InternetExplorerOptions options = new InternetExplorerOptions();					
			//options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,false);
			//options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);					
			options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			//options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			browser = new InternetExplorerDriver(options);
		}
		
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		
	}
	
	
	
	@Parameters({"testId"})
	@BeforeMethod
	public synchronized void methodSetup(Method caller,String testId) throws IOException
	{
		
		testReporter.startTest(getTestName(caller), getTestDescription(caller));
	    FileWriter pw = new FileWriter(System.getProperty("user.dir") + "\\Logs\\" + "data.csv",false);
	    FileWriter pw1 = new FileWriter(System.getProperty("user.dir") + "\\InputData\\" + "inputdata.csv",false);
        pw.flush();
        pw.close();
        pw1.flush();
        pw1.close();
	}
	
	@AfterMethod
	public synchronized void afterMethod()
	{
		testReporter.endTest();
		Reporter.flushReport();
	}
	
	@Parameters({"testId","testName","qctestfolder","qctestid"})
	@AfterTest
	public void closeDriver(String testId, String testName, String qctestfolder, String qctestid) throws Throwable
	{
		SSInWord.CreateSSDoc(testId,testName);
		updateqc.qcupdate(testName,qctestfolder,qctestid);
		browser.quit();
		
		
	}

	private String getTestName(Method caller)
	{
		Test testAnnotation = caller.getAnnotation(Test.class);
		if(testAnnotation != null)
		{
			return testAnnotation.testName();
		}
		return "";
	}
	
	private String getTestDescription(Method caller)
	{
		Test testAnnotation = caller.getAnnotation(Test.class);
		if(testAnnotation != null)
		{
			return testAnnotation.description();
		}
		return "";
	}
}
