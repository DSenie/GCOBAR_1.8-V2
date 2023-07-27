package logiciel_etiq.View.Etiquette.Tablette;

import logiciel_etiq.Controller.gestion_imp_tablette;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import logiciel_etiq.constant;

import javax.swing.*;
import java.awt.*;

import java.util.*;


public class etiquette_tablette extends generale {


	private JPanel pan_model = new JPanel();
	private JPanel pan_model_jtext = new JPanel();
	private JPanel pan_model_lab = new JPanel();
	private JLabel model_lab = new JLabel("Modèle");
	private jcombo model_jtext=new jcombo(list_comb);

	private JPanel pan_color = new JPanel();
	private JPanel pan_color_comb = new JPanel();
	private JPanel pan_color_lab = new JPanel();
	private JLabel color_lab = new JLabel("Liste des Couleur");
	private JComboBox color_comb=new JComboBox();

	private JPanel pan_sn_lab = new JPanel();
	private JPanel pan_sn = new JPanel();
	private JPanel pan_sn_jtext = new JPanel();
	private JLabel sn_lab = new JLabel("SN");
	private JTextField sn_jtext= new  JTextField();


     String snaimp;

	private JPanel pan_nvsn_lab = new JPanel();
	private JPanel pan_nvsn = new JPanel();
	private JPanel pan_nvsn_jtext = new JPanel();
	private JLabel nvsn_lab = new JLabel("Nouveau SN");
	private JTextField nvsn_jtext= new  JTextField();


	private JPanel pan_licence_lab = new JPanel();
	private JPanel pan_licence = new JPanel();
	private JPanel pan_licence_jtext = new JPanel();
	private JLabel licence_lab = new JLabel("licence");
	private JTextField licence_jtext= new  JTextField();





	private JPanel pan_dimension = new JPanel();
	private JPanel pan_dimension_lab = new JPanel();
	private JPanel pan_dimension_comb = new JPanel();
	private JLabel dimension_lab = new JLabel("Etiquette dimension");
	private JComboBox<String> dimension_comb= new JComboBox<>();

	private JPanel pan = new JPanel();
	private JPanel pan_general = new JPanel();
	private JPanel pan_form = new JPanel();
	private JPanel pan_gener = new JPanel();
	private JPanel pan_button = new JPanel();



	private gestion_imp_tablette imp_tablette = new gestion_imp_tablette();

	private ArrayList   resultat;

	public etiquette_tablette(final String log) {

		composant(log);
	}

	public void composant(final String logi_prio) {
		JPanel panel = generalPanle();
		declarationButton(pan_button);
		visibiliteButton(true, false, false, false,
				false, false,false);
		imp_etq.setVisible(false);
		initFrame("Etiquette Tablette", pan, this, logi_prio,"Etiquette Tablette");


		pan_nvsn.setVisible(false);
        Date actuelle=new Date();


        remplirArticleType(model_jtext, 7);
        model_jtext.addActionListener(e -> code_article = splitcombo(model_jtext));




        remplirActDimComb( dimension_comb,"etiquette_tablette");





        remplirColor(color_comb,"etiquette_tablette");
		color_comb.addActionListener(e -> addTocomboAction(color_comb));




        sn_jtext.addActionListener(e ->
            resultat=imei()
        );





		retour.addActionListener(e -> {
			titleFrame("Edition etiquette tablette",pan);
			visibiliteButton(true, false, false, true,
					false, false,false);
			imp_etq.setVisible(false);
			color_comb.setSelectedIndex(0);
			model_jtext.setSelectedIndex(0);
			sn_jtext.setText("");
			nvsn_jtext.setText("");
			licence_jtext.setText("");

			sn_jtext.setEditable(true);
            pan_nvsn.setVisible(false);
		});

		ajouter.addActionListener(e -> {
			titleFrame("Edition etiquette tablette",pan);
			visibiliteButton(false, false, false, true,
					true, false,false);
			imp_etq.setVisible(false);
		});



		valid_ajou.addActionListener(e -> {
			Thread att= new Thread(() -> {
				msg = "";
				validationChamp();

				if (msg.equals("")) {
					visibiliteButton(false, false, false, true,
							false, false,false);

					imp_etq.setVisible(true);


					imp_tablette.insert_tablette_fournisseur(code_article,String.valueOf(color_comb.getSelectedItem()),sn_jtext.getText(),actuelle,"",licence_jtext.getText());

					JOptionPane.showMessageDialog(null, "l'étiquette portable " + sn_jtext.getText()	+ "  a été bien ajouté");
                     snaimp=sn_jtext.getText();
				} else {
					JOptionPane.showMessageDialog(panel, msg, "Error", JOptionPane.ERROR_MESSAGE);

				}	});
			att.start();
		});



        modifier.addActionListener(e -> {
            titleFrame("Modifier etiquette tablette",pan);
            visibiliteButton(false, false, false, true,
                    false, true,false);
            imp_etq.setVisible(false);
        });



        valid_modif.addActionListener(e -> {
			Thread	att= new Thread(() -> {
				imp_tablette.delete_tablette_fournisseur(sn_jtext.getText());

				msg = "";
				validationChamp();

				if (msg.equals("")) {
					visibiliteButton(false, false, false, true,
							false, false,false);
					imp_etq.setVisible(true);


					imp_tablette.insert_tablette_fournisseur(code_article,String.valueOf(color_comb.getSelectedItem()),nvsn_jtext.getText(), actuelle,resultat.get(4).toString(),licence_jtext.getText());
					snaimp=nvsn_jtext.getText();
					JOptionPane.showMessageDialog(null, "l'étiquette tablette " + sn_jtext.getText()	+ "  a été bien Modifier");

				} else {
					JOptionPane.showMessageDialog(panel, msg, "Error", JOptionPane.ERROR_MESSAGE);
				}	});
			att.start();
		});



		imp_etq.addActionListener(
				event -> {

					if (dimension_comb.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette"); }
					else {
						code_aimprimer.add(snaimp);

						parameters.put("serial", code_aimprimer);

						String parcour = constant.etiq_tablette +dimension_comb.getSelectedItem()+"_"+snaimp+"_Etiquette_tablette.pdf";


						Imprimer_pdf_Final( parcour, report, parameters);
						sn_jtext.setText("");
						nvsn_jtext.setText("");
						licence_jtext.setText("");

						visibiliteButton(true, false, false, true,
								false, false, false);
						imp_etq.setVisible(false);
						sn_jtext.setEditable(true);
						pan_nvsn.setVisible(false);


					}
				});





		////////////////////////*********************style de lab
		styleButton(imp_etq,pan_button);
		styleLabel(model_lab);
		styleLabel(color_lab);
		styleLabel(sn_lab);
		styleLabel(dimension_lab);
		styleLabel(nvsn_lab);
		styleLabel(licence_lab);


		// /*************************add component in pane************/


		pan_dimension.add(pan_dimension_lab);
		pan_dimension_lab.add(dimension_lab);
		pan_dimension.add(pan_dimension_comb);
		pan_dimension_comb.add(dimension_comb);

		pan_model.add(pan_model_lab);
		pan_model_lab.add(model_lab);
		pan_model.add(pan_model_jtext);
		pan_model_jtext.add(model_jtext);

		pan_color.add(pan_color_lab);
		pan_color_lab.add(color_lab);
		pan_color.add(pan_color_comb);
		pan_color_comb.add(color_comb);

		pan_sn.add(pan_sn_lab);
		pan_sn_lab.add(sn_lab);
		pan_sn.add(pan_sn_jtext);
		pan_sn_jtext.add(sn_jtext);


		pan_nvsn.add(pan_nvsn_lab);
		pan_nvsn_lab.add(nvsn_lab);
		pan_nvsn.add(pan_nvsn_jtext);
		pan_nvsn_jtext.add(nvsn_jtext);


		pan_licence.add(pan_licence_lab);
		pan_licence_lab.add(licence_lab);
		pan_licence.add(pan_licence_jtext);
		pan_licence_jtext.add(licence_jtext);



		pan_form.add(pan_model);
		pan_form.add(pan_color);
		pan_form.add(pan_sn);
		pan_form.add(pan_nvsn);
		pan_form.add(pan_licence);

		pan_form.add(pan_dimension);


		pan_general.add(pan_button);

		pan_gener.add(pan_form);
		pan.add(pan_gener);
		pan.add(pan_general);
		panel.add(pan);


		//////////****************visibilite de panel **************/

		pan_model.setOpaque(false);
		pan_model_lab.setOpaque(false);
		pan_model_jtext.setOpaque(false);


		pan_dimension.setOpaque(false);
		pan_dimension_lab.setOpaque(false);
		pan_dimension_comb.setOpaque(false);


		pan_color.setOpaque(false);
		pan_color_lab.setOpaque(false);
		pan_color_comb.setOpaque(false);

		pan_sn.setOpaque(false);
		pan_sn_lab.setOpaque(false);
		pan_sn_jtext.setOpaque(false);



		pan_nvsn.setOpaque(false);
		pan_nvsn_lab.setOpaque(false);
		pan_nvsn_jtext.setOpaque(false);

		pan_licence.setOpaque(false);
		pan_licence_lab.setOpaque(false);
		pan_licence_jtext.setOpaque(false);




		pan_button.setOpaque(false);


		pan_form.setOpaque(false);

		pan_gener.setOpaque(false);
		pan_general.setOpaque(false);
		pan.setOpaque(false);



		/////****************************************add layout to panel******************************/

		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));
		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));
		pan_model.setLayout(new BoxLayout(pan_model, BoxLayout.X_AXIS));
		pan_color.setLayout(new BoxLayout(pan_color, BoxLayout.X_AXIS));
		pan_sn.setLayout(new BoxLayout(pan_sn, BoxLayout.X_AXIS));
		pan_nvsn.setLayout(new BoxLayout(pan_nvsn, BoxLayout.X_AXIS));
		pan_licence.setLayout(new BoxLayout(pan_licence, BoxLayout.X_AXIS));

		pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));
		pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_model_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_model_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_color_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_color_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_sn_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_sn_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_nvsn_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_nvsn_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));


		pan_licence_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_licence_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		// /******************component size**************************/

		color_comb.setPreferredSize(new Dimension(210, 30));
		model_jtext.setPreferredSize(new Dimension(210, 30));
		sn_jtext.setPreferredSize(new Dimension(210, 30));

		nvsn_jtext.setPreferredSize(new Dimension(210, 30));

		licence_jtext.setPreferredSize(new Dimension(210, 30));


		dimension_comb.setPreferredSize(new Dimension(210, 30));


		// /***********************Border de panel**************************/


		pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_model_lab.setBorder(BorderFactory.createEmptyBorder(20, 120, 0, 0));
		pan_model_jtext.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));



		pan_color_lab.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
		pan_color_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_sn_lab.setBorder(BorderFactory.createEmptyBorder(0, 135, 0, 0));
		pan_sn_jtext.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));



		pan_nvsn_lab.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 0));
		pan_nvsn_jtext.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));



		pan_licence_lab.setBorder(BorderFactory.createEmptyBorder(0, 105, 0, 0));
		pan_licence_jtext.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));



		setContentPane(panel);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new etiquette_tablette("7");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}




private ArrayList  imei(){

				ArrayList <String>  resultat=imp_tablette.select_tablette_fourn(sn_jtext.getText());
				if(resultat.size()!=0){
					pan_nvsn.setVisible(true);

					model_jtext.setSelectedItem(resultat.get(0)+" "+resultat.get(1));
					color_comb.setSelectedItem(resultat.get(2));
					sn_jtext.setText(resultat.get(3));
					licence_jtext.setText(resultat.get(5));

					sn_jtext.setEditable(false);
					visibiliteButton(false, false, false, true,
							false, true,false);
					imp_etq.setVisible(false);

				}
				else{
					pan_nvsn.setVisible(false);

					imp_etq.setVisible(false);
					visibiliteButton(true, false, false, true,
							false, false,false);


				}

	return resultat;
	}




	private void validationChamp(){
        code_aimprimer.clear();

        ArrayList <String>  resultat=imp_tablette.select_tablette_fourn(sn_jtext.getText());
		ArrayList <String>  tab_existe=imp_tablette.tablette_licence_exist(licence_jtext.getText(), sn_jtext.getText());

		if (sn_jtext.getText().trim().equals("")) {
			msg += "Veuillez remplir  le Sn \n";
		}
		else if (licence_jtext.getText().trim().equals("")) {
			msg += "Veuillez remplir  la licence \n";
		}

		else if (color_comb.getSelectedIndex() == 0) {
			msg += "Veuillez selectionner une couleur \n";
		}
		else if (model_jtext.getSelectedIndex()==0) {
			msg += "Veuillez selectionner  l'article \n";
		}

		else if (resultat.size()!=0) {
			msg += " Le Serial Number existe déjà \n";
		}

		else if (tab_existe.size()!=0) {
			msg += "La licence est déjà associée à une autre tablette.  \n";
		}
		else if(pan_nvsn.isVisible()==true){
			ArrayList <String>  resultat_nv=imp_tablette.select_tablette_fourn(nvsn_jtext.getText());
         if (resultat_nv.size()!=0) {
				msg += " Le Serial Number a modifier existe déjà \n";
			}



		}
	}

}

