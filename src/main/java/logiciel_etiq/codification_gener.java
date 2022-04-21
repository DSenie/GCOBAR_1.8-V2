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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;


@SuppressWarnings("serial")
public class codification_gener extends JFrame implements ActionListener{
	private static final String    CTRL_J                = "CTRL+J";
	   final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

 @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
 	 
  
         // gestion de l'action
        
          if (e.getActionCommand().equals(CTRL_J)){
        	  LineBorder border = new LineBorder ( Color.white, 1, true );
          	  TitledBorder titl2 = new TitledBorder ( border, "Edition Famille", TitledBorder.DEFAULT_POSITION,
              TitledBorder.DEFAULT_POSITION, police2, Color.white);
              pan_form.setBorder(titl2);
            famille_comb.enable(); 
           	famille.enable();
           	designation.enable();
           	but_sauv.setVisible(true);
           	but_modif.setVisible(false);
           	but_sup.setVisible(false);
           	retour.setVisible(false);
           	valid_supp.setVisible(false);
           	valid_ajou.setVisible(false);
           	valid_modif.setVisible(false);
          designation.setText("");
          famille.setText("");
         famille_comb.setSelectedIndex(0);
          }}
	private JPanel panel;
	private String msg="";
	private boolean desc;

	private boolean existe;
	private boolean comb_intitule;

	//private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private JPanel pan_form=new JPanel();
   
	private JPanel pan_combo=new JPanel();
	private JPanel pan_lab_famille=new JPanel();
	private JPanel pan_combo_famille=new JPanel();
	private JPanel pan_famille=new JPanel();
	private JPanel pan_lab_cfamille=new JPanel();
	private JPanel pan_jtext_cfamille=new JPanel();
	private JPanel pan_designation=new JPanel();
	private JPanel pan_lab_designation=new JPanel();
	private JPanel pan_jtext_designation=new JPanel();
	private JPanel pan_button=new JPanel();
	private JButton but_sauv=new JButton("Ajouter");
	private JButton but_modif=new JButton("Modifier");
	private JButton but_sup=new JButton("Supprimer");
	
	private JButton valid_ajou=new JButton("Valider");
	private JButton valid_modif=new JButton("Valider");
	private JButton valid_supp=new JButton("Valider");
	private JButton retour=new JButton("Retour");
	private JLabel famille_lab=new JLabel("Code famille");
	private JLabel lab_combo=new JLabel("Liste de famille");
	private JTextField famille;
	private JLabel designation_lab=new JLabel("Designation");
	private JTextField designation=new JTextField();
	private  jcombo famille_comb;
	private static List<String> list_f;
	private static ArrayList<String> list_famille_tr=new ArrayList<String>(); 
	
	private  static gestion_codegener famil=new gestion_codegener();
   codification_gener(final String log){ 
	
	Toolkit kit = Toolkit.getDefaultToolkit();
    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
   setIconImage(img);
   selectioncomb.windows(this,log);

	   generale.styles("Nimbus");
	   SwingUtilities.updateComponentTreeUI(this);

    composant();
}

@SuppressWarnings("deprecation")
public void composant(){
	list_f= new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner une famille-----"}));
	famille_comb = new jcombo(list_f.toArray());
	// famille_comb.addItem("---Selectionner une famille-----");
	list_famille_tr=famil.select_famille_code();
	
	   for(int i=0;i<list_famille_tr.size();i++)
	   {
	          //Pour affecter une valeur de base de donn�es � un Combobox
		  famille_comb.addItem(list_famille_tr.get(i)+" "+list_famille_tr.get(i+1));
		   i++;
	   }
	   famille_comb.setWide(true);
	   if(!selectioncomb.prv.contains("codegener"))
	   selectioncomb.prv.add("codegener");
	Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
   final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);
but_sauv.setVisible(true);
but_modif.setVisible(false);
but_sup.setVisible(false);
retour.setVisible(false);
valid_supp.setVisible(false);
valid_ajou.setVisible(false);
valid_modif.setVisible(false);

	  panel= new JPanel(){   
  		public void paintComponent(Graphics g){
  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
             img.paintIcon(this, g,0, 0);
  	}};
  	
  	panel.registerKeyboardAction(this, CTRL_J, KeyStroke.getKeyStroke(
            KeyEvent.VK_J, Event.CTRL_MASK),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
  	 MaskFormatter num = null;
     try {
          num = new MaskFormatter("***");
         } 
     catch (ParseException e) {
                               e.printStackTrace();
                              }
    famille= new  JFormattedTextField(num);
    famille.setText("");
  	famille.enable();
  	designation.enable();

	LineBorder border = new LineBorder ( Color.white, 1, true );
	TitledBorder titl2 = new TitledBorder ( border, "Edition Famille", TitledBorder.DEFAULT_POSITION,
    TitledBorder.DEFAULT_POSITION, police2, Color.white);
    pan_form.setBorder(titl2);
      
	retour.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
              LineBorder border = new LineBorder ( Color.white, 1, true );
          	  TitledBorder titl2 = new TitledBorder ( border, "Edition Famille", TitledBorder.DEFAULT_POSITION,
              TitledBorder.DEFAULT_POSITION, police2, Color.white);
              pan_form.setBorder(titl2);
            famille_comb.enable(); 
           	famille.enable();
           	designation.enable();
           	but_sauv.setVisible(true);
           	but_modif.setVisible(false);
           	but_sup.setVisible(false);
           	retour.setVisible(false);
           	valid_supp.setVisible(false);
           	valid_ajou.setVisible(false);
           	valid_modif.setVisible(false);
          designation.setText("");
          famille.setText("");
         famille_comb.setSelectedIndex(0);

            }});
  	/////////////////////////////////////////////////////////
    
    
    but_sup.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	LineBorder border = new LineBorder ( Color.white, 1, true );
          	    TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
                TitledBorder.DEFAULT_POSITION, police2, Color.white);
                pan_form.setBorder(titl2);
                famille_comb.enable(); 
                if(existe==false){
                 	JOptionPane.showMessageDialog(null, "Cette famille n'existe pas", "",
                 	JOptionPane.INFORMATION_MESSAGE);
                 	but_sauv.setVisible(true);
                    but_modif.setVisible(false);
                    but_sup.setVisible(false);
                    retour.setVisible(false);
                    valid_supp.setVisible(false);
                    valid_modif.setVisible(false);
                    valid_ajou.setVisible(false);
               	    famille_comb.setSelectedIndex(0);               	
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
            	 //famil.famille(designation.getText(),famille.getText());
            	   if(famille_comb.getSelectedIndex()==0){
	      				msg+="Vous Devez D'abord Selectionner une famille \n";
                      }
                   else if(existe==false){msg+="Cette famille n'existe pas \n";}

            	if(!msg.equals("")){
	            		
	  	            	JOptionPane.showMessageDialog(null,msg);                                                                            
	  	            	}
            	else{
  	 famil.delete_famille(famille.getText(),designation.getText());
  	 designation.setText("");
  	 famille.setText("");

  	 famille_comb.removeAllItems();
  	 famille_comb.addItem("---Selectionner une famille-----");
  	list_famille_tr=famil.select_famille_code();
	   for(int i=0;i<list_famille_tr.size();i++)
	   {
	          //Pour affecter une valeur de base de donn�es � un Combobox
		   famille_comb.addItem(list_famille_tr.get(i)+" "+list_famille_tr.get(i+1));
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
              famille_comb.enable(); 
//              if(famil.ex==false){
//               	JOptionPane.showMessageDialog(null, "Cette famille n'existe pas", "",
//               	JOptionPane.INFORMATION_MESSAGE);
//               	but_sauv.setVisible(true);
//                  but_modif.setVisible(false);
//                  but_sup.setVisible(false);
//                  retour.setVisible(false);
//                  valid_supp.setVisible(false);
//                  valid_modif.setVisible(false);
//                  valid_ajou.setVisible(false);
//             	   famille_comb.setSelectedIndex(0);               	
//               }
//               else{
              	 but_sauv.setVisible(false);
                   but_modif.setVisible(false);
                   but_sup.setVisible(false);
                   retour.setVisible(true);
                   valid_supp.setVisible(false);
                   valid_modif.setVisible(true);
                   valid_ajou.setVisible(false);
            //   }
           	
            	
            }});
  	valid_modif.addActionListener(
	            new ActionListener() { 
	            public void actionPerformed(ActionEvent e) {
	            	   msg="";
	            	   desc=false;
	            	   if(famille_comb.getSelectedIndex()==0){
	                	
	        	      				msg+="Vous Devez D'abord Sélectionner une famille \n";
	        	  		       
	                   }
	            	   else if(designation.getText().equals("")) {	desc=true;
	                          msg+="Veuillez remplir la designation \n";
 
	            	 	}
	            	 	
	            	 	
	            	 	
	            	 	if(!msg.equals("")){
  		            		
 		  	            	JOptionPane.showMessageDialog(null,msg);                                                                            
 		  	            	}
	            	 	else{
	    	            	famil.setupdate_famille(famille.getText(), designation.getText());
	    	            	 JOptionPane.showMessageDialog(null,"La famille a été bien modifier");
	   	    	          designation.setText("");
		    	          famille.setText("");

	    	            		 famille_comb.removeAllItems();
	    	            	  	 famille_comb.addItem("---Selectionner une famille-----");
	    	            	  	list_famille_tr=famil.select_famille_code();
	    	            		   for(int i=0;i<list_famille_tr.size();i++)
	    	            		   {
	    	            		          //Pour affecter une valeur de base de données é un Combobox
	    	            			   famille_comb.addItem(list_famille_tr.get(i)+" "+list_famille_tr.get(i+1));
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
                famille_comb.disable();
                //famil.famille(designation.getText(),famille.getText());
                
                if(existe==true){
                	 JOptionPane.showMessageDialog(null, "Cette famille existe déja", "",
                	 JOptionPane.INFORMATION_MESSAGE);
                	   but_sauv.setVisible(false);
                     but_modif.setVisible(true);
                     but_sup.setVisible(true);
                     retour.setVisible(false);
                     valid_supp.setVisible(false);
                     valid_modif.setVisible(false);
                     valid_ajou.setVisible(false);
                     famille.setText(famil.code_f);
                     //System.out.println()
                     famille_comb.setSelectedItem(famil.code_f+" "+famil.desc); 
                     designation.setText(famil.desc);
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
              // famil.famille(designation.getText(),famille.getText());

        	   
        	 	if(designation.getText().equals("")) {	desc=true;
                      msg+="Veuillez remplir la designation \n";}
        	 	else if(famille.getText().equals("")) {	desc=true;
                msg+="Veuillez remplir le code famille \n";}
        	 	else if (existe==true) {msg+="Cette famille existe déja \n";}
        	 	else{
        	 		
        	 famil.ajouter_famille(famille.getText(), designation.getText());
        	 famille_comb.removeAllItems();
        	  	 famille_comb.addItem("---Selectionner une famille-----");
        	  	list_famille_tr=famil.select_famille_code();
        		   for(int i=0;i<list_famille_tr.size();i++)
        		   {
        		          //Pour affecter une valeur de base de données é un Combobox
        			   famille_comb.addItem(list_famille_tr.get(i)+" "+list_famille_tr.get(i+1));
        			   i++;
        		   }
        	 	
        	 	}
        	 	 if(desc==false&&existe==false){
              	   JOptionPane.showMessageDialog(null,"La famille a été bien ajouter");
              	  famille_comb.enable();
                     designation.setText("");
                     famille.setText("");

                 }
                 else JOptionPane.showMessageDialog(null,msg);
        
        	 			  	 
        	 	
	    		}});
   
   
  
   
   famille_comb.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
        	   String part1="";
        	   String part2="";
        	
        	   
        	   LineBorder border = new LineBorder ( Color.white, 1, true );
               TitledBorder titl2 = new TitledBorder ( border, "Edition Famille", TitledBorder.DEFAULT_POSITION,
               TitledBorder.DEFAULT_POSITION, police2, Color.white);
               pan_form.setBorder(titl2);
           	   if(famille_comb.getSelectedIndex()==0){
         			 famille.enable();

           		 if(valid_modif.isVisible()==false||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
           	         but_sauv.setVisible(true);
           	         valid_ajou.setVisible(false);
                      valid_supp.setVisible(false);
                      valid_modif.setVisible(false);
                      retour.setVisible(false);
           	     }
                  
           	     but_modif.setVisible(false);
                  but_sup.setVisible(false);
                  if(comb_intitule!=true){
                  designation.setText("");
                  famille.setText("");}

           	   }
           	   else {      
           		 if(famille_comb.getItemCount()>1){
           			 
                	   String[] parts = famille_comb.getSelectedItem().toString().split(" ");
             		   part1 = parts[0]; // 004
             		    part2 = famille_comb.getSelectedItem().toString().replace(part1+" ", "");
             		   
             		 //  part2 = parts[1]; 
        	           existe= famil.famille(part2,part1);
        	          
           			 famille.disable();
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
            		 
            		 famille.setText(part1);
            		 designation.setText(part2);
           		 }
        	   }
        	  // }
           }});
   
   
   action(designation);
   action(famille);

   
     famille.setPreferredSize(new Dimension(250, 30));
     designation.setPreferredSize(new Dimension(250,30));
     panel.add(pan_form);
     pan_form.add(pan_combo);
     pan_form.add(pan_famille);
     pan_form.add(pan_designation);
     pan_form.add(pan_button);
     pan_form.setOpaque(false);
  	pan_combo.add(pan_lab_famille);
  	pan_combo.add(pan_combo_famille);

  	
  	pan_lab_famille.add(lab_combo);
  	pan_combo_famille.add(famille_comb);
  	
  	pan_famille.add(pan_lab_cfamille);
  	pan_famille.add(pan_jtext_cfamille);

  	pan_lab_cfamille.add(famille_lab);
  	pan_jtext_cfamille.add(famille);
	
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
  	pan_famille.setOpaque(false);
  	pan_designation.setOpaque(false);
  	pan_lab_famille.setOpaque(false);
  	pan_combo_famille.setOpaque(false);
  	pan_lab_cfamille.setOpaque(false);
  	pan_jtext_cfamille.setOpaque(false);
  	pan_lab_designation.setOpaque(false);
  	pan_jtext_designation.setOpaque(false);
  	pan_combo.setOpaque(false);
  	
  	famille_comb.setFont(police_fi);
  	famille.setFont(police_fi);
  	designation.setFont(police_fi);

  	famille_lab.setFont(police2);
  	designation_lab.setFont(police2);
  	lab_combo.setFont(police2);
  	famille_lab.setForeground(new Color(255,255,255)); 
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
     famille_comb.setPreferredSize(new Dimension(250, 33));

    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    pan_button.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
 
    pan_lab_cfamille.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 0));
    pan_jtext_cfamille.setBorder(BorderFactory.createEmptyBorder(0, -10, 20, 0));

    pan_lab_designation.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
    pan_lab_famille.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

    pan_combo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 30));
    pan_designation.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 15));

    pan_lab_famille.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_combo_famille.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
    
    pan_lab_cfamille.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_jtext_cfamille.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
    pan_lab_designation.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_jtext_designation.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
  	pan_button.setLayout( new FlowLayout( FlowLayout.CENTER ));

  	pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
  	
  	panel.setLayout(new GridLayout(1, 1));  	
  	pan_combo.setLayout(new BoxLayout(pan_combo,BoxLayout.X_AXIS));
  	pan_famille.setLayout(new BoxLayout(pan_famille,BoxLayout.X_AXIS));
  	pan_designation.setLayout(new BoxLayout(pan_designation,BoxLayout.X_AXIS));

    setTitle("Gestion des Familles" );
    setSize(1000, 600);
    setLocationRelativeTo(null);          
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(panel);
   



}

public void action(JTextField comp){
	comp.addFocusListener(new FocusListener() {
          public void focusLost(FocusEvent e) {
        	  existe= famil.famille(designation.getText(),famille.getText()); 
        	  }
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
			}
        });
	  
	   comp.addActionListener(
	           new ActionListener() { 
	           @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
	           famille_comb.enable();
	           final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);
	           LineBorder border = new LineBorder ( Color.white, 1, true );
	           TitledBorder titl2 = new TitledBorder ( border, "Edition Famille", TitledBorder.DEFAULT_POSITION,
	           TitledBorder.DEFAULT_POSITION, police2, Color.white);
	           pan_form.setBorder(titl2);
	           existe= famil.famille(designation.getText(),famille.getText());  
	          // System.out.println("rrrrrrrrr"+famil.ex);

	           if(designation.getText().equals("")&&famille.getText().equals("")||existe==false){
         			 famille.enable();
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
	               famille_comb.setSelectedIndex(0);
	           	   }
	          else  {   
        	      famille.disable();
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
	        	if(famille.getText().equals("   ")) {	
	        	famille.setText(famil.code_f);
	   	        famille_comb.setSelectedItem(famil.code_f+" "+famil.desc); 
}  
	        	else if(designation.getText().equals("")) {	
	        	designation.setText(famil.desc);
		          famille_comb.setSelectedItem(famil.code_f+" "+famil.desc); }
}
	        	
	           }});} 
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
			 new codification_gener("7");
				//frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}








