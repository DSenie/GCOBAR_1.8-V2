package logiciel_etiq.View.Etiquette;

import logiciel_etiq.View.generale;

import javax.swing.*;

import java.awt.*;


import static logiciel_etiq.constant.etiq_autre;


public class autre_etiquette extends generale {


	private JPanel pan_etiquette = new JPanel();
	private JPanel pan_etiquette_area = new JPanel();
	private JPanel pan_etiquette_lab = new JPanel();
	private JLabel etiquette_lab = new JLabel("Message a imprimer");
	private JTextArea etiquette_area=new JTextArea( ) ;


	private JPanel pan = new JPanel();
	private JPanel pan_general = new JPanel();
	private JPanel pan_button = new JPanel();


	private JPanel pan_dimension = new JPanel();
	private JPanel pan_dimension_lab = new JPanel();
	private JPanel pan_dimension_comb= new JPanel();
	private JLabel dimension_lab = new JLabel("Dimension");
	private JComboBox<String> dimension_comb =new JComboBox<>();

	private JPanel pan_gener = new JPanel();


	public autre_etiquette(final String log) {
		composant(log);
	}

	public void composant(final String logi_prio) {

		JPanel panel = generalPanle();
		initFrame("Autre Etiquette", panel, this, logi_prio,"Autre Etiquette");

		remplirActDimComb( dimension_comb,"autre_etiquette");



		


		imp_etq.addActionListener(
				e -> {

					code_aimprimer.clear();
					if (dimension_comb.getSelectedIndex() == 0) {
						JOptionPane
								.showMessageDialog(
										null,
										" Vous dever choisir la dimension de l'étiquette ",
										"", JOptionPane.INFORMATION_MESSAGE);
					} else {

						String parcour = etiq_autre + String.valueOf(dimension_comb.getSelectedItem()).split(" ")[1] + ".pdf";

							code_aimprimer.add(etiquette_area.getText());

							parameters.put("autre", code_aimprimer);

							Imprimer_pdf_Final( parcour, report, parameters);



					}

				});







 /////********************** label style********************************/
		styleLabel(etiquette_lab);
		styleLabel(dimension_lab);

 /////********************** composant  style********************************/

		styleComponent(dimension_comb);
		etiquette_area.setPreferredSize(new Dimension(310, 200));
		styleButton(imp_etq,pan_button);

///*************************add component on panel ************/
		pan_dimension.add(pan_dimension_lab);
		pan_dimension.add(pan_dimension_comb);
		pan_dimension_lab.add(dimension_lab);
		pan_dimension_comb.add(dimension_comb);

		pan_etiquette.add(pan_etiquette_lab);
		pan_etiquette_lab.add(etiquette_lab);
		pan_etiquette.add(pan_etiquette_area);
		pan_etiquette_area.add(etiquette_area);


		pan_general.add(pan_gener);
		pan_gener.add(pan_dimension);

		pan_gener.add(pan_etiquette);
		pan_general.add(pan_button);

		pan.add(pan_general);
		panel.add(pan);



//*************************visibilite de panel ************/

		pan_etiquette.setOpaque(false);
		pan_etiquette_lab.setOpaque(false);
		pan_etiquette_area.setOpaque(false);
		pan_dimension.setOpaque(false);
		pan_dimension_lab.setOpaque(false);
		pan_dimension_comb.setOpaque(false);

		pan_button.setOpaque(false);
		pan_gener.setOpaque(false);

		pan_general.setOpaque(false);
		pan.setOpaque(false);




		//*************************layout dans le panel ************/

		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		pan_general.setLayout(new BoxLayout(pan_general, BoxLayout.Y_AXIS));
		pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.Y_AXIS));
		pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.Y_AXIS));
		pan_etiquette.setLayout(new BoxLayout(pan_etiquette, BoxLayout.Y_AXIS));

		pan_etiquette_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_etiquette_area.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_etiquette_lab.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));





		setContentPane(panel);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {

				new autre_etiquette("7");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}


}
