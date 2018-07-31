package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import actions.Keywords;
import interfaces.ITestReporter;

public class LoginPage {
	
	private static Keywords Keywords;
	private static WebDriver driver;
	private static ITestReporter testReporter;

	public LoginPage(WebDriver browserInst, ITestReporter reporter) {
		driver = browserInst;
		testReporter = reporter;
		Keywords = new Keywords(driver,testReporter);
	}
	
	By SigninButton = By.xpath("//span[text()='Sign in']");
	By uname = By.name("principal");
	By pwd = By.xpath("/html/body/div[3]/div[3]/div/div/div[2]/div[2]/div[2]/div/div/div/div[2]/form/div/div[4]/input");
	By LoginButton = By.className("actionButton");

	public void launchapp(String appurl) throws IOException {
		Keywords.launchapp(appurl, "App - " + appurl +  " is launched", "App should be launched", "Launch Cyclos APP", "CYC_TC_001");

	}	
	
	public void login(String username,String password) throws IOException {
		Keywords.clickElement(SigninButton,"Signin button is clicked", "Signin button should be clicked", "Click Signin button", "CYC_TC_002");
		Keywords.enterData(uname, username,"Username is entered", "Username should be entered", "Enter UserName", "CYC_TC_003");
		Keywords.enterData(pwd, password,"Password is entered", "Password should be entered", "Enter Password","CYC_TC_004");
		Keywords.clickElement(LoginButton,"Login button is clicked", "Login button should be clicked", "Click Login button", "CYC_TC_005");
	}

}
