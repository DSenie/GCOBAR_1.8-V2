package logiciel_etiq.View.article;

import logiciel_etiq.Controller.gestion_article_famille;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;

import javax.swing.*;
import java.awt.*;




public class famille_article extends generale {
	private String msg="";

	//*************** definition des panneau**************/
	private JPanel pan_form=new JPanel();

	private JPanel pan_combo=new JPanel();
	private JPanel pan_lab_artfamille=new JPanel();
	private JPanel pan_combo_artfamille=new JPanel();
	private JLabel lab_combo=new JLabel("Liste de type article");
	private jcombo artfamille_comb=new jcombo(list_comb);


	private JPanel pan_artfamille=new JPanel();
	private JPanel pan_lab_codeartfamille=new JPanel();
	private JPanel pan_jtext_codeartfamille=new JPanel();
	private JLabel artfamille_lab=new JLabel("Code type article");
	private	JTextField code_artfamille=new JTextField();

	private	JPanel pan_designation=new JPanel();
	private	JPanel pan_lab_designation	=new JPanel();
	private	JPanel pan_jtext_designation=new JPanel();
	private JLabel designation_lab=new JLabel("Désignation");
	private JTextField designation=new JTextField();


	private	JPanel pan_button=new JPanel();
	private   gestion_article_famille artfamille=new gestion_article_famille();


	public famille_article(final String log){
		composant(log);
	}



	private void composant(String log){

		JPanel panel = generalPanle();

		declarationButton(pan_button);
		visibiliteButton(true, false, false, false,
				false, false,false);
		initFrame("Edition type article", pan_form, this, log,"Gestion de type article");

		replirArtFamille(artfamille_comb);

		code_artfamille.disable();
		code_artfamille.setText(artfamille.afficher_code_artfamille());





		retour.addActionListener(
				e -> {
					titleFrame("Edition Type Article", pan_form);
					artfamille_comb.setSelectedIndex(0);
					visibiliteButton(true, false, false, false,
							false, false,false);
				});



		///////////////////////////////////supprimerrrrrrrr//////////////////////


		Supprimer.addActionListener(
				e -> {
					titleFrame("Supprimer un tpe article", pan_form);
					visibiliteButton(false, false, false, true,
							false, false,true);

				});

		valid_supp.addActionListener(
				e -> {
					if(artfamille_comb.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null,"Vous devez sélectionner un type article");
					}
					else{
						artfamille.delete_artfamille(code_artfamille.getText(),designation.getText());
						replirArtFamille(artfamille_comb);

					}
				});



		///////////////////////////////////modifier//////////////////////

		modifier.addActionListener(
				e -> {
					titleFrame("Modifier un type article", pan_form);
					visibiliteButton(false, false, false, true,
							false, true,false);
				});

		valid_modif.addActionListener(
				e -> {
					msg="";
					if(artfamille_comb.getSelectedIndex()==0) {
						msg += "Vous devez sélectionner un type article \n";
					}
					else if(designation.getText().equals("")) {
						msg+="Veuillez remplir la désignation \n";
					}

					if(!msg.equals("")){

						JOptionPane.showMessageDialog(null,msg);
					}
					else{
						artfamille.setupdate_artfamille(code_artfamille.getText(),designation.getText());
						replirArtFamille(artfamille_comb);

					}
				});


		///////////////////////////////////ajouter//////////////////////

		ajouter.addActionListener(
				e -> {
					titleFrame("Ajouter un type article", pan_form);
					visibiliteButton(false, false, false, true,
							true, false,false);
				});

		valid_ajou.addActionListener(
				e -> {
					msg="";

					if(designation.getText().equals("")) {
						msg+="Veuillez remplir la designation \n";}


					if(!msg.equals("")){
						JOptionPane.showMessageDialog(null,msg);

					}
					else{

						artfamille.ajouter_artfamille(code_artfamille.getText(), designation.getText());
						replirArtFamille(artfamille_comb);
					}
				});




		artfamille_comb.addActionListener(
				e -> {
					titleFrame("Edition Type Article", pan_form);
					code_artfamille.setText(artfamille.afficher_code_artfamille());
					actionComboDeuxChamp(artfamille_comb, code_artfamille,  designation,2);
				});





		designation.addActionListener(
				e -> {

					titleFrame("Edition Type Article", pan_form);
					boolean existe=artfamille.type(designation.getText());

					if(!existe){
						visibiliteButton(true, false, false, false,
								false, false,false);
						code_artfamille.setText(artfamille.afficher_code_artfamille());
					}
					else  {

						visibiliteButton(false, true, true, false,
								false, false,false);

						code_artfamille.setText(artfamille.code_type);
						designation.setText(artfamille.des_type);
						artfamille_comb.setSelectedItem(artfamille.code_type+" "+artfamille.des_type);
					}

				});





		//******************** add component in panel***********/
		panel.add(pan_form);

		pan_form.setOpaque(false);

		pan_form.add(pan_combo);
		pan_form.add(pan_artfamille);
		pan_form.add(pan_designation);
		pan_form.add(pan_button);

		pan_combo.add(pan_lab_artfamille);
		pan_combo.add(pan_combo_artfamille);


		pan_lab_artfamille.add(lab_combo);
		pan_combo_artfamille.add(artfamille_comb);

		pan_artfamille.add(pan_lab_codeartfamille);
		pan_artfamille.add(pan_jtext_codeartfamille);

		pan_lab_codeartfamille.add(artfamille_lab);
		pan_jtext_codeartfamille.add(code_artfamille);

		pan_designation.add(pan_lab_designation);
		pan_designation.add(pan_jtext_designation);

		pan_lab_designation.add(designation_lab);
		pan_jtext_designation.add(designation);



		//*********** background de panell*****/
		pan_button.setOpaque(false);
		pan_artfamille.setOpaque(false);
		pan_designation.setOpaque(false);
		pan_lab_artfamille.setOpaque(false);
		pan_combo_artfamille.setOpaque(false);
		pan_lab_codeartfamille.setOpaque(false);
		pan_jtext_codeartfamille.setOpaque(false);
		pan_lab_designation.setOpaque(false);
		pan_jtext_designation.setOpaque(false);
		pan_combo.setOpaque(false);


		//*********** style des composnat*****/

		styleComponent(artfamille_comb);
		styleComponent(designation);
		styleComponent(code_artfamille);




		//*********** style des clabels*****/

		styleLabel(artfamille_lab);
		styleLabel(designation_lab);
		styleLabel(lab_combo);




		//**************border de composant ***********************/
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pan_button.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

		pan_lab_artfamille.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_combo_artfamille.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));


		pan_lab_codeartfamille.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 0));
		pan_jtext_codeartfamille.setBorder(BorderFactory.createEmptyBorder(20,60 , 20, 0));


		pan_lab_designation.setBorder(BorderFactory.createEmptyBorder(20, 47, 20, 0));
		pan_jtext_designation.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 0));




		//*************layout ****************/
		pan_lab_artfamille.setLayout( new FlowLayout( FlowLayout.RIGHT ));
		pan_combo_artfamille.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_lab_codeartfamille.setLayout( new FlowLayout( FlowLayout.RIGHT ));
		pan_jtext_codeartfamille.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_lab_designation.setLayout( new FlowLayout( FlowLayout.RIGHT ));
		pan_jtext_designation.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_button.setLayout( new FlowLayout( FlowLayout.CENTER ));
		pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
		panel.setLayout(new GridLayout(1, 1));
		pan_combo.setLayout(new BoxLayout(pan_combo,BoxLayout.X_AXIS));
		pan_artfamille.setLayout(new BoxLayout(pan_artfamille,BoxLayout.X_AXIS));
		pan_designation.setLayout(new BoxLayout(pan_designation,BoxLayout.X_AXIS));
		pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));


		setContentPane(panel);




	}



}
