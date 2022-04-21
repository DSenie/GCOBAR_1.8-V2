package logiciel_etiq;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class process_tache extends JFrame implements ActionListener {
	private static final String CTRL_J = "CTRL+J";
	final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18);
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		// gestion de l'action
		if (e.getActionCommand().equals(CTRL_J)) {
			LineBorder border = new LineBorder(Color.white, 1, true);
			TitledBorder titl2 = new TitledBorder(border, "Edition Process",
			TitledBorder.DEFAULT_POSITION,
			TitledBorder.DEFAULT_POSITION, police2, Color.white);
			pan_ft.setBorder(titl2);
			tache_comb.enable();
			poste_comb.enable();
			pan_but_tache.setVisible(true);
			retour.setVisible(false);
			but_modif.setVisible(true);
			but_sup.setVisible(true);
			valid_supp.setVisible(false);
			valid_modif.setVisible(false);
			poste_comb.setSelectedIndex(0);
			int rows = tab.table.getRowCount();

			for (int i = rows - 1; i >= 0; i--) {
				((DefaultTableModel) tab.table.getModel()).removeRow(i);
			}
		}
	}

	/**
	 * 
	 */
	
	ArrayList <String>list_zone= new ArrayList<String>() ;
	private static final long serialVersionUID = 1L;
	private String msg = "";
    private JTabbedPane jTabbedPane = new JTabbedPane();

//	private boolean co_bo2;
//	private boolean repet2;
	
	private boolean valid_j,valid_a,valid_o;
	//private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

	private JPanel panel=new JPanel();
	private JPanel panel_article=new JPanel();
	private JPanel panel_observation=new JPanel();
	
	private JPanel pan_form = new JPanel();
	private JPanel pan_form1 = new JPanel();
	private JPanel pan_ft1 = new JPanel();

	private JPanel pan_ft = new JPanel();
	private JPanel pan_comb = new JPanel();
	private JPanel pan_comb_lab = new JPanel();
	private JPanel pan_comb_jtext = new JPanel();
	
	private JPanel pan_produit = new JPanel();
	private JPanel pan_produit_lab = new JPanel();
	private JPanel pan_produit_comb = new JPanel();
	
	
	
	private JPanel pan_outil= new JPanel();
	
	
	private JPanel pan_profil= new JPanel();
	private JPanel pan_profil_lab = new JPanel();
	private JPanel pan_profil_comb = new JPanel();

	private JPanel pan_numero_poste= new JPanel();
	private JPanel pan_numero_poste_lab = new JPanel();
	private JPanel pan_numero_poste_comb = new JPanel();
	
	private JPanel pan_button = new JPanel();
	private JPanel pan_tab = new JPanel();
	private JPanel pan_but_tache = new JPanel();

	private JPanel pan_tab_article = new JPanel();
	private JPanel pan_but_article = new JPanel();
	
	private JPanel pan_tab_observation = new JPanel();
	private JPanel pan_but_observation = new JPanel();
	
	
	private JButton but_ajou = new JButton("Ajouter une Tache");
	private JButton but_supprim = new JButton("Supprimer une Tache");

	private JButton but_ajou_a = new JButton("Ajouter un Article");
	private JButton but_supprim_a = new JButton("Supprimer un Article");
	
	
	private JButton but_ajou_o = new JButton("Ajouter une Observation");
	private JButton but_supprim_o = new JButton("Supprimer une Observation");
	
	
	private JButton valid_supp = new JButton("Valider");
	private JButton valid_modif = new JButton("Valider");
	private JButton retour = new JButton("Retour");

	private JButton but_modif = new JButton("Sauvegarder Process ");
	private JButton but_sup = new JButton("Supprimer");
	//private JList outil;
	private JLabel comb_lab = new JLabel("Poste");
	private JLabel pro_lab = new JLabel("Produits");
	private JLabel prof_lab = new JLabel("Process");
	private JLabel date_lab = new JLabel("Date");
	private JLabel outil_lab = new JLabel("Outillage");
	private JLabel numero_poste_lab = new JLabel("Numero de Poste");


	private jcombo poste_comb;
	private JComboBox<String> marg = new JComboBox<String>();
	private JComboBox<String> numero_poste_comb = new JComboBox<String>();

	private jcombo produit_comb ;
	private jcombo profils_comb ;
	private Java2sAutoComboBox article_comb ;
	private Java2sAutoComboBox article_comb2 ;

	private Java2sAutoComboBox tache_comb;
	final JButton buttonOk = new JButton("Ajouter ");
	private Object[] entete = { "Code/Intitulé Tache" ,"article","Temp normal","Marge","Temps Total"};
	private final Tableau tab = new Tableau(entete);
	private gestion_poste pos = new gestion_poste();
	private gestion_zone zone = new gestion_zone();

	private Object[] entet_article = { "Article","N° D'ORIGNIE", "N° ENIE","DESIGNATION","COEF","POSITION"};
	private final Tableau tab_article = new Tableau(entet_article);
	
	
	private Object[] entet_observation = { "Observation"};
	private final Tableau tab_observation = new Tableau(entet_observation);
	
	
	private JScrollPane p = new JScrollPane(tab);
	
	private JScrollPane scrol_art = new JScrollPane(tab_article);
	private JScrollPane scrol_observ = new JScrollPane(tab_observation);

	private int i,k,m;
	private static List<Object> list_p;
	private static List<String> list_pro;
	private static List<String> list_prof;
    private boolean existe_profil;
      
	//private static ArrayList<String> list_poste_tr=new ArrayList<String>();
	private static ArrayList<Object> list_article;
	private static ArrayList<Object> list_article2;
    String profils_prod;
    private static ArrayList<String> list_article_t=new ArrayList<String>();
    private static ArrayList<String> list_observation_t=new ArrayList<String>();

	private static gestion_tache tache=new gestion_tache();
	int  profils_prod_c;
	 int num_poste ;
	int list_poste_num;
	
	  DefaultListModel<String> model= new DefaultListModel<String>();
	  String outilage[];
	   String names[] = {"Banglore", "Hyderabad", "Ooty", "Chennai", "Mumbai", "Delhi", "Kochi", "Darjeeling"};
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		
	    JList outil = new JList(model) ; 
	    
	process_tache(final String log) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
		setIconImage(img);
		selectioncomb.windows(this, log);
		composant(log);
	}

	@SuppressWarnings("deprecation")
	public void composant(final String log) {
	    for (String elem : names)
            model.addElement(elem);
		  tab_article.allowEdition3=false;
		  tab_article.allowEdition1=false;
		  tab_article.allowEdition4=false;		
		  tab_article.allowEdition5=false;
		  tab_article.allowEdition6=false;

		                    // creating JList object; pass the array as parameter
		    outil.setVisibleRowCount(5); 
				     
		    outil.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
		 
		 
		  
		  list_p = new ArrayList<Object>(Arrays.asList(new String[]{"---Sélectionner un Poste-----"}));
		  poste_comb = new jcombo(list_p.toArray());
	      selectioncomb.selectposte(poste_comb ,this,log);
	      
		
		  
		  numero_poste_comb.addItem("--Sélectionner un Numero de Poste--");
		  
		   
			  
			   
			   list_article= new ArrayList<Object>(
						Arrays.asList(new String[] { "---Sélectionner un Article -----" }));
			          article_comb = new Java2sAutoComboBox(list_article);
						selectioncomb.selectarticle_process(article_comb ,"enie",this,log,list_article);
						 article_comb.setDataList(list_article);
						
						
						        list_article2= new ArrayList<Object>(
								Arrays.asList(new String[] { "---Sélectionner un Article -----" }));
						        
						        article_comb2 = new Java2sAutoComboBox(list_article2);
								selectioncomb.selectarticle_process(article_comb2,"enie",this,log,list_article2);
						        
						         article_comb2.setDataList(list_article2);
						         
							
								
			   marg.addItem("18%");
			   marg.addItem("15%");
				
		        list_pro = new ArrayList<String>(
				Arrays.asList(new String[] { "---Sélectionner un Produit-----" }));
				produit_comb = new jcombo(list_pro.toArray());
				selectioncomb.selectproduit(produit_comb ,this,log);
				
				
				
		list_prof = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un processe-----"}));
	    profils_comb = new jcombo(list_prof.toArray());
		selectioncomb.selectprocess(profils_comb  ,this,log);	
		
		
		  list_p = new ArrayList<Object>(Arrays.asList(new String[]{"---Sélectionner une Tache-----"}));
			//zone_comb = new jcombo(list_z.toArray());
		   // selectioncomb.selectzone_chaine1(this,log,tab,list_z);

		   tache_comb = new Java2sAutoComboBox(list_p);
			selectioncomb.selecttache_poste(tache_comb,this,log,tab,list_p);
	       
			tache_comb.setDataList(list_p);
		
		
		
		
		if(!selectioncomb.prv.contains("process_tache"))
		selectioncomb.prv.add("process_tache");
//		for (int i = 0; i < imp.select_poste_code().size(); i++) {
//			// Pour affecter une valeur de base de donn�es � un Combobox
//			poste_comb.addItem(imp.select_poste_code().get(i)+" "+imp.select_poste_code().get(i + 1));
//			i++;
//		}

		final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,18);

		but_modif.setVisible(true);
		but_sup.setVisible(true);
		valid_supp.setVisible(false);
		valid_modif.setVisible(false);
		retour.setVisible(false);
		// outil.disable();
		pan_but_tache.setVisible(true);
		but_supprim.disable();
		poste_comb.setSelectedIndex(0);
		// tab.allowEdition1=false;

		tab.setStyle(2);
		tab_article.setStyle(2);
		tab_observation.setStyle(2);

		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon(getClass()
						.getClassLoader().getResource("menu.png"));
				img.paintIcon(this, g, 0, 0);
			}
		};
		
		panel_article = new JPanel() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon(getClass()
						.getClassLoader().getResource("menu.png"));
				img.paintIcon(this, g, 0, 0);
			}
		};
		panel_observation = new JPanel() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon(getClass()
						.getClassLoader().getResource("menu.png"));
				img.paintIcon(this, g, 0, 0);
			}
		};
		panel.registerKeyboardAction(this, CTRL_J,
				KeyStroke.getKeyStroke(KeyEvent.VK_J, Event.CTRL_MASK),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		
		LineBorder border = new LineBorder(Color.white, 1, true);
		TitledBorder titl2 = new TitledBorder(border, "Edition Process",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		pan_ft.setBorder(titl2);

		
		marg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pourcant = 0;
				tab.table.setCellSelectionEnabled(true);
				  if(tab.table.getSelectedRow()!=-1){
				  Float tn=Float.parseFloat(tab.table.getValueAt(tab.table.getSelectedRow(),2 ).toString());
		    	  pourcant=Integer.parseInt(tab.table.getValueAt(tab.table.getSelectedRow(),3).toString().replace("%", ""));
		    	  float pp= (float) tn*pourcant/100;
		    	  float tt=tn+pp;
		    	  tab.table.setValueAt(tt, tab.table.getSelectedRow(),4 );
				  }
			}});
		
		tab.table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener(){
		      public void valueChanged(ListSelectionEvent event) {
		    	  int pourcant = 0;
					  tab.table.setCellSelectionEnabled(true);
					  if(tab.table.getSelectedRow()!=-1){
				      Float tn=Float.parseFloat(tab.table.getValueAt(tab.table.getSelectedRow(),2 ).toString());
			    	  pourcant=Integer.parseInt(tab.table.getValueAt(tab.table.getSelectedRow(),3).toString().replace("%", ""));
			    	  float pp= (float) tn*pourcant/100;
			    	  float tt=tn+pp;
			    	  tab.table.setValueAt(tt, tab.table.getSelectedRow(),4 );
					  }	    	 
		}  }); 
		
		produit_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(produit_comb.getSelectedIndex()!=0){
				String[] part_prod = produit_comb.getSelectedItem().toString().split(" ");
       		    String part_prode = part_prod[0];
       		 profils_prod=zone.select_produit_profil(part_prode).get(0);
     	     profils_prod_c=Integer.parseInt(zone.select_produit_profil(part_prode).get(1));
       	     profils_comb.setSelectedItem(profils_prod);
       		 }
				else{
					profils_comb.setSelectedIndex(0);
					int rows = tab.table.getRowCount();
					int rows1 = tab_article.table.getRowCount();
					int rows2 = tab_observation.table.getRowCount();
					for (int i = rows - 1; i >= 0; i--) {
						((DefaultTableModel) tab.table.getModel()).removeRow(i);
					}
					for (int i = rows1 - 1; i >= 0; i--) {
						((DefaultTableModel) tab_article.table.getModel()).removeRow(i);
					}
					for (int i = rows2 - 1; i >= 0; i--) {
						((DefaultTableModel) tab_observation.table.getModel()).removeRow(i);
					}
				}
			}});
		
		
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Edition Tache",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan_ft.setBorder(titl2);
				poste_comb.enable();
				produit_comb.enable();
				profils_comb.enable();
				pan_but_tache.setVisible(true);
				retour.setVisible(false);
				but_modif.setVisible(true);
				but_sup.setVisible(true);
				valid_supp.setVisible(false);
				valid_modif.setVisible(false);
				poste_comb.setSelectedIndex(0);
				produit_comb.setSelectedIndex(0);
				profils_comb.setSelectedIndex(0);
            //    outil.setText("");
				int rows = tab.table.getRowCount();
				int rows1 = tab_article.table.getRowCount();
				int rows2 = tab_observation.table.getRowCount();

				for (int i = rows - 1; i >= 0; i--) {
					((DefaultTableModel) tab.table.getModel()).removeRow(i);
				}
				for (int i = rows1 - 1; i >= 0; i--) {
					((DefaultTableModel) tab_article.table.getModel()).removeRow(i);
				}
				for (int i = rows2 - 1; i >= 0; i--) {
					((DefaultTableModel) tab_observation.table.getModel()).removeRow(i);
				}
			}
		});

		but_sup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//use.select_zone_jtext(designation.getText());
				// System.out.println(designation.getText()+"  "+use.exist_des);
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Supprimer",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan_ft.setBorder(titl2);
				
				tache_comb.disable();
				pan_but_tache.setVisible(false);
				poste_comb.enable();
				but_modif.setVisible(false);
				but_sup.setVisible(false);
				retour.setVisible(true);
				valid_supp.setVisible(true);
				valid_modif.setVisible(false);
				
			}
		});

		but_ajou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				tab.ajouter();
				TableColumn gradeColumn = tab.table.getColumnModel().getColumn(0);
				gradeColumn.setCellEditor(new DefaultCellEditor(tache_comb));
				
				TableColumn gradeColumn2 = tab.table.getColumnModel().getColumn(3);
				gradeColumn2.setCellEditor(new DefaultCellEditor(marg));
			
				TableColumn gradeColumn3 = tab.table.getColumnModel().getColumn(1);
				gradeColumn3.setCellEditor(new DefaultCellEditor(article_comb2));
				
				
				int k = tab.table.getRowCount();
				tab.table.setValueAt("", k - 1, 0);
				tab.table.setValueAt("", k - 1, 1);
				tab.table.setValueAt("0", k - 1, 2);
				tab.table.setValueAt("0", k - 1, 3);
				tab.table.setValueAt("0", k - 1, 4);
					}
		});
		
		
		but_ajou_a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tab_article.ajouter();
				TableColumn gradeColumn = tab_article.table.getColumnModel().getColumn(0);
				gradeColumn.setCellEditor(new DefaultCellEditor(article_comb));
				
				int k = tab_article.table.getRowCount();
				tab_article.table.setValueAt("", k - 1, 0);
				tab_article.table.setValueAt("", k - 1, 1);
				tab_article.table.setValueAt("", k - 1, 2);
				tab_article.table.setValueAt("", k - 1, 3);
				tab_article.table.setValueAt("", k - 1, 4);
				tab_article.table.setValueAt("", k - 1, 5);
			}
		});
		
		but_supprim_a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tab_article.table.getSelectedRow() == -1) {
					JOptionPane
							.showMessageDialog(
									null,
									"Vous devez d'abord sélectionner l'article que vous voulez supprimer",
									"", JOptionPane.INFORMATION_MESSAGE);
				} else {
					((DefaultTableModel) tab_article.table.getModel())
							.removeRow(tab_article.table.getSelectedRow());
				}
			}
		});
		
		but_supprim_o.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tab_observation.table.getSelectedRow() == -1) {
					JOptionPane
							.showMessageDialog(
									null,
									"Vous devez d'abord sélectionner l'observation que vous voulez supprimer",
									"", JOptionPane.INFORMATION_MESSAGE);
				} else {
					((DefaultTableModel) tab_observation.table.getModel())
							.removeRow(tab_observation.table.getSelectedRow());
				}
			}
		});
		but_ajou_o.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tab_observation.ajouter();
//				TableColumn gradeColumn = tab_article.table.getColumnModel().getColumn(0);
//				gradeColumn.setCellEditor(new DefaultCellEditor(article_comb));
				
				int k = tab_observation.table.getRowCount();
				tab_observation.table.setValueAt("", k - 1, 0);
			}
		});
		
		valid_supp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	use.select_zone_jtext(designation.getText());
				msg = "";
				if (poste_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord Sélectionner un poste\n";
				} else if (produit_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord Sélectionner un produit\n";
				}
				
				if (!msg.equals("")) {
					JOptionPane.showMessageDialog(null, msg);
				} else {
					String[] parts = poste_comb.getSelectedItem().toString().split(" ");
					String code_p = parts[0]; // 004
					String[] poste_numero = numero_poste_comb.getSelectedItem().toString().split(" ");
					int numero =Integer.parseInt(poste_numero[1]); // 004
					 int code_t=tache.code_tache(profils_prod_c,code_p,numero);
					tache.delete_feuille_process(profils_prod_c, code_t,code_p,numero
						);
					tache_comb.enable();
				}
			}
		});

		but_supprim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tab.table.getSelectedRow() == -1) {
					JOptionPane
							.showMessageDialog(
									null,
									"Vous devez d'abord sélectionner la tache que vous voulez supprimer",
									"", JOptionPane.INFORMATION_MESSAGE);
				} else {
					((DefaultTableModel) tab.table.getModel())
							.removeRow(tab.table.getSelectedRow());
				}
			}
		});

		but_modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	use.select_zone_jtext(designation.getText());
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Modifier",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan_ft.setBorder(titl2);
				tab.allowEdition1 = true;
				poste_comb.enable();
				tache_comb.enable();
				pan_but_tache.setVisible(true);

				but_modif.setVisible(false);
				but_sup.setVisible(false);
				retour.setVisible(true);
				valid_supp.setVisible(false);
				valid_modif.setVisible(true);
				// }
			}
		});

		valid_modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	use.select_zone_jtext(designation.getText());
				msg = "";
				boolean co_bo2 = true;
				valid_j = false;
				valid_a = false;
				valid_o = false;

				
				if (poste_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord Sélectionner un poste\n";
				} else if (produit_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord Sélectionner un produit\n";
				}
				else if (profils_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord Sélectionner un process\n";
				}
				
				 else if (tab.table.getRowCount() == 0) {
					 msg +=	"Vous devez  d'abord ajouter une Tache ";
					}
				 else if (tab_article.table.getRowCount() == 0) {
					 msg +="Vous devez  d'abord ajouter un Article ";
					}
				 else if (tab_observation.table.getRowCount() == 0) {
					 msg +="Vous devez  d'abord ajouter une Observation";
					}
				 else {

					if (vaidCheck(tab) == false) {
						valid_j = true;
						msg += "Un Champ dans le tableau est vide \n";
					}if (vaidCheck(tab_article) == false) {
						valid_a = true;
						msg += "Un Champ dans le tableau article est vide \n";
					}if (vaidCheck(tab_observation) == false) {
						valid_o = true;
						msg += "Un Champ dans le tableau observation est vide \n";
					}

					if (valid_j == false) {
						for (int i = 0; i < tab.table.getRowCount(); i++) {
							for (int j = i + 1; j < tab.table.getRowCount(); j++) {

				if (tab.table.getValueAt(i, 0).toString().equals(tab.table.getValueAt(j, 0).toString())&&
						tab.table.getValueAt(i,1).toString().equals(tab.table.getValueAt(j, 1).toString())	) {
				  msg += "Il existe plusieurs ou un tache dupliqué. \n";
								}
							}
						}}
					if (valid_a == false) {
						for (int i = 0; i < tab_article.table.getRowCount(); i++) {
							for (int j = i + 1; j < tab_article.table.getRowCount(); j++) {

				if (tab_article.table.getValueAt(i, 0).toString().equals(tab_article.table.getValueAt(j, 0).toString())) {
				  msg += "Il existe plusieurs ou un article dupliqué. \n";
								}
							}
						}}
					if (valid_o == false) {
						for (int i = 0; i < tab_observation.table.getRowCount(); i++) {
							for (int j = i + 1; j < tab_observation.table.getRowCount(); j++) {

				if (tab_observation.table.getValueAt(i, 0).toString().equals(tab_observation.table.getValueAt(j, 0).toString())) {
				  msg += "Il existe plusieurs ou un observation dupliqué. \n";
								}
							}
						}}
					
					
					for (int i = 0; i < tab.table.getRowCount(); i++) {
						String col2 = tab.table.getValueAt(i,2).toString();
						String col4 = tab.table.getValueAt(i,4).toString();

						if (isValid(col2) == false||isValid(col4) == false) {
							  co_bo2 = false;
						}
					}
					
				
					if(co_bo2==false) msg += "Le temp normale ou total doit etre un nombre \n";
					
				 }
				
					if (!msg.equals("")) {
						JOptionPane.showMessageDialog(null, msg);
					}

					else {
						// System.out.print(co_bo2+""+repet2+""+desc2+""+valid_j+""+comb+""+use.exist_des);
							String[] zone_l = poste_comb.getSelectedItem().toString().split(" ");
							String code_p = zone_l[0]; // 004
						
							String[] poste_numero = numero_poste_comb.getSelectedItem().toString().split(" ");
							int numero =Integer.parseInt(poste_numero[1]); // 004
						
//							String process=profils_comb.getSelectedItem().toString();
                				String outils = "";
							    Object obj[ ] = outil.getSelectedValues();
							    for(int i = 0; i < obj.length; i++)
							    {
							     	if(i == obj.length-1)
							    	outils += (String) obj[i];
							    	else outils += (String) obj[i]+",";
							    }
							  //  System.out.println(outils);
							   // numero_poste_comb.get
						tache.insert_feuille_process(profils_prod_c,code_p,outils,numero);
						int code_t=tache.code_tache(profils_prod_c,code_p,numero);
							 tache.delete_tache(code_t);
							 tache.delete_article(code_t);
							 tache.delete_observation(code_t);
						        for (int i = 0; i < tab.table.getRowCount(); i++) {
//								String col2 = tab.table.getValueAt(i, 1).toString();
//								int col2_i = Integer.parseInt(col2);
  							    String[] article_t= tab.table.getValueAt(i, 1).toString().split(" ");
								String code_article = article_t[0]; // 004
								String[] tache_t= tab.table.getValueAt(i, 0).toString().split(" ");
								String code_tache = tache_t[0]; // 004
								float temp_n=Float.parseFloat(tab.table.getValueAt(i, 2).toString());
								float temp_t=Float.parseFloat(tab.table.getValueAt(i, 4).toString());
								String marg=tab.table.getValueAt(i, 3).toString();


//								zone.setupdate_zone_tab_ajou(zone_p,
//										col2_i, part1,profils_comb.getSelectedItem().toString());
								
		                 tache.insert_tache_process(profils_prod_c,code_t,code_p,code_tache,code_article,temp_n, temp_t, marg,numero);
							}
							for (int i = 0; i < tab_article.table.getRowCount(); i++) {
								
							 tache.insert_article_process(code_t,tab_article.table.getValueAt(i,2).toString());
							 
							}
							for (int i = 0; i < tab_observation.table.getRowCount(); i++) {
						//System.out.println("rrrr5"+tab_observation.table.getValueAt(i, 0).toString());
						 tache.insert_observation_process(code_t, tab_observation.table.getValueAt(i, 0).toString());
								}							
						JOptionPane.showMessageDialog(null,"Les Tache du process a été bien modifiée");
						
					}
				
			}
		});

		

		

//		tache_comb.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				if (tache_comb.hasFocus()) {
//					// /
//					if (tache_comb.getSelectedIndex() != 0) {
//
//						String[] parts = tache_comb.getSelectedItem().toString().split(" ");
//						String part1 = parts[0]; // 004
//						String[] zone_l = poste_comb.getSelectedItem().toString().split(" ");
//						String zone_p = zone_l[0]; // 004
////						tab.table.setValueAt(
////								zone.select_poste_num(part1, zone_p,profils_comb.getSelectedItem().toString()),
////								tab.table.getSelectedRow(), 1);
//					}
//				}
//
//			}
//		});
		
		
		article_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("hjhkjhhhj");
              
		
					int rows=0;
					if(rows>=0)
                    rows=tab_article.table.getSelectedRow();
					// /
					
					if (article_comb.getSelectedIndex() > 0) {

						String[] parts = article_comb.getSelectedItem().toString().split(" ");
						String part1 = parts[0]; // 004
					   String part2 = article_comb.getSelectedItem().toString().replace(part1+" ", ""); // 004
					  

						String[] parts_p = produit_comb.getSelectedItem().toString().split(" ");
						String part_p = parts_p[0]; // 004
						
						ArrayList<String> artcile_tache=new ArrayList<String>();
						
						artcile_tache=tache.select_article_tache(part1,part_p);
						if(artcile_tache.size()>0){
						System.out.println(artcile_tache+" "+rows);
						tab_article.table.setValueAt(artcile_tache.get(0),rows,1);
						tab_article.table.setValueAt(artcile_tache.get(1),rows,4);
						tab_article.table.setValueAt(artcile_tache.get(2),rows,5);

//						tab.table.setValueAt(
//								zone.select_poste_num(part1, zone_p,profils_comb.getSelectedItem().toString()),
//								tab.table.getSelectedRow(), 1);
						
						tab_article.table.setValueAt(part1,rows,2);
						tab_article.table.setValueAt(part2,rows,3);}


					}
					else if (article_comb.getSelectedIndex() == 0) {
						System.out.println(rows);
						tab_article.table.setValueAt("",rows,1);
						tab_article.table.setValueAt("",rows,4);
						//tab.table.setValueAt(
								//zone.select_poste_num(part1, zone_p,profils_comb.getSelectedItem().toString()),
								//tab.table.getSelectedRow(), 1);
						
						tab_article.table.setValueAt("",rows,2);
						tab_article.table.setValueAt("",rows,3);
						tab_article.table.setValueAt("",rows,5);

					}
				}

			
		});

		poste_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				action();
				numero_poste_comb.removeAllItems();
				numero_poste_comb.addItem("--Sélectionner un Numero de Poste--");
				 String[] part_poste = poste_comb.getSelectedItem().toString().split(" ");
			     String part1 = part_poste[0];
			     int ncode=tache.select_profils_ncode(profils_comb.getSelectedItem().toString());
			     
			     if(poste_comb.getSelectedIndex()!=0)
			     list_poste_num=pos.afficher_numero_poste(part1,ncode);	  
			     
			     //System.out.println("hhhhhh"+list_poste_num+" "+poste_comb);
			     if(list_poste_num!=0){
			    // System.out.println("rrrrr"+list_poste_num);
				   for(int i=1;i<=list_poste_num;i++)
				   {
				          //Pour affecter une valeur de base de donn�es � un Combobox
					   numero_poste_comb.addItem("Poste "+i);
					
				   }}
				}
		});
		
		profils_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action();
            	
            
			}});
		
	
		
		
		
		numero_poste_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(x);
				if(numero_poste_comb.getItemCount()!=0&&numero_poste_comb.getSelectedIndex()!=0){
				String[] pp = numero_poste_comb.getSelectedItem().toString().split(" ");
				num_poste = Integer.parseInt(pp[1]);
				}

				action();

//	            action();
//            	System.out.println(numero_poste_comb.getSelectedItem().toString().split(" "));
//			     if(numero_poste_comb.getSelectedIndex()!=0){
//				     String[] nume_p = numero_poste_comb.getSelectedItem().toString().split(" ");
//				      num_poste = Integer.parseInt(nume_p[1]);
//			     }
			}});


		generale.styles("Nimbus");
		SwingUtilities.updateComponentTreeUI(this);

		Font police_fi = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13);

		comb_lab.setFont(police2);
		pro_lab.setFont(police2);
		prof_lab.setFont(police2);
		pro_lab.setFont(police2);
		prof_lab.setFont(police2);
		date_lab.setFont(police2);
		outil_lab.setFont(police2);
		numero_poste_lab.setFont(police2);

		
		tab.table.setFont(police_fi);
		tab_article.table.setFont(police_fi);
		poste_comb.setFont(police_fi);
		produit_comb.setFont(police_fi);
		profils_comb.setFont(police_fi);
		numero_poste_comb.setFont(police_fi);

		
		// outil.setPreferredSize(new Dimension(210,100));
		pro_lab.setForeground(new Color(255, 255, 255));
		prof_lab.setForeground(new Color(255, 255, 255));
		date_lab.setForeground(new Color(255, 255, 255));
		outil_lab.setForeground(new Color(255, 255, 255));

		numero_poste_lab.setForeground(new Color(255, 255, 255));
		comb_lab.setForeground(new Color(255, 255, 255));
		// ***************************************************************test**********************************************
		outil.setFont(new Font("Serif", Font.ITALIC, 13));
	//	outil.setLineWrap(true); // wrap line
	//	outil.setWrapStyleWord(true); // wrap line at word boundary
		outil.setBackground(new Color(204, 238, 241)); // light blue

		// setPreferredSize(new Dimension(230, 30));
		// Wrap the JTextArea inside a JScrollPane
		JScrollPane tAreaScrollPane = new JScrollPane(outil);
		// tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,
		// 50));
		tAreaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tAreaScrollPane.setBounds(0, 10, 10, -100);
		tAreaScrollPane.setPreferredSize(new Dimension(230, 130));
	
		// *******************************************************************************************************************
	
		JScrollPane scrollPane = new JScrollPane(pan_ft1);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getViewport().setOpaque(false);
    	p.getViewport().setOpaque(false);
    	scrol_art.getViewport().setOpaque(false);
    	scrol_observ.getViewport().setOpaque(false);
		pan_form1.add(pan_comb);
		pan_produit.add(pan_produit_lab);
		pan_produit.add(pan_produit_comb);
		pan_produit_lab.add(pro_lab);
		pan_produit_comb.add(produit_comb);
		
		pan_profil.add(pan_profil_lab);
		pan_profil.add(pan_profil_comb);
		pan_profil_lab.add(prof_lab);
		pan_profil_comb.add(profils_comb);
		
		
		pan_numero_poste.add(pan_numero_poste_lab);
		pan_numero_poste.add(pan_numero_poste_comb);
		pan_numero_poste_lab.add(numero_poste_lab);
		pan_numero_poste_comb.add(numero_poste_comb);
		
		
		pan_comb.add(pan_comb_lab);
		pan_comb.add(pan_comb_jtext);
		pan_comb_lab.add(comb_lab);
		pan_comb_jtext.add(poste_comb);
		
		
		pan_form1.add(pan_produit);
		pan_form1.add(pan_profil);
		pan_form1.add(pan_comb);
		pan_form1.add(pan_numero_poste);

		pan_form1.add(pan_outil);

		pan_ft1.add(pan_form1);
		pan_ft1.add(pan_form);

		panel.add(pan_ft);
		pan_ft.add(scrollPane);

		pan_ft.add(pan_tab);
		pan_tab.add(p);
		pan_tab.add(pan_but_tache);
		panel.add(pan_button);
		//jTabbedPane.addTab("Composant", panel_general);
		jTabbedPane.addTab("Edition Tache", panel);
		jTabbedPane.addTab("Article de process", panel_article);
		jTabbedPane.addTab("Observation de process", panel_observation);
		pan_outil.add(outil_lab);
		// pan_desc.add(description);
		panel_article.add(pan_tab_article);
		panel_article.add(pan_but_article);
		
		panel_observation.add(pan_tab_observation);
		panel_observation.add(pan_but_observation);
		pan_tab_observation.add(scrol_observ);
		pan_but_observation.add(but_ajou_o);
		pan_but_observation.add(but_supprim_o);
		
		pan_but_article.add(but_ajou_a);
		pan_but_article.add(but_supprim_a);
		pan_tab_article.add(scrol_art);
		pan_but_tache.add(but_ajou);
		pan_but_tache.add(but_supprim);
		pan_button.add(retour);
	
		pan_button.add(valid_supp);
		pan_button.add(valid_modif);
		pan_button.add(but_modif);
		pan_button.add(but_sup);
		
		pan_but_article.setOpaque(false);
		pan_tab_article.setOpaque(false);
		pan_but_observation.setOpaque(false);
		pan_tab_observation.setOpaque(false);
		panel_article.setOpaque(false);
		panel_observation.setOpaque(false);

		pan_comb.setOpaque(false);
		pan_comb_lab.setOpaque(false);
		pan_comb_jtext.setOpaque(false);
		pan_produit_lab.setOpaque(false);
		pan_produit_comb.setOpaque(false);
	
		pan_profil_lab.setOpaque(false);
		pan_profil_comb.setOpaque(false);
		pan_numero_poste_lab.setOpaque(false);
		pan_numero_poste_comb.setOpaque(false);
		pan_numero_poste.setOpaque(false);
		pan_ft.setOpaque(false);
		pan_form.setOpaque(false);
		pan_button.setOpaque(false);
		pan_outil.setOpaque(false);
		pan_profil.setOpaque(false);
		pan_produit.setOpaque(false);
		pan_ft1.setOpaque(false);
		pan_form1.setOpaque(false);
     	jTabbedPane.setOpaque(false);
     	panel.setOpaque(false);
     	//panel_general.setOpaque(false);

		// tab.setPreferredSize(new Dimension(700,700));
		p.setPreferredSize(new Dimension(700, 700));
		scrol_art.setPreferredSize(new Dimension(900, 800));
		scrol_observ.setPreferredSize(new Dimension(900, 800));

		pan_ft1.setPreferredSize(new Dimension(350, 700));
		poste_comb.setPreferredSize(new Dimension(230, 30));
		profils_comb.setPreferredSize(new Dimension(230, 30));
		produit_comb.setPreferredSize(new Dimension(230, 30));
		numero_poste_comb.setPreferredSize(new Dimension(230, 30));

		pan_form.add(tAreaScrollPane, BorderLayout.CENTER);

		but_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_ajou_a.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_supprim_a.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_ajou_o.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_supprim_o.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_supprim.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_sup.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_supp.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		but_ajou.setPreferredSize(new Dimension(150, 33));
		but_ajou_a.setPreferredSize(new Dimension(170, 33));
		but_supprim_a.setPreferredSize(new Dimension(170, 33));
		but_ajou_o.setPreferredSize(new Dimension(200, 33));
		but_supprim_o.setPreferredSize(new Dimension(200, 33));

		but_modif.setPreferredSize(new Dimension(170, 33));
		but_supprim.setPreferredSize(new Dimension(170, 33));
		but_sup.setPreferredSize(new Dimension(120, 33));
		retour.setPreferredSize(new Dimension(120, 33));
		valid_supp.setPreferredSize(new Dimension(120, 33));
		valid_modif.setPreferredSize(new Dimension(120, 33));

		p.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		pan_button.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
		comb_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 30));
		pro_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 75));
		prof_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 75));
		date_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 75));

		outil_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 22));
		pan_produit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pan_profil.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pan_outil.setBorder(BorderFactory.createEmptyBorder(0, 0, -30, 0));
		pan_ft1.setBorder(BorderFactory.createEmptyBorder(0, 0, -30, 50));

		pan_outil.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_numero_poste_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_numero_poste_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		pan_produit_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_produit_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		pan_profil_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_profil_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		


		pan_comb_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_comb_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		// Setup the content-pane of JFrame in BorderLayout

	
		pan_button.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_but_tache.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_form.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_ft.setLayout(new BoxLayout(pan_ft, BoxLayout.X_AXIS));
		panel_article.setLayout(new BoxLayout(panel_article, BoxLayout.Y_AXIS));
		panel_observation.setLayout(new BoxLayout(panel_observation, BoxLayout.Y_AXIS));

		pan_form1.setLayout(new BoxLayout(pan_form1, BoxLayout.Y_AXIS));
		// pan_form.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 100));
		pan_tab.setLayout(new BoxLayout(pan_tab, BoxLayout.Y_AXIS));
		pan_comb.setLayout(new GridLayout(2, 1, 0, -25));
		pan_produit.setLayout(new GridLayout(2, 1, 0, -25));
		pan_profil.setLayout(new GridLayout(2, 1, 0, -25));
		pan_numero_poste.setLayout(new GridLayout(2, 1, 0, 5));

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//		Container cp = this.getContentPane();
//		cp.add(panel);
		// cp.add(tAreaScrollPane, BorderLayout.CENTER);
		setTitle("Tache de Production");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setContentPane(jTabbedPane);
		 tab_article.table.setRowHeight(20);
		 tab_observation.table.setRowHeight(20);
		tab.table.setRowHeight(20);
	}

	public boolean isValid(String chaine) {
		try {
			Float.parseFloat(chaine);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public boolean validnombre()

	{
		if (tab.table.getCellEditor() != null) {

			tab.table.getCellEditor().stopCellEditing();

		}
		for (int i = 0; i < tab.table.getRowCount(); i++) {

			String indice = tab.table.getValueAt(i, 1).toString();
			String coef = tab.table.getValueAt(i, 4).toString();

			if (isValid(indice) == true && isValid(coef) == true)

			{
				return false;

			}

		}

		return true;
	}

	public boolean vaidCheck(Tableau tab)

	{

		if (tab.table.getCellEditor() != null) {

			tab.table.getCellEditor().stopCellEditing();

		}

		for (int l = 0; l < tab.table.getRowCount(); l++)

		{

			for (int k = 0; k < tab.table.getColumnCount(); k++)

			{

				String om = tab.table.getValueAt(l, k).toString();
              //  System.out.println("gggg"+om);
				if (om.trim().length() == 0
						|| om.equals("---Sélectionner un Poste-----"))

				{
					msg = "";
					return false;

				}
			}
		}

		return true;
	}
	public boolean exist(jcombo jcom,String item){
	boolean exists = false;
	 for (int index = 0; index < jcom.getItemCount() && !exists; index++) {
	   if (item.equals(jcom.getItemAt(index))){
	     exists = true;
	   }
	 }
	 return exists;
	}
	
	public void action(){
		String part1="";
		 String[] part_poste = poste_comb.getSelectedItem().toString().split(" ");
	      part1 = part_poste[0];
		LineBorder border = new LineBorder(Color.white, 1, true);
		TitledBorder titl2 = new TitledBorder(border, "Edition Process",
				TitledBorder.DEFAULT_POSITION,
				TitledBorder.DEFAULT_POSITION, police2, Color.white);
		pan_ft.setBorder(titl2);
		pan_but_tache.setVisible(true);
      

		int rows = tab.table.getRowCount();
		for (int i = rows - 1; i >= 0; i--) {
			((DefaultTableModel) tab.table.getModel()).removeRow(i);
		}
		
		int rows1 = tab_article.table.getRowCount();
		for (int i = rows1 - 1; i >= 0; i--) {
			((DefaultTableModel) tab_article.table.getModel()).removeRow(i);
		}
		int rowso = tab_observation.table.getRowCount();
		for (int i = rowso - 1; i >= 0; i--) {
			((DefaultTableModel) tab_observation.table.getModel()).removeRow(i);
		}
				if(tache.select_poste_profils(profils_comb.getSelectedItem().toString(),part1)){
					existe_profil=true;
				}else{existe_profil=false;}
                list_zone=tache.select_tache(part1,profils_comb.getSelectedItem().toString(),existe_profil,
                num_poste,profils_prod_c);
                if(profils_comb.getSelectedIndex()!=0&&produit_comb.getSelectedIndex()!=0&&
                		numero_poste_comb.getSelectedIndex()!=0&&poste_comb.getSelectedIndex()!=0){
               outilage= tache.select_outil(part1,profils_comb.getSelectedItem().toString(),existe_profil,
                        num_poste,profils_prod_c);
               System.out.println("lllll"+outilage.length);
               for(int j=0;j<outilage.length;j++){
            	System.out.println("tttttttttt"+outilage[j]);
//            	 //  outil.addSelectionInterval(0, 7);
           	 outil.setSelectedValue(outilage[j], true);
           	 model.removeElement(outilage[j]); 
              if(!model.contains(outilage[j])){
            	//  model.removeElement(outilage[j]) ; 
         	    model.add(j,outilage[j]);
         	   outil.addSelectionInterval(0,j);
              }
           }
                    	
                }
             
                	for (i = 0; i < list_zone.size(); i = i + 7) {
					tab.ajouter();
					TableColumn gradeColumn = tab.table.getColumnModel().getColumn(0);
					gradeColumn.setCellEditor(new DefaultCellEditor(tache_comb));
					TableColumn gradeColumn2 = tab.table.getColumnModel().getColumn(3);
					gradeColumn2.setCellEditor(new DefaultCellEditor(marg));
					TableColumn gradeColumn3 = tab.table.getColumnModel().getColumn(1);
					gradeColumn3.setCellEditor(new DefaultCellEditor(article_comb2));
					int k = tab.table.getRowCount();
					tab.table.setValueAt("", k - 1, 0);
					tab.table.setValueAt(" ", k - 1, 1);
					tab.table.setValueAt("", k - 1, 2);
					tab.table.setValueAt("", k - 1, 3);
					tab.table.setValueAt("", k - 1, 4);
				}
				
				int j = 1;
				int l = 0;
				i = 1;
				while (l <list_zone.size()) {
					while (l < j * 7) {
						tab.getTable().setValueAt(list_zone.get(l)+ " "+ list_zone.get(l + 1), j - 1, 0);
						if(existe_profil==true){
						tab.getTable().setValueAt(list_zone.get(l+2)+ " "+ list_zone.get(l + 3), j - 1, 1);
						tab.getTable().setValueAt(list_zone.get(l + 4),j - 1, 2);
						tab.getTable().setValueAt(list_zone.get(l + 5),j - 1, 3);
						tab.getTable().setValueAt(list_zone.get(l + 6),j - 1, 4);
						}
						
						else {
							tab.getTable().setValueAt("", j - 1, 1);
							tab.getTable().setValueAt("0",j - 1, 2);
							tab.getTable().setValueAt("0",j - 1, 3);
							tab.getTable().setValueAt("0",j - 1, 4);
						}
						i = i + 1;
						l = l + 7;
					}

					i = 1;
					j = j + 1;
				}
				
				
				
		/**************************selection article*****************************/
				
				list_article_t=tache.select_article(part1,profils_comb.getSelectedItem().toString(),
		        num_poste,profils_prod_c);
		                	for (k = 0; k < list_article_t.size(); k = k + 7) {
		                		tab_article.ajouter();
		        				TableColumn gradeColumn = tab_article.table.getColumnModel().getColumn(0);
		        				gradeColumn.setCellEditor(new DefaultCellEditor(article_comb));
		        				int k = tab_article.table.getRowCount();
		        				tab_article.table.setValueAt("", k - 1, 0);
		        				tab_article.table.setValueAt("", k - 1, 1);
		        				tab_article.table.setValueAt("", k - 1, 2);
		        				tab_article.table.setValueAt("", k - 1, 3);
		        				tab_article.table.setValueAt("", k - 1, 4);
		        				tab_article.table.setValueAt("", k - 1, 5);
						}
						int z = 1;
						int e = 0;
						k = 1;
						while (e <list_article_t.size()) {
							while (e < z * 5) {
								tab_article.getTable().setValueAt(list_article_t.get(e)+ " "+ list_article_t.get(e + 1), z - 1, 0);
								tab_article.getTable().setValueAt(list_article_t.get(e+2), z - 1, 1);
								tab_article.getTable().setValueAt(list_article_t.get(e), z - 1, 2);
								tab_article.getTable().setValueAt(list_article_t.get(e+1), z - 1, 3);
								tab_article.getTable().setValueAt(list_article_t.get(e+3), z - 1, 4);
								tab_article.getTable().setValueAt(list_article_t.get(e + 4),z - 1, 5);
								k = k + 1;
								e = e + 5;
							}

							k = 1;
							z = z + 1;
						}
						
	/*************************************selectioner observation*******************************/
						list_observation_t=tache.select_observation(part1,profils_comb.getSelectedItem().toString(),
				                num_poste,profils_prod_c);
				                
				                	for (m = 0; m < list_observation_t.size(); m = m + 1) {
									tab_observation.ajouter();
									
									int k = tab_observation.table.getRowCount();
									tab_observation.table.setValueAt("", k-1 , 0);
									
								}
								
								int r = 1;
								int t = 0;
								m = 1;
								while (t <list_observation_t.size()) {
									while (t < r ) {
										tab_observation.getTable().setValueAt(list_observation_t.get(t), r-1 , 0);
										m = m + 1;
										t = t + 1;
									}

									m = 1;
									r = r + 1;
								}
				
				
				
				
			
			
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					new process_tache("7");
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
