package logiciel_etiq;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.JXDatePicker;

//import org.jdesktop.swingx.JXDatePicker;
public class changement_chaine  extends JFrame implements ActionListener{
	private static final String    CTRL_J                = "CTRL+J";

	    @SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
	            // gestion de l'action
	             if (e.getActionCommand().equals(CTRL_J)){
	            	 LineBorder border = new LineBorder ( Color.white, 1, true );
	         		 TitledBorder titl2 = new TitledBorder ( border, "Changement Chaine", TitledBorder.DEFAULT_POSITION,
	         	     TitledBorder.DEFAULT_POSITION, police2, Color.white);
	         		panel_gener.setBorder(titl2);
	              	 ajout.setVisible(true);
	          		 modif.setVisible(false);
	          		 supp.setVisible(false);
	          		 retour.setVisible(false);
	          		 valid_ajout.setVisible(false);
	          		 valid_modif.setVisible(false);
	          		 valid_sup.setVisible(false);
	                 pan_but_perso.setVisible(false);

		           	 zone_combo.setSelectedIndex(0);
		           	 chaine_combo.setSelectedIndex(0);
		             picker.setDate(Calendar.getInstance().getTime());
		     	   int rows = tab.table.getRowCount(); 

		        	for(int i = rows - 1; i >=0; i--)
		           	{
		           		((DefaultTableModel) tab.table.getModel()).removeRow(i);

		           	} 
	             }}
	private static final long serialVersionUID = 1L;
//	private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    private String msg;
    private int i;
    private gestion_user use=new gestion_user();
    
    private gestion_poste pos=new gestion_poste();

    private JPanel panel;
    private JPanel pan_form=new JPanel();
    private JPanel panel_gener=new JPanel();
    private JPanel panel_but=new JPanel();
    private JPanel pan_date=new JPanel();
    private JPanel pan_chaine=new JPanel();
    private JPanel pan_zone=new JPanel();
    private JPanel pan_lab_date=new JPanel();
    private JPanel pan_jtext_date=new JPanel();
    private JPanel pan_lab_chaine=new JPanel();
    private JPanel pan_jtext_chaine=new JPanel();
    private JPanel pan_lab_zone=new JPanel();
    private JPanel pan_jtext_zone=new JPanel();
    private JPanel pan_tab=new JPanel();
    private JPanel pan_but_perso=new JPanel();
    private JLabel date_lab=new JLabel("Date");
    private JLabel chaine_lab=new JLabel("Chaine");
    private JLabel zone_lab=new JLabel("Zone");
    private jcombo chaine_combo;
    private jcombo zone_combo;
    private JComboBox<String> poste_combo=new JComboBox<String>();
    private JComboBox<String> perm_combo=new JComboBox<String>();
    private JComboBox<String> vol_combo=new JComboBox<String>();
    private JButton modif=new JButton("Modifier");
    private JButton ajout=new JButton("Ajouter");
    private JButton supp=new JButton("Supprimer");
    private JButton valid_modif=new JButton("Valider");
    private JButton valid_ajout=new JButton("Valider");
    private JButton valid_sup=new JButton("Valider");
    private JButton retour=new JButton("Retour");
    private JButton but_ajou=new JButton("Ajouter un Poste");
    private JButton but_supprim=new JButton("Supprimer un Poste");
    private boolean valid_j;
    private boolean repet2;
    private  boolean valid_heure;
    private  boolean comb,zomb;
    private String [] poste;	
    private String poste1;
    private String heur_d,heur_f,perm_f,vol_f,post_f;
    private Object [] entete={"Poste","Permanent","Volant","Heure Debut","Heure Fin"};
    private final Tableau tab=new Tableau(entete);
    private JScrollPane p=new JScrollPane(tab);
    private gestion_changement chang=new gestion_changement();
    private static List<String> list_z;
    private static List<String> list_c;
    final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);
	 final JXDatePicker picker = new JXDatePicker();
	 private String chaine,zone;
	 private static ArrayList<String> list_poste_tr=new ArrayList<String>();
	 private static ArrayList<String> list_persvol_tr=new ArrayList<String>();
	 private static ArrayList<String> list_perm_tr=new ArrayList<String>();
	 private static ArrayList<String> list_chang=new ArrayList<String>();
	private static ArrayList<String> list_chang_volant=new ArrayList<String>();


    changement_chaine(final String log){
    	Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(img);
        selectioncomb.windows(this,log);
        
	    panel= new JPanel(){   
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g){
	  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
	             img.paintIcon(this, g,0, 0);
	  	}  };
	 
		panel.registerKeyboardAction(this, CTRL_J, KeyStroke.getKeyStroke(
	            KeyEvent.VK_J, Event.CTRL_MASK),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);
		
        composant(log);

}

public void composant(final String log){
	
	LineBorder border = new LineBorder ( Color.white, 1, true );
		 TitledBorder titl2 = new TitledBorder ( border, "Edition Changement", TitledBorder.DEFAULT_POSITION,
	     TitledBorder.DEFAULT_POSITION, police2, Color.white); 
		 panel_gener.setBorder(titl2);
		 tab.allowEdition = true;
	     	    tab.allowEdition1 = true;
	     		tab.allowEdition2 = true;
	     		tab.allowEdition3 = true;
	     		tab.allowEdition4 = true;
	     		tab.allowEdition5 = true;
	 ajout.setVisible(true);
	 modif.setVisible(false);
	 supp.setVisible(false);
	 retour.setVisible(false);
	 valid_ajout.setVisible(false);
	 valid_modif.setVisible(false);
	 valid_sup.setVisible(false);
	 pan_but_perso.setVisible(false);
	 
     picker.setDate(Calendar.getInstance().getTime());
     picker.getEditor().setEditable(false);
     tab.setStyle(2);


	generale.styles("Nimbus");
	SwingUtilities.updateComponentTreeUI(this);
     
     if(!selectioncomb.prv.contains("changement")){
			//System.out.println("eeeeee");
		 selectioncomb.prv.add("changement");}
     list_z = new ArrayList<String>(Arrays.asList(new String[]{"---Sélectionner une Zone-----"}));
	 zone_combo = new jcombo(list_z.toArray());
     selectioncomb.selectzone(zone_combo ,this,log);
     
       list_c = new ArrayList<String>(Arrays.asList(new String[]{"---Sélectionner une Chaine-----"}));
	   chaine_combo = new jcombo(list_c.toArray());
	   selectioncomb.selectchaine(chaine_combo,this,log);
	   list_poste_tr=pos.select_poste_code();
	   for(int i=0;i<list_poste_tr.size();i++)
	   {
	      //Pour affecter une valeur de base de données é un Combobox
		   poste_combo.addItem(list_poste_tr.get(i)+" "+list_poste_tr.get(i+1));
		   i++;
	   }
	   
	   
     picker.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
            	  int rows = tab.table.getRowCount(); 

  	        	for(int i = rows - 1; i >=0; i--)
  	           	{
  	           		((DefaultTableModel) tab.table.getModel()).removeRow(i);

  	           	}
            action_chaine_zone_date();
       		remplir_table(); 

            	 }});
     
     chaine_combo.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
	        	  if(chaine_combo.getSelectedIndex()!=chaine_combo.getItemCount()-1){

            	  int rows = tab.table.getRowCount(); 

  	        	for(int i = rows - 1; i >=0; i--)
  	           	{
  	           		((DefaultTableModel) tab.table.getModel()).removeRow(i);

  	           	}
            	 String[] chaines = chaine_combo.getSelectedItem().toString().split(" ");
  		         chaine = chaines[0]; // 004
            	 action_chaine_zone_date();
            	 
            		remplir_table(); 
	        	  }
             }});
     
  
     
     
	   zone_combo.addActionListener(
	          new ActionListener() { 
	          public void actionPerformed(ActionEvent e) {
	        	  int rows = tab.table.getRowCount(); 

		        	for(int i = rows - 1; i >=0; i--)
		           	{
		           		((DefaultTableModel) tab.table.getModel()).removeRow(i);
		           	}
	        	  if(zone_combo.getSelectedIndex()!=zone_combo.getItemCount()-1){
	           vol_combo.removeAllItems();
	           vol_combo.addItem("");
	           String[] zones = zone_combo.getSelectedItem().toString().split(" ");
		           zone = zones[0]; // 004
		      // System.out.println(use.select_volant(partz1).size());
		           list_persvol_tr=use.select_volant(zone);
	   for(int i=0;i<list_persvol_tr.size();i++)
	   {
	          //Pour affecter une valeur de base de données é un Combobox
		   vol_combo.addItem(list_persvol_tr.get(i)+" "+list_persvol_tr.get(i+1)+" "+list_persvol_tr.get(i+2));
		  i= i+2;
	   }  
	   
	    action_chaine_zone_date();
		remplir_table(); 
		
		}}});
     picker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
	 ajout.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
            	
             LineBorder border = new LineBorder ( Color.white, 1, true );
       		 TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
       	     TitledBorder.DEFAULT_POSITION, police2, Color.white); 
       		 panel_gener.setBorder(titl2);
       		 ajout.setVisible(false);
       		 modif.setVisible(false);
       		 supp.setVisible(false);
       		
       		 retour.setVisible(true);
       		valid_ajout.setVisible(true);
       		 valid_modif.setVisible(false);
       		 valid_sup.setVisible(false);
       		 pan_but_perso.setVisible(true);
             }});
	 
	 valid_sup.addActionListener(
	            new ActionListener() { 
	            @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
	            	  int reponse = JOptionPane.showConfirmDialog(null,
	      		            "Voulez-vous vraiment supprimer ce changement?",
	      		            "Confirmation",
	      		            JOptionPane.YES_NO_OPTION,
	      		            JOptionPane.QUESTION_MESSAGE);
	      		if (reponse== JOptionPane.YES_OPTION){
		     	//  System.out.println(partc1+" "+partz1+" ");
		     	  chang.delete_changement(chaine, zone, picker.getEditor().getText());
		 		 JOptionPane.showMessageDialog(null,"Suppression réussie");
		 		chaine_combo.setSelectedIndex(0);
	 	  		zone_combo.setSelectedIndex(0);
	 	  	    picker.setDate(Calendar.getInstance().getTime());

	      		}
	      		
	            }});
	 
	 valid_ajout.addActionListener(
	            new ActionListener() { 
	            public void actionPerformed(ActionEvent e) {
	            	int debut = 0;
	            	int fin=0;
	            	msg="";
                    valid_heure=true;
		    		comb=false;
		    		zomb=false;
	 	    		valid_j=false;
	 	    		repet2=false;
	 	    		comb=false;
	 	    	if(chaine_combo.getSelectedIndex()==0){  comb=true; }
	 	  		if(zone_combo.getSelectedIndex()==0){  zomb=true; }	
	 	  		if(comb==true || zomb==true){JOptionPane.showMessageDialog(null, 
	 	  				"Vous Devez d'abord Sélectionner une chaine ou une zone \n", "",
	 	  		        JOptionPane.INFORMATION_MESSAGE);  }
	 	  	    else if(tab.table.getRowCount()==0){ JOptionPane.showMessageDialog(null, 
	 	      			"Vous devez d'abord ajouter un changement ", "",
	 	  		        JOptionPane.INFORMATION_MESSAGE);}
	        	 else{
	 	    	  if(vaidCheck()) {valid_j=true;}
	 	          for(int i=0;i<tab.table.getRowCount();i++){
	            	 heur_d =tab.table.getValueAt(i, 3).toString();
	            	 heur_f =tab.table.getValueAt(i, 4).toString();
	              if(valid_j==true){
	            		debut=Integer.parseInt(heur_d.replace(":",""));
	            		fin=Integer.parseInt(heur_f.replace(":",""));
	            			           }
	              if((validTime24(heur_d)==false||validTime24(heur_f)==false)||debut>=fin)
	            		{	 
	            			valid_heure=false;
            		   }
	
	 	    		}
	 	          
	 	        
	 	    		
          		  if(valid_j==true&&comb==false&&zomb==false)
	            	{	
          			 for(int i=0;i<tab.table.getRowCount();i++){
  	  	           	   for(int j=i+1;j<tab.table.getRowCount();j++){
  	  	           		   if(tab.table.getValueAt(i, 0).toString().equals(tab.table.getValueAt(j, 0).toString())||
  	  	           				tab.table.getValueAt(i, 2).toString().equals(tab.table.getValueAt(j, 2).toString())
  	  	           				&&tab.table.getValueAt(i, 4).toString().equals(tab.table.getValueAt(j, 3).toString())
  	  	           				   ){
  	  	           			   repet2=true;
  	  	           		   }
  	  	           	   }
  	           	   }
	            	}
      		
	 	    	if(valid_j==false) {msg+="Un Champ dans le tableau est vide \n";}
	 	    	else if(valid_heure==false){ msg+="Heure non valid ou heure debut est superieure a celle de fin \n";}
	 	    	else if(repet2==true){ msg+="Il existe un ou plusieurs changement dupliquées \n";  }
	 	    	
		  		if(!msg.equals("")){JOptionPane.showMessageDialog(null,msg); }
		  		if(repet2==false&&valid_j==true&&comb==false&&zomb==false&&valid_heure==true){
		  		      
		  		       for(int i=0;i<tab.table.getRowCount();i++){
		  		     	 String[] perm =tab.table.getValueAt(i, 1).toString().split(" ");
		  			     perm_f = perm[0]; // permanat final
		  			     String[] vol =tab.table.getValueAt(i, 2).toString().split(" ");
		  			     vol_f = vol[0]; // volant final
		  			     String[] post =tab.table.getValueAt(i, 0).toString().split(" ");
		  			     post_f= post[0]; // post final
		  			     heur_d =tab.table.getValueAt(i, 3).toString();
		  	             heur_f =tab.table.getValueAt(i, 4).toString();
		  		         chang.ajouter_changement(chaine, zone, post_f,perm_f,vol_f, picker.getEditor().getText(), heur_d, heur_f);
		  		     		        }
		  		     JOptionPane.showMessageDialog(null,"Ajout  réussit");
		  		     }
			  	    	}}});
	 
	 
	 modif.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
            	 tab.allowEdition = true;
              	tab.allowEdition1 = true;
              		tab.allowEdition2 = true;
              		tab.allowEdition3 = true;
              		tab.allowEdition4 = true;
              		tab.allowEdition5 = true;
             comb=false;
             zomb=false;
	 	  	 if(chaine_combo.getSelectedIndex()==0){  comb=true; }
	 	  	 if(zone_combo.getSelectedIndex()==0){  zomb=true; }	
	 	  	 if(comb==true || zomb==true){	
	 	  		JOptionPane.showMessageDialog(null, 
	 	      	"Vous Devez d'abord Sélectionner une chaine ou une zone \n", "",
	 	  		JOptionPane.INFORMATION_MESSAGE);  	    
              }else{
	          LineBorder border = new LineBorder ( Color.white, 1, true );
       		  TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
       	      TitledBorder.DEFAULT_POSITION, police2, Color.white);
       		panel_gener.setBorder(titl2);
       		 ajout.setVisible(false);
       		 modif.setVisible(false);
       		 supp.setVisible(false);
       		
       		 retour.setVisible(true);
       		 valid_modif.setVisible(true);
       		 valid_ajout.setVisible(false);
       		 valid_sup.setVisible(false);
       		 pan_but_perso.setVisible(true);
//       		 chaine_combo.disable();
//       		 zone_combo.disable();
//       		 picker.getEditor().disable();
       		
              }}});
	 
	 valid_modif.addActionListener(
	            new ActionListener() { 
	            public void actionPerformed(ActionEvent e) {
                 valid_heure=true;
                 msg="";
		    	 comb=false;
		    	 zomb=false;
	 	    	 valid_j=false;
	 	    	 repet2=false;
	 	    	 comb=false;
	 	    	 int debut=0;
	 	    	 int fin=0;
	 	    	 
	 	  		 if(chaine_combo.getSelectedIndex()==0){  comb=true; }
	 	  		 if(zone_combo.getSelectedIndex()==0){  zomb=true; }	
	 	  		 if(comb==true || zomb==true){	
	 	  			JOptionPane.showMessageDialog(null, 
	 	      		"Vous Devez d'abord Sélectionner une chaine ou une zone \n", "",
	 	  		     JOptionPane.INFORMATION_MESSAGE);  	    
                                              }
	 	  	   else if(tab.table.getRowCount()==0){ JOptionPane.showMessageDialog(null, 
	 	      			"Vous devez tout d'abord ajouter un changement ", "",
	 	  		        JOptionPane.INFORMATION_MESSAGE);}
	           else{
	 	       if(vaidCheck()){valid_j=true;}
	 	       for(int i=0;i<tab.table.getRowCount();i++){
	 	    	 String[] perm =tab.table.getValueAt(i, 1).toString().split(" ");
	  		     perm_f = perm[0]; // permanat final
	  		     String[] vol =tab.table.getValueAt(i, 2).toString().split(" ");
	  		     vol_f = vol[0]; // volant final
	  		     String[] post =tab.table.getValueAt(i, 0).toString().split(" ");
	  		     post_f= post[0]; // post final
	             heur_d =tab.table.getValueAt(i, 3).toString();
	             heur_f =tab.table.getValueAt(i, 4).toString();
	             if(valid_j==true){
		          debut=Integer.parseInt(heur_d.replace(":",""));
		          fin=Integer.parseInt(heur_f.replace(":",""));
		            			   }
	             if((validTime24(heur_d)==false||validTime24(heur_f)==false)||debut>=fin)
	             {valid_heure=false;}
	             }
	 	    		
	 	    	if(valid_j==true&&comb==false&&zomb==false)
	            	{	
	           	   for(int i=0;i<tab.table.getRowCount();i++){
	  	           	   for(int j=i+1;j<tab.table.getRowCount();j++){
	  	           		   if(tab.table.getValueAt(i, 0).toString().equals(tab.table.getValueAt(j, 0).toString())||
	  	           				tab.table.getValueAt(i, 2).toString().equals(tab.table.getValueAt(j, 2).toString())
	  	           		    	&&tab.table.getValueAt(i, 4).toString().equals(tab.table.getValueAt(j, 3).toString())){
	  	           			   repet2=true;
	  	           		   }
	  	           	   }
	           	   }}
   		
		if(valid_j==false) {msg+="Un Champ dans le tableau est vide \n";}
		else if(valid_heure==false) {msg+="Heure non valid ou heure debut est superieure a celle de fin \n";}
		else if(repet2==true){msg+="Il existe une ou plusieurs changement dupliquées \n";}
		
        if(!msg.equals("")){JOptionPane.showMessageDialog(null,msg);  }
		  		            	
		if(repet2==false&&valid_j==true&&comb==false&&zomb==false&&valid_heure==true){
			chang.delete_changement(chaine, zone, picker.getEditor().getText());
			for(int i=0;i<tab.table.getRowCount();i++){


				String[] perm =tab.table.getValueAt(i, 1).toString().split(" ");
				perm_f = perm[0]; // permanat final
				String[] vol =tab.table.getValueAt(i, 2).toString().split(" ");
				vol_f = vol[0]; // volant final
				String[] post =tab.table.getValueAt(i, 0).toString().split(" ");
				post_f= post[0]; // post final.

				heur_d =tab.table.getValueAt(i, 3).toString();
				heur_f =tab.table.getValueAt(i, 4).toString();

				chang.Update_changement(chaine, zone, post_f, perm_f,vol_f,  picker.getEditor().getText(), heur_d, heur_f);

			}
		  		JOptionPane.showMessageDialog(null,"Modification réussite");

	    }}}});
	 
	 supp.addActionListener(
             new ActionListener() { 
             @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
            	
             comb=false; zomb=false;
             if(chaine_combo.getSelectedIndex()==0){  comb=true; }
	 	  	 if(zone_combo.getSelectedIndex()==0){  zomb=true; }	
	 	  	 if(comb==true || zomb==true){	
	 	  		 JOptionPane.showMessageDialog(null, 
	 	      	 "Vous Devez d'abord Sélectionner une chaine ou une zone \n", "",
	 	  		  JOptionPane.INFORMATION_MESSAGE);  	    
             }else{
              LineBorder border = new LineBorder ( Color.white, 1, true );
       		  TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
       	      TitledBorder.DEFAULT_POSITION, police2, Color.white); 
       		 panel_gener.setBorder(titl2);
       		 ajout.setVisible(false);
       		 modif.setVisible(false);
       		 supp.setVisible(false);
       		 
       		 retour.setVisible(true);
       	 	valid_sup.setVisible(true);
       		 valid_ajout.setVisible(false);
       		 valid_modif.setVisible(false);
       		 pan_but_perso.setVisible(false);

	 	  	 }}});
	 

	 
	 but_supprim.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
             if(tab.table.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(null, 
     	 	    "Vous devez d'abord sélectionner la zone ", "",
     	 	  	JOptionPane.INFORMATION_MESSAGE);
                	 }
               else{
     			((DefaultTableModel) tab.table.getModel()).removeRow(tab.table.getSelectedRow());
     			} }});
	 
	 
	 but_ajou.addActionListener(
	            new ActionListener() { 
	            public void actionPerformed(ActionEvent e) {
	            msg="";
	         	if(zone_combo.getSelectedIndex()==0&&chaine_combo.getSelectedIndex()==0){
		        	  JOptionPane.showMessageDialog(null, 
		 	      	  "Vous Devez d'abord Sélectionner une chaine ou une zone \n", "",
		 	  		   JOptionPane.INFORMATION_MESSAGE);
		          }
		         else{
		        	    tab.ajouter();
		        	    tab.table.getColumnModel().getColumn(0).setCellEditor(new CustomComboBoxEditor());
		                tab.table.getColumnModel().getColumn(1).setCellEditor(new CustomComboBoxEditor1());
			  	      	tab.table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(vol_combo));  
			  	      	TableColumn gradeColumn3 = tab.table.getColumnModel().getColumn(3);
			  	      	TableColumn gradeColumn4 = tab.table.getColumnModel().getColumn(4);
			  	      MaskFormatter num = null;
			          try {
			               num = new MaskFormatter("##:##");
			              } 
			          catch (ParseException e1) {
			                                    e1.printStackTrace();
			                                   }
			          MaskFormatter num2 = null;
			          try {
			               num2 = new MaskFormatter("##:##");
			              } 
			          catch (ParseException e2) {
			                                    e2.printStackTrace();
			                                   }
			            JFormattedTextField heur_d= new  JFormattedTextField(num);
			            JFormattedTextField heur_f= new  JFormattedTextField(num2);	        
				      //gradeColumn.setCellEditor(new DefaultCellEditor(poste_combo)); 
			          //gradeColumn2.setCellEditor(new DefaultCellEditor(heur_d));  	 
				        gradeColumn3.setCellEditor(new DefaultCellEditor(heur_d));  
				        gradeColumn4.setCellEditor(new DefaultCellEditor(heur_f));  
			         //	gradeColumn1.setCellEditor(new DefaultCellEditor(perm_combo));   
			         	  int k=tab.table.getRowCount();
				      	  tab.table.setValueAt("", k-1,0);
				          tab.table.setValueAt("", k-1,1);
				          tab.table.setValueAt("", k-1,2);
				          tab.table.setValueAt("", k-1,3);
				          tab.table.setValueAt("", k-1,4);

		          }}});
	 
	tab.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		   public void valueChanged(ListSelectionEvent event) {		
		     perm_combo.removeAllItems();
		   	 perm_combo.addItem("");
		     poste= poste_combo.getSelectedItem().toString().split(" ");
	         poste1=poste[0];
		     if(tab.table.getRowCount()>0){
		 	 //System.out.println(tab.table.getSelectedRow());
		    	 list_perm_tr=use.select_permanant(poste1);
		 	 for(int i=0;i<list_perm_tr.size();i++)
		 	   {
		 	          //Pour affecter une valeur de base de données é un Combobox
		 		perm_combo.addItem(list_perm_tr.get(i)+" "+list_perm_tr.get(i+1)+" "+list_perm_tr.get(i+2));
		 		   i=i+2;
		 	   }}
	  }}); 
	
	   
	   
		 retour.addActionListener(
              new ActionListener() { 
              @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
            	  
              	 LineBorder border = new LineBorder ( Color.white, 1, true );
         		 TitledBorder titl2 = new TitledBorder ( border, "Changement Chaine", TitledBorder.DEFAULT_POSITION,
         	     TitledBorder.DEFAULT_POSITION, police2, Color.white);
         		panel_gener.setBorder(titl2);
              	 ajout.setVisible(true);
          		 modif.setVisible(false);
          		 supp.setVisible(false);
          		 retour.setVisible(false);
          		 valid_ajout.setVisible(false);
          		 valid_modif.setVisible(false);
          		 valid_sup.setVisible(false);
                 pan_but_perso.setVisible(false);

	           	 zone_combo.setSelectedIndex(0);
	           	 chaine_combo.setSelectedIndex(0);
	             picker.setDate(Calendar.getInstance().getTime());
	     	     int rows = tab.table.getRowCount(); 

	        	for(int i = rows - 1; i >=0; i--)
	           	{
	           		((DefaultTableModel) tab.table.getModel()).removeRow(i);

	           	} 
               }});
		 
		 try{
		   	//	UIManager.setLookAndFeel(laf);
		   		SwingUtilities.updateComponentTreeUI(this);
		   		
		   	}catch(Exception e1){
		   		
		   		e1.printStackTrace();
		   	} 

		 
panel.add(panel_gener);	

panel_gener.add(pan_form);
pan_form.add(pan_date);
pan_form.add(pan_chaine);
pan_form.add(pan_zone);
panel.add(panel_but);
pan_date.add(pan_lab_date);
pan_lab_date.add(date_lab);
pan_date.add(pan_jtext_date);
pan_jtext_date.add(picker);
pan_chaine.add(pan_lab_chaine);
pan_lab_chaine.add(chaine_lab);
pan_chaine.add(pan_jtext_chaine);	
pan_jtext_chaine.add(chaine_combo);
pan_zone.add(pan_lab_zone);
pan_lab_zone.add(zone_lab);
pan_zone.add(pan_jtext_zone);
pan_jtext_zone.add(zone_combo);

pan_tab.add(p);
pan_tab.add(pan_but_perso); 
pan_but_perso.add(but_ajou);
pan_but_perso.add(but_supprim);

panel_gener.add(pan_tab);
panel_but.add(retour);

panel_but.add(modif);
panel_but.add(ajout);
panel_but.add(supp);
panel_but.add(valid_sup);
panel_but.add(valid_modif);
panel_but.add(valid_ajout);




panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
pan_tab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
pan_form.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
pan_but_perso.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));


pan_lab_date.setBorder(BorderFactory.createEmptyBorder(0, -170, -30, 0));
pan_lab_chaine.setBorder(BorderFactory.createEmptyBorder(0, -170,-30, 0));
pan_lab_zone.setBorder(BorderFactory.createEmptyBorder(0, -170, -30, 0));


panel_gener.setLayout(new BoxLayout(panel_gener,BoxLayout.X_AXIS));
panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
pan_date.setLayout(new BoxLayout(pan_date,BoxLayout.Y_AXIS));
pan_chaine.setLayout(new BoxLayout(pan_chaine,BoxLayout.Y_AXIS));
pan_zone.setLayout(new BoxLayout(pan_zone,BoxLayout.Y_AXIS));
pan_tab.setLayout(new BoxLayout(pan_tab,BoxLayout.Y_AXIS));

pan_lab_date.setLayout( new FlowLayout(  FlowLayout.CENTER));
pan_lab_chaine.setLayout( new FlowLayout(  FlowLayout.CENTER));
pan_lab_zone.setLayout( new FlowLayout(  FlowLayout.CENTER));
pan_jtext_date.setLayout( new FlowLayout(  FlowLayout.CENTER));
pan_jtext_chaine.setLayout( new FlowLayout(  FlowLayout.CENTER));
pan_jtext_zone.setLayout( new FlowLayout(  FlowLayout.CENTER));
panel_but.setLayout( new FlowLayout(  FlowLayout.RIGHT));
pan_but_perso.setLayout( new FlowLayout( FlowLayout.CENTER ));

panel.setOpaque(false);
pan_date.setOpaque(false);
pan_lab_date.setOpaque(false);
pan_jtext_date.setOpaque(false);
pan_lab_chaine.setOpaque(false);
pan_jtext_chaine.setOpaque(false);
pan_lab_zone.setOpaque(false);
pan_jtext_zone.setOpaque(false);
panel_gener.setOpaque(false);
pan_chaine.setOpaque(false);
pan_zone.setOpaque(false);
panel_but.setOpaque(false);
//pan_tab.setOpaque(false);
pan_form.setOpaque(false);


but_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
but_supprim.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
ajout.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
modif.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
retour.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
supp.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
valid_ajout.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
valid_sup.setFont(new Font("Comic Sans MS", Font.BOLD, 15));


but_ajou.setPreferredSize(new Dimension(180, 30));
but_supprim.setPreferredSize(new Dimension(180, 30));
ajout.setPreferredSize(new Dimension(130, 33));
modif.setPreferredSize(new Dimension(130, 33));
supp.setPreferredSize(new Dimension(130, 33));
retour.setPreferredSize(new Dimension(130, 33));
valid_sup.setPreferredSize(new Dimension(130, 33));
valid_modif.setPreferredSize(new Dimension(130, 33));
valid_ajout.setPreferredSize(new Dimension(130, 33));
p.setPreferredSize(new Dimension(700,700));

//pan_form.setPreferredSize(new Dimension(250,700));


date_lab.setForeground(new Color(255,255,255)); 
zone_lab.setForeground(new Color(255,255,255)); 
chaine_lab.setForeground(new Color(255,255,255)); 

picker.setPreferredSize(new Dimension(210, 30));
chaine_combo.setPreferredSize(new Dimension(210, 30));
zone_combo.setPreferredSize(new Dimension(210, 30));
 

date_lab.setFont(police2);
chaine_lab.setFont(police2);
zone_lab.setFont(police2);

setTitle("Changement de chaine");
setSize(1000, 600);
setLocationRelativeTo(null);          
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setContentPane(panel);
tab.table.setRowHeight(30);


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


if(om.trim().length()==0)

{
	msg="";
return false;

}}}


return true;
}	
class CustomComboBoxEditor extends DefaultCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	gestion_user use=new gestion_user();
    // Declare a model that is used for adding the elements to the `ComboBox`
    private DefaultComboBoxModel<String> model;

    private List<String> obtainedList;

    @SuppressWarnings("unchecked")
	public CustomComboBoxEditor() {
        super(new JComboBox<String>());
        this.model = (DefaultComboBoxModel<String>)((JComboBox<String>)getComponent()).getModel();
        obtainedList = new ArrayList<String>();
        for(int i = 0; i < pos.select_poste_code().size(); i++) {
        obtainedList.add(pos.select_poste_code().get(i)+" "+pos.select_poste_code().get(i+1));
        i=i+1;
    }
    }
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
       if(column == 0) {
           model.removeAllElements();
           for(int i = 0; i < obtainedList.size(); i++) {
               model.addElement(obtainedList.get(i));
               
            } 
        } else {

             model.removeAllElements();
             String selectedItem = (String) table.getValueAt(row, 0);
             for(int i = 0; i < obtainedList.size(); i++) {
                    if(!selectedItem.equals(obtainedList.get(i)))
                    model.addElement(obtainedList.get(i));

             } 
         } // Close else
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
     }
    }
class CustomComboBoxEditor1 extends DefaultCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	gestion_user use=new gestion_user();
    // Declare a model that is used for adding the elements to the `ComboBox`
    private DefaultComboBoxModel<String> model;

    List<String> obtainedList1;

    @SuppressWarnings("unchecked")
	public CustomComboBoxEditor1() {
        super(new JComboBox<String>());
        this.model = (DefaultComboBoxModel<String>)((JComboBox<String>)getComponent()).getModel();
        obtainedList1 = new ArrayList<String>();
      
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		  String[] ItemSEL = table.getValueAt(row, 0).toString().split(" ");
		  obtainedList1.clear();
          String Item=ItemSEL[0];
          model.removeAllElements();
		 // System.out.println(Item);
		  for(int i = 0; i < use.select_permanant(Item).size(); i++) {
		  obtainedList1.add(use.select_permanant(Item).get(i)+" "+use.select_permanant(Item).get(i+1)+" "+use.select_permanant(Item).get(i+2));
		  i=i+2;
		  }
          for(int i = 0; i < obtainedList1.size(); i++) {
		    model.addElement(obtainedList1.get(i));}
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
     }
    }


public boolean validTime24(String time) {    

    return time.matches("^([01]\\d|2[0-3]):[0-5]\\d$");
}


public void action_chaine_zone_date(){
	
	 if(chaine_combo.getSelectedIndex()!=0&&zone_combo.getSelectedIndex()!=0&&!picker.getEditor().equals("")){
		 String[] partsc = chaine_combo.getSelectedItem().toString().split(" ");
	     String partc1 = partsc[0]; // 004
	     String[] partsz = zone_combo.getSelectedItem().toString().split(" ");
        String partz1 = partsz[0]; // 004
   	     
   	 
  		 if(chang.select_chang(partc1, partz1 ,picker.getEditor().getText())==true){
		      JOptionPane.showMessageDialog(null,"Ce changement est n'existe pas.");
		        tab.allowEdition = true;
          	   tab.allowEdition1 = true;
          		tab.allowEdition2 = true;
          		tab.allowEdition3 = true;
          		tab.allowEdition4 = true;
          		tab.allowEdition5 = true;
  			 ajout.setVisible(true);
      		 modif.setVisible(false);
      		 supp.setVisible(false);
      		 retour.setVisible(false);
      		 valid_ajout.setVisible(false);
      		 valid_modif.setVisible(false);
      		 valid_sup.setVisible(false);
      		 pan_but_perso.setVisible(true);
      		 int rows = tab.table.getRowCount(); 

	        	for(int i = rows - 1; i >=0; i--)
	           	{
	           		((DefaultTableModel) tab.table.getModel()).removeRow(i);

	           	} 
  		 }
  		 else{
  			   tab.allowEdition = false;
        	   tab.allowEdition1 = false;
        		tab.allowEdition2 = false;
        		tab.allowEdition3 = false;
        		tab.allowEdition4 = false;
        		tab.allowEdition5 = false;
  			 ajout.setVisible(false);
      		 modif.setVisible(true);
      		 supp.setVisible(true);
      		 retour.setVisible(false);
      		 valid_ajout.setVisible(false);
      		 valid_modif.setVisible(false);
      		 valid_sup.setVisible(false);
      		 pan_but_perso.setVisible(true);
  		 }
	 }
	 else{
		 tab.allowEdition = true;
     	tab.allowEdition1 = true;
     		tab.allowEdition2 = true;
     		tab.allowEdition3 = true;
     		tab.allowEdition4 = true;
     		tab.allowEdition5 = true;
		 ajout.setVisible(true);
  		 modif.setVisible(false);
  		 supp.setVisible(false);
  		 retour.setVisible(false);
  		 valid_ajout.setVisible(false);
  		 valid_modif.setVisible(false);
  		 valid_sup.setVisible(false);
  		 pan_but_perso.setVisible(true);
  		 int rows = tab.table.getRowCount(); 

        	for(int i = rows - 1; i >=0; i--)
           	{
           		((DefaultTableModel) tab.table.getModel()).removeRow(i);

           	} 
		 
	 }
	
}

public void remplir_table(){
	
	if(chaine_combo.getSelectedIndex()!=0&&zone_combo.getSelectedIndex()!=0)
	   {
		String[] partc = chaine_combo.getSelectedItem().toString().split(" ");
	      String partc1 = partc[0]; // 004
	      String[] partz = zone_combo.getSelectedItem().toString().split(" ");
        String partz1 = partz[0]; // 004
	  //chang.select_chang(partc1, partz1,  picker.getEditor().getText());
		String pick =picker.getEditor().getText();
		list_chang=chang.select_chaine_zone_date(partc1,partz1,pick);
		list_chang_volant=chang.select_chaine_zone_date_volant(partc1,partz1,pick);
	for(i=0;i<(list_chang.size()+list_chang_volant.size());i=i+11){
		tab.ajouter();
		TableColumn gradeColumn = tab.table.getColumnModel().getColumn(0);
    	TableColumn gradeColumn1 = tab.table.getColumnModel().getColumn(1);
    	TableColumn gradeColumn2 = tab.table.getColumnModel().getColumn(2);
    	TableColumn gradeColumn3 = tab.table.getColumnModel().getColumn(3);
    	TableColumn gradeColumn4 = tab.table.getColumnModel().getColumn(4);
    MaskFormatter num = null;
  try {
       num = new MaskFormatter("##:##");
      } 
  catch (ParseException e1) {
                            e1.printStackTrace();
                           }
  MaskFormatter num2 = null;
  try {
       num2 = new MaskFormatter("##:##");
      } 
  catch (ParseException e2) {
       e2.printStackTrace();
                            }
    JFormattedTextField heur_d= new  JFormattedTextField(num);
    JFormattedTextField heur_f= new  JFormattedTextField(num2);
 	gradeColumn3.setCellEditor(new DefaultCellEditor(heur_d));  
 	gradeColumn4.setCellEditor(new DefaultCellEditor(heur_f));  
 	gradeColumn.setCellEditor(new DefaultCellEditor(poste_combo));  
 	gradeColumn1.setCellEditor(new DefaultCellEditor(perm_combo));  	 
 	gradeColumn2.setCellEditor(new DefaultCellEditor(vol_combo));  	 
	   }


	   int j=1;
	   int l = 0; i=1;
	   while(l<(list_chang.size())){
	   while(l<j*7){
			 tab.getTable().setValueAt(list_chang.get(l)+" "+list_chang.get(l+1), j-1,0);
			 tab.getTable().setValueAt(list_chang.get(l+2)
					+" "+list_chang.get(l+3)
					+" "+list_chang.get(l+4),j-1,1);


			 tab.getTable().setValueAt(list_chang.get(l+5), j-1,3);
			 tab.getTable().setValueAt(list_chang.get(l+6), j-1,4);
			 i=i+1; l=l+7;
		 }
		i=1;
		j=j+1;		
		}


	int jv=1;
	int lv = 0; i=1;
	while(lv<(list_chang_volant.size())){
		while(lv<jv*3){

			tab.getTable().setValueAt(list_chang_volant.get(lv)
					+" "+list_chang_volant.get(lv+1)
					+" "+list_chang_volant.get(lv+2),jv-1,2);
			i=i+1;
			lv=lv+3;
		}
		i=1;
		jv=jv+1;
	}}
}

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				changement_chaine frame = new changement_chaine("7");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}

}
