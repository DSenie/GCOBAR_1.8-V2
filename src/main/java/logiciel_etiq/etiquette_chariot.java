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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

public class etiquette_chariot extends JFrame {

    /**
     *
     */
    String report;

    private JPanel controlPanel = new JPanel();
    private JFrame frame = new JFrame();

    private static final long serialVersionUID = 1L;
    //private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    private String msg;
    private String Chemin = "c:\\GCOBAR\\";
    private String bdd = Chemin + Utilitaire.InitBdd() + ".accdb";

    private gestion_imp imp = new gestion_imp();
    private JProgressBar progressBar;

    private JPanel pan_chaine = new JPanel();
    private JPanel pan_chaine_comb = new JPanel();
    private JPanel pan_chaine_lab = new JPanel();
    private JLabel chaine_lab = new JLabel("Liste des Chaine");
    private List<String> list_c;
    private jcombo chaine_comb;



    private JPanel pan_color = new JPanel();
    private JPanel pan_color_comb = new JPanel();
    private JPanel pan_color_lab = new JPanel();
    private JLabel color_lab = new JLabel("Liste des Couleur");
    private List<String> list_col;
    private JComboBox color_comb=new JComboBox();



    private JPanel pan_code = new JPanel();
    private JPanel pan_code_jtext = new JPanel();
    private JPanel pan_code_lab = new JPanel();
    private JLabel code_lab = new JLabel("Serial Number");

    private JTextField code_jtext = new JTextField();



    private JPanel pan_date = new JPanel();
    private JPanel pan_date_jtext = new JPanel();
    private JPanel pan_date_lab = new JPanel();
    private JLabel date_lab = new JLabel("Date");
    final JXDatePicker date_picker = new JXDatePicker();



    private JPanel pan_cont = new JPanel();
    private JPanel pan_cont_jtext = new JPanel();
    private JLabel cont_lab = new JLabel("Conteur");
    private JTextField cont_jtext = new JTextField();
    private JPanel pan_cont_lab = new JPanel();

    private JTextField assemblage_jtext = new JTextField();
    private JTextField assqc_jtext = new JTextField();
    private JTextField actqc_jtext = new JTextField();

    private JPanel pan = new JPanel();
    private JPanel pan_general = new JPanel();
    private JPanel pan_form = new JPanel();
    private JPanel panel;


    private JPanel pan_dimension = new JPanel();
    private JPanel pan_dimension_lab = new JPanel();
    private JPanel pan_dimension_comb= new JPanel();
    private JLabel dimension_lab = new JLabel("Dimension");
    private JComboBox<String> dimension_comb =new JComboBox<String>();



    private JPanel pan_button = new JPanel();

    private JButton but_sauv = new JButton("Ajouter");
    private JButton but_modif = new JButton("Modifier");
    private JButton valid_ajou = new JButton("Valider");
    private JButton valid_modif = new JButton("Valider");
    private JButton imp_etq = new JButton("Imprimer L'étiquette");
    private JButton retour = new JButton("Retour");
    private String conteur = null;
    private static ArrayList<String> list_model_tr=new ArrayList<String>();
    gestion_article art=new gestion_article();
    private JPanel pan_gener = new JPanel();
    private JPanel pan_tab = new JPanel();
    ArrayList <String>list_dimension= new ArrayList<String>() ;
    ArrayList <String>list_etq= new ArrayList<String>() ;

    String chaine;

    int co;
    Thread att;
    NumberFormat format = new DecimalFormat("000");
    NumberFormat formatter = new DecimalFormat("00");
    NumberFormat form = new DecimalFormat("0");
    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    Map<String, Object> parameters = new HashMap<String, Object>();
    etiquette_chariot(final String log) {
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
        color_comb.setEditable(true);



        dimension_comb.addItem("--Sélectionner la dimension de l'etiquette--");
        list_dimension=imp.select_dimension_etq("etiquette_chariot");

        for(int i=0;i<list_dimension.size();i++)
        {
            //Pour affecter une valeur de base de donn?es ? un Combobox
            dimension_comb.addItem(list_dimension.get(i));
        }


        list_col = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner une couleur-----"}));
        color_comb.addItem("---Selectionner une couleur-----");
        list_col=imp.select_color_chariot();
        for(int i=0;i<list_col.size();i++)
        {
            //Pour affecter une valeur de base de donn?es ? un Combobox
            color_comb.addItem(list_col.get(i));

        }

        color_comb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                boolean ex=produit.exist(color_comb,color_comb.getEditor().getItem().toString());
                if((ex==false))
                {
                    int cont =color_comb.getItemCount()+2;
                    color_comb.addItem(color_comb.getEditor().getItem().toString());
                }
            }});


        list_c = new ArrayList<String>(
                Arrays.asList(new String[] { "--Sélectionner une Chaine--" }));

        chaine_comb = new jcombo(list_c.toArray());
        selectioncomb.selectchaine(chaine_comb, this, logi_prio);



        final Calendar cal = Calendar.getInstance(); // date du jour
        if (!selectioncomb.prv.contains("etiquette_chariot"))
            selectioncomb.prv.add("etiquette_chariot");

        panel = new JPanel() {
            /***  */
            private static final long serialVersionUID = 1L;
            public void paintComponent(Graphics g) {
                ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
                img.paintIcon(this, g, 0, 0);
            }
        };

        final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,15);

        code_jtext.disable();
        chaine_comb.enable();
        if (chaine_comb.getItemCount() > 2)
            chaine_comb.setSelectedIndex(1);

        action_chaine();
        LineBorder border = new LineBorder(Color.white, 1, true);
        TitledBorder titl2 = new TitledBorder(border, "Etiquette Chariot",
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
                chaine_comb.enable();

                but_sauv.setVisible(false);
                but_modif.setVisible(false);
                retour.setVisible(true);
                valid_ajou.setVisible(true);
                valid_modif.setVisible(false);
                imp_etq.setVisible(false);
                chaine=chaine_comb.getSelectedItem().toString().split(" ")[0].substring(2).trim();

                imp.afficher_conteur_tpe(date_picker.getDate(),chaine,"imp_chariot","CT");



            }
        });



       /* chaine_comb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chaine=chaine_comb.getSelectedItem().toString().split(" ")[0].substring(2).trim();

                action_chaine();
            }

        });*/


        date_picker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cal.setTime(date_picker.getDate());
                action_chaine();
            }

        });


        valid_ajou.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                att= new Thread(){
                    public void run() {
                        msg = "";
                        cal.setTime(date_picker.getDate());
                        String date = format1.format(cal.getTime());
                        String[] date_s = date.toString().split("/");
                        String anne = date_s[2].substring(2); // 004
                        String[] part_c = chaine_comb.getSelectedItem().toString().split(" ");
                        String cc = part_c[0].substring(2); // 004
                        String code_chaine = part_c[0];
                        cal.setFirstDayOfWeek(Calendar.SUNDAY);
                        String couleur=color_comb.getSelectedItem().toString();

                        conteur = imp.afficher_conteur_chariot(date_picker.getDate(),"imp_chariot","CT");


                        cal.setTime(date_picker.getDate());
                        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                        String date2 = format1.format(cal.getTime());
                        String[] date_s2 = date.toString().split("/");
                        String anne2 = date_s[2].substring(2); // 004


                        cal.setFirstDayOfWeek(Calendar.SUNDAY);
                        int week2 = cal.get(Calendar.WEEK_OF_YEAR) ;
                        String week = formatter.format(week2);
                        int jour1 = cal.get(Calendar.DAY_OF_WEEK);
                        String jour = form.format(jour1);
                        String codec;

                        codec = "CT" + jour+ week + anne   + conteur;


                        if (color_comb.getSelectedIndex() == 0) {
                            msg += "Veuillez selectionner une couleur \n";
                        } else if (cont_jtext.getText().equals("")) {
                            msg += "Veuillez remplir le conteur \n";
                        } else if (isValid(cont_jtext.getText()) == false) {
                            msg += "Le compteur doit etre un nombre \n";
                        }


                        if (msg.equals("")) {
                            String etq=null;

                            retour.setEnabled(false);
                            valid_ajou.setEnabled(false);
                            co = Integer.parseInt(cont_jtext.getText());
                            int af_cont = Integer.parseInt(conteur);
                            progressBar = new JProgressBar(af_cont,af_cont+co-1);
                            progressBar.setValue(0);



                            JLabel titr=new JLabel("la création de "+co+" etiquette");
                            titr.setFont( titr.getFont().deriveFont(Font.BOLD|Font.ITALIC) );

                            UIManager.put("nimbusOrange", (new Color(70,130,180)));

                            frame.add(controlPanel);
                            titr.setFont(police2);
                            progressBar.setStringPainted(true);
                            controlPanel.add(titr);
                            controlPanel.add(progressBar);

                            progressBar.setPreferredSize( new Dimension (200, 25));
                            frame.setSize(300, 70);
                            frame.setLocationRelativeTo(null);
                            NumberFormat form1 = new DecimalFormat("0000");
                         //   String sf=form1.format(imp.ass/ imp.assqc );   //donne la chaine representant le double avec 2 chiffres apres la virgules
//			          int po;


//						System.out.println("tuple "+tuple+"po "+ po);
                            for(int i=af_cont;i<=af_cont+co-1;i++ ) {

                                System.out.println("count"+i+"  "+conteur);
                                String count=form1.format(i);
                                codec = "CT" + jour+ week + anne +count;
                                System.out.println("codec"+codec);
                                list_etq.add(codec);

                                imp.ajouter_chariot(codec,date_picker.getEditor().getText(),couleur);


                                //	CConnect.close();
                                progressBar.setValue(i);
                                progressBar.setStringPainted(true);
                                frame.validate();
                                frame.setVisible(true);

                            }
                           action_chaine();
                           // code_jtext.setText(conteur);
                            controlPanel.remove(titr);
                            controlPanel.remove(progressBar);
                            frame.setVisible(false);
                            valid_ajou.setVisible(false);
                            but_sauv.setVisible(true);
                            retour.setVisible(false);
                            JOptionPane.showMessageDialog(null, "l'étiquette a été bien ajouté");
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
                    report=imp.select_report(dimension_comb.getSelectedItem().toString(),"etiquette_chariot");

            }});


        imp_etq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {// dispose();
                att= new Thread(){
                    public void run(){
                        if(dimension_comb.getSelectedIndex()==0){
                            JOptionPane
                                    .showMessageDialog(
                                            null,
                                            " Vous dever choisir la dimension de l'étiquette ",
                                            "", JOptionPane.INFORMATION_MESSAGE);
                        }else {

                            BufferedReader bfr = null;
                            String parcour = null;
                            String model;

                            String code=code_jtext.getText();
                            String cont=cont_jtext.getText();
                            parcour = "C:\\GCOBAR\\pdf\\etq_chariot\\" + dimension_comb.getSelectedItem().toString().split(" ")[0] +"_"+code+"_"+ cont + "_Etiquette_chariot" + ".pdf";



                            //   parcour = "C:\\GCOBAR\\pdf\\etq_emballage_tpe\\"+dimension_comb.getSelectedItem().toString()+parcel+"_Etiquette_emballage_tpe"+".pdf";
                            model = "C:\\GCOBAR\\CODE\\" + report;
                            //imp.update_imp_s("1");
                            File fichier = new File(parcour);
                            fichier.delete();
                            UIManager.put("nimbusOrange", (new Color(70, 130, 180)));



                        UIManager.put("nimbusOrange", (new Color(70,130,180)));

                        try {
                            bfr = new BufferedReader(new FileReader(parcour));
                            bfr.close();
                            System.out.println("buffer");
                            try {
                                Desktop.getDesktop().open(new File(parcour));
                            } catch (IOException p) {
                                // TODO Auto-generated catch block
                                p.printStackTrace();
                            }
                        } catch (FileNotFoundException fnfe) {
                            System.out.println(list_etq+"what");
                            parameters.put("serial",list_etq);


                            selectioncomb.imprimer(bdd, parcour,model,parameters);
                            list_etq.clear();



                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                            //controlPanel.remove(progressBar);
                        }

                    }};

                att.start();

            }
        });



/*
        imp_etq.addActionListener(new ActionListener() {
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
                        }else {
                            String parcel = parcel_jtext.getText().replace("/", "_");
                            //int indice=article_comb.getSelectedIndex();
                            BufferedReader bfr = null;
                            Boolean pal = false;
                            String parcour = null;
                            String model;
                            if (dimension_comb.getSelectedItem().toString().contains("Palette")) {
                                pal = true;
                                parcour = "C:\\GCOBAR\\pdf\\etq_palette\\" + dimension_comb.getSelectedItem().toString().split(" ")[1] + palette_comb.getSelectedItem() + "_Etiquette_palette_tpe" + ".pdf";

                            } else if (!dimension_comb.getSelectedItem().toString().contains("Palette")) {
                                parcour = "C:\\GCOBAR\\pdf\\etq_emballage_tpe\\" + dimension_comb.getSelectedItem().toString().split(" ")[1] + parcel + "_Etiquette_emballage" + ".pdf";


                            }

                            //   parcour = "C:\\GCOBAR\\pdf\\etq_emballage_tpe\\"+dimension_comb.getSelectedItem().toString()+parcel+"_Etiquette_emballage_tpe"+".pdf";
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

                                if (pal == true) {

                                    String count = imp.select_count_palette("imp_emballage_tpe", palette_comb.getSelectedItem().toString());
                                    parameters.put("count", count);
                                } else {
                                    parameters.clear();
                                }


                                String code_text=code_jtext.getText().substring(0, 8);
                                System.out.println("eee"+code_text);
                                parameters.put("serial", code_text);
                                //parameters.put("model", model_comb.getSelectedItem());
                                selectioncomb.imprimer( bdd, parcour,model,parameters);




                                parameters.put("parcel", parcel_jtext.getText());
                                selectioncomb.imprimer(bdd, parcour, model, parameters);


                                //controlPanel.remove(progressBar);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                    }};

                att.start();

            }
        });*/

        but_sauv.setVisible(true);
        but_modif.setVisible(true);
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
                chaine_comb.setSelectedIndex(1);
                code_jtext.setText("");
                cont_jtext.setText("");
                but_sauv.setVisible(true);
                but_modif.setVisible(true);
                imp_etq.setVisible(false);
                retour.setVisible(false);
                valid_ajou.setVisible(false);
                valid_modif.setVisible(false);
                chaine_comb.enable();
                code_jtext.disable();
               /* String[] part_c = chaine_comb.getSelectedItem().toString().split(" ");
                String code_chaine = part_c[0];*/

                imp.afficher_conteur_chariot(date_picker.getDate(),"imp_chariot","CT");



                action_chaine();

            }
        });



        generale.styles("Nimbus");
        SwingUtilities.updateComponentTreeUI(this);



        chaine_lab.setForeground(Color.white);
        code_lab.setForeground(Color.white);
        cont_lab.setForeground(Color.white);
        dimension_lab.setForeground(Color.white);
        color_lab.setForeground(Color.white);

        date_lab.setForeground(Color.white);


        date_lab.setFont(police2);

        chaine_lab.setFont(police2);
        code_lab.setFont(police2);
        cont_lab.setFont(police2);

        /*************************code_com pour le code commercial************/


        pan_date.setOpaque(false);
        pan_date_lab.setOpaque(false);
        pan_date_jtext.setOpaque(false);

        pan_date.add(pan_date_lab);
        pan_date_lab.add(date_lab);
        pan_date.add(pan_date_jtext);
        pan_date_jtext.add(date_picker);

        pan_color.add(pan_color_lab);
        pan_color_lab.add(color_lab);
        pan_color.add(pan_color_comb);
        pan_color_comb.add(color_comb);

        pan_color.setOpaque(false);
        pan_color_lab.setOpaque(false);
        pan_color_comb.setOpaque(false);




        pan_chaine.add(pan_chaine_lab);
        pan_chaine_lab.add(chaine_lab);
        pan_chaine.add(pan_chaine_comb);
        pan_chaine_comb.add(chaine_comb);

        pan_chaine.setOpaque(false);
        pan_chaine_lab.setOpaque(false);
        pan_chaine_comb.setOpaque(false);

        pan_code.add(pan_code_lab);
        pan_code_lab.add(code_lab);
        pan_code.add(pan_code_jtext);
        pan_code_jtext.add(code_jtext);

        pan_code.setOpaque(false);
        pan_code_lab.setOpaque(false);
        pan_code_jtext.setOpaque(false);
        pan_cont.add(pan_cont_lab);
        pan_cont_lab.add(cont_lab);
        pan_cont.add(pan_cont_jtext);
        pan_cont_jtext.add(cont_jtext);

        pan_cont.setOpaque(false);
        pan_cont_lab.setOpaque(false);
        pan_cont_jtext.setOpaque(false);



        pan_dimension.add(pan_dimension_lab);
        pan_dimension_lab.add(dimension_lab);

        pan_dimension.add(pan_dimension_comb);
        pan_dimension_comb.add(dimension_comb);


        pan_dimension.setOpaque(false);
       pan_dimension_lab.setOpaque(false);
        pan_dimension_comb.setOpaque(false);



        pan_button.add(retour);
        pan_button.add(valid_ajou);
        pan_button.add(valid_modif);
        pan_button.add(but_sauv);
        // pan_button.add(but_modif);
        pan_button.add(imp_etq);

        pan_button.setOpaque(false);

        pan_form.add(pan_date);

        //pan_form.add(pan_model);
       // pan_form.add(pan_chaine);
        pan_form.add(pan_code);
        pan_form.add(pan_cont);
        pan_form.add(pan_color);
        pan_form.add(pan_dimension);

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

        but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        but_sauv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        dimension_comb.setPreferredSize(new Dimension(230, 30));
        color_lab.setPreferredSize(new Dimension(230, 30));
        color_lab.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

        dimension_lab.setPreferredSize(new Dimension(230, 30));
        dimension_lab.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

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

        color_comb.setPreferredSize(new Dimension(210, 30));


        date_picker.setPreferredSize(new Dimension(210, 30));
        chaine_comb.setPreferredSize(new Dimension(210, 30));
        code_jtext.setPreferredSize(new Dimension(210, 30));
        cont_jtext.setPreferredSize(new Dimension(210, 30));
        color_comb.setPreferredSize(new Dimension(210, 30));

        dimension_comb.setPreferredSize(new Dimension(210, 30));

        pan_date.setLayout(new BoxLayout(pan_date, BoxLayout.X_AXIS));
        pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));
        pan_code.setLayout(new BoxLayout(pan_code, BoxLayout.X_AXIS));

        pan_cont.setLayout(new BoxLayout(pan_cont, BoxLayout.X_AXIS));


        pan_color.setLayout(new BoxLayout(pan_color, BoxLayout.X_AXIS));





        pan_date_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_date_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 297, 0, 0));
        pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        pan_color_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_color_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_color_lab.setBorder(BorderFactory.createEmptyBorder(0, 320, 0, -20));
        pan_color_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200));



        pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 360, 0, -60));
        pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200));
        /*pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 480, 0, 0));
        pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 220));
*/
     /*   pan_chaine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 400, 0, 0));
        pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 250));*/

        pan_code_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_code_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_code_lab.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, 0));
        pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        pan_cont_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_cont_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_cont_lab.setBorder(BorderFactory.createEmptyBorder(0, 270, 0, 0));
        pan_cont_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

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

                    new etiquette("7");
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

    public String action_chaine() {
        final Calendar cal = Calendar.getInstance(); // date du jour




     //   if (chaine_comb.getSelectedIndex() != 0	&& chaine_comb.getItemCount() >= 1&&chaine_comb.getSelectedIndex() != chaine_comb.getItemCount() - 1) {

            String code;

            code=imp.afficher_conteur_chariot(date_picker.getDate(),"imp_chariot","CT");

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
        String codec;

        codec = "CT" + jour+ week + anne   + code;


            code_jtext.setText(codec);
            return code;
       // } else {

         //   code_jtext.setText("");
           // return "";			}


    }



}
