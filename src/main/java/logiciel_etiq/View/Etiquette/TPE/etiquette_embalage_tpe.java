package logiciel_etiq.View.Etiquette.TPE;

import logiciel_etiq.Controller.gestion_imp_tpe;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import logiciel_etiq.View.tableau.Tableau;
import logiciel_etiq.constant;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.text.ParseException;
import java.util.*;





public class etiquette_embalage_tpe extends generale {


    private JPanel pan_form = new JPanel();
    private JPanel pan_form1 = new JPanel();
    private JPanel pan_ft1 = new JPanel();

    private JPanel pan_ft = new JPanel();
    private JPanel pan_article = new JPanel();
    private JPanel pan_article_lab = new JPanel();
    private JPanel pan_article_comb = new JPanel();
    private JLabel article_lab = new JLabel("Article");
    private jcombo article_comb=new jcombo(list_comb);


    private JPanel pan_model = new JPanel();
    private JPanel pan_model_lab = new JPanel();
    private JPanel pan_model_jtext= new JPanel();
    private JLabel modele_lab = new JLabel("Modele");
    private JTextField modele_jtext =new JTextField();


    
    private JPanel pan_qte = new JPanel();
    private JPanel pan_qte_lab = new JPanel();
    private JPanel pan_qte_comb= new JPanel();
    private JLabel qte_lab = new JLabel("Quantité");
    private JTextField qte_jtext =new JTextField();



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


    private JPanel pan_commentaire = new JPanel();
    private JPanel pan_commentaire_lab = new JPanel();
    private JPanel pan_commentaire_area= new JPanel();
    private JLabel commentaire_lab=new JLabel("Commentaire");
    private JTextArea commentaire=new JTextArea();
    
    
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




    private JPanel pan_codeEnie = new JPanel();
    private JPanel pan_codeEnie_lab = new JPanel();
    private JPanel pan_codeEnie_box= new JPanel();
    private JLabel codeEnie_lab = new JLabel("Code Enie");
    private JCheckBox codeEnie = new JCheckBox("Code Enie");



    private JPanel pan_id_imei = new JPanel();
    private JPanel pan_id_imei_lab = new JPanel();
    private JPanel pan_id_imei_radio= new JPanel();
    private JLabel id_imei_lab = new JLabel("Type d'etiquette");
    private    ButtonGroup  id_imei_radio =new ButtonGroup();
    private JRadioButton id_radio = new JRadioButton("ID");
    private JRadioButton imei_radio = new JRadioButton("IMEI");


    private JPanel pan_chaine = new JPanel();
    private JPanel pan_chaine_lab = new JPanel();
    private JPanel pan_chaine_comb= new JPanel();
    private JLabel chaine_lab = new JLabel("Chaine");
    private jcombo chaine_comb= new jcombo(list_comb) ;


    private JPanel pan_dimension = new JPanel();
    private JPanel pan_dimension_lab = new JPanel();
    private JPanel pan_dimension_comb= new JPanel();
    private JLabel dimension_lab = new JLabel("Dimension");
    private JComboBox<String> dimension_comb =new JComboBox<>();


    private JPanel pan_button = new JPanel();
    private JPanel pan_tab = new JPanel();
    private JPanel pan_but_ligne  = new JPanel();


    private Object[] entete = {"N°","Code Enie","Code IMEI1/ID" ,"Serial Number"};
    private final Tableau tab = new Tableau(entete);

    private JScrollPane p = new JScrollPane(tab);
    private JTabbedPane jTabbedPane = new JTabbedPane();


    private static gestion_imp_tpe imp_tpe=new gestion_imp_tpe();



    public etiquette_embalage_tpe(final String log) {

        composant(log);
    }

    public void composant(final String log) {


        JPanel panel = generalPanle();
        initFrame("Edition Etiquette TPE", pan_ft, this, log,"Gestion Etiquette TPE");
        declarationButton(pan_button);
        visibiliteButton(true, false, false, true,
                false, false,false);
        imp_etq.setVisible(false);


        remplirActDimComb( dimension_comb,"etiquette_emballage_tpe");


        replirDate(date_picker);
        date_picker.addActionListener(e ->  action_date());

        remplirChaine(chaine_comb);
        chaine_comb.addActionListener(e -> action_date());


        remplirArticleType(article_comb, 3 );
        article_comb.addActionListener(e -> {
            if (article_comb.getSelectedIndex() != 0 ) {
                code_article = splitcombo(article_comb);
                String model=imp_tpe.select_model(code_article);
                modele_jtext.setText(model);
            }});


        remplirSizeCarton(sizecart_comb, "etiquette_emballage_tpe" );
        sizecart_comb.addActionListener(e -> addTocomboAction(sizecart_comb));



        remplirGw(gw_comb, "etiquette_emballage_tpe" );
        gw_comb.addActionListener(e ->    addTocomboAction(gw_comb));



        remplirPalette(palette_comb,"PT");
        countCartonPalette(palette_comb,qtepalette,"etiquette_emballage_tpe" );
        palette_comb.addActionListener(e -> countCartonPalette(palette_comb,qtepalette,"etiquette_emballage_tpe" ));


        parcel_jtext.disable();
        imei_radio.setSelected(true);
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
                String aff = imp_tpe.afficher_conteur_palette("PT", code_chaine);
            palette_comb.addItem(aff);
            palette_comb.setSelectedItem(aff);
            countCartonPalette(palette_comb, qtepalette, "etiquette_emballage_tpe");
        }
        });

      /////*********************delete row from table action***********************/
        removeOneRowTab(but_supprim, tab);


        but_ajou.addActionListener(e -> {
            int i= tab.table.getRowCount();
            AddRowInTable(i+1,i,tab);
            qte_jtext.setText(""+tab.table.getRowCount());

        });





        TableColumn colomn = tab.table.getColumnModel().getColumn(1);
        TableColumn colomn_sn = tab.table.getColumnModel().getColumn(tab.table.getColumnCount()-1);
        AddRowInTable(10,0, tab);
        qte_jtext.setText(""+tab.table.getRowCount());
        ActionTable(tab);



        retour.addActionListener(e -> {
            titleFrame("Edition etiquette emballage tpe",pan_ft);

            imp_etq.setVisible(false);
            visibiliteButton(true, false, false, true,
                    false, false,false);

            date_picker.setDate(Calendar.getInstance().getTime());

            rech_jtext.setText("");
            article_comb.setSelectedIndex(0);
            modele_jtext.setText("");

            qte_jtext.setText("");
            gw_comb.setSelectedIndex(0);
            sizecart_comb.setSelectedIndex(0);
            chaine_comb.setSelectedIndex(0);
            commentaire.setText("");

            action_date();
            AddRowInTable(10,0, tab);
            qte_jtext.setText(""+tab.table.getRowCount());
            countCartonPalette(palette_comb,qtepalette,"etiquette_emballage_tpe" );

        });




        qte_jtext.addActionListener(e -> {
            int row= Integer.parseInt(qte_jtext.getText());
            AddRowInTable(row,0,tab);
        });





        codeEnie.addActionListener(e -> {
            if(codeEnie.isSelected()) {
                        remove_colomn("Code Enie");
                    }
            else {
                add_colomn("Code Enie", 1,colomn);
            }
        });



        id_radio.addActionListener(e -> {
            col_sn=-1;
            remove_colomn("Serial Number");
        });


        imei_radio.addActionListener(e -> add_colomn("Serial Number", tab.table.getColumnCount(),colomn_sn));






        Supprimer.addActionListener(e -> {
            titleFrame("Supprimer Etiquette Emballage",pan_ft);
            visibiliteButton(false, false, false, true,
                    false, false,true);
            imp_etq.setVisible(false);
        });


        valid_supp.addActionListener(e -> {

            if (rech_jtext.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null,  "Vous devez  remplir le code carton a supprimer \n");
            }
            else {
                imp_tpe.supprimer_carton("etiquette_emballage_tpe","etiquette_tpe",parcel_jtext.getText());
                JOptionPane.showMessageDialog(null,"L'etiquette a été bien supprimée");
                retour.doClick();
            }
        });




        ajouter.addActionListener(e -> {
            titleFrame("Ajouter Etiquette Emballage",pan_ft);
            visibiliteButton(false, false, false, true,
                    true, false,false);
            imp_etq.setVisible(false);

        });
        valid_ajou.addActionListener(e -> insert_update_action("I"));


        modifier.addActionListener(e -> {
            titleFrame("Modifier Etiquette Emballage",pan_ft);
            visibiliteButton(false, false, false, true,
                    false, true,false);
            imp_etq.setVisible(false);

        });

        valid_modif.addActionListener(e -> insert_update_action("U"));




        
        
        rech_jtext.addActionListener(e -> {

            if (imp_tpe.exist_emballage(rech_jtext.getText(),"etiquette_emballage_tpe")) {


                ArrayList result=imp_tpe.selection_tpe_emballage(rech_jtext.getText());
                palette_comb.setSelectedItem(result.get(8));
                article_comb.setSelectedItem(result.get(0) + " " + result.get(1));
                qte_jtext.setText((String) result.get(7));
                commentaire.setText((String) result.get(6));
                gw_comb.setSelectedItem(result.get(3));
                sizecart_comb.setSelectedItem(result.get(4));

                try {
                    date_picker.setDate(dateform.parse((String) result.get(5)));
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

                for(int i=0;i<chaine_comb.getItemCount();i++){
                    if (chaine_comb.getItemAt(i).toString().contains(result.get(2).toString().substring(2,3))){
                        chaine_comb.setSelectedIndex(i);
                    }
                }


                ArrayList liste = imp_tpe.listCarton("etiquette_emballage_tpe", (String) result.get(8));
                int qte_modif = Integer.parseInt(liste.get(0).toString());
                qtepalette.setText("" + qte_modif);
                parcel_jtext.setText((String) result.get(2));



                ArrayList list_emei = imp_tpe.selection_tpe_table(parcel_jtext.getText());

                if(list_emei.size()!=0) {
                    int row =  list_emei.size()/3 ;

                    removeTabRow(tab);

                    for (int i = 0; i < row; i = i + 1) {
                        tab.ajouter();
                    }

                    int j = 1;
                    int l = 0;

                    if (list_emei.get(2) == null || list_emei.get(2).toString().trim().equals("")|| list_emei.get(2).toString().trim().equals("Sans Code Enie")) {
                        if(!codeEnie.isSelected()) codeEnie.doClick();

                    } else {

                        if(codeEnie.isSelected()) codeEnie.doClick();
                    }

                    if (list_emei.get(1) == null|| list_emei.get(1).toString().trim().equals("")) {
                        id_radio.doClick();
                    } else {
                        imei_radio.doClick();
                    }

                    int eniepos = getColumnIndexFromName(tab, "Code Enie");
                    int snpos = getColumnIndexFromName(tab, "Serial Number");
                    int emeipos = getColumnIndexFromName(tab, "Code IMEI1/ID");

                    int col = 3;
                    while (l < list_emei.size()) {
                        while (l < j * col) {

                            tab.getTable().setValueAt(j, j - 1, 0);

                            if (eniepos != -1)
                                tab.getTable().setValueAt(list_emei.get(l + 2), j - 1, eniepos);
                            if (snpos != -1)
                                tab.getTable().setValueAt(list_emei.get(l + 1), j - 1, snpos);

                            if (emeipos != -1)
                                tab.getTable().setValueAt(list_emei.get(l), j - 1, emeipos);


                            l = l + 3;
                        }

                        j = j + 1;
                    }
                }

                imp_etq.setVisible(false);

                visibiliteButton(false, true, true, true,
                        false, false, false);
                qte_jtext.setText("" + tab.table.getRowCount());

            } else {
                 JOptionPane
                        .showMessageDialog(
                                null,
                                "Ce TPE n'existe pas",
                                "", JOptionPane.INFORMATION_MESSAGE);

                retour.doClick();

            }

            });



        imp_etq.addActionListener(
                event -> {


                    if (dimension_comb.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette"); }
                    else {


                        code_aimprimer.add(parcel_jtext.getText());

                        parameters.put("parcel", code_aimprimer);

                        String parcour = constant.etiq_tpe + String.valueOf(dimension_comb.getSelectedItem()).replace("/","_");

                        if (String.valueOf(dimension_comb.getSelectedItem()).contains("Palette")) {
                            parcour += "_" + palette_comb.getSelectedItem() + ".pdf";

                        } else {

                            parcour += "_" + parcel_jtext.getText() + ".pdf";

                        }

                        Imprimer_pdf_Final( parcour, report, parameters);

                        countCartonPalette(palette_comb, qtepalette, "etiquette_emballage_tpe");

                        date_picker.setDate(Calendar.getInstance().getTime());
                        rech_jtext.setText("");
                        action_date();

                        int row = Integer.parseInt(qte_jtext.getText());
                        AddRowInTable(row, 0, tab);

                        imp_etq.setVisible(false);
                        visibiliteButton(true, false, false, true,
                                false, false, false);

                    }
                });



        
        
        id_imei_radio.add(id_radio);
        id_imei_radio.add(imei_radio);






        //////********************style labele

        styleLabel(article_lab);
        styleLabel(modele_lab);
        styleLabel(parcel_lab);
        styleLabel(rech_lab);
        styleLabel(id_imei_lab);
        styleLabel(codeEnie_lab);
        styleLabel(gw_lab);
        styleLabel(chaine_lab);
        styleLabel(dimension_lab);

        styleLabel(qte_lab);
        styleLabel(sizecart_lab);
        styleLabel(commentaire_lab);
        styleLabel(qtepalette_lab);
        styleLabel(date_lab);
        styleLabel(palette_lab);








/////***********************style component ***********************/
        styleComponent(article_comb);
        styleComponent(modele_jtext);
        styleComponent(parcel_jtext);
        styleComponent(date_picker);
        styleComponent(commentaire);
        styleComponent(gw_comb);
        styleComponent(qte_jtext);
        styleComponent(sizecart_comb);
        styleComponent(dimension_comb);
        styleComponent(chaine_comb);
        styleComponent(rech_jtext);
        styleComponent(qtepalette);

        ////***********style button table**************************/

        styleButtonTab(but_ajou,pan_but_ligne);
        styleButtonTab(but_supprim,pan_but_ligne);


  ////***********style  table**************************/
        StyleTable(this,tab);
        
   // ***********************************Sttyle commentaire **********************************************



        id_radio.setFont(new Font("Serif", Font.ITALIC, 13));
        id_radio.setForeground(Color.white);

        imei_radio.setFont(new Font("Serif", Font.ITALIC, 13));
        imei_radio.setForeground(Color.white);

        codeEnie.setFont(new Font("Serif", Font.ITALIC, 13));
        codeEnie.setForeground(Color.white);



/////****************************Scrolle pannel in component part********************//
        JScrollPane scrollPane = new JScrollPane(pan_ft1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setOpaque(false);
        p.getViewport().setOpaque(false);



/////***************************add component in panel********************//


        pan_form1.add(pan_id_imei);
        pan_form1.add(pan_codeEnie);
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


        pan_codeEnie.add(pan_codeEnie_lab);
        pan_codeEnie.add(pan_codeEnie_box);
        pan_codeEnie_lab.add(codeEnie_lab);
        pan_codeEnie_box.add(codeEnie);


        
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
        StyleAreaText(commentaire,pan_commentaire_area);


        pan_date.add(pan_date_lab);
        pan_date.add(pan_date_jtext);
        pan_date_lab.add(date_lab);
        pan_date_jtext.add(date_picker);



        panel.add(pan_ft);
        pan_ft.add(scrollPane);
        pan_ft1.add(pan_form1);

        pan_ft.add(pan_tab);
        pan_tab.add(p);
        pan_tab.add(pan_but_ligne);
        panel.add(pan_button);
        jTabbedPane.addTab("etiquette emballage tpe", panel);

        /////**************************************visibilite of panel********************/
        pan_rech.setOpaque(false);
        pan_rech_lab.setOpaque(false);
        pan_rech_jtext.setOpaque(false);
        
        pan_qtepalette.setOpaque(false);
		pan_qtepalette_lab.setOpaque(false);
		pan_qtepalette_text.setOpaque(false);
		
		
		pan_palette.setOpaque(false);
		pan_palette_lab.setOpaque(false);
		pan_palette_combo.setOpaque(false);


        pan_codeEnie.setOpaque(false);
        pan_codeEnie_lab.setOpaque(false);
        pan_codeEnie_box.setOpaque(false);



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
        p.setOpaque(false);



        //////**********************       prefered size********************************/
        p.setPreferredSize(new Dimension(500, 700));
        
        pan_id_imei_radio.setPreferredSize((new Dimension(200, 30)));
        styleButton(imp_etq,pan_button);



        //**************************** border panel*******************************/
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 20));
        pan_button.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));


        pan_ft1.setBorder(BorderFactory.createEmptyBorder(0, 0, -30, 50));

        pan_qtepalette_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pan_qtepalette_text.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

		pan_palette_lab.setBorder(BorderFactory.createEmptyBorder(0, 47, 0, 0));
        pan_palette_combo.setBorder(BorderFactory.createEmptyBorder(0,23, 0, 0));

        pan_rech_lab.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        pan_rech_jtext.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));

        pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
        pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));

        pan_parcel_lab.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        pan_parcel_jtext.setBorder(BorderFactory.createEmptyBorder(0,17, 0, 0));


        pan_article_lab.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));



        pan_model_lab.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        pan_model_jtext.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));

        pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));


        pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0,5, 0, 0));



        pan_qte_lab.setBorder(BorderFactory.createEmptyBorder(0, 18, 0, 0));
        pan_qte_comb.setBorder(BorderFactory.createEmptyBorder(0,25, 0, 0));



        pan_gw_lab.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
        pan_gw_jtext.setBorder(BorderFactory.createEmptyBorder(0,25, 0, 0));


        pan_sizecart_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pan_sizecart_comb.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));


        pan_commentaire.setBorder(BorderFactory.createEmptyBorder(0, -30, 0, 0));
        pan_commentaire_area.setBorder(BorderFactory.createEmptyBorder(0,20, 0, 0));


        pan_codeEnie.setBorder(BorderFactory.createEmptyBorder(0, -150, 0, 0));
        pan_codeEnie_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pan_codeEnie_box.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));




        pan_id_imei.setBorder(BorderFactory.createEmptyBorder(0, -90, 0, 0));
        pan_id_imei_lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pan_id_imei_radio.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));


        //**************************** Layout of the  panel*******************************/


        pan_rech_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_rech_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_palette_combo.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_palette_lab.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_qtepalette_text.setLayout(new FlowLayout(FlowLayout.LEFT));
		pan_qtepalette_lab.setLayout(new FlowLayout(FlowLayout.LEFT));



        pan_date_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_date_lab.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_parcel_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_parcel_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));


        pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_article_lab.setLayout(new FlowLayout(FlowLayout.LEFT));


        pan_model_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_model_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));


        pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_chaine_lab.setLayout(new FlowLayout(FlowLayout.LEFT));


        pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.LEFT));





        
        pan_commentaire_area.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_commentaire_lab.setLayout(new FlowLayout(FlowLayout.LEFT));




        
        pan_id_imei_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_id_imei_radio.setLayout(new FlowLayout(FlowLayout.LEFT));


        pan_codeEnie_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_codeEnie_box.setLayout(new FlowLayout(FlowLayout.LEFT));



        pan_qte_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_qte_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        
    
        
        pan_gw_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_gw_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        

        
        pan_sizecart_lab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_sizecart_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_button.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_but_ligne.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_form.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_ft.setLayout(new BoxLayout(pan_ft, BoxLayout.X_AXIS));
        pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));

        pan_form1.setLayout(new BoxLayout(pan_form1, BoxLayout.Y_AXIS));
        pan_tab.setLayout(new BoxLayout(pan_tab, BoxLayout.Y_AXIS));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));



        setContentPane(jTabbedPane);
    

    }



    ///////////////////////////////////////////Verification des champ ////////////////////////////////////




    private String  verifierchamp(){
        String msg = "";

        if (article_comb.getSelectedIndex() == 0) {
            msg = "Vous Devez  Sélectionner un Article\n";
        }
        else if (gw_comb.getSelectedItem()==null) {
            msg = "Vous Devez d'abord Remplir le G.W \n";
        }else if(gw_comb.getSelectedItem().equals("")) msg += "Vous Devez d'abord Remplir le G.W \n";
        else if (sizecart_comb.getSelectedItem()==null) {
            msg = "Vous Devez d'abord selectionner la Dimension du Carton \n";
        }else if (sizecart_comb.getSelectedItem().equals(""))msg += "Vous Devez d'abord selectionner la Dimension du Carton \n";
        else if (parcel_jtext.getText().equals("")) {
            msg = "Vous Devez  Remplir le numero  Parcel \n";
        }
        else if (palette_comb.getSelectedIndex()==0) {
            msg = "Vous devez  selectionner une Pallette ";
        }
        else if (tab.table.getRowCount() == 0) {
            msg = "Vous devez  d'abord ajouter une Ligne ";
        }

        else {
            if (!vaidCheck(tab)) {
                    msg = "Un Champ dans le tableau  est vide \n";
                }
       else {
                    int i = 0, j , x , z ;

                    while (i < tab.table.getRowCount()) {
                        j = 1;
                        while (j < tab.table.getColumnCount()) {
                            x = i;
                            while (x < tab.table.getRowCount()) {
                                z = j;
                                if (i != x) z = 1;
                                while (z < tab.table.getColumnCount()) {


                                    if (!(i == x && j == z)) {
                                        if (tab.table.getValueAt(i, j).equals(tab.table.getValueAt(x, z))) {

                                            int l = i + 1;
                                            int l2 = x + 1;
                                            msg = "La Ligne " + l + " colonne " + tab.table.getColumnName(j) + " est dupliquée avec la ligne " + l2 + " colonne " + tab.table.getColumnName(z) + " . \n";
                                        }
                                    }

                                    z++;
                                }
                                x++;
                            }
                            j++;
                        }

                        i++;
                    }
                }


        }

        return msg;
    }
///****************get col index of each colomn using nme of colomn*****************************/

private int col_enie=-1,col_sn=-1,col_iemi=-1;

    private void col_index(){
        for(int j=0;j<tab.table.getColumnCount();j++) {

            if (tab.table.getColumnName(j).equals("Code Enie")) {
                 col_enie= j;
            }
            if (tab.table.getColumnName(j).equals("Serial Number"))
            {
                col_sn = j;

            }
            if (tab.table.getColumnName(j).equals("Code IMEI1/ID"))
                col_iemi= j;
        }
    }


    private void AddRowInTable(int rows,int debut,Tableau tab) {
        if(debut==0)
            removeTabRow(tab);
        for (int i = debut; i < rows; i = i + 1) {
            tab.allowEdition2=false;
            tab.ajouter();
            tab.table.getCellEditor(i, 1).stopCellEditing();
            int k = tab.table.getRowCount();
            col_index();
            tab.table.setValueAt(i + 1, k - 1, 0);
            if (col_sn != -1)
                tab.table.setValueAt("", k - 1, col_sn);
            if (col_iemi != -1)
                tab.table.setValueAt("", k - 1, col_iemi);
            if (col_enie != -1)
                tab.table.setValueAt("", k - 1, col_enie);
        }
    }

    private void remove_colomn(String col_name ){
        int index=getColumnIndexFromName(tab,col_name);
        if (index!=-1) {
            TableColumn colmn = tab.table.getColumnModel().getColumn(index);
            tab.table.removeColumn(colmn);
            DefaultTableModel tableModel = (DefaultTableModel) tab.table.getModel();


            int modelIndex = colmn.getModelIndex();
            // Set all the cells in the column to empty
            int rowCount = tableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                tableModel.setValueAt("", i, modelIndex);
            }



        }
     }


    private void add_colomn(String col_name, int place, TableColumn colomn  ){
                   int index=getColumnIndexFromName(tab,col_name);
                   if (index==-1) {
                        tab.table.addColumn(colomn);
                        tab.table.moveColumn(tab.table.getColumnCount()-1, place);
                    }
                }




    private void action_date(){
        if(chaine_comb.getSelectedIndex()!=0)
            code_chaine=splitcombo(chaine_comb).substring(2).trim();
        String code=imp_tpe.afficher_conteur(date_picker.getDate(),code_chaine,"etiquette_emballage_tpe","TP","parcel","date_emb");
        parcel_jtext.setText(code);
    }



    private String Code_Enie="",sn_colomn="",imei_colomn="";
    private void getColomnValue(int i){
        Code_Enie="";sn_colomn="";imei_colomn="";
            for(int j=0;j<tab.table.getColumnCount();j++) {

                if (tab.table.getColumnName(j).equals("Code Enie")) {
                    Code_Enie= tab.table.getValueAt(i,j).toString();
                }
                if (tab.table.getColumnName(j).equals("Serial Number"))
                    sn_colomn= tab.table.getValueAt(i,j).toString();


                if (tab.table.getColumnName(j).equals("Code IMEI1/ID"))
                    imei_colomn= tab.table.getValueAt(i,j).toString();
            }


    }


    private void insert_update_action(String type){
        code_aimprimer.clear();

        String msg=verifierchamp();
        for (int i = 0; i < tab.table.getRowCount(); i++) {
            getColomnValue(i);
            msg += imp_tpe.emai_deja_associer(imei_colomn,Code_Enie,sn_colomn, rech_jtext.getText());
        }

        if (!msg.equals("")) {
            JOptionPane.showMessageDialog(null, msg,"", JOptionPane.ERROR_MESSAGE);
        }
        else{


            imp_tpe.update_insert_embalage_tpe(parcel_jtext.getText(),code_article,qte_jtext.getText()
                    ,(String) gw_comb.getSelectedItem()
                    ,(String) sizecart_comb.getSelectedItem(),commentaire.getText()
                    ,(String) palette_comb.getSelectedItem(),date_picker.getDate(),type);

            imp_tpe.delete_code(parcel_jtext.getText(),"etiquette_tpe");

            for (int i = 0; i < tab.table.getRowCount(); i++) {
                getColomnValue(i);
                imp_tpe.ajout_emei_tpe(parcel_jtext.getText(), imei_colomn, sn_colomn,  Code_Enie,splitcombo(chaine_comb),date_picker.getDate(),code_article);
            }
            JOptionPane.showMessageDialog(null, "l'etiquette a été bien ajouter");

            visibiliteButton(false, false, false, true,
                    false, false,false);
            imp_etq.setVisible(true);

        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new etiquette_embalage_tpe("7");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
