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


public class tache extends JFrame implements ActionListener{
   final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
    	 
     
            // gestion de l'action
           
             if (e.getActionCommand().equals(CTRL_J)){
            	 LineBorder border = new LineBorder ( Color.white, 1, true );
               	TitledBorder titl2 = new TitledBorder ( border, "Edition Tache", TitledBorder.DEFAULT_POSITION,
                 TitledBorder.DEFAULT_POSITION, police2, Color.white);
                 pan_form.setBorder(titl2);
                 tache_comb.enable(); 
                	tache.disable();
                	intitule.enable();
                	but_sauv.setVisible(true);
                	but_modif.setVisible(false);
                	but_sup.setVisible(false);
                	retour.setVisible(false);
                	valid_supp.setVisible(false);
                	valid_ajou.setVisible(false);
                	valid_modif.setVisible(false);
                 intitule.setText("");
                 tache_comb.setSelectedIndex(0);
                 tache.setText(tach.afficher_code_tache());
            }
            
        
    }
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private String msg="";
private boolean desc;
private boolean exist;
private boolean comb_intitule;

private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

private JPanel panel;
private JPanel pan_combo=new JPanel();
private JPanel pan_lab_tache=new JPanel();
private JPanel pan_combo_tache=new JPanel();
private JPanel pan_form=new JPanel();
private JPanel pan_tache=new JPanel();
private JPanel pan_lab_ctache=new JPanel();
private JPanel pan_jtext_ctache=new JPanel();	
private JPanel pan_intitule=new JPanel();
private	JPanel pan_lab_intitule=new JPanel();
private JPanel pan_jtext_intitule=new JPanel();	
private JPanel pan_button=new JPanel();
	
private JButton but_sauv=new JButton("Ajouter");
private JButton but_modif=new JButton("Modifier");
private	JButton but_sup=new JButton("Supprimer");
private	JButton valid_ajou=new JButton("Valider");
private	JButton valid_modif=new JButton("Valider");
private JButton valid_supp=new JButton("Valider");
private JButton retour=new JButton("Retour");
	
private JLabel tache_lab=new JLabel("Code Tache");
private JLabel lab_combo=new JLabel("Tache_Intitulé");
private	JTextField tache=new JTextField();
private	JLabel intitule_lab=new JLabel("Intitulé");
private	JTextField intitule=new JTextField();
protected jcombo tache_comb;
private static gestion_tache tach=new gestion_tache();
private static List<Object> list_t;
private static ArrayList<String> list_tache_tr=new ArrayList<String>();
private static final String    CTRL_J                = "CTRL+J";
tache(final String log){ 
	
	
  
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


list_t = new ArrayList<Object>(Arrays.asList(new String[]{"---Sélectionner une Tache-----"}));
tache_comb = new jcombo(list_t.toArray());
 selectioncomb.selecttache(tache_comb ,this,log);	      
 tache_comb.addActionListener(
         new ActionListener() { 
         public void actionPerformed(ActionEvent e) {
      	     setFocusable(true);
      	     setFocusTraversalKeysEnabled(false);
         LineBorder border = new LineBorder ( Color.white, 1, true );
         TitledBorder titl2 = new TitledBorder ( border, "Edition Tache", TitledBorder.DEFAULT_POSITION,
         TitledBorder.DEFAULT_POSITION, police2, Color.white);
         pan_form.setBorder(titl2);
         if(tache_comb.getSelectedIndex()==0){
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
            tache.setText(tach.afficher_code_tache());
         	   }
         else {      
         if(tache_comb.getItemCount()>1){
      	 
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
         String[] parts = tache_comb.getSelectedItem().toString().split(" ");
         String part1 = parts[0]; // 004
         
         String part2 = tache_comb.getSelectedItem().toString().replace(part1+" ", ""); 
         tache.setText(part1);
         intitule.setText(part2);
         		 }
      	   }
         }});
 
 selectioncomb.prv.add("tache");
 composant();
 
}

@SuppressWarnings("deprecation")
public void composant( ){
      
	
	
     
   	
   Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
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

  	tache.disable();
  	intitule.enable();

	  LineBorder border = new LineBorder ( Color.white, 1, true );
	  TitledBorder titl2 = new TitledBorder ( border, "Edition Tache", TitledBorder.DEFAULT_POSITION,
      TitledBorder.DEFAULT_POSITION, police2, Color.white);
      pan_form.setBorder(titl2);
      
	retour.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            LineBorder border = new LineBorder ( Color.white, 1, true );
          	TitledBorder titl2 = new TitledBorder ( border, "Edition Tache", TitledBorder.DEFAULT_POSITION,
            TitledBorder.DEFAULT_POSITION, police2, Color.white);
            pan_form.setBorder(titl2);
            tache.setText(tach.afficher_code_tache());
            tache_comb.enable(); 
           	tache.disable();
           	intitule.enable();
           	but_sauv.setVisible(true);
           	but_modif.setVisible(false);
           	but_sup.setVisible(false);
           	retour.setVisible(false);
           	valid_supp.setVisible(false);
           	valid_ajou.setVisible(false);
           	valid_modif.setVisible(false);
            intitule.setText("");
            tache_comb.setSelectedIndex(0);
            }});
  	/////////////////////////////////////////////////////////
    tache.setText(tach.afficher_code_tache());
    
    but_sup.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            LineBorder border = new LineBorder ( Color.white, 1, true );
          	TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
            TitledBorder.DEFAULT_POSITION, police2, Color.white);
            pan_form.setBorder(titl2);
            tache_comb.enable(); 
           	tache.disable();
            tach.tache(intitule.getText());
            if(tach.ex==false){
             	JOptionPane.showMessageDialog(null, "Cette Tache n'existe pas", "",
             	JOptionPane.INFORMATION_MESSAGE);
             	but_sauv.setVisible(true);
                but_modif.setVisible(false);
                but_sup.setVisible(false);
                retour.setVisible(false);
                valid_supp.setVisible(false);
                valid_modif.setVisible(false);
                valid_ajou.setVisible(false);
           	     tache_comb.setSelectedIndex(0);
           	     tache.setText(tach.afficher_code_tache());
           	
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
          tach.tache(intitule.getText());
               
           if(tache_comb.getSelectedIndex()==0){
	      				msg+="Vous Devez D'abord Sélectionner un Tache \n";
          }
           else if(tach.ex==false){msg+="Cette Tache n'existe pas \n";}
           
           if(!msg.equals("")){
	          JOptionPane.showMessageDialog(null,msg);                                                                            
	  	            	    }
           else{
  	       tach.delete_tache(tache.getText(),intitule.getText());
  	       intitule.setText("");
  	       tache_comb.removeAllItems();
  	       tache_comb.addItem("---Sélectionner une Tache-----");
  	     list_tache_tr=tach.select_tache_code();
	   for(int i=0;i<list_tache_tr.size();i++)
	   {
		   tache_comb.addItem(list_tache_tr.get(i)+" "+list_tache_tr.get(i+1));
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
             tache_comb.enable(); 
             tach.tache(intitule.getText());
//             if(pos.ex==false){
//              	JOptionPane.showMessageDialog(null, "Ce Poste n'exist pas", "",
//              	JOptionPane.INFORMATION_MESSAGE);
//              	but_sauv.setVisible(true);
//                 but_modif.setVisible(false);
//                 but_sup.setVisible(false);
//                 retour.setVisible(false);
//                 valid_supp.setVisible(false);
//                 valid_modif.setVisible(false);
//                 valid_ajou.setVisible(false);
//            	 poste_comb.setSelectedIndex(0);
//            	 poste.setText(pos.afficher_code_poste());
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
            //  }
            
            }});
	
  	valid_modif.addActionListener(
	            new ActionListener() { 
	            public void actionPerformed(ActionEvent e) {
	            msg="";
	            desc=false;
	            if(tache_comb.getSelectedIndex()==0){
	                msg+="Vous Devez D'abord Sélectionner une Tache \n";
	        	  	                                    }
	            else if(intitule.getText().equals("")) {	
	                msg+="Veuillez remplir l'intitulé \n";
	            	 	}
	            if(!msg.equals("")){
 		  	           JOptionPane.showMessageDialog(null,msg);                                                                            
 		  	            	}
	            else{
	    	          tach.setupdate_tache(tache.getText(), intitule.getText());
	    	          JOptionPane.showMessageDialog(null,"La Tache a été bien modifié");
	    	          intitule.setText("");
	    	          tache_comb.removeAllItems();
	    	          tache_comb.addItem("---Sélectionner une Tache-----");
	    	          list_tache_tr=tach.select_tache_code();
	    	          for(int i=0;i<list_tache_tr.size();i++){
	    	        	  tache_comb.addItem(list_tache_tr.get(i)+" "+list_tache_tr.get(i+1));
	    	            i++;
	    	            		   }
	    	            	 	}
	            	 }});
	   
	
  	 but_sauv.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
              LineBorder border = new LineBorder ( Color.white, 1, true );
           	  TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
              TitledBorder.DEFAULT_POSITION, police2, Color.white);
              pan_form.setBorder(titl2);
              tache_comb.disable();
              tach.tache(intitule.getText());

              if(tach.ex==true){
              	 JOptionPane.showMessageDialog(null, "Cette Tache existe déjà", "",
              	 JOptionPane.INFORMATION_MESSAGE);
              	   but_sauv.setVisible(false);
                   but_modif.setVisible(true);
                   but_sup.setVisible(true);
                   retour.setVisible(false);
                   valid_supp.setVisible(false);
                   valid_modif.setVisible(false);
                   valid_ajou.setVisible(false);
                   tach.tache(intitule.getText());
                   tache.setText(tach.code);
                   //System.out.println()
                   tache_comb.setSelectedItem(tach.code+" "+tach.intitul); 
                   intitule.setText(tach.intitul);
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
        	   tach.tache(intitule.getText());
        	msg="";
        	desc=false;
        	if(intitule.getText().equals("")) {	desc=true;
                   msg+="Veuillez remplir l'intitulé \n";}
        	else if (tach.ex==true) {msg+="Cette Tache existe déjà \n";}
        	else{
        	 exist= tach.ajouter_tache(tache.getText(), intitule.getText());
        	 tache_comb.removeAllItems();
        	 tache_comb.addItem("---Sélectionner une Tache-----");
        	 list_tache_tr=tach.select_tache_code();
             for(int i=0;i<list_tache_tr.size();i++){
            	 tache_comb.addItem(list_tache_tr.get(i)+" "+list_tache_tr.get(i+1));
             i++;
        		   }
        	 }
        	 	
           if(exist==false&&desc==false&&tach.ex==false){
        	   JOptionPane.showMessageDialog(null,"La Tache a été bien ajouté");
        	   tache_comb.enable();
               intitule.setText("");
           }
           else JOptionPane.showMessageDialog(null,msg);
         
	    		}});
   
   
   
   
   
   
   
   intitule.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
        	     setFocusable(true);
        	     setFocusTraversalKeysEnabled(false);
        	     tache_comb.enable();
           LineBorder border = new LineBorder ( Color.white, 1, true );
           TitledBorder titl2 = new TitledBorder ( border, "Edition Poste", TitledBorder.DEFAULT_POSITION,
           TitledBorder.DEFAULT_POSITION, police2, Color.white);
           pan_form.setBorder(titl2);
           tach.tache(intitule.getText());
           if(intitule.getText().equals("")||tach.ex==false){
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
               
               tache.setText(tach.afficher_code_tache());
               tache_comb.setSelectedIndex(0);
          // if(poste_comb.getSelectedIndex()!=0){ comb_intitule=false;}
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
        	  
        	  tache.setText(tach.code);
          intitule.setText(tach.intitul);
          tache_comb.setSelectedItem(tach.code+" "+tach.intitul); 
        	   }
        	  // }
           }});

   
    tache.setPreferredSize(new Dimension(250, 30));
     intitule.setPreferredSize(new Dimension(250,30));
     panel.add(pan_form);
     pan_form.add(pan_combo);
     pan_form.add(pan_tache);
     pan_form.add(pan_intitule);
     pan_form.add(pan_button);
     pan_form.setOpaque(false);
  	 pan_combo.add(pan_lab_tache);
  	 pan_combo.add(pan_combo_tache);	
  	 pan_lab_tache.add(lab_combo);
  	 pan_combo_tache.add(tache_comb);
  	 pan_tache.add(pan_lab_ctache);
  	 pan_tache.add(pan_jtext_ctache);
  	 pan_lab_ctache.add(tache_lab);
  	 pan_jtext_ctache.add(tache);
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
  	 pan_tache.setOpaque(false);
  	 pan_intitule.setOpaque(false);
  	 pan_lab_tache.setOpaque(false);
  	 pan_combo_tache.setOpaque(false);
  	 pan_lab_ctache.setOpaque(false);
  	 pan_jtext_ctache.setOpaque(false);
  	 pan_lab_intitule.setOpaque(false);
  	 pan_jtext_intitule.setOpaque(false);
  	 pan_combo.setOpaque(false);
  	tache_comb.setFont(police_fi);
  	tache.setFont(police_fi);
  	 intitule.setFont(police_fi);
  	tache_lab.setFont(police2);
  	 intitule_lab.setFont(police2);
  	 lab_combo.setFont(police2);
  	tache_lab.setForeground(new Color(255,255,255)); 
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
     tache_comb.setPreferredSize(new Dimension(250, 33));
     panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
     pan_button.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
     pan_lab_ctache.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
     pan_lab_intitule.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
     pan_lab_tache.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
     pan_combo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 30));
     pan_intitule.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 30));
     pan_lab_tache.setLayout( new FlowLayout( FlowLayout.CENTER ));
     pan_combo_tache.setLayout(new FlowLayout(FlowLayout.LEFT));
     pan_lab_ctache.setLayout( new FlowLayout( FlowLayout.CENTER ));
     pan_jtext_ctache.setLayout(new FlowLayout(FlowLayout.LEFT));
     pan_lab_intitule.setLayout( new FlowLayout( FlowLayout.CENTER ));
     pan_jtext_intitule.setLayout(new FlowLayout(FlowLayout.LEFT));
  	 pan_button.setLayout( new FlowLayout( FlowLayout.CENTER ));
  	 pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
     panel.setLayout(new GridLayout(1, 1));  	
  	 pan_combo.setLayout(new BoxLayout(pan_combo,BoxLayout.X_AXIS));
  	 pan_tache.setLayout(new BoxLayout(pan_tache,BoxLayout.X_AXIS));
  	 pan_intitule.setLayout(new BoxLayout(pan_intitule,BoxLayout.X_AXIS));
     setTitle("Gestion des Taches" );
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
		 new tache("7");
				//frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}








