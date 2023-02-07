package logiciel_etiq.View.chaine;

import logiciel_etiq.Controller.gestion_chaine;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Chaine extends generale {
	private String msg="";


	/*************** definition des panneau**************/
	private JPanel pan_form=new JPanel();

	private JPanel pan_combo=new JPanel();
	private JPanel pan_lab_chaine=new JPanel();
	private JPanel pan_combo_chaine=new JPanel();
	private JLabel lab_combo=new JLabel("Liste de Chaine");
	private jcombo chaine_comb=new jcombo(list_comb);


	private JPanel pan_chaine=new JPanel();
	private JPanel pan_lab_codechaine=new JPanel();
	private JPanel pan_jtext_codechaine=new JPanel();
	private JLabel chaine_lab=new JLabel("Code Chaine");
	private	JTextField code_chaine=new JTextField();

	private	JPanel pan_designation=new JPanel();
	private	JPanel pan_lab_designation	=new JPanel();
	private	JPanel pan_jtext_designation=new JPanel();
	private JLabel designation_lab=new JLabel("Désignation");
	private JTextField designation=new JTextField();



	private	JPanel pan_button=new JPanel();
	private  gestion_chaine chaine=new gestion_chaine();


	public Chaine(final String log){


		composant(log);
	}



	private void composant(String log){

		JPanel panel = generalPanle();

		declarationButton(pan_button);
		visibiliteButton(true, false, false, false,
				false, false,false);
		initFrame("Edition Chaine", pan_form, this, log,"Gestion de Chaine");


		remplirChaine(chaine_comb);


		code_chaine.disable();
		designation.enable();
		code_chaine.setText(chaine.afficher_code_chaine());





		retour.addActionListener(
				e -> {
					titleFrame("Edition Chaine", pan_form);
					chaine_comb.setSelectedIndex(0);
					visibiliteButton(true, false, false, false,
							false, false,false);

				});
		///////////////////////////////////supprimerrrrrrrr//////////////////////


		Supprimer.addActionListener(
				e -> {
					titleFrame("Supprimer Chaine", pan_form);
					visibiliteButton(false, false, false, true,
								false, false,true);
				});

		valid_supp.addActionListener(
				e -> {
					msg="";
					if(chaine_comb.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null,"Vous devez d'abord Sélectionner une chaine");
					}
					else{
						chaine.setdelete_chaine(code_chaine.getText(),designation.getText());
						remplirChaine(chaine_comb);
					}
				});



		///////////////////////////////////modifier//////////////////////

		modifier.addActionListener(
				e -> {
					titleFrame("Modifier Chaine", pan_form);
					visibiliteButton(false, false, false, true,
							false, true,false);
				});

		valid_modif.addActionListener(
				e -> {
					 msg="";
					if(chaine_comb.getSelectedIndex()==0){
						msg+="Vous devez sélectionner une chaine \n";
					}
					else if(designation.getText().equals("")) {
						msg+="Veuillez remplir la designation \n";
					}

					if(!msg.equals("")){

						JOptionPane.showMessageDialog(null,msg);
					}
					else{
						chaine.setupdate_chaine(code_chaine.getText(),designation.getText());
						remplirChaine(chaine_comb);
					}
				});


		///////////////////////////////////ajouter//////////////////////

		ajouter.addActionListener(
				e -> {
			        titleFrame("Ajouter Chaine", pan_form);
					visibiliteButton(false, false, false, true,
								true, false,false);
				});

		valid_ajou.addActionListener(
				e -> {
					msg="";

					if(designation.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"Veuillez remplir la designation \n");
					}

					else{

						chaine.ajouter_chaine(code_chaine.getText(), designation.getText());
						remplirChaine(chaine_comb);
					}
				});




	       chaine_comb.addActionListener(
				   e -> {
				   	  titleFrame("Edition Chaine", pan_form);
					   code_chaine.setText(chaine.afficher_code_chaine());
					   actionComboDeuxChamp(chaine_comb, code_chaine,  designation,2);
				   });



		designation.addActionListener(
				e -> {

					chaine_comb.enable();
					titleFrame("Edition Chaine", pan_form);
					boolean existe=chaine.select_chaine_jtext(designation.getText());

					if(!existe){
						visibiliteButton(true, false, false, false,
								false, false,false);
						code_chaine.setText(chaine.afficher_code_chaine());

					}
					else  {

						visibiliteButton(false, true, true, false,
								false, false,false);

						code_chaine.setText(chaine.code_chaine);
						designation.setText(chaine.desig);
						chaine_comb.setSelectedItem(chaine.code_chaine+" "+chaine.desig);
					}

				});





		//******************** add component in panel***********/
		panel.add(pan_form);

		pan_form.setOpaque(false);

		pan_form.add(pan_combo);
		pan_form.add(pan_chaine);
		pan_form.add(pan_designation);
		pan_form.add(pan_button);

		pan_combo.add(pan_lab_chaine);
		pan_combo.add(pan_combo_chaine);


		pan_lab_chaine.add(lab_combo);
		pan_combo_chaine.add(chaine_comb);

		pan_chaine.add(pan_lab_codechaine);
		pan_chaine.add(pan_jtext_codechaine);

		pan_lab_codechaine.add(chaine_lab);
		pan_jtext_codechaine.add(code_chaine);

		pan_designation.add(pan_lab_designation);
		pan_designation.add(pan_jtext_designation);

		pan_lab_designation.add(designation_lab);
		pan_jtext_designation.add(designation);



       //*********** background de panell*****/
		pan_button.setOpaque(false);
		pan_chaine.setOpaque(false);
		pan_designation.setOpaque(false);
		pan_lab_chaine.setOpaque(false);
		pan_combo_chaine.setOpaque(false);
		pan_lab_codechaine.setOpaque(false);
		pan_jtext_codechaine.setOpaque(false);
		pan_lab_designation.setOpaque(false);
		pan_jtext_designation.setOpaque(false);
		pan_combo.setOpaque(false);


		//*********** style des composnat*****/

		styleComponent(chaine_comb);
		styleComponent(designation);
		styleComponent(code_chaine);




		//*********** style des clabels*****/

		styleLabel(chaine_lab);
		styleLabel(designation_lab);
		styleLabel(lab_combo);




		//**************border de composant ***********************/
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pan_button.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

		pan_lab_chaine.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_combo_chaine.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));


		pan_lab_codechaine.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_jtext_codechaine.setBorder(BorderFactory.createEmptyBorder(20,80 , 20, 0));


		pan_lab_designation.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pan_jtext_designation.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 0));




		//*************layout ****************/
		pan_lab_chaine.setLayout( new FlowLayout( FlowLayout.RIGHT ));
		pan_combo_chaine.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_lab_codechaine.setLayout( new FlowLayout( FlowLayout.RIGHT ));
		pan_jtext_codechaine.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_lab_designation.setLayout( new FlowLayout( FlowLayout.RIGHT ));
		pan_jtext_designation.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_button.setLayout( new FlowLayout( FlowLayout.CENTER ));
		pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));
		panel.setLayout(new GridLayout(1, 1));
		pan_combo.setLayout(new BoxLayout(pan_combo,BoxLayout.X_AXIS));
		pan_chaine.setLayout(new BoxLayout(pan_chaine,BoxLayout.X_AXIS));
		pan_designation.setLayout(new BoxLayout(pan_designation,BoxLayout.X_AXIS));
		pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.Y_AXIS));


		setContentPane(panel);




	}


	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new Chaine("7");
				//frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
