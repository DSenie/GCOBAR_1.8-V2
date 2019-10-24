package logiciel_etiq;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;


public class recap_codification extends JFrame{
/**
	 * 
	 */
	ArrayList <String>list_recap_code= new ArrayList<String>() ;
	ArrayList <String>list_recap_code2= new ArrayList<String>() ;
	ArrayList <String>list_recap_code3= new ArrayList<String>() ;

	private static final long serialVersionUID = 1L;
private JPanel pan_code_lab=new JPanel();
private JPanel pan_code_combo=new JPanel();
private JPanel pan_code=new JPanel();
private JPanel pan_ref_lab=new JPanel();
private JPanel pan_ref_combo=new JPanel();
private JPanel pan_ref=new JPanel();
private JPanel pan_codegener_combo=new JPanel();
private JPanel pan_codegener_lab=new JPanel();

private JPanel pan_codegener=new JPanel();
private JPanel pan_tab=new JPanel();
private JPanel pan_rech_lab=new JPanel();
private JPanel pan_rech_jtext=new JPanel();
private JPanel pan_rech=new JPanel();
private JPanel pan_pied=new JPanel();
private JPanel pan_but=new JPanel();
private JPanel pan_count=new JPanel();

private JPanel pan_entete=new JPanel();
private JLabel code_lab=new JLabel("Codification");
private JComboBox<String> code_combo=new JComboBox<String>();

private JLabel rech_lab=new JLabel("Recherche");
private JTextField rech_jtext=new JTextField();
private JPanel panel;
private JButton recherche=new JButton("Recherche");
private int i=1;

private String co="Nombre : 0";
private JLabel count = new JLabel(co);
private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

public recap_codification (final String log){

		
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	   setIconImage(img);		
		  selectioncomb.windows(this,log,laf);


	composant();
}
public void composant(){


	final gestion_fournisseur forn=new gestion_fournisseur();
	  Object [] entete={"code r�f�rence ou famille","designation"};
	    final Tableau tab=new Tableau(entete);
	    JScrollPane p=new JScrollPane(tab);
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
	   final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,15);
	   
	  
	   selectioncomb.prv.add("recap_codification");
		   
		   code_combo.addItem("---Selectionner une Codification-----");
		          //Pour affecter une valeur de base de donn�es � un Combobox 
			   code_combo.addItem("Code r�f�rence");
			   code_combo.addItem("Code famille");


			 
			   
			   
			  
			  
		   recherche.addActionListener(
		             new ActionListener() { 
		                 public void actionPerformed(ActionEvent e) {
		                	 if(rech_jtext.getText().equals("")&&code_combo.getSelectedIndex()==0
		                			 ){
		                		 JOptionPane.showMessageDialog(null, 
		      	 	      				"Vous devez au moins selectionner un crit�re", "",
		      	 	  		        JOptionPane.INFORMATION_MESSAGE);
		                	 }
		                	 else{
		                		int rows = tab.table.getRowCount(); 
		    	            	for(int i = rows - 1; i >=0; i--)
		    	            	{
		    	            		((DefaultTableModel) tab.table.getModel()).removeRow(i);

		    	            	}
		                	 
		        		   
		        	      
		           			
		           		 if(code_combo.getSelectedIndex()==1){
	                		// forn.select();
	                		 list_recap_code=forn.recherche("Code r�f�rence",rech_jtext.getText());
	                		 for(i=0;i<list_recap_code.size();i=i+2){tab.ajouter();}
	                			int j=1;
	                			int l = 0; i=1;
	                			while(l<list_recap_code.size()){
	                			
	                			 while(l<j*2){
	                				 tab.getTable().setValueAt(list_recap_code.get(l).toLowerCase(), j-1, i-1);
	                				 i=i+1; l=l+1;
	                			 }
	                			 i=1;
	                			j=j+1;		
	                			}	

	                	 }
	                	 else  if(code_combo.getSelectedIndex()==2){	                		
	                		// forn.select();
	                	 list_recap_code2=forn.recherche("Code famille",rech_jtext.getText());
	                		 for(i=0;i<list_recap_code2.size();i=i+2){tab.ajouter();}
	                			int j=1;
	                			int l = 0; i=1;
	                			while(l<list_recap_code2.size()){
	                			
	                			 while(l<j*2){
	                				 tab.getTable().setValueAt(list_recap_code2.get(l).toLowerCase(), j-1, i-1);
	                				 i=i+1; l=l+1;
	                			 }
	                			 i=1;
	                			j=j+1;		
	                			}	

	                	 }
	                	 else {  
	                		 
	                		 list_recap_code3= forn.recherche("",rech_jtext.getText());
	                		 //forn.select();
	                		    for(i=0;i<list_recap_code3.size();i=i+2){tab.ajouter();}
	                			int j=1;
	                			int l = 0; i=1;
	                			while(l<list_recap_code3.size()){
	                			
	                			 while(l<j*2){
	                				 tab.getTable().setValueAt(list_recap_code3.get(l).toLowerCase(), j-1, i-1);
	                				 i=i+1; l=l+1;
	                			 }
	                			 i=1;
	                			j=j+1;		
	                			}
	                		
	                	 }
		           		
		           				
		           					
		           				}
		           				
		                	 if(tab.table.getRowCount()==0){
		                		 JOptionPane.showMessageDialog(null, 
			      	 	      				"r�f�rence ou code famille inexistant", "",
			      	 	  		        JOptionPane.INFORMATION_MESSAGE);
		                	 }
		           		 co =  "Nombre  :  "+tab.table.getRowCount();
		           		// System.out.println(co);
		           		 count.setText(co);
		                	 }
		           			 });
		   
		   try{
		   		UIManager.setLookAndFeel(laf);
		   		SwingUtilities.updateComponentTreeUI(this);
		   	}
		     catch(Exception e1){
		   		e1.printStackTrace();
		   	}
		   
		    count.setForeground(Color.white); 
		    count.setBackground(new Color(6,119,144));
		    count.setOpaque(true);
		    count.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		    count.setFont(police2);
		   
	       pan_entete.add(pan_rech);
		   pan_entete.add(pan_code);
		   pan_entete.add(pan_ref);
		   pan_entete.add(pan_codegener);
		   
		   pan_rech.add(pan_rech_lab);
		   pan_rech_lab.add(rech_lab);
		   pan_rech.add(pan_rech_jtext);
		   pan_rech_jtext.add(rech_jtext);

		   pan_code.add(pan_code_lab);
		   pan_code_lab.add(code_lab);
		   pan_code.add(pan_code_combo);
		   pan_code_combo.add(code_combo);

		  
		   
           pan_pied.add(pan_count);
           pan_pied.add(pan_but);
           
           pan_count.add(count);
           pan_but.add(recherche);

		   panel.add(pan_entete);
		   panel.add(pan_tab);
		   pan_tab.add(p);
		   panel.add(pan_pied);
		   p.setPreferredSize(new Dimension(930,700));
		   code_combo.setPreferredSize(new Dimension(50,30));
		
		   rech_jtext.setPreferredSize(new Dimension(50,30));

	 
	 

	  pan_code.setOpaque(false);
	  pan_code_lab.setOpaque(false);
	  pan_code_combo.setOpaque(false);
	  pan_ref.setOpaque(false);
	  pan_ref_lab.setOpaque(false);
	  pan_ref_combo.setOpaque(false);
	  pan_codegener.setOpaque(false);
	  pan_codegener_lab.setOpaque(false);
	  pan_codegener_combo.setOpaque(false);
	  pan_rech.setOpaque(false);
	  pan_rech_lab.setOpaque(false);
	  pan_rech_jtext.setOpaque(false);
      pan_entete.setOpaque(false);
      panel.setOpaque(false);
	  pan_tab.setOpaque(false);
      pan_pied.setOpaque(false);
      pan_but.setOpaque(false);
      pan_count.setOpaque(false);

	  pan_tab.setPreferredSize(new Dimension(40, 400));

	 
    rech_jtext.setPreferredSize(new Dimension(210,30));
    code_combo.setPreferredSize(new Dimension(210,30));
  
    code_lab.setFont(police2);
    rech_lab.setFont(police2);
    
    code_lab.setForeground(new Color(255,255,255)); 
    rech_lab.setForeground(new Color(255,255,255)); 
  


        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    pan_entete.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    
		pan_tab.setLayout(new BoxLayout(pan_tab,BoxLayout.Y_AXIS));
	    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	    pan_rech.setLayout(new BoxLayout(pan_rech,BoxLayout.Y_AXIS));
	    pan_rech_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));
	    pan_rech_jtext.setLayout( new FlowLayout( FlowLayout.LEFT ));

	    pan_code.setLayout(new BoxLayout(pan_code,BoxLayout.Y_AXIS));
	    pan_pied.setLayout(new BoxLayout(pan_pied,BoxLayout.X_AXIS));
		pan_but.setLayout( new FlowLayout( FlowLayout.RIGHT));
		pan_count.setLayout( new FlowLayout( FlowLayout.LEFT));

	    pan_code_combo.setLayout( new FlowLayout( FlowLayout.LEFT ));
	    pan_code_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));


		pan_ref.setLayout(new BoxLayout(pan_ref,BoxLayout.Y_AXIS));
		pan_ref_combo.setLayout( new FlowLayout( FlowLayout.LEFT ));
		pan_ref_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));

		pan_codegener.setLayout(new BoxLayout(pan_codegener,BoxLayout.Y_AXIS));
		pan_codegener_combo.setLayout( new FlowLayout( FlowLayout.LEFT ));
		pan_codegener_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));

		pan_entete.setLayout(new BoxLayout(pan_entete,BoxLayout.X_AXIS));

	    recherche.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
	    recherche.setPreferredSize(new Dimension(120, 33));
	     
    setTitle("Recherche avanc�" );
    setSize(1000, 600);
    setLocationRelativeTo(null);          
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(panel);
  
    tab.table.setRowHeight(20);
    


}

}
