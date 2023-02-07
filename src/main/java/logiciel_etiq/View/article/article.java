package logiciel_etiq.View.article;

import logiciel_etiq.Controller.gestion_article_famille;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import logiciel_etiq.View.tableau.Java2sAutoComboBox;
import logiciel_etiq.View.tableau.Tableau;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;

import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Calendar;



public class article extends generale{

    protected String msg;


	private JPanel pan_tab=new JPanel();
	private JPanel pan_tab_prod=new JPanel();
	private JPanel pan_but_poste=new JPanel();
	private JPanel pan_but_prod=new JPanel();
	private JPanel pan_artgener=new JPanel();

	private JPanel pan_article=new JPanel();
	private JPanel pan_lab_combo=new JPanel();
	private JPanel pan_article_comb=new JPanel();
	private JLabel lab_combo=new JLabel("Liste des articles");
	private jcombo article_comb=new jcombo(list_comb);


	private JPanel pan_indice = new JPanel();
	private JPanel pan_indice_comb = new JPanel();
	private JPanel pan_indice_lab = new JPanel();
	private JLabel indice_lab = new JLabel("A imprimer");
    private JComboBox indice=new JComboBox();

    private JPanel pan_refer=new JPanel();
    private JPanel pan_ref_lab=new JPanel();
    private JPanel pan_ref_comb=new JPanel();
	private JLabel ref_lab_combo=new JLabel("Réfèrence");
	private  jcombo ref_comb=new jcombo(list_comb);

	private JPanel pan_codegener=new JPanel();
	private JPanel pan_codegener_lab=new JPanel();
	private JPanel pan_codegener_comb=new JPanel();
	private JLabel codegener_lab=new JLabel("Famille");
	private  jcombo codegener_comb=new jcombo(list_comb);


	private JPanel pan_famille=new JPanel();
	private JPanel pan_famille_lab=new JPanel();
	private JPanel pan_famille_comb=new JPanel();
	private JLabel famille_lab=new JLabel("Type");
	private  jcombo famille_comb=new jcombo(list_comb);

	private JPanel pan_designation=new JPanel();
	private JPanel pan_designation_lab=new JPanel();
	private JPanel pan_designation_jtext=new JPanel();
	private JLabel designation_lab=new JLabel("Designation");
	private JTextField designation=new JTextField();



	private JPanel pan_description=new JPanel();
	private JPanel pan_description_lab=new JPanel();
	private JPanel pan_description_jtext=new JPanel();
	private JLabel description_lab=new JLabel("Description");
	private JTextArea description=new JTextArea();

	private JPanel pan_coeff=new JPanel();
	private JPanel pan_coeff_lab=new JPanel();
	private JPanel pan_coeff_jtext=new JPanel();
	private JLabel coeff_lab=new JLabel("Coefficient");
	private JTextField coeff_jtext=new JTextField("0");


	private JPanel pan_model=new JPanel();
	private JPanel pan_model_lab=new JPanel();
	private JPanel pan_model_jtext=new JPanel();
	private JLabel model_lab=new JLabel("modèle");
	private JTextField model_jtext=new JTextField();


	private JPanel pan_datelancement=new JPanel();
	private JPanel pan_datelancement_lab=new JPanel();
	private JPanel pan_datelancement_jtext=new JPanel();
	private JPanel pan_infogener=new JPanel();
	private JLabel datelancement_lab=new JLabel("Date lancement");
    private JXDatePicker datelancement = new JXDatePicker();


	private JPanel pan_codeprogener=new JPanel();

    private JPanel pan_codeprod=new JPanel();
    private JPanel pan_codeprod_lab=new JPanel();
    private JPanel pan_codeprod_jtext=new JPanel();
	private JLabel codeprod_lab=new JLabel("Code Article");
	private JTextField codeprod=new JTextField();

    private JPanel pan_gener=new JPanel();
    private JPanel pan_form=new JPanel();
    private JPanel pan_form1=new JPanel();
    private JPanel pan_form2=new JPanel();
    private JPanel pan_buton=new JPanel();



	private JTabbedPane jTabbedPane = new JTabbedPane();

	private Object [] entete1={"Code/Intitule produit","Produit","Position","prix","Quantité"};
	private Object [] entete={"Code/Intitule article","Quantité"};
	private final Tableau tab=new Tableau(entete);
	private final Tableau tab_prod=new Tableau(entete1);

	private JScrollPane p=new JScrollPane(tab);
	private JScrollPane p_prod=new JScrollPane(tab_prod);
    private JScrollPane scrollPane = new JScrollPane(pan_form);


	private Java2sAutoComboBox fourniss_comb ;
	private Java2sAutoComboBox art_comb ;


    private JButton but_ajou=new JButton("Ajouter un Article");
    private JButton but_supprim=new JButton("Supprimer un Article");

    private JButton but_ajou_prod=new JButton("Ajouter un produit");
    private JButton but_supprim_prod=new JButton("Supprimer un produit");

    private ArrayList list_artnomo;



    private static String part2;
    private static String part1;
    private static String part_codegener1;
    private static String part_ref1;
    private  static String code_arti;
    private int par_ind;


    public article(final String log){

    composant(log);
}

   public void composant(final String logi_prio) {



	   JPanel panel = generalPanle();
       JPanel pan_nomo= generalPanle();
       JPanel pan_prod= generalPanle();



       declarationButton(pan_buton);
       visibiliteButton(true, false, false, false,
			false, false,false);
	    initFrame("Edition  article", pan_form, this, logi_prio,"Gestion des articles");


       replirDate(datelancement);


       replirModelImp( indice);
       indice.addActionListener(e -> {
           par_ind = 0;
           if( indice.getSelectedIndex()!=0){
               par_ind = indice.getSelectedIndex();
           }
       });



       replirArtFamille(famille_comb);
       famille_comb.addActionListener(
               e -> {
                   part1 =splitcombo( famille_comb);
                   part2=String.valueOf(famille_comb.getSelectedItem()).replace(part1+" ","");
                   if(part2.equals("Ensemble")||part2.equals("Sous Ensemble")){
                       jTabbedPane.addTab("Composant", pan_nomo);
                   }
                   else{ jTabbedPane.remove(pan_nomo);
                   }
               });


       replirArtCode(article_comb);
       article_comb.addActionListener(
               e -> {

                   titleFrame("Edition Article", pan_form);

                   removeTabRow(tab);
                   removeTabRow(tab_prod);

                   if(article_comb.getSelectedIndex()==0){
                       jTabbedPane.remove(pan_nomo);

                       article_comb.setSelectedIndex(0);
                       ref_comb.setSelectedIndex(0);
                       famille_comb.setSelectedIndex(0);
                       codegener_comb.setSelectedIndex(0);
                       fourniss_comb.setSelectedIndex(0);
                       indice.setSelectedIndex(0);
                       coeff_jtext.setText("0");
                       datelancement.setDate(Calendar.getInstance().getTime());
                       designation.setText("");
                       model_jtext.setText("");
                       description.setText("");
                       codeprod.setText("");
                       codeprod.enable();
                       visibiliteButton(true, false, false, false,
                               false, false,false);
                   }
                   else {

                       if(article_comb.getItemCount()>1){
                           code_arti =splitcombo( article_comb);

                           visibiliteButton(false, true, true, true,
                                   false, false,false);
                           codeprod.disable();

                           gestion_article_famille.selection(code_arti);

                           ArrayList list_art_tab=art.select_nomo(code_arti);
                           remplirTab(list_art_tab, 2,  tab,0);

                           ArrayList list_art_tab_prod=art.select_produi_art(code_arti);
                           remplirTab(list_art_tab_prod,5,  tab_prod,0);


                           ref_comb.setSelectedItem(gestion_article_famille.code_ref +" "+ gestion_article_famille.designation_ref);
                           codegener_comb.setSelectedItem(gestion_article_famille.code_gener +" "+ gestion_article_famille.designation_gener);
                           famille_comb.setSelectedItem(gestion_article_famille.code_famille +" "+ gestion_article_famille.designation_famille);

                           coeff_jtext.setText(""+ gestion_article_famille.coeff);
                           model_jtext.setText(gestion_article_famille.model);

                           designation.setText(gestion_article_famille.designation);

                           datelancement.getEditor().setText(gestion_article_famille.date_deplacement);
                           description.setText(gestion_article_famille.description);
                           codeprod.setText(gestion_article_famille.code_article);
                           indice.setSelectedIndex(gestion_article_famille.indice);
                       }
                   }

               });




       replirRefArticle(ref_comb);
       ref_comb.addActionListener(
               (ActionEvent e) -> part_ref1 =splitcombo( ref_comb));


       replirCodeGener(codegener_comb);
       codegener_comb.addActionListener(
               e -> part_codegener1 =splitcombo( codegener_comb));



       ArrayList list_fourniss = frns.select_fournisseur_code();
       fourniss_comb= remplirTabCombo(list_fourniss,2);


       list_artnomo=art.select_article_code();
       art_comb=remplirTabCombo(list_artnomo,2);


       ArrayList list_model = art.select_model();
       Java2sAutoComboBox model_comb = remplirTabCombo(list_model, 1);


       TableColumn gradeColumn = tab.table.getColumnModel().getColumn(0);
       gradeColumn.setCellEditor(new DefaultCellEditor(art_comb));

       TableColumn gradeColumn2 = tab_prod.table.getColumnModel().getColumn(1);
       gradeColumn2.setCellEditor(new DefaultCellEditor(model_comb));


       retour.addActionListener(
			e -> {
				titleFrame("Edition Article", pan_form);
				article_comb.setSelectedIndex(0);
				visibiliteButton(true, false, false, false,
						false, false,false);
			});



	Supprimer.addActionListener(
			e -> {
				titleFrame("Supprimmer Article", pan_form);
				visibiliteButton(false, false, false, true,
						false, false,true);

			});

	valid_supp.addActionListener(
			e -> {

				art.setdelete(code_arti,designation.getText());
                replirArtCode(article_comb);

                list_artnomo=art.select_article_code();
                art_comb=remplirTabCombo(list_artnomo,2);

			});


    modifier.addActionListener(
            e -> {
                titleFrame("Modifier Article", pan_form);
                visibiliteButton(false, false, false, true,
                        false, true,false);
            });

    valid_modif.addActionListener(
            e -> {
                msg="";
                String msg=ValidChamp();
                if(msg.equals("")){

                    art.setupdate(designation.getText(), datelancement.getEditor().getText(),
                            description.getText(), part_ref1, part_codegener1,
                            code_arti, part1,
                            Integer.parseInt(coeff_jtext.getText()),par_ind,model_jtext.getText());


                    art.delete_nomoclature(code_arti);
                    art.delete_produit(code_arti);
                    update_ajout_tabs();
                }
                else{
                    JOptionPane.showMessageDialog(null,msg);}

            });



       ajouter.addActionListener(
               e -> {
                   titleFrame("Ajouter Article", pan_form);
                   visibiliteButton(false, false, false, true,
                           true, false,false);
              });

       valid_ajou.addActionListener(
               e -> {
                   msg="";

                   String msg=ValidChamp();
                   if(msg.equals("")) {

                       art.setinsert(designation.getText(),
                               datelancement.getEditor().getText(),
                               description.getText(), part_ref1, part_codegener1, codeprod.getText(), part1,
                               Integer.parseInt(coeff_jtext.getText()),
                               par_ind, model_jtext.getText());
                         update_ajout_tabs();
                   }
                   else{JOptionPane.showMessageDialog(null,msg);}
               });







       removeOneRowTab(but_supprim_prod, tab_prod);
       removeOneRowTab(but_supprim, tab);

           but_ajou.addActionListener(
                   e -> {
                       tab.ajouter();

                       addOneRow(tab);

                   });

             but_ajou_prod.addActionListener(
                     e -> {
                    tab_prod.ajouter(fourniss_comb);

                    addOneRow(tab_prod);

                 });











       actionFocusAddAction(codeprod);









   ////////////************************** style de label ***********************/

       styleLabel(lab_combo);
       styleLabel(ref_lab_combo);
       styleLabel(codegener_lab);
       styleLabel(codeprod_lab);
       styleLabel(famille_lab);
       styleLabel(designation_lab);
       styleLabel(coeff_lab);
       styleLabel(datelancement_lab);
       styleLabel(description_lab);

       styleLabel(indice_lab);
       styleLabel(model_lab);




       ////////////************************** titre dans les panel ***********************/

       titleFrame("Information Article", pan_artgener);
       titleFrame("Codification", pan_codeprogener);
       titleFrame("Info general", pan_infogener);







    	//**************************add component in panel*************************


       pan_nomo.add(pan_tab);
       pan_tab.add(p);
       pan_tab.add(pan_but_poste);


       pan_prod.add(pan_tab_prod);
       pan_tab_prod.add(p_prod);
       pan_tab_prod.add(pan_but_prod);

       jTabbedPane.add("Edition Article",panel);
       jTabbedPane.add("Produit fournisseur",pan_prod);
		 panel.add(pan_gener);
		 pan_gener.add(scrollPane);
	     pan_form.add(pan_form1);
	     pan_form.add(pan_form2);

	     pan_form1.add(pan_artgener);
	     pan_form1.add(pan_codeprogener);
	     pan_form2.add(pan_infogener);
	     pan_gener.add(pan_buton);

	     pan_infogener.add(pan_coeff);
         pan_infogener.add(pan_datelancement);

         pan_infogener.add(pan_indice);
         pan_infogener.add(pan_model);
         pan_infogener.add(pan_description);


	     pan_article.add(pan_lab_combo);
		 pan_article.add(pan_article_comb);
		 pan_lab_combo.add(lab_combo);
		 pan_article_comb.add(article_comb);
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





 	////***********************visibilite panel***********************
 		pan_indice.setOpaque(false);
 		pan_indice_lab.setOpaque(false);
 		pan_indice_comb.setOpaque(false);

 		pan_model.setOpaque(false);
 		pan_model_lab.setOpaque(false);
 		pan_model_jtext.setOpaque(false);

       scrollPane.getViewport().setOpaque(false);
       p.getViewport().setOpaque(false);
       p_prod.getViewport().setOpaque(false);
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
       pan_artgener.setOpaque(false);

       pan_infogener.setOpaque(false);
       pan_article.setOpaque(false);
       pan_lab_combo.setOpaque(false);
       pan_article_comb.setOpaque(false);

       pan_designation.setOpaque(false);
       pan_designation_lab.setOpaque(false);
       pan_designation_jtext.setOpaque(false);
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

       pan_form.setOpaque(false);
       pan_form1.setOpaque(false);
       pan_form2.setOpaque(false);
       pan_gener.setOpaque(false);

       jTabbedPane.setOpaque(false);
       pan_tab.setOpaque(false);
       pan_tab_prod.setOpaque(false);
       pan_but_poste.setOpaque(false);
       pan_but_prod.setOpaque(false);



       ////***********************Layout of  panel***********************
         pan_gener.setLayout(new BoxLayout(pan_gener,BoxLayout.Y_AXIS));
         pan_form.setLayout(new BoxLayout(pan_form,BoxLayout.X_AXIS));
         pan_form1.setLayout(new BoxLayout(pan_form1,BoxLayout.Y_AXIS));
         pan_form2.setLayout(new BoxLayout(pan_form2,BoxLayout.Y_AXIS));
         pan_designation.setLayout(new BoxLayout(pan_designation,BoxLayout.X_AXIS));
         pan_article.setLayout(new BoxLayout(pan_article,BoxLayout.X_AXIS));
         pan_indice.setLayout(new BoxLayout(pan_indice, BoxLayout.X_AXIS));

         pan_famille.setLayout(new BoxLayout(pan_famille,BoxLayout.X_AXIS));
         pan_codegener.setLayout(new BoxLayout(pan_codegener,BoxLayout.X_AXIS));
         pan_refer.setLayout(new BoxLayout(pan_refer,BoxLayout.X_AXIS));
         pan_codeprod.setLayout(new BoxLayout(pan_codeprod,BoxLayout.X_AXIS));

         pan_description.setLayout(new BoxLayout(pan_description,BoxLayout.X_AXIS));
         pan_model.setLayout(new BoxLayout(pan_model,BoxLayout.X_AXIS));

         pan_datelancement.setLayout(new BoxLayout(pan_datelancement,BoxLayout.X_AXIS));
         pan_coeff.setLayout(new BoxLayout(pan_coeff,BoxLayout.X_AXIS));

         pan_tab.setLayout(new BoxLayout(pan_tab,BoxLayout.Y_AXIS));
         pan_tab_prod.setLayout(new BoxLayout(pan_tab_prod,BoxLayout.Y_AXIS));



    	pan_indice_lab.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pan_indice_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_coeff_lab.setLayout( new FlowLayout( FlowLayout.RIGHT));
        pan_coeff_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_lab_combo.setLayout( new FlowLayout( FlowLayout.RIGHT));
        pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

       pan_ref_lab.setLayout( new FlowLayout( FlowLayout.RIGHT ));
       pan_ref_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

       pan_ref_lab.setLayout( new FlowLayout( FlowLayout.RIGHT ));
       pan_ref_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

       pan_lab_combo.setLayout( new FlowLayout( FlowLayout.RIGHT));
       pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

       pan_famille_lab.setLayout( new FlowLayout( FlowLayout.RIGHT));
       pan_famille_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

       pan_model_lab.setLayout( new FlowLayout( FlowLayout.RIGHT));
       pan_model_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

       pan_description_lab.setLayout( new FlowLayout( FlowLayout.RIGHT));
       pan_description_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

       pan_datelancement_lab.setLayout( new FlowLayout( FlowLayout.RIGHT));
       pan_datelancement_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

       pan_codegener_lab.setLayout( new FlowLayout( FlowLayout.RIGHT));
       pan_codegener_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

       pan_ref_lab.setLayout( new FlowLayout( FlowLayout.RIGHT));
       pan_ref_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

       pan_codeprod_lab.setLayout( new FlowLayout( FlowLayout.RIGHT));
       pan_codeprod_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

       pan_designation_lab.setLayout( new FlowLayout( FlowLayout.RIGHT));
       pan_designation_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

       panel.setLayout(new GridLayout(1, 1));

       ////// ***************************border in panel

       pan_lab_combo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0,0));
       pan_article_comb.setBorder(BorderFactory.createEmptyBorder(10, 32, 0,0));

       pan_famille_lab.setBorder(BorderFactory.createEmptyBorder(10, 88, 0,0));
       pan_famille_comb.setBorder(BorderFactory.createEmptyBorder(10, 35, 0,0));

       pan_designation_lab.setBorder(BorderFactory.createEmptyBorder(10, 45, 0,0));
       pan_designation_jtext.setBorder(BorderFactory.createEmptyBorder(10, 25, 0,0));


       pan_codegener_lab.setBorder(BorderFactory.createEmptyBorder(10, 65, 0,0));
       pan_codegener_comb.setBorder(BorderFactory.createEmptyBorder(10, 40, 0,0));

       pan_ref_lab.setBorder(BorderFactory.createEmptyBorder(10, 50, 0,0));
       pan_ref_comb.setBorder(BorderFactory.createEmptyBorder(10, 30, 0,0));


       pan_codeprod_lab.setBorder(BorderFactory.createEmptyBorder(0, 25, 0,0));
       pan_codeprod_jtext.setBorder(BorderFactory.createEmptyBorder(0, 35, 0,0));


        pan_indice_lab.setBorder(BorderFactory.createEmptyBorder(10, 35, 0, 0));
		pan_indice_comb.setBorder(BorderFactory.createEmptyBorder(10, 56, 0, 0));


         p_prod.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));

         pan_datelancement_lab.setBorder(BorderFactory.createEmptyBorder(10, 5, 0,0));
         pan_datelancement_jtext.setBorder(BorderFactory.createEmptyBorder(10, 55, 0,0));


         pan_model_lab.setBorder(BorderFactory.createEmptyBorder(10, 60, 0,0));
         pan_model_jtext.setBorder(BorderFactory.createEmptyBorder(10, 60, 0,0));


         pan_coeff_lab.setBorder(BorderFactory.createEmptyBorder(10, 37, 0,0));
         pan_coeff_jtext.setBorder(BorderFactory.createEmptyBorder(10, 55, 0,0));

         pan_description_lab.setBorder(BorderFactory.createEmptyBorder(10,40, 0,0));
         pan_description_jtext.setBorder(BorderFactory.createEmptyBorder(10, 50, 0,0));



           p.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));
           pan_but_poste.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));
           pan_but_prod.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));







/////******************coponent size &and style ****************



       styleButtonTab(but_ajou,pan_but_poste);
       styleButtonTab(but_supprim,pan_but_poste);
       styleButtonTab(but_ajou_prod,pan_but_prod);
       styleButtonTab(but_supprim_prod,pan_but_prod);


       styleComponent(article_comb);
       styleComponent(model_jtext);
       styleComponent(model_jtext);
       styleComponent(ref_comb);
       styleComponent(famille_comb);
       styleComponent(indice);
       styleComponent(codegener_comb);
       styleComponent(datelancement);
       styleComponent(coeff_jtext);
       styleComponent(codeprod);
       styleComponent(designation);


       /////******************style table****************
       StyleTable(this, tab);
       StyleTable(this, tab_prod);





         description.setPreferredSize(new Dimension(250,100));
         p.setPreferredSize(new Dimension(900, 430));
         p_prod.setPreferredSize(new Dimension(900, 430));
 	     pan_but_poste.setPreferredSize(new Dimension(900, 80));
 	     pan_but_prod.setPreferredSize(new Dimension(900, 80));
         pan_form1.setPreferredSize(new Dimension(400, 400));
         pan_form2.setPreferredSize(new Dimension(400, 400));



		    setContentPane(jTabbedPane);

}




/////******************** verifier champ avant ajouter*****************/
private void checkTable(Tableau table) {
    String col2="",col1="";
    boolean number=false, duplique=false;

    for (int i = 0; i < table.table.getRowCount(); i++) {
        for (int j = 0; j < table.table.getColumnCount(); j++) {

            String nom = table.table.getColumnName(j);

            if (nom.equals("prix")) col1 = table.table.getValueAt(i, j).toString();
            if (nom.equals("Quantité")) col2 = table.table.getValueAt(i, j).toString();
        }

        if (!isValid(col2)&&!col2.equals("")) {
                 number=true;
        }


        if (!isValid(col1)&&!col1.equals("")) {
            number=true;
        }
        for (int j = i + 1; j < table.table.getRowCount(); j++) {
            if (table.table.getValueAt(i, 0).toString().equals(table.table.getValueAt(j, 0).toString())) {
                duplique=true;
            }
        }}


    if (!vaidCheck(table)) {
        msg += "Un Champ dans le tableau est vide \n";
    } else if (table.table.getRowCount() == 0) {
        msg += "Vous devez tout d'abord ajouter le produit fournisseur ou l'article composé";
    } else if(number) {
        msg += "La quantite ou le prix dans le tableau doit étre un nombre \n";
    }
    else if(duplique){

        msg += "Il existe un ou plusieur articles dupliqués \n";
    }
}

    private String ValidChamp() {

        if(tab.table.getCellEditor()!=null){
            tab.table.getCellEditor().stopCellEditing();
        }
        if(codeprod.getText().equals("")){
            msg+="Veuillez  saisir le code d'article \n";
        }
        else if(codegener_comb.getSelectedIndex()==0){
            msg+="Veuillez selectionner un code générique \n";
        }

        else if(ref_comb.getSelectedIndex()==0){
            msg+="Veuillez selectionner une réfèrence \n";
        }

        else if(designation.getText().equals("")){
            msg+="Veuillez saisir la designation de l'article\n";
        }

        else if(famille_comb.getSelectedIndex()==0){
            msg+="Veuillez selectionner un type article \n";
        }

        else if(coeff_jtext.getText().equals("")){
            msg+="Veuillez saisir le coefficient \n";
        }
        if(gestion_article_famille.exist_article(codeprod.getText(), designation.getText())) {
            msg+="La designation article existe déjà.";
        }

        else if(part2.equals("Ensemble")||part2.equals("Sous Ensemble")){
            checkTable(tab);

        }

        checkTable(tab_prod);



        return msg;

    }





    /////// *******************action jtext code et designatiojn if existe*****************

    private void actionFocusAddAction(JTextField codeprode){
        codeprode.addActionListener(
                e -> existJtextCode(codeprode));
        codeprode.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {

                existJtextCode(codeprode);
            }
            public void focusGained(FocusEvent arg0) {
            }
        });
    }
    private void existJtextCode(JTextField field) {

        String code = field.getText();
        boolean existe = gestion_article_famille.selection(code);

                   if (existe) {

                       article_comb.setSelectedItem(gestion_article_famille.code_article + " " + gestion_article_famille.designation);

                   }

   }

   private void update_ajout_tabs(){

       String code_art = codeprod.getText();
       for(int i=0;i<tab.table.getRowCount();i++){
           String col2= tab.table.getValueAt(i, 1).toString();
           int qte=Integer.parseInt(col2);
           String[] partsa=tab.table.getValueAt(i, 0).toString().split(" ");
           String code_nomo_art = partsa[0]; // 004
           art.update_nomoclature(code_art,code_nomo_art,qte);
       }

       for(int i=0;i<tab_prod.table.getRowCount();i++){

           String codef= tab_prod.table.getValueAt(i, 0).toString();
           String model= tab_prod.table.getValueAt(i, 1).toString();
           String position= tab_prod.table.getValueAt(i, 2).toString();

           float pu=Float.parseFloat(tab_prod.table.getValueAt(i, 3).toString());
           int qte=Integer.parseInt(tab_prod.table.getValueAt(i, 4).toString());

           art.ajouter_prod_fourniss(code_art,codef,model,pu,qte,position);
       }


       replirArtCode(article_comb);


       list_artnomo=art.select_article_code();
       art_comb=remplirTabCombo(list_artnomo,2);

       removeTabRow(tab);
       removeTabRow(tab_prod);
   }



    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new article("7");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
