package logiciel_etiq.View.Etiquette.Composent;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import logiciel_etiq.Controller.gestion_composant;
import logiciel_etiq.View.generale;
import logiciel_etiq.View.tableau.Tableau;
import logiciel_etiq.constant;



public class list_etq_composants extends generale {


    private Object [] entete={"Code Carton","Code/Designation","Réference","Serie Bobine","Quantité par Bobine"};

    private Tableau tab=new Tableau(entete);


    private JPanel paneltext =new JPanel();

    private JPanel panRecherche=new JPanel();
    private JPanel pan_rech_lab=new JPanel();
    private JPanel pan_rech_jtext=new JPanel();
    private JLabel rech_lab=new JLabel("Recherche");
    private JTextField filterText = new JTextField("");


    private JPanel pandimension=new JPanel();
    private JPanel pandimensiontext=new JPanel();
    private JPanel pandimensionlab=new JPanel();
    private JLabel dimension_lab = new JLabel("Dimension");
    private JComboBox<String> dimension_comb= new JComboBox<>();

    private JScrollPane p=new JScrollPane(tab);
    private JPanel pan_footer=new JPanel();
    private JPanel panentettab=new JPanel();


    private JLabel count=new JLabel();
    private gestion_composant imp_composants=new gestion_composant();


    public list_etq_composants (final String log){
        build(tab,popupMenu);
        composant(log);}




    public void composant(String log){



        JPanel panel = generalPanle();
        initFrame("Liste des Etiquette Composnats", panel, this, log,"Liste des Etiquette Composnats");



        remplirActDimComb( dimension_comb,"etiquette_composant");




        recherche();



        actionMouseJtextField(filterText);
        actionKeyJtextFieldSorter(filterText, tab,  count) ;



        imprimer.addActionListener(
                e -> {
                    if (dimension_comb.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette"); }
                    else {
                        code_aimprimer = removeDoublant(tab,0);
                        if (selectedRow != 0) {

                            String parcour = constant.etiq_composant + dimension_comb.getSelectedItem() + "_" +code_aimprimer.get(0)+"_"+code_aimprimer.size() + "etq_composant.pdf";
                            parameters.put("code_carton_parm", code_aimprimer);

                            Imprimer_pdf_Final( parcour, report, parameters);


                            code_aimprimer.clear();

                        } else {

                            JOptionPane.showMessageDialog(null, "Vous devez selectionner au moins une ligne");
                        }
                    }
                });





        exporter.addActionListener(
                e -> generateExcel("list_etq_composant","Liste des etiquette Composants",  tab, entete,  selectedRow));




        ///////////*************style de table
        labelStyleTable(count);
        StyleTable(this, tab);



        //////////*********************style label*******************

        styleLabel(rech_lab);

        styleLabel(dimension_lab);

        //////********************************add compnent*****/
        panel.add(panentettab);
        panel.add(p);
        panel.add(pan_footer);
        panentettab.add(paneltext);

        entetTabComponent(paneltext, panRecherche, pan_rech_lab, pan_rech_jtext, filterText, rech_lab);

        entetTabComponent(paneltext, pandimension, pandimensionlab, pandimensiontext, dimension_comb, dimension_lab);

        pan_footer.add(count);

        //////********************************  Layout component *****/

        panentettab.setLayout(new FlowLayout(  FlowLayout.LEFT));
        paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        pan_footer.setLayout(new FlowLayout(  FlowLayout.LEFT));



        //////********************************  border  component *****/
        paneltext.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 50));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        //////********************************  visibilite de panel *****/

        panel.setOpaque(false);
        paneltext.setOpaque(false);
        panentettab.setOpaque(false);

        panRecherche.setOpaque(false);
        pan_rech_jtext.setOpaque(false);
        pan_rech_lab.setOpaque(false);

        pandimension.setOpaque(false);
        pandimensionlab.setOpaque(false);
        pandimensiontext.setOpaque(false);

        pan_footer.setOpaque(false);
        p.setOpaque(false);


        //*************************set component size
        p.setPreferredSize(new Dimension(700,700));

        //************************* add component to
        popupMenu.add(imprimer);
        popupMenu.add(exporter);

        setContentPane(panel);


    }



    private void recherche() {
        ArrayList list_fiche=imp_composants.select_composant();
        remplirTab(list_fiche, 5,  tab,0);
        count.setText("Nombre Total : " +tab.table.getRowCount());
    }




    //////////////////////////methode  de rechrche globale////////////////////////////////////////////

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new list_etq_composants("4");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}


