package logiciel_etiq;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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

public class list_poste extends JFrame{
	/**
	 * 
	 */
	ArrayList <String>list_poste= new ArrayList<String>() ;
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	//private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private  Object [] entete={"Code Poste","Intitule Poste"};
	private  final Tableau tab=new Tableau(entete);
	private	    final JTextField filterText = new JTextField("Recherche");
		
	private   final JPanel paneltext =new JPanel();
	private	final JPanel pan=new JPanel();
	private	final JPanel pan1=new JPanel();
	private	final JPanel pan2=new JPanel();
	private	final JPanel pan3=new JPanel();
	private   JLabel count;    
	private  JScrollPane p=new JScrollPane(tab);
	private   gestion_poste pos=new gestion_poste();
	private int i=1;
	

list_poste(final String log){ 
	Toolkit kit = Toolkit.getDefaultToolkit();
    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
   setIconImage(img);
   selectioncomb.windows(this,log);

composant();}
public void composant(){
	 selectioncomb.prv.add("liste_poste");
	tab.allowEdition1=false;
	tab.allowEdition2=false;

	
	tab.table.getTableHeader().setBackground(Color.black);

     tab.setStyle(2);

	  panel= new JPanel(){   
  		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
             img.paintIcon(this, g,0, 0);

  	}  };
  	
  	
  	
  	
  	
	 filterText.setForeground(Color.gray); 

		
	 tab.table.getTableHeader().setBackground(Color.black);

	 tab.setStyle(2);
	

 	  	
	  filterText.addKeyListener(new KeyAdapter() {
	      public void keyReleased(KeyEvent e) {
	    	  String text = filterText.getText();
             if (text.length() == 0) {
             tab.sorter.setRowFilter(null);
             } else {

              tab.sorter.setRowFilter(RowFilter.regexFilter(text.toLowerCase()));
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

	  
	
	  list_poste=   pos.select();
	    for(i=0;i<list_poste.size();i=i+2){tab.ajouter();}
		int j=1;
		int l = 0; i=1;
		while(l<list_poste.size()){
		
		 while(l<j*2){
			 tab.getTable().setValueAt(list_poste.get(l).toLowerCase(), j-1, i-1);
			 i=i+1; l=l+1;
		 }
		 i=1;
		j=j+1;		
		}











	generale.styles("Nimbus");
	SwingUtilities.updateComponentTreeUI(this);
     
     Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);

     Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
    tab.table.setFont(police_fi);

	    count = new JLabel("Le nombre des postes :  "+tab.table.getRowCount());
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

    setTitle("Consultation Poste" );
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
				
		
				 new list_poste("7");
				//frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}












