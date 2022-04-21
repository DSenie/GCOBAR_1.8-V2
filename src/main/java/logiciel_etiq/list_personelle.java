package logiciel_etiq;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class list_personelle extends JFrame {
	/**
	 * 
	 */
	ArrayList <String>list_perso= new ArrayList<String>() ;
	private static final long serialVersionUID = 1L;
	//public static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    public Object [] entete={"Matricule","Nom/Prenom","Telephone","Chaine","Zone","Poste","Type"};
    public final Tableau tab=new Tableau(entete);
    public final JTextField filterText = new JTextField("Recherche");
	
    public final JPanel paneltext =new JPanel();
    public final JPanel pan=new JPanel();
    public final JPanel pan1=new JPanel();
    public final JPanel pan2=new JPanel();
    public final JPanel pan3=new JPanel();
    public gestion_personelle perso=new gestion_personelle();
    public final JLabel rech = new JLabel("Recherche");
    private String co;
    private JLabel count ;
    public JScrollPane p=new JScrollPane(tab);
    public int i=1;
    public int j=1;
    public int l = 0; 
	
    list_personelle(final String log){
	 Toolkit kit = Toolkit.getDefaultToolkit();
	  Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	  setIconImage(img);
	  selectioncomb.windows(this,log);
	 
			
		tab.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		      @SuppressWarnings("deprecation")
			public void valueChanged(ListSelectionEvent event) {
		    	  
			int a=tab.supprimer_modif_personelle(log);
		     if(a==0){
			 hide();
		      }
		     }  });  
		composant();
	}	
	
	public void composant(){
		 count= new JLabel(co);
		tab.table.getTableHeader().setBackground(Color.black);
		tab.setStyle(2);
		if(!selectioncomb.prv.contains("list_personelle")){
			//	System.out.println("eeeeee");
			 selectioncomb.prv.add("list_personelle");}
		// selectioncomb.prv.add("list_personelle");
		 JPanel panel =new JPanel() {   
		  		/****/
			private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g){
		  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
		             img.paintIcon(this, g,0, 0);
		             }  } ;
		  	  	
		 filterText.setForeground(Color.gray);	  	  	
		  filterText.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		    	  String text = filterText.getText();
                  if (text.length() == 0) {
                  tab.sorter.setRowFilter(null);
                  } else {
                   tab.sorter.setRowFilter(RowFilter.regexFilter(text.toLowerCase())); 
                   tab.table.clearSelection();
                   co = "Nombre de personelles : "+tab.sorter.getViewRowCount();
	           		// System.out.println(tab.sorter.getViewRowCount());
	           		 count.setText(co);
                  }
		      }

		      public void keyPressed(KeyEvent e) {
		    	
		      } });

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
			    public void focusGained(FocusEvent e) { filterText.setForeground(Color.black); 

			    }

			    @Override
			    public void focusLost(FocusEvent e) {
					 filterText.setText("Recherche");
					 filterText.setForeground(Color.gray); 

			        // You could do something here when the field loses focus, if you like
			    }

			});

		  filterText.addActionListener(
		             new ActionListener() { 
		                 public void actionPerformed(ActionEvent e) {
		                	
		                	 
		                 }});
		
		  list_perso= perso.select();
		    for(i=0;i<list_perso.size();i=i+11){tab.ajouter();}
			int j=1;
			int l = 0; i=1;
			while(l<list_perso.size()){

			 while(l<j*11){
				 
				 tab.getTable().setValueAt(list_perso.get(l+2).toLowerCase(), j-1, 0);
				 tab.getTable().setValueAt(list_perso.get(l+3).toLowerCase()+" "+list_perso.get(l+4).toLowerCase(), j-1, 1);
				 tab.getTable().setValueAt(list_perso.get(l+5), j-1, 2);				 
				 
				 tab.getTable().setValueAt(list_perso.get(l+6).toLowerCase()+" "+list_perso.get(l+10).toLowerCase(), j-1, 3);
				 
				 tab.getTable().setValueAt(list_perso.get(l+8).toLowerCase()+" "+list_perso.get(l+9).toLowerCase(), j-1, 4);
				 tab.getTable().setValueAt(list_perso.get(l+7).toLowerCase(), j-1, 6);

				 tab.getTable().setValueAt(list_perso.get(l).toLowerCase()+" "+list_perso.get(l+1).toLowerCase(), j-1, 5);
				 i=i+1; l=l+11;
			 }
			 i=1;
			j=j+1;		
			}




		generale.styles("Nimbus");
		SwingUtilities.updateComponentTreeUI(this);
    
    co = "Nombre de personelles : "+tab.table.getRowCount();
	// System.out.println(tab.sorter.getViewRowCount());
	 count.setText(co);
	 
    Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);

    Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
   tab.table.setFont(police_fi);
	
    count.setForeground(Color.white); 
	    count.setBackground(new Color(6,119,144));
	    count.setOpaque(true);
	    count.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
   count.setFont(police2);
    panel.add(paneltext);
	panel.add(p); 
	panel.add(pan);
	
	paneltext.add(pan2);
	paneltext.add(pan3);

	pan2.add(filterText);
	
	pan.add(count);
	pan.add(pan1);

    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
    pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
    paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));

    filterText.setPreferredSize(new Dimension(50,30));
    tab.setPreferredSize(new Dimension(40, 400));
    paneltext.setPreferredSize(new Dimension(40, 90));

    panel.setOpaque(false);
    pan.setOpaque(false);
    paneltext.setOpaque(false);
    pan1.setOpaque(false);
    pan2.setOpaque(false);
    pan3.setOpaque(false); 
    
    pan2.setLayout(null);
    filterText.setBounds(00, 30, 150,30);
    
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    pan1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	     
		  setTitle("Liste des personelle" );
          setSize(1000, 600);
          setLocationRelativeTo(null);          
          setVisible(true);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setContentPane(panel);
	      tab.table.setRowHeight(20);
	
	}
	
	
	    		 
	    		 
	 public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
					 new list_user("4");
						//frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}


