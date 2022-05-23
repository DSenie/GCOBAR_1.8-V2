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
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
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

public class etiquette_tpe_3code extends JFrame {

    String chaine="";
    private JPanel controlPanel = new JPanel();
    private JFrame frame = new JFrame();

    private static final long serialVersionUID = 1L;
    //private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    private String msg;
    private String Chemin = "c:\\GCOBAR\\";
    private String bdd = Chemin + Utilitaire.InitBdd() + ".accdb";
    Map<String, Object> parameters = new HashMap<String, Object>();

    private gestion_imp imp = new gestion_imp();
    private JProgressBar progressBar;


    ArrayList<String> listrc1 = new ArrayList<String>();


    private JPanel pan_model = new JPanel();
    private JPanel pan_model_comb = new JPanel();
    private JPanel pan_model_lab = new JPanel();
    private JLabel model_lab = new JLabel("TPE Modèle");


    private JPanel pan_chaine = new JPanel();
    private JPanel pan_chaine_lab = new JPanel();
    private JPanel pan_chaine_comb= new JPanel();
    private JLabel chaine_lab = new JLabel("Chaine");
    jcombo chaine_comb ;
    ArrayList list_chaine = new ArrayList<String>();

    //private JTextField model_comb = new JTextField();
    private List<String> list_m;
    private jcombo model_comb;

    private JPanel pan_code_enie = new JPanel();
    private JPanel pan_code_enie_jtext = new JPanel();
    private JPanel pan_code_enie_lab = new JPanel();
    private JLabel code_enie_lab = new JLabel("Code ENIE");
    private List<String> list_c;
    private JTextField code_enie_jtext=new JTextField();





    private JPanel pan_sn_lab = new JPanel();
    private JPanel pan_sn = new JPanel();
    private JPanel pan_sn_jtext = new JPanel();
    private JLabel sn_lab = new JLabel("SN");
    JTextField sn_jtext=new JTextField();
    //private JTextField sn_jtext = new JTextField();


    private JPanel pan_imei_lab = new JPanel();
    private JPanel pan_imei = new JPanel();
    private JPanel pan_imei_jtext = new JPanel();
    private JLabel imei_lab = new JLabel("IMEI");
    JFormattedTextField imei_jtext;
    //private JTextField imei2_jtext = new JTextField();


    private JPanel pan_date = new JPanel();
    private JPanel pan_date_lab = new JPanel();
    private JPanel pan_date_jtext= new JPanel();
    private JLabel date_lab = new JLabel("Date");
    final JXDatePicker date_picker = new JXDatePicker();


    private JPanel pan_dimension = new JPanel();
    private JPanel pan_dimension_lab = new JPanel();
    private JPanel pan_dimension_comb = new JPanel();
    private JLabel dimension_lab = new JLabel("Etiquette dimension");
    private JComboBox dimension_comb= new JComboBox();



    private JPanel pan = new JPanel();
    private JPanel pan_general = new JPanel();
    private JPanel pan_form = new JPanel();
    private JPanel pan_gener = new JPanel();

    private JPanel panel;


    private JPanel pan_button = new JPanel();

    private JButton but_sauv = new JButton("Ajouter");
    private JButton valid_ajou = new JButton("Valider");

    private JButton but_modif = new JButton("Modifier");
    private JButton valid_modif= new JButton("Valider");

    private JButton imp_etq = new JButton("Imprimer L'étiquette");

    private JButton retour = new JButton("Retour");

    private String conteur = null;
    NumberFormat formatter = new DecimalFormat("00");
    NumberFormat form = new DecimalFormat("0");

    gestion_article art=new gestion_article();
    String report;

    int diff;
    int moy;
    int co;
    Thread att;
    ArrayList <String>list_dimension= new ArrayList<String>() ;

    String date_jour;
    NumberFormat format = new DecimalFormat("00000");

    String code_article;


    etiquette_tpe_3code(final String log) {
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

        code_enie_jtext.setEditable(false);
        Date actuelle=new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date_jour = dateFormat.format(actuelle);

        date_picker.setDate(Calendar.getInstance().getTime());
        date_picker.getEditor().setEditable(false);
        date_picker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        Calendar cal = Calendar.getInstance();

        chaine_comb = new jcombo(list_chaine.toArray());
        selectioncomb.selectchaine(chaine_comb, this, logi_prio);
        chaine_comb.setSelectedIndex(0);
        MaskFormatter num = null;
        try {
            num = new MaskFormatter("###############");

        }
        catch (ParseException e1) {
            e1.printStackTrace();
        }


        MaskFormatter num2 = null;
        try {
            num2 = new MaskFormatter("###############");

        }
        catch (ParseException e1) {
            e1.printStackTrace();
        }


        imei_jtext= new  JFormattedTextField(num);



        action_date();



        dimension_comb.addItem("--- Sélectionner la dimension de l'etiquette ----");

        list_dimension=imp.select_dimension_etq("etq_tpe_3code");

        for(int i=0;i<list_dimension.size();i++)
        {
            //Pour affecter une valeur de base de donn?es ? un Combobox
            dimension_comb.addItem(list_dimension.get(i));

        }


        chaine_comb.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent arg0) {
                action_date();
            }
        });





        date_picker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action_date();

            }});

        frame.setUndecorated(true);
        code_enie_jtext.setEditable(false);




        list_m = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un Modèle----"}));
        model_comb = new jcombo(list_m.toArray());
        selectioncomb.selectarticle_tpe(model_comb, this, logi_prio);

        model_comb.setSelectedIndex(0);




        code_enie_jtext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code_enie=code_enie_jtext.getText();
                if(code_enie.equals("")) {
                    JOptionPane.showMessageDialog(
                                    null,
                                    "Veuillez remplir le code enie",
                                    "", JOptionPane.INFORMATION_MESSAGE);
                }
                   else if(imp.exist_codeenie_tpe(code_enie)==true){
                     ArrayList<String> tpe_liste=  imp.selection_3tpe_champ(code_enie,sn_jtext.getText(),imei_jtext.getText());
                    System.out.println(tpe_liste.get(1));

                    System.out.println(tpe_liste.get(1));
                      //  code_enie_jtext.setText(imp.code_enie);
                        sn_jtext.setText(tpe_liste.get(1));
                        imei_jtext.setText(tpe_liste.get(2));
                       for(int i=0;i<chaine_comb.getItemCount();i++){
                            if (chaine_comb.getItemAt(i).toString().contains(tpe_liste.get(3))) {
                                chaine_comb.setSelectedIndex(i);
                            }
                        }
                        for(int i=0;i<model_comb.getItemCount();i++){
                            if (model_comb.getItemAt(i).toString().contains(tpe_liste.get(4))) {
                                model_comb.setSelectedIndex(i);
                            }
                        }


                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date d = null;
                        try {

                            Date date = formatter.parse(tpe_liste.get(5).substring(0,10));
                            date_picker.setDate(date);
                        } catch (ParseException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }



                        imp_etq.setVisible(false);
                        but_modif.setVisible(false);
                        retour.setVisible(true);
                        but_sauv.setVisible(false);
                        valid_ajou.setVisible(false);
                        valid_modif.setVisible(true);

                    }
                    else{

                        imp_etq.setVisible(false);
                        but_modif.setVisible(true);
                        retour.setVisible(false);
                        but_sauv.setVisible(true);
                        valid_ajou.setVisible(false);
                        valid_modif.setVisible(false);
                        model_comb.setSelectedIndex(0);
                        chaine_comb.setSelectedIndex(0);
                        sn_jtext.setText("");

                        imei_jtext.setText("");
                        code_enie_jtext.setText("");
                        date_picker.setDate(Calendar.getInstance().getTime());

                        date_picker.setEditable(true);
                        chaine_comb.setEnabled(true);

                        JOptionPane.showMessageDialog(
                                        null,
                                        "Ce TPE n'existe pas",
                                        "", JOptionPane.ERROR_MESSAGE);
                    }

                }




        });




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




        but_sauv.setVisible(true);
        retour.setVisible(false);
        valid_ajou.setVisible(false);

        but_modif.setVisible(true);
        valid_modif.setVisible(false);

        imp_etq.setVisible(false);
        //	imp_imei.setVisible(false);

        LineBorder border = new LineBorder(Color.white, 1, true);
        TitledBorder titl2 = new TitledBorder(border, "Etiquette TPE ",
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
                but_sauv.setVisible(false);
                retour.setVisible(true);
                valid_ajou.setVisible(true);
                imp_etq.setVisible(false);
                but_modif.setVisible(false);
                valid_modif.setVisible(false);
                code_enie_jtext.setEditable(false);
                action_date();

            }
        });


        but_modif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //change
                LineBorder border = new LineBorder(Color.white, 1, true);
                TitledBorder titl2 = new TitledBorder(border, "Modifier",
                        TitledBorder.DEFAULT_POSITION,
                        TitledBorder.DEFAULT_POSITION, police2, Color.white);
                pan.setBorder(titl2);

                but_sauv.setVisible(false);
                retour.setVisible(true);
                valid_ajou.setVisible(false);
                imp_etq.setVisible(false);
                but_modif.setVisible(false);
                valid_modif.setVisible(true);
                code_enie_jtext.setText("");
                code_enie_jtext.setEditable(true);

                date_picker.setEditable(false);
                chaine_comb.setEnabled(false);

            }
        });


        sn_jtext.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if(evt.getKeyCode()== KeyEvent.VK_ENTER)
                {
                    imei_jtext.requestFocus();
                }



            }
        });

        imei_jtext.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if(imei_jtext.getText().trim().length()==0){
                    imei_jtext.setText("");
                }

            }
        });



            model_comb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (model_comb.getSelectedIndex() != 0 && model_comb.getSelectedItem()!=null) {
                    String[] art = model_comb.getSelectedItem().toString().split(" ");
                    code_article = art[0].toString(); // 004
                }}});

        valid_ajou.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                att= new Thread(){
                    public void run() {
                      //  String msg="";
                        validationChamp();

                        boolean existe;
                        existe=imp.exist_imei_sn(sn_jtext.getText(), imei_jtext.getText());

                         System.out.println("esexisssssssstttttttt "+msg);

                        if (msg.equals("")&&existe==false
                        ) {

                            chaine=chaine_comb.getSelectedItem().toString().split(" ")[0].trim();

                            imp.insert_Tpe_3Code(code_enie_jtext.getText(),sn_jtext.getText(), imei_jtext.getText(), chaine,code_article,date_picker.getEditor().getText());
                            JOptionPane.showMessageDialog(null, "l'étiquette TPE " + code_enie_jtext.getText()	+ "  a été bien ajouté");

                            retour.setVisible(true);
                            valid_ajou.setVisible(false);
                            but_sauv.setVisible(false);
                            imp_etq.setVisible(true);
                            valid_modif.setVisible(false);
                            but_modif.setVisible(false);

                        } else if (!msg.equals("")) {
                            JOptionPane.showMessageDialog(panel, msg, "Error", JOptionPane.ERROR_MESSAGE);
                        }	}};
                att.start();
            }
        });




        valid_modif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                att= new Thread(){
                    public void run() {
                        validationChamp();
                        System.out.println("msg"+msg);
                        boolean existe;
                        existe=imp.exist_tpe_modif(sn_jtext.getText(), imei_jtext.getText(), code_enie_jtext.getText());

                        if (msg.equals("")&&existe==false) {
                            selectioncomb.closePdf();

                            retour.setVisible(true);
                            valid_ajou.setVisible(false);
                            but_sauv.setVisible(false);
                            imp_etq.setVisible(true);
                            valid_modif.setVisible(false);
                            but_modif.setVisible(false);

                          imp.update_tpe_3code(sn_jtext.getText(), imei_jtext.getText(), code_article, code_enie_jtext.getText() );

                            JOptionPane.showMessageDialog(null, "l'étiquette TPE " + code_enie_jtext.getText()	+ "  a été bien Modifier");

                        }else if (!msg.equals("")) {
                            JOptionPane.showMessageDialog(panel, msg, "Error", JOptionPane.ERROR_MESSAGE);

                        }	}};
                att.start();
            }
        });




        dimension_comb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(dimension_comb.getSelectedIndex()!=0)
                    report=imp.select_report(dimension_comb.getSelectedItem().toString(),"etq_tpe_3code");


            }});
        sn_jtext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {//
                if(sn_jtext.getText().contains("SN") || sn_jtext.getText().contains("sn")){
                    String[] test=sn_jtext.getText().split(";");
                    String[] sn=test[0].split(":");

                    sn_jtext.setText(""+sn[1]);
                    String[] imei=test[1].split(":");
                    imei_jtext.setText(""+imei[1]);
                }

                sn_imei_action();

            }});


        imei_jtext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {//

                sn_imei_action();

            }});


                imp_etq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {// dispose();
                att= new Thread(){
                    public void run(){
                        if(dimension_comb.getSelectedIndex()!=0){

                            String parcour ;String model;
                            parcour = "C:\\GCOBAR\\pdf\\etq_tpe_3code\\"+dimension_comb.getSelectedItem()+"_"+code_enie_jtext.getText()+
                                    "_Etiquette_Tpe_3Code"+".pdf";
                            model = "C:\\GCOBAR\\CODE\\"+report;
                            File fichier = new File(parcour);
                            fichier.delete();
                            UIManager.put("nimbusOrange", (new Color(70,130,180)));

                            try {

                                //
                                new BufferedReader(new FileReader(
                                        parcour));

                                try {
                                    Desktop.getDesktop().open(new File(parcour));

                                } catch (IOException p) {
                                    // TODO Auto-generated catch block
                                    p.printStackTrace();

                                }}

                            catch (FileNotFoundException fnfe) {

                                listrc1.add(code_enie_jtext.getText());
                                selectioncomb.closePdf();
                                selectioncomb.imprimer("code_enie_parm",listrc1,bdd,parcour,model);
                                listrc1.clear();

                                sn_jtext.setText("");
                                imei_jtext.setText("");

                                but_sauv.setVisible(true);
                                imp_etq.setVisible(false);
                                valid_ajou.setVisible(false);
                                retour.setVisible(false);
                                valid_modif.setVisible(false);
                                but_modif.setVisible(true);
                                action_date();

                                code_enie_jtext.setEditable(false);

                                date_picker.setEditable(true);
                                chaine_comb.setEnabled(true);
                                sn_jtext.setText("");
                                imei_jtext.setText("");
                                sn_jtext.requestFocus();

                            }}
                        else{  JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette");}

                    }};

                att.start();

            }
        });




        but_sauv.setVisible(true);
        but_modif.setVisible(true);
        imp_etq.setVisible(false);
        //imp_imei.setVisible(false);
        valid_ajou.setVisible(false);
        retour.setVisible(false);

        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LineBorder border = new LineBorder(Color.white, 1, true);
                TitledBorder titl2 = new TitledBorder(border, "Edition",
                        TitledBorder.DEFAULT_POSITION,
                        TitledBorder.DEFAULT_POSITION, police2, Color.white);
                pan.setBorder(titl2);

              //  color_comb.setSelectedIndex(0);
              //  model_comb.setSelectedIndex(0);
                sn_jtext.setText("");
                imei_jtext.setText("");
                but_sauv.setVisible(true);
                but_modif.setVisible(true);
                imp_etq.setVisible(false);
                retour.setVisible(false);
                valid_ajou.setVisible(false);
                valid_modif.setVisible(false);
                action_date();
                code_enie_jtext.setEditable(false);

                date_picker.setEditable(true);
                chaine_comb.setEnabled(true);

            }
        });




        generale.styles("Nimbus");
        SwingUtilities.updateComponentTreeUI(this);



        model_lab.setForeground(Color.white);
        code_enie_lab.setForeground(Color.white);
        chaine_lab.setForeground(Color.white);
        sn_lab.setForeground(Color.white);
        imei_lab.setForeground(Color.white);
        date_lab.setForeground(Color.white);
        dimension_lab.setForeground(Color.white);


        model_lab.setFont(police2);
        sn_lab.setFont(police2);
        imei_lab.setFont(police2);
        code_enie_lab.setFont(police2);
        date_lab.setFont(police2);
        dimension_lab.setFont(police2);
        chaine_lab.setFont(police2);



        /*************************code_com pour le code commercial************/

        pan_date.add(pan_date_lab);
        pan_date_lab.add(date_lab);
        pan_date.add(pan_date_jtext);
        pan_date_jtext.add(date_picker);


        pan_date.setOpaque(false);
        pan_date_lab.setOpaque(false);
        pan_date_jtext.setOpaque(false);

        pan_dimension.add(pan_dimension_lab);
        pan_dimension_lab.add(dimension_lab);
        pan_dimension.add(pan_dimension_comb);
        pan_dimension_comb.add(dimension_comb);

        pan_dimension.setOpaque(false);
        pan_dimension_lab.setOpaque(false);
        pan_dimension_comb.setOpaque(false);




        pan_model.add(pan_model_lab);
        pan_model_lab.add(model_lab);
        pan_model.add(pan_model_comb);
        pan_model_comb.add(model_comb);



        pan_model.setOpaque(false);
        pan_model_lab.setOpaque(false);
        pan_model_comb.setOpaque(false);



        pan_chaine.add(pan_chaine_lab);
        pan_chaine_lab.add(chaine_lab);
        pan_chaine.add(pan_chaine_comb);
        pan_chaine_comb.add(chaine_comb);


        pan_chaine.setOpaque(false);
        pan_chaine_lab.setOpaque(false);
        pan_chaine_comb.setOpaque(false);



        pan_code_enie.add(pan_code_enie_lab);
        pan_code_enie_lab.add(code_enie_lab);
        pan_code_enie.add(pan_code_enie_jtext);
        pan_code_enie_jtext.add(code_enie_jtext);

        pan_code_enie.setOpaque(false);
        pan_code_enie_lab.setOpaque(false);
        pan_code_enie_jtext.setOpaque(false);


        pan_sn.add(pan_sn_lab);
        pan_sn_lab.add(sn_lab);
        pan_sn.add(pan_sn_jtext);
        pan_sn_jtext.add(sn_jtext);

        pan_imei.add(pan_imei_lab);
        pan_imei_lab.add(imei_lab);
        pan_imei.add(pan_imei_jtext);
        pan_imei_jtext.add(imei_jtext);

        pan_sn.setOpaque(false);
        pan_sn_lab.setOpaque(false);
        pan_sn_jtext.setOpaque(false);

        pan_imei.setOpaque(false);
        pan_imei_lab.setOpaque(false);
        pan_imei_jtext.setOpaque(false);



        pan_button.add(valid_ajou);
        pan_button.add(but_sauv);
        pan_button.add(but_modif);
        pan_button.add(valid_modif);

        pan_button.add(imp_etq);
        //pan_button.add(imp_imei);
        pan_button.add(retour);
        pan_button.setOpaque(false);


        pan_form.add(pan_model);


        pan_form.add(pan_code_enie);
        pan_form.add(pan_chaine);
        pan_form.add(pan_sn);

        //pan_form.add(pan_model);
        pan_form.add(pan_imei);
         pan_form.add(pan_date);

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
        //	imp_imei.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

        valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

        but_sauv.setPreferredSize(new Dimension(120, 33));
        imp_etq.setPreferredSize(new Dimension(170, 33));


        but_modif.setPreferredSize(new Dimension(120, 33));
        valid_modif.setPreferredSize(new Dimension(120, 33));
        //	imp_imei.setPreferredSize(new Dimension(170, 33));

        retour.setPreferredSize(new Dimension(120, 33));
        valid_ajou.setPreferredSize(new Dimension(120, 33));



        code_enie_jtext.setPreferredSize(new Dimension(210, 30));
        model_comb.setPreferredSize(new Dimension(210, 30));
        chaine_comb.setPreferredSize(new Dimension(210, 30));
        sn_jtext.setPreferredSize(new Dimension(210, 30));
        imei_jtext.setPreferredSize(new Dimension(210, 30));
        date_picker.setPreferredSize(new Dimension(210, 30));
        dimension_comb.setPreferredSize(new Dimension(210, 30));



        pan_model.setLayout(new BoxLayout(pan_model, BoxLayout.X_AXIS));
        pan_chaine.setLayout(new BoxLayout(pan_chaine, BoxLayout.X_AXIS));
        pan_code_enie.setLayout(new BoxLayout(pan_code_enie, BoxLayout.X_AXIS));
        pan_sn.setLayout(new BoxLayout(pan_sn, BoxLayout.X_AXIS));
        pan_imei.setLayout(new BoxLayout(pan_imei, BoxLayout.X_AXIS));
        pan_date.setLayout(new BoxLayout(pan_date, BoxLayout.X_AXIS));
        pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));





        pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));



        pan_chaine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
        pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        pan_date_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_date_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
        pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        pan_model_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_model_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_model_lab.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        pan_model_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        pan_code_enie_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_code_enie_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_code_enie_lab.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        pan_code_enie_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_sn_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_sn_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_sn_lab.setBorder(BorderFactory.createEmptyBorder(0, 140, 0, 0));
        pan_sn_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_imei_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_imei_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_imei_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
        pan_imei_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        setTitle("imprime code enie tpe");
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

                    new etiquette_tpe_3code("7");
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





    public void validationChamp(){
        msg="";
        if (sn_jtext.getText().trim().equals("")) {
            msg += "Veuillez remplir  le SN \n";
        }
        else if (imei_jtext.getText().trim().equals("")) {
            msg += "Veuillez remplir  le Imei  \n";
        }


        else if(imei_jtext.getText().trim().length()<15)
        {
            msg += "Le code imei  doit contenir 15 nombres  \n";
        }

        else if (model_comb.getSelectedIndex()==0) {
            msg += "Veuillez selectionner le modele de TPE \n";
        }

    }
    public String action_date(){

        chaine=chaine_comb.getSelectedItem().toString().split(" ")[0].substring(2).trim();
        System.out.println("chaine  "+chaine);

        Calendar cal = Calendar.getInstance();

        String aff=imp.afficher_conteur_tpe_3code(date_picker.getDate(),chaine,"etiquette_tpe_3code","TPF");
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

        code = "TPF" +chaine+ jour+ week + anne   + aff;
        code_enie_jtext.setText(code);
        return code;
    }



    public void sn_imei_action(){


        boolean existe=imp.exist_imei_sn(sn_jtext.getText(), imei_jtext.getText());

        if(existe==true){
            System.out.println(sn_jtext.getText());
           ArrayList<String> tpe_liste= imp.selection_3tpe_champ(code_enie_jtext.getText(),sn_jtext.getText(),imei_jtext.getText());
            code_enie_jtext.setText(tpe_liste.get(0));
            sn_jtext.setText(tpe_liste.get(1));
            imei_jtext.setText(tpe_liste.get(2));
            for(int i=0;i<chaine_comb.getItemCount();i++){
                if (chaine_comb.getItemAt(i).toString().contains(tpe_liste.get(3))){
                    chaine_comb.setSelectedIndex(i);
                }
            }

            for(int i=0;i<model_comb.getItemCount();i++){
                if (model_comb.getItemAt(i).toString().contains(tpe_liste.get(4))) {
                    model_comb.setSelectedIndex(i);
                }
            }


            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d = null;
            try {
                Date date = formatter.parse(tpe_liste.get(5).substring(0,10));
                date_picker.setDate(date);
                } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }



            imp_etq.setVisible(false);
            but_modif.setVisible(false);
            retour.setVisible(true);
            but_sauv.setVisible(false);
            valid_ajou.setVisible(false);
            valid_modif.setVisible(true);

        }
        else{

            imp_etq.setVisible(false);
            but_modif.setVisible(true);
            retour.setVisible(false);
            but_sauv.setVisible(true);
            valid_ajou.setVisible(false);
            valid_modif.setVisible(false);
            model_comb.setSelectedIndex(0);
            chaine_comb.setSelectedIndex(0);
            date_picker.setDate(Calendar.getInstance().getTime());
            date_picker.setEditable(true);
            chaine_comb.setEnabled(true);


        }

    }
}

