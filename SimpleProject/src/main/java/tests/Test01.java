package tests;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.TestNG;

import actions.ConnectExcel;
import pages.CreateApp;
import pages.LoginPage;

public class Test01<Workbook, Sheet> extends EnvironmentSetup {

	@Test(testName = "Launch Cyclos and Perform a Payment to system", description = "Demo Test script")
	public void LaunchGoogle() throws InterruptedException, IOException {
		//browser.get("https://demo.cyclos.org");
		String getdataval = ConnectExcel.retrievedata("TS1");
		String[] splitval = getdataval.split("@@");
		LoginPage LoginPage = new LoginPage(browser,testReporter);
		LoginPage.launchapp("https://demo.cyclos.org");
		LoginPage.login(splitval[208], splitval[209]);	
		
		CreateApp CreateAppl = new CreateApp(browser,testReporter);
		//CreateAppl.switchToFrame0();
		//CreateAppl.contactPerson(splitval[0]);
		CreateAppl.clickBankingMenu();
		CreateAppl.clickPaymenttosystem(splitval[0]);
		CreateAppl.enterdescinpts(splitval[1]);
		CreateAppl.clickptssubmit();
		CreateAppl.clickptsconfirm();
		//CreateAppl.clickPaymenttouser(splitval[2]);
		//CreateAppl.enterptudescription(splitval[3]);
		//CreateAppl.clickptusubmit();
		//CreateAppl.clickptuconfirm();
		CreateAppl.clicklogout();
	}
	 			   
	
	
}
