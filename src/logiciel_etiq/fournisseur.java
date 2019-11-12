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


public class fournisseur extends JFrame implements ActionListener{
	private static final String    CTRL_J                = "CTRL+J";
	   final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
    	 
     
            // gestion de l'action
           
             if (e.getActionCommand().equals(CTRL_J)){
            	 LineBorder border = new LineBorder ( Color.white, 1, true );
               	TitledBorder titl2 = new TitledBorder ( border, "Edition Fournisseur", TitledBorder.DEFAULT_POSITION,
                 TitledBorder.DEFAULT_POSITION, police2, Color.white);
                 pan_form.setBorder(titl2);
                 fourniss_comb.enable(); 
                	fourniss.disable();
                	intitule.enable();
                	but_sauv.setVisible(true);
                	but_modif.setVisible(false);
                	but_sup.setVisible(false);
                	retour.setVisible(false);
                	valid_supp.setVisible(false);
                	valid_ajou.setVisible(false);
                	valid_modif.setVisible(false);
                 intitule.setText("");
                 fourniss_comb.setSelectedIndex(0);
               	fourniss.setText(forn.afficher_code_fourniss());
             }}
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private JPanel panel;
private String msg="";
private boolean desc;
private boolean exist;
private boolean comb_intitule;

private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

private JPanel pan_form=new JPanel();
private JPanel pan_combo=new JPanel();
private JPanel pan_lab_fourniss=new JPanel();
private JPanel pan_combo_fourniss=new JPanel();
private JPanel pan_fourniss=new JPanel();
private JPanel pan_lab_cfourniss=new JPanel();
private JPanel pan_jtext_cfourniss=new JPanel();
private	JPanel pan_intitule=new JPanel();
private	JPanel pan_lab_intitule	=new JPanel();
private	JPanel pan_jtext_intitule=new JPanel();
private	JPanel pan_button=new JPanel();
	
private JButton but_sauv=new JButton("Ajouter");
private JButton but_modif=new JButton("Modifier");
private JButton but_sup=new JButton("Supprimer");
	
private JButton valid_ajou=new JButton("Valider");
private JButton valid_modif=new JButton("Valider");
private JButton valid_supp=new JButton("Valider");
private JButton retour=new JButton("Retour");
	
private JLabel fourniss_lab=new JLabel("Code fournisseur");
private JLabel lab_combo=new JLabel("Liste de fournisseur");
private	JTextField fourniss=new JTextField();
private JLabel intitule_lab=new JLabel("Intitule");
private JTextField intitule=new JTextField();
private jcombo fourniss_comb;

private static gestion_fournisseur forn=new gestion_fournisseur();
private static ArrayList<String> list_fourniss_tr=new ArrayList<String>();

private static List<String> list_f;

fournisseur(final String log){ 
	
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
	
	list_f= new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un fournisseur-----"}));
	fourniss_comb = new jcombo(list_f.toArray());
	list_fourniss_tr=forn.select_fournisseur_code();
	   for(int i=0;i<list_fourniss_tr.size();i++)
	   {    //Pour affecter une valeur de base de donn?es ? un Combobox 
		  fourniss_comb.addItem(list_fourniss_tr.get(i)+" "+list_fourniss_tr.get(i+1));
		   i++;
	   }
	   fourniss_comb.setWide(true);
   Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
   if(!selectioncomb.prv.contains("fournisseur"))
   selectioncomb.prv.add("fournisseur");
but_sauv.setVisible(true);
but_modif.setVisible(false);
but_sup.setVisible(false);
retour.setVisible(false);
valid_supp.setVisible(false);
valid_ajou.setVisible(false);
valid_modif.setVisible(false);

   panel= new JPanel(){   
  		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
             img.paintIcon(this, g,0, 0);

  	}  };
  	
  	panel.registerKeyboardAction(this, CTRL_J, KeyStroke.getKeyStroke(
            KeyEvent.VK_J, Event.CTRL_MASK),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
  	
  	fourniss.disable();
  	intitule.enable();

	LineBorder border = new LineBorder ( Color.white, 1, true );
	TitledBorder titl2 = new TitledBorder ( border, "Edition Fournisseur", TitledBorder.DEFAULT_POSITION,
    TitledBorder.DEFAULT_POSITION, police2, Color.white);
    pan_form.setBorder(titl2);
  	fourniss.setText(forn.afficher_code_fourniss());

	retour.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            LineBorder border = new LineBorder ( Color.white, 1, true );
          	TitledBorder titl2 = new TitledBorder ( border, "Edition Fournisseur", TitledBorder.DEFAULT_POSITION,
            TitledBorder.DEFAULT_POSITION, police2, Color.white);
            pan_form.setBorder(titl2);
            fourniss_comb.enable(); 
           	fourniss.disable();
           	intitule.enable();
           	but_sauv.setVisible(true);
           	but_modif.setVisible(false);
           	but_sup.setVisible(false);
           	retour.setVisible(false);
           	valid_supp.setVisible(false);
           	valid_ajou.setVisible(false);
           	valid_modif.setVisible(false);
            intitule.setText("");
            fourniss_comb.setSelectedIndex(0);
          	fourniss.setText(forn.afficher_code_fourniss());
            }});
  	/////////////////////////////////////////////////////////
    
    
    but_sup.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            LineBorder border = new LineBorder ( Color.white, 1, true );
          	TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
            TitledBorder.DEFAULT_POSITION, police2, Color.white);
            pan_form.setBorder(titl2);
            fourniss_comb.enable(); 
           	fourniss.disable();
           	
           	forn.fournisseur(intitule.getText());
            if(forn.ex==false){
             	JOptionPane.showMessageDialog(null, "Ce Fournisseur n'existe pas", "",
             	JOptionPane.INFORMATION_MESSAGE);
             	but_sauv.setVisible(true);
                but_modif.setVisible(false);
                but_sup.setVisible(false);
                retour.setVisible(false);
                valid_supp.setVisible(false);
                valid_modif.setVisible(false);
                valid_ajou.setVisible(false);
           	    fourniss_comb.setSelectedIndex(0);
           	    fourniss.setText(forn.afficher_code_fourniss());
           	
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
            	forn.fournisseur(intitule.getText());
                if(forn.ex==false){msg+="Ce Fournisseur n'existe pas \n";}
                else if(fourniss_comb.getSelectedIndex()==0){
	      				msg+="Vous Devez D'abord Selectionner un Fournisseur \n";
          }
            	if(!msg.equals("")){
	            		
	  	            	JOptionPane.showMessageDialog(null,msg);                                                                            
	  	            	}
            	else{
  	forn.delete_fournisseur(fourniss.getText(),intitule.getText());
    intitule.setText("");
  	fourniss_comb.removeAllItems();
  	fourniss_comb.addItem("---Selectionner un fournisseur-----");
  	list_fourniss_tr=forn.select_fournisseur_code();
	   for(int i=0;i<list_fourniss_tr.size();i++)
	   {
	          //Pour affecter une valeur de base de donn?es ? un Combobox 
		   fourniss_comb.addItem(list_fourniss_tr.get(i)+" "+list_fourniss_tr.get(i+1));
		   i++;
	   }
     	//fourniss.setText(forn.afficher_code_fourniss());
            	}
            }});
  	
  	 
	but_modif.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
             LineBorder border = new LineBorder ( Color.white, 1, true );
          	 TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
             TitledBorder.DEFAULT_POSITION, police2, Color.white);
             pan_form.setBorder(titl2);
             fourniss_comb.enable(); 
            // forn.fournisseur(intitule.getText());
//             if(forn.ex==false){
//              	JOptionPane.showMessageDialog(null, "Ce Fournisseur n'exist pas", "",
//              	JOptionPane.INFORMATION_MESSAGE);
//              	but_sauv.setVisible(true);
//                 but_modif.setVisible(false);
//                 but_sup.setVisible(false);
//                 retour.setVisible(false);
//                 valid_supp.setVisible(false);
//                 valid_modif.setVisible(false);
//                 valid_ajou.setVisible(false);
//            	 fourniss_comb.setSelectedIndex(0);
//            	 fourniss.setText(forn.afficher_code_fourniss());
//            	
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
	            	   msg="";
	            	   desc=false;
	            	   if(fourniss_comb.getSelectedIndex()==0){
	                	
	        	      				msg+="Vous Devez D'abord Sélectionner un fournisseur \n";
	        	  		       
	                   }
	            	   else if(intitule.getText().equals("")) {	desc=true;
	                          msg+="Veuillez remplir l'intitule \n";
 
	            	 	}
	            	 	
	            	 	
	            	 	
	            	 	if(!msg.equals("")){
  		            		
 		  	            	JOptionPane.showMessageDialog(null,msg);                                                                            
 		  	            	}
	            	 	else{
	    	            	 forn.setupdate_fourniss(fourniss.getText(), intitule.getText());
	    	            	 JOptionPane.showMessageDialog(null,"Le fournisseur a été bien modifier");
	   	    	             intitule.setText("");
	    	            		 fourniss_comb.removeAllItems();
	    	            	  	 fourniss_comb.addItem("---Selectionner un fournisseur-----");
	    	            	  	list_fourniss_tr=forn.select_fournisseur_code();
	    	            		   for(int i=0;i<list_fourniss_tr.size();i++)
	    	            		   {
	    	            		          //Pour affecter une valeur de base de donn?es ? un Combobox 
	    	            			   fourniss_comb.addItem(list_fourniss_tr.get(i)+" "+list_fourniss_tr.get(i+1));
	    	            			   i++;
	    	            		   }
	    	            		   
	    	                     	fourniss.setText(forn.afficher_code_fourniss());
	    	            	 	}	            	 	
			  	    	}});
	   
	
  
  	 but_sauv.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
              LineBorder border = new LineBorder ( Color.white, 1, true );
           	  TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
              TitledBorder.DEFAULT_POSITION, police2, Color.white);
              pan_form.setBorder(titl2);
              fourniss_comb.disable();
              forn.fournisseur(intitule.getText());

              if(forn.ex==true){
              	 JOptionPane.showMessageDialog(null, "Ce fournisseur existe déja", "",
              	 JOptionPane.INFORMATION_MESSAGE);
              	   but_sauv.setVisible(false);
                   but_modif.setVisible(true);
                   but_sup.setVisible(true);
                   retour.setVisible(false);
                   valid_supp.setVisible(false);
                   valid_modif.setVisible(false);
                   valid_ajou.setVisible(false);
                   forn.fournisseur(intitule.getText());
                   fourniss.setText(forn.code);
                   fourniss_comb.setSelectedItem(forn.code+" "+forn.nom); 
                   intitule.setText(forn.nom);
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
               forn.fournisseur(intitule.getText());

        	 	if(intitule.getText().equals("")) {	desc=true;
                      msg+="Veuillez remplir l'intitule \n";}
        	 	else if(fourniss.getText().equals("")) {	desc=true;
                msg+="Veuillez remplir le code fournisseur \n";}
        	 	else if (forn.ex==true) {msg+="Ce Fournisseur existe déja \n";}
        	 	else{
        	 		
        	  exist= forn.ajouter_fourniss(fourniss.getText(), intitule.getText());
        	   fourniss_comb.removeAllItems();
        	  	 fourniss_comb.addItem("---Selectionner un fournisseur-----");
        	  	list_fourniss_tr=forn.select_fournisseur_code();
        		   for(int i=0;i<list_fourniss_tr.size();i++)
        		   {
        		          //Pour affecter une valeur de base de donn?es ? un Combobox 
        			   fourniss_comb.addItem(list_fourniss_tr.get(i)+" "+list_fourniss_tr.get(i+1));
        			   i++;
        		   }
                 	fourniss.setText(forn.afficher_code_fourniss());

        	 	
        	 	}
        	 	
        	 	if(exist==false&&desc==false&&forn.ex==false){
        	 		JOptionPane.showMessageDialog(null,"Le fournisseur a été bien ajouter");
        	 		 fourniss_comb.enable();
                     intitule.setText("");
        	 	}
        	 	else{
        	JOptionPane.showMessageDialog(null,msg);
        }
        	 			  	 
        	 	
	    		}});
   
   
  
   
          fourniss_comb.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
        	   LineBorder border = new LineBorder ( Color.white, 1, true );
               TitledBorder titl2 = new TitledBorder ( border, "Edition Fournisseur", TitledBorder.DEFAULT_POSITION,
               TitledBorder.DEFAULT_POSITION, police2, Color.white);
               pan_form.setBorder(titl2);
               if(fourniss_comb.getSelectedIndex()==0){
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
                        intitule.setText("");
                    fourniss.setText(forn.afficher_code_fourniss());
           		     
           		   
           	   }
           	   else {      
           		 if(fourniss_comb.getItemCount()>1){
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
            		  String[] parts = fourniss_comb.getSelectedItem().toString().split(" ");
            		  String part1 = parts[0]; // 004
            		  
            		  String part2 = fourniss_comb.getSelectedItem().toString().replace(part1+" ",""); 
            		 fourniss.setText(part1);
            		 intitule.setText(part2);
           		 }
        	   }
        	  // }
           }});
   

          intitule.addActionListener(
                  new ActionListener() { 
                  public void actionPerformed(ActionEvent e) {
                  fourniss_comb.enable();
                  LineBorder border = new LineBorder ( Color.white, 1, true );
                  TitledBorder titl2 = new TitledBorder ( border, "Edition Poste", TitledBorder.DEFAULT_POSITION,
                  TitledBorder.DEFAULT_POSITION, police2, Color.white);
                  pan_form.setBorder(titl2);
                  forn.fournisseur(intitule.getText());
                  if(intitule.getText().equals("")||forn.ex==false){
               	   if(valid_modif.isVisible()==false||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
               	       but_sauv.setVisible(true);
               	       valid_ajou.setVisible(false);
                       valid_supp.setVisible(false);
                       valid_modif.setVisible(false);
                       retour.setVisible(false);
                       comb_intitule=true;

               	     }
               	  but_modif.setVisible(false);
                  but_sup.setVisible(false);
                  fourniss.setText(forn.afficher_code_fourniss());
                  fourniss_comb.setSelectedIndex(0);
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
               	  
                 fourniss.setText(forn.code);
                 intitule.setText(forn.nom);
                 fourniss_comb.setSelectedItem(forn.code+" "+forn.nom); 
               	   }
               	  // }
                  }});

     fourniss.setPreferredSize(new Dimension(250, 30));
     intitule.setPreferredSize(new Dimension(250,30));
     panel.add(pan_form);
     pan_form.add(pan_combo);
     pan_form.add(pan_fourniss);
     pan_form.add(pan_intitule);
     pan_form.add(pan_button);
     pan_form.setOpaque(false);
  	pan_combo.add(pan_lab_fourniss);
  	pan_combo.add(pan_combo_fourniss);

  	
  	pan_lab_fourniss.add(lab_combo);
  	pan_combo_fourniss.add(fourniss_comb);
  	
  	pan_fourniss.add(pan_lab_cfourniss);
  	pan_fourniss.add(pan_jtext_cfourniss);

  	pan_lab_cfourniss.add(fourniss_lab);
  	pan_jtext_cfourniss.add(fourniss);
	
	pan_intitule.add(pan_lab_intitule);
	pan_intitule.add(pan_jtext_intitule);
  	
  	pan_lab_intitule.add(intitule_lab);
  	pan_jtext_intitule.add(intitule);

  	pan_button.add(but_sauv);
	pan_button.add(but_modif);	
	pan_button.add(but_sup);
	pan_button.add(retour);
	pan_button.add(valid_modif);	
	pan_button.add(valid_supp);
	pan_button.add(valid_ajou);

	
  	pan_button.setOpaque(false);
  	pan_fourniss.setOpaque(false);
  	pan_intitule.setOpaque(false);
  	pan_lab_fourniss.setOpaque(false);
  	pan_combo_fourniss.setOpaque(false);
  	pan_lab_cfourniss.setOpaque(false);
  	pan_jtext_cfourniss.setOpaque(false);
  	pan_lab_intitule.setOpaque(false);
  	pan_jtext_intitule.setOpaque(false);
  	pan_combo.setOpaque(false);
  	
  	fourniss_comb.setFont(police_fi);
  	fourniss.setFont(police_fi);
  	intitule.setFont(police_fi);

  	fourniss_lab.setFont(police2);
  	intitule_lab.setFont(police2);
  	lab_combo.setFont(police2);
  	fourniss_lab.setForeground(new Color(255,255,255)); 
  	intitule_lab.setForeground(new Color(255,255,255)); 
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
     fourniss_comb.setPreferredSize(new Dimension(250, 33));

    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    pan_button.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
 
    pan_lab_cfourniss.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 0));
    pan_jtext_cfourniss.setBorder(BorderFactory.createEmptyBorder(0, -8, 20, 0));

    pan_lab_intitule.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
    pan_lab_fourniss.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

    pan_combo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 30));
    pan_intitule.setBorder(BorderFactory.createEmptyBorder(20, 95, 20, 15));

    pan_lab_fourniss.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_combo_fourniss.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
    
    pan_lab_cfourniss.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_jtext_cfourniss.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
    pan_lab_intitule.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_jtext_intitule.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
  	pan_button.setLayout( new FlowLayout( FlowLayout.CENTER ));

  	pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
  	
  	panel.setLayout(new GridLayout(1, 1));  	
  	pan_combo.setLayout(new BoxLayout(pan_combo,BoxLayout.X_AXIS));
  	pan_fourniss.setLayout(new BoxLayout(pan_fourniss,BoxLayout.X_AXIS));
  	pan_intitule.setLayout(new BoxLayout(pan_intitule,BoxLayout.X_AXIS));

    setTitle("Gestion des Fournisseurs" );
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
				 new fournisseur("7");
				//frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}








