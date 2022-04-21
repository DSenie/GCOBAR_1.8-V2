package logiciel_etiq;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class imprime extends JFrame {
	/**
	 * 
	 */
	Map<String, Object> parameters = new HashMap<String, Object>();

	private static final long serialVersionUID = 1L;
	ArrayList <String>list_imp= new ArrayList<String>() ;
	
	ArrayList <String>list_imp_nomo= new ArrayList<String>() ;

	//static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	int assqc;int ligne;
String msg;
public String Chemin = "c:\\GCOBAR\\";
	public  String bdd = Chemin+Utilitaire.InitBdd()+".accdb";	
	private gestion_imp imp=new gestion_imp();

	private JPanel pan_article=new JPanel();
	private JPanel pan_article_comb=new JPanel();
	private JPanel pan_article_lab=new JPanel();
	private JLabel article_lab=new JLabel("Liste des Articles");
    private jcombo article_comb;
    
    
    private JPanel pan_chaine=new JPanel();
    private JPanel pan_chaine_comb=new JPanel();
    private JPanel pan_chaine_lab=new JPanel();
    private JLabel chaine_lab=new JLabel("Liste des Chaine");  
    private jcombo chaine_comb;
    
    private JPanel pan_poste=new JPanel();
    private JPanel pan_poste_comb=new JPanel();
    private JPanel pan_poste_lab=new JPanel();
    private JLabel poste_lab=new JLabel("Liste des Poste");
    private jcombo poste_comb;
    
    
    private JPanel pan_code=new JPanel();
    private JPanel pan_code_jtext=new JPanel();
    private JPanel pan_code_lab=new JPanel();
    private JLabel code_lab=new JLabel("Serial Number");
    private JTextField code_jtext=new JTextField();
	

	
    private JPanel pan_assemblage=new JPanel();
    private JPanel pan_assemblage_lab =new JPanel();
    private JPanel pan_assemblage_jtext=new JPanel();
    private JLabel assemblage_lab=new JLabel("Poste Assemblage");
    private JTextField assemblage_jtext=new JTextField();
	
    private JPanel pan_assqc=new JPanel();
    private JPanel pan_assqc_lab =new JPanel();
    private JPanel pan_assqc_jtext=new JPanel();
    private JLabel assqc_lab=new JLabel("QC Assemblage");
    private JTextField assqc_jtext=new JTextField();
	
    private JPanel pan_actqc=new JPanel();
    private JPanel pan_actqc_lab =new JPanel();
    private JPanel pan_actqc_jtext=new JPanel();
    private JLabel actqc_lab=new JLabel("QC Activation");
    private JTextField actqc_jtext=new JTextField();
	
    private JPanel pan=new JPanel();
    private JPanel pan_general=new JPanel();
	
    private JPanel pan_form=new JPanel();
    private JPanel panel;

    private JLabel  entrep= new JLabel("         Entreprise"); 
    private JRadioButton enie= new JRadioButton("ENIE",false);
    private JRadioButton autre = new JRadioButton("AUTRE",true); 
    private  ButtonGroup bG = new ButtonGroup();


    private JPanel pan_radio=new JPanel();
    private JPanel pan_radio_lab=new JPanel();
    private JPanel pan_radio_bg=new JPanel();
    private JPanel pan_button=new JPanel();
    private JPanel pan_gener=new JPanel();
    private JPanel pan_tab=new JPanel();
    
    private JButton but_sauv=new JButton("Ajouter");
    private JButton but_modif=new JButton("Modifier");
    private JButton valid_ajou=new JButton("Valider");
    private JButton valid_modif=new JButton("Valider");
    private JButton imp_fiche=new JButton("Imprimer La fiche");
    private JButton imp_etq=new JButton("Imprimer L'étiquette");
    private JButton retour=new JButton("Retour");
    
    
    private int i;

    private static List<String> list_ar;
	private static List<String> list_c;
	private static List<String> list_p;
	
    private Object [] entete={"","Designation","S/N","Quantite"};
 	final Tableau_fiche tab2=new Tableau_fiche(entete);
 	private JScrollPane p=new JScrollPane(tab2);
 	private final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,15);


	private String[] article;
	private String code_art; // 004
	private  String des; // 004  	     
	private String[] chaine;
	private  String cchaine; // 004
	private String[] poste;
	private  String cposte; // 004
    
    imprime(final String log){ 
	
	  Toolkit kit = Toolkit.getDefaultToolkit();
	  Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	  setIconImage(img);
	  selectioncomb.windows(this,log);
	    if(!selectioncomb.prv.contains("imprime"))
            selectioncomb.prv.add("imprime"); 
		composant(log);  
		}
    
		@SuppressWarnings({ "deprecation" })
		public void composant(final String logi_prio){
			
			
			list_ar = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un article-----"}));
			article_comb = new jcombo(list_ar.toArray());
			list_c = new ArrayList<String>(Arrays.asList(new String[]{"--Sélectionner une Chaine--"}));
			chaine_comb = new jcombo(list_c.toArray());
			list_p = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un Poste-----"}));
			poste_comb = new jcombo(list_p.toArray());
			//selectioncomb.selectarticle( article_comb, enie, autre, this,logi_prio);	   

		//	selectioncomb.selectchaine(chaine_comb,this,logi_prio);
		//	selectioncomb.selectposte( poste_comb,this,logi_prio);
		 //   imp.select_conteur();
		  //  serialnumber();
           // chaine_comb.setSelectedIndex(1);
		    tab2.table.getColumnModel().getColumn(0).setMinWidth(0);
		    tab2.table.getColumnModel().getColumn(0).setMaxWidth(0);	    	
		    tab2.allowEdition2=false;
		 	tab2.allowEdition1=false;
		 	tab2.allowEdition4=false;
		 	tab2.setStyle(2);
		 	
             panel= new JPanel(){   
					  		/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

							public void paintComponent(Graphics g){
					  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
					             img.paintIcon(this, g,0, 0);
		       }};
        	 //  imp.select_fiche(code_jtext.getText());	

		     //  if(!imp.exist.equals("")){	
	        	   //}		
					  	
		        but_sauv.setVisible(true);
				but_modif.setVisible(false);
	       		imp_etq.setVisible(false);
	       		imp_fiche.setVisible(false);
				valid_modif.setVisible(false);
				valid_ajou.setVisible(false);
			    retour.setVisible(false);		  
			  	assqc_jtext.disable();
				actqc_jtext.disable();
				assemblage_jtext.disable();
				code_jtext.enable();
				article_comb.enable();
				chaine_comb.disable();
				poste_comb.enable();
//				assqc_jtext.setText("1");
//				actqc_jtext.setText("1");
//				assemblage_jtext.setText("1");.
				NumberFormat form1 = new DecimalFormat("0");
			//	String sf=form1.format(imp.ass/ imp.assqc );
				//assqc=imp.select_count_assqc();
				//ligne=imp.count_ligne();
				//int po=ligne % Integer.parseInt(sf);
			//	 if(po==0){
					  if(assqc==3) assqc=1;
					   else{ assqc=assqc+1;
					  // System.out.println(assqc+"ffffffffffffff");
					   }
					   		 //  }else{ 
					   			   //System.out.println(assqc+"fvvvvv");
					   			assqc=assqc+0;
				// }

				//article_comb.setSelectedIndex(1);
//				assemblage_jtext.setText("" + imp.count_ass);
//				assqc_jtext.setText("hhhhh" + assqc);
//				actqc_jtext.setText("" + imp.count_actqc);
				
            	
                artcomb(); 

			   LineBorder border = new LineBorder ( Color.white, 1, true );
	       	   TitledBorder titl2 = new TitledBorder ( border, "Fiche Suiveuse", TitledBorder.DEFAULT_POSITION,
	       	   TitledBorder.DEFAULT_POSITION, police2, Color.white); 
	       	   pan.setBorder(titl2);
	       		
	       	   
			   but_sauv.addActionListener(
			             new ActionListener() { 
			               public void actionPerformed(ActionEvent e) {
			            LineBorder border = new LineBorder ( Color.white, 1, true );
			       		TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
			       	    TitledBorder.DEFAULT_POSITION, police2, Color.white); 
			       		pan.setBorder(titl2);	    
			    		article_comb.enable();
			    		chaine_comb.disable();
			    		poste_comb.enable();
			            but_sauv.setVisible(false);
			            but_modif.setVisible(false);
			            imp_fiche.setVisible(false);
			       		retour.setVisible(true);
			       		valid_ajou.setVisible(true);
			       		valid_modif.setVisible(false);
			       		imp_etq.setVisible(false);
			         	imp.select_conteur();
		        	 	imp.conteur();
		        	 	NumberFormat form1 = new DecimalFormat("0");
						String sf=form1.format(imp.ass/ imp.assqc );
						assqc=imp.select_count_assqc();
						ligne=imp.count_ligne();
						int po=ligne % Integer.parseInt(sf);
						 if(po==0){
							  if(assqc==3) assqc=1;
							   else{ assqc=assqc+1;
							   }
							   		   }else{ 
							   			assqc=assqc+0;
						 }
		        		assemblage_jtext.setText("" + imp.count_ass);
						assqc_jtext.setText("" + assqc);
						actqc_jtext.setText("" + imp.count_actqc);
			             }});
			    
			   
			   but_modif.addActionListener(
			             new ActionListener() { 
			             public void actionPerformed(ActionEvent e) {
			             LineBorder border = new LineBorder ( Color.white, 1, true );
			       		 TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
			       	     TitledBorder.DEFAULT_POSITION, police2, Color.white); 
			       		 pan.setBorder(titl2);
			             but_sauv.setVisible(false);
			             but_modif.setVisible(false);
					     imp_fiche.setVisible(false);
			       		 retour.setVisible(true);
			       		 valid_ajou.setVisible(false);
			       		 valid_modif.setVisible(true);
				       	 imp_etq.setVisible(false);
				       	 article_comb.disable();
				       	 code_jtext.disable();
				       	 poste_comb.disable();
				         chaine_comb.disable();
			             }});
			   
			   
			   valid_modif.addActionListener(
			             new ActionListener() { 
			             public void actionPerformed(ActionEvent e) {
				           msg="";			 
				           boolean rep=true;
				           try{
				        	   if( tab2.table.getCellEditor()!=null)
					           tab2.table.getCellEditor().stopCellEditing(); 

						   	}
						     catch(Exception e1){
						   		e1.printStackTrace();
						   	}				           imp.select_fiche(code_jtext.getText());		
			               
			               for(int i=0;i<tab2.table.getRowCount();i++){
				  	           	   for(int j=i+1;j<tab2.table.getRowCount();j++){
				  	           		   if(tab2.table.getValueAt(i, 1).toString().equals(tab2.table.getValueAt(j, 1).toString())){
				  	           			  rep=false; 
				  	           	   }}  }  
//			            	  
//			                if(vaidCheck()==false){
//					        		   msg+="Un Champ dans le tableau est vide \n";
//					        	   }			    	        
if (rep==false)     		   msg+="Il existe un ou plusieur articles dupliqués \n";

else  if(imp.exist.equals("non")){
			            	    	  msg+="Ce Produit n'existe pas \n";
			            	    }
				              
			                if(msg.equals("")){
			                	 article= article_comb.getSelectedItem().toString().split(" ");
			        			 code_art= article[0]; // 004
			        			 
			               	     des= article_comb.getSelectedItem().toString().replace(code_art+" ", ""); // 004  	 
			               	     
			                imp.setupdate(code_jtext.getText());
			                for(int i=0;i<tab2.table.getRowCount();i++){
			                String sn =tab2.table.getValueAt(i, 2).toString();
			            	String codeart =tab2.table.getValueAt(i, 0).toString();
                            int qte =Integer.parseInt(tab2.table.getValueAt(i, 3).toString());
			  		         imp.ajouter_imprim_compo(code_art,code_jtext.getText(),codeart,sn,qte);
                              }			    
			            	 JOptionPane.showMessageDialog(null,""+des+" "+code_jtext.getText()+" a été bien modifié");
			            	 imp_etq.setVisible(true);
					       	 imp_fiche.setVisible(true);
				              }
				           else{JOptionPane.showMessageDialog(null,msg);}

			             } });
			   
			   valid_ajou.addActionListener(
			             new ActionListener() { 
			             public void actionPerformed(ActionEvent e) { 
			            	// int asscont= imp.conteur_assqc();
			            	 msg="";
			            	 boolean rep=true;
			            	 try{
					        	   if( tab2.table.getCellEditor()!=null)

						           tab2.table.getCellEditor().stopCellEditing(); 

							   	}
							     catch(Exception e1){
							   		e1.printStackTrace();
							   	}

			            	 for(int i=0;i<tab2.table.getRowCount();i++){
				  	           	   for(int j=i+1;j<tab2.table.getRowCount();j++){
				  	           		   if(tab2.table.getValueAt(i, 1).toString().equals(tab2.table.getValueAt(j, 1).toString())){
				  	           			   rep=false;
				  	           	   }}
			  	           	   }  
			            	
			            	   for(int i=0;i<tab2.table.getRowCount();i++){
					             		  String col2= tab2.table.getValueAt(i, 3).toString();
					             		  if(isValid(col2)==false){  
					             			  msg+="La quantite dans le tableau doit étre un nombre\n";
					             		  }}
		  	           		if (rep==false)   msg+="Il existe un ou plusieur articles dupliqués \n";

		  	           		else if(article_comb.getSelectedIndex()==0){msg+="Veuillez selectionner un article \n";}            	
				              else if(poste_comb.getSelectedIndex()==0){msg+="Veuillez selectionner un poste \n";}
					          else if(chaine_comb.getSelectedIndex()==0){msg+="Veuillez selectionner une chaine \n";}
			            	  if(msg.equals("")){   
			            		
			            		 
				              int   cont1=Integer.parseInt(assemblage_jtext.getText());
				              int   cont3=Integer.parseInt(actqc_jtext.getText());

				              poste= poste_comb.getSelectedItem().toString().split(" ");
				              cposte = poste[0]; // 004
				          	   
				          	  chaine= chaine_comb.getSelectedItem().toString().split(" ");
				          	   cchaine = chaine[0]; // 004
				          	   
				          	 article= article_comb.getSelectedItem().toString().split(" ");
			    			 code_art= article[0]; // 004
			           	     des= article[1]; // 004  
			           	    NumberFormat form1 = new DecimalFormat("0");
							String sf=form1.format(imp.ass/ imp.assqc );
							assqc=imp.select_count_assqc();
							ligne=imp.count_ligne();
							int po=ligne % Integer.parseInt(sf);
							 if(po==0){
								  if(assqc==3) assqc=1;
								   else{ assqc=assqc+1;
								  }
								   		   }else{ 
								   			assqc=assqc+0;
							 }
							 
			                  //imp.ajouter_imprimer(code_art,cposte,cchaine,code_jtext.getText(),cont1,cont3,assqc);
			                  
			                  for(int i=0;i<tab2.table.getRowCount();i++){
			            	  String codeart =tab2.table.getValueAt(i, 0).toString();
			            	  String sn =tab2.table.getValueAt(i, 2).toString();
                              int qte =Integer.parseInt(tab2.table.getValueAt(i, 3).toString());
	  		            	  imp.ajouter_imprim_compo(code_art,code_jtext.getText(),codeart,sn,qte);
	  		            	  imp_etq.setVisible(true);
						      imp_fiche.setVisible(true);
			            		 }
			
						      JOptionPane.showMessageDialog(null,"l'étiquette a "+des+"  a été bien ajouté");

			            	  }
			            	  else{JOptionPane.showMessageDialog(null,msg);}

			             }});
			   
			   
			    
			    retour.addActionListener(
			            new ActionListener() { 
			            public void actionPerformed(ActionEvent e) {
			            	LineBorder border = new LineBorder ( Color.white, 1, true );
			            	 TitledBorder titl2 = new TitledBorder ( border, "Edition", TitledBorder.DEFAULT_POSITION,
			            	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
			            	 pan.setBorder(titl2);
			            	 article_comb.setSelectedIndex(1);
			                 chaine_comb.setSelectedIndex(1);
			                 
			                 poste_comb.setSelectedIndex(0);
			                 code_jtext.setText("");
			                 but_sauv.setVisible(true);
			                 but_modif.setVisible(false);
			                 imp_etq.setVisible(false);
			                 imp_fiche.setVisible(false);
			                 retour.setVisible(false);
			                 valid_ajou.setVisible(false);
			                 valid_modif.setVisible(false);
			                 poste_comb.enable();
					         chaine_comb.disable();
			                 article_comb.enable();
			                 code_jtext.enable();
			                 int rows = tab2.table.getRowCount(); 
			                 if( article_comb.getSelectedIndex()==0){
			                 for(int i = rows - 1; i >=0; i--)
			                   	{
			                   		((DefaultTableModel) tab2.table.getModel()).removeRow(i);

			                   	}}
			                 for(int i = 0; i <=rows-1; i++)
			                   	{
			                 tab2.table.setValueAt("", i, 2);}
			                 imp.select_conteur();
			        	 	 imp.conteur();
			        	 	NumberFormat form1 = new DecimalFormat("0");
			    			String sf=form1.format(imp.ass/ imp.assqc );
			    			assqc=imp.select_count_assqc();
			    			ligne=imp.count_ligne();
			    			int po=ligne % Integer.parseInt(sf);
			    			 if(po==0){
			    				  if(assqc==3) assqc=1;
			    				   else{ assqc=assqc+1;
			    				  }
			    				   		   }else{ 
			    				   			assqc=assqc+0;
			    			 }
			        	 	assemblage_jtext.setText("" + imp.count_ass);
			    			assqc_jtext.setText("" + assqc);
			    			actqc_jtext.setText("" + imp.count_actqc);
			            }});
			    
			    article_comb.addActionListener(
				           new ActionListener() { 
				           public void actionPerformed(ActionEvent e) {
				        	   imp.select_fiche(code_jtext.getText());	
				        	   //System.out.println("gggggg"+imp.exist);
				        	   if(!imp.exist.equals("")){	
				                artcomb(); 
				        	   }			        	   
				           	   }});
			    
				 
//			    imp_etq.addActionListener(new ActionListener() {
//					 public void actionPerformed(ActionEvent event) {// dispose();
//						 String parcour= "C:\\GCOBAR\\CODE\\"+code_jtext.getText()+".pdf";
//							String model ="C:\\GCOBAR\\CODE\\reporttest.jrxml";
//							try{
//	                		 new BufferedReader(new FileReader("C:\\GCOBAR\\CODE\\"+code_jtext.getText()+".pdf"));
//	                			  try {
//	    	                		  Desktop.getDesktop().open(new File("C:\\GCOBAR\\CODE\\"+code_jtext.getText()+".pdf"));
//
//	    	                		  //cop(new File(bdd),new File(save.getSelectedFile().getPath().replace(".accdb", ".naw")));
//	    	                		  //cop(new File("C:\\GEFACT\\factpro\\"+code_jtext.getText().replace("/", "")+".pdf"),new File("C:\\GCOBAR\\CODE BARRE\\"+code_jtext.getText().replace("/", "")+" "+date+".fpc"));
//	    	                		  } catch (IOException p) {
//	    	                		  // TODO Auto-generated catch block
//	    	                		  p.printStackTrace();
//	    	                		  }
//	                			} 
//							catch (FileNotFoundException fnfe) {
//								
//             				selectioncomb.imprimer(code_jtext.getText(),bdd,parcour,model);
//             			  imp.delete_imp();
//	                  }        
//	                  }});
			    



				 
			    imp_fiche.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent event) {// dispose();
						String parcour= "C:\\GCOBAR\\CODE\\fichesuiveuse"+code_jtext.getText()+".pdf";
						String model ="C:\\GCOBAR\\CODE\\report1.jrxml";
	                		  try{
	                			 new BufferedReader(new FileReader("C:\\GCOBAR\\CODE\\fichesuiveuse"+code_jtext.getText()+".pdf"));
	                			  try {
	                				  if(valid_modif.isVisible()==true){
	                					  File file = new File("C:\\GCOBAR\\CODE\\fichesuiveuse"+code_jtext.getText()+".pdf");
	                			        	
	                			    		file.delete();
	                			    		parameters.put("serial",code_jtext.getText());
	                			    		   selectioncomb.imprimer(	 bdd, parcour,model,parameters);
	                			    		   
	                			    		//selectioncomb.imprimer(code_jtext.getText(),bdd,parcour,model);
	                				  }
	                				  else{
	    	                		  Desktop.getDesktop().open(new File("C:\\GCOBAR\\CODE\\fichesuiveuse"+code_jtext.getText()+".pdf"));
	                				  }
	    	                		  //cop(new File(bdd),new File(save.getSelectedFile().getPath().replace(".accdb", ".naw")));
	    	                		  //cop(new File("C:\\GEFACT\\factpro\\"+code_jtext.getText().replace("/", "")+".pdf"),new File("C:\\GCOBAR\\CODE BARRE\\"+code_jtext.getText().replace("/", "")+" "+date+".fpc"));
	    	                		  } catch (IOException p) {
	    	                		  // TODO Auto-generated catch block
	    	                		  p.printStackTrace();
	    	                		  }
	                			} catch (FileNotFoundException fnfe) {
	                				//selectioncomb.imprimer(code_jtext.getText(),bdd,parcour,model);
	                			}        
	                  }});

	
				 
				
			tab2.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				      public void valueChanged(ListSelectionEvent event) {
			    	  tab2.table.setColumnSelectionInterval(2, 2);
			    	  tab2.table.setCellSelectionEnabled(true);
				    for(i=0;i<tab2.table.getRowCount()-1;i=i+1){
				  	if(tab2.table.getValueAt(i, 2).toString().length()==5){
				  		//System.out.println("kkkkkkkkkkkkkk");
				    		tab2.table.getCellEditor(i, 2);
				    		tab2.table.isCellSelected(i, 2);
				    		
				    	}}
				}  });

			generale.styles("Nimbus");
			SwingUtilities.updateComponentTreeUI(this);
				 
				 
			        article_lab.setForeground(Color.white);
			        poste_lab.setForeground(Color.white);
			        chaine_lab.setForeground(Color.white);
			        code_lab.setForeground(Color.white);
			        assemblage_lab.setForeground(Color.white);
			        assqc_lab.setForeground(Color.white);
			        actqc_lab.setForeground(Color.white);
			        entrep.setForeground(Color.white);
			        enie.setForeground(Color.white);
			        autre.setForeground(Color.white);

			        entrep.setFont(police2);
			        enie.setFont(police2);
			        autre.setFont(police2);
			        article_lab.setFont(police2);
			        poste_lab.setFont(police2);
			        chaine_lab.setFont(police2);
			        code_lab.setFont(police2);
			        assemblage_lab.setFont(police2);
			        assqc_lab.setFont(police2);
			        actqc_lab.setFont(police2);
			        
			        bG.add(enie);
		       	    bG.add(autre);
		       	    pan_radio_lab.add(entrep);
		       	    pan_radio.add(pan_radio_lab);
		            pan_radio.add(pan_radio_bg);
		            pan_radio_bg.add(enie);
		       	    pan_radio_bg.add(autre);
		       	 
		       	    pan_article.add(pan_article_lab);
	                pan_article_lab.add(article_lab);
	                pan_article.add(pan_article_comb);
	                pan_article_comb.add(article_comb);
	      
			        pan_poste.add(pan_poste_lab);
                    pan_poste_lab.add(poste_lab);
                    pan_poste.add(pan_poste_comb);
                    pan_poste_comb.add(poste_comb);
            
                    pan_chaine.add(pan_chaine_lab);
                    pan_chaine_lab.add(chaine_lab);
                    pan_chaine.add(pan_chaine_comb);
                    pan_chaine_comb.add(chaine_comb);

               
                    pan_code.add(pan_code_lab);
                    pan_code_lab.add(code_lab);
                    pan_code.add(pan_code_jtext);
                    pan_code_jtext.add(code_jtext);

                    pan_assemblage.add(pan_assemblage_lab);
                    pan_assemblage_lab.add(assemblage_lab);
                    pan_assemblage.add(pan_assemblage_jtext);
                    pan_assemblage_jtext.add(assemblage_jtext);

                    pan_assqc.add(pan_assqc_lab);
                    pan_assqc_lab.add(assqc_lab);
                    pan_assqc.add(pan_assqc_jtext);
                    pan_assqc_jtext.add(assqc_jtext);

                    pan_actqc.add(pan_actqc_lab);
                    pan_actqc_lab.add(actqc_lab);
                    pan_actqc.add(pan_actqc_jtext);
                    pan_actqc_jtext.add(actqc_jtext);
 
                    pan_button.add(retour);
            	    pan_button.add(valid_ajou);
            	    pan_button.add(valid_modif);
                 	pan_button.add(but_sauv);
            	    pan_button.add(but_modif);	
            	   //pan_button.add(imp_etq);
            	   pan_button.add(imp_fiche);

                   pan_form.add(pan_radio);
                   pan_form.add(pan_article);
                   pan_form.add(pan_poste);
                   pan_form.add(pan_chaine);
                   pan_form.add(pan_code);
                   pan_form.add(pan_assemblage);
                   pan_form.add(pan_assqc);
                   pan_form.add(pan_actqc);
                   pan_general.add(pan_button);
                
                   pan_gener.add(pan_form);
                   pan_gener.add(pan_tab);
                   pan.add(pan_gener);
                   pan.add(pan_general);
                   pan_tab.add(p);
                   panel.add(pan);
                   
                   pan_form.setOpaque(false);
                   pan_button.setOpaque(false);
                   pan_assqc.setOpaque(false);
                   pan_assqc_lab.setOpaque(false);
                   pan_assqc_jtext.setOpaque(false);
                   pan_actqc.setOpaque(false);
                   pan_actqc_lab.setOpaque(false);
                   pan_actqc_jtext.setOpaque(false);
                   pan_code.setOpaque(false);
                   pan_code_lab.setOpaque(false);
                   pan_code_jtext.setOpaque(false);
                   pan_chaine.setOpaque(false);
                   pan_chaine_lab.setOpaque(false);
                   pan_chaine_comb.setOpaque(false);
                   pan_radio_bg.setOpaque(false);
   		       	   pan_radio_lab.setOpaque(false);
   		       	   pan_radio.setOpaque(false);
                   pan_article.setOpaque(false);
                   pan_article_lab.setOpaque(false);
                   pan_article_comb.setOpaque(false);
                   pan_poste.setOpaque(false);
                   pan_poste_lab.setOpaque(false);
                   pan_poste_comb.setOpaque(false);
                   pan_assemblage.setOpaque(false);
                   pan_assemblage_lab.setOpaque(false);
                   pan_assemblage_jtext.setOpaque(false);
                   pan_gener.setOpaque(false);
                   pan_tab.setOpaque(false);
                   pan_general.setOpaque(false);
                   pan.setOpaque(false);
        
                   p.setPreferredSize(new Dimension(550,550));

			  	   
			  	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			  	pan_gener.setLayout(new BoxLayout(pan_gener,BoxLayout.X_AXIS));
			  	pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
			    but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
			    but_sauv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
			    imp_fiche.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
			    retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
			    imp_etq.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
			    valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
			    valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

			    but_modif.setPreferredSize(new Dimension(120, 33));
			    but_sauv.setPreferredSize(new Dimension(120, 33));
			    imp_etq.setPreferredSize(new Dimension(170, 33));
			    retour.setPreferredSize(new Dimension(120, 33));
			    imp_fiche.setPreferredSize(new Dimension(170, 33));
			    valid_modif.setPreferredSize(new Dimension(120, 33));
			    valid_ajou.setPreferredSize(new Dimension(120, 33));
			    article_comb.setPreferredSize(new Dimension(210, 30));
			    chaine_comb.setPreferredSize(new Dimension(210, 30));
			    poste_comb.setPreferredSize(new Dimension(210, 30));
			    code_jtext.setPreferredSize(new Dimension(210,30));
			    assemblage_jtext.setPreferredSize(new Dimension(210,30));
			    assqc_jtext.setPreferredSize(new Dimension(210, 30));
			    actqc_jtext.setPreferredSize(new Dimension(210,30));

		         pan_article.setLayout(new BoxLayout(pan_article,BoxLayout.X_AXIS));
		         pan_poste.setLayout(new BoxLayout(pan_poste,BoxLayout.X_AXIS));
		         pan_chaine.setLayout(new BoxLayout(pan_chaine,BoxLayout.X_AXIS));
		         pan_code.setLayout(new BoxLayout(pan_code,BoxLayout.X_AXIS));
		         pan_assemblage.setLayout(new BoxLayout(pan_assemblage,BoxLayout.X_AXIS));
		         pan_assqc.setLayout(new BoxLayout(pan_assqc,BoxLayout.X_AXIS));
		         pan_actqc.setLayout(new BoxLayout(pan_actqc,BoxLayout.X_AXIS));
		         pan_radio.setLayout(new BoxLayout(pan_radio,BoxLayout.X_AXIS));
			  	 pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));

		         
		         pan_radio_lab.setLayout( new FlowLayout( FlowLayout.LEFT));
		         pan_radio_bg.setLayout(new FlowLayout(FlowLayout.LEFT));
		         pan_radio_lab.setBorder(BorderFactory.createEmptyBorder(0,0, 0,0));
		         pan_radio_bg.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
		         
		         pan_article_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
		         pan_article_comb.setLayout(new FlowLayout(FlowLayout.RIGHT));
		         pan_article_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
		         pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0, 30, 0,0));
		         
		         pan_poste_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
		         pan_poste_comb.setLayout(new FlowLayout(FlowLayout.RIGHT));
		         pan_poste_lab.setBorder(BorderFactory.createEmptyBorder(0, 18, 0,0));
		         pan_poste_comb.setBorder(BorderFactory.createEmptyBorder(0, 28, 0,0));
		         
		         pan_chaine_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
		         pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.RIGHT));
		         pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 5, 0,0));
		         pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0, 23, 0,0));
		         
		         pan_code_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
		         pan_code_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));
		         pan_code_lab.setBorder(BorderFactory.createEmptyBorder(0, 15, 0,0));
		         pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(0, 13, 0,0));
		         
		         pan_assemblage_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
		         pan_assemblage_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));
		         pan_assemblage_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
		         pan_assemblage_jtext.setBorder(BorderFactory.createEmptyBorder(0, 28, 0,0));
		         
		         pan_assqc_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
		         pan_assqc_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));
		         pan_assqc_lab.setBorder(BorderFactory.createEmptyBorder(0, 15, 0,0));
		         pan_assqc_jtext.setBorder(BorderFactory.createEmptyBorder(0, 18, 0,0));
		         
		         pan_actqc_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
		         pan_actqc_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));
		         pan_actqc_lab.setBorder(BorderFactory.createEmptyBorder(0, 20, 0,0));
		         pan_actqc_jtext.setBorder(BorderFactory.createEmptyBorder(0, 22, 0,0));
		         
			    setTitle("imprime" );
			    setSize(1000, 600);
			    setLocationRelativeTo(null);          
			    setVisible(true);
			    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    setContentPane(panel);
}

		
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						
				
						 new imprime("7");
						//frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		public boolean vaidCheck()

		{

		if(tab2.table.getCellEditor()!=null){

		tab2.table.getCellEditor().stopCellEditing();

		}

		for(int l=0;l< tab2.table.getRowCount();l++)

		{

		for (int k=0;k<tab2.table.getColumnCount();k++)

		{

		String om=tab2.table.getValueAt(l,k).toString();


		if(om.trim().length()==0)

		{
			msg="";
		return false;

		}}}


		return true;
		}	
		
		public void artcomb(){
			  LineBorder border = new LineBorder ( Color.white, 1, true );
	       	   TitledBorder titl2 = new TitledBorder ( border, "Fiche Suiveuse", TitledBorder.DEFAULT_POSITION,
	       	   TitledBorder.DEFAULT_POSITION, police2, Color.white); 
	       	   pan.setBorder(titl2);
			 int rows = tab2.table.getRowCount(); 
        		for(int i = rows - 1; i >=0; i--)
            	{
            		((DefaultTableModel) tab2.table.getModel()).removeRow(i);

            	}
	       
//        	  if(article_comb.getSelectedIndex()==0){
//         	  article_comb.setSelectedIndex(0);
//              poste_comb.setSelectedIndex(0);
//              chaine_comb.setSelectedIndex(0);
//              code_jtext.setText("");
//              assemblage_jtext.setText("");
//              assqc_jtext.setText("");
//              actqc_jtext.setText("");
//        	   }
//        	   else {    
	         imp.select_conteur();
    	 	 imp.conteur();
    	 	NumberFormat form1 = new DecimalFormat("0");
			String sf=form1.format(imp.ass/ imp.assqc );
			assqc=imp.select_count_assqc();
			ligne=imp.count_ligne();
			int po=ligne % Integer.parseInt(sf);
			 if(po==0){
				  if(assqc==3) assqc=1;
				   else{ assqc=assqc+1;
				  // System.out.println(assqc+"ffffffffffffff");
				   }
				   		   }else{// System.out.println(assqc+"fvvvvv");
				   			assqc=assqc+0;
			 }
    	 	assemblage_jtext.setText("" + imp.count_ass);
			assqc_jtext.setText("" + assqc);
			actqc_jtext.setText("" + imp.count_actqc);

 			  //String code=cp+anne+week+jour+cc+imp.afficher_conteur();
 			//  code_jtext.setText(code);
 			   if(article_comb.getSelectedIndex()!=0&&article_comb.getSelectedIndex()!=article_comb.getItemCount()-1){
	    	     article= article_comb.getSelectedItem().toString().split(" ");
    			 code_art= article[0]; // 004
           	     des= article_comb.getSelectedItem().toString().replace(code_art+" ", ""); // 004  
}
        		 if(article_comb.getItemCount()>1){
        			// System.out.println(imp.select_nomo(code_art).size()+"fff555555555"+code_art);
        			 list_imp_nomo=imp.select_nomo(code_art);
        		//	 System.out.println(list_imp_nomo+"gggggggg"+code_art);
         		   for(i=0;i<list_imp_nomo.size();i=i+3){
         			 tab2.ajouter();
   		         	 int k=tab2.table.getRowCount();
         			tab2.table.setValueAt("", k-1,2);
           		 	}
           			int j=1;
           			int l = 0; i=1;
           			while(l<list_imp_nomo.size()){
           			 while(l<j*3){
           				 tab2.getTable().setValueAt(list_imp_nomo.get(l), j-1,0);
       				     tab2.getTable().setValueAt(list_imp_nomo.get(l+1), j-1,1);
           				 tab2.getTable().setValueAt(list_imp_nomo.get(l+2),j-1,3);		
           				 i=i+1; l=l+3;
           			 }
       				
           			 i=1;
           			j=j+1;		
           			}			
        		 
        	   }
        		 if(article_comb.getSelectedIndex()==0){
             		for(int i = rows - 1; i >=0; i--)
                 	{
                 		((DefaultTableModel) tab2.table.getModel()).removeRow(i);

                 	}
        		 }
		}
		
		 public void serialnumber(){
			 code_jtext.addKeyListener(new KeyAdapter() {
			      @SuppressWarnings("deprecation")
				public void keyReleased(KeyEvent e) {		
			    	  LineBorder border = new LineBorder ( Color.white, 1, true );
			       	   TitledBorder titl2 = new TitledBorder ( border, "Fiche Suiveuse", TitledBorder.DEFAULT_POSITION,
			       	   TitledBorder.DEFAULT_POSITION, police2, Color.white); 
			       	   pan.setBorder(titl2);
			       	   
	 if(article_comb.getSelectedIndex()!=0&&article_comb.getSelectedIndex()!=article_comb.getItemCount()-1){
			    	     article= article_comb.getSelectedItem().toString().split(" ");
		    			 code_art= article[0]; // 004
		    			  des= article_comb.getSelectedItem().toString().replace(code_art+" ", ""); // 004  	
		           	     //des= article[1]; // 004  
	       }
			    	 
	
		    	         imp.select_fiche(code_jtext.getText());	
			                String part1 = code_jtext.getText(); // 004
			    	    	   //System.out.println("gggggg"+imp.exist+"  "+imp.select_fiche_compo(part1).size());

			    	       if(imp.exist.equals("")&&imp.select_fiche_compo(part1).size()>0){
			    				 int rows = tab2.table.getRowCount(); 

			    	    	   for(int i = rows - 1; i >=0; i--)
			                 	{
			                 		((DefaultTableModel) tab2.table.getModel()).removeRow(i);

			                 	}
			    	        code_jtext.disable();
			    	        if(valid_modif.isVisible()==false ||valid_ajou.isVisible()==false){
				                  but_modif.setVisible(true); 
				                  but_sauv.setVisible(false);
				                  retour.setVisible(true);
				                  valid_ajou.setVisible(false);
				                  valid_modif.setVisible(false);

				                  }
				              else{
				             	 valid_ajou.setVisible(false);
				             	 but_modif.setVisible(true);
				                 but_sauv.setVisible(false);
				                  retour.setVisible(true);

				              } 
			    	        but_sauv.setVisible(false);
			    	        imp.select_conteur();
			        	 	imp.conteur();
			                chaine_comb.setSelectedItem(imp.chaine+" "+imp.chaine_design);
			                poste_comb.setSelectedItem(imp.cpost+" "+imp.intitule);
			                article_comb.setSelectedItem(imp.article+" "+imp.designation);
			                assemblage_jtext.setText(imp.contass);
			                assqc_jtext.setText(imp.contqc);
			                actqc_jtext.setText(imp.actqcc);
			                code_jtext.disable();
		    	        	 article_comb.disable();
		    	        	 poste_comb.disable();
		    	        	 list_imp=imp.select_fiche_compo(part1);
		            	     for(i=0;i<list_imp.size();i=i+4){
		            	     tab2.ajouter();
		      		         int k=tab2.table.getRowCount();
		      		         	//imp.select_fiche_compo	(part1);
		      		         	tab2.table.setValueAt("", k-1,2);
		              		 	
		              		 	}        			  	

		              			int j=1;
		              			int l = 0; i=1;
		              			while(l<list_imp.size()){
		              			//System.out.println(imp.select_fiche_compo(part1).get(l+2));
		              			 while(l<j*4){
		              				// System.out.println("eeeeee"+imp.select_fiche_compo(part1).get(l+1));
		              				 tab2.getTable().setValueAt(list_imp.get(l), j-1,0);
		              				 tab2.getTable().setValueAt(list_imp.get(l+1), j-1,1);
		              				 tab2.getTable().setValueAt(list_imp.get(l+2),j-1,2);
		              				 tab2.getTable().setValueAt(list_imp.get(l+3),j-1,3);
		              				 i=i+1; l=l+4;
		              			 }
		          				
		              			 i=1;
		              			j=j+1;		
		              			}	
		           		 }
			    	        
			    	       else if(!imp.exist.equals("")){			    	       
			    				 JOptionPane.showMessageDialog(null,"La fiche n'existe pas");
			    	        	 article_comb.enable();
			    	        	 poste_comb.enable();
			    	        	 artcomb();
			    	        	 if(valid_modif.isVisible()==false||valid_ajou.isVisible()==false){
					        	       but_sauv.setVisible(true);
					        	       valid_ajou.setVisible(false);
					                   valid_modif.setVisible(false);
						               retour.setVisible(true);
					        	     }
				                  retour.setVisible(true);
					        	  but_modif.setVisible(false);
			    
				                 	NumberFormat formatter = new DecimalFormat("00");
				                 	int cz=0;
				                 	if(code_jtext.getText().length()>6&&isValid(code_jtext.getText().substring(6,7 ))){
					    	         chaine_comb.disable();
					    	        // System.out.println("fdsgdfg"+chaine);
				                 	 cz=Integer.parseInt(code_jtext.getText().substring(6,7 ));
				                 	 }
				                 	 String chaine=  formatter.format(cz);
				                 	// System.out.println("   "+chaine);
							    	 imp.chaine("C"+chaine);
				                 	 chaine_comb.setSelectedItem("C"+chaine+" "+imp.des_chaine);
				                 	 imp.select_conteur();
						        	 imp.conteur();
						        	 NumberFormat form1 = new DecimalFormat("0");
										String sf=form1.format(imp.ass/ imp.assqc );
										assqc=imp.select_count_assqc();
										ligne=imp.count_ligne();
										int po=ligne % Integer.parseInt(sf);
										 if(po==0){
											  if(assqc==3) assqc=1;
											   else{ assqc=assqc+1;
											   }
											   		   }else{ 
											   			assqc=assqc+0;
										 }
						            	assemblage_jtext.setText(""+ imp.count_ass);
						            	actqc_jtext.setText(""+assqc);			
						            	assqc_jtext.setText(""+imp.imp_assqc);
						            	code_jtext.disable();

						            	
			    	         }
			      
			      else if(imp.exist.equals("")&&imp.select_fiche_compo(part1).size()==0){
			    	  
	    				 int rows = tab2.table.getRowCount(); 

	    	        	  for(int i = rows - 1; i >=0; i--)
		                 	{
		                 		((DefaultTableModel) tab2.table.getModel()).removeRow(i);

		                 	}
		    	        	 artcomb();

	    	        	  code_jtext.disable();
		    	        if(valid_modif.isVisible()==false ||valid_ajou.isVisible()==false){
			                  but_modif.setVisible(true); 
			                  but_sauv.setVisible(false);
			                  retour.setVisible(true);
			                  valid_ajou.setVisible(false);
			                  valid_modif.setVisible(false);

			                  }
			              else{
			             	 valid_ajou.setVisible(false);
			             	 but_modif.setVisible(true);
			                 but_sauv.setVisible(false);
			                  retour.setVisible(true);

			              } 
		    	        but_sauv.setVisible(false);
		    	        imp.select_conteur();
		        	 	imp.conteur();
		                chaine_comb.setSelectedItem(imp.chaine+" "+imp.chaine_design);
		                poste_comb.setSelectedItem(imp.cpost+" "+imp.intitule);
		                article_comb.setSelectedItem(imp.article+" "+imp.designation);
		                assemblage_jtext.setText(imp.contass);
		                assqc_jtext.setText(imp.contqc);
		                actqc_jtext.setText(imp.actqcc);
		                code_jtext.disable();
	    	        	 article_comb.disable();
	    	        	 poste_comb.disable();
			    	  
			      }

			      } } );
			 
}
		 public boolean isValid(String chaine) {
				try {
					Integer.parseInt(chaine);
				} catch (NumberFormatException e){
					return false;
				}

				return true;
			} 
		 

		
}
