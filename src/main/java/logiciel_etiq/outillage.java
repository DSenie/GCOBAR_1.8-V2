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


public class outillage extends JFrame implements ActionListener{
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
                 outil_comb.enable(); 
                	outil.disable();
                	intitule.enable();
                	but_sauv.setVisible(true);
                	but_modif.setVisible(false);
                	but_sup.setVisible(false);
                	retour.setVisible(false);
                	valid_supp.setVisible(false);
                	valid_ajou.setVisible(false);
                	valid_modif.setVisible(false);
                 intitule.setText("");
                 outil_comb.setSelectedIndex(0);
                 outil.setText(otl.afficher_code_outil());
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

//private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

private JPanel pan_form=new JPanel();
private JPanel pan_combo=new JPanel();
private JPanel pan_lab_outil=new JPanel();
private JPanel pan_combo_outil=new JPanel();
private JPanel pan_outil=new JPanel();
private JPanel pan_lab_coutil=new JPanel();
private JPanel pan_jtext_coutil=new JPanel();
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
	
private JLabel outil_lab=new JLabel("Code fournisseur");
private JLabel lab_combo=new JLabel("Liste de fournisseur");
private	JTextField outil=new JTextField();
private JLabel intitule_lab=new JLabel("Intitule");
private JTextField intitule=new JTextField();
private jcombo outil_comb;

private static gestion_outil otl=new gestion_outil();
private static ArrayList<String> list_outil_tr=new ArrayList<String>();

private static List<String> list_o;

outillage(final String log){ 
	
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
	
	list_o= new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un outillage-----"}));
	outil_comb = new jcombo(list_o.toArray());
	list_outil_tr=otl.select_outil_code();
	   for(int i=0;i<list_outil_tr.size();i++)
	   {    //Pour affecter une valeur de base de donn�es � un Combobox
		  outil_comb.addItem(list_outil_tr.get(i)+" "+list_outil_tr.get(i+1));
		   i++;
	   }
	   outil_comb.setWide(true);
   Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
   if(!selectioncomb.prv.contains("outillage"))
   selectioncomb.prv.add("outillage");
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
  	
  	outil.disable();
  	intitule.enable();

	LineBorder border = new LineBorder ( Color.white, 1, true );
	TitledBorder titl2 = new TitledBorder ( border, "Edition Outillage", TitledBorder.DEFAULT_POSITION,
    TitledBorder.DEFAULT_POSITION, police2, Color.white);
    pan_form.setBorder(titl2);
    outil.setText(otl.afficher_code_outil());

	retour.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            LineBorder border = new LineBorder ( Color.white, 1, true );
          	TitledBorder titl2 = new TitledBorder ( border, "Edition Outillage", TitledBorder.DEFAULT_POSITION,
            TitledBorder.DEFAULT_POSITION, police2, Color.white);
            pan_form.setBorder(titl2);
            outil_comb.enable(); 
           	outil.disable();
           	intitule.enable();
           	but_sauv.setVisible(true);
           	but_modif.setVisible(false);
           	but_sup.setVisible(false);
           	retour.setVisible(false);
           	valid_supp.setVisible(false);
           	valid_ajou.setVisible(false);
           	valid_modif.setVisible(false);
            intitule.setText("");
            outil_comb.setSelectedIndex(0);
          	outil.setText(otl.afficher_code_outil());
            }});
  	/////////////////////////////////////////////////////////
    
    
    but_sup.addActionListener(
            new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            LineBorder border = new LineBorder ( Color.white, 1, true );
          	TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
            TitledBorder.DEFAULT_POSITION, police2, Color.white);
            pan_form.setBorder(titl2);
            outil_comb.enable(); 
           	outil.disable();
           	
           	otl.outillage(intitule.getText());
            if(otl.ex==false){
             	JOptionPane.showMessageDialog(null, "Cet outil n'existe pas", "",
             	JOptionPane.INFORMATION_MESSAGE);
             	but_sauv.setVisible(true);
                but_modif.setVisible(false);
                but_sup.setVisible(false);
                retour.setVisible(false);
                valid_supp.setVisible(false);
                valid_modif.setVisible(false);
                valid_ajou.setVisible(false);
           	    outil_comb.setSelectedIndex(0);
           	    outil.setText(otl.afficher_code_outil());
           	
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
            	otl.outillage(intitule.getText());
                if(otl.ex==false){msg+="Cet outil n'existe pas \n";}
                else if(outil_comb.getSelectedIndex()==0){
	      				msg+="Vous Devez D'abord Selectionner un outil \n";
          }
            	if(!msg.equals("")){
	            		
	  	            	JOptionPane.showMessageDialog(null,msg);                                                                            
	  	            	}
            	else{
  	otl.delete_outillage(outil.getText(),intitule.getText());
    intitule.setText("");
  	outil_comb.removeAllItems();
  	outil_comb.addItem("---Selectionner un outil-----");
  	list_outil_tr=otl.select_outil_code();
	   for(int i=0;i<list_outil_tr.size();i++)
	   {
	          //Pour affecter une valeur de base de donn�es � un Combobox
		   outil_comb.addItem(list_outil_tr.get(i)+" "+list_outil_tr.get(i+1));
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
             outil_comb.enable(); 
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
//            	 outil_comb.setSelectedIndex(0);
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
	            	   if(outil_comb.getSelectedIndex()==0){
	                	msg+="Vous Devez D'abord Sélectionner un outil \n";
	                   }
	            	   else if(intitule.getText().equals("")) {	desc=true;
	                          msg+="Veuillez remplir l'intitule \n";
 
	            	 	}
	            	 	
	            	 	if(!msg.equals("")){
  		            		
 		  	            	JOptionPane.showMessageDialog(null,msg);                                                                            
 		  	            	}
	            	 	else{
	    	            	 otl.setupdate_outil(outil.getText(), intitule.getText());
	    	            	 JOptionPane.showMessageDialog(null,"L'outil a été bien modifier");
	   	    	             intitule.setText("");
	    	            		 outil_comb.removeAllItems();
	    	            	  	 outil_comb.addItem("---Selectionner un outil-----");
	    	            	  	list_outil_tr=otl.select_outil_code();
	    	            		   for(int i=0;i<list_outil_tr.size();i++)
	    	            		   {
	    	            		          //Pour affecter une valeur de base de donn�es � un Combobox
	    	            			   outil_comb.addItem(list_outil_tr.get(i)+" "+list_outil_tr.get(i+1));
	    	            			   i++;
	    	            		   }
	    	            		   
	    	                     	outil.setText(otl.afficher_code_outil());
	    	            	 	}	            	 	
			  	    	}});
	   
	
  
  	 but_sauv.addActionListener(
             new ActionListener() { 
             public void actionPerformed(ActionEvent e) {
              LineBorder border = new LineBorder ( Color.white, 1, true );
           	  TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
              TitledBorder.DEFAULT_POSITION, police2, Color.white);
              pan_form.setBorder(titl2);
              outil_comb.disable();
              otl.outillage(intitule.getText());

              if(otl.ex==true){
              	 JOptionPane.showMessageDialog(null, "Cet outil existe déja", "",
              	 JOptionPane.INFORMATION_MESSAGE);
              	   but_sauv.setVisible(false);
                   but_modif.setVisible(true);
                   but_sup.setVisible(true);
                   retour.setVisible(false);
                   valid_supp.setVisible(false);
                   valid_modif.setVisible(false);
                   valid_ajou.setVisible(false);
                   otl.outillage(intitule.getText());
                   outil.setText(otl.code);
                   outil_comb.setSelectedItem(otl.code+" "+otl.nom); 
                   intitule.setText(otl.nom);
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
               otl.outillage(intitule.getText());

        	 	if(intitule.getText().equals("")) {	desc=true;
                      msg+="Veuillez remplir l'intitule \n";}
        	 	else if(outil.getText().equals("")) {	desc=true;
                msg+="Veuillez remplir le code outil \n";}
        	 	else if (otl.ex==true) {msg+="Cet outil existe déja \n";}
        	 	else{
        	 		
        	  exist= otl.ajouter_outil(outil.getText(), intitule.getText());
        	   outil_comb.removeAllItems();
        	  	 outil_comb.addItem("---Selectionner un outil-----");
        	  	list_outil_tr=otl.select_outil_code();
        		   for(int i=0;i<list_outil_tr.size();i++)
        		   {
        		          //Pour affecter une valeur de base de données é un Combobox
        			   outil_comb.addItem(list_outil_tr.get(i)+" "+list_outil_tr.get(i+1));
        			   i++;
        		   }
                 	outil.setText(otl.afficher_code_outil());

        	 	
        	 	}
        	 	
        	 	if(exist==false&&desc==false&&otl.ex==false){
        	 		JOptionPane.showMessageDialog(null,"L'outil a été bien ajouter");
        	 		 outil_comb.enable();
                     intitule.setText("");
        	 	}
        	 	else{
        	JOptionPane.showMessageDialog(null,msg);
        }
        	 			  	 
        	 	
	    		}});
   
   
  
   
          outil_comb.addActionListener(
           new ActionListener() { 
           public void actionPerformed(ActionEvent e) {
        	   LineBorder border = new LineBorder ( Color.white, 1, true );
               TitledBorder titl2 = new TitledBorder ( border, "Edition outillage", TitledBorder.DEFAULT_POSITION,
               TitledBorder.DEFAULT_POSITION, police2, Color.white);
               pan_form.setBorder(titl2);
               if(outil_comb.getSelectedIndex()==0){
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
                    outil.setText(otl.afficher_code_outil());
           		     
           		   
           	   }
           	   else {      
           		 if(outil_comb.getItemCount()>1){
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
            		  String[] parts = outil_comb.getSelectedItem().toString().split(" ");
            		  String part1 = parts[0]; // 004
            		  
            		  String part2 = outil_comb.getSelectedItem().toString().replace(part1+" ",""); 
            		 outil.setText(part1);
            		 intitule.setText(part2);
           		 }
        	   }
        	  // }
           }});
   

          intitule.addActionListener(
                  new ActionListener() { 
                  public void actionPerformed(ActionEvent e) {
                  outil_comb.enable();
                  LineBorder border = new LineBorder ( Color.white, 1, true );
                  TitledBorder titl2 = new TitledBorder ( border, "Edition Poste", TitledBorder.DEFAULT_POSITION,
                  TitledBorder.DEFAULT_POSITION, police2, Color.white);
                  pan_form.setBorder(titl2);
                  otl.outillage(intitule.getText());
                  if(intitule.getText().equals("")||otl.ex==false){
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
                  outil.setText(otl.afficher_code_outil());
                  outil_comb.setSelectedIndex(0);
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
               	  
                 outil.setText(otl.code);
                 intitule.setText(otl.nom);
                 outil_comb.setSelectedItem(otl.code+" "+otl.nom); 
               	   }
               	  // }
                  }});

     outil.setPreferredSize(new Dimension(250, 30));
     intitule.setPreferredSize(new Dimension(250,30));
     panel.add(pan_form);
     pan_form.add(pan_combo);
     pan_form.add(pan_outil);
     pan_form.add(pan_intitule);
     pan_form.add(pan_button);
     pan_form.setOpaque(false);
  	pan_combo.add(pan_lab_outil);
  	pan_combo.add(pan_combo_outil);

  	
  	pan_lab_outil.add(lab_combo);
  	pan_combo_outil.add(outil_comb);
  	
  	pan_outil.add(pan_lab_coutil);
  	pan_outil.add(pan_jtext_coutil);

  	pan_lab_coutil.add(outil_lab);
  	pan_jtext_coutil.add(outil);
	
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
  	pan_outil.setOpaque(false);
  	pan_intitule.setOpaque(false);
  	pan_lab_outil.setOpaque(false);
  	pan_combo_outil.setOpaque(false);
  	pan_lab_coutil.setOpaque(false);
  	pan_jtext_coutil.setOpaque(false);
  	pan_lab_intitule.setOpaque(false);
  	pan_jtext_intitule.setOpaque(false);
  	pan_combo.setOpaque(false);
  	
  	outil_comb.setFont(police_fi);
  	outil.setFont(police_fi);
  	intitule.setFont(police_fi);

  	outil_lab.setFont(police2);
  	intitule_lab.setFont(police2);
  	lab_combo.setFont(police2);
  	outil_lab.setForeground(new Color(255,255,255)); 
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
     outil_comb.setPreferredSize(new Dimension(250, 33));

    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    pan_button.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
 
    pan_lab_coutil.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 0));
    pan_jtext_coutil.setBorder(BorderFactory.createEmptyBorder(0, -8, 20, 0));

    pan_lab_intitule.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
    pan_lab_outil.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

    pan_combo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 30));
    pan_intitule.setBorder(BorderFactory.createEmptyBorder(20, 95, 20, 15));

    pan_lab_outil.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_combo_outil.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
    
    pan_lab_coutil.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_jtext_coutil.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
    pan_lab_intitule.setLayout( new FlowLayout( FlowLayout.CENTER ));
    pan_jtext_intitule.setLayout(new FlowLayout(FlowLayout.LEFT));
  	
  	pan_button.setLayout( new FlowLayout( FlowLayout.CENTER ));

  	pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
  	
  	panel.setLayout(new GridLayout(1, 1));  	
  	pan_combo.setLayout(new BoxLayout(pan_combo,BoxLayout.X_AXIS));
  	pan_outil.setLayout(new BoxLayout(pan_outil,BoxLayout.X_AXIS));
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
				 new outillage("7");
				//frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}








