package logiciel_etiq.View;

import logiciel_etiq.Controller.*;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.tableau.Java2sAutoComboBox;
import logiciel_etiq.View.tableau.Tableau;
import logiciel_etiq.constant;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import javax.print.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXDatePicker;

import static logiciel_etiq.constant.chemin_report;

public class generale extends JFrame {


    /////*****************button componet
    public  JButton  ajouter;
    public  JButton  modifier;
    public  JButton  Supprimer;
    public  JButton  retour;
    public  JButton valid_ajou;
    public  JButton valid_modif;
    public  JButton valid_supp;
    protected JButton but_ajou = new JButton("Ajouter une ligne");
    protected JButton but_supprim = new JButton("Supprimer une ligne");
    protected  JButton imp_etq = new JButton("Imprimer");

    /////*****************ArrayList********************************/

    public  Object[] list_comb=new ArrayList<>(Collections.emptyList()).toArray();
    public  ArrayList<Object> code_aimprimer=new ArrayList<>();
    public   Map<String, Object> parameters = new HashMap<>();


    /////***************** controller instance  ********************************/
    private gestion_general_imp imp_general=new gestion_general_imp();
    private  gestion_chaine chaine=new gestion_chaine();
    private gestion_produit prod=new gestion_produit();
    protected   gestion_article_famille art=new gestion_article_famille();
    private  gestion_reference_codegener ref_famille=new gestion_reference_codegener();
    protected  gestion_fournisseur frns=new gestion_fournisseur();

    //********* Popup Menu*******************************/
    protected  JPopupMenu popupMenu = new JPopupMenu();
    protected  JMenuItem imprimer = new JMenuItem("Imprimer" );
    protected  JMenuItem exporter = new JMenuItem("Exporter en excel" );

   //********* Formet de données *******************************/

    private Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,15);
    private   Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
    protected SimpleDateFormat dateform = new SimpleDateFormat("yyyy-MM-dd");
    protected NumberFormat format3 = new DecimalFormat("000");
    protected NumberFormat format2 = new DecimalFormat("00");
    protected NumberFormat format4 = new DecimalFormat("0000");


    //********* Declaration variable *******************************/

    public   String report;
    public   String code_chaine="";
    public   String  code_article="";
    protected int selectedRow;
    public   String msg;
    protected Thread att;


    //********* autre composant *******************************/

    protected JPanel controlPanel = new JPanel();
    public JFrame frame= new JFrame();
    protected JProgressBar progressBar;
    protected JLabel titr=new JLabel();


/////********************************************** les action sur les combobox***************************************/


//*************** retirer la premier partie de JCombobx******************////

    public  String splitcombo(jcombo comb){
        String code = "";
        if (comb.getSelectedIndex() != 0) {
            String[] part_art = String.valueOf(comb.getSelectedItem()).split(" ");
            code = part_art[0];
        }
        return code;
    }

    ////***************remplir Produit  ****************/
    protected void replirProduit(JComboBox produit_jcom) {

        ArrayList list_produit = prod.select_produit_code();
        remplirCombo(list_produit, produit_jcom, 2);
    }

    ////***************remplir model d'impression ****************/
    protected void replirModelImp(JComboBox indice) {
        ArrayList<String> resultat_model = art.model_imp();
        remplirCombo(resultat_model, indice, 2);
    }


    ////***************remplir fournisseur ****************/
    protected void replirFournisseur(JComboBox fournisseur_comb) {
        ArrayList list_fournisseur= frns.select_fournisseur_code();
        remplirCombo(list_fournisseur,fournisseur_comb,2);

    }

    ////***************remplir la famille d'article****************/
    protected void replirArtFamille(JComboBox famille_comb) {
        ArrayList list_artfamille = art.select_artfamille_code();
        remplirCombo(list_artfamille, famille_comb, 2);
    }

    ////***************remplir remplir le code art****************/
    protected void replirArtCode(JComboBox article) {
        ArrayList list_article = art.select_article_code();
        remplirCombo(list_article, article, 2);
    }


    ////***************remplir la reference  article****************/
    protected void replirRefArticle(JComboBox ref_comb) {
        ArrayList list_reference = ref_famille.select_reference_code();
        remplirCombo(list_reference, ref_comb, 2);
    }


    ////***************remplir date picker****************/
    protected void replirCodeGener(JComboBox codegener_comb) {
        ArrayList list_famille = ref_famille.select_famille_code();
        remplirCombo(list_famille, codegener_comb, 2);
    }


        ////***************remplir date picker****************/
   protected void replirDate(JXDatePicker date_picker) {
        Calendar cal = Calendar.getInstance();
        date_picker.setDate(cal.getTime());
        date_picker.getEditor().setEditable(true);
        date_picker.setFormats(dateform);
    }

    ////***************remplir date picker debut et fin****************/
    protected void RemplirDateDebutDateFin(JXDatePicker datedebutText, JXDatePicker datefinText){
        Calendar cal_greg = new GregorianCalendar();
        cal_greg.set(Calendar.DAY_OF_YEAR, 1);
        datedebutText.setDate(cal_greg.getTime());
        cal_greg.set(Calendar.DAY_OF_YEAR, 366);
        datefinText.setDate(cal_greg.getTime());
        datedebutText.setFormats(dateform);
        datefinText.setFormats(dateform);
    }


    //// remplir le combobx de chaine********/
    protected void remplirChaine(jcombo chaine_comb){
        ArrayList list_chaine = chaine.select_chaine_code();
        remplirCombo(list_chaine,chaine_comb,2);
        chaine_comb.setSelectedIndex(0);
    }

    //// remplir le combobx de article en choisisant le type de produit tablette, tpe.....//********/

    protected void remplirArticleType(jcombo article_comb, int indice){
        ArrayList list_article = imp_general.select_article_code_type(indice);
        remplirCombo(list_article, article_comb,2);
        article_comb.setSelectedIndex(0);
    }



    //////**********remplir size carton ***********/
    protected void remplirSizeCarton(JComboBox sizecart_comb, String table) {
        ArrayList list_size = imp_general.select_size(table);
        remplirCombo(list_size, sizecart_comb, 1);
        sizecart_comb.setSelectedIndex(0);
        sizecart_comb.setEditable(true);

    }

    //////**********remplir poit carton ***********/

    protected void remplirGw(JComboBox gw_comb, String table) {
        ArrayList list_gw = imp_general.select_gw(table);
        remplirCombo(list_gw, gw_comb, 1);
        gw_comb.setSelectedIndex(0);
        gw_comb.setEditable(true);
    }


    //////**********remplir NW carton ***********/

    protected void remplirNw(JComboBox nw_comb, String table) {
        ArrayList list_gw = imp_general.select_nw(table);
        remplirCombo(list_gw, nw_comb, 1);
        nw_comb.setSelectedIndex(0);
        nw_comb.setEditable(true);
    }



    ////**********remplir les palette *********/
    protected void remplirPalette(JComboBox<String> palette_comb, String prefix) {
        ArrayList<String> select_palette = imp_general.select_palette(prefix);
        palette_comb.addItem("---Selectionner une Palette-----");
        for(int i = 0; i < select_palette.size(); i++) {
            palette_comb.addItem(select_palette.get(i));
        }
        palette_comb.setSelectedIndex(palette_comb.getItemCount() - 1);
    }




    ////*********************** remplir comboColor******************/
    protected void remplirColor(JComboBox color_comb, String table) {
        ArrayList list_color = imp_general.select_color(table);
        remplirCombo(list_color, color_comb, 1);
        color_comb.setEditable(true);
    }

    //*********************** remplir et action de combobx de dimension **********************/
    public void remplirActDimComb(JComboBox<String> dimension_comb,String indice) {

         dimension_comb.addItem("--- Sélectionner la dimension de l'etiquette ----");
         ArrayList list_dimension = imp_general.select_dimension_etq(indice);
         for (int i = 0; i < list_dimension.size(); i++) {
             dimension_comb.addItem((String) list_dimension.get(i));
         }

         dimension_comb.addActionListener(
                event -> {
                    if(dimension_comb.getSelectedIndex()!=0)
                        report=imp_general.select_report(String.valueOf(dimension_comb.getSelectedItem()),indice);
                });

    }




//////////// action pour les fentre qui contient un combo , et deux jtaxt

    protected void actionComboDeuxChamp(JComboBox combo, JTextField code, JTextField intitule, int casvider){
        if (combo.getSelectedIndex() == 0) {
            if(casvider==1) {
                code.enable();
                code.setText("");
            }
            else{
                code.disable();
            }

            intitule.setText("");
            visibiliteButton(true, false, false, false,
                    false, false, false);
            combo.setSelectedIndex(0);
        } else {
            if (combo.getItemCount() > 1) {
                code.disable();
                visibiliteButton(false, true, true, false,
                        false, false, false);
                String[] parts = String.valueOf(combo.getSelectedItem()).split(" ");
                String part1 = parts[0];
                String part2 = String.valueOf(combo.getSelectedItem()).replace(part1 + " ", "");

                code.setText(part1);
                intitule.setText(part2);
                combo.setSelectedItem(part1 + " " + part2);
            }

        }

    }

     ///***************************methode    de remplisage sde compbobox dans le tableau ******************/

    protected Java2sAutoComboBox remplirTabCombo(ArrayList list_bdd, int pas){

        ArrayList<String> liste_total=new ArrayList<>();
        liste_total.clear();

        for(int i=0;i<list_bdd.size();i++)
        {
            if(pas==2) {
                liste_total.add(list_bdd.get(i) + " " + list_bdd.get(i + 1));
                i++;
            }
            else{
                liste_total.add((String) list_bdd.get(i));
            }
        }
        return new Java2sAutoComboBox(liste_total);
    }

    ///***************************methode    de remplisage sde compbobox dans la fenetre ******************/

    protected  void remplirCombo(ArrayList list_bdd,JComboBox combo, int pas){
        combo.removeAllItems();
        combo.addItem("---Selectionner un élement-----");
        for(int i=0;i<list_bdd.size();i=i+1)
        {
            //Pour affecter une valeur de base de données
            if(pas==2) {
                combo.addItem(list_bdd.get(i) + " " + list_bdd.get(i + 1));
                i++;
            }
            else {
                combo.addItem( list_bdd.get(i));
            }


        }


    }


/////****************** verifier si un item existe dans un combobox**************/
    private  boolean exist(JComboBox<String> jcom,String item){
        boolean exists = false;
        if(jcom.getSelectedIndex()==0){exists = true;}
        else{

            for (int index = 0; index < jcom.getItemCount() && !exists; index++) {

                String part_profi = jcom.getItemAt(index);

                if (item.equals( part_profi)){
                    exists = true;
                }
            }}
        return exists;
    }


/////****************** ajouter un  item  dans un combobox**************/

    protected  void addTocomboAction(JComboBox combo){
        boolean ex=exist(combo,combo.getEditor().getItem().toString());
        if((!ex && !combo.getEditor().getItem().toString().trim().equals("")))
        {
            combo.addItem(combo.getEditor().getItem().toString());
        }
    }


    ///**************************** Style de composant methodes*****************************************/


    //*********************** style de composant generale  **********************/

    public  void StyleTable(JFrame frame, Tableau tab) {
        tab.setStyle(2);
        styles("Nimbus");
        SwingUtilities.updateComponentTreeUI(frame);
        Font police_fi = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13);
        tab.table.setFont(police_fi);
        tab.table.setRowHeight(20);

    }


    public   void labelStyleTable(JLabel count){
        count.setForeground(Color.white);
        count.setBackground(new Color(6,119,144));
        count.setOpaque(true);
        count.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        count.setFont(police2);
    }


    public  void styleButton(JButton buton, JPanel panel){
        buton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        buton.setPreferredSize(new Dimension(150, 33));
        panel.add(buton);
    }

    protected   void styleButtonTab(JButton buton, JPanel panel){
        buton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        buton.setPreferredSize(new Dimension(190, 33));
        panel.add(buton);
    }



    public  void styleLabel(JLabel lab){
        lab.setFont(police2);
        lab.setForeground(Color.white);
    }


    public  void styleComponent(JComponent comp ){
        comp.setFont(police_fi);
        comp.setPreferredSize(new Dimension(250, 30));
    }

  protected  void StyleAreaText(JTextArea commentaire,JPanel pan){
      commentaire.setFont(new Font("Serif", Font.ITALIC, 13));
      commentaire.setLineWrap(true);       // wrap line
      commentaire.setWrapStyleWord(true);  // wrap line at word boundary
      commentaire.setBackground(new Color(204, 238, 241)); // light blue
      JScrollPane tAreaScrollPane = new JScrollPane(commentaire);
      tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      tAreaScrollPane.setPreferredSize(new Dimension(230, 130));
      pan.add(tAreaScrollPane, BorderLayout.CENTER);

  }

    public static void styles(String style) {
        try {

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (style.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());

                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






////******************************* ***************** traitement de tableau ***********************************************************/

public  void remplirTab(ArrayList liste, int col, Tableau tab, int debut_col) {
        removeTabRow(tab);
    int row = liste.size();
    if(debut_col==0)
        row=row  / col;
    else{
        row=row  / (col-1);
    }

    System.out.println(row);
    int part = 0;
    int f = 0;
    for (int r = 0; r < row; r++) {
        tab.ajouter();

        if(debut_col==1){
            tab.getTable().setValueAt(r+1, r,0);
        }


        for (int c = debut_col; c < col; c++) {

            tab.getTable().setValueAt(liste.get(f), r, c);

            if (f < liste.size() - 1) {
                f++;
            }

        }
        part = part + col;

    }
}



///****************** remove table row  *************************/
    protected  void removeTabRow(Tableau tab){

        int rows = tab.table.getRowCount();
        for(int i = rows - 1; i >=0; i--)
        {
            ((DefaultTableModel) tab.table.getModel()).removeRow(i);

        }

    }


    protected  void removeOneRowTab(JButton supp, Tableau tab){
        supp.addActionListener(
                e -> {
                    if(tab.table.getSelectedRow()==-1){
                        JOptionPane.showMessageDialog(null,
                                "Vous devez d'abord sélectionner la ligne  que vous voulez supprimer", "",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        ((DefaultTableModel) tab.table.getModel()).removeRow(tab.table.getSelectedRow());

                        for(int i=0;i<tab.table.getRowCount();i++){
                            tab.table.setValueAt(i+1,i,0);
                        }
                    }

                });
    }

///****************** add row in table****************************/
    protected  void addRowVide(int debut, int rows, Tableau tab){
        if(debut==0)
        removeTabRow(tab);
        for (int i = debut; i < rows; i = i + 1) {
            tab.allowEdition2=false;
            tab.ajouter();
            tab.table.setValueAt(i+1 , i , 0);

            for (int c = 1; c < tab.table.getColumnCount(); c++) {
            tab.table.setValueAt("", i , c);
        }
    }
    }


    protected  void addOneRow(Tableau tab){
        int k=tab.table.getRowCount();
        for(int i=0;i<tab.table.getColumnCount();i++){
            tab.table.setValueAt("", k-1,i);

        }

    }

/////////********actionTable***********/
    protected  void ActionTable(Tableau tab){
        tab.table.getColumnModel().getColumn(0).setPreferredWidth(3);
        Tableau.sorter.setSortable(0, false);
        addRowVide(0, 10,  tab);
        final KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        final KeyStroke tabu = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        tab.table.getSelectionModel().addListSelectionListener(event -> {
            int colomn1 =tab.table.getColumnCount()-1;
            tab.table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, tabu);
            tab.table.getActionMap().put(tabu, new generale.EnterAction());
            tab.table.setColumnSelectionInterval(colomn1, 1);
        });

    }


   /////******************* check if un champ dans le tableau et vide****************//
    protected  boolean vaidCheck(Tableau tab)
    {
        if(tab.table.getCellEditor()!=null){
            tab.table.getCellEditor().stopCellEditing();
        }
        for(int l=0;l< tab.table.getRowCount();l++)
        {
            for (int k=0;k<tab.table.getColumnCount();k++)
            {
                String om=tab.table.getValueAt(l,k).toString();
                if(om.trim().length()==0)
                {
                    return false;
                }}}
        return true;
    }





    //*********************** pour afficher le menu popup dans les tableau
    private  int showPopup(MouseEvent e, Tableau tab, JPopupMenu popupMenu) {
        int selectedRow=0;
        if (e.isPopupTrigger()) {
            Point p = new Point(e.getX(), e.getY());
            tab.table.columnAtPoint(p);
            selectedRow = tab.table.getSelectedRowCount();
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
        return selectedRow;
    }




/////*****************************ajouter l'entete de recherche dans la fenetre liste des tableau****************/

    protected   void entetTabComponent(JPanel panEntete, JPanel pangener, JPanel panlab, JPanel panComp, JComponent comp, JLabel lab){
        panEntete.add(pangener);
        pangener.add(panlab);
        pangener.add(panComp);
        pangener.setLayout(new BoxLayout(pangener,BoxLayout.Y_AXIS));

        panlab.add(lab);
        panComp.add(comp);

        comp.setPreferredSize(new Dimension(180,30));

        panlab.setLayout(new FlowLayout(  FlowLayout.LEFT));
        panComp.setLayout(new FlowLayout(  FlowLayout.LEFT));
        panlab.setBorder(BorderFactory.createEmptyBorder(0, 0, -5, 0));
    }

    ///**************************** selection des row pour l'imression ou l'exportation excel *****************************************/
    protected  void build(Tableau tab, JPopupMenu popupMenu) {
        tab.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3){
                    selectedRow = showPopup(e,tab,popupMenu);
                }
            }
        });

    }

    //////********************** recuperep le sid de la colonne a partir de nom de colonne**************/
    protected  int getColumnIndexFromName(Tableau tab, String columnToRemove) {
        for (int columnIndex = 0; columnIndex < tab.table.getColumnCount(); columnIndex++) {
            if (tab.table.getColumnName(columnIndex).equals(columnToRemove)) {
                return columnIndex;
            }

        }
        return -1;

    }




    ///************************* actrion enter sur les coloonne tableau*************************/
    public  class EnterAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            Robot rob;
            try {
                rob = new Robot();
                rob.keyPress(KeyEvent.VK_TAB);
                rob.keyRelease(KeyEvent.VK_TAB);
            } catch (AWTException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }



///////*********** action JText sur les champ de tablea*********************/

    protected void actionKeyJtextFieldSorter(JTextField filterText, Tableau tab, JLabel count) {
        filterText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = filterText.getText();
                if (text.length() == 0) {
                    Tableau.sorter.setRowFilter(null);
                } else {
                    Tableau.sorter.setRowFilter(RowFilter.regexFilter("(?i)" +text));
                    tab.table.clearSelection();
                    System.out.println(Tableau.sorter.getViewRowCount());
                }
                count.setText("Nombre Total : " + Tableau.sorter.getViewRowCount());

            }

            public void keyPressed(KeyEvent e) {

            }
        });

    }
    protected void actionMouseJtextField(JTextField filterText){

        filterText.addMouseListener(
                new  MouseAdapter(){
                    public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub
                        filterText.setText("");
                        filterText.setForeground(Color.gray);
                    }
                });

        filterText.addFocusListener(
                new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent e) {
                        filterText.setForeground(Color.black);

                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        filterText.setText("Recherche");
                        filterText.setForeground(Color.gray);
                    }

                });
    }





/////*************************************methode general*************************/

/////************************************* methode pour ajouter une image au panel global*************************/

    public static JPanel generalPanle(){
        JPanel panel= new JPanel(){
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g){
                ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("menu.png")));
                img.paintIcon(this, g,0, 0);

            }  };
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setOpaque(false);
        return panel;
    }



/////************************************* methode pour declarer les button et leur stylel*************************/
    public  void declarationButton( JPanel panel){
        ajouter = new JButton ("Ajouter");
        modifier = new JButton ("Modifier");
        Supprimer = new JButton("Supprimer");
        retour = new JButton ("Retour");
        valid_ajou= new  JButton("Valider");
        valid_modif=new  JButton("Valider");
        valid_supp=new  JButton("Valider");
        styleButton(retour,  panel);
        styleButton(ajouter,  panel);
        styleButton(modifier,  panel);
        styleButton(Supprimer,  panel);
        styleButton(valid_ajou,  panel);
        styleButton(valid_modif,  panel);
        styleButton(valid_supp,  panel);

    }

    /////************************************* visibilite de button*************************/

    public  void visibiliteButton(boolean ajou, boolean modif,
                                        boolean supp, boolean ret,
                                        boolean valid_aj, boolean valid_mod,
                                        boolean valid_su) {
        ajouter.setVisible(ajou);
        modifier.setVisible(modif);
        Supprimer.setVisible(supp);
        retour.setVisible(ret);
        valid_ajou.setVisible(valid_aj);
        valid_modif.setVisible(valid_mod);
        valid_supp.setVisible(valid_su);
    }

    /////************************************* titre de panel et bordure blanc*************************/

    public  void titleFrame(String title, JPanel pan) {
        LineBorder border = new LineBorder(Color.white, 1, true);
        TitledBorder titl2 = new TitledBorder(border, title, TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, police2, Color.white);
        pan.setBorder(titl2);
    }


    /////************************************* style generale de des fenetre *************************/

    public  void initFrame(String title, JPanel pan, JFrame frame, String log, String titleFrame){
        titleFrame(title, pan);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage(generale.class.getClassLoader().getResource("icon.png"));
        frame.setIconImage(img);
        windows(frame,log);
        frame.setTitle(titleFrame );
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        styles("Nimbus");

    }


    /////************************************* ouverture fermiture de JFRAme *************************/

    private   void windows(final JFrame frame, final String log){
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                //call the object of NewWindow and set visible true
                int reponse = JOptionPane.showConfirmDialog(
                        null, "Voulez-vous vraiment quitter cette fenétre ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (reponse==JOptionPane.YES_OPTION){
                    // System.out.println(prv.size()-1+"avant"+prv.get(prv.size()-1)+"    "+prv.toString());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.dispose();
                    menu fr=new menu(log);
                    fr.setVisible(true);

                }
                else if (reponse==JOptionPane.NO_OPTION){
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	  }
                else{frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);}
            }
        });
    }


   ////********************  la bare de progresse dans l'ors de la generation des etiquette *********************/

    protected   void StylePopup(int af_cont, int conteur ){
        titr.setText("la création de "+conteur+" etiquette");
        titr.setFont( titr.getFont().deriveFont(Font.BOLD|Font.ITALIC) );

        UIManager.put("nimbusOrange", (new Color(70,130,180)));

        progressBar= new JProgressBar(af_cont,af_cont+conteur-1);
        progressBar.setValue(0);
        frame.add(controlPanel);
        titr.setFont(police2);
        progressBar.setStringPainted(true);
        controlPanel.add(titr);
        controlPanel.add(progressBar);

        progressBar.setPreferredSize( new Dimension (200, 20));
        frame.setSize(300, 70);
        frame.setLocationRelativeTo(null);
    }



 /////******************************************traitement des champ ********************************/

    ///// verifier si un  nombre
    protected   boolean isValid(String chaine) {
        try {
            Float.parseFloat(chaine);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    ///// format de date

    public static String  date_traiter(Date dates) {
        final Calendar cal = Calendar.getInstance();  // date du jour
        cal.setTime(dates);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(cal.getTime());

    }







    /////*************************** ***************** imression etquette   *************************************/


    private void creeDossier(String dossier){
        if(!new File(dossier).exists())
        {
            new File(dossier).mkdirs();
        }
    }


    private void killProcess(String process){
        try {
           Process p= Runtime.getRuntime().exec("taskkill /f /im "+process);
           p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }




    public  void Imprimer_pdf_Final(String parcour,String report, Map<String, Object> parameters) {

        creeDossier(parcour);

        String model = chemin_report + report;
        File fichier = new File(parcour);
        fichier.delete();
        killProcess("AcroRd32.exe");
        killProcess("Acrobat.exe");
        killProcess("FoxitReader.exe");
        killProcess("Foxit Reader.exe");

        try {

            JPanel controlPanel = new JPanel();
            JFrame frame = new JFrame();
            frame.add(controlPanel);
            frame.setUndecorated(true);

            JLabel titr = new JLabel("Veuillez patienter ....");
            titr.setFont(titr.getFont().deriveFont(Font.BOLD | Font.ITALIC));
            UIManager.put("nimbusOrange", (new Color(70, 130, 180)));

            titr.setFont(police2);
            controlPanel.add(titr);
            controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

            frame.setSize(300, 70);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            JasperDesign jasperDesign = JRXmlLoader.load(model);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


            //- Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, CConnect.getInstance());

            //- Cr?ation du rapport au format PDF

            JasperExportManager.exportReportToPdfFile(jasperPrint, parcour);
            controlPanel.remove(titr);
            frame.setVisible(false);
            int reponse = 0;
                       /* JOptionPane.showConfirmDialog(
                        null, "Operation terminée. Voulez-vous ouvrir le pdf?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);*/
            if (reponse == 0) {
                try {
                    Desktop.getDesktop().open(new File(parcour));

                } catch (IOException p) {
//TODO Auto-generated catch block
                    p.printStackTrace();

                }
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
    }


        ///////**************generation de l'excel lors de selecction tableu*************/
    protected void generateExcel(String nom_par, String titre, Tableau tab, Object[] entete, int selectedRow){
        if(selectedRow!=0){

            try {

                Date actuelle=new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String date_jour = dateFormat.format(actuelle);
                creeDossier(constant.excel);
                killProcess("EXCEL.exe");

                String parcour=constant.excel+nom_par+date_jour+".xlsx";
                File fichier = new File(parcour);
                fichier.delete();

                FileOutputStream fileOut = new FileOutputStream(parcour);
                XSSFWorkbook worksheet = new XSSFWorkbook();
                String safeName = WorkbookUtil.createSafeSheetName(titre);
                XSSFSheet sheet = worksheet.createSheet(safeName);

                XSSFRow row ;

                int[] selection = tab.table.getSelectedRows() ;
                int z =selection.length;
                for (int j=0;j<z; j++) {
                    row = sheet.createRow(j);

                    for(int i=0; i<entete.length;i++) {
                        XSSFCell cell = row.createCell(i);

                        if(j==0){
                            cell.setCellValue(entete[i].toString());
                            CellStyle cellStyle = worksheet.createCellStyle();
                            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
                            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                            cell.setCellStyle(cellStyle);
                        }
                        else
                            cell.setCellValue(tab.table.getValueAt(selection[j], i).toString());


                        sheet.autoSizeColumn(i);
                    }

                }

                worksheet.write(fileOut);
                fileOut.close();
                fileOut.flush();
                BufferedReader bfr=	new BufferedReader(new FileReader(parcour));
                bfr.close();
                try {
                    Desktop.getDesktop().open(new File( parcour));

                } catch (IOException ignored) {
                }

            }
            catch (IOException ignored) {


            }

        }

        else{

            JOptionPane.showMessageDialog(null, "Vous devez selectionner au moin une ligne");
        }
    }








    ///////////*******************************autre traitement************************/


    ///******************************** enlever les redandant dans une liste selectionner de tableau**********

    protected ArrayList removeDoublant(Tableau tab, int colom_param) {
        int[] selection = tab.table.getSelectedRows();
        int z = selection.length;
        for(int i = 0; i < z; i++) {
            if (!code_aimprimer.contains(tab.table.getValueAt(selection[i], colom_param)))
                code_aimprimer.add(tab.table.getValueAt(selection[i], colom_param));
        }
        return code_aimprimer;
    }



    protected void countCartonPalette(JComboBox palette_comb, JTextField qtepalette, String table){
        int qte=0;
        if(palette_comb.getItemCount()!=0)
            qte=Integer.parseInt(imp_general.select_count_palette(table,(String) palette_comb.getSelectedItem()))+1;
        qtepalette.setText(""+qte);


    }

}

















