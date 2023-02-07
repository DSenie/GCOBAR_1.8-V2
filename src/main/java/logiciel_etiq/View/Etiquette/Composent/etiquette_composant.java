package logiciel_etiq.View.Etiquette.Composent;

import logiciel_etiq.Controller.gestion_composant;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import logiciel_etiq.constant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class etiquette_composant extends generale {

	private JPanel pan_article = new JPanel();
	private JPanel pan_article_comb = new JPanel();
	private JPanel pan_article_lab = new JPanel();
	private JLabel article_lab = new JLabel("Liste des Articles");
	private jcombo article_comb=new jcombo(list_comb) ;

	private JPanel pan_nbrbobine = new JPanel();
	private JPanel pan_nbrbobine_jtext = new JPanel();
	private JPanel pan_nbrbobine_lab = new JPanel();
	private JLabel nbrbobine_lab = new JLabel("Nombre de bobines");
	private JTextField nbrbobine_jtext=new JTextField();


	private JPanel pan_seriebobine = new JPanel();
	private JPanel pan_seriebobine_jtext = new JPanel();
	private JPanel pan_seriebobine_lab = new JPanel();
	private JLabel seriebobine_lab = new JLabel("Qte/total bobine");
	private JTextField totalbobine_jtext=new JTextField();


	private JPanel pan_recherche = new JPanel();
	private JPanel pan_recherche_jtext = new JPanel();
	private JPanel pan_recherche_lab = new JPanel();
	private JLabel recherche_lab = new JLabel("Recherche Carton");
	private JTextField recherche_jtext=new JTextField();



	private JPanel pan_qtebobine = new JPanel();
	private JPanel pan_qtebobine_jtext = new JPanel();
	private JPanel pan_qtebobine_lab = new JPanel();
	private JLabel qtebobine_lab = new JLabel("Quantité par Bobine");
	private JTextField qtebobine_jtext=new JTextField();

	private JPanel pan_carton = new JPanel();
	private JPanel pan_carton_jtext = new JPanel();
	private JPanel pan_carton_lab = new JPanel();
	private JLabel carton_lab = new JLabel("Code Carton");
	private JTextField carton_jtext=new JTextField();



	private JPanel pan_qteglobale = new JPanel();
	private JPanel pan_qteglobale_jtext = new JPanel();
	private JPanel pan_qteglobale_lab = new JPanel();
	private JLabel qteglobale_lab = new JLabel("Quantité Globale");
	private JTextField qteglobale_jtext=new JTextField();



	private JPanel pan_nbrcarton = new JPanel();
	private JPanel pan_nbrcarton_jtext = new JPanel();
	private JPanel pan_nbrcarton_lab = new JPanel();
	private JLabel nbrcarton_lab = new JLabel("Quantité de carton");
	private JTextField nbrcarton_jtext=new JTextField();


	private JPanel pan_id = new JPanel();
	private JPanel pan_id_jtext = new JPanel();
	private JPanel pan_id_lab = new JPanel();
	private JLabel id_lab = new JLabel("Référence Fournisseur");
	private JTextField id_jtext=new JTextField();


	private JPanel pan_dimension = new JPanel();
	private JPanel pan_dimension_lab = new JPanel();
	private JPanel pan_dimension_comb = new JPanel();
	private JLabel dimension_lab = new JLabel("Etiquette dimension");
	private JComboBox<String> dimension_comb= new JComboBox<>();

	private JPanel pan = new JPanel();
	private JPanel pan_general = new JPanel();
	private JPanel pan_gener = new JPanel();

	private JPanel pan_form = new JPanel();

	private JPanel pan_button = new JPanel();

	private gestion_composant imp_composant=new gestion_composant();
	private String serie = "";

	public etiquette_composant(final String log) {
		composant(log);
	}

	public void composant(final String logi_prio) {


		JPanel panel = generalPanle();
		declarationButton(pan_button);
		visibiliteButton(true, false, false, true,
				false, false,false);
		imp_etq.setVisible(false);
		initFrame("Edition Etiquette Composant ", pan, this, logi_prio,"Gestion Etiquette Composant");
		frame.setUndecorated(true);
		carton_jtext.disable();
		qteglobale_jtext.disable();
		totalbobine_jtext.disable();



		remplirArticleType(article_comb,6 );
		article_comb.addActionListener(e -> {
			code_article=splitcombo(article_comb);
			action_carton();

			if (article_comb.getSelectedIndex() == 0) {
				qtebobine_jtext.setText("");
				nbrbobine_jtext.setText("");
				qteglobale_jtext.setText("");
				recherche_jtext.setText("");
				id_jtext.setText("");
				visibiliteButton(true, false, false, true,
						false, false,false);
				imp_etq.setVisible(false);
			}

		});


		remplirActDimComb( dimension_comb,"etiquette_composant");
		action_carton();





		retour.addActionListener(e -> {
			titleFrame("Edition Etiquette Composnat",pan);
			article_comb.setSelectedIndex(0);
		});


		ajouter.addActionListener( e -> {
			titleFrame("Ajouter etiquette Composant",pan);
			visibiliteButton(false, false, false, true,
					true, false,false);
			imp_etq.setVisible(false);
		});


		valid_ajou.addActionListener(e -> {
			att= new Thread(() -> {
                String code=carton_jtext.getText();
				code_aimprimer.clear();
				msg = validChamp();

				if (!msg.equals("")) {
					JOptionPane.showMessageDialog(null, msg);
				}
				else {
                    System.out.println(code_aimprimer);
					imp_composant.ajouter_composant(code, code_article, qtebobine_jtext.getText(), nbrbobine_jtext.getText()
							, id_jtext.getText());


					StylePopup(0,Integer.parseInt(nbrbobine_jtext.getText()));

					for (int j = 1; j <= Integer.parseInt(nbrbobine_jtext.getText()); j++) {

						serie = "S" + format4.format(j) + "/" + nbrbobine_jtext.getText();
						imp_composant.insert_composant_bobine(code, serie);

						progressBar.setValue(j);
						progressBar.setStringPainted(true);
						frame.validate();
						frame.setVisible(true);

					}
					frame.setVisible(false);
					controlPanel.remove(titr);
					controlPanel.remove(progressBar);

					visibiliteButton(false, false, false, true,
							false, false, false);
					imp_etq.setVisible(true);

					code_aimprimer.add(code);

					JOptionPane.showMessageDialog(null, "l'étiquette a été bien ajouté");
				}
       	});
			att.start();
		});






		modifier.addActionListener(e -> {
			titleFrame("Modifier etiquette Composant",pan);
			visibiliteButton(false, false, false, true,
					false, true,false);
			imp_etq.setVisible(false);

		});

	valid_modif.addActionListener(e -> {
		att= new Thread(() -> {
			code_aimprimer.clear();
			 msg=validChamp();

			if (!msg.equals("")) {
				JOptionPane.showMessageDialog(null, msg);
			}
			else{

				imp_composant.update_composant(carton_jtext.getText(),code_article,qtebobine_jtext.getText(),nbrbobine_jtext.getText()
						,id_jtext.getText(),recherche_jtext.getText());

				imp_composant.delete_composant_bobine(recherche_jtext.getText());


				StylePopup(0,Integer.parseInt(nbrbobine_jtext.getText()));

				for(int j=1;j<=Integer.parseInt(nbrbobine_jtext.getText());j++){

					int cont=imp_composant.counteur_composant_bobine(code_article);
					String serie="S"+format4.format(j)+"/"+format2.format(j);
					totalbobine_jtext.setText(""+cont);
					imp_composant.insert_composant_bobine(carton_jtext.getText(),serie);

					progressBar.setValue(j);
					progressBar.setStringPainted(true);
					frame.validate();
					frame.setVisible(true);

				}
				frame.setVisible(false);
				controlPanel.remove(titr);
				controlPanel.remove(progressBar);

				JOptionPane.showMessageDialog(null,"L'etiquette a été bien modifiée");
				titleFrame("Ajouter etiquette TV",pan);
				visibiliteButton(false, false, false, true,
						false, false,false);
				imp_etq.setVisible(true);
				code_aimprimer.add(carton_jtext.getText());

			}
		});
		att.start();
		});

		actionJtextNumbrBobine(nbrbobine_jtext);
		actionJtextNumbrBobine(qtebobine_jtext);


			recherche_jtext.addActionListener(e -> {


						if(imp_composant.exist_composant(recherche_jtext.getText())){
						ArrayList<String> result=imp_composant.selection_composant_champ(recherche_jtext.getText());
						article_comb.setSelectedItem(result.get(1)+" "+result.get(0));
						qtebobine_jtext.setText(result.get(2));
						nbrbobine_jtext.setText(result.get(3));
						id_jtext.setText(result.get(4));

						carton_jtext.setText(recherche_jtext.getText());
                        action_carton();

						imp_etq.setVisible(false);
						visibiliteButton(false, true, false, true,
									false, false, false);

							int code1 = imp_composant.counteur_composant_bobine(code_article);
							totalbobine_jtext.setText("" + code1);
							int qte_global=Integer.parseInt(nbrbobine_jtext.getText() )*Integer.parseInt(qtebobine_jtext.getText() );
							qteglobale_jtext.setText(""+qte_global);

						}
						 else{

							 imp_etq.setVisible(false);
							  visibiliteButton(true, false, false, true,
									false, false, false);

							  retour.doClick();

								 JOptionPane
									.showMessageDialog(
											null,
											"Ce carton n'existe pas",
											"", JOptionPane.INFORMATION_MESSAGE);
							}



				});





		imp_etq.addActionListener(
				event -> {
					if (dimension_comb.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(panel, "Vous devez choisir un type d'étiquette", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {

						parameters.put("code_carton_parm", code_aimprimer);
						String parcour = constant.etiq_composant + dimension_comb.getSelectedItem() + "_" +code_aimprimer.get(0)+"_"+code_aimprimer.size() + "etq_composant.pdf";

						Imprimer_pdf_Final( parcour, report, parameters);



						visibiliteButton(true, false, false, true,
								false, false, false);
						imp_etq.setVisible(false);
						action_carton();

					}
				});



       //////********************style label***********////////
		styleButton(imp_etq,pan_button);

		styleLabel(article_lab);
		styleLabel(carton_lab);
		styleLabel(nbrbobine_lab);
		styleLabel(qtebobine_lab);
		styleLabel(qteglobale_lab);
		styleLabel(id_lab);
		styleLabel(recherche_lab);
		styleLabel(nbrcarton_lab);
		styleLabel(dimension_lab);
		styleLabel(seriebobine_lab);


		//////*********************Style ccomposant **********************/
        styleComponent(article_comb);
		styleComponent(dimension_comb);
		styleComponent(qtebobine_jtext);
		styleComponent(nbrbobine_jtext);
		styleComponent(qteglobale_jtext);
		styleComponent(carton_jtext);
		styleComponent(id_jtext);
		styleComponent(recherche_jtext);
		styleComponent(nbrcarton_jtext);
		styleComponent(totalbobine_jtext);


		///*************************ad Panel Component************/
		pan_recherche.add(pan_recherche_lab);
		pan_recherche_lab.add(recherche_lab);
		pan_recherche.add(pan_recherche_jtext);
		pan_recherche_jtext.add(recherche_jtext);


		pan_seriebobine.add(pan_seriebobine_lab);
		pan_seriebobine_lab.add(seriebobine_lab);
		pan_seriebobine.add(pan_seriebobine_jtext);
		pan_seriebobine_jtext.add(totalbobine_jtext);

		pan_article.add(pan_article_lab);
		pan_article_lab.add(article_lab);
		pan_article.add(pan_article_comb);
		pan_article_comb.add(article_comb);


		pan_qtebobine.add(pan_qtebobine_lab);
		pan_qtebobine_lab.add(qtebobine_lab);
		pan_qtebobine.add(pan_qtebobine_jtext);
		pan_qtebobine_jtext.add(qtebobine_jtext);


		pan_id.add(pan_id_lab);
		pan_id_lab.add(id_lab);
		pan_id.add(pan_id_jtext);
		pan_id_jtext.add(id_jtext);



		pan_nbrbobine.add(pan_nbrbobine_lab);
		pan_nbrbobine_lab.add(nbrbobine_lab);
		pan_nbrbobine.add(pan_nbrbobine_jtext);
		pan_nbrbobine_jtext.add(nbrbobine_jtext);

		pan_qtebobine.add(pan_qtebobine_lab);
		pan_qtebobine_lab.add(qtebobine_lab);
		pan_qtebobine.add(pan_qtebobine_jtext);
		pan_qtebobine_jtext.add(qtebobine_jtext);


		pan_qteglobale.add(pan_qteglobale_lab);
		pan_qteglobale_lab.add(qteglobale_lab);
		pan_qteglobale.add(pan_qteglobale_jtext);
		pan_qteglobale_jtext.add(qteglobale_jtext);


		pan_carton.add(pan_carton_lab);
		pan_carton_lab.add(carton_lab);
		pan_carton.add(pan_carton_jtext);
		pan_carton_jtext.add(carton_jtext);


		pan_nbrcarton.add(pan_nbrcarton_lab);
		pan_nbrcarton_lab.add(nbrcarton_lab);
		pan_nbrcarton.add(pan_nbrcarton_jtext);
		pan_nbrcarton_jtext.add(nbrcarton_jtext);

		pan_dimension.add(pan_dimension_lab);

        pan_dimension.add(pan_dimension_comb);
        pan_dimension_lab.add(dimension_lab);
        pan_dimension_comb.add(dimension_comb);


		pan_button.add(imp_etq);


		pan_form.add(pan_recherche);
		pan_form.add(pan_carton);
		pan_form.add(pan_article);
		pan_form.add(pan_id);
		pan_form.add(pan_nbrbobine);
		pan_form.add(pan_qtebobine);
		pan_form.add(pan_qteglobale);
		pan_form.add(pan_seriebobine);
		pan_form.add(pan_dimension);

		pan_general.add(pan_button);

		pan_gener.add(pan_form);
		pan.add(pan_gener);
		pan.add(pan_general);

		panel.add(pan);






		/////*************************visibilite des panelµ**************************/

		pan_qteglobale.setOpaque(false);
		pan_qteglobale_lab.setOpaque(false);
		pan_qteglobale_jtext.setOpaque(false);

		pan_qtebobine.setOpaque(false);
		pan_qtebobine_lab.setOpaque(false);
		pan_qtebobine_jtext.setOpaque(false);


		pan_recherche.setOpaque(false);
		pan_recherche_lab.setOpaque(false);
		pan_recherche_jtext.setOpaque(false);

		pan_seriebobine.setOpaque(false);
		pan_seriebobine_lab.setOpaque(false);
		pan_seriebobine_jtext.setOpaque(false);
		pan_article.setOpaque(false);
		pan_article_lab.setOpaque(false);
		pan_article_comb.setOpaque(false);

		pan_qtebobine.setOpaque(false);
		pan_qtebobine_lab.setOpaque(false);
		pan_qtebobine_jtext.setOpaque(false);
		pan_id.setOpaque(false);
		pan_id_lab.setOpaque(false);
		pan_id_jtext.setOpaque(false);
		pan_nbrbobine.setOpaque(false);
		pan_nbrbobine_lab.setOpaque(false);
		pan_nbrbobine_jtext.setOpaque(false);

		pan_carton.setOpaque(false);
		pan_carton_lab.setOpaque(false);
		pan_carton_jtext.setOpaque(false);

		pan_nbrcarton.setOpaque(false);
		pan_nbrcarton_lab.setOpaque(false);
		pan_nbrcarton_jtext.setOpaque(false);
		pan_dimension.setOpaque(false);
		pan_dimension_lab.setOpaque(false);
		pan_dimension_comb.setOpaque(false);

		pan_button.setOpaque(false);

		pan_gener.setOpaque(false);
		pan_general.setOpaque(false);
		pan.setOpaque(false);
		pan_form.setOpaque(false);


		pan_dimension.setOpaque(false);


		///////************************layout de panel****************************/

		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));
		pan_seriebobine.setLayout(new BoxLayout(pan_seriebobine, BoxLayout.X_AXIS));
		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));
		pan_seriebobine.setLayout(new BoxLayout(pan_seriebobine, BoxLayout.X_AXIS));

		pan_article.setLayout(new BoxLayout(pan_article, BoxLayout.X_AXIS));
		pan_qtebobine.setLayout(new BoxLayout(pan_qtebobine, BoxLayout.X_AXIS));
		pan_nbrbobine.setLayout(new BoxLayout(pan_nbrbobine, BoxLayout.X_AXIS));
		pan_carton.setLayout(new BoxLayout(pan_carton, BoxLayout.X_AXIS));
		pan_qteglobale.setLayout(new BoxLayout(pan_qteglobale, BoxLayout.X_AXIS));
		pan_id.setLayout(new BoxLayout(pan_id, BoxLayout.X_AXIS));
		pan_recherche.setLayout(new BoxLayout(pan_recherche, BoxLayout.X_AXIS));
		pan_nbrcarton.setLayout(new BoxLayout(pan_nbrcarton, BoxLayout.X_AXIS));
		pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));


		pan_nbrcarton_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_nbrcarton_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_seriebobine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_seriebobine_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_recherche_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_recherche_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_id_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_id_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_article_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_nbrbobine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_nbrbobine_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_qtebobine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_qtebobine_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_qteglobale_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_qteglobale_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_carton_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_carton_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		///////*******************************border panel*******************************/



		pan_recherche_lab.setBorder(BorderFactory.createEmptyBorder(10, 140, 0, 0));
		pan_recherche_jtext.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		pan_carton_lab.setBorder(BorderFactory.createEmptyBorder(0, 180, 0, 0));
		pan_carton_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


		pan_article_lab.setBorder(BorderFactory.createEmptyBorder(0, 140, 0, 0));
		pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_id_lab.setBorder(BorderFactory.createEmptyBorder(0, 105, 0, 0));
		pan_id_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


		pan_nbrbobine_lab.setBorder(BorderFactory.createEmptyBorder(0, 125, 0, 0));
		pan_nbrbobine_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));



		pan_qtebobine_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
		pan_qtebobine_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


		pan_qteglobale_lab.setBorder(BorderFactory.createEmptyBorder(0, 145, 0, 0));
		pan_qteglobale_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


		pan_nbrcarton_lab.setBorder(BorderFactory.createEmptyBorder(0, 130, 0, 0));
		pan_nbrcarton_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_seriebobine_lab.setBorder(BorderFactory.createEmptyBorder(0, 140, 0, 0));
		pan_seriebobine_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));



		pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
		pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));





		setContentPane(panel);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {

				new etiquette_composant("7");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}


	private void action_carton() {

		String nbr_Bobine="00";
		if(!nbrbobine_jtext.getText().equals("")){
			nbr_Bobine= format2.format(Integer.parseInt(nbrbobine_jtext.getText()));
		}
		String code = imp_composant.afficher_conteur_composant(code_article,nbr_Bobine);

		carton_jtext.setText(code);


      if(!imp_composant.exist_composant(recherche_jtext.getText())) {
		  int code1 = imp_composant.counteur_composant_bobine(code_article) + Integer.parseInt(nbr_Bobine);
		  totalbobine_jtext.setText("" + code1);
	  }
      else {
      	String code_sub= recherche_jtext.getText().substring(recherche_jtext.getText().length()-4,recherche_jtext.getText().length()-2);
      	String carton=code.replace(code_sub,nbr_Bobine);
        carton_jtext.setText(carton);
	  }


	}




	private String validChamp(){
		msg = "";

		if (article_comb.getSelectedIndex() == 0) {
			msg += "Vous Devez  Sélectionner un Article\n";
		}

		else if (carton_jtext.getText().equals("")) {
			msg += "Vous devez verifier si le code carton est remplie \n";
		}

		else if (nbrbobine_jtext.getText().equals("")) {
			msg += "Vous Devez remplire le nombre de bobines \n";
		}

		else if (qtebobine_jtext.getText().equals("")) {
			msg += "Vous Devez remplire la quantité de bobines \n";
		}
		else if (!isValid(qtebobine_jtext.getText())||Integer.parseInt(qtebobine_jtext.getText())==0) {
			msg += "La quantité doit etre un nombre \n";
		}
		else if (!isValid(nbrbobine_jtext.getText())||Integer.parseInt(nbrbobine_jtext.getText())==0) {
			msg += "La nombre de bobine doit etre un nombre \n";
		}

		return msg;

	}



	private void actionJtextNumbrBobine(JTextField comp){

		comp.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {

				// TODO Auto-generated method stub

			if(!nbrbobine_jtext.getText().equals("")&&!qtebobine_jtext.getText().equals("")&&isValid(qtebobine_jtext.getText())&&
						isValid(nbrbobine_jtext.getText())){
				action_carton();

				String nbr="00";
				if(!nbrbobine_jtext.getText().trim().equals(""))
					nbr=format2.format(Integer.parseInt(nbrbobine_jtext.getText().trim()));

				int qte_global=Integer.parseInt(nbr)*Integer.parseInt(qtebobine_jtext.getText() );
					qteglobale_jtext.setText(""+qte_global);

				}

				else{

					qteglobale_jtext.setText("");

				}
			}
		});



	}

}
