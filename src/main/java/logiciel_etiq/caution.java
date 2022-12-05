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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.JXDatePicker;

public class caution extends JFrame {

	/**
	 * 
	 */

	private JFrame frame = new JFrame();

	private static final long serialVersionUID = 1L;
//	private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private String msg;
	private String Chemin = "c:\\GCOBAR\\";
	private String bdd = Chemin + Utilitaire.InitBdd() + ".accdb";

	
	private JPanel pan_caution = new JPanel();
	private JPanel pan_caution_area = new JPanel();
	private JPanel pan_caution_lab = new JPanel();
	
	private JLabel caution_lab = new JLabel("Message Caution");
	private JTextArea caution_area=new JTextArea( "Do not accept if seal is broken.") ;
	
	
	private JPanel pan = new JPanel();
	private JPanel pan_general = new JPanel();
	private JPanel panel;

	private JPanel pan_button = new JPanel();
	private JButton imp_caution = new JButton("Imprimer");
	private JPanel pan_dimension = new JPanel();
	private JPanel pan_dimension_lab = new JPanel();
	private JPanel pan_dimension_comb= new JPanel();
	private JLabel dimension_lab = new JLabel("Dimension");
	private JComboBox<String> dimension_comb =new JComboBox<String>();

	private JPanel pan_gener = new JPanel();

	String report;
	ArrayList <String>list_dimension= new ArrayList<String>() ;
	private static gestion_imp imp=new gestion_imp();

	Map<String, Object> parameters = new HashMap<String, Object>();
	caution(final String log) {
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


		dimension_comb.addItem("--Sélectionner la dimension de l'etiquette--");
		list_dimension=imp.select_dimension_etq("etq_caution");

		for(int i=0;i<list_dimension.size();i++)
		{
			//Pour affecter une valeur de base de donn?es ? un Combobox
			dimension_comb.addItem(list_dimension.get(i));
		}


		dimension_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(dimension_comb.getSelectedIndex()!=0)
					report=imp.select_report(dimension_comb.getSelectedItem().toString(),"etq_caution");

			}});


		final Calendar cal = Calendar.getInstance(); // date du jour
		if (!selectioncomb.prv.contains("caution"))
			selectioncomb.prv.add("caution");

		panel = new JPanel() {
			/***  */
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
				img.paintIcon(this, g, 0, 0);
			}
		};
		

		final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,15);

	
		LineBorder border = new LineBorder(Color.white, 1, true);
		TitledBorder titl2 = new TitledBorder(border, "Etiquette composants",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		pan.setBorder(titl2);
		
		
	
			


			
			
			
			
			
			

			

		
		imp_caution.addActionListener(
                new ActionListener() { 
                public void actionPerformed(ActionEvent e) {


					if (dimension_comb.getSelectedIndex() == 0) {
						JOptionPane
								.showMessageDialog(
										null,
										" Vous dever choisir la dimension de l'étiquette ",
										"", JOptionPane.INFORMATION_MESSAGE);
					} else {
						BufferedReader bfr = null;

						String parcour = null;
						String model;
						parcour = "C:\\GCOBAR\\pdf\\etq_portable\\caution" + dimension_comb.getSelectedItem().toString().split(" ")[1] + ".pdf";

						model = "C:\\GCOBAR\\CODE\\" + report;
						//imp.update_imp_s("1");
						File fichier = new File(parcour);
						fichier.delete();
						UIManager.put("nimbusOrange", (new Color(70, 130, 180)));

						try {

							//
							bfr = new BufferedReader(new FileReader(parcour));
							bfr.close();
							System.out.println("buffer");
							try {
								Desktop.getDesktop().open(new File(parcour));
								System.out.println("desk");

							} catch (IOException p) {
								// TODO Auto-generated catch block
								p.printStackTrace();
							}
						} catch (FileNotFoundException fnfe) {
							parameters.put("caution", caution_area.getText());

							System.out.println(parameters.get(0) + " " + caution_area.getText());
							selectioncomb.imprimer(bdd, parcour, model, parameters);


						} catch (IOException e1) {
							e1.printStackTrace();
						}


					}

				}});






		Font police_fi = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13);


		generale.styles("Nimbus");
		SwingUtilities.updateComponentTreeUI(this);

		caution_lab.setForeground(Color.white);
		dimension_comb.setFont(police_fi);
		dimension_lab.setFont(police2);



		caution_lab.setFont(police2);


		dimension_comb.setPreferredSize(new Dimension(230, 30));


		/*************************code_com pour le code commercial************/
		pan_dimension.add(pan_dimension_lab);
		pan_dimension.add(pan_dimension_comb);
		pan_dimension_lab.add(dimension_lab);
		pan_dimension_comb.add(dimension_comb);

		pan_caution.add(pan_caution_lab);
		pan_caution_lab.add(caution_lab);
		pan_caution.add(pan_caution_area);
		pan_caution_area.add(caution_area);

		pan_caution.setOpaque(false);
		pan_caution_lab.setOpaque(false);
		pan_caution_area.setOpaque(false);
		pan_dimension.setOpaque(false);
		pan_dimension_lab.setOpaque(false);
		pan_dimension_comb.setOpaque(false);
	
		pan_button.add(imp_caution);
		

		pan_button.setOpaque(false);
		pan_gener.setOpaque(false);

		pan_general.add(pan_gener);
		pan_gener.add(pan_dimension);

		pan_gener.add(pan_caution);
		pan_general.add(pan_button);




		pan.add(pan_general);
		panel.add(pan);
		
		pan_general.setOpaque(false);
		pan.setOpaque(false);
		
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		pan_general.setLayout(new BoxLayout(pan_general, BoxLayout.Y_AXIS));

		pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.Y_AXIS));

		imp_caution.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

		dimension_lab.setForeground(new Color(255, 255, 255));


		
	    caution_area.setPreferredSize(new Dimension(310, 200));
	

		

		pan_caution.setLayout(new BoxLayout(pan_caution, BoxLayout.Y_AXIS));
		pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.Y_AXIS));



		pan_caution_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_caution_area.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_caution_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//pan_caution_area.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));





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

					new caution("7");
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
