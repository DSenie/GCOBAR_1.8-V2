package logiciel_etiq.View.Etiquette.TV;

import logiciel_etiq.Controller.gestion_imp_tv;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import logiciel_etiq.constant;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;


public class etiquette_tv extends generale {


	private JPanel pan_article = new JPanel();
	private JPanel pan_article_comb = new JPanel();
	private JPanel pan_article_lab = new JPanel();
	private JLabel article_lab = new JLabel("Liste des Articles");
	private jcombo article_comb=new jcombo(list_comb);

	private JPanel pan_chaine = new JPanel();
	private JPanel pan_chaine_comb = new JPanel();
	private JPanel pan_chaine_lab = new JPanel();
	private JLabel chaine_lab = new JLabel("Liste des Chaine");
	private jcombo chaine_comb=new jcombo(list_comb);


	private JPanel pan_code = new JPanel();
	private JPanel pan_code_jtext = new JPanel();
	private JPanel pan_code_lab = new JPanel();
	private JLabel code_lab = new JLabel("Serial Number");
	private JTextField code_jtext = new JTextField();


	private JPanel pan_com = new JPanel();
	private JPanel pan_com_jtext = new JPanel();
	private JPanel pan_com_lab = new JPanel();
	private JLabel com_lab = new JLabel("Code Commercial");
	private JTextField com_jtext = new JTextField();


	private JPanel pan_date = new JPanel();
	private JPanel pan_date_jtext = new JPanel();
	private JPanel pan_date_lab = new JPanel();
	private JLabel date_lab = new JLabel("Date");
	private final JXDatePicker date_picker = new JXDatePicker();


	private JPanel pan_cont = new JPanel();
	private JPanel pan_cont_jtext = new JPanel();
	private JLabel cont_lab = new JLabel("Conteur");
	private JTextField cont_jtext = new JTextField();
	private JPanel pan_cont_lab = new JPanel();


	private JPanel pan = new JPanel();
	private JPanel pan_general = new JPanel();
	private JPanel pan_form = new JPanel();
	private JPanel pan_button = new JPanel();
	private JPanel pan_gener = new JPanel();
	private JPanel pan_tab = new JPanel();

	private JPanel pan_dimension = new JPanel();
	private JPanel pan_dimension_lab = new JPanel();
	private JPanel pan_dimension_comb = new JPanel();
	private JLabel dimension_lab = new JLabel("Etiquette dimension");
	private JComboBox<String> dimension_comb= new JComboBox<>();




	private gestion_imp_tv imp_tv = new gestion_imp_tv();


	private String code;
    private int conteur;





	public etiquette_tv(final String log) {
		composant(log);
	}

	public void composant(final String logi_prio) {

		JPanel panel = generalPanle();
		initFrame("Edition Etiquette TV", pan, this, logi_prio,"Gestion Etiquette TV");
		frame.setUndecorated(true);

		replirDate(date_picker);
		date_picker.addActionListener(
				e -> code=action_chaine());


        replirArtCode(article_comb);
		article_comb.addActionListener(
				e -> {
						code=action_chaine();
						if(article_comb.getSelectedIndex()==0) {
							chaine_comb.setSelectedIndex(0);
							cont_jtext.setText("");
							com_jtext.setText("");
						}

				});


		remplirChaine(chaine_comb);
		chaine_comb.addActionListener(e -> code=action_chaine());



		remplirActDimComb( dimension_comb,"etiquette_tv");


		action_chaine();

		declarationButton(pan_button);
		visibiliteButton(true, false, false, false,
				false, false,false);
		imp_etq.setVisible(false);


		code_jtext.disable();
		article_comb.enable();
		chaine_comb.enable();
		chaine_comb.setSelectedIndex(0);
		article_comb.setSelectedIndex(0);






		ajouter.addActionListener(
				e -> {
					titleFrame("Edition Etiquette TV", pan);
					visibiliteButton(false, false, false, true,
							true, false,false);
					imp_etq.setVisible(false);

				});





		valid_ajou.addActionListener(
				e -> {
					att= new Thread(() -> {
					code_aimprimer.clear();
					msg = "";

					if (article_comb.getSelectedIndex() == 0) {
						msg += "Veuillez selectionner un article \n";
					}
					else if (chaine_comb.getSelectedIndex() == 0) {
						msg += "Veuillez selectionner une chaine \n";
					} else if (cont_jtext.getText().equals("")) {
						msg += "Veuillez remplir le conteur \n";
					} else if (!isValid(cont_jtext.getText())) {
						msg += "Le compteur doit etre un nombre \n";
					}
					else if (com_jtext.getText().equals("")) {
						msg += "Veuillez saisir le code commercial \n";
					}

					if (msg.equals("")) {

						conteur = Integer.parseInt(cont_jtext.getText());
						int af_cont = Integer.parseInt(code.substring(code.length()-3));

						String prefix = code.substring(0,code.length()-3);

						StylePopup(af_cont,conteur);


						for(int i=af_cont;i<=af_cont+conteur-1;i++ ){
							code =prefix+ format3.format(i);

							imp_tv.ajouter_imprimer(code_article,code_chaine, code,com_jtext.getText(),date_picker.getDate());
							code_aimprimer.add(code);

							progressBar.setValue(i);
							progressBar.setStringPainted(true);
							frame.validate();
							frame.setVisible(true);
						}
						   code=action_chaine();
						   code_jtext.setText(code);
                           frame.setVisible(false);
						   controlPanel.remove(titr);
						   controlPanel.remove(progressBar);

						visibiliteButton(false, false, false, true,
								true, false,false);
						imp_etq.setVisible(true);

						JOptionPane.showMessageDialog(null, "les etiquette ont été bien ajouter");
					} else {
						JOptionPane.showMessageDialog(null, msg);
					}	});
					att.start();
				});








		imp_etq.addActionListener(
				event -> {
			att= new Thread(() -> {

				if (dimension_comb.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette"); }
				else {
					String code_art = code_article.replaceAll("/", "_"); // 004

					parameters.put("serial", code_aimprimer);

					String parcour = constant.etiq_tv + code_jtext.getText() + "_" + code_art + "_" + code_chaine + "_" + date_picker.getEditor().getText() + "_" + dimension_comb.getSelectedItem() + ".pdf";


					Imprimer_pdf_Final( parcour, report, parameters);
				}
			});

				att.start();

		});


		retour.addActionListener(
				e -> {
					titleFrame("Edition etiquette TV",pan);
					article_comb.setSelectedIndex(0);
					date_picker.setDate(Calendar.getInstance().getTime());
					visibiliteButton(true, false, false, false,
							false, false,false);
					imp_etq.setVisible(false);
				});

		






		////******************************style label
		styleLabel(article_lab);
		styleLabel(chaine_lab);
		styleLabel(code_lab);
		styleLabel(com_lab);
		styleLabel(cont_lab);
		styleLabel(date_lab);
		styleLabel(dimension_lab);



		//*************************add componenrt pour le code commercial************/

		pan_article.add(pan_article_lab);
		pan_article_lab.add(article_lab);
		pan_article.add(pan_article_comb);
		pan_article_comb.add(article_comb);

		pan_date.add(pan_date_lab);
		pan_date_lab.add(date_lab);
		pan_date.add(pan_date_jtext);
		pan_date_jtext.add(date_picker);

		pan_dimension.add(pan_dimension_lab);
		pan_dimension_lab.add(dimension_lab);
		pan_dimension.add(pan_dimension_comb);
		pan_dimension_comb.add(dimension_comb);


		pan_chaine.add(pan_chaine_lab);
		pan_chaine_lab.add(chaine_lab);
		pan_chaine.add(pan_chaine_comb);
		pan_chaine_comb.add(chaine_comb);

		pan_code.add(pan_code_lab);
		pan_code_lab.add(code_lab);
		pan_code.add(pan_code_jtext);
		pan_code_jtext.add(code_jtext);
		
		pan_com.add(pan_com_lab);
		pan_com_lab.add(com_lab);
		pan_com.add(pan_com_jtext);
		pan_com_jtext.add(com_jtext);

		pan_cont.add(pan_cont_lab);
		pan_cont_lab.add(cont_lab);
		pan_cont.add(pan_cont_jtext);
		pan_cont_jtext.add(cont_jtext);

		pan_form.add(pan_article);
		pan_form.add(pan_date);

		pan_form.add(pan_chaine);
		pan_form.add(pan_code);
		pan_form.add(pan_com);
		pan_form.add(pan_cont);
		pan_form.add(pan_dimension);

		pan_general.add(pan_button);

		pan_gener.add(pan_form);
		pan_gener.add(pan_tab);
		pan.add(pan_gener);
		pan.add(pan_general);

		panel.add(pan);




   ////////////////************* visibilite de panel background

		pan_form.setOpaque(false);
		pan_cont.setOpaque(false);
		pan_cont_lab.setOpaque(false);
		pan_cont_jtext.setOpaque(false);
		pan_com.setOpaque(false);
		pan_com_lab.setOpaque(false);
		pan_com_jtext.setOpaque(false);
		pan_chaine.setOpaque(false);
		pan_chaine_lab.setOpaque(false);
		pan_chaine_comb.setOpaque(false);

		pan_dimension.setOpaque(false);
		pan_dimension_lab.setOpaque(false);
		pan_dimension_comb.setOpaque(false);
		pan_article.setOpaque(false);
		pan_article_lab.setOpaque(false);
		pan_article_comb.setOpaque(false);

		pan_date.setOpaque(false);
		pan_date_lab.setOpaque(false);
		pan_date_jtext.setOpaque(false);
		pan_code.setOpaque(false);
		pan_code_lab.setOpaque(false);
		pan_code_jtext.setOpaque(false);

		pan_button.setOpaque(false);
		pan_gener.setOpaque(false);
		pan_tab.setOpaque(false);
		pan_general.setOpaque(false);
		pan.setOpaque(false);



		////////////////************* panell layout

		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));
		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));

		pan_article.setLayout(new BoxLayout(pan_article, BoxLayout.X_AXIS));
		pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));

		pan_date.setLayout(new BoxLayout(pan_date, BoxLayout.X_AXIS));
		pan_chaine.setLayout(new BoxLayout(pan_chaine, BoxLayout.X_AXIS));
		pan_code.setLayout(new BoxLayout(pan_code, BoxLayout.X_AXIS));
		pan_com.setLayout(new BoxLayout(pan_com, BoxLayout.X_AXIS));

		pan_cont.setLayout(new BoxLayout(pan_cont, BoxLayout.X_AXIS));



		pan_article_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));


		pan_date_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_date_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_chaine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_code_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_code_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_com_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_com_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));


		pan_cont_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_cont_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));






		/////////////****************************border of panel

		pan_article_lab.setBorder(BorderFactory.createEmptyBorder(20, 200, 0, 0));
		pan_article_comb.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		pan_cont_lab.setBorder(BorderFactory.createEmptyBorder(0, 270, 0, 0));
		pan_cont_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pan_com_lab.setBorder(BorderFactory.createEmptyBorder(0, 207, 0, 0));
		pan_com_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pan_code_lab.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, 0));
		pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, -15));
		pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 297, 0, 0));
		pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 180, 0, 0));
		pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));



    ///////////////////////////*******************dimension components

		styleButton(imp_etq,pan_button);
		styleComponent(dimension_comb);
		styleComponent(article_comb);
		styleComponent(date_picker);
		styleComponent(chaine_comb);
		styleComponent(com_jtext);
		styleComponent(code_jtext);
		styleComponent(cont_jtext);



		setContentPane(panel);
	}




	///*******************pour remplir le serial number **************/
	private String action_chaine() {
		code_chaine="";

		code_chaine = splitcombo(chaine_comb);
		code_article = splitcombo(article_comb);

		int indic = imp_tv.select_indice(code_article);
		code = imp_tv.afficher_conteur(code_chaine, date_picker.getDate(), indic);
		code_jtext.setText(code);
		return code;
	}
		
		


}
