package logiciel_etiq;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
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
import javax.swing.JComboBox;
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


public class list_etq_emei extends JFrame {
	/**
	 * 
	 */
	ArrayList <String>list_fiche= new ArrayList<String>() ;
	private static final long serialVersionUID = 1L;
	public String Chemin = "c:\\GCOBAR\\";
	public  String bdd = Chemin+Utilitaire.InitBdd()+".accdb";	
    Object [] entete={"Model","couleur","Imei1","Imei2","Date"};
    final Tableau tab=new Tableau(entete);

    final JTextField filterText = new JTextField("Recherche");
    
	private JComboBox dimension_comb= new JComboBox();

    
    
	static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	 JMenuItem menuItem = new JMenuItem("Imprimer etiquette " );
	 //JMenuItem menuItem2 = new JMenuItem("Imprimer etiquette 3 Imei" );
	// JMenuItem menuItem3 = new JMenuItem("Imprimer etiquette (50*25)" );

	 String date_jour;
	 JMenuItem menuItem4 = new JMenuItem("Exporter en excel" );

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
	
	String report;
	int l = 0; 
	  ArrayList<String> listrc = new ArrayList<String>();
	  
	  ArrayList<String> listrarpt = new ArrayList<String>();
	  ArrayList <String>list_dimension= new ArrayList<String>() ;

	  ArrayList<String> listrc1 = new ArrayList<String>();
	  ArrayList<String> listfc = new ArrayList<String>();

	list_etq_emei(final String log) throws ParseException{
		final menu fr=new menu(log);
		fr.setVisible(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	   setIconImage(img);
	   selectioncomb.windows(this,log,laf);

			
		    build();
				
			composant();}
	
		 
		
	    		 
	   public void composant() throws ParseException{
		   if(!selectioncomb.prv.contains("list_portable")){
			//System.out.println("list_fiche"+selectioncomb.prv);
			 selectioncomb.prv.add("list_portable");}
		   //selectioncomb.prv.add("list_fiche");
		   tab.allowEdition1=false;
		   tab.allowEdition2=false;
		   tab.allowEdition3=false;
		   tab.allowEdition4=false;
		   tab.allowEdition5=false;
		   tab.allowEdition6=false;
		   filterText.setForeground(Color.gray); 		 
		   tab.setStyle(2);
		   
		   
		   dimension_comb.addItem("--- Sélectionner la dimension de l'etiquette ----");

			list_dimension=imp.select_dimension_etq("etq_portable_forn");
		 	  
			   for(int i=0;i<list_dimension.size();i++)
			   {
			          //Pour affecter une valeur de base de données é un Combobox
				   dimension_comb.addItem(list_dimension.get(i));
				   
			   }
			   

			 JPanel panel =new JPanel() {   
		  		/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				public void paintComponent(Graphics g){
		  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
		             img.paintIcon(this, g,0, 0);
		             }  } ;

		  	  	
			  filterText.addKeyListener(new KeyAdapter() {
			      public void keyReleased(KeyEvent e) {
			    	  String text = filterText.getText();
	                  if (text.length() == 0) {
	                  tab.sorter.setRowFilter(null);
	                  } else {
	                   tab.sorter.setRowFilter(RowFilter.regexFilter(text.toLowerCase()));
	                  }
	                  count.setText("Le Nombre Des Fiches :  "+tab.table.getRowCount());
	                  
			      }

			      public void keyPressed(KeyEvent e) {
			    	  
			      } });

			  filterText.addMouseListener( 
					  new  MouseAdapter(){
						  public void mousePressed(MouseEvent e) {
							    // TODO Auto-generated method stub
								 filterText.setText("");
								 filterText.setForeground(Color.gray); 
							}     
					  });
			  
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

			  
			
			  imp.select_portable();
			  list_fiche=imp.select_imprim_fourn();
			  System.out.println(list_fiche);
			    for(i=0;i<list_fiche.size();i=i+5){tab.ajouter();}
				int j=1;
				int l = 0; i=1;
				while(l<list_fiche.size()){
				
				 while(l<j*5){
					
					 tab.getTable().setValueAt(list_fiche.get(l).toLowerCase(), j-1, 0);
					 tab.getTable().setValueAt(list_fiche.get(l+1).toLowerCase(), j-1, 1);
					 tab.getTable().setValueAt(list_fiche.get(l+2).toLowerCase(), j-1, 2);
             		 tab.getTable().setValueAt(list_fiche.get(l+3).toLowerCase(), j-1,3);
             		 
              		String date_s = list_fiche.get(l+4).toLowerCase(); 
              		String date_i="";
              		
             		if(date_s==null){
             		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss"); 
             		Date date = dt.parse(date_s); 
             		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
             		date_i=dt1.format(date);
             		}
             		 tab.getTable().setValueAt(date_i, j-1,4);

           			 i=i+1; l=l+5;
				 }
				 i=1;
				j=j+1;		
				}
		

				  try{
				   		UIManager.setLookAndFeel(laf);
				   		SwingUtilities.updateComponentTreeUI(this);
				   	}
				     catch(Exception e1){
				   		e1.printStackTrace();
				   	}
				  
				  
					
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
			pan3.add(dimension_comb);
			pan.add(count);
			//pan.add(importer);

			pan.add(pan1);
		 

		    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		    pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
		    paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));

		    dimension_comb.setPreferredSize(new Dimension(230,30));
		    filterText.setPreferredSize(new Dimension(230,30));

		    p.setPreferredSize(new Dimension(700,700));

		   // paneltext.setPreferredSize(new Dimension(40, 150));

		    panel.setOpaque(false);
		    pan.setOpaque(false);
		    paneltext.setOpaque(false);
		    pan1.setOpaque(false);
		    pan2.setOpaque(false);
		    pan3.setOpaque(false); 
		    
		    pan2.setLayout(new FlowLayout(FlowLayout.CENTER));
			pan3.setLayout(new FlowLayout(FlowLayout.LEFT));
			pan2.setBorder(BorderFactory.createEmptyBorder(0, 90, 0, 0));
			pan3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		    pan1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

			     
				  setTitle("List des Etiquette Fournisseur" );
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
		    

		     dimension_comb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if(dimension_comb.getSelectedIndex()!=0)
						report=imp.select_report(dimension_comb.getSelectedItem().toString(),"etq_portable_forn");
						
						
					}});
		     
		     
		     popupMenu.add(menuItem);
		     //popupMenu.add(menuItem2);
		  //   popupMenu.add(menuItem3);
		     
		     
		     
		     menuItem.addActionListener(
	                  new ActionListener() { 
	                  public void actionPerformed(ActionEvent e) {
	                	  if(dimension_comb.getSelectedIndex()!=0){
	  	                int[] selection = tab.table.getSelectedRows() ;
	                	  int z =selection.length;
		   		    	    for (int i=0; i<z; i++) {
		   		    		if(!listrc.contains((String) tab.table.getValueAt(selection[i], 2)))
		   		    	    listrc.add((String) tab.table.getValueAt(selection[i], 2));
		   		    	   }
	                	  if(selectedRow!=-1){
	                		  String parcour= "C:\\GCOBAR\\pdf\\etq_portable_fournisseur\\"+dimension_comb.getSelectedItem()+listrc.get(0)+"_"+listrc.get(z-1)+"_"+z+"_"+"etq_fournisseur.pdf";
								String model ="C:\\GCOBAR\\CODE\\"+report;
	                		  try{

                            new BufferedReader(new FileReader("C:\\GCOBAR\\pdf\\etq_portable_fournisseur\\"+dimension_comb.getSelectedItem()+listrc.get(0)+"_"+listrc.get(z-1)+"_"+z+"_"+"etq_fournisseur.pdf"));
	                			  try {
	    	                		  Desktop.getDesktop().open(new File("C:\\GCOBAR\\pdf\\etq_portable_fournisseur\\"+dimension_comb.getSelectedItem()+listrc.get(0)+"_"+listrc.get(z-1)+"_"+z+"_"+"etq_fournisseur.pdf"));

	    	                		  //cop(new File(bdd),new File(save.getSelectedFile().getPath().replace(".accdb", ".naw")));
	    	                		  //cop(new File("C:\\GEFACT\\factpro\\"+code_jtext.getText().replace("/", "")+".pdf"),new File("C:\\GCOBAR\\CODE BARRE\\"+code_jtext.getText().replace("/", "")+" "+date+".fpc"));
	    	                		  } catch (IOException p) {
	    	                		  // TODO Auto-generated catch block
	    	                		  p.printStackTrace();
	    	                		  }
	                			} catch (FileNotFoundException fnfe) {
	                	  selectioncomb.imprimer("imei1",listrc,bdd,parcour,model);
	                	  listrc.clear(); 

	
	                	  
	                  } } }
	                	  else{
	                		  
	                		  JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette");
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
			 	          			String parcour="C://GCOBAR//pdf//excel//phone_fournisseur"+date_jour+".xlsx";
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
		 	        			cellA1.setCellValue("Modèle");
		 	        			CellStyle cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellA1.setCellStyle(cellStyle);
		 	        			
		 	        			
		 	        			Cell cellA2 = row1.createCell((short) 1);
		 	        			cellA2.setCellValue("Couleur");
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellA2.setCellStyle(cellStyle);
		 	        			

		 	        			Cell cellB2 = row1.createCell((short) 2);
		 	        			cellB2.setCellValue("IMEI1");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		 	        			cellB2.setCellStyle(cellStyle);
		 	        			
		 	        			
		 	        			Cell cellB4 = row1.createCell((short) 3);
		 	        			cellB4.setCellValue("IMEI2");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB4.setCellStyle(cellStyle);
		 	        		
		 	        			
		 	        			
		 	        			Cell cellB5 = row1.createCell((short) 4);
		 	        			cellB5.setCellValue("Date");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB5.setCellStyle(cellStyle);
		 	        		
		 	        			
		 	        			
		 	        	
		 	        			
		 	        			
		 	        			
		 	        			Workbook wb = new HSSFWorkbook();
		 	        		    CreationHelper createhelper = wb.getCreationHelper();
		 	        		    Sheet sheet1 = wb.createSheet("new sheet");
		 	        		    Row row = null;
		 	        		    Cell cell = null;
		 	        		    
 	        		            
 	        		           int[] selection = tab.table.getSelectedRows() ;
 		                	  int z =selection.length;
 		                	  
 		                	  
 		                	 for (int i=0; i<z; i++) {
 		                	   row = sheet.createRow(i+1);
 		                		for (int j=0;j<tab.table.getColumnCount();j++) {
 		                			cell = row.createCell(j);
 		                			
 		             
 		                			cell.setCellValue((String) tab.table.getValueAt(selection[i], j));
 		                			}
 		                		}
 			   		    	   

 		                	sheet.autoSizeColumn(1); 
 		                	sheet.autoSizeColumn(2);
 		                	sheet.autoSizeColumn(3);
 		                	sheet.autoSizeColumn(4);

 		                	sheet.autoSizeColumn(0); 
		 	        		worksheet.write(fileOut);
	 			          	fileOut.close();
	 			          	fileOut.flush();
	  					    BufferedReader bfr=	new BufferedReader(new FileReader("C://GCOBAR//pdf//excel//phone_fournisseur"+date_jour+".xlsx"));
	  					     bfr.close();		
	  						 					try {
	  		            	 Desktop.getDesktop().open(new File( "C://GCOBAR//pdf//excel//phone_fournisseur"+date_jour+".xlsx"));
	  						                  
	  						 					} catch (IOException p1) {
	  						 						
	  						 						//p.printStackTrace();
	  						 						
	  						 					}
	  						 					
	  					}
	  						 				 catch (IOException fnfe) {
	  						 				
	  						 			 try {
											Desktop.getDesktop().open(new File( "C://GCOBAR//pdf//excel//phone_fournisseur"+date_jour+".xlsx"));
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
//	  	                int[] selection = tab.table.getSelectedRows() ;
//	                	  int z =selection.length;
//		   		    	    for (int i=0; i<z; i++) {
//		   		    		if(!listrarpt.contains((String) tab.table.getValueAt(selection[i], 2)))
//		   		    			listrarpt.add((String) tab.table.getValueAt(selection[i], 2));
//		   		    	   }
//		   		    	 
//	                	  if(selectedRow!=-1){
//	                		  String parcour= "C:\\GCOBAR\\pdf\\etq_portable_fournisseur\\"+listrarpt.get(0)+"_"+listrarpt.get(z-1)+"_"+z+"_"+"etq_fournisseur_3imei.pdf";
//								String model ="C:\\GCOBAR\\CODE\\etiquette_fournisseur_3imei.jrxml";
//	                		  try{
//
//                           new BufferedReader(new FileReader("C:\\GCOBAR\\pdf\\etq_portable_fournisseur\\"+listrarpt.get(0)+"_"+listrarpt.get(z-1)+"_"+z+"_"+"etq_fournisseur_3imei.pdf"));
//	                			  try {
//	    	                		  Desktop.getDesktop().open(new File("C:\\GCOBAR\\pdf\\etq_portable_fournisseur\\"+listrarpt.get(0)+"_"+listrarpt.get(z-1)+"_"+z+"_"+"etq_fournisseur_3imei.pdf"));
//
//	    	                		  //cop(new File(bdd),new File(save.getSelectedFile().getPath().replace(".accdb", ".naw")));
//	    	                		  //cop(new File("C:\\GEFACT\\factpro\\"+code_jtext.getText().replace("/", "")+".pdf"),new File("C:\\GCOBAR\\CODE BARRE\\"+code_jtext.getText().replace("/", "")+" "+date+".fpc"));
//	    	                		  } catch (IOException p) {
//	    	                		  // TODO Auto-generated catch block
//	    	                		  p.printStackTrace();
//	    	                		  }
//	                			} catch (FileNotFoundException fnfe) {
//	                				   System.out.println(listrarpt);
//	                	  selectioncomb.imprimer(listrarpt,bdd,parcour,model);
//	                	  listrarpt.clear(); 
//
//	
//	                	  
//	                  } }            
//	                  }});
//		     
//		     
//		     menuItem3.addActionListener(
//	                  new ActionListener() { 
//	                  public void actionPerformed(ActionEvent e) {
//	  	                int[] selection = tab.table.getSelectedRows() ;
//	                	  int z =selection.length;
//		   		    	    for (int i=0; i<z; i++) {
//		   		    		if(!listrc.contains((String) tab.table.getValueAt(selection[i], 2)))
//		   		    	    listrc.add((String) tab.table.getValueAt(selection[i], 2));
//		   		    	   }
//	                	  if(selectedRow!=-1){
//	                		  String parcour= "C:\\GCOBAR\\pdf\\etq_portable_fournisseur\\"+listrc.get(0)+"_"+listrc.get(z-1)+"_"+z+"_"+"etq_fournisseur(50x25).pdf";
//								String model ="C:\\GCOBAR\\CODE\\etiquette_fournisseur_list50.jrxml";
//	                		  try{
//
//                           new BufferedReader(new FileReader("C:\\GCOBAR\\pdf\\etq_portable_fournisseur\\"+listrc.get(0)+"_"+listrc.get(z-1)+"_"+z+"_"+"etq_fournisseur(50x25).pdf"));
//	                			  try {
//	    	                		  Desktop.getDesktop().open(new File("C:\\GCOBAR\\pdf\\etq_portable_fournisseur\\"+listrc.get(0)+"_"+listrc.get(z-1)+"_"+z+"_"+"etq_fournisseur(50x25).pdf"));
//
//	    	                		  //cop(new File(bdd),new File(save.getSelectedFile().getPath().replace(".accdb", ".naw")));
//	    	                		  //cop(new File("C:\\GEFACT\\factpro\\"+code_jtext.getText().replace("/", "")+".pdf"),new File("C:\\GCOBAR\\CODE BARRE\\"+code_jtext.getText().replace("/", "")+" "+date+".fpc"));
//	    	                		  } catch (IOException p) {
//	    	                		  // TODO Auto-generated catch block
//	    	                		  p.printStackTrace();
//	    	                		  }
//	                			} catch (FileNotFoundException fnfe) {
//	                	  selectioncomb.imprimer(listrc,bdd,parcour,model);
//	                	  listrc.clear(); 
//
//	
//	                	  
//	                  } }            
//	                  }});
		     

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
						 new list_etq_emei("4");
						//frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}


