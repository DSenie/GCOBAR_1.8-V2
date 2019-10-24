package logiciel_etiq;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class composants extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

	   Object [] entete={"CODE ORIGINE","INDICE","CODE ENIE","DESIGNATION","COEFF","Position"};
	   final Tableau tab=new Tableau(entete);
       JLabel count;
     JButton ajouter=new JButton("Ajouter");
  	 JButton modif=new JButton("Modifier");
  	 JButton supp=new JButton("Supprimer");
     JButton ajouter_lig=new JButton("Ajouter un ligne");
     
     JButton valid_ajouter=new JButton("valider");
  	 JButton valid_modif=new JButton("valider");
  	 JButton valid_supp=new JButton("valider");
     JButton retour=new JButton("retour");

     
    final JPanel panrech =new JPanel();
	final JPanel pantab=new JPanel();
	final JPanel panbut=new JPanel();

    final JLabel code = new JLabel("Recherche");
    final JTextField code1 = new JTextField("Recherche");
    gestion_composant comp=new gestion_composant();
    JScrollPane p=new JScrollPane(tab);
   
	int i;
	int j;
	boolean co_bo=true,cf_bo=true;
	boolean co_bo1=true,cf_bo1=true;
	  String msg="";String msgm="";
	  
	  public boolean isValid(String chaine) {
			try {
				Integer.parseInt(chaine);
			} catch (NumberFormatException e){
				return false;
			}
	 
			return true;
		}
	
	  composants(final String log){ 
	       
		  
			final menu fr=new menu(log);
			fr.setVisible(false);
			tab.allowEdition3=false;
			Toolkit kit = Toolkit.getDefaultToolkit();
		    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	  	    setIconImage(img);
			addWindowListener(new WindowAdapter(){
		        @SuppressWarnings("deprecation")
				public void windowClosing(WindowEvent e){
		        	
		              int reponse = JOptionPane.showConfirmDialog(
		                                   null, "Voulez-vous vraiment quitter cette fen�tre ?",
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


		           	   if (reponse==JOptionPane.NO_OPTION){
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
		final JPanel panelcomp=new JPanel();

		  LineBorder border = new LineBorder ( Color.white, 1, true );
		  TitledBorder titl2 = new TitledBorder ( border, "Edition Composant", TitledBorder.DEFAULT_POSITION,
	        TitledBorder.DEFAULT_POSITION, police2, Color.white);
	      panelcomp.setBorder(titl2);

   			valid_ajouter.setVisible(false);
          	valid_modif.setVisible(false);
          	valid_supp.setVisible(false);
          	ajouter.setVisible(true);
          	modif.setVisible(true);
          	supp.setVisible(true);
          	retour.setVisible(true);
          	ajouter_lig.setVisible(false);
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
	  	  	
	  	 code1.addMouseListener( 
				  new  MouseAdapter(){
					  public void mousePressed(MouseEvent e) {
						    // TODO Auto-generated method stub
							 code1.setText("");
							 code1.setForeground(Color.gray); 
						}     
				  });
		  
		  code1.addFocusListener(
				  new FocusListener() {

			    @Override
			    public void focusGained(FocusEvent e) { code1.setForeground(Color.black); 

			    }

			    @Override
			    public void focusLost(FocusEvent e) {
					 code1.setText("Recherche");
					 code1.setForeground(Color.gray); 

			        // You could do something here when the field loses focus, if you like
			    }

			});

	  	   ajouter_lig.addActionListener(
	  	            new ActionListener() { 
	  	            public void actionPerformed(ActionEvent e) {
	  	         	 tab.ajouter();
		  	      	 int i=tab.table.getRowCount();
		  	      		tab.table.setValueAt("", i-1,0);
		  	      	tab.table.setValueAt("", i-1,2);
		  	      tab.table.setValueAt("", i-1,3);
		  	    tab.table.setValueAt("", i-1,5);
	  	         	tab.table.setValueAt("", i-1,1);
	  	         	tab.table.setValueAt("", i-1,4);
	  	         	
		  	      	 
	  	            }});
	  	 ajouter.addActionListener(
	  	            new ActionListener() { 
	  	            public void actionPerformed(ActionEvent e) {
	  	            	 Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

	  	     		  LineBorder border = new LineBorder ( Color.white, 1, true );
	  	     		  TitledBorder titl2 = new TitledBorder ( border, "Ajouter", TitledBorder.DEFAULT_POSITION,
	  	     	        TitledBorder.DEFAULT_POSITION, police2, Color.white);
            	      panelcomp.setBorder(titl2);

	  	            	valid_ajouter.setVisible(true);
	  	            	valid_modif.setVisible(false);
	  	            	valid_supp.setVisible(false);
	  	            	ajouter.setVisible(false);
	  	            	modif.setVisible(false);
	  	            	supp.setVisible(false);
	  	            	retour.setVisible(true);
	  	            	ajouter_lig.setVisible(true);

	  	          	int rows = tab.table.getRowCount(); 
	            	for(int i = rows - 1; i >=0; i--)
	            	{
	            		((DefaultTableModel) tab.table.getModel()).removeRow(i);

	            	}
	  	            }});
	  	valid_ajouter.addActionListener(
	  	            new ActionListener() { 
	  	            public void actionPerformed(ActionEvent e) {
		  	      		
	  	            	if(tab.table.getRowCount()==0){ JOptionPane.showMessageDialog(null, 
		  	      				"Vous devez tout d'abord ajouter une ligne. ", "",
		  	  		        JOptionPane.INFORMATION_MESSAGE);}
		  	    	else{
		  	    		msg="";
		  	    	  	co_bo1=true;
			  	  	  	cf_bo1=true;
			  	            	if(vaidCheck())

			  	            	{	      
	  	           for(int i=0;i<tab.table.getRowCount();i++){
	  	        	 String col2= tab.table.getValueAt(i, 1).toString();
           		  String cof=tab.table.getValueAt(i, 4).toString();
           		if(isValid(col2)==false){co_bo1=false;}
        		if(isValid(cof)==false){cf_bo1=false;}
      		  if(co_bo1==true&&cf_bo1==true){

           		 int cofe=Integer.parseInt(cof);
       		     int col2_i=Integer.parseInt(col2);	  
	  	  	            comp.ajouter_composant(tab.table.getValueAt(i, 0).toString(),col2_i,tab.table.getValueAt(i, 2).toString(),tab.table.getValueAt(i, 3).toString(),cofe,tab.table.getValueAt(i, 5).toString());    
	  	            		 }
	  	           }
	  	           
	  	    	 if(co_bo1==true&&cf_bo1==true) JOptionPane.showMessageDialog(null,"Les Composants ont �t� bien ajout�s");
	 	  	   	if(co_bo1==false||cf_bo1==false){
	          		 msg+="Le coef ou l'indice doit etre un nombre \n";
	 	  	           }
	          	  else {
	          	  msg+=msg.replace("Le coef ou l'indice doit etre un nombre \n","");}
	          		
	 	  	            	}
	   	            		else{	  	            		
	 	  	            		if(vaidCheck()==false) {	
	 	  	                      msg+="Un Champ est vide \n";}
	 	  	  	  	            	else {msg=msg.replace("Un Champ est vide \n", "");}
	 	  	            	
	 	  	            	
	 	  	            	}
	 	  	            	if(!msg.equals("")){
	 		  	            	JOptionPane.showMessageDialog(null,msg);                                                                            
	 		  	            	}
		  	    	}}});
	  	modif.addActionListener(
  	            new ActionListener() { 
  	            public void actionPerformed(ActionEvent e) {
  	            	 Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

  	     		  LineBorder border = new LineBorder ( Color.white, 1, true );
  	     		  TitledBorder titl2 = new TitledBorder ( border, "Modifier", TitledBorder.DEFAULT_POSITION,
  	     	        TitledBorder.DEFAULT_POSITION, police2, Color.white);
  	     		 panelcomp.setBorder(titl2);
  	            	valid_ajouter.setVisible(false);
  	            	valid_modif.setVisible(true);
  	            	valid_supp.setVisible(false);
  	            	ajouter.setVisible(false);
  	            	modif.setVisible(false);
  	            	supp.setVisible(false);
  	            	retour.setVisible(true);
  	            	ajouter_lig.setVisible(false);

  	            }});
	  	valid_modif.addActionListener(
	  	            new ActionListener() { 
	  	            public void actionPerformed(ActionEvent e) {
	  	            	msgm="";
	  	           	co_bo=true;
	  	  	  	cf_bo=true;
	  	            	if(vaidCheck())

	  	            	{	  	      

	  	      	 for(int i=0;i<tab.table.getRowCount();i++){
	            		  String col2= tab.table.getValueAt(i, 1).toString();
	            		  String cof=tab.table.getValueAt(i, 4).toString();
	            		if(isValid(col2)==false){co_bo=false;}
	            		if(isValid(cof)==false){cf_bo=false;}
	            		  if(co_bo==true&&cf_bo==true){
	            	      int col2_i=Integer.parseInt(col2);
	            		  int cofe=Integer.parseInt(cof);
	            		  comp.update_composant(tab.table.getValueAt(i, 0).toString(),col2_i,tab.table.getValueAt(i, 2).toString(),tab.table.getValueAt(i, 3).toString(),cofe,tab.table.getValueAt(i, 5).toString());    
	     	  	      	
  	 }  	
	            		 
	            		}
	  	      	 if(co_bo==true&&cf_bo==true&&(tab.table.getRowCount()!=0)) JOptionPane.showMessageDialog(null,"Modification r�ussie");
	  	      	 
	  	
	  	            	
	  	   	if(co_bo==false||cf_bo==false){
         		 msgm+="Le coef ou l'indice doit �tre  un nombre \n";
	  	           }
         	  else {
         	  msgm+=msgm.replace("Le coef ou l'indice doit �tre  un nombre \n","");}
	  	   	if(tab.table.getRowCount()==0){
       		 msgm+="Vous devez d'abord s�lectionner un composant. \n";
	  	           }
       	  else {
       	  msgm+=msgm.replace("Vous devez d'abord s�lectionner un composant. \n","");
	  	            	}
	  	            	}
	  	            	
	  	            	
	  	            	

  	            		else{	  	            		
	  	            		if(vaidCheck()==false) {	
	  	                      msgm+="Un Champ est vide \n";}
	  	  	  	            	else {msgm=msgm.replace("Un Champ est vide \n", "");}
	  	            	
	  	            	
	  	            	}
	  	            	if(!msgm.equals("")){
		  	            	JOptionPane.showMessageDialog(null,msgm);                                                                            
		  	            	}
	  	            }});
	  	 
		supp.addActionListener(
  	            new ActionListener() { 
  	            public void actionPerformed(ActionEvent e) {
  	            	 Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

  	     		  LineBorder border = new LineBorder ( Color.white, 1, true );
  	     		  TitledBorder titl2 = new TitledBorder ( border, "Supprimer", TitledBorder.DEFAULT_POSITION,
  	     	        TitledBorder.DEFAULT_POSITION, police2, Color.white);
  	     		 panelcomp.setBorder(titl2);
  	            	valid_ajouter.setVisible(false);
  	            	valid_modif.setVisible(false);
  	            	valid_supp.setVisible(true);
  	            	ajouter.setVisible(false);
  	            	modif.setVisible(false);
  	            	supp.setVisible(false);
  	            	retour.setVisible(true);
  	            	ajouter_lig.setVisible(false);

  	            }});
		
	  	 valid_supp.addActionListener(
	  	            new ActionListener() { 
	  	            public void actionPerformed(ActionEvent e) {

	  	            	tab.allowEdition=false;
	  	            	 if(tab.table.getSelectedRow()==-1){
	  	            		 
	  	            		 JOptionPane.showMessageDialog(null, 
	      	 	      				"Vous devez d'abord s�lectionner le composant que vous voulez supprimer", "",
	      	 	  		        JOptionPane.INFORMATION_MESSAGE);
	  	            	 }
	  	            	 else{
	  	            	int ligne = tab.table.getSelectedRow();//Si tu veut la ligne selectionn�e
	            		  comp.delete_composant(tab.table.getValueAt(ligne,2).toString());
	            		  ((DefaultTableModel) tab.table.getModel()).removeRow(tab.table.getSelectedRow());
	  	            	 }   }});
	  	 
	  	retour.addActionListener(
	            new ActionListener() { 
	            public void actionPerformed(ActionEvent e) {

	      		  LineBorder border = new LineBorder ( Color.white, 1, true );
	      		  TitledBorder titl2 = new TitledBorder ( border, "Edition Composant", TitledBorder.DEFAULT_POSITION,
	      	        TitledBorder.DEFAULT_POSITION, police2, Color.white);
	      		 panelcomp.setBorder(titl2);
	            	valid_ajouter.setVisible(false);
	              	valid_modif.setVisible(false);
	              	valid_supp.setVisible(false);
	              	ajouter.setVisible(true);
	              	modif.setVisible(true);
	              	supp.setVisible(true);
	              	retour.setVisible(true);
	              	ajouter_lig.setVisible(false);
	            	
	            }});
	 	   code1.addActionListener(
		            new ActionListener() { 
		            public void actionPerformed(ActionEvent e) {
		            	int rows = tab.table.getRowCount(); 
		            	for(int i = rows - 1; i >=0; i--)
		            	{
		            		((DefaultTableModel) tab.table.getModel()).removeRow(i);

		            	}
		      
		 	    
		 	  String text = code1.getText();
	    	  tab.text=text;
             if (text.length() == 0) {
            	 ajouter.setVisible(true);
                 ajouter_lig.setVisible(true);
	              supp.setVisible(false);
	              modif.setVisible(false);
             } else {
            	 
            	   comp.select_comp(text);
       		    for(i=0;i<comp.select_comp(text).size();i=i+6){		                	
                  tab.ajouter();}
       			int j=1;
       			int l = 0; i=1;
       			while(l<comp.select_comp(text).size()){

       			 while(l<j*6){
       				 tab.getTable().setValueAt(comp.select_comp(text).get(l), j-1, i-1);
       				 i=i+1; l=l+1;
       			 }
       			 i=1;
       			j=j+1;		
       			}		
       			if(comp.select_comp(text).size()>0){
       				ajouter.setVisible(true);
       				modif.setVisible(true);
       			    ajouter_lig.setVisible(false);
       			    supp.setVisible(true);
       			    valid_supp.setVisible(false);
       			   valid_ajouter.setVisible(false);
       			   valid_modif.setVisible(false);


}
       			
       			else{
	 JOptionPane.showMessageDialog(null, "Ce composant n'existe pas.", "",
		        JOptionPane.INFORMATION_MESSAGE);
		ajouter.setVisible(true);
			modif.setVisible(true);
		    ajouter_lig.setVisible(false);
		    supp.setVisible(true);
		    valid_supp.setVisible(false);
		   valid_ajouter.setVisible(false);
		   valid_modif.setVisible(false);
//	
}
           	// tab. sorter.setRowFilter(tab.filter);
           	// tab. sorter.setRowFilter(tab.filter1);
       		    //tab.sorter.setRowFilter(RowFilter.regexFilter(text));
       		    
             }
  		}
     });
	 	    try{
	 	   		UIManager.setLookAndFeel(laf);
	 	   		SwingUtilities.updateComponentTreeUI(this);
	 	   	}
	 	     catch(Exception e1){
	 	   		e1.printStackTrace();
	 	   	}
	 	    
	     panel.add(panelcomp);

	     panelcomp.add(panrech);
	     panelcomp.add(p); 
	     panelcomp.add(panbut);
//		panrech.add(pan2);
//		panrech.add(pan3);

		panrech.add(code1);
		
		panbut.add(valid_ajouter);
		 panbut.add(valid_modif);
		 panbut.add(valid_supp);
		 panbut.add(retour);
		panbut.add(ajouter);
		 panbut.add(modif);
		 panbut.add(supp);
		 panbut.add(ajouter_lig);
         panelcomp.setOpaque(false);
//		pan.add(pan1);

	    //pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
	    //paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));

	    code1.setPreferredSize(new Dimension(200,30));
	    tab.setPreferredSize(new Dimension(400, 4000));
	    //paneltext.setPreferredSize(new Dimension(40, 90));
	  	panrech.setLayout( new FlowLayout( FlowLayout.LEFT ));
	  	panbut.setLayout( new FlowLayout( FlowLayout.RIGHT ));
	    panel.setLayout(new GridLayout(1,1));
	    panelcomp.setLayout(new BoxLayout(panelcomp,BoxLayout.Y_AXIS));
	    

	    panel.setOpaque(false);
	    panbut.setOpaque(false);
	    panrech.setOpaque(false);
	    p.setOpaque(false);
	    p.setSize(10000,10000);
//	    pan1.setOpaque(false);
//	    pan2.setOpaque(false);
//	    pan3.setOpaque(false); 
	    //pan2.setLayout(null);
	    
	    ajouter.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
	    modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
	    supp.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
	    ajouter_lig.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
	    valid_ajouter.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
	    valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
	    valid_supp.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
	    retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

	    ajouter.setPreferredSize(new Dimension(150, 33));
	    modif.setPreferredSize(new Dimension(150, 33));
	    supp.setPreferredSize(new Dimension(150, 33));
	    ajouter_lig.setPreferredSize(new Dimension(150, 33));
	    valid_ajouter.setPreferredSize(new Dimension(150, 33));
	    valid_modif.setPreferredSize(new Dimension(150, 33));
	    valid_supp.setPreferredSize(new Dimension(150, 33));
	    retour.setPreferredSize(new Dimension(150, 33));
	    
	    code1.setBounds(00, 30, 150,30);
	    panrech.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
	    panbut.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		     
			  setTitle("Gestion de production" );
	          setSize(1000, 600);
	          setLocationRelativeTo(null);          
	          setVisible(true);
	          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      setContentPane(panel);
		      tab.table.setRowHeight(20);

	}	
	  
	
	public boolean validnombre()

	{
		if(tab.table.getCellEditor()!=null){

			tab.table.getCellEditor().stopCellEditing();

			}
	for(int i=0;i< tab.table.getRowCount();i++)
	{
	

	String indice=tab.table.getValueAt(i,1).toString();
	String coef=tab.table.getValueAt(i,4).toString();

	if(isValid(indice)==true&&isValid(coef)==true)

	{
	return false;

	}
	
	}

	return true;
	}	
	
	
	public boolean vaidCheck()

	{

	if(tab.table.getCellEditor()!=null){

	tab.table.getCellEditor().stopCellEditing();

	}

	for(int i=0;i< tab.table.getRowCount();i++)

	{

	for (int j=0;j<tab.table.getColumnCount();j++)

	{

	String om=tab.table.getValueAt(i,j).toString();


	if(om.trim().length()==0)

	{
		msg="";
	return false;

	}}
	}

	return true;
	}	
	
	
	 public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
			 new composants("4");
						//frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}


