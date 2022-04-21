package logiciel_etiq;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

public class imprimer_bom extends JFrame {

	/**
	 * 
	 */
//	private JPanel controlPanel = new JPanel();
	private JFrame frame = new JFrame();

	private static final long serialVersionUID = 1L;
	//private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private String msg;
	private String Chemin = "c:\\GCOBAR\\";
	private String bdd = Chemin + Utilitaire.InitBdd() + ".accdb";

	private gestion_article art = new gestion_article();
	// private JProgressBar progressBar;
	private JPanel pan_article = new JPanel();
	private JPanel pan_article_comb = new JPanel();
	private JPanel pan_article_lab = new JPanel();
	private JLabel article_lab = new JLabel("Liste des Articles");
	private List<String> list_ar;
	private jcombo article_comb;
	private jcombo model_comb;
	private JPanel pan_model = new JPanel();
	private JPanel pan_model_comb = new JPanel();
	private JPanel pan_model_lab = new JPanel();
	private JLabel model_lab = new JLabel("Liste des Modèle");
	private List<String> list_m;

	
	
	 Thread att; 
    
	
	
	private JTextField etabli_jtext = new JTextField();
	private JPanel pan_etab = new JPanel();
	private JPanel pan_etab_jtext = new JPanel();
	private JPanel pan_etab_lab = new JPanel();
	private JLabel etab_lab = new JLabel("Etablie Par");
	

	
	private JPanel pan_date = new JPanel();
	private JPanel pan_date_jtext = new JPanel();
	private JPanel pan_date_lab = new JPanel();
	private JLabel date_lab = new JLabel("Date");
	final JXDatePicker date_picker = new JXDatePicker();
    private static ArrayList<String> list_model_tr=new ArrayList<String>();
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

	Map<String, Object> parameters = new HashMap<String, Object>();
	private JButton imp_bom = new JButton("Imprimer BOM");
	private JButton retour = new JButton("Retour");

	private JPanel pan_gener = new JPanel();
	private JPanel pan_tab = new JPanel();
	
	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

	imprimer_bom(final String log) {
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
		

	
		     list_m = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un modèle-----"}));
		     model_comb = new jcombo(list_m.toArray());
		     list_model_tr=art.select_model();	
		    
		     for(int i=0;i<list_model_tr.size();i++)
			   {
			          //Pour affecter une valeur de base de donn�es � un Combobox
		    	 model_comb.addItem(list_model_tr.get(i));
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

		article_comb.enable();
		
		

		LineBorder border = new LineBorder(Color.white, 1, true);
		TitledBorder titl2 = new TitledBorder(border, "Fiche Suiveuse",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		pan.setBorder(titl2);

		
		
		date_picker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cal.setTime(date_picker.getDate());
			}

		});


	

		
		imp_bom.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent event) {// dispose();
	 				msg="";
	 				if (article_comb.getSelectedIndex()==0){msg+="veuillez sélectionner un article \n";}
	 				if (model_comb.getSelectedIndex()==0){msg+="veuillez sélectionner un modele \n";}
	 				if(msg.equals("")){
	 				att= new Thread(){
	 					@SuppressWarnings("unused")
						public void run(){
	 						
	 						String[] arti = article_comb.getSelectedItem().toString().split(" ");
	 						String code_art = arti[0];
	 					    System.out.println(code_art);
	 						String code = arti[0].replaceAll("\\/","_"); // 004
	 						String parcour ;String model;
	    	 				 parcour = "C:\\GCOBAR\\pdf\\BOM\\"+code+"_"+model_comb.getSelectedItem()+"_BOM"+".pdf";
	    	 				 model = "C:\\GCOBAR\\CODE\\hiarche.jrxml";    	 	
	 						File fichier = new File(parcour);
 	 					    fichier.delete();
	 						//int indice=article_comb.getSelectedIndex();    	 					
	 								     
	                 UIManager.put("nimbusOrange", (new Color(70,130,180)));

	 				try {
	@SuppressWarnings("resource")
	BufferedReader bfr=new BufferedReader(new FileReader( "C:\\GCOBAR\\pdf\\BOM\\"+code+"_"+model_comb.getSelectedItem()+"_BOM"+".pdf"));
	 bfr.close();				
	 					try {
	 						
	 			Desktop.getDesktop().open(new File( "C:\\GCOBAR\\pdf\\BOM\\"+code+"_"+model_comb.getSelectedItem()+"_BOM"+".pdf"));
	                  
	 					} catch (IOException p) {
	 						// TODO Auto-generated catch block
	 						
	 						//p.printStackTrace();
	 						
	 					}
	 }
//	 				
	 				 catch (FileNotFoundException fnfe) {
	 					  parameters.put("serial",code_art);
	 					 	parameters.put("model",model_comb.getSelectedItem());
	 						parameters.put("etabli",etabli_jtext.getText());
	 					    selectioncomb.imprimer(	 bdd, parcour,model,parameters);
	 					 //controlPanel.remove(progressBar);
} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}   
	 					}}; 
	 					
	 					att.start();
	 					
	 			} else{JOptionPane.showMessageDialog(null,msg);}
	 				} 
	 		});
	    	 
	
		imp_bom.setVisible(true);
		retour.setVisible(true);

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Edition",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan.setBorder(titl2);
				article_comb.setSelectedIndex(0);
				model_comb.setSelectedIndex(0);
                etabli_jtext.setText("");
			
			
			
			}
		});




		generale.styles("Nimbus");
		SwingUtilities.updateComponentTreeUI(this);
		article_lab.setForeground(Color.white);
		model_lab.setForeground(Color.white);
		etab_lab.setForeground(Color.white);
		entrep.setForeground(Color.white);
		enie.setForeground(Color.white);
		autre.setForeground(Color.white);
		date_lab.setForeground(Color.white);
		
		
		etab_lab.setFont(police2);
		date_lab.setFont(police2);
		entrep.setFont(police2);
		enie.setFont(police2);
		autre.setFont(police2);
		article_lab.setFont(police2);
		model_lab.setFont(police2);
		
		bG.add(enie);
		bG.add(autre);
		pan_radio_lab.add(entrep);
		pan_radio.add(pan_radio_lab);
		pan_radio.add(pan_radio_bg);
		
		
		/*************************code_com pour le code commercial************/
		
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
		
		
		
		
		pan_model.add(pan_model_lab);
		pan_model_lab.add(model_lab);
		pan_model.add(pan_model_comb);
		pan_model_comb.add(model_comb);

		pan_model.setOpaque(false);
		pan_model_lab.setOpaque(false);
		pan_model_comb.setOpaque(false);

	

		
		pan_etab.add(pan_etab_lab);
		pan_etab_lab.add(etab_lab);
		pan_etab.add(pan_etab_jtext);
		pan_etab_jtext.add(etabli_jtext);
		
		pan_etab.setOpaque(false);
		pan_etab_lab.setOpaque(false);
		pan_etab_jtext.setOpaque(false);
		
	

	

		pan_button.add(retour);
		
		
		pan_button.add(imp_bom);

		pan_button.setOpaque(false);

		pan_form.add(pan_radio);
		pan_form.add(pan_article);
		pan_form.add(pan_date);

		pan_form.add(pan_model);
		
		
		pan_form.add(pan_etab);
		

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
		imp_bom.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		
		
		imp_bom.setPreferredSize(new Dimension(170, 33));
		retour.setPreferredSize(new Dimension(120, 33));
		


		
		article_comb.setPreferredSize(new Dimension(210, 30));
		date_picker.setPreferredSize(new Dimension(210, 30));
		model_comb.setPreferredSize(new Dimension(210, 30));
		etabli_jtext.setPreferredSize(new Dimension(210, 30));
		

		pan_article.setLayout(new BoxLayout(pan_article, BoxLayout.X_AXIS));
		

		pan_date.setLayout(new BoxLayout(pan_date, BoxLayout.X_AXIS));
		pan_model.setLayout(new BoxLayout(pan_model, BoxLayout.X_AXIS));
		pan_etab.setLayout(new BoxLayout(pan_etab, BoxLayout.X_AXIS));

		

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
		pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 294, 0, 0));
		pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_model_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_model_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_model_lab.setBorder(BorderFactory.createEmptyBorder(0, 207, 0, 0));
		pan_model_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_etab_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_etab_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_etab_lab.setBorder(BorderFactory.createEmptyBorder(0, 245, 0, 0));
		pan_etab_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		
	
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

					new imprimer_bom("7");
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



	


}
