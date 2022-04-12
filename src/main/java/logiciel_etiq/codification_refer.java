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


public class codification_refer extends JFrame implements ActionListener{
	private static final String    CTRL_J                = "CTRL+J";
	   final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

 @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
 	 
  
         // gestion de l'action
        
          if (e.getActionCommand().equals(CTRL_J)){
        	  LineBorder border = new LineBorder ( Color.white, 1, true );
           	 TitledBorder titl2 = new TitledBorder ( border, "Edition réfèrence", TitledBorder.DEFAULT_POSITION,
              TitledBorder.DEFAULT_POSITION, police2, Color.white);
              pan_form.setBorder(titl2);
             refer_comb.enable(); 
            	creference.disable();
            	but_sauv.setVisible(true);
            	but_modif.setVisible(false);
            	but_sup.setVisible(false);
            	retour.setVisible(false);
            	valid_supp.setVisible(false);
            	valid_ajou.setVisible(false);
            	valid_modif.setVisible(false);
             designation.setText("");
             refer_comb.setSelectedIndex(0);
     	    creference.setText(ref.afficher_code_reference());
          }}

	private static final long serialVersionUID = 1L;
private JPanel panel;
private String msg="";
private boolean desc;
private boolean existe;
private boolean comb_intitule;

//private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

private JPanel pan_combo=new JPanel();
private JPanel pan_lab_refer=new JPanel();
private JPanel pan_combo_refer=new JPanel();
private JPanel pan_form=new JPanel();
private	JPanel pan_refer=new JPanel();
private	JPanel pan_lab_crefer=new JPanel();
private	JPanel pan_jtext_crefer=new JPanel();
private	JPanel pan_designation=new JPanel();
private	JPanel pan_lab_designation=new JPanel();
private	JPanel pan_jtext_designation=new JPanel();
private JPanel pan_button=new JPanel();
	
private JButton but_sauv=new JButton("Ajouter");
private JButton but_modif=new JButton("Modifier");
private JButton but_sup=new JButton("Supprimer");	
private JButton valid_ajou=new JButton("Valider");
private JButton valid_modif=new JButton("Valider");
private JButton valid_supp=new JButton("Valider");
private JButton retour=new JButton("Retour");
	
private JLabel lab_combo=new JLabel("Liste des réfèrences");
private  jcombo refer_comb;

private JLabel refer_lab=new JLabel("Chiffre de réfèrences");
private JTextField creference=new JTextField();	
private	JLabel designation_lab=new JLabel("Désignation");
private	JTextField designation=new JTextField();
private  static gestion_reference ref=new gestion_reference();
private static List<String> list_ref;
private static ArrayList<String> list_refer_tr=new ArrayList<String>();

codification_refer(final String log){ 
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
	list_ref= new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner une réfèrence-----"}));
	refer_comb = new jcombo(list_ref.toArray());
	list_refer_tr=ref.select_reference_code();
	   for(int i=0;i<list_refer_tr.size();i++)
	   {
	          //Pour affecter une valeur de base de donn�es � un Combobox
		  refer_comb.addItem(list_refer_tr.get(i)+" "+list_refer_tr.get(i+1));
		   i++;
	   }
	   if(!selectioncomb.prv.contains("coderef")){
	   selectioncomb.prv.add("coderef");}
	   refer_comb.setWide(true);
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
  		
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
             img.paintIcon(this, g,0, 0);

  	}  };
  	
  	panel.registerKeyboardAction(this, CTRL_J, KeyStroke.getKeyStroke(
            KeyEvent.VK_J, Event.CTRL_MASK),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
  	creference.disable();
  	designation.enable();

	LineBorder border = new LineBorder ( Color.white, 1, true );
	TitledBorder titl2 = new TitledBorder ( border, "Edition réfèrences", TitledBorder.DEFAULT_POSITION,
    TitledBorder.DEFAULT_POSITION, police2, Color.white);
    pan_form.setBorder(titl2);
      
	 retour.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
             LineBorder border = new LineBorder ( Color.white, 1, true );
          	 TitledBorder titl2 = new TitledBorder ( border, "Edition réfèrences", TitledBorder.DEFAULT_POSITION,
             TitledBorder.DEFAULT_POSITION, police2, Color.white);
             pan_form.setBorder(titl2);
            creference.setText(ref.afficher_code_reference());
            refer_comb.enable(); 
           	creference.disable();
           	but_sauv.setVisible(true);
           	but_modif.setVisible(false);
           	but_sup.setVisible(false);
           	retour.setVisible(false);
           	valid_supp.setVisible(false);
           	valid_ajou.setVisible(false);
           	valid_modif.setVisible(false);
            designation.setText("");
            refer_comb.setSelectedIndex(0);
            }});
	 
  	/////////////////////////////////////////////////////////
    creference.setText(ref.afficher_code_reference());
    
    
    but_sup.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
           LineBorder border = new LineBorder ( Color.white, 1, true );
           TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
           TitledBorder.DEFAULT_POSITION, police2, Color.white);
           pan_form.setBorder(titl2);
           refer_comb.enable(); 
           creference.disable();
           //ref.reference(designation.getText());
    	 //  System.out.println("dddddd"+existe);

           if(existe==false){
            	JOptionPane.showMessageDialog(null, "Cette réfèrence n'existe pas", "",
            	JOptionPane.INFORMATION_MESSAGE);
            	but_sauv.setVisible(true);
               but_modif.setVisible(false);
               but_sup.setVisible(false);
               retour.setVisible(false);
               valid_supp.setVisible(false);
               valid_modif.setVisible(false);
               valid_ajou.setVisible(false);
               refer_comb.setSelectedIndex(0);
               creference.setText(ref.afficher_code_reference());
            }
            else{
           	 but_sauv.setVisible(false);
                but_modif.setVisible(false);
                but_sup.setVisible(false);
                retour.setVisible(true);
                valid_supp.setVisible(true);
                valid_modif.setVisible(false);
                valid_ajou.setVisible(false);
            }  }});
    
  	valid_supp.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	msg="";
               // ref.reference(designation.getText());

            	   if(refer_comb.getSelectedIndex()==0){
	      				msg+="Vous Devez D'abord Selectionner une réfèrence \n";
                      }
            	   else if(existe==false){msg+="Cette réfèrence n'existe pas \n";}
            	if(!msg.equals("")){
	            		
	  	            	JOptionPane.showMessageDialog(null,msg);                                                                            
	  	            	}
            	else{
  	ref.delete_reference(creference.getText(),designation.getText());
    designation.setText("");
  	refer_comb.removeAllItems();
  	refer_comb.addItem("---Selectionner une réfèrences-----");
	list_refer_tr=ref.select_reference_code();

	for(int i=0;i<list_refer_tr.size();i++)
	   {
	          //Pour affecter une valeur de base de donn?es ? un Combobox 
		   refer_comb.addItem(list_refer_tr.get(i)+" "+list_refer_tr.get(i+1));
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
              refer_comb.enable(); 
//              if(ref.ex==false){
//               	JOptionPane.showMessageDialog(null, "Ce Poste n'existe pas", "",
//               	JOptionPane.INFORMATION_MESSAGE);
//               	but_sauv.setVisible(true);
//                  but_modif.setVisible(false);
//                  but_sup.setVisible(false);
//                  retour.setVisible(false);
//                  valid_supp.setVisible(false);
//                  valid_modif.setVisible(false);
//                  valid_ajou.setVisible(false);
//             	 refer_comb.setSelectedIndex(0);
//             	 creference.setText(ref.afficher_code_reference());
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
            //   }
            }});
	
  	valid_modif.addActionListener(
	            new ActionListener() { 
	            public void actionPerformed(ActionEvent e) {
	            	   msg="";
	            	   desc=false;
	            	   if(refer_comb.getSelectedIndex()==0){
	        	      				msg+="Vous Devez D'abord Sélectionner une réfèrence\n";
	                   }
	            	   else if(designation.getText().equals("")) {	
	                          msg+="Veuillez remplir l'intitule \n";
	            	 	}
	            	 	
	            	 	if(!msg.equals("")){
  		            		
 		  	            	JOptionPane.showMessageDialog(null,msg);                                                                            
 		  	            	}
	            	 	else{
	    	            	ref.setupdate_reference(creference.getText(), designation.getText());
	    	            	 JOptionPane.showMessageDialog(null,"La réfèrencese a été bien modifier");
	   	    	             designation.setText("");
	    	            		refer_comb.removeAllItems();
	    	            	  	 refer_comb.addItem("---Selectionner une réfèrences-----");
	    	            	 	list_refer_tr=ref.select_reference_code();

	    	            		   for(int i=0;i<list_refer_tr.size();i++)
	    	            		   {
	    	            		          //Pour affecter une valeur de base de donn?es ? un Combobox 
	    	            			   refer_comb.addItem(list_refer_tr.get(i)+" "+list_refer_tr.get(i+1));
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
                 refer_comb.disable();
                // ref.reference(designation.getText());

                 if(existe==true){
                 	 JOptionPane.showMessageDialog(null, "Cette réfèrences existe déja", "",
                 	 JOptionPane.INFORMATION_MESSAGE);
                 	   but_sauv.setVisible(false);
                      but_modif.setVisible(true);
                      but_sup.setVisible(true);
                      retour.setVisible(false);
                      valid_supp.setVisible(false);
                      valid_modif.setVisible(false);
                      valid_ajou.setVisible(false);
                      creference.setText(ref.code);
                      //System.out.println()
                      refer_comb.setSelectedItem(ref.code+" "+ref.des); 
                      designation.setText(ref.des);
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
           	  
        	 	if(designation.getText().equals("")) {	desc=true;
                      msg+="Veuillez remplir la designation \n";}
        	 	else if (existe==true) {msg+="Cette réfèrences existe déja \n";}
        	 	else{
        	 		
        	   ref.ajouter_reference(creference.getText(), designation.getText());
        	   refer_comb.removeAllItems();
        	  	 refer_comb.addItem("---Selectionner une réfèrence-----");
        	  	list_refer_tr=ref.select_reference_code();
        		   for(int i=0;i<list_refer_tr.size();i++)
        		   {
        		          //Pour affecter une valeur de base de donn?es ? un Combobox 
        			   refer_comb.addItem(list_refer_tr.get(i)+" "+list_refer_tr.get(i+1));
        			   i++;
        		   }
        	 	
        	 	}
        	 	
        	 	
                if(desc==false&&existe==false){
             	   JOptionPane.showMessageDialog(null,"La réfèrence a été bien ajouter");
             	   refer_comb.enable();
                   designation.setText("");
                }
                else JOptionPane.showMessageDialog(null,msg);
              
        }});
   
   
  
   
   refer_comb.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
        	   String part1="";
        	   String part2="";
        	   LineBorder border = new LineBorder ( Color.white, 1, true );
               TitledBorder titl2 = new TitledBorder ( border, "Edition réfèrence", TitledBorder.DEFAULT_POSITION,
               TitledBorder.DEFAULT_POSITION, police2, Color.white);
               pan_form.setBorder(titl2);
              
           	   if(refer_comb.getSelectedIndex()==0){
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
                  creference.setText(ref.afficher_code_reference());
                  }
           	   
           	   else {      
           		 if(refer_comb.getItemCount()>1){
                      String[] parts = refer_comb.getSelectedItem().toString().split(" ");
                      
               		   part1 = parts[0]; // 004
               		   part2 = refer_comb.getSelectedItem().toString().replace(part1+" ", ""); 
                  	   existe=ref.reference(part2);
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
            		 
            		creference.setText(part1);
            		designation.setText(part2);
           		 }
        	   }      	
           }});
   
   designation.addFocusListener(new FocusListener() {
       public void focusLost(FocusEvent e) {
    	   existe=ref.reference(designation.getText());
       }
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
		}
     });
   
   designation.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
           refer_comb.enable();
           LineBorder border = new LineBorder ( Color.white, 1, true );
           TitledBorder titl2 = new TitledBorder ( border, "Edition réfèrences", TitledBorder.DEFAULT_POSITION,
           TitledBorder.DEFAULT_POSITION, police2, Color.white);
           pan_form.setBorder(titl2);
           existe=ref.reference(designation.getText());

           if(designation.getText().equals("")||existe==false){
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
               
           creference.setText(ref.afficher_code_reference());
           refer_comb.setSelectedIndex(0);
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
        	  
          creference.setText(ref.code);
          designation.setText(ref.des);
          refer_comb.setSelectedItem(ref.code+" "+ref.des); 
        	   }
           }});
   
     creference.setPreferredSize(new Dimension(250, 30));
     designation.setPreferredSize(new Dimension(250,30));
     panel.add(pan_form);
     pan_form.add(pan_combo);
     pan_form.add(pan_refer);
     pan_form.add(pan_designation);
     pan_form.add(pan_button);
     pan_form.setOpaque(false);
  	pan_combo.add(pan_lab_refer);
  	pan_combo.add(pan_combo_refer);

  	
  	pan_lab_refer.add(lab_combo);
  	pan_combo_refer.add(refer_comb);
  	
  	pan_refer.add(pan_lab_crefer);
  	pan_refer.add(pan_jtext_crefer);

  	pan_lab_crefer.add(refer_lab);
  	pan_jtext_crefer.add(creference);
	
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
  	pan_refer.setOpaque(false);
  	pan_designation.setOpaque(false);
  	pan_lab_refer.setOpaque(false);
  	pan_combo_refer.setOpaque(false);
  	pan_lab_crefer.setOpaque(false);
  	pan_jtext_crefer.setOpaque(false);
  	pan_lab_designation.setOpaque(false);
  	pan_jtext_designation.setOpaque(false);
  	pan_combo.setOpaque(false);
  	
  	refer_comb.setFont(police_fi);
  	creference.setFont(police_fi);
  	designation.setFont(police_fi);

  	refer_lab.setFont(police2);
  	designation_lab.setFont(police2);
  	lab_combo.setFont(police2);
  	refer_lab.setForeground(new Color(255,255,255)); 
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
     refer_comb.setPreferredSize(new Dimension(250, 33));

    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    pan_button.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
 
    pan_lab_crefer.setBorder(BorderFactory.createEmptyBorder(0, 0, -10, -25));
    pan_lab_designation.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 17));
    pan_lab_refer.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
    pan_jtext_crefer.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    pan_jtext_designation.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, 0));

    pan_combo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 30));
    pan_designation.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 30));

    pan_lab_refer.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_combo_refer.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
    
    pan_lab_crefer.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_jtext_crefer.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
    pan_lab_designation.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_jtext_designation.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
  	pan_button.setLayout( new FlowLayout( FlowLayout.CENTER ));

  	pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
  	
  	panel.setLayout(new GridLayout(1, 1));  	
  	pan_combo.setLayout(new BoxLayout(pan_combo,BoxLayout.X_AXIS));
  	pan_refer.setLayout(new BoxLayout(pan_refer,BoxLayout.X_AXIS));
  	pan_designation.setLayout(new BoxLayout(pan_designation,BoxLayout.X_AXIS));

    setTitle("Gestion des Réfèrences" );
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
				codification_refer frame = new codification_refer("7");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}








