package logiciel_etiq.View.reference_gener;

import logiciel_etiq.Controller.gestion_reference_codegener;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;


public class codification_gener extends generale {
	private String msg = "";

	//************* definition des panneau**************/
	private JPanel pan_form = new JPanel();

	private JPanel pan_combo = new JPanel();
	private JPanel pan_lab_famille = new JPanel();
	private JPanel pan_combo_famille = new JPanel();
	private JLabel lab_combo = new JLabel("Liste de famille ");
	private jcombo famille_comb = new jcombo(list_comb);


	private JPanel pan_famille = new JPanel();
	private JPanel pan_lab_codefamille = new JPanel();
	private JPanel pan_jtext_codefamille = new JPanel();
	private JLabel famille_lab = new JLabel("Code Famille");
	private JTextField code_famille;

	private JPanel pan_designation = new JPanel();
	private JPanel pan_lab_designation = new JPanel();
	private JPanel pan_jtext_designation = new JPanel();
	private JLabel designation_lab = new JLabel("Désignation");
	private JTextField designation = new JTextField();


	private JPanel pan_button = new JPanel();
	private static gestion_reference_codegener ref_code = new gestion_reference_codegener();


	public codification_gener(final String log) {


		composant(log);
	}


	private void composant(String log) {

		JPanel panel = generalPanle();

		declarationButton(pan_button);
		visibiliteButton(true, false, false, false,
				false, false, false);
		initFrame("Edition Famille", pan_form, this, log, "Gestion des familles");

		replirCodeGener(famille_comb);



		MaskFormatter num = null;
		try {
			num = new MaskFormatter("***");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		code_famille = new JFormattedTextField(num);



		retour.addActionListener(
				e -> {
					titleFrame("Edition Famille", pan_form);
					famille_comb.setSelectedIndex(0);
					visibiliteButton(true, false, false, false,
							false, false, false);

				});
		///////////////////////////////////supprimerrrrrrrr//////////////////////


		Supprimer.addActionListener(
				e -> {
					titleFrame("Supprimer une famille", pan_form);
					visibiliteButton(false, false, false, true,
							false, false, true);
				});

		valid_supp.addActionListener(
				e -> {
					msg = "";
					if (famille_comb.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Vous devez sélectionner une famille \n");
					} else {
						ref_code.delete_famille(code_famille.getText(), designation.getText());
						replirCodeGener(famille_comb);

					}
				});


		///////////////////////////////////modifier//////////////////////

		modifier.addActionListener(
				e -> {
					titleFrame("Modifier une famille", pan_form);

					visibiliteButton(false, false, false, true,
							false, true, false);
				});

		valid_modif.addActionListener(
				e -> {
					msg = "";
					if (famille_comb.getSelectedIndex() == 0) {
						msg += "Vous devez sélectionner une famille \n";
					} else if (designation.getText().equals("")) {
						msg += "Veuillez remplir la designation \n";
					} else if (code_famille.getText().equals("")) {
						msg += "Veuillez remplir le code famille \n";
					}
					if (!msg.equals("")) {

						JOptionPane.showMessageDialog(null, msg);
					} else {
						ref_code.setupdate_famille(code_famille.getText(), designation.getText());
						replirCodeGener(famille_comb);
					}
				});


		///////////////////////////////////ajouter//////////////////////

		ajouter.addActionListener(
				e -> {
					titleFrame("Ajouter une famille", pan_form);
					visibiliteButton(false, false, false, true,
							true, false, false);
				});

		valid_ajou.addActionListener(
				e -> {
					msg = "";

					if (designation.getText().equals("")) {
						msg += "Veuillez remplir la désignation \n";
					}

					if (code_famille.getText().equals("")) {
						msg += "Veuillez remplir le code famille \n";
					}


					if (!msg.equals("")) {
						JOptionPane.showMessageDialog(null, msg);

					} else {

						ref_code.ajouter_famille(code_famille.getText(), designation.getText());
						replirCodeGener(famille_comb);

					}
				});



        famille_comb.addActionListener(
                e -> {
                    titleFrame("Edition Famille", pan_form);
                    actionComboDeuxChamp(famille_comb, code_famille,  designation,1);

                });


		action(code_famille);


		//******************** add component in panel***********/
		panel.add(pan_form);

		pan_form.setOpaque(false);

		pan_form.add(pan_combo);
		pan_form.add(pan_famille);
		pan_form.add(pan_designation);
		pan_form.add(pan_button);

		pan_combo.add(pan_lab_famille);
		pan_combo.add(pan_combo_famille);


		pan_lab_famille.add(lab_combo);
		pan_combo_famille.add(famille_comb);

		pan_famille.add(pan_lab_codefamille);
		pan_famille.add(pan_jtext_codefamille);

		pan_lab_codefamille.add(famille_lab);
		pan_jtext_codefamille.add(code_famille);

		pan_designation.add(pan_lab_designation);
		pan_designation.add(pan_jtext_designation);

		pan_lab_designation.add(designation_lab);
		pan_jtext_designation.add(designation);


		//*********** background de panell*****/
		pan_button.setOpaque(false);
		pan_famille.setOpaque(false);
		pan_designation.setOpaque(false);
		pan_lab_famille.setOpaque(false);
		pan_combo_famille.setOpaque(false);
		pan_lab_codefamille.setOpaque(false);
		pan_jtext_codefamille.setOpaque(false);
		pan_lab_designation.setOpaque(false);
		pan_jtext_designation.setOpaque(false);
		pan_combo.setOpaque(false);


		//*********** style des composnat*****/

		styleComponent(famille_comb);
		styleComponent(designation);
		styleComponent(code_famille);


		//*********** style des clabels*****/

		styleLabel(famille_lab);
		styleLabel(designation_lab);
		styleLabel(lab_combo);


		//**************border de composant ***********************/
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pan_button.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

		pan_lab_famille.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_combo_famille.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));


		pan_lab_codefamille.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_jtext_codefamille.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 0));


		pan_lab_designation.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_jtext_designation.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 0));


		//*************layout ****************/
		pan_lab_famille.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_combo_famille.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_lab_codefamille.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_jtext_codefamille.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_lab_designation.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_jtext_designation.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_button.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));
		panel.setLayout(new GridLayout(1, 1));
		pan_combo.setLayout(new BoxLayout(pan_combo, BoxLayout.X_AXIS));
		pan_famille.setLayout(new BoxLayout(pan_famille, BoxLayout.X_AXIS));
		pan_designation.setLayout(new BoxLayout(pan_designation, BoxLayout.X_AXIS));
		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));


		setContentPane(panel);

	}


	private void action(JTextField comp) {
		comp.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
			    actioGeneral();
			}
			public void focusGained(FocusEvent arg0) {
			}
		});

        comp.addActionListener(
                e -> actioGeneral());
    }



	private void actioGeneral(){
        titleFrame("Edition Famille", pan_form);

        boolean existe = ref_code.famille(designation.getText(), code_famille.getText());

        if(!existe){

            visibiliteButton(true, false, false, false,
                    false, false,false);

        }
        else  {
            visibiliteButton(false, true, true, false,
                    false, false,false);

            code_famille.setText(ref_code.code_gener);
            designation.setText(ref_code.desc);
            famille_comb.setSelectedItem(ref_code.code_gener+" "+ref_code.desc);
        }
    }
}


