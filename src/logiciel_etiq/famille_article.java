package logiciel_etiq;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class famille_article extends JFrame  implements ActionListener{
	private static final String    CTRL_J                = "CTRL+J";
	   final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
	 

      // gestion de l'action
     
       if (e.getActionCommand().equals(CTRL_J)){
    	   LineBorder border = new LineBorder ( Color.white, 1, true );
       	  TitledBorder titl2 = new TitledBorder ( border, "Edition Type article", TitledBorder.DEFAULT_POSITION,
           TitledBorder.DEFAULT_POSITION, police2, Color.white);
           pan_form.setBorder(titl2);
           
         cartfamilleence.setText(artfamil.afficher_code_artfamille());
         artfamille_comb.enable(); 
        	cartfamilleence.disable();
         designation.enable();
        	but_sauv.setVisible(true);
        	but_modif.setVisible(false);
        	but_sup.setVisible(false);
        	retour.setVisible(false);
        	valid_supp.setVisible(false);
        	valid_ajou.setVisible(false);
        	valid_modif.setVisible(false);
         designation.setText("");
 	 
     artfamille_comb.setSelectedIndex(0);
 	cartfamilleence.setText(artfamil.afficher_code_artfamille());
 	
       }}
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JPanel panel;
private  String msg="";
private boolean desc;
private boolean exist;

private JPanel pan_combo=new JPanel();
private JPanel pan_lab_artfamille=new JPanel();
private JPanel pan_combo_artfamille=new JPanel();
private JPanel pan_form=new JPanel();
private	JPanel pan_artfamille=new JPanel();
private	JPanel pan_lab_cartfamille=new JPanel();
private	JPanel pan_jtext_cartfamille=new JPanel();
private	JPanel pan_designation=new JPanel();
private	JPanel pan_lab_designation=new JPanel();
private	JPanel pan_jtext_designation=new JPanel();	
private	JPanel pan_button=new JPanel();
private	JButton but_sauv=new JButton("Ajouter");
private	JButton but_modif=new JButton("Modifier");
private	JButton but_sup=new JButton("Supprimer");	
private	JButton valid_ajou=new JButton("Valider");
private JButton valid_modif=new JButton("Valider");
private JButton valid_supp=new JButton("Valider");
private JButton retour=new JButton("Retour");
	
private JLabel lab_combo=new JLabel("Liste des familles articles");
private  jcombo artfamille_comb;

private JLabel artfamille_lab=new JLabel("Code famille article");
private JTextField cartfamilleence=new JTextField();
private JLabel designation_lab=new JLabel("Désignation");
private JTextField designation=new JTextField();
private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

private  static gestion_famille_article artfamil=new gestion_famille_article();
private static List<String> list_t;
private static ArrayList<String> list_artfamil_tr=new ArrayList<String>();

famille_article(final String log){ 	
	Toolkit kit = Toolkit.getDefaultToolkit();
    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
    setIconImage(img);
    selectioncomb.windows(this,log,laf);


try{
		UIManager.setLookAndFeel(laf);
		SwingUtilities.updateComponentTreeUI(this);
		
	}catch(Exception e1){
		
		e1.printStackTrace();
	}
composant();
}
@SuppressWarnings("deprecation")
public void composant(){
	
	list_t= new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un type article-----"}));
	artfamille_comb = new jcombo(list_t.toArray());
	list_artfamil_tr=artfamil.select_artfamille_code();
	   for(int i=0;i<list_artfamil_tr.size();i++)
	   {
	          //Pour affecter une valeur de base de donn?es ? un Combobox 
		  artfamille_comb.addItem(list_artfamil_tr.get(i)+" "+list_artfamil_tr.get(i+1));
		   i++;
	   }
	artfamille_comb.setWide(true);
	Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
	
	if(!selectioncomb.prv.contains("famille"))
	 selectioncomb.prv.add("famille");
	
    final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);
but_sauv.setVisible(true);
but_modif.setVisible(false);
but_sup.setVisible(false);
retour.setVisible(false);
valid_supp.setVisible(false);
valid_ajou.setVisible(false);
valid_modif.setVisible(false);

	  panel= new JPanel(){   
  		/** * */
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g){
  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
             img.paintIcon(this, g,0, 0);
  	}  };
  	
 	panel.registerKeyboardAction(this, CTRL_J, KeyStroke.getKeyStroke(
            KeyEvent.VK_J, Event.CTRL_MASK),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
 	
  	cartfamilleence.disable();
  	designation.enable();

	LineBorder border = new LineBorder ( Color.white, 1, true );
	TitledBorder titl2 = new TitledBorder ( border, "Edition Type article", TitledBorder.DEFAULT_POSITION,
    TitledBorder.DEFAULT_POSITION, police2, Color.white);
    pan_form.setBorder(titl2);
      
	retour.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
              LineBorder border = new LineBorder ( Color.white, 1, true );
          	  TitledBorder titl2 = new TitledBorder ( border, "Edition Type article", TitledBorder.DEFAULT_POSITION,
              TitledBorder.DEFAULT_POSITION, police2, Color.white);
              pan_form.setBorder(titl2);
              
            cartfamilleence.setText(artfamil.afficher_code_artfamille());
            artfamille_comb.enable(); 
           	cartfamilleence.disable();
            designation.enable();
           	but_sauv.setVisible(true);
           	but_modif.setVisible(false);
           	but_sup.setVisible(false);
           	retour.setVisible(false);
           	valid_supp.setVisible(false);
           	valid_ajou.setVisible(false);
           	valid_modif.setVisible(false);
            designation.setText("");
    	 
        artfamille_comb.setSelectedIndex(0);
    	
            }});
  	/////////////////////////////////////////////////////////
    cartfamilleence.setText(artfamil.afficher_code_artfamille());
    
    
    but_sup.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
           LineBorder border = new LineBorder ( Color.white, 1, true );
           TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
           TitledBorder.DEFAULT_POSITION, police2, Color.white);
           pan_form.setBorder(titl2);

           artfamille_comb.enable(); 
           cartfamilleence.disable();
           artfamil.type(designation.getText());
           if(artfamil.ex==false){
            	JOptionPane.showMessageDialog(null, "Ce type n'exist pas", "",
            	JOptionPane.INFORMATION_MESSAGE);
            	but_sauv.setVisible(true);
               but_modif.setVisible(false);
               but_sup.setVisible(false);
               retour.setVisible(false);
               valid_supp.setVisible(false);
               valid_modif.setVisible(false);
               valid_ajou.setVisible(false);
               artfamille_comb.setSelectedIndex(0);
          	  cartfamilleence.setText(artfamil.afficher_code_artfamille());
          	
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
            	msg="";
                artfamil.type(designation.getText());

            	   if(artfamille_comb.getSelectedIndex()==0){
	      				msg+="Vous Devez D'abord Selectionner un type article \n";
          }
                else if(artfamil.ex==false){msg+="Ce type n'exist pas \n";}
            	if(!msg.equals("")){
	            		
	  	            	JOptionPane.showMessageDialog(null,msg);                                                                            
	  	            	}
            	else{
  	artfamil.delete_artfamille(cartfamilleence.getText(),designation.getText());
   designation.setText("");
  	artfamille_comb.removeAllItems();
  	artfamille_comb.addItem("---Selectionner un type article-----");
  	list_artfamil_tr=artfamil.select_artfamille_code();
	for(int i=0;i<list_artfamil_tr.size();i++)
	   {
	          //Pour affecter une valeur de base de donn?es ? un Combobox 
		   artfamille_comb.addItem(list_artfamil_tr.get(i)+" "+list_artfamil_tr.get(i+1));
		   i++;
	   }
            	}
            }});
  	
  	 
	but_modif.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	LineBorder border = new LineBorder ( Color.white, 1, true );
          	  TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
               TitledBorder.DEFAULT_POSITION, police2, Color.white);
               pan_form.setBorder(titl2);
              artfamille_comb.enable(); 
             
//              if(artfamil.ex==false){
//               	JOptionPane.showMessageDialog(null, "Ce Type n'exist pas", "",
//               	JOptionPane.INFORMATION_MESSAGE);
//               	but_sauv.setVisible(true);
//                  but_modif.setVisible(false);
//                  but_sup.setVisible(false);
//                  retour.setVisible(false);
//                  valid_supp.setVisible(false);
//                  valid_modif.setVisible(false);
//                  valid_ajou.setVisible(false);
//             	 artfamille_comb.setSelectedIndex(0);
//             	cartfamilleence.setText(artfamil.afficher_code_artfamille());
//             	
//               }
//               else{
             	  but_sauv.setVisible(false);
                   but_modif.setVisible(false);
                   but_sup.setVisible(false);
                   retour.setVisible(true);
                   valid_supp.setVisible(false);
                   valid_modif.setVisible(true);
                   valid_ajou.setVisible(false);	
               //}
          
            }});
  	valid_modif.addActionListener(
	            new ActionListener() { 
	            public void actionPerformed(ActionEvent e) {
	            	   msg="";
	            	   desc=false;
	            	   artfamil.type(designation.getText());
	            	   if (artfamil.ex==true) {msg+="Ce type article existe déja \n";}
	            	   else if(artfamille_comb.getSelectedIndex()==0){
	        	      				msg+="Vous Devez D'abord Sélectionner un type article\n";
	                   }
	            	   else if(designation.getText().equals("")) {	desc=true;
	                          msg+="Veuillez remplir l'intitule \n";
 
	            	 	}
	            	 	
	            	 	if(!msg.equals("")){
  		            		
 		  	            	JOptionPane.showMessageDialog(null,msg);                                                                            
 		  	            	}
	            	 	else{
	    	            	artfamil.setupdate_artfamille(cartfamilleence.getText(), designation.getText());
	    	            	 JOptionPane.showMessageDialog(null,"Le Type article a été bien modifier");
	    	            	 designation.setText("");
	    	            		artfamille_comb.removeAllItems();
	    	            	  	 artfamille_comb.addItem("---Selectionner un type article-----");
	    	            	  	list_artfamil_tr=artfamil.select_artfamille_code();
	    	            		   for(int i=0;i<list_artfamil_tr.size();i++)
	    	            		   {
	    	            		          //Pour affecter une valeur de base de donn?es ? un Combobox 
	    	            			   artfamille_comb.addItem(list_artfamil_tr.get(i)+" "+list_artfamil_tr.get(i+1));
	    	            			   i++;
	    	            		   }cartfamilleence.setText(artfamil.afficher_code_artfamille());
	    	            	 	}
	            	 	
			  	    	}});
	   
	
  
  	 but_sauv.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
            	 LineBorder border = new LineBorder ( Color.white, 1, true );
           	  TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
                   TitledBorder.DEFAULT_POSITION, police2, Color.white);
                 pan_form.setBorder(titl2);
                 artfamille_comb.disable();
                artfamil.type(designation.getText());

                 if(artfamil.ex==true){
                 	 JOptionPane.showMessageDialog(null, "Ce Type exist déja", "",
                 	 JOptionPane.INFORMATION_MESSAGE);
                 	   but_sauv.setVisible(false);
                      but_modif.setVisible(true);
                      but_sup.setVisible(true);
                      retour.setVisible(false);
                      valid_supp.setVisible(false);
                      valid_modif.setVisible(false);
                      valid_ajou.setVisible(false);
                      cartfamilleence.setText(artfamil.code);
                      //System.out.println()
                      artfamille_comb.setSelectedItem(artfamil.code+" "+artfamil.des); 
                      designation.setText(artfamil.des);
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
  	
   valid_ajou.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
        	   msg="";
        	   desc=false;
        	   artfamil.type(designation.getText());
        	   
        	 	if(designation.getText().equals("")) {	desc=true;
                      msg+="Veuillez remplir la designation \n";}
        	 	else if (artfamil.ex==true) {msg+="Ce type article existe déja \n";}
        	 	else{
        	 		
        	  exist= artfamil.ajouter_artfamille(cartfamilleence.getText(), designation.getText());
        	  artfamille_comb.removeAllItems();
        	  	 artfamille_comb.addItem("---Selectionner un type article-----");
        		   for(int i=0;i<artfamil.select_artfamille_code().size();i++)
        		   {
        		          //Pour affecter une valeur de base de donn?es ? un Combobox 
        			   artfamille_comb.addItem(artfamil.select_artfamille_code().get(i)+" "+artfamil.select_artfamille_code().get(i+1));
        			   i++;
        		   }
        		   cartfamilleence.setText(artfamil.afficher_code_artfamille());
        	 	
        	 	}
        	 	 if(exist==false&&desc==false&&artfamil.ex==false){
              	   JOptionPane.showMessageDialog(null,"Le Type a été bien ajouter");
              	   artfamille_comb.enable();
                   designation.setText("");
                 }
                 else JOptionPane.showMessageDialog(null,msg);
        	 			  	 
        	 	
	    		}});
   
   
  
   
   artfamille_comb.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
        	   LineBorder border = new LineBorder ( Color.white, 1, true );
               TitledBorder titl2 = new TitledBorder ( border, "Edition Type", TitledBorder.DEFAULT_POSITION,
               TitledBorder.DEFAULT_POSITION, police2, Color.white);
               pan_form.setBorder(titl2);

           	   if(artfamille_comb.getSelectedIndex()==0){
           		 if(valid_modif.isVisible()==false||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
           	         but_sauv.setVisible(true);
           	         valid_ajou.setVisible(false);
                      valid_supp.setVisible(false);
                      valid_modif.setVisible(false);
                      retour.setVisible(false);
           	     }
                  
           	    but_modif.setVisible(false);
                  but_sup.setVisible(false);
           	      cartfamilleence.setText(artfamil.afficher_code_artfamille());           		   
           	   }
           	   else {      
           		 if(artfamille_comb.getItemCount()>1){
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
            		  String[] parts = artfamille_comb.getSelectedItem().toString().split(" ");
            		  String part1 = parts[0]; // 004
            		  String part2 =artfamille_comb.getSelectedItem().toString().replace(part1+" ", ""); 
            		cartfamilleence.setText(part1);
            		designation.setText(part2);
           		 }
        	   }
        	  // }
           }});
   
   designation.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
           artfamille_comb.enable();
           LineBorder border = new LineBorder ( Color.white, 1, true );
           TitledBorder titl2 = new TitledBorder ( border, "Edition Poste", TitledBorder.DEFAULT_POSITION,
           TitledBorder.DEFAULT_POSITION, police2, Color.white);
           pan_form.setBorder(titl2);
           artfamil.type(designation.getText());
           if(designation.getText().equals("")||artfamil.ex==false){
        	   if(valid_modif.isVisible()==false||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
        	       but_sauv.setVisible(true);
        	       valid_ajou.setVisible(false);
                   valid_supp.setVisible(false);
                   valid_modif.setVisible(false);
                   retour.setVisible(false);

        	     }
               
        	   but_modif.setVisible(false);
               but_sup.setVisible(false);
               
               cartfamilleence.setText(artfamil.afficher_code_artfamille());
              artfamille_comb.setSelectedIndex(0);
           	   }
          else  {   
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
        	cartfamilleence.setText(artfamil.code);
          designation.setText(artfamil.des);
         artfamille_comb.setSelectedItem(artfamil.code+" "+artfamil.des); 
        	   }
        	  // }
           }});
   
     cartfamilleence.setPreferredSize(new Dimension(250, 30));
     designation.setPreferredSize(new Dimension(250,30));
     panel.add(pan_form);
     pan_form.add(pan_combo);
     pan_form.add(pan_artfamille);
     pan_form.add(pan_designation);
     pan_form.add(pan_button);
     pan_form.setOpaque(false);
  	pan_combo.add(pan_lab_artfamille);
  	pan_combo.add(pan_combo_artfamille);

  	
  	pan_lab_artfamille.add(lab_combo);
  	pan_combo_artfamille.add(artfamille_comb);
  	
  	pan_artfamille.add(pan_lab_cartfamille);
  	pan_artfamille.add(pan_jtext_cartfamille);

  	pan_lab_cartfamille.add(artfamille_lab);
  	pan_jtext_cartfamille.add(cartfamilleence);
	
	pan_designation.add(pan_lab_designation);
  	pan_designation.add(pan_jtext_designation);
  	
  	pan_lab_designation.add(designation_lab);
  	pan_jtext_designation.add(designation);

  	pan_button.add(but_sauv);
	pan_button.add(but_modif);	
	pan_button.add(but_sup);
	pan_button.add(retour);
	pan_button.add(valid_modif);	
	pan_button.add(valid_supp);
	pan_button.add(valid_ajou);

	
  	pan_button.setOpaque(false);
  	pan_artfamille.setOpaque(false);
  	pan_designation.setOpaque(false);
  	pan_lab_artfamille.setOpaque(false);
  	pan_combo_artfamille.setOpaque(false);
  	pan_lab_cartfamille.setOpaque(false);
  	pan_jtext_cartfamille.setOpaque(false);
  	pan_lab_designation.setOpaque(false);
  	pan_jtext_designation.setOpaque(false);
  	pan_combo.setOpaque(false);
  	
  	artfamille_comb.setFont(police_fi);
  	cartfamilleence.setFont(police_fi);
  	designation.setFont(police_fi);

  	artfamille_lab.setFont(police2);
  	designation_lab.setFont(police2);
  	lab_combo.setFont(police2);
  	artfamille_lab.setForeground(new Color(255,255,255)); 
  	designation_lab.setForeground(new Color(255,255,255)); 
  	lab_combo.setForeground(new Color(255,255,255)); 
  	
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
     artfamille_comb.setPreferredSize(new Dimension(250, 33));

    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    pan_button.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
 
    pan_lab_cartfamille.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, -35));
    pan_jtext_cartfamille.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, -35));

    pan_lab_designation.setBorder(BorderFactory.createEmptyBorder(0, 70, 20, 0));
    pan_lab_artfamille.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

    pan_artfamille.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 0));
    pan_combo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 30));
    pan_designation.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 30));

    pan_lab_artfamille.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_combo_artfamille.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
    
    pan_lab_cartfamille.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_jtext_cartfamille.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
    pan_lab_designation.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_jtext_designation.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
  	pan_button.setLayout( new FlowLayout( FlowLayout.CENTER ));

  	pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
  	
  	panel.setLayout(new GridLayout(1, 1));  	
  	pan_combo.setLayout(new BoxLayout(pan_combo,BoxLayout.X_AXIS));
  	pan_artfamille.setLayout(new BoxLayout(pan_artfamille,BoxLayout.X_AXIS));
  	pan_designation.setLayout(new BoxLayout(pan_designation,BoxLayout.X_AXIS));

    setTitle("Gestion des familles articles" );
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
				famille_article frame = new famille_article("7");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}








