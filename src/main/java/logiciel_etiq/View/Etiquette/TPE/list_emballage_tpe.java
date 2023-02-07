package logiciel_etiq.View.Etiquette.TPE;

import logiciel_etiq.Controller.gestion_imp_tpe;
import logiciel_etiq.View.generale;
import logiciel_etiq.View.tableau.Tableau;
import logiciel_etiq.constant;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



public class list_emballage_tpe extends generale {

    private Object [] entete={"Palette","Parcel","Code/Designation","Date emballage","GW","Quantité","Code Enie","IMEI","Serial Number", "Commentaire"};

    private Tableau tab=new Tableau(entete);


    private JPanel paneltext =new JPanel();

    private JPanel panRecherche=new JPanel();
    private JPanel pan_rech_lab=new JPanel();
    private JPanel pan_rech_jtext=new JPanel();
    private JLabel rech_lab=new JLabel("Recherche");
    private JTextField filterText = new JTextField("");

    private JPanel pandatedebut=new JPanel();
    private JPanel pandatedebuttext=new JPanel();
    private JPanel pandatedebutlab=new JPanel();
    private JLabel datedebut = new JLabel("Debut");
    private JXDatePicker datedebutText = new JXDatePicker();


    private JPanel pandatefin=new JPanel();
    private JPanel pandatefintext=new JPanel();
    private JPanel pandatefinlab=new JPanel();
    private JLabel datefin = new JLabel("Fin");
    private JXDatePicker datefinText = new JXDatePicker();



    private JPanel pandimension=new JPanel();
    private JPanel pandimensiontext=new JPanel();
    private JPanel pandimensionlab=new JPanel();
    private JLabel dimension_lab = new JLabel("Dimension");
    private JComboBox<String> dimension_comb= new JComboBox<>();


    private JPanel pan_button=new JPanel();
    private JButton rech_but= new JButton("Recherche");


    private JScrollPane p=new JScrollPane(tab);
    private JPanel pan_footer=new JPanel();
    private JPanel panentettab=new JPanel();


    private JLabel count=new JLabel();
    private JLabel count_palette=new JLabel();
    private JLabel count_carton=new JLabel();

    private gestion_imp_tpe imp_tpe=new gestion_imp_tpe();


    public list_emballage_tpe(final String log){

        build(tab,popupMenu);
        composant(log);}




    public void composant(String log){

        RemplirDateDebutDateFin(datedebutText,  datefinText);



        JPanel panel = generalPanle();
        initFrame("Liste des Etiquette Emballage TPE", panel, this, log,"Liste des Etiquette Emballage TPE");


        styleButton(rech_but,pan_button);

        remplirActDimComb( dimension_comb,"etiquette_emballage_tpe");




        recherche();
        rech_but.addActionListener(e ->
            recherche()
        );



        imprimer.addActionListener(
                e -> {
                    if (dimension_comb.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette"); }
                    else {
                         removeDoublant(tab,1);
                        if (selectedRow != 0) {
                            String parcour = constant.etiq_tpe + String.valueOf(dimension_comb.getSelectedItem()).replace("/","-") + tab.table.getSelectedRowCount() + "_" + ".pdf";
                            parameters.put("parcel", code_aimprimer);
                            Imprimer_pdf_Final(parcour, report, parameters);
                            code_aimprimer.clear();

                        } else {

                            JOptionPane.showMessageDialog(null, "Vous devez selectionner au moin une ligne");
                        }
                    }
                });





        exporter.addActionListener(
                e -> generateExcel("list_etq_tpe_emballage","Liste des etiquette TPE emballage",  tab, entete,  selectedRow));




        ///////////*************style de table
        labelStyleTable(count);
        labelStyleTable(count_carton);

        labelStyleTable(count_palette);

        StyleTable(this, tab);



        //////////*********************style label*******************
        styleLabel(datedebut);
        styleLabel(datefin);
        styleLabel(rech_lab);

        styleLabel(dimension_lab);

        //////********************************add compnent*****/
        panel.add(panentettab);
        panel.add(p);
        panel.add(pan_footer);
        panentettab.add(paneltext);

        entetTabComponent(paneltext, panRecherche, pan_rech_lab, pan_rech_jtext, filterText, rech_lab);
        entetTabComponent(paneltext, pandatedebut, pandatedebutlab, pandatedebuttext, datedebutText, datedebut);

        entetTabComponent(paneltext, pandatefin, pandatefinlab, pandatefintext, datefinText, datefin);
        entetTabComponent(paneltext, pandimension, pandimensionlab, pandimensiontext, dimension_comb, dimension_lab);

        paneltext.add(pan_button);
        pan_footer.add(count);
        pan_footer.add(count_carton);
        pan_footer.add(count_palette);


        //////********************************  Layout component *****/

        panentettab.setLayout(new FlowLayout(  FlowLayout.LEFT));
        pan_button.setLayout(new FlowLayout(  FlowLayout.LEFT));
        paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        pan_footer.setLayout(new FlowLayout(  FlowLayout.LEFT));



        //////********************************  border  component *****/
        pan_button.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        paneltext.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 50));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        //////********************************  visibilite de panel *****/

        panel.setOpaque(false);
        paneltext.setOpaque(false);
        panentettab.setOpaque(false);

        panRecherche.setOpaque(false);
        pan_rech_jtext.setOpaque(false);
        pan_rech_lab.setOpaque(false);

        pandatedebut.setOpaque(false);
        pandatedebutlab.setOpaque(false);
        pandatedebuttext.setOpaque(false);

        pandatefin.setOpaque(false);
        pandatefinlab.setOpaque(false   );
        pandatefintext.setOpaque(false);

        pandimension.setOpaque(false);
        pandimensionlab.setOpaque(false);
        pandimensiontext.setOpaque(false);

        pan_button.setOpaque(false);
        pan_footer.setOpaque(false);
        p.setOpaque(false);


        //*************************set component size
        p.setPreferredSize(new Dimension(700,700));

        //************************* add component to
        popupMenu.add(imprimer);
        popupMenu.add(exporter);
        setContentPane(panel);


    }


   public void recherche(){
       ArrayList list_fiche=imp_tpe.select_tpe_emballage(filterText.getText(),datedebutText.getDate(),datefinText.getDate());
       remplirTab(list_fiche, 10,  tab,0);

       count_palette.setText("Nombre Palette : " +imp_tpe.nbr_palette_tpe(filterText.getText(),datedebutText.getDate(),datefinText.getDate(),"parcel_palette" ));
       count_carton.setText("Nombre Carton : " +imp_tpe.nbr_palette_tpe(filterText.getText(),datedebutText.getDate(),datefinText.getDate(),"parcel"));
       count.setText("Nombre Total : " +tab.table.getRowCount());
   }
    //////////////////////////methode  de rechrche globale////////////////////////////////////////////

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new list_emballage_tpe("4");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}


