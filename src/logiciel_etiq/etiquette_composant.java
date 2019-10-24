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

public class etiquette_composant extends JFrame {

	/**
	 * 
	 */
	  ArrayList<String> list_imp = new ArrayList<String>();

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
	
	private JPanel pan_nbrbobine = new JPanel();
	private JPanel pan_nbrbobine_jtext = new JPanel();
	private JPanel pan_nbrbobine_lab = new JPanel();
	private JLabel nbrbobine_lab = new JLabel("Nombre de bobines");
	private JTextField nbrbobine_jtext;
	
	
	
	private JPanel pan_seriebobine = new JPanel();
	private JPanel pan_seriebobine_jtext = new JPanel();
	private JPanel pan_seriebobine_lab = new JPanel();
	private JLabel seriebobine_lab = new JLabel("Qte/total bobine");
	private JTextField totalbobine_jtext=new JTextField();
	
	
	private JPanel pan_recherche = new JPanel();
	private JPanel pan_recherche_jtext = new JPanel();
	private JPanel pan_recherche_lab = new JPanel();
	private JLabel recherche_lab = new JLabel("Recherche Carton");
	private JTextField recherche_jtext=new JTextField();
	
	
	
	private JPanel pan_qtebobine = new JPanel();
	private JPanel pan_qtebobine_jtext = new JPanel();
	private JPanel pan_qtebobine_lab = new JPanel();
	private JLabel qtebobine_lab = new JLabel("Quantit� par Bobine");
	private JTextField qtebobine_jtext=new JTextField();
	
	private JPanel pan_carton = new JPanel();
	private JPanel pan_carton_jtext = new JPanel();
	private JPanel pan_carton_lab = new JPanel();
	private JLabel carton_lab = new JLabel("Code Carton");
	private JTextField carton_jtext=new JTextField();
	
    
    
	private JPanel pan_qteglobale = new JPanel();
	private JPanel pan_qteglobale_jtext = new JPanel();
	private JPanel pan_qteglobale_lab = new JPanel();
	private JLabel qteglobale_lab = new JLabel("Quantit� Globale");
	private JTextField qteglobale_jtext=new JTextField();
	

	
	private JPanel pan_nbrcarton = new JPanel();
	private JPanel pan_nbrcarton_jtext = new JPanel();
	private JPanel pan_nbrcarton_lab = new JPanel();
	private JLabel nbrcarton_lab = new JLabel("Quantit� de carton");
	private JTextField nbrcarton_jtext=new JTextField();


	private JPanel pan_id = new JPanel();
	private JPanel pan_id_jtext = new JPanel();
	private JPanel pan_id_lab = new JPanel();
	private JLabel id_lab = new JLabel("Produit Fournisseur");
	private JTextField id_jtext=new JTextField();


	private JPanel pan_dimension = new JPanel();
	private JPanel pan_dimension_lab = new JPanel();
	private JPanel pan_dimension_comb = new JPanel();
	private JLabel dimension_lab = new JLabel("Etiquette dimension");
	private JComboBox dimension_comb= new JComboBox();
	
	private JPanel pan = new JPanel();
	private JPanel pan_general = new JPanel();
	private JPanel pan_form = new JPanel();
	private JPanel panel;

	private JPanel pan_button = new JPanel();
	private JButton but_sauv = new JButton("Ajouter");
	private JButton but_modif = new JButton("Modifier");
	private JButton valid_ajou = new JButton("Valider");
	private JButton valid_modif = new JButton("Valider");
	private JButton imp_etq = new JButton("Imprimer L'�tiquette");
	private JButton retour = new JButton("Retour");
	
	ArrayList <String>list_dimension= new ArrayList<String>() ;
    String report;
	private JPanel pan_gener = new JPanel();
	final Calendar cal = Calendar.getInstance();
    Thread att; 
    NumberFormat format = new DecimalFormat("000");
	NumberFormat formatter = new DecimalFormat("00");
	NumberFormat form = new DecimalFormat("0");
	
	  NumberFormat formserie = new DecimalFormat("0000");
      NumberFormat formseriej = new DecimalFormat("00");
      
	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	Map<String, Object> parameters = new HashMap<String, Object>();
	etiquette_composant(final String log) {
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

		  MaskFormatter num = null;
          try {
               num = new MaskFormatter("##");
              } 
          catch (ParseException e) {
                                    e.printStackTrace();
                                   }
          nbrbobine_jtext = new  JFormattedTextField(num);
		
		
//		imp.conteur(date_picker.getDate());
		
		list_ar = new ArrayList<String>(
		Arrays.asList(new String[] { "---Selectionner un article-----" }));
		article_comb = new jcombo(list_ar.toArray());
		selectioncomb.select_composant(article_comb, this, logi_prio);
		
		
		dimension_comb.addItem("--- S�lectionner la dimension de l'etiquette ----");

		list_dimension=imp.select_dimension_etq("etq_composant");
	 	  
		   for(int i=0;i<list_dimension.size();i++)
		   {
		          //Pour affecter une valeur de base de donn�es � un Combobox 
			   dimension_comb.addItem(list_dimension.get(i));
			   
		   }
	
		
		
		
		final Calendar cal = Calendar.getInstance(); // date du jour
		if (!selectioncomb.prv.contains("etiquette_composant"))
			selectioncomb.prv.add("etiquette_composant");

		panel = new JPanel() {
			/***  */
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
				img.paintIcon(this, g, 0, 0);
			}
		};

		final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,15);

		carton_jtext.disable();
		totalbobine_jtext.disable();

		article_comb.enable();
		nbrbobine_jtext.enable();
		qtebobine_jtext.enable();
		qteglobale_jtext.disable();
		if (article_comb.getItemCount() > 2)

			article_comb.setSelectedIndex(1);
		  String[] art = article_comb.getSelectedItem().toString().split(" ");
		  String arti = art[0]; // 004
		//  System.out.println(imp.counteur_composant_bobine(arti));
		// int code1=Integer.parseInt(imp.counteur_composant_bobine(arti));
		 //String serie="S"+formserie.format(code1+1)+"/01";
		
		// totalbobine_jtext.setText(""+code1);
		action_carton();

		LineBorder border = new LineBorder(Color.white, 1, true);
		TitledBorder titl2 = new TitledBorder(border, "Etiquette composants",
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
				nbrbobine_jtext.enable();
				qtebobine_jtext.enable();
				carton_jtext.disable();

				but_sauv.setVisible(false);
				but_modif.setVisible(false);
				retour.setVisible(true);
				valid_ajou.setVisible(true);
				valid_modif.setVisible(false);
				imp_etq.setVisible(false);
			
			}
		});

		
		but_modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineBorder border = new LineBorder(Color.white, 1, true);
				TitledBorder titl2 = new TitledBorder(border, "Modifier",
						TitledBorder.DEFAULT_POSITION,
						TitledBorder.DEFAULT_POSITION, police2, Color.white);
				pan.setBorder(titl2);
				article_comb.enable();
				nbrbobine_jtext.enable();
				qtebobine_jtext.enable();
				carton_jtext.disable();
			 //   nbrcarton_jtext.setVisible(false);

				but_sauv.setVisible(false);
				but_modif.setVisible(false);
				retour.setVisible(true);
				valid_ajou.setVisible(false);
				valid_modif.setVisible(true);
				imp_etq.setVisible(false);
				
			

			}
		});
		
		
		nbrbobine_jtext.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				  action_carton();
				if(!nbrbobine_jtext.getText().equals("")){
					
		if(!qtebobine_jtext.getText().equals("")&&isValid(qtebobine_jtext.getText())&&isValid(nbrbobine_jtext.getText())){
		    int qte_global=Integer.parseInt(nbrbobine_jtext.getText() )*Integer.parseInt(qtebobine_jtext.getText());
		    qteglobale_jtext.setText(""+qte_global);
    	 
		    String[] art = article_comb.getSelectedItem().toString().split(" ");
			  String arti = art[0]; // 004
			  
		    int code1=Integer.parseInt(imp.counteur_composant_bobine(arti));
			 String serie="S"+formserie.format(1)+"/01";
			
				}}
				else{
					
					 qteglobale_jtext.setText("");
					
				}
			}

				
			
				
		});
			

			
			
			qtebobine_jtext.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					if(!nbrbobine_jtext.getText().equals("")&&!qtebobine_jtext.getText().equals("")&&isValid(qtebobine_jtext.getText())&&
							isValid(nbrbobine_jtext.getText())){
					    int qte_global=Integer.parseInt(nbrbobine_jtext.getText() )*Integer.parseInt(qtebobine_jtext.getText() );
					  qteglobale_jtext.setText(""+qte_global);
					  
							}
							
							else{
								
								 qteglobale_jtext.setText("");
								
							}
				}
		});
			
			
			recherche_jtext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			
						if(!recherche_jtext.getText().equals("")){
							 if(imp.exist_composant(recherche_jtext.getText())==true){
							//	 System.out.println("ffgfhgf");
							imp.selection_composant_champ(recherche_jtext.getText());
							article_comb.setSelectedItem(imp.code_article_composant+" "+imp.designation_composant);
							qtebobine_jtext.setText(imp.qte_bobine);
							nbrbobine_jtext.setText(imp.nbr_bobine);
						    id_jtext.setText(imp.reference);
						    //seriebobine_jtext.setText(imp.serie);

						    carton_jtext.setText(recherche_jtext.getText());
						    imp_etq.setVisible(false);
						   // nbrcarton_jtext.setVisible(false);
						    nbrcarton_lab.setVisible(false);

							but_modif.setVisible(true);
							retour.setVisible(false);
							but_sauv.setVisible(false);
							valid_ajou.setVisible(false);
							valid_modif.setVisible(false);
							  int qte_global=Integer.parseInt(nbrbobine_jtext.getText() )*Integer.parseInt(qtebobine_jtext.getText() );
							  qteglobale_jtext.setText(""+qte_global);
							 }
							 else{
								
								 imp_etq.setVisible(false);
									but_modif.setVisible(false);
									
								//	but_sup.setVisible(false);
									retour.setVisible(false);
									but_sauv.setVisible(true);
									valid_ajou.setVisible(false);
								//	valid_supp.setVisible(false);
									valid_modif.setVisible(false);
									article_comb.setSelectedIndex(0);
								    carton_jtext.setText("");
									id_jtext.setText("");
									qtebobine_jtext.setText("");
									nbrbobine_jtext.setText("");
									qteglobale_jtext.setText("");
								    totalbobine_jtext.setText("");

								    
									//contrat_jtext.setText("");
									
								//	parcel_jtext.setText("");
								
					            //    outil.setText("");
									
								   
								     JOptionPane
										.showMessageDialog(
												null,
												"Ce carton n'existe pas",
												"", JOptionPane.INFORMATION_MESSAGE);
								}
							 
						}else{

						
							imp_etq.setVisible(false);
							but_modif.setVisible(false);
							
							 imp_etq.setVisible(false);
								but_modif.setVisible(false);
								
							//	but_sup.setVisible(false);
								retour.setVisible(false);
								but_sauv.setVisible(true);
								valid_ajou.setVisible(false);
							//	valid_supp.setVisible(false);
								valid_modif.setVisible(false);
								article_comb.setSelectedIndex(0);
							    carton_jtext.setText("");
								id_jtext.setText("");
								qtebobine_jtext.setText("");
								nbrbobine_jtext.setText("");
								qteglobale_jtext.setText("");
							    totalbobine_jtext.setText("");

						    
						}
						
					}

				
			});

			valid_modif.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				//	use.select_zone_jtext(designation.getText());
	                    msg = "";
					
					if (article_comb.getSelectedIndex() == 0) {
						msg += "Vous Devez  S�lectionner un Article\n";
					} 
					
					
					else if (carton_jtext.getText().equals("")) {
						msg += "Vous Devez verifier si le code carton est remplie \n";
					} 
					
					else if (nbrbobine_jtext.getText().equals("")) {
						msg += "Vous Devez remplire le nombre de bobines \n";
					} 
					
					else if (qtebobine_jtext.getText().equals("")) {
						msg += "Vous Devez remplire la quantit� de bobines \n";
					} 
					else if (isValid(qtebobine_jtext.getText()) == false) {
						msg += "La quantit� doit etre un nombre \n";
					}
					else if (isValid(nbrbobine_jtext.getText()) == false) {
						msg += "La nombre de bobine doit etre un nombre \n";
					}
					else if (dimension_comb.getSelectedIndex() == 0) {
						msg += "Vous Devez  S�lectionner une dimension\n";
					} 
					
					
					
					
						if (!msg.equals("")) {
							JOptionPane.showMessageDialog(null, msg);
						}
						else{
			  String[] art = article_comb.getSelectedItem().toString().split(" ");
			  String arti = art[0]; // 004
		
				imp.update_composant(carton_jtext.getText(),arti,qtebobine_jtext.getText(),nbrbobine_jtext.getText()
				,id_jtext.getText(),recherche_jtext.getText());
		
				imp.delete_composant_bobine(carton_jtext.getText());
				
				for(int j=1;j<=Integer.parseInt(nbrbobine_jtext.getText());j++){
   					
     				  int code1=Integer.parseInt(imp.counteur_composant_bobine(arti))+1;
                
                    
     					String serie="S"+formserie.format(code1)+"/"+formseriej.format(j);

     					totalbobine_jtext.setText(""+code1);

     					
     					imp.insert_composant_bobine(carton_jtext.getText(),serie);

     				}
				

			
							JOptionPane.showMessageDialog(null,"L'etiquette a �t� bien modifi�e");
							
							imp_etq.setVisible(true);
							valid_modif.setVisible(false);
							but_modif.setVisible(false);
							retour.setVisible(true);

						}
					
				}
			});
			
			
		valid_ajou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att= new Thread(){
			    public void run() {
				msg = "";
              	list_imp.clear(); 

				String[] art = article_comb.getSelectedItem().toString().split(" ");
				String arti = art[0]; // 004
				String des = article_comb.getSelectedItem().toString().replace(arti + " ", ""); // 004
			
				if (article_comb.getSelectedIndex() == 0) {
					msg += "Veuillez selectionner un article \n";
				}
				else if (nbrbobine_jtext.equals("")) {
					msg += "Veuillez saisir le nombre de Bobine par carton \n";
				} else if (qtebobine_jtext.getText().equals("")) {
					msg += "Veuillez remplir la quantit�  \n";
				} else if (isValid(qtebobine_jtext.getText()) == false) {
					msg += "La quantit� doit etre un nombre \n";
				}
				else if (isValid(nbrbobine_jtext.getText()) == false) {
					msg += "La nombre de bobine doit etre un nombre \n";
				}
				
				
				if (msg.equals("")) {
					String etq=null;String serie="";
					action_carton();
					 retour.setEnabled(false);
					 valid_ajou.setEnabled(false);
		
                    JLabel titr=new JLabel("la cr�ation de "+nbrbobine_jtext.getText()+" etiquette");
                    titr.setFont( titr.getFont().deriveFont(Font.BOLD|Font.ITALIC) );
                  
                    UIManager.put("nimbusOrange", (new Color(70,130,180)));
                   // int size=Integer.parseInt(nbrcarton_jtext.getText());
                    String code=carton_jtext.getText();
                    String fix=code.substring(5, 16);
                    String change=code.substring(1,5);
                    
                    
					  NumberFormat formater = new DecimalFormat("0000");
					//  int code1=Integer.parseInt(imp.counteur_composant_bobine(arti));
				//  for(int i=0;i<size;i++){
					int indice=Integer.parseInt(change);
					
					System.out.print(indice);
					  String code_cart ="L"+formater.format(indice)+fix;
					  
					  
					  list_imp.add(code_cart);
					  
                      imp.ajouter_composant(code_cart,arti,qtebobine_jtext.getText(), nbrbobine_jtext.getText()
                		   ,id_jtext.getText());
                 
                 

       				for(int j=1;j<=Integer.parseInt(nbrbobine_jtext.getText());j++){
       					
       				//   code1=code1+1;
                  
                      
       					 serie="S"+formserie.format(j)+"/"+nbrbobine_jtext.getText();

       					

       					
       					imp.insert_composant_bobine(code_cart,serie);

       				}
                   
				//  }
				  
			//	  totalbobine_jtext.setText(""+code1);
               	   action_carton();
					
				
					   

			
						
                          valid_ajou.setVisible(false);
                          but_sauv.setVisible(true);
                          retour.setVisible(false);
					      JOptionPane.showMessageDialog(null, "l'�tiquette a " + des	+ "  a �t� bien ajout�");
					 retour.setEnabled(true);
					 valid_ajou.setEnabled(true);
					imp_etq.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, msg);
				}	}}; 
				att.start();
			}
		});
		
		dimension_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(dimension_comb.getSelectedIndex()!=0)
				report=imp.select_report(dimension_comb.getSelectedItem().toString(),"etq_composant");
				
				
			}});

		
		imp_etq.addActionListener(
                new ActionListener() { 
                public void actionPerformed(ActionEvent e) {

                	BufferedReader bfr = null ;	
                
              	  if(list_imp.size()!=0){
              		  String parcour= "C:\\GCOBAR\\pdf\\etq_composant\\"+dimension_comb.getSelectedItem()+list_imp.get(0)+"_"+list_imp.size()+"etq_composant.pdf";
							String model ="C:\\GCOBAR\\CODE\\"+report;
							
							 File fichier = new File(parcour);
							    fichier.delete(); 
              		  try{
              			bfr=	new BufferedReader(new FileReader(
    							"C:\\GCOBAR\\pdf\\etq_composant\\"+dimension_comb.getSelectedItem()+list_imp.get(0)+"_"+list_imp.size()+"etq_composant.pdf"));
    					
							bfr.close();
					
							try {
								Desktop.getDesktop().open(new File(
										"C:\\GCOBAR\\pdf\\etq_composant\\"+dimension_comb.getSelectedItem()+list_imp.get(0)+"_"+list_imp.size()+"etq_composant.pdf"));
							//	System.out.println("desk");
							
							} catch (IOException p) {
								// TODO Auto-generated catch block
								p.printStackTrace();
							}}
						
              			 catch (IOException fnfe) {
              
              	  selectioncomb.imprimer("code_carton_parm",list_imp,bdd,parcour,model);
                  System.out.println("ezzerzrzerzerzer"+list_imp);

              	  
                } }            
                }});
		
		
		
		but_sauv.setVisible(true);
		but_modif.setVisible(false);
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
				id_jtext.setText("");
				qtebobine_jtext.setText("");
				carton_jtext.setText("");
				nbrbobine_jtext.setText("");
				qteglobale_jtext.setText("");
				but_sauv.setVisible(true);
				but_modif.setVisible(false);
				imp_etq.setVisible(false);
				retour.setVisible(false);
				valid_ajou.setVisible(false);
				valid_modif.setVisible(false);	
				action_carton();

			
			}
		});
		
		article_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (article_comb.getSelectedIndex() == 0) {
					article_comb.setSelectedIndex(0);
					qtebobine_jtext.setText("");
					carton_jtext.setText("");
					nbrbobine_jtext.setText("");
					qteglobale_jtext.setText("");
					
					but_sauv.setVisible(true);
					but_modif.setVisible(false);
					imp_etq.setVisible(false);
					retour.setVisible(false);
					valid_ajou.setVisible(false);
					valid_modif.setVisible(false);	
				
				} else {
					
					
							action_carton();


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
		carton_lab.setForeground(Color.white);
		nbrbobine_lab.setForeground(Color.white);
		qtebobine_lab.setForeground(Color.white);
		qteglobale_lab.setForeground(Color.white);
		id_lab.setForeground(Color.white);
		recherche_lab.setForeground(Color.white);
		nbrcarton_lab.setForeground(Color.white);
		dimension_lab.setForeground(Color.white);
		seriebobine_lab.setForeground(Color.white);

		
		article_lab.setFont(police2);	
		carton_lab.setFont(police2);
		nbrbobine_lab.setFont(police2);
		qtebobine_lab.setFont(police2);
		qteglobale_lab.setFont(police2);
		id_lab.setFont(police2);
		recherche_lab.setFont(police2);
		nbrcarton_lab.setFont(police2);
		seriebobine_lab.setFont(police2);
		dimension_lab.setFont(police2);

		
	
		/*************************code_com pour le code commercial************/
		pan_recherche.add(pan_recherche_lab);
		pan_recherche_lab.add(recherche_lab);
		pan_recherche.add(pan_recherche_jtext);
		pan_recherche_jtext.add(recherche_jtext);

		pan_recherche.setOpaque(false);
		pan_recherche_lab.setOpaque(false);
		pan_recherche_jtext.setOpaque(false);
		
		
		pan_seriebobine.add(pan_seriebobine_lab);
		pan_seriebobine_lab.add(seriebobine_lab);
		pan_seriebobine.add(pan_seriebobine_jtext);
		pan_seriebobine_jtext.add(totalbobine_jtext);

		pan_seriebobine.setOpaque(false);
		pan_seriebobine_lab.setOpaque(false);
		pan_seriebobine_jtext.setOpaque(false);
		
		pan_article.add(pan_article_lab);
		pan_article_lab.add(article_lab);
		pan_article.add(pan_article_comb);
		pan_article_comb.add(article_comb);

		pan_article.setOpaque(false);
		pan_article_lab.setOpaque(false);
		pan_article_comb.setOpaque(false);
		
		pan_qtebobine.setOpaque(false);
		pan_qtebobine_lab.setOpaque(false);
		pan_qtebobine_jtext.setOpaque(false);
		
		pan_qtebobine.add(pan_qtebobine_lab);
		pan_qtebobine_lab.add(qtebobine_lab);
		pan_qtebobine.add(pan_qtebobine_jtext);
		pan_qtebobine_jtext.add(qtebobine_jtext);
		
		
		
		pan_id.setOpaque(false);
		pan_id_lab.setOpaque(false);
		pan_id_jtext.setOpaque(false);
		
		pan_id.add(pan_id_lab);
		pan_id_lab.add(id_lab);
		pan_id.add(pan_id_jtext);
		pan_id_jtext.add(id_jtext);
		
		
		
		pan_nbrbobine.add(pan_nbrbobine_lab);
		pan_nbrbobine_lab.add(nbrbobine_lab);
		pan_nbrbobine.add(pan_nbrbobine_jtext);
		pan_nbrbobine_jtext.add(nbrbobine_jtext);
		
		
		pan_nbrbobine.setOpaque(false);
		pan_nbrbobine_lab.setOpaque(false);
		pan_nbrbobine_jtext.setOpaque(false);
		
		
		pan_qtebobine.add(pan_qtebobine_lab);
		pan_qtebobine_lab.add(qtebobine_lab);
		pan_qtebobine.add(pan_qtebobine_jtext);
		pan_qtebobine_jtext.add(qtebobine_jtext);

		
		
		pan_qtebobine.setOpaque(false);
		pan_qtebobine_lab.setOpaque(false);
		pan_qtebobine_jtext.setOpaque(false);

		
		
		pan_qteglobale.add(pan_qteglobale_lab);
		pan_qteglobale_lab.add(qteglobale_lab);
		pan_qteglobale.add(pan_qteglobale_jtext);
		pan_qteglobale_jtext.add(qteglobale_jtext);
		
		
		pan_qteglobale.setOpaque(false);
		pan_qteglobale_lab.setOpaque(false);
		pan_qteglobale_jtext.setOpaque(false);
		
		
		
		
		pan_carton.add(pan_carton_lab);
		pan_carton_lab.add(carton_lab);
		pan_carton.add(pan_carton_jtext);
		pan_carton_jtext.add(carton_jtext);
		
		
		pan_carton.setOpaque(false);
		pan_carton_lab.setOpaque(false);
		pan_carton_jtext.setOpaque(false);
		

		pan_nbrcarton.add(pan_nbrcarton_lab);
		pan_nbrcarton_lab.add(nbrcarton_lab);
		pan_nbrcarton.add(pan_nbrcarton_jtext);
		pan_nbrcarton_jtext.add(nbrcarton_jtext);
		
		pan_dimension.add(pan_dimension_lab);
		
        pan_dimension.add(pan_dimension_comb);
        pan_dimension_lab.add(dimension_lab);
        pan_dimension_comb.add(dimension_comb);

		pan_nbrcarton.setOpaque(false);
		pan_nbrcarton_lab.setOpaque(false);
		pan_nbrcarton_jtext.setOpaque(false);
		pan_dimension.setOpaque(false);
		pan_dimension_lab.setOpaque(false);
		pan_dimension_comb.setOpaque(false);
		
	
		pan_button.add(retour);
		pan_button.add(valid_ajou);
		pan_button.add(valid_modif);
		pan_button.add(but_sauv);
		 pan_button.add(but_modif);
		pan_button.add(imp_etq);

		pan_button.setOpaque(false);
		
		pan_form.add(pan_recherche);
		pan_form.add(pan_carton);
		pan_form.add(pan_article);
		pan_form.add(pan_id);
		pan_form.add(pan_nbrbobine);
		pan_form.add(pan_qtebobine);
		pan_form.add(pan_qteglobale);
		pan_form.add(pan_seriebobine);
		//pan_form.add(pan_nbrcarton);
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
		
		pan_dimension.setOpaque(false);
		
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));
		pan_seriebobine.setLayout(new BoxLayout(pan_seriebobine, BoxLayout.X_AXIS));

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
		dimension_comb.setPreferredSize(new Dimension(210, 30));
		qtebobine_jtext.setPreferredSize(new Dimension(210, 30));
		nbrbobine_jtext.setPreferredSize(new Dimension(210, 30));
		qteglobale_jtext.setPreferredSize(new Dimension(210, 30));
		carton_jtext.setPreferredSize(new Dimension(210, 30));
		id_jtext.setPreferredSize(new Dimension(210, 30));
		recherche_jtext.setPreferredSize(new Dimension(210, 30));
		nbrcarton_jtext.setPreferredSize(new Dimension(210, 30));
		totalbobine_jtext.setPreferredSize(new Dimension(210, 30));


		
		pan_seriebobine.setLayout(new BoxLayout(pan_seriebobine, BoxLayout.X_AXIS));

		pan_article.setLayout(new BoxLayout(pan_article, BoxLayout.X_AXIS));
		pan_qtebobine.setLayout(new BoxLayout(pan_qtebobine, BoxLayout.X_AXIS));
		pan_nbrbobine.setLayout(new BoxLayout(pan_nbrbobine, BoxLayout.X_AXIS));
		pan_carton.setLayout(new BoxLayout(pan_carton, BoxLayout.X_AXIS));
		pan_qteglobale.setLayout(new BoxLayout(pan_qteglobale, BoxLayout.X_AXIS));
		pan_id.setLayout(new BoxLayout(pan_id, BoxLayout.X_AXIS));
		pan_recherche.setLayout(new BoxLayout(pan_recherche, BoxLayout.X_AXIS));
		pan_nbrcarton.setLayout(new BoxLayout(pan_nbrcarton, BoxLayout.X_AXIS));
		pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));

		
		pan_nbrcarton_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_nbrcarton_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_nbrcarton_lab.setBorder(BorderFactory.createEmptyBorder(0, 130, 0, 0));
		pan_nbrcarton_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
		pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		pan_seriebobine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_seriebobine_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_seriebobine_lab.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));
		pan_seriebobine_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		pan_recherche_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_recherche_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_recherche_lab.setBorder(BorderFactory.createEmptyBorder(0, 140, 0, 0));
		pan_recherche_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		
		pan_id_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_id_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_id_lab.setBorder(BorderFactory.createEmptyBorder(0, 140, 0, 0));
		pan_id_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		
		
		pan_article_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_article_lab.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));
		pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		
		pan_nbrbobine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_nbrbobine_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_nbrbobine_lab.setBorder(BorderFactory.createEmptyBorder(0, 130, 0, 0));
		pan_nbrbobine_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_qtebobine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_qtebobine_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_qtebobine_lab.setBorder(BorderFactory.createEmptyBorder(0, 130, 0, 0));
		pan_qtebobine_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		pan_qteglobale_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_qteglobale_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_qteglobale_lab.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));
		pan_qteglobale_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pan_carton_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_carton_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_carton_lab.setBorder(BorderFactory.createEmptyBorder(0, 180, 0, 0));
		pan_carton_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


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

					new etiquette_composant("7");
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

	public String action_carton() {
      if(imp.exist_composant(recherche_jtext.getText())==false){
			if (article_comb.getSelectedIndex() != 0	&& article_comb.getItemCount() >= 1&&
					article_comb.getSelectedIndex() != article_comb.getItemCount() - 1) {
                
               
				String arti = null;
				String arti_sansslash = null;

				if(article_comb.getSelectedIndex()!=0){
					String[] art = article_comb.getSelectedItem().toString().split(" ");
					 arti = art[0]; // 004
					 
					 

					 if(arti.contains("/")) {
						 arti_sansslash = arti.substring(2); // 004
					 }
					 else{
						 arti_sansslash = arti.replace("-", "");
						 }
					 }

			  
				  
			    int code1=Integer.parseInt(imp.counteur_composant_bobine(arti_sansslash));
			//	 String serie="S"+formserie.format(1)+"/01";
				 totalbobine_jtext.setText(""+code1);

 				//	System.out.println("serie"+serie);
   				//	seriebobine_jtext.setText(serie);

				String indice=imp.afficher_conteur_composant(arti_sansslash);
                 System.out.println(indice);
				//NumberFormat formater = new DecimalFormat("00");
				String nbr_bobine="00";
				
				String code;
				String year =String.valueOf(cal.get(Calendar.YEAR)).substring(2,4) ;
				if(!nbrbobine_jtext.getText().trim().equals("")){
				nbr_bobine=nbrbobine_jtext.getText();
				}
			
					code = "L" + indice + arti_sansslash + nbr_bobine +year ;
				
				carton_jtext.setText(code);
				return code;
			} else {

				carton_jtext.setText("");
				return "";			}
}
      else return recherche_jtext.getText();
		
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
