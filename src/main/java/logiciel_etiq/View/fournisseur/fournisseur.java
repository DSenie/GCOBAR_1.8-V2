package logiciel_etiq.View.fournisseur;

import logiciel_etiq.Controller.gestion_fournisseur;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;

import javax.swing.*;
import java.awt.*;




public class fournisseur extends generale {
	private String msg="";

	/*************** definition des panneau**************/
	private JPanel pan_form=new JPanel();

	private JPanel pan_combo=new JPanel();
	private JPanel pan_lab_fournisseur=new JPanel();
	private JPanel pan_combo_fournisseur=new JPanel();
	private JLabel lab_combo=new JLabel("Liste de Fournisseur");
	private jcombo fournisseur_comb=new jcombo(list_comb);


	private JPanel pan_fournisseur=new JPanel();
	private JPanel pan_lab_codefournisseur=new JPanel();
	private JPanel pan_jtext_codefournisseur=new JPanel();
	private JLabel fournisseur_lab=new JLabel("Code Fournisseur");
	private	JTextField code_fournisseur=new JTextField();

	private	JPanel pan_designation=new JPanel();
	private	JPanel pan_lab_designation	=new JPanel();
	private	JPanel pan_jtext_designation=new JPanel();
	private JLabel designation_lab=new JLabel("Nom Fournisseur");
	private JTextField designation=new JTextField();



	private	JPanel pan_button=new JPanel();
	private static gestion_fournisseur fournisseur=new gestion_fournisseur();


	public fournisseur(final String log){


		composant(log);
	}



	private void composant(String log){

		JPanel panel = generalPanle();

		declarationButton(pan_button);
		visibiliteButton(true, false, false, false,
				false, false,false);
		initFrame("Edition Fournisseur", pan_form, this, log,"Gestion des fournisseurs");



		replirFournisseur(fournisseur_comb);
		code_fournisseur.disable();
		code_fournisseur.setText(fournisseur.afficher_code_fourniss());





		retour.addActionListener(
				e -> {
					titleFrame("Edition Fournisseur", pan_form);
					fournisseur_comb.setSelectedIndex(0);
					visibiliteButton(true, false, false, false,
							false, false,false);

				});
		///////////////////////////////////supprimerrrrrrrr//////////////////////


		Supprimer.addActionListener(
				e -> {
					titleFrame("Supprimer un Fournisseur", pan_form);
					visibiliteButton(false, false, false, true,
								false, false,true);

				});

		valid_supp.addActionListener(
				e -> {
					msg="";
					if(fournisseur_comb.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null,"Vous devez sélectionner un fournisseur \n");
					}

					else{
						fournisseur.delete_fournisseur(code_fournisseur.getText(),designation.getText());
						replirFournisseur(fournisseur_comb);

					}
				});



		///////////////////////////////////modifier//////////////////////

		modifier.addActionListener(
				e -> {
					titleFrame("Modifier un Fournisseur", pan_form);
					visibiliteButton(false, false, false, true,
							false, true,false);
				});

		valid_modif.addActionListener(
				e -> {
					msg="";
					if(fournisseur_comb.getSelectedIndex()==0) {
						msg += "Vous devez sélectionner un fournisseur \n";
					}
					else if(designation.getText().equals("")) {
						msg+="Veuillez remplir le nom de fournisseur \n";
					}

					if(!msg.equals("")){

						JOptionPane.showMessageDialog(null,msg);
					}
					else{
					    fournisseur.setupdate_fourniss(code_fournisseur.getText(),designation.getText());
					    replirFournisseur(fournisseur_comb);


					}
				});


		///////////////////////////////////ajouter//////////////////////

		ajouter.addActionListener(
				e -> {
			        titleFrame("Ajouter un fournisseur", pan_form);
						visibiliteButton(false, false, false, true,
								true, false,false);
				});

		valid_ajou.addActionListener(
				e -> {
					msg="";

					if(designation.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"Veuillez remplir le nom de fournisseur \n");
					}
					else{

						fournisseur.ajouter_fourniss(code_fournisseur.getText(), designation.getText());
						designation.setText("");
						replirFournisseur(fournisseur_comb);
					}
				});




	       fournisseur_comb.addActionListener(
				   e -> {
				   	  titleFrame("Edition Fournisseur", pan_form);
					   code_fournisseur.setText(fournisseur.afficher_code_fourniss());
					   actionComboDeuxChamp(fournisseur_comb, code_fournisseur,  designation,2);

				   });



		designation.addActionListener(
				e -> {

					fournisseur_comb.enable();
					titleFrame("Edition Fournisseur", pan_form);
					boolean existe=fournisseur.select_fournisseur_jtext(designation.getText());

					if(!existe){
						visibiliteButton(true, false, false, false,
								false, false,false);
						code_fournisseur.setText(fournisseur.afficher_code_fourniss());
					}
					else  {

						visibiliteButton(false, true, true, false,
								false, false,false);

						code_fournisseur.setText(fournisseur.code);
						designation.setText(fournisseur.nom);
						fournisseur_comb.setSelectedItem(fournisseur.code+" "+fournisseur.nom);
					}

				});





		//******************** add component in panel***********/
		panel.add(pan_form);

		pan_form.setOpaque(false);

		pan_form.add(pan_combo);
		pan_form.add(pan_fournisseur);
		pan_form.add(pan_designation);
		pan_form.add(pan_button);

		pan_combo.add(pan_lab_fournisseur);
		pan_combo.add(pan_combo_fournisseur);


		pan_lab_fournisseur.add(lab_combo);
		pan_combo_fournisseur.add(fournisseur_comb);

		pan_fournisseur.add(pan_lab_codefournisseur);
		pan_fournisseur.add(pan_jtext_codefournisseur);

		pan_lab_codefournisseur.add(fournisseur_lab);
		pan_jtext_codefournisseur.add(code_fournisseur);

		pan_designation.add(pan_lab_designation);
		pan_designation.add(pan_jtext_designation);

		pan_lab_designation.add(designation_lab);
		pan_jtext_designation.add(designation);



       //*********** background de panell*****/
		pan_button.setOpaque(false);
		pan_fournisseur.setOpaque(false);
		pan_designation.setOpaque(false);
		pan_lab_fournisseur.setOpaque(false);
		pan_combo_fournisseur.setOpaque(false);
		pan_lab_codefournisseur.setOpaque(false);
		pan_jtext_codefournisseur.setOpaque(false);
		pan_lab_designation.setOpaque(false);
		pan_jtext_designation.setOpaque(false);
		pan_combo.setOpaque(false);


		//*********** style des composnat*****/

		styleComponent(fournisseur_comb);
		styleComponent(designation);
		styleComponent(code_fournisseur);




		//*********** style des clabels*****/

		styleLabel(fournisseur_lab);
		styleLabel(designation_lab);
		styleLabel(lab_combo);




		//**************border de composant ***********************/
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pan_button.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

		pan_lab_fournisseur.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_combo_fournisseur.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));


		pan_lab_codefournisseur.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_jtext_codefournisseur.setBorder(BorderFactory.createEmptyBorder(20,80 , 20, 0));


		pan_lab_designation.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_jtext_designation.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 0));




		//*************layout ****************/
		pan_lab_fournisseur.setLayout( new FlowLayout( FlowLayout.RIGHT ));
		pan_combo_fournisseur.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_lab_codefournisseur.setLayout( new FlowLayout( FlowLayout.RIGHT ));
		pan_jtext_codefournisseur.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_lab_designation.setLayout( new FlowLayout( FlowLayout.RIGHT ));
		pan_jtext_designation.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_button.setLayout( new FlowLayout( FlowLayout.CENTER ));
		pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
		panel.setLayout(new GridLayout(1, 1));
		pan_combo.setLayout(new BoxLayout(pan_combo,BoxLayout.X_AXIS));
		pan_fournisseur.setLayout(new BoxLayout(pan_fournisseur,BoxLayout.X_AXIS));
		pan_designation.setLayout(new BoxLayout(pan_designation,BoxLayout.X_AXIS));
		pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));


		setContentPane(panel);




	}


	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new fournisseur("7");
				//frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
