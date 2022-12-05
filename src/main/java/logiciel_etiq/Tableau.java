package logiciel_etiq;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;


public class Tableau extends JPanel implements ICustomTable, ConstantesStyles, SwingConstants {

	/**
	 * L'uid
	 */
	private static final long serialVersionUID = -1572744239267031822L;

	/**
	 * La JTable affich?e
	 */
	JTable table;
	static TableRowSorter<TableModel> sorter;
	static RowFilter<Object, Object> filter;
	String text;
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
	boolean allowEdition2 = true;
	boolean allowEdition3 = true;
	boolean allowEdition4 = true;
	boolean allowEdition5 = true;
	boolean allowEdition6 = true;
	boolean allowEdition7 = true;
	boolean allowEdition8 = true;
	boolean allowEdition9 = true;


	
	public static void remplir_table(ArrayList<String> list,String select, ArrayList<String> list_bdd,Java2sAutoComboBox comb ){
		
	     // list.clear();
		  list.add(select);
		 // list_bdd=art.select_article_codenomo(fabr,famille);
		    for(int i=0;i<list_bdd.size();i++)
		     {
		          //Pour affecter une valeur de base de donn?es ? un Combobox 
		    	list.add(list_bdd.get(i)+" "+list_bdd.get(i+1));
			   i++;
		     }
		   comb = new Java2sAutoComboBox(list);

	}
	
	public Tableau(final Object[] entetes){//, final boolean editable
		setLayout(new GridLayout(1, 0));
		
		Object[][] data = new Object[0][entetes.length];
		//DefaultTableModel model = new DefaultTableModel(data, entetes)
		  DefaultTableModel model = new DefaultTableModel(data, entetes){
			private static final long serialVersionUID = 8142475658077955728L;
			/*public boolean isCellEditable(int rowIndex, int columnIndex){
				return editable;
			}*/
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
		//ModeleDynamiqueObjet model = new ModeleDynamiqueObjet();
		table=new JTable(model);
		sorter = new TableRowSorter<TableModel>(model);
		
//	   filter = new RowFilter<Object, Object>() {
//		      public boolean include(Entry entry) {
//		        return ((String) entry.getValue(0)).startsWith(text);
//		      }
//		    };
//		    filter1 = new RowFilter<Object, Object>() {
//			      public boolean include(Entry entry) {
//			   
//			        return ((String) entry.getValue(3)).startsWith(text);
//			      }
//			    };
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
		//System.out.println("lllllllllllllllllllll"+table.getColumnCount());
		((DefaultTableModel)(table.getModel())).addRow(new String[table.getColumnCount()]);
		 comb.setEditable(true);		
        //JTextComponent editor = (JTextComponent) comb.getEditor().getEditorComponent();
        
        // change the editor's document
//        new AutoCompleteComboBox(comb);
//        editor.setDocument(new AutoCompleteComboBox(comb));
        
        
	}
	
	public void ajouter(){
		if(table.getRowCount()!=0)
			table.getSelectionModel().clearSelection();
		//System.out.println("lllllllllllllllllllll"+table.getColumnCount());
		((DefaultTableModel)(table.getModel())).addRow(new String[table.getColumnCount()]);
	}
	public void allowEditionAction(int index ,boolean state) {
	     allowEdition = state;
	  }

	/**
	 * Procedure qui supprime la ligne selectionnee
	 */
	public int supprimer_modif_user(final String log){
		int a=-1;
		if(table.getSelectedRow()!=-1){
			 String[] buttons = { "Modifier", "Supprimer" };
			
        	  int reponse = JOptionPane.showOptionDialog(null, "voulez vous supprimer ou modifier cette ligne?", "Confirmation",
        			  JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
        	  
if (reponse==1){
			String g=table.getValueAt(table.getSelectedRow(), 0).toString();
		    gestion_user use=new gestion_user();
if(use.setdelete(g,table.getValueAt(table.getSelectedRow(), 1).toString(),table.getValueAt(table.getSelectedRow(), 2).toString())==true){
				((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());}
					}

if (reponse==0){
		String mat=table.getValueAt(table.getSelectedRow(), 0).toString();
		String nom=table.getValueAt(table.getSelectedRow(), 1).toString();
		String prenom=table.getValueAt(table.getSelectedRow(), 2).toString();
		String login=table.getValueAt(table.getSelectedRow(), 3).toString();
		//String pass=table.getValueAt(table.getSelectedRow(), 4).toString();
		String prio=table.getValueAt(table.getSelectedRow(), 4).toString();
	   String role=table.getValueAt(table.getSelectedRow(), 5).toString();

	gestion_user use=new gestion_user();
      connection_user con=new connection_user(log);
      LineBorder border = new LineBorder ( Color.white, 1, true );
	  TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
       TitledBorder.DEFAULT_POSITION, con.police2, Color.white);
	

      con.paneluser.setBorder(titl2);
      con.mat1.setText(mat);
	  use.selection(mat);

      con.nom1.setText(nom);
      con.prenom1.setText(prenom);
      con.login1.setText(login);
      con.pass1.setText(use.pass);


      System.out.println("eeeee "+prio+" "+con.role);


      if(!prio.equals("utilisateur")){
     	 con.respo.setSelected(true);
		 }
		 else{ con.utili.setSelected(true);}
		
		 if(role.contains("imp")){
			con.imp.setSelected(true);
		 }
		 else{ con.prf.setSelected(true);}

		con.mat1.setEnabled(false); 
		con.nom1.setEnabled(true); 
		con.prenom1.setEnabled(true); 
		con.login1.setEnabled(true); 
		con.pass1.setEnabled(true); 
		 con.retour.setVisible(true); 
		 con.valid_modif.setVisible(true); 
		 con.valid_supp.setVisible(false); 
		con.valid_ajou.setVisible(false); 
		con.modifier.setVisible(false); 
		con.ajouter.setVisible(false); 
		con.Supprimer.setVisible(false); 

		con.retour.setVisible(true); 
                   }
		}return a;
	}
	

	
	
	@SuppressWarnings("deprecation")
	public int supprimer_modif_personelle(final String log){
		int a=-1;
		if(table.getSelectedRow()!=-1){
			 String[] buttons = { "Modifier", "Supprimer" };
			
        	  
        	   
        	  int reponse = JOptionPane.showOptionDialog(null, "Voulez vous supprimer ou modifier cette personne?", "Confirmation",
        			  JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
        	  
if (reponse==1){
	a=1;
			String g=table.getValueAt(table.getSelectedRow(), 0).toString();
		       gestion_personelle perso=new gestion_personelle();
		       
if(perso.setdelete(g,table.getValueAt(table.getSelectedRow(), 1).toString())==true){
				((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());}
		}
else if (reponse==0){
	a=0;
	 
		String mat=table.getValueAt(table.getSelectedRow(), 0).toString();
		String nom_prenom=table.getValueAt(table.getSelectedRow(), 1).toString();
		String tel=table.getValueAt(table.getSelectedRow(), 2).toString();
		String chaine=table.getValueAt(table.getSelectedRow(), 3).toString();
		String zone=table.getValueAt(table.getSelectedRow(), 4).toString();
		String poste=table.getValueAt(table.getSelectedRow(), 5).toString();

      gestion_personelle perso=new gestion_personelle();
      personelle pers1=new personelle(log);
      LineBorder border = new LineBorder ( Color.white, 1, true );
	  TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
        TitledBorder.DEFAULT_POSITION, pers1.police2, Color.white);
      pers1.panelpers.setBorder(titl2);
      
	  String[] partz = nom_prenom.split(" ");
		  String nom = partz[0]; // 004
		  
		  String prenom = partz[1]; 
      pers1.mat1.setText(mat);
	  perso.selection(pers1.mat1.getText());

      pers1.nom1.setText(nom);
      pers1.prenom1.setText(prenom);
      pers1.num_tel1.setText(tel);

  	   pers1.zone.setSelectedItem(zone);
  	  pers1.poste.setSelectedItem(poste);
  	 pers1.num_chaine.setSelectedItem(chaine);    
  	 
  	 if(pers1.type.equals("Permanant")){
  		pers1. perm.setSelected(true);
		 }
		 else{ pers1.perm.setSelected(false);}
		
		 if(pers1.type.equals("Volant")){
			 pers1.vol.setSelected(true);
		 }
		 else{ pers1.vol.setSelected(false);}
		 pers1.modif.setVisible(false); 
		// System.out.println(perso.zone);
		 pers1.zone.setSelectedItem(perso.zone+" "+perso.des);
		 pers1.poste.setSelectedItem(perso.poste+" "+perso.intitule);
		 pers1.num_chaine.setSelectedItem(perso.N_chaine+" "+perso.des_chaine);
		 pers1.valid_modif.setVisible(true); 
		 pers1.valid_ajou.setVisible(false); 
		 pers1.valid_supp.setVisible(false); 
		 pers1.supp.setVisible(false); 
		 pers1.ajout.setVisible(false); 
		 pers1.retour.setVisible(true); 

		 pers1.mat1.setEnabled(false); 
		 pers1.nom1.enable();
		 pers1.prenom1.enable();
		 pers1.num_tel1.enable();
		 pers1.zone.enable();
		 pers1.poste.enable();
		 pers1.num_chaine.enable();


                   }
    else{
	table.clearSelection();

     }
		}
		
		
			
		return a;
	}
	

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
	
	@Override
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
		 * @param data
		 * @param title
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
