package logiciel_etiq.View.Etiquette.Tablette;

import logiciel_etiq.Controller.gestion_imp_tablette;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import logiciel_etiq.constant;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class etiquette_chariot extends generale {




    private JPanel pan_chaine = new JPanel();
    private JPanel pan_chaine_comb = new JPanel();
    private JPanel pan_chaine_lab = new JPanel();
    private JLabel chaine_lab = new JLabel("Liste des Chaine");
    private jcombo chaine_comb= new jcombo(list_comb);



    private JPanel pan_color = new JPanel();
    private JPanel pan_color_comb = new JPanel();
    private JPanel pan_color_lab = new JPanel();
    private JLabel color_lab = new JLabel("Liste des Couleur");
    private JComboBox<String> color_comb=new JComboBox<>();



    private JPanel pan_code = new JPanel();
    private JPanel pan_code_jtext = new JPanel();
    private JPanel pan_code_lab = new JPanel();
    private JLabel code_lab = new JLabel("Serial Number");
    private JTextField code_jtext = new JTextField();



    private JPanel pan_date = new JPanel();
    private JPanel pan_date_jtext = new JPanel();
    private JPanel pan_date_lab = new JPanel();
    private JLabel date_lab = new JLabel("Date");
    private final JXDatePicker date_picker = new JXDatePicker();



    private JPanel pan_cont = new JPanel();
    private JPanel pan_cont_jtext = new JPanel();
    private JLabel cont_lab = new JLabel("Conteur");
    private JTextField cont_jtext = new JTextField();
    private JPanel pan_cont_lab = new JPanel();

    private JPanel pan_dimension = new JPanel();
    private JPanel pan_dimension_lab = new JPanel();
    private JPanel pan_dimension_comb= new JPanel();
    private JLabel dimension_lab = new JLabel("Dimension");
    private JComboBox<String> dimension_comb =new JComboBox<>();

    private JPanel pan = new JPanel();
    private JPanel pan_general = new JPanel();
    private JPanel pan_form = new JPanel();
    private JPanel pan_button = new JPanel();
    private JPanel pan_gener = new JPanel();
    private JPanel pan_tab = new JPanel();



    private gestion_imp_tablette imp_tablette = new gestion_imp_tablette();
    private String code;
    private int conteur;


    public etiquette_chariot(final String log) {

        composant(log);
    }


    public void composant(final String logi_prio) {
        declarationButton(pan_button);
        visibiliteButton(true, false, false, true,
                false, false,false);
        imp_etq.setVisible(false);
        JPanel panel = generalPanle();
        initFrame("Edition Etiquette Chariot", pan, this, logi_prio,"Gestion Etiquette Chariot");
        frame.setUndecorated(true);
        code_jtext.disable();



        remplirActDimComb( dimension_comb,"etiquette_chariot");



        replirDate(date_picker);
        date_picker.addActionListener(e -> action_champ());




        remplirColor(color_comb, "etiquette_chariot");
        color_comb.addActionListener(e -> addTocomboAction(color_comb));


        remplirChaine(chaine_comb);
        chaine_comb.addActionListener(e -> action_champ());


        action_champ();


        retour.addActionListener(e -> {
            titleFrame("Edition Etiquette Chariot",pan);

            chaine_comb.setSelectedIndex(0);
            color_comb.setSelectedIndex(0);
            cont_jtext.setText("");
            date_picker.setDate(Calendar.getInstance().getTime());
            visibiliteButton(true, false, false, true,
                    false, false,false);
            imp_etq.setVisible(false);
            action_champ();

        });









        ajouter.addActionListener(e -> {
            visibiliteButton(false, false, false, true,
                    true, false,false);
            imp_etq.setVisible(false);
            action_champ();
        });




        valid_ajou.addActionListener(e -> {
            code_aimprimer.clear();

            att= new Thread(() -> {
                msg = "";



                if (color_comb.getSelectedIndex() == 0) {
                    msg += "Veuillez selectionner une couleur \n";
                } else if (cont_jtext.getText().equals("")) {
                    msg += "Veuillez remplir le conteur \n";
                } else if (!isValid(cont_jtext.getText())) {
                    msg += "Le compteur doit etre un nombre \n";
                }

                if (msg.equals("")) {


                    code=code_jtext.getText();
                    conteur = Integer.parseInt(cont_jtext.getText());
                    String prefix = code.substring(0,code.length()-3);

                    int af_cont = Integer.parseInt(code.substring(code.length()-3));

                    StylePopup(af_cont,conteur);






                    for(int i=af_cont;i<=af_cont+conteur-1;i++ ) {

                        code =prefix+ format3.format(i);
                        imp_tablette.ajouter_chariot(code,(String) color_comb.getSelectedItem(),splitcombo(chaine_comb),date_picker.getDate());
                        code_aimprimer.add(code);

                        progressBar.setValue(i);
                        progressBar.setStringPainted(true);
                        frame.validate();
                        frame.setVisible(true);

                    }



                    frame.setVisible(false);
                    controlPanel.remove(titr);
                    controlPanel.remove(progressBar);


                    action_champ();



                    visibiliteButton(true, false, false, true,
                            false, false,false);
                    imp_etq.setVisible(true);


                    JOptionPane.showMessageDialog(null, "l'étiquette a été bien ajouté");

                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }	});
            att.start();
        });


        imp_etq.addActionListener(
                event -> {
                    att= new Thread(() -> {

                        if (dimension_comb.getSelectedIndex() == 0) {
                            JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette"); }
                        else {

                            parameters.put("serial", code_aimprimer);
                            String parcour = constant.etiq_tablette + String.valueOf(dimension_comb.getSelectedItem()).split(" ")[0] +"_"+code+"_"+ conteur + "_Etiquette_chariot" + ".pdf";



                            Imprimer_pdf_Final( parcour, report, parameters);
                        }
                    });

                    att.start();

                });






        ////******************************style label

        styleLabel(chaine_lab);
        styleLabel(code_lab);
        styleLabel(cont_lab);
        styleLabel(dimension_lab);
        styleLabel(color_lab);
        styleLabel(date_lab);


        ////******************************ass component in panel **********************************/

        pan_date.add(pan_date_lab);
        pan_date_lab.add(date_lab);
        pan_date.add(pan_date_jtext);
        pan_date_jtext.add(date_picker);

        pan_color.add(pan_color_lab);
        pan_color_lab.add(color_lab);
        pan_color.add(pan_color_comb);
        pan_color_comb.add(color_comb);


        pan_chaine.add(pan_chaine_lab);
        pan_chaine_lab.add(chaine_lab);
        pan_chaine.add(pan_chaine_comb);
        pan_chaine_comb.add(chaine_comb);


        pan_code.add(pan_code_lab);
        pan_code_lab.add(code_lab);
        pan_code.add(pan_code_jtext);
        pan_code_jtext.add(code_jtext);


        pan_cont.add(pan_cont_lab);
        pan_cont_lab.add(cont_lab);
        pan_cont.add(pan_cont_jtext);
        pan_cont_jtext.add(cont_jtext);


        pan_dimension.add(pan_dimension_lab);
        pan_dimension_lab.add(dimension_lab);
        pan_dimension.add(pan_dimension_comb);
        pan_dimension_comb.add(dimension_comb);

        pan_form.add(pan_code);
        pan_form.add(pan_date);
        pan_form.add(pan_chaine);
        pan_form.add(pan_color);

        pan_form.add(pan_cont);
        pan_form.add(pan_dimension);

        pan_general.add(pan_button);

        pan_gener.add(pan_form);
        pan_gener.add(pan_tab);
        pan.add(pan_gener);
        pan.add(pan_general);

        panel.add(pan);



        ////******************************set visibilite of panel **********************************/

        pan_form.setOpaque(false);

        pan_color.setOpaque(false);
        pan_color_lab.setOpaque(false);
        pan_color_comb.setOpaque(false);

        pan_date.setOpaque(false);
        pan_date_lab.setOpaque(false);
        pan_date_jtext.setOpaque(false);

        pan_chaine.setOpaque(false);
        pan_chaine_lab.setOpaque(false);
        pan_chaine_comb.setOpaque(false);


        pan_code.setOpaque(false);
        pan_code_lab.setOpaque(false);
        pan_code_jtext.setOpaque(false);

        pan_cont.setOpaque(false);
        pan_cont_lab.setOpaque(false);
        pan_cont_jtext.setOpaque(false);



        pan_dimension.setOpaque(false);
        pan_dimension_lab.setOpaque(false);
        pan_dimension_comb.setOpaque(false);

        pan_button.setOpaque(false);

        pan_gener.setOpaque(false);
        pan_tab.setOpaque(false);
        pan_general.setOpaque(false);
        pan.setOpaque(false);


        ////******************************Layout  of panel and component  **********************************/

        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

        pan_button.setLayout(new FlowLayout(FlowLayout.CENTER));


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));
        pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));

        pan_date.setLayout(new BoxLayout(pan_date, BoxLayout.X_AXIS));
        pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));
        pan_code.setLayout(new BoxLayout(pan_code, BoxLayout.X_AXIS));
        pan_chaine.setLayout(new BoxLayout(pan_chaine, BoxLayout.X_AXIS));

        pan_cont.setLayout(new BoxLayout(pan_cont, BoxLayout.X_AXIS));


        pan_color.setLayout(new BoxLayout(pan_color, BoxLayout.X_AXIS));

        pan_date_lab.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_date_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_color_lab.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_color_comb.setLayout(new FlowLayout(FlowLayout.LEFT));


        pan_chaine_lab.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_code_lab.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_code_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_cont_lab.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_cont_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));





        ////******************************sizze of component  component  **********************************/

        styleButton(imp_etq,pan_button);

        styleComponent(dimension_comb);
        styleComponent(chaine_comb);
        styleComponent(date_picker);
        styleComponent(code_jtext);
        styleComponent(cont_jtext);
        styleComponent(color_comb);



        ////******************************component border  **********************************/

        pan_code_lab.setBorder(BorderFactory.createEmptyBorder(20, 200, 0, 0));
        pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 0));


        pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 273, 0, 0));
        pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 187, 0, 0));
        pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));


        pan_color_lab.setBorder(BorderFactory.createEmptyBorder(0, 180, 0, 0));
        pan_color_comb.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));


        pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 235, 0, 0));
        pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));


        pan_cont_lab.setBorder(BorderFactory.createEmptyBorder(0, 245, 0, 0));
        pan_cont_jtext.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));



        setContentPane(panel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {

                new etiquette_chariot("7");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }



    private void action_champ(){
        System.out.println();
        if(chaine_comb.getSelectedIndex()!=0)
            code_chaine=splitcombo(chaine_comb).substring(2).trim();

        String code=imp_tablette.afficher_conteur(date_picker.getDate(),code_chaine,"etiquette_chariot","CT","code","date_chariot");
        code_jtext.setText(code);
    }


}
