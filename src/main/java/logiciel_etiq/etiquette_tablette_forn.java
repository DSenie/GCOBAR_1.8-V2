package logiciel_etiq;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.*;
import java.util.List;
import java.util.*;

public class etiquette_tablette_forn extends JFrame {


	private JPanel controlPanel = new JPanel();
	private JFrame frame = new JFrame();

	private static final long serialVersionUID = 1L;
	//private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private String msg;
	private String Chemin = "c:\\GCOBAR\\";
	private String bdd = Chemin + Utilitaire.InitBdd() + ".accdb";
	Map<String, Object> parameters = new HashMap<String, Object>();

	private gestion_imp imp = new gestion_imp();
	private JProgressBar progressBar;

    String sn_av_modif="";
	ArrayList<String> listrc1 = new ArrayList<String>();


	private JPanel pan_model = new JPanel();
	private JPanel pan_model_jtext = new JPanel();
	private JPanel pan_model_lab = new JPanel();
	private JLabel model_lab = new JLabel("Modèle");

	//private JTextField model_jtext = new JTextField();
	private List<String> list_m;
	private jcombo model_jtext;

	private JPanel pan_color = new JPanel();
	private JPanel pan_color_comb = new JPanel();
	private JPanel pan_color_lab = new JPanel();
	private JLabel color_lab = new JLabel("Liste des Couleur");
	private List<String> list_c;
	private JComboBox color_comb=new JComboBox();





	private JPanel pan_imei1_lab = new JPanel();
	private JPanel pan_imei1 = new JPanel();
	private JPanel pan_imei1_jtext = new JPanel();
	private JLabel imei1_lab = new JLabel("SN");
	JTextField imei1_jtext;
	//private JTextField imei1_jtext = new JTextField();

/*	private JPanel pan_imei2_lab = new JPanel();
	private JPanel pan_imei2 = new JPanel();
	private JPanel pan_imei2_jtext = new JPanel();
	private JLabel imei2_lab = new JLabel("IMEI2");
    JFormattedTextField imei2_jtext;
	//private JTextField imei2_jtext = new JTextField();*/


	private JPanel pan_dimension = new JPanel();
	private JPanel pan_dimension_lab = new JPanel();
	private JPanel pan_dimension_comb = new JPanel();
	private JLabel dimension_lab = new JLabel("Etiquette dimension");
	private JComboBox dimension_comb= new JComboBox();



	private JPanel pan = new JPanel();
	private JPanel pan_general = new JPanel();
	private JPanel pan_form = new JPanel();
	private JPanel pan_gener = new JPanel();

	private JPanel panel;


	private JPanel pan_button = new JPanel();

	private JButton but_sauv = new JButton("Ajouter");
	private JButton valid_ajou = new JButton("Valider");

	private JButton but_modif = new JButton("Modifier");
	private JButton valid_modif= new JButton("Valider");

	private JButton imp_etq = new JButton("Imprimer L'étiquette");

	private JButton retour = new JButton("Retour");

	private String conteur = null;

	gestion_article art=new gestion_article();
	String report;

	int diff;
	int moy;
	int co;
	Thread att;
	ArrayList <String>list_dimension= new ArrayList<String>() ;

	String date_jour;
	NumberFormat format = new DecimalFormat("00000");



	etiquette_tablette_forn(final String log) {
		final menu fr = new menu(log);
		fr.setVisible(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
		setIconImage(img);
		selectioncomb.windows(this, log);
		composant(log);
	}

	@SuppressWarnings("deprecation")
	public void composant(final String logi_prio) {
		Date actuelle=new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		date_jour = dateFormat.format(actuelle);


       		/*MaskFormatter num = null;
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
		}*/




		//imei2_jtext= new  JFormattedTextField(num);
		imei1_jtext= new  JTextField();




		dimension_comb.addItem("--- Sélectionner la dimension de l'etiquette ----");

		list_dimension=imp.select_dimension_etq("etq_tablette_forn");

		for(int i=0;i<list_dimension.size();i++)
		{
			//Pour affecter une valeur de base de donn?es ? un Combobox
			dimension_comb.addItem(list_dimension.get(i));

		}


		frame.setUndecorated(true);

		color_comb.setEditable(true);
//		model_jtext.setEditable(true);



		list_m = new ArrayList<String>(Arrays.asList(new String[] { "---Selectionner un article-----" }));
		model_jtext = new jcombo(list_m.toArray());
		selectioncomb.selectarticle_tablette(model_jtext, this, logi_prio);

		model_jtext.setSelectedIndex(0);

       /* list_m = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un Modele----"}));
        model_jtext.addItem("---Selectionner un Modele----");
        list_m=imp.select_modele();
        for(int i=0;i<list_m.size();i++)
        {
            //Pour affecter une valeur de base de donn?es ? un Combobox
            model_jtext.addItem(list_m.get(i));

        }*/




       /* model_jtext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                boolean ex=produit.exist(model_jtext,model_jtext.getEditor().getItem().toString());
                if((ex==false))
                {
                    int cont =model_jtext.getItemCount()+2;
                    model_jtext.addItem(model_jtext.getEditor().getItem().toString());
                }


            }});*/






		list_c = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner une couleur-----"}));
		color_comb.addItem("---Selectionner une couleur-----");
		list_c=imp.select_color_tablette();
		System.out.println("ouyi"+list_c);
		for(int i=0;i<list_c.size();i++)
		{
			//Pour affecter une valeur de base de donn?es ? un Combobox
			color_comb.addItem(list_c.get(i));

		}

		color_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean ex=produit.exist(color_comb,color_comb.getEditor().getItem().toString());
				if((ex==false))
				{
					int cont =color_comb.getItemCount()+2;
					color_comb.addItem(color_comb.getEditor().getItem().toString());
				}


			}});

		if (!selectioncomb.prv.contains("etiquette_tablette"))
			selectioncomb.prv.add("etiquette_tablette");

		panel = new JPanel() {
			/***  */
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
				img.paintIcon(this, g, 0, 0);
			}
		};

		final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,15);




		but_sauv.setVisible(true);
		retour.setVisible(false);
		valid_ajou.setVisible(false);

		but_modif.setVisible(false);
		valid_modif.setVisible(false);


		imp_etq.setVisible(false);
		//	imp_imei.setVisible(false);

		LineBorder border = new LineBorder(Color.white, 1, true);
		TitledBorder titl2 = new TitledBorder(border, "Etiquette portable",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		pan.setBorder(titl2);

		but_sauv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Ajouter",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan.setBorder(titl2);
				but_sauv.setVisible(false);
				retour.setVisible(true);
				valid_ajou.setVisible(true);
				imp_etq.setVisible(false);
				but_modif.setVisible(false);
				valid_modif.setVisible(false);
			}
		});


		but_modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//change
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Modifier",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan.setBorder(titl2);
				but_sauv.setVisible(false);
				retour.setVisible(true);
				valid_ajou.setVisible(false);
				imp_etq.setVisible(false);
				but_modif.setVisible(false);
				valid_modif.setVisible(true);
			}
		});


		imei1_jtext.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {

				if(imei1_jtext.getText().trim().length()==0){
					imei1_jtext.setText("");
				}

				imei(evt);

              /*  if(imei1_jtext.getText().trim().length()==15&&evt.getKeyCode()== KeyEvent.VK_ENTER)
				{
					imei2_jtext.requestFocus();
				}*/



			}
		});

		/*imei2_jtext.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {

					if(imei2_jtext.getText().trim().length()==0){
						imei2_jtext.setText("");
					}
						imei(evt);

				}
			});*/






		valid_ajou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att= new Thread(){
					public void run() {
						msg = "";
						String code;
						validationChamp();

						if (msg.equals("")) {
							retour.setVisible(true);
							valid_ajou.setVisible(false);
							but_sauv.setVisible(false);
							imp_etq.setVisible(true);
							valid_modif.setVisible(false);
							but_modif.setVisible(false);
							String[] art = model_jtext.getSelectedItem().toString().split(" ");
							String arti = art[0]; // 004
							imp.insert_tablette_fournisseur(arti,color_comb.getSelectedItem().toString(),imei1_jtext.getText(),date_jour);

							JOptionPane.showMessageDialog(null, "l'étiquette portable " + imei1_jtext.getText()	+ "  a été bien ajouté");

						} else {
							JOptionPane.showMessageDialog(panel, msg, "Error", JOptionPane.ERROR_MESSAGE);

							//JOptionPane.showMessageDialog(null, msg);
						}	}};
				att.start();
			}
		});




		valid_modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att= new Thread(){
					public void run() {
						msg = "";
						validationChamp();

						if (msg.equals("")) {
							selectioncomb.closePdf();

							retour.setVisible(true);
							valid_ajou.setVisible(false);
							but_sauv.setVisible(false);
							imp_etq.setVisible(true);
							valid_modif.setVisible(false);
							but_modif.setVisible(false);
                            System.out.println("sn_av_modif"+sn_av_modif);
							imp.delete_tablette_fournisseur(sn_av_modif);
							String[] art = model_jtext.getSelectedItem().toString().split(" ");
							String arti = art[0]; // 0
							imp.insert_tablette_fournisseur(arti,color_comb.getSelectedItem().toString(),imei1_jtext.getText(), date_jour);

							JOptionPane.showMessageDialog(null, "l'étiquette tablette " + imei1_jtext.getText()	+ "  a été bien Modifier");

						} else {
							JOptionPane.showMessageDialog(panel, msg, "Error", JOptionPane.ERROR_MESSAGE);

							//JOptionPane.showMessageDialog(null, msg);
						}	}};
				att.start();
			}
		});




		dimension_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(dimension_comb.getSelectedIndex()!=0)
					report=imp.select_report(dimension_comb.getSelectedItem().toString(),"etq_tablette_forn");


			}});


		imp_etq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {// dispose();
				att= new Thread(){
					public void run(){
						if(dimension_comb.getSelectedIndex()!=0){

							String parcour ;String model;
							parcour = "C:\\GCOBAR\\pdf\\etq_tablette_fournisseur\\"+dimension_comb.getSelectedItem()+"_"+imei1_jtext.getText()+"_Etiquette_tablette"+".pdf";
							model = "C:\\GCOBAR\\CODE\\"+report;
							File fichier = new File(parcour);
							fichier.delete();
							UIManager.put("nimbusOrange", (new Color(70,130,180)));

							try {

								//
								new BufferedReader(new FileReader(
										"C:\\GCOBAR\\pdf\\etq_tablette_fournisseur\\"+dimension_comb.getSelectedItem()+"_"+imei1_jtext.getText()+"_Etiquette_tablette"+".pdf"));

								try {
									Desktop.getDesktop().open(new File(
											"C:\\GCOBAR\\pdf\\etq_tablette_fournisseur\\"+dimension_comb.getSelectedItem()+"_"+imei1_jtext.getText()+"_Etiquette_tablette"+".pdf"));

								} catch (IOException p) {
									// TODO Auto-generated catch block
									p.printStackTrace();

								}}

							catch (FileNotFoundException fnfe) {

								listrc1.add(imei1_jtext.getText());

								//	parameters.put("serial",imei1_jtext.getText());
								//parameters.put("imei2",imei2_jtext.getText());
								selectioncomb.closePdf();

								parameters.put("serial",imei1_jtext.getText());
								System.out.println("fffffff"+listrc1);
								selectioncomb.imprimer( bdd, parcour,model,parameters);
								//selectioncomb.imprimer("sn_param",listrc1,bdd,parcour,model);
								listrc1.clear();
								System.out.println("fffffff"+listrc1);
								//imp.setupdate_portable();

								//controlPanel.remove(progressBar);





								imei1_jtext.setText("");
								//	imei2_jtext.setText("");

								but_sauv.setVisible(true);
								imp_etq.setVisible(false);
								valid_ajou.setVisible(false);
								retour.setVisible(false);
								valid_modif.setVisible(false);
								but_modif.setVisible(false);
							}}
						else{  JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette");}

					}};

				att.start();

			}
		});




		but_sauv.setVisible(true);
		imp_etq.setVisible(false);
		//imp_imei.setVisible(false);
		valid_ajou.setVisible(false);
		retour.setVisible(false);

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Edition",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan.setBorder(titl2);

			//	color_comb.setSelectedIndex(0);
				model_jtext.setSelectedIndex(0);
				imei1_jtext.setText("");
				//imei2_jtext.setText("");
				but_sauv.setVisible(true);
				but_modif.setVisible(false);
				imp_etq.setVisible(false);
				//	imp_imei.setVisible(false);

				retour.setVisible(false);
				valid_ajou.setVisible(false);
				valid_modif.setVisible(false);

				color_comb.enable();

			}
		});




		generale.styles("Nimbus");
		SwingUtilities.updateComponentTreeUI(this);



		model_lab.setForeground(Color.white);
		color_lab.setForeground(Color.white);
		imei1_lab.setForeground(Color.white);
		//imei2_lab.setForeground(Color.white);
		dimension_lab.setForeground(Color.white);


		model_lab.setFont(police2);
		imei1_lab.setFont(police2);
		//imei2_lab.setFont(police2);
		color_lab.setFont(police2);
		dimension_lab.setFont(police2);


		/*************************code_com pour le code commercial************/


		pan_dimension.add(pan_dimension_lab);
		pan_dimension_lab.add(dimension_lab);
		pan_dimension.add(pan_dimension_comb);
		pan_dimension_comb.add(dimension_comb);

		pan_dimension.setOpaque(false);
		pan_dimension_lab.setOpaque(false);
		pan_dimension_comb.setOpaque(false);




		pan_model.add(pan_model_lab);
		pan_model_lab.add(model_lab);
		pan_model.add(pan_model_jtext);
		pan_model_jtext.add(model_jtext);



		pan_model.setOpaque(false);
		pan_model_lab.setOpaque(false);
		pan_model_jtext.setOpaque(false);



		pan_color.add(pan_color_lab);
		pan_color_lab.add(color_lab);
		pan_color.add(pan_color_comb);
		pan_color_comb.add(color_comb);

		pan_color.setOpaque(false);
		pan_color_lab.setOpaque(false);
		pan_color_comb.setOpaque(false);


		pan_imei1.add(pan_imei1_lab);
		pan_imei1_lab.add(imei1_lab);
		pan_imei1.add(pan_imei1_jtext);
		pan_imei1_jtext.add(imei1_jtext);

	/*	pan_imei2.add(pan_imei2_lab);
		pan_imei2_lab.add(imei2_lab);
		pan_imei2.add(pan_imei2_jtext);
		pan_imei2_jtext.add(imei2_jtext);*/

		pan_imei1.setOpaque(false);
		pan_imei1_lab.setOpaque(false);
		pan_imei1_jtext.setOpaque(false);

		/*pan_imei2.setOpaque(false);
		pan_imei2_lab.setOpaque(false);
		pan_imei2_jtext.setOpaque(false);*/


		pan_button.add(retour);
		pan_button.add(valid_ajou);
		pan_button.add(but_sauv);
		pan_button.add(but_modif);
		pan_button.add(valid_modif);

		pan_button.add(imp_etq);
		//pan_button.add(imp_imei);

		pan_button.setOpaque(false);


		pan_form.add(pan_model);

		pan_form.add(pan_color);
		pan_form.add(pan_imei1);

		//pan_form.add(pan_model);
		//pan_form.add(pan_imei2);

		pan_form.add(pan_dimension);


		pan_general.add(pan_button);
		pan_form.setOpaque(false);

		pan_gener.add(pan_form);
		pan.add(pan_gener);
		pan.add(pan_general);

		panel.add(pan);
		pan_gener.setOpaque(false);
		pan_general.setOpaque(false);
		pan.setOpaque(false);

		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));

		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));

		but_sauv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		imp_etq.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		//	imp_imei.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		but_sauv.setPreferredSize(new Dimension(120, 33));
		imp_etq.setPreferredSize(new Dimension(170, 33));


		but_modif.setPreferredSize(new Dimension(120, 33));
		valid_modif.setPreferredSize(new Dimension(120, 33));
		//	imp_imei.setPreferredSize(new Dimension(170, 33));

		retour.setPreferredSize(new Dimension(120, 33));
		valid_ajou.setPreferredSize(new Dimension(120, 33));



		color_comb.setPreferredSize(new Dimension(210, 30));
		model_jtext.setPreferredSize(new Dimension(210, 30));
		imei1_jtext.setPreferredSize(new Dimension(210, 30));
		//imei2_jtext.setPreferredSize(new Dimension(210, 30));
		dimension_comb.setPreferredSize(new Dimension(210, 30));



		pan_model.setLayout(new BoxLayout(pan_model, BoxLayout.X_AXIS));
		pan_color.setLayout(new BoxLayout(pan_color, BoxLayout.X_AXIS));
		pan_imei1.setLayout(new BoxLayout(pan_imei1, BoxLayout.X_AXIS));
		//pan_imei2.setLayout(new BoxLayout(pan_imei2, BoxLayout.X_AXIS));
		pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));





		pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


		pan_model_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_model_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_model_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
		pan_model_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


		pan_color_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_color_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_color_lab.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
		pan_color_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_imei1_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_imei1_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_imei1_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
		pan_imei1_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		/*pan_imei2_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_imei2_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_imei2_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
		pan_imei2_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));*/


		setTitle("imprime");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					new etiquette_tablette_forn("7");
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean isValid(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}


public void  imei(KeyEvent evt){
	//imei1_jtext.setText("");
	//imei2_jtext.setText("");

	//imei1_jtext.setFocusLostBehavior(JTextField.PERSIST);
		if(evt.getKeyCode() == evt.VK_ENTER && (!but_modif.isVisible() && !valid_modif.isVisible()) ){
		    System.out.println(but_modif.isVisible() +"jjjjjjjbbb"+valid_modif.isVisible());
			ArrayList <String> resultat= new ArrayList<String>();
			if(!imei1_jtext.getText().trim().equals("")){
				resultat=imp.select_tablette_fourn(imei1_jtext.getText());
				System.out.println("res"+resultat);
				if(resultat.size()>0){
					sn_av_modif=imei1_jtext.getText().toString();
					model_jtext.setSelectedItem(resultat.get(0)+" "+resultat.get(1));
					color_comb.setSelectedItem(resultat.get(2));
					imei1_jtext.setText(resultat.get(3));
					//imei2_jtext.setText(resultat.get(3));
					imp_etq.setVisible(true);
					but_sauv.setVisible(false);
					retour.setVisible(true);

					valid_ajou.setVisible(false);
					valid_modif.setVisible(false);
					but_modif.setVisible(true);
				}
				else{
					imp_etq.setVisible(false);
					but_sauv.setVisible(true);
					retour.setVisible(true);
					valid_ajou.setVisible(false);
					valid_modif.setVisible(false);
					but_modif.setVisible(false);


				}}
		}
	}


	public void validationChamp(){
		if (imei1_jtext.getText().trim().equals("")) {
			msg += "Veuillez remplir  le Sn \n";
		}
/*	else if (imei2_jtext.getText().trim().equals("")) {
		msg += "Veuillez remplir  le Imei 2  \n";
	}

	else if(imei1_jtext.getText().trim().length()<15)
	{
		msg += "Le code imei 1 doit contenir 15 nombres  \n";
	}
	else if(imei2_jtext.getText().trim().length()<15)
	{
		msg += "Le code imei 1 doit contenir 15 nombres  \n";
	}*/
		else if (color_comb.getSelectedIndex() == 0) {
			msg += "Veuillez selectionner une couleur \n";
		}
		else if (model_jtext.getSelectedIndex()==0) {
			msg += "Veuillez selectionner  l'article \n";
		}
/*	else if (imei1_jtext.getText().trim().equals(imei2_jtext.getText().trim())) {
		msg += "Le code Imei 1 est égale au code Imei 2 \n";
	}*/

	}

}

