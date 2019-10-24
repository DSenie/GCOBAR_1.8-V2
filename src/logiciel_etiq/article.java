package logiciel_etiq;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXDatePicker;


public class article extends JFrame  implements ActionListener{
	private static final String    CTRL_J                = "CTRL+J";
	final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);
    //@SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
      // gestion de l'action

		if (e.getActionCommand().equals(CTRL_J)){
    	 LineBorder border = new LineBorder ( Color.white, 1, true );
       	 TitledBorder titl2 = new TitledBorder ( border, "Edition Article", TitledBorder.DEFAULT_POSITION,
       	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
       	 pan_form.setBorder(titl2);
       	 jTabbedPane.remove(pan_nomo);
         article_comb.setSelectedIndex(0);
         article_comb.setEditable(true);
         ref_comb.setSelectedIndex(0);
         ref_comb.setEditable(false);
		 indice.setSelectedIndex(0);
         famille_comb.setSelectedIndex(0);
         famille_comb.setEditable(false);
         codegener_comb.setSelectedIndex(0);
         codegener_comb.setEditable(false);
         fourniss_comb.setSelectedIndex(0);
         fourniss_comb.setEditable(false);
         indice.setSelectedIndex(0);

         coeff_jtext.setText("0");
         codeprod.setText("");
         codeprod1.setText("");


         datelancement.setDate(Calendar.getInstance().getTime());
         soft.setText("");
         ingenieur.setText("");
         description.setText("");
         codeprod.disable();
         but_sauv.setVisible(true);
         but_modif.setVisible(false);
       //  impl.setVisible(false);

         but_sup.setVisible(true);
         retour.setVisible(false);
         valid_supp.setVisible(false);
         valid_ajou.setVisible(false);
         valid_modif.setVisible(false);
         designation.setText("");
         article_comb.enable();
         ref_comb.disable();
         famille_comb.disable();
         codegener_comb.disable();
         codeprod1.disable();
         designation.enable();
         fourniss_comb.disable();
         coeff_jtext.disable();
         datelancement.getEditor().disable();
         soft.disable();
         ingenieur.disable();
         indice.disable();
         description.disable();
         remove_ligne_table(tab);
         remove_ligne_table(tab_prod);
       }}
	/**
	 *
	 */
	Thread att;
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private String msg;
	private static gestion_article art=new gestion_article();

	private JPanel pan_tab=new JPanel();
	private JPanel pan_tab_prod=new JPanel();
	private JPanel pan_but_poste=new JPanel();
	private JPanel pan_but_prod=new JPanel();
	boolean repet;boolean desc;
	ArrayList<String> article_list = new ArrayList<String>();
	ArrayList<String> model_list = new ArrayList<String>();
	ArrayList<String> list_fo = new ArrayList<String>();
	private JPanel pan_artgener=new JPanel();
	private JLabel lab_combo=new JLabel("Liste des articles");
	private  jcombo article_comb;

	//private JComboBox<String> art_comb=new JComboBox<String>();

	private JPanel pan_article=new JPanel();
	private JPanel pan_lab_combo=new JPanel();
	private JPanel pan_article_comb=new JPanel();
	private JPanel pan_radio=new JPanel();
	private JPanel pan_radio_lab=new JPanel();
	private JPanel pan_radio_bg=new JPanel();

	private JPanel pan_indice = new JPanel();
	private JPanel pan_indice_comb = new JPanel();
	private JPanel pan_indice_lab = new JPanel();
	private JLabel indice_lab = new JLabel("A imprimer");
    private JComboBox<String> indice=new JComboBox<String>();

	private JLabel  entrep= new JLabel("         Entreprise");
	private JRadioButton enie= new JRadioButton("ENIE",true);
	private JRadioButton autre = new JRadioButton("AUTRE",false);
	private ButtonGroup bG = new ButtonGroup();
	private JPanel pan_entrep=new JPanel();

	private JPanel pan_codeprogener=new JPanel();
	private JLabel ref_lab_combo=new JLabel("Chiffre référence");
    private List<String> list_r;
    private  jcombo ref_comb;
    private JPanel pan_refer=new JPanel();
    private JPanel pan_ref_lab=new JPanel();
    private JPanel pan_ref_comb=new JPanel();
    private JLabel codegener_lab=new JLabel("Famille");
    private List<String> list;

    private List<String> list_a;
    private  jcombo codegener_comb;
    private JPanel pan_codegener=new JPanel();
    private JPanel pan_codegener_lab=new JPanel();
    private JPanel pan_codegener_comb=new JPanel();
    private JLabel codeprod_lab=new JLabel("Code Article");
    private JTextField codeprod=new JTextField();
    private JPanel pan_codeprod=new JPanel();
    private JPanel pan_codeprod_lab=new JPanel();
    private JPanel pan_codeprod_jtext=new JPanel();
    private JPanel pan_codeprogener1=new JPanel();
    private JLabel codeprod1_lab=new JLabel("Code Article");
    private JTextField codeprod1=new JTextField();
    private JPanel pan_codeprod1=new JPanel();
    private JPanel pan_codeprod1_lab=new JPanel();
    private JPanel pan_codeprod1_jtext=new JPanel();
    private JLabel famille_lab=new JLabel("Type");
    private List<String> list_fa;
    private  jcombo famille_comb;
    private JPanel pan_famille=new JPanel();
    private JPanel pan_famille_lab=new JPanel();
    private JPanel pan_famille_comb=new JPanel();
    private JLabel designation_lab=new JLabel("Designation");
    private JTextField designation=new JTextField();
    private JPanel pan_designation=new JPanel();
    private JPanel pan_designation_lab=new JPanel();
    private JPanel pan_designation_jtext=new JPanel();

    private Java2sAutoComboBox fourniss_comb ;
    private Java2sAutoComboBox art_comb ;
    private Java2sAutoComboBox model_comb;
    private JLabel coeff_lab=new JLabel("Coefficient");
    private JTextField coeff_jtext=new JTextField();
    private JPanel pan_coeff=new JPanel();
    private JPanel pan_coeff_lab=new JPanel();
    private JPanel pan_coeff_jtext=new JPanel();


    private JLabel model_lab=new JLabel("Modèle");
    private JTextField model_jtext=new JTextField();
    private JPanel pan_model=new JPanel();
    private JPanel pan_model_lab=new JPanel();
    private JPanel pan_model_jtext=new JPanel();


    private JPanel pan_infogener=new JPanel();
    private JLabel datelancement_lab=new JLabel("Date lancement");
    private JPanel pan_datelancement=new JPanel();
    private JPanel pan_datelancement_lab=new JPanel();
    private JPanel pan_datelancement_jtext=new JPanel();
    private JLabel soft_lab=new JLabel("Software");
    private JTextField soft=new JTextField();
    private JPanel pan_soft=new JPanel();
    private JPanel pan_soft_lab=new JPanel();
    private JPanel pan_soft_jtext=new JPanel();
    private JLabel ingenieur_lab=new JLabel("Ingenieur chargé");
    private JTextField ingenieur=new JTextField();
    private JPanel pan_ingenieur=new JPanel();
    private JPanel pan_ingenieur_lab=new JPanel();
    private JPanel pan_ingenieur_jtext=new JPanel();
    private JLabel description_lab=new JLabel("Description");
    private JTextArea description=new JTextArea();
    private JPanel pan_description=new JPanel();
    private JPanel pan_description_lab=new JPanel();
    private JPanel pan_description_jtext=new JPanel();
    private JPanel pan_gener=new JPanel();
    private JPanel pan_form=new JPanel();
    private JPanel pan_form1=new JPanel();
    private JPanel pan_form2=new JPanel();
    private JPanel pan_buton=new JPanel();

    private JButton but_sauv=new JButton("Ajouter");
    private JButton but_modif=new JButton("Modifier");
    private JButton but_sup=new JButton("Supprimer");
    private JButton valid_ajou=new JButton("Valider");
    private JButton valid_modif=new JButton("Valider");
    private JButton valid_supp=new JButton("Valider");
 //   private JButton impl=new JButton("Imprimer BOM");
    private JButton retour=new JButton("Retour");
    private JButton but_ajou=new JButton("Ajouter un Article");
    private JButton but_supprim=new JButton("Supprimer un Article");

    private JButton but_ajou_prod=new JButton("Ajouter un produit");
    private JButton but_supprim_prod=new JButton("Supprimer un produit");

    final JXDatePicker datelancement = new JXDatePicker();
    private static ArrayList<String> list_article_tr=new ArrayList<String>();
    private static ArrayList<String> list_fourniss_tr=new ArrayList<String>();
    private static ArrayList<String> list_artnomo_tr=new ArrayList<String>();
    private static ArrayList<String> list_model_tr=new ArrayList<String>();

    private static ArrayList<String> list_art_tab=new ArrayList<String>();
    private static ArrayList<String> list_art_tab_prod=new ArrayList<String>();

    private JTabbedPane jTabbedPane = new JTabbedPane();
    private JPanel pan_nomo=new JPanel();
    private JPanel pan_prod=new JPanel();
    private int i,a;
    private Object [] entete1={"Code/Intitule produit","Fournisseur","Produit","Position","prix","Quantité"};
    private Object [] entete={"Code/Intitule article","Quantité"};
    private final Tableau tab=new Tableau(entete);

    private static gestion_fournisseur frns=new gestion_fournisseur();
    private final Tableau tab_prod=new Tableau(entete1);

    private JScrollPane p=new JScrollPane(tab);
    private JScrollPane p_prod=new JScrollPane(tab_prod);
    int par_ind;
    int ind;
    String fabr="";
    article(final String log){
	Toolkit kit = Toolkit.getDefaultToolkit();
    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
    setIconImage(img);
    selectioncomb.windows(this,log,laf);
    composant(log);
}

@SuppressWarnings("deprecation")
public void composant(final String logi_prio) {
	  //art_comb.setEditable(true);
	indice.addItem("---Sélectionner un indice---");
	 ArrayList <String>resultat_model= new ArrayList<String>() ;
	 resultat_model=art.model_imp();
	 for(int i=0;i<resultat_model.size();i=i+2) {
		 System.out.println("55555"+i);
		 indice.addItem(resultat_model.get(i+1)+" "+resultat_model.get(i));

		 System.out.println("gfgfgfgf"+i);
	 }



	if(!selectioncomb.prv.contains("article")){
	    selectioncomb.prv.add("article");}

	  list_fa = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un type article-----"}));
	  famille_comb = new jcombo(list_fa.toArray());
	  selectioncomb.selectfamille(famille_comb ,this,logi_prio);



	 // remplir_table(list_fo,"---Sélectionner un fournisseur---",list_fourniss_tr,fourniss_comb);
	  remplir_fourniss_table();
	  remplir_art_table();
	  remplir_model_table();

 	   if(enie.isSelected()==true){fabr="enie";}
 	   else {fabr="autre";}


	     list_a = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un article-----"}));
	     article_comb = new jcombo(list_a.toArray());
	     list_article_tr=art.select_article_code(fabr);


	     for(int i=0;i<list_article_tr.size();i++)
		   {
		          //Pour affecter une valeur de base de données  un Combobox
			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
			   i++;
		   }
	      article_comb.setWide(true);


		   list_r = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner une référence-----"}));
		   ref_comb = new jcombo(list_r.toArray());
		    selectioncomb.selectrefer(ref_comb ,this,logi_prio);



		   list = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner une famille article-----"}));
		   codegener_comb = new jcombo(list.toArray());
		   selectioncomb.selectgener(codegener_comb ,this,logi_prio);



		   datelancement.setDate(Calendar.getInstance().getTime());
		   datelancement.setFormats(new SimpleDateFormat("dd/MM/yyyy"));

		   final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);
		   if( fabr.equals("autre")){
			  	 pan_codeprogener.setVisible(false);
		         pan_codeprogener1.setVisible(true);
		           }
		           else{
		        		 pan_codeprogener.setVisible(true);
		                 pan_codeprogener1.setVisible(false);
		           }

	but_sauv.setVisible(true);
  	but_modif.setVisible(false);
  	but_sup.setVisible(true);
  	retour.setVisible(false);
  	valid_supp.setVisible(false);
  	valid_ajou.setVisible(false);
  	valid_modif.setVisible(false);
  	// impl.setVisible(false);


     coeff_jtext.setText("0");
     tab.setStyle(2);
     tab_prod.setStyle(2);
	 panel= new JPanel(){
	  		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){
	  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
	             img.paintIcon(this, g,0, 0);

	  	}  };

	 	panel.registerKeyboardAction(this, CTRL_J, KeyStroke.getKeyStroke(
	            KeyEvent.VK_J, Event.CTRL_MASK),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);
	  	 pan_nomo= new JPanel(){
			private static final long serialVersionUID = 1L;
				public void paintComponent(Graphics g){
		  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
		             img.paintIcon(this, g,0, 0);

		  	}  };

		  	 pan_prod= new JPanel(){
				private static final long serialVersionUID = 1L;
					public void paintComponent(Graphics g){
			  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
			             img.paintIcon(this, g,0, 0);

			  	}  };



	  	  LineBorder border = new LineBorder ( Color.white, 1, true );
      	  TitledBorder titl2 = new TitledBorder ( border, "Edition Article", TitledBorder.DEFAULT_POSITION,
          TitledBorder.DEFAULT_POSITION, police2, Color.white);
          pan_form.setBorder(titl2);


          indice.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  			 par_ind = 0;
      	  if( indice.getSelectedIndex()!=0){

       		   par_ind = indice.getSelectedIndex(); // 004
	        	  }
  			}
  		});


	  	retour.addActionListener(
	            new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	LineBorder border = new LineBorder ( Color.white, 1, true );
	            	 TitledBorder titl2 = new TitledBorder ( border, "Edition Article", TitledBorder.DEFAULT_POSITION,
	            	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
	            	 pan_form.setBorder(titl2);
	            	 jTabbedPane.remove(pan_nomo);

   	                 article_comb.setSelectedIndex(0);
   	                 article_comb.setEditable(true);
                     ref_comb.setSelectedIndex(0);
                     ref_comb.setEditable(false);
   				     indice.setSelectedIndex(0);
	                 famille_comb.setSelectedIndex(0);
	                 famille_comb.setEditable(false);
	                 codegener_comb.setSelectedIndex(0);
	                 codegener_comb.setEditable(false);
                     fourniss_comb.setSelectedIndex(0);
                     fourniss_comb.setEditable(false);
                     //impl.setVisible(false);
                     indice.setSelectedIndex(0);

	                 coeff_jtext.setText("0");
	                 codeprod.setText("");
	                 codeprod1.setText("");

		             datelancement.setDate(Calendar.getInstance().getTime());
	                 soft.setText("");
	                 ingenieur.setText("");

	                 description.setText("");

	                 codeprod.disable();
	                 but_sauv.setVisible(true);
	                 but_modif.setVisible(false);
	                 but_sup.setVisible(true);
	                 retour.setVisible(false);
	                 valid_supp.setVisible(false);
	                 valid_ajou.setVisible(false);
	                 valid_modif.setVisible(false);
	                 designation.setText("");
	                 article_comb.enable();
	                 ref_comb.disable();

	                 famille_comb.disable();
	                 codegener_comb.disable();
	                 codeprod1.disable();
	                 designation.enable();
	                 fourniss_comb.disable();

	                 coeff_jtext.disable();

	                 datelancement.getEditor().disable();
	                 soft.disable();
	                 ingenieur.disable();
	                 indice.disable();

	                 description.disable();

	                 remove_ligne_table(tab);
	                 remove_ligne_table(tab_prod);
	            }});

	  	 but_sup.addActionListener(
	             new ActionListener() {
	             public void actionPerformed(ActionEvent e) {
	            	 LineBorder border = new LineBorder ( Color.white, 1, true );
	            	 TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
	            	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
	            	 pan_form.setBorder(titl2);
	            	 but_sauv.setVisible(false);
	                 but_modif.setVisible(false);
	             //    impl.setVisible(false);
	                 but_sup.setVisible(false);
	                 retour.setVisible(true);
	                 valid_supp.setVisible(true);
	                 valid_modif.setVisible(false);
	                 valid_ajou.setVisible(false);
	                 article_comb.enable();
	                 article_comb.setEditable(true);
		             ref_comb.disable();
		             ref_comb.setEditable(false);
		             famille_comb.disable();
		             famille_comb.setEditable(false);
		             codegener_comb.disable();
		             codegener_comb.setEditable(false);
		             codeprod.disable();
		             codeprod1.disable();
		             designation.disable();
		             fourniss_comb.disable();
		             fourniss_comb.setEditable(false);

		             coeff_jtext.disable();



		             datelancement.getEditor().disable();
		             soft.disable();
		             ingenieur.disable();

		             description.disable();
		             indice.disable();

	             }});

	 	valid_supp.addActionListener(
	            new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	  String[] part_art = article_comb.getSelectedItem().toString().split(" ");
	           		  String part_art1 = part_art[0]; // 004
	            	  art.setdelete(part_art1,designation.getText());
		        	   remplir_art(fabr);
		     		   remplir_art_table();

		     		  remove_ligne_table(tab);
		                 remove_ligne_table(tab_prod);

	            }});

	 	but_modif.addActionListener(
	            new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	LineBorder border = new LineBorder ( Color.white, 1, true );
	            	 TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
	            	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
	            	 pan_form.setBorder(titl2);
	            	 but_sauv.setVisible(false);
	                 but_modif.setVisible(false);
	              //   impl.setVisible(false);
	                 but_sup.setVisible(false);
	                 retour.setVisible(true);
	                 valid_supp.setVisible(false);
	                 valid_modif.setVisible(true);
	                 valid_ajou.setVisible(false);
	                 article_comb.enable();
	                 article_comb.setEditable(true);

	                 ref_comb.disable();
	                 ref_comb.setEditable(false);
	                 famille_comb.enable();
	                 famille_comb.setEditable(true);
	                 codegener_comb.disable();
	                 codegener_comb.setEditable(false);
	                 codeprod.enable();
	                 codeprod1.enable();
	                 designation.enable();
	                 fourniss_comb.enable();
	                 fourniss_comb.setEditable(true);

	                 coeff_jtext.enable();


	                 datelancement.getEditor().enable();
	                 soft.enable();
	                 ingenieur.enable();
	                 description.enable();
	                 indice.enable();
	            }});

		valid_modif.addActionListener(
	            new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	  if(tab.table.getCellEditor()!=null){
		        		   tab.table.getCellEditor().stopCellEditing();
		        		   }
		        	//   System.out.println(designation.getText());
		        	   art.article(designation.getText());
		        	   msg=""; String part2 = null;

		        	   if(famille_comb.getSelectedIndex()!=0&&famille_comb.getSelectedIndex()!=famille_comb.getItemCount()-1){
		        		    //part2 = famille_comb.getSelectedItem().toString().substring(4);
		        		    String[] famille_part = famille_comb.getSelectedItem().toString().split(" ");
		            	    String  famil_part = famille_part[0]; // 004
		            	    part2 = famille_comb.getSelectedItem().toString().replace(famil_part+" ", "");
		            //    System.out.println("rrrrrr"+part2+"fffffff");
	            	// part2 = parts[1];
	            	//System.out.println("gggggg"+ part2);
		        	   }
			 	    	//System.out.println(isValid(pu_jtext.getText()));

		 	    	 if (enie.isSelected()&&(codegener_comb.getSelectedIndex()==0||ref_comb.getSelectedIndex()==0)){
		                	 if(codegener_comb.getSelectedIndex()==0||codegener_comb.getSelectedIndex()==-1){msg+="Veuillez selectionner un code générique \n";}

		                	 else if(ref_comb.getSelectedIndex()==0||ref_comb.getSelectedIndex()==-1){msg+="Veuillez selectionner une référence \n";}
	             		  }
		                 else  if (autre.isSelected()&&codeprod1.getText().equals("")){
		                	 if(codeprod1.getText().equals("")){msg+="Veuillez  saisir le code d'article \n";}
	             		  }

		                 else  if (enie.isSelected()&&codeprod.getText().equals("")){
		                	 if(codeprod.getText().equals("")){msg+="Veuillez  saisir le code d'article \n";}
	             		  }
		                 else if(designation.getText().equals("")){ msg+="Veuillez saisir la designation de l'article\n";}

		          // else if(numcontrat.getText().equals("")){msg+="Veuillez saisir le Numero de contrat \n";}

		         //  else if(modeltv.getText().equals("")){msg+="Veuillez saisir le Model \n";}
		         //  else if(ingenieur.getText().equals("")){msg+="Veuillez saisir l'ingenieur\n";}
		         //  else if(coeff_jtext.getText().equals("")){msg+="Veuillez saisir le coefficient\n";}
		        //   else if(pu_jtext.getText().equals("")){msg+="Veuillez saisir le prix unitaire \n";}
		        //   else if(!isValid(pu_jtext.getText())){msg+="Le prix unitaire doit étre un nombre\n";}
		        //   else if(!isValid(coeff_jtext.getText())){msg+="Le coefficient doit etre un nombre \n";}


		         //  else if(qte.getText().equals("")){msg+="Veuillez saisir la quantite \n";}
		       //    else if(!isValid(qte.getText())){msg+="La quantite doit etre un nombre \n";}

		           else if(famille_comb.getSelectedIndex()==0||famille_comb.getSelectedIndex()==-1){msg+="Veuillez selectionner un type article \n";}


		           else if(coeff_jtext.getText().equals("")){msg+="Veuillez saisir le coefficient \n";}
		           else if(part2.equals("Ensemble")||part2.equals("Sous Ensemble")){

			        	   for(int i=0;i<tab.table.getRowCount();i++){
				             	  // for(int j=0;j<tab.table.getColumnCount();j++){
				             		  String col2= tab.table.getValueAt(i, 1).toString();
				             		  if(isValid(col2)==false){
				             			  msg+="La quantite dans le tableau doit étre un nombre \n";
				             		  }}
			        	   for(int i=0;i<tab.table.getRowCount();i++){
			  	           	   for(int j=i+1;j<tab.table.getRowCount();j++){
			  	           		   if(tab.table.getValueAt(i, 0).toString().equals(tab.table.getValueAt(j, 0).toString())){
			  	           			   repet=true;
			  	           		   msg+="Il existe un ou plusieur articles dupliqués \n";

			  	           	   }}
		  	           	   }
			        //	   System.out.println("eeeeee"+tab.table.getRowCount());
			        	   if(tab.table.getRowCount()==0){
			        		//   System.out.println(tab.table.getRowCount());
	              		   msg+="Vous devez tout d'abord ajouter l'article composé";
			        		 }

			        	   else if(vaidCheck()==false){
			        		   msg+="Un Champ dans le tableau est vide \n";
			        	   }

			        	   }


		        	   for(int i=0;i<tab_prod.table.getRowCount();i++){
			             	  // for(int j=0;j<tab.table.getColumnCount();j++){
			             		  String col5= tab_prod.table.getValueAt(i, 5).toString();
			             		  String col0= tab_prod.table.getValueAt(i, 0).toString();
			             		  String col1= tab_prod.table.getValueAt(i, 1).toString();
			             		  String col2= tab_prod.table.getValueAt(i, 2).toString();

			             		  if(isValid(col5)==false){
			             			  msg+="La quantite dans le tableau doit étre un nombre \n";
			             		  }
			             		  if(col0.equals("")||col1.equals("")||col2.equals("")) {
			             			 msg+=" un champ dans la table produit est vide \n";
			             		  }
		        	   }


		        	   if(tab_prod.table.getRowCount()==0){
			        		//   System.out.println(tab.table.getRowCount());
	              		   msg+="l'article doit étre associé au moins à un produit ";
			        		 }

		        	   for(int i=0;i<tab_prod.table.getRowCount();i++){
		  	           	   for(int j=i+1;j<tab_prod.table.getRowCount();j++){
		  	           		   if((tab_prod.table.getValueAt(i, 2).toString().trim().equals(tab_prod.table.getValueAt(j, 2).toString().trim()))){
		  	           			   repet=true;
		  	           		   msg+="Il existe un ou plusieur modèle dupliqués \n";

		  	           	   }}
	  	           	   }


		                           if(msg.equals("")){

		          String entr;String code_art;
		          String part_ref1="";
		          String part_codegener1="";
		          String part_famille1="";
		          if(ref_comb.getSelectedIndex()!=0){
		          String[] part_ref = ref_comb.getSelectedItem().toString().split(" ");
           		  part_ref1 = part_ref[0]; // 004
		          }


		          if(codegener_comb.getSelectedIndex()!=0){
           		  String[] part_codegener = codegener_comb.getSelectedItem().toString().split(" ");
           		  part_codegener1 = part_codegener[0]; // 004
		          }

		          if(famille_comb.getSelectedIndex()!=0){
           	      String[] part_famille = famille_comb.getSelectedItem().toString().split(" ");
         		   part_famille1 = part_famille[0]; // 004
		          }



           		  if (enie.isSelected()){
           			  entr="enie";
           		      code_art=codeprod.getText();}
          	 		else{ entr="autre";
          	 		      code_art=codeprod1.getText();  }

		        	              art.setupdate(designation.getText(),entr,
		        			      soft.getText(), ingenieur.getText(),
		        			      datelancement.getEditor().getText(),
		        				  description.getText(), part_ref1, part_codegener1,
		        				   code_art, part_famille1,
		        				   Integer.parseInt(coeff_jtext.getText()),par_ind,model_jtext.getText());

		        	    String[] part_art = article_comb.getSelectedItem().toString().split(" ");
		           		String arti = part_art[0]; // 004

		           		art.delete_nomoclature(arti);

		        	    for(int i=0;i<tab.table.getRowCount();i++){
		             		  String col2= tab.table.getValueAt(i, 1).toString();
		             		  int col2_i=Integer.parseInt(col2);
		             		  String[] partsa=tab.table.getValueAt(i, 0).toString().split(" ");
		             		  String parta1 = partsa[0]; // 004
		         	          art.update_nomoclature(code_art,parta1,col2_i);
		             		  }

		        	    art.delete_produit(arti);
		        	    for(int i=0;i<tab_prod.table.getRowCount();i++){

		        	    	 String col0= tab_prod.table.getValueAt(i, 0).toString();
		             		 String col2= tab_prod.table.getValueAt(i, 2).toString();
		             		String col3= tab_prod.table.getValueAt(i, 3).toString();
		             		String col4= tab_prod.table.getValueAt(i, 4).toString();
		             		String col5= tab_prod.table.getValueAt(i, 5).toString();

		             		 float col4_i=Float.parseFloat(col4);
		             		  int col5_i=Integer.parseInt(col5);
		             		  String[] fourniss =tab_prod.table.getValueAt(i, 1).toString().split(" ");
		             		  String code_four = fourniss[0]; // 004
		         	          art.ajouter_prod_fourniss(code_art,col0,code_four,col2,col4_i,col5_i,col3);

//		             		  String col2= tab_prod.table.getValueAt(i, 1).toString();
//		             		  int col2_i=Integer.parseInt(col2);
//		             		  String[] partsa=tab_prod.table.getValueAt(i, 0).toString().split(" ");
//		             		  String parta1 = partsa[0]; // 004
//
//		         	          art.upd6555ate_nomoclature(code_art,parta1,col2_i);
		             		  }

		        	   remplir_art(fabr);
		        	   remplir_art_table();

		        	   remove_ligne_table(tab);
		                 remove_ligne_table(tab_prod);

		               designation.setText("");
		              // impl.setVisible(false);
		                           }
		                  else{JOptionPane.showMessageDialog(null,msg);}

	            }});

		 but_sauv.addActionListener(
	             new ActionListener() {
	             public void actionPerformed(ActionEvent e) {
	            	 LineBorder border = new LineBorder ( Color.white, 1, true );
	            	 TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
	            	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
	            	 pan_form.setBorder(titl2);
	            	 but_sauv.setVisible(false);
	                 but_modif.setVisible(false);
	                 but_sup.setVisible(false);
	                 retour.setVisible(true);
	                 //impl.setVisible(false);
	                 valid_supp.setVisible(false);
	                 valid_modif.setVisible(false);
	                 valid_ajou.setVisible(true);
	                 article_comb.disable();
	                 article_comb.setEditable(false);
	                 ref_comb.enable();
	                 ref_comb.setEditable(true);

	                 famille_comb.enable();
	                 famille_comb.setEditable(true);
	                 codegener_comb.enable();
	                 codegener_comb.setEditable(true);
	                 codeprod.enable();
	                 codeprod1.enable();
	                 designation.enable();
	                 fourniss_comb.enable();
	                 fourniss_comb.setEditable(true);

	                 coeff_jtext.enable();

	                 datelancement.getEditor().enable();
	                 soft.enable();
	                 ingenieur.enable();
	                 description.enable();
	                 indice.enable();
	             }});

		 valid_ajou.addActionListener(
		           new ActionListener() {
		           public void actionPerformed(ActionEvent e) {
		        	   if(tab.table.getCellEditor()!=null){
		        		   tab.table.getCellEditor().stopCellEditing();
		        		   }
		        	//   System.out.println(designation.getText());
		        	   art.article(designation.getText());
		        	   msg=""; String part2 = null;

		        	   if(famille_comb.getSelectedIndex()!=0&&famille_comb.getSelectedIndex()!=famille_comb.getItemCount()-1){
		        		    //part2 = famille_comb.getSelectedItem().toString().substring(4);
		        		    String[] famille_part = famille_comb.getSelectedItem().toString().split(" ");
		            	    String  famil_part = famille_part[0]; // 004
		            	    part2 = famille_comb.getSelectedItem().toString().replace(famil_part+" ", "");
		            	//    System.out.println("rrrrrr"+part2+"fffffff");
	            	     // part2 = parts[1];
	            		 //System.out.println("gggggg"+ part2);
		        	   }
			 	    	//System.out.println(isValid(pu_jtext.getText()));

		 	    	 if (enie.isSelected()&&(codegener_comb.getSelectedIndex()==0||ref_comb.getSelectedIndex()==0)){
		                	 if(codegener_comb.getSelectedIndex()==0||codegener_comb.getSelectedIndex()==-1){msg+="Veuillez selectionner un code générique \n";}

		                	 else if(ref_comb.getSelectedIndex()==0||ref_comb.getSelectedIndex()==-1){msg+="Veuillez selectionner une référence \n";}
	             		  }
		                 else  if (autre.isSelected()&&codeprod1.getText().equals("")){
		                	 if(codeprod1.getText().equals("")){msg+="Veuillez  saisir le code d'article \n";}
	             		  }

		                 else  if (enie.isSelected()&&codeprod.getText().equals("")){
		                	 if(codeprod.getText().equals("")){msg+="Veuillez  saisir le code d'article \n";}
	             		  }
		                 else if(designation.getText().equals("")){ msg+="Veuillez saisir la designation de l'article\n";}

		          // else if(numcontrat.getText().equals("")){msg+="Veuillez saisir le Numero de contrat \n";}
		           else if(art.ex==true){msg+="Cette article exist déja \n";}
		         //  else if(modeltv.getText().equals("")){msg+="Veuillez saisir le Model \n";}
		         //  else if(ingenieur.getText().equals("")){msg+="Veuillez saisir l'ingenieur\n";}
		         //  else if(coeff_jtext.getText().equals("")){msg+="Veuillez saisir le coefficient\n";}
		        //   else if(pu_jtext.getText().equals("")){msg+="Veuillez saisir le prix unitaire \n";}
		        //   else if(!isValid(pu_jtext.getText())){msg+="Le prix unitaire doit ?tre un nombre\n";}
		        //   else if(!isValid(coeff_jtext.getText())){msg+="Le coefficient doit etre un nombre \n";}


		         //  else if(qte.getText().equals("")){msg+="Veuillez saisir la quantite \n";}
		       //    else if(!isValid(qte.getText())){msg+="La quantite doit etre un nombre \n";}

		           else if(famille_comb.getSelectedIndex()==0||famille_comb.getSelectedIndex()==-1){msg+="Veuillez selectionner un type article \n";}

		           else if(coeff_jtext.getText().equals("")){msg+="Veuillez saisir le coefficient \n";}
		           else if(part2.equals("Ensemble")||part2.equals("Sous Ensemble")){

			        	   for(int i=0;i<tab.table.getRowCount();i++){
				             	  // for(int j=0;j<tab.table.getColumnCount();j++){
				             		  String col2= tab.table.getValueAt(i, 1).toString();
				             		  if(isValid(col2)==false){
				             			  msg+="La quantite dans le tableau doit étre un nombre \n";

				             		  }}

			        	   for(int i=0;i<tab.table.getRowCount();i++){
			  	           	   for(int j=i+1;j<tab.table.getRowCount();j++){
			  	           		   if(tab.table.getValueAt(i, 0).toString().equals(tab.table.getValueAt(j, 0).toString())){
			  	           			   repet=true;
			  	           		   msg+="Il existe un ou plusieur articles dupliqués \n";

			  	           	   }}
		  	           	   }
			        //	   System.out.println("eeeeee"+tab.table.getRowCount());
			        	   if(tab.table.getRowCount()==0){
			        		//   System.out.println(tab.table.getRowCount());
	              		   msg+="Vous devez tout d'abord ajouter l'article composé";
			        		 }

			        	   else if(vaidCheck()==false){
			        		   msg+="Un Champ dans le tableau est vide \n";

			        	   }

			        	   }

		        	   for(int i=0;i<tab_prod.table.getRowCount();i++){
			             	  // for(int j=0;j<tab.table.getColumnCount();j++){
			             		  String col5= tab_prod.table.getValueAt(i, 5).toString();
			             		  String col0= tab_prod.table.getValueAt(i, 0).toString();
			             		  String col1= tab_prod.table.getValueAt(i, 1).toString();
			             		  String col2= tab_prod.table.getValueAt(i, 2).toString();

			             		  if(isValid(col5)==false){
			             			  msg+="La quantite dans le tableau doit étre un nombre \n";
			             		  }
			             		  if(col0.equals("")||col1.equals("")||col2.equals("")) {
			             			 msg+=" un champ dans la table produit est vide \n";
			             		  }
		        	   }


		        	   if(tab_prod.table.getRowCount()==0){
			        		//   System.out.println(tab.table.getRowCount());
	              		   msg+="l'article doit étre associé au moins à un produit ";
			        		 }

		        	   for(int i=0;i<tab_prod.table.getRowCount();i++){
		  	           	   for(int j=i+1;j<tab_prod.table.getRowCount();j++){
		  	           		   if((tab_prod.table.getValueAt(i, 2).toString().trim().equals(tab_prod.table.getValueAt(j, 2).toString().trim()))){
		  	           			   repet=true;
		  	           		   msg+="Il existe un ou plusieur modèle dupliqués \n";

		  	           	   }}
	  	           	   }

		     if(msg.equals("")){
		        	  String entr = "";
		        	  String code_art="";
		        	  String part_ref1="";
		        	  String part_famille1="";
		        	  String part_codegener1="";
		        	  if( ref_comb.getSelectedIndex()!=0){
		        	  String[] part_ref = ref_comb.getSelectedItem().toString().split(" ");
             		  part_ref1 = part_ref[0]; // 004
		        	  }
		        	  else part_ref1="";

		        	  if( famille_comb.getSelectedIndex()!=0){
             		  String[] part_famille = famille_comb.getSelectedItem().toString().split(" ");
             		   part_famille1 = part_famille[0]; // 004
		        	  }
		        	  else part_famille1="";


		        	  if( codegener_comb.getSelectedIndex()!=0){
             		  String[] part_codegener = codegener_comb.getSelectedItem().toString().split(" ");
            		  part_codegener1 = part_codegener[0]; // 004
		        	  }
		        	  else part_codegener1="";


             		  if (enie.isSelected()){ entr="enie";
             		  code_art=codeprod.getText();}
            	 		else{ entr="autre";   code_art=codeprod1.getText();  }


		        	   art.setinsert(designation.getText(),entr,soft.getText(), ingenieur.getText(),
		        			   datelancement.getEditor().getText(),
		        				   description.getText(), part_ref1, part_codegener1, code_art, part_famille1,
		        				Integer.parseInt(coeff_jtext.getText()),
		        				   par_ind,model_jtext.getText());


		        	   for(int i=0;i<tab.table.getRowCount();i++){
		             		  String col2= tab.table.getValueAt(i, 1).toString();
		             		  int col2_i=Integer.parseInt(col2);
		             		  String[] partsa =tab.table.getValueAt(i, 0).toString().split(" ");
		             		  String parta1 = partsa[0]; // 004
		         	          art.ajouter_nomoclature(code_art,parta1,col2_i);
		             		  }


		        	   for(int i=0;i<tab_prod.table.getRowCount();i++){
		             		  String col0= tab_prod.table.getValueAt(i, 0).toString();
		             		 String col2= tab_prod.table.getValueAt(i, 2).toString();
		             		String col3= tab_prod.table.getValueAt(i, 3).toString();
		             		String col4= tab_prod.table.getValueAt(i, 4).toString();
		             		String col5= tab_prod.table.getValueAt(i, 5).toString();

		             		 float col4_i=Float.parseFloat(col4);
		             		  int col5_i=Integer.parseInt(col5);
		             		  String[] fourniss =tab_prod.table.getValueAt(i, 1).toString().split(" ");
		             		  String code_four = fourniss[0]; // 004
		         	          art.ajouter_prod_fourniss(code_art,col0,code_four,col2,col4_i,col5_i,col3);
		             		  }

	        	        remplir_art(fabr);
		        	   remplir_art_table();
		        	   remove_ligne_table(tab);
		                 remove_ligne_table(tab_prod);
		                 designation.setText("");
                        article_comb.enable();
                      //  impl.setVisible(false);
		                           }
		              else{JOptionPane.showMessageDialog(null,msg);}



		           }});


		 but_ajou.addActionListener(
		            new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            //	System.out.println("rrrrrrrrrrrrrrrrrr");
		         	 tab.ajouter();
		  	      	TableColumn gradeColumn = tab.table.getColumnModel().getColumn(0);

		         	gradeColumn.setCellEditor(new DefaultCellEditor(art_comb));

		         	 int k=tab.table.getRowCount();
			      	tab.table.setValueAt("", k-1,0);
			         tab.table.setValueAt("", k-1,1);
		            }});

		 but_supprim.addActionListener(
	             new ActionListener() {
	                 public void actionPerformed(ActionEvent e) {

	                	 if(tab.table.getSelectedRow()==-1){
	                		 JOptionPane.showMessageDialog(null,
	     	 	      				"Vous devez d'abord sélectionner l'article  que vous voulez supprimer", "",
	     	 	  		        JOptionPane.INFORMATION_MESSAGE);
	                	 }
	                	 else{
	     				((DefaultTableModel) tab.table.getModel()).removeRow(tab.table.getSelectedRow());}

	                 }});


		 but_ajou_prod.addActionListener(
		            new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            //	System.out.println("rrrrrrrrrrrrrrrrrr");
		         	tab_prod.ajouter(fourniss_comb);
		  	      	TableColumn gradeColumn = tab_prod.table.getColumnModel().getColumn(1);
		         	gradeColumn.setCellEditor(new DefaultCellEditor(fourniss_comb));

		         	TableColumn gradeColumn1 = tab_prod.table.getColumnModel().getColumn(2);
		        	gradeColumn1.setCellEditor(new DefaultCellEditor(model_comb));
		         	 int k=tab_prod.table.getRowCount();
			      	//tab.table.setValueAt("---Selectionner un article-----", k-1,0);
		         	tab_prod.table.setValueAt("", k-1,0);
		         	tab_prod.table.setValueAt("", k-1,1);
		        	tab_prod.table.setValueAt("", k-1,2);
		        	tab_prod.table.setValueAt("", k-1,3);
		        	tab_prod.table.setValueAt("0", k-1,4);
		        	tab_prod.table.setValueAt("0", k-1,5);
		            }});

		 but_supprim_prod.addActionListener(
	             new ActionListener() {
	                 public void actionPerformed(ActionEvent e) {

	                	 if(tab_prod.table.getSelectedRow()==-1){
	                		 JOptionPane.showMessageDialog(null,
              "Vous devez d'abord sélectionner le produit fournisseur  que vous voulez supprimer", "",
	     	 	  		        JOptionPane.INFORMATION_MESSAGE);
	                	 }
	                	 else{
	     				((DefaultTableModel) tab_prod.table.getModel()).removeRow(tab_prod.table.getSelectedRow());}

	                 }});

		 enie.addActionListener(
		           new ActionListener() {
		           public void actionPerformed(ActionEvent e) {
		        	  fabr="enie";

		        	  remplir_art(fabr);
		        	   remplir_art_table();

				         pan_codeprogener.setVisible(true);
				         pan_codeprogener1.setVisible(false);
		           }		           });

		 autre.addActionListener(
		           new ActionListener() {
		           public void actionPerformed(ActionEvent e) {
		        	   fabr="autre";
		        	   remplir_art(fabr);
		        	   remplir_art_table();

		    		   pan_codeprogener1.setVisible(true);
				       pan_codeprogener.setVisible(false);
		           }});



		ref_comb.addActionListener(
		           new ActionListener() {
		        	   public void actionPerformed(ActionEvent e) {
			           	  if(ref_comb.getSelectedIndex()!=0&&codegener_comb.getSelectedIndex()!=0){
			           	  // System.out.println("hhhhhh");
//		        		   String[] part_ref = ref_comb.getSelectedItem().toString().split(" ");
//		             	   String part_ref1 = part_ref[0]; // 004
//		             	   String[] part_codegener = codegener_comb.getSelectedItem().toString().split(" ");
//		            	   String part_codegener1 = part_codegener[0]; // 004
		            		//codeprod.setText(part_ref1+part_codegener1+art.afficher_code_produit(part_codegener1,part_ref1));
		            		//System.out.println(part_ref1+part_codegener1+art.afficher_code_produit(part_codegener1,part_ref1));

			           	   }
		        	   }});

		codegener_comb.addActionListener(
		           new ActionListener() {
		        	   public void actionPerformed(ActionEvent e) {
		        		   if(ref_comb.getSelectedIndex()!=0&&codegener_comb.getSelectedIndex()!=0){
			        		//   System.out.println("hhhhhh");
//			        		   String[] part_ref = ref_comb.getSelectedItem().toString().split(" ");
//			             	   String part_ref1 = part_ref[0]; // 004
//			             	   String[] part_codegener = codegener_comb.getSelectedItem().toString().split(" ");
//			            	   String part_codegener1 = part_codegener[0]; // 004
			            		//codeprod.setText(part_ref1+part_codegener1+art.afficher_code_produit(part_codegener1,part_ref1));
		        		   }}});


		 article_comb.addActionListener(
		           new ActionListener() {
		           public void actionPerformed(ActionEvent e) {
		        	  LineBorder border = new LineBorder ( Color.white, 1, true );
		           	  TitledBorder titl2 = new TitledBorder ( border, "Edition Article", TitledBorder.DEFAULT_POSITION,
		               TitledBorder.DEFAULT_POSITION, police2, Color.white);
		                 pan_form.setBorder(titl2);
		                 remove_ligne_table(tab);
		                 remove_ligne_table(tab_prod);
		           	   if(article_comb.getSelectedIndex()==0){
		           		 jTabbedPane.remove(pan_nomo);
		           		 ref_comb.enable();
		           	     ref_comb.setEditable(true);
		                 codegener_comb.enable();
		                 codegener_comb.setEditable(true);
		                 article_comb.enable();
		                 article_comb.setEditable(true);
		           		 article_comb.setSelectedIndex(0);
		                 ref_comb.setSelectedIndex(0);
		                 famille_comb.setSelectedIndex(0);
		                 codegener_comb.setSelectedIndex(0);
		                 fourniss_comb.setSelectedIndex(0);
		                 indice.setSelectedIndex(0);
		                 coeff_jtext.setText("0");
		                 codeprod1.setText("");
			             datelancement.setDate(Calendar.getInstance().getTime());
		                 soft.setText("");
		                 model_jtext.setText("");
		                 ingenieur.setText("");
		                 description.setText("");
		                 codeprod.setText("");
		                 if(valid_modif.isVisible()==false||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
			        	       but_sauv.setVisible(true);
			        	       valid_ajou.setVisible(false);
			                   valid_supp.setVisible(false);
			                   valid_modif.setVisible(false);
			                   retour.setVisible(false);
			                //   impl.setVisible(false);

			        	     }
		                //  impl.setVisible(false);
			        	   but_modif.setVisible(false);
			               but_sup.setVisible(false);
		           	   }
		           	   else {

		           		 if(article_comb.getItemCount()>1){
		           			 if(valid_modif.isVisible()==false ||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
				                  but_modif.setVisible(true);
				                  but_sup.setVisible(true);
				               //   impl.setVisible(false);
				                  but_sauv.setVisible(false);
				                  retour.setVisible(false);
				                  valid_ajou.setVisible(false);
				                  valid_supp.setVisible(false);
				                  valid_modif.setVisible(false);
				                  }
				              else{
				            	  //impl.setVisible(false);
				             	 valid_ajou.setVisible(false);
				             	 but_modif.setVisible(true);
				                 but_sup.setVisible(true);
				                 but_sauv.setVisible(false);
				                 retour.setVisible(false);

				              }
		           			 ref_comb.disable();
		           			 ref_comb.setEditable(false);
			                 codegener_comb.disable();
			                 codegener_comb.setEditable(false);
			                 article_comb.enable();
			                 article_comb.setEditable(true);

		            		  String[] parts = article_comb.getSelectedItem().toString().split(" ");
		            		  String part1 = parts[0]; // 004
		            		  art.selection(part1);
		            		// System.out.println(part1);
		            		  list_art_tab=art.select_nomo(part1);
		            		   for(i=0;i<list_art_tab.size();i=i+3){
		            			tab.ajouter();
		              		 	TableColumn gradeColumn = tab.table.getColumnModel().getColumn(0);
		                    	// tab.table.setValueAt( art_comb, i-1,0);
		                    	gradeColumn.setCellEditor(new DefaultCellEditor(art_comb));



		            		   }
		              			int j=1;
		              			int l = 0; i=1;
		              			while(l<list_art_tab.size()){

		              			 while(l<j*3){
		              				 tab.getTable().setValueAt(list_art_tab.get(l)+" "+list_art_tab.get(l+1), j-1,0);
		              				 tab.getTable().setValueAt(list_art_tab.get(l+2),j-1,1);

		              				 i=i+1; l=l+3;
		              			 }

		              			 i=1;
		              			j=j+1;
		              			}



		              			list_art_tab_prod=art.select_produi_art(part1);
		              			System.out.println("ee4ee4"+list_art_tab_prod);
			            		   for(a=0;a<list_art_tab_prod.size();a=a+7){
			            			tab_prod.ajouter();
			              		 	TableColumn gradeColumn = tab_prod.table.getColumnModel().getColumn(1);
			                    	//  tab.table.setValueAt( zone_comb, i-1,0);
			                        gradeColumn.setCellEditor(new DefaultCellEditor(fourniss_comb));
			                        TableColumn gradeColumn1 = tab_prod.table.getColumnModel().getColumn(2);
			                    	// tab.table.setValueAt( art_comb, i-1,0);
			                    	gradeColumn1.setCellEditor(new DefaultCellEditor(model_comb));
			            		   }
			              			int z=1;
			              			int e1 = 0; a=1;
			              			while(e1<list_art_tab_prod.size()){

			              			 while(e1<z*7){
			              				tab_prod.getTable().setValueAt(list_art_tab_prod.get(e1), z-1,0);
			              				tab_prod.getTable().setValueAt(list_art_tab_prod.get(e1+1)+" "+list_art_tab_prod.get(e1+2),z-1,1);
			              				tab_prod.getTable().setValueAt(list_art_tab_prod.get(e1+3), z-1,2);
			              				tab_prod.getTable().setValueAt(list_art_tab_prod.get(e1+4), z-1,3);
			              				tab_prod.getTable().setValueAt(list_art_tab_prod.get(e1+5), z-1,4);
			              				tab_prod.getTable().setValueAt(list_art_tab_prod.get(e1+6), z-1,5);
			              				 a=a+1; e1=e1+7;
			              			 }

			              			 a=1;
			              			z=z+1;
			              			}

		            		 //   System.out.println(art.code_ref+" "+art.designation_ref);
		            		    ref_comb.setSelectedItem(art.code_ref+" "+art.designation_ref);
				           		codegener_comb.setSelectedItem(art.code_gener+" "+art.designation_gener);
				           		famille_comb.setSelectedItem(art.code_famille+" "+art.designation_famille);

				           	 if(art.entreprise.equals("enie")){
			           	      enie.setSelected(true);
			           		  pan_codeprogener.setVisible(true);
						      pan_codeprogener1.setVisible(false);
			           		 }else{ autre.setSelected(false);
			           	   pan_codeprogener1.setVisible(true);
					       pan_codeprogener.setVisible(false);}


	            		         coeff_jtext.setText(""+art.coeff);
	            		         model_jtext.setText(art.model);

		            		     codeprod1.setText(art.code_article);
				                 designation.setText(art.designation);

				                 datelancement.getEditor().setText(art.date_deplacement);
				                 soft.setText(art.software);
				                 ingenieur.setText(art.ingenieur);
				                 description.setText(art.description);
				                 codeprod.setText(art.code_article);
				                 indice.setSelectedIndex(art.indice);
				               //  impl.setVisible(true);
		           		 }
		        	   }
		        	  // }
		           }});

		 codeprod.addActionListener(
		           new ActionListener() {
		           public void actionPerformed(ActionEvent e) {
		        	   LineBorder border = new LineBorder ( Color.white, 1, true );
		           	  TitledBorder titl2 = new TitledBorder ( border, "Edition Article", TitledBorder.DEFAULT_POSITION,
		                   TitledBorder.DEFAULT_POSITION, police2, Color.white);
		                 pan_form.setBorder(titl2);
		                 remove_ligne_table(tab);
		                 remove_ligne_table(tab_prod);
		           	  String des=codeprod.getText();
		           		 art.selection(des);
		           		 System.out.println(codeprod.getText());

		           		 System.out.println(codeprod.getText()+" "+art.designation);
		           	   if(codeprod.getText().equals("")||art.exist_des==true){
		           		if(valid_modif.isVisible()==false||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
			        	       but_sauv.setVisible(true);
			        	       valid_ajou.setVisible(false);
			                   valid_supp.setVisible(false);
			                   valid_modif.setVisible(false);
			                   retour.setVisible(false);
			        	     }
			        	 but_modif.setVisible(false);
			             but_sup.setVisible(false);
		           		 article_comb.setSelectedIndex(0);
		                 ref_comb.setSelectedIndex(0);
		                 ref_comb.enable();
		                 ref_comb.setEditable(true);
		                 codegener_comb.enable();
		                 codegener_comb.setEditable(true);
		                 article_comb.enable();
		                 article_comb.setEditable(true);
		                 famille_comb.setSelectedIndex(0);
		                 codegener_comb.setSelectedIndex(0);
		                 fourniss_comb.setSelectedIndex(0);
		                 indice.setSelectedIndex(0);

		                 coeff_jtext.setText("0");
		                 codeprod1.setText("");

			             datelancement.setDate(Calendar.getInstance().getTime());
		                 soft.setText("");
		                 ingenieur.setText("");
		                 description.setText("");
		                 codeprod.setText("");
		                 jTabbedPane.remove(pan_nomo);

				    	 JOptionPane.showMessageDialog(null,"L'article "+designation.getText()+" n'exist pas");
		           	   }
		           	   else {
		           		 if(valid_modif.isVisible()==false ||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
			                  but_modif.setVisible(true);
			                  but_sup.setVisible(true);
			                  but_sauv.setVisible(false);
			                  retour.setVisible(false);
			                  valid_ajou.setVisible(false);
			                  valid_supp.setVisible(false);
			                  valid_modif.setVisible(false);
			                  //impl.setVisible(false);
			                  }
			              else{
			             	 valid_ajou.setVisible(false);
			             	 but_modif.setVisible(true);
			                 but_sup.setVisible(true);
			                 but_sauv.setVisible(false);
			                 retour.setVisible(false);
			                 //impl.setVisible(false);
			              }
		           		 ref_comb.disable();
		           		 ref_comb.setEditable(false);
		                 codegener_comb.disable();
		                 codegener_comb.setEditable(false);
		                 article_comb.enable();
		                 article_comb.setEditable(true);
		           		 article_comb.setSelectedItem(art.code_article+" "+art.designation);
		            		//  System.out.println(art.code_ref+" "+art.designation_ref);
		            		   ref_comb.setSelectedItem(art.code_ref+" "+art.designation_ref);
				           		codegener_comb.setSelectedItem(art.code_gener+" "+art.designation_gener);
				           		famille_comb.setSelectedItem(art.code_famille+" "+art.designation_famille);
				           	 if(art.entreprise.equals("enie")){
			           	      enie.setSelected(true);
			           		  pan_codeprogener.setVisible(true);
						      pan_codeprogener1.setVisible(false);
			           		 }else{ autre.setSelected(false);
			           	   pan_codeprogener1.setVisible(true);
					       pan_codeprogener.setVisible(false);}


	            		         coeff_jtext.setText(""+art.coeff);

		            		     codeprod1.setText(art.code_article);

				                 datelancement.getEditor().setText(art.date_deplacement);
				                 soft.setText(art.software);
				                 ingenieur.setText(art.ingenieur);
				                 description.setText(art.description);
				                 codeprod.setText(art.code_article);

				                 indice.setSelectedIndex(art.indice);
				                 //impl.setVisible(true);
		           		 }
		        	   }
		        	  // }
		           });
		 designation.addActionListener(
		           new ActionListener() {
		           public void actionPerformed(ActionEvent e) {
		        	   LineBorder border = new LineBorder ( Color.white, 1, true );
		           	  TitledBorder titl2 = new TitledBorder ( border, "Edition Article", TitledBorder.DEFAULT_POSITION,
		                   TitledBorder.DEFAULT_POSITION, police2, Color.white);
		                 pan_form.setBorder(titl2);
		                 remove_ligne_table(tab);
		                 remove_ligne_table(tab_prod);
		           	  String des=designation.getText();
		           		 art.selection(des);
		           	   if(designation.getText().equals("")||art.exist_des==true){
		           		if(valid_modif.isVisible()==false||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
			        	       but_sauv.setVisible(true);
			        	       valid_ajou.setVisible(false);
			                   valid_supp.setVisible(false);
			                   valid_modif.setVisible(false);
			                   retour.setVisible(false);
			        	     }
			        	 but_modif.setVisible(false);
			             but_sup.setVisible(false);
		           		 article_comb.setSelectedIndex(0);
		                 ref_comb.setSelectedIndex(0);
		                 ref_comb.enable();
		                 ref_comb.setEditable(true);
		                 codegener_comb.enable();
		                 codegener_comb.setEditable(true);
		                 article_comb.enable();
		                 article_comb.setEditable(true);
		                 famille_comb.setSelectedIndex(0);
		                 codegener_comb.setSelectedIndex(0);
		                 fourniss_comb.setSelectedIndex(0);
		                 indice.setSelectedIndex(0);

		                 coeff_jtext.setText("0");
		                 codeprod1.setText("");

			             datelancement.setDate(Calendar.getInstance().getTime());
		                 soft.setText("");
		                 ingenieur.setText("");
		                 description.setText("");
		                 codeprod.setText("");
		                 jTabbedPane.remove(pan_nomo);

				    	 JOptionPane.showMessageDialog(null,"L'article "+designation.getText()+" n'exist pas");
		           	   }
		           	   else {
		           		 if(valid_modif.isVisible()==false ||valid_ajou.isVisible()==false||valid_supp.isVisible()==false){
			                  but_modif.setVisible(true);
			                  but_sup.setVisible(true);
			                  but_sauv.setVisible(false);
			                  retour.setVisible(false);
			                  valid_ajou.setVisible(false);
			                  valid_supp.setVisible(false);
			                  valid_modif.setVisible(false);
			                  //impl.setVisible(false);
			                  }
			              else{
			             	 valid_ajou.setVisible(false);
			             	 but_modif.setVisible(true);
			                 but_sup.setVisible(true);
			                 but_sauv.setVisible(false);
			                 retour.setVisible(false);
			                 //impl.setVisible(false);
			              }
		           		 ref_comb.disable();
		           		 ref_comb.setEditable(false);
		                 codegener_comb.disable();
		                 codegener_comb.setEditable(false);
		                 article_comb.enable();
		                 article_comb.setEditable(true);
		           		 article_comb.setSelectedItem(art.code_article+" "+art.designation);
		            		//  System.out.println(art.code_ref+" "+art.designation_ref);
		            		   ref_comb.setSelectedItem(art.code_ref+" "+art.designation_ref);
				           		codegener_comb.setSelectedItem(art.code_gener+" "+art.designation_gener);
				           		famille_comb.setSelectedItem(art.code_famille+" "+art.designation_famille);
				           	 if(art.entreprise.equals("enie")){
			           	      enie.setSelected(true);
			           		  pan_codeprogener.setVisible(true);
						      pan_codeprogener1.setVisible(false);
			           		 }else{ autre.setSelected(false);
			           	   pan_codeprogener1.setVisible(true);
					       pan_codeprogener.setVisible(false);}


	            		         coeff_jtext.setText(""+art.coeff);

		            		     codeprod1.setText(art.code_article);

				                 datelancement.getEditor().setText(art.date_deplacement);
				                 soft.setText(art.software);
				                 ingenieur.setText(art.ingenieur);
				                 description.setText(art.description);
				                 codeprod.setText(art.code_article);

				                 indice.setSelectedIndex(art.indice);
				                 //impl.setVisible(true);
		           		 }
		        	   }
		        	  // }
		           });

		 try{
		   		UIManager.setLookAndFeel(laf);
		   		SwingUtilities.updateComponentTreeUI(this);
		   	//	ComboBoxUI.setLookAndFeel(laf);
		   	}
		     catch(Exception e1){
		   		e1.printStackTrace();
		   	}

		     JScrollPane scrollPane = new JScrollPane(pan_form);
	         //scrollPane.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


	        enie.isSelected();


		lab_combo.setForeground(Color.white);
	        entrep.setForeground(Color.white);
	        ref_lab_combo.setForeground(Color.white);
	        codegener_lab.setForeground(Color.white);
	        codeprod_lab.setForeground(Color.white);
	        codeprod1_lab.setForeground(Color.white);
	        famille_lab.setForeground(Color.white);
	        designation_lab.setForeground(Color.white);

	        coeff_lab.setForeground(Color.white);

	        datelancement_lab.setForeground(Color.white);
	        soft_lab.setForeground(Color.white);
	        ingenieur_lab.setForeground(Color.white);
	        description_lab.setForeground(Color.white);
	        enie.setForeground(Color.white);
	        autre.setForeground(Color.white);
	        indice_lab.setForeground(Color.white);
			indice_lab.setFont(police2);



			model_lab.setForeground(Color.white);
			model_lab.setFont(police2);

		 lab_combo.setFont(police2);
		 entrep.setFont(police2);
		 ref_lab_combo.setFont(police2);
		 codegener_lab.setFont(police2);
		 codeprod_lab.setFont(police2);
		 codeprod1_lab.setFont(police2);
		 famille_lab.setFont(police2);
		 designation_lab.setFont(police2);

		 coeff_lab.setFont(police2);

		 datelancement_lab.setFont(police2);
		 soft_lab.setFont(police2);
		 ingenieur_lab.setFont(police2);
		 description_lab.setFont(police2);


		 TitledBorder titl1 = new TitledBorder ( border, "Information Article", TitledBorder.DEFAULT_POSITION,
    	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
    	 pan_artgener .setBorder(titl1);

    	 TitledBorder titl3 = new TitledBorder ( border, "Codification", TitledBorder.DEFAULT_POSITION,
    	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
    	 pan_codeprogener.setBorder(titl3);

    	 TitledBorder titl5 = new TitledBorder ( border, "Codification", TitledBorder.DEFAULT_POSITION,
    	    	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
    	    	 pan_codeprogener1.setBorder(titl5);

    	    	 TitledBorder titl6 = new TitledBorder ( border, "Info general", TitledBorder.DEFAULT_POSITION,
    	    	    	 TitledBorder.DEFAULT_POSITION, police2, Color.white);
    	    	    	 pan_infogener.setBorder(titl6);




    	    		pan_nomo.add(pan_tab);
    	    	   	pan_tab.add(p);
    	    	   	pan_tab.add(pan_but_poste);
    	    	   	pan_but_poste.add(but_ajou);
    	    	   	pan_but_poste.add(but_supprim);

    	    	   	pan_prod.add(pan_tab_prod);
    	    	   	pan_tab_prod.add(p_prod);
    	    	   	pan_tab_prod.add(pan_but_prod);
    	    	   	pan_but_prod.add(but_ajou_prod);
    	    	   	pan_but_prod.add(but_supprim_prod);

    	    	 jTabbedPane.add("Edition Article",panel);
    	    	 jTabbedPane.add("Produit fournisseur",pan_prod);

    	    	 famille_comb.addActionListener(
    			           new ActionListener() {
    			           public void actionPerformed(ActionEvent e) {

    			        	   if(famille_comb.getItemCount()>=1||famille_comb.getSelectedIndex()!=(famille_comb.getItemCount()-1)){
    			        		remplir_art_table();


    			        		 if(famille_comb.getSelectedIndex()!=0&&famille_comb.getSelectedIndex()!=famille_comb.getItemCount()-1){
    			        	 //  String part2 = famille_comb.getSelectedItem().toString().substring(4);

    			        	   String[] famille_part = famille_comb.getSelectedItem().toString().split(" ");
    		            	    String  famil_part = famille_part[0]; // 004
    		            	    String part2 = famille_comb.getSelectedItem().toString().replace(famil_part+" ", "");
    			        	   // String part2 = parts[1];
    			        	   if(part2.equals("Ensemble")||part2.equals("Sous Ensemble")){
    			        		   jTabbedPane.addTab("Composant", pan_nomo);
    			        	   }
    			        	   else{ jTabbedPane.remove(pan_nomo);}}}}});


//    	    	 impl.addActionListener(new ActionListener() {
//    	 			public void actionPerformed(ActionEvent event) {// dispose();
//    	 				att= new Thread(){
//    	 					public void run(){
//    	 						String[] arti = article_comb.getSelectedItem().toString().split(" ");
//    	 						String code_art = arti[0];
//    	 					     System.out.println(code_art);
//    	 						String code = arti[0].replaceAll("\\/","_"); // 004
//    	 						String parcour ;String model;
//    	    	 				 parcour = "C:\\GCOBAR\\pdf\\BOM\\"+code+"_"+modeltv.getText()+"_BOM"+".pdf";
//    	    	 				 model = "C:\\GCOBAR\\CODE\\hiarche.jrxml";
//    	 						File fichier = new File(parcour);
//        	 					fichier.delete();
//    	 						//int indice=article_comb.getSelectedIndex();
//
//    	                 UIManager.put("nimbusOrange", (new Color(70,130,180)));
//
//    	 				try {
//
//    	 						new BufferedReader(new FileReader( "C:\\GCOBAR\\pdf\\BOM\\"+code+"_"+modeltv.getText()+"_BOM"+".pdf"));
//    	 					try {
//    	 						Desktop.getDesktop().open(new File( "C:\\GCOBAR\\pdf\\BOM\\"+code+"_"+modeltv.getText()+"_BOM"+".pdf"));
//
//    	 					} catch (IOException p) {
//    	 						// TODO Auto-generated catch block
//    	 						p.printStackTrace();
//    	 					}}
//    	 				 catch (FileNotFoundException fnfe) {
//    	 					selectioncomb.imprimer(code_art, bdd, parcour,model);
//    	 					 //controlPanel.remove(progressBar);
//}
//    	 					}};
//
//    	 					att.start();
//
//    	 			}
//    	 		});

		 panel.add(pan_gener);

		 //pan_gener.add(pan_form);
		 pan_gener.add(scrollPane);
	     pan_form.add(pan_form1);
	     pan_form.add(pan_form2);

	     pan_form1.add(pan_artgener);
	     pan_form1.add(pan_codeprogener);
	     pan_form1.add(pan_codeprogener1);
	   //  pan_form1.add(pan_familprodgener);
	     pan_form2.add(pan_infogener);
	     pan_gener.add(pan_buton);


	     bG.add(enie);
	     bG.add(autre);
	     pan_radio_lab.add(entrep);

	     pan_radio.add(pan_radio_lab);
         pan_radio.add(pan_radio_bg);
         pan_radio_bg.add(enie);
	     pan_radio_bg.add(autre);
	     pan_article.add(pan_lab_combo);
		 pan_article.add(pan_article_comb);
		 pan_lab_combo.add(lab_combo);
		 pan_article_comb.add(article_comb);
		 pan_artgener.add(pan_radio);
		 pan_artgener.add(pan_article);



	     pan_refer.add(pan_ref_lab);
		 pan_refer.add(pan_ref_comb);
		 pan_ref_lab.add(ref_lab_combo);
		 pan_ref_comb.add(ref_comb);

		 pan_codegener.add(pan_codegener_lab);
		 pan_codegener.add(pan_codegener_comb);
		 pan_codegener_lab.add(codegener_lab);
		 pan_codegener_comb.add(codegener_comb);



		 pan_codeprod.add(pan_codeprod_lab);
		 pan_codeprod.add(pan_codeprod_jtext);
		 pan_codeprod_lab.add(codeprod_lab);
		 pan_codeprod_jtext.add(codeprod);

		 pan_codeprogener.add(pan_refer);
		 pan_codeprogener.add(pan_codegener);
		 pan_codeprogener.add(pan_codeprod);


		 pan_codeprod1.add(pan_codeprod1_lab);
		 pan_codeprod1.add(pan_codeprod1_jtext);
		 pan_codeprod1_lab.add(codeprod1_lab);
		 pan_codeprod1_jtext.add(codeprod1);
		 pan_codeprogener1.add(pan_codeprod1);

		 pan_famille.add(pan_famille_lab);
		 pan_famille.add(pan_famille_comb);
		 pan_famille_lab.add(famille_lab);
		 pan_famille_comb.add(famille_comb);



		 pan_designation.add(pan_designation_lab);
		 pan_designation.add(pan_designation_jtext);
		 pan_designation_lab.add(designation_lab);
		 pan_designation_jtext.add(designation);
		 pan_artgener.add(pan_famille);
		 pan_artgener.add(pan_designation);








         pan_soft.add(pan_soft_lab);
         pan_soft.add(pan_soft_jtext);
         pan_soft_lab.add(soft_lab);
         pan_soft_jtext.add(soft);

         pan_ingenieur.add(pan_ingenieur_lab);
         pan_ingenieur.add(pan_ingenieur_jtext);
         pan_ingenieur_lab.add(ingenieur_lab);
         pan_ingenieur_jtext.add(ingenieur);

         pan_datelancement.add(pan_datelancement_lab);
         pan_datelancement.add(pan_datelancement_jtext);
         pan_datelancement_lab.add(datelancement_lab);
         pan_datelancement_jtext.add(datelancement);

         pan_coeff.add(pan_coeff_lab);
         pan_coeff.add(pan_coeff_jtext);
         pan_coeff_lab.add(coeff_lab);
         pan_coeff_jtext.add(coeff_jtext);


         pan_description.add(pan_description_lab);
         pan_description.add(pan_description_jtext);
         pan_description_lab.add(description_lab);
         pan_description_jtext.add(description);

         pan_model.add(pan_model_lab);
         pan_model.add(pan_model_jtext);
         pan_model_lab.add(model_lab);
         pan_model_jtext.add(model_jtext);

         pan_indice.add(pan_indice_lab);
 		 pan_indice_lab.add(indice_lab);
 		 pan_indice.add(pan_indice_comb);
 		 pan_indice_comb.add(indice);

 		pan_indice.setOpaque(false);
 		pan_indice_lab.setOpaque(false);
 		pan_indice_comb.setOpaque(false);

 		pan_model.setOpaque(false);
 		pan_model_lab.setOpaque(false);
 		pan_model_jtext.setOpaque(false);

         pan_infogener.add(pan_soft);
         pan_infogener.add(pan_ingenieur);

         pan_infogener.add(pan_coeff);


         pan_infogener.add(pan_indice);
         pan_infogener.add(pan_model);
         pan_infogener.add(pan_description);




        pan_buton.add(but_sauv);
     	pan_buton.add(but_modif);
     	//pan_buton.add(but_sup);
     	pan_buton.add(retour);
     	pan_buton.add(valid_modif);
     	pan_buton.add(valid_supp);
     	pan_buton.add(valid_ajou);

     	//pan_buton.add(impl);

     	scrollPane.getViewport().setOpaque(false);
    	p.getViewport().setOpaque(false);
    	p_prod.getViewport().setOpaque(false);
     	//scrollPane.setOpaque(false);
     	pan_coeff.setOpaque(false);
     	pan_coeff_lab.setOpaque(false);
     	pan_coeff_jtext.setOpaque(false);


     	pan_form.setOpaque(false);
     	pan_buton.setOpaque(false);
     	pan_nomo.setOpaque(false);

     	pan_prod.setOpaque(false);
     	 pan_refer.setOpaque(false);
		 pan_ref_lab.setOpaque(false);
		 pan_ref_comb.setOpaque(false);
		 pan_codegener.setOpaque(false);
		 pan_codegener_lab.setOpaque(false);
		 pan_codegener_comb.setOpaque(false);

		 pan_codeprod.setOpaque(false);
		 pan_codeprod_lab.setOpaque(false);
		 pan_codeprod_jtext.setOpaque(false);
		 pan_codeprogener.setOpaque(false);



	     pan_artgener.setOpaque(false);
	     pan_codeprogener1.setOpaque(false);
	     pan_artgener.setOpaque(false);

	     pan_infogener.setOpaque(false);

	     pan_article.setOpaque(false);
	     pan_lab_combo.setOpaque(false);
	     pan_article_comb.setOpaque(false);
	     pan_entrep.setOpaque(false);





		 pan_designation.setOpaque(false);
		 pan_designation_lab.setOpaque(false);
		 pan_designation_jtext.setOpaque(false);


		 pan_codeprod1.setOpaque(false);
		 pan_codeprod1_lab.setOpaque(false);
		 pan_codeprod1_jtext.setOpaque(false);
		 pan_codeprogener1.setOpaque(false);
		 pan_famille.setOpaque(false);
		 pan_famille_lab.setOpaque(false);
		 pan_famille_comb.setOpaque(false);

		 pan_infogener.setOpaque(false);

         pan_description.setOpaque(false);
         pan_description_lab.setOpaque(false);
         pan_description_jtext.setOpaque(false);


         pan_datelancement.setOpaque(false);
         pan_datelancement_lab.setOpaque(false);
         pan_datelancement_jtext.setOpaque(false);

         pan_soft.setOpaque(false);
         pan_soft_lab.setOpaque(false);
         pan_soft_jtext.setOpaque(false);
         pan_ingenieur.setOpaque(false);
         pan_ingenieur_lab.setOpaque(false);
          pan_ingenieur_jtext.setOpaque(false);
         pan_form.setOpaque(false);
         pan_form1.setOpaque(false);
         pan_form2.setOpaque(false);
         pan_gener.setOpaque(false);
         pan_radio.setOpaque(false);
         pan_radio_lab.setOpaque(false);
      	 pan_radio_bg.setOpaque(false);
        	jTabbedPane.setOpaque(false);
      	 pan_tab.setOpaque(false);
      	 pan_tab_prod.setOpaque(false);
      	 pan_but_poste.setOpaque(false);
      	 pan_but_prod.setOpaque(false);


         pan_gener.setLayout(new BoxLayout(pan_gener,BoxLayout.Y_AXIS));
         pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.X_AXIS));
         pan_form1.setLayout(new BoxLayout(pan_form1,BoxLayout.Y_AXIS));
         pan_form2.setLayout(new BoxLayout(pan_form2,BoxLayout.Y_AXIS));
         pan_designation.setLayout(new BoxLayout(pan_designation,BoxLayout.X_AXIS));
         pan_radio.setLayout(new BoxLayout(pan_radio,BoxLayout.X_AXIS));
         pan_article.setLayout(new BoxLayout(pan_article,BoxLayout.X_AXIS));
         pan_indice.setLayout(new BoxLayout(pan_indice, BoxLayout.X_AXIS));

         pan_famille.setLayout(new BoxLayout(pan_famille,BoxLayout.X_AXIS));
         pan_codegener.setLayout(new BoxLayout(pan_codegener,BoxLayout.X_AXIS));
         pan_refer.setLayout(new BoxLayout(pan_refer,BoxLayout.X_AXIS));
         pan_codeprod.setLayout(new BoxLayout(pan_codeprod,BoxLayout.X_AXIS));
         pan_codeprod1.setLayout(new BoxLayout(pan_codeprod1,BoxLayout.X_AXIS));

         pan_soft.setLayout(new BoxLayout(pan_soft,BoxLayout.X_AXIS));
         pan_description.setLayout(new BoxLayout(pan_description,BoxLayout.X_AXIS));
         pan_model.setLayout(new BoxLayout(pan_model,BoxLayout.X_AXIS));

         pan_ingenieur.setLayout(new BoxLayout(pan_ingenieur,BoxLayout.X_AXIS));
         pan_datelancement.setLayout(new BoxLayout(pan_datelancement,BoxLayout.X_AXIS));
         pan_coeff.setLayout(new BoxLayout(pan_coeff,BoxLayout.X_AXIS));

       	pan_tab.setLayout(new BoxLayout(pan_tab,BoxLayout.Y_AXIS));
     	pan_tab_prod.setLayout(new BoxLayout(pan_tab_prod,BoxLayout.Y_AXIS));

    	pan_indice_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan_indice_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_indice_lab.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
		pan_indice_comb.setBorder(BorderFactory.createEmptyBorder(0, 55, 0, 0));





         pan_description_lab.setBorder(BorderFactory.createEmptyBorder(0,40, 0,0));
         pan_description_jtext.setBorder(BorderFactory.createEmptyBorder(0, 55, 0,0));
         description.setPreferredSize(new Dimension(210,100));



         p.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));
         pan_but_poste.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));
         pan_but_prod.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));


         p_prod.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));


         pan_soft_lab.setBorder(BorderFactory.createEmptyBorder(0, 65, 0,0));
         pan_soft_jtext.setBorder(BorderFactory.createEmptyBorder(0, 45, 0,0));

         pan_ingenieur.setBorder(BorderFactory.createEmptyBorder(0, -70, 0,0));
         pan_ingenieur_lab.setBorder(BorderFactory.createEmptyBorder(0, 30, 0,0));
         pan_ingenieur_jtext.setBorder(BorderFactory.createEmptyBorder(0, 50, 0,0));


         pan_datelancement.setBorder(BorderFactory.createEmptyBorder(0, -70, 0,0));
         pan_datelancement_lab.setBorder(BorderFactory.createEmptyBorder(0, 40, 0,0));
         pan_datelancement_jtext.setBorder(BorderFactory.createEmptyBorder(0, 50, 0,0));


         pan_model.setBorder(BorderFactory.createEmptyBorder(0, 30, 0,0));
         pan_model_lab.setBorder(BorderFactory.createEmptyBorder(0, 40, 0,0));
         pan_model_jtext.setBorder(BorderFactory.createEmptyBorder(0, 57, 0,0));

         pan_model_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_model_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));

         pan_soft_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_soft_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));
         pan_description_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_description_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));

         pan_ingenieur_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_ingenieur_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));
         pan_datelancement_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_datelancement_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));

         pan_codeprod1_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_codeprod1_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));



         pan_codeprod1_lab.setBorder(BorderFactory.createEmptyBorder(0, 40, 0,0));
         pan_codeprod1_jtext.setBorder(BorderFactory.createEmptyBorder(0, 30, 0,0));

         pan_codegener_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_codegener_comb.setLayout(new FlowLayout(FlowLayout.RIGHT));
         pan_codegener_comb.setBorder(BorderFactory.createEmptyBorder(0, 30, 0,0));
         pan_codegener_lab.setBorder(BorderFactory.createEmptyBorder(0, 220, 0,0));

         pan_ref_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_ref_comb.setLayout(new FlowLayout(FlowLayout.RIGHT));
         pan_ref_comb.setBorder(BorderFactory.createEmptyBorder(0, -130, 0,0));
         pan_ref_lab.setBorder(BorderFactory.createEmptyBorder(0, 160, 0,0));

         pan_codeprod_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_codeprod_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));
         pan_codeprod_lab.setBorder(BorderFactory.createEmptyBorder(0, 40, 0,0));
         pan_codeprod_jtext.setBorder(BorderFactory.createEmptyBorder(0, 30, 0,0));

         pan_designation_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_designation_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));
         pan_designation_lab.setBorder(BorderFactory.createEmptyBorder(0, 58, 0,0));
         pan_designation_jtext.setBorder(BorderFactory.createEmptyBorder(0, 30, 0,0));

         pan_radio_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_radio_bg.setLayout(new FlowLayout(FlowLayout.RIGHT));
         pan_radio.setBorder(BorderFactory.createEmptyBorder(0, -40, 0,0));
         pan_radio_bg.setBorder(BorderFactory.createEmptyBorder(0, -200, 0,0));

         pan_famille_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_famille_comb.setLayout(new FlowLayout(FlowLayout.RIGHT));
         pan_famille_lab.setBorder(BorderFactory.createEmptyBorder(0, 93, 0,0));
         pan_famille_comb.setBorder(BorderFactory.createEmptyBorder(0, 50, 0,0));

         pan_coeff_lab.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_coeff_jtext.setLayout(new FlowLayout(FlowLayout.RIGHT));
         pan_coeff.setBorder(BorderFactory.createEmptyBorder(0, -60, 0,0));
         pan_coeff_lab.setBorder(BorderFactory.createEmptyBorder(0, 50, 0,0));

         pan_coeff_jtext.setBorder(BorderFactory.createEmptyBorder(0, -100, 0,0));

         pan_lab_combo.setLayout( new FlowLayout( FlowLayout.CENTER));
         pan_article_comb.setLayout(new FlowLayout(FlowLayout.RIGHT));
         pan_article.setBorder(BorderFactory.createEmptyBorder(0, -20, 0,0));
         pan_radio.setBorder(BorderFactory.createEmptyBorder(0, -50, 0,0));

         pan_lab_combo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
         pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0, 32, 0,0));

         pan_ref_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));
         pan_ref_comb.setLayout(new FlowLayout(FlowLayout.CENTER));

         pan_ref_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));
         pan_ref_comb.setLayout(new FlowLayout(FlowLayout.CENTER));
         pan_codegener_lab.setLayout( new FlowLayout( FlowLayout.RIGHT ));
         pan_codegener_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
         pan_radio_lab.setLayout( new FlowLayout( FlowLayout.RIGHT));
         pan_radio_bg.setLayout(new FlowLayout(FlowLayout.CENTER));

         pan_lab_combo.setLayout( new FlowLayout( FlowLayout.RIGHT));
         pan_article_comb.setLayout(new FlowLayout(FlowLayout.CENTER));






		 pan_article.setBorder(BorderFactory.createEmptyBorder(0, 0, 00, 0));

         but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         but_sauv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         but_sup.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         valid_supp.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         but_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         but_supprim.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         but_ajou_prod.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         but_supprim_prod.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        // impl.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
         pan_ingenieur_lab.setPreferredSize(new Dimension(260, 33));
         pan_ingenieur_jtext.setPreferredSize(new Dimension(270, 33));

         pan_coeff_lab.setPreferredSize(new Dimension(260, 33));
         pan_coeff_jtext.setPreferredSize(new Dimension(260, 33));
         pan_datelancement_lab.setPreferredSize(new Dimension(260, 33));
         pan_datelancement_jtext.setPreferredSize(new Dimension(270, 33));
         pan_radio_lab.setPreferredSize(new Dimension(320, 33));
         pan_radio_bg.setPreferredSize(new Dimension(400, 33));
         pan_ref_lab.setPreferredSize(new Dimension(320, 33));
         pan_ref_comb.setPreferredSize(new Dimension(450, 37));
         pan_codegener_lab.setPreferredSize(new Dimension(320, 33));
         pan_codegener_comb.setPreferredSize(new Dimension(450, 37));
         but_modif.setPreferredSize(new Dimension(120, 33));
         but_sauv.setPreferredSize(new Dimension(120, 33));
         but_sup.setPreferredSize(new Dimension(120, 33));
         retour.setPreferredSize(new Dimension(120, 33));
         valid_supp.setPreferredSize(new Dimension(120, 33));
         valid_modif.setPreferredSize(new Dimension(120, 33));
         valid_ajou.setPreferredSize(new Dimension(120, 33));
      //   impl.setPreferredSize(new Dimension(120, 33));
         article_comb.setPreferredSize(new Dimension(250, 33));
         model_jtext.setPreferredSize(new Dimension(250, 33));

         ref_comb.setPreferredSize(new Dimension(250, 33));
         famille_comb.setPreferredSize(new Dimension(250, 33));

 		indice.setPreferredSize(new Dimension(250, 33));

    codegener_comb.setPreferredSize(new Dimension(250, 10));
//         Dimension d = codegener_comb.getPreferredSize();

      codegener_comb.setPreferredSize(new Dimension(250, 30));

        // codegener_comb.setDimensions(250);

         coeff_jtext.setPreferredSize(new Dimension(250, 30));
         codeprod.setPreferredSize(new Dimension(250, 30));
         codeprod1.setPreferredSize(new Dimension(250,30));
         designation.setPreferredSize(new Dimension(250, 30));



         datelancement.setPreferredSize(new Dimension(250,30));
         soft.setPreferredSize(new Dimension(250, 30));
         ingenieur.setPreferredSize(new Dimension(250,30));

         description.setPreferredSize(new Dimension(250,100));

       	 panel.setLayout(new GridLayout(1, 1));
         pan_artgener.setPreferredSize(new Dimension(400, 240));
         pan_codeprogener.setPreferredSize(new Dimension(400, 185));

         p.setPreferredSize(new Dimension(900, 430));
         p_prod.setPreferredSize(new Dimension(900, 430));
 	     pan_but_poste.setPreferredSize(new Dimension(900, 80));
 	     pan_but_prod.setPreferredSize(new Dimension(900, 80));
 	     tab.table.setRowHeight(25);
 	     tab_prod.table.setRowHeight(25);

          pan_form1.setPreferredSize(new Dimension(400, 400));
	      pan_form2.setPreferredSize(new Dimension(400, 400));
		    setTitle("Gestion des Articles" );
		    setSize(1000, 600);
		    setLocationRelativeTo(null);
		    setVisible(true);
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setContentPane(jTabbedPane);

}



public boolean isValid(String chaine) {
	try {
		//Integer.parseInt(chaine);
		Float.parseFloat(chaine);
		//Double.parseDouble(chaine);
	} catch (NumberFormatException e){
		return false;
	}

	return true;
}
public void remplir_art_table(){

	      article_list.clear();
		  article_list.add("---Selectionner un article-----");
		  list_artnomo_tr=art.select_article_codenomo(fabr);
		    for(int i=0;i<list_artnomo_tr.size();i++)
		     {
		          //Pour affecter une valeur de base de données à un Combobox
		    	article_list.add(list_artnomo_tr.get(i)+" "+list_artnomo_tr.get(i+1));
			   i++;
		     }
		   art_comb = new Java2sAutoComboBox(article_list);

}


public void remplir_model_table(){


	model_list.add("");

	list_model_tr=art.select_model();
	    for(int i=0;i<list_model_tr.size();i++)
	     {
	          //Pour affecter une valeur de base de données à un Combobox
	    	model_list.add(list_model_tr.get(i));

	     }
	 model_comb = new Java2sAutoComboBox(model_list);
}

public  void remplir_table(ArrayList<String> list,String select, ArrayList<String> list_bdd,Java2sAutoComboBox comb ){

    // list.clear();
	  list.add(select);
	 // list_bdd=art.select_article_codenomo(fabr,famille);
	    for(int i=0;i<list_bdd.size();i++)
	     {
	          //Pour affecter une valeur de base de données à un Combobox
	    	list.add(list_bdd.get(i)+" "+list_bdd.get(i+1));
		   i++;
	     }
	   comb = new Java2sAutoComboBox(list);

}

public  void remplir_fourniss_table(){

	   list_fo.clear();
	  list_fo.add("");
	  list_fourniss_tr=frns.select_fournisseur_code();
	    for(int i=0;i<list_fourniss_tr.size();i++)
	     {
	          //Pour affecter une valeur de base de données à un Combobox
	    	list_fo.add(list_fourniss_tr.get(i)+" "+list_fourniss_tr.get(i+1));
		   i++;
	     }
	   fourniss_comb = new Java2sAutoComboBox(list_fo);

}


public  void remplir_art(String fabr ){
    article_comb.removeAllItems();
	article_comb.addItem("---Selectionner un article-----");
	list_article_tr=art.select_article_code(fabr);
for(int i=0;i<list_article_tr.size();i++)
{
       //Pour affecter une valeur de base de données à un Combobox
	   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
	   i++;
}}


public void remove_ligne_table(Tableau tab){
	 int rows = tab.table.getRowCount();

   	for(int i = rows - 1; i >=0; i--)
   	{
   		((DefaultTableModel) tab.table.getModel()).removeRow(i);

   	}

  }


public boolean vaidCheck()
{
if(tab.table.getCellEditor()!=null){
tab.table.getCellEditor().stopCellEditing();
}
for(int l=0;l< tab.table.getRowCount();l++)
{
for (int k=0;k<tab.table.getColumnCount();k++)
{
String om=tab.table.getValueAt(l,k).toString();
if(om.trim().length()==0)
{
	msg="";
  return false;
}}}
return true;
}

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				article frame = new article("7");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}

}
