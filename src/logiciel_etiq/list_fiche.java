package logiciel_etiq;

import java.awt.*;
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
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXDatePicker;


public class list_fiche extends JFrame {
	/**
	 * 
	 */
	ArrayList <String>list_fiche= new ArrayList<String>() ;
	private static final long serialVersionUID = 1L;
	public String Chemin = "c:\\GCOBAR\\";
	public  String bdd = Chemin+Utilitaire.InitBdd()+".accdb";	
    Object [] entete={"Code Article","Designation","Chaine","Date","serial_number","code_commercial"};
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
	final JPanel pan4=new JPanel();

	static JProgressBar b;
	String date_jour;
    final JLabel rech = new JLabel("Recherche");


	final JLabel datedebut = new JLabel("Debut");
	final JLabel datefin = new JLabel("Fin");


	final JXDatePicker datedebutText = new JXDatePicker();
	final JXDatePicker datefinText = new JXDatePicker();



    JLabel count = new JLabel();
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
	String filter= "";
	 JMenuItem menuItem3 = new JMenuItem("Imprimer etiquette commerciale" );
	list_fiche(final String log) throws ParseException{
		final menu fr=new menu(log);
		fr.setVisible(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	   setIconImage(img);
	   selectioncomb.windows(this,log,laf);

			
		    build();
				
			composant();}
	

		
	    		 
	   public void composant() throws ParseException{
		   Calendar cal = new GregorianCalendar();

		   cal.set(Calendar.DAY_OF_YEAR, 1);
		   datedebutText.setDate(cal.getTime());

		   cal.set(Calendar.DAY_OF_YEAR, 366); // for leap years
		   datefinText.setDate(cal.getTime());

		   datedebutText.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		   datefinText.setFormats(new SimpleDateFormat("dd/MM/yyyy"));




		   if(!selectioncomb.prv.contains("list_fiche")){
			 selectioncomb.prv.add("list_fiche");}

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





			 addtotable(filter,datedebutText.getEditor().getText(),datefinText.getEditor().getText());

		   datedebutText.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   if(filterText.getText().equals("Recherche")) filter="";
				   else
					   filter=filterText.getText().trim();
				   remove();
				   addtotable(filter,datedebutText.getEditor().getText(), datefinText.getEditor().getText());
				   count.setText("Le Nombre Des Fiches :  "+tab.table.getRowCount());
			   }
			    });

		   datefinText.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   if(filterText.getText().equals("Recherche")) filter="";
				   else
					   filter=filterText.getText().trim();
				   remove();
				   addtotable(filter,datedebutText.getEditor().getText(),datefinText.getEditor().getText());
				   count.setText("Le Nombre Des Fiches :  "+tab.table.getRowCount());
			   }


		   });


		   filterText.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   if(filterText.getText().equals("Recherche")) filter="";
				   else
					   filter=filterText.getText().trim();
				   remove();
				   addtotable(filter,datedebutText.getEditor().getText(),datefinText.getEditor().getText());
				   count.setText("Le Nombre Des Fiches :  "+tab.table.getRowCount());
			   }   });



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
	
			
		    count.setText("Le Nombre Des Fiches :  "+tab.table.getRowCount());
		    count.setForeground(Color.white); 
	 	    count.setBackground(new Color(6,119,144));
	 	    count.setOpaque(true);
	 	    count.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	 	    count.setFont(police2);


		   datedebut.setFont(police_fi);
		   datedebut.setForeground(Color.white);

		   datefin.setFont(police_fi);
		   datefin.setForeground(Color.white);

		    panel.add(paneltext);
			panel.add(p); 
			panel.add(pan);


			paneltext.add(pan2);
			paneltext.add(pan3);
		    paneltext.add(pan4);

		    pan2.add(filterText);

		    pan3.add(datedebut);
		    pan3.add(datedebutText);


		   pan4.add(datefin);
		   pan4.add(datefinText);

		   pan.add(count);
			//pan.add(importer);

			pan.add(pan1);

			importer.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		    pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
		    paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));

		    filterText.setPreferredSize(new Dimension(180,30));
		    p.setPreferredSize(new Dimension(700,700));

		    paneltext.setPreferredSize(new Dimension(40, 150));



		   datefinText.setPreferredSize(new Dimension(180,30));
		   datedebutText.setPreferredSize(new Dimension(180, 30));


		    panel.setOpaque(false);
		    pan.setOpaque(false);
		    paneltext.setOpaque(false);
		    pan1.setOpaque(false);
		    pan2.setOpaque(false);
		    pan3.setOpaque(false);
		    pan4.setOpaque(false);




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
								XSSFWorkbook worksheet = new XSSFWorkbook();

			 	          			CreationHelper ch = worksheet.getCreationHelper();
			 	          			String safeName = WorkbookUtil.createSafeSheetName("Liste Produit");
								XSSFSheet sheet = worksheet.createSheet(safeName);
			 	          			 
			 	          			File fichier = new File(parcour);
			 	 					    fichier.delete();
		 	          			// index from 0,0... cell A1 is cell(0,0)
								XSSFRow row1 =  sheet.createRow(0);


		  						Cell cellA1 = row1.createCell( 0);
		 	        			cellA1.setCellValue("Code Article");
		 	        			CellStyle cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellA1.setCellStyle(cellStyle);

		  						Cell cellAa = row1.createCell( 1);
		 	        			cellAa.setCellValue("Designation");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellAa.setCellStyle(cellStyle);





		 	        			Cell cellB6 = row1.createCell( 2);
		 	        			cellB6.setCellValue("Chaine");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB6.setCellStyle(cellStyle);



		 	        			Cell cellB7 = row1.createCell( 3);
		 	        			cellB7.setCellValue("Date");
		 	        			cellStyle = worksheet.createCellStyle();
		 	        			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		 	        			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 	        			cellB7.setCellStyle(cellStyle);



								Cell cellB8 = row1.createCell( 4);
								cellB8.setCellValue("Serial number");
								cellStyle = worksheet.createCellStyle();
								cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
								cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
								cellB8.setCellStyle(cellStyle);



								Cell cellB9 = row1.createCell( 5);
								cellB9.setCellValue("Code Commercial");
								cellStyle = worksheet.createCellStyle();
								cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
								cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
								cellB9.setCellStyle(cellStyle);



								XSSFRow  row = null;

 	        		            
 	        		           int[] selection = tab.table.getSelectedRows() ;
 		                	   int z =selection.length;


								final progressBare it = new progressBare(1,z);



								for (int i=0; i<z; i++) {

										final int percent = i;
										try {
											SwingUtilities.invokeLater(new Runnable() {
												public void run() {
													it.updateBar(percent);

												}
											});
											if(i==z-1){
												it.f.setVisible(false);
											}
											java.lang.Thread.sleep(5);
										} catch (InterruptedException e) {
											;
										}



									row = sheet.createRow(i+1);
									XSSFCell cell = row.createCell(0);
									cell.setCellValue( tab.table.getValueAt(selection[i], 0).toString());


									XSSFCell cell1 = row.createCell(1);
									cell1.setCellValue( tab.table.getValueAt(selection[i], 1).toString());

									XSSFCell cell2 = row.createCell(2);
									cell2.setCellValue( tab.table.getValueAt(selection[i], 2).toString());

									XSSFCell cell3 = row.createCell(3);
									cell3.setCellValue( tab.table.getValueAt(selection[i], 3).toString());


									XSSFCell cell4 = row.createCell(4);
									cell4.setCellValue( tab.table.getValueAt(selection[i], 4).toString());


									XSSFCell cell5 = row.createCell(5);
									cell5.setCellValue( tab.table.getValueAt(selection[i], 5).toString());




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

	   		    		/*if(!listrc1.contains((String) tab.table.getValueAt(selection[i], 5)))
		   		    	    listrc1.add((String) tab.table.getValueAt(selection[i], 5));*/
	   		    	   }
						  System.out.println(listrc);
					//	  System.out.println(listrc1);
	                	  if(selectedRow!=-1){
	                		  String parcour= "C:\\GCOBAR\\pdf\\etq_commericial\\"+listrc.get(0)+"_"+listrc.get(z-1)+"_"+z+"_"+"etq_commercial.pdf";
								String model ="C:\\GCOBAR\\CODE\\reportC2.jrxml";
	                		  try{
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



		    }

public void addtotable(String rech,String date_d,String date_f) {


	list_fiche=imp.select(rech,date_d,date_f);


	for(i=0;i<list_fiche.size();i=i+6){tab.ajouter();}

	int j=1;
	int l = 0; i=1;
	while(l<list_fiche.size()){

		while(l<j*6){
			tab.getTable().setValueAt(list_fiche.get(l+5).toLowerCase(), j-1,0);

			tab.getTable().setValueAt(list_fiche.get(l).toLowerCase(), j-1, 1);
			tab.getTable().setValueAt(list_fiche.get(l+1).toLowerCase(), j-1, 2);

			tab.getTable().setValueAt(list_fiche.get(l+2), j-1,3);
			tab.getTable().setValueAt(list_fiche.get(l+3).toLowerCase(), j-1,4);
			tab.getTable().setValueAt(list_fiche.get(l+4).toLowerCase(), j-1,5);

			i=i+1; l=l+6;
		}
		i=1;
		j=j+1;
	}






	if(tab.table.getRowCount()>1){
		count.setText("Le Nombre Des Fiches : "+tab.table.getRowCount());
	//	 System.out.println(tab.table.getRowCount());
		 }


}

public void remove(){
	int rows = tab.table.getRowCount();

	for (int i = rows - 1; i >= 0; i--) {
		((DefaultTableModel) tab.table.getModel()).removeRow(i);
	}
	//rows = tab.table.getRowCount();
}




	public void filtrer() throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date_b = null;
		Date date_f = null;
		try {
			date_b = format.parse(datedebutText.getEditor().getText());
			date_f = format.parse(datefinText.getEditor().getText());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();

		filters.add(RowFilter.dateFilter(RowFilter.ComparisonType.AFTER, date_b, 3));
		filters.add(RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE, date_f, 3));

		RowFilter<Object, Object> af = RowFilter.orFilter(filters);
		tab.sorter.setRowFilter(af);


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


