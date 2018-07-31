package actions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.LogStatus;
import interfaces.ITestReporter;
import utils.ScreenCapture;

public class Keywords {
	
	public static ArrayList<String> list = new ArrayList<String>();
	public static ArrayList<String> list1 = new ArrayList<String>();
	public static ArrayList<String> list2 = new ArrayList<String>();
	public static ArrayList<String> list3 = new ArrayList<String>();
	public static ArrayList<String> list4 = new ArrayList<String>();
	public static ArrayList<String> list5 = new ArrayList<String>();
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static ITestReporter testReporter;
	private static ScreenCapture web;
	
	public Keywords(WebDriver driverInst,ITestReporter reporter) {
		driver = driverInst;
		testReporter = reporter;
		wait = new WebDriverWait(driver, 3);
		web = new ScreenCapture();
	}
	
	public void clickElement(By elementLoc,String printResult, String expected_result, String step_desc, String StepID) throws IOException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementLoc));
		    //driver.findElement(elementLoc).click();
			WebElement element = driver.findElement(elementLoc);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			executor.executeScript("arguments[0].click();", element);
			

		    
		    if(tests.EnvironmentSetup.scrshot.equalsIgnoreCase("Yes")) {
			    String sspath= web.GetScreenshot(driver);
			    testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(sspath));		    	
		    	//testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(web.GetScreenshot(driver)));
			    list.add("Passed");
			    list1.add(printResult);
			    list2.add(sspath);
			    list3.add(expected_result);
			    list4.add(step_desc);
			    list5.add(StepID);
			    writetocsv(StepID,"Passed",printResult);			    
		    }else {
			    testReporter.log(LogStatus.PASS, printResult);
			    list.add("Passed");
			    list1.add(printResult);
			    list2.add("NA");
			    list3.add(expected_result);
			    list4.add(step_desc);
			    list5.add(StepID);
			    writetocsv(StepID,"Passed",printResult);			    
		    	
		    }
		    	
		    
		}catch(Exception e) {
			testReporter.log(LogStatus.FAIL, "System encountered a failure while trying to click on the web element, Reason: " + e.getMessage());
			//testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(web.GetScreenshot(driver)));
		    String sspath= web.GetScreenshot(driver);
		    testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(sspath));			
		    list.add("Failed");
		    list1.add("System encountered a failure while trying to click on the web element, Reason: " + e.getMessage());
		    list2.add(sspath);
		    list3.add(expected_result);
		    list4.add(step_desc);	
		    list5.add(StepID);
		    writetocsv(StepID,"Failed","System encountered a failure while trying to click on the web element, Reason: " + e.getMessage());
			throw new AssertionError("A clear description of the failure", e);
		}
	}

	public void clickCheckbox (By elementLoc,String printResult, String expected_result, String step_desc) {
		try {
			//wait.until(ExpectedConditions.elementToBeClickable(elementLoc));
		    //driver.findElement(elementLoc).click();
			WebElement element = driver.findElement(elementLoc);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			executor.executeScript("arguments[0].click();", element);
			

		    if(tests.EnvironmentSetup.scrshot.equalsIgnoreCase("Yes")) {
		    	//testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(web.GetScreenshot(driver)));
			    String sspath= web.GetScreenshot(driver);
			    testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(sspath));
			    list.add("Passed");
			    list1.add(printResult);
			    list2.add(sspath);
			    list3.add(expected_result);
			    list4.add(step_desc);			    
		    }else {
			    testReporter.log(LogStatus.PASS, printResult);
			    list.add("Passed");
			    list1.add(printResult);
			    list2.add("NA");
			    list3.add(expected_result);
			    list4.add(step_desc);			    
		    	
		    }
		}catch(Exception e) {
			//testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(web.GetScreenshot(driver)));
		    String sspath= web.GetScreenshot(driver);
		    testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(sspath));
			testReporter.log(LogStatus.FAIL, "System encountered a failure while trying to click on the web element, Reason: " + e.getMessage());
		    list.add("Failed");
		    list1.add(printResult);
		    list2.add(sspath);
		    list3.add(expected_result);
		    list4.add(step_desc);		    
		}
	}
	
	public String getTextVal(By elementLoc,String printResult, String expected_result, String step_desc) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementLoc));
		    String textval = driver.findElement(elementLoc).getText();

		    if(tests.EnvironmentSetup.scrshot.equalsIgnoreCase("Yes")) {
		    	//testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(web.GetScreenshot(driver)));
			    String sspath= web.GetScreenshot(driver);
			    testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(sspath));
			    list.add("Passed");
			    list1.add(printResult);
			    list2.add(sspath);
			    list3.add(expected_result);
			    list4.add(step_desc);			    
		    }else {
		    	
			    testReporter.log(LogStatus.PASS, printResult);
			    list.add("Passed");
			    list1.add(printResult);
			    list2.add("NA");
			    list3.add(expected_result);
			    list4.add(step_desc);			    
		    }
		    return textval;
		}catch(Exception e) {
			testReporter.log(LogStatus.FAIL, "System encountered a failure while trying to retrieve text on the web element, Reason: " + e.getMessage());
			//testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(web.GetScreenshot(driver)));
		    String sspath= web.GetScreenshot(driver);
		    testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(sspath));
		    list.add("Failed");
		    list1.add(printResult);
		    list2.add(sspath);
		    list3.add(expected_result);
		    list4.add(step_desc);		    
			throw new AssertionError("A clear description of the failure", e);
		}
	}
	
	
	public String getTextValNew(By elementLoc,String printResult, String expected_result, String step_desc) {
		try {
			//wait.until(ExpectedConditions.elementToBeClickable(elementLoc));
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		    if (driver.findElements(elementLoc).size()>0){
				String textval = driver.findElement(elementLoc).getText();
				testReporter.log(LogStatus.FAIL, printResult);
				//testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(web.GetScreenshot(driver)));
			    String sspath= web.GetScreenshot(driver);
			    testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(sspath));
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			    list.add("Failed");
			    list1.add(printResult);
			    list2.add(sspath);
			    list3.add(expected_result);
			    list4.add(step_desc);			    
				return textval;	
			    
		    }else{
		    	
			    if(tests.EnvironmentSetup.scrshot.equalsIgnoreCase("Yes")) {
			    	//testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(web.GetScreenshot(driver)));
				    String sspath= web.GetScreenshot(driver);
				    testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(sspath));
				    list.add("Passed");
				    list1.add(printResult);
				    list2.add(sspath);
				    list3.add(expected_result);
				    list4.add(step_desc);				    
			    }else {
			    	testReporter.log(LogStatus.PASS, "No error message displayed. Login successful");
			    }
			    
			    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			    return null;
		    }
		    
		   

		}catch(Exception e) {
			testReporter.log(LogStatus.FAIL, "System encountered a failure while trying to retrieve text on the web element, Reason: " + e.getMessage());
			//testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(web.GetScreenshot(driver)));
		    String sspath= web.GetScreenshot(driver);
		    testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(sspath));
		    list.add("Failed");
		    list1.add(printResult);
		    list2.add(sspath);
		    list3.add(expected_result);
		    list4.add(step_desc);		    
			throw new AssertionError("A clear description of the failure", e);
		}		
	//return null;
	//throw new AssertionError("A clear description of the failure", e);
}	
	
	
	
		
	public void enterData(By elementLoc, String dataval,String printResult, String expected_result, String step_desc, String StepID) throws IOException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementLoc));
			driver.findElement(elementLoc).clear();
			driver.findElement(elementLoc).sendKeys(dataval);

		    if(tests.EnvironmentSetup.scrshot.equalsIgnoreCase("Yes")) {
		    	//testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(web.GetScreenshot(driver)));
			    String sspath= web.GetScreenshot(driver);
			    testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(sspath));
			    list.add("Passed");
			    list1.add(printResult);
			    list2.add(sspath);
			    list3.add(expected_result);
			    list4.add(step_desc);
			    list5.add(StepID);
			    writetocsv(StepID,"Passed",printResult);	
		    }else {
			    testReporter.log(LogStatus.PASS, printResult);
			    list.add("Passed");
			    list1.add(printResult);
			    list2.add("NA");
			    list3.add(expected_result);
			    list4.add(step_desc);	
			    list5.add(StepID);
			    writetocsv(StepID,"Passed",printResult);	
		    }
		}catch(Exception e) {
			testReporter.log(LogStatus.FAIL, "System encountered a failure while trying to enter the data " + dataval + " into the web element, Reason:" + e.getMessage());
			//testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(web.GetScreenshot(driver)));
		    String sspath= web.GetScreenshot(driver);
		    testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(sspath));
		    list.add("Failed");
		    list1.add("System encountered a failure while trying to enter the data " + dataval + " into the web element, Reason:" + e.getMessage());
		    list2.add(sspath);
		    list3.add(expected_result);
		    list4.add(step_desc);	
		    list5.add(StepID);
		    writetocsv(StepID,"Failed","System encountered a failure while trying to enter the data " + dataval + " into the web element, Reason:" + e.getMessage());	
			throw new AssertionError("A clear description of the failure", e);
		}
	}
	
	public String getLogData(String printResult) {
		try {

			for(int j=1; j<=10; j++) {

				String asdf=driver.findElement(By.xpath("/html/body")).getText();
                asdf=asdf + driver.getPageSource();

				//LogEntries logs = driver.manage().logs().get("browser");
				//String asdf = logs.toString();
				if(asdf.contains("Codigo SMS: ")) {
		               int i = 0;
		               String dbval = "";
		               String val = "";
		                 int found = asdf.indexOf("Codigo SMS: ", i);
		                 //int start = found + 12; // start of actual name
		                 int end = found + 23;
		                 String actualval = asdf.substring(found+17, end);
		                 return actualval;

				}else {
					Thread.sleep(2000);
				}
			}
			
		    
		    if(tests.EnvironmentSetup.scrshot.equalsIgnoreCase("Yes")) {
		    	//testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(web.GetScreenshot(driver)));
			    String sspath= web.GetScreenshot(driver);
			    testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(sspath));
			    list.add("Passed");
			    list1.add(printResult);
			    list2.add(sspath);
			    list3.add("NA");
			    list4.add("NA");			    
		    }else {
		    	testReporter.log(LogStatus.PASS, printResult);
		    }
		}catch(Exception e) {
			testReporter.log(LogStatus.FAIL, "System encountered a failure while trying to enter the data  into the web element, Reason:" + e.getMessage());
			//testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(web.GetScreenshot(driver)));
		    String sspath= web.GetScreenshot(driver);
		    testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(sspath));
		    list.add("Failed");
		    list1.add(printResult);
		    list2.add(sspath);
		    list3.add("NA");
		    list4.add("NA");		    
			throw new AssertionError("A clear description of the failure", e);
		}
		return null;
	}	
	public void writetocsv(String StepID, String Status, String Actualresult) throws IOException{
	    FileWriter pw = new FileWriter(System.getProperty("user.dir") + "\\Logs\\" + "data.csv",true);
	    //Iterator s = customerIterator();
	/*    if (s.hasNext()==false){
	        System.out.println("Empty");
	    }*/
	    //while(s.hasNext()){
	        //Customer current  = (Customer) s.next();
	        //System.out.println(current.toString()+"\n");
	        pw.append(StepID);
	        pw.append(",");
	        pw.append(Status);
	        pw.append(",");
	        pw.append(Actualresult);
	        pw.append("\n");
	    //}
	        pw.flush();
	        pw.close();
	}
	

	public void launchapp(String URL,String printResult, String expected_result, String step_desc, String StepID) throws IOException {
		try {
			
			driver.get(URL);			
		    
		    if(tests.EnvironmentSetup.scrshot.equalsIgnoreCase("Yes")) {
			    String sspath= web.GetScreenshot(driver);
			    testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(sspath));		    	
		    	//testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(web.GetScreenshot(driver)));
			    list.add("Passed");
			    list1.add(printResult);
			    list2.add(sspath);
			    list3.add(expected_result);
			    list4.add(step_desc);
			    list5.add(StepID);
			    writetocsv(StepID,"Passed",printResult);
		    }else {
			    testReporter.log(LogStatus.PASS, printResult);
			    list.add("Passed");
			    list1.add(printResult);
			    list2.add("NA");
			    list3.add(expected_result);
			    list4.add(step_desc);	
			    list5.add(StepID);
			    writetocsv(StepID,"Passed",printResult);
		    }
		    	
		    
		}catch(Exception e) {
			testReporter.log(LogStatus.FAIL, "System encountered a failure while trying to click on the web element, Reason: " + e.getMessage());
			//testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(web.GetScreenshot(driver)));
		    String sspath= web.GetScreenshot(driver);
		    testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(sspath));			
		    list.add("Failed");
		    list1.add("System encountered a failure while trying to click on the web element, Reason: " + e.getMessage());
		    list2.add(sspath);
		    list3.add(expected_result);
		    list4.add(step_desc);
		    list5.add(StepID);
		    writetocsv(StepID,"Failed","System encountered a failure while trying to click on the web element, Reason: " + e.getMessage());
			throw new AssertionError("A clear description of the failure", e);
		}
	}
	

}

