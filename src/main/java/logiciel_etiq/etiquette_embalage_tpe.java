package logiciel_etiq;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;






/********************************� LA PALETTE TP0000000 EST AJOUTER A LA BDDD POR EVITER LES CAS DE L4AVANT PALETTE**********/


public class etiquette_embalage_tpe extends JFrame implements ActionListener {
    private static final String CTRL_J = "CTRL+J";
    final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18);
    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent e) {
        // gestion de l'action
        if (e.getActionCommand().equals(CTRL_J)) {
            
            LineBorder border = new LineBorder(Color.white, 1, true);
            TitledBorder titl2 = new TitledBorder(border, "Edition etiquette emballage tpe",
                    TitledBorder.DEFAULT_POSITION,
                    TitledBorder.DEFAULT_POSITION, police2, Color.white);
            pan_ft.setBorder(titl2);
    
            pan_but_ligne.setVisible(true);
            retour.setVisible(true);
            but_modif.setVisible(true);
            but_ajouter.setVisible(true);
            valid_ajou.setVisible(false);
            valid_modif.setVisible(false);
            imp_embalage.setVisible(false);
            article_comb.setSelectedIndex(0);
            modele_jtext.setText("");
            qte_jtext.setText("");
             gw_comb.setSelectedItem("");
            parcel_jtext.disable();
            sizecart_comb.setSelectedItem("");
            parcel_jtext.setText("");
            int rows = tab.table.getRowCount();
            
            for (int i = rows - 1; i >= 0; i--) {
                ((DefaultTableModel) tab.table.getModel()).removeRow(i);
            }
            
        }
    }

    /**
     * 
     */
     Thread att; 
    ArrayList <String>list_size= new ArrayList<String>() ;
    ArrayList <String>list_gw= new ArrayList<String>() ;
    ArrayList <String>list_emei= new ArrayList<String>() ;
    Map<String, Object> parameters = new HashMap<String, Object>();
    
    private String Chemin = "c:\\GCOBAR\\";
    private String bdd = Chemin + Utilitaire.InitBdd() + ".accdb";
    private static final long serialVersionUID = 1L;
    private String msg = "";
    private JTabbedPane jTabbedPane = new JTabbedPane();
    String report;


    String chaine="";
    private boolean valid_j,valid_a,valid_o;
   // private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

    private JPanel panel=new JPanel();
    
    private JPanel pan_form = new JPanel();
    private JPanel pan_form1 = new JPanel();
    private JPanel pan_ft1 = new JPanel();

    private JPanel pan_ft = new JPanel();
    private JPanel pan_article = new JPanel();
    private JPanel pan_article_lab = new JPanel();
    private JPanel pan_article_comb = new JPanel();
    
    private JPanel pan_model = new JPanel();
    private JPanel pan_model_lab = new JPanel();
    private JPanel pan_model_jtext= new JPanel();
    
    
    
    private JPanel pan_qte = new JPanel();
    private JPanel pan_qte_lab = new JPanel();
    private JPanel pan_qte_comb= new JPanel();
    
    private JPanel pan_palette = new JPanel();
	private JPanel pan_palette_lab = new JPanel();
	private JPanel pan_palette_combo= new JPanel();
	private JLabel palette_lab=new JLabel("Palette");
	private JComboBox palette_comb =new JComboBox();
	private JButton palette_ajou = new JButton("New");
	
	
	
	
	
	private JPanel pan_qtepalette = new JPanel();
	private JPanel pan_qtepalette_lab = new JPanel();
	private JPanel pan_qtepalette_text= new JPanel();
	private JLabel qtepalette_lab=new JLabel("Qte Palette");
	private JTextField qtepalette=new JTextField();
    
    
    
    private JPanel pan_gw = new JPanel();
    private JPanel pan_gw_lab = new JPanel();
    private JPanel pan_gw_jtext= new JPanel();
    
    
    private JPanel pan_sizecart = new JPanel();
    private JPanel pan_sizecart_lab = new JPanel();
    private JPanel pan_sizecart_comb= new JPanel();
    
    private JPanel pan_parcel = new JPanel();
    private JPanel pan_parcel_lab = new JPanel();
    private JPanel pan_parcel_jtext= new JPanel();
    
    
    private JPanel pan_commentaire = new JPanel();
    private JPanel pan_commentaire_lab = new JPanel();
    private JPanel pan_commentaire_area= new JPanel();
    private JLabel commentaire_lab=new JLabel("Commentaire");
    private JTextArea commentaire=new JTextArea();
    
    
    private JPanel pan_date = new JPanel();
    private JPanel pan_date_lab = new JPanel();
    private JPanel pan_date_jtext= new JPanel();
    private JLabel date_lab = new JLabel("Date");
    final JXDatePicker date_picker = new JXDatePicker();
    
    
    
    private JPanel pan_rech = new JPanel();
    private JPanel pan_rech_lab = new JPanel();
    private JPanel pan_rech_jtext= new JPanel();
    private JLabel rech_lab = new JLabel("Recherche");
    final JTextField rech_jtext = new JTextField();
    
    
    
    
    private JPanel pan_id_imei = new JPanel();
    private JPanel pan_id_imei_lab = new JPanel();
    private JPanel pan_id_imei_radio= new JPanel();
    private JLabel id_imei_lab = new JLabel("Type d'etiquette");
    private    ButtonGroup  id_imei_radio =new ButtonGroup();
     JRadioButton id_radio = new JRadioButton("ID");
    JRadioButton imei_radio = new JRadioButton("IMEI");
    
   

    
    private JPanel pan_button = new JPanel();
    private JPanel pan_tab = new JPanel();
    private JPanel pan_but_table = new JPanel();

    
    
    
    private JPanel pan_but_ligne  = new JPanel();

    private JButton but_ajou = new JButton("Ajouter une ligne");
    private JButton but_supprim = new JButton("Supprimer une ligne");


    
    private JButton valid_ajou = new JButton("Valider");
    private JButton valid_supp = new JButton("Valider");
    private JButton valid_modif = new JButton("Valider");
    private JButton retour = new JButton("Retour");

    private JButton but_ajouter = new JButton("Ajouter");
    private JButton but_modif = new JButton("Modifier");
    private JButton but_sup = new JButton("Supprimer");

    private JPanel pan_chaine = new JPanel();
    private JPanel pan_chaine_lab = new JPanel();
    private JPanel pan_chaine_comb= new JPanel();
    private JLabel chaine_lab = new JLabel("Chaine");
    jcombo chaine_comb ;
    ArrayList list_chaine = new ArrayList<String>();

    //private JList outil;
    
    private JPanel pan_dimension = new JPanel();
    private JPanel pan_dimension_lab = new JPanel();
    private JPanel pan_dimension_comb= new JPanel();
    private JLabel dimension_lab = new JLabel("Dimension");
    private JComboBox<String> dimension_comb =new JComboBox<String>();
    
    
    private JButton imp_embalage = new JButton("Imprimer");

    
    private JLabel article_lab = new JLabel("Article");
    private JLabel modele_lab = new JLabel("Modele");
    private JLabel qte_lab = new JLabel("Quantité");
    private JLabel gw_lab = new JLabel("G.W");
    private JLabel sizecart_lab = new JLabel("Carton Size");
    private JLabel parcel_lab = new JLabel("Parcel N°");
   
    

    ArrayList <String>list_dimension= new ArrayList<String>() ;
    private jcombo article_comb;
    private JTextField modele_jtext =new JTextField();
    private JTextField qte_jtext =new JTextField();
    //private JTextField contrat_jtext =new JTextField();
    private JComboBox sizecart_comb =new JComboBox();
    

    
    private JTextField parcel_jtext =new JTextField();

    private JComboBox gw_comb =new JComboBox();


    private Object[] entete = {"N°","Code Enie","Code IMEI1/ID" ,"Serial Number"};
    private final Tableau tab = new Tableau(entete);

    private JScrollPane p = new JScrollPane(tab);
    
    

    private int i,k,m;

     String check="";

    //private static ArrayList<String> list_poste_tr=new ArrayList<String>();
    private static ArrayList<String> list_article;
    NumberFormat format = new DecimalFormat("000");
    NumberFormat formatter = new DecimalFormat("00");
    NumberFormat form = new DecimalFormat("0");

    private static gestion_imp imp=new gestion_imp();

        
    etiquette_embalage_tpe(final String log) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(img);
        selectioncomb.windows(this, log);
        composant(log);
    }

    @SuppressWarnings("deprecation")
    public void composant(final String log) {
    	 id_radio.setSelected(true);

    	dimension_comb.addItem("--Sélectionner la dimension de l'etiquette--");
 		list_dimension=imp.select_dimension_etq("etq_tpe_emballage");

 		   for(int i=0;i<list_dimension.size();i++)
 		   {
 		          //Pour affecter une valeur de base de donn?es ? un Combobox 
 			   dimension_comb.addItem(list_dimension.get(i));
 		   }

      //  list_chaine=imp.select_dimension_etq("etq_emballage");
        chaine_comb = new jcombo(list_chaine.toArray());
        selectioncomb.selectchaine(chaine_comb, this, log);
        chaine_comb.setSelectedIndex(0);

          tab.allowEdition2=false;
          date_picker.setDate(Calendar.getInstance().getTime());
          date_picker.getEditor().setEditable(false);
          date_picker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
           Calendar cal = Calendar.getInstance();
         
            list_article = new ArrayList<String>(Arrays.asList(new String[] { "---Selectionner un article5555-----" }));
                article_comb = new jcombo(list_article.toArray());
                selectioncomb.selectarticle_tpe(article_comb, this, log);

                article_comb.setSelectedIndex(0);
                         sizecart_comb.setEditable(true);
                        // sizecart_comb.addItem("---Selectionner une dimension-----");
                          list_size=imp.select_size_tpe();
                      
                               for(int i=0;i<list_size.size();i++)
                               {
                                      //Pour affecter une valeur de base de donn?es ? un Combobox 
                                   sizecart_comb.addItem(list_size.get(i));
                                   
                               }
        
                               sizecart_comb.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        boolean ex=produit.exist(sizecart_comb,sizecart_comb.getEditor().getItem().toString());
                                        if((ex==false && !sizecart_comb.getEditor().getItem().toString().trim().equals("")))
                                        {
                                            int cont =sizecart_comb.getItemCount()+2;
                                            sizecart_comb.addItem(sizecart_comb.getEditor().getItem().toString());
                                        }
                                    }});
        
                               ArrayList<String> select_palette=new ArrayList<String>();
       						select_palette=imp.select_palette("PT");
                              palette_comb.addItem("---Selectionner une Palette-----");
       						  for(int i=0;i<select_palette.size();i++)
       						   {
       						          //Pour affecter une valeur de base de donn?es ? un Combobox 
       							   palette_comb.addItem(select_palette.get(i));
       							   
       						   }
       						  palette_comb.setSelectedIndex(palette_comb.getItemCount()-1);
       						  
       						   int qte=0 ;
       					        if(palette_comb.getItemCount()!=0)
       								qte=Integer.parseInt(imp.select_count_palette("imp_emballage_tpe",palette_comb.getSelectedItem().toString()))+1;
       					             qtepalette.setText(""+qte);
       					             
       					             
                                   
                                       gw_comb.setEditable(true);
                                        // sizecart_comb.addItem("---Selectionner une dimension-----");
                                          list_gw=imp.select_gw_tpe();
                                      
                                               for(int i=0;i<list_gw.size();i++)
                                               {
                                                      //Pour affecter une valeur de base de donn?es ? un Combobox 
                                                   gw_comb.addItem(list_gw.get(i));
                                               }
                        
                                               gw_comb.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent e) {
                                                        boolean ex=produit.exist(gw_comb,gw_comb.getEditor().getItem().toString());
                                                        if((ex==false && !gw_comb.getEditor().getItem().toString().trim().equals("") ))
                                                        {
                                                            int cont =gw_comb.getItemCount()+2;
                                                            gw_comb.addItem(gw_comb.getEditor().getItem().toString());
                                                        }
                                                    }});
                                            
                                               
                                                String arti = null;
                                                if(article_comb.getSelectedIndex()!=0){
                                                    String[] art = article_comb.getSelectedItem().toString().split(" ");
                                                     arti = art[0]; // 004
                                                }
                                                action_date();

         tab.table.getColumnModel().getColumn(0).setPreferredWidth(3);
         tab.sorter.setSortable(0, false);
         for(i=0;i<10;i=i+1){tab.ajouter();
         int k=tab.table.getRowCount();
         tab.table.setValueAt(i+1, k-1,0);
         tab.table.setValueAt("", k-1,1);
         tab.table.setValueAt("", k-1,2);
             tab.table.setValueAt("", k-1,3);
         }
    
         qte_jtext.setText(""+tab.table.getRowCount());


        qte_jtext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row= Integer.parseInt(qte_jtext.getText());
                int rows = tab.table.getRowCount();

                for (int i = rows - 1; i >= 0; i--) {
                    ((DefaultTableModel) tab.table.getModel()).removeRow(i);
                }
                for(i=0;i<row;i=i+1){
                    tab.ajouter();
                    int k=tab.table.getRowCount();
                    tab.table.setValueAt(i+1, k-1,0);

                    tab.table.setValueAt("", k-1,1);
                    tab.table.setValueAt("", k-1,2);
                    tab.table.setValueAt("", k-1,3);

                }
            }
        });



        if(!selectioncomb.prv.contains("etiqutte_embalage_tpe"))
        selectioncomb.prv.add("etiqutte_embalage_tpe");


        final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,18);
		qtepalette.setEditable(false);

        but_modif.setVisible(false);
        but_sup.setVisible(false);
        valid_supp.setVisible(false);

        but_ajouter.setVisible(true);
        but_ajouter.setVisible(true);
        valid_ajou.setVisible(false);
        valid_modif.setVisible(false);
        retour.setVisible(true);
        imp_embalage.setVisible(false);
    
         but_supprim.disable();
         modele_jtext.disable(); 
         tab.setStyle(2);
        

        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {
                ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
                img.paintIcon(this, g, 0, 0);
            }
        };


        chaine_comb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             action_date();
            }
        });

                palette_ajou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
			        Calendar cal = Calendar.getInstance();
			       String aff=imp.afficher_conteur_palette("PT",chaine_comb.getSelectedItem().toString().substring(2,3));
			       System.out.println("ouiiii"+aff);
			       palette_comb.addItem(aff);
			        palette_comb.setSelectedItem(aff);
			        int qte=0 ;
			        if(palette_comb.getItemCount()!=0)
						  qte=Integer.parseInt(imp.select_count_palette("imp_emballage_tpe",palette_comb.getSelectedItem().toString()))+1;
			             qtepalette.setText(""+qte);
			}
		});
		
		
		palette_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int qte=0 ;
						 
			        	qte=Integer.parseInt(imp.select_count_palette("imp_emballage_tpe",palette_comb.getSelectedItem().toString()))+1;
			            qtepalette.setText(""+qte);
			             
			     
			}
		});
		
    
        panel.registerKeyboardAction(this, CTRL_J,
                KeyStroke.getKeyStroke(KeyEvent.VK_J, Event.CTRL_MASK),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        
        LineBorder border = new LineBorder(Color.white, 1, true);
        TitledBorder titl2 = new TitledBorder(border, "  edition etiquette TPE ",
                TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
                police2, Color.white);
        pan_ft.setBorder(titl2);

        /*tab.table.changeSelection(1, 0, true, true);
        //	final String solve = "Solve";
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        KeyStroke tabu = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        tab.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                tab.table.setColumnSelectionInterval(2, 1);
            }  });
        tab.table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, tabu);
        tab.table.getActionMap().put(tabu, new EnterAction());
        tab.table.getActionMap().put(tabu, new EnterAction());

*/


      /*  tab.table.changeSelection(1, 0, true, true);
        final KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        final KeyStroke tabu = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        tab.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if(imei_radio.isSelected()){
                    tab.table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, tabu);
                    tab.table.getActionMap().put(tabu, new etiquette_embalage_tpe.EnterAction());

                    tab.table.setColumnSelectionInterval(2, 1);
                }
                else if(id_radio.isSelected()){

                    tab.table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).remove(enter);
                    tab.table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).remove(tabu);

                    tab.table.setColumnSelectionInterval(3, 1);
                }

            }  });
*/


        tab.table.changeSelection(1, 0, true, true);
        final KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        final KeyStroke tabu = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        tab.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if(imei_radio.isSelected()){
                    tab.allowEdition4=true;
                    tab.table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, tabu);
                    tab.table.getActionMap().put(tabu, new etiquette_embalage_tpe.EnterAction());
                    tab.table.setColumnSelectionInterval(3, 1);
                }
                else if(id_radio.isSelected()){
                    tab.allowEdition4=false;
                    tab.table.setColumnSelectionInterval(2, 1);

                    tab.table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, tabu);
                    tab.table.getActionMap().put(tabu, new etiquette_embalage_tpe.EnterAction());


                }

            }  });
        // tab.table.getCellEditor(i, 1).stopCellEditing();
        
        
    //  final String solve = "Solve";



        /*tab.table.changeSelection(1, 0, true, true);
        final KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        final KeyStroke tabu = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        tab.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
              public void valueChanged(ListSelectionEvent event) {
            	  if(imei_radio.isSelected()){
            	  
            	        tab.table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, tabu);
            	        tab.table.getActionMap().put(tabu, new EnterAction());
            	        tab.table.getActionMap().put(tabu, new EnterAction());
                         tab.table.setColumnSelectionInterval(2, 1);}
            	  else if(id_radio.isSelected()){
            		  tab.table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).remove(enter);
            		  tab.table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).remove(tabu);

            		  tab.table.setColumnSelectionInterval(2, 1);
            	  }
            
        }  });  */

      //  tab.table.getColumnModel().getColumn(0).setPreferredWidth(3);
       // tab.sorter.setSortable(0, false);
        
        
     

        parcel_jtext.disable();
        
        
        date_picker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String arti = null;
                if(article_comb.getSelectedIndex()!=0){
                    String[] art = article_comb.getSelectedItem().toString().split(" ");
                     arti = art[0]; // 004
                }
                action_date();
                
            }});
        
        
        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LineBorder border = new LineBorder(Color.white, 1, true);
                TitledBorder titl2 = new TitledBorder(border, "Edition etiquette emballage tpe",
                        TitledBorder.DEFAULT_POSITION,
                        TitledBorder.DEFAULT_POSITION, police2, Color.white);
                date_picker.setDate(Calendar.getInstance().getTime());
                action_date();
                pan_ft.setBorder(titl2);
                imp_embalage.setVisible(false);
                pan_but_ligne.setVisible(true);
                retour.setVisible(true);

                but_sup.setVisible(false);
                valid_supp.setVisible(false);

                but_modif.setVisible(false);
                but_ajouter.setVisible(true);
                valid_ajou.setVisible(false);

                valid_modif.setVisible(false);
                article_comb.setSelectedIndex(0);
                dimension_comb.setSelectedIndex(0);

                modele_jtext.setText("");
                qte_jtext.setText("");
                //contrat_jtext.setText("");
                gw_comb.setSelectedItem("");
                sizecart_comb.setSelectedItem("");
                //parcel_jtext.setText("");
                rech_jtext.setText("");
                
            //    outil.setText("");
                int rows = tab.table.getRowCount();
                
                for (int i = rows - 1; i >= 0; i--) {
                    ((DefaultTableModel) tab.table.getModel()).removeRow(i);
                }
                for(i=0;i<10;i=i+1){tab.ajouter();
                 int k=tab.table.getRowCount();
                 tab.table.setValueAt(i+1, k-1,0);
                 tab.table.setValueAt("", k-1,1);
                 tab.table.setValueAt("", k-1,2);
                 tab.table.setValueAt("", k-1,3);

                 }
                 qte_jtext.setText(""+tab.table.getRowCount());
                int qte=0 ;
                if(palette_comb.getItemCount()!=0)
                    qte=Integer.parseInt(imp.select_count_palette("imp_emballage_tpe",palette_comb.getSelectedItem().toString()))+1;
                qtepalette.setText(""+qte);

            }
        });



        but_ajou.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tab.ajouter();
                int k = tab.table.getRowCount();
                int i= tab.table.getRowCount();
                tab.table.setValueAt(i, k-1,0);
                tab.table.setValueAt("", k - 1,1);
                tab.table.setValueAt("", k - 1, 2);
                tab.table.setValueAt("", k - 1, 3);

                qte_jtext.setText(""+tab.table.getRowCount());

                    }
        });
        
        
        
        
        

        but_supprim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tab.table.getSelectedRow() == -1) {
                    JOptionPane
                            .showMessageDialog(
                                    null,
                                    "Vous devez d'abord sélectionner la ligne que vous voulez supprimer",
                                    "", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ((DefaultTableModel) tab.table.getModel())
                            .removeRow(tab.table.getSelectedRow());
                }            
                 qte_jtext.setText(""+tab.table.getRowCount());

            }
        });


        but_sup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //  use.select_zone_jtext(designation.getText());
                LineBorder border = new LineBorder(Color.white, 1, true);
                TitledBorder titl2 = new TitledBorder(border, "Supprimer",
                        TitledBorder.DEFAULT_POSITION,
                        TitledBorder.DEFAULT_POSITION, police2, Color.white);
                pan_ft.setBorder(titl2);
                tab.allowEdition1 = true;
                pan_but_ligne.setVisible(true);


                but_modif.setVisible(false);
                imp_embalage.setVisible(false);
                retour.setVisible(true);
                but_ajouter.setVisible(false);
                valid_ajou.setVisible(false);
                valid_supp.setVisible(true);
                but_sup.setVisible(false);
                valid_modif.setVisible(false);
            }
        });





        but_ajouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //  use.select_zone_jtext(designation.getText());
                LineBorder border = new LineBorder(Color.white, 1, true);
                TitledBorder titl2 = new TitledBorder(border, "Modifier",
                        TitledBorder.DEFAULT_POSITION,
                        TitledBorder.DEFAULT_POSITION, police2, Color.white);
                pan_ft.setBorder(titl2);
                tab.allowEdition1 = true;
        
            
                pan_but_ligne.setVisible(true);

                but_modif.setVisible(false);
                imp_embalage.setVisible(false);
                but_sup.setVisible(false);
                retour.setVisible(true);
                but_ajouter.setVisible(false);
                valid_ajou.setVisible(true);
                valid_supp.setVisible(false);
                valid_modif.setVisible(false);
            }
        });
        

        but_modif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //  use.select_zone_jtext(designation.getText());
                LineBorder border = new LineBorder(Color.white, 1, true);
                TitledBorder titl2 = new TitledBorder(border, "Modifier",
                        TitledBorder.DEFAULT_POSITION,
                        TitledBorder.DEFAULT_POSITION, police2, Color.white);
                pan_ft.setBorder(titl2);
                tab.allowEdition1 = true;
                pan_but_ligne.setVisible(true);
                but_modif.setVisible(false);
                imp_embalage.setVisible(false);
                but_sup.setVisible(false);
                retour.setVisible(true);
                but_ajouter.setVisible(false);
                valid_ajou.setVisible(false);
                valid_supp.setVisible(false);
                valid_modif.setVisible(true);
            }
        });



        valid_supp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //  use.select_zone_jtext(designation.getText());
                msg = "";

                if (rech_jtext.getText().trim().equals("")) {
                    msg += "Vous devez  remplir le code carton a supprimer \n";
                }

                else {
                    imp.supprimer_carton("imp_emballage_tpe","emei_tpe",parcel_jtext.getText());
                    JOptionPane.showMessageDialog(null,"L'etiquette a été bien supprimée");

                    date_picker.setDate(Calendar.getInstance().getTime());
                    action_date();
                    rech_jtext.setText("");
                    int ligne=Integer.parseInt(qte_jtext.getText());

                    int rows = tab.table.getRowCount();

                    for (int i = rows - 1; i >= 0; i--) {
                        ((DefaultTableModel) tab.table.getModel()).removeRow(i);
                    }


                    for(i=0;i<ligne;i=i+1){tab.ajouter();
                        int k=tab.table.getRowCount();
                        tab.table.setValueAt(i+1, k-1,0);
                        tab.table.setValueAt("", k-1,1);
                        tab.table.setValueAt("", k-1,2);
                        tab.table.setValueAt("", k-1,3);

                    }



                    but_modif.setVisible(false);
                    imp_embalage.setVisible(false);
                    but_sup.setVisible(false);
                    retour.setVisible(true);
                    but_ajouter.setVisible(true);
                    valid_ajou.setVisible(false);
                    valid_supp.setVisible(false);
                    valid_modif.setVisible(false);
                }


            }
        });


            

        valid_modif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //  use.select_zone_jtext(designation.getText());

                verifierchamp();
                    if (!msg.equals("")) {

                        JOptionPane.showMessageDialog(null, msg);
                    }
                    else{
          String[] art = article_comb.getSelectedItem().toString().split(" ");
          String arti = art[0]; // 004
          
            imp.update_embalage_tpe(parcel_jtext.getText(),arti,qte_jtext.getText(),gw_comb.getSelectedItem().toString()
            ,sizecart_comb.getSelectedItem().toString(),commentaire.getText(),date_picker.getEditor().getText(),
            palette_comb.getSelectedItem().toString());
            imp.delete_emei_tpe(parcel_jtext.getText());
            
            
         for (int i = 0; i < tab.table.getRowCount(); i++) {
              imp.update_emei_tpe(parcel_jtext.getText(),tab.table.getValueAt(i, 3).toString(), tab.table.getValueAt(i, 2).toString(), tab.table.getValueAt(i, 1).toString());

                            }
                        JOptionPane.showMessageDialog(null,"L'etiquette a été bien modifiée");
                        
                        imp_embalage.setVisible(true);
                        valid_modif.setVisible(false);
                        but_modif.setVisible(false);
                        retour.setVisible(true);

                    }
                
            }
        });

        

        

        valid_ajou.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verifierchamp();
                
                    if (!msg.equals("")) {
                        JOptionPane.showMessageDialog(null, msg);
                    }
                    else{
                        String[] art = article_comb.getSelectedItem().toString().split(" ");
                        String arti = art[0]; // 004

                        imp.ajout_embalage_tpe(parcel_jtext.getText(),arti,qte_jtext.getText()
                            ,gw_comb.getSelectedItem().toString()
                            ,sizecart_comb.getSelectedItem().toString(),commentaire.getText(),date_picker.getEditor().getText()
                            ,palette_comb.getSelectedItem().toString())    ;
                    
                    for(int i=0;i<tab.table.getRowCount();i++){                 
                           imp.ajout_emei_tpe(parcel_jtext.getText(),tab.table.getValueAt(i,3).toString(),
                                   tab.table.getValueAt(i, 2).toString(),  tab.table.getValueAt(i, 1).toString());
                           }
                    
                    JOptionPane.showMessageDialog(null, "l'etiquette a été bien ajouter");
                    imp_embalage.setVisible(true);
                    valid_ajou.setVisible(false);
                    but_ajouter.setVisible(false);
                    retour.setVisible(true);
                    }
            }});
        
        article_comb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (article_comb.getSelectedIndex() != 0 && article_comb.getSelectedItem()!=null) {
                String[] art = article_comb.getSelectedItem().toString().split(" ");
                String arti = art[0]; // 004
        String model=imp.select_model(arti);
        modele_jtext.setText(model);
                }}});
        
        
       
        
        
        rech_jtext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                    if(!parcel_jtext.getText().equals("")){

                        if(imp.exist_emballage_tpe(rech_jtext.getText())==true){

                             imp.selection_tpe_champ(rech_jtext.getText());
                            palette_comb.setSelectedItem(imp.palette);
                            palette_comb.setSelectedItem(imp.palette);
                            article_comb.setSelectedItem(imp.code_article+" "+imp.designation_tpe);
                            qte_jtext.setText(imp.qte_tpe);



                          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                          
                        Date d = null;
                        try {

                            Date date = formatter.parse(imp.date_emb.substring(0,10));


                            date_picker.setDate(date);

                        } catch (ParseException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }


                        gw_comb.setSelectedItem(imp.gw_tpe);
                        sizecart_comb.setSelectedItem(imp.carton_size);                     
                        parcel_jtext.setText(rech_jtext.getText());
                            ArrayList liste=imp.listCarton("imp_emballage_tpe", imp.palette);
                            int qte_modif=liste.indexOf(rech_jtext.getText().toUpperCase())+1;
                             qtepalette .setText(""+qte_modif);

                             list_emei=imp.selection_tpe_table(parcel_jtext.getText());

                               if(list_emei.get(0).trim().equals("")){
                                   id_radio.setSelected(true);
                               }else imei_radio.setSelected(true);



                             int rows = tab.table.getRowCount();
                             int row=Integer.parseInt(imp.qte_tpe);



                             for (int i = rows - 1; i >= 0; i--) {
                                 ((DefaultTableModel) tab.table.getModel()).removeRow(i);
                             }

                             for(i=0;i<row;i=i+1) {
                                 tab.ajouter();
                             }

                             int j=1;
                        int l = 0; i=1;
                        while(l<list_emei.size()){
                        while(l<j*3){


                            System.out.println(list_emei);
                        tab.getTable().setValueAt(j, j-1,0);

                        tab.getTable().setValueAt(list_emei.get(l), j-1,3);
                        tab.getTable().setValueAt(list_emei.get(l+1),j-1,2);
                        tab.getTable().setValueAt(list_emei.get(l+2),j-1,1);

                         i=i+1; l=l+3;
                             }
                         i=1;
                         j=j+1;
                         }




                        imp_embalage.setVisible(false);
                        pan_but_ligne.setVisible(true);

                        but_modif.setVisible(true);
                        but_sup.setVisible(true);
                        retour.setVisible(true);
                        but_ajouter.setVisible(false);
                        valid_ajou.setVisible(false);
                        valid_supp.setVisible(false);
                        valid_modif.setVisible(false);
                         qte_jtext.setText(""+tab.table.getRowCount());

                         }
                         else{
                            System.out.println("marahach2");

                            pan_but_ligne.setVisible(true);
                                imp_embalage.setVisible(false);
                                but_modif.setVisible(false);
                                
                            //  but_sup.setVisible(false);
                                retour.setVisible(true);
                                but_ajouter.setVisible(true);
                                valid_ajou.setVisible(false);
                            //  valid_supp.setVisible(false);
                                valid_modif.setVisible(false);
                                article_comb.setSelectedIndex(0);
                            
                                modele_jtext.setText("");
                                qte_jtext.setText("");
                                //contrat_jtext.setText("");
                                gw_comb.setSelectedItem("");
                                sizecart_comb.setSelectedItem("");
                            //  parcel_jtext.setText("");
                            
                            //    outil.setText("");
                                int rows = tab.table.getRowCount();
                                
                                for (int i = rows - 1; i >= 0; i--) {
                                    ((DefaultTableModel) tab.table.getModel()).removeRow(i);
                                }
                                
                                for(i=0;i<10;i=i+1){tab.ajouter();
                                 int k=tab.table.getRowCount();
                                    tab.table.setValueAt(i+1, k-1,0);
                                    tab.table.setValueAt("", k-1,1);
                                    tab.table.setValueAt("", k-1,2);
                                    tab.table.setValueAt("", k-1,3);

                                 }
                                 qte_jtext.setText(""+tab.table.getRowCount());
                                 JOptionPane
                                    .showMessageDialog(
                                            null,
                                            "Ce TPE n'existe pas",
                                            "", JOptionPane.INFORMATION_MESSAGE);
                            }
                         
                    }else{

                        pan_but_ligne.setVisible(true);
                        imp_embalage.setVisible(false);
                        but_modif.setVisible(false);
                        
                    //  but_sup.setVisible(false);
                        retour.setVisible(true);
                        but_ajouter.setVisible(true);
                        valid_ajou.setVisible(false);
                    //  valid_supp.setVisible(false);
                        valid_modif.setVisible(false);
                        article_comb.setSelectedIndex(0);
                        modele_jtext.setText("");
                        qte_jtext.setText("");
                        //contrat_jtext.setText("");
                        gw_comb.setSelectedItem("");
                    
                        sizecart_comb.setSelectedItem("");
                    //  parcel_jtext.setText("");
                    //    outil.setText("");
                        int rows = tab.table.getRowCount();
                        
                        for (int i = rows - 1; i >= 0; i--) {
                            ((DefaultTableModel) tab.table.getModel()).removeRow(i);
                        }
                        
                        for(i=0;i<10;i=i+1){tab.ajouter();
                         int k=tab.table.getRowCount();
                         tab.table.setValueAt(i, k-1,0);

                         tab.table.setValueAt("", k-1,1);
                         tab.table.setValueAt("", k-1,2);
                         tab.table.setValueAt("", k-1,3);

                         }
                         qte_jtext.setText(""+tab.table.getRowCount());
                        
                    }
                    
                }

            
        });


        
    
        imp_embalage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {// dispose();
                att= new Thread(){
                    public void run(){
//                    	rani hna f report
                  if(dimension_comb.getSelectedIndex()==0){
                	     JOptionPane
							.showMessageDialog(
									null,
									" Vous dever choisir la dimension de l'étiquette ",
									"", JOptionPane.INFORMATION_MESSAGE);
                  }else{
                        String parcel=parcel_jtext.getText().replace("/","_");
                        //int indice=article_comb.getSelectedIndex();
                        BufferedReader bfr = null ; 
                        Boolean pal=false;
                           String parcour = null ;String model;
                           if(dimension_comb.getSelectedItem().toString().contains("Palette")){
                        	   pal=true;
   	    	                parcour = "C:\\GCOBAR\\pdf\\etq_palette\\"+dimension_comb.getSelectedItem().toString().split(" ")[1]+palette_comb.getSelectedItem()+"_Etiquette_palette_tpe"+".pdf";

   	                    }
   	                    else if(!dimension_comb.getSelectedItem().toString().contains("Palette")){
   	    	                parcour = "C:\\GCOBAR\\pdf\\etq_emballage_tpe\\"+dimension_comb.getSelectedItem().toString().split(" ")[1]+parcel+"_Etiquette_emballage"+".pdf";

   	                    	
   	                    }
                           
             //   parcour = "C:\\GCOBAR\\pdf\\etq_emballage_tpe\\"+dimension_comb.getSelectedItem().toString()+parcel+"_Etiquette_emballage_tpe"+".pdf";
                 model = "C:\\GCOBAR\\CODE\\"+report;
                //imp.update_imp_s("1");
                 File fichier = new File(parcour);
                    fichier.delete(); 
                UIManager.put("nimbusOrange", (new Color(70,130,180)));

                try {
                    
                    //  
                    bfr=    new BufferedReader(new FileReader(parcour));
                    bfr.close();
                    System.out.println("buffer");
                    try {
                        Desktop.getDesktop().open(new File(parcour));
                        System.out.println("desk");
                    
                    } catch (IOException p) {
                        // TODO Auto-generated catch block
                        p.printStackTrace();
                    }}
                
                catch (FileNotFoundException fnfe) {
                	 
                    if(pal==true){

                    	String count=imp.select_count_palette("imp_emballage_tpe",palette_comb.getSelectedItem().toString());
	                    parameters.put("count",count);
                    }
                    else{
                    	parameters.clear();
                    }
                    
                    parameters.put("parcel",parcel_jtext.getText());
                    selectioncomb.imprimer( bdd, parcour,model,parameters);




                     //controlPanel.remove(progressBar);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                      int qte=0 ;
                      if(palette_comb.getItemCount()!=0)
                          qte=Integer.parseInt(imp.select_count_palette("imp_emballage_tpe",palette_comb.getSelectedItem().toString()))+1;
                      qtepalette.setText(""+qte);

                      retour.setVisible(true);
                      but_ajouter.setVisible(true);
                      imp_embalage.setVisible(false);
                      rech_jtext.setText("");

                      date_picker.setDate(Calendar.getInstance().getTime());
                      action_date();
                      int rows = tab.table.getRowCount();

                      for (int i = rows - 1; i >= 0; i--) {
                          ((DefaultTableModel) tab.table.getModel()).removeRow(i);
                      }
                      int row=Integer.parseInt(qte_jtext.getText());
                      for(i=0;i<row;i=i+1){tab.ajouter();
                          int k=tab.table.getRowCount();
                          tab.table.setValueAt(i+1, k-1,0);
                          tab.table.setValueAt("", k-1,1);
                          tab.table.setValueAt("", k-1,2);
                          tab.table.setValueAt("", k-1,3);

                      }


                  }
                    }}; 
                    
                    att.start();
                    
            }
        });
        
        
        dimension_comb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(dimension_comb.getSelectedIndex()!=0)
				report=imp.select_report(dimension_comb.getSelectedItem().toString(),"etq_tpe_emballage");	
				
			}});

        
        
        id_imei_radio.add(id_radio);
        id_imei_radio.add(imei_radio);



        generale.styles("Nimbus");
        SwingUtilities.updateComponentTreeUI(this);


        Font police_fi = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13);

        article_lab.setFont(police2);
        modele_lab.setFont(police2);
        parcel_lab.setFont(police2);
        rech_lab.setFont(police2);
        id_imei_lab.setFont(police2);
        gw_lab.setFont(police2);
        dimension_lab.setFont(police2);
        chaine_lab.setFont(police2);

        //contrat_lab.setFont(police2);
        qte_lab.setFont(police2);
        sizecart_lab.setFont(police2);
        commentaire_lab.setFont(police2);
        date_lab.setFont(police2);
        qtepalette_lab.setFont(police2);
		palette_lab.setFont(police2);
        
        tab.table.setFont(police_fi);
        article_comb.setFont(police_fi);
        modele_jtext.setFont(police_fi);
        
        parcel_jtext.setFont(police_fi);
        date_picker.setFont(police_fi);
        commentaire.setFont(police_fi);
        gw_comb.setFont(police_fi);
        
    //  contrat_jtext.setFont(police2);
        qte_jtext.setFont(police_fi);
        sizecart_comb.setFont(police_fi);
        dimension_comb.setFont(police_fi);
        chaine_comb.setFont(police_fi);

        // outil.setPreferredSize(new Dimension(210,100));
        date_lab.setForeground(new Color(255, 255, 255));
        dimension_lab.setForeground(new Color(255, 255, 255));
        chaine_lab.setForeground(new Color(255, 255, 255));


        commentaire_lab.setForeground(new Color(255, 255, 255));

    	qtepalette_lab.setForeground(new Color(255, 255, 255));
		palette_lab.setForeground(new Color(255, 255, 255));
        
        id_imei_lab.setForeground(new Color(255, 255, 255));
        rech_lab.setForeground(new Color(255, 255, 255));

        article_lab.setForeground(new Color(255, 255, 255));
        modele_lab.setForeground(new Color(255, 255, 255));
        
        parcel_lab.setForeground(new Color(255, 255, 255));
        
        gw_lab.setForeground(new Color(255, 255, 255));
        id_radio.setForeground(new Color(255, 255, 255));
        imei_radio.setForeground(new Color(255, 255, 255));
        //contrat_lab.setForeground(new Color(255, 255, 255));
    
        qte_lab.setForeground(new Color(255, 255, 255));
        sizecart_lab.setForeground(new Color(255, 255, 255));
        

        
        JScrollPane scrollPane = new JScrollPane(pan_ft1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setOpaque(false);
        p.getViewport().setOpaque(false);
    
        
           // ***************************************************************test**********************************************

        commentaire.setFont(new Font("Serif", Font.ITALIC, 13));
        commentaire.setLineWrap(true);       // wrap line
        commentaire.setWrapStyleWord(true);  // wrap line at word boundary
        commentaire.setBackground(new Color(204, 238, 241)); // light blue 
     
        //setPreferredSize(new Dimension(230, 30));
        // Wrap the JTextArea inside a JScrollPane
        JScrollPane tAreaScrollPane = new JScrollPane(commentaire);
        tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tAreaScrollPane.setPreferredSize(new Dimension(230, 130));



        pan_form1.add(pan_id_imei);
        pan_form1.add(pan_rech);
    	pan_form1.add(pan_palette);
		pan_form1.add(pan_qtepalette);
        pan_form1.add(pan_date);
        pan_form1.add(pan_parcel);
        pan_form1.add(pan_article);
        pan_form1.add(pan_model);
        pan_form1.add(pan_chaine);
        pan_form1.add(pan_dimension);

        pan_form1.add(pan_qte);
    
        pan_form1.add(pan_gw);
        
        pan_form1.add(pan_sizecart);
        pan_form1.add(pan_commentaire);
        
        pan_id_imei.add(pan_id_imei_lab);
        pan_id_imei.add(pan_id_imei_radio);
        
        pan_id_imei_lab.add(id_imei_lab);
        pan_id_imei_radio.add(id_radio);
        pan_id_imei_radio.add(imei_radio);

        pan_chaine.add(pan_chaine_lab);
        pan_chaine.add(pan_chaine_comb);
        pan_chaine_lab.add(chaine_lab);
        pan_chaine_comb.add(chaine_comb);

        pan_dimension.add(pan_dimension_lab);
        pan_dimension.add(pan_dimension_comb);
        pan_dimension_lab.add(dimension_lab);
        pan_dimension_comb.add(dimension_comb);
        
        pan_rech.add(pan_rech_lab);
        pan_rech.add(pan_rech_jtext);
        pan_rech_lab.add(rech_lab);
        pan_rech_jtext.add(rech_jtext);
        
    	pan_palette.add(pan_palette_lab);
		pan_palette.add(pan_palette_combo);
		pan_palette_lab.add(palette_lab);
		pan_palette_combo.add(palette_comb);
		pan_palette_combo.add(palette_ajou);

		
		
		pan_qtepalette.add(pan_qtepalette_lab);
		pan_qtepalette.add(pan_qtepalette_text);
		pan_qtepalette_lab.add(qtepalette_lab);
		pan_qtepalette_text.add(qtepalette);
		
        pan_parcel.add(pan_parcel_lab);
        pan_parcel.add(pan_parcel_jtext);
        pan_parcel_lab.add(parcel_lab);
        pan_parcel_jtext.add(parcel_jtext);
        
        pan_article.add(pan_article_lab);
        pan_article.add(pan_article_comb);
        pan_article_lab.add(article_lab);
        pan_article_comb.add(article_comb);
        
        
        pan_model.add(pan_model_lab);
        pan_model.add(pan_model_jtext);
        pan_model_lab.add(modele_lab);
        pan_model_jtext.add(modele_jtext);



        pan_qte.add(pan_qte_lab);
        pan_qte.add(pan_qte_comb);
        pan_qte_lab.add(qte_lab);
        pan_qte_comb.add(qte_jtext);
    
        
        
        pan_gw.add(pan_gw_lab);
        pan_gw.add(pan_gw_jtext);
        pan_gw_lab.add(gw_lab);
        pan_gw_jtext.add(gw_comb);
        
        pan_parcel.add(pan_parcel_lab);
        pan_parcel.add(pan_parcel_jtext);
        pan_parcel_lab.add(parcel_lab);
        pan_parcel_jtext.add(parcel_jtext);
        
        pan_sizecart.add(pan_sizecart_lab);
        pan_sizecart.add(pan_sizecart_comb);
        pan_sizecart_lab.add(sizecart_lab);
        pan_sizecart_comb.add(sizecart_comb);
        
    

        pan_commentaire.add(pan_commentaire_lab);
        pan_commentaire.add(pan_commentaire_area);
        pan_commentaire_lab.add(commentaire_lab);
        pan_commentaire_area.add(tAreaScrollPane, BorderLayout.CENTER);
        pan_date.add(pan_date_lab);
        pan_date.add(pan_date_jtext);
        pan_date_lab.add(date_lab);
        pan_date_jtext.add(date_picker);

        //pan_form1.add(pan_date);
        pan_ft1.add(pan_form1);
        

        panel.add(pan_ft);
        pan_ft.add(scrollPane);

        pan_ft.add(pan_tab);
        pan_tab.add(p);
        pan_tab.add(pan_but_ligne);
        panel.add(pan_button);
        //jTabbedPane.addTab("Composant", panel_general);
        jTabbedPane.addTab("etiquette emballage tpe", panel);

        // pan_desc.add(description);
        
        
        
        pan_but_ligne.add(but_ajou);
        pan_but_ligne.add(but_supprim);
        pan_button.add(retour);
    
        pan_button.add(valid_ajou);
        pan_button.add(valid_supp);

        pan_button.add(valid_modif);
        pan_button.add(but_modif);
        pan_button.add(but_sup);

        pan_button.add(but_ajouter);
        pan_button.add(imp_embalage);
        
        
        pan_rech.setOpaque(false);
        pan_rech_lab.setOpaque(false);
        pan_rech_jtext.setOpaque(false);
        
        pan_qtepalette.setOpaque(false);
		pan_qtepalette_lab.setOpaque(false);
		pan_qtepalette_text.setOpaque(false);
		
		
		pan_palette.setOpaque(false);
		pan_palette_lab.setOpaque(false);
		pan_palette_combo.setOpaque(false);
		
        
        pan_dimension.setOpaque(false);
        pan_dimension_lab.setOpaque(false);
        pan_dimension_comb.setOpaque(false);

        pan_chaine.setOpaque(false);
        pan_chaine_lab.setOpaque(false);
        pan_chaine_comb.setOpaque(false);
        
        pan_id_imei.setOpaque(false);

        pan_id_imei_radio.setOpaque(false);
        pan_id_imei_lab.setOpaque(false);
       
        pan_commentaire.setOpaque(false);
        pan_commentaire_lab.setOpaque(false);
        pan_commentaire_area.setOpaque(false);
        
        pan_date.setOpaque(false);
        pan_date_lab.setOpaque(false);
        pan_date_jtext.setOpaque(false);

        pan_article.setOpaque(false);
        pan_article_lab.setOpaque(false);
        pan_article_comb.setOpaque(false);
        
        pan_model_lab.setOpaque(false);
        pan_model_jtext.setOpaque(false);
        pan_model.setOpaque(false);
        
        
        
        pan_qte.setOpaque(false);
        pan_qte_comb.setOpaque(false);
        pan_qte_lab.setOpaque(false);
    
    
        
        pan_sizecart.setOpaque(false);
        pan_sizecart_comb.setOpaque(false);
        pan_sizecart_lab.setOpaque(false); 
        
        pan_parcel.setOpaque(false);
        pan_parcel_jtext.setOpaque(false);
        pan_parcel_lab.setOpaque(false); 
        
        
        
        
        
        pan_gw.setOpaque(false);
        pan_gw_jtext.setOpaque(false);
        pan_gw_lab.setOpaque(false); 
        
        
        
        
        
        pan_ft.setOpaque(false);
        pan_form.setOpaque(false);
        pan_button.setOpaque(false);
    
        pan_ft1.setOpaque(false);
        pan_form1.setOpaque(false);
        jTabbedPane.setOpaque(false);
        panel.setOpaque(false);
        //panel_general.setOpaque(false);

        // tab.setPreferredSize(new Dimension(700,700));
        
        
  
        
        
        p.setPreferredSize(new Dimension(500, 700));
        
        pan_id_imei_radio.setPreferredSize((new Dimension(200, 30)));
        
        palette_comb.setPreferredSize(new Dimension(175, 30));
		qtepalette.setPreferredSize(new Dimension(230, 30));
        pan_ft1.setPreferredSize(new Dimension(500, 900));
        article_comb.setPreferredSize(new Dimension(230, 30));
        modele_jtext.setPreferredSize(new Dimension(230, 30));
        qte_jtext.setPreferredSize(new Dimension(230, 30));
        gw_comb.setPreferredSize(new Dimension(230, 30));
      //  dimension_comb.setPreferredSize(new Dimension(230, 30));

        
        rech_jtext.setPreferredSize(new Dimension(230, 30));
        parcel_jtext.setPreferredSize(new Dimension(230, 30));
        sizecart_comb.setPreferredSize(new Dimension(230, 30));
        dimension_comb.setPreferredSize(new Dimension(230, 30));
        chaine_comb.setPreferredSize(new Dimension(230, 30));
        imp_embalage.setPreferredSize(new Dimension(230, 30));
        date_picker.setPreferredSize(new Dimension(230, 30));
        
        

        imp_embalage.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
       // dimension_lab.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
      //  id_imei_lab.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        id_radio.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        imei_radio.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
       // chaine_lab.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
       
     //   id_imei_lab.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        but_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        
        but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        but_sup.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        valid_supp.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        but_supprim.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        but_ajouter.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));




        but_sup.setPreferredSize(new Dimension(170, 33));
        valid_supp.setPreferredSize(new Dimension(170, 33));



        but_ajou.setPreferredSize(new Dimension(150, 33));
        but_modif.setPreferredSize(new Dimension(170, 33));
        but_supprim.setPreferredSize(new Dimension(170, 33));
        but_ajouter.setPreferredSize(new Dimension(120, 33));
        retour.setPreferredSize(new Dimension(120, 33));
        valid_ajou.setPreferredSize(new Dimension(120, 33));
        valid_modif.setPreferredSize(new Dimension(120, 33));
		palette_ajou.setPreferredSize(new Dimension(53, 33));

        p.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 20));
        pan_button.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
        article_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 30));
        modele_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 30));
    
        qte_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 20));
        gw_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 50));
    
    
        sizecart_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 00));
        date_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 50));
        commentaire_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 00));
        pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 00));

        pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 00));
        pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 00, 0, 10));
        
        //pan_id_imei_radio.setBorder(BorderFactory.createEmptyBorder(0,-10,0,0));
        pan_sizecart.setBorder(BorderFactory.createEmptyBorder(0, -10, 0, 0));
        pan_model.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pan_commentaire.setBorder(BorderFactory.createEmptyBorder(0, -10, 0, 0));
        

        pan_rech.setBorder(BorderFactory.createEmptyBorder(0, -10, 0, 0));

        pan_ft1.setBorder(BorderFactory.createEmptyBorder(0, 0, -30, 50));

        qtepalette_lab.setBorder(BorderFactory.createEmptyBorder(0, 17, 0, 0));
		pan_qtepalette.setBorder(BorderFactory.createEmptyBorder(0, -37, 0, 0));
		

		
		pan_palette_combo.setBorder(BorderFactory.createEmptyBorder(0,0, 0, 0));
		pan_palette_lab.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));

		
		pan_qtepalette_text.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_qtepalette_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		pan_palette_combo.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_palette_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_chaine_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        
        pan_commentaire_area.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_commentaire_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        pan_date_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_date_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_article_lab.setLayout(new FlowLayout(FlowLayout.LEFT));

        
        pan_parcel_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_parcel_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        pan_model_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_model_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        pan_id_imei_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_id_imei_radio.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        pan_rech_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_rech_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        

        pan_qte_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_qte_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        
    
        
        pan_gw_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_gw_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        

        
        pan_sizecart_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_sizecart_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        
    
        // Setup the content-pane of JFrame in BorderLayout

    
        pan_button.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_but_ligne.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_form.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_ft.setLayout(new BoxLayout(pan_ft, BoxLayout.X_AXIS));
        pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));


        pan_form1.setLayout(new BoxLayout(pan_form1, BoxLayout.Y_AXIS));
        // pan_form.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 100));
        pan_tab.setLayout(new BoxLayout(pan_tab, BoxLayout.Y_AXIS));
//      pan_comb.setLayout(new GridLayout(2, 1, 0, -25));
//      pan_produit.setLayout(new GridLayout(2, 1, 0, -25));
//      pan_profil.setLayout(new GridLayout(2, 1, 0, -25));
//      pan_numero_poste.setLayout(new GridLayout(2, 1, 0, 5));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//      Container cp = this.getContentPane();
//      cp.add(panel);
        // cp.add(tAreaScrollPane, BorderLayout.CENTER);
        setTitle("Etiquette emballage tpe");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setContentPane(jTabbedPane);
    
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
/////////////////////////////valid tableau//////////////////////////////////////////////////////
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
                if (om.trim().length() == 0)

                {
                    msg = "";
                    return false;

                }
            }
        }

        return true;
    }
    ///////////////////////////////////////////////////////////////////////////////

    public boolean vaidCheckonecloum(Tableau tab)
    {
        if (tab.table.getCellEditor() != null) {

            tab.table.getCellEditor().stopCellEditing();
        }

        for (int l = 0; l < tab.table.getRowCount(); l++)

        {
                String om = tab.table.getValueAt(l, 1).toString();
              //  System.out.println("gggg"+om);
                if (om.trim().length() == 0)

                {
                    msg = "";
                    return false;

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



    public void verifierchamp(){
        msg = "";
        selectioncomb.closePdf();

        if (article_comb.getSelectedIndex() == 0) {
            msg += "Vous Devez  Sélectionner un Article\n";
        }
        else if (gw_comb.getSelectedItem()==null) {
            msg += "Vous Devez d'abord Remplir le G.W \n";
        }else if(gw_comb.getSelectedItem().equals("")) msg += "Vous Devez d'abord Remplir le G.W \n";
        else if (sizecart_comb.getSelectedItem()==null) {
            msg += "Vous Devez d'abord selectionner la Dimension du Carton \n";
        }else if (sizecart_comb.getSelectedItem().equals(""))msg += "Vous Devez d'abord selectionner la Dimension du Carton \n";
        else if (parcel_jtext.getText().equals("")) {
            msg += "Vous Devez  Remplir le numero  Parcel \n";
        }
        else if (palette_comb.getSelectedIndex()==0) {
            msg += "Vous devez  selectionner une Pallette ";
        }
        else if (tab.table.getRowCount() == 0) {
            msg += "Vous devez  d'abord ajouter une Ligne ";
        }

        else {


            if(id_radio.isSelected()){
                if (vaidCheckonecloum(tab) == false) {
                    valid_j = true;
                    msg += "Un des id est vide \n";
                }
                for (int i = 0; i < tab.table.getRowCount(); i++) {
                    for (int j = i + 1; j < tab.table.getRowCount(); j++) {
                        int p=i+1; int g=j+1;
                        if ( tab.table.getValueAt(i, 2).toString().equals(tab.table.getValueAt(j, 2).toString())||
                                tab.table.getValueAt(i,1).toString().equals(tab.table.getValueAt(j, 1).toString())||
                                tab.table.getValueAt(i, 1).toString().equals(tab.table.getValueAt(j, 2).toString())||
                                tab.table.getValueAt(i,1).toString().equals(tab.table.getValueAt(j, 2).toString())   ) {
                            msg += "les Ligne "+p+ " et "+g+" sont  dupliqué. \n";
                        }
                    }
                }
            }
            else if(imei_radio.isSelected()){
                if (vaidCheck(tab) == false) {
                    valid_j = true;
                    msg += "Un Champ dans le tableau est vide \n";
                }
                int v;
                if (valid_j == false) {
                    System.out.println("hhhhh");
                    for (int i = 0; i < tab.table.getRowCount(); i++) {
                        System.out.println("hhhhh");
                        if (tab.table.getValueAt(i, 1).toString().equals(tab.table.getValueAt(i, 2).toString())) {
                            v=i+1;
                            msg += "Le code enie est egals au code imei."+v+ "\n";
                        }

                        if (tab.table.getValueAt(i, 1).toString().equals(tab.table.getValueAt(i, 3).toString())) {
                            v=i+1;
                            msg += "Le code enie est egals au serial number dans la ligne."+v+ "\n";
                        }
                        if (tab.table.getValueAt(i, 2).toString().equals(tab.table.getValueAt(i, 3).toString())) {
                            v=i+1;
                            msg += "L'imei  est egals au serial number dans la ligne."+v+ "\n";
                        }
                        for (int j = i + 1; j < tab.table.getRowCount(); j++) {
                            int p=i+1; int g=j+1;
                            if (tab.table.getValueAt(i, 2).toString().equals(tab.table.getValueAt(j, 2).toString())||
                                    tab.table.getValueAt(i,1).toString().equals(tab.table.getValueAt(j, 1).toString())||
                                    tab.table.getValueAt(i, 1).toString().equals(tab.table.getValueAt(j, 2).toString())||
                                    tab.table.getValueAt(i, 3).toString().equals(tab.table.getValueAt(j, 3).toString())||
                                    tab.table.getValueAt(i,1).toString().equals(tab.table.getValueAt(j, 3).toString())||
                                    tab.table.getValueAt(i, 3).toString().equals(tab.table.getValueAt(j, 2).toString())


                            )
                            {
                                msg += " Les Ligne "+p+" et "+g+" sont  dupliqué. \n";
                            }
                        }
                    }}

            }


        }
    }
/*selection article*****************************/
                
                
                
                
    public String action_date(){

        chaine=chaine_comb.getSelectedItem().toString().split(" ")[0].substring(2).trim();
        Calendar cal = Calendar.getInstance();
        System.out.println(chaine+"eeeeeee"+date_picker.getDate());

        String aff=imp.afficher_conteur_tpe(date_picker.getDate(),chaine,"imp_emballage_tpe","TP");
                System.out.println("eeeeeee"+aff);
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
        
            code = "TP" +chaine+ jour+ week + anne   + aff;
    System.out.println(code);
        parcel_jtext.setText(code);
        return code;
    }
            
    private class EnterAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            Robot rob;
            try {
                rob = new Robot();
                rob.keyPress(KeyEvent.VK_TAB);
                rob.keyRelease(KeyEvent.VK_TAB);
            } catch (AWTException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   //    UIManager.setLookAndFeel(laf);

                    new etiquette_embalage_tpe("7");
                    // frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
