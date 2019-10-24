package logiciel_etiq;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
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


public class list_fiche extends JFrame {
	/**
	 * 
	 */
	ArrayList <String>list_fiche= new ArrayList<String>() ;
	private static final long serialVersionUID = 1L;
	public String Chemin = "c:\\GCOBAR\\";
	public  String bdd = Chemin+Utilitaire.InitBdd()+".accdb";	
    Object [] entete={"Designation","Poste","Chaine","Date","serial_number","code_commercial"};
    final Tableau tab=new Tableau(entete);

    final JTextField filterText = new JTextField("Recherche");
	static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	 JMenuItem menuItem = new JMenuItem("Imprimer etiquette" );
	 JMenuItem menuItem4 = new JMenuItem("Exporter en excel" );
	  Thread att;
	 private JButton importer=new JButton("Importer Excel");

    // JMenuItem menuItem2 = new JMenuItem("Imprimer fiche" );
    final JPanel paneltext =new JPanel();
	final JPanel pan=new JPanel();
	final JPanel pan1=new JPanel();
	final JPanel pan2=new JPanel();
	final JPanel pan3=new JPanel();
	  String date_jour; 
    final JLabel rech = new JLabel("Recherche");
    JLabel count;
    private JPopupMenu popupMenu = new JPopupMenu();
    private int selectedRow=-1;
    JScrollPane p=new JScrollPane(tab);
    gestion_imp imp=new gestion_imp();
	int i=1;
	int j=1;
	int l = 0; 
	  ArrayList<String> listrc = new ArrayList<String>();
	  ArrayList<String> listrc1 = new ArrayList<String>();
	  ArrayList<String> listfc = new ArrayList<String>();

	 JMenuItem menuItem3 = new JMenuItem("Imprimer etiquette commerciale" );
	list_fiche(final String log) throws ParseException{
		final menu fr=new menu(log);
		fr.setVisible(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	   setIconImage(img);
	   selectioncomb.windows(this,log,laf);
//			addWindowListener(new WindowAdapter(){
//	             @SuppressWarnings("deprecation")
//				public void windowClosing(WindowEvent e){
//	                   int reponse = JOptionPane.showConfirmDialog(
//	                                        null, "Voulez-vous vraiment quitter cette fenetre ?",
//	                                        "Confirmation",
//	                                        JOptionPane.YES_NO_OPTION,
//	                                        JOptionPane.QUESTION_MESSAGE);
//	                   if (reponse==JOptionPane.YES_OPTION){
//		                hide(); 
//	                   fr.setVisible(true); 
//	                   try{
//	                		UIManager.setLookAndFeel(laf);
//	                		SwingUtilities.updateComponentTreeUI(fr);
//	                		
//	                	}catch(Exception e1){
//	                		
//	                		e1.printStackTrace();
//	                	}
//	                    setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
//	                   }
//	                    
//	                	   
//	                	   if (reponse==JOptionPane.NO_OPTION){
//	                	   setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);	                   }
//	             }
//	    });  
			
//			tab.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//			      public void valueChanged(ListSelectionEvent event) {
//			    	  
//				 int a=tab.supprimer_modif_user(log);
//			     if(a==0){
//				 hide();
//			      } }  });  
			
		    build();
				
			composant();}
	
		 
		
	    		 
	   public void composant() throws ParseException{
		   if(!selectioncomb.prv.contains("list_fiche")){
			//System.out.println("list_fiche"+selectioncomb.prv);
			 selectioncomb.prv.add("list_fiche");}
		   //selectioncomb.prv.add("list_fiche");
		   tab.allowEdition1=false;
		   tab.allowEdition2=false;
		   tab.allowEdition3=false;
		   tab.allowEdition4=false;
		   tab.allowEdition5=false;
		   tab.allowEdition6=false;
		   filterText.setForeground(Color.gray); 		 
		   tab.setStyle(2);

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

			  
			
			  imp.select();
			  list_fiche=imp.select();
			    for(i=0;i<list_fiche.size();i=i+6){tab.ajouter();}
				int j=1;
				int l = 0; i=1;
				while(l<list_fiche.size()){
				
				 while(l<j*6){
					
					 tab.getTable().setValueAt(list_fiche.get(l).toLowerCase(), j-1, 0);
					 tab.getTable().setValueAt(list_fiche.get(l+1).toLowerCase(), j-1, 1);
					 tab.getTable().setValueAt(list_fiche.get(l+2).toLowerCase(), j-1, 2);
					 
             		 tab.getTable().setValueAt(list_fiche.get(l+3).toLowerCase(), j-1,3);
        			 tab.getTable().setValueAt(list_fiche.get(l+4).toLowerCase(), j-1,4);
        			 tab.getTable().setValueAt(list_fiche.get(l+5).toLowerCase(), j-1,5);

           			 i=i+1; l=l+6;
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
			
			pan.add(count);
			//pan.add(importer);

			pan.add(pan1);
			importer.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		    pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
		    paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));

		    filterText.setPreferredSize(new Dimension(50,30));
		    p.setPreferredSize(new Dimension(700,700));

		    paneltext.setPreferredSize(new Dimension(40, 150));

		    panel.setOpaque(false);
		    pan.setOpaque(false);
		    paneltext.setOpaque(false);
		    pan1.setOpaque(false);
		    pan2.setOpaque(false);
		    pan3.setOpaque(false); 
		    
		    pan2.setLayout(null);
		    filterText.setBounds(00, 30, 150,30);
		    
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
		     popupMenu.add(menuItem3);
		    popupMenu.add(menuItem4);
		    
		     menuItem4.addActionListener(
	                  new ActionListener() { 
	                  public void actionPerformed(ActionEvent e) {
	                	   att = new Thread(){
	  	 					public void run(){
	                	          
	               
			          		
		  					try {
		  						
		  						Date actuelle=new Date();
			 	                  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			 					   date_jour = dateFormat.format(actuelle);
			 					  
			 	          			String parcour="C://GCOBAR//pdf//excel//liste_fiche"+date_jour+".xlsx";
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
		 	        			cellA1.setCellValue("Code Article");
		 	        			CellStyle cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellA1.setCellStyle(cellStyle);

		  						Cell cellAa = row1.createCell((short) 1);
		 	        			cellAa.setCellValue("Designation");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellAa.setCellStyle(cellStyle);
		 	        			
		 	        			        			
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
		 	        			
		 	        			/*Cell cellB8 = row1.createCell((short) 7);
		 	        			cellB8.setCellValue("Carte M�re");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB8.setCellStyle(cellStyle);
		 	        			
		 	        			
		 	        			Cell cellB9 = row1.createCell((short) 8);
		 	        			cellB9.setCellValue("Mod�le carte M�re");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB9.setCellStyle(cellStyle);
		 	        			
		 	        			Cell cellB10 = row1.createCell((short) 9);
		 	        			cellB10.setCellValue("Dalle TV");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB10.setCellStyle(cellStyle);
		 	        			
		 	        			Cell cellB11 = row1.createCell((short) 10);
		 	        			cellB11.setCellValue("Mod�le Dalle");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellStyle.setAlignment(HorizontalAlignment.RIGHT);		 	        			
		 	        			cellB11.setCellStyle(cellStyle);*/
		 	        			
		 	        			
		 	        			Workbook wb = new HSSFWorkbook();
		 	        		    CreationHelper createhelper = wb.getCreationHelper();
		 	        		    Sheet sheet1 = wb.createSheet("new sheet");
		 	        		    Row row = null;
		 	        		    
 	        		            
 	        		           int[] selection = tab.table.getSelectedRows() ;
 		                	   int z =selection.length;
 		                	   
 		                	    ArrayList<String> liste =new  ArrayList<String>();
		          			    ArrayList<String> lis = new  ArrayList<String>();
		 	     		    	 
		 		   		    	    for (int i=0; i<z; i++) {
		 		   		    		if(!lis.contains((String) tab.table.getValueAt(selection[i], 4)))
		 		   		    			lis.add((String) tab.table.getValueAt(selection[i], 4));
		 		   		    	   }
		 	          			  
		 	          			    for (int h = 0 ; h < lis.size() ;h++) {
		 	          			    liste.addAll(imp.excel_select(lis.get(h)));
		 	          			    }
		 	          			    
		 	          			
		 		  				      int taille=liste.size()/lis.size();
		 		                      int k = 0,p;
		 		                      String value;	
		 		                      
		 		  					for(int i=0; i<liste.size();i=i+taille){

		 		  						 k++; p=0;
		 		  						 row = sheet.createRow((short)k);

		 		  						for(int j=i; j<taille*k;j++){
		 		  							
		 		  			             value = liste.get(j);
		 		  				        row.createCell(p ).setCellValue(value);		
		 		  				        p++;
		 		  						}
		 		  						
		 		  						}
		 		  					
		 		  					
		 		  					
		 		  					
		 		  					for (short i = sheet.getRow(0).getFirstCellNum(),
		 			          			     end = sheet.getRow(0).getLastCellNum() ; i < end ; i++) {
		 			          			    sheet.autoSizeColumn(i);
		 			          			}
		 			          			 
	 	        			
		 	        		worksheet.write(fileOut);
	 			          	fileOut.close();
	 			          	fileOut.flush();
	  					    BufferedReader bfr=	new BufferedReader(new FileReader("C://GCOBAR//pdf//excel//liste_fiche"+date_jour+".xlsx"));
	  					     bfr.close();		
	  						 					try {
	  		            	 Desktop.getDesktop().open(new File( "C://GCOBAR//pdf//excel//liste_fiche"+date_jour+".xlsx"));
	  						                  
	  						 					} catch (IOException p1) {
	  						 						
	  						 						//p.printStackTrace();
	  						 						
	  						 					}
	  						 					
	  					}
	  						 				 catch (IOException fnfe) {
	  						 				
	  						 			 try {
											Desktop.getDesktop().open(new File( "C://GCOBAR//pdf//excel//liste_fiche"+date_jour+".xlsx"));
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
	  						 					 //controlPanel.remove(progressBar);
	  					                } 
	  					
		  					
		  					
	          		
	  	 						    	}

	  	 					}; 
		 					
		 					att.start();
		 					
		 			
	                	   
	                	  
	                  }});
		     
		     
		     
		     
		 	        			
		 	        		
		 	        		    
		 	        		    
		 	        		    
		 	        		    
		 	        			
		 	          		    
		 			          			
		  					  
		 			          		
		     
		     menuItem3.addActionListener(
	                  new ActionListener() { 
	                  public void actionPerformed(ActionEvent e) {
	                  int[] selection = tab.table.getSelectedRows() ;
	     		    	 
	   		    	   int z =selection.length;
	   		    	    for (int i=0; i<z; i++) {
	   		    		if(!listrc.contains((String) tab.table.getValueAt(selection[i], 4)))
	   		    	    listrc.add((String) tab.table.getValueAt(selection[i], 4));
	   		    		
	   		    		if(!listrc1.contains((String) tab.table.getValueAt(selection[i], 5)))
		   		    	    listrc1.add((String) tab.table.getValueAt(selection[i], 5));
	   		    	   }
	   		    	    
	                	  if(selectedRow!=-1){
	                		  String parcour= "C:\\GCOBAR\\pdf\\etq_commericial\\"+listrc.get(0)+"_"+listrc.get(z-1)+"_"+z+"_"+"etq_commercial.pdf";
								String model ="C:\\GCOBAR\\CODE\\reportC2.jrxml";
	                		  try{
               System.out.println("hhhhhhh");
                          new BufferedReader(new FileReader(parcour));
	                			  try {               System.out.println("sssss");

	    	                		  Desktop.getDesktop().open(new File(parcour));
	    	                		  //cop(new File(bdd),new File(save.getSelectedFile().getPath().replace(".accdb", ".naw")));
	    	                		  //cop(new File("C:\\GEFACT\\factpro\\"+code_jtext.getText().replace("/", "")+".pdf"),new File("C:\\GCOBAR\\CODE BARRE\\"+code_jtext.getText().replace("/", "")+" "+date+".fpc"));
	    	                		  } catch (IOException p) {
	    	                		  // TODO Auto-generated catch block
	    	                		  p.printStackTrace();
	    	                		  }
	                			} catch (FileNotFoundException fnfe) {
	                				System.out.println(tab.getTable().getValueAt(selectedRow, 4));
                		
	                	        selectioncomb.imprimer(listrc,bdd,parcour,model);
	        						
	                	        listrc.clear();
	                			}
	                  }          
	                  }});
		     
		     menuItem.addActionListener(
	                  new ActionListener() { 
	                  public void actionPerformed(ActionEvent e) {
	  	                int[] selection = tab.table.getSelectedRows() ;
	                	  int z =selection.length;
		   		    	    for (int i=0; i<z; i++) {
		   		    		if(!listrc.contains((String) tab.table.getValueAt(selection[i], 4)))
		   		    	    listrc.add((String) tab.table.getValueAt(selection[i], 4));
		   		    	   }
	                	  if(selectedRow!=-1){
	                		  String parcour= "C:\\GCOBAR\\pdf\\etq_simple\\"+listrc.get(0)+"_"+listrc.get(z-1)+"_"+z+"_"+"etq_simple.pdf";
								String model ="C:\\GCOBAR\\CODE\\reportC.jrxml";
	                		  try{

                            new BufferedReader(new FileReader("C:\\GCOBAR\\pdf\\etq_simple\\"+listrc.get(0)+"_"+listrc.get(z-1)+"_"+z+"_"+"etq_simple.pdf"));
	                			  try {
	    	                		  Desktop.getDesktop().open(new File("C:\\GCOBAR\\pdf\\etq_simple\\"+listrc.get(0)+"_"+listrc.get(z-1)+"_"+z+"_"+"etq_simple.pdf"));

	    	                		  //cop(new File(bdd),new File(save.getSelectedFile().getPath().replace(".accdb", ".naw")));
	    	                		  //cop(new File("C:\\GEFACT\\factpro\\"+code_jtext.getText().replace("/", "")+".pdf"),new File("C:\\GCOBAR\\CODE BARRE\\"+code_jtext.getText().replace("/", "")+" "+date+".fpc"));
	    	                		  } catch (IOException p) {
	    	                		  // TODO Auto-generated catch block
	    	                		  p.printStackTrace();
	    	                		  }
	                			} catch (FileNotFoundException fnfe) {
	                	  selectioncomb.imprimer(listrc,bdd,parcour,model);
	                	  listrc.clear(); 

	
	                	  
	                  } }            
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
						 new list_fiche("4");
						//frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}


