package logiciel_etiq;


import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXDatePicker;



public class list_palette_portable extends JFrame {
    /**
     *
     */
    ArrayList <String>list_fiche= new ArrayList<String>() ;

    ArrayList <String>list_rech= new ArrayList<String>() ;
    ArrayList <String>list_count= new ArrayList<String>() ;
    Map<String, Object> parameters = new HashMap<String, Object>();

    private static final long serialVersionUID = 1L;
    public String Chemin = "c:\\GCOBAR\\";
    public  String bdd = Chemin+Utilitaire.InitBdd()+".accdb";
    Object [] entete={"Code/Designation","Palette","Parcel","Imei1","Imei2","Couleur"};
    final Tableau tab=new Tableau(entete);
    ArrayList <String>list_dimension= new ArrayList<String>() ;

    String report;

    String date_jour;
    JMenuItem menuItem4 = new JMenuItem("Exporter en excel" );

    final JTextField filterText = new JTextField("Recherche");

    final JXDatePicker datedebutText = new JXDatePicker();
    final JXDatePicker datefinText = new JXDatePicker();

    final JComboBox articlecombo= new JComboBox();
    static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    JMenuItem menuItem = new JMenuItem("Imprimer etiquette" );
    JMenuItem menuItemtpe = new JMenuItem("Imprimer etiquette" );
    JMenuItem menuItemtpeexcel = new JMenuItem("Exporter en excel" );
    //JMenuItem menuItem4 = new JMenuItem("Exporter en excel" );
    Thread att;
    // private JButton importer=new JButton("Importer Excel");

    // JMenuItem menuItem2 = new JMenuItem("Imprimer fiche" );


    final JPanel paneltext =new JPanel();
    final JPanel pan=new JPanel();
    final JPanel pan1=new JPanel();
    final JPanel pantpe=new JPanel();
    final JPanel pan2=new JPanel();
    JPanel panel;

    final JPanel pan3=new JPanel();
    final JPanel pandatedebut=new JPanel();
    final JPanel pandatefin=new JPanel();
    final JPanel panarticle=new JPanel();

    final JPanel pandatedebuttext=new JPanel();
    final JPanel pandatedebutlab=new JPanel();

    final JPanel pandatefintext=new JPanel();
    final JPanel pandatefinlab=new JPanel();


    final JLabel rech = new JLabel("Recherche");
    final JLabel datedebut = new JLabel("Debut");
    final JLabel datefin = new JLabel("Fin");
    final JLabel article = new JLabel("D");
    private JComboBox dimension_comb= new JComboBox();


    JLabel countpalette;
    JLabel countcarton;
    JLabel count;
    private JPopupMenu popupMenu = new JPopupMenu();
    private JPopupMenu popupMenutpe = new JPopupMenu();
    private int selectedRow=-1;
    private int selectedRowtpe=-1;
    JScrollPane p=new JScrollPane(tab);

    gestion_imp imp=new gestion_imp();
    int i=1;
    int j=1;
    int l = 0;

    int i1=1;
    int j1=1;
    int l1 = 0;
    ArrayList<String> listrc = new ArrayList<String>();
    ArrayList<String> listpal = new ArrayList<String>();
    ArrayList<String> listrc1 = new ArrayList<String>();
    ArrayList<String> listfc = new ArrayList<String>();

    ArrayList<String> list_emei = new ArrayList<String>();
    String parcel="";



    public list_palette_portable (final String log){
        final menu fr=new menu(log);
        fr.setVisible(false);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(img);
        selectioncomb.windows(this,log,laf);


        dimension_comb.addItem("--- Sélectionner la dimension de l'etiquette ----");

        list_dimension=imp.select_dimension_etq("etq_portable_palette");

        for(int i=0;i<list_dimension.size();i++)
        {
            //Pour affecter une valeur de base de données à un Combobox
            dimension_comb.addItem(list_dimension.get(i));

        }
        build();

        composant();}




    public void composant(){
        if(!selectioncomb.prv.contains("list_etqemballage")){
            //System.out.println("list_fiche"+selectioncomb.prv);
            selectioncomb.prv.add("list_etqemballage");}
        //selectioncomb.prv.add("list_fiche");
        tab.allowEdition1=false;
        tab.allowEdition2=false;
        tab.allowEdition3=false;
        tab.allowEdition4=false;
        tab.allowEdition5=false;
        tab.allowEdition6=false;
        tab.allowEdition7=false;
        tab.allowEdition8=false;
        tab.allowEdition9=false;

        filterText.setForeground(Color.gray);
        tab.setStyle(2);

        datedebutText.setDate(Calendar.getInstance().getTime());
        datefinText.setDate(Calendar.getInstance().getTime());
        datedebutText.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        datefinText.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        datedebutText.getEditor().setEditable(false);
        datefinText.getEditor().setEditable(false);

        //articlecombo.addItem("TV");
        articlecombo.addItem("Portable");
        articlecombo.addItem("TPE");


        filterText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //parcel=imp.select_emei_table(filterText.getText());
                recherche();
                //list_rech=imp.select_emei_table(filterText.getText());

            }});

        datedebutText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //parcel=imp.select_emei_table(filterText.getText());
                recherche();
                //list_rech=imp.select_emei_table(filterText.getText());

            }});

        datefinText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //parcel=imp.select_emei_table(filterText.getText());
                recherche();
                //list_rech=imp.select_emei_table(filterText.getText());

            }});
        dimension_comb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(dimension_comb.getSelectedIndex()!=0)
                    report=imp.select_report(dimension_comb.getSelectedItem().toString(),"etq_portable_palette");
            }});


        panel =new JPanel() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;
             public void paintComponent(Graphics g){
               ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
             img.paintIcon(this, g,0, 0);
                }
        } ;


//			  filterText.addKeyListener(new KeyAdapter() {
//			      public void keyReleased(KeyEvent e) {
//			    	  String text = filterText.getText();
//	                  if (text.length() == 0) {
//	                  tab.sorter.setRowFilter(null);
//	                  } else {
//	                	  if(!parcel.equals("")){
//	                		  tab.sorter.setRowFilter(RowFilter.regexFilter(parcel));
//	  					}
//	                	  else{
//	                  tab.sorter.setRowFilter(RowFilter.regexFilter(text.toLowerCase()));
//	                	  }
//	                  }
//			      }
//
//			      public void keyPressed(KeyEvent e) {
//
//			      } });
//

        //recherche();
        filterText.addMouseListener(
                new  MouseAdapter(){
                    public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub
                        //filterText.setText("");
                        filterText.setForeground(Color.gray);
                    }
                });
//
        filterText.addFocusListener(
                new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent e) { filterText.setForeground(Color.black);

                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        //filterText.setText("Recherche");
                        filterText.setForeground(Color.gray);

                        // You could do something here when the field loses focus, if you like
                    }

                });
        ///////////////////////////////////////////////////////////////////////////////////////////

        datedebutText.addMouseListener(
                new  MouseAdapter(){
                    public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub
                        //datedebutText.setText("");
                        datedebutText.setForeground(Color.gray);
                    }
                });
//
        datedebutText.addFocusListener(
                new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent e) { filterText.setForeground(Color.black);

                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        //datedebutText.setText("Date debut");
                        datedebutText.setForeground(Color.gray);

                        // You could do something here when the field loses focus, if you like
                    }

                });

        ////////////////////////////datefin////////////////////////////////

        datefinText.addMouseListener(
                new  MouseAdapter(){
                    public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub
                        //	datefinText.setText("");
                        datefinText.setForeground(Color.gray);
                    }
                });
//
        datefinText.addFocusListener(
                new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent e) { filterText.setForeground(Color.black);

                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        //datefinText.setText("Date fin");
                        //	datefinText.setForeground(Color.gray);

                        // You could do something here when the field loses focus, if you like
                    }

                });


//		list_fiche=imp.select_etqemballage();
//		for(i=0;i<list_fiche.size();i=i+13){tab.ajouter();}
//		int j=1;
//		int l = 0; i=1;
//		while(l<list_fiche.size()){
//
//			while(l<j*13){
//				tab.getTable().setValueAt(list_fiche.get(l).toLowerCase(), j-1, 0);
//
//				tab.getTable().setValueAt(list_fiche.get(l+1).toLowerCase()+" "+list_fiche.get(l+2).toLowerCase(), j-1, 1);
//				tab.getTable().setValueAt(list_fiche.get(l+3).toLowerCase(), j-1, 2);
//				tab.getTable().setValueAt(list_fiche.get(l+4).toLowerCase(), j-1, 3);
//				tab.getTable().setValueAt(list_fiche.get(l+5).toLowerCase(), j-1,4);
//				tab.getTable().setValueAt(list_fiche.get(l+6).toLowerCase(), j-1,5);
//
//				tab.getTable().setValueAt(list_fiche.get(l+7).toLowerCase(), j-1,6);
//				tab.getTable().setValueAt(list_fiche.get(l+8).toLowerCase(), j-1,7);
//				if(list_fiche.get(l+9)!=null)
//					tab.getTable().setValueAt(list_fiche.get(l+9).toLowerCase(), j-1,8);
//				if(list_fiche.get(l+10)!=null)
//					tab.getTable().setValueAt(list_fiche.get(l+10).toLowerCase(), j-1,9);
//
//
//				tab.getTable().setValueAt(list_fiche.get(l+11).toLowerCase(), j-1,10);
//				tab.getTable().setValueAt(list_fiche.get(l+12).toLowerCase(), j-1,11);
//
//				i=i+1; l=l+13;
//			}
//			i=1;
//			j=j+1;
//		}


        try{
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch(Exception e1){
            e1.printStackTrace();
        }



        Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);

        Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
        tab.table.setFont(police_fi);



        count = new JLabel("Le Nombre Des portable :  "+tab.table.getRowCount());
        count.setForeground(Color.white);
        count.setBackground(new Color(6,119,144));
        count.setOpaque(true);
        count.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        count.setFont(police2);

        countpalette = new JLabel("Le Nombre Des palette :  "+tab.table.getRowCount());
        countpalette.setForeground(Color.white);
        countpalette.setBackground(new Color(6,119,144));
        countpalette.setOpaque(true);
        countpalette.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        countpalette.setFont(police2);

        countcarton = new JLabel("Le Nombre Des carton :  "+tab.table.getRowCount());
        countcarton.setForeground(Color.white);
        countcarton.setBackground(new Color(6,119,144));
        countcarton.setOpaque(true);
        countcarton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        countcarton.setFont(police2);


        datedebut.setFont(police2);
        // datedebut.setBackground(new Color(6,119,144));
        datedebut.setForeground(Color.white);
        // datedebut.setOpaque(true);
        datedebut.setBorder(BorderFactory.createEmptyBorder(0, 0, -5, 0));

        datefin.setFont(police2);
        datefin.setForeground(Color.white);
        // datefin.setBackground(new Color(6,119,144));
        //  datefin.setOpaque(true);
        datefin.setBorder(BorderFactory.createEmptyBorder(0, 0, -5, 0));

        panel.add(paneltext);
        panel.add(p);
        panel.add(pan);





        paneltext.add(pan2);
        //paneltext.add(pan3);

        paneltext.add(pandatedebut);
        paneltext.add(pandatefin);
        paneltext.add(panarticle);


        pandatedebut.add(pandatedebutlab);
        pandatedebut.add(pandatedebuttext);


        //pandatedebut.add(datedebut);
        pan2.add(filterText);

        pandatedebutlab.add(datedebut);
        pandatedebuttext.add(datedebutText);

        //datedebutText.setForeground(Color.gray);
        pandatefin.add(pandatefinlab);
        pandatefin.add(pandatefintext);

        //datefinText.setForeground(Color.gray);
        //	pan2.add(pandatedebut);
        pandatefinlab.add(datefin);
        pandatefintext.add(datefinText);
        panarticle.add(dimension_comb);


        pan.add(countpalette);
        pan.add(countcarton);
        pan.add(count);
        //pan.add(importer);

        pan.add(pan1);

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
        paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));
        pandatefin.setLayout(new BoxLayout(pandatefin,BoxLayout.X_AXIS));
        pan2.setLayout(new BoxLayout(pan2,BoxLayout.X_AXIS));
        pandatedebut.setLayout(new BoxLayout(pandatedebut,BoxLayout.X_AXIS));
        panarticle.setLayout(new BoxLayout(panarticle,BoxLayout.X_AXIS));
        filterText.setPreferredSize(new Dimension(50,30));
        pandatedebutlab.setLayout(new FlowLayout(  FlowLayout.RIGHT));
        pandatedebuttext.setLayout(new FlowLayout(  FlowLayout.LEFT));
        pandatefinlab.setLayout(new FlowLayout(  FlowLayout.RIGHT));
        pandatefintext.setLayout(new FlowLayout(  FlowLayout.LEFT));

        p.setPreferredSize(new Dimension(700,700));


//
//        articlecombo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//			 if(articlecombo.getSelectedItem().equals("TPE")){
//				// tab.setVisible(false);
//
//				   recherche();
//					panel.remove(p);
//					panel.remove(pan);
//
//					panel.revalidate();
//					panel.repaint();
//
//
//					panel.add(pan);
//
//				//	panel.revalidate();
//					//panel.repaint();
//
//			 }
//
//			 if(articlecombo.getSelectedItem().equals("Portable")){
//				 System.out.println("hhhhknfskdnfskln");
//				 recherche();
//
//					panel.remove(pan);
//				//tab.setVisible(true);
//					panel.revalidate();
//					panel.repaint();
//					panel.add(p);
//					panel.add(pan);
//
//					//panel.revalidate();
//					//panel.repaint();
//				//	p.setPreferredSize(new Dimension(700,700));
//					}
//
//
//
//
//			}});
//





        paneltext.setPreferredSize(new Dimension(40, 100));
        datedebutText.setPreferredSize(new Dimension(180, 30));
        datedebut.setPreferredSize(new Dimension(50, 20));
        datefin.setPreferredSize(new Dimension(50, 20));
        datefinText.setPreferredSize(new Dimension(180, 30));
        dimension_comb.setPreferredSize(new Dimension(50, 30));
        pandatefin.setPreferredSize(new Dimension(0, -50));
        pandatedebut.setPreferredSize(new Dimension(0,-20));
        pan2.setPreferredSize(new Dimension(50, 30));
        //pandatedebutlab.setPreferredSize(new Dimension(150, 130));


        panel.setOpaque(false);
        pan.setOpaque(false);
        paneltext.setOpaque(false);
        pan1.setOpaque(false);
        pan2.setOpaque(false);
        pan3.setOpaque(false);
        pandatedebut.setOpaque(false);
        pandatefin.setOpaque(false);
        panarticle.setOpaque(false);
        pandatedebuttext.setOpaque(false);
        pandatedebutlab.setOpaque(false);

        pandatefintext.setOpaque(false);
        pandatefinlab.setOpaque(false);
        pan2.setLayout(null);
        //pandatedebut.setLayout(null);
        //pandatefin.setLayout(null);

        filterText.setBounds(20, 40, 150,30);
        datefinText.setBounds(50, 30, 180,30);
        datedebutText.setBounds(0, 30, 180,30);
        //articlecombo.setBounds(00, 30, 150,30);
        dimension_comb.setBounds(00, 30, 150,30);
        //datefin.setBounds(-10, 30, 90,30);
        //datedebut.setBounds(0, 30, 120,30);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pan1.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        pandatedebut.setBorder(BorderFactory.createEmptyBorder(35, 20, 30,0));
        pandatefin.setBorder(BorderFactory.createEmptyBorder(35, 10, 30, 0));
        panarticle.setBorder(BorderFactory.createEmptyBorder(40, 0, 30, 20));
        // pandatedebutlab.setBorder(BorderFactory.createEmptyBord.er(0, 0, 80,0));
        //  pandatedebuttext.setBorder(BorderFactory.createEmptyBorder(0, 0, 00, 0));
        pan2.setBorder(BorderFactory.createEmptyBorder(150, 50, 150,80));
        // paneltext.setBorder(BorderFactory.createEmptyBorder(0,0, 0, 0));
        setTitle("List des Palette Portable" );
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        recherche();


    }

    private void build() {

        tab.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3){

                    //String[] data = tab.table.getValueAt(row, col);

                    showPopup(e);
                }
            }
        });




        popupMenu.add(menuItem);
        //   popupMenu.add(menuItem2);




        menuItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        listrc.clear();
                        listpal.clear();
                        if(dimension_comb.getSelectedIndex()!=0){
                            int[] selection = tab.table.getSelectedRows() ;
                            int z =selection.length;

                            System.out.println("zzzzz"+z);
                            for (int i=0; i<z; i++) {
                                if (!listrc.contains(tab.table.getValueAt(selection[i], 2)))

                                    //System.out.println("tab1  "+tab.table.getValueAt(selection[i], 1)+"tab-1  "+listpal.get(i));

                                    listrc.add((String) tab.table.getValueAt(selection[i], 2));
                                if (!listpal.contains(tab.table.getValueAt(selection[i], 1)))
                                    listpal.add((String) tab.table.getValueAt(selection[i], 1));
                            }
                            int j=listrc.size();
                            for(i=0;i<j;i++) {


                                list_count.add(imp.nbr_carton_palette_potable(listpal.get(i))) ;


                            }

                            if(selectedRow!=-1){

                                String parcour= "C:\\GCOBAR\\pdf\\etq_portable\\"+dimension_comb.getSelectedItem()+listrc.get(0).toString().replaceAll("\\/", "_")
                                        +"_"+listrc.get(j-1).toString().replaceAll("\\/", "_")+"_"+z+"_"+"etq_portable.pdf";
                                String model ="C:\\GCOBAR\\CODE\\"+report;
                                try{

                                    new BufferedReader(new FileReader("C:\\GCOBAR\\pdf\\etq_portable\\"+dimension_comb.getSelectedItem()+listrc.get(0).toString().replaceAll("\\/", "_")
                                            +"_"+listrc.get(j-1).toString().replaceAll("\\/", "_")+"_"+z+"_"+"etq_portable.pdf"));
                                    try {
                                        Desktop.getDesktop().open(new File("C:\\GCOBAR\\pdf\\etq_portable\\"+dimension_comb.getSelectedItem()+listrc.get(0).toString().replaceAll("\\/", "_")
                                                +"_"+listrc.get(j-1).toString().replaceAll("\\/", "_")+"_"+z+"_"+"etq_portable.pdf"));

                                        //cop(new File(bdd),new File(save.getSelectedFile().getPath().replace(".accdb", ".naw")));
                                        //cop(new File("C:\\GEFACT\\factpro\\"+code_jtext.getText().replace("/", "")+".pdf"),new File("C:\\GCOBAR\\CODE BARRE\\"+code_jtext.getText().replace("/", "")+" "+date+".fpc"));
                                    } catch (IOException p) {
                                        // TODO Auto-generated catch block
                                        p.printStackTrace();
                                    }
                                } catch (FileNotFoundException fnfe) {

                                    //   System.out.println(listrc);

                                    parameters.put("serial",listrc);
                                    parameters.put("qty",list_count);


                                    selectioncomb.imprimer(bdd, parcour,model,parameters);
                                    listrc.clear();

                                    list_count.clear();



                                } }}
                        else{

                            JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette");
                        }
                    }});



        //	popupMenu.add(menuItem);
        //popupMenutpe.add(menuItemtpe);

        //   popupMenu.add(menuItem2);




//		menuItem.addActionListener(
//				new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						listrc.clear();
//						int[] selection = tab.table.getSelectedRows() ;
//						int z =selection.length;
//						for (int i=0; i<z; i++) {
//							if(!listrc.contains((String) tab.table.getValueAt(selection[i], 0))){
//								String parcel=tab.table.getValueAt(selection[i], 0).toString().replace("/", "_");
//								listrc.add(parcel);
//								listrc1.add((String) tab.table.getValueAt(selection[i], 0));
//							}
////		   		    		if(!listrc1.contains((String) tab.table.getValueAt(selection[i], 0))){
////
////		   		    	    listrc1.add((String) tab.table.getValueAt(selection[i], 0));
////
////		   		    		}
//						}
//						if(selectedRow!=-1){
//							String parcour= "C:\\GCOBAR\\pdf\\etq_emballage\\"+listrc+"etq_emballage.pdf";
//							String model ="C:\\GCOBAR\\CODE\\etiquette_emballage_list.jrxml";
//							try{
//
//								new BufferedReader(new FileReader("C:\\GCOBAR\\pdf\\etq_emballage\\"+listrc+"etq_emballage.pdf"));
//								try {
//									Desktop.getDesktop().open(new File("C:\\GCOBAR\\pdf\\etq_emballage\\"+listrc+"etq_emballage.pdf"));
//
//									//cop(new File(bdd),new File(save.getSelectedFile().getPath().replace(".accdb", ".naw")));
//									//cop(new File("C:\\GEFACT\\factpro\\"+code_jtext.getText().replace("/", "")+".pdf"),new File("C:\\GCOBAR\\CODE BARRE\\"+code_jtext.getText().replace("/", "")+" "+date+".fpc"));
//								} catch (IOException p) {
//									// TODO Auto-generated catch block
//									p.printStackTrace();
//								}
//							} catch (FileNotFoundException fnfe) {
//
//
//								selectioncomb.imprimer(listrc1,bdd,parcour,model);
//								listrc1.clear();
//
//
//
//							} }
//					}});



        popupMenu.add(menuItem4);
        //	  popupMenutpe.add(menuItemtpeexcel);


        menuItem4.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        att = new Thread(){
                            public void run(){
                                int reponse = JOptionPane.showConfirmDialog(
                                        null, "Operation terminée. Voulez-vous ouvrir le fichier Excel ?",
                                        "Confirmation",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);
                                if (reponse==JOptionPane.YES_OPTION){
                                    try {
                                        Date actuelle=new Date();
                                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                        date_jour = dateFormat.format(actuelle);

                                        System.out.println(date_jour);
                                        String parcour="C://GCOBAR//pdf//excel//portable_emballage"+date_jour+".xlsx";
                                        FileOutputStream fileOut = new FileOutputStream(parcour);
                                        Workbook worksheet = new XSSFWorkbook();
                                        CreationHelper ch = worksheet.getCreationHelper();
                                        String safeName = WorkbookUtil.createSafeSheetName("Liste Produit");
                                        org.apache.poi.ss.usermodel.Sheet sheet = worksheet.createSheet(safeName);

                                        File fichier = new File(parcour);
                                        fichier.delete();

                                        // index from 0,0... cell A1 is cell(0,0)
                                        Row row1 = sheet.createRow((short)0);
                                        ///Code/Designation","parcel","imei1","imei2","couleur
                                        Cell cellA1 = row1.createCell((short) 0);
                                        cellA1.setCellValue("Code/Designation");
                                        CellStyle  cellStyle = worksheet.createCellStyle();
                                        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
                                        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                                        cellA1.setCellStyle(cellStyle);

                                        Cell cellAx = row1.createCell((short) 1);
                                        cellAx.setCellValue("Palette_parcel");
                                        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
                                        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                                        cellAx.setCellStyle(cellStyle);


                                        Cell cellA2 = row1.createCell((short) 2);
                                        cellA2.setCellValue("carton_parcel");
                                        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
                                        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                                        cellA2.setCellStyle(cellStyle);


                                        Cell cellB2 = row1.createCell((short) 3);
                                        cellB2.setCellValue("Imei1");
                                        cellStyle = worksheet.createCellStyle();
                                        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
                                        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                                        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                                        cellB2.setCellStyle(cellStyle);

                                        Cell cellB4 = row1.createCell((short) 4);
                                        cellB4.setCellValue("Imei2");
                                        cellStyle = worksheet.createCellStyle();
                                        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
                                        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                                        cellB4.setCellStyle(cellStyle);

                                        Cell cellB5 = row1.createCell((short) 5);
                                        cellB5.setCellValue("Couleur");
                                        cellStyle = worksheet.createCellStyle();
                                        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
                                        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                                        cellB5.setCellStyle(cellStyle);







                                        Workbook wb = new HSSFWorkbook();
                                        CreationHelper createhelper = wb.getCreationHelper();
                                        Sheet sheet1 = wb.createSheet("new sheet");
                                        Row row = null;
                                        Cell cell = null;


                                        int[] selection = tab.table.getSelectedRows() ;
                                        int z =selection.length;


                                        for (int i=0; i<z; i++) {
                                            row = sheet.createRow(i+1);
                                            for (int j=0;j<tab.table.getColumnCount();j++) {
                                                cell = row.createCell(j);


                                                cell.setCellValue((String) tab.table.getValueAt(selection[i], j));

                                            }
                                        }

                                        sheet.autoSizeColumn(1);
                                        sheet.autoSizeColumn(2);
                                        sheet.autoSizeColumn(3);
                                        sheet.autoSizeColumn(4);
                                        sheet.autoSizeColumn(5);
                                        sheet.autoSizeColumn(6);
                                        sheet.autoSizeColumn(7);

                                        sheet.autoSizeColumn(0);
                                        worksheet.write(fileOut);
                                        fileOut.close();
                                        fileOut.flush();
                                        BufferedReader bfr=	new BufferedReader(new FileReader("C://GCOBAR//pdf//excel//portable_emballage"+date_jour+".xlsx"));
                                        bfr.close();
                                        try {
                                            Desktop.getDesktop().open(new File( "C://GCOBAR//pdf//excel//portable_emballage"+date_jour+".xlsx"));

                                        } catch (IOException p1) {

                                            //p.printStackTrace();

                                        }

                                    }
                                    catch (IOException fnfe) {

                                        try {
                                            Desktop.getDesktop().open(new File( "C://GCOBAR//pdf//excel//portable_emballage"+date_jour+".xlsx"));
                                        } catch (IOException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                        //controlPanel.remove(progressBar);
                                    }




                                }

                            }};

                        att.start();




                    }});




    }





    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            Point p = new Point(e.getX(), e.getY());

            tab.table.columnAtPoint(p);
            selectedRow = tab.table.rowAtPoint(p);
            popupMenu.show(e.getComponent(), e.getX(), e.getY());




        }
    }



    //////////////////////////methode  de rechrche globale////////////////////////////////////////////

    private void recherche(){
        int rows = tab.table.getRowCount();
        String datedebut="";
        String datefin="";
        String filtre="";
        Date actuelle=new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date_jour = dateFormat.format(actuelle);

        datedebut=datedebutText.getEditor().getText();
        datefin=datefinText.getEditor().getText();
        if(filterText.getText().equals("Recherche")) filtre="";
        else
            filtre=filterText.getText().trim();



        list_rech=imp.recherche_palette_portable(filtre, datedebut, datefin);

        System.out.println("hada 3lah"+list_rech);



        //list_rech=imp.select_emei_table(filterText.getText());
        for(int i1=0;i1<list_rech.size();i1=i1+7){ tab.ajouter();}
        int j1=1;
        int l1 = 0; i1=1;
        while(l1<list_rech.size()){
            while(l1<j1*7){
                tab.getTable().setValueAt(list_rech.get(l1).toLowerCase()+" "+list_rech.get(l1+1).toLowerCase(), j1-1, 0);
                tab.getTable().setValueAt(list_rech.get(l1+2).toLowerCase(), j1-1, 1);
                tab.getTable().setValueAt(list_rech.get(l1+3).toLowerCase(), j1-1, 2);
                tab.getTable().setValueAt(list_rech.get(l1+4).toLowerCase(), j1-1, 3);
                tab.getTable().setValueAt(list_rech.get(l1+5).toLowerCase(), j1-1,4);
                tab.getTable().setValueAt(list_rech.get(l1+5).toLowerCase(), j1-1,5);

                i1=i1+1; l1=l1+7;
            }
            i1=1;
            j1=j1+1;
        }
        countpalette.setText("Nombre de Palette  :"+imp.nbr_palette_portable(filtre, datedebut, datefin));
        countcarton.setText("Nombre de Carton  :"+imp.nbr_carton_portable(filtre, datedebut, datefin));
        count.setText("Nombre de Portable  :"+tab.table.getRowCount());

    }








    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new list_palette_portable("4");
                    //frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


