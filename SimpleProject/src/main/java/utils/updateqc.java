package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class updateqc {

    public static void qcupdate(String testName, String qctestfolder, String qctestid) throws IOException, Throwable {
    	
		String FinalStatus = "";
	    
	    for (int i=0; i<actions.Keywords.list.size();i++ ) {
	        
			if(actions.Keywords.list.get(i).equalsIgnoreCase("failed")) {
	    		FinalStatus = "Failed";
	    	}else {
	    		FinalStatus = "Passed";
	    		
	    	}
	    }
	    
	    String logcsvfile = System.getProperty("user.dir") + "\\Logs\\" + "data.csv";
	    String inputdataFileName = System.getProperty("user.dir") + "\\InputData\\" + "inputdata.csv";
		BufferedReader br = new BufferedReader(new FileReader(inputdataFileName));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		if(line!=null)
		   line = line.trim();
        
        while (line != null) {
            sb.append(line);            
            line = br.readLine();
	         if(line!=null)
	        	 line = line.trim();
        }        
		        
        String logpath = sb.toString().trim();

        String qcbin = "http://192.168.22.222:8080/qcbin";
        String qcun = "bankinguser01";
        String qcpwd = "banking123";
        String qcdomain = "DEFAULT";
        String qcproj = "Automation_Initiatives_01";
        
        
        String argval = "Yes##" + qcbin + "##" + qcun + "##" + qcpwd + "##" + qcdomain + "##" + qcproj +"##"+qctestfolder+"##"+testName+"##"+qctestid+"##"+logcsvfile+"##" + inputdataFileName + "##" + FinalStatus + "";	    
        System.out.println(argval);
        try {
        	//String[] parms = {"wscript", "openURL.vbs", "Anna B. Carlson", "12", "fale"};
        	//Runtime.getRuntime().exec(parms);
            //Runtime.getRuntime().exec("cscript E:\\Anuj\\git-repo\\bigbee.testng.framework\\SimpleProject\\VBS\\qcupload.vbs" + argval);
/*            String script = "E:\\Anuj\\git-repo\\bigbee.testng.framework\\SimpleProject\\VBS\\qcupload.vbs";
            String executable = "C:\\Windows\\SysWOW64\\cscript.exe"; 
            String cmdArr [] = {executable, script};
            Runtime.getRuntime ().exec (cmdArr + argval);*/
            
            String str[]={"C:\\Windows\\SysWOW64\\wscript.exe","E:\\Anuj\\git-repo\\bigbee.testng.framework\\SimpleProject\\VBS\\qcupload.vbs",argval};
            Process proc = Runtime.getRuntime().exec(str);
            //proc.waitFor();
            //proc.destroy();
        } catch (IOException e) {

            System.exit(0);
        }
        
    }
    
}
