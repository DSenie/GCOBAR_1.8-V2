package logiciel_etiq.View.Etiquette.Phone;

import logiciel_etiq.Controller.gestion_imp_phone;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import logiciel_etiq.constant;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.*;
import java.util.*;

public class etiquette_portable_forn extends generale {

	private JPanel pan_recherche = new JPanel();
	private JPanel pan_recherche_jtext = new JPanel();
	private JPanel pan_recherche_lab = new JPanel();
	private JLabel recherche_lab = new JLabel("Recherche");
	private JTextField recherche_jtext=new JTextField();

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
	

	private JPanel pan_imei1_lab = new JPanel();
	private JPanel pan_imei1 = new JPanel();
	private JPanel pan_imei1_jtext = new JPanel();
	private JLabel imei1_lab = new JLabel("IMEI1");
	private JFormattedTextField imei1_jtext;


	private JPanel pan_imei2_lab = new JPanel();
	private JPanel pan_imei2 = new JPanel();
	private JPanel pan_imei2_jtext = new JPanel();
	private JLabel imei2_lab = new JLabel("IMEI2");
	private JFormattedTextField imei2_jtext;

	
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


    private gestion_imp_phone imp_phone=new gestion_imp_phone();


	public etiquette_portable_forn(final String log) {
		composant(log);
	}

	public void composant(final String logi_prio) {

		JPanel panel = generalPanle();
		declarationButton(pan_button);
		visibiliteButton(true, false, false, true,
				false, false,false);
		imp_etq.setVisible(false);
		initFrame("Etiquette Tablette", pan, this, logi_prio,"Etiquette Tablette");



		remplirActDimComb( dimension_comb,"etiquette_portable");

		Date actuelle=new Date();


       		MaskFormatter num = null;
		try {
			num = new MaskFormatter("###############");

		}
		catch (ParseException e1) {
			e1.printStackTrace();
		}


		MaskFormatter num2 = null;
		try {
			num2 = new MaskFormatter("###############");

		}
		catch (ParseException e1) {
			e1.printStackTrace();
		}




		retour.addActionListener(e -> {
			titleFrame("Edition Portable Fournisseur",pan);
			visibiliteButton(true, false, false, true,
					false, false,false);
			imp_etq.setVisible(false);
			imei1_jtext.setText("");
			imei2_jtext.setText("");
			model_jtext.setSelectedIndex(0);
			color_comb.setSelectedIndex(0);
		});







		imei2_jtext= new  JFormattedTextField(num);
		imei1_jtext= new  JFormattedTextField(num2);





		remplirArticleType(model_jtext, 5);
		model_jtext.addActionListener(e -> code_article = splitcombo(model_jtext));



		remplirColor(color_comb, "etiquette_portable");
		color_comb.addActionListener(e -> addTocomboAction(color_comb));




		ajouter.addActionListener(e -> {
			titleFrame("Edition Etiquette Portable",pan);
			visibiliteButton(false, false, false, true,
					true, false,false);
			imp_etq.setVisible(false);
		});


        modifier.addActionListener(e -> {
			titleFrame("Modifier Etiquette Portable",pan);
			visibiliteButton(false, false, false, true,
					false, true,false);
			imp_etq.setVisible(false);
        });

		recherche_jtext.addActionListener(e -> imei());


		imei1_jtext.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {

				if(imei1_jtext.getText().trim().length()==0){
					imei1_jtext.setText("");
				}

                if(imei1_jtext.getText().trim().length()==15&&evt.getKeyCode()== KeyEvent.VK_ENTER)
				{
					imei2_jtext.requestFocus();
				}

			}
		});

		imei2_jtext.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {
					if(imei2_jtext.getText().trim().length()==0){
						imei2_jtext.setText("");
					}

				}
			});






		valid_ajou.addActionListener(e -> {
			att= new Thread(() -> {
			validationChamp();
             code_aimprimer.clear();
			if (msg.equals("")) {

				imp_phone.insert_phone_fournisseur(code_article,(String) color_comb.getSelectedItem()	,imei1_jtext.getText(),imei2_jtext.getText(),
						actuelle);
				visibiliteButton(false, false, false, true,
						false, false, false);
				imp_etq.setVisible(true);

					JOptionPane.showMessageDialog(null, "l'étiquette portable " + imei1_jtext.getText()	+ "  a été bien ajouté");

			} else {
					JOptionPane.showMessageDialog(panel, msg, "Error", JOptionPane.ERROR_MESSAGE);
			}	});
			att.start();
		});




        valid_modif.addActionListener(e -> {
			att= new Thread(() -> {
				msg = "";
			   validationChamp();
				code_aimprimer.clear();

				if (msg.equals("")) {
					visibiliteButton(false, false, false, true,
							false, false, false);
					imp_etq.setVisible(true);


					imp_phone.delete_phone_fournisseur(imei1_jtext.getText(),imei2_jtext.getText());
					imp_phone.insert_phone_fournisseur(code_article,(String) color_comb.getSelectedItem(),imei1_jtext.getText(),imei2_jtext.getText(), actuelle);


					JOptionPane.showMessageDialog(null, "l'étiquette portable " + imei1_jtext.getText()	+ "  a été bien Modifier");

				} else {
					JOptionPane.showMessageDialog(panel, msg, "Error", JOptionPane.ERROR_MESSAGE);
				}	});
			att.start();
		});






		imp_etq.addActionListener(
				event -> {

					if (dimension_comb.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(panel, "Vous devez choisir un type d'étiquette", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {

						code_aimprimer.add(imei1_jtext.getText());

						parameters.put("imei1", code_aimprimer);



						String parcour = constant.etiq_portable + dimension_comb.getSelectedItem() + "_"+ imei1_jtext.getText()+
								"_imei2"+imei1_jtext.getText()+"_Etiquette_portable.pdf";

						Imprimer_pdf_Final( parcour, report, parameters);

						visibiliteButton(true, false, false, true,
								false, false, false);
						imp_etq.setVisible(false);

						imei1_jtext.setText("");
						imei2_jtext.setText("");

					}
				});






		//////************************style de label*********************************/
		styleLabel(model_lab);
		styleLabel(imei1_lab);
		styleLabel(imei2_lab);
		styleLabel(color_lab);
		styleLabel(color_lab);
		styleLabel(dimension_lab);
		styleLabel(recherche_lab);

      ////***************************style de composnat*****************************/

		styleButton(imp_etq,pan_button);
		styleComponent(color_comb);
		styleComponent(model_jtext);
		styleComponent(imei1_jtext);
		styleComponent(imei2_jtext);
		styleComponent(dimension_comb);
		styleComponent(recherche_jtext);



		///*************************add panel in component ************/
		
		
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

		pan_imei1.add(pan_imei1_lab);
		pan_imei1_lab.add(imei1_lab);
		pan_imei1.add(pan_imei1_jtext);
		pan_imei1_jtext.add(imei1_jtext);
		
		pan_imei2.add(pan_imei2_lab);
		pan_imei2_lab.add(imei2_lab);
		pan_imei2.add(pan_imei2_jtext);
		pan_imei2_jtext.add(imei2_jtext);

		pan_recherche.add(pan_recherche_lab);
		pan_recherche_lab.add(recherche_lab);
		pan_recherche.add(pan_recherche_jtext);
		pan_recherche_jtext.add(recherche_jtext);

		pan_form.add(pan_recherche);
		pan_form.add(pan_model);
		pan_form.add(pan_color);
		pan_form.add(pan_imei1);
		pan_form.add(pan_imei2);
		pan_form.add(pan_dimension);
		pan_general.add(pan_button);
		pan_gener.add(pan_form);
		pan.add(pan_gener);
		pan.add(pan_general);

		panel.add(pan);


/////*******************************visivilte panel***************************************/
		pan_button.setOpaque(false);

		pan_dimension.setOpaque(false);
		pan_dimension_lab.setOpaque(false);
		pan_dimension_comb.setOpaque(false);

		pan_model.setOpaque(false);
		pan_model_lab.setOpaque(false);
		pan_model_jtext.setOpaque(false);
		pan_color.setOpaque(false);
		pan_color_lab.setOpaque(false);
		pan_color_comb.setOpaque(false);


		pan_imei1.setOpaque(false);
		pan_imei1_lab.setOpaque(false);
		pan_imei1_jtext.setOpaque(false);

		pan_imei2.setOpaque(false);
		pan_imei2_lab.setOpaque(false);
		pan_imei2_jtext.setOpaque(false);


		pan_recherche.setOpaque(false);
		pan_recherche_lab.setOpaque(false);
		pan_recherche_jtext.setOpaque(false);

		pan_gener.setOpaque(false);
		pan_general.setOpaque(false);
		pan.setOpaque(false);

		pan_form.setOpaque(false);


	/////***************************layout des panel ***************************/

		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));


		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));
		pan_recherche.setLayout(new BoxLayout(pan_recherche, BoxLayout.X_AXIS));

		pan_model.setLayout(new BoxLayout(pan_model, BoxLayout.X_AXIS));
		pan_color.setLayout(new BoxLayout(pan_color, BoxLayout.X_AXIS));
		pan_imei1.setLayout(new BoxLayout(pan_imei1, BoxLayout.X_AXIS));
		pan_imei2.setLayout(new BoxLayout(pan_imei2, BoxLayout.X_AXIS));
		pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));

		pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_recherche_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_recherche_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_model_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_model_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_color_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_color_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_imei1_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_imei1_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_imei2_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_imei2_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));


		///////**************************border de component dans les panel ***************************/

		pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_model_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
		pan_model_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_color_lab.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
		pan_color_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_imei1_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
		pan_imei1_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_imei2_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
		pan_imei2_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_recherche_lab.setBorder(BorderFactory.createEmptyBorder(20, 100, 0, 0));
		pan_recherche_jtext.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));



		setContentPane(panel);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {

				new etiquette_portable_forn("7");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}




private void  imei(){

				ArrayList<String> resultat=imp_phone.rech_imprim_fourn(recherche_jtext.getText());
				if(resultat.size()>0){
					model_jtext.setSelectedItem(resultat.get(0));
					color_comb.setSelectedItem(resultat.get(1));

					imei1_jtext.setText(resultat.get(2));
					imei2_jtext.setText(resultat.get(3));

					visibiliteButton(false, false, false, true,
							false, true, false);
					imp_etq.setVisible(false);

				}
				else{
					visibiliteButton(true, false, false, true,
							false, false, false);
					imp_etq.setVisible(false);


				}

	}


private void validationChamp(){
		msg="";
	boolean exist=imp_phone.exist_phone_fournisseur(imei1_jtext.getText(),imei2_jtext.getText());

	if (imei1_jtext.getText().trim().equals("")) {
		msg += "Veuillez remplir  le Imei 1  \n";
	}
	else if (imei2_jtext.getText().trim().equals("")) {
		msg += "Veuillez remplir  le Imei 2  \n";
	}

	else if(imei1_jtext.getText().trim().length()<15)
	{
		msg += "Le code imei 1 doit contenir 15 nombres  \n";
	}
	else if(imei2_jtext.getText().trim().length()<15)
	{
		msg += "Le code imei 1 doit contenir 15 nombres  \n";
	}
	else if (color_comb.getSelectedIndex() == 0) {
		msg += "Veuillez selectionner une couleur \n";
	}
	else if (model_jtext.getSelectedIndex()==0) {
		msg += "Veuillez selectionner  le modele \n";
	}
	else if (imei1_jtext.getText().trim().equals(imei2_jtext.getText().trim())) {
		msg += "Le code Imei 1 est égale au code Imei 2 \n";
	}
	else if(exist){
		msg += " Code Imei 1 ou Imei 2 existe daja \n";

	}


}

}

