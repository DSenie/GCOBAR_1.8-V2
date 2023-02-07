package logiciel_etiq.View.user;

import logiciel_etiq.Controller.gestion_user;
import logiciel_etiq.View.generale;

import javax.swing.*;

import javax.swing.text.MaskFormatter;
import java.awt.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Objects;



public class connection_user extends generale {



	/**************matricule******************/
	private JPanel panelmt= new JPanel();
	 private JPanel panelm= new JPanel();
	 private JPanel panel_lab_m= new JPanel();
	 private JPanel  panel_jtext_m= new JPanel();
	 private JLabel mat = new JLabel("Matricule");
	 private JTextField mat1;

	 /**************Nom et prenom******************/
	 private JPanel panelnp= new JPanel();

	 private JPanel paneln= new JPanel();
	 private JPanel panel_lab_n= new JPanel();
	 private JPanel panel_jtext_n= new JPanel();
	 private JLabel nom = new JLabel("Nom");
	 private JTextField nom1= new  JTextField();

	 private JPanel panelp= new JPanel();
	 private JPanel panel_lab_p= new JPanel();
	 private JPanel  panel_jtext_p= new JPanel();
	 private  JLabel prenom = new JLabel("Prénom");
	 private JTextField prenom1= new  JTextField();



	 /**************Login and password******************/
	 private JPanel paneluser=new JPanel();


	 private JPanel paneluse= new JPanel();
	 private JPanel panel_lab_use= new JPanel();
	 private JPanel panel_jtext_use= new JPanel();
	 private JLabel login = new JLabel("User");
	 private JTextField login1= new  JTextField();

	 private JLabel pass22 = new JLabel("Password");
	 private JPasswordField pass1 = new JPasswordField();
	 private JPanel panelpass= new JPanel();
	 private JPanel panel_lab_pass= new JPanel();
	 private JPanel  panel_jtext_pass= new JPanel();

	 /**************Panel General******************/
	 private JPanel panelform= new JPanel();
	 private JPanel panelinfp= new JPanel();
	 private JPanel panelimg= new JPanel();
	 private JPanel panelinfc= new JPanel();
	 private JPanel panelbut= new JPanel();



	 /**************instance class user ******************/
	 private gestion_user use= new  gestion_user();

	 private String msg="";



	 public static void main(String[] args) {
			EventQueue.invokeLater(() -> {
				try {
					connection_user frame = new connection_user("4");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	  
	
	  @SuppressWarnings("deprecation")
	  public connection_user(final String log){


		  declarationButton(panelbut);

		  initFrame("Edition Utilisateur", paneluser, this, log,"Gestion des Utilisateurs");


		  MaskFormatter num = null;
          try {
               num = new MaskFormatter("#####");
              } 
          catch (ParseException e) {
                                    e.printStackTrace();
                                   }
          mat1= new  JFormattedTextField(num);

		  visibiliteButton(true, false, false, false,
				  false, false,false);

	  	  nom1.enable();
		  mat1.enable();
		  prenom1.enable();
		  login1.enable();
		  pass1.enable();


		  initComponents();

		   matricule();

		retour.addActionListener(
				e -> {
					titleFrame("Edition Utilisateur", paneluser);
					mat1.enable();
					  nom1.enable();
					   prenom1.enable();
					   login1.enable();
					   pass1.enable();
					   nom1.setText("");
					   mat1.setText("");
					prenom1.setText("");
					login1.setText("");
					   pass1.setText("");
					visibiliteButton(true, false, false, false,
							false, false,false);
				});


		  modifier.addActionListener(
				 e -> {
					 titleFrame("Modifier Utilisateur", paneluser);
					 visibiliteButton(false, false, false, true,
							 false, true,false);
					   nom1.enable();
					   mat1.enable();
					   prenom1.enable();
					   login1.enable();
					   pass1.enable();
				 });



		  valid_modif.addActionListener(
				e -> {
					msg = validChamp();

					use.selection(mat1.getText());
					 if (msg.equals("")) {
						use.setupdate(mat1.getText(), nom1.getText(),
								prenom1.getText(),
								login1.getText(),
								pass1.getText());

					} else {
						JOptionPane.showMessageDialog(null, msg);
					}
				});

		   //supprimer 
		  Supprimer.addActionListener(
				  e -> {

					  titleFrame("Supprimer Utilisateur", paneluser);

					  visibiliteButton(false, false, false, true,
							  false, false,true);
						nom1.disable();
					  mat1.enable();
					  prenom1.disable();
					  login1.disable();
					  pass1.disable();
				  });

		  valid_supp.addActionListener(
				  e -> {
					  if(mat1.getText().trim().length() <5){
					JOptionPane.showMessageDialog(null,"Vous devez remplir le matricule");
					  }
					  else if(nom1.getText().equals("")){
						  JOptionPane.showMessageDialog(null,"Cette personne n'existe pas");
					   }
					  else{
					  use.setdelete(mat1.getText(),nom1.getText(),prenom1.getText());



					  }
				  });



		 //ajouter
		  ajouter.addActionListener(
				  e -> {
					  titleFrame("Ajouter Utilisateur", paneluser);


					  visibiliteButton(false, false, false, true,
							  true, false,false);
						nom1.enable();
						mat1.enable();
						prenom1.enable();
						login1.enable();
						pass1.enable();
				  });


		  valid_ajou.addActionListener(
				  e -> {
					msg=validChamp();
				 if(msg.equals("")){

					  use.setinsert(mat1.getText(),nom1.getText(),
																 prenom1.getText(),
																 login1.getText(),
																 pass1.getText()
																  );

															 }
							else{JOptionPane.showMessageDialog(null,msg);}
				  });
	}
	  
private void initComponents(){

	JPanel panel = generalPanle();




	styleLabel(mat);
	styleLabel(nom);
	styleLabel(prenom);
	styleLabel(pass22);
	styleLabel(login);



	mat1.setPreferredSize(new Dimension(210, 30));
	nom1.setPreferredSize(new Dimension(210, 30));
	prenom1.setPreferredSize(new Dimension(210, 30));
	login1.setPreferredSize(new Dimension(210, 30));
	pass1.setPreferredSize(new Dimension(210, 30));


	titleFrame("Information Personnele", panelinfp);
	titleFrame("Connection", panelinfc);


	JLabel image = new JLabel( new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("enrg.png"))));
	image.setBounds(10, 23, 150, 190);
	image.setBorder(BorderFactory.createLineBorder(Color.black,2));


	//*****************add component in panels*****************/

        panel.add(paneluser);
    	paneluser.setOpaque(false);


	    paneluser.add(panelimg);
	    panelimg.add(image);
	    panelimg.setOpaque(false);

	    paneluser.add(panelform);
	    panelform.setOpaque(false);

	    panelform.add(panelinfp);
	    panelinfp.setOpaque(false);

	    panelform.add(panelinfc);
	    panelinfc.setOpaque(false);

	    panelform.add(panelbut);
     	panelbut.setOpaque(false);

        panelinfp.add(panelmt);
	    panelmt.add(panelm);

     	panelmt.setOpaque(false);

	    panelm.add(panel_lab_m);
        panel_lab_m.add(mat);
        panelm.add(panel_jtext_m);
        panel_jtext_m.add(mat1);

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

	  	paneluse.add(panel_lab_use);
	  	panel_lab_use.add(login);
	  	paneluse.add(panel_jtext_use);
	    panel_jtext_use.add(login1);

	  	panelinfc.add(panelpass);
	  	panelpass.add(panel_lab_pass);
	  	panel_lab_pass.add(pass22);
	    panelpass.add(panel_jtext_pass);
	    panel_jtext_pass.add(pass1);



	//*******************rendre les panneau transparant*****************/

	panel_lab_p.setOpaque(false);
	panel_lab_use.setOpaque(false);
	panel_lab_pass.setOpaque(false);
	panel_jtext_n.setOpaque(false);
	panel_jtext_p.setOpaque(false);
	panel_jtext_pass.setOpaque(false);
	panel_jtext_use.setOpaque(false);

	panel_lab_n.setOpaque(false);

	panel_lab_m.setOpaque(false);

	panel_jtext_m.setOpaque(false);

	panelinfp.setOpaque(false);
	panelbut.setOpaque(false);

	panelnp.setOpaque(false);
	paneln.setOpaque(false);
	panelm.setOpaque(false);
	panelp.setOpaque(false);


	paneluse.setOpaque(false);
	panelpass.setOpaque(false);







	//*******************Border of Panel*****************/

	panelm.setBorder(BorderFactory.createEmptyBorder(30,0, 0, 0));
	panelnp.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
	paneluse.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
	panelpass.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
	panelbut.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
	panel_lab_m.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	panel_lab_n.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,38 ));
	panel_lab_use.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 35));






	//*******************Layout  of Panel*****************/

	    panelimg.setLayout(null);
	    panelform.setLayout(new BoxLayout(panelform,BoxLayout.Y_AXIS));
	    panelinfp.setLayout(new BoxLayout( panelinfp,BoxLayout.Y_AXIS));
	    panelinfc.setLayout(new BoxLayout( panelinfc,BoxLayout.X_AXIS));
	    panelnp.setLayout(new BoxLayout(panelnp,BoxLayout.X_AXIS));
	    panelmt.setLayout(new BoxLayout(panelmt,BoxLayout.X_AXIS));
	    paneln.setLayout(new BoxLayout(paneln,BoxLayout.X_AXIS));
	    panelp.setLayout(new BoxLayout(panelp,BoxLayout.X_AXIS));
	    panelm.setLayout(new BoxLayout(panelm,BoxLayout.X_AXIS));
	    paneluse.setLayout(new BoxLayout(paneluse,BoxLayout.X_AXIS));
	    panelpass.setLayout(new BoxLayout(panelpass,BoxLayout.X_AXIS));


	    panel.setLayout(new GridLayout(1,1));
	    paneluser.setLayout(new BoxLayout(paneluser,BoxLayout.X_AXIS));
	    panel_lab_m.setLayout( new FlowLayout(  FlowLayout.CENTER));
	    panel_lab_n.setLayout( new FlowLayout(  FlowLayout.CENTER));
	    panel_lab_p.setLayout( new FlowLayout(  FlowLayout.CENTER));
	    panel_lab_use.setLayout( new FlowLayout(  FlowLayout.CENTER));
	    panel_lab_pass.setLayout( new FlowLayout(  FlowLayout.CENTER));
	    panel_jtext_m.setLayout( new FlowLayout(  FlowLayout.LEFT));
	    panel_jtext_p.setLayout( new FlowLayout(  FlowLayout.LEFT));
	    panel_jtext_n.setLayout( new FlowLayout(  FlowLayout.LEFT));
	    panel_jtext_use.setLayout( new FlowLayout(  FlowLayout.LEFT));
	    panel_jtext_pass.setLayout( new FlowLayout(  FlowLayout.LEFT));
	    panelimg.setPreferredSize(new Dimension(60, 700));
	    panelform.setPreferredSize(new Dimension(300, 600));
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));


        setContentPane(panel);

            }

private void matricule(){
	 mat1.addKeyListener(new KeyAdapter() {
	      @SuppressWarnings("deprecation")
		public void keyReleased(KeyEvent e) {
			  if(mat1.getText().trim().length()==5){
				 
		           	use.selection(mat1.getText());
		           	 if(mat1.getText().equals(use.matricule)){

						 titleFrame("Modifier Utilisateur", paneluser);
						 visibiliteButton(false, true, true, false,
								 false, false,false);

		             	 JOptionPane.showMessageDialog(null,"Cette personne existe déja.");

		    		 mat1.enable();
              	     nom1.enable();
	           	     prenom1.enable();
	           	     login1.enable();
	           		 pass1.enable();


	           		 nom1.setText(use.nom);
	           		 prenom1.setText(use.prenom);
	           		 login1.setText(use.login);
	           		 pass1.setText(use.pass);
		           	 }
		             else {
						 titleFrame("Ajouter Utilisateur", paneluser);

						 visibiliteButton(true, false, false, false,
								 false, false,false);

			    		 mat1.enable();
		           		 nom1.enable();
		           	     prenom1.enable();
		           	     pass1.enable();
		           	     login1.enable();
		           		 nom1.setText("");
		            	 prenom1.setText("");
		            	 login1.setText("");
		            	 pass1.setText("");
		           		 }
			  
			  }  }});}

	    






			  private String validChamp() {
				  if (mat1.getText().trim().length() < 5) {

					  JOptionPane.showMessageDialog(null, "Vous devez remplir le matricule");
				  }
				   else {
					  msg = "";
					  if (nom1.getText().equals("")) {
						  msg += "veuillez saisir le nom \n";
					  } else if (!nom1.getText().matches("[a-zA-Z]*")) {
						  msg += "veuillez vérifier le nom \n";
					  } else if (prenom1.getText().equals("")) {
						  msg += "veuillez saisir le prénom \n";
					  } else if (!prenom1.getText().matches("[a-zA-Z]*")) {
						  msg += "veuillez vérifier le prénom \n";
					  } else if (login1.getText().equals("")) {
						  msg += "veuillez saisir le login \n";
					  } else if (pass1.getText().equals("")) {
						  msg += "veuillez saisir le mot de passe \n";
					  }


				  }


				  return msg;
			  }

}
