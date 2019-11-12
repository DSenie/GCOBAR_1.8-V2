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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class list_article extends JFrame{
	/**
	 * 
	 */
	ArrayList <String>list_poste= new ArrayList<String>() ;
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private  Object [] entete={"Code/designation  article","Fournisseur","Code Produit","Prix","Modèle","Quantite","Position"};
	private  final Tableau tab=new Tableau(entete);
	private jcombo article_comb;
	 int a;
	private   final JPanel paneltext =new JPanel();
	private	final JPanel pan=new JPanel();
	private	final JPanel pan1=new JPanel();
	private	final JPanel pan2=new JPanel();
	private	final JPanel pan3=new JPanel();
    private JPanel pan_codepro=new JPanel();
    
    private JPanel pan_codepro_jtext=new JPanel();

    private JTextField codeprod=new JTextField();
	private   JLabel count;    
	private  JScrollPane p=new JScrollPane(tab);
//	private   gestion_poste pos=new gestion_poste();
	//private int i=1;
	private ArrayList <String>list_article= new ArrayList<String>() ;

	  private List<String> list_a;
	  private static ArrayList<String> list_article_tr=new ArrayList<String>();
	  private static gestion_article art=new gestion_article();
	  
	  
	  
		private JRadioButton enie= new JRadioButton("ENIE",true);
		private JRadioButton autre = new JRadioButton("AUTRE",false); 
		private ButtonGroup bG = new ButtonGroup();
		  String fabr="";  
		  
		    private JPanel pan_article=new JPanel();
			private JPanel pan_article_comb=new JPanel();
			private JPanel pan_radio=new JPanel();
			private JPanel pan_radio_bg=new JPanel();
		
        list_article(final String log){ 
	Toolkit kit = Toolkit.getDefaultToolkit();
    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
   setIconImage(img);
   selectioncomb.windows(this,log,laf);

composant();}
public void composant(){
	
	   if(enie.isSelected()==true){fabr="enie";}
	   else {fabr="autre";}
	
	  list_a = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner un article-----"}));
	     article_comb = new jcombo(list_a.toArray());
	     list_article_tr=art.select_article_code(fabr);
	     
	     
	     for(int i=0;i<list_article_tr.size();i++)
		   {
		          //Pour affecter une valeur de base de données é un Combobox
			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
			   i++;
		   }
	      article_comb.setWide(true);
	      
	      
	      
	      
	      enie.addActionListener(
		           new ActionListener() { 
		           public void actionPerformed(ActionEvent e) {
		        	  // fabr="enie";
		        	   fabr="enie";
		        	 
		        	 remplir_art(fabr,article_comb);
		           }		           });
		 
		 autre.addActionListener(
		           new ActionListener() { 
		           public void actionPerformed(ActionEvent e) {
		        fabr="autre";
		        remplir_art(fabr,article_comb);
		        }});
	 selectioncomb.prv.add("liste_article");
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
  	
  	
  	
  	 article_comb.addActionListener(
	           new ActionListener() { 
	           public void actionPerformed(ActionEvent e) {
	          
	        	    
	           int rows = tab.table.getRowCount(); 

   		   	for(int i = rows - 1; i >=0; i--)
   		   	{
   		   		((DefaultTableModel) tab.table.getModel()).removeRow(i);

   		   	}
   		   	
   		   	if(article_comb.getItemCount()>1){
     		  String[] parts = article_comb.getSelectedItem().toString().split(" ");
     		  String part1 = parts[0]; // 004
     		 list_article=art.select_produit_article(part1);
     		  }
	        		
          		//	System.out.println("ee4ee4"+list_art_tab_prod);
            		   for(a=0;a<list_article.size();a=a+9){
            			tab.ajouter();
            		   }
              			int z=1;
              			int e1 = 0; a=1;
              			while(e1<list_article.size()){
              			
              			 while(e1<z*9){
              				tab.getTable().setValueAt(list_article.get(e1)+" "+list_article.get(e1+1), z-1,0);
              				tab.getTable().setValueAt(list_article.get(e1+3)+" "+list_article.get(e1+4),z-1,1);
              				tab.getTable().setValueAt(list_article.get(e1+2), z-1,2);
              				tab.getTable().setValueAt(list_article.get(e1+5), z-1,4);
              				tab.getTable().setValueAt(list_article.get(e1+6), z-1,6);
              				tab.getTable().setValueAt(list_article.get(e1+7), z-1,3);
              				tab.getTable().setValueAt(list_article.get(e1+8), z-1,5);
              				 a=a+1; e1=e1+9;
              			 }
          				
              			 a=1;
              			z=z+1;		
              			}
            		   
	       		
            		   
            		
	           }});
  	 
  	 
  	 
  	codeprod.addActionListener(
	           new ActionListener() { 
	           public void actionPerformed(ActionEvent e) {
	          
	        	    
	           int rows = tab.table.getRowCount(); 

		   	for(int i = rows - 1; i >=0; i--)
		   	{
		   		((DefaultTableModel) tab.table.getModel()).removeRow(i);

		   	}
  		 // 004
	        		list_article=art.select_art_produit(codeprod.getText());
       		//	System.out.println("ee4ee4"+list_art_tab_prod);
         		   for(a=0;a<list_article.size();a=a+9){
         			tab.ajouter();
         		   }
           			int z=1;
           			int e1 = 0; a=1;
           			while(e1<list_article.size()){
           			
           			 while(e1<z*9){
           				tab.getTable().setValueAt(list_article.get(e1)+" "+list_article.get(e1+1), z-1,0);
           				tab.getTable().setValueAt(list_article.get(e1+3)+" "+list_article.get(e1+4),z-1,1);
           				tab.getTable().setValueAt(list_article.get(e1+2), z-1,2);
           				tab.getTable().setValueAt(list_article.get(e1+5), z-1,4);
           				tab.getTable().setValueAt(list_article.get(e1+6), z-1,6);
           				tab.getTable().setValueAt(list_article.get(e1+7), z-1,3);
           				tab.getTable().setValueAt(list_article.get(e1+8), z-1,5);
           				 a=a+1; e1=e1+9;
           			 }
       				
           			 a=1;
           			z=z+1;		
           			}
         		   
	       		
         		   
         		
	           }});
  	
	

		
    

    codeprod.addMouseListener( 
			  new  MouseAdapter(){
				  public void mousePressed(MouseEvent e) {
					    // TODO Auto-generated method stub
					  codeprod.setText("");
					  codeprod.setForeground(Color.gray); 
					}     
			  });
	  
    codeprod.addFocusListener(
			  new FocusListener() {

		    @Override
		    public void focusGained(FocusEvent e) { codeprod.setForeground(Color.black); 

		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	codeprod.setText("Recherche par code produit");
		    	codeprod.setForeground(Color.gray); 

		        // You could do something here when the field loses focus, if you like
		    }

		});

	 tab.table.getTableHeader().setBackground(Color.black);

	 tab.setStyle(2);
	

 	  	
	
	  
	
//	  list_poste=   pos.select();
//	    for(i=0;i<list_poste.size();i=i+2){tab.ajouter();}
//		int j=1;
//		int l = 0; i=1;
//		while(l<list_poste.size()){
//		
//		 while(l<j*2){
//			 tab.getTable().setValueAt(list_poste.get(l).toLowerCase(), j-1, i-1);
//			 i=i+1; l=l+1;
//		 }
//		 i=1;
//		j=j+1;		
//		}



  	 
  	
  
   
  
   
   
  
     try{
   		UIManager.setLookAndFeel(laf);
   		SwingUtilities.updateComponentTreeUI(this);
   	}
     catch(Exception e1){
   		e1.printStackTrace();
   	}
     
     Font police_fi = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);

     Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,13);
    tab.table.setFont(police_fi);

	    count = new JLabel("Le nombre des produits :  "+tab.table.getRowCount());
	    count.setForeground(Color.white); 
	    count.setBackground(new Color(6,119,144));
	    count.setOpaque(true);
	    count.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    count.setFont(police2);

	    panel.add(paneltext);
		panel.add(p); 
		panel.add(pan);
		
		
		

		
		
		     bG.add(enie);
		     bG.add(autre);
		    
	         pan_radio.add(pan_radio_bg);
	         pan_radio_bg.add(enie);
		     pan_radio_bg.add(autre);
			 pan_article.add(pan_article_comb);
			 pan_article_comb.add(article_comb);
		     pan_codepro.add(pan_codepro_jtext);
		     pan_codepro_jtext.add(codeprod);
		     
			 
			 paneltext.add(pan_codepro);
			 paneltext.add(pan_article);

			 paneltext.add(pan_radio);
				paneltext.add(pan3);
				
				
		pan.add(count);
		pan.add(pan1);

	    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	    pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
	    paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));
	    

	    article_comb.setPreferredSize(new Dimension(200,30));
	    tab.setPreferredSize(new Dimension(40, 400));
	    paneltext.setPreferredSize(new Dimension(40, 90));
	    codeprod.setPreferredSize(new Dimension(200,30));
	   enie.setForeground(Color.white);
	    autre.setForeground(Color.white);
	    panel.setOpaque(false);
	    pan_article.setOpaque(false);
	    pan_radio.setOpaque(false);
	    pan.setOpaque(false);
        pan_radio_bg.setOpaque(false);
	    pan_article_comb.setOpaque(false);
	    paneltext.setOpaque(false);
	    pan1.setOpaque(false);
	    pan2.setOpaque(false);
	    pan3.setOpaque(false); 
	    pan_codepro.setOpaque(false);
	    pan_codepro_jtext.setOpaque(false);
	    
	    pan2.setLayout(null);
	    
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    pan1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    
	    pan_article.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 20));
	    pan_codepro.setBorder(BorderFactory.createEmptyBorder(10, -60, 20, 20));
	    pan_radio.setBorder(BorderFactory.createEmptyBorder(18, -60, 20, 20));


    setTitle("Consultation Produit" );
    setSize(1000, 600);
    setLocationRelativeTo(null);          
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(panel);
  
    tab.table.setRowHeight(20);
}


public  void remplir_art(String fabr ,jcombo article_comb){
	article_comb.removeAllItems();
	article_comb.addItem("---Selectionner un article-----");
	list_article_tr=art.select_article_code(fabr);
for(int i=0;i<list_article_tr.size();i++)
{
       //Pour affecter une valeur de base de données é un Combobox
	article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
	   i++;
}}


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












