package logiciel_etiq.View.Etiquette.Phone;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.util.*;

import javax.swing.*;

import logiciel_etiq.Controller.gestion_imp_phone;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import logiciel_etiq.View.tableau.Tableau;
import logiciel_etiq.constant;
import org.jdesktop.swingx.JXDatePicker;

public class etiquette_emballage_portable extends generale {


    private JPanel pan_form = new JPanel();
    private JPanel pan_form1 = new JPanel();
    private JPanel pan_ft1 = new JPanel();

    private JPanel pan_ft = new JPanel();
    private JPanel pan_article = new JPanel();
    private JPanel pan_article_lab = new JPanel();
    private JPanel pan_article_comb = new JPanel();
    private JLabel article_lab = new JLabel("Article");
    private jcombo article_comb=new jcombo(list_comb);


    private JPanel pan_dimension = new JPanel();
    private JPanel pan_dimension_lab = new JPanel();
    private JPanel pan_dimension_comb= new JPanel();
    private JLabel dimension_lab = new JLabel("Dimension");
    private JComboBox<String> dimension_comb =new JComboBox<>();


    private JPanel pan_model = new JPanel();
    private JPanel pan_model_lab = new JPanel();
    private JPanel pan_model_jtext= new JPanel();
    private JLabel modele_lab = new JLabel("Modele");
    private JTextField modele_jtext =new JTextField();


    private JPanel pan_couleur = new JPanel();
    private JPanel pan_couleur_lab = new JPanel();
    private JPanel pan_couleur_comb= new JPanel();
    private JLabel couleur_lab = new JLabel("Couleur");
    private JComboBox couleur_comb = new JComboBox<>(list_comb);



    private JPanel pan_qte = new JPanel();
    private JPanel pan_qte_lab = new JPanel();
    private JPanel pan_qte_comb= new JPanel();
    private JLabel qte_lab = new JLabel("Quantité");
    private JTextField qte_jtext =new JTextField();


    private JPanel pan_delivery = new JPanel();
    private JPanel pan_delivery_lab = new JPanel();
    private JPanel pan_delivery_jtext= new JPanel();
    private JLabel delivry_lab = new JLabel("Delivery");
    private JTextField delivery_jtext =new JTextField();

    private JPanel pan_gw = new JPanel();
    private JPanel pan_gw_lab = new JPanel();
    private JPanel pan_gw_jtext= new JPanel();
    private JLabel gw_lab = new JLabel("G.W");
    private JComboBox gw_comb =new JComboBox<>(list_comb);


    private JPanel pan_sizecart = new JPanel();
    private JPanel pan_sizecart_lab = new JPanel();
    private JPanel pan_sizecart_comb= new JPanel();
    private JLabel sizecart_lab = new JLabel("Carton Size");
    private JComboBox sizecart_comb =new JComboBox<>(list_comb);

    private JPanel pan_parcel = new JPanel();
    private JPanel pan_parcel_lab = new JPanel();
    private JPanel pan_parcel_jtext= new JPanel();
    private JLabel parcel_lab = new JLabel("Parcel N°");
    private JTextField parcel_jtext =new JTextField();



    private JPanel pan_nw = new JPanel();
    private JPanel pan_nw_lab = new JPanel();
    private JPanel pan_nw_jtext= new JPanel();
    private JLabel nw_lab = new JLabel("N.W");
    private JComboBox nw_comb =new JComboBox<>(list_comb);


    private JPanel pan_chaine = new JPanel();
    private JPanel pan_chaine_lab = new JPanel();
    private JPanel pan_chaine_comb= new JPanel();
    private JLabel chaine_lab = new JLabel("Chaine");
    private jcombo chaine_comb = new jcombo(list_comb);


    private JPanel pan_commentaire = new JPanel();
    private JPanel pan_commentaire_lab = new JPanel();
    private JPanel pan_commentaire_area= new JPanel();
    private JLabel commentaire_lab=new JLabel("Commentaire");
    private JTextArea commentaire=new JTextArea();


    private JPanel pan_palette = new JPanel();
    private JPanel pan_palette_lab = new JPanel();
    private JPanel pan_palette_combo= new JPanel();
    private JLabel palette_lab=new JLabel("Palette");
    private JComboBox<String> palette_comb =new JComboBox<>();
    private JButton palette_ajou = new JButton("New");

    private JPanel pan_qtepalette = new JPanel();
    private JPanel pan_qtepalette_lab = new JPanel();
    private JPanel pan_qtepalette_text= new JPanel();
    private JLabel qtepalette_lab=new JLabel("Qte Palette");
    private JTextField qtepalette=new JTextField();


    private JPanel pan_date = new JPanel();
    private JPanel pan_date_lab = new JPanel();
    private JPanel pan_date_jtext= new JPanel();
    private JLabel date_lab = new JLabel("Date");
    private final JXDatePicker date_picker = new JXDatePicker();

    private JPanel pan_rech = new JPanel();
    private JPanel pan_rech_lab = new JPanel();
    private JPanel pan_rech_jtext= new JPanel();
    private JLabel rech_lab = new JLabel("Recherche");
    private final JTextField rech_jtext = new JTextField();


    private JPanel pan_but_ligne  = new JPanel();
    private JPanel pan_button = new JPanel();
    private JPanel pan_tab = new JPanel();



    private Object[] entete = {"N°","Code IMEI1" ,"Code IMEI2"};
    private final Tableau tab = new Tableau(entete);
    private JScrollPane p = new JScrollPane(tab);

    private JTabbedPane jTabbedPane = new JTabbedPane();



    private gestion_imp_phone imp_phone=new gestion_imp_phone();


    public etiquette_emballage_portable(final String log) {
        composant(log);
    }


    public void composant(final String log) {
        parcel_jtext.setEnabled(false);


        JPanel panel = generalPanle();
        initFrame("Edition Etiquette Portable", pan_ft, this, log,"Gestion Etiquette Portable");
        declarationButton(pan_button);
        visibiliteButton(true, false, false, true,
                false, false,false);
        imp_etq.setVisible(false);


        remplirActDimComb( dimension_comb,"etiquette_emballage_portable");



        replirDate(date_picker);
        date_picker.addActionListener(e ->  action_date());

        remplirChaine(chaine_comb);
        chaine_comb.addActionListener(e -> action_date());


        remplirArticleType(article_comb, 5 );
        article_comb.addActionListener(e -> {
            if (article_comb.getSelectedIndex() != 0 ) {
                code_article = splitcombo(article_comb);

                modele_jtext.setText(imp_phone.select_model(code_article));
            }});



        remplirSizeCarton(sizecart_comb, "etiquette_emballage_portable" );
        sizecart_comb.addActionListener(e -> addTocomboAction(sizecart_comb));

        remplirColor(couleur_comb, "etiquette_emballage_portable");
        couleur_comb.addActionListener(e -> addTocomboAction(couleur_comb));



        remplirGw(gw_comb, "etiquette_emballage_portable" );
        gw_comb.addActionListener(e ->    addTocomboAction(gw_comb));


        remplirNw(nw_comb, "etiquette_emballage_portable" );
        nw_comb.addActionListener(e ->    addTocomboAction(nw_comb));



        remplirPalette(palette_comb,"PP");
        countCartonPalette(palette_comb,qtepalette,"etiquette_emballage_portable" );
        palette_comb.addActionListener(e -> countCartonPalette(palette_comb,qtepalette,"etiquette_emballage_portable" ));



        tab.allowEdition2=false;
        parcel_jtext.disable();
        qtepalette.setEditable(false);
        modele_jtext.disable();
        action_date();



        palette_ajou.addActionListener(e -> {
            if (code_chaine.equals("0")) {
                JOptionPane.showMessageDialog(
                        null,
                        "Vous devez choisir la chaine de production" ,
                        "", JOptionPane.ERROR_MESSAGE);
            } else{
                String aff = imp_phone.afficher_conteur_palette("PP", code_chaine);
                palette_comb.addItem(aff);
                palette_comb.setSelectedItem(aff);
                countCartonPalette(palette_comb, qtepalette, "etiquette_emballage_portable");
            }
        });






        /////*********************delete row from table action***********************/

        removeOneRowTab(but_supprim, tab);
        ActionTable(tab);


        but_ajou.addActionListener(e -> {
            int i= tab.table.getRowCount();
            addRowVide(i,i+1,tab);
            qte_jtext.setText(""+tab.table.getRowCount());
        });


        qte_jtext.setText(""+tab.table.getRowCount());
        qte_jtext.addActionListener(e -> {
            int row= Integer.parseInt(qte_jtext.getText());
            addRowVide(0,row,tab);
        });



        retour.addActionListener(e -> {
            titleFrame("Edition etiquette emballage Portable",pan_ft);
            imp_etq.setVisible(false);
            visibiliteButton(true, false, false, true,
                    false, false,false);
            qte_jtext.setText("");
            date_picker.setDate(Calendar.getInstance().getTime());
            gw_comb.setSelectedItem("");
            nw_comb.setSelectedItem("");
            sizecart_comb.setSelectedItem("");
            delivery_jtext.setText("");
            rech_jtext.setText("");
            couleur_comb.setSelectedIndex(0);
            modele_jtext.setText("");
            dimension_comb.setSelectedIndex(0);
            article_comb.setSelectedIndex(0);


            action_date();
            addRowVide(0,10,tab);
            qte_jtext.setText("" + tab.table.getRowCount());
            countCartonPalette(palette_comb,qtepalette,"etiquette_emballage_portable" );

        });






        ajouter.addActionListener(e -> {
            titleFrame("Ajouter Etiquette Emballage Portable",pan_ft);
            visibiliteButton(false, false, false, true,
                    true, false,false);
            imp_etq.setVisible(false);
        });
        valid_ajou.addActionListener( e-> insert_update_action("I"));



        modifier.addActionListener(e -> {
            titleFrame("Modifier Etiquette Emballage Portable",pan_ft);
            visibiliteButton(false, false, false, true,
                    false, true,false);
            imp_etq.setVisible(false);
        });
        valid_modif.addActionListener( e-> insert_update_action("U"));



        Supprimer.addActionListener(e -> {
            titleFrame("Supprimer Etiquette Emballage Portable",pan_ft);
            visibiliteButton(false, false, false, true,
                    false, false,true);
            imp_etq.setVisible(false);
        });


        valid_supp.addActionListener(e -> {

            if (rech_jtext.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null,  "Vous devez  remplir le code carton a supprimer \n");
            }

            else {
                imp_phone.supprimer_carton("etiquette_emballage_portable","etiquette_portable",parcel_jtext.getText());
                JOptionPane.showMessageDialog(null,"L'etiquette a été bien supprimée");
                retour.doClick();

            }


        });





        rech_jtext.addActionListener(e -> {


                if(imp_phone.exist_emballage(rech_jtext.getText(),"etiquette_emballage_portable")){
                   ArrayList result= imp_phone.select_parcel_portable(rech_jtext.getText());
                    article_comb.setSelectedItem(result.get(0)+" "+result.get(1));
                    modele_jtext.setText((String) result.get(2));
                    couleur_comb.setSelectedItem(result.get(3));
                    nw_comb.setSelectedItem(result.get(5));
                    gw_comb.setSelectedItem(result.get(6));
                    delivery_jtext.setText((String) result.get(7));
                    sizecart_comb.setSelectedItem( result.get(8));
                    palette_comb.setSelectedItem( result.get(10));


                    try {
                        date_picker.setDate(dateform.parse((String) result.get(9)));
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }


                    for(int i=0;i<chaine_comb.getItemCount();i++){
                        if (chaine_comb.getItemAt(i).toString().contains(rech_jtext.getText().substring(2,3))){
                            chaine_comb.setSelectedIndex(i);
                        }
                    }

                    ArrayList<String> liste=imp_phone.listCarton("etiquette_emballage_portable",(String) result.get(10));
                    int qte_modif=Integer.parseInt(liste.get(0));
                    qtepalette.setText(""+qte_modif);
                    parcel_jtext.setText(rech_jtext.getText());


                    ArrayList<String> list_emei=imp_phone.select_imei(parcel_jtext.getText());
                    System.out.println(list_emei);
                    remplirTab(list_emei,3, tab,  1);
                    qte_jtext.setText((String) result.get(4));



                    imp_etq.setVisible(false);

                    visibiliteButton(false, true, true, true,
                            false, false, false);

                }
                else {

                    if (!rech_jtext.getText().trim().equals("")) {
                        JOptionPane
                                .showMessageDialog(
                                        null,
                                        "Cette tablette n'existe pas",
                                        "", JOptionPane.INFORMATION_MESSAGE);
                    }
                    retour.doClick();

                }
                });





        imp_etq.addActionListener(
                event -> {


                    if (dimension_comb.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette"); }
                    else {


                        code_aimprimer.add(parcel_jtext.getText());

                        parameters.put("serial", code_aimprimer);

                        String parcour = constant.etiq_portable + String.valueOf(dimension_comb.getSelectedItem()).replace("/","_");

                        if (String.valueOf(dimension_comb.getSelectedItem()).contains("Palette")) {
                            parcour += "_" + palette_comb.getSelectedItem() + "_palette.pdf";

                        } else {

                            parcour += "_" + parcel_jtext.getText() + "_carton.pdf";


                        }

                        Imprimer_pdf_Final( parcour, report, parameters);

                        countCartonPalette(palette_comb, qtepalette, "etiquette_emballage_portable");


                        rech_jtext.setText("");
                        action_date();

                        int row = Integer.parseInt(qte_jtext.getText());
                        addRowVide(0, row, tab);

                        imp_etq.setVisible(false);
                        visibiliteButton(true, false, false, true,
                                false, false, false);

                    }
                });




     ///*****************************style de label******************************/
        styleLabel(article_lab);
        styleLabel(modele_lab);
        styleLabel(couleur_lab);
        styleLabel(parcel_lab);
        styleLabel(nw_lab);
        styleLabel(gw_lab);
        styleLabel(qte_lab);
        styleLabel(sizecart_lab);
        styleLabel(commentaire_lab);
        styleLabel(date_lab);
        styleLabel(qtepalette_lab);
        styleLabel(palette_lab);
        styleLabel(dimension_lab);
        styleLabel(chaine_lab);
        styleLabel(rech_lab);
        styleLabel(delivry_lab);

        ////*********************Style Component************************//
        styleComponent(couleur_comb);
        styleComponent(rech_jtext);
        styleComponent(qtepalette);

        styleComponent(article_comb);
        styleComponent(modele_jtext);
        styleComponent(parcel_jtext);
        styleComponent(date_picker);
        styleComponent(commentaire);
        styleComponent(nw_comb);
        styleComponent(gw_comb);
        styleComponent(qte_jtext);
        styleComponent(sizecart_comb);
        styleComponent(delivery_jtext);
        styleComponent(dimension_comb);
        styleComponent(chaine_comb);

        /////****************************Scrolle pannel in component part********************//
        JScrollPane scrollPane = new JScrollPane(pan_ft1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setOpaque(false);
        p.getViewport().setOpaque(false);


        // ********************************************add component on panel*********************************************


        pan_form1.add(pan_rech);
        pan_form1.add(pan_palette);
        pan_form1.add(pan_qtepalette);
        pan_form1.add(pan_parcel);
        pan_form1.add(pan_article);
        pan_form1.add(pan_model);
        pan_form1.add(pan_couleur);
        pan_form1.add(pan_qte);
        pan_form1.add(pan_nw);
        pan_form1.add(pan_gw);
        pan_form1.add(pan_delivery);
        pan_form1.add(pan_sizecart);
        pan_form1.add(pan_date);
        pan_form1.add(pan_chaine);
        pan_form1.add(pan_dimension);
        pan_form1.add(pan_commentaire);

        pan_chaine.add(pan_chaine_lab);
        pan_chaine.add(pan_chaine_comb);
        pan_chaine_lab.add(chaine_lab);
        pan_chaine_comb.add(chaine_comb);

        pan_rech.add(pan_rech_lab);
        pan_rech.add(pan_rech_jtext);
        pan_rech_lab.add(rech_lab);
        pan_rech_jtext.add(rech_jtext);

        pan_dimension.add(pan_dimension_lab);
        pan_dimension.add(pan_dimension_comb);
        pan_dimension_lab.add(dimension_lab);
        pan_dimension_comb.add(dimension_comb);


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

        pan_couleur.add(pan_couleur_lab);
        pan_couleur.add(pan_couleur_comb);
        pan_couleur_lab.add(couleur_lab);
        pan_couleur_comb.add(couleur_comb);


        pan_qte.add(pan_qte_lab);
        pan_qte.add(pan_qte_comb);
        pan_qte_lab.add(qte_lab);
        pan_qte_comb.add(qte_jtext);

        pan_nw.add(pan_nw_lab);
        pan_nw.add(pan_nw_jtext);
        pan_nw_lab.add(nw_lab);
        pan_nw_jtext.add(nw_comb);

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

        pan_delivery.add(pan_delivery_lab);
        pan_delivery.add(pan_delivery_jtext);
        pan_delivery_lab.add(delivry_lab);
        pan_delivery_jtext.add(delivery_jtext);


        pan_commentaire.add(pan_commentaire_lab);
        pan_commentaire.add(pan_commentaire_area);
        pan_commentaire_lab.add(commentaire_lab);
        StyleAreaText(commentaire,pan_commentaire_area);

        pan_date.add(pan_date_lab);
        pan_date.add(pan_date_jtext);
        pan_date_lab.add(date_lab);
        pan_date_jtext.add(date_picker);


        pan_ft1.add(pan_form1);
        panel.add(pan_ft);
        pan_ft.add(scrollPane);
        pan_ft.add(pan_tab);
        pan_tab.add(p);
        pan_tab.add(pan_but_ligne);
        panel.add(pan_button);
        jTabbedPane.addTab("Edition etiquette emballage Portable", panel);

        /////**************************************visibilite of panel********************/


        pan_qtepalette.setOpaque(false);
        pan_qtepalette_lab.setOpaque(false);
        pan_qtepalette_text.setOpaque(false);

        pan_rech.setOpaque(false);
        pan_rech_lab.setOpaque(false);
        pan_rech_jtext.setOpaque(false);

        pan_palette.setOpaque(false);
        pan_palette_lab.setOpaque(false);
        pan_palette_combo.setOpaque(false);

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

        pan_couleur.setOpaque(false);
        pan_couleur_comb.setOpaque(false);
        pan_couleur_lab.setOpaque(false);

        pan_qte.setOpaque(false);
        pan_qte_comb.setOpaque(false);
        pan_qte_lab.setOpaque(false);

        pan_delivery.setOpaque(false);
        pan_delivery_jtext.setOpaque(false);
        pan_delivery_lab.setOpaque(false);

        pan_sizecart.setOpaque(false);
        pan_sizecart_comb.setOpaque(false);
        pan_sizecart_lab.setOpaque(false);

        pan_parcel.setOpaque(false);
        pan_parcel_jtext.setOpaque(false);
        pan_parcel_lab.setOpaque(false);

        pan_nw.setOpaque(false);
        pan_nw_jtext.setOpaque(false);
        pan_nw_lab.setOpaque(false);

        pan_gw.setOpaque(false);
        pan_gw_jtext.setOpaque(false);
        pan_gw_lab.setOpaque(false);

        pan_chaine.setOpaque(false);
        pan_chaine_lab.setOpaque(false);
        pan_chaine_comb.setOpaque(false);

        pan_dimension.setOpaque(false);
        pan_dimension_lab.setOpaque(false);
        pan_dimension_comb.setOpaque(false);


        pan_ft.setOpaque(false);
        pan_form.setOpaque(false);
        pan_button.setOpaque(false);

        pan_ft1.setOpaque(false);
        pan_form1.setOpaque(false);
        jTabbedPane.setOpaque(false);
        panel.setOpaque(false);

        p.setOpaque(false);


        ////***********style  table**************************/
        StyleTable(this,tab);


        ////***********style button table**************************/

        styleButtonTab(but_ajou,pan_but_ligne);
        styleButtonTab(but_supprim,pan_but_ligne);

        //////**********************       prefered size********************************/
        p.setPreferredSize(new Dimension(500, 700));
        styleButton(imp_etq,pan_button);

        //**************************** Layout of the  panel*******************************/

        pan_rech_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_rech_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));


        pan_qtepalette_text.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_qtepalette_lab.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_palette_combo.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_palette_lab.setLayout(new FlowLayout(FlowLayout.LEFT));

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

        pan_couleur_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_couleur_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_qte_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_qte_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_delivery_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_delivery_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_gw_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_gw_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_nw_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_nw_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_sizecart_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_sizecart_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_button.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_but_ligne.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_form.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_ft.setLayout(new BoxLayout(pan_ft, BoxLayout.X_AXIS));
        pan_form1.setLayout(new BoxLayout(pan_form1, BoxLayout.Y_AXIS));
        pan_tab.setLayout(new BoxLayout(pan_tab, BoxLayout.Y_AXIS));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));



        /////************border in the panel*********************************************/

        pan_rech.setBorder(BorderFactory.createEmptyBorder(0, -10, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 20));
        pan_button.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));


        pan_ft1.setBorder(BorderFactory.createEmptyBorder(0, 0, -30, 50));

        pan_qtepalette_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pan_qtepalette_text.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        pan_palette_lab.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
        pan_palette_combo.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));

        pan_rech_lab.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        pan_rech_jtext.setBorder(BorderFactory.createEmptyBorder(0,17, 0, 0));

        pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
        pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));

        pan_parcel_lab.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        pan_parcel_jtext.setBorder(BorderFactory.createEmptyBorder(0,17, 0, 0));


        pan_article_lab.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));


        pan_delivery_lab.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        pan_delivery_jtext.setBorder(BorderFactory.createEmptyBorder(0,25, 0, 0));



        pan_model_lab.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        pan_model_jtext.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));

        pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0,25, 0, 0));

        pan_couleur_lab.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        pan_couleur_comb.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));

        pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
        pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0,25, 0, 0));

        pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0,25, 0, 0));

        pan_qte_lab.setBorder(BorderFactory.createEmptyBorder(0, 18, 0, 0));
        pan_qte_comb.setBorder(BorderFactory.createEmptyBorder(0,25, 0, 0));

        pan_gw_lab.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
        pan_gw_jtext.setBorder(BorderFactory.createEmptyBorder(0,25, 0, 0));

        pan_nw_lab.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
        pan_nw_jtext.setBorder(BorderFactory.createEmptyBorder(0,25, 0, 0));


        pan_sizecart_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pan_sizecart_comb.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));


        pan_commentaire.setBorder(BorderFactory.createEmptyBorder(0, -30, 0, 0));
        pan_commentaire_area.setBorder(BorderFactory.createEmptyBorder(0,30, 0, 0));



        setContentPane(jTabbedPane);

    }


    private String  verifierchamp(){
        String msg = "";

        if (article_comb.getSelectedIndex() == 0) {
            msg = "Vous Devez  Sélectionner un Article\n";
        } else if (couleur_comb.getSelectedIndex() == 0) {
            msg = "Vous Devez  Sélectionner un Couleur\n";
        }
        else if (palette_comb.getSelectedIndex()==0) {
            msg = "Vous devez  selectionner une Pallette ";
        }
        else if(String.valueOf(gw_comb.getSelectedItem()).equals(""))
            msg = "Vous Devez d'abord Remplir le G.W \n";
        else if (String.valueOf(sizecart_comb.getSelectedItem()).equals(""))
            msg = "Vous Devez d'abord selectionner la Dimension du Carton \n";
        else if (parcel_jtext.getText().equals("")) {
            msg = "Vous Devez  Remplir le numero  Parcel \n";
        }
        else if (String.valueOf(nw_comb.getSelectedItem()).equals(""))
            msg = "Vous Devez  Remplir le N.W \n";
        else if (tab.table.getRowCount() == 0) {
            msg =	"Vous devez  d'abord ajouter une Ligne ";
        }


        else {
                if (!vaidCheck(tab)) {
                    msg = "Un Champ dans le tableau  est vide \n";
                }

            else {
                    for (int i = 0; i < tab.table.getRowCount(); i++) {
                        if (tab.table.getValueAt(i, 2).toString().equals(tab.table.getValueAt(i, 1).toString())
                        ) {
                            int l=i+1;
                            msg = "un code imei 1 et egals a un code imei2 dans la ligne "+l+ ".\n";
                        }
                        for (int j = i + 1; j < tab.table.getRowCount(); j++) {
                            int p=i+1; int g=j+1;
                            if (tab.table.getValueAt(i, 2).toString().equals(tab.table.getValueAt(j, 2).toString())||
                                    tab.table.getValueAt(i,1).toString().equals(tab.table.getValueAt(j, 1).toString()) ||
                                    tab.table.getValueAt(i, 2).toString().equals(tab.table.getValueAt(j, 1).toString())||
                                    tab.table.getValueAt(i,1).toString().equals(tab.table.getValueAt(j, 2).toString() )) {
                                msg = "les Ligne "+p+" et "+g+" sont  dupliqué. \n";
                            }
                        }
                    }
            }


        }

        return msg;
    }



    private void action_date(){
        System.out.println(splitcombo(chaine_comb));
        if(chaine_comb.getSelectedIndex()!=0)
            code_chaine=splitcombo(chaine_comb).substring(2).trim();

        String code=imp_phone.afficher_conteur(date_picker.getDate(),code_chaine,"etiquette_emballage_portable","MF","parcel","date_emb");
        parcel_jtext.setText(code);


    }



    //********************selection article*****************************/

    private void insert_update_action(String type){

        String msg= verifierchamp();
        for (int i = 0; i < tab.table.getRowCount(); i++) {
            msg = imp_phone.imei_deja_associer((String) tab.table.getValueAt(i,1),
                    (String) tab.table.getValueAt(i,2),parcel_jtext.getText());
        }


        if (!msg.equals("")) {
            JOptionPane.showMessageDialog(null, msg,"", JOptionPane.ERROR_MESSAGE);
        }
        else{

            imp_phone.update_insert_embalage_tablette(parcel_jtext.getText(),code_article,
                    String.valueOf(couleur_comb.getSelectedItem()),qte_jtext.getText(),
                    delivery_jtext.getText(),String.valueOf(gw_comb.getSelectedItem()),String.valueOf(nw_comb.getSelectedItem())
                    ,String.valueOf(sizecart_comb.getSelectedItem()),commentaire.getText(),date_picker.getDate()
                    ,String.valueOf(palette_comb.getSelectedItem())
                    ,type);

            imp_phone.delete_code(parcel_jtext.getText(),"etiquette_portable");


            for (int i = 0; i < tab.table.getRowCount(); i++) {

                imp_phone.ajout_emei_portable(parcel_jtext.getText(),tab.table.getValueAt(i, 1).toString(),tab.table.getValueAt(i, 2).toString() ,
                        couleur_comb.getSelectedItem().toString(), date_picker.getDate(), code_article);

            }
            JOptionPane.showMessageDialog(null, "l'etiquette a été bien sauvgarder");

            visibiliteButton(false, false, false, true,
                    false, false,false);
            imp_etq.setVisible(true);


        }

    }






    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new etiquette_emballage_portable("7");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
