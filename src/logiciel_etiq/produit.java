package logiciel_etiq;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;


public class produit extends JFrame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String    CTRL_J                = "CTRL+J";
	   final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);
@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
	 

   // gestion de l'action
  
    if (e.getActionCommand().equals(CTRL_J)){
    	  LineBorder border = new LineBorder ( Color.white, 1, true );
     	  TitledBorder titl2 = new TitledBorder ( border, "Edition Produit", TitledBorder.DEFAULT_POSITION,
         TitledBorder.DEFAULT_POSITION, police2, Color.white);
         pan_gauche_droite.setBorder(titl2);
   	but_sauv.setVisible(true);
	but_modif.setVisible(false);
	but_sup.setVisible(false);
	retour.setVisible(false);
	valid_ajou.setVisible(false);
	valid_modif.setVisible(false);
	valid_supp.setVisible(false);
	
	code_jtext.setText("");
	desin_jtext.setText("");
	dimension_jtext.setText("");
	quantite_jtext.setText("0");
	  numcontrat.setText("");
	 model.setText("");
	fournisseur_jcom.setSelectedIndex(0);
	type_jcom.setSelectedIndex(0);
	date_lc.setDate(Calendar.getInstance().getTime());
	date_lanc.setDate(Calendar.getInstance().getTime());
	
	profil_jcom.setSelectedIndex(0);
	code_jtext.enable();
	dimension_jtext.enable();
	fournisseur_jcom.enable();
	quantite_jtext.enable();
	model.enable();
	desin_jtext.enable();
	type_jcom.enable();
	date_lc.getEditor().enable();
	date_lanc.getEditor().enable();
	profil_jcom.enable();
	  numcontrat.enable();
	profil_jcom.setEditable(true);
	
	produit_jcom.setSelectedIndex(0);
    }}

private static ArrayList<String> list_produit_tr=new ArrayList<String>();
private static ArrayList<String> list_type=new ArrayList<String>();

	//******************les label**********************************************************
	private JLabel code_lab= new JLabel("Code produit "); 
	private JLabel desin_lab= new JLabel("Designation "); 
	private JLabel dimension_lab= new JLabel("Dimension "); 
	private JLabel fournisseur_lab= new JLabel("Fournisseur "); 
	private JLabel datelc_lab= new JLabel("Date LC"); 
	private JLabel datelanc_lab= new JLabel("Date Lancement "); 
	private JLabel quantite_lab= new JLabel("Quantite "); 
	private JLabel type_lab= new JLabel("Type  "); 
	private JLabel profil_lab = new JLabel("Processe");
	private JLabel produit_lab = new JLabel("Produit");
	//*******************composant*************************************************
	//*************jtext****************************
	private JTextField code_jtext=new JTextField();
	private JTextField dimension_jtext=new JTextField();
	private JTextField desin_jtext=new JTextField();
	private JTextField quantite_jtext=new JTextField();
    private JLabel numcontrat_lab=new JLabel("N Contrat");
	private JTextField numcontrat=new JTextField();
	private JPanel pan_numcontrat=new JPanel();
	private JPanel pan_numcontrat_lab=new JPanel();
	private JPanel pan_numcontrat_jtext=new JPanel();
	//***************************************************************************
	//***********************combobox********************************************
	private jcombo fournisseur_jcom;
	ArrayList<String> list_fo ;
	private jcombo produit_jcom;
	ArrayList<String> list_pro ;
	
	private JComboBox<String> profil_jcom= new JComboBox<String>();
	ArrayList<String> list_profil;
	private JComboBox<String> type_jcom= new JComboBox<String>();
	final JXDatePicker date_lc = new JXDatePicker();
	final JXDatePicker date_lanc = new JXDatePicker();
    private JTextField model=new JTextField();
	//********************************************************
    //***************les panal*****************************************************
    private JPanel pan_global;
    private JPanel pan_gauche=new JPanel();
    private JPanel pan_droite=new JPanel();
    private JPanel pan_gauche_droite=new JPanel();
    private JPanel pan_code=new JPanel();
    private JPanel pan_code_lab=new JPanel();
    private JPanel pan_code_jtext=new JPanel();
    
    private JPanel pan_desin=new JPanel();
    private JPanel pan_desin_lab=new JPanel();
    private JPanel pan_desin_jtext=new JPanel();
    
    private JPanel pan_dimension=new JPanel();
    private JPanel pan_dimension_lab=new JPanel();
    private JPanel pan_dimension_jtext=new JPanel();
     
    private JPanel pan_quantite=new JPanel();
    private JPanel pan_quantite_lab=new JPanel();
    private JPanel pan_quantite_jtext=new JPanel();
    
    private JPanel pan_fournisseur=new JPanel();
    private JPanel pan_fournisseur_lab=new JPanel();
    private JPanel pan_fournisseur_jcom=new JPanel();
    
    private JPanel pan_profil=new JPanel();
    private JPanel pan_profil_lab=new JPanel();
    private JPanel pan_profil_jcom=new JPanel();
    
    private JPanel pan_produit=new JPanel();
    private JPanel pan_produit_lab=new JPanel();
    private JPanel pan_produit_jcom=new JPanel();
    
    private JPanel pan_type=new JPanel();
    private JPanel pan_type_lab=new JPanel();
    private JPanel pan_type_jcom=new JPanel();
    
    private JPanel pan_datelc=new JPanel();
    private JPanel pan_datelc_lab=new JPanel();
    private JPanel pan_datelc_dp=new JPanel();
    
    private JPanel pan_datelanc=new JPanel();
    private JPanel pan_datelanc_lab=new JPanel();
    private JPanel pan_datelanc_dp=new JPanel();
    
    private JPanel pan_model_lab =new JPanel();
    private JPanel pan_model_jtext =new JPanel();

    
    private JPanel pan_button=new JPanel();
    
    private JButton but_sauv=new JButton("Ajouter");
    private JButton but_modif=new JButton("Modifier");
    private JButton but_sup=new JButton("Supprimer");
    private JButton valid_ajou=new JButton("Valider");
    private JButton valid_modif=new JButton("Valider");
    private JButton valid_supp=new JButton("Valider");
    private JButton retour=new JButton("Retour");
   // *************************** les variable**********************************
    String part_fournisseur, part_profil ,smarts,msg;
    gestion_produit prod=new gestion_produit();
   //************************************************************** 
    
    private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";	
    produit(final String log){
    	pan_global= new JPanel(){   
   			private static final long serialVersionUID = 1L;
   			public void paintComponent(Graphics g){
   	  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
   	             img.paintIcon(this, g,0, 0);
   	  	}  };
   	 pan_global.registerKeyboardAction( this, CTRL_J, KeyStroke.getKeyStroke(
	            KeyEvent.VK_J, Event.CTRL_MASK),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);
   	 
    	  date_lc.setDate(Calendar.getInstance().getTime());
    	  date_lanc.setDate(Calendar.getInstance().getTime());
    	date_lc.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
    	date_lanc.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
       

    	date_lanc.getEditor().setEditable(false);
    	date_lc.getEditor().setEditable(false);
    	selectioncomb.windows(this,log,laf);
    	if(!selectioncomb.prv.contains("produit")){
			//System.out.println("article"+selectioncomb.prv);
		 selectioncomb.prv.add("produit");}
    	
    	but_sauv.setVisible(true);
    	but_modif.setVisible(false);
    	but_sup.setVisible(false);
    	retour.setVisible(false);
    	valid_ajou.setVisible(false);
    	valid_modif.setVisible(false);
    	valid_supp.setVisible(false);

    	
    	list_fo = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un fournisseur-----"}));
   	    fournisseur_jcom = new jcombo(list_fo.toArray());
   	    selectioncomb.selectfourniss(fournisseur_jcom  ,this,log);
   	   
   	   type_jcom.addItem("---Selectionner un Type-----");
   	   list_type=prod.select_produit_type();

	 for(int i=0;i<list_type.size();i++)
	 {
	        //Pour affecter une valeur de base de donn�es � un Combobox 
		  type_jcom.addItem(list_type.get(i));

	 }
	   list_profil = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un processe-----"}));
	   profil_jcom.addItem("---Selectionner un processe-----");
 	 list_profil=prod.select_profils();
		   for(int i=0;i<list_profil.size();i++)
		   {
		          //Pour affecter une valeur de base de donn�es � un Combobox 
			   profil_jcom.addItem(list_profil.get(i));
			   
		   }
		   
   	
   	  
   	
   	   list_pro= new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un produit-----"}));
   	   produit_jcom = new jcombo(list_pro.toArray());
//   	    selectioncomb.selectproduit(produit_jcom  ,this,log);
   	   
   	//produit_jcom.addItem("--S�lectionner un Produit--");
   	list_produit_tr=prod.select_produit_code();

	 for(int i=0;i<list_produit_tr.size();i++)
	 {
	        //Pour affecter une valeur de base de donn�es � un Combobox 
		  produit_jcom.addItem(list_produit_tr.get(i)+" "+list_produit_tr.get(i+1));
		  i++;
	 }
	 produit_jcom.setWide(true);
	
   	     profil_jcom.setEditable(true);
   	     
    	 profil_jcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean ex=exist(profil_jcom,profil_jcom.getEditor().getItem().toString());
				if((ex==false))
				{
					int cont =profil_jcom.getItemCount()+2;System.out.println("hhhhhh"+cont);
					profil_jcom.addItem(profil_jcom.getEditor().getItem().toString());
					prod.insertion_profils(cont,profil_jcom.getEditor().getItem().toString());
				}
				
				
			}});
    	
        	produit_jcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String part_prode;
				if(produit_jcom.getSelectedIndex()!=0&&produit_jcom.getItemCount()!=0){
					System.out.println("ggggggggg"+produit_jcom.getSelectedIndex());
					but_modif.setVisible(true);
					but_sup.setVisible(true);
					but_sauv.setVisible(false);
					retour.setVisible(false);
	            	valid_ajou.setVisible(false);
	            	valid_modif.setVisible(false);
	            	valid_supp.setVisible(false);
	            	
	            	
	            	  String[] part_prod = produit_jcom.getSelectedItem().toString().split(" ");
             		  part_prode = part_prod[0];
             		  System.out.println("eeee"+part_prode);
             		  prod.select_produit(part_prode);
             		  code_jtext.setText(prod.code);
             		  desin_jtext.setText(prod.desin);
             		  dimension_jtext.setText(prod.dimension);
             		  numcontrat.setText(prod.Ncontrat);
             		// model.setText(prod.model);
             		  fournisseur_jcom.setSelectedItem(prod.fournisseur);
             		  type_jcom.setSelectedItem(prod.type);
             		  quantite_jtext.setText(prod.quantite);
             		  date_lc.getEditor().setText(prod.datelc);
             		  date_lanc.getEditor().setText(prod.datelanc);
             		  profil_jcom.setSelectedItem(prod.profil);
				
				}
				else{
				code_jtext.setText("");
           		  desin_jtext.setText("");
           		  dimension_jtext.setText("");
           		  fournisseur_jcom.setSelectedIndex(0);
           		  type_jcom.setSelectedIndex(0);
           		  quantite_jtext.setText("0");
           		//model.setText("");
           		numcontrat.setText("");
           		  date_lc.setDate(Calendar.getInstance().getTime());
          	      date_lanc.setDate(Calendar.getInstance().getTime());
           	
           		  profil_jcom.setSelectedIndex(0);
					
				but_modif.setVisible(false);
				but_sup.setVisible(false);
				but_sauv.setVisible(true);
				retour.setVisible(false);
            	valid_ajou.setVisible(false);
            	valid_modif.setVisible(false);
            	valid_supp.setVisible(false);}
			}});
    	
    	retour.addActionListener(
	            new ActionListener() { 
	            @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
	            	  LineBorder border = new LineBorder ( Color.white, 1, true );
	             	  TitledBorder titl2 = new TitledBorder ( border, "Edition Produit", TitledBorder.DEFAULT_POSITION,
	                 TitledBorder.DEFAULT_POSITION, police2, Color.white);
	                 pan_gauche_droite.setBorder(titl2);
	            	but_sauv.setVisible(true);
	            	but_modif.setVisible(false);
	            	but_sup.setVisible(false);
	            	retour.setVisible(false);
	            	valid_ajou.setVisible(false);
	            	valid_modif.setVisible(false);
	            	valid_supp.setVisible(false);
	            	  numcontrat.setText("");
	            	//  model.setText("");
	            	code_jtext.setText("");
	            	desin_jtext.setText("");
	            	dimension_jtext.setText("");
	            	quantite_jtext.setText("0");
	            	fournisseur_jcom.setSelectedIndex(0);
	            	type_jcom.setSelectedIndex(0);
	            	date_lc.setDate(Calendar.getInstance().getTime());
	            	date_lanc.setDate(Calendar.getInstance().getTime());
	            	profil_jcom.setSelectedIndex(0);
	            	code_jtext.enable();
	            	dimension_jtext.enable();
	            	fournisseur_jcom.enable();
	            	quantite_jtext.enable();
	            	desin_jtext.enable();
	            	type_jcom.enable();
	            	  numcontrat.enable();
	            	 // model.enable();
	            	date_lc.getEditor().enable();
	            	date_lanc.getEditor().enable();
	            	profil_jcom.enable();
	            	profil_jcom.setEditable(true);
	            	produit_jcom.setSelectedIndex(0);
	            }});
    	
    	
    	but_modif.addActionListener(
	            new ActionListener() { 
	            @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
	            	LineBorder border = new LineBorder ( Color.white, 1, true );
	             	TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
	                TitledBorder.DEFAULT_POSITION, police2, Color.white);
	                pan_gauche_droite.setBorder(titl2);
	            	but_sauv.setVisible(false);
	            	but_modif.setVisible(false);
	            	but_sup.setVisible(false);
	            	retour.setVisible(true);
	            	valid_ajou.setVisible(false);
	            	valid_modif.setVisible(true);
	            	valid_supp.setVisible(false);
	            	code_jtext.enable();
	            	dimension_jtext.enable();
	            	fournisseur_jcom.enable();
	            	quantite_jtext.enable();
	            	desin_jtext.enable();
	            	type_jcom.enable();
	            	 numcontrat.enable();
	            	date_lc.getEditor().enable();
	            	date_lanc.getEditor().enable();
	            	profil_jcom.enable();
	            //	model.enable();
	            }});
    	
    	
    	but_sup.addActionListener(
	            new ActionListener() { 
	            @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
	            	  LineBorder border = new LineBorder ( Color.white, 1, true );
	             	  TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
	                 TitledBorder.DEFAULT_POSITION, police2, Color.white);
	                 pan_gauche_droite.setBorder(titl2);
	            	but_sauv.setVisible(false);
	            	but_modif.setVisible(false);
	            	but_sup.setVisible(false);
	            	retour.setVisible(true);
	            	valid_ajou.setVisible(false);
	            	valid_modif.setVisible(false);
	            	valid_supp.setVisible(true);
	            	code_jtext.disable();
	            	 numcontrat.disable();
	            	dimension_jtext.disable();
	            	fournisseur_jcom.disable();
	            	quantite_jtext.disable();
	            	desin_jtext.disable();
	            	type_jcom.disable();
	            	date_lc.getEditor().disable();
	            	date_lanc.getEditor().disable();
	            	profil_jcom.disable();
	            //	model.disable();
	            	profil_jcom.setEditable(false);
	            	
	            }});
    	
    	
    	but_sauv.addActionListener(
	            new ActionListener() { 
	            @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
	            	  LineBorder border = new LineBorder ( Color.white, 1, true );
	             	  TitledBorder titl2 = new TitledBorder ( border, "Ajout", TitledBorder.DEFAULT_POSITION,
	                 TitledBorder.DEFAULT_POSITION, police2, Color.white);
	                 pan_gauche_droite.setBorder(titl2);
	            	but_sauv.setVisible(false);
	            	but_modif.setVisible(false);
	            	but_sup.setVisible(false);
	            	retour.setVisible(true);
	            	valid_ajou.setVisible(true);
	            	valid_modif.setVisible(false);
	            	valid_supp.setVisible(false);
	            	code_jtext.enable();
	            	dimension_jtext.enable();
	            	fournisseur_jcom.enable();
	            	numcontrat.enable();
	            	quantite_jtext.enable();
	            	desin_jtext.enable();
	            	type_jcom.enable();
	            	date_lc.getEditor().enable();
	            	date_lanc.getEditor().enable();
	            	profil_jcom.enable();
	            	//model.enable();
	            	
	            }});
    	
    	 valid_ajou.addActionListener(
		           new ActionListener() { 
		           public void actionPerformed(ActionEvent e) {
		        	   
		        	   msg = "";
						if (code_jtext.getText().equals("")) {
							msg += "Vous Devez remplir le code produit\n";
						} else if (desin_jtext.getText().equals("")) {
							msg += "Vous Devez remplir la designation\n";
						}
//						else if (dimension_jtext.getText().equals("")) {
//							msg += "Vous Devez remplir la dimension\n";
//						}
//						else if (quantite_jtext.getText().equals("")) {
//							msg += "Vous Devez remplir la quantite\n";
//						}
						
						else if (profil_jcom.getSelectedIndex()==0) {
							msg += "Vous Devez selectionner ou ajouter un processe\n";
						}
						
						else if (fournisseur_jcom.getSelectedIndex()==0) {
							msg += "Vous Devez selectionner  un fournisseur\n";
						}
						
//						else if (type_jcom.getSelectedIndex()==0) {
//							msg += "Vous Devez selectionner  un type\n";
//						}
//						else if (model.getText().equals("")) {
//							msg += "Vous Devez saisir le mod�le\n";
//						}
					
						else if(!isValid(quantite_jtext.getText())){msg+="La quantite doit etre un nombre \n";}
						if (!msg.equals("")) {
							JOptionPane.showMessageDialog(null, msg);
						} else {
		        		
		        	      String[] part_fourn = fournisseur_jcom.getSelectedItem().toString().split(" ");
	             		  part_fournisseur = part_fourn[0];
	             		
	             	int part_profi = profil_jcom.getSelectedIndex();
	             	System.out.println(part_profi);
	             	
		        	prod.ajouter_produit(code_jtext.getText(),desin_jtext.getText(),dimension_jtext.getText(),
		            part_fournisseur ,date_lc.getEditor().getText(),date_lanc.getEditor().getText(),
           			Integer.parseInt(quantite_jtext.getText()),part_profi,type_jcom.getSelectedItem().toString(),
           			numcontrat.getText());
		        	JOptionPane.showMessageDialog(null, "Le produit a ete bien ajouter");
		        	produit_jcom.removeAllItems();
		        	produit_jcom.addItem("--S�lectionner un Produit--");
		           	list_produit_tr=prod.select_produit_code();
		        	 for(int i=0;i<list_produit_tr.size();i++)
		        	 {
		        	        //Pour affecter une valeur de base de donn�es � un Combobox 
		        		  produit_jcom.addItem(list_produit_tr.get(i)+" "+list_produit_tr.get(i+1));
		        		  i++;
		        	 }
		        	 
					 produit_jcom.setWide(true);
						}
		           }});
    	 
    	 valid_modif.addActionListener(
    			 
		           new ActionListener() { 
		           public void actionPerformed(ActionEvent e) {
		          	   msg = "";
		    						if (code_jtext.getText().equals("")) {
		    							msg += "Vous Devez remplir le code produit\n";
		    						} else if (desin_jtext.getText().equals("")) {
		    							msg += "Vous Devez remplir la designation\n";
		    						}
//		    						else if (dimension_jtext.getText().equals("")) {
//		    							msg += "Vous Devez remplir la dimension\n";
//		    						}
//		    						else if (quantite_jtext.getText().equals("")) {
//		    							msg += "Vous Devez remplir la quantite\n";
//		    						}
		    						
		    						else if (profil_jcom.getSelectedIndex()==0) {
		    							msg += "Vous Devez selectionner ou ajouter un processe\n";
		    						}
		    						
		    						else if (fournisseur_jcom.getSelectedIndex()==0) {
		    							msg += "Vous Devez selectionner  un fournisseur\n";
		    						}
		    						
//		    						else if (type_jcom.getSelectedIndex()==0) {
//		    							msg += "Vous Devez selectionner  un type\n";
//		    						}
		    					
		    						else if(!isValid(quantite_jtext.getText())){msg+="La quantite doit etre un nombre \n";}
		    						if (!msg.equals("")) {
		    							JOptionPane.showMessageDialog(null, msg);
		    						} else {
		    							   String[] part_fourn = fournisseur_jcom.getSelectedItem().toString().split(" ");
		    			             		  part_fournisseur = part_fourn[0];
		    			             		  
		    			             			int part_profi = profil_jcom.getSelectedIndex();
		        		
		        		String[] part_prods = produit_jcom.getSelectedItem().toString().split(" ");
	             		 String  part_pro = part_prods[0];
		        	   System.out.println(part_pro);
		        	   prod.update_produit(part_pro,desin_jtext.getText(),dimension_jtext.getText(),
			            part_fournisseur ,date_lc.getEditor().getText(),date_lanc.getEditor().getText(),
			            Integer.parseInt(quantite_jtext.getText()),part_profi,type_jcom.getSelectedItem().toString(),code_jtext.getText(),numcontrat.getText());
		        	   JOptionPane.showMessageDialog(null, "Le produit a ete bien modifier");
		        	   produit_jcom.removeAllItems();
			        	produit_jcom.addItem("--S�lectionner un Produit--");
			           	list_produit_tr=prod.select_produit_code();
			        	 for(int i=0;i<list_produit_tr.size();i++)
			        	 {
			        	        //Pour affecter une valeur de base de donn�es � un Combobox 
			        		  produit_jcom.addItem(list_produit_tr.get(i)+" "+list_produit_tr.get(i+1));
			        		  i++;
			        	 }
			        	 
						 produit_jcom.setWide(true);
		    						}
		    						
		        	   
		           }});
    	 
valid_supp.addActionListener(
        new ActionListener() { 
        @SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
        	prod.delete_produit(code_jtext.getText());
        	JOptionPane.showMessageDialog(null, "Le produit a ete bien supprimer");
        	
        	produit_jcom.removeAllItems();
        	produit_jcom.addItem("--S�lectionner un Produit--");
           	list_produit_tr=prod.select_produit_code();
        	 for(int i=0;i<list_produit_tr.size();i++)
        	 {
        	        //Pour affecter une valeur de base de donn�es � un Combobox 
        		  produit_jcom.addItem(list_produit_tr.get(i)+" "+list_produit_tr.get(i+1));
        		  i++;
        	 }
        	 
			 produit_jcom.setWide(true);
			 
				code_jtext.enable();
            	dimension_jtext.enable();
            	fournisseur_jcom.enable();
            	quantite_jtext.enable();
            	desin_jtext.enable();
            	type_jcom.enable();
            	date_lc.getEditor().enable();
            	date_lanc.getEditor().enable();
            	profil_jcom.enable();
            	//model.enable();
            	numcontrat.enable();
            	profil_jcom.setEditable(true);

        }});


    	 
    	code_jtext.addActionListener(  new ActionListener() { 
		           public void actionPerformed(ActionEvent e) {
		        	   if(!code_jtext.getText().equals("")){
							but_modif.setVisible(true);
							but_sup.setVisible(true);
							but_sauv.setVisible(false);
							retour.setVisible(false);
			            	valid_ajou.setVisible(false);
			            	valid_modif.setVisible(false);
			            	valid_supp.setVisible(false);
			            	
			            	 numcontrat.setText(prod.Ncontrat);
			            	  
		             		  prod.select_produit(code_jtext.getText());
		             		  produit_jcom.setSelectedItem(prod.code+" "+prod.desin);
		             		  code_jtext.setText(prod.code);
		             		//  model.setText(prod.model);
		             		  desin_jtext.setText(prod.desin);
		             		  dimension_jtext.setText(prod.dimension);
		             		  fournisseur_jcom.setSelectedItem(prod.fournisseur);
		             		  type_jcom.setSelectedItem(prod.type);
		             		  quantite_jtext.setText(prod.quantite);
		             		  date_lc.getEditor().setText(prod.datelc);
		             		  date_lanc.getEditor().setText(prod.datelanc);
		             		 
		             		  profil_jcom.setSelectedItem(prod.profil);
						
						}
						else{
						  code_jtext.setText("");
		           		  desin_jtext.setText("");
		           		  dimension_jtext.setText("");
		           	      numcontrat.setText("");
		           	 //     model.setText("");
		           		  fournisseur_jcom.setSelectedIndex(0);
		           		  type_jcom.setSelectedIndex(0);
		           		  quantite_jtext.setText("0");
		           		  date_lc.setDate(Calendar.getInstance().getTime());
		          	      date_lanc.setDate(Calendar.getInstance().getTime());
		           		  profil_jcom.setSelectedIndex(0);
							
						but_modif.setVisible(false);
						but_sup.setVisible(false);
						but_sauv.setVisible(true);
						retour.setVisible(false);
		            	valid_ajou.setVisible(false);
		            	valid_modif.setVisible(false);
		            	valid_supp.setVisible(false);}
		        	   
		           }});
    	produit_jcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String part_prode;
				if(produit_jcom.getSelectedIndex()!=0&&produit_jcom.getItemCount()!=0){
					but_modif.setVisible(true);
					but_sup.setVisible(true);
					but_sauv.setVisible(false);
					retour.setVisible(false);
	            	valid_ajou.setVisible(false);
	            	valid_modif.setVisible(false);
	            	valid_supp.setVisible(false);
	            	
	            	
	            	  String[] part_prod = produit_jcom.getSelectedItem().toString().split(" ");
             		  part_prode = part_prod[0];
             		  System.out.println("eeee"+part_prode);
             		  prod.select_produit(part_prode);
             		  code_jtext.setText(prod.code);
             		  desin_jtext.setText(prod.desin);
             		  dimension_jtext.setText(prod.dimension);
             		  fournisseur_jcom.setSelectedItem(prod.fournisseur);
             		  type_jcom.setSelectedItem(prod.type);
             		  quantite_jtext.setText(prod.quantite);
             		  date_lc.getEditor().setText(prod.datelc);
             		  date_lanc.getEditor().setText(prod.datelanc);
             		  numcontrat.setText(prod.Ncontrat);
             		//  model.setText(prod.model);
             		  profil_jcom.setSelectedItem(prod.profil);
				
				}
				else{
					  numcontrat.setText("");
					//  model.setText("");

			    	code_jtext.setText("");
           		  desin_jtext.setText("");
           		  dimension_jtext.setText("");
           		  fournisseur_jcom.setSelectedIndex(0);
           		  type_jcom.setSelectedIndex(0);
           		  quantite_jtext.setText("0");
           		  date_lc.setDate(Calendar.getInstance().getTime());
          	      date_lanc.setDate(Calendar.getInstance().getTime());
           		  profil_jcom.setSelectedIndex(0);
					
				but_modif.setVisible(false);
				but_sup.setVisible(false);
				but_sauv.setVisible(true);
				retour.setVisible(false);
            	valid_ajou.setVisible(false);
            	valid_modif.setVisible(false);
            	valid_supp.setVisible(false);}
			}});
    
   	  
	  
	   try{
	   		UIManager.setLookAndFeel(laf);
	   		SwingUtilities.updateComponentTreeUI(this);
	   	//	ComboBoxUI.setLookAndFeel(laf);
	   	}
	     catch(Exception e1){
	   		e1.printStackTrace();
	   	}
	   
	   numcontrat_lab.setForeground(Color.white);
	   numcontrat_lab.setFont(police2);
		pan_produit.add(pan_produit_lab);
		pan_produit.add(pan_produit_jcom);
		pan_produit_lab.add(produit_lab);
		pan_produit_jcom.add(produit_jcom);
	//	model_lab.setFont(police2);
   	  	pan_code.add(pan_code_lab);
   	  	pan_code.add(pan_code_jtext);
   	  	pan_code_lab.add(code_lab);
   	  	pan_code_jtext.add(code_jtext);
   	  	
   	  	pan_desin.add(pan_desin_lab);
   	    pan_desin.add(pan_desin_jtext);
   	  	pan_desin_lab.add(desin_lab);
   	  	pan_desin_jtext.add(desin_jtext);
   	  	
   	    pan_dimension.add(pan_dimension_lab);
   	  	pan_dimension.add(pan_dimension_jtext);
   	  	pan_dimension_lab.add(dimension_lab);
   	  	pan_dimension_jtext.add(dimension_jtext);
   	  	
   	  	pan_fournisseur.add(pan_fournisseur_lab);
   	  	pan_fournisseur.add(pan_fournisseur_jcom);
   	    pan_fournisseur_lab.add(fournisseur_lab);
   	    pan_fournisseur_jcom.add(fournisseur_jcom);
   	    
   	    
   	  pan_numcontrat.add(pan_numcontrat_lab);
      pan_numcontrat.add(pan_numcontrat_jtext);
      pan_numcontrat_lab.add(numcontrat_lab);
      pan_numcontrat_jtext.add(numcontrat);
      
   	  	
   	  	pan_profil.add(pan_profil_lab);
   	  	pan_profil.add(pan_profil_jcom);
   	    pan_profil_lab.add(profil_lab);
   	    pan_profil_jcom.add(profil_jcom);
   	    
   	    pan_quantite.add(pan_quantite_lab);
	  	pan_quantite.add(pan_quantite_jtext);
	    pan_quantite_lab.add(quantite_lab);
	    pan_quantite_jtext.add(quantite_jtext);
	    
	    pan_type.add(pan_type_lab);
	    pan_type.add(pan_type_jcom);
	    pan_type_lab.add(type_lab);
	    pan_type_jcom.add(type_jcom);
	    
	    pan_datelc.add(pan_datelc_lab);
	    pan_datelc.add(pan_datelc_dp);
	    pan_datelc_lab.add(datelc_lab);
	    pan_datelc_dp.add(date_lc);
	    
	    pan_datelanc.add(pan_datelanc_lab);
	    pan_datelanc.add(pan_datelanc_dp);
	    pan_datelanc_lab.add(datelanc_lab);
	    pan_datelanc_dp.add(date_lanc);
	    
	//    pan_model.add(pan_model_lab);
//	    pan_model.add(pan_model_jtext);
//	    pan_model_lab.add(model_lab);
//	    pan_model_jtext.add(model);
	    
	  
	    pan_global.add(pan_gauche_droite);
	    pan_button.add(but_modif);
	    pan_button.add(but_sauv);
	    pan_button.add(but_sup);
	    pan_button.add(retour);
	    pan_button.add(valid_ajou);
	    pan_button.add(valid_modif);
	    pan_button.add(valid_supp);
	   
	    
	    pan_gauche.add(pan_produit);
	    pan_gauche.add(pan_code);
	    pan_gauche.add(pan_desin);
	    pan_gauche.add(pan_fournisseur);
	    pan_gauche.add(pan_dimension);
	    pan_gauche.add(pan_quantite);
	    
	    pan_droite.add(pan_datelanc);
	    pan_droite.add(pan_datelc);
	    pan_droite.add(pan_profil);
	    pan_droite.add(pan_type);
	   // pan_droite.add(pan_model);
	    pan_droite.add(pan_numcontrat);
	    
	    pan_gauche_droite.add(pan_gauche);
	    pan_gauche_droite.add(pan_droite);
	    
	    pan_global.add(pan_button);
        
	    pan_global.setLayout(new BoxLayout(pan_global,BoxLayout.Y_AXIS));
	    pan_button.setLayout(new BoxLayout(pan_button,BoxLayout.X_AXIS));
	    pan_gauche_droite.setLayout(new BoxLayout(pan_gauche_droite,BoxLayout.X_AXIS));
	    pan_gauche.setLayout(new BoxLayout(pan_gauche,BoxLayout.Y_AXIS));
	    pan_droite.setLayout(new BoxLayout(pan_droite,BoxLayout.Y_AXIS));
	    
	    pan_produit.setLayout(new BoxLayout(pan_produit,BoxLayout.X_AXIS));
	    pan_code.setLayout(new BoxLayout(pan_code,BoxLayout.X_AXIS));
	    pan_desin.setLayout(new BoxLayout(pan_desin,BoxLayout.X_AXIS));
	    pan_dimension.setLayout(new BoxLayout(pan_dimension,BoxLayout.X_AXIS));
	    pan_fournisseur.setLayout(new BoxLayout(pan_fournisseur,BoxLayout.X_AXIS));
	    pan_quantite.setLayout(new BoxLayout(pan_quantite,BoxLayout.X_AXIS));
	    pan_datelc.setLayout(new BoxLayout(pan_datelc,BoxLayout.X_AXIS));
	    pan_datelanc.setLayout(new BoxLayout(pan_datelanc,BoxLayout.X_AXIS));
	    pan_profil.setLayout(new BoxLayout(pan_profil,BoxLayout.X_AXIS));
	    pan_type.setLayout(new BoxLayout(pan_type,BoxLayout.X_AXIS));
	 //   pan_model.setLayout(new BoxLayout(pan_model,BoxLayout.X_AXIS));
	    
	    pan_numcontrat.setLayout(new BoxLayout(pan_numcontrat,BoxLayout.X_AXIS));
	    
	    pan_numcontrat_lab.setBorder(BorderFactory.createEmptyBorder(0, 55, 0,0));
        pan_numcontrat_jtext.setBorder(BorderFactory.createEmptyBorder(0, 50, 0,0));

        pan_numcontrat_lab.setBorder(BorderFactory.createEmptyBorder(0, 55, 0,0));
        pan_numcontrat_jtext.setBorder(BorderFactory.createEmptyBorder(0, 50, 0,0));
	    
	    pan_code_lab.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    pan_code_jtext.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    
	    pan_produit_lab.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    pan_produit_jcom.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    
	    pan_desin_lab.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    pan_desin_jtext.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    
	    pan_dimension_lab.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    pan_dimension_jtext.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    
	    pan_quantite_lab.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    pan_quantite_jtext.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    
	    pan_fournisseur_lab.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    pan_fournisseur_jcom.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    
	    pan_type_lab.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    pan_type_jcom.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    
	    pan_datelc_lab.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    pan_datelc.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    
	    pan_profil_lab.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    pan_profil_jcom.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    
	    pan_datelanc_lab.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    pan_datelanc.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    
	///    pan_model_lab.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    pan_model_jtext.setLayout(new FlowLayout(  FlowLayout.CENTER));
	    
	    
	    pan_numcontrat_lab.setLayout( new FlowLayout( FlowLayout.LEFT));
        pan_numcontrat_jtext.setLayout(new FlowLayout(FlowLayout.CENTER));
	   //****************enlever l 'arriere plan *******************************************
	    pan_global.setOpaque(false);
	    pan_gauche_droite.setOpaque(false);
	    pan_gauche.setOpaque(false);
	    pan_droite.setOpaque(false);
	    
	    
	    pan_numcontrat.setOpaque(false);
        pan_numcontrat_lab.setOpaque(false);
        pan_numcontrat_jtext.setOpaque(false);
	    
	    pan_code.setOpaque(false);
	    pan_code_lab.setOpaque(false);
	    pan_code_jtext.setOpaque(false);
	    
	    pan_desin.setOpaque(false);
	    pan_desin_lab.setOpaque(false);
	    pan_desin_jtext.setOpaque(false);
	    
	    pan_dimension.setOpaque(false);
	    pan_dimension_lab.setOpaque(false);
	    pan_dimension_jtext.setOpaque(false);
	    
	    pan_quantite.setOpaque(false);
	    pan_quantite_lab.setOpaque(false);
	    pan_quantite_jtext.setOpaque(false);
	    
	    pan_fournisseur.setOpaque(false);
	    pan_fournisseur_lab.setOpaque(false);
	    pan_fournisseur_jcom.setOpaque(false);
	    
	    pan_profil.setOpaque(false);
	    pan_profil_lab.setOpaque(false);
	    pan_profil_jcom.setOpaque(false);
	    
	    pan_produit.setOpaque(false);
	    pan_produit_lab.setOpaque(false);
	    pan_produit_jcom.setOpaque(false);
	    
	    pan_type.setOpaque(false);
	    pan_type_lab.setOpaque(false);
	    pan_type_jcom.setOpaque(false);
	    
	    pan_datelc.setOpaque(false);
	    pan_datelc_lab.setOpaque(false);
	    pan_datelc_dp.setOpaque(false);
	    
	    pan_datelanc.setOpaque(false);
	    pan_datelanc_lab.setOpaque(false);
	    pan_datelanc_dp.setOpaque(false);
	    
	    
	 //   pan_model.setOpaque(false);
	    pan_model_lab.setOpaque(false);
	    pan_model_jtext.setOpaque(false);
	    
	    pan_button.setOpaque(false);
	    produit_jcom.setPreferredSize(new Dimension(250,30));
	    code_jtext.setPreferredSize(new Dimension(250,30));
	    desin_jtext.setPreferredSize(new Dimension(250,30));
	    dimension_jtext.setPreferredSize(new Dimension(250,30));
	    quantite_jtext.setPreferredSize(new Dimension(250,30));
	    fournisseur_jcom.setPreferredSize(new Dimension(250,30));
	    profil_jcom.setPreferredSize(new Dimension(250,30));
	    type_jcom.setPreferredSize(new Dimension(250,30));
	    date_lc.setPreferredSize(new Dimension(250,30));
	    date_lanc.setPreferredSize(new Dimension(250,30));
	    numcontrat.setPreferredSize(new Dimension(250, 30));
	  //  model.setPreferredSize(new Dimension(250, 30));
	    
	    final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

	     code_lab.setForeground(Color.white);
	     desin_lab.setForeground(Color.white);
	     dimension_lab.setForeground(Color.white);
	     quantite_lab.setForeground(Color.white);
	     fournisseur_lab.setForeground(Color.white);
	     produit_lab.setForeground(Color.white);
	     datelc_lab.setForeground(Color.white);
	     datelanc_lab.setForeground(Color.white);
	     type_lab.setForeground(Color.white);
	  //   model_lab.setForeground(Color.white);
	     profil_lab.setForeground(Color.white);

	     code_lab.setFont(police2);
	     desin_lab.setFont(police2);
	     dimension_lab.setFont(police2);
	     quantite_lab.setFont(police2);
	     fournisseur_lab.setFont(police2);
	     datelc_lab.setFont(police2);
	     datelanc_lab.setFont(police2);
	     type_lab.setFont(police2);
	 //    model_lab.setFont(police2);
	     profil_lab.setFont(police2);
	     produit_lab.setFont(police2);
	     
	     but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         but_sauv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         but_sup.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         valid_supp.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         
         but_modif.setPreferredSize(new Dimension(120, 33));
         but_sauv.setPreferredSize(new Dimension(120, 33));
         but_sup.setPreferredSize(new Dimension(120, 33));
         retour.setPreferredSize(new Dimension(120, 33));
         valid_supp.setPreferredSize(new Dimension(120, 33));
         valid_modif.setPreferredSize(new Dimension(120, 33));
         valid_ajou.setPreferredSize(new Dimension(120, 33));
         
	     LineBorder border = new LineBorder ( Color.white, 1, true );
     	  TitledBorder titl2 = new TitledBorder ( border, "Edition Produit", TitledBorder.DEFAULT_POSITION,
         TitledBorder.DEFAULT_POSITION, police2, Color.white);
         pan_gauche_droite.setBorder(titl2);
         
         LineBorder border_g = new LineBorder ( Color.white, 1, true );
    	  TitledBorder titl2_g = new TitledBorder ( border_g, "", TitledBorder.DEFAULT_POSITION,
        TitledBorder.DEFAULT_POSITION, police2, Color.white);
        pan_gauche.setBorder(titl2_g);
        
        LineBorder border_d = new LineBorder ( Color.white, 1, true );
   	  TitledBorder titl2_d = new TitledBorder ( border_d, "", TitledBorder.DEFAULT_POSITION,
       TitledBorder.DEFAULT_POSITION, police2, Color.white);
       pan_droite.setBorder(titl2_d);
       
       pan_code_lab.setBorder(BorderFactory.createEmptyBorder(20, 20, 0,-30));
       pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(20, 00, 0,0));
       
       pan_produit_lab.setBorder(BorderFactory.createEmptyBorder(10,70, 0,0));
       pan_produit_jcom.setBorder(BorderFactory.createEmptyBorder(10, -20, 0,0));
       
       pan_desin_lab.setBorder(BorderFactory.createEmptyBorder(10, 30, 0,-30));
       pan_desin_jtext.setBorder(BorderFactory.createEmptyBorder(10, 05, 0,0));
       
       pan_fournisseur_lab.setBorder(BorderFactory.createEmptyBorder(10, 30, 0,-30));
       pan_fournisseur_jcom.setBorder(BorderFactory.createEmptyBorder(10, 05, 0,0));
       
       pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(10, 43, 0,-30));
       pan_dimension_jtext.setBorder(BorderFactory.createEmptyBorder(10, 05, 0,0));
       
       pan_quantite.setBorder(BorderFactory.createEmptyBorder(10, 45, 0,10));
       pan_quantite_jtext.setBorder(BorderFactory.createEmptyBorder(10, 05, 0,0));
       
       pan_datelc_lab.setBorder(BorderFactory.createEmptyBorder(20, 65, 0,18));
       pan_datelc_dp.setBorder(BorderFactory.createEmptyBorder(20, 00, 0,0));
       
       pan_profil_lab.setBorder(BorderFactory.createEmptyBorder(20, 95, 0,-75));
       pan_profil_jcom.setBorder(BorderFactory.createEmptyBorder(20, 00, 0,0));
       
       pan_datelanc_lab.setBorder(BorderFactory.createEmptyBorder(10, 0, 0,0));
       pan_datelanc_dp.setBorder(BorderFactory.createEmptyBorder(10, 05, 0,0));
       
       pan_type_lab.setBorder(BorderFactory.createEmptyBorder(10, 125, 0,-85));
       pan_type_jcom.setBorder(BorderFactory.createEmptyBorder(10, 0, 0,0));
       
  //     pan_model_lab.setBorder(BorderFactory.createEmptyBorder(10, 75, 0,-40));
   //    pan_model_jtext.setBorder(BorderFactory.createEmptyBorder(10, 0, 0,0));

       pan_numcontrat_lab.setBorder(BorderFactory.createEmptyBorder(10, 90, 0,-60));
       pan_numcontrat_jtext.setBorder(BorderFactory.createEmptyBorder(10, -20, 0,0));
       
       pan_global.setPreferredSize(new Dimension(900, 550));
       pan_gauche_droite.setPreferredSize(new Dimension(800, 550));
       pan_button.setPreferredSize(new Dimension(100, 100));
	    
	    
	   
	    setTitle("PRODUIT");
	    setSize(1000, 600);
	    setLocationRelativeTo(null);          
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setContentPane(pan_global);
	    
	    
	    
   	   
   	  	 
   	  	 
   	  	
   	  	
    	
    	
    }
    
    public static boolean exist(JComboBox<String> jcom,String item){
    	boolean exists = false;
    	 if(jcom.getSelectedIndex()==0){exists = true;}
    	 else{
    	 for (int index = 0; index < jcom.getItemCount() && !exists; index++) {
    		
    		   String part_profi = jcom.getItemAt(index).toString();
	            
    	   if (item.equals( part_profi)){
    	     exists = true;
    	   }
    	 }}
    	 return exists;
    	}
    
    
    public boolean isValid(String chaine) {
    	try {
    		//Integer.parseInt(chaine);
    		Float.parseFloat(chaine);
    		//Double.parseDouble(chaine);
    	} catch (NumberFormatException e){
    		return false;
    	}

    	return true;
    }
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				produit frame = new produit("7");
    				frame.setVisible(true);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	});
    }
	
	
	
 
	
	
}
