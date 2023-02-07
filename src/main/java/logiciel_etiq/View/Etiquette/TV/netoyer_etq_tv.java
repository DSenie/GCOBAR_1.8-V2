package logiciel_etiq.View.Etiquette.TV;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logiciel_etiq.Controller.gestion_imp_tv;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import org.jdesktop.swingx.JXDatePicker;


public class netoyer_etq_tv extends generale {



    private JPanel pan_article = new JPanel();
    private JPanel pan_article_comb = new JPanel();
    private JPanel pan_article_lab = new JPanel();
    private JLabel article_lab = new JLabel("Liste des Articles");
    private jcombo article_comb=new jcombo(list_comb);


    private JPanel pan_chaine = new JPanel();
    private JPanel pan_chaine_comb = new JPanel();
    private JPanel pan_chaine_lab = new JPanel();
    private JLabel chaine_lab = new JLabel("Liste des Chaine");
    private jcombo chaine_comb=new jcombo(list_comb);



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
    private JLabel cont_lab = new JLabel("Conteur Debut");
    private JTextField cont_jtext = new JTextField();
    private JPanel pan_cont_lab = new JPanel();

    private JPanel pan_cont1 = new JPanel();
    private JPanel pan_cont_jtext1 = new JPanel();
    private JLabel cont_lab1 = new JLabel("Conteur Fin");
    private JTextField cont_jtext1 = new JTextField();
    private JPanel pan_cont_lab1 = new JPanel();


    private JPanel pan = new JPanel();
    private JPanel pan_general = new JPanel();
    private JPanel pan_form = new JPanel();


    private JButton but_net = new JButton("Nettoyer");
    private JButton valid_net = new JButton("Valider");
    private JButton retour = new JButton("Retour");

    private JPanel pan_gener = new JPanel();
    private JPanel pan_tab = new JPanel();
    private JPanel pan_button = new JPanel();

    private gestion_imp_tv imp_tv = new gestion_imp_tv();


    private String code;

    public netoyer_etq_tv(final String log) {

        composant(log);
    }


    public void composant(final String logi_prio) {

        JPanel panel = generalPanle();
        initFrame("Netoyer Etiquette TV", pan, this, logi_prio,"Netoyer Etiquette TV");


        replirDate(date_picker);
        date_picker.addActionListener(e ->
                code = action_chaine());



        replirArtCode(article_comb);
        article_comb.addActionListener(e -> {
            if (article_comb.getSelectedIndex() == 0) {
                article_comb.setSelectedIndex(0);
                code_jtext.setText("");

                chaine_comb.setSelectedIndex(0);
                cont_jtext.setText("");
                cont_jtext1.setText("");
                retour.setVisible(false);
                valid_net.setVisible(false);
                but_net.setVisible(true);

            } else {
                action_chaine();
            }
        });


        remplirChaine(chaine_comb);
        chaine_comb.addActionListener(e -> code = action_chaine());





        code_jtext.disable();
        but_net.setVisible(true);
        valid_net.setVisible(false);
        retour.setVisible(false);

        action_chaine();



        but_net.addActionListener(e -> {
            titleFrame("Netoyer Etiquette TV", pan);
            but_net.setVisible(false);
            valid_net.setVisible(true);
            retour.setVisible(true);
        });






        valid_net.addActionListener(e -> {
            att= new Thread(() -> {
                msg ="";

                if (article_comb.getSelectedIndex() == 0) {
                    msg += "Veuillez selectionner un article \n";
                }
                else if (chaine_comb.getSelectedIndex() == 0) {
                    msg += "Veuillez selectionner une chaine \n";
                } else if (cont_jtext.getText().equals("")) {
                    msg += "Veuillez remplir le conteur de debut \n";
                }  else if (cont_jtext1.getText().equals("")) {
                    msg += "Veuillez remplir le conteur de fin \n";
                } else if (!isValid(cont_jtext.getText())||!isValid(cont_jtext1.getText())) {
                    msg += "Le compteur doit etre un nombre \n";
                }


                if (msg.equals("")) {

                    String debut=code_jtext.getText()+""+format3.format(Integer.parseInt(cont_jtext.getText()));
                    String fin=code_jtext.getText()+""+format3.format(Integer.parseInt(cont_jtext1.getText()));

                    boolean exist=imp_tv.nettoyer_serial(debut, fin);
                    if(exist){
                        JOptionPane.showMessageDialog(null, "Les étiquettes ont été bien nettoyer");
                        article_comb.setSelectedIndex(0);

                    }


                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }	});
            att.start();
        });





        retour.addActionListener(e -> {
            titleFrame("Netoyer Etiquette TV", pan);
            article_comb.setSelectedIndex(0);
        });









        //**************style de jlabel**************************************/
         styleLabel(article_lab);
        styleLabel(chaine_lab);
        styleLabel(code_lab);
        styleLabel(cont_lab);
        styleLabel(cont_lab1);
        styleLabel(date_lab);


        //**************style de button**************************************/
        styleButton(retour,pan_button);
        styleButton(but_net,pan_button);
        styleButton(valid_net,pan_button);


        //**************add component in panel********************/

        pan_article.add(pan_article_lab);
        pan_article_lab.add(article_lab);
        pan_article.add(pan_article_comb);
        pan_article_comb.add(article_comb);

        pan_date.add(pan_date_lab);
        pan_date_lab.add(date_lab);
        pan_date.add(pan_date_jtext);
        pan_date_jtext.add(date_picker);

        pan_chaine.add(pan_chaine_lab);
        pan_chaine_lab.add(chaine_lab);
        pan_chaine.add(pan_chaine_comb);
        pan_chaine_comb.add(chaine_comb);

        pan_code.add(pan_code_lab);
        pan_code_lab.add(code_lab);
        pan_code.add(pan_code_jtext);
        pan_code_jtext.add(code_jtext);

        pan_cont1.add(pan_cont_lab1);
        pan_cont_lab1.add(cont_lab1);
        pan_cont1.add(pan_cont_jtext1);
        pan_cont_jtext1.add(cont_jtext1);


        pan_cont.add(pan_cont_lab);
        pan_cont_lab.add(cont_lab);
        pan_cont.add(pan_cont_jtext);
        pan_cont_jtext.add(cont_jtext);


        pan_form.add(pan_article);
        pan_form.add(pan_cont);
        pan_form.add(pan_cont1);
        pan_form.add(pan_date);
        pan_form.add(pan_chaine);
        pan_form.add(pan_code);
        pan_general.add(pan_button);
        pan_gener.add(pan_form);
        pan_gener.add(pan_tab);
        pan.add(pan_gener);
        pan.add(pan_general);

        panel.add(pan);


        //**************visibilite  component in panel********************/

        pan_code.setOpaque(false);
        pan_code_lab.setOpaque(false);
        pan_code_jtext.setOpaque(false);
        pan_article.setOpaque(false);
        pan_article_lab.setOpaque(false);
        pan_article_comb.setOpaque(false);

        pan_date.setOpaque(false);
        pan_date_lab.setOpaque(false);
        pan_date_jtext.setOpaque(false);
        pan_chaine.setOpaque(false);
        pan_chaine_lab.setOpaque(false);
        pan_chaine_comb.setOpaque(false);
        pan_cont1.setOpaque(false);
        pan_cont.setOpaque(false);

        pan_cont_lab.setOpaque(false);
        pan_cont_jtext.setOpaque(false);


        pan_cont_lab1.setOpaque(false);
        pan_cont_jtext1.setOpaque(false);
        pan_form.setOpaque(false);
        pan_gener.setOpaque(false);
        pan_tab.setOpaque(false);
        pan_general.setOpaque(false);
        pan.setOpaque(false);
       pan_button.setOpaque(false);





        //**************set layout in panel********************/

        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));
        pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        pan_article.setLayout(new BoxLayout(pan_article, BoxLayout.X_AXIS));


        pan_date.setLayout(new BoxLayout(pan_date, BoxLayout.X_AXIS));
        pan_chaine.setLayout(new BoxLayout(pan_chaine, BoxLayout.X_AXIS));
        pan_code.setLayout(new BoxLayout(pan_code, BoxLayout.X_AXIS));

        pan_cont1.setLayout(new BoxLayout(pan_cont1, BoxLayout.X_AXIS));
        pan_cont.setLayout(new BoxLayout(pan_cont, BoxLayout.X_AXIS));


        pan_article_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_date_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_date_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_chaine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_code_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_code_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));

        pan_cont_lab1.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_cont_jtext1.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_cont_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_cont_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));



        //**************set layout in panel********************/


        pan_article_lab.setBorder(BorderFactory.createEmptyBorder(20, 200, 0, 0));
        pan_article_comb.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        pan_date_lab.setBorder(BorderFactory.createEmptyBorder(0, 297, 0, 0));
        pan_date_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, -15));
        pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_code_lab.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, 0));
        pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_cont_lab1.setBorder(BorderFactory.createEmptyBorder(0, 240, 0, 0));
        pan_cont_jtext1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_cont_lab.setBorder(BorderFactory.createEmptyBorder(0, 220, 0, 0));
        pan_cont_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        //**************component size********************/



        styleComponent(article_comb);
        styleComponent(date_picker);
        styleComponent(chaine_comb);
        styleComponent(code_jtext);
        styleComponent(cont_jtext);
        styleComponent(cont_jtext1);




        setContentPane(panel);
    }


    ///*******************pour remplir le serial number **************/
    private String action_chaine() {

       String code_chaine = splitcombo(chaine_comb);
       String code_arti = splitcombo(article_comb);

        int indic = imp_tv.select_indice(code_arti);
        code = imp_tv.afficher_conteur(code_chaine, date_picker.getDate(), indic);
        String prefix = code.substring(0,code.length()-3);

        code_jtext.setText(prefix);
        return prefix;
    }




}
