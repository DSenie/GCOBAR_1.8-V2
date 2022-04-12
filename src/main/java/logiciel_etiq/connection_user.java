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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;


public class connection_user  extends JFrame implements ActionListener{
	private static final String    CTRL_J                = "CTRL+J";

    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
    	 
     
            // gestion de l'action
           
             if (e.getActionCommand().equals(CTRL_J)){
            	 
            	 LineBorder border = new LineBorder ( Color.white, 1, true );
        		 TitledBorder titl2 = new TitledBorder ( border, "Edition Utilisateur", TitledBorder.DEFAULT_POSITION,
        	     TitledBorder.DEFAULT_POSITION, police2, Color.white);
        	     paneluser.setBorder(titl2);
            	 mat1.enable();
          	 nom1.enable();
           	     prenom1.enable();
           	     login1.enable();
           		 pass1.enable();
       		    respo.enable();
           		 utili.enable();
         		 imp.enable();
           		 prf.enable();
           		 nom1.setText("");
           		 mat1.setText("");
            	 prenom1.setText("");
            	 login1.setText("");
           		 pass1.setText("");
           		 ajouter.setVisible(true);
           		 modifier.setVisible(false);
           		 Supprimer.setVisible(false);
           		 valid_ajou.setVisible(false);
           		 retour.setVisible(false);
           		 valid_modif.setVisible(false);
           		 valid_supp.setVisible(false);
             }}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void close(){
        this.dispose();
        }
	
      protected JPanel paneluser=new JPanel();
      protected JLabel mat = new JLabel("Matricule");
      protected JTextField mat1;
      protected JLabel nom = new JLabel("Nom");
      protected JTextField nom1= new  JTextField();
      protected JLabel profil = new JLabel("Profils");
      protected List<String> list_prof;
      protected jcombo profils_comb;
  	  protected JPanel pan_lab_prof=new JPanel();
  	  protected JPanel pan_comb_prof=new JPanel();
  	  
      protected gestion_user use= new  gestion_user();
      protected String s;
      protected String a;
      protected String l="";
      protected String msg="";
      protected  JLabel prenom = new JLabel("Pr�nom");
      protected JTextField prenom1= new  JTextField();
      protected  JLabel login = new JLabel("User");
      protected JTextField login1= new  JTextField();
      protected JLabel pass22 = new JLabel("Password");

      protected JPasswordField pass1 = new JPasswordField();
      protected JLabel prio = new JLabel("Priorite"); 
      protected JRadioButton respo= new JRadioButton("Responsable",true);
      protected JRadioButton utili = new JRadioButton("Utilisateur",false); 
      protected JLabel role= new JLabel("Role"); 
      protected JRadioButton imp= new JRadioButton("Responsable impression",true);
      protected JRadioButton prf = new JRadioButton("Responsable fin chaine",false);
      protected ButtonGroup bG = new ButtonGroup();
      protected ButtonGroup bG2 = new ButtonGroup();
      private JPanel panel;
      protected JButton  ajouter = new JButton ("Ajouter");
      protected JButton  modifier = new JButton ("Modifier");
      protected JButton  Supprimer = new JButton("Supprimer");
      protected JButton  retour = new JButton ("Retour");
      protected JButton valid_ajou= new  JButton("Valider");
      protected JButton valid_modif=new  JButton("Valider"); 
      protected JButton valid_supp=new  JButton("Valider");
      
      protected JPanel panelimg= new JPanel();
      protected JPanel panelform= new JPanel();
      protected JPanel panelinfp= new JPanel();
      protected JPanel panelnp= new JPanel();
      protected JPanel paneln= new JPanel();
      protected JPanel panel_lab_n= new JPanel();
      protected JPanel panel_jtext_n= new JPanel();
      protected JPanel panelp= new JPanel();
      protected JPanel panel_lab_p= new JPanel();
      protected JPanel  panel_jtext_p= new JPanel();
      protected JPanel panelmt= new JPanel();
      protected JPanel panelm= new JPanel();
      protected JPanel panel_lab_m= new JPanel();
      protected JPanel  panel_jtext_m= new JPanel();
      protected  JPanel panelvide= new JPanel();      
      protected JPanel panelinfc= new JPanel();
      protected JPanel paneluse= new JPanel();
      protected JPanel panel_lab_use= new JPanel();
      protected JPanel  panel_jtext_use= new JPanel();
      protected JPanel panelpass= new JPanel();
      protected JPanel panel_lab_pass= new JPanel();
      protected JPanel  panel_jtext_pass= new JPanel();
      protected JPanel panelradio= new JPanel();
      protected JPanel panelradio1= new JPanel();
      protected JPanel panelprio= new JPanel();
      protected JPanel panelrole= new JPanel();
      protected JPanel panelbut= new JPanel();
      protected Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);
	  
      
	 //  public static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
     
	  public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {					     
					//	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

						connection_user frame = new connection_user("4");
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	  
	
	  @SuppressWarnings("deprecation")
	connection_user(final String log){
		    list_prof = new ArrayList<String>(
			Arrays.asList(new String[] { "---Selectionner un Profils-----" }));
			profils_comb = new jcombo(list_prof.toArray());
			
		  selectioncomb.selectprofils(profils_comb, this, log);
		  LineBorder border = new LineBorder ( Color.white, 1, true );
		  TitledBorder titl2 = new TitledBorder ( border, "Edition Utilisateur", TitledBorder.DEFAULT_POSITION,
	      TitledBorder.DEFAULT_POSITION, police2, Color.white);
	      paneluser.setBorder(titl2);
	      
	      
		  Toolkit kit = Toolkit.getDefaultToolkit();
		  Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
		  setIconImage(img);
		  selectioncomb.windows(this,log);
		  if(!selectioncomb.prv.contains("utilisateur"))
			  selectioncomb.prv.add("utilisateur");
		  MaskFormatter num = null;
          try {
               num = new MaskFormatter("#####");
              } 
          catch (ParseException e) {
                                    e.printStackTrace();
                                   }
          mat1= new  JFormattedTextField(num);
          
		  ajouter.setVisible(true);
		  modifier.setVisible(false);
		  Supprimer.setVisible(false);
		  retour.setVisible(false);
		  valid_ajou.setVisible(false);
		  valid_modif.setVisible(false);
		  valid_supp.setVisible(false);
	  	  nom1.enable();
		  mat1.enable();
		  prenom1.enable();
		  login1.enable();
		  pass1.enable();


		  setTitle("Gestion des Utilisateurs" );
          setSize(1000, 600);
          setLocationRelativeTo(null);          
          setVisible(true);
		  initComponents();
    
		 
			
		matricule();
		
		retour.addActionListener(
                 new ActionListener() { 
                 public void actionPerformed(ActionEvent e) {
                	 LineBorder border = new LineBorder ( Color.white, 1, true );
            		 TitledBorder titl2 = new TitledBorder ( border, "Edition Utilisateur", TitledBorder.DEFAULT_POSITION,
            	     TitledBorder.DEFAULT_POSITION, police2, Color.white);
            	     paneluser.setBorder(titl2);
            	     profils_comb.setSelectedIndex(0);
                	 mat1.enable();
              	     nom1.enable();
	           	     prenom1.enable();
	           	     login1.enable();
	           		 pass1.enable();
           		      respo.enable();
	           		 utili.enable();
             		 imp.enable();
	           		 prf.enable();
            		 profils_comb.enable();

	           		 nom1.setText("");
	           		 mat1.setText("");
	            	 prenom1.setText("");
	            	 login1.setText("");
	           		 pass1.setText("");
	           		 ajouter.setVisible(true);
	           		 modifier.setVisible(false);
	           		 Supprimer.setVisible(false);
	           		 valid_ajou.setVisible(false);
	           		 retour.setVisible(false);
	           		 valid_modif.setVisible(false);
	           		 valid_supp.setVisible(false);     
                 }});
		
		 modifier.addActionListener(
                 new ActionListener() { 
                 public void actionPerformed(ActionEvent e) { 
                	  LineBorder border = new LineBorder ( Color.white, 1, true );
            		  TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
            	      TitledBorder.DEFAULT_POSITION, police2, Color.white);
            	      paneluser.setBorder(titl2);
                	  ajouter.setVisible(false);
	             	  modifier.setVisible(false);
	             	  Supprimer.setVisible(false);
	             	  retour.setVisible(true);
	             	  valid_ajou.setVisible(false);
	             	  valid_modif.setVisible(true);
	             	  valid_supp.setVisible(false);
	             	  nom1.enable();
	             	  mat1.enable();
	             	  prenom1.enable();
	             	  login1.enable();
	             	  pass1.enable();
	             	 
 	  
                 }});
		 
		valid_modif.addActionListener(
                  new ActionListener() { 
                  public void actionPerformed(ActionEvent e) {
                	 use.selection(mat1.getText());
                	

                  if(mat1.getText().trim().length() <5){

          		  JOptionPane.showMessageDialog(null,"Vous devez remplir le matricule");
                	  }
                	  else 
                		 if(!mat1.getText().equals(use.k)){    
                		   JOptionPane.showMessageDialog(null,"Cette personne n'existe pas.");
                          }
                	 else{
                	    if (respo.isSelected()){ s="1"; }   
                 		else { s="0";  } 
                    	if (imp.isSelected()){  l="Responsable impression" ; }   
                  	    else{  l="Responsable fin chaine";   } 
                	    msg="";
                    	 if(nom1.getText().equals("")){ msg+="veuillez saisir le nom \n";}
                    	 else  	 if (!nom1.getText().matches("[a-zA-Z]*")){
                       		 msg+="veuillez vérifier le nom \n";
                         }
                    	 
                    	 else if(prenom1.getText().equals("")){msg+="veuillez saisir le prénom \n";}
                    	 else   if (!prenom1.getText().matches("[a-zA-Z]*")){
                       		 msg+="veuillez vérifier le prénom \n";
                         }
                       	 
                    	 else if(login1.getText().equals("")){msg+="veuillez saisir le login \n";}                  	 
                    	 else if(pass1.getText().equals("")){msg+="veuillez saisir le mot de passe \n";} 
                    	 
                         if(msg.equals("")){
               	         use.setupdate(mat1.getText(),nom1.getText(),
                      		   prenom1.getText(),
                      		  login1.getText(),
                      		  pass1.getText(),
                      		   s,
                      		    l);
               	   
                                 }
                         else{JOptionPane.showMessageDialog(null,msg);}}
                                 }});
		   //supprimer 
		 Supprimer.addActionListener(
                 new ActionListener() { 
                 public void actionPerformed(ActionEvent e) {
                	  LineBorder border = new LineBorder ( Color.white, 1, true );
            		  TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
            	      TitledBorder.DEFAULT_POSITION, police2, Color.white);
            	     paneluser.setBorder(titl2);
                 	 ajouter.setVisible(false);
            		 modifier.setVisible(false);
            		 Supprimer.setVisible(false);
            		 retour.setVisible(true);
            		 valid_ajou.setVisible(false);
            		 valid_modif.setVisible(false);
            		 valid_supp.setVisible(true);
              		 nom1.disable();
            		 mat1.enable();;
            		 prenom1.disable();
            		 login1.disable();
            		 pass1.disable(); 
            		 profils_comb.disable();

                 }});
		 
		  valid_supp.addActionListener(
                  new ActionListener() { 
                  public void actionPerformed(ActionEvent e) {
                	  if(mat1.getText().trim().length() <5){
                	JOptionPane.showMessageDialog(null,"Vous devez remplir le matricule");
                	  }
                	  else if(nom1.getText().equals("")){            
                		  JOptionPane.showMessageDialog(null,"Cette personne n'existe pas");
                       }
                	  else{
               	   use.setdelete(mat1.getText(),nom1.getText(),prenom1.getText());}
                  }});
		  
		 //ajouter
		 ajouter.addActionListener(
	                 new ActionListener() { 
	                 public void actionPerformed(ActionEvent e) {     
	            		  LineBorder border = new LineBorder ( Color.white, 1, true );
	            		  TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
	            	      TitledBorder.DEFAULT_POSITION, police2, Color.white);
	            	      paneluser.setBorder(titl2);
	                	  ajouter.setVisible(false);
		                  modifier.setVisible(false);
		                  Supprimer.setVisible(false);
		             	  retour.setVisible(true);
		             	  valid_ajou.setVisible(true);
		             	  valid_modif.setVisible(false);
		             	  valid_supp.setVisible(false);
		             	  nom1.enable();
		             	  mat1.enable();
		             	  prenom1.enable();
		             	  login1.enable();
		             	  pass1.enable();
	                 }});
		 
		   valid_ajou.addActionListener(
                  new ActionListener() { 
                  public void actionPerformed(ActionEvent e) {
                	if (respo.isSelected()){ s="1"; }   
               		else { s="0";  } 
                  	if (imp.isSelected()){  l="Responsable impression" ; }   
                	else{  l="Responsable fin chaine";   } 
                	  msg="";
               	 if(nom1.getText().equals("")){ msg+="veuillez saisir le nom \n";}
               	 else  	 if (!nom1.getText().matches("[a-zA-Z]*")){
               		 msg+="veuillez vérifier le nom \n";
                 }
               	 else if(prenom1.getText().equals("")){msg+="veuillez saisir le prénom \n";}
               	 else if(profils_comb.getSelectedIndex()==0){msg+="veuillez selectionner un profils \n";}
               	 
               	 else if (!prenom1.getText().matches("[a-zA-Z]*")){ msg+="veuillez vérifier le prénom \n";   }
 
               	 
               	 else  if(login1.getText().equals("")){msg+="veuillez saisir le login \n";}
               	 else if(pass1.getText().equals("")){msg+="veuillez saisir le mot de passe \n";}
              
                 if(msg.equals("")){
                	 String[] parts = profils_comb.getSelectedItem().toString().split(" ");
           		     int code_p = Integer.parseInt(parts[0]); // 004
           		  
                      use.setinsert(mat1.getText(),nom1.getText(),
                                                       		  prenom1.getText(),
                                                       		  login1.getText(),
                                                       		  pass1.getText(),
                                                       		   s,
                                                       		   l,code_p
                                                       		   );
                                                         
                                                             }
                            else{JOptionPane.showMessageDialog(null,msg);}
                  }
                  });
	}
	  
@SuppressWarnings("deprecation")
private void initComponents(){
    
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
    	
       ajouter.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
       modifier.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
       retour.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
       Supprimer.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
       valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
       valid_supp.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
       valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 15));


    	 panel.setOpaque(false);
	     JLabel image = new JLabel( new ImageIcon( getClass().getClassLoader().getResource("enrg.png")));
	     image.setBounds(10, 23, 150, 190);
		 image.setBorder(BorderFactory.createLineBorder(Color.black,2));	
        panelimg.add(image); 
        panel.add(paneluser);
	    paneluser.add(panelimg);
	    
	    
        paneluser.add(panelform);
        panelform.add(panelinfp);
        panelinfp.add(panelmt);
        panelmt.add(panelm);
        panelm.add(panel_lab_m);
        panel_lab_m.add(mat);
        panelm.add(panel_jtext_m);
        panel_jtext_m.add(mat1);

        panelmt.add(panelvide);
        panelvide.add(pan_lab_prof);
        panelvide.add(pan_comb_prof);
        pan_lab_prof.add(profil);
        pan_comb_prof.add(profils_comb);

        panelinfp.add(panelnp);
        panelnp.add(paneln);
        paneln.add(panel_lab_n);
        panel_lab_n.add(nom);
        paneln.add(panel_jtext_n);
        panel_jtext_n.add(nom1);
        
        panelnp.add(panelp);
        panelp.add(panel_lab_p);
        panel_lab_p.add(prenom);
        panelp.add(panel_jtext_p);
        panel_jtext_p.add(prenom1);
        
        
	  	panelform.add(panelinfc);

	  	panelinfc.add(paneluse);
	  	paneluse.add(panel_lab_use);
	  	 panel_lab_use.add(login);
	     paneluse.add(panel_jtext_use);
	    panel_jtext_use.add(login1);
	    
	  	panelinfc.add(panelpass);
	  	panelpass.add(panel_lab_pass);
	  	panel_lab_pass.add(pass22);
	    panelpass.add(panel_jtext_pass);
	    panel_jtext_pass.add(pass1);
	    
	   JPanel prio1=new JPanel(); 
	   JPanel prio2=new JPanel(); 

	   JPanel role1=new JPanel(); 
	   JPanel role2=new JPanel(); 

	  	panelform.add(panelradio);
	  	
	  	panelradio.add(panelprio);
	  	panelprio.add(prio1);	  	panelprio.add(prio2);
        prio1.add(utili);          prio2.add(respo);

	  	panelradio.add(panelrole);
	  	panelrole.add(role1);	  	panelrole.add(role2);
        role1.add(prf);             role2.add(imp);

        
	  	panelform.add(panelbut);
	  	
	  	


    
	    mat.setForeground(Color.white); 
	    nom.setForeground(Color.white); 
	    prenom.setForeground(Color.white); 
	 	   panelm.setBorder(BorderFactory.createEmptyBorder(30,0, 0, 0));
	 	   panelvide.setBorder(BorderFactory.createEmptyBorder(30,-20, 0, 0));

	 	   panelnp.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
	 	   paneluse.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
	 	   panelpass.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
	 	   prio1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
	 	   role1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
	 	   panelbut.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

 	   panel_lab_m.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
 	   panel_lab_n.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,38 ));
 	   panel_lab_use.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 35));


       mat1.setPreferredSize(new Dimension(210, 30));
       profils_comb.setPreferredSize(new Dimension(210, 30));
       nom1.setPreferredSize(new Dimension(210, 30));
       prenom1.setPreferredSize(new Dimension(210, 30));
       login1.setPreferredSize(new Dimension(210, 30));
       pass1.setPreferredSize(new Dimension(210, 30));
    
 	    
 	 
 	   
        Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);
	    mat.setFont(police2);
	    nom.setFont(police2);
	    prenom.setFont(police2);
	    pass22.setFont(police2);
	    login.setFont(police2);

	       prio1.setOpaque(false);
	       prio2.setOpaque(false);
	       role1.setOpaque(false);
	       role2.setOpaque(false);
	       pan_lab_prof.setOpaque(false);
	       pan_comb_prof.setOpaque(false);

	       panel_lab_m.setOpaque(false);

	       panel_lab_n.setOpaque(false);
	       panel_lab_p.setOpaque(false);
	       panel_lab_use.setOpaque(false);
	       panel_lab_pass.setOpaque(false);
	   

	       panel_jtext_m.setOpaque(false);
	       panel_jtext_n.setOpaque(false);
	       panel_jtext_p.setOpaque(false);
	       panel_jtext_use.setOpaque(false);
	       panel_jtext_pass.setOpaque(false);

	    panelinfp.setOpaque(false);
	    panelinfc.setOpaque(false);
	    panelbut.setOpaque(false);

	    panelform.setOpaque(false);
	    panelnp.setOpaque(false);
	    panelmt.setOpaque(false);
	    paneln.setOpaque(false);
	    panelm.setOpaque(false);
	    panelp.setOpaque(false);
	    panelvide.setOpaque(false);
	    paneluser.setOpaque(false);
	    panelradio.setOpaque(false);
	    panelprio.setOpaque(false);
	    panelrole.setOpaque(false);

	    paneluse.setOpaque(false);
	    panelpass.setOpaque(false);
	    panelbut.setOpaque(false);
	    
	    panelimg.setOpaque(false);

	    LineBorder border = new LineBorder ( Color.white, 1, true );
	    TitledBorder titled2 = new TitledBorder ( border, "Information Personnele", TitledBorder.DEFAULT_POSITION,
	            TitledBorder.DEFAULT_POSITION, police2, Color.white );
	    TitledBorder titled1 = new TitledBorder ( border, "Connection", TitledBorder.DEFAULT_POSITION,
	            TitledBorder.DEFAULT_POSITION, police2, Color.white );
	    TitledBorder titledrad1 = new TitledBorder ( border, "Priorite", TitledBorder.DEFAULT_POSITION,
	            TitledBorder.DEFAULT_POSITION, police2, Color.white );
	    TitledBorder titledrad2 = new TitledBorder ( border, "Role", TitledBorder.DEFAULT_POSITION,
	            TitledBorder.DEFAULT_POSITION, police2, Color.white);
	    //TitledBorder titled = new TitledBorder("Information Personnele");
	    panelinfp.setBorder(titled2);
	    panelinfc.setBorder(titled1);
	    panelprio.setBorder(titledrad1);
	    panelrole.setBorder(titledrad2);

	    //titled.setFont(police2);

	   titled2.setTitleColor(Color.white);
	  
	
	    login.setForeground(Color.white); 
	    pass22.setForeground(Color.white); 

	  // panel.add(prio);
	  respo.setContentAreaFilled(false);
	  utili.setContentAreaFilled(false);
	  prf.setContentAreaFilled(false);
	  imp.setContentAreaFilled(false);
	  respo.setForeground(Color.white); 
	  utili.setForeground(Color.white); 
	  prf.setForeground(Color.white); 
	  imp.setForeground(Color.white); 
	  profil.setForeground(Color.white); 
	
	   bG.add(respo);
	   bG.add(utili);
       bG2.add(imp);
       bG2.add(prf);
       respo.disable(); utili.disable();
       imp.disable(); prf.disable();
   
      
	   respo.setFont(police2);
	   utili.setFont(police2);
	   profil.setFont(police2);

	
	   imp.setFont(police2);
	   prf.setFont(police2);
	   
       panelbut.add(ajouter);
       panelbut.add(modifier);
       panelbut.add(Supprimer);
       panelbut.add(retour);
       panelbut.add(valid_ajou);
       panelbut.add(valid_modif);
       panelbut.add(valid_supp);
      

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        profil.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        //pan_comb_prof.setBorder(BorderFactory.createEmptyBorder(0, -20, 0, 0));
	    panelimg.setLayout(null);
	    panelform.setLayout(new BoxLayout(panelform,BoxLayout.Y_AXIS));
	    
	    panelinfp.setLayout(new BoxLayout( panelinfp,BoxLayout.Y_AXIS));
	    panelinfc.setLayout(new BoxLayout( panelinfc,BoxLayout.X_AXIS));
	    panelradio.setLayout(new BoxLayout( panelradio,BoxLayout.X_AXIS));

	    panelnp.setLayout(new BoxLayout(panelnp,BoxLayout.X_AXIS));
	    panelmt.setLayout(new BoxLayout(panelmt,BoxLayout.X_AXIS));
	    
	    paneln.setLayout(new BoxLayout(paneln,BoxLayout.X_AXIS));
	    panelp.setLayout(new BoxLayout(panelp,BoxLayout.X_AXIS));

	    panelm.setLayout(new BoxLayout(panelm,BoxLayout.X_AXIS));
	    panelvide.setLayout(new BoxLayout(panelvide,BoxLayout.X_AXIS));

	    paneluse.setLayout(new BoxLayout(paneluse,BoxLayout.X_AXIS));
	    panelpass.setLayout(new BoxLayout(panelpass,BoxLayout.X_AXIS));

	    panelrole.setLayout(new BoxLayout(panelrole,BoxLayout.Y_AXIS));
	    panelprio.setLayout(new BoxLayout(panelprio,BoxLayout.Y_AXIS));

	    panel.setLayout(new GridLayout(1,1));
	    paneluser.setLayout(new BoxLayout(paneluser,BoxLayout.X_AXIS));
	    pan_lab_prof.setLayout( new FlowLayout(  FlowLayout.CENTER));
	    panel_lab_m.setLayout( new FlowLayout(  FlowLayout.CENTER));
	    panel_lab_n.setLayout( new FlowLayout(  FlowLayout.CENTER));
	    panel_lab_p.setLayout( new FlowLayout(  FlowLayout.CENTER));
	    panel_lab_use.setLayout( new FlowLayout(  FlowLayout.CENTER));
	    panel_lab_pass.setLayout( new FlowLayout(  FlowLayout.CENTER));
	   
	    pan_comb_prof.setLayout( new FlowLayout(  FlowLayout.LEFT));
	    panel_jtext_m.setLayout( new FlowLayout(  FlowLayout.LEFT));
	    panel_jtext_p.setLayout( new FlowLayout(  FlowLayout.LEFT));
	    panel_jtext_n.setLayout( new FlowLayout(  FlowLayout.LEFT));
	    panel_jtext_use.setLayout( new FlowLayout(  FlowLayout.LEFT));
	    panel_jtext_pass.setLayout( new FlowLayout(  FlowLayout.LEFT));

	    panelimg.setPreferredSize(new Dimension(60, 700));
	    panelform.setPreferredSize(new Dimension(300, 600));
	

      //panel.setBorder(new EmptyBorder(5, 5, 5, 5));
       //paninfo.setContentAreaFilled(false);
       panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
    

       
       ajouter.setPreferredSize(new Dimension(130, 33));
       modifier.setPreferredSize(new Dimension(130, 33));
        Supprimer.setPreferredSize(new Dimension(130, 33));
     
       retour.setPreferredSize(new Dimension(130, 33));
       valid_supp.setPreferredSize(new Dimension(130, 33));
       valid_modif.setPreferredSize(new Dimension(130, 33));
       valid_ajou.setPreferredSize(new Dimension(130, 33));

   
          panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setContentPane(panel);

            }
           
public void matricule(){
	 mat1.addKeyListener(new KeyAdapter() {
	      @SuppressWarnings("deprecation")
		public void keyReleased(KeyEvent e) {
			  if(mat1.getText().trim().length()==5){
				 
		           	use.selection(mat1.getText());
		           	 if(mat1.getText().equals(use.k)){
		           	 LineBorder border = new LineBorder ( Color.white, 1, true );
		           	 TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
		           	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
		           	paneluser.setBorder(titl2);
		           	 JOptionPane.showMessageDialog(null,"Cette personne existe déja.");
		           	 ajouter.setVisible(false);
		    		 modifier.setVisible(true);
		    		 Supprimer.setVisible(true);
		    		 retour.setVisible(false);
		    		 valid_ajou.setVisible(false);
		    		 valid_modif.setVisible(false);
		    		 valid_supp.setVisible(false);
		    		 mat1.enable();
              	     nom1.enable();
	           	     prenom1.enable();
	           	     login1.enable();
	           		 pass1.enable();
           		      respo.enable();
	           		 utili.enable();
             		 imp.enable();
	           		 prf.enable();
            		 profils_comb.enable();
		    		 //mat1.disable();
		    		// System.out.println("eeeee"+use.prio+"  "+use.prio);
		    		 if(use.prio.equals("1")){
	           			 respo.setSelected(true);
	           		 }
	           		 else{ utili.setSelected(true);}
	           		
	           		 if(use.role.equals("Responsable impression")){
	           			 imp.setSelected(true);
	           		 }
	           		 else{ prf.setSelected(true);}
	           		nom1.setText(use.nom);
	           		// nom1.disable();
	           		 prenom1.setText(use.prenom);
	           		 //prenom1.disable();
	           		 login1.setText(use.login);
	           		 //login1.disable();
	           		 pass1.setText(use.pass);
	           		// pass1.disable();
	           		profils_comb.setSelectedItem(use.code_p+" "+use.nom_p);
		           	 }
		             else {
		            	 LineBorder border = new LineBorder ( Color.white, 1, true );
		           		 TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
		           	     TitledBorder.DEFAULT_POSITION, police2, Color.white);
		           		 paneluser.setBorder(titl2);
		            	 ajouter.setVisible(true);
			    		 modifier.setVisible(false);
			    		 Supprimer.setVisible(false);
			    		 retour.setVisible(false);
			    		 valid_ajou.setVisible(false);
			    		 valid_modif.setVisible(false);
			    		 valid_supp.setVisible(false);
			    		 mat1.enable();
		           		 nom1.enable();
		           	     prenom1.enable();
		           	     pass1.enable();
		           	    login1.enable();
		           	     respo.enable();
		           		 utili.enable();
		           		 imp.enable();
		           		 prf.enable();
		           		 nom1.setText("");
		            	 prenom1.setText("");
		            	 login1.setText("");
		            	 pass1.setText("");
		            	 profils_comb.setSelectedIndex(0);
		           		 }
			  
			  }  }});}

	    


public boolean isValid(String chaine) {
	try {
		Integer.parseInt(chaine);
	} catch (NumberFormatException e){
		return false;
	}

	return true;
}
}
