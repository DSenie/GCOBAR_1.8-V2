package logiciel_etiq;

import java.awt.BorderLayout;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class zone extends JFrame implements ActionListener {
	private static final String CTRL_J = "CTRL+J";
	final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18);

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {

		// gestion de l'action

		if (e.getActionCommand().equals(CTRL_J)) {
			LineBorder border = new LineBorder(Color.white, 1, true);
			TitledBorder titl2 = new TitledBorder(border, "Edition Zone",
			TitledBorder.DEFAULT_POSITION,
			TitledBorder.DEFAULT_POSITION, police2, Color.white);
			pan_ft.setBorder(titl2);
			zone_fi.disable();
			description.enable();
			designation.enable();
			poste_comb.enable();
			zone_comb.enable();
			pan_but_poste.setVisible(true);
			retour.setVisible(false);
			but_sauv.setVisible(true);
			but_modif.setVisible(false);
			but_sup.setVisible(false);
			valid_supp.setVisible(false);
			valid_modif.setVisible(false);
			valid_ajou.setVisible(false);
			description.setText("");
			designation.setText("");
			zone_comb.setSelectedIndex(0);
			zone_fi.setText(code);
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
	private boolean co_bo1;
	private boolean repet;
	private boolean desc;
	private boolean repet2;
	private boolean desc2;
	private boolean comb;
	private boolean valid;
	private boolean valid_j;
	private boolean comb_intitule;
    private boolean existe=true;
	private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

	private JPanel panel;
	private JPanel pan_form = new JPanel();
	private JPanel pan_form1 = new JPanel();
	private JPanel pan_ft1 = new JPanel();

	private JPanel pan_ft = new JPanel();
	private JPanel pan_comb = new JPanel();
	private JPanel pan_comb_lab = new JPanel();
	private JPanel pan_comb_jtext = new JPanel();

	private JPanel pan_feild = new JPanel();
	private JPanel pan_feild_lab = new JPanel();
	private JPanel pan_feild_jtext = new JPanel();
	private JPanel pan_button = new JPanel();
	private JPanel pan_tab = new JPanel();
	private JPanel pan_but_poste = new JPanel();

	private JPanel pan_desc = new JPanel();

	private JPanel pan_desi = new JPanel();
	private JPanel pan_desi_lab = new JPanel();
	private JPanel pan_desi_jtext = new JPanel();
	private JButton but_ajou = new JButton("Ajouter un Poste");
	private JButton but_supprim = new JButton("Supprimer un Poste");
	private JButton valid_ajou = new JButton("Valider");
	private JButton valid_supp = new JButton("Valider");
	private JButton valid_modif = new JButton("Valider");
	private JButton retour = new JButton("Retour");
	private JButton but_sauv = new JButton("Ajouter");
	private JButton but_modif = new JButton("Modifier");
	private JButton but_sup = new JButton("Supprimer");

	private JTextField zone_fi = new JTextField();
	private JLabel comb_lab = new JLabel("Zone");
	private JLabel zone_lab = new JLabel("Code Zone");
	private JTextField designation = new JTextField();
	private JLabel designation_lab = new JLabel("D?signation");
	private JTextArea description = new JTextArea();
	private JLabel description_lab = new JLabel("D?scription");
	private jcombo zone_comb;
	private Java2sAutoComboBox poste_comb;
	final JButton buttonOk = new JButton("Ajouter ");
	private Object[] entete = { "Code/Intitul? Poste" };
	private final Tableau tab = new Tableau(entete);
	private gestion_user use = new gestion_user();
	private JScrollPane p = new JScrollPane(tab);
	private int i;
	private static List<Object> list_p;
	private static List<String> list_z;

	private static ArrayList<String> list_zone_tr=new ArrayList<String>();
    private String code;
	zone(final String log) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage(getClass().getResource("icon.png"));
		setIconImage(img);
		selectioncomb.windows(this, log, laf);
		composant(log);
	}

	@SuppressWarnings("deprecation")
	public void composant(final String log) {

		
		 list_z = new ArrayList<String>(Arrays.asList(new String[]{"--S?lectionner une Zone--"}));
		 zone_comb = new jcombo(list_z.toArray());
	   
		 list_zone_tr=use.select_zone_code();	  
		   for(int i=0;i<list_zone_tr.size();i++)
		   {
		          //Pour affecter une valeur de base de donn?es ? un Combobox 
			   zone_comb.addItem(list_zone_tr.get(i)+" "+list_zone_tr.get(i+1));
			   i++;
		   }
		   
		   
		
		   list_p = new ArrayList<Object>(Arrays.asList(new String[]{"---S?lectionner un Poste-----"}));
			//zone_comb = new jcombo(list_z.toArray());
		   // selectioncomb.selectzone_chaine1(this,log,tab,list_z);

		   poste_comb = new Java2sAutoComboBox(list_p);
		   selectioncomb.selectposte_tab(poste_comb ,this,log,tab,list_p);
			//selectioncomb.selecttache_poste(poste_comb,this,log,tab,list_p);
	       
			poste_comb.setDataList(list_p);
			
	
		//poste_comb = new jcombo(list_p.toArray());
		
		if(!selectioncomb.prv.contains("zone"))
		selectioncomb.prv.add("zone");
//		for (int i = 0; i < imp.select_poste_code().size(); i++) {
//			// Pour affecter une valeur de base de donn?es ? un Combobox
//			poste_comb.addItem(imp.select_poste_code().get(i)+" "+imp.select_poste_code().get(i + 1));
//			i++;
//		}

		final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,18);

		but_sauv.setVisible(true);
		but_modif.setVisible(false);
		but_sup.setVisible(false);
		valid_supp.setVisible(false);
		valid_modif.setVisible(false);
		valid_ajou.setVisible(false);
		retour.setVisible(false);
		zone_fi.disable();
		// description.disable();
		designation.enable();
		pan_but_poste.setVisible(true);
		but_supprim.disable();
		zone_comb.setSelectedIndex(0);
		// tab.allowEdition1=false;

		tab.setStyle(2);

		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon(getClass()
						.getResource("menu.png"));
				img.paintIcon(this, g, 0, 0);
			}
		};
		panel.registerKeyboardAction(this, CTRL_J,
				KeyStroke.getKeyStroke(KeyEvent.VK_J, Event.CTRL_MASK),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		code=use.afficher_codezone();
		zone_fi.setText(code);
		LineBorder border = new LineBorder(Color.white, 1, true);
		TitledBorder titl2 = new TitledBorder(border, "Edition Zone",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		pan_ft.setBorder(titl2);

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Edition Zone",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan_ft.setBorder(titl2);
				zone_fi.disable();
				description.enable();
				designation.enable();
				poste_comb.enable();
				zone_comb.enable();
				pan_but_poste.setVisible(true);
				retour.setVisible(false);
				but_sauv.setVisible(true);
				but_modif.setVisible(false);
				but_sup.setVisible(false);
				valid_supp.setVisible(false);
				valid_modif.setVisible(false);
				valid_ajou.setVisible(false);
				description.setText("");
				designation.setText("");
				zone_comb.setSelectedIndex(0);
				zone_fi.setText(code);
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
				zone_fi.disable();
				description.disable();
				designation.enable();
				poste_comb.disable();
				pan_but_poste.setVisible(false);
				zone_comb.enable();

				if (existe == true) {
					JOptionPane.showMessageDialog(null,
							"Cette Zone n'existe pas", "",
							JOptionPane.INFORMATION_MESSAGE);
					but_sauv.setVisible(true);
					but_modif.setVisible(false);
					but_sup.setVisible(false);
					retour.setVisible(false);
					valid_supp.setVisible(false);
					valid_modif.setVisible(false);
					valid_ajou.setVisible(false);
					int rows = tab.table.getRowCount();
					for (int i = rows - 1; i >= 0; i--) {
						((DefaultTableModel) tab.table.getModel()).removeRow(i);

					}
					zone_comb.setSelectedIndex(0);
					zone_fi.setText(code);

				} else {
					but_sauv.setVisible(false);
					but_modif.setVisible(false);
					but_sup.setVisible(false);
					retour.setVisible(true);
					valid_supp.setVisible(true);
					valid_modif.setVisible(false);
					valid_ajou.setVisible(false);
				}
			}
		});

		valid_supp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	use.select_zone_jtext(designation.getText());
				msg = "";
				if (zone_comb.getSelectedIndex() == 0) {
					msg += "Vous Devez d'abord S?lectionner une zone\n";
				} else if (existe == true) {
					msg += "Cette Zone n'existe pas \n";
				}
				if (!msg.equals("")) {
					JOptionPane.showMessageDialog(null, msg);
				} else {
					use.setdelete_zone(zone_fi.getText(), designation.getText());
					designation.setText("");
					zone_comb.removeAllItems();
					zone_comb.addItem("--S?lectionner une Zone--");
					list_zone_tr=use.select_zone_code();
					for (int i = 0; i < list_zone_tr.size(); i++) {
						// Pour affecter une valeur de base de donn?es ? un
						// Combobox
						zone_comb.addItem(list_zone_tr.get(i)+" "+list_zone_tr.get(i + 1));
						i++;
					}
					poste_comb.enable();
				}
			}
		});

		but_supprim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tab.table.getSelectedRow() == -1) {
					JOptionPane
							.showMessageDialog(
									null,
									"Vous devez d'abord s?lectionner le poste que vous voulez supprimer",
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
				description.enable();
				designation.enable();
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
				but_sauv.setVisible(false);
				but_modif.setVisible(false);
				but_sup.setVisible(false);
				retour.setVisible(true);
				valid_supp.setVisible(false);
				valid_modif.setVisible(true);
				valid_ajou.setVisible(false);
				// }
			}
		});

		valid_modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	use.select_zone_jtext(designation.getText());
				msg = "";
				valid_j = false;
				repet2 = false;
				desc2 = false;
				comb = false;
				// System.out.print(designation.getText()+" "+use.exist_des);
				if (zone_comb.getSelectedIndex() == 0) {
					comb = true;
				}
				if (comb == true) {
					JOptionPane.showMessageDialog(null,
							"Veuillez d'abord S?lectionner une Zone \n");
				} else if (tab.table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null,
							"Vous devez  d'abord ajouter un poste ", "",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					if (vaidCheck() == false) {
						valid_j = true;
					}

					if (valid == false) {
						for (int i = 0; i < tab.table.getRowCount(); i++) {
							for (int j = i + 1; j < tab.table.getRowCount(); j++) {
//								System.out.println(repet+ " "+i+" "+j+" "+tab.table
//										.getValueAt(i, 0)
//										.toString()+" "+tab.table.getValueAt(j, 0)
//										.toString());
								if (tab.table
										.getValueAt(i, 0)
										.toString()
										.equals(tab.table.getValueAt(j, 0)
												.toString())) {
									
									repet2 = true;
									//System.out.println(repet2);
								}
							}
						}}
				

					if (designation.getText().equals(""))
						desc2 = true;

					// System.out.print(co_bo2+""+repet2+""+desc2+""+valid_j+""+comb+""+use.exist_des);
					if ( repet2 == false && desc2 == false
							&& valid_j == false && comb == false) {
						use.setupdate_zone_tab_delete(zone_fi.getText());

						for (int i = 0; i < tab.table.getRowCount(); i++) {
							
							String[] parts = tab.table.getValueAt(i, 0)
									.toString().split(" ");
							String part1 = parts[0]; // 004
							use.setupdate_zone_tab_ajou(zone_fi.getText(),
									 part1);
						}
						use.setupdate_zone(zone_fi.getText(),
								designation.getText(), description.getText());

					}

					if (desc2 == true) {
						msg += "Veuillez remplir la d?signation \n";
					} else if (valid_j == true) {
						msg += "Un Champ dans le tableau est vide \n";
					} else if (repet2 == true) {
						msg += "Il existe plusieurs ou un poste dupliqu?. \n";
					}

					if (!msg.equals("")) {
						JOptionPane.showMessageDialog(null, msg);
					}

					else {
						JOptionPane.showMessageDialog(null,
								"La Zone a ?t? bien modifi?e");
						designation.setText("");
						zone_comb.removeAllItems();
						zone_comb.addItem("--S?lectionner une Zone--");
						list_zone_tr=use.select_zone_code();
						for (int i = 0; i < list_zone_tr.size(); i++) {
							// Pour affecter une valeur de base de donn?es ? un
							// Combobox
							zone_comb.addItem(list_zone_tr.get(i)+" "+list_zone_tr.get(i + 1));
							i++;
						}
					}
				}
			}
		});

		but_sauv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//use.select_zone_jtext(designation.getText());
				// System.out.print(use.exist_des+" "+designation.getText());
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Ajouter",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan_ft.setBorder(titl2);
				zone_comb.disable();
				tab.allowEdition1 = true;
				zone_fi.setText(code);
				description.enable();
				designation.enable();
				poste_comb.enable();
				pan_but_poste.setVisible(true);
         //   System.out.println("dddddd"+existe);
				if (existe == false) {
					JOptionPane.showMessageDialog(null,
							"Cette Zone existe d?j?", "",
							JOptionPane.INFORMATION_MESSAGE);
					but_sauv.setVisible(false);
					but_modif.setVisible(true);
					but_sup.setVisible(true);
					retour.setVisible(false);
					valid_supp.setVisible(false);
					valid_modif.setVisible(false);
					valid_ajou.setVisible(false);
					//use.select_zone_jtext(designation.getText());
					zone_fi.setText(use.code_z);
					zone_comb.setSelectedItem(use.code_z + " " + use.desig);
					description.setText(use.desc);
				} else {
					but_sauv.setVisible(false);
					but_modif.setVisible(false);
					but_sup.setVisible(false);
					retour.setVisible(true);
					valid_supp.setVisible(false);
					valid_modif.setVisible(false);
					valid_ajou.setVisible(true);
				}
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
				//tab.table.setValueAt("", k - 1, 1);
			}
		});

		valid_ajou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msg = "";
				co_bo1 = true;
				valid = false;
				repet = false;
				desc = false;
				//use.select_zone_jtext(designation.getText());
				if (existe == false) {
					JOptionPane.showMessageDialog(null,
							"Cette Zone existe d?j?", "",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (tab.table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null,
							"Vous devez d'abord ajouter un poste ", "",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (vaidCheck() == false) {
						valid = true;
					}
					if (valid == false) {
						for (int i = 0; i < tab.table.getRowCount(); i++) {
							for (int j = i + 1; j < tab.table.getRowCount(); j++) {
								if (tab.table
										.getValueAt(i, 0)
										.toString()
										.equals(tab.table.getValueAt(j, 0)
												.toString())) {
									repet = true;
								}
							}
						}

						for (int i = 0; i < tab.table.getRowCount(); i++) {
							
							if (co_bo1 == true && repet == false) {
								String[] parts = tab.table.getValueAt(i, 0)
										.toString().split(" ");
								String part1 = parts[0]; // 004
								use.ajouter_zone_poste(zone_fi.getText(),
										part1);

							}
						}
					}

					if (designation.getText().equals(""))
						desc = true;
					if (co_bo1 == true && repet == false && desc == false
							&& valid == false ) {
						use.ajouter_zone(zone_fi.getText(),
								designation.getText(), description.getText());

						JOptionPane.showMessageDialog(null,
								"La zone a ?t? bien ajout?e");
						designation.setText("");
						zone_comb.enable();
						zone_comb.removeAllItems();
						zone_comb.addItem("--S?lectionner une Zone--");
						list_zone_tr=use.select_zone_code();
						for (int i = 0; i < list_zone_tr.size(); i++) {
							// Pour affecter une valeur de base de donn?es ? un
							// Combobox
							zone_comb.addItem(list_zone_tr.get(i)
									+ " " + list_zone_tr.get(i + 1));
							i++;
						}
					}

					if (desc == true) {
						msg += "Veuillez remplir la d?signation \n";
					} else if (valid == true) {
						msg += "Un Champ dans le tableau est vide \n";
					} else if (co_bo1 == false) {
						msg += "L'effectif de poste doit ?tre un nombre\n";
					} else if (repet == true) {
						msg += "Il existe un ou plusieur postes dupliqu?s \n";
					}
					if (!msg.equals("")) {
						JOptionPane.showMessageDialog(null, msg);
					}
				}
			}
		});

		poste_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (poste_comb.hasFocus()) { 
					// /
					if (poste_comb.getSelectedIndex() != 0) {

						String[] parts = poste_comb.getSelectedItem()
								.toString().split(" ");
						String part1 = parts[0]; // 004
						tab.table.setValueAt(
								use.select_poste_num(part1, zone_fi.getText()),
								tab.table.getSelectedRow(), 1);
					}
				}

			}
		});

		zone_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String part1="";
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Edition Zone",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan_ft.setBorder(titl2);
				pan_but_poste.setVisible(true);
				description.enable();
	            if(zone_comb.getItemCount()!=0) {
				String[] parts = zone_comb.getSelectedItem().toString().split(" ");
				 part1 = parts[0]; // 004
	             
				existe=use.select_zone_jtext(part1);}

				int rows = tab.table.getRowCount();
				for (int i = rows - 1; i >= 0; i--) {
					((DefaultTableModel) tab.table.getModel()).removeRow(i);

				}

				if (zone_comb.getSelectedIndex() == 0) {
					if (valid_modif.isVisible() == false
							|| valid_ajou.isVisible() == false
							|| valid_supp.isVisible() == false) {
						but_sauv.setVisible(true);
						valid_ajou.setVisible(false);
						valid_supp.setVisible(false);
						valid_modif.setVisible(false);
						retour.setVisible(false);
					}
					but_modif.setVisible(false);
					but_sup.setVisible(false);
					if (comb_intitule != true)
						designation.setText("");
					code=use.afficher_codezone();
					zone_fi.setText(code);
					description.setText("");
				} else {
					if (zone_comb.getItemCount() > 1) {
						comb_intitule = false;

						if (valid_modif.isVisible() == false
								|| valid_ajou.isVisible() == false
								|| valid_supp.isVisible() == false) {
							but_modif.setVisible(true);
							but_sup.setVisible(true);
							but_sauv.setVisible(false);
							retour.setVisible(false);
							valid_ajou.setVisible(false);
							valid_supp.setVisible(false);
							valid_modif.setVisible(false);
						} else {
							valid_ajou.setVisible(false);
							but_modif.setVisible(true);
							but_sup.setVisible(true);
							but_sauv.setVisible(false);
							retour.setVisible(false);
						}

						
						zone_fi.setText(part1);
                        list_zone=use.select_zone(part1);
						for (i = 0; i < list_zone.size(); i = i + 2) {
							tab.ajouter();
							TableColumn gradeColumn = tab.table
									.getColumnModel().getColumn(0);
							gradeColumn.setCellEditor(new DefaultCellEditor(poste_comb));
						}
						
						int j = 1;
						int l = 0;
						i = 1;
						while (l <list_zone.size()) {
							while (l < j * 2) {
								tab.getTable().setValueAt(
										list_zone.get(l)
												+ " "
												+ list_zone.get(
														l + 1), j - 1, 0);
								
								i = i + 1;
								l = l +2;
							}

							i = 1;
							j = j + 1;
						}
						description.setText(use.desc);
						designation.setText(use.desig);
					}}}
		});

		  designation.addFocusListener(new FocusListener() {
	             public void focusLost(FocusEvent e) {
	            	 existe=use.select_zone_jtext(designation.getText());	             }
				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
				}
	           });
		  
		designation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zone_comb.enable();
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Edition Chaine",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan_ft.setBorder(titl2);
				pan_but_poste.setVisible(true);
				description.enable();
				int rows = tab.table.getRowCount();
				for (int i = rows - 1; i >= 0; i--) {
					((DefaultTableModel) tab.table.getModel()).removeRow(i);

				}
				existe=use.select_zone_jtext(designation.getText());
				// System.out.println(use.exist_des);
				if (designation.getText().equals("") || existe == true) {
					if (valid_modif.isVisible() == false
							|| valid_ajou.isVisible() == false
							|| valid_supp.isVisible() == false) {
						but_sauv.setVisible(true);
						valid_ajou.setVisible(false);
						valid_supp.setVisible(false);
						valid_modif.setVisible(false);
						comb_intitule = true;

					}

					but_modif.setVisible(false);
					but_sup.setVisible(false);
					zone_comb.setSelectedIndex(0);

					code=use.afficher_codezone();
					zone_fi.setText(code);
				} else {
					if (zone_comb.getItemCount() >= 1) {
						zone_comb.enable();

						if (valid_modif.isVisible() == false
								|| valid_ajou.isVisible() == false
								|| valid_supp.isVisible() == false) {
							but_modif.setVisible(true);
							but_sup.setVisible(true);
							but_sauv.setVisible(false);
							retour.setVisible(false);
							valid_ajou.setVisible(false);
							valid_supp.setVisible(false);
							valid_modif.setVisible(false);

						} else {
							valid_ajou.setVisible(false);
							but_modif.setVisible(true);
							but_sup.setVisible(true);
							but_sauv.setVisible(false);
							retour.setVisible(false);
						}
						zone_fi.setText(use.code_z);
						// System.out.println(use.code_z+"  "+use.desig);
						zone_comb.setSelectedItem(use.code_z + " " + use.desig);
						description.setText(use.desc);
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
		Font police_fi = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13);

		comb_lab.setFont(police2);
		zone_lab.setFont(police2);
		description_lab.setFont(police2);
		designation_lab.setFont(police2);
		zone_fi.setFont(police_fi);
		description.setFont(police_fi);
		designation.setFont(police_fi);
		tab.table.setFont(police_fi);
		zone_comb.setFont(police_fi);
		zone_fi.setPreferredSize(new Dimension(230, 30));
		designation.setPreferredSize(new Dimension(230, 30));
		// description.setPreferredSize(new Dimension(210,100));
		zone_lab.setForeground(new Color(255, 255, 255));
		designation_lab.setForeground(new Color(255, 255, 255));
		description_lab.setForeground(new Color(255, 255, 255));
		comb_lab.setForeground(new Color(255, 255, 255));
		// ***************************************************************test**********************************************

		description.setFont(new Font("Serif", Font.ITALIC, 13));
		description.setLineWrap(true); // wrap line
		description.setWrapStyleWord(true); // wrap line at word boundary
		description.setBackground(new Color(204, 238, 241)); // light blue

		// setPreferredSize(new Dimension(230, 30));
		// Wrap the JTextArea inside a JScrollPane
		JScrollPane tAreaScrollPane = new JScrollPane(description);
		// tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,
		// 50));
		tAreaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tAreaScrollPane.setBounds(0, 10, 10, -100);
		tAreaScrollPane.setPreferredSize(new Dimension(230, 130));
		// *******************************************************************************************************************
		pan_form1.add(pan_comb);
		pan_comb.add(pan_comb_lab);
		pan_comb.add(pan_comb_jtext);
		pan_comb_lab.add(comb_lab);
		pan_comb_jtext.add(zone_comb);
		pan_form1.add(pan_feild);
		pan_form1.add(pan_desi);
		pan_form1.add(pan_desc);
		pan_ft1.add(pan_form1);
		pan_ft1.add(pan_form);

		panel.add(pan_ft);
		pan_ft.add(pan_ft1);

		pan_ft.add(pan_tab);
		pan_tab.add(p);
		pan_tab.add(pan_but_poste);
		panel.add(pan_button);

		pan_feild.add(pan_feild_lab);
		pan_feild.add(pan_feild_jtext);
		pan_feild_lab.add(zone_lab);
		pan_feild_jtext.add(zone_fi);
		pan_desi.add(pan_desi_lab);
		pan_desi.add(pan_desi_jtext);
		pan_desi_lab.add(designation_lab);
		pan_desi_jtext.add(designation);
		pan_desc.add(description_lab);
		// pan_desc.add(description);

		pan_but_poste.add(but_ajou);
		pan_but_poste.add(but_supprim);
		pan_button.add(retour);
		pan_button.add(valid_ajou);
		pan_button.add(valid_supp);
		pan_button.add(valid_modif);
		pan_button.add(but_sauv);
		pan_button.add(but_modif);
		pan_button.add(but_sup);
		pan_comb.setOpaque(false);
		pan_comb_lab.setOpaque(false);
		pan_comb_jtext.setOpaque(false);
		pan_desi_lab.setOpaque(false);
		pan_desi_jtext.setOpaque(false);
		pan_feild_lab.setOpaque(false);
		pan_feild_jtext.setOpaque(false);
		pan_ft.setOpaque(false);
		pan_form.setOpaque(false);
		pan_button.setOpaque(false);
		pan_feild.setOpaque(false);
		pan_desi.setOpaque(false);
		pan_desc.setOpaque(false);
		pan_ft1.setOpaque(false);
		pan_form1.setOpaque(false);

		// tab.setPreferredSize(new Dimension(700,700));
		p.setPreferredSize(new Dimension(700, 700));
		pan_ft1.setPreferredSize(new Dimension(300, 700));
		zone_comb.setPreferredSize(new Dimension(230, 30));

		pan_form.add(tAreaScrollPane, BorderLayout.CENTER);

		but_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_supprim.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_sauv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_sup.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_supp.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		but_ajou.setPreferredSize(new Dimension(150, 33));
		but_modif.setPreferredSize(new Dimension(120, 33));
		but_supprim.setPreferredSize(new Dimension(150, 33));
		but_sauv.setPreferredSize(new Dimension(120, 33));
		but_sup.setPreferredSize(new Dimension(120, 33));
		retour.setPreferredSize(new Dimension(120, 33));
		valid_supp.setPreferredSize(new Dimension(120, 33));
		valid_modif.setPreferredSize(new Dimension(120, 33));
		valid_ajou.setPreferredSize(new Dimension(120, 33));

		p.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		pan_button.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
		zone_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 30));
		comb_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 75));
		designation_lab
				.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		description_lab
				.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 22));
		pan_feild.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pan_desi.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pan_desc.setBorder(BorderFactory.createEmptyBorder(0, 0, -30, 0));
		pan_ft1.setBorder(BorderFactory.createEmptyBorder(0, -10, -30, 0));

		pan_desc.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_feild_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_feild_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_comb_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_comb_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		// Setup the content-pane of JFrame in BorderLayout

		// pan_desc.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_desi_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_desi_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

		pan_button.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_but_poste.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_form.setLayout(new FlowLayout(FlowLayout.RIGHT));

		pan_ft.setLayout(new BoxLayout(pan_ft, BoxLayout.X_AXIS));
		pan_form1.setLayout(new BoxLayout(pan_form1, BoxLayout.Y_AXIS));
		// pan_form.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 100));
		pan_tab.setLayout(new BoxLayout(pan_tab, BoxLayout.Y_AXIS));
		pan_comb.setLayout(new GridLayout(2, 1, 0, -25));
		pan_feild.setLayout(new GridLayout(2, 1, 0, -25));
		pan_desi.setLayout(new GridLayout(2, 1, 0, -25));

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
						|| om.equals("---S?lectionner un Poste-----"))

				{
					msg = "";
					return false;

				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					new zone("7");
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
