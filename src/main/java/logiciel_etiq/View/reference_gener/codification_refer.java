package logiciel_etiq.View.reference_gener;

import logiciel_etiq.Controller.gestion_reference_codegener;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;




public class codification_refer extends generale {
	private String msg = "";

	/*************** definition des panneau**************/
	private JPanel pan_form = new JPanel();

	private JPanel pan_combo = new JPanel();
	private JPanel pan_lab_reference = new JPanel();
	private JPanel pan_combo_reference = new JPanel();
	private JLabel lab_combo = new JLabel("Liste des réfèrences ");
	private jcombo reference_comb = new jcombo(list_comb);


	private JPanel pan_reference = new JPanel();
	private JPanel pan_lab_codereference = new JPanel();
	private JPanel pan_jtext_codereference = new JPanel();
	private JLabel reference_lab = new JLabel("Code réfèrences");
	private JTextField code_reference=new JTextField();

	private JPanel pan_designation = new JPanel();
	private JPanel pan_lab_designation = new JPanel();
	private JPanel pan_jtext_designation = new JPanel();
	private JLabel designation_lab = new JLabel("Désignation");
	private JTextField designation = new JTextField();


	private JPanel pan_button = new JPanel();
	private static gestion_reference_codegener ref_code = new gestion_reference_codegener();


	public codification_refer(final String log) {


		composant(log);
	}


	private void composant(String log) {

		JPanel panel = generalPanle();

		declarationButton(pan_button);
		visibiliteButton(true, false, false, false,
				false, false, false);
		initFrame("Edition Réfèrence", pan_form, this, log, "Gestion des réfèrences");



		replirRefArticle(reference_comb);


		retour.addActionListener(
				e -> {
					titleFrame("Edition Réfèrence", pan_form);
					reference_comb.setSelectedIndex(0);
					visibiliteButton(true, false, false, false,
							false, false, false);

				});
		///////////////////////////////////supprimerrrrrrrr//////////////////////


		Supprimer.addActionListener(
				e -> {
					titleFrame("Supprimer une réfèrence", pan_form);
					visibiliteButton(false, false, false, true,
							false, false, true);
				});

		valid_supp.addActionListener(
				e -> {
					msg = "";
					if (reference_comb.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Vous devez sélectionner une réfèrence \n");
				 	} else {
						ref_code.delete_reference(code_reference.getText(), designation.getText());
						replirRefArticle(reference_comb);

					}
				});


		///////////////////////////////////modifier//////////////////////

		modifier.addActionListener(
				e -> {
					titleFrame("Modifier une réfèrence", pan_form);
					visibiliteButton(false, false, false, true,
							false, true, false);
				});

		valid_modif.addActionListener(
				e -> {
					msg = "";
					if (reference_comb.getSelectedIndex() == 0) {
						msg += "Vous devez sélectionner une réfèrence \n";
					} else if (designation.getText().equals("")) {
						msg += "Veuillez remplir la designation \n";
					} else if (code_reference.getText().equals("")) {
						msg += "Veuillez remplir le code réfèrence \n";
					}
					if (!msg.equals("")) {

						JOptionPane.showMessageDialog(null, msg);
					} else {
						ref_code.setupdate_reference(code_reference.getText(), designation.getText());
						replirRefArticle(reference_comb);
					}
				});


		///////////////////////////////////ajouter//////////////////////

		ajouter.addActionListener(
				e -> {
					titleFrame("Ajouter une réfèrence", pan_form);
					visibiliteButton(false, false, false, true,
							true, false, false);
				});

		valid_ajou.addActionListener(
				e -> {
					msg = "";

					if (designation.getText().equals("")) {
						msg += "Veuillez remplir la désignation \n";
					}

					if (code_reference.getText().equals("")) {
						msg += "Veuillez remplir la réfèrence \n";
					}


					if (!msg.equals("")) {
						JOptionPane.showMessageDialog(null, msg);

					} else {

						ref_code.ajouter_reference(code_reference.getText(), designation.getText());
						replirRefArticle(reference_comb);

					}
				});


		reference_comb.addActionListener(
				e -> {
					titleFrame("Edition Réfèrences", pan_form);
					actionComboDeuxChamp(reference_comb, code_reference,  designation,1);
				});

		action(code_reference);


		//******************** add component in panel***********/
		panel.add(pan_form);

		pan_form.setOpaque(false);

		pan_form.add(pan_combo);
		pan_form.add(pan_reference);
		pan_form.add(pan_designation);
		pan_form.add(pan_button);

		pan_combo.add(pan_lab_reference);
		pan_combo.add(pan_combo_reference);


		pan_lab_reference.add(lab_combo);
		pan_combo_reference.add(reference_comb);

		pan_reference.add(pan_lab_codereference);
		pan_reference.add(pan_jtext_codereference);

		pan_lab_codereference.add(reference_lab);
		pan_jtext_codereference.add(code_reference);

		pan_designation.add(pan_lab_designation);
		pan_designation.add(pan_jtext_designation);

		pan_lab_designation.add(designation_lab);
		pan_jtext_designation.add(designation);


		//*********** background de panell*****/
		pan_button.setOpaque(false);
		pan_reference.setOpaque(false);
		pan_designation.setOpaque(false);
		pan_lab_reference.setOpaque(false);
		pan_combo_reference.setOpaque(false);
		pan_lab_codereference.setOpaque(false);
		pan_jtext_codereference.setOpaque(false);
		pan_lab_designation.setOpaque(false);
		pan_jtext_designation.setOpaque(false);
		pan_combo.setOpaque(false);


		//*********** style des composnat*****/

		styleComponent(reference_comb);
		styleComponent(designation);
		styleComponent(code_reference);


		//*********** style des clabels*****/

		styleLabel(reference_lab);
		styleLabel(designation_lab);
		styleLabel(lab_combo);


		//**************border de composant ***********************/
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pan_button.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

		pan_lab_reference.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_combo_reference.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));


		pan_lab_codereference.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_jtext_codereference.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 0));


		pan_lab_designation.setBorder(BorderFactory.createEmptyBorder(20, 35, 20, 0));
		pan_jtext_designation.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 0));


		//*************layout ****************/
		pan_lab_reference.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_combo_reference.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_lab_codereference.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_jtext_codereference.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_lab_designation.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_jtext_designation.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_button.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));
		panel.setLayout(new GridLayout(1, 1));
		pan_combo.setLayout(new BoxLayout(pan_combo, BoxLayout.X_AXIS));
		pan_reference.setLayout(new BoxLayout(pan_reference, BoxLayout.X_AXIS));
		pan_designation.setLayout(new BoxLayout(pan_designation, BoxLayout.X_AXIS));
		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));


		setContentPane(panel);

	}


	private void action(JTextField comp) {
		comp.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				actioGeneral();
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		comp.addActionListener(
				(ActionEvent e) -> actioGeneral());



	}



	private void actioGeneral(){
		titleFrame("Edition Réfèrence", pan_form);

       boolean exist = ref_code.reference(designation.getText(), code_reference.getText());

		if(!exist){

			visibiliteButton(true, false, false, false,
					false, false,false);

		}
		else  {
			visibiliteButton(false, true, true, false,
					false, false,false);

			code_reference.setText(ref_code.code_ref);
			designation.setText(ref_code.des);
			reference_comb.setSelectedItem(ref_code.code_ref+" "+ref_code.des);
		}
	}


}


