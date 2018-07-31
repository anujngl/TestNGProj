package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenCapture {
	
	public String GetScreenshot(WebDriver browser){
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String filename = System.getProperty("user.dir") + "/screenshots/" + "Error " + timeStamp + ".png";
		File src= ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(filename));
			return filename;
		}catch (Exception e){
			return e.getMessage();
		}
		
	}

}
