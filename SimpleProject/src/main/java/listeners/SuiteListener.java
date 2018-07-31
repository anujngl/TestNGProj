package listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import reporting.Reporter;

public class SuiteListener implements ISuiteListener {

	
	public static final String OUTPUT_FOLDER = "target/Reports/";

	@Override
	public void onFinish(ISuite arg0) {
		Reporter.closeReport();
	}

	@Override
	public void onStart(ISuite arg0) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		Reporter.createReporter(OUTPUT_FOLDER + "Execution Dashboard " + timeStamp + ".html", true);
	}

}
