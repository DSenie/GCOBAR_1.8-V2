package logiciel_etiq;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

public class etiquette extends JFrame {

	/**
	 * 
	 */
	
	private JPanel controlPanel = new JPanel();
	private JFrame frame = new JFrame();

	private static final long serialVersionUID = 1L;
	private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private String msg;
	private String Chemin = "c:\\GCOBAR\\";
	private String bdd = Chemin + Utilitaire.InitBdd() + ".accdb";

	private gestion_imp imp = new gestion_imp();
	 private JProgressBar progressBar;
	private JPanel pan_article = new JPanel();
	private JPanel pan_article_comb = new JPanel();
	private JPanel pan_article_lab = new JPanel();
	private JLabel article_lab = new JLabel("Liste des Articles");
	private List<String> list_ar;
	private jcombo article_comb;
	private JPanel pan_chaine = new JPanel();
	private JPanel pan_chaine_comb = new JPanel();
	private JPanel pan_chaine_lab = new JPanel();
	private JLabel chaine_lab = new JLabel("Liste des Chaine");
	private List<String> list_c;
	private jcombo chaine_comb;
	private JPanel pan_poste = new JPanel();
	private JPanel pan_model = new JPanel();
	private JPanel pan_model_comb = new JPanel();
	private JPanel pan_model_lab = new JPanel();
	private JPanel pan_poste_comb = new JPanel();
	private JPanel pan_poste_lab = new JPanel();
	private JLabel model_lab = new JLabel("Liste des Modèle ");
	private JLabel poste_lab = new JLabel("Liste des poste ");
	private List<String> list_p;
	private jcombo model_comb;
	private jcombo poste_comb;
	
    
    
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
	final JXDatePicker date_picker = new JXDatePicker();

	

	private JLabel type_etq = new JLabel("Type Etiquette");

	private JRadioButton simple = new JRadioButton("Etiquette Simple", false);
	private JRadioButton code_com = new JRadioButton("Etiquette +code commercial", true);
	private ButtonGroup bG1 = new ButtonGroup();
	private JPanel pan_radio1 = new JPanel();
	private JPanel pan_radio_lab1 = new JPanel();
	private JPanel pan_radio_bg1 = new JPanel();
	
	private JPanel pan_cont = new JPanel();
	private JPanel pan_cont_jtext = new JPanel();
	private JLabel cont_lab = new JLabel("Conteur");
	private JTextField cont_jtext = new JTextField();
	private JPanel pan_cont_lab = new JPanel();

	private JTextField assemblage_jtext = new JTextField();
	private JTextField assqc_jtext = new JTextField();
	private JTextField actqc_jtext = new JTextField();

	private JPanel pan = new JPanel();
	private JPanel pan_general = new JPanel();
	private JPanel pan_form = new JPanel();
	private JPanel panel;
	private JLabel entrep = new JLabel("         Entreprise");
	private JRadioButton enie = new JRadioButton("ENIE", true);
	private JRadioButton autre = new JRadioButton("AUTRE", false);
	private ButtonGroup bG = new ButtonGroup();
	private JPanel pan_radio = new JPanel();
	private JPanel pan_radio_lab = new JPanel();
	private JPanel pan_radio_bg = new JPanel();
	private JPanel pan_button = new JPanel();

	private JButton but_sauv = new JButton("Ajouter");
	private JButton but_modif = new JButton("Modifier");
	private JButton valid_ajou = new JButton("Valider");
	private JButton valid_modif = new JButton("Valider");
	private JButton imp_etq = new JButton("Imprimer L'étiquette");
	private JButton retour = new JButton("Retour");
	private String conteur = null;
    private static ArrayList<String> list_model_tr=new ArrayList<String>();
    gestion_article art=new gestion_article();
	private JPanel pan_gener = new JPanel();
	private JPanel pan_tab = new JPanel();
	int diff;
	int moy;
	int co;
    Thread att; 
    NumberFormat format = new DecimalFormat("000");
	NumberFormat formatter = new DecimalFormat("00");
	NumberFormat form = new DecimalFormat("0");
	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	Map<String, Object> parameters = new HashMap<String, Object>();
	etiquette(final String log) {
		final menu fr = new menu(log);
		fr.setVisible(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
		setIconImage(img);
		selectioncomb.windows(this, log, laf);
		composant(log);
	}

	@SuppressWarnings("deprecation")
	public void composant(final String logi_prio) {
	
		  date_picker.setDate(Calendar.getInstance().getTime());
		  date_picker.getEditor().setEditable(false);
		  date_picker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		  frame.setUndecorated(true);

		assqc_jtext.setText("0");
		actqc_jtext.setText("1");
		assemblage_jtext.setText("1");
		
		imp.select_conteur();
//		imp.conteur(date_picker.getDate());
		
		list_ar = new ArrayList<String>(
		Arrays.asList(new String[] { "---Selectionner un article-----" }));
		article_comb = new jcombo(list_ar.toArray());
		selectioncomb.selectarticle_etq(article_comb, enie, autre, this, logi_prio);
		list_c = new ArrayList<String>(
				Arrays.asList(new String[] { "--Sélectionner une Chaine--" }));

		chaine_comb = new jcombo(list_c.toArray());
		selectioncomb.selectchaine(chaine_comb, this, logi_prio);

		list_p = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un Poste-----"}));
		poste_comb = new jcombo(list_p.toArray());
		selectioncomb.selectposte( poste_comb,this,logi_prio);

		
		
		list_p = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un modèle-----"}));
	     model_comb = new jcombo(list_p.toArray());
	     list_model_tr=art.select_model();		     
	     for(int i=0;i<list_model_tr.size();i++)
		   {
		          //Pour affecter une valeur de base de donn?es ? un Combobox 
	    	 model_comb.addItem(list_model_tr.get(i));
			   i++;
		   }
	     model_comb.setWide(true);
		
		
		final Calendar cal = Calendar.getInstance(); // date du jour
		if (!selectioncomb.prv.contains("etiquette"))
			selectioncomb.prv.add("etiquette");

		panel = new JPanel() {
			/***  */
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
				img.paintIcon(this, g, 0, 0);
			}
		};

		final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,15);

		code_jtext.disable();
		article_comb.enable();
		chaine_comb.enable();
		model_comb.enable();
		if (chaine_comb.getItemCount() > 2)
			chaine_comb.setSelectedIndex(1);
		if (article_comb.getItemCount() > 2)

			article_comb.setSelectedIndex(1);
		action_chaine();

		LineBorder border = new LineBorder(Color.white, 1, true);
		TitledBorder titl2 = new TitledBorder(border, "Fiche Suiveuse",
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
				article_comb.enable();
				chaine_comb.enable();
				model_comb.enable();

				but_sauv.setVisible(false);
				but_modif.setVisible(false);
				retour.setVisible(true);
				valid_ajou.setVisible(true);
				valid_modif.setVisible(false);
				imp_etq.setVisible(false);
				String[] part_c = chaine_comb.getSelectedItem().toString().split(" ");
				String code_chaine = part_c[0];
				String[] art = article_comb.getSelectedItem().toString().split(" ");
				String arti = art[0]; // 004
		    	int indic=imp.select_indice(arti);

				imp.afficher_conteur(arti,code_chaine,date_picker.getDate(),indic);
				assemblage_jtext.setText("" + imp.count_ass);
				assqc_jtext.setText("" + imp.count_assqc);
				actqc_jtext.setText("" + imp.count_actqc);

			}
		});



		chaine_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action_chaine();
			}

		});
		
		
		date_picker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cal.setTime(date_picker.getDate());
				action_chaine();
			}

		});


		valid_ajou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att= new Thread(){
			    public void run() {
				msg = "";
				cal.setTime(date_picker.getDate());
				String date = format1.format(cal.getTime());
				String[] date_s = date.toString().split("/");
				String anne = date_s[2].substring(2); // 004
				String[] part_c = chaine_comb.getSelectedItem().toString().split(" ");
				String cc = part_c[0].substring(2); // 004
				String code_chaine = part_c[0];
				String[] art = article_comb.getSelectedItem().toString().split(" ");
				String arti = art[0]; // 004
				String des = article_comb.getSelectedItem().toString().replace(arti + " ", ""); // 004
				String[] poste = poste_comb.getSelectedItem().toString().split(" ");
				String cposte = poste[0]; // 004
				cal.setFirstDayOfWeek(Calendar.SUNDAY);
				
				int week1 = cal.get(Calendar.WEEK_OF_YEAR);
				String week = formatter.format(week1);
				int jour1 = cal.get(Calendar.DAY_OF_WEEK);
				String jour = form.format(jour1);
				String code;
		    	int indic=imp.select_indice(arti);

				conteur =imp.afficher_conteur(arti,code_chaine,date_picker.getDate(),indic);
				//System.out.println(date_picker.getDate()+" "+anne+" "+" "+week+" "+jour);
				
				
				if (enie.isSelected() == true) {
					code = "E" + anne + week + jour + cc + indic+conteur;
				} else {
					code = "A" + anne + week + jour + cc +indic+ conteur;
				}
				if ((chaine_comb.getSelectedIndex() != 0))
					code_jtext.setText(code);

				if (article_comb.getSelectedIndex() == 0) {
					msg += "Veuillez selectionner un article \n";
				}
				
//				else if (model_comb.getSelectedIndex() == 0) {
//					msg += "Veuillez selectionner un mod?le \n";
//				}
				
				else if (chaine_comb.getSelectedIndex() == 0) {
					msg += "Veuillez selectionner une chaine \n";
				} else if (cont_jtext.getText().equals("")) {
					msg += "Veuillez remplir le conteur \n";
				} else if (isValid(cont_jtext.getText()) == false) {
					msg += "Le compteur doit etre un nombre \n";
				}
				else if (com_jtext.getText().equals("")) {
					msg += "Veuillez saisir le code commercial \n";
				}
				
				if (msg.equals("")) {
					String etq=null;
					if(simple.isSelected()){			
						etq="S";
						 }
					else{
						
						etq="C";
					}
					 retour.setEnabled(false);
					 valid_ajou.setEnabled(false);
					co = Integer.parseInt(cont_jtext.getText());
					int af_cont = Integer.parseInt(conteur);
					progressBar = new JProgressBar(af_cont,af_cont+co-1);
					progressBar.setValue(0);
					
					//imp.conteur(date_picker.getDate());
					assemblage_jtext.setText("" + imp.count_ass);
					assqc_jtext.setText("" + imp.count_assqc);
					actqc_jtext.setText("" + imp.count_actqc);

                    JLabel titr=new JLabel("la création de "+co+" etiquette");
                    titr.setFont( titr.getFont().deriveFont(Font.BOLD|Font.ITALIC) );
                  
                    UIManager.put("nimbusOrange", (new Color(70,130,180)));
                    
					frame.add(controlPanel);
                    titr.setFont(police2);
                    progressBar.setStringPainted(true);
					controlPanel.add(titr);
					controlPanel.add(progressBar);

					progressBar.setPreferredSize( new Dimension (200, 25));
					frame.setSize(300, 70);
					frame.setLocationRelativeTo(null);
					NumberFormat form1 = new DecimalFormat("0");
                    String sf=form1.format(imp.ass/ imp.assqc );   //donne la chaine representant le double avec 2 chiffres apres la virgules
//			          int po;
					int actqc=imp.select_count_actqc();
					int assembl=imp.select_count_assembl();
					int assqc=imp.select_count_assqc();
					int ligne=imp.count_ligne();
					
//						System.out.println("tuple "+tuple+"po "+ po);
					   for(int i=af_cont;i<=af_cont+co-1;i++ ){
						   int po=ligne % Integer.parseInt(sf);
						   ligne++;
						 actqc++;
						 assembl++;
						if(actqc>imp.actqc) actqc=1;
						if(assembl>imp.ass)  assembl=1;
						 if(po==0){
							  if(assqc==3) assqc=1;
							   else{ assqc=assqc+1;}
							   		   }else{
							   			assqc=assqc+0;
						 }
						
						if (enie.isSelected() == true) {
							code = "E" + anne + week + jour+cc+indic
									+ format.format(i);
						} else {
							code = "A" + anne + week + jour+cc+indic 
									+ format.format(i);
						}
                   imp.ajouter_imprimer(arti, cposte, code_chaine, code,actqc,assembl,assqc,
								 com_jtext.getText(),date_picker.getDate());
						imp.ajouter_imprim(code, arti,com_jtext.getText(),etq);
						
					//	CConnect.close();
						progressBar.setValue(i);
						progressBar.setStringPainted(true);
						frame.validate();
						frame.setVisible(true);
					}
					   
//						if (enie.isSelected() == true) {
//							code = "E" + anne + week + jour+cc+indic
//									+ p;
//						} else {
//							code = "A" + anne + week + jour+cc+indic 
//									+ p;
//						}
					   
					   code_jtext.setText(code);
					   controlPanel.remove(titr);
					   controlPanel.remove(progressBar);
						frame.setVisible(false);
                          valid_ajou.setVisible(false);
                          but_sauv.setVisible(true);
                          retour.setVisible(false);
					JOptionPane.showMessageDialog(null, "l'étiquette a " + des	+ "  a été bien ajouté");
					 retour.setEnabled(true);
					 valid_ajou.setEnabled(true);
					imp_etq.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, msg);
				}	}}; 
				att.start();
			}
		});

		
		imp_etq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {// dispose();
				att= new Thread(){
					public void run(){
						//int indice=article_comb.getSelectedIndex();
						String[] part_c = chaine_comb.getSelectedItem().toString().split(" ");
						String chaine = part_c[0]; // 004
						String[] arti = article_comb.getSelectedItem().toString().split(" ");
						String code = arti[0].replaceAll("\\/","_"); // 004
						String date=date_picker.getEditor().getText().replaceAll("\\/", "_");
						String parcour ;String model;

			if(simple.isSelected()){
				 parcour = "C:\\GCOBAR\\pdf\\etq_simple\\"+code_jtext.getText()+"_"+code+"_"+chaine+"_"+date +"_Etiquette_simple"+".pdf";
				 model = "C:\\GCOBAR\\CODE\\reporttest.jrxml";
				//imp.update_imp_s("1");
				 }
			else{
				 parcour = "C:\\GCOBAR\\pdf\\etq_commericial\\"+code_jtext.getText()+"_"+code+"_"+chaine+"_"+date +"_Etiquette_commercial"+".pdf";
				 model = "C:\\GCOBAR\\CODE\\reportcommercial.jrxml";
			 //imp.update_imp_c("1");
			     }
                UIManager.put("nimbusOrange", (new Color(70,130,180)));

				try {
					if(simple.isSelected()){
						new BufferedReader(new FileReader("C:\\GCOBAR\\pdf\\etq_simple\\"+code_jtext.getText()+"_"+code+"_"+chaine+"_"+date +"_Etiquette_simple"+".pdf"));
				//	imp.update_imp_s("1");
					try {
						Desktop.getDesktop().open(
								new File("C:\\GCOBAR\\pdf\\etq_simple\\"+code_jtext.getText()+"_"+code+"_"+chaine+"_"+date+"_Etiquette_simple"+".pdf"));
					//	imp.update_imp_s("1");

					} catch (IOException p) {
						// TODO Auto-generated catch block
						p.printStackTrace();

					}}
					else{
						new BufferedReader(new FileReader( "C:\\GCOBAR\\pdf\\etq_commericial\\"+code_jtext.getText()+"_"+code+"_"+chaine+"_"+date+"_Etiquette_commercial"+".pdf"));
						imp.update_imp_c("1");
					try {
						Desktop.getDesktop().open(
								new File( "C:\\GCOBAR\\pdf\\etq_commericial\\"+code_jtext.getText()+"_"+code+"_"+chaine+"_"+date +"_Etiquette_commercial"+".pdf"));
						imp.update_imp_c("1");
					} catch (IOException p) {
						// TODO Auto-generated catch block
						p.printStackTrace();
					}}
				} catch (FileNotFoundException fnfe) {
					String code_text=code_jtext.getText().substring(0, 8);
					System.out.println("eee"+code_text);
					parameters.put("serial", code_text);
					//parameters.put("model", model_comb.getSelectedItem());
					selectioncomb.imprimer( bdd, parcour,model,parameters);
					 if(simple.isSelected()){
						imp.update_imp_s("1");}
					 else{imp.update_imp_c("1");	}
					imp.delete_imp();
					 //controlPanel.remove(progressBar);
				}

					}};

					att.start();

			}
		});
		but_sauv.setVisible(true);
		but_modif.setVisible(true);
		imp_etq.setVisible(false);
		valid_modif.setVisible(false);
		valid_ajou.setVisible(false);
		retour.setVisible(false);

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Edition",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan.setBorder(titl2);
				article_comb.setSelectedIndex(1);
				chaine_comb.setSelectedIndex(1);
				model_comb.setSelectedIndex(0);
				code_jtext.setText("");
				cont_jtext.setText("");
				but_sauv.setVisible(true);
				but_modif.setVisible(true);
				imp_etq.setVisible(false);
				retour.setVisible(false);
				valid_ajou.setVisible(false);
				valid_modif.setVisible(false);
				model_comb.enable();
				poste_comb.enable();
				chaine_comb.enable();
				article_comb.enable();
				code_jtext.disable();
				String[] part_c = chaine_comb.getSelectedItem().toString().split(" ");
				String code_chaine = part_c[0];
				String[] art = article_comb.getSelectedItem().toString().split(" ");
				String arti = art[0]; // 004
				 int indic=imp.select_indice(arti);
				imp.afficher_conteur(arti,code_chaine,date_picker.getDate(),indic);

				assemblage_jtext.setText("" + imp.count_ass);
				assqc_jtext.setText("" + imp.count_assqc);
				actqc_jtext.setText("" + imp.count_actqc);
				action_chaine();

			}
		});
		
		article_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assemblage_jtext.setText("" + imp.count_ass);
				assqc_jtext.setText("" + imp.count_assqc);
				actqc_jtext.setText("" + imp.count_actqc);

				if (article_comb.getSelectedIndex() == 0) {
					article_comb.setSelectedIndex(0);
					model_comb.setSelectedIndex(0);
					poste_comb.setSelectedIndex(0);
					chaine_comb.setSelectedIndex(1);
					
					code_jtext.setText("");
					cont_jtext.setText("");
				} else {
					if ( chaine_comb.getSelectedIndex() != 0) {
						String[] part_c = chaine_comb.getSelectedItem().toString().split(" ");
						String cc = part_c[0].substring(2); // 004
						String code_chaine = part_c[0];
						String arti = null;	
						if(article_comb.getSelectedIndex()!=0&&article_comb.getItemCount()!=0){
						 String[] art = article_comb.getSelectedItem().toString().split(" ");
						 arti = art[0]; // 004
					     }
                        int indic=imp.select_indice(arti);

						String code_art=	imp.afficher_conteur(arti,code_chaine,date_picker.getDate(),indic);
						cal.setTime(date_picker.getDate());
						
						String date = format1.format(cal.getTime());
						String[] date_s = date.toString().split("/");
						String anne = date_s[2].substring(2); // 004
	
						cal.setFirstDayOfWeek(Calendar.SUNDAY);
						int week1 = cal.get(Calendar.WEEK_OF_YEAR) ;
						String week = formatter.format(week1);
						int jour1 = cal.get(Calendar.DAY_OF_WEEK);
						String jour = form.format(jour1);
                        String code;
                        if (enie.isSelected() == true) {
							code = "E" + anne + week + jour + cc +indic+ code_art;
						} else {
							code = "A" + anne + week + jour + cc +indic+ code_art;
						}
						code_jtext.setText(code);
					}

				}
			}
		});



		try {
			UIManager.setLookAndFeel(laf);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		article_lab.setForeground(Color.white);
		model_lab.setForeground(Color.white);
		poste_lab.setForeground(Color.white);
		chaine_lab.setForeground(Color.white);
		code_lab.setForeground(Color.white);
		com_lab.setForeground(Color.white);
		cont_lab.setForeground(Color.white);
		type_etq.setForeground(Color.white);
		simple.setForeground(Color.white);
		code_com.setForeground(Color.white);
		entrep.setForeground(Color.white);
		enie.setForeground(Color.white);
		autre.setForeground(Color.white);
		date_lab.setForeground(Color.white);
		
		type_etq.setFont(police2);
		
		com_lab.setFont(police2);
		date_lab.setFont(police2);
		simple.setFont(police2);
		code_com.setFont(police2);
		entrep.setFont(police2);
		enie.setFont(police2);
		autre.setFont(police2);
		article_lab.setFont(police2);
		model_lab.setFont(police2);
		poste_lab.setFont(police2);
		chaine_lab.setFont(police2);
		code_lab.setFont(police2);
		cont_lab.setFont(police2);
		bG1.add(simple);
		bG1.add(code_com);
		bG.add(enie);
		bG.add(autre);
		pan_radio_lab.add(entrep);
		pan_radio.add(pan_radio_lab);
		pan_radio.add(pan_radio_bg);
		
		pan_radio_lab1.add(type_etq);
		pan_radio1.add(pan_radio_lab1);
		pan_radio1.add(pan_radio_bg1);
		pan_radio_bg1.add(simple);
		/*************************code_com pour le code commercial************/
		pan_radio_bg1.add(code_com);
		
		pan_radio_bg.add(enie);
		pan_radio_bg.add(autre);
		pan_radio_bg.setOpaque(false);
		pan_radio_lab.setOpaque(false);
		pan_radio.setOpaque(false);

		pan_radio_bg1.setOpaque(false);
		pan_radio_lab1.setOpaque(false);
		pan_radio1.setOpaque(false);
		
		
		pan_article.add(pan_article_lab);
		pan_article_lab.add(article_lab);
		pan_article.add(pan_article_comb);
		pan_article_comb.add(article_comb);

		pan_article.setOpaque(false);
		pan_article_lab.setOpaque(false);
		pan_article_comb.setOpaque(false);
		
		pan_date.setOpaque(false);
		pan_date_lab.setOpaque(false);
		pan_date_jtext.setOpaque(false);
		
		pan_date.add(pan_date_lab);
		pan_date_lab.add(date_lab);
		pan_date.add(pan_date_jtext);
		pan_date_jtext.add(date_picker);
		
		
		
		
		pan_model.add(pan_model_lab);
		pan_model_lab.add(model_lab);
		pan_model.add(pan_model_comb);
		pan_model_comb.add(model_comb);
		
		pan_poste.add(pan_poste_lab);
		pan_poste_lab.add(poste_lab);
		pan_poste.add(pan_poste_comb);
		pan_poste_comb.add(poste_comb);

		pan_model.setOpaque(false);
		pan_model_lab.setOpaque(false);
		pan_model_comb.setOpaque(false);
		
		pan_poste.setOpaque(false);
		pan_poste_lab.setOpaque(false);
		pan_poste_comb.setOpaque(false);

		pan_chaine.add(pan_chaine_lab);
		pan_chaine_lab.add(chaine_lab);
		pan_chaine.add(pan_chaine_comb);
		pan_chaine_comb.add(chaine_comb);

		pan_chaine.setOpaque(false);
		pan_chaine_lab.setOpaque(false);
		pan_chaine_comb.setOpaque(false);

		pan_code.add(pan_code_lab);
		pan_code_lab.add(code_lab);
		pan_code.add(pan_code_jtext);
		pan_code_jtext.add(code_jtext);
		
		pan_com.add(pan_com_lab);
		pan_com_lab.add(com_lab);
		pan_com.add(pan_com_jtext);
		pan_com_jtext.add(com_jtext);
		
		pan_com.setOpaque(false);
		pan_com_lab.setOpaque(false);
		pan_com_jtext.setOpaque(false);
		
		pan_code.setOpaque(false);
		pan_code_lab.setOpaque(false);
		pan_code_jtext.setOpaque(false);
		pan_cont.add(pan_cont_lab);
		pan_cont_lab.add(cont_lab);
		pan_cont.add(pan_cont_jtext);
		pan_cont_jtext.add(cont_jtext);

		pan_cont.setOpaque(false);
		pan_cont_lab.setOpaque(false);
		pan_cont_jtext.setOpaque(false);

		pan_button.add(retour);
		pan_button.add(valid_ajou);
		pan_button.add(valid_modif);
		pan_button.add(but_sauv);
		// pan_button.add(but_modif);
		pan_button.add(imp_etq);

		pan_button.setOpaque(false);

		pan_form.add(pan_radio);
		pan_form.add(pan_article);
		pan_form.add(pan_date);

		pan_form.add(pan_poste);
		//pan_form.add(pan_model);
		pan_form.add(pan_chaine);
		pan_form.add(pan_code);
		pan_form.add(pan_com);
		pan_form.add(pan_radio1);
		pan_form.add(pan_cont);

		pan_general.add(pan_button);
		pan_form.setOpaque(false);

		pan_gener.add(pan_form);
		pan_gener.add(pan_tab);
		pan.add(pan_gener);
		pan.add(pan_general);

		panel.add(pan);
		pan_gener.setOpaque(false);
		pan_tab.setOpaque(false);
		pan_general.setOpaque(false);
		pan.setOpaque(false);
		
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));

		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));

		but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_sauv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		imp_etq.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		but_modif.setPreferredSize(new Dimension(120, 33));
		but_sauv.setPreferredSize(new Dimension(120, 33));
		imp_etq.setPreferredSize(new Dimension(170, 33));
		retour.setPreferredSize(new Dimension(120, 33));
		valid_modif.setPreferredSize(new Dimension(120, 33));
		valid_ajou.setPreferredSize(new Dimension(120, 33));


		
		article_comb.setPreferredSize(new Dimension(210, 30));
		date_picker.setPreferredSize(new Dimension(210, 30));
		chaine_comb.setPreferredSize(new Dimension(210, 30));
		model_comb.setPreferredSize(new Dimension(210, 30));
		poste_comb.setPreferredSize(new Dimension(210, 30));
		com_jtext.setPreferredSize(new Dimension(210, 30));
		code_jtext.setPreferredSize(new Dimension(210, 30));
		cont_jtext.setPreferredSize(new Dimension(210, 30));

		pan_article.setLayout(new BoxLayout(pan_article, BoxLayout.X_AXIS));
		

		pan_date.setLayout(new BoxLayout(pan_date, BoxLayout.X_AXIS));
		pan_poste.setLayout(new BoxLayout(pan_poste, BoxLayout.X_AXIS));
		pan_model.setLayout(new BoxLayout(pan_model, BoxLayout.X_AXIS));
		pan_chaine.setLayout(new BoxLayout(pan_chaine, BoxLayout.X_AXIS));
		pan_code.setLayout(new BoxLayout(pan_code, BoxLayout.X_AXIS));
		pan_com.setLayout(new BoxLayout(pan_com, BoxLayout.X_AXIS));

		pan_cont.setLayout(new BoxLayout(pan_cont, BoxLayout.X_AXIS));
		pan_radio1.setLayout(new BoxLayout(pan_radio1, BoxLayout.X_AXIS));

		pan_radio.setLayout(new BoxLayout(pan_radio, BoxLayout.X_AXIS));

		pan_radio_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_radio_bg.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_radio_lab.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0));
		pan_radio_bg.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		
		pan_radio_lab1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_radio_bg1.setLayout(new GridLayout(2,0));
		pan_radio_lab1.setBorder(BorderFactory.createEmptyBorder(0, 220, 0, 0));
		pan_radio_bg1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		pan_article_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_article_lab.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 0));
		pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
	
		
		pan_date_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_date_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 297, 0, 0));
		pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_model_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_model_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_model_lab.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 0));
		pan_model_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		pan_poste_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_poste_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_poste_lab.setBorder(BorderFactory.createEmptyBorder(0, 230, 0, -15));
		pan_poste_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_chaine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, -15));
		pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_code_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_code_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_code_lab.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, 0));
		pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_com_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_com_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_com_lab.setBorder(BorderFactory.createEmptyBorder(0, 207, 0, 0));
		pan_com_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		
		pan_cont_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_cont_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_cont_lab.setBorder(BorderFactory.createEmptyBorder(0, 270, 0, 0));
		pan_cont_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

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

					new etiquette("7");
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

	public String action_chaine() {
		final Calendar cal = Calendar.getInstance(); // date du jour

			


			if (chaine_comb.getSelectedIndex() != 0	&& chaine_comb.getItemCount() >= 1&&chaine_comb.getSelectedIndex() != chaine_comb.getItemCount() - 1) {
				String[] part_c = chaine_comb.getSelectedItem().toString().split(" ");
				String cc = part_c[0].substring(2); // 004
				String code_chaine = part_c[0];
				String arti = null;

				if(article_comb.getSelectedIndex()!=0){
					String[] art = article_comb.getSelectedItem().toString().split(" ");
					 arti = art[0]; // 004
				}
				int indic=imp.select_indice(arti);

				String aff=imp.afficher_conteur(arti,code_chaine,date_picker.getDate(),indic);
				cal.setTime(date_picker.getDate());
				SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
				String date = format1.format(cal.getTime());
				String[] date_s = date.toString().split("/");
				String anne = date_s[2].substring(2); // 004

				
				cal.setFirstDayOfWeek(Calendar.SUNDAY);
				int week1 = cal.get(Calendar.WEEK_OF_YEAR) ;
				String week = formatter.format(week1);
				int jour1 = cal.get(Calendar.DAY_OF_WEEK);
				String jour = form.format(jour1);
				String code;
				if (enie.isSelected() == true) {
					code = "E" + anne + week + jour + cc+indic
							+ aff;
				} else {
					code = "A" + anne + week + jour + cc+indic
							+aff;
				}
				code_jtext.setText(code);
				return code;
			} else {

				code_jtext.setText("");
				return "";			}
		
		
	}

//	public void serialnumber() {
//		code_jtext.addKeyListener(new KeyAdapter() {
//			public void keyReleased(KeyEvent e) {
//
//				assemblage_jtext.setText(imp.contass);
//				assqc_jtext.setText(imp.contqc);
//				actqc_jtext.setText(imp.actqcc);
//				imp.select_fiche(code_jtext.getText());
//				chaine_comb.setSelectedItem(imp.chaine + " "
//						+ imp.chaine_design);
//				model_comb.setSelectedItem(imp.cpost + " " + imp.intitule);
//				article_comb.setSelectedItem(imp.article + " "
//						+ imp.designation);
//
//			}
//		});
//
//	}


}
