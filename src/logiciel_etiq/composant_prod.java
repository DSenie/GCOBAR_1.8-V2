package logiciel_etiq;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class composant_prod extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

	JLabel code_orig_jlab=new JLabel("code origine");
	JTextField code_orig_jtext=new JTextField();
	JLabel famille_lab=new JLabel("famille");
	JComboBox<String> famille=new JComboBox<String>();
	JLabel code_enie_lab=new JLabel("code Enie");
	JTextField code_enie_jtext=new JTextField();
	JLabel designation_lab=new JLabel("Designation");
	JTextField designation_jtext=new JTextField();
	JLabel position_lab=new JLabel("Position");
	JTextField position_jtext=new JTextField();
	JLabel indice_lab=new JLabel("Indice");
	JTextField indice_jtext=new JTextField();
	JLabel coef_lab=new JLabel("Coeficient");
	JTextField coef_jtext=new JTextField();
	
	JButton valid_ajou=new JButton("Valider");
	JButton valid_supp=new JButton("Valider");
	JButton valid_modif=new JButton("Valider");
	JButton retour=new JButton("Retour");
	
	JButton but_sauv=new JButton("Ajouter");
	JButton but_modif=new JButton("Modifier");
	JButton but_sup=new JButton("Supprimer");
	
	JPanel pan_global=new JPanel();
	
	JPanel pan_code_orig=new JPanel();
	JPanel pan_code_orig_lab=new JPanel();
	JPanel pan_code_orig_jtext=new JPanel();

	JPanel pan_code_enie=new JPanel();
	JPanel pan_code_enie_lab=new JPanel();
	JPanel pan_code_enie_jtext=new JPanel();
	
	JPanel pan_designation=new JPanel();
	JPanel pan_designation_lab=new JPanel();
	JPanel pan_designation_jtext=new JPanel();
	
	JPanel pan_position=new JPanel();
	JPanel pan_position_lab=new JPanel();
	JPanel pan_position_jtext=new JPanel();
	
	JPanel pan_indice=new JPanel();
	JPanel pan_indice_lab=new JPanel();
	JPanel pan_indice_jtext=new JPanel();
	
	JPanel pan_coef=new JPanel();
	JPanel pan_coef_lab=new JPanel();
	JPanel pan_coef_jtext=new JPanel();
	
	composant_prod(final String log){ 
		final menu fr=new menu(log);
		fr.setVisible(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
  	    setIconImage(img);
		addWindowListener(new WindowAdapter(){
	        @SuppressWarnings("deprecation")
			public void windowClosing(WindowEvent e){
	        	
	              int reponse = JOptionPane.showConfirmDialog(
	                                   null, "Voulez-vous vraiment quitter cette fenétre ?",
	                                   "Confirmation",
	                                   JOptionPane.YES_NO_OPTION,
	                                   JOptionPane.QUESTION_MESSAGE);
	              if (reponse==JOptionPane.YES_OPTION){
	               hide(); 
	               
	              fr.setVisible(true); 
	              try{
	             		UIManager.setLookAndFeel(laf);
	             		SwingUtilities.updateComponentTreeUI(fr);
	             		
	             	}catch(Exception e1){
	             		
	             		e1.printStackTrace();
	             	}
	               setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
	              }


	              else  if (reponse==JOptionPane.NO_OPTION){
	           	   setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);	                   }
	              else  {
		           	   setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);	                   }
	        }
	});  
	try{
			UIManager.setLookAndFeel(laf);
			SwingUtilities.updateComponentTreeUI(this);
			
		}catch(Exception e1){
			
			e1.printStackTrace();
		}
	composant();

	  }
	public void composant(){
		final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);
		  LineBorder border = new LineBorder ( Color.white, 1, true );
		  TitledBorder titl2 = new TitledBorder ( border, "Edition Composant", TitledBorder.DEFAULT_POSITION,
	        TitledBorder.DEFAULT_POSITION, police2, Color.white);
		  pan_global.setBorder(titl2);

   			valid_ajou.setVisible(false);
          	valid_modif.setVisible(false);
          	valid_supp.setVisible(false);
          	but_sauv.setVisible(true);
          	but_modif.setVisible(true);
          	but_sup.setVisible(true);
          	retour.setVisible(true);
		    JPanel panel =new JPanel() {   
	  		
				private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){
	  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
	             img.paintIcon(this, g,0, 0);
	             }  } ;
	             
	             but_sauv.addActionListener(
	 	  	            new ActionListener() { 
	 	  	            public void actionPerformed(ActionEvent e) {
		       	      		  LineBorder border = new LineBorder ( Color.white, 1, true );
		       	      		  TitledBorder titl2 = new TitledBorder ( border, "Ajouter Composant", TitledBorder.DEFAULT_POSITION,
		       	      	      TitledBorder.DEFAULT_POSITION, police2, Color.white);
		       	      	 pan_global.setBorder(titl2);
		       	      		valid_ajou.setVisible(true);
	       	              	valid_modif.setVisible(false);
	       	              	valid_supp.setVisible(false);
	       	              	but_sauv.setVisible(false);
	       	                but_modif.setVisible(false);
	       	                but_sup.setVisible(false);
	       	              	retour.setVisible(true);
	 	  	            }});
	 	  	           
	             but_modif.addActionListener(
		 	  	            new ActionListener() { 
		 	  	            public void actionPerformed(ActionEvent e) {
		 	  	             LineBorder border = new LineBorder ( Color.white, 1, true );
		       	      		 TitledBorder titl2 = new TitledBorder ( border, "modifier Composant", TitledBorder.DEFAULT_POSITION,
		       	      	     TitledBorder.DEFAULT_POSITION, police2, Color.white);
		       	      	pan_global.setBorder(titl2);
		       	      	valid_ajou.setVisible(false);
       	              	valid_modif.setVisible(true);
       	              	valid_supp.setVisible(false);
       	              	but_sauv.setVisible(false);
       	                but_modif.setVisible(false);
       	                but_sup.setVisible(false);
       	              	retour.setVisible(true);
		 	  	            }});
	             but_sup.addActionListener(
		 	  	            new ActionListener() { 
		 	  	            public void actionPerformed(ActionEvent e) {
		 	  	             LineBorder border = new LineBorder ( Color.white, 1, true );
		       	      		  TitledBorder titl2 = new TitledBorder ( border, "supprimer Composant", TitledBorder.DEFAULT_POSITION,
		       	      	        TitledBorder.DEFAULT_POSITION, police2, Color.white);
		       	      	 pan_global.setBorder(titl2);
		       	      	valid_ajou.setVisible(false);
       	              	valid_modif.setVisible(false);
       	              	valid_supp.setVisible(true);
       	              	but_sauv.setVisible(false);
       	                but_modif.setVisible(false);
       	                but_sup.setVisible(false);
       	              	retour.setVisible(true);
		 	  	            }});
	             
	               valid_ajou.addActionListener(
		 	  	            new ActionListener() { 
		 	  	            public void actionPerformed(ActionEvent e) {
		 	  	            	
		 	  	            }});
		 	  	           
	               valid_modif.addActionListener(
			 	  	            new ActionListener() { 
			 	  	            public void actionPerformed(ActionEvent e) {
			 	  	            	
			 	  	            }});
	               valid_supp.addActionListener(
			 	  	            new ActionListener() { 
			 	  	            public void actionPerformed(ActionEvent e) {
			 	  	            	
			 	  	            }});
	               
	               retour.addActionListener(
	       	            new ActionListener() { 
	       	            public void actionPerformed(ActionEvent e) {
	       	      		 LineBorder border = new LineBorder ( Color.white, 1, true );
	       	      		 TitledBorder titl2 = new TitledBorder ( border, "Edition Composant", TitledBorder.DEFAULT_POSITION,
	       	      	     TitledBorder.DEFAULT_POSITION, police2, Color.white);
	       	      	      pan_global.setBorder(titl2);
	       	            	valid_ajou.setVisible(false);
	       	              	valid_modif.setVisible(false);
	       	              	valid_supp.setVisible(false);
	       	              	but_sauv.setVisible(true);
	       	                but_modif.setVisible(true);
	       	                but_sup.setVisible(true);
	       	              	retour.setVisible(false);
	       	            }});
	               
	       	       code_orig_jlab.setFont(police2);
	       	       code_orig_jlab.setForeground(Color.white); 
	       	    
	               panel.add(pan_global);
	               pan_global.add(pan_code_orig);
	               pan_code_orig.add(pan_code_orig_lab);
	               pan_code_orig.add(pan_code_orig_jtext);
	               pan_code_orig_lab.add(code_orig_jlab);
	               pan_code_orig_jtext.add(code_orig_jtext);
	               pan_global.setOpaque(false);
	               pan_code_orig.setOpaque(false);
	               pan_code_orig_jtext.setOpaque(false);
	               pan_code_orig_lab.setOpaque(false);
	    	 	   panel.setBorder(BorderFactory.createEmptyBorder(30,30, 30, 30));

	       	      panel.setLayout(new GridLayout(1,1));
	       	      pan_global.setLayout(new BoxLayout(pan_global,BoxLayout.Y_AXIS));
	              setTitle("Gestion des composants" );
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
					
			
					 new composant_prod("7");
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
