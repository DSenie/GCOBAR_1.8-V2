package logiciel_etiq;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiWriteExcelFile {
	public String bdd = "C:\\GCOBAR\\IE.accdb";	
	private static gestion_imp imp=new gestion_imp();
	public static void main(String[] args) {
		try {
			Date d=new Date();
			String date_jour=d.getDay()+"_"+d.getMonth()+"_"+d.getYear();
			FileOutputStream fileOut = new FileOutputStream("C://GCOBAR//pdf//excel//savbdd"+date_jour+".xlsx");
			Workbook worksheet = new XSSFWorkbook();
			CreationHelper ch = worksheet.getCreationHelper();
			String safeName = WorkbookUtil.createSafeSheetName("Population");
			org.apache.poi.ss.usermodel.Sheet sheet = worksheet.createSheet(safeName);
			 
			// index from 0,0... cell A1 is cell(0,0)
			Row row1 = sheet.createRow((short)0);

			Cell cellA1 = row1.createCell((short) 0);
			cellA1.setCellValue("Code Article");
			CellStyle cellStyle = worksheet.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellA1.setCellStyle(cellStyle);
			
			
			Cell cellA2 = row1.createCell((short) 1);
			cellA2.setCellValue("Model produit");
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellA2.setCellStyle(cellStyle);
			
			
			Cell cellB1 = row1.createCell((short) 2);
			cellB1.setCellValue("Model TV");
			cellStyle = worksheet.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellB1.setCellStyle(cellStyle);

			
			Cell cellB2 = row1.createCell((short) 3);
			cellB2.setCellValue("Serial");
			cellStyle = worksheet.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellB2.setCellStyle(cellStyle);
			
			
			
			
			Cell cellB4 = row1.createCell((short) 4);
			cellB4.setCellValue("Software");
			cellStyle = worksheet.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellB4.setCellStyle(cellStyle);
		
			Cell cellB5 = row1.createCell((short) 5);
			cellB5.setCellValue("Fournisseur");
			cellStyle = worksheet.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellB5.setCellStyle(cellStyle);
			
			
			Cell cellB6 = row1.createCell((short) 6);
			cellB6.setCellValue("Chaine");
			cellStyle = worksheet.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellB6.setCellStyle(cellStyle);
			

			Cell cellB7 = row1.createCell((short) 7);
			cellB7.setCellValue("Date");
			cellStyle = worksheet.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellB7.setCellStyle(cellStyle);
			
			Cell cellB8 = row1.createCell((short) 8);
			cellB8.setCellValue("Carte M�re");
			cellStyle = worksheet.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellB8.setCellStyle(cellStyle);
			
			
			Cell cellB9 = row1.createCell((short) 9);
			cellB9.setCellValue("Mod�le carte M�re");
			cellStyle = worksheet.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellB9.setCellStyle(cellStyle);
			
			Cell cellB10 = row1.createCell((short) 10);
			cellB10.setCellValue("Dalle TV");
			cellStyle = worksheet.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellB10.setCellStyle(cellStyle);
			
			Cell cellB11 = row1.createCell((short) 11);
			cellB11.setCellValue("Mod�le Dalle");
			cellStyle = worksheet.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
			cellB11.setCellStyle(cellStyle);
			
			
			
			
			 
			
			  
			    ArrayList<String> liste =new  ArrayList<String>();
			    ArrayList<String> lis = new  ArrayList<String>();
			    lis.add("E1707212001");
			    lis.add("E1707212002");
			    System.out.println(lis.size());
			    for (int h = 0 ; h < lis.size() ;h++) {
			    System.out.println(imp.excel_select(lis.get(h).toString()));
			    liste.addAll(imp.excel_select(lis.get(h)));
			    }
			  
			    
			    
					int rowNum=1;
					int l = 0;
					int colon=0;
					int taille=liste.size()/lis.size();
					System.out.println("eee"+taille);
                    int k = 0,p;
                    String value;			
					for(int i=0; i<liste.size();i=i+taille){

						k++; p=0;
						Row row = sheet.createRow((short)k);

						for(int j=i; j<taille*k;j++){
							
			             value = liste.get(j);
				        row.createCell(p ).setCellValue(value);		
				        p++;
						}
						
						}

//					while(l<liste.size()){
//					 while(l<rowNum*taille){
//						  Row row = sheet.createRow((short)rowNum);
//						   for(l=1;l>=liste.size();l++){
//						    String value = liste.get(taille-l);
//					        row.createCell(colon ).setCellValue(value);
//						   }
//					        
//					        colon=colon+1;
// 
//						     l=l+taille;
//						// System.out.println(colon);
//					 }
//					 colon=0;
//					 rowNum=rowNum+1;		
//					}
					
//					
//			    int rowNum = 1;
//			    int j=1;
//			    
//			    while (rowNum<colNames.size()) {
//			    	
//			 //   List<String> fields = csv.next();
//			   
//			    System.out.println(rowNum+" "+colNames.size()+" "+j);
//			    Row row = sheet.createRow((short)rowNum);
//			   
//			    /* First 5 fields are text. The next 2 are floating
//			     * point. */
//			    
//			    System.out.println(colNames.get(rowNum));
//			    for (int i = 0 ; i < 5 ; i++) {
//			    	
//			        String value = colNames.get(j);
//			        row.createCell(i).setCellValue(value);
//			     j++;
//			    }
			 
//			    for (int i = 5 ; i < fields.size() ; i++) {
//			        /* Attempt to set as double. If that fails, set as
//			         * text. */
//			        try {
//			        double value = Double.parseDouble(fields.get(i));
//			        row.createCell(i).setCellValue(value);
//			        } catch(NumberFormatException ex) {
//			        String value = fields.get(i);
//			        row.createCell(i).setCellValue(value);
//			        }
//			    }
//			    rowNum=rowNum+5;
//
//			    }
			
			for (short i = sheet.getRow(0).getFirstCellNum(),
			     end = sheet.getRow(0).getLastCellNum() ; i < end ; i++) {
			    sheet.autoSizeColumn(i);
			}
			 
			worksheet.write(fileOut);
			fileOut.close();
			
			fileOut.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}



