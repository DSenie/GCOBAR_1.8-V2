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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.JXDatePicker;

public class etiquette_tpe extends JFrame {

	  ArrayList<String> listrc1 = new ArrayList<String>();

	private JPanel controlPanel = new JPanel();
	private JFrame frame = new JFrame();

	private static final long serialVersionUID = 1L;
	private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private String msg;
	private String Chemin = "c:\\GCOBAR\\";
	private String bdd = Chemin + Utilitaire.InitBdd() + ".accdb";
	Map<String, Object> parameters = new HashMap<String, Object>();

	private gestion_imp imp = new gestion_imp();
	 private JProgressBar progressBar;
	 String arti;
	 
	
		
		private JPanel pan_model = new JPanel();
		private JPanel pan_model_jtext = new JPanel();
		private JPanel pan_model_lab = new JPanel();
		private JLabel model_lab = new JLabel("Modèle");
		
		private JTextField model_jtext = new JTextField();
		
	

	
	



	private JPanel pan_code_lab = new JPanel();
	private JPanel pan_code = new JPanel();
	private JPanel pan_code_jtext = new JPanel();
	private JLabel  code_lab = new JLabel("Code TPE");
	private JTextField code_jtext = new JTextField();


	private JPanel pan_rech_lab = new JPanel();
	private JPanel pan_rech = new JPanel();
	private JPanel pan_rech_jtext = new JPanel();
	private JLabel rech_lab = new JLabel("Recherche");
	private JTextField rech_jtext = new JTextField();
	
	
	private JPanel pan_article = new JPanel();
	private JPanel pan_article_lab = new JPanel();
	private JPanel pan_article_comb = new JPanel();
	private JLabel article_lab = new JLabel("Article");
	private jcombo article_comb;
	
	private JPanel pan_dimension = new JPanel();
	private JPanel pan_dimension_lab = new JPanel();
	private JPanel pan_dimension_comb = new JPanel();
	private JLabel dimension_lab = new JLabel("Etiquette dimension");
	private JComboBox dimension_comb= new JComboBox();

	private JPanel pan_arpt_lab = new JPanel();
	private JPanel pan_arpt = new JPanel();
	private JPanel pan_arpt_jtext = new JPanel();
	private JLabel  arpt_lab = new JLabel("ARPT TPE");
	private JTextField arpt_jtext = new JTextField();

	
	
    private JPanel pan_date = new JPanel();
    private JPanel pan_date_lab = new JPanel();
    private JPanel pan_date_jtext= new JPanel();
    private JLabel date_lab = new JLabel("Date");
    final JXDatePicker date_picker = new JXDatePicker();

	private JPanel pan = new JPanel();
	private JPanel pan_general = new JPanel();
	private JPanel pan_form = new JPanel();
	private JPanel pan_gener = new JPanel();

	private JPanel panel;
	

	private JPanel pan_button = new JPanel();

	private JButton but_sauv = new JButton("Ajouter");
	private JButton valid_ajou = new JButton("Valider");
	
	
	private JButton but_modif= new JButton("Modifier");
	private JButton valid_modif = new JButton("Valider");
	
	
	
	private JButton imp_etq = new JButton("Imprimer L'étiquette");
	

	private JButton retour = new JButton("Retour");
	
	private String conteur = null;
	
    gestion_article art=new gestion_article();
    
	ArrayList <String>list_dimension= new ArrayList<String>() ;
	ArrayList <String>list_article= new ArrayList<String>() ;

	int diff;
	int moy;
	int co;
    Thread att; 
    String report;
    
    NumberFormat format = new DecimalFormat("00000");

	
	etiquette_tpe(final String log) {
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
	
	
		  frame.setUndecorated(true);
   model_jtext.disable();
		  
   
   date_picker.setDate(Calendar.getInstance().getTime());
   date_picker.getEditor().setEditable(false);
   date_picker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
   Calendar cal = Calendar.getInstance();

	
		 	list_article = new ArrayList<String>(Arrays.asList(new String[] { "---Selectionner un article-----" }));
			article_comb = new jcombo(list_article.toArray());
			selectioncomb.selectarticle_tpe(article_comb, this, logi_prio);
			
			article_comb.setSelectedIndex(0);
          
	//	imp.select_conteur();6+66
//		imp.conteur(date_picker.getDate());
			
			
			dimension_comb.addItem("--- Sélectionner la dimension de l'etiquette ----");

			list_dimension=imp.select_dimension_etq("etq_tpe");
		 	  
			   for(int i=0;i<list_dimension.size();i++)
			   {
			          //Pour affecter une valeur de base de donn?es ? un Combobox 
				   dimension_comb.addItem(list_dimension.get(i));
				   
			   }
		
		
		if (!selectioncomb.prv.contains("etiquette_tpe"))
			selectioncomb.prv.add("etiquette_tpe");

		panel = new JPanel() {
			/***  */
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
				img.paintIcon(this, g, 0, 0);
			}
		};

		final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,15);

		

	 
		
		retour.setVisible(false);
		valid_ajou.setVisible(false);
		but_sauv.setVisible(true);
		
		valid_modif.setVisible(false);
		but_modif.setVisible(false);
		imp_etq.setVisible(false);

		LineBorder border = new LineBorder(Color.white, 1, true);
		TitledBorder titl2 = new TitledBorder(border, "Etiquette TPE",
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
				
				but_modif.setVisible(false);
				valid_modif.setVisible(false);

				but_sauv.setVisible(false);
				retour.setVisible(true);
				valid_ajou.setVisible(true);
				imp_etq.setVisible(false);
		

			}
		});
		
		
		
		
		but_modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Ajouter",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan.setBorder(titl2);
				
				but_modif.setVisible(false);
				valid_modif.setVisible(true);

				but_sauv.setVisible(false);
				retour.setVisible(true);
				valid_ajou.setVisible(false);
				imp_etq.setVisible(false);
		

			}
		});
		
		
		code_jtext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ArrayList <String> resultat= new ArrayList<String>() ;
				if(!code_jtext.getText().equals("")||!code_jtext.getText().equals("")){
					resultat=imp.remplir_etq_tpe(code_jtext.getText());
					System.out.println(resultat);
					if(resultat.size()>0){
				//	imp.select_parcel(parcel_jtext.getText());
					model_jtext.setText(resultat.get(0));
					article_comb.setSelectedItem(resultat.get(1)+" "+resultat.get(2));
					arpt_jtext.setText(resultat.get(3));
					
					
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                     
                     Date d = null;
                     try { 
                         Date date = formatter.parse(resultat.get(4).substring(0,10));
                         date_picker.setDate(date);

                     } catch (ParseException e1) {
                         // TODO Auto-generated catch block
                         e1.printStackTrace();
                     }
                     
                     
                     
					imp_etq.setVisible(true);
					but_sauv.setVisible(false);
					retour.setVisible(true);
					valid_ajou.setVisible(false);	
					
					but_modif.setVisible(true);
					valid_modif.setVisible(false);
					
					
					 }
					 else{

							retour.setVisible(false);
							but_sauv.setVisible(true);
							valid_ajou.setVisible(false);
							imp_etq.setVisible(false);
							model_jtext.setText("");
							arpt_jtext.setText("");
							 date_picker.setDate(Calendar.getInstance().getTime());
							article_comb.setSelectedIndex(0);
							
							but_modif.setVisible(false);
							valid_modif.setVisible(false);
			          
						}}else{

							retour.setVisible(false);
							but_sauv.setVisible(true);
							valid_ajou.setVisible(false);
							imp_etq.setVisible(false);
							model_jtext.setText("");
							arpt_jtext.setText("");
							   date_picker.setDate(Calendar.getInstance().getTime());
							article_comb.setSelectedIndex(0);
							but_modif.setVisible(false);
							valid_modif.setVisible(false);


				}
				
			}});
	
		article_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		if(article_comb.getSelectedIndex()!=0 && article_comb.getSelectedItem()!=null){
			String[] art = article_comb.getSelectedItem().toString().split(" ");
			 arti = art[0]; // 004	
	        String model=imp.select_model(arti);
	        model_jtext.setText(model);
	
		
		}
			}});

		valid_ajou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att= new Thread(){
			    public void run() {
				msg = "";
			
				String code;
				
				
				
				if (code_jtext.getText().equals("")) {
					msg += "Veuillez remplir  le code tpe \n";
				}
			
				else if (article_comb.getSelectedIndex() == 0) {
					msg += "Veuillez selectionner un article \n";
				}
				
				
				else if (dimension_comb.getSelectedIndex()==0) {
					msg += "Veuillez selectionner la dimension  \n";
				}
				
			
				if (msg.equals("")) {  
				//	System.out.println(serial_jtext.getText()..length());
					String etq=null;
					
					 retour.setVisible(true);
					 valid_ajou.setVisible(false);
					 but_sauv.setVisible(false);
					 imp_etq.setVisible(true);
					 but_modif.setVisible(false);
						valid_modif.setVisible(false);

					imp.insert_etq_tpe(arti,code_jtext.getText(),arpt_jtext.getText(),date_picker.getEditor().getText());

				    	JOptionPane.showMessageDialog(null, "l'étiquette TPE " + code_jtext.getText()	+ "  a été bien ajouté");

				} else {
					JOptionPane.showMessageDialog(null, msg);
				}	}}; 
				att.start();
			}
		});

		
		valid_modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att= new Thread(){
			    public void run() {
				msg = "";
			
				String code;
				
				
				
				if (code_jtext.getText().equals("")) {
					msg += "Veuillez remplir  le code tpe \n";
				}
			
				else if (article_comb.getSelectedIndex() == 0) {
					msg += "Veuillez selectionner un article \n";
				}
				
				
				else if (dimension_comb.getSelectedIndex()==0) {
					msg += "Veuillez selectionner la dimension  \n";
				}
				
			
				if (msg.equals("")) {
					selectioncomb.closePdf();

					//	System.out.println(serial_jtext.getText()..length());
					String etq=null;
					
					 retour.setVisible(true);
					 valid_ajou.setVisible(false);
					 but_sauv.setVisible(false);
					 imp_etq.setVisible(true);
					 but_modif.setVisible(false);
				     valid_modif.setVisible(false);

					imp.modif_etq_tpe(arti,code_jtext.getText(),arpt_jtext.getText(),date_picker.getEditor().getText().toString());

				    	JOptionPane.showMessageDialog(null, "l'étiquette TPE " + code_jtext.getText()	+ "  a été bien modifié");

				} else {
					JOptionPane.showMessageDialog(null, msg);
				}	}}; 
				att.start();
			}
		});
		
		
		
		dimension_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(dimension_comb.getSelectedIndex()!=0)
				report=imp.select_report(dimension_comb.getSelectedItem().toString(),"etq_tpe");
				
				
			}});
		
		imp_etq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {// dispose();
				att= new Thread(){
					public void run(){
			
						if(dimension_comb.getSelectedIndex()!=0){
				String parcour ;String model;
			    parcour = "C:\\GCOBAR\\pdf\\etq_tpe\\code"+code_jtext.getText()+
			    	"_Etiquette_tpe"+dimension_comb.getSelectedItem()+".pdf";
				 model = "C:\\GCOBAR\\CODE\\"+report;
				//imp.update_imp_s("1");
                UIManager.put("nimbusOrange", (new Color(70,130,180)));

				try {
					
					//	
					new BufferedReader(new FileReader(
							"C:\\GCOBAR\\pdf\\etq_tpe\\code"+code_jtext.getText()+
					    	"_Etiquette_tpe"+dimension_comb.getSelectedItem()+".pdf"));
					
					try {
						Desktop.getDesktop().open(new File(
								"C:\\GCOBAR\\pdf\\etq_tpe\\code"+code_jtext.getText()+
						    	"_Etiquette_tpe"+dimension_comb.getSelectedItem()+".pdf"));
						
					} catch (IOException p) {
						// TODO Auto-generated catch block
						p.printStackTrace();

					}}
				
				catch (FileNotFoundException fnfe) {
					if(!dimension_comb.getSelectedItem().toString().contains("ARPT")){
					listrc1.add(code_jtext.getText());}
					else{listrc1.add(arpt_jtext.getText());}
					  selectioncomb.imprimer("code_tpe",listrc1,bdd,parcour,model);
                	  listrc1.clear(); 
					
				

				}
						}
	                	  else{
	                		  
	                		  JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette");
	                	  }
					}}; 
					
					att.start();
					
			}
		});
		
		
	
		
		but_sauv.setVisible(true);
		but_modif.setVisible(false);

		imp_etq.setVisible(false);
		
		valid_ajou.setVisible(false);
		valid_modif.setVisible(false);
		retour.setVisible(false);

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Edition",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan.setBorder(titl2);
			
				model_jtext.setText("");
				code_jtext.setText("");
				arpt_jtext.setText("");
				   date_picker.setDate(Calendar.getInstance().getTime());
				but_sauv.setVisible(true);
				but_modif.setVisible(false);
				imp_etq.setVisible(false);
				dimension_comb.setSelectedIndex(0);

				retour.setVisible(false);
				valid_ajou.setVisible(false);
				valid_modif.setVisible(false);

				article_comb.setSelectedIndex(0);;
			
			}
		});
		
		



		try {
			UIManager.setLookAndFeel(laf);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		model_lab.setForeground(Color.white);
		code_lab.setForeground(Color.white);
		article_lab.setForeground(Color.white);
		dimension_lab.setForeground(Color.white);
		arpt_lab.setForeground(Color.white);

	
		model_lab.setFont(police2);
		code_lab.setFont(police2);
		article_lab.setFont(police2);
		dimension_lab.setFont(police2);
		arpt_lab.setFont(police2);
        date_lab.setFont(police2);

        
        

        
        date_lab.setForeground(new Color(255, 255, 255));

		/*************************code_com pour le code commercial************/
		
		
		
        pan_date.add(pan_date_lab);
        pan_date.add(pan_date_jtext);
        pan_date_lab.add(date_lab);
        pan_date_jtext.add(date_picker);
		
        pan_date.setOpaque(false);
        pan_date_lab.setOpaque(false);
        pan_date_jtext.setOpaque(false);
        
		pan_model.add(pan_model_lab);
		pan_model_lab.add(model_lab);
		pan_model.add(pan_model_jtext);
		pan_model_jtext.add(model_jtext);
		
	

		pan_model.setOpaque(false);
		pan_model_lab.setOpaque(false);
		pan_model_jtext.setOpaque(false);
		
		pan_arpt.add(pan_arpt_lab);
		pan_arpt_lab.add(arpt_lab);
		pan_arpt.add(pan_arpt_jtext);
		pan_arpt_jtext.add(arpt_jtext);
		
	

		pan_arpt.setOpaque(false);
		pan_arpt_lab.setOpaque(false);
		pan_arpt_jtext.setOpaque(false);

		pan_article.add(pan_article_lab);
		pan_article_lab.add(article_lab);
		pan_article.add(pan_article_comb);
		pan_article_comb.add(article_comb);

		pan_article.setOpaque(false);
		pan_article_lab.setOpaque(false);
		pan_article_comb.setOpaque(false);

		
		pan_code.add(pan_code_lab);
		pan_code_lab.add(code_lab);
		pan_code.add(pan_code_jtext);
		pan_code_jtext.add(code_jtext);
		
		pan_dimension.add(pan_dimension_lab);
		pan_dimension_lab.add(dimension_lab);
		pan_dimension.add(pan_dimension_comb);
		pan_dimension_comb.add(dimension_comb);
		
		pan_dimension.setOpaque(false);
		pan_dimension_lab.setOpaque(false);
		pan_dimension_comb.setOpaque(false);
		
		pan_code.setOpaque(false);
		pan_code_lab.setOpaque(false);
		pan_code_jtext.setOpaque(false);
		
	
		pan_button.add(retour);
		pan_button.add(valid_ajou);
		pan_button.add(but_sauv);
		pan_button.add(but_modif);
		pan_button.add(valid_modif);

		
		pan_button.add(imp_etq);

		pan_button.setOpaque(false);

		pan_form.add(pan_code);

		pan_form.add(pan_article);
		pan_form.add(pan_date);

		pan_form.add(pan_model);

		pan_form.add(pan_arpt);

		
		//pan_form.add(pan_model);
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

		valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		but_sauv.setPreferredSize(new Dimension(120, 33));
		
		
		valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		but_modif.setPreferredSize(new Dimension(120, 33));
		
		
		imp_etq.setPreferredSize(new Dimension(170, 33));

		retour.setPreferredSize(new Dimension(120, 33));
		valid_ajou.setPreferredSize(new Dimension(120, 33));

		valid_modif.setPreferredSize(new Dimension(120, 33));

		
		article_comb.setPreferredSize(new Dimension(230, 30));
		model_jtext.setPreferredSize(new Dimension(230, 30));
		dimension_comb.setPreferredSize(new Dimension(230, 30));
		code_jtext.setPreferredSize(new Dimension(230, 30));
		arpt_jtext.setPreferredSize(new Dimension(230, 30));

        date_picker.setPreferredSize(new Dimension(230, 30));

        date_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 50));

        
		pan_model.setLayout(new BoxLayout(pan_model, BoxLayout.X_AXIS));
		pan_article.setLayout(new BoxLayout(pan_article, BoxLayout.X_AXIS));
		pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));
		pan_code.setLayout(new BoxLayout(pan_code, BoxLayout.X_AXIS));
		pan_arpt.setLayout(new BoxLayout(pan_arpt, BoxLayout.X_AXIS));
		pan_date.setLayout(new BoxLayout(pan_date, BoxLayout.X_AXIS));

		
		pan_arpt_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_arpt_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_arpt_lab.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		pan_arpt_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_model_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_model_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_model_lab.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0));
		pan_model_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	

		pan_article_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_article_lab.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
		pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_code_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_code_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_code_lab.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
		pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -30));
		pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
	
		
		pan_date_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_date_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0,75, 0, -50));
		pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		 
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

					new etiquette_tpe("7");
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
