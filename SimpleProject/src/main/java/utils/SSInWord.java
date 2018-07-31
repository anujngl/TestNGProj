package utils;

import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SSInWord extends XWPFDocument {
	public static String ExecutionLog = "";
	public static String SSLocation = "";
	public static boolean CreateSSDoc(String TestID, String testName ) {
		try {

			SimpleDateFormat sdf1 = new SimpleDateFormat("MMddyyyyhmmss");
			System.out.println("Doc creation start Time : " + sdf1.format(new Date()));
			ExecutionLog = TestID + "_Execution log_" + sdf1.format(new Date()) +".docx";
			
			SSLocation = System.getProperty("user.dir") + "\\Logs\\" + ExecutionLog;
			FileOutputStream out = new FileOutputStream(SSLocation);
			
			XWPFDocument docx = new XWPFDocument();
			
			//create paragraph
		      XWPFParagraph paragraph = docx.createParagraph();
		        
		      //Set Bold an Italic
		      XWPFRun paragraphOneRunOne = paragraph.createRun();
		      paragraphOneRunOne.setBold(true);
		      paragraphOneRunOne.setItalic(false);
		      paragraphOneRunOne.setUnderline(UnderlinePatterns.SINGLE);;
		      paragraphOneRunOne.setText("Test Execution Log and Screenshots");
		      //paragraphOneRunOne.addBreak();
		      
			//docx.createParagraph().createRun().setText("Test Execution Log and Screenshots");
			//XWPFRun r = p.createRun();
			//docx.createParagraph().createRun();
			
			XWPFTable table = docx.createTable();
	        
/*			XWPFTableRow rowOne = table.getRow(0);
            
            setRun(paragraph.createRun() , "Calibre LIght" , 10, "2b5079" , "Some string" , false, false);

private static void setRun (XWPFRun run , String fontFamily , int fontSize , String colorRGB , String text , boolean bold , boolean addBreak) {
    run.setFontFamily(fontFamily);
    run.setFontSize(fontSize);
    run.setColor(colorRGB);
    run.setText(text);
    run.setBold(bold);
    if (addBreak) run.addBreak();
}	*/		
	        XWPFTableRow tableRowOne = table.getRow(0);
	        
	        XWPFTableCell cellTextString = tableRowOne.getCell(0); 
	        cellTextString.removeParagraph(0);

	        XWPFParagraph paragraph1 = tableRowOne.getCell(0).addParagraph();
	        XWPFRun run = paragraph1.createRun();
	        run.setText("Test Script ID");
	        run.setBold(true);
	        //paragraph1.createRun().setBold(true);	   
	        //XWPFTableRow row = table.insertNewTableRow(0);
	        XWPFRun run1 = tableRowOne.addNewTableCell().addParagraph().createRun();
	        XWPFTableCell cellTextString6 = tableRowOne.getCell(1); 
	        cellTextString6.removeParagraph(0);	        
	        run1.setText(TestID);
	        //tableRowOne.addNewTableCell().setText(TestID );
	                
	        XWPFTableRow tableRowTwo = table.createRow();
	        //tableRowTwo.getCell(0).setText("Test Script Description");
	        XWPFTableCell cellTextString2 = tableRowTwo.getCell(0); 
	        cellTextString2.removeParagraph(0);	        
	        XWPFParagraph paragraph2 = tableRowTwo.getCell(0).addParagraph();
	        XWPFRun run2 = paragraph2.createRun();
	        run2.setText("Test Script Description   ");
	        run2.setBold(true);	        
	        //tableRowTwo.getCell(1).setText(TestID + "_" + testName);
	        XWPFTableCell cellTextString7 = tableRowTwo.getCell(1); 
	        cellTextString7.removeParagraph(0);	 
	        XWPFRun run3 = tableRowTwo.getCell(1).addParagraph().createRun();
	        run3.setText(TestID + "_" + testName);run3.setBold(false);
	        
	        XWPFTableRow tableRowThree = table.createRow();
	        XWPFTableCell cellTextString3 = tableRowThree.getCell(0); 
	        cellTextString3.removeParagraph(0);	   
	        XWPFRun run4 = tableRowThree.getCell(0).addParagraph().createRun();
	        run4.setText("Executed by");run4.setBold(true);	        
	        //tableRowThree.getCell(0).setText("Status");
	        String un = System.getProperty("user.name");
	        XWPFTableCell cellTextString8 = tableRowThree.getCell(1); 
	        cellTextString8.removeParagraph(0);	 	        
	        XWPFRun run5 = tableRowThree.getCell(1).addParagraph().createRun();
	        run5.setText(un);run5.setBold(false);	
	        //tableRowThree.getCell(1).setText(TestID + "_" + testName);
	        
/*	        XWPFTableRow tableRowFour = table.createRow();
	        tableRowFour.getCell(0).setText("Test DataSet");
	        tableRowFour.getCell(1).setText(TestID + "_" + testName);*/

	        String FinalStatus = "";
		    for (int i=0; i<actions.Keywords.list.size();i++ ) {
		        
		    	if(actions.Keywords.list.get(i).equalsIgnoreCase("failed")) {
		    		FinalStatus = "Failed";
		    	}else {
		    		FinalStatus = "Passed";
		    		
		    	}
		    } 	
		    
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
			System.out.println("Doc creation start Time : " + sdf.format(new Date()));
			
	        /*XWPFTableRow tableRowFive = table.createRow();
	        tableRowFive.getCell(0).setText("Executed Date & Time");
	        tableRowFive.getCell(1).setText(sdf.format(new Date()));*/
			XWPFTableRow tableRowFour = table.createRow();
	        
	        XWPFTableCell cellTextString4 = tableRowFour.getCell(0); 
	        cellTextString4.removeParagraph(0);	 	        
	        XWPFRun run6 = tableRowFour.getCell(0).addParagraph().createRun();
	        run6.setText("Executed Date & Time");run6.setBold(true);	        
	        //tableRowThree.getCell(0).setText("Status");
	        //String un = System.getProperty("user.name");
	        XWPFTableCell cellTextString9 = tableRowFour.getCell(1); 
	        cellTextString9.removeParagraph(0);	 	   	        
	        XWPFRun run7 = tableRowFour.getCell(1).addParagraph().createRun();
	        run7.setText(sdf.format(new Date()));run7.setBold(false);
	        
	        
	        XWPFTableRow tableRowSix = table.createRow();
	        XWPFTableCell cellTextString5 = tableRowSix.getCell(0); 
	        cellTextString5.removeParagraph(0);	 	        
	        XWPFRun run8 = tableRowSix.getCell(0).addParagraph().createRun();
	        run8.setText("Test Status");run8.setBold(true);	        
	        //tableRowSix.getCell(0).setText("Test Status");	        
	        //tableRowSix.getCell(1).setText(FinalStatus);
	        XWPFTableCell cellTextString10 = tableRowSix.getCell(1); 
	        cellTextString10.removeParagraph(0);	 	        
	        XWPFParagraph p = tableRowSix.getCell(1).addParagraph();
	        XWPFRun r = p.createRun();
	        
	        if(FinalStatus == "Passed") {
	        	r.setColor("008000");
	        }else {
	        	 r.setColor("ff0000");
	        }
	        r.setText(FinalStatus);r.setBold(true);
	        //docx.createParagraph();
	        docx.createParagraph().setPageBreak(true);
	        docx.write(out); 
	        out.close();	
				       
	        SSInWord document = new SSInWord(new FileInputStream(new File(SSLocation)));
	        FileOutputStream fos = new FileOutputStream(new File(SSLocation));
	           
	        //String SSQuery = "Select * from SQS_TEST_RESULT where SQS_TR_Run_Id = " + TestAttributes.Run_ID + " order by SQS_TR_Step_Id";
			
       		//ResultSet SSResults = TestAttributes.stmt.executeQuery(SSQuery);
       		
	        for (int i=0; i<actions.Keywords.list.size();i++ ){
       			//document.createParagraph();
	        	XWPFParagraph step_para = document.createParagraph();
       			int k = i + 1;
       			//document.createParagraph().createRun().setText("Step Name: " + "Step_" + k );
                //1)
                XWPFRun run_Name = step_para.createRun();
                run_Name.setText("Step Name: ");
                run_Name.addTab();
                run_Name.setText("Step Name: " + "Step_" + k );
                run_Name.addTab();
                run_Name.addBreak();
       			
       			//document.createParagraph().createRun().setText("Step Details: " + actions.Keywords.list4.get(i));
                //2)
                XWPFRun run_Description = step_para.createRun();
                run_Description.setText("Step Details: " + actions.Keywords.list4.get(i));
                run_Description.addBreak();
                
       			//document.createParagraph().createRun().setText("Expected Result: " + actions.Keywords.list3.get(i));
                //3)
                XWPFRun run_Expected = step_para.createRun();
                run_Expected.setText("Expected Result: " + actions.Keywords.list3.get(i));
                run_Expected.addBreak();

       			//document.createParagraph().createRun().setText("Actual Result: " + actions.Keywords.list1.get(i));
                //4)
                XWPFRun run_Actual = step_para.createRun();
                run_Actual.setText("Actual Result: " + actions.Keywords.list1.get(i));
                run_Actual.addBreak();
                
       			//document.createParagraph().createRun().setText("Step Status: " + actions.Keywords.list.get(i));
                //5)
                XWPFRun run_Result = step_para.createRun();
                 run_Result.setText("Result: ");
                    XWPFRun run_Result_Value = step_para.createRun();
        	        if(actions.Keywords.list.get(i) == "Passed") {
        	        	run_Result_Value.setColor("008000");
        	        }else {
        	        	run_Result_Value.setColor("ff0000");
        	        }                    
                    run_Result_Value.setText(actions.Keywords.list.get(i));
                    run_Result_Value.addBreak();
                
       			String SSPaths = actions.Keywords.list2.get(i);
       			
       			if (!SSPaths.trim().equalsIgnoreCase("") && !SSPaths.trim().equalsIgnoreCase("NA")) {
       				String[] SSPathsSplit = SSPaths.trim().split(";", -1);
       				
       				for (int j = 0; j < SSPathsSplit.length; j++){
		       			String id = document.addPictureData(new FileInputStream(new File(SSPathsSplit[j])), Document.PICTURE_TYPE_PNG);        
		       	        document.createPicture(id,document.getNextPicNameNumber(Document.PICTURE_TYPE_PNG), 600, 400);
		       	        document.createParagraph();		       	        
       				}
       			}
       		}
       		
	    	document.write(fos);
	        fos.flush();
	        fos.close();
	        //DeleteSS();
	        writeexeclogtocsv(SSLocation);
	        return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void DeleteSS() throws SQLException, IOException, InterruptedException {
		//String SSQuery = "Select * from SQS_TEST_RESULT where SQS_TR_Run_Id = " + TestAttributes.Run_ID + " order by SQS_TR_Step_Id";
		
   		//ResultSet SSResults = TestAttributes.stmt.executeQuery(SSQuery);
		for (int i=0; i<actions.Keywords.list.size();i++ ){
   			String SSPaths = actions.Keywords.list2.get(i);
   			
   			if (!SSPaths.trim().equalsIgnoreCase("")) {
   				String[] SSPathsSplit = SSPaths.trim().split(";",-1);
   				
   				for (int j = 0; j < SSPathsSplit.length; j++) {   					
	       			Files.deleteIfExists(Paths.get(SSPathsSplit[j].trim()));
   				}
   			}
   		}
	}
	
    public SSInWord(InputStream in) throws IOException {
        super(in);
    }

    public void createPicture(String blipId,int id, int width, int height) throws XmlException {
        final int EMU = 9525;
        width *= EMU;
        height *= EMU;
        //String blipId = getAllPictures().get(id).getPackageRelationship().getId();

        CTInline inline = createParagraph().createRun().getCTR().addNewDrawing().addNewInline();

        String picXml = "" +
                "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">" +
                "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
                "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
                "         <pic:nvPicPr>" +
                "            <pic:cNvPr id=\"" + id + "\" name=\"Generated\"/>" +
                "            <pic:cNvPicPr/>" +
                "         </pic:nvPicPr>" +
                "         <pic:blipFill>" +
                "            <a:blip r:embed=\"" + blipId + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>" +
                "            <a:stretch>" +
                "               <a:fillRect/>" +
                "            </a:stretch>" +
                "         </pic:blipFill>" +
                "         <pic:spPr>" +
                "            <a:xfrm>" +
                "               <a:off x=\"0\" y=\"0\"/>" +
                "               <a:ext cx=\"" + width + "\" cy=\"" + height + "\"/>" +
                "            </a:xfrm>" +
                "            <a:prstGeom prst=\"rect\">" +
                "               <a:avLst/>" +
                "            </a:prstGeom>" +
                "         </pic:spPr>" +
                "      </pic:pic>" +
                "   </a:graphicData>" +
                "</a:graphic>";

        //CTGraphicalObjectData graphicData = inline.addNewGraphic().addNewGraphicData();
        XmlToken xmlToken = null;
        //try
       // {
            xmlToken = XmlToken.Factory.parse(picXml);
        //}
       // catch(XmlException xe)
        //{
            //xe.printStackTrace();
        //}
        inline.set(xmlToken);
        //graphicData.set(xmlToken);

        inline.setDistT(0);
        inline.setDistB(0);
        inline.setDistL(0);
        inline.setDistR(0);

        CTPositiveSize2D extent = inline.addNewExtent();
        extent.setCx(width);
        extent.setCy(height);

        CTNonVisualDrawingProps docPr = inline.addNewDocPr();
        docPr.setId(id);
        docPr.setName("Picture " + id);
        docPr.setDescr("Generated");
    }
	public static void writeexeclogtocsv(String sslocation) throws IOException{
	    FileWriter pw = new FileWriter(System.getProperty("user.dir") + "\\InputData\\" + "inputdata.csv",true);

	        pw.append(sslocation);
	        pw.append("\n");
	        pw.flush();
	        pw.close();
	}	
}
