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
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class recap extends JFrame{
/**
	 * 
	 */
	ArrayList <String>list_recap= new ArrayList<String>() ;

	private static final long serialVersionUID = 1L;
private JPanel pan_chaine_lab=new JPanel();
private JPanel pan_chaine_combo=new JPanel();
private JPanel pan_chaine=new JPanel();
private JPanel pan_zone_lab=new JPanel();
private JPanel pan_zone_combo=new JPanel();
private JPanel pan_zone=new JPanel();
private JPanel pan_poste_lab=new JPanel();
private JPanel pan_poste_combo=new JPanel();
private JPanel pan_poste=new JPanel();
private JPanel pan_tab=new JPanel();
private JPanel pan_rech_lab=new JPanel();
private JPanel pan_rech_jtext=new JPanel();
private JPanel pan_rech=new JPanel();
private JPanel pan_pied=new JPanel();
private JPanel pan_but=new JPanel();
private JPanel pan_count=new JPanel();

private JPanel pan_entete=new JPanel();
private JLabel chaine_lab=new JLabel("Chaine");
private jcombo chaine_combo;
private JLabel zone_lab=new JLabel("Zone");
private jcombo zone_combo;
private JLabel poste_lab=new JLabel("Poste");
private jcombo poste_combo;
private JLabel rech_lab=new JLabel("Recherche");
private JTextField rech_jtext=new JTextField();
private JPanel panel;
private JButton recherche=new JButton("Recherche");
private int i=1;
private String co="Nombre de poste : 0";
private JLabel count = new JLabel(co);
private static List<String> list_z;
private static List<String> list_c;
private static List<String> list_p;

//private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

public recap (final String log){

		
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	   setIconImage(img);
	   selectioncomb.windows(this,log);
	    composant(log);
	}
public void composant(final String log){
	final gestion_recap recap=new gestion_recap();
	  Object [] entete={"Matricule","Nom/Prenom","Num telephone","Type"};
	    final Tableau tab=new Tableau(entete);
	    JScrollPane p=new JScrollPane(tab);
		   tab.setStyle(2);
	generale.styles("Nimbus");
	SwingUtilities.updateComponentTreeUI(this);
	  panel= new JPanel(){   
	  	
		private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){
	  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
	             img.paintIcon(this, g,0, 0);

	  	}  };
	   final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,15);
	   
	  

	      list_z = new ArrayList<String>(Arrays.asList(new String[]{"---Sélectionner une Zone-----"}));
	      zone_combo = new jcombo(list_z.toArray());
	      selectioncomb.selectzone(zone_combo ,this,log);
	      
	       list_c = new ArrayList<String>(Arrays.asList(new String[]{"---Sélectionner une Chaine-----"}));
	       chaine_combo = new jcombo(list_c.toArray());
			selectioncomb.selectchaine(chaine_combo,this,log);
	 	   
	 	   
	 	  list_p = new ArrayList<String>(Arrays.asList(new String[]{"---Sélectionner un Poste-----"}));
	      poste_combo = new jcombo(list_p.toArray());
	      selectioncomb.selectposte(poste_combo ,this,log);
	      selectioncomb.prv.add("recap");
		  recherche.addActionListener(
		             new ActionListener() { 
		                 public void actionPerformed(ActionEvent e) {
		                	 if(rech_jtext.getText().equals("")&&zone_combo.getSelectedIndex()==0&&poste_combo.getSelectedIndex()==0
		                			 &&chaine_combo.getSelectedIndex()==0){
		                		 JOptionPane.showMessageDialog(null, 
		      	 	      				"Vous devez au moins sélectionner un critére", "",
		      	 	  		        JOptionPane.INFORMATION_MESSAGE);
		                	 }
		                	 else{
		                		int rows = tab.table.getRowCount(); 
		    	            	for(int i = rows - 1; i >=0; i--)
		    	            	{
		    	            		((DefaultTableModel) tab.table.getModel()).removeRow(i);

		    	            	}
		                	 
		                	 String[] partsc = chaine_combo.getSelectedItem().toString().split(" ");
		        		     String partc1 = partsc[0]; // 004
		        		     String[] partsz = zone_combo.getSelectedItem().toString().split(" ");
		        	         String partz1 = partsz[0]; // 004
		        	         String[] part_post = poste_combo.getSelectedItem().toString().split(" ");
		        	         String code_post = part_post[0]; 
		        	     //    System.out.println(partc1+" dddddd");
		        	         int j=1;
		           			int l = 0;  i=1;
		           			list_recap=recap.recherche(partc1,partz1,code_post,rech_jtext.getText());
		           		  for(i=0;i<list_recap.size();i=i+5){
		        			   tab.ajouter();}
		           			while(l<list_recap.size()){
		           			
		           			 while(l<j*5){
		           				 
		           				 tab.getTable().setValueAt(list_recap.get(l), j-1,0);
		           				 tab.getTable().setValueAt(list_recap.get(l+1)
		           						+" "+list_recap.get(l+2),j-1,1);
		           				 
		           				 tab.getTable().setValueAt(list_recap.get(l+3), j-1,2);
		           				 tab.getTable().setValueAt(list_recap.get(l+4), j-1,3);
		           				
		           				 i=i+1; l=l+5;
		           			 }
		       				
		           			 i=1;
		           			j=j+1;		
		           			}           			
		           		 co = co.replace("Nombre de poste : 0", "Nombre de poste :  "+tab.table.getRowCount());
		           		// System.out.println(co);
		           		 count.setText(co);
		                	 }
		                 } });
		   
		   try{
		   	//	UIManager.setLookAndFeel(laf);
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
		   pan_entete.add(pan_chaine);
		   pan_entete.add(pan_zone);
		   pan_entete.add(pan_poste);
		   
		   pan_rech.add(pan_rech_lab);
		   pan_rech_lab.add(rech_lab);
		   pan_rech.add(pan_rech_jtext);
		   pan_rech_jtext.add(rech_jtext);

		   pan_chaine.add(pan_chaine_lab);
		   pan_chaine_lab.add(chaine_lab);
		   pan_chaine.add(pan_chaine_combo);
		   pan_chaine_combo.add(chaine_combo);

		   pan_zone.add(pan_zone_lab);
		   pan_zone_lab.add(zone_lab);
		   pan_zone.add(pan_zone_combo);
		   pan_zone_combo.add(zone_combo);

		   pan_poste.add(pan_poste_lab);
		   pan_poste_lab.add(poste_lab);
		   pan_poste.add(pan_poste_combo);
		   pan_poste_combo.add(poste_combo);
		   
           pan_pied.add(pan_count);
           pan_pied.add(pan_but);
           
           pan_count.add(count);
           pan_but.add(recherche);

		   panel.add(pan_entete);
		   panel.add(pan_tab);
		   pan_tab.add(p);
		   panel.add(pan_pied);
		   p.setPreferredSize(new Dimension(930,700));
		   chaine_combo.setPreferredSize(new Dimension(50,30));
		   zone_combo.setPreferredSize(new Dimension(50,30));
		   poste_combo.setPreferredSize(new Dimension(50,30));
		   rech_jtext.setPreferredSize(new Dimension(50,30));

	 
	 

	  pan_chaine.setOpaque(false);
	  pan_chaine_lab.setOpaque(false);
	  pan_chaine_combo.setOpaque(false);
	  pan_zone.setOpaque(false);
	  pan_zone_lab.setOpaque(false);
	  pan_zone_combo.setOpaque(false);
	  pan_poste.setOpaque(false);
	  pan_poste_lab.setOpaque(false);
	  pan_poste_combo.setOpaque(false);
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
    chaine_combo.setPreferredSize(new Dimension(210,30));
    zone_combo.setPreferredSize(new Dimension(210,30));
    poste_combo.setPreferredSize(new Dimension(210,30));
    chaine_lab.setFont(police2);
    rech_lab.setFont(police2);
    zone_lab.setFont(police2);
    poste_lab.setFont(police2);
    chaine_lab.setForeground(new Color(255,255,255)); 
    rech_lab.setForeground(new Color(255,255,255)); 
    zone_lab.setForeground(new Color(255,255,255)); 
    poste_lab.setForeground(new Color(255,255,255));


        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    pan_entete.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    
		pan_tab.setLayout(new BoxLayout(pan_tab,BoxLayout.Y_AXIS));
	    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	    pan_rech.setLayout(new BoxLayout(pan_rech,BoxLayout.Y_AXIS));
	    pan_rech_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));
	    pan_rech_jtext.setLayout( new FlowLayout( FlowLayout.LEFT ));

	    pan_chaine.setLayout(new BoxLayout(pan_chaine,BoxLayout.Y_AXIS));
	    pan_pied.setLayout(new BoxLayout(pan_pied,BoxLayout.X_AXIS));
		pan_but.setLayout( new FlowLayout( FlowLayout.RIGHT));
		pan_count.setLayout( new FlowLayout( FlowLayout.LEFT));

	    pan_chaine_combo.setLayout( new FlowLayout( FlowLayout.LEFT ));
	    pan_chaine_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));


		pan_zone.setLayout(new BoxLayout(pan_zone,BoxLayout.Y_AXIS));
		pan_zone_combo.setLayout( new FlowLayout( FlowLayout.LEFT ));
		pan_zone_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));

		pan_poste.setLayout(new BoxLayout(pan_poste,BoxLayout.Y_AXIS));
		pan_poste_combo.setLayout( new FlowLayout( FlowLayout.LEFT ));
		pan_poste_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));

		pan_entete.setLayout(new BoxLayout(pan_entete,BoxLayout.X_AXIS));

	    recherche.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
	    recherche.setPreferredSize(new Dimension(120, 33));
	     
    setTitle("Recherche avancée" );
    setSize(1000, 600);
    setLocationRelativeTo(null);          
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(panel);
  
    tab.table.setRowHeight(20);
    


}

}
