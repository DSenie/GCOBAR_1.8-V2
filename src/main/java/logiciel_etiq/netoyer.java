package logiciel_etiq;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

public class netoyer extends JFrame {

	private JFrame frame = new JFrame();

	private static final long serialVersionUID = 1L;
	//private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private String msg;
	private gestion_imp imp = new gestion_imp();
	 //private JProgressBar progressBar;
	 
	 
	private JPanel pan_article = new JPanel();
	private JPanel pan_article_comb = new JPanel();
	private JPanel pan_article_lab = new JPanel();
	private JLabel article_lab = new JLabel("Liste des Articles");
	

	
	private List<String> list_ar;
	private jcombo article_comb;
	private jcombo chaine_comb;

	
	private JPanel pan_chaine = new JPanel();
	private JPanel pan_chaine_comb = new JPanel();
	private JPanel pan_chaine_lab = new JPanel();
	private JLabel chaine_lab = new JLabel("Liste des Chaine");
	
	
	private List<String> list_c;

	private JPanel pan_code = new JPanel();
	private JPanel pan_code_jtext = new JPanel();
	private JPanel pan_code_lab = new JPanel();
	private JLabel code_lab = new JLabel("Serial Number");
	private JTextField code_jtext = new JTextField();



	
	private JPanel pan_date = new JPanel();
	private JPanel pan_date_jtext = new JPanel();
	private JPanel pan_date_lab = new JPanel();
	private JLabel date_lab = new JLabel("Date");
	final JXDatePicker date_picker = new JXDatePicker();

	
	
	private JPanel pan_cont = new JPanel();
	private JPanel pan_cont_jtext = new JPanel();
	private JLabel cont_lab = new JLabel("Conteur Debut");
	private JTextField cont_jtext = new JTextField();
	private JPanel pan_cont_lab = new JPanel();

	private JPanel pan_cont1 = new JPanel();
	private JPanel pan_cont_jtext1 = new JPanel();
	private JLabel cont_lab1 = new JLabel("Conteur Fin");
	private JTextField cont_jtext1 = new JTextField();
	private JPanel pan_cont_lab1 = new JPanel();

//	private JTextField assemblage_jtext = new JTextField();
//	private JTextField assqc_jtext = new JTextField();
//	private JTextField actqc_jtext = new JTextField();

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

	private JButton but_net = new JButton("Nettoyer");
	private JButton valid_ajou = new JButton("Valider");
	private JButton retour = new JButton("Retour");
	
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
	
	
	netoyer(final String log) {
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
	
		  date_picker.setDate(Calendar.getInstance().getTime());
		  date_picker.getEditor().setEditable(false);
		  date_picker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		  frame.setUndecorated(true);

		
//		imp.conteur(date_picker.getDate());
		
		list_ar = new ArrayList<String>(
		Arrays.asList(new String[] { "---Selectionner un article-----" }));
		
		
	
		
		article_comb = new jcombo(list_ar.toArray());

		selectioncomb.selectarticle_etq(article_comb, enie, autre, this, logi_prio);

		list_c = new ArrayList<String>(
				Arrays.asList(new String[] { "--Sélectionner une Chaine--" }));
		chaine_comb = new jcombo(list_c.toArray());
		selectioncomb.selectchaine(chaine_comb, this, logi_prio);
		
		code_jtext.disable();

		final Calendar cal = Calendar.getInstance(); // date du jour
		if (!selectioncomb.prv.contains("netoyer"))
			selectioncomb.prv.add("netoyer");

		panel = new JPanel() {
			/***  */
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
				img.paintIcon(this, g, 0, 0);
			}
		};

		final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,15);

		
		action_chaine();

		LineBorder border = new LineBorder(Color.white, 1, true);
		TitledBorder titl2 = new TitledBorder(border, "Fiche Suiveuse",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		pan.setBorder(titl2);

		but_net.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Netoyer",
				TitledBorder.DEFAULT_POSITION,
				TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan.setBorder(titl2);
				but_net.setVisible(false);
				valid_ajou.setVisible(true);
				retour.setVisible(true);
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
			    public void run(){
				msg ="";

				if (article_comb.getSelectedIndex() == 0) {
					msg += "Veuillez selectionner les deux articles \n";
				}
				
				else if (chaine_comb.getSelectedIndex() == 0) {
					msg += "Veuillez selectionner une chaine \n";
				} else if (cont_jtext.getText().equals("")) {
					msg += "Veuillez remplir le conteur de debut \n";
				}  else if (cont_jtext1.getText().equals("")) {
					msg += "Veuillez remplir le conteur de fin \n";
				} else if (isValid(cont_jtext.getText()) == false||isValid(cont_jtext1.getText()) == false) {
					msg += "Le compteur doit etre un nombre \n";
				}
				
				
				if (msg.equals("")) {


					System.out.println(format.format(Integer.parseInt(cont_jtext.getText())));

                     String debut=code_jtext.getText()+""+format.format(Integer.parseInt(cont_jtext.getText()));
					 String fin=code_jtext.getText()+""+format.format(Integer.parseInt(cont_jtext1.getText()));



                     System.out.println(debut);
					 Boolean exist=imp.nettoyer_serial(debut, fin);
					if(exist==true){
						JOptionPane.showMessageDialog(null, "Les étiquettes ont été bien nettoyer");
						article_comb.setSelectedIndex(0);
						chaine_comb.setSelectedIndex(0);
						code_jtext.setText("");
						cont_jtext.setText("");
						cont_jtext1.setText("");
						retour.setVisible(false);
						valid_ajou.setVisible(false);
						but_net.setVisible(true);

					}



				} else {
					JOptionPane.showMessageDialog(null, msg);
				}	}}; 
				att.start();
			}
		});

		
	
		
		but_net.setVisible(true);		
		valid_ajou.setVisible(false);
		retour.setVisible(false);

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Edition",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan.setBorder(titl2);
				article_comb.setSelectedIndex(0);
				chaine_comb.setSelectedIndex(0);
				code_jtext.setText("");
				cont_jtext.setText("");
				cont_jtext1.setText("");
				retour.setVisible(false);
				valid_ajou.setVisible(false);
				but_net.setVisible(true);
				code_jtext.disable();
			}
		});
		
		article_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (article_comb.getSelectedIndex() == 0) {
					article_comb.setSelectedIndex(0);
					chaine_comb.setSelectedIndex(1);
					code_jtext.setText("");
					cont_jtext.setText("");
					
				} else {
					action_chaine();
				}
			}
		});



		generale.styles("Nimbus");
		SwingUtilities.updateComponentTreeUI(this);
		
		article_lab.setForeground(Color.white);
		chaine_lab.setForeground(Color.white);
		code_lab.setForeground(Color.white);
		cont_lab.setForeground(Color.white);
		cont_lab1.setForeground(Color.white);
		entrep.setForeground(Color.white);
		enie.setForeground(Color.white);
		autre.setForeground(Color.white);
		date_lab.setForeground(Color.white);
		
		
		date_lab.setFont(police2);
		
		
		entrep.setFont(police2);
		enie.setFont(police2);
		autre.setFont(police2);
		article_lab.setFont(police2);
		cont_lab1.setFont(police2);
		
		
		chaine_lab.setFont(police2);
		code_lab.setFont(police2);
		cont_lab.setFont(police2);
		
		
		bG.add(enie);
		bG.add(autre);
		pan_radio_lab.add(entrep);
		pan_radio.add(pan_radio_lab);
		pan_radio.add(pan_radio_bg);
		
	
		
		pan_radio_bg.add(enie);
		pan_radio_bg.add(autre);
		pan_radio_bg.setOpaque(false);
		pan_radio_lab.setOpaque(false);
		pan_radio.setOpaque(false);


		
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
		
		
		
		pan_code.setOpaque(false);
		pan_code_lab.setOpaque(false);
		pan_code_jtext.setOpaque(false);
		
		pan_cont1.add(pan_cont_lab1);
		pan_cont_lab1.add(cont_lab1);
		pan_cont1.add(pan_cont_jtext1);
		pan_cont_jtext1.add(cont_jtext1);

		
		pan_cont.add(pan_cont_lab);
		pan_cont_lab.add(cont_lab);
		pan_cont.add(pan_cont_jtext);
		pan_cont_jtext.add(cont_jtext);
		
		
		pan_cont1.setOpaque(false);
		pan_cont.setOpaque(false);

		pan_cont_lab.setOpaque(false);
		pan_cont_jtext.setOpaque(false);
		
		
		pan_cont_lab1.setOpaque(false);
		pan_cont_jtext1.setOpaque(false);

		pan_button.add(retour);
		pan_button.add(valid_ajou);
		pan_button.add(but_net);
		
		pan_button.setOpaque(false);

		pan_form.add(pan_radio);
	
		
		pan_form.add(pan_article);
		pan_form.add(pan_cont);
		pan_form.add(pan_cont1);
		
		
		pan_form.add(pan_date);
		

		pan_form.add(pan_chaine);
		
		pan_form.add(pan_code);
		

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

		

		retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_net.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		but_net.setPreferredSize(new Dimension(120, 33));
		retour.setPreferredSize(new Dimension(120, 33));
		valid_ajou.setPreferredSize(new Dimension(120, 33));


		
		article_comb.setPreferredSize(new Dimension(210, 30));

		date_picker.setPreferredSize(new Dimension(210, 30));
		chaine_comb.setPreferredSize(new Dimension(210, 30));
		code_jtext.setPreferredSize(new Dimension(210, 30));
		cont_jtext.setPreferredSize(new Dimension(210, 30));
		cont_jtext1.setPreferredSize(new Dimension(210, 30));

		pan_article.setLayout(new BoxLayout(pan_article, BoxLayout.X_AXIS));
		

		pan_date.setLayout(new BoxLayout(pan_date, BoxLayout.X_AXIS));
		pan_chaine.setLayout(new BoxLayout(pan_chaine, BoxLayout.X_AXIS));
		pan_code.setLayout(new BoxLayout(pan_code, BoxLayout.X_AXIS));

		pan_cont1.setLayout(new BoxLayout(pan_cont1, BoxLayout.X_AXIS));
		pan_cont.setLayout(new BoxLayout(pan_cont, BoxLayout.X_AXIS));

		pan_radio.setLayout(new BoxLayout(pan_radio, BoxLayout.X_AXIS));

		pan_radio_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_radio_bg.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_radio_lab.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0));
		pan_radio_bg.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		
		
		
		pan_article_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_article_lab.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 0));
		pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
	

		pan_date_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_date_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 297, 0, 0));
		pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		
		pan_chaine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, -15));
		pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_code_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_code_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_code_lab.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, 0));
		pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_cont_lab1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_cont_jtext1.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_cont_lab1.setBorder(BorderFactory.createEmptyBorder(0, 240, 0, 0));
		pan_cont_jtext1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		pan_cont_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_cont_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_cont_lab.setBorder(BorderFactory.createEmptyBorder(0, 220, 0, 0));
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

					new netoyer("7");
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
				
				String arti = null;

				if(article_comb.getSelectedIndex()!=0){
					String[] art = article_comb.getSelectedItem().toString().split(" ");
					arti = art[0]; // 004
				}

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
				int indic=imp.select_indice(arti);
				if (enie.isSelected() == true) {
					code = "E" + anne + week + jour + cc+indic;
							
				} else {
					code = "A" + anne + week + jour + cc+indic;
							
				}
				code_jtext.setText(code);
				return code;
			} else {

				code_jtext.setText("");
				return "";			}
		
		
	}



}
