package logiciel_etiq.View.tableau;

import logiciel_etiq.View.generale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.*;


public class Tableau extends JPanel implements ICustomTable,ConstantesStyles, SwingConstants {

    /**
     * L'uid
     */
    private static final long serialVersionUID = -1572744239267031822L;

    /**
     * La JTable affich?e
     */
    public JTable table;
    public static TableRowSorter<TableModel> sorter;

    /**
     * Le renderer
     */
    private RendererDecorator renderer;

    /**
     * Le style courant
     */

    private int style;
    boolean allowEdition = false;
    boolean allowEdition1 = true;
    public boolean allowEdition2 = true;
    boolean allowEdition3 = true;
    boolean allowEdition4 = true;
    boolean allowEdition5 = true;
    boolean allowEdition6 = true;
    boolean allowEdition7 = true;
    boolean allowEdition8 = true;
    boolean allowEdition9 = true;



    public Tableau(final Object[] entetes){//, final boolean editable
        setLayout(new GridLayout(1, 0));

        Object[][] data = new Object[0][entetes.length];
        DefaultTableModel model = new DefaultTableModel(data, entetes){
            private static final long serialVersionUID = 8142475658077955728L;
            public boolean isCellEditable(int rowIndex, int columnIndex){
                switch(columnIndex){
                    case 0:
                        if(allowEdition2 == true) return true;
                        else return false;

                    case 1:
                        if(allowEdition1 == true) return true;
                        else return false;
                    case 2:
                        if(allowEdition3== true) return true;
                        else return false;
                    case 3:
                        if(allowEdition4== true) return true;
                        else return false;
                    case 4:
                        if(allowEdition5== true) return true;
                        else return false;
                    case 5:
                        if(allowEdition6== true) return true;
                        else return false;
                    case 6:
                        if(allowEdition7== true) return true;
                        else return false;
                    case 7:
                        if(allowEdition8== true) return true;
                        else return false;
                    case 8:
                        if(allowEdition9== true) return true;
                        else return false;
                    case 9:
                        return true;
                    case 10:
                        return true;
                    case 11:
                        return true;
                    case 12:
                        return true;
                    case 13:
                        return true;
                    case 14:
                        return true;
                    case 15:
                        return true;
                }
                return false;
            }

        };
        table=new JTable(model);
        sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);

        renderer = new RendererDecorator();
        table.setDefaultRenderer(Object.class, renderer);
        table.setOpaque(false);
        setLayout(new BorderLayout());
        add(table.getTableHeader(), BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);

    }


    public void Removecol(JTable table, int col_index){
        TableColumn tcol = table.getColumnModel().getColumn(col_index);
        table.removeColumn(tcol);
    }
    public void Addcol(JTable table, int col_index){
        TableColumn tcol = table.getColumnModel().getColumn(col_index);
        table.addColumn(tcol);
    }

    public Tableau(final Object[] entetes, int style){// final boolean editable,
        this(entetes);//, editable
        setStyle(style);
    }

    /**
     * Procedure qui permet d'ajouter une ligne vide au tableau
     */

    //  Determine editor to be used by row
    public TableCellEditor getCellEditor(int row, int column, List<TableCellEditor> editors )
    {
        int modelColumn = table.convertColumnIndexToModel( column );

        if (modelColumn == 2)
            return editors.get(row);
//             else
//                 return super.getCellEditor(row, column);
        return null;
    }


    public void ajouter(JComboBox comb){
        List<TableCellEditor> edit = new ArrayList<TableCellEditor>();
        DefaultCellEditor dce1 = new DefaultCellEditor( comb );
        edit.add( dce1 );
        if(table.getRowCount()!=0)
            table.getSelectionModel().clearSelection();
        ((DefaultTableModel)(table.getModel())).addRow(new String[table.getColumnCount()]);
        comb.setEditable(true);
    }

    public void ajouter(){
        if(table.getRowCount()!=0)
            table.getSelectionModel().clearSelection();
        ((DefaultTableModel)(table.getModel())).addRow(new String[table.getColumnCount()]);
    }


    public void allowEditionAction(int index ,boolean state) {
        allowEdition = state;
    }

    /**
     * Procedure qui supprime la ligne selectionnee
     */







    /**
     * Procedure qui permet d'ajouter une ligne
     * @param donnees
     * 	la ligne ? ajouter
     */
    public void ajouterLigne(Object[] donnees){
        ((DefaultTableModel)(table.getModel())).addRow(donnees);
    }

    /**
     * Methode d'acces ? la table
     * @return la table contenue dans le panel
     */
    public JTable getTable() {
        return table;
    }

    public void setStyle(int style){
        this.style=style;
        switch(style){
            case STYLE_NORMAL :
                break;
            case STYLE_GRIS :
                setCouleurHeader(new Color(50, 50, 50));
                setForegroundHeader(Color.WHITE);
                setFontHeader(new Font("Calibri", Font.BOLD, 17));
                setAlternerLignes(true);
                setBackground1(new Color(130, 130, 130));
                setBackground2(Color.LIGHT_GRAY);
                setForeground2(Color.BLACK);
                setAlignement(CENTER);
                break;
            case STYLE_BLEU :
                setBackground(Color.white);

                //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                table.getTableHeader().getDefaultRenderer();
                setCouleurHeader(new Color(6,119,144));
                setForegroundHeader(new Color(220,237,252));
                JTableHeader header = table.getTableHeader();

                header.setPreferredSize(new Dimension(100,32));
                setFontHeader(new Font("Time New Roman", Font.BOLD|Font.ITALIC, 18));


                generale.styles("Metal");

                SwingUtilities.updateComponentTreeUI(this);



                break;
            case STYLE_ROUGE :
                setCouleurHeader(new Color(192,10,23));
                setForegroundHeader(Color.WHITE);
                setFontHeader(new Font("Calibri", Font.BOLD, 14));
                setAlternerLignes(true);
                setBackground2(new Color(255,179,184));
                setForeground2(Color.BLACK);
                setAlignement(CENTER);
                break;
        }
    }

    public int getStyle(){
        return style;
    }

    public Color getBackground2() {
        return renderer.getBackground2();
    }

    @Override
    public Color getBackgroundSelection() {
        return renderer.getBackgroundSelection();
    }

    @Override
    public Color getForeground2() {
        return renderer.getForeground2();
    }

    @Override
    public Color getForegroundSelection() {
        return renderer.getForegroundSelection();
    }

    @Override
    public void setBackground2(Color background2) {
        renderer.setBackground2(background2);
    }

    @Override
    public void setBackgroundSelection(Color couleur) {
        renderer.setBackgroundSelection(couleur);
    }

    @Override
    public void setForeground2(Color foreground2) {
        renderer.setForeground2(foreground2);
    }

    @Override
    public void setForegroundSelection(Color couleur) {
        renderer.setForegroundSelection(couleur);
    }

    @Override
    public Color getBackground1() {
        return renderer.getBackground1();
    }

    @Override
    public Border getBorderCellule() {
        return renderer.getBorderCellule();
    }

    @Override
    public Font getFontCellule() {
        return renderer.getFontCellule();
    }

    @Override
    public Color getForeground1() {
        return renderer.getForeground1();
    }

    public void setBackground1(Color couleur) {
        renderer.setBackground1(couleur);
    }

    @Override
    public void setBorderCellule(Border border) {
        renderer.setBorderCellule(border);
    }

    @Override
    public void setFontCellule(Font font) {
        renderer.setFontCellule(font);
    }

    @Override
    public void setForeground1(Color couleur) {
        renderer.setForeground1(couleur);
    }

    @Override
    public int getAlignement() {
        return renderer.getAlignement();
    }

    @Override
    public void setAlignement(int alignement) {
        renderer.setAlignement(alignement);
    }

    public Color getCouleurHeader() {
        return getTable().getTableHeader().getBackground();
    }

    public void setCouleurHeader(Color couleurHeader) {
        getTable().getTableHeader().setBackground(couleurHeader);
    }

    public Color getForegroundHeader() {
        return getTable().getTableHeader().getForeground();
    }

    public void setForegroundHeader(Color couleurHeader) {
        getTable().getTableHeader().setForeground(couleurHeader);
    }

    public void setFontHeader(Font font){
        getTable().getTableHeader().setFont(font);
    }

    public Font getFontHeader(Font font){
        return getTable().getTableHeader().getFont();
    }

    @Override
    public boolean isAlternerLignes() {
        return renderer.isAlternerLignes();
    }

    @Override
    public void setAlternerLignes(boolean alternerLignes) {
        renderer.setAlternerLignes(alternerLignes);
    }
    class ZModel extends AbstractTableModel{

        private static final long serialVersionUID = 1L;
        private Object[][] data;
        private String[] title;
        /**
         * Constructeur
        // * @param data
         //* @param title
         */
        public ZModel(Object[][] data, String[] title){
            this.data = data;
            this.title = title;
        }

        /**
         * Retourne le nombre de colonnes
         */
        public int getColumnCount() {
            return this.title.length;
        }

        /**
         * Retourne le nombre de lignes
         */
        public int getRowCount() {
            return this.data.length;
        }

        /**
         * Retourne la valeur ? l'emplacement sp?cifi?
         */
        public Object getValueAt(int row, int col) {
            return this.data[row][col];
        }








    }
}

