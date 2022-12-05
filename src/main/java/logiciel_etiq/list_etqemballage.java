package logiciel_etiq;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class list_etqemballage extends JFrame {
	/**
	 * 
	 */
	ArrayList <String>list_fiche= new ArrayList<String>() ;
	

	ArrayList <String>list_rech= new ArrayList<String>() ;

	private static final long serialVersionUID = 1L;
	public String Chemin = "c:\\GCOBAR\\";
	public  String bdd = Chemin+Utilitaire.InitBdd()+".accdb";	
    Object [] entete={"Parcel","Code/Designation","modèle","couleur","delivery","G.W","N.W","Carton Size","commentaire","Date","Imei1","Imei2"};
    final Tableau tab=new Tableau(entete);
    String date_jour;
	 JMenuItem menuItem4 = new JMenuItem("Exporter en excel" );

    final JTextField filterText = new JTextField("Recherche");
//	static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	 JMenuItem menuItem = new JMenuItem("Imprimer etiquette" );
	 //JMenuItem menuItem4 = new JMenuItem("Exporter en excel" );
	  Thread att;
	// private JButton importer=new JButton("Importer Excel");

    // JMenuItem menuItem2 = new JMenuItem("Imprimer fiche" );
    final JPanel paneltext =new JPanel();
	final JPanel pan=new JPanel();
	final JPanel pan1=new JPanel();
	final JPanel pan2=new JPanel();
	final JPanel pan3=new JPanel();
    final JLabel rech = new JLabel("Recherche");
    JLabel count;
    private JPopupMenu popupMenu = new JPopupMenu();
    private int selectedRow=-1;
    JScrollPane p=new JScrollPane(tab);
    gestion_imp imp=new gestion_imp();
	int i=1;
	int j=1;
	int l = 0; 
	
	int i1=1;
	int j1=1;
	int l1 = 0; 
	  ArrayList<String> listrc = new ArrayList<String>();
	  ArrayList<String> listrc1 = new ArrayList<String>();
	  ArrayList<String> listfc = new ArrayList<String>();

	  ArrayList<String> list_emei = new ArrayList<String>();
     String parcel="";
	list_etqemballage(final String log) throws ParseException{
		final menu fr=new menu(log);
		fr.setVisible(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	   setIconImage(img);
	   selectioncomb.windows(this,log);

			
		    build();
				
			composant();}
	
		 
		
	    		 
	   public void composant() throws ParseException{
		   if(!selectioncomb.prv.contains("list_etqemballage")){
			//System.out.println("list_fiche"+selectioncomb.prv);
			 selectioncomb.prv.add("list_etqemballage");}
		   //selectioncomb.prv.add("list_fiche");
		   tab.allowEdition1=false;
		   tab.allowEdition2=false;
		   tab.allowEdition3=false;
		   tab.allowEdition4=false;
		   tab.allowEdition5=false;
		   tab.allowEdition6=false;
		   tab.allowEdition7=false;
		   tab.allowEdition8=false;
		   tab.allowEdition9=false;

		   filterText.setForeground(Color.gray); 		 
		   tab.setStyle(2);

		   filterText.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//parcel=imp.select_emei_table(filterText.getText());
					//System.out.println("hhhhh "+parcel);
					int rows = tab.table.getRowCount();
					
					for (int i = rows - 1; i >= 0; i--) {
						((DefaultTableModel) tab.table.getModel()).removeRow(i);
					}
					
					
					if(filterText.getText().trim().equals("")){		
						list_rech=imp.select_etqemballage();
                       }
					else{try {
						list_rech=imp.select_emei_table(filterText.getText());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
					    for(int i1=0;i1<list_rech.size();i1=i1+13){ tab.ajouter();}
						int j1=1;
						int l1 = 0; i1=1;
						while(l1<list_rech.size()){
						 while(l1<j1*13){
							 tab.getTable().setValueAt(list_rech.get(l1).toLowerCase(), j1-1, 0);
							 tab.getTable().setValueAt(list_rech.get(l1+1).toLowerCase()+" "+list_rech.get(l1+2).toLowerCase(), j1-1, 1);
							 if(list_rech.get(l+10)!=null)
							 tab.getTable().setValueAt(list_rech.get(l1+3).toLowerCase(), j1-1, 2);
							 tab.getTable().setValueAt(list_rech.get(l1+4).toLowerCase(), j1-1, 3);
		             		 tab.getTable().setValueAt(list_rech.get(l1+5).toLowerCase(), j1-1,4);
		        			 tab.getTable().setValueAt(list_rech.get(l1+6).toLowerCase(), j1-1,5);
		        			 tab.getTable().setValueAt(list_rech.get(l1+7).toLowerCase(), j1-1,6);
		        			 tab.getTable().setValueAt(list_rech.get(l1+8).toLowerCase(), j1-1,7);
		        			 if(list_rech.get(l1+9)!=null)
		        			 tab.getTable().setValueAt(list_rech.get(l1+9).toLowerCase(), j1-1,8);
		        			 
		        			 if(list_rech.get(l+10)!=null){

		         				String date_s = list_rech.get(l+10).toLowerCase(); 
		         				System.out.println(date_s);
		                   		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
		                   		 String date_i;
								try {
									Date date = dt.parse(date_s);
									SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
			                   		 date_i=dt1.format(date);
			                   		System.out.println(date_i);
			                   		 tab.getTable().setValueAt(date_i, j1-1,9);

								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
		         			 }

		                     tab.getTable().setValueAt(list_rech.get(l1+11).toLowerCase(), j1-1,10);
		        			 tab.getTable().setValueAt(list_rech.get(l1+12).toLowerCase(), j1-1,11);
		           			 i1=i1+1; l1=l1+13;
						 }
						 i1=1;
						j1=j1+1;		
						}
						
						count.setText("Le Nombre Des Fiches :  "+tab.table.getRowCount());
				}});
		   
			 JPanel panel =new JPanel() {   
		  		/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				public void paintComponent(Graphics g){
		  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
		             img.paintIcon(this, g,0, 0);
		             } 
				} ;

		  	  	
//			  filterText.addKeyListener(new KeyAdapter() {
//			      public void keyReleased(KeyEvent e) {
//			    	  String text = filterText.getText();
//	                  if (text.length() == 0) {
//	                  tab.sorter.setRowFilter(null);
//	                  } else {
//	                	  if(!parcel.equals("")){
//	                		  tab.sorter.setRowFilter(RowFilter.regexFilter(parcel));
//	  					}
//	                	  else{
//	                  tab.sorter.setRowFilter(RowFilter.regexFilter(text.toLowerCase()));
//	                	  }
//	                  }
//			      }
//
//			      public void keyPressed(KeyEvent e) {
//			    	  
//			      } });
//
			  filterText.addMouseListener( 
					  new  MouseAdapter(){
						  public void mousePressed(MouseEvent e) {
							    // TODO Auto-generated method stub
								 filterText.setText("");
								 filterText.setForeground(Color.gray); 
							}     
					  });
//			  
			  filterText.addFocusListener(
					  new FocusListener() {

				    @Override
				    public void focusGained(FocusEvent e) { filterText.setForeground(Color.black); 

				    }

				    @Override
				    public void focusLost(FocusEvent e) {
						 filterText.setText("Recherche");
						 filterText.setForeground(Color.gray); 

				        // You could do something here when the field loses focus, if you like
				    }

				});

			  
			
			  list_fiche=imp.select_etqemballage();
			    for(i=0;i<list_fiche.size();i=i+13){tab.ajouter();}
				int j=1;
				int l = 0; i=1;
				while(l<list_fiche.size()){
				
				 while(l<j*13){
					 tab.getTable().setValueAt(list_fiche.get(l).toLowerCase(), j-1, 0);

					 tab.getTable().setValueAt(list_fiche.get(l+1).toLowerCase()+" "+list_fiche.get(l+2).toLowerCase(), j-1, 1);
					 tab.getTable().setValueAt(list_fiche.get(l+3).toLowerCase(), j-1, 2);
					 tab.getTable().setValueAt(list_fiche.get(l+4).toLowerCase(), j-1, 3);
             		 tab.getTable().setValueAt(list_fiche.get(l+5).toLowerCase(), j-1,4);
        			 tab.getTable().setValueAt(list_fiche.get(l+6).toLowerCase(), j-1,5);
        			 
        			 tab.getTable().setValueAt(list_fiche.get(l+7).toLowerCase(), j-1,6);
        			 tab.getTable().setValueAt(list_fiche.get(l+8).toLowerCase(), j-1,7);
        			 if(list_fiche.get(l+9)!=null)
        			 tab.getTable().setValueAt(list_fiche.get(l+9).toLowerCase(), j-1,8);
        			 if(list_fiche.get(l+10)!=null){
        				String date_s = list_fiche.get(l+10).toLowerCase(); 
                  		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
                  		Date date = dt.parse(date_s); 
                  		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
                  		String date_i=dt1.format(date);
                  		 tab.getTable().setValueAt(date_i, j-1,9);
        			 
        			 }
                     tab.getTable().setValueAt(list_fiche.get(l+11).toLowerCase(), j-1,10);
        			 tab.getTable().setValueAt(list_fiche.get(l+12).toLowerCase(), j-1,11);

           			 i=i+1; l=l+13;
				 }
				 i=1;
				j=j+1;		
				}


		   generale.styles("Nimbus");
		   SwingUtilities.updateComponentTreeUI(this);
				  
				  
					
				   Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);

				    Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
				   tab.table.setFont(police_fi);
	
			
		    count = new JLabel("Le Nombre Des Fiches :  "+tab.table.getRowCount());
		    count.setForeground(Color.white); 
	 	    count.setBackground(new Color(6,119,144));
	 	    count.setOpaque(true);
	 	    count.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	 	    count.setFont(police2);

		    panel.add(paneltext);
			panel.add(p); 
			panel.add(pan);
			
			paneltext.add(pan2);
			paneltext.add(pan3);

			pan2.add(filterText);
			
			pan.add(count);
			//pan.add(importer);

			pan.add(pan1);
		    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		    pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
		    paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));

		    filterText.setPreferredSize(new Dimension(230,30));
		    p.setPreferredSize(new Dimension(700,700));

		    paneltext.setPreferredSize(new Dimension(250, 150));

		    panel.setOpaque(false);
		    pan.setOpaque(false);
		    paneltext.setOpaque(false);
		    pan1.setOpaque(false);
		    pan2.setOpaque(false);
		    pan3.setOpaque(false); 
		    
		   // pan2.setLayout(null);
		  //  filterText.setBounds(00, 30, 150,30);
		    
		    pan2.setLayout(new FlowLayout(FlowLayout.LEFT));
		 			pan3.setLayout(new FlowLayout(FlowLayout.LEFT));
		 			pan2.setBorder(BorderFactory.createEmptyBorder(0, 90, 0, 0));
		 			pan3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		 			
		 			
		    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		    pan1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

			     
				  setTitle("List des Fiches" );
		          setSize(1000, 600);
		          setLocationRelativeTo(null);          
		          setVisible(true);
		          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			      setContentPane(panel);
			    
		   
	   } 		 
	   
	   private void build() {
			
		     tab.table.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mouseReleased(MouseEvent e) {
		       if(e.getButton()==MouseEvent.BUTTON3){
		    	 
		    	   //String[] data = tab.table.getValueAt(row, col);
		    	 
		        showPopup(e);
		       }
		      }
		     });
		    


		     popupMenu.add(menuItem);
		  //   popupMenu.add(menuItem2);
		
		     
		     
		     
		     menuItem.addActionListener(
	                  new ActionListener() { 
	                  public void actionPerformed(ActionEvent e) {
	                	  listrc.clear();
						  BufferedReader bfr = null ;
	  	                int[] selection = tab.table.getSelectedRows() ;
	                	  int z =selection.length;
		   		    	    for (int i=0; i<z; i++) {
		   		    		if(!listrc.contains((String) tab.table.getValueAt(selection[i], 0))){
		   		    			String parcel=tab.table.getValueAt(selection[i], 0).toString().replace("/", "_");
		   		    	    listrc.add(parcel);
		   		    	     listrc1.add((String) tab.table.getValueAt(selection[i], 0));
		   		    		}
//		   		    		if(!listrc1.contains((String) tab.table.getValueAt(selection[i], 0))){
//		   		    			
//		   		    	    listrc1.add((String) tab.table.getValueAt(selection[i], 0));
//		   		    	    
//		   		    		}
		   		    	   }
	                	  if(selectedRow!=-1){
	                		  String parcour= "C:\\GCOBAR\\pdf\\etq_emballage\\"+listrc+"etq_emballage.pdf";
								String model ="C:\\GCOBAR\\CODE\\etiquette_emballage_list.jrxml";
							  File fichier = new File(parcour);
							  fichier.delete();

							  try {

								  //
								  bfr=    new BufferedReader(new FileReader(parcour));
								  bfr.close();
								  System.out.println("buffer");

								  try {
									  Desktop.getDesktop().open(new File(parcour));
									  System.out.println("desk");

								  } catch (IOException p) {
									  // TODO Auto-generated catch block
									  p.printStackTrace();
								  }}

							catch (FileNotFoundException fnfe) {
	                				
	                				
	                	  selectioncomb.imprimer(listrc1,bdd,parcour,model);
	                	  listrc1.clear(); 

	
	                	  
	                  } catch (IOException e1) {
								  e1.printStackTrace();
							  }
						  }
	                  }});
		     
		     
		     
		     popupMenu.add(menuItem4);
		     menuItem4.addActionListener(
	                  new ActionListener() { 
	                  public void actionPerformed(ActionEvent e) {
	                	   att = new Thread(){
	  	 					public void run(){
	  	 						int reponse = JOptionPane.showConfirmDialog(
	  	 						        null, "Operation terminée. Voulez-vous ouvrir le fichier Excel ?",
	  	 						        "Confirmation",
	  	 						        JOptionPane.YES_NO_OPTION,
	  	 						        JOptionPane.QUESTION_MESSAGE);
	  	 						    	if (reponse==JOptionPane.YES_OPTION){
		  					try {
		  						Date actuelle=new Date();
			 	                  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			 					   date_jour = dateFormat.format(actuelle);
			 					  
			 	          			System.out.println(date_jour);
			 	          			String parcour="C://GCOBAR//pdf//excel//tpe_emballage"+date_jour+".xlsx";
			 	          			FileOutputStream fileOut = new FileOutputStream(parcour);
			 	          			Workbook worksheet = new XSSFWorkbook();
			 	          			CreationHelper ch = worksheet.getCreationHelper();
			 	          			String safeName = WorkbookUtil.createSafeSheetName("Liste Produit");
			 	          			org.apache.poi.ss.usermodel.Sheet sheet = worksheet.createSheet(safeName);
			 	          			 
			 	          			File fichier = new File(parcour);
			 	 					    fichier.delete();
		 	 					    
		 	          			// index from 0,0... cell A1 is cell(0,0)
		 	          			Row row1 = sheet.createRow((short)0);

		 	          			Cell cellA1 = row1.createCell((short) 0);
		 	        			cellA1.setCellValue("Parcel");
		 	        			CellStyle cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellA1.setCellStyle(cellStyle);
		 	        			
		 	        			
		 	        			Cell cellA2 = row1.createCell((short) 1);
		 	        			cellA2.setCellValue("Code Article");
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellA2.setCellStyle(cellStyle);
		 	        			

		 	        			Cell cellB2 = row1.createCell((short) 2);
		 	        			cellB2.setCellValue("Designation");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB2.setCellStyle(cellStyle);
		 	        			
		 	        			Cell cellB4 = row1.createCell((short) 3);
		 	        			cellB4.setCellValue("Modèle");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB4.setCellStyle(cellStyle);
		 	        			
		 	        			Cell cellB5 = row1.createCell((short) 4);
		 	        			cellB5.setCellValue("Couleur");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB5.setCellStyle(cellStyle);
		 	        		
		 	        			
		 	        			Cell cellB6 = row1.createCell((short) 5);
		 	        			cellB6.setCellValue("Delivery");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB6.setCellStyle(cellStyle); 
		 	        			
		 	        			
		 	        			Cell cellB7 = row1.createCell((short) 6);
		 	        			cellB7.setCellValue("GW");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB7.setCellStyle(cellStyle);
		 	        			
		 	        			
		 	        			Cell cellB8 = row1.createCell((short) 7);
		 	        			cellB8.setCellValue("NW");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB8.setCellStyle(cellStyle);
		 	        			
		 	        			
		 	        			Cell cellB9 = row1.createCell((short) 8);
		 	        			cellB9.setCellValue("Carton Size");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB9.setCellStyle(cellStyle);
		 	        		
		 	        			
		 	        			Cell cellB10 = row1.createCell((short) 9);
		 	        			cellB10.setCellValue("Commentaire");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB10.setCellStyle(cellStyle);
		 	        			
		 	        			
		 	        			Cell cellB11 = row1.createCell((short) 10);
		 	        			cellB11.setCellValue("Date");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB11.setCellStyle(cellStyle);
		 	        			
		 	        			
		 	        			Cell cellB12 = row1.createCell((short) 11);
		 	        			cellB12.setCellValue("IMEI1");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB12.setCellStyle(cellStyle);
		 	        			
		 	        			
		 	        			Cell cellB13 = row1.createCell((short) 12);
		 	        			cellB13.setCellValue("IMEI2");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB13.setCellStyle(cellStyle);
		 	        			
		 	        			
		 	        			
		 	        			Workbook wb = new HSSFWorkbook();
		 	        		    CreationHelper createhelper = wb.getCreationHelper();
		 	        		    Sheet sheet1 = wb.createSheet("new sheet");
		 	        		    Row row = null;
		 	        		    Cell cell = null;
		 	        		    
 	        		            
 	        		           int[] selection = tab.table.getSelectedRows() ;
 		                	  int z =selection.length;
 		                	   
 		                	  
 		                	 for (int i=0; i<z; i++) {
 		                	   row = sheet.createRow(i+1);
 		                		for (int j=0;j<=tab.table.getColumnCount();j++) {
 		                			cell = row.createCell(j);
 		                			
 		                			 String[] part_art = tab.table.getValueAt(selection[i], 1).toString().split(" ");
		          	           		 String part_art1 = part_art[0]; 
		          	           		  
		          	                  String part_art2 = tab.table.getValueAt(selection[i], 1).toString().replaceAll(part_art1+" ", "");

 		                			if(j==1){		       
 		          	                    cell.setCellValue((String) part_art1);
 		                			}
 		                			else if(j==2){
 		                				
 		                				cell.setCellValue((String) part_art2);
 		                			}
                                    else if(j==0){
 		                			  cell.setCellValue((String) tab.table.getValueAt(selection[i], 0));
 		                			}
 		                			else{
 		                			cell.setCellValue((String) tab.table.getValueAt(selection[i], j-1));
 		                			}
 		                		}
 			   		    	   }

 		                	sheet.autoSizeColumn(1); 
 		                	sheet.autoSizeColumn(2);
 		                	sheet.autoSizeColumn(3);
 		                	sheet.autoSizeColumn(4);
 		                	sheet.autoSizeColumn(5);
 		                	sheet.autoSizeColumn(6);
 		                	sheet.autoSizeColumn(7);
 		                	sheet.autoSizeColumn(8);
 		                	sheet.autoSizeColumn(9);
 		                	sheet.autoSizeColumn(10);
 		                	sheet.autoSizeColumn(11);
 		                	sheet.autoSizeColumn(12);

	 	        			sheet.autoSizeColumn(0); 
		 	        		worksheet.write(fileOut);
	 			          	fileOut.close();
	 			          	fileOut.flush();
	  					    BufferedReader bfr=	new BufferedReader(new FileReader("C://GCOBAR//pdf//excel//tpe_emballage"+date_jour+".xlsx"));
	  					     bfr.close();		
	  						 					try {
	  		            	 Desktop.getDesktop().open(new File( "C://GCOBAR//pdf//excel//tpe_emballage"+date_jour+".xlsx"));
	  						                  
	  						 					} catch (IOException p1) {
	  						 						
	  						 						//p.printStackTrace();
	  						 						
	  						 					}
	  						 					
	  					}
	  						 				 catch (IOException fnfe) {
	  						 				
	  						 			 try {
											Desktop.getDesktop().open(new File( "C://GCOBAR//pdf//excel//tpe_emballage"+date_jour+".xlsx"));
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
	  						 					 //controlPanel.remove(progressBar);
	  					                } 
	  					
		  					
		  					
	          		
	  	 						    	}

	  	 					}}; 
		 					
		 					att.start();
		 					
		 			
	                	   
	                	  
	                  }});
		     
		     
		     
//		     menuItem2.addActionListener(
//	                  new ActionListener() { 
//	                  public void actionPerformed(ActionEvent e) {
//	                	  String parcour= "C:\\GCOBAR\\CODE\\fichesuiveuse"+tab.getTable().getValueAt(selectedRow, 4)+".pdf";
//							String model ="C:\\GCOBAR\\CODE\\report1.jrxml";
//							   int[] selection = tab.table.getSelectedRows() ;
//			                	  int z =selection.length;
//				   		    	    for (int i=0; i<z; i++) {
//				   		    		if(!listfc.contains((String) tab.table.getValueAt(selection[i], 4)))
//				   		    	    listfc.add((String) tab.table.getValueAt(selection[i], 4));
//				   		    	   }
//	                	  if(selectedRow!=-1){
//	                		  
//	                		  try{
//	                			new BufferedReader(new FileReader("C:\\GCOBAR\\CODE\\fichesuiveuse"+tab.getTable().getValueAt(selectedRow, 4)+".pdf"));
//	                			  try {
//	      	                		Desktop.getDesktop().open(new File("C:\\GCOBAR\\CODE\\fichesuiveuse"+tab.getTable().getValueAt(selectedRow, 4)+".pdf"));
//	      	                		} catch (IOException s) {
//	      	                		// TODO Auto-generated catch block
//	      	                		s.printStackTrace();
//	      	                		}
//	                			} catch (FileNotFoundException fnfe) {
//		                	        selectioncomb.imprimer(listfc,bdd,parcour,model);
//
//	      	                	//  selectioncomb.imprimer(tab.getTable().getValueAt(selectedRow,4), bdd,parcour,model);
//
//	                			   //si le fichier n'existe pas tu arrives ici.
//	                			
////	                	  try{	
////	                		  // JasperReport jasperReport = (JasperReport) JRLoader.loadObject("C:\\GCOBAR\\CODE\\reportC.jrxml");
////	                								 JasperDesign jasperDesign = JRXmlLoader.load("C:\\GCOBAR\\CODE\\report1.jrxml");
////	                								 JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
////
////	                		// - Param�tres � envoyer au rapport
////	                		Map parameters = new HashMap();
////	                		// parameters.put("2200000004");
////	                		parameters.put("serial",tab.getTable().getValueAt(selectedRow, 4));
////
////	                		// - Execution du rapport
////	                		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, CConnect.getInstance(bdd));
////
////	                		// - Cr�ation du rapport au format PDF
////	                		JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\GCOBAR\\CODE\\fichesuiveuse"+tab.getTable().getValueAt(selectedRow, 4)+".pdf");
////	                		} catch (JRException s) {
////	                		// TODO Auto-generated catch block
////	                		s.printStackTrace();
////	                		}
////
////	                			}
//	                		
//	                  }}}             
//	                  });

		    }


		    
	   
	   
	   private void showPopup(MouseEvent e) {
		     if (e.isPopupTrigger()) {
		      Point p = new Point(e.getX(), e.getY());
		      tab.table.columnAtPoint(p);
		      selectedRow = tab.table.rowAtPoint(p);
		      popupMenu.show(e.getComponent(), e.getX(), e.getY());
		      }
		    }
		  
		    
		  
		    
		    
	 public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						 new list_etqemballage("4");
						//frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}

