package logiciel_etiq.View.Etiquette.TPE;

import logiciel_etiq.Controller.gestion_imp_tpe;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import logiciel_etiq.constant;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;


public class etiquette_tpe extends generale {

    private JPanel pan_article = new JPanel();
    private JPanel pan_article_lab = new JPanel();
    private JPanel pan_article_comb= new JPanel();
    private JLabel article_lab = new JLabel("Article");
    private jcombo article_comb =new jcombo(list_comb);


    private JPanel pan_chaine = new JPanel();
    private JPanel pan_chaine_lab = new JPanel();
    private JPanel pan_chaine_comb= new JPanel();
    private JLabel chaine_lab = new JLabel("Chaine");
    private  jcombo chaine_comb =new jcombo(list_comb);


    private JPanel pan_code_enie = new JPanel();
    private JPanel pan_code_enie_jtext = new JPanel();
    private JPanel pan_code_enie_lab = new JPanel();
    private JLabel code_enie_lab = new JLabel("Code ENIE");
    private JTextField code_enie_jtext=new JTextField();



    private JPanel pan_sn_lab = new JPanel();
    private JPanel pan_sn = new JPanel();
    private JPanel pan_sn_jtext = new JPanel();
    private JLabel sn_lab = new JLabel("SN");
    private JTextField sn_jtext=new JTextField();


    private JPanel pan_imei_lab = new JPanel();
    private JPanel pan_imei = new JPanel();
    private JPanel pan_imei_jtext = new JPanel();
    private JLabel imei_lab = new JLabel("IMEI");
    private JTextField imei_jtext=new JTextField();



    private JPanel pan_date = new JPanel();
    private JPanel pan_date_lab = new JPanel();
    private JPanel pan_date_jtext= new JPanel();
    private JLabel date_lab = new JLabel("Date");
    private final JXDatePicker date_picker = new JXDatePicker();


    private JPanel pan_dimension = new JPanel();
    private JPanel pan_dimension_lab = new JPanel();
    private JPanel pan_dimension_comb = new JPanel();
    private JLabel dimension_lab = new JLabel("Etiquette dimension");
    private JComboBox<String> dimension_comb= new JComboBox<>();



    private JPanel pan = new JPanel();
    private JPanel pan_general = new JPanel();
    private JPanel pan_form = new JPanel();
    private JPanel pan_gener = new JPanel();



    private JPanel pan_button = new JPanel();
    private gestion_imp_tpe imp_tpe=new gestion_imp_tpe();

    private String code="";

    public etiquette_tpe(final String log) {

        composant(log);
    }

    public void composant(final String logi_prio) {



        JPanel panel = generalPanle();
        declarationButton(pan_button);
        visibiliteButton(true, false, false, false,
                false, false,false);
        initFrame("Etiquette TPE", pan, this, logi_prio,"Etiquette TPE");
        code_enie_jtext.setEditable(false);
        imp_etq.setVisible(false);



        replirDate(date_picker);
        date_picker.addActionListener(e -> action_date());


        remplirChaine(chaine_comb);
        chaine_comb.addActionListener(arg0 -> {
            code_chaine=splitcombo(chaine_comb);
            action_date();


        });



        remplirArticleType(article_comb,3 );
        article_comb.addActionListener(e ->{
                    code_article=splitcombo(article_comb);
                    if(article_comb.getSelectedIndex()==0){
                        sn_jtext.setText("");
                        imei_jtext.setText("");
                        chaine_comb.setSelectedIndex(0);
                        date_picker.setDate(Calendar.getInstance().getTime());
                        action_date();
                    }
                }
        );

        action_date();








        imei_jtext.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if(evt.getKeyCode()== KeyEvent.VK_ENTER)
                {
                    sn_jtext.requestFocus();
                }
            }
        });



        sn_jtext.addActionListener(event -> {


            if(sn_jtext.getText().contains("SN") || sn_jtext.getText().contains("sn")){
                String[] test=sn_jtext.getText().split(";");
                String[] sn=test[0].split(":");

                sn_jtext.setText(""+sn[1]);
                String[] imei=test[1].split(":");
                imei_jtext.setText(""+imei[1]);
            }

            code=sn_jtext.getText();


            code_enie_sn_imei_action();


        });

        code_enie_jtext.addActionListener(e -> {
            code=code_enie_jtext.getText();
            code_enie_sn_imei_action();
        });

        imei_jtext.addActionListener(event -> {

            code=imei_jtext.getText();
            code_enie_sn_imei_action();

        });







        retour.addActionListener(e -> {
            titleFrame("Edition Etiqette TPE", pan);
            visibiliteButton(true, false, false, false,
                    false, false,false);
            imp_etq.setVisible(false);
            article_comb.setSelectedIndex(0);
        });






        ajouter.addActionListener(e -> {
            titleFrame("Ajouter etiquette TPE",pan);
            visibiliteButton(false, false, false, true,
                    true, false,false);
            imp_etq.setVisible(false);
        });


        modifier.addActionListener(e -> {
            titleFrame("Modifier etiquette TPE",pan);
            visibiliteButton(false, false, false, true,
                    false, true,false);
        });






        valid_ajou.addActionListener(e -> {

                    String msg=validationChamp();

                    boolean existe=imp_tpe.exist_imei_sn_code(sn_jtext.getText(), imei_jtext.getText(),code_enie_jtext.getText());


                    if (msg.equals("")&&!existe) {

                        imp_tpe.insert_Tpe(code_enie_jtext.getText(),sn_jtext.getText(), imei_jtext.getText(), code_chaine,code_article,date_picker.getDate());
                        JOptionPane.showMessageDialog(null, "l'étiquette TPE " + code_enie_jtext.getText()	+ "  a été bien ajouté");

                        visibiliteButton(false, false, false, true,
                                false, false,false);
                        imp_etq.setVisible(true);


                    } else if (!msg.equals("")) {
                        JOptionPane.showMessageDialog(panel, msg, "Error", JOptionPane.ERROR_MESSAGE);
                    }
        });




        valid_modif.addActionListener(e -> {

                  String msg= validationChamp();
                   boolean existe=imp_tpe.exist_imei_sn_code(sn_jtext.getText(), imei_jtext.getText(),code_enie_avant);

                    if (msg.equals("")&&!existe) {

                        visibiliteButton(false, false, false, true,
                                false, false,false);
                        imp_etq.setVisible(true);

                        imp_tpe.update_tpe(sn_jtext.getText(), imei_jtext.getText(), code_article, code_enie_jtext.getText(), code_enie_avant,date_picker.getDate(),code_chaine);

                        JOptionPane.showMessageDialog(null, "l'étiquette TPE " + code_enie_jtext.getText()	+ "  a été bien Modifier");

                    }
                    else if (!msg.equals("")) {
                        JOptionPane.showMessageDialog(panel, msg, "Error", JOptionPane.ERROR_MESSAGE);

                    }
        });





        remplirActDimComb( dimension_comb,"etiquette_tpe");
        dimension_comb.setSelectedIndex(0);





        imp_etq.addActionListener(
                event -> {

                    if (dimension_comb.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(panel, "Vous devez choisir un type d'étiquette", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        System.out.println("index   2"+dimension_comb.getSelectedIndex());

                        code_aimprimer.add(code_enie_jtext.getText());

                        parameters.put("code_enie_parm", code_aimprimer);
                        String parcour = constant.etiq_tpe + dimension_comb.getSelectedItem() + "_" + code_enie_jtext.getText() + ".pdf";

                        Imprimer_pdf_Final( parcour, report, parameters);
                        date_picker.setDate(Calendar.getInstance().getTime());

                        sn_jtext.setText("");
                        imei_jtext.setText("");

                        action_date();

                        imei_jtext.requestFocus();

                        visibiliteButton(false, false, false, true,
                                true, false, false);
                        imp_etq.setVisible(false);
                    }
                });









     ////////////////////////*********************style de lab
        styleButton(imp_etq,pan_button);
        styleLabel(article_lab);
        styleLabel(code_enie_lab);
        styleLabel(chaine_lab);
        styleLabel(date_lab);
        styleLabel(sn_lab);
        styleLabel(imei_lab);
        styleLabel(dimension_lab);




       // /*************************add component in pane************/

        pan_date.add(pan_date_lab);
        pan_date_lab.add(date_lab);
        pan_date.add(pan_date_jtext);
        pan_date_jtext.add(date_picker);



        pan_dimension.add(pan_dimension_lab);
        pan_dimension_lab.add(dimension_lab);
        pan_dimension.add(pan_dimension_comb);
        pan_dimension_comb.add(dimension_comb);



        pan_article.add(pan_article_lab);
        pan_article_lab.add(article_lab);
        pan_article.add(pan_article_comb);
        pan_article_comb.add(article_comb);


        pan_chaine.add(pan_chaine_lab);
        pan_chaine_lab.add(chaine_lab);
        pan_chaine.add(pan_chaine_comb);
        pan_chaine_comb.add(chaine_comb);


        pan_code_enie.add(pan_code_enie_lab);
        pan_code_enie_lab.add(code_enie_lab);
        pan_code_enie.add(pan_code_enie_jtext);
        pan_code_enie_jtext.add(code_enie_jtext);

        pan_sn.add(pan_sn_lab);
        pan_sn_lab.add(sn_lab);
        pan_sn.add(pan_sn_jtext);
        pan_sn_jtext.add(sn_jtext);

        pan_imei.add(pan_imei_lab);
        pan_imei_lab.add(imei_lab);
        pan_imei.add(pan_imei_jtext);
        pan_imei_jtext.add(imei_jtext);




        pan_form.add(pan_article);
        pan_form.add(pan_code_enie);
        pan_form.add(pan_chaine);
        pan_form.add(pan_imei);
        pan_form.add(pan_sn);
        pan_form.add(pan_date);
        pan_form.add(pan_dimension);
        pan_general.add(pan_button);


        pan_gener.add(pan_form);
        pan.add(pan_gener);
        pan.add(pan_general);

        panel.add(pan);




        //////////****************visibilite de panel **************/
        pan_sn.setOpaque(false);
        pan_sn_lab.setOpaque(false);
        pan_sn_jtext.setOpaque(false);

        pan_imei.setOpaque(false);
        pan_imei_lab.setOpaque(false);
        pan_imei_jtext.setOpaque(false);
        pan_dimension.setOpaque(false);
        pan_dimension_lab.setOpaque(false);
        pan_dimension_comb.setOpaque(false);


        pan_date.setOpaque(false);
        pan_date_lab.setOpaque(false);
        pan_date_jtext.setOpaque(false);

        pan_article.setOpaque(false);
        pan_article_lab.setOpaque(false);
        pan_article_comb.setOpaque(false);



        pan_chaine.setOpaque(false);
        pan_chaine_lab.setOpaque(false);
        pan_chaine_comb.setOpaque(false);


        pan_code_enie.setOpaque(false);
        pan_code_enie_lab.setOpaque(false);
        pan_code_enie_jtext.setOpaque(false);

        pan_form.setOpaque(false);
        pan_button.setOpaque(false);
        pan_gener.setOpaque(false);
        pan_general.setOpaque(false);
        pan.setOpaque(false);


        /////****************************************add layout to panel******************************/

        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));
        pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));

        pan_article.setLayout(new BoxLayout(pan_article, BoxLayout.X_AXIS));
        pan_chaine.setLayout(new BoxLayout(pan_chaine, BoxLayout.X_AXIS));
        pan_code_enie.setLayout(new BoxLayout(pan_code_enie, BoxLayout.X_AXIS));
        pan_sn.setLayout(new BoxLayout(pan_sn, BoxLayout.X_AXIS));
        pan_imei.setLayout(new BoxLayout(pan_imei, BoxLayout.X_AXIS));
        pan_date.setLayout(new BoxLayout(pan_date, BoxLayout.X_AXIS));
        pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));

        pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_chaine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_date_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_date_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_article_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_code_enie_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_code_enie_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_sn_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_sn_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_imei_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_imei_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));






        // /***********************Border de panel**************************/

        pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 118, 0, 0));
        pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 125, 0, 0));
        pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_article_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
        pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_code_enie_lab.setBorder(BorderFactory.createEmptyBorder(0, 85, 0, 0));
        pan_code_enie_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_sn_lab.setBorder(BorderFactory.createEmptyBorder(0, 137, 0, 0));
        pan_sn_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_imei_lab.setBorder(BorderFactory.createEmptyBorder(0, 125, 0, 0));
        pan_imei_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));




       // /******************component size**************************/
        styleComponent(code_enie_jtext);
        styleComponent(article_comb);
        styleComponent(chaine_comb);
        styleComponent(sn_jtext);
        styleComponent(imei_jtext);
        styleComponent(date_picker);
        styleComponent(dimension_comb);


        setContentPane(panel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {

                new etiquette_tpe("7");
                // frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }







    private String validationChamp(){
        code_aimprimer.clear();

        String msg="";
        if (imei_jtext.getText().trim().equals("")) {
            msg += "Veuillez remplir  le Imei  \n";
        }

        else if (article_comb.getSelectedIndex()==0) {
            msg += "Veuillez selectionner le modele de TPE \n";
        }
        else if (chaine_comb.getSelectedIndex()==0) {
            msg += "Veuillez selectionner la chaine \n";
        }
        return msg;

    }
    String code_enie_avant="";

    private void action_date(){

         if(chaine_comb.getSelectedIndex()!=0)
            code_chaine=splitcombo(chaine_comb).substring(2).trim();
         String code=imp_tpe.afficher_conteur(date_picker.getDate(),code_chaine,"etiquette_tpe","TPF","code_enie","date_tpe");
        code_enie_jtext.setText(code);
    }


    private void code_enie_sn_imei_action(){

        ArrayList<String> tpe_liste=imp_tpe.selection_tpe_champ(code);

        if(tpe_liste.size()!=0){


            code_enie_jtext.disable();

            for(int i=0;i<chaine_comb.getItemCount();i++){
                if (chaine_comb.getItemAt(i).toString().contains(tpe_liste.get(3))){
                    chaine_comb.setSelectedIndex(i);
                }
            }

            for(int i=0;i<article_comb.getItemCount();i++){
                if (article_comb.getItemAt(i).toString().contains(tpe_liste.get(4))) {
                    article_comb.setSelectedIndex(i);
                }
            }
            code_enie_avant=tpe_liste.get(0);
            code_enie_jtext.setText(tpe_liste.get(0));
            sn_jtext.setText(tpe_liste.get(1));
            imei_jtext.setText(tpe_liste.get(2));
            try {
                date_picker.setDate(dateform.parse(tpe_liste.get(5)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            visibiliteButton(false, true, false, true,
                    false, false,false);
            imp_etq.setVisible(false);


        }
        else{

            imp_etq.setVisible(false);
            visibiliteButton(true, false, false, true,
                    false, false,false);
            //retour.doClick();
            action_date();


        }



    }









}

