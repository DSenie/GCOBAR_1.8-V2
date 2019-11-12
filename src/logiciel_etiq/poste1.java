package logiciel_etiq;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class poste1 extends JFrame implements ActionListener{
	private static final String    CTRL_J                = "CTRL+J";
	  final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

	    @SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
	    	 
	     
	            // gestion de l'action
	           
	             if (e.getActionCommand().equals(CTRL_J)){
	            	 
	            	 tab.allowEdition1=false;
	                 LineBorder border = new LineBorder ( Color.white, 1, true );
	                 TitledBorder titl2 = new TitledBorder ( border, "Edition Chaine", TitledBorder.DEFAULT_POSITION,
	               	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
	               	 pan_ft.setBorder(titl2);
	                 poste_fi.disable();
	               	 description.enable();
	              	 designation.enable();
	              	 tache_comb.enable();
	                 pan_but_poste.setVisible(true);
	                 poste_comb.setSelectedIndex(0);
	                 poste_comb.enable();
	                 description.setText("");
	                 designation.setText("");
	                 but_sauv.setVisible(true);
	                 but_modif.setVisible(false);
	                 but_sup.setVisible(false);
	                 retour.setVisible(false);
	                 valid_modif.setVisible(false);
	                 valid_ajou.setVisible(false);
	        	     int rows = tab.table.getRowCount(); 
	        	     poste_fi.setText(code);
	        	     
	             	 for(int i = rows - 1; i >=0; i--)
	           	     {
	           		((DefaultTableModel) tab.table.getModel()).removeRow(i);
	           	     }
	             }}
/**	 *  */
private static final long serialVersionUID = 1L;
private String msg="";
private boolean repet;
private boolean desc;
private boolean repet2;
private boolean desc2;
private boolean valid,valid_j;
private boolean existe=true;
private boolean comb;
private boolean comb_intitule;
private String code="";
private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

private JPanel panel;
private JPanel pan_form=new JPanel();
private JPanel pan_form1=new JPanel();
private JPanel pan_ft1=new JPanel();
private JPanel pan_ft=new JPanel(); 

private JPanel pan_comb=new JPanel();
private JPanel pan_comb_lab=new JPanel();
private JPanel pan_comb_jtext=new JPanel();

private JPanel pan_feild=new JPanel();
private JPanel pan_feild_lab=new JPanel();
private JPanel pan_feild_jtext=new JPanel();

private JPanel pan_button=new JPanel();
private JPanel pan_tab=new JPanel();
private JPanel pan_but_poste=new JPanel();

private JPanel pan_desc=new JPanel();
private JPanel pan_desi=new JPanel();
private JPanel pan_desi_lab=new JPanel();
private JPanel pan_desi_jtext=new JPanel();

private JButton but_ajou=new JButton("Ajouter une Tache");
private JButton but_supprim=new JButton("Supprimer une Tache");
private JButton valid_ajou=new JButton("Valider");
private JButton valid_supp=new JButton("Valider");
private JButton valid_modif=new JButton("Valider");
private JButton retour=new JButton("Retour");
private JButton but_sauv=new JButton("Ajouter");
private JButton but_modif=new JButton("Modifier");
private JButton but_sup=new JButton("Supprimer");
private JTextField poste_fi=new JTextField();
private JLabel comb_lab=new JLabel("Poste");
private JLabel poste_lab=new JLabel("Code Poste");
private JTextField designation=new JTextField();
private JLabel designation_lab=new JLabel("Désignation");
private JTextArea description=new JTextArea();
private JLabel description_lab=new JLabel("Description");
private jcombo poste_comb;
private Java2sAutoComboBox tache_comb;

private Object [] entete={"Code/Désignation Tache","Désignation"};
private final Tableau tab=new Tableau(entete);
private gestion_poste pos= new  gestion_poste();

private JScrollPane p=new JScrollPane(tab);
private int i;
private static List<Object> list_t;
private static ArrayList<String> list_poste_tr=new ArrayList<String>();
private static ArrayList<String> list_tab=new ArrayList<String>();

//private static ArrayList<String> list_zone_tr=new ArrayList<String>();

poste1(final String log){ 

	Toolkit kit = Toolkit.getDefaultToolkit();
    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
    setIconImage(img);
    selectioncomb.windows(this,log,laf);
    composant(log);
    }

@SuppressWarnings("deprecation")
public void composant(final String log){
	
	
	 tab.allowEdition1=false;
	 
	 list_t = new ArrayList<Object>(Arrays.asList(new String[]{"--Sélectionner un Poste--"}));
	 poste_comb = new jcombo(list_t.toArray());
   
     list_poste_tr=pos.select_poste_code();	  
	   for(int i=0;i<list_poste_tr.size();i++)
	   {
	          //Pour affecter une valeur de base de données é un Combobox
		   poste_comb.addItem(list_poste_tr.get(i)+" "+list_poste_tr.get(i+1));
		   i++;
	   }
	   
	   // list_zone_tr=use.select_zone_code();
	   
	   // list_zone_tr=use.select_zone_code();
	   list_t = new ArrayList<Object>(Arrays.asList(new String[]{"---Sélectionner une Tache-----"}));
		//zone_comb = new jcombo(list_z.toArray());
	   // selectioncomb.selectzone_chaine1(this,log,tab,list_z);

	   tache_comb = new Java2sAutoComboBox(list_t);
		selectioncomb.selecttache_poste(tache_comb,this,log,tab,list_t);
       
		tache_comb.setDataList(list_t);
        
	
		
		if(!selectioncomb.prv.contains("poste")){
		//	System.out.println("eeeeee");
		 selectioncomb.prv.add("poste");}
//		zone_comb.addActionListener(
//	            new ActionListener() { 
//	            public void actionPerformed(ActionEvent e) {
//		 tab.table.setValueAt(zone_comb.getSelectedItem(),tab.table.getSelectedRow() , 0);
//  	     }});
		
//		 for(int i=0;i<list_zone_tr.size();i++)
//  	   {
//  	          //Pour affecter une valeur de base de données é un Combobox
//  		   zone_comb.addItem(list_zone_tr.get(i)+" "+list_zone_tr.get(i+1));
//  		   i++;
//  	   }
//		   
	  
	    but_sauv.setVisible(true);
	    but_modif.setVisible(false);
	    but_sup.setVisible(false);
	    valid_supp.setVisible(false);
	    valid_modif.setVisible(false);
	    valid_ajou.setVisible(false);
        retour.setVisible(false);
        poste_fi.disable();
   	   // description.disable();
  	    designation.enable();
  	    //zone_comb.disable();
        pan_but_poste.setVisible(true);
        but_supprim.disable();
        tache_comb.setSelectedIndex(0);
        //tab.allowEdition1=false;
  		
        LineBorder border = new LineBorder ( Color.white, 1, true );
 	    TitledBorder titl2 = new TitledBorder ( border, "Edition Poste", TitledBorder.DEFAULT_POSITION,
        TitledBorder.DEFAULT_POSITION, police2, Color.white);
	    pan_ft.setBorder(titl2);
        tab.table.getTableHeader().setBackground(Color.black);
        tab.setStyle(2);
        
	    panel= new JPanel(){   
		/**
			 * 
			 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
  		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
        img.paintIcon(this, g,0, 0);}};
        
        panel.registerKeyboardAction(this, CTRL_J, KeyStroke.getKeyStroke(
                KeyEvent.VK_J, Event.CTRL_MASK),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
  	    poste_fi.disable();
  	    code=pos.afficher_code_poste();
  	  poste_fi.setText(code);
    	//System.out.println(code);
  	    retour.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
             tab.allowEdition1=false;
             LineBorder border = new LineBorder ( Color.white, 1, true );
             TitledBorder titl2 = new TitledBorder ( border, "Edition Tache", TitledBorder.DEFAULT_POSITION,
           	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
           	 pan_ft.setBorder(titl2);
           	 poste_fi.disable();
           	 description.enable();
          	 designation.enable();
          	 tache_comb.enable();
             pan_but_poste.setVisible(true);
             poste_comb.setSelectedIndex(0);
             poste_comb.enable();
             description.setText("");
             designation.setText("");
             but_sauv.setVisible(true);
             but_modif.setVisible(false);
             but_sup.setVisible(false);
             retour.setVisible(false);
             valid_modif.setVisible(false);
             valid_ajou.setVisible(false);
    	     int rows = tab.table.getRowCount(); 
    	     poste_fi.setText(code);
    	     
         	 for(int i = rows - 1; i >=0; i--)
       	     {
       		((DefaultTableModel) tab.table.getModel()).removeRow(i);
       	     } }});
  	
  	
  	but_sup.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) { LineBorder border = new LineBorder ( Color.white, 1, true );
      	    TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
    	    TitledBorder.DEFAULT_POSITION, police2, Color.white);
           // use.select_chaine_jtext(designation.getText());
    	    pan_ft.setBorder(titl2);
            poste_fi.disable();
            description.disable();
            designation.enable();
            tache_comb.disable();
            poste_comb.enable();
            pan_but_poste.setVisible(false);
            if(existe==true){
            	poste_comb.setSelectedIndex(0);
              	 JOptionPane.showMessageDialog(null, "Ce Poste n'existe pas", "",
              	 JOptionPane.INFORMATION_MESSAGE);
              	 but_sauv.setVisible(true);
                but_modif.setVisible(false);
                but_sup.setVisible(false);
                retour.setVisible(false);
                valid_supp.setVisible(false);
                valid_modif.setVisible(false);
                valid_ajou.setVisible(false);
                int rows = tab.table.getRowCount(); 
            	for(int i = rows - 1; i >=0; i--)
                   	{
                   		((DefaultTableModel) tab.table.getModel()).removeRow(i);

                   	}
            	tache_comb.setSelectedIndex(0);
            	poste_fi.setText(code);
            	
              }
              else{
           	      but_sauv.setVisible(false);
                  but_modif.setVisible(false);
                  but_sup.setVisible(false);
                  retour.setVisible(true);
                  valid_supp.setVisible(true);
                  valid_modif.setVisible(false);
                  valid_ajou.setVisible(false);
              }
            }});
  	
	valid_supp.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
           // use.select_chaine_jtext(designation.getText());

            msg="";
            if(poste_comb.getSelectedIndex()==0){
	          msg+="Vous Devez d'abord Sélectionner un Poste \n";
	         }
            else if(existe==true){
            	 msg+="Ce Poste n'existe pas \n";
             	}
              if(!msg.equals("")){
	  	     JOptionPane.showMessageDialog(null,msg);                                                                            
	  	            	}
        	 else{
  	         pos.setdelete_poste(poste_fi.getText(),designation.getText());
  	         designation.setText("");
  	         poste_comb.removeAllItems();
  	         poste_comb.addItem("--Sélectionner un Poste--");
  	         list_poste_tr=pos.select_poste_code();
	         for(int i=0;i<list_poste_tr.size();i++)
	         {
	          //Pour affecter une valeur de base de données é un Combobox
		      poste_comb.addItem(list_poste_tr.get(i)+" "+list_poste_tr.get(i+1));
		      i++;
	         }
	         tache_comb.enable();
  	         }}});
  	
  	 but_supprim.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
             if(tab.table.getSelectedRow()==-1){
             JOptionPane.showMessageDialog(null, "Vous devez d'abord sélectionner la tache que vous voulez supprimer", "",
     	 	 JOptionPane.INFORMATION_MESSAGE);
                	                           }
             else{
     		 ((DefaultTableModel) tab.table.getModel()).removeRow(tab.table.getSelectedRow());}
                 }});
  	 
  	but_modif.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
          //  use.select_chaine_jtext(designation.getText());
            LineBorder border = new LineBorder ( Color.white, 1, true );
           	TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
         	TitledBorder.DEFAULT_POSITION, police2, Color.white);
            pan_ft.setBorder(titl2);
            tab.allowEdition1=true;
            poste_comb.enable();
            description.enable();
            designation.enable();
            tache_comb.enable();             	 
            pan_but_poste.setVisible(true);
//            if(use.exist_des==true){
//             	chaine_comb.setSelectedIndex(0);
//
//              	 JOptionPane.showMessageDialog(null, "Cette Chaine n'existe pas", "",
//              	 JOptionPane.INFORMATION_MESSAGE);
//              	but_sauv.setVisible(true);
//                but_modif.setVisible(false);
//                but_sup.setVisible(false);
//                retour.setVisible(false);
//                valid_supp.setVisible(false);
//                valid_modif.setVisible(false);
//                valid_ajou.setVisible(false);
//                int rows = tab.table.getRowCount(); 
//             	 for(int i = rows - 1; i >=0; i--)
//                    	{
//                    		((DefaultTableModel) tab.table.getModel()).removeRow(i);
//
//                    	}
//             	zone_comb.setSelectedIndex(0);
//             	zone_fi.setText(use.afficher_codechaine());
//              }
//              else{
           	      but_sauv.setVisible(false);
                  but_modif.setVisible(false);
                  but_sup.setVisible(false);
                  retour.setVisible(true);
                  valid_supp.setVisible(false);
                  valid_modif.setVisible(true);
                  valid_ajou.setVisible(false);
             // }
       	          		       }});
  	 
  	valid_modif.addActionListener(
	            new ActionListener() { 
	            public void actionPerformed(ActionEvent e) {
		       // use.select_chaine_jtext(designation.getText());
	        	msg="";
	 	    	valid_j=false;
	 	    	repet2=false;
	 	    	desc2=false;
	 	   		comb=false;
	 	  	   
	 	  		 if(tab.table.getRowCount()==0){ JOptionPane.showMessageDialog(null, 
	 	      			"Vous devez d'abord ajouter une Tache", "",
	 	  		        JOptionPane.INFORMATION_MESSAGE);}
	        	 else{
	 	    	 if(vaidCheck()==false){valid_j=true;}
	 	    	 for(int i=0;i<tab.table.getRowCount();i++){
		  	    	 for(int j=i+1;j<tab.table.getRowCount();j++){
		  	         if(tab.table.getValueAt(i, 0).toString().equals(tab.table.getValueAt(j, 0).toString())){
	    			  repet2=true;}
		  	           	           }
	 	           	                }
			  	if(designation.getText().equals("")) desc2=true;
			  	if(repet2==false &&desc2==false&&valid_j==false&&comb==false)
            	{ 
		        pos.setupdate_poste_tab_delete(poste_fi.getText());
   	            for(int i=0;i<tab.table.getRowCount();i++){      		
       		    if(repet2==false){
       		    String[] parts =tab.table.getValueAt(i, 0).toString().split(" ");
       		    String part1 = parts[0]; // 004
   	            pos.setupdate_poste_tab_ajou(poste_fi.getText(),part1);} 
       		    }
   	           
		  	    pos.setupdate_poste(poste_fi.getText(),designation.getText(),description.getText());

	  	     	}
		  	   
		  	    
		  		if(desc2==true) {msg+="Veuillez remplir la désignation \n";}
		  		else  if(valid_j==true) { msg+="Un Champ dans le tableau est vide \n";}
		  		else if(repet2==true){ msg+="Il existe une ou plusieurs Taches dupliquées \n"; }
		  		
		  		if(!msg.equals("")){JOptionPane.showMessageDialog(null,msg); }
		  		
		  		else{
		  	    JOptionPane.showMessageDialog(null,"Le Poste a été bien modifié");
		  	    designation.setText("");
		  		poste_comb.removeAllItems();
		  		poste_comb.addItem("--Sélectionner une Poste--");
		  		list_poste_tr=pos.select_poste_code();
		  		for(int i=0;i<list_poste_tr.size();i++){
		  		 //Pour affecter une valeur de base de données é un Combobox
		  		 poste_comb.addItem(list_poste_tr.get(i)+" "+list_poste_tr.get(i+1));
		  		 i++; }}
	 	  	     }}});
	   
  	
  	but_sauv.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
             //use.select_chaine_jtext(designation.getText());

             LineBorder border = new LineBorder ( Color.white, 1, true );
           	 TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
        	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
        	 pan_ft.setBorder(titl2);
             poste_comb.disable();

//          	 int rows = tab.table.getRowCount(); 
//             for(int i = rows - 1; i >=0; i--)
//                   	{
//             ((DefaultTableModel) tab.table.getModel()).removeRow(i);
//
//                   	}
             
              tab.allowEdition1=true;
      		  poste_fi.setText(code);
              description.enable();
              designation.enable();
              tache_comb.enable();
              pan_but_poste.setVisible(true);
              
              if(existe==false){
             	 JOptionPane.showMessageDialog(null, "Ce Poste existe déjà", "",
             	 JOptionPane.INFORMATION_MESSAGE);
             	 but_sauv.setVisible(false);
                  but_modif.setVisible(true);
                  but_sup.setVisible(true);
                  retour.setVisible(false);
                  valid_supp.setVisible(false);
                  valid_modif.setVisible(false);
                  valid_ajou.setVisible(false);
                 // use.select_chaine_jtext(designation.getText());
                  //pos.poste(designation.getText());
                  poste_fi.setText(pos.code);
                  poste_comb.setSelectedItem(pos.code+" "+pos.intitul); 
                  description.setText(pos.desc);
             }
             else{
             	but_sauv.setVisible(false);
                 but_modif.setVisible(false);
                 but_sup.setVisible(false);
                 retour.setVisible(true);
                 valid_supp.setVisible(false);
                 valid_modif.setVisible(false);
                 valid_ajou.setVisible(true);
             }
             
            	 
             }});
	
   but_ajou.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
         	tab.ajouter();
  	      	TableColumn gradeColumn = tab.table.getColumnModel().getColumn(0);
         	gradeColumn.setCellEditor(new DefaultCellEditor(tache_comb));  	 
         	int k=tab.table.getRowCount();
            tab.table.setValueAt("", k-1,0);
	        tab.table.setValueAt("", k-1,1);
            }});
   
   
   valid_ajou.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
	    		msg="";
 	    		valid=false;
 	    		repet=false;
 	    		desc=false;
 	    		//use.select_chaine_jtext(designation.getText());
 	       if(existe==false){
 	              	 JOptionPane.showMessageDialog(null, "Ce Poste existe déjà", "",
 	              	 JOptionPane.INFORMATION_MESSAGE);}
 	       else if(tab.table.getRowCount()==0){ 
 	    	   JOptionPane.showMessageDialog(null,"Vous devez d'abord ajouter une Zone.", "",
 	  		    JOptionPane.INFORMATION_MESSAGE);}
 	    	else{

 	        if(vaidCheck()==false){valid=true; }
 	    	 if(valid==false)
	  	            	{	
	  	      for(int i=0;i<tab.table.getRowCount();i++){
		  	  for(int j=i+1;j<tab.table.getRowCount();j++){
		  	  if(tab.table.getValueAt(i, 0).toString().equals(tab.table.getValueAt(j, 0).toString())){
		  	           			   repet=true;
		  	           		   }
		  	           	   }
	  	           	   }
	 
	  	            	}
 	    	 
	  	       if(designation.getText().equals("")) desc=true;
	  	       
	  	       
               if(repet==false &&desc==false&&valid==false){
            	   
            	   for(int i=0;i<tab.table.getRowCount();i++){            		
                       String[] parts =tab.table.getValueAt(i, 0).toString().split(" ");
                       String part1 = parts[0]; // 004
                	   pos.ajouter_poste_tache(poste_fi.getText(),part1);  
                	   }
	  	 	   pos.ajouter_poste(poste_fi.getText(),designation.getText(),description.getText());  
	  	 	   }
               
               if(repet==false&&desc==false&&valid==false   ) {
                JOptionPane.showMessageDialog(null,"Le Poste a été bien ajoutée");
                designation.setText("");
               poste_comb.enable();
                //System.out.println(use.select_chaine_code().get(i));
               poste_comb.removeAllItems();
               poste_comb.addItem("--Sélectionner un Poste--");
	  		  list_poste_tr=pos.select_poste_code();
			   for(int i=0;i<list_poste_tr.size();i++)
			   {
				   poste_comb.addItem(list_poste_tr.get(i)+" "+list_poste_tr.get(i+1));
				   i++;
			   }
			   }
	  		 	if(desc==true) {msg+="Veuillez remplir la désignation \n"; }
	  		 	else if(valid==true) { msg+="Un Champ dans le tableau est vide \n";}
	  		 	else if(repet==true){msg+="Il existe plusieurs ou des taches dupliquées. \n";}
		  		if(!msg.equals("")){JOptionPane.showMessageDialog(null,msg);}
	  		   }}});
   
  
   
   
        tache_comb.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
        	   System.out.println("eeee"+tache_comb.hasFocus());
        	  
        	if(tache_comb.getSelectedIndex()!=0){
        		
        		  String[] parts = tache_comb.getSelectedItem().toString().split(" ");
                  String part1 = parts[0]; // 004
            String des = tache_comb.getSelectedItem().toString().replace(part1+" ", ""); 	
         //   String des = zone_comb.getSelectedItem().toString().substring(4);
          if(tab.table.getSelectedRow()>=0 )
            tab.table.setValueAt(des,tab.table.getSelectedRow() , 1);
            }
        	else{
        	 if(tab.table.getSelectedRow()>=0 )
        		tab.table.setValueAt("",tab.table.getSelectedRow() , 1);	
        	}
           
           
           }});
   
         poste_comb.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
        	   String part1="";
        	   LineBorder border = new LineBorder ( Color.white, 1, true );
               TitledBorder titl2 = new TitledBorder ( border, "Edition Poste", TitledBorder.DEFAULT_POSITION,
               TitledBorder.DEFAULT_POSITION, police2, Color.white);
          	   pan_ft.setBorder(titl2);

        	   pan_but_poste.setVisible(true);
               description.enable();
              if(poste_comb.getItemCount()!=0) {
           	String[] parts = poste_comb.getSelectedItem().toString().split(" ");
            part1 = parts[0]; // 004
     		 existe= pos.select_poste_jtext(part1);}
           int rows = tab.table.getRowCount(); 
           	for(int i = rows - 1; i >=0; i--)
           	{
           		((DefaultTableModel) tab.table.getModel()).removeRow(i);

           	}

           	  if(poste_comb.getSelectedIndex()==0){
           	
           		if(valid_modif.isVisible()==false||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
         	         but_sauv.setVisible(true);
         	         valid_ajou.setVisible(false);
                    valid_supp.setVisible(false);
                    valid_modif.setVisible(false);
                    retour.setVisible(false);
         	     }
                
         	    but_modif.setVisible(false);
                but_sup.setVisible(false);
                if(comb_intitule!=true)
                 designation.setText("");
                code=pos.afficher_code_poste();
        	    poste_fi.setText(code);
            description.setText("");
            }
        	else {   		
            if(poste_comb.getItemCount()>1){
           	  comb_intitule=false; 
            	   if(valid_modif.isVisible()==false ||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
                       but_modif.setVisible(true);
                       but_sup.setVisible(true);
                       but_sauv.setVisible(false);
                       retour.setVisible(false);
                       valid_ajou.setVisible(false);
                       valid_supp.setVisible(false);
                       valid_modif.setVisible(false);
                       }
              else{
                  	   valid_ajou.setVisible(false);
                  	   but_modif.setVisible(true);
                       but_sup.setVisible(true);
                       but_sauv.setVisible(false);
                       retour.setVisible(false);
                   }
        

            poste_fi.setText(part1);
       	   // System.out.println(chaine_comb.getSelectedItem().toString());
            list_tab=pos.select_poste(part1);
        	for(i=0;i<list_tab.size();i=i+2){tab.ajouter();
       		TableColumn gradeColumn = tab.table.getColumnModel().getColumn(0);
            gradeColumn.setCellEditor(new DefaultCellEditor(tache_comb));
            }
        	
       		int j=1;
       	    int l = 0; i=1;
       	    while(l<list_tab.size()){
       	    while(l<j*2){
       		tab.getTable().setValueAt(list_tab.get(l)+" "+list_tab.get(l+1), j-1,0);
       		tab.getTable().setValueAt(list_tab.get(l+1),j-1,1);
             i=i+1; l=l+2;
       			 }
   			 i=1;
       		 j=j+1;
       		 }
         	 description.setText(pos.desc);
         	 designation.setText(pos.intitul);
         	 
}} 
        	}});
     
     
         
         designation.addFocusListener(new FocusListener() {
             public void focusLost(FocusEvent e) {
              	existe=pos.select_poste_jtext(designation.getText());
             }
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
			}
           });
         
     designation.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
                 poste_comb.enable();
            	 LineBorder border = new LineBorder ( Color.white, 1, true );
            	 TitledBorder titl2 = new TitledBorder ( border, "Edition Chaine", TitledBorder.DEFAULT_POSITION,
                 TitledBorder.DEFAULT_POSITION, police2, Color.white);
            	 pan_ft.setBorder(titl2);

            	 pan_but_poste.setVisible(true);
                 description.enable();
            int rows = tab.table.getRowCount(); 
         	for(int i = rows - 1; i >=0; i--)
           	{
           		((DefaultTableModel) tab.table.getModel()).removeRow(i);

           	}
         	existe=pos.select_poste_jtext(designation.getText());
          //   System.out.println(use.exist_des);
             if(designation.getText().equals("")||existe==true){
             if(valid_modif.isVisible()==false||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
      	         but_sauv.setVisible(true);
      	         valid_ajou.setVisible(false);
                 valid_supp.setVisible(false);
                 valid_modif.setVisible(false);
                 comb_intitule=true;

      	     }
             
      	     but_modif.setVisible(false);
             but_sup.setVisible(false);
             poste_comb.setSelectedIndex(0);
             code=pos.afficher_code_poste();
          	 poste_fi.setText(code);
             }
          	 else {   		
             if(poste_comb.getItemCount()>=1){
            	 poste_comb.enable();
             if(valid_modif.isVisible()==false ||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
                 but_modif.setVisible(true);
                 but_sup.setVisible(true);
                 but_sauv.setVisible(false);
                 retour.setVisible(false);
                 valid_ajou.setVisible(false);
                 valid_supp.setVisible(false);
                 valid_modif.setVisible(false);

                 }
             else{
            	 valid_ajou.setVisible(false);
            	 but_modif.setVisible(true);
                 but_sup.setVisible(true);
                 but_sauv.setVisible(false);
                 retour.setVisible(false);

             }
             poste_fi.setText(pos.code);
             //System.out.println(use.code_chaine+"  "+use.desig);
             poste_comb.setSelectedItem(pos.code+" "+pos.intitul); 
           	 description.setText(pos.desc);
           	 
  }} 
          	}});
     
     
     try{
   		UIManager.setLookAndFeel(laf);
   		SwingUtilities.updateComponentTreeUI(this);
   		
   	}catch(Exception e1){
   		
   		e1.printStackTrace();
   	}
     Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
     // ***************************************************************test**********************************************

     description.setFont(new Font("Serif", Font.ITALIC, 13));
     description.setLineWrap(true);       // wrap line
     description.setWrapStyleWord(true);  // wrap line at word boundary
     description.setBackground(new Color(204, 238, 241)); // light blue 
  
     //setPreferredSize(new Dimension(230, 30));
     // Wrap the JTextArea inside a JScrollPane
     JScrollPane tAreaScrollPane = new JScrollPane(description);
     tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
     tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     tAreaScrollPane.setPreferredSize(new Dimension(230, 130));
   //*******************************************************************************************************************
     comb_lab.setFont(police2);
	   poste_lab.setFont(police2);
	    description_lab.setFont(police2);
	   designation_lab.setFont(police2);
	   poste_fi.setFont(police_fi);
	  description.setFont(police_fi);
	  designation.setFont(police_fi);
       tab.table.setFont(police_fi);
       tache_comb.setFont(police_fi);
       poste_comb.setFont(police_fi);
       

     poste_fi.setPreferredSize(new Dimension(230, 30));
     designation.setPreferredSize(new Dimension(230,30));

     //description.setPreferredSize(new Dimension(210,100));
     poste_lab.setForeground(new Color(255,255,255)); 
     designation_lab.setForeground(new Color(255,255,255)); 
     description_lab.setForeground(new Color(255,255,255)); 
     comb_lab.setForeground(new Color(255,255,255)); 
     
  	pan_form1.add(pan_comb);
   	pan_comb.add(pan_comb_lab);
    pan_comb.add(pan_comb_jtext);
     pan_comb_lab.add(comb_lab);
     pan_comb_jtext.add(poste_comb);
  	 pan_form1.add(pan_feild);
  	 pan_form1.add(pan_desi);
  	 pan_form1.add(pan_desc);
  	 pan_ft1.add(pan_form1);
  	 pan_ft1.add(pan_form);

  	 
   	
  	panel.add(pan_ft);
  	 pan_ft.add(pan_ft1); 

   	pan_ft.add(pan_tab); 
   	
   	pan_tab.add(p); 
   	pan_tab.add(pan_but_poste); 


   	panel.add(pan_button);
   	
 	pan_feild.add(pan_feild_lab);
   	pan_feild.add(pan_feild_jtext);
  	 pan_feild_lab.add(poste_lab);
  	 pan_feild_jtext.add(poste_fi);
  	pan_desi.add(pan_desi_lab);
  	pan_desi.add(pan_desi_jtext);
	 pan_desi_lab.add(designation_lab);
  	 pan_desi_jtext.add(designation);
  	 pan_desc.add(description_lab);
  	 
  	

  
  	pan_but_poste.add(but_ajou);		
	pan_but_poste.add(but_supprim);		

  	pan_button.add(retour);
	pan_button.add(valid_ajou);
	pan_button.add(valid_supp);
	pan_button.add(valid_modif);
  	pan_button.add(but_sauv);
	pan_button.add(but_modif);	
	pan_button.add(but_sup);	
	pan_comb.setOpaque(false);

	pan_ft.setOpaque(false);
	pan_form.setOpaque(false);
  	pan_button.setOpaque(false);
  	pan_feild.setOpaque(false);
  	pan_desi.setOpaque(false);
  	pan_desc.setOpaque(false);
  	 pan_ft1.setOpaque(false);
  	 pan_form1.setOpaque(false);
  	pan_comb.setOpaque(false);
	 pan_comb_lab.setOpaque(false);
	 pan_comb_jtext.setOpaque(false);
	 pan_desi_lab.setOpaque(false);
	 pan_desi_jtext.setOpaque(false);
	 pan_feild_lab.setOpaque(false);
	 pan_feild_jtext.setOpaque(false);
  	//pan_but_poste.setOpaque(false);

   // tab.setPreferredSize(new Dimension(700,700));
    p.setPreferredSize(new Dimension(700,700));
    pan_ft1.setPreferredSize(new Dimension(300,700));
    pan_form.add(tAreaScrollPane, BorderLayout.CENTER);

    but_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
    but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
    but_supprim.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
    but_sauv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
    but_sup.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
    retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
    valid_supp.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
    valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
    valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

    
    
    but_ajou.setPreferredSize(new Dimension(160, 33));
    but_modif.setPreferredSize(new Dimension(120, 33));
    but_supprim.setPreferredSize(new Dimension(160, 33));
    but_sauv.setPreferredSize(new Dimension(120, 33));
    but_sup.setPreferredSize(new Dimension(120, 33));
    retour.setPreferredSize(new Dimension(120, 33));
    valid_supp.setPreferredSize(new Dimension(120, 33));
    valid_modif.setPreferredSize(new Dimension(120, 33));
    valid_ajou.setPreferredSize(new Dimension(120, 33));
    poste_comb.setPreferredSize(new Dimension(230, 30));

	p.setOpaque(false);
	
	   panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
	    pan_button.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
	    poste_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 30));
	    comb_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 75));
	    designation_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
	    description_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 22));
	    pan_feild.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	    pan_desi.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
	    pan_desc.setBorder(BorderFactory.createEmptyBorder(0, 0, -30, 0));
	    pan_ft1.setBorder(BorderFactory.createEmptyBorder(0, -10, -30, 0));
	    
  

	    pan_desc.setLayout( new FlowLayout( FlowLayout.LEFT ));

	  	pan_feild_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));
	  	pan_feild_jtext.setLayout( new FlowLayout( FlowLayout.LEFT ));

	  	pan_comb_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));
	  	pan_comb_jtext.setLayout( new FlowLayout( FlowLayout.LEFT ));
	    // Setup the content-pane of JFrame in BorderLayout

	  	 	//pan_desc.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_desi_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_desi_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
  

  	pan_button.setLayout( new FlowLayout( FlowLayout.RIGHT ));
  	pan_but_poste.setLayout( new FlowLayout( FlowLayout.CENTER ));

	pan_ft.setLayout(new BoxLayout(pan_ft,BoxLayout.X_AXIS));
  	pan_form1.setLayout(new BoxLayout(pan_form1,BoxLayout.Y_AXIS));
   // pan_form.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 100));
  	pan_tab.setLayout(new BoxLayout(pan_tab,BoxLayout.Y_AXIS));
  	pan_comb.setLayout(new GridLayout(2,1, 0, -25));
  	pan_feild.setLayout(new GridLayout(2,1, 0, -25));
  	pan_desi.setLayout(new GridLayout(2,1, 0, -25));

  	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
  	 Container cp = this.getContentPane();
     cp.add(panel);
     
//  	pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
//    pan_form.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));


    setTitle("Chaine de Production" );
    setSize(1000, 600);
    setLocationRelativeTo(null);          
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(panel);
  
    tab.table.setRowHeight(20);



}
public boolean isValid(String chaine) {
	try {
		Integer.parseInt(chaine);
	} catch (NumberFormatException e){
		return false;
	}

	return true;
}

public boolean validnombre()

{
	if(tab.table.getCellEditor()!=null){

		tab.table.getCellEditor().stopCellEditing();

		}
for(int i=0;i< tab.table.getRowCount();i++)
{


String indice=tab.table.getValueAt(i,1).toString();
String coef=tab.table.getValueAt(i,4).toString();

if(isValid(indice)==true&&isValid(coef)==true)

{
return false;

}

}

return true;
}	


public boolean vaidCheck()

{

if(tab.table.getCellEditor()!=null){

tab.table.getCellEditor().stopCellEditing();

}

for(int l=0;l< tab.table.getRowCount();l++)

{

for (int k=0;k<tab.table.getColumnCount();k++)

{

String om=tab.table.getValueAt(l,k).toString();


if(om.trim().length()==0||om.equals("---Sélectionner une zone-----"))

{
	msg="";
return false;

}}}


return true;
}	

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}

