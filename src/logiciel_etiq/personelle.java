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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;


public class personelle extends JFrame implements ActionListener{
	private static final String    CTRL_J                = "CTRL+J";

	    @SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
	    	 
	     
	            // gestion de l'action
	           
	             if (e.getActionCommand().equals(CTRL_J)){
	            	 LineBorder border = new LineBorder ( Color.white, 1, true );
	           		  TitledBorder titl2 = new TitledBorder ( border, "Edition Personelle", TitledBorder.DEFAULT_POSITION,
	           	        TitledBorder.DEFAULT_POSITION, police2, Color.white);
	           	      panelpers.setBorder(titl2);
	                	 ajout.setVisible(true);
	            		 modif.setVisible(false);
	            		 supp.setVisible(false);
	            		 retour.setVisible(false);
	            		 valid_ajou.setVisible(false);
	            		 valid_modif.setVisible(false);
	            		valid_supp.setVisible(false);
	        	         mat1.enable();
		           		 nom1.enable();
		           	     prenom1.enable();
		           	     num_tel1.enable();
	              		 zone.setSelectedIndex(0);
		           		 zone.enable();
	           		 poste.setSelectedIndex(0);
		           		 poste.disable();
		           		 num_chaine.setSelectedIndex(0);
		           		 num_chaine.enable();
		           		perm.enable();
		           	     vol.enable();
		           		 nom1.setText("");
		           		 mat1.setText("");
		            	 prenom1.setText("");
		            	 num_tel1.setText("");
		          
	             }}
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JLabel mat = new JLabel("Matricule");
	   protected JTextField mat1;
	   protected JLabel nom = new JLabel("Nom");
	   protected JTextField nom1= new  JTextField();
	   protected final Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

	   protected JLabel prenom = new JLabel("Pr�nom");
	   protected JTextField prenom1= new  JTextField();
	   protected JLabel num_tl = new JLabel("telephone");
	   protected JTextField num_tel1= new  JTextField();
	   protected String msg;
	   protected JLabel type= new JLabel("Type"); 
	   protected JRadioButton perm= new JRadioButton("Permanant",true);
	   protected JRadioButton vol = new JRadioButton("Volant",false);
	   protected ButtonGroup bG = new ButtonGroup();
	   protected JLabel chaine= new JLabel("N Chaine"); 
	   protected jcombo num_chaine;
	   protected JLabel post= new JLabel("Poste"); 
	   protected jcombo poste;
	   protected JLabel zon= new JLabel("Zone"); 
	   protected jcombo zone;
	   protected JPanel panel;
	   protected JPanel panelpers=new JPanel();
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
	   protected  JPanel panel_lab_m= new JPanel();
	   protected JPanel  panel_jtext_m= new JPanel();
	   protected JPanel panelt= new JPanel();
	   protected JPanel panel_lab_t= new JPanel();
	   protected JPanel  panel_jtext_t= new JPanel();
	   protected JPanel panelinfc= new JPanel();
	   protected JPanel panelcz= new JPanel();
	   protected JPanel panelc= new JPanel();
	   protected JPanel panel_lab_c= new JPanel();
	   protected JPanel  panel_jtext_c= new JPanel();
	   protected JPanel panelz= new JPanel();
	   protected JPanel panel_lab_z= new JPanel();
	   protected JPanel  panel_jtext_z= new JPanel();
	   protected JPanel panelpo= new JPanel();
	   protected JPanel panelpos= new JPanel();
	   protected JPanel panel_lab_pos= new JPanel();
	   protected JPanel  panel_jtext_pos= new JPanel();
	   protected JPanel panelty= new JPanel();
	   protected JPanel panel_lab_ty= new JPanel();
	   protected JPanel  panel_jtext_ty= new JPanel();
	   protected String s;
	   protected  JPanel panelbut= new JPanel();

	   protected JButton ajout= new  JButton("Ajouter");
	   protected  JButton modif=new  JButton("Modifier"); 
	   protected JButton supp=new  JButton("Supprimer");
	   protected JButton retour=new  JButton("Retour");
	   protected  JButton valid_ajou= new  JButton("Valider");
	   protected  JButton valid_modif=new  JButton("Valider"); 
	   protected JButton valid_supp=new JButton("Valider");
       private static List<String> list_z;
	   private static List<String> list_c;
	   private static List<String> list_p;
	   public static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	   protected gestion_personelle perso=new  gestion_personelle();
	   MaskFormatter num = null;
	  personelle(final String log){
		  
		   final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

		  LineBorder border = new LineBorder ( Color.white, 1, true );
		  TitledBorder titl2 = new TitledBorder ( border, "Edition Personelle", TitledBorder.DEFAULT_POSITION,
	      TitledBorder.DEFAULT_POSITION, police2, Color.white);
	      panelpers.setBorder(titl2);
		  Toolkit kit = Toolkit.getDefaultToolkit();
		  Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
		  setIconImage(img);
		  selectioncomb.windows(this,log,laf);
		  if(!selectioncomb.prv.contains("personelle")){
				//	System.out.println("eeeeee");
				 selectioncomb.prv.add("personelle");}
//		 if(!selectioncomb.prv.contains("personelle")){
//		  System.out.println("ee"+selectioncomb.prv);
//		  }
			 
	          try {
	               num = new MaskFormatter("#####");
	              } 
	          catch (ParseException e) {
	                                    e.printStackTrace();
	                                   }
	       mat1= new  JFormattedTextField(num);
		
	          list_z = new ArrayList<String>(Arrays.asList(new String[]{"---S�lectionner une Zone-----"}));
		      zone = new jcombo(list_z.toArray());
		       selectioncomb.selectzone(zone ,this,log);	      
		     
		     
		       list_c = new ArrayList<String>(Arrays.asList(new String[]{"---S�lectionner une Chaine-----"}));
		       num_chaine = new jcombo(list_c.toArray());
			   selectioncomb.selectchaine(num_chaine,this,log);
		 	   
		 	   
		 	  list_p = new ArrayList<String>(Arrays.asList(new String[]{"---S�lectionner un Poste-----"}));
		      poste = new jcombo(list_p.toArray());
		      selectioncomb.selectposte(poste ,this,log);
	      
		 ajout.setVisible(true);
		 modif.setVisible(false);
		 supp.setVisible(false);
		 retour.setVisible(false);
		 valid_ajou.setVisible(false);
		 valid_modif.setVisible(false);
		 valid_supp.setVisible(false);
		 
//  		nom1.disable();
//		mat1.enable();
//		prenom1.disable();
//		num_tel1.disable();
//		perm.disable();
//		vol.disable();
//		num_chaine.disable();
//		zone.disable();
//		poste.disable();
		
 		matricule();


		ajout.addActionListener(
                 new ActionListener() { 
                 @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
                 LineBorder border = new LineBorder ( Color.white, 1, true );
           		 TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
           	     TitledBorder.DEFAULT_POSITION, police2, Color.white);
           	      panelpers.setBorder(titl2);
                  ajout.setVisible(false);
            	  modif.setVisible(false);
            	  supp.setVisible(false);
            	  retour.setVisible(true);
            	  valid_ajou.setVisible(true);
            	  valid_modif.setVisible(false);
            	  valid_supp.setVisible(false);
            	  nom1.enable();
            	  mat1.enable();
            	  prenom1.enable();
            	  num_tel1.enable();
            	  perm.enable();
            	  vol.enable();
            	  num_chaine.enable();
            	  zone.enable();
            	  poste.enable();
            	  //nom1.setText("");
            	 // prenom1.setText("");
            	 // num_tel1.setText("");
            	 // num_chaine.setSelectedIndex(0);
            	  //zone.setSelectedIndex(0);
            	  //poste.setSelectedIndex(0);
                 }});
		 
		 
		 valid_ajou.addActionListener(
                 new ActionListener() { 
                 public void actionPerformed(ActionEvent e) {
                 if (perm.isSelected()){ s="Permanant"; }   
          	 	 else{ s="Volant";   } 
               	  msg="";
               	 if(nom1.getText().equals("")){ msg+="Veuillez saisir le nom \n";}
            	 else{
            	 if (!nom1.getText().matches("[a-zA-Z�]*")){
               		 msg+="veuillez verifier le nom \n";
                 }
               	 else{msg=msg.replace("Veuillez verifier le nom \n", "");}
            	 }
               	 
            	 if(prenom1.getText().equals("")){msg+="Veuillez saisir le prenom \n";}
            	 else{
            	 if (!prenom1.getText().matches("[a-zA-Z�]*")){
               		 msg+="Veuillez verifier le prenom \n";
                 }
               	 else{msg=msg.replace("Veuillez verifier le prenom \n", "");}
            	 }
            		perso.selection(mat1.getText());
		           	
		          if(mat1.getText().equals(perso.Matricule)){msg+="Cette personne existe d�ja \n";}
		          else if(zone.getSelectedIndex()==0){msg+="veuillez s�lectionner une zone \n";}
            	 else if(poste.getSelectedIndex()==0){msg+="veuillez s�lectionner un poste \n";}
            	 else  if(num_chaine.getSelectedIndex()==0){msg+="veuillez s�lectionner une chaine \n";}            	 
              	 else  if(!isValid(mat1.getText())){msg+="Le matricule doit etre un nombre \n";}
                    if(msg.equals("")){
                        		  String[] parts = num_chaine.getSelectedItem().toString().split(" ");
                        		  String part1 = parts[0]; // 004
                        		  
                        		  String[] partp = poste.getSelectedItem().toString().split(" ");
                        		  String partp1 = partp[0]; // 004
                        		  
                        		  String[] partz = zone.getSelectedItem().toString().split(" ");
                        		  String partz1 = partz[0]; // 004
                                                        perso.setinsert(mat1.getText(),
                                                 	    		  nom1.getText(),
                                                         		  prenom1.getText(),
                                                         		  num_tel1.getText(),
                                                         		  part1,
                                                         		  partp1,
                                                         		   s
                                                         		 , partz1);
                                                     //  JOptionPane.showMessageDialog(null,nom1+" "+ prenom1+"a �t� bien ajouter");
                                                            }
                           else{JOptionPane.showMessageDialog(null,msg);}
                 }
                 });
		 
		
		   
		   modif.addActionListener(
	                  new ActionListener() { 
	                  @SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
	                	  LineBorder border = new LineBorder ( Color.white, 1, true );
	            		  TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
	            	      TitledBorder.DEFAULT_POSITION, police2, Color.white);
	            	      panelpers.setBorder(titl2);
	                	  ajout.setVisible(false);
	             		  modif.setVisible(false);
	             		  supp.setVisible(false);
	             		  retour.setVisible(true);
	             		  valid_ajou.setVisible(false);
	             		  valid_modif.setVisible(true);
	             		 valid_supp.setVisible(false);
	             		 nom1.enable();
	             		 mat1.enable();
	             		 prenom1.enable();
	             		 num_tel1.enable();
	             		 perm.enable();
	             		 vol.enable();
	             		 num_chaine.enable();
	             		 zone.enable();
	             		 poste.enable();
	                  }});
		   

		 valid_modif.addActionListener(
                  new ActionListener() { 
                  public void actionPerformed(ActionEvent e) {
                	  msg="";
                	  perso.selection(mat1.getText());
                	  if (perm.isSelected()){ s="Permanant"; }   
            	 		else{ s="Volant";   } 
               if(mat1.getText().trim().length() <5){
                     		 JOptionPane.showMessageDialog(null,"Vous devez remplir le matricule");
                	  }
                	  else 
                		  
             		 if(!mat1.getText().equals(perso.Matricule)){    
             		  JOptionPane.showMessageDialog(null,"Cette personne n'exist pas");
                       }
             	 else{
                    	 if(nom1.getText().equals("")){ msg+="veuillez saisir le nom \n";}
                    	 else{msg=msg.replace("veuillez saisir le nom \n", "");
                    	 if (!nom1.getText().matches("[a-zA-Z�]*")){
                       		 msg+="veuillez verifier le nom \n";
                         }
                       	 else{msg=msg.replace("veuillez verifier le nom \n", "");}
                    	 }
                    	 if(prenom1.getText().equals("")){msg+="veuillez saisir le prenom \n";}
                    	 else{msg=msg.replace("veuillez saisir le prenom \n", "");
                    	 if (!prenom1.getText().matches("[a-zA-Z�]*")){
                       		 msg+="veuillez verifier le prenom \n";
                         }
                       	 else{msg=msg.replace("veuillez verifier le prenom \n", "");}
                    	 }
                    	 if(zone.getSelectedIndex()==0){msg+="veuillez s�lectionner une zone \n";}
                    	 else{msg=msg.replace("veuillez s�lectionner une zone \n", "");}
                    	 if(poste.getSelectedIndex()==0){msg+="veuillez s�lectionner un poste \n";}
                    	 else{msg=msg.replace("veuillez s�lectionner un poste \n", "");}
                      	 if(num_chaine.getSelectedIndex()==0){msg+="veuillez s�lectionner une chaine \n";}
                    	 else{msg=msg.replace("veuillez s�lectionner une chaine \n", "");}
                    	 if(!isValid(mat1.getText())){msg+="Le matricule doit etre un nombre \n";}
                    	 else{msg=msg.replace("Le matricule doit �tre un nombre \n", "");}
                                 if(msg.equals("")){
                                	 String[] parts = num_chaine.getSelectedItem().toString().split(" ");
                           		  String part1 = parts[0]; // 004
                           		  
                           		  String[] partp = poste.getSelectedItem().toString().split(" ");
                           		  String partp1 = partp[0]; // 004
                           		  
                           		  String[] partz = zone.getSelectedItem().toString().split(" ");
                           		  String partz1 = partz[0]; // 004
                           		  perso.setupdate(mat1.getText(),
                                                    	    		  nom1.getText(),
                                                            		   prenom1.getText(),
                                                            		  num_tel1.getText(),
                                                            		  part1,
                                                            		partp1,
                                                            		 s
                                                            		 , partz1);
                                 	//JOptionPane.showMessageDialog(null,nom1+" "+ prenom1+"a �t� bien modifier");

               	   
                                 }    else{JOptionPane.showMessageDialog(null,msg);}
             	 }    }});
		 
		 
		 retour.addActionListener(
                 new ActionListener() { 
                 @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
                	 LineBorder border = new LineBorder ( Color.white, 1, true );
           		  TitledBorder titl2 = new TitledBorder ( border, "Edition Personelle", TitledBorder.DEFAULT_POSITION,
           	        TitledBorder.DEFAULT_POSITION, police2, Color.white);
           	        panelpers.setBorder(titl2);
                	 ajout.setVisible(true);
            		 modif.setVisible(false);
            		 supp.setVisible(false);
            		 retour.setVisible(false);
            		 valid_ajou.setVisible(false);
            		 valid_modif.setVisible(false);
            		 valid_supp.setVisible(false);
                	 mat1.enable();
	           		 nom1.enable();
	           	     prenom1.enable();
	           	     num_tel1.enable();
              		 zone.setSelectedIndex(0);
	           		 zone.enable();
           		     poste.setSelectedIndex(0);
	           		 poste.enable();
	           		 num_chaine.setSelectedIndex(0);
	           		 num_chaine.enable();
	           		 perm.enable();
	           	     vol.enable();
	           		 nom1.setText("");
	           		 mat1.setText("");
	            	 prenom1.setText("");
	            	 num_tel1.setText("");
	            	 
                 }});
		 
		 supp.addActionListener(
                 new ActionListener() { 
                 @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
                	 LineBorder border = new LineBorder ( Color.white, 1, true );
           		  TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
           	        TitledBorder.DEFAULT_POSITION, police2, Color.white);
           	      panelpers.setBorder(titl2);
           		
                	 ajout.setVisible(false);
            		 modif.setVisible(false);
            		 supp.setVisible(false);
            		 retour.setVisible(true);
            		 valid_ajou.setVisible(false);
            		 valid_modif.setVisible(false);
            		valid_supp.setVisible(true);
              		nom1.disable();
            		mat1.enable();;
            		prenom1.disable();
            		num_tel1.disable();
            		perm.disable();
            		vol.disable();
            		num_chaine.disable();
            		zone.disable();
            		poste.disable();
                 }});
		 
		 valid_supp.addActionListener(
                  new ActionListener() { 
                  public void actionPerformed(ActionEvent e) {  
                	  if(mat1.getText().trim().length() <5){
            		  JOptionPane.showMessageDialog(null,"Vous devez remplir le matricule");
            	  }
                	  else if(nom1.getText().equals("")){            
                		  JOptionPane.showMessageDialog(null,"Cette personne n'exist pas");
}
                	  else{         	 
               	   perso.setdelete(mat1.getText(),nom1.getText()+""+prenom1.getText());
         //      	JOptionPane.showMessageDialog(null,nom1+" "+ prenom1+"a �t� bien supprimer");               	
            	  }
                  }});
		 composant();
	} 
	  
	  public void composant(){
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
		  	
	    	JLabel image = new JLabel( new ImageIcon( getClass().getResource("enrg.png")));
	    	 image.setBounds(10, 20, 150, 190);
		 	 image.setBorder(BorderFactory.createLineBorder(Color.black,2));
		 	 
            panelimg.add(image); 
            panel.add(panelpers);
	    	panelpers.add(panelimg);
	    	panelpers.add(panelform);
            panelform.add(panelinfp);
            panelinfp.add(panelmt);
            panelinfp.add(panelnp);
		  	panelform.add(panelinfc);

            panelmt.add(panelm);
            panelm.add(panel_lab_m);
            panelm.add(panel_jtext_m);

            panelmt.add(panelt);
            panelt.add(panel_lab_t);
            panelt.add(panel_jtext_t);
            
         
            panelnp.add(paneln);
            paneln.add(panel_lab_n);
            paneln.add(panel_jtext_n);

            panelnp.add(panelp);
            panelp.add(panel_lab_p);
            panelp.add(panel_jtext_p);

		  	panelinfc.add(panelcz);
		  	panelcz.add(panelc);
		    panelc.add(panel_lab_c);
            panelc.add(panel_jtext_c);

		  	panelcz.add(panelz);
		    panelz.add(panel_lab_z);
            panelz.add(panel_jtext_z);

		  	panelinfc.add(panelpo);
		  	panelpo.add(panelpos);
		    panelpos.add(panel_lab_pos);
            panelpos.add(panel_jtext_pos);
		  	panelpo.add(panelty);
		  	panelty.add(panel_lab_ty);
	        panelty.add(panel_jtext_ty);
	            
            panelform.add(panelbut);
         
		   perm.setContentAreaFilled(false);
		   vol.setContentAreaFilled(false);
		   
		   panel_lab_m.add(mat);
		   panel_jtext_m.add(mat1);
		  	panel_lab_n.add(nom);
		  	panel_jtext_n.add(nom1);
		  	panel_lab_p.add(prenom);
		  	panel_jtext_p.add(prenom1);
		  	panel_lab_t.add(num_tl);
		  	panel_jtext_t.add(num_tel1);
		  	
		    panel_lab_c.add(chaine);
		    panel_jtext_c.add(num_chaine);
		  	panel_lab_z.add(zon);
		  	panel_jtext_z.add(zone);
		  	panel_lab_pos.add(post);
		  	panel_jtext_pos.add(poste);
		  	
		    panel_lab_ty.add(type);
		    bG.add(perm);
		    bG.add(vol);
		    panel_jtext_ty.add(perm);
		    panel_jtext_ty.add(vol);
			panelbut.add(ajout);
			panelbut.add(modif);
			panelbut.add(supp);
			panelbut.add(retour);

			panelbut.add(valid_ajou);
			panelbut.add(valid_modif);
			panelbut.add(valid_supp);
			
		     Font police = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,14);

			     LineBorder border = new LineBorder ( Color.white, 1, true );
			    TitledBorder titl2 = new TitledBorder ( border, "Information Personnelle", TitledBorder.DEFAULT_POSITION,
			            TitledBorder.DEFAULT_POSITION, police2, Color.white);
			    
			    TitledBorder titl = new TitledBorder ( border, "Information Chaine", TitledBorder.DEFAULT_POSITION,
			            TitledBorder.DEFAULT_POSITION, police2, Color.white);
			    
			panelinfp.setBorder(titl2);
			panelinfc.setBorder(titl);

			    mat.setFont(police2);
			    nom.setFont(police2);
			    num_tl.setFont(police2);
			    prenom.setFont(police2);
			    chaine.setFont(police2);
			    zon.setFont(police2);
			    post.setFont(police2);
			    type.setFont(police2);
			    perm.setFont(police);
			    vol.setFont(police);
			    
			    type.setForeground(new Color(255,255,255)); 

			    mat.setForeground(new Color(255,255,255)); 
			    nom.setForeground(new Color(255,255,255)); 
			    prenom.setForeground(new Color(255,255,255)); 
			    chaine.setForeground(new Color(255,255,255)); 
			    num_tl.setForeground(new Color(255,255,255));
			     zon.setForeground(new Color(255,255,255)); 
			     post.setForeground(new Color(255,255,255)); 
			     perm.setForeground(new Color(255,255,255)); 
			     vol.setForeground(new Color(255,255,255)); 

		   mat.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));
   	       mat1.setPreferredSize(new Dimension(210, 30));
   	  
   	       nom1.setPreferredSize(new Dimension(210, 30));
   	       prenom1.setPreferredSize(new Dimension(210, 30));
   	       num_tel1.setPreferredSize(new Dimension(210, 30));
   	       num_chaine.setPreferredSize(new Dimension(210, 30));
   	       poste.setPreferredSize(new Dimension(210, 30));
   	       zone.setPreferredSize(new Dimension(210, 30));
   	       
   	       panelimg.setPreferredSize(new Dimension(60, 170));
   	       panelform.setPreferredSize(new Dimension(210, 170));

		    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		    panel.setLayout(new GridLayout(1,1));
		    panelpers.setLayout(new BoxLayout(panelpers,BoxLayout.X_AXIS));
		    
		    panelimg.setLayout(null);
		    panelform.setLayout(new BoxLayout(panelform,BoxLayout.Y_AXIS));
		    
		    panelinfp.setLayout(new BoxLayout( panelinfp,BoxLayout.Y_AXIS));
		    panelinfc.setLayout(new BoxLayout( panelinfc,BoxLayout.Y_AXIS));

		    panelnp.setLayout(new BoxLayout(panelnp,BoxLayout.X_AXIS));
		    panelmt.setLayout(new BoxLayout(panelmt,BoxLayout.X_AXIS));
		    panelcz.setLayout(new BoxLayout(panelcz,BoxLayout.X_AXIS));
		    panelpo.setLayout(new BoxLayout(panelpo,BoxLayout.X_AXIS));
		   // panelbut.setLayout(new BoxLayout(panelbut,BoxLayout.X_AXIS));
		    
		    paneln.setLayout(new BoxLayout(paneln,BoxLayout.X_AXIS));
		    panelp.setLayout(new BoxLayout(panelp,BoxLayout.X_AXIS));

		    panelm.setLayout(new BoxLayout(panelm,BoxLayout.X_AXIS));
		    panelt.setLayout(new BoxLayout(panelt,BoxLayout.X_AXIS));

		    panelc.setLayout(new BoxLayout(panelc,BoxLayout.X_AXIS));
		    panelz.setLayout(new BoxLayout(panelz,BoxLayout.X_AXIS));

		    panelpos.setLayout(new BoxLayout(panelpos,BoxLayout.X_AXIS));
		    panelty.setLayout(new BoxLayout(panelty,BoxLayout.X_AXIS));

		    
		    panel_lab_m.setLayout( new FlowLayout(  FlowLayout.CENTER));
		    panel_lab_n.setLayout( new FlowLayout(  FlowLayout.CENTER));
		    panel_lab_p.setLayout( new FlowLayout(  FlowLayout.CENTER));
		    panel_lab_t.setLayout( new FlowLayout(  FlowLayout.CENTER));
		    panel_lab_c.setLayout( new FlowLayout(  FlowLayout.CENTER));
		    panel_lab_z.setLayout( new FlowLayout(  FlowLayout.CENTER));
		    panel_lab_pos.setLayout( new FlowLayout(  FlowLayout.CENTER));
		    panel_lab_ty.setLayout( new FlowLayout(  FlowLayout.CENTER));
		    
		    panel_jtext_m.setLayout( new FlowLayout(  FlowLayout.LEFT));
		    panel_jtext_p.setLayout( new FlowLayout(  FlowLayout.LEFT));
		    panel_jtext_n.setLayout( new FlowLayout(  FlowLayout.LEFT));
		    panel_jtext_t.setLayout( new FlowLayout(  FlowLayout.LEFT));
		    panel_jtext_c.setLayout( new FlowLayout(  FlowLayout.LEFT));
		    panel_jtext_z.setLayout( new FlowLayout(  FlowLayout.LEFT));
		    panel_jtext_pos.setLayout( new FlowLayout(  FlowLayout.LEFT));

		    panel_jtext_ty.setLayout( new FlowLayout(  FlowLayout.LEFT));

		    
//		    panelc.setLayout( new FlowLayout(  FlowLayout.LEFT));
//		    panelz.setLayout( new FlowLayout (  FlowLayout.CENTER));
//		    panelpos.setLayout( new FlowLayout (  FlowLayout.LEFT));
//		    panelty.setLayout( new FlowLayout (  FlowLayout.CENTER));

//		    panelmt.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 20));
//		    panelnp.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 20));
		 //   panelbut.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
		     
		       ajout.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		       modif.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		       retour.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		       supp.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		       valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		       valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		       valid_supp.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		       
		       
		       
		       ajout.setPreferredSize(new Dimension(130, 30));
		       modif.setPreferredSize(new Dimension(130, 33));
		       supp.setPreferredSize(new Dimension(130, 33));
		     
		       retour.setPreferredSize(new Dimension(130, 33));
		       valid_supp.setPreferredSize(new Dimension(130, 33));
		       valid_modif.setPreferredSize(new Dimension(130, 33));
		       valid_ajou.setPreferredSize(new Dimension(130, 33));
//		    int haut = 0;
//		    int bas = 0;
//		    int droit = 200;
//		    int gauche = 200;
//
//		    Insets marges = new Insets(haut,gauche,bas,droit);
//
//		    modif.setMargin( marges);

		    panelmt.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
		    panelnp.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
		    
		    panelcz.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
		    panelpo.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
		    
		    panel_lab_c.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,20));
		    
		    panel_lab_n.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,50));
		    panel_lab_p.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 32));


		    panel_lab_pos.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 12));
		    
		    panel_lab_z.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 30));
		    panel_lab_ty.setBorder(BorderFactory.createEmptyBorder(0, -20, 0, 20));
              
		       panelpers.setOpaque(false);
		       panel_lab_m.setOpaque(false);
		       panel_lab_t.setOpaque(false);
		       panel_lab_n.setOpaque(false);
		       panel_lab_p.setOpaque(false);
		       panel_lab_c.setOpaque(false);
		       panel_lab_z.setOpaque(false);
		       panel_lab_pos.setOpaque(false);
		       panel_lab_ty.setOpaque(false);

		       panel_jtext_m.setOpaque(false);
		       panel_jtext_t.setOpaque(false);
		       panel_jtext_n.setOpaque(false);
		       panel_jtext_p.setOpaque(false);
		       panel_jtext_c.setOpaque(false);
		       panel_jtext_z.setOpaque(false);
		       panel_jtext_pos.setOpaque(false);
		       panel_jtext_ty.setOpaque(false);

		    panelinfp.setOpaque(false);
		    panelinfc.setOpaque(false);
		    panelbut.setOpaque(false);

		    panelform.setOpaque(false);
		    panelnp.setOpaque(false);
		    panelmt.setOpaque(false);
		    paneln.setOpaque(false);
		    panelm.setOpaque(false);
		    panelp.setOpaque(false);
		    panelt.setOpaque(false);
		    
		    panelcz.setOpaque(false);
		    panelc.setOpaque(false);
		    panelz.setOpaque(false);

		    panelpo.setOpaque(false);
		    panelpos.setOpaque(false);
		    panelty.setOpaque(false);

		    
		    panelimg.setOpaque(false);
		    
		      
		    setTitle("Personelle" );
	        setSize(1000, 600);
		    setLocationRelativeTo(null);          
		    setVisible(true);
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setContentPane(panel);

	  }
	  
	  
	 public void matricule(){
		 mat1.addKeyListener(new KeyAdapter() {
		      @SuppressWarnings("deprecation")
			public void keyReleased(KeyEvent e) {
		    	
				  if(mat1.getText().trim().length()==5){
		            if (perm.isSelected()){ s="Permanant";}   
		          	else{ s="Volant";} 
		           	perso.selection(mat1.getText());
		           	
		           	 if(mat1.getText().equals(perso.Matricule)){
		           	 LineBorder border = new LineBorder ( Color.white, 1, true );
		           	 TitledBorder titl2 = new TitledBorder ( border, "Edition", TitledBorder.DEFAULT_POSITION,
		           	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
		           	 panelpers.setBorder(titl2);
		           	 JOptionPane.showMessageDialog(null,"Cette personne existe d�j�."); 
		           	 ajout.setVisible(false);
		    		 modif.setVisible(true);
		    		 supp.setVisible(true);
		    		 retour.setVisible(false);
		    		 valid_ajou.setVisible(false);
		    		 valid_modif.setVisible(false);
		    		 valid_supp.setVisible(false);
		    		 //mat1.disable();
	           		 nom1.setText(perso.nom);
	           	//	 nom1.disable();
	           		 prenom1.setText(perso.prenom);
	           		// prenom1.disable();
	           		 num_tel1.setText(perso.num_tel);
	           		// num_tel1.disable();
	           		  zone.setSelectedItem(perso.zone+" "+perso.des);
	           		  poste.setSelectedItem(perso.poste+" "+perso.intitule);
	           		  num_chaine.setSelectedItem(perso.N_chaine+" "+perso.des_chaine);
	           		  zone.enable();
	           		  poste.enable();
	           		prenom1.enable();
	           		  nom1.enable();
	           		num_chaine.enable();
	           		num_tel1.enable();
	           		 //zone.disable();poste.disable();num_chaine.disable();
	           		 if(perso.type.equals("Permanant")){
	           			 perm.setSelected(true);
	           		 }
	           		 else{ perm.setSelected(false);}
	           		
	           		 if(perso.type.equals("Volant")){
	           			 vol.setSelected(true);
	           		 }
	           		 else{ vol.setSelected(false);}
		           		
		           		 }

		             else {
		            	 LineBorder border = new LineBorder ( Color.white, 1, true );
		           		 TitledBorder titl2 = new TitledBorder ( border, "Edition", TitledBorder.DEFAULT_POSITION,
		           	     TitledBorder.DEFAULT_POSITION, police2, Color.white);
		           	      panelpers.setBorder(titl2);
		            	 ajout.setVisible(true);
			    		 modif.setVisible(false);
			    		 supp.setVisible(false);
			    		 retour.setVisible(false);
			    		 valid_ajou.setVisible(false);
			    		 valid_modif.setVisible(false);
			    		 valid_supp.setVisible(false);
			    		 mat1.enable();
		           		 nom1.enable();
		           	     prenom1.enable();
		           	     num_tel1.enable();
		           		 zone.setSelectedIndex(0);
		           	   zone.enable();
		           		 poste.setSelectedIndex(0);
		           		poste.enable();
		           		 num_chaine.setSelectedIndex(0);
		           		 num_chaine.enable();
		           		perm.enable();
		           		 vol.enable();
		           		 nom1.setText("");
		            	 prenom1.setText("");
		            	 num_tel1.setText("");
		           		 }
		         				  }}});}
	 
	  public boolean isValid(String chaine) {
			try {
				Integer.parseInt(chaine);
			} catch (NumberFormatException e){
				return false;
			}

			return true;
		}
	  
	  public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
					personelle frame = new personelle("7");
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}

