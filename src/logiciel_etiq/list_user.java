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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class list_user extends JFrame {
	
	/**
	 * 
	 */
	ArrayList <String>list_user= new ArrayList<String>() ;
	private static final long serialVersionUID = 1L;
	private Object [] entete={"Matricule","Nom","Prenom","Login","Priorite","Role"};
	private final Tableau tab=new Tableau(entete);
	private final JTextField filterText = new JTextField("Recherche");
	private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

	private final JPanel paneltext =new JPanel();
	private final JPanel pan=new JPanel();
	private final JPanel pan1=new JPanel();
	private final JPanel pan2=new JPanel();
	private final JPanel pan3=new JPanel();
	private String co;
    private JLabel count;
    private JScrollPane p=new JScrollPane(tab);
	private int i=1;
	
	
	list_user(final String log){
		
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	   setIconImage(img);
	   selectioncomb.windows(this,log,laf);

			
			tab.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			      @SuppressWarnings("deprecation")
				public void valueChanged(ListSelectionEvent event) {
			    	  
				 int a=tab.supprimer_modif_user(log);
			     if(a==0){
				 hide();
			      } }  });  
				
			composant();}
	
		 
		
	    		 
	   public void composant(){
		   selectioncomb.prv.add("list_user");
		   count= new JLabel(co);
		 
		   filterText.setForeground(Color.gray); 
		 
		   tab.setStyle(2);

			 JPanel panel =new JPanel() {   
		  		/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g){
		  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
		             img.paintIcon(this, g,0, 0);
		             }  } ;

		  	  	
			  filterText.addKeyListener(new KeyAdapter() {
			      public void keyReleased(KeyEvent e) {
			    	  String text = filterText.getText();
	                  if (text.length() == 0) {
	                  tab.sorter.setRowFilter(null);
	                  } else {
	                   tab.sorter.setRowFilter(RowFilter.regexFilter(text.toLowerCase()));
	                   tab.table.clearSelection();
	                   co = "Nombre des utilisateurs : "+tab.sorter.getViewRowCount();
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

			  
			
			  list_user=menu.use.select();
			    for(i=0;i<list_user.size();i=i+6){tab.ajouter();}
				int j=1;
				int l = 0; i=1;
				while(l<list_user.size()){
				
				 while(l<j*6){
					
					 tab.getTable().setValueAt(list_user.get(l).toLowerCase(), j-1, 0);
					 tab.getTable().setValueAt(list_user.get(l+1).toLowerCase(), j-1, 1);
					 tab.getTable().setValueAt(list_user.get(l+2).toLowerCase(), j-1, 2);
					 tab.getTable().setValueAt(list_user.get(l+3).toLowerCase(), j-1,3);

					 if(menu.use.select().get(l+4).equals("0")){
						 tab.getTable().setValueAt("Utilisateur".toLowerCase(), j-1, 4);
						}
						 else if(menu.use.select().get(l+4).equals("1")){
							 tab.getTable().setValueAt("Responsable".toLowerCase(), j-1, 4);} 
					 tab.getTable().setValueAt(list_user.get(l+5).toLowerCase(), j-1, 5);
           			 i=i+1; l=l+6;
				 }
				 i=1;
				j=j+1;		
				}
		

				  try{
				   		UIManager.setLookAndFeel(laf);
				   		SwingUtilities.updateComponentTreeUI(this);
				   	}
				     catch(Exception e1){
				   		e1.printStackTrace();
				   	}
				  
				  co = "Nombre des utilisateurs : "+tab.table.getRowCount();
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
		    p.setPreferredSize(new Dimension(700,700));

		    paneltext.setPreferredSize(new Dimension(40, 150));

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

			     
				  setTitle("List des utilisateurs" );
		          setSize(1000, 600);
		          setLocationRelativeTo(null);          
		          setVisible(true);
		          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			      setContentPane(panel);
			    
		   
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


