package logiciel_etiq;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class zone_process extends JFrame implements ActionListener {
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
			poste_comb.enable();
			zone_comb.enable();
			pan_but_poste.setVisible(true);
			retour.setVisible(false);
			but_modif.setVisible(true);
			but_sup.setVisible(true);
			valid_supp.setVisible(false);
			valid_modif.setVisible(false);
			
			zone_comb.setSelectedIndex(0);
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
	
	private boolean co_bo2;
	private boolean repet2;
	
	private boolean valid_j;
	private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

	private JPanel panel;
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
	
	private JPanel pan_profil= new JPanel();
	private JPanel pan_profil_lab = new JPanel();
	private JPanel pan_profil_comb = new JPanel();

	
	private JPanel pan_button = new JPanel();
	private JPanel pan_tab = new JPanel();
	private JPanel pan_but_poste = new JPanel();


	
	private JButton but_ajou = new JButton("Ajouter un Poste");
	private JButton but_supprim = new JButton("Supprimer un Poste");

	private JButton valid_supp = new JButton("Valider");
	private JButton valid_modif = new JButton("Valider");
	private JButton retour = new JButton("Retour");

	private JButton but_modif = new JButton("Sauvegarder Process ");
	private JButton but_sup = new JButton("Supprimer");

	private JLabel comb_lab = new JLabel("Zone");
	private JLabel pro_lab = new JLabel("Produits");
	private JLabel prof_lab = new JLabel("Process");


	private jcombo zone_comb ;
	private jcombo produit_comb ;
	private jcombo profils_comb ;

	private Java2sAutoComboBox poste_comb;
	final JButton buttonOk = new JButton("Ajouter ");
	private Object[] entete = { "Code/Intitul� Poste", "Effectif de Poste" };
	private final Tableau tab = new Tableau(entete);
	//private gestion_user use = new gestion_user();
	private gestion_zone zone = new gestion_zone();

	private JScrollPane p = new JScrollPane(tab);
	private int i;
	private static List<Object> list_p;
	private static List<Object> list_z;
	private static List<String> list_pro;
	private static List<String> list_prof;
    private boolean existe_profil;
    String profils_prod;
    int profils_prod_c;
	//private static ArrayList<String> list_zone_tr=new ArrayList<String>();
	zone_process(final String log) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
		setIconImage(img);
		selectioncomb.windows(this, log, laf);
		composant(log);
	}

	@SuppressWarnings("deprecation")
	public void composant(final String log) {
		
		
//		zone_comb.addItem("--S�lectionner une Zone--");
//		list_zone_tr=use.select_zone_code();
//		for (int i = 0; i < list_zone_tr.size(); i++) {
//			// Pour affecter une valeur de base de donn�es � un Combobox
//			zone_comb.addItem(list_zone_tr.get(i)+" "+list_zone_tr.get(i + 1));
//			i++;
//		}
		
		
		  list_z = new ArrayList<Object>(Arrays.asList(new String[]{"---S�lectionner une Zone-----"}));
		  zone_comb = new jcombo(list_z.toArray());
	       selectioncomb.selectzone(zone_comb ,this,log);	      
	     
		
		list_pro = new ArrayList<String>(
				Arrays.asList(new String[] { "---S�lectionner un Produit-----" }));
				produit_comb = new jcombo(list_pro.toArray());
				selectioncomb.selectproduit(produit_comb ,this,log);
				
		list_prof = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un processe-----"}));
	    profils_comb = new jcombo(list_prof.toArray());
		selectioncomb.selectprocess(profils_comb  ,this,log);	
		
		
		   list_p = new ArrayList<Object>(Arrays.asList(new String[]{"---S�lectionner un Poste-----"}));
					//zone_comb = new jcombo(list_z.toArray());
				   // selectioncomb.selectzone_chaine1(this,log,tab,list_z);

				   poste_comb = new Java2sAutoComboBox(list_p);
				   selectioncomb.selectposte_tab(poste_comb ,this,log,tab,list_p);
					//selectioncomb.selecttache_poste(poste_comb,this,log,tab,list_p);
			       
					poste_comb.setDataList(list_p);
					
	
		
		if(!selectioncomb.prv.contains("zone_process"))
		selectioncomb.prv.add("zone_process");
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
		// description.disable();
		pan_but_poste.setVisible(true);
		but_supprim.disable();
		zone_comb.setSelectedIndex(0);
		// tab.allowEdition1=false;

		tab.setStyle(2);

		panel = new JPanel() {
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
		TitledBorder titl2 = new TitledBorder(border, "Edition Zone",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		pan_ft.setBorder(titl2);

		
		
		produit_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] part_prod = produit_comb.getSelectedItem().toString().split(" ");
       		    String part_prode = part_prod[0];
       		    if(produit_comb.getSelectedIndex()!=0){
       		    	 profils_prod=zone.select_produit_profil(part_prode).get(0);
				     profils_comb.setSelectedItem(profils_prod);
				     profils_prod_c=Integer.parseInt(zone.select_produit_profil(part_prode).get(1));
       		     }
       		    else{
       		    	profils_comb.setSelectedIndex(0);
       		    }
			}});
		
		
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Edition Zone",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan_ft.setBorder(titl2);
				poste_comb.enable();
				zone_comb.enable();
				pan_but_poste.setVisible(true);
				retour.setVisible(false);
				but_modif.setVisible(true);
				but_sup.setVisible(true);
				valid_supp.setVisible(false);
				valid_modif.setVisible(false);
				zone_comb.setSelectedIndex(0);
				int rows = tab.table.getRowCount();

				for (int i = rows - 1; i >= 0; i--) {
					((DefaultTableModel) tab.table.getModel()).removeRow(i);
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
				
				poste_comb.disable();
				pan_but_poste.setVisible(false);
				zone_comb.enable();

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
				TableColumn gradeColumn = tab.table.getColumnModel().getColumn(
						0);
				gradeColumn.setCellEditor(new DefaultCellEditor(poste_comb));
				int k = tab.table.getRowCount();
				tab.table.setValueAt("", k - 1, 0);
				tab.table.setValueAt("", k - 1, 1);
			}
		});
		
		valid_supp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	use.select_zone_jtext(designation.getText());
				msg = "";
				if (zone_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord S�lectionner une zone\n";
				} else if (produit_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord S�lectionner un produit\n";
				}
				else if (profils_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord S�lectionner un process\n";
				}
				if (!msg.equals("")) {
					JOptionPane.showMessageDialog(null, msg);
				} else {
					String[] parts = zone_comb.getSelectedItem().toString().split(" ");
					String zone_fi = parts[0]; // 004
					zone.setupdate_zone_tab_delete(zone_fi, profils_prod_c);
					poste_comb.enable();
					JOptionPane.showMessageDialog(null,"La Zone du process a �t� bien supprim�e");
				}
			}
		});

		but_supprim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tab.table.getSelectedRow() == -1) {
					JOptionPane
							.showMessageDialog(
									null,
									"Vous devez d'abord s�lectionner le poste que vous voulez supprimer",
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
				zone_comb.enable();
				poste_comb.enable();
				pan_but_poste.setVisible(true);

				// if(use.exist_des==true){
				// JOptionPane.showMessageDialog(null,
				// "Cette Zone n'existe pas", "",
				// JOptionPane.INFORMATION_MESSAGE);
				//
				// but_sauv.setVisible(true);
				// but_modif.setVisible(false);
				// but_sup.setVisible(false);
				// retour.setVisible(false);
				// valid_supp.setVisible(false);
				// valid_modif.setVisible(false);
				// valid_ajou.setVisible(false);
				// int rows = tab.table.getRowCount();
				// for(int i = rows - 1; i >=0; i--)
				// {
				// ((DefaultTableModel) tab.table.getModel()).removeRow(i);
				//
				// }
				// zone_comb.setSelectedIndex(0);
				// zone_fi.setText(use.afficher_codezone());
				// }
				// else{
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
				co_bo2 = true;
				valid_j = false;
				repet2 = false;
				
				// System.out.print(designation.getText()+" "+use.exist_des);
				if (zone_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord S�lectionner une zone\n";
				} else if (produit_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord S�lectionner un produit\n";
				}
				else if (profils_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord S�lectionner un process\n";
				}
				else if (tab.table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null,
							"Vous devez  d'abord ajouter un poste ", "",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					if (vaidCheck() == false) {
						valid_j = true;
					}

					if (valid_j == false) {
						for (int i = 0; i < tab.table.getRowCount(); i++) {
							for (int j = i + 1; j < tab.table.getRowCount(); j++) {

								if (tab.table
										.getValueAt(i, 0)
										.toString()
										.equals(tab.table.getValueAt(j, 0)
												.toString())) {
									
									repet2 = true;
								}
							}
						}}
					for (int i = 0; i < tab.table.getRowCount(); i++) {
						String col2 = tab.table.getValueAt(i, 1).toString();
						if (isValid(col2) == false||col2.equals("0")) {
							co_bo2 = false;
						}
					}

					

					// System.out.print(co_bo2+""+repet2+""+desc2+""+valid_j+""+comb+""+use.exist_des);
					if (co_bo2 == true && repet2 == false && valid_j == false ) {
						String[] zone_l = zone_comb.getSelectedItem().toString().split(" ");
						String zone_p = zone_l[0]; // 004
						
						zone.setupdate_zone_tab_delete(zone_p,profils_prod_c);

						for (int i = 0; i < tab.table.getRowCount(); i++) {
							String col2 = tab.table.getValueAt(i, 1).toString();
							int col2_i = Integer.parseInt(col2);
							String[] parts = tab.table.getValueAt(i, 0)
									.toString().split(" ");
							String part1 = parts[0]; // 004
							zone.setupdate_zone_tab_ajou(zone_p,col2_i, part1,profils_prod_c);
						}
					
					}

				 if (valid_j == true) {
						msg += "Un Champ dans le tableau est vide \n";
					} else if (co_bo2 == false) {
						msg += "L'effectif de postes doit �tre un nombre ou diff�rent de zero \n";
					} else if (repet2 == true) {
						msg += "Il existe plusieurs ou un poste dupliqu�. \n";
					}

					if (!msg.equals("")) {
						JOptionPane.showMessageDialog(null, msg);
					}

					else {
						JOptionPane.showMessageDialog(null,"La Zone du process a �t� bien modifi�e");
						
					}
				}
			}
		});

		

		

		poste_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (poste_comb.hasFocus()) {
					// /
					if (poste_comb.getSelectedIndex() != 0) {

						String[] parts = poste_comb.getSelectedItem().toString().split(" ");
						String part1 = parts[0]; // 004
						String[] zone_l = zone_comb.getSelectedItem().toString().split(" ");
						String zone_p = zone_l[0]; // 004
						tab.table.setValueAt(zone.select_poste_num(part1, zone_p,profils_comb.getSelectedItem().toString()),
								tab.table.getSelectedRow(), 1);
					}
				}

			}
		});

		zone_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action();
				}
		});
		
		profils_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action();
			}});

		
		  
		
		try {
			UIManager.setLookAndFeel(laf);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Font police_fi = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13);

		comb_lab.setFont(police2);
		pro_lab.setFont(police2);
		prof_lab.setFont(police2);

		
		
		pro_lab.setFont(police2);
		prof_lab.setFont(police2);

		
		tab.table.setFont(police_fi);
		zone_comb.setFont(police_fi);
		produit_comb.setFont(police_fi);
		profils_comb.setFont(police_fi);

		
		// description.setPreferredSize(new Dimension(210,100));
		pro_lab.setForeground(new Color(255, 255, 255));
		prof_lab.setForeground(new Color(255, 255, 255));

		
		comb_lab.setForeground(new Color(255, 255, 255));
		// ***************************************************************test**********************************************

		

	
		// *******************************************************************************************************************
		
		
		
		pan_form1.add(pan_produit);
		pan_form1.add(pan_profil);
	    pan_form1.add(pan_comb);
		pan_comb.add(pan_comb_lab);
		pan_comb.add(pan_comb_jtext);
		pan_produit.add(pan_produit_lab);
		pan_produit.add(pan_produit_comb);
		pan_produit_lab.add(pro_lab);
		pan_produit_comb.add(produit_comb);
		pan_profil.add(pan_profil_lab);
		pan_profil.add(pan_profil_comb);
		pan_profil_lab.add(prof_lab);
		pan_profil_comb.add(profils_comb);
		pan_comb_lab.add(comb_lab);
		pan_comb_jtext.add(zone_comb);
		
		pan_ft1.add(pan_form1);
		pan_ft1.add(pan_form);

		panel.add(pan_ft);
		pan_ft.add(pan_ft1);

		pan_ft.add(pan_tab);
		pan_tab.add(p);
		pan_tab.add(pan_but_poste);
		panel.add(pan_button);

	
		// pan_desc.add(description);

		pan_but_poste.add(but_ajou);
		pan_but_poste.add(but_supprim);
		pan_button.add(retour);
		pan_button.add(valid_supp);
		pan_button.add(valid_modif);
		pan_button.add(but_modif);
		pan_button.add(but_sup);
		pan_comb.setOpaque(false);
		pan_comb_lab.setOpaque(false);
		pan_comb_jtext.setOpaque(false);
		
	
		pan_ft.setOpaque(false);
		pan_form.setOpaque(false);
		pan_button.setOpaque(false);
		
		pan_ft1.setOpaque(false);
		pan_form1.setOpaque(false);

		pan_produit_lab.setOpaque(false);
		pan_profil_lab.setOpaque(false);
		pan_profil_comb.setOpaque(false);
		pan_produit_comb.setOpaque(false);
		pan_produit.setOpaque(false);
		pan_profil.setOpaque(false);

		// tab.setPreferredSize(new Dimension(700,700));
		p.setPreferredSize(new Dimension(500, 700));
		pan_ft1.setPreferredSize(new Dimension(300, 700));
		zone_comb.setPreferredSize(new Dimension(230, 30));
		produit_comb.setPreferredSize(new Dimension(230, 30));
		profils_comb.setPreferredSize(new Dimension(230, 30));


		but_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_supprim.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_sup.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_supp.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		but_ajou.setPreferredSize(new Dimension(150, 33));
		but_modif.setPreferredSize(new Dimension(180, 33));
		but_supprim.setPreferredSize(new Dimension(150, 33));
		but_sup.setPreferredSize(new Dimension(120, 33));
		retour.setPreferredSize(new Dimension(120, 33));
		valid_supp.setPreferredSize(new Dimension(120, 33));
		valid_modif.setPreferredSize(new Dimension(120, 33));

		p.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		pan_button.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
		comb_lab.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 75));
		pro_lab.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 75));
		prof_lab.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 75));

		
		
		pan_ft1.setBorder(BorderFactory.createEmptyBorder(0, -10, -30, 0));


		pan_produit_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_produit_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		pan_profil_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_profil_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		
	
		pan_comb_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_comb_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		// Setup the content-pane of JFrame in BorderLayout

		// pan_desc.setLayout(new FlowLayout(FlowLayout.LEFT));
		

		pan_button.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_but_poste.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_form.setLayout(new FlowLayout(FlowLayout.RIGHT));

		pan_ft.setLayout(new BoxLayout(pan_ft, BoxLayout.X_AXIS));
		pan_form1.setLayout(new BoxLayout(pan_form1, BoxLayout.Y_AXIS));
		// pan_form.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 100));
		pan_tab.setLayout(new BoxLayout(pan_tab, BoxLayout.Y_AXIS));
		pan_comb.setLayout(new GridLayout(2, 1, 0, 0));
		pan_produit.setLayout(new GridLayout(2, 1, 0, 0));
		pan_profil.setLayout(new GridLayout(2, 1, 0, 0));
	

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Container cp = this.getContentPane();
		cp.add(panel);
		// cp.add(tAreaScrollPane, BorderLayout.CENTER);

		setTitle("Zone de Production");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);

		tab.table.setRowHeight(20);

	}

	public boolean isValid(String chaine) {
		try {
			Integer.parseInt(chaine);
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

	public boolean vaidCheck()

	{

		if (tab.table.getCellEditor() != null) {

			tab.table.getCellEditor().stopCellEditing();

		}

		for (int l = 0; l < tab.table.getRowCount(); l++)

		{

			for (int k = 0; k < tab.table.getColumnCount(); k++)

			{

				String om = tab.table.getValueAt(l, k).toString();
               // System.out.println(om);
				if (om.trim().length() == 0
						|| om.equals("---S�lectionner un Poste-----"))

				{
					msg = "";
					return false;

				}
			}
		}

		return true;
	}
//	public boolean exist(jcombo jcom,String item){
//	boolean exists = false;
//	 for (int index = 0; index < jcom.getItemCount() && !exists; index++) {
//	   if (item.equals(jcom.getItemAt(index))){
//	     exists = true;
//	   }
//	 }
//	 return exists;
//	}
	public void action(){
		String part1="";
		LineBorder border = new LineBorder(Color.white, 1, true);
		TitledBorder titl2 = new TitledBorder(border, "Edition Process",
				TitledBorder.DEFAULT_POSITION,
				TitledBorder.DEFAULT_POSITION, police2, Color.white);
		pan_ft.setBorder(titl2);
		pan_but_poste.setVisible(true);
      

		int rows = tab.table.getRowCount();
		for (int i = rows - 1; i >= 0; i--) {
			((DefaultTableModel) tab.table.getModel()).removeRow(i);

		}

				String[] part_prod = produit_comb.getSelectedItem().toString().split(" ");
       		    String part_prode = part_prod[0];
       		    String[] part_zone = zone_comb.getSelectedItem().toString().split(" ");
    		     part1 = part_zone[0];
    		     
       		    System.out.println(part_prode+" "+part1+" "+profils_comb.getSelectedItem().toString()+"popopop"+zone.select_zone_profils(profils_comb.getSelectedItem().toString(),part1));
				
       		    if(zone.select_zone_profils(profils_comb.getSelectedItem().toString(),part1)){
					existe_profil=true;
				}else{existe_profil=false;}
                list_zone=zone.select_zone(part1,profils_comb.getSelectedItem().toString(),existe_profil);
				for (i = 0; i < list_zone.size(); i = i + 3) {
					tab.ajouter();
					TableColumn gradeColumn = tab.table
							.getColumnModel().getColumn(0);
					gradeColumn.setCellEditor(new DefaultCellEditor(poste_comb));
				}
				
				int j = 1;
				int l = 0;
				i = 1;
				while (l <list_zone.size()) {
					while (l < j * 3) {
						tab.getTable().setValueAt(
								list_zone.get(l)
										+ " "
										+ list_zone.get(
												l + 1), j - 1, 0);
						if(existe_profil==true){
						tab.getTable().setValueAt(
								list_zone.get(l + 2),
								j - 1, 1);}
						else {
							tab.getTable().setValueAt(
									0,
									j - 1, 1);
						}
						i = i + 1;
						l = l + 3;
					}

					i = 1;
					j = j + 1;
				}
			
			
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					new zone_process("7");
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
