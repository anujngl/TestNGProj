package pages;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;



import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import actions.Keywords;
import interfaces.ITestReporter;


public class CreateApp {
//TestNG CreateApp Class
	private static Keywords Keywords;
	private static WebDriver driver;
	private static ITestReporter testReporter;
	
	
	public CreateApp(WebDriver browserInst, ITestReporter reporter) {
		driver = browserInst;
		testReporter = reporter;
		Keywords = new Keywords(driver,testReporter);
	}
		
	By BankingMenu = By.xpath("//*[text()='Banking']");
	By Paymenttosystem = By.xpath("//*[text()='Payment to system']");
	By ptsamount = By.xpath("/html/body/div[3]/div[3]/div/div/div[2]/div[2]/div[2]/div/div/table/tbody/tr[5]/td[2]/table/tbody/tr/td[1]/table/tbody/tr/td[1]/input");
	By ptsdesc = By.name("description");
	By ptssubmit = By.xpath("//*[text()='Submit']");
	By ptsconfirm = By.xpath("//*[text()='Confirm']");
	By Paymenttouser = By.xpath("//*[text()='Payment to user']");
	By ptuamount = By.xpath("/html/body/div[3]/div[3]/div/div/div[2]/div[2]/div[2]/div/div/table/tbody/tr[7]/td[2]/table/tbody/tr/td[1]/table/tbody/tr/td[1]/input");
	//By ptudescription = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div/div[2]/div[2]/div[2]/div/div/table/tbody/tr[8]/td[2]/textarea"));
	By logout = By.xpath("//*[text()='Logout']");

public void CreateApp(WebDriver driver) {
	this.driver = driver;
	
}

public void waitforloader(WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flyout-mask']")));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}

public void clickBankingMenu () throws IOException {
  	Keywords.clickElement(BankingMenu,"Banking Menu is clicked", "Banking Menu should be clicked", "Click Banking Menu", "CYC_TC_006");
  	
}

public void clickPaymenttosystem (String Amount) throws InterruptedException, IOException {
	Thread.sleep(2000);
  	Keywords.clickElement(Paymenttosystem,"Payment to System tab is clicked", "Payment to System tab should be clicked", "Click Payment to System tab", "CYC_TC_007");
  	Keywords.enterData(ptsamount, Amount, "Amount " + Amount + " is entered", "Amount " + Amount + " should be entered", "Enter the given amount", "CYC_TC_008");
}

public void enterdescinpts (String desc) throws IOException {
  	
	Keywords.enterData(ptsdesc, desc, "Description " + desc + " is entered", "Description " + desc + " should be entered", "Enter the given description", "CYC_TC_009");
  	  
}


public void clickptssubmit () throws InterruptedException, IOException {
	Thread.sleep(1000);
  	Keywords.clickElement(ptssubmit,"Submit button is clicked", "Submit button should be clicked", "Click Submit button", "CYC_TC_010");
  	
}

public void clickptsconfirm () throws InterruptedException, IOException {
	Thread.sleep(2000);
  	Keywords.clickElement(ptsconfirm,"Confirm button is clicked", "Confirm button should be clicked", "Click Confirm button","CYC_TC_011");
  	
}

public void clickPaymenttouser (String pytamount) throws InterruptedException, IOException {
	Thread.sleep(2000);
  	Keywords.clickElement(Paymenttouser,"Payment to User tab is clicked", "Payment to User tab should be clicked", "Click Payment to User tab", "");
	WebElement usersearch = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div/div[2]/div[2]/div[2]/div/div/table/tbody/tr[5]/td[2]/div/input"));
	usersearch.click();
	driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div/div[2]/div[2]/div[2]/div/div/table/tbody/tr[5]/td[2]/div/input")).sendKeys("Lameed");
	usersearch.sendKeys(Keys.TAB);
	WebElement puamount = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div/div[2]/div[2]/div[2]/div/div/table/tbody/tr[7]/td[2]/table/tbody/tr/td[1]/table/tbody/tr/td[1]/input"));	
	//String puamount1 = newsheet1.getRow(2).getCell(0).getStringCellValue();
	//puamount.sendKeys(puamount1);
	//Keywords.enterData(ptuamount, pytamount , "Amount " + Amount + " is entered", "Amount " + Amount + " should be entered", "Enter the given amount");
}

public void enterptudescription (String ptudesc)
{
	//Keywords.enterData(ptudescription, ptudesc, "Description " + desc + " is entered", "Description " + desc + " should be entered", "Enter the given description");
}
public void clicklogout () throws InterruptedException, IOException {
  	Keywords.clickElement(logout,"logout button is clicked", "logout button should be clicked", "Click logout button","CYC_TC_012");
  	Thread.sleep(2000);
  	
}

}
