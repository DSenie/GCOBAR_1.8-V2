package logiciel_etiq;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxRenderer;






import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


public class selectioncomb {
 
      public static boolean existe=false;
      public static boolean existe_zone=false;
      public static boolean existe_tache=false;

      public static boolean existe_chaine=false;
      public static boolean existe_profils=false;
      public static boolean existe_fournisseur=false;
      
      public static boolean existe_article=false;
      public static boolean existe_refer=false;
      public static boolean existe_gener=false;
      public static boolean existe_famille=false;
      public static ArrayList<String> prv=new ArrayList<String>();
      public static ArrayList<String> list_profil=new ArrayList<String>();
      
      public static ArrayList<String> list_color=new ArrayList<String>();

      public static String precedent;
	   protected final static JButton buttonOk = new JButton("Ajouter ");
	   protected final static JButton buttonOk1 = new JButton("Ajouter ");
	   protected final static JButton buttonOk2 = new JButton("Ajouter ");
	   protected final static JButton buttonOk3 = new JButton("Ajouter ");
     protected final static JButton buttonOktache = new JButton("Ajouter ");
     protected final static JButton buttonOkprod = new JButton("Ajouter ");
     protected final static JButton buttonOkprof = new JButton("Ajouter ");

	   protected final static JButton buttonOkzone = new JButton("Ajouter ");
      private final static JButton buttonOk4 = new JButton("Ajouter ");
      private final static JButton buttonOk5 = new JButton("Ajouter ");
      private final static JButton buttonOk6 = new JButton("Ajouter ");
      private final static JButton buttonOk7 = new JButton("Ajouter ");
      private final static JButton buttonOk8 = new JButton("Ajouter ");
      private final static JButton buttonall = new JButton("Ajouter ");

      
      
      protected final static JButton buttonOktpe = new JButton("Ajouter ");
      
      
	   private static gestion_chaine chain=new gestion_chaine();
	   private static gestion_profils profil=new gestion_profils();
	   private static gestion_produit prod=new gestion_produit();
	   private static gestion_article artic=new gestion_article();

	   private static gestion_tache tach=new gestion_tache();
	   private static gestion_user use=new gestion_user();
	   private static gestion_imp imp=new gestion_imp();
	   private static gestion_reference gestref=new gestion_reference();
	    private static gestion_codegener codegen=new gestion_codegener();
	    private static gestion_famille_article familart=new gestion_famille_article();
	    private static gestion_fournisseur frns=new gestion_fournisseur();
	      private static article arti;	
	  private static  poste1 pos;
	//  private static  produit produit;

	  private static zone zone;
	  private static tache tache;
	  private static  Chaine chai;
	  private static fournisseur forn;
	  private static  profils prof;

	  private static famille_article  famil_art;

	  private static codification_gener code_gener;
	  private static codification_refer code_refer;
	  private static ArrayList<String> list_produit_tr=new ArrayList<String>();
	  private static ArrayList<String> list_profil_tr=new ArrayList<String>();

	  private static ArrayList<String> list_tache_tr=new ArrayList<String>();
	  private static ArrayList<String> list_zone_tr=new ArrayList<String>();
	  private static ArrayList<String> list_poste_tr=new ArrayList<String>();
	  private static ArrayList<String> list_chaine_tr=new ArrayList<String>();
	  private static ArrayList<String> list_article_tr=new ArrayList<String>();
	  
	  private static ArrayList<String> list_article_tpe=new ArrayList<String>();
	  private static ArrayList<String> list_composant_tr=new ArrayList<String>();

	  private static ArrayList<String> list_article_tout=new ArrayList<String>();

	  
	  private static ArrayList<String> list_fourniss_tr=new ArrayList<String>();
	  private static ArrayList<String> list_gener_tr=new ArrayList<String>();
	  private static ArrayList<String> list_ref_tr=new ArrayList<String>();
	  private static ArrayList<String> list_famille_tr=new ArrayList<String>();
	  private static ArrayList<String> list_process=new ArrayList<String>();
	  private static ArrayList<String> list_article_tr_phone =new ArrayList<String>();
	private static ArrayList<String> list_article_tr_tablette=new ArrayList<String>();

	private static ArrayList<String> list_article_tr_tpe =new ArrayList<String>();

		static ArrayList<String> resultat=new ArrayList<String>();

		final static Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,
				15);
	 selectioncomb(){}
	 
	 
	 
	 @SuppressWarnings("unchecked")
		public static void selectarticle_etq(final jcombo article_comb,final JRadioButton enie, final JRadioButton autre, final JFrame frame,
				final String log){
		      resultat=use.selection_profils(log);

			
		 	list_article_tr=imp.select_article_code_etq("enie");
	//artic.select_article_code("enie");
			for(int i=0;i<list_article_tr.size();i++)
			   {  //Pour affecter une valeur de base de donn?es ? un Combobox 
				   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
				   i++;
			   }
			   
			   article_comb.setWide(true);
			  if(resultat.get(26).equals("1"))
			  article_comb.addItem(buttonOk1);
			   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
			   
			   enie.addActionListener(
			           new ActionListener() { 
			           public void actionPerformed(ActionEvent e) {
			        	   article_comb.removeAllItems();
			        	  
			        	   article_comb.addItem("---Selectionner un article-----");
			        	   list_article_tr=imp.select_article_code_etq("enie");
			    		   for(int i=0;i<list_article_tr.size();i++)
			    		   {
			    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
			    			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
			    			   i++;
			    		   }
			    		  if(resultat.get(26).equals("1"))
						   article_comb.addItem(buttonOk1);
						   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
			           }		           });
			 
			 autre.addActionListener(
			           new ActionListener() { 
			           public void actionPerformed(ActionEvent e) {
			        	   article_comb.removeAllItems();
			        	   article_comb.addItem("---Selectionner un article-----");
			        	   list_article_tr=imp.select_article_code_etq("autre");
			    		   for(int i=0;i<list_article_tr.size();i++)
			    		   {
			    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
			    			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
			    			   i++;
			    		   }
				    	  if(resultat.get(26).equals("1"))
						   article_comb.addItem(buttonOk1);
						   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
			           }});
			 
			 
			   article_comb.addActionListener(new ButtonComboBoxListener(frame, article_comb));
				buttonOk1.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent ae) {
				        if(existe_article==true){
				  	    	 arti.setVisible(true);
				  		   prv.add("article");
				  		  if( !selectioncomb.prv.contains("article")){prv.add("article");}
				  	    }else{
				  	    	 arti= new  article(log);
				  	    	existe_article=true;
				  
				  	    }
				 
				    frame.setState(Frame.ICONIFIED); 
				    article_comb.setSelectedIndex(0);
			  	 //   frame.setState(frame.ICONIFIED);
				
					 article_comb.setSelectedIndex(0);

				    arti.addWindowListener(new WindowAdapter(){
				        public void windowClosing(WindowEvent e){
				        	
				        	 frame.setState(Frame.NORMAL);
							 article_comb.setSelectedIndex(0);
				             article_comb.removeAllItems();

				            if(enie.isSelected()==true){
							        	   article_comb.removeAllItems();
							        	   article_comb.addItem("---Selectionner un article-----");
							        	   list_article_tr=imp.select_article_code_etq("enie");
							    		   for(int i=0;i<list_article_tr.size();i++)
							    		   {
							    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
							    			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
							    			   i++;
							    		   }
							    		   
							    		   article_comb.setWide(true);
										   article_comb.addItem(buttonOk1);
										   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
									         
							           }	
				            else if(autre.isSelected()==true){
							        	   article_comb.removeAllItems();
							        	   article_comb.addItem("---Selectionner un article-----");
							        	   list_article_tr=imp.select_article_code_etq("autre");

							    		   for(int i=0;i<list_article_tr.size();i++)
							    		   {
							    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
							    			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
							    			   i++;
							    		   }
							    		   article_comb.setWide(true);
										   article_comb.addItem(buttonOk1);
										   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
										  
							           }
				        	 frame.setState(Frame.NORMAL);


			        }});
			    }});
			   }
	 
	 @SuppressWarnings("unchecked")
     public static void select_composant(final jcombo composant_comb, final JFrame frame,
             final String log){
           resultat=use.selection_profils(log);

         
         list_composant_tr=imp.select_article_composant();
 //artic.select_article_code("enie");
         for(int i=0;i<list_composant_tr.size();i++)
            {  //Pour affecter une valeur de base de donn?es ? un Combobox 
             composant_comb.addItem(list_composant_tr.get(i)+" "+list_composant_tr.get(i+1));
                i++;
            }
            
         composant_comb.setWide(true);
           if(resultat.get(26).equals("1"))
               composant_comb.addItem(buttonOk1);
           composant_comb.setRenderer(new ButtonComboBoxRenderer()); 
            
           
          
          
          composant_comb.addActionListener(new ButtonComboBoxListener(frame, composant_comb));
             buttonOk1.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent ae) {
                     if(existe_article==true){
                          arti.setVisible(true);
                        prv.add("article");
                       if( !selectioncomb.prv.contains("article")){prv.add("article");}
                     }else{
                          arti= new  article(log);
                         existe_article=true;
               
                     }
              
                 frame.setState(Frame.ICONIFIED); 
                 composant_comb.setSelectedIndex(0);
              //   frame.setState(frame.ICONIFIED);
             
                 composant_comb.setSelectedIndex(0);

                 arti.addWindowListener(new WindowAdapter(){
                     public void windowClosing(WindowEvent e){
                         
                          frame.setState(Frame.NORMAL);
                          composant_comb.setSelectedIndex(0);
                          composant_comb.removeAllItems();

                       
                          frame.setState(Frame.NORMAL);


                 }});
             }});
            }
	 
	 
	 
	 public static void selectarticle_tout(final jcombo article_comb, final JFrame frame,final String log){
		      resultat=use.selection_profils(log);
		 	list_article_tout=imp.select_article_tout();
	          //artic.select_article_code("enie");
			for(int i=0;i<list_article_tout.size();i++)
			   {  //Pour affecter une valeur de base de donn?es ? un Combobox 
				   article_comb.addItem(list_article_tout.get(i)+" "+list_article_tout.get(i+1));
				   i++;
			   }
			   
			   article_comb.setWide(true);
			  if(resultat.get(26).equals("1"))
			   article_comb.addItem(buttonall);
			   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
			   article_comb.addActionListener(new ButtonComboBoxListener(frame, article_comb));
			   buttonall.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent ae) {
				        if(existe_article==true){
				  	    	 arti.setVisible(true);
				  		     prv.add("article");
				  		  if( !selectioncomb.prv.contains("article")){prv.add("article");}
				  	    }else{
				  	    	 arti= new  article(log);
				  	    	existe_article=true;
				  
				  	    }
				 
				    frame.setState(Frame.ICONIFIED); 
				    article_comb.setSelectedIndex(0);
			  	 //   frame.setState(frame.ICONIFIED);
				
				    arti.addWindowListener(new WindowAdapter(){
				        public void windowClosing(WindowEvent e){
				       	 frame.setState(Frame.NORMAL);
						 article_comb.setSelectedIndex(0);
			             article_comb.removeAllItems();
                         article_comb.addItem("---Selectionner un article-----");
							        	   list_article_tout=imp.select_article_tout();
							    		   for(int i=0;i<list_article_tout.size();i++)
							    		   {
							    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
							    			   article_comb.addItem(list_article_tout.get(i)+" "+list_article_tout.get(i+1));
							    			   i++;
							    		   }
							    		   
							    		   article_comb.setWide(true);
										   article_comb.addItem(buttonOk1);
										   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
									         
							          
				      
				        	 frame.setState(Frame.NORMAL);


			        }});
			    }});
			   }
	 
	 
	 
	 public static void selectarticle_etqphone(final jcombo article_comb, final JFrame frame,
				final String log){
		      resultat=use.selection_profils(log);
		 	list_article_tr_phone=imp.select_article_code_etqphone();
	          //artic.select_article_code("enie");
			for(int i=0;i<list_article_tr_phone.size();i++)
			   {  //Pour affecter une valeur de base de donn?es ? un Combobox 
				   article_comb.addItem(list_article_tr_phone.get(i)+" "+list_article_tr_phone.get(i+1));
				   i++;
			   }
			   
			   article_comb.setWide(true);
			  if(resultat.get(26).equals("1"))
			   article_comb.addItem(buttonOk1);
			   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
		
			 
		
			        	
			         
			 
			   article_comb.addActionListener(new ButtonComboBoxListener(frame, article_comb));
				buttonOk1.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent ae) {
				        if(existe_article==true){
				  	    	 arti.setVisible(true);
				  		     prv.add("article");
				  		  if( !selectioncomb.prv.contains("article")){prv.add("article");}
				  	    }else{
				  	    	 arti= new  article(log);
				  	    	existe_article=true;
				  
				  	    }
				 
				    frame.setState(Frame.ICONIFIED); 
				    article_comb.setSelectedIndex(0);
			  	 //   frame.setState(frame.ICONIFIED);
				
				    arti.addWindowListener(new WindowAdapter(){
				        public void windowClosing(WindowEvent e){
				       	 frame.setState(Frame.NORMAL);
						 article_comb.setSelectedIndex(0);
			             article_comb.removeAllItems();
                         article_comb.addItem("---Selectionner un article-----");
							        	   list_article_tr=imp.select_article_code_etqphone();
							    		   for(int i=0;i<list_article_tr.size();i++)
							    		   {
							    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
							    			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
							    			   i++;
							    		   }
							    		   
							    		   article_comb.setWide(true);
										   article_comb.addItem(buttonOk1);
										   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
									         
							          
				      
				        	 frame.setState(Frame.NORMAL);


			        }});
			    }});
			   }
	 
	 
	 
	 public static void selectarticle_tpe(final jcombo article_comb, final JFrame frame,
				final String log){
		      resultat=use.selection_profils(log);
		      list_article_tr_tpe=imp.select_article_code_tpe();
	          //artic.select_article_code("enie");
			for(int i=0;i<list_article_tr_tpe.size();i++)
			   {  //Pour affecter une valeur de base de donn?es ? un Combobox 
				   article_comb.addItem(list_article_tr_tpe.get(i)+" "+list_article_tr_tpe.get(i+1));
				   i++;
			   }
			   
			   article_comb.setWide(true);
			  if(resultat.get(26).equals("1"))
			   article_comb.addItem(buttonOktpe);
			   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
		
			 
		
			        	
			         
			 
			   article_comb.addActionListener(new ButtonComboBoxListener(frame, article_comb));
			   buttonOktpe.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent ae) {
				        if(existe_article==true){
				  	    	 arti.setVisible(true);
				  		     prv.add("article");
				  		  if( !selectioncomb.prv.contains("article")){prv.add("article");}
				  	    }else{
				  	    	 arti= new  article(log);
				  	    	existe_article=true;
				  
				  	    }
				 
				    frame.setState(Frame.ICONIFIED); 
				    article_comb.setSelectedIndex(0);
			  	 //   frame.setState(frame.ICONIFIED);
				
				    arti.addWindowListener(new WindowAdapter(){
				        public void windowClosing(WindowEvent e){
				       	 frame.setState(Frame.NORMAL);
						 article_comb.setSelectedIndex(0);
			             article_comb.removeAllItems();
                      article_comb.addItem("---Selectionner un article-----");
                      list_article_tpe=imp.select_article_code_tpe();
							    		   for(int i=0;i<list_article_tpe.size();i++)
							    		   {
							    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
							    			   article_comb.addItem(list_article_tpe.get(i)+" "+list_article_tpe.get(i+1));
							    			   i++;
							    		   }
							    		   
							    		   article_comb.setWide(true);
										   article_comb.addItem(buttonOktpe);
										   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
									         
							          
				      
				        	 frame.setState(Frame.NORMAL);


			        }});
			    }});
			   }


	public static void selectarticle_tablette(final jcombo article_comb, final JFrame frame,
										 final String log){
		resultat=use.selection_profils(log);
		list_article_tr_tablette=imp.select_article_code_tablette();
		//artic.select_article_code("enie");
		for(int i=0;i<list_article_tr_tablette.size();i++)
		{  //Pour affecter une valeur de base de donn?es ? un Combobox
			article_comb.addItem(list_article_tr_tablette.get(i)+" "+list_article_tr_tablette.get(i+1));
			i++;
		}

		article_comb.setWide(true);
		if(resultat.get(26).equals("1"))
			article_comb.addItem(buttonOktpe);
		article_comb.setRenderer(new ButtonComboBoxRenderer());






		article_comb.addActionListener(new ButtonComboBoxListener(frame, article_comb));
		buttonOktpe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(existe_article==true){
					arti.setVisible(true);
					prv.add("article");
					if( !selectioncomb.prv.contains("article")){prv.add("article");}
				}else{
					arti= new  article(log);
					existe_article=true;

				}

				frame.setState(Frame.ICONIFIED);
				article_comb.setSelectedIndex(0);
				//   frame.setState(frame.ICONIFIED);

				arti.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						frame.setState(Frame.NORMAL);
						article_comb.setSelectedIndex(0);
						article_comb.removeAllItems();
						article_comb.addItem("---Selectionner un article-----");
						list_article_tpe=imp.select_article_code_tablette();
						for(int i=0;i<list_article_tr_tablette.size();i++)
						{
							//Pour affecter une valeur de base de donn?es ? un Combobox
							article_comb.addItem(list_article_tr_tablette.get(i)+" "+list_article_tr_tablette.get(i+1));
							i++;
						}

						article_comb.setWide(true);
						article_comb.addItem(buttonOktpe);
						article_comb.setRenderer(new ButtonComboBoxRenderer());



						frame.setState(Frame.NORMAL);


					}});
			}});
	}





	public static void selectprocess(final jcombo process_comb ,final JFrame frame ,final String log){
	   //   resultat=prod.select_profils();

	// chaine_comb.addItem("--S?lectionner une Chaine--");
		
		  // fourniss_comb.addItem("---Selectionner un fournisseur-----");
		list_process=prod.select_profils();
		   for(int i=0;i<list_process.size();i++)
		   {
		          //Pour affecter une valeur de base de donn?es ? un Combobox 
			   process_comb.addItem(list_process.get(i));
			   
		   }
		   process_comb.setWide(true);
		 
	}

	public static void selectarticle_process(final Java2sAutoComboBox article_comb,final String enie, final JFrame frame,
			final String log, final List<Object> list_a){
	       resultat=use.selection_profils(log);
	 	   list_article_tr=artic.select_article_code(enie);
		   for(int i=0;i<list_article_tr.size();i++)
		   {  //Pour affecter une valeur de base de donn?es ? un Combobox 
			   list_a.add(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
			   i++;
		   }
		   
		  // article_comb.setWide(true);
		  if(resultat.get(26).equals("1"))
			  list_a.add("                Ajouter");
		  
		  article_comb.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent ae) {
			   	 if(article_comb.getSelectedItem().toString().trim().equals("Ajouter")){
			        if(existe_article==true){
			  	    	 arti.setVisible(true);
			  		   prv.add("article");
			  		  if( !selectioncomb.prv.contains("article")){prv.add("article");}
			  	    }else{
			  	    	 arti= new  article(log);
			  	    	existe_article=true;
			  
			  	    }
			 
			    frame.setState(Frame.ICONIFIED); 
			    article_comb.setSelectedIndex(0);
		  	 //   frame.setState(frame.ICONIFIED);
			
				 article_comb.setSelectedIndex(0);

			    arti.addWindowListener(new WindowAdapter(){
			        public void windowClosing(WindowEvent e){
			        	
			        	 frame.setState(Frame.NORMAL);
						 article_comb.setSelectedIndex(0);

						 list_a.clear();
						 list_a.add("---Selectionner un article-----");
						        	   list_article_tr=artic.select_article_code("enie");
						    		   for(int i=0;i<list_article_tr.size();i++)
						    		   {
						    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
						    			   list_a.add(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
						    			   i++;
						    		   }
						    		   
						    		 //  article_comb.setWide(true);
						    		   
						    		      list_a.add("                  Ajouter");
				 					  	 article_comb.setDataList(list_a);
				 					  	article_comb.setSelectedIndex(0);
				 					  	  
			        	 frame.setState(Frame.NORMAL);
		        }});}
		    }});
		   }
	@SuppressWarnings("unchecked")
	public static void selectarticle(final jcombo article_comb,final JRadioButton enie, final JRadioButton autre, final JFrame frame,
			final String log){
	      resultat=use.selection_profils(log);

		
	 	list_article_tr=imp.select_article_code("enie");
//artic.select_article_code("enie");
		for(int i=0;i<list_article_tr.size();i++)
		   {  //Pour affecter une valeur de base de donn?es ? un Combobox 
			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
			   i++;
		   }
		   
		   article_comb.setWide(true);
		  if(resultat.get(26).equals("1"))
		  article_comb.addItem(buttonOk1);
		   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
		   
		   enie.addActionListener(
		           new ActionListener() { 
		           public void actionPerformed(ActionEvent e) {
		        	   article_comb.removeAllItems();
		        	  
		        	   article_comb.addItem("---Selectionner un article-----");
		        	   list_article_tr=artic.select_article_code("enie");
		    		   for(int i=0;i<list_article_tr.size();i++)
		    		   {
		    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
		    			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
		    			   i++;
		    		   }
		    		  if(resultat.get(26).equals("1"))
					   article_comb.addItem(buttonOk1);
					   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
		           }		           });
		 
		 autre.addActionListener(
		           new ActionListener() { 
		           public void actionPerformed(ActionEvent e) {
		        	   article_comb.removeAllItems();
		        	   article_comb.addItem("---Selectionner un article-----");
		        	   list_article_tr=artic.select_article_code("autre");
		    		   for(int i=0;i<list_article_tr.size();i++)
		    		   {
		    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
		    			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
		    			   i++;
		    		   }
			    	  if(resultat.get(26).equals("1"))
					   article_comb.addItem(buttonOk1);
					   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
		           }});
		 
		 
		   article_comb.addActionListener(new ButtonComboBoxListener(frame, article_comb));
			buttonOk1.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent ae) {
			        if(existe_article==true){
			  	    	 arti.setVisible(true);
			  		   prv.add("article");
			  		  if( !selectioncomb.prv.contains("article")){prv.add("article");}
			  	    }else{
			  	    	 arti= new  article(log);
			  	    	existe_article=true;
			  
			  	    }
			 
			    frame.setState(Frame.ICONIFIED); 
			    article_comb.setSelectedIndex(0);
		  	 //   frame.setState(frame.ICONIFIED);
			
				 article_comb.setSelectedIndex(0);

			    arti.addWindowListener(new WindowAdapter(){
			        public void windowClosing(WindowEvent e){
			        	
			        	 frame.setState(Frame.NORMAL);
						 article_comb.setSelectedIndex(0);
			            article_comb.removeAllItems();

			            if(enie.isSelected()==true){
						        	   article_comb.removeAllItems();
						        	   article_comb.addItem("---Selectionner un article-----");
						        	   list_article_tr=artic.select_article_code("enie");
						    		   for(int i=0;i<list_article_tr.size();i++)
						    		   {
						    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
						    			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
						    			   i++;
						    		   }
						    		   
						    		   article_comb.setWide(true);
									   article_comb.addItem(buttonOk1);
									   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
								         
						           }	
			            else if(autre.isSelected()==true){
						        	   article_comb.removeAllItems();
						        	   article_comb.addItem("---Selectionner un article-----");
						        	   list_article_tr=artic.select_article_code("autre");

						    		   for(int i=0;i<list_article_tr.size();i++)
						    		   {
						    		          //Pour affecter une valeur de base de donn?es ? un Combobox 
						    			   article_comb.addItem(list_article_tr.get(i)+" "+list_article_tr.get(i+1));
						    			   i++;
						    		   }
						    		   article_comb.setWide(true);
									   article_comb.addItem(buttonOk1);
									   article_comb.setRenderer(new ButtonComboBoxRenderer()); 
									  
						           }
			        	 frame.setState(Frame.NORMAL);


		        }});
		    }});
		   }


		@SuppressWarnings("unchecked")
		public static void selectfourniss(final jcombo fourniss_comb ,final JFrame frame ,final String log){
		      resultat=use.selection_profils(log);

		// chaine_comb.addItem("--S?lectionner une Chaine--");
			
			  // fourniss_comb.addItem("---Selectionner un fournisseur-----");
		  	list_fourniss_tr=frns.select_fournisseur_code();
			   for(int i=0;i<list_fourniss_tr.size();i++)
			   {
			          //Pour affecter une valeur de base de donn?es ? un Combobox 
				 fourniss_comb.addItem(list_fourniss_tr.get(i)+" "+list_fourniss_tr.get(i+1));
				   i++;
			   }
			 fourniss_comb.setWide(true);
			 
	          if(resultat.get(24).equals("1"))
			 fourniss_comb.addItem(buttonOk4);
			 fourniss_comb.setRenderer(new ButtonComboBoxRenderer()); 
			 fourniss_comb.addActionListener(new ButtonComboBoxListener(frame, fourniss_comb));
		 
		  buttonOk4.addActionListener(new ActionListener() {
		  	    public void actionPerformed(ActionEvent ae) {
			
				fourniss_comb.setSelectedIndex(0);
		  	    frame.setState(Frame.ICONIFIED);
		  	    if(existe_fournisseur==true){
		  	    	 forn.setVisible(true);
		  		 //   prv.add("fournisseur");
		  		  if( !selectioncomb.prv.contains("fournisseur")){prv.add("fournisseur");}
		  	    }else{
		  	    	 forn= new  fournisseur(log);
		  	    	existe_fournisseur=true;
		  
		  	    }
			  	fourniss_comb.setSelectedIndex(0);

		  	    forn.addWindowListener(new WindowAdapter(){
		  	        public void windowClosing(WindowEvent e){
		  	        	
		  	        	fourniss_comb.removeAllItems();
		  	        	fourniss_comb.addItem("---Selectionner un fournisseur-----");
		  				list_fourniss_tr=frns.select_fournisseur_code();

		  	        	 for(int i=0;i<list_fourniss_tr.size();i++)
		  			   {
		  			          //Pour affecter une valeur de base de donn?es ? un Combobox 
		  				 fourniss_comb.addItem(list_fourniss_tr.get(i)+" "+list_fourniss_tr.get(i+1));
		  				   i++;
		  			   }
		  	      frame.setState(Frame.NORMAL);	
		  	    fourniss_comb.addItem(buttonOk4);
		  	  fourniss_comb.setRenderer(new ButtonComboBoxRenderer());

		  	        }});
		  	    }});
		}

		@SuppressWarnings("unchecked")
		public static void selectrefer(final jcombo ref_comb ,final JFrame frame ,final String log){
		      resultat=use.selection_profils(log);

			// chaine_comb.addItem("--S?lectionner une Chaine--");
				
				  // fourniss_comb.addItem("---Selectionner un fournisseur-----");
			 // 	ref_comb.addItem("---Selectionner une r?f?rence-----");
				list_ref_tr=gestref.select_reference_code();

			   for(int i=0;i<list_ref_tr.size();i++)
			   {
			          //Pour affecter une valeur de base de donn?es ? un Combobox 
				   ref_comb.addItem(list_ref_tr.get(i)+" "+list_ref_tr.get(i+1));
				   i++;
			   }
			   ref_comb.setWide(true);
			   
		       if(resultat.get(6).equals("1"))
			    ref_comb.addItem(buttonOk6);
			   ref_comb.setRenderer(new ButtonComboBoxRenderer()); 
				   ref_comb.addActionListener(new ButtonComboBoxListener(frame, ref_comb));
			 
			  buttonOk6.addActionListener(new ActionListener() {
			  	    public void actionPerformed(ActionEvent ae) {
			
					ref_comb.setSelectedIndex(0);
			  	    frame.setState(Frame.ICONIFIED);
			  	    if(existe_refer==true){
			  	    	
			  	    	code_refer.setVisible(true);
			  	    	 if( !selectioncomb.prv.contains("coderef")){prv.add("coderef");}
			  	    	//prv.add("coderef");
			  	    }else{
			  	    	 code_refer= new  codification_refer(log); 
			  	    	existe_refer=true;
			  	    }
				  	ref_comb.setSelectedIndex(0);

			    	  code_refer.addWindowListener(new WindowAdapter(){
			  	        public void windowClosing(WindowEvent e){
			  	
			  	        	ref_comb.removeAllItems();
			  	        	ref_comb.addItem("---Selectionner une r?f?rence-----");
			  	        	list_ref_tr=gestref.select_reference_code();

			  	        	 for(int i=0;i<list_ref_tr.size();i++)
			  			   {
			  			          //Pour affecter une valeur de base de donn?es ? un Combobox 
			  				   ref_comb.addItem(list_ref_tr.get(i)+" "+list_ref_tr.get(i+1));
			  				   i++;
			  			   }
			  	      frame.setState(Frame.NORMAL);	
			  	    ref_comb.addItem(buttonOk6);
			  	  ref_comb.setRenderer(new ButtonComboBoxRenderer());

			  	        }});
			  	    }});
				
			}
	@SuppressWarnings("unchecked")
	public static void selectgener(final jcombo codegener_comb ,final JFrame frame ,final String log){
	      resultat=use.selection_profils(log);

			//chaine_comb.addItem("--S?lectionner une Chaine--");
				
				  // fourniss_comb.addItem("---Selectionner un fournisseur-----");
      	list_gener_tr=codegen.select_famille_code();

		 for(int i=0;i<list_gener_tr.size();i++)
	  	   {
	  	          //Pour affecter une valeur de base de donn?es ? un Combobox 
	  		 codegener_comb.addItem(list_gener_tr.get(i)+" "+list_gener_tr.get(i+1));
	  		   i++;
	  	   }
		 codegener_comb.setWide(true);
		 
         if(resultat.get(7).equals("1"))
		 codegener_comb.addItem(buttonOk7);
		 codegener_comb.setRenderer(new ButtonComboBoxRenderer()); 
		 codegener_comb.addActionListener(new ButtonComboBoxListener(frame, codegener_comb));
			 
			  buttonOk7.addActionListener(new ActionListener() {
			  	    public void actionPerformed(ActionEvent ae) {
			
					codegener_comb.setSelectedIndex(0);
			  	    frame.setState(Frame.ICONIFIED);
			  	    if(existe_gener==true){
			  	      code_gener.setVisible(true);	
			  	    if( !selectioncomb.prv.contains("codegener")){prv.add("codegener");}
			  	     // prv.add("codegener");
			  	    	
			  	    }else{ 
			  	      code_gener= new  codification_gener(log); 
			  	    existe_gener=true;
			  	    }
				  	codegener_comb.setSelectedIndex(0);

			  	    code_gener.addWindowListener(new WindowAdapter(){
			  	        public void windowClosing(WindowEvent e){
			
			  	        	codegener_comb.removeAllItems();
			  	        	codegener_comb.addItem("---Selectionner une famille article-----");
			  	        	list_gener_tr=codegen.select_famille_code();

			  	        	 for(int i=0;i<list_gener_tr.size();i++)
			  		  	   {
			  		  	          //Pour affecter une valeur de base de donn?es ? un Combobox 
			  		  		 codegener_comb.addItem(list_gener_tr.get(i)+" "+list_gener_tr.get(i+1));
			  		  		   i++;
			  		  	   }
			  	      frame.setState(Frame.NORMAL);	
			  	    codegener_comb.addItem(buttonOk7);
			  	  codegener_comb.setRenderer(new ButtonComboBoxRenderer());

			  	        }});
			  	    }});
				
			}
		@SuppressWarnings("unchecked")
		public static void selectfamille(final jcombo famille_comb ,final JFrame frame ,final String log){
		      resultat=use.selection_profils(log);

			// chaine_comb.addItem("--S?lectionner une Chaine--");
				
				  // fourniss_comb.addItem("---Selectionner un fournisseur-----");
			list_famille_tr=familart.select_artfamille_code();
			for(int i=0;i<list_famille_tr.size();i++)
			   {
			          //Pour affecter une valeur de base de donn?es ? un Combobox 
				  famille_comb.addItem(list_famille_tr.get(i)+" "+list_famille_tr.get(i+1));
				   i++;
			   }
				   famille_comb.setWide(true);
				   
			       if(resultat.get(9).equals("1"))
				   famille_comb.addItem(buttonOk5);
				   famille_comb.setRenderer(new ButtonComboBoxRenderer()); 
				   famille_comb.addActionListener(new ButtonComboBoxListener(frame, famille_comb));
			 
			  buttonOk5.addActionListener(new ActionListener() {
			  	    public void actionPerformed(ActionEvent ae) {
					famille_comb.setSelectedIndex(0);
			  	    frame.setState(Frame.ICONIFIED);
			  	    
			  	    if(existe_famille==true)
			  	    {
			  	    	famil_art.setVisible(true);
			  	    	//prv.add("famille");
			  	      if( !selectioncomb.prv.contains("famille")){prv.add("famille");}
			  	    	
			  	    }else{
			  	    	famil_art= new  famille_article(log); 	
			  	    	existe_famille=true;
			  	    }
			  	    
					
			  	    famil_art.addWindowListener(new WindowAdapter(){
			  	        public void windowClosing(WindowEvent e){
			  	   
			  	        	famille_comb.removeAllItems();
			  	        	famille_comb.addItem("---Selectionner un type article-----");
			  				list_famille_tr=familart.select_artfamille_code();

			  	        	for(int i=0;i<list_famille_tr.size();i++)
			 			   {
			 			          //Pour affecter une valeur de base de donn?es ? un Combobox 
			 				  famille_comb.addItem(list_famille_tr.get(i)+" "+list_famille_tr.get(i+1));
			 				   i++;
			 			   }
					  	      frame.setState(Frame.NORMAL);	

			  	    famille_comb.addItem(buttonOk5);
			  	 

			  	        }});
			  	    }});
				
			}
			
		
		@SuppressWarnings("unchecked")
		public static void selectchaine(final jcombo chaine_comb ,final JFrame frame ,final String log){
		      resultat=use.selection_profils(log);

			// chaine_comb.addItem("--S?lectionner une Chaine--");
			list_chaine_tr=chain.select_chaine_code();

			 for(int i=0;i<list_chaine_tr.size();i++)
			 {
			        //Pour affecter une valeur de base de donn?es ? un Combobox 
				   chaine_comb.addItem(list_chaine_tr.get(i)+" "+list_chaine_tr.get(i+1));
				   i++;
			 }
			 chaine_comb.setWide(true);
			 
	          if(resultat.get(16).equals("1"))
		     chaine_comb.addItem(buttonOk);
			 chaine_comb.setRenderer(new ButtonComboBoxRenderer()); 
			 chaine_comb.addActionListener(new ButtonComboBoxListener(frame, chaine_comb));

			 
			  buttonOk.addActionListener(new ActionListener() {
			  	    public void actionPerformed(ActionEvent ae) {	
			  	      frame.setState(Frame.ICONIFIED); 
			  	    	 if(existe_chaine==true)
					  	    {
					  	    	chai.setVisible(true);
					  	    	//prv.add("chaine");
					  	    	 if( !selectioncomb.prv.contains("chaine")){prv.add("chaine");}
					  	    	
					  	    }else{
					  	    	chai= new  Chaine(log); 	
					  	    	existe_chaine=true;
					  	    }
			  	    	 
				  	      chaine_comb.setSelectedIndex(0);
			  	    chai.addWindowListener(new WindowAdapter(){
			  	        public void windowClosing(WindowEvent e){
			  	         // code_jtext.setText("");
			  	          
					  	   
					  	    
			  	           chaine_comb.removeAllItems();
			  	          chaine_comb.addItem("--S?lectionner une Chaine--");
			  			list_chaine_tr=chain.select_chaine_code();

			  	          for(int i=0;i<list_chaine_tr.size();i++)
						   {
						          //Pour affecter une valeur de base de donn?es ? un Combobox 
							   chaine_comb.addItem(list_chaine_tr.get(i)+" "+list_chaine_tr.get(i+1));
							   i++;
						   }
				  	      frame.setState(Frame.NORMAL);	

			  	      chaine_comb.addItem(buttonOk);
			  	      chaine_comb.setRenderer(new ButtonComboBoxRenderer());


			  	        }});
			  	    }});
			}
		 @SuppressWarnings("unchecked")
			public static void selectposte(final jcombo poste_comb,final JFrame frame,final String log){
		      resultat=use.selection_profils(log);

			//  poste_comb.addItem("---Selectionner un Poste-----");
			list_poste_tr=imp.select_poste_code();

			 for(int i=0;i<list_poste_tr.size();i++)
			 {
			        //Pour affecter une valeur de base de donn?es ? un Combobox 
				  poste_comb.addItem(list_poste_tr.get(i)+" "+list_poste_tr.get(i+1));
				  i++;
			 }
			 
				  poste_comb.setWide(true);
				  
			  		if(resultat.get(14).equals("1"))
			      poste_comb.addItem(buttonOk2);
				  poste_comb.setRenderer(new ButtonComboBoxRenderer()); 
				  poste_comb.addActionListener(new ButtonComboBoxListener(frame, poste_comb));
					buttonOk2.addActionListener(new ActionListener() {
				  	    public void actionPerformed(ActionEvent ae) {
				  	    frame.setState(Frame.ICONIFIED); 
				  	 //   pos.setVisible(true);
						
						if(existe==true){pos.setVisible(true);
						if( !selectioncomb.prv.contains("poste")){prv.add("poste");}
						}
						else{pos=new poste1(log);
						existe=true;}
						  poste_comb.setSelectedIndex(0);

				  	    pos.addWindowListener(new WindowAdapter(){
				  	        public void windowClosing(WindowEvent e){
						
								poste_comb.removeAllItems();
								poste_comb.addItem("---Sélectionner un Poste-----");
								list_poste_tr=imp.select_poste_code();
					 			   for(int i=0;i<list_poste_tr.size();i++)
								   {
								          //Pour affecter une valeur de base de donn?es ? un Combobox 
									  poste_comb.addItem(list_poste_tr.get(i)+" "+list_poste_tr.get(i+1));
									   i++;
								   }
							  	      frame.setState(Frame.NORMAL);	

							  poste_comb.addItem(buttonOk2);
							  
				  	        }});
				  	    }});
			}
		 
		 
		 
		 
		 @SuppressWarnings("unchecked")
			public static void selectproduit(final jcombo produit_comb,final JFrame frame,final String log){
		      resultat=use.selection_profils(log);

			//  poste_comb.addItem("---Selectionner un Poste-----");
			list_produit_tr=prod.select_produit_code();

			 for(int i=0;i<list_produit_tr.size();i++)
			 {
			        //Pour affecter une valeur de base de donn?es ? un Combobox 
				  produit_comb.addItem(list_produit_tr.get(i)+" "+list_produit_tr.get(i+1));
				  i++;
			 }
			 		
			 produit_comb.setWide(true);
				  
			  		//if(resultat.get(14).equals("1"))
			  		//produit_comb.addItem(buttonOkprod);
			  		produit_comb.setRenderer(new ButtonComboBoxRenderer()); 
			  		produit_comb.addActionListener(new ButtonComboBoxListener(frame, produit_comb));
//					buttonOkprod.addActionListener(new ActionListener() {
//				  	    public void actionPerformed(ActionEvent ae) {
//				  	    frame.setState(Frame.ICONIFIED); 
//				  	 //   produit.setVisible(true);
//						
//						if(existe==true){produit.setVisible(true);
//						if( !selectioncomb.prv.contains("produit")){prv.add("produit");}
//						}
//						else{produit=new produit(log);
//						existe=true;}
//						produit_comb.setSelectedIndex(0);
//
//				  	    produit.addWindowListener(new WindowAdapter(){
//				  	        public void windowClosing(WindowEvent e){
//						
//				  	        	produit_comb.removeAllItems();
//				  	        	produit_comb.addItem("---S?lectionner un Produit-----");
//				  				list_produit_tr=prod.select_produit_code();
//					 			   for(int i=0;i<list_produit_tr.size();i++)
//								   {
//								          //Pour affecter une valeur de base de donn?es ? un Combobox 
//					 				  produit_comb.addItem(list_produit_tr.get(i)+" "+list_produit_tr.get(i+1));
//									   i++;
//								   }
//							  	      frame.setState(Frame.NORMAL);	
//
//							  	    produit_comb.addItem(buttonOkprod);
//							  
//				  	        }});
				  	   // }});
			}
		 
		 
		 @SuppressWarnings("unchecked")
			public static void selectprofil_prod(final jcombo profil_comb,final JFrame frame,final String log){
		      resultat=use.selection_profils(log);

			//  poste_comb.addItem("---Selectionner un Poste-----");
			list_profil_tr=prod.select_profil_code();

			 for(int i=0;i<list_profil_tr.size();i++)
			 {
			        //Pour affecter une valeur de base de donn?es ? un Combobox 
				 profil_comb.addItem(list_profil_tr.get(i));
				  i++;
			 }
			 
			 profil_comb.setWide(true);
				  
			  		if(resultat.get(14).equals("1"))
			  		//produit_comb.addItem(buttonOkprod);
			  			profil_comb.setRenderer(new ButtonComboBoxRenderer()); 
			  		profil_comb.addActionListener(new ButtonComboBoxListener(frame, profil_comb));
//					buttonOkprof.addActionListener(new ActionListener() {
//				  	    public void actionPerformed(ActionEvent ae) {
//				  	    frame.setState(Frame.ICONIFIED); 
//				  	 //   porofil.setVisible(true);
//						
//						if(existe==true){porofil.setVisible(true);
//						if( !selectioncomb.prv.contains("porofil")){prv.add("porofil");}
//						}
//						else{porofil=new porofil(log);
//						existe=true;}
//						profil_comb.setSelectedIndex(0);
//
//				  	    porofil.addWindowListener(new WindowAdapter(){
//				  	        public void windowClosing(WindowEvent e){
//						
//				  	        	profil_comb.removeAllItems();
//				  	        	profil_comb.addItem("---S?lectionner un Profil-----");
					             //list_profil_tr=prod.select_profil_code();
//					 			   for(int i=0;i<list_profil_tr.size();i++)
//								   {
//								          //Pour affecter une valeur de base de donn?es ? un Combobox 
//					 				  profil_comb.addItem(list_profil_tr.get(i));
//									   i++;
//								   }
//							  	      frame.setState(Frame.NORMAL);	
//
//							  	    profil_comb.addItem(buttonOkprod);
//							  
//				  	        }});
				  	   // }});
			}
		 
		public static void selectposte_tab(final Java2sAutoComboBox poste_comb,final JFrame frame,final String log,final Tableau tab,
				final List<Object> list_p){
		      resultat=use.selection_profils(log);

		//  poste_comb.addItem("---Selectionner un Poste-----");
		      
		      
		list_poste_tr=imp.select_poste_code();

		 for(int i=0;i<list_poste_tr.size();i++)
		 {
		        //Pour affecter une valeur de base de donn?es ? un Combobox 
			 list_p.add(list_poste_tr.get(i)+" "+list_poste_tr.get(i+1));
			  i++;
		 }
		 
			 // poste_comb.setWide(true);
		  		if(resultat.get(14).equals("1"))
		  			list_p.add("                  Ajouter");
			
		  		poste_comb.addActionListener(new ActionListener() {
			  	    public void actionPerformed(ActionEvent ae) {
			  	 	 if(poste_comb.getSelectedItem().toString().trim().equals("Ajouter")){
			  	    frame.setState(Frame.ICONIFIED); 
			  	 //   pos.setVisible(true);
					
					if(existe==true){pos.setVisible(true);
					 if( !selectioncomb.prv.contains("poste")){prv.add("poste");}
					}
					else{pos=new poste1(log);
					existe=true;}
					  poste_comb.setSelectedIndex(0);

			  	    pos.addWindowListener(new WindowAdapter(){
			  	        public void windowClosing(WindowEvent e){
							
			  	        	list_p.clear();
			  	        	list_p.add("---Sélectionner un Poste-----");
							list_poste_tr=imp.select_poste_code();
				 			   for(int i=0;i<list_poste_tr.size();i++)
							   {
							          //Pour affecter une valeur de base de donn?es ? un Combobox 
				 				  list_p.add(list_poste_tr.get(i)+" "+list_poste_tr.get(i+1));
								   i++;
							   }
				 			   
				 			   
				 			   list_p.add("                  Ajouter");
		 					  	 poste_comb.setDataList(list_p);
		 					    	poste_comb.setSelectedIndex(0);
		 					  	    int col=tab.table.getSelectedColumn();
		 					  	  int row=tab.table.getSelectedRow();
		 					  	  tab.table.setValueAt("---Sélectionner un Poste-----", row, col);
		 					  	
			  	        }});
			  	 	 }}});
		}
		 
//		 @SuppressWarnings("unchecked")
//			public static void selectposte_zone(final jcombo poste_comb,final JFrame frame,final String log,final Tableau tab){
//			//  poste_comb.addItem("---Selectionner un Poste-----");
//					list_poste_tr=imp.select_poste_code();
//
//			 for(int i=0;i<list_poste_tr.size();i++)
//			 {
//			        //Pour affecter une valeur de base de donn?es ? un Combobox 
//				  poste_comb.addItem(list_poste_tr.get(i)+" "+list_poste_tr.get(i+1));
//				  i++;
//			 }
//			 
//				  poste_comb.setWide(true);
//				  poste_comb.addItem(buttonOkzone);
//				  poste_comb.setRenderer(new ButtonComboBoxRenderer()); 
//				  
//				  poste_comb.addActionListener(new ButtonComboBoxListener(frame, poste_comb));
//
//				  buttonOkzone.addActionListener(new ActionListener() {
//				  	    public void actionPerformed(ActionEvent ae) {
//				  	    	
//				  	    frame.setState(Frame.ICONIFIED); 
//				  	    pos= new  Poste(log); 
//						varbool=false;
//				  	    pos.addWindowListener(new WindowAdapter(){
//				  	        public void windowClosing(WindowEvent e){
//				  	        	
//								varbool=true;
//
//								poste_comb.removeAllItems();
//								poste_comb.addItem("---S?lectionner un Poste-----");
//								list_poste_tr=imp.select_poste_code();
//					 			   for(int i=0;i<list_poste_tr.size();i++)
//								   {
//								          //Pour affecter une valeur de base de donn?es ? un Combobox 
//									  poste_comb.addItem(list_poste_tr.get(i)+" "+list_poste_tr.get(i+1));
//									   i++;
//								   }
//				  	          frame.setState(Frame.NORMAL);				  	   	
//							  poste_comb.addItem(buttonOkzone);
//							  poste_comb.setSelectedIndex(0);
//							
//							  tab.table.setValueAt(poste_comb.getSelectedItem(),tab.table.getSelectedRow() , 0);
//
//				  	        }});
//				  	    }});
//		}
		 
		 public static void selecttache_poste(final Java2sAutoComboBox tache_comb ,final JFrame frame ,final String log,final Tableau tab,final List<Object> list_t ){	
		 		      resultat=use.selection_profils(log);

		 				 list_tache_tr=tach.select_tache_code();
		 					// chaine_comb.addItem("--S?lectionner une Chaine--");
		 						 for(int i=0;i<list_tache_tr.size();i++)
		 				    	   {
		 				    	          //Pour affecter une valeur de base de donn?es ? un Combobox 
		 							list_t.add(list_tache_tr.get(i)+" "+list_tache_tr.get(i+1));
		 				    		   i++;
		 				    	   }
		 						 
		 						//tache_comb.setWide(true);
		 				      if(resultat.get(2).equals("1"))
		 				    	 list_t.add("                  Ajouter");
		 				      

		 				  	tache_comb.addActionListener(new ActionListener() {
		 					  	 public void actionPerformed(ActionEvent ae) {
		 					 System.out.println("gggg"+tache_comb.getSelectedItem().toString().trim()+"ffff");
		 					  		 if(tache_comb.getSelectedItem().toString().trim().equals("Ajouter")){
		 					  	  //  frame.setState(frame.ICONIFIED);
		 					  		if(existe_tache==true){tache.setVisible(true);
		 							 if( !selectioncomb.prv.contains("tache")){prv.add("tache");}
		 							}
		 							else{tache= new  tache(log);
		 							existe_tache=true;}
		 					  	     
		 					  		tache_comb.setSelectedIndex(0);


		 					  		tache.addWindowListener(new WindowAdapter(){
		 					  	        public void windowClosing(WindowEvent e){
		 							  	//frame.setState(frame.NORMAL);	
		 					  	   
		 					  	     // code_jtext.setText("");
		 					  	         list_t.clear();
		 					  	      list_t.add("--Sélectionner une Tache--");
		 								 list_tache_tr=tach.select_tache_code();

		 					  	        for(int i=0;i<list_tache_tr.size();i++)
		 						    	   {
		 						    	  //Pour affecter une valeur de base de donn?es ? un Combobox 
		 					  	        	list_t.add(list_tache_tr.get(i)+" "+list_tache_tr.get(i+1));
		 						    		   i++;
		 						    	   }
		 					  	     list_t.add("                  Ajouter");
		 					  	   tache_comb.setDataList(list_t);
		 					    	tache_comb.setSelectedIndex(0);
		 					  	    int col=tab.table.getSelectedColumn();
		 					  	  int row=tab.table.getSelectedRow();
		 					  	  tab.table.setValueAt("--Sélectionner une Tache--", row, col);
		 					  	        }});
		 					  		 }   }});
		 					}
		 


		 public static void selectzone_chaine1(final Java2sAutoComboBox zone_comb,final JFrame frame ,final String log,final Tableau tab,final List<Object> list_z ){	
			 
			   resultat=use.selection_profils(log);

				 list_zone_tr=use.select_zone_code();
					// chaine_comb.addItem("--S?lectionner une Chaine--");
						 for(int i=0;i<list_zone_tr.size();i++)
				    	   {
				    	          //Pour affecter une valeur de base de donn?es ? un Combobox 
							 list_z.add(list_zone_tr.get(i)+" "+list_zone_tr.get(i+1));
				    		   i++;
				    	   }
						 
					//	 zone_comb.setWide(true);
						
				        if(resultat.get(2).equals("1"))
				        	list_z.add("                Ajouter");
				        
		   zone_comb.addActionListener(new ActionListener() {
			  	 public void actionPerformed(ActionEvent ae) {
			  		 if(zone_comb.getSelectedItem().toString().trim().equals("Ajouter")){
			  		
			  	  //  frame.setState(frame.ICONIFIED);
			  		if(existe_zone==true){zone.setVisible(true);
					 if( !selectioncomb.prv.contains("zone")){prv.add("zone");}
					}
					else{zone= new zone(log);
					existe_zone=true;}
			  	 
			  	    zone.addWindowListener(new WindowAdapter(){
			  	        public void windowClosing(WindowEvent e){
					  	//frame.setState(frame.NORMAL);	
			  	      
			  	     // code_jtext.setText("");
			  	      
			  	     list_z.clear();
			  	   
		  	         list_z.add("--Sélectionner une Zone--");
		  	        
		  	    
						 list_zone_tr=use.select_zone_code();

			  	        for(int i=0;i<list_zone_tr.size();i++)
				    	   {
				    	  //Pour affecter une valeur de base de donn?es ? un Combobox 
			  	        	list_z.add(list_zone_tr.get(i)+" "+list_zone_tr.get(i+1));
				    		   i++;
				    	   }
			  	      if(resultat.get(2).equals("1"))
			  	    	list_z.add("                Ajouter");
			  	    zone_comb.setDataList(list_z);
			  	   zone_comb.setSelectedIndex(0);
			  	    int col=tab.table.getSelectedColumn();
			  	  int row=tab.table.getSelectedRow();
			  	  tab.table.setValueAt("--Sélectionner une Zone--", row, col);
			  	          }});   
			  	  }    }});
		   
		 }
		 
		 @SuppressWarnings("unchecked")
public static void selectzone_chaine(final Java2sAutoComboBox zone_comb ,final JFrame frame ,final String log,final Tableau tab,
		List<Object> list_z ){
//zone_comb = new jcombo(list){	
		      resultat=use.selection_profils(log);

				 list_zone_tr=use.select_zone_code();
					// chaine_comb.addItem("--S?lectionner une Chaine--");
						 for(int i=0;i<list_zone_tr.size();i++)
				    	   {
				    	          //Pour affecter une valeur de base de donn?es ? un Combobox 
							 list_z.add(list_zone_tr.get(i)+" "+list_zone_tr.get(i+1));
				    		   i++;
				    	   }
						 
					//	 zone_comb.setWide(true);
						
				        if(resultat.get(2).equals("1"))
						zone_comb.addItem(buttonOkzone);
				  	    zone_comb.setRenderer(new ButtonComboBoxRenderer());
				  	    zone_comb.addActionListener(new ButtonComboBoxListener(frame, zone_comb));
				  	    
				  	   buttonOkzone.addActionListener(new ActionListener() {
					  	 public void actionPerformed(ActionEvent ae) {
					  	  //  frame.setState(frame.ICONIFIED);
					  		if(existe_zone==true){zone.setVisible(true);
							 if( !selectioncomb.prv.contains("zone")){prv.add("zone");}
							}
							else{zone= new  zone(log);
							existe_zone=true;}
					  	     
					  	     zone_comb.setSelectedIndex(0);


					  	    zone.addWindowListener(new WindowAdapter(){
					  	        public void windowClosing(WindowEvent e){
							  	//frame.setState(frame.NORMAL);	
					  	   
					  	     // code_jtext.setText("");
					  	        zone_comb.removeAllItems();
					  	        zone_comb.addItem("--Sélectionner une Zone--");
								 list_zone_tr=use.select_zone_code();

					  	        for(int i=0;i<list_zone_tr.size();i++)
						    	   {
						    	  //Pour affecter une valeur de base de donn?es ? un Combobox 
						    	zone_comb.addItem(list_zone_tr.get(i)+" "+list_zone_tr.get(i+1));
						    		   i++;
						    	   }
					  	     zone_comb.addItem(buttonOkzone);
					  	     tab.table.setValueAt(zone_comb.getSelectedItem(),tab.table.getSelectedRow() , 0);
					  	   tab.table.setValueAt("",tab.table.getSelectedRow() , 1);
					  	        }});
					  	    }});
					}
		 
		 public static void selecttache(final jcombo tache_comb ,final JFrame frame ,final String log ){
		      resultat=use.selection_profils(log);
		      list_tache_tr=tach.select_tache_code();
				// chaine_comb.addItem("--S?lectionner une Chaine--");
					 for(int i=0;i<list_tache_tr.size();i++)
			    	   {
			    	          //Pour affecter une valeur de base de donn?es ? un Combobox 
						 tache_comb.addItem(list_tache_tr.get(i)+" "+list_tache_tr.get(i+1));
			    		   i++;
			    	   }
					 tache_comb.setWide(true);
				}
		 
		 @SuppressWarnings("unchecked")
		public static void selectzone(final jcombo zone_comb ,final JFrame frame ,final String log ){
		      resultat=use.selection_profils(log);
			 list_zone_tr=use.select_zone_code();
				// chaine_comb.addItem("--S?lectionner une Chaine--");
					 for(int i=0;i<list_zone_tr.size();i++)
			    	   {
			    	          //Pour affecter une valeur de base de donn?es ? un Combobox 
			    		   zone_comb.addItem(list_zone_tr.get(i)+" "+list_zone_tr.get(i+1));
			    		   i++;
			    	   }
					 zone_comb.setWide(true);
			          if(resultat.get(2).equals("1"))
					 zone_comb.addItem(buttonOk3);
			  	     zone_comb.setRenderer(new ButtonComboBoxRenderer());
			  	     zone_comb.addActionListener(new ButtonComboBoxListener(frame, zone_comb));
				     buttonOk3.addActionListener(new ActionListener() {
				  	 public void actionPerformed(ActionEvent ae) {
				  	  //  frame.setState(frame.ICONIFIED);
				  		if(existe_zone==true){zone.setVisible(true);
						 if( !selectioncomb.prv.contains("zone")){prv.add("zone");}
						}
						else{zone= new  zone(log);
						existe_zone=true;}
				  	     zone_comb.setSelectedIndex(0);

				  	    zone.addWindowListener(new WindowAdapter(){
				  	        public void windowClosing(WindowEvent e){
						  	//frame.setState(frame.NORMAL);	
		
				  	     // code_jtext.setText("");
				  	        zone_comb.removeAllItems();
				  	        zone_comb.addItem("--Sélectionner une Zone--");
							 list_zone_tr=use.select_zone_code();

				  	        for(int i=0;i<list_zone_tr.size();i++)
					    	   {
					    	  //Pour affecter une valeur de base de donn?es ? un Combobox 
					    	zone_comb.addItem(list_zone_tr.get(i)+" "+list_zone_tr.get(i+1));
					    		   i++;
					    	   }
				  	     zone_comb.addItem(buttonOk3);
				  	        }});
				  	    }});
				}
		 public static void windows( final JFrame frame, final String log){
			  
			 frame.addWindowListener(new WindowAdapter(){
		        public void windowClosing(WindowEvent e){
					//call the object of NewWindow and set visible true
		        	int reponse = JOptionPane.showConfirmDialog(
		                      null, "Voulez-vous vraiment quitter cette fenétre ?",
		                      "Confirmation",
		                      JOptionPane.YES_NO_OPTION,
		                      JOptionPane.QUESTION_MESSAGE);
		 
		        	if (reponse==JOptionPane.YES_OPTION){
		        		// System.out.println(prv.size()-1+"avant"+prv.get(prv.size()-1)+"    "+prv.toString());
		        		 prv.remove(prv.size()-1);
		        		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						 frame.dispose();
			            
//			             if(prv.get(prv.size()-1).equals( "article")){
//			            	  final article art=new article(log);
//			            	  art.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("chaine")){
//			            	  final Chaine cha=new Chaine(log);
//			            	  cha.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("changement")){
//			            	  final changement_chaine chang=new changement_chaine(log);
//			            	  chang.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("codegener")){
//			            	  final codification_gener gener=new codification_gener(log);
//			            	  gener.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("coderefer")){
//			            	  final codification_refer refer=new codification_refer(log);
//			            	  refer.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("etiquette")){
//			            	  final etiquette etq=new etiquette(log);
//			            	  etq.setVisible(true);
//			             }
//			             
//			             else if(prv.get(prv.size()-1).equals("famille")){
//			            	  final famille_article fam=new famille_article(log);
//			            	  fam.setVisible(true);
//			             }
//			             
//			             else if(prv.get(prv.size()-1).equals("fournisseur")){
//			            	  final fournisseur fourn=new fournisseur(log);
//			            	  fourn.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("imprime")){
//			            	  final imprime imp=new imprime(log);
//			            	  imp.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("list_fiche")){
//			            	  final list_fiche fiche=new list_fiche(log);
//			            	  fiche.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("list_fourniss")){
//			            	  final list_fourniss fourni=new list_fourniss(log);
//			            	  fourni.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("list_personelle")){
//			            	  final list_personelle perso=new list_personelle(log);
//			            	  perso.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("list_poste")){
//			            	  final list_poste lposte=new list_poste(log);
//			            	  lposte.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("list_user")){
//			            	  final list_user luser=new list_user(log);
//			            	  luser.setVisible(true);
//			             }
//			              
//			             else if(prv.get(prv.size()-1).equals("recap_codification")){
//			            	  final recap_codification recapc=new recap_codification(log);
//			            	  recapc.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("recap")){
//			            	  final recap recap=new recap(log);
//			            	  recap.setVisible(true);
//			             }
//			             
//			             
//			             
//			             
//			             
//			             else if(prv.get(prv.size()-1).equals("zone")){
//			            	  final zone zone=new zone(log);
//			            	  zone.setVisible(true);
//			             }
//			             else if(prv.get(prv.size()-1).equals("poste")){
//			            	  final Poste pos=new Poste(log);
//			            	  pos.setVisible(true);
//			             }
						// System.out.println(prv.size()-1+"fffffffffffffff"+prv.get(prv.size()-1)+"    "+prv.toString());
						 if(prv.get(prv.size()-1)=="list_personelle"){
			            	// System.out.println("fffffffffffffff");
			            	 list_personelle fr=new list_personelle(log);	
			            	 fr.setVisible(true);
			             }
						 
						 if(prv.get(prv.size()-1)=="menu"){
			            	// System.out.println("fffffffffffffff");
			            	  menu fr=new menu(log);	
			            	 fr.setVisible(true);
			             }
			             
//					zone fra = new zone("7");
//					fra.setVisible(true);
					//set default close operation
			
					 
		        	}
		        	else if (reponse==JOptionPane.NO_OPTION){
		           	   frame.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);	  }
		        	else{frame.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);}
				}
			});
		}
//	public static void windows(final JFrame frame, final String log,final String laf){
//		
//		
//		frame.addWindowListener(new WindowAdapter(){
//	        public void windowClosing(WindowEvent e){
//	        	
//	              int reponse = JOptionPane.showConfirmDialog(
//	                                   null, "Voulez-vous vraiment quitter cette fen?tre ?",
//	                                   "Confirmation",
//	                                   JOptionPane.YES_NO_OPTION,
//	                                   JOptionPane.QUESTION_MESSAGE);
//	              if (reponse==JOptionPane.YES_OPTION){
//	            	  frame.dispose(); 
//		            	 final menu fr=new menu(log);
//		                  fr.setVisible(true);
//		                
//		            	 System.out.println(prv.size()-1+"fffffffffffffff"+prv.toString());
//	             prv.remove(prv.size()-1);
//	             //frame.dispose();
//	             System.out.println(prv.size()-1+"fffffffffffffff"+prv.toString());
//	             menu frp=new menu(log);	
//            	 frp.setVisible(true);
//	             if(prv.get(prv.size()-1) .equals( "article")){
//	            	  final article art=new article(log);
//	            	  art.setVisible(true);
//	             }
//	             else if(prv.get(prv.size()-1).equals("chaine")){
//	            	  final Chaine cha=new Chaine(log);
//	            	  cha.setVisible(true);
//	             }
//	             if(prv.get(prv.size()-1).equals( "menu")){
//	            	 System.out.println("hhhhhhhhhhhhhhhhhh");
//	            	  //menu fr=new menu(log);	
//	            	 //fr.setVisible(true);
//	             }
//	             
////	              System.out.println("hhhhhhhhhhhhhhhhhhhhhh"+varbool);
////	              final menu fr=new menu(log);
////	               if(varbool==true){ 
////	            	   System.out.println("dddddddddddddddddddddd");
////	            	 
////	                   fr.setVisible(true);
////	                 }
////	                    else{ 
////	                    	System.out.println("eqrgrfghrthrthrthdd");
////	                    	 frame.dispose();
////	                    	 frame.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
////	                        }             
////	               System.out.println("dfdfffffffgffffffffff");
////	              frame.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
//	              }
//	               
//	           	   
//	           	   if (reponse==JOptionPane.NO_OPTION){
//	           	   frame.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);	                   }
//	        }
//	});
//	}	

	
	
		 @SuppressWarnings("unchecked")
			public static void selectprofils(final jcombo profils_comb ,final JFrame frame ,final String log){
				
				// chaine_comb.addItem("--S?lectionner une Chaine--");
			 list_profil=profil.select_profils();

				 for(int i=0;i<list_profil.size();i++)
				 {
				        //Pour affecter une valeur de base de donn?es ? un Combobox 
					 profils_comb.addItem(list_profil.get(i)+" "+list_profil.get(i+1));
					   i++;
				 }
				 profils_comb.setWide(true);
				 profils_comb.addItem(buttonOk8);
				 profils_comb.setRenderer(new ButtonComboBoxRenderer()); 
				 profils_comb.addActionListener(new ButtonComboBoxListener(frame, profils_comb));

				 
				  buttonOk8.addActionListener(new ActionListener() {
				  	    public void actionPerformed(ActionEvent ae) {	
				  	      frame.setState(Frame.ICONIFIED); 
				  	    	 if(existe_profils==true)
						  	    {
						  	    	prof.setVisible(true);
						  	    	//prv.add("chaine");
						  	    	 if( !selectioncomb.prv.contains("profils")){prv.add("profils");}
						  	    //	System.out.println("ttttttt");
						  	    }else{
						  	    	prof= new  profils(log); 	
						  	    	existe_profils=true;
						  	  //  	System.out.println("zzzzzzzzzzzzz");
						  	    }
				  	    	  profils_comb.setSelectedIndex(0);
				  	    prof.addWindowListener(new WindowAdapter(){
				  	        public void windowClosing(WindowEvent e){
				  	         // code_jtext.setText("");
				  	        	profils_comb.removeAllItems();
				  	        	profils_comb.addItem("--Sélectionner un profils--");
				  	        	 list_profil=profil.select_profils();

				  	          for(int i=0;i<list_profil.size();i++)
							   {
							          //Pour affecter une valeur de base de donn?es ? un Combobox 
				  	        	profils_comb.addItem(list_profil.get(i)+" "+list_profil.get(i+1));
								   i++;
							   }
					  	      frame.setState(Frame.NORMAL);	

					  	    profils_comb.addItem(buttonOk8);
					  	  profils_comb.setRenderer(new ButtonComboBoxRenderer());
					  	 


				  	        }});
				  	    }});
				}
    
		
    public static void imprimer(String bdd,String parcour,String model,Map<String, Object> parameters){
    	try{
    		 JPanel controlPanel = new JPanel();
    		 JFrame frame = new JFrame();
    		 frame.add(controlPanel);
    			frame.setUndecorated(true);
				
             JLabel titr=new JLabel("Veuillez patienter ....");
             titr.setFont( titr.getFont().deriveFont(Font.BOLD|Font.ITALIC) );
            // progressBar.setStringPainted(true);
             UIManager.put("nimbusOrange", (new Color(70,130,180)));
             
            
             titr.setFont(police2);
				controlPanel.add(titr);
				//controlPanel.add(progressBar);
				controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));

			//	progressBar.setPreferredSize( new Dimension (200, 25));
				frame.setSize(300, 70);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
    		 
    	//	"C:\\GCOBAR\\CODE\\fichesuiveuse"+code_jtext.getText()+".pdf"
      	  // JasperReport jasperReport = (JasperReport) JRLoader.loadObject("C:\\GCOBAR\\CODE\\reportC.jrxml");
				 JasperDesign jasperDesign = JRXmlLoader.load(model);
				 JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

// - Param?tres ? envoyer au rapport
// parameters.put("2200000004");
System.out.println(parameters.toString());
  
// - Execution du rapport
JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, CConnect.getInstance(bdd));

// - Cr?ation du rapport au format PDF
JasperExportManager.exportReportToPdfFile(jasperPrint, parcour );
controlPanel.remove(titr);
frame.setVisible(false);
int reponse = JOptionPane.showConfirmDialog(
        null, "Operation terminée. Voulez-vous ouvrir le pdf?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);
    	if (reponse==JOptionPane.YES_OPTION){
try {
Desktop.getDesktop().open(new File(parcour));
} catch (IOException p) {
// TODO Auto-generated catch block
p.printStackTrace();
}
    	}
} catch (JRException p) {
// TODO Auto-generated catch block
p.printStackTrace();
}	              	  
        }
    public static void imprimer_deux(String param1,ArrayList<String> object,String param2,ArrayList<String> object2,String bdd,String parcour,String model){
    	try{
    		 JPanel controlPanel = new JPanel();
    		 JFrame frame = new JFrame();
    		 frame.add(controlPanel);
    		 frame.setUndecorated(true);
    			
             JLabel titr=new JLabel("Veuillez patienter ....");
             titr.setFont( titr.getFont().deriveFont(Font.BOLD|Font.ITALIC) );
             UIManager.put("nimbusOrange", (new Color(70,130,180)));
             titr.setFont(police2);
    	     controlPanel.add(titr);
    	     controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));
    		 frame.setSize(300, 70);
    		 frame.setLocationRelativeTo(null);
    		 frame.setVisible(true);
    		 JasperDesign jasperDesign = JRXmlLoader.load(model);
    	     JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(param1,object);
    parameters.put(param2,object2);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, CConnect.getInstance(bdd));
    JasperExportManager.exportReportToPdfFile(jasperPrint, parcour );
    controlPanel.remove(titr);
    frame.setVisible(false);
    int reponse = JOptionPane.showConfirmDialog(
        null, "Operation terminée. Voulez-vous ouvrir le pdf?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);
    	if (reponse==JOptionPane.YES_OPTION){
    try {
    Desktop.getDesktop().open(new File(parcour));
    } catch (IOException p) {
    //TODO Auto-generated catch block
    p.printStackTrace();
    }
    	}
    } catch (JRException p) {
    //TODO Auto-generated catch block
    p.printStackTrace();
    }	              	  
        }




	public static void imprimer(ArrayList<String> object,String bdd,String parcour,String model){
		try{
			JPanel controlPanel = new JPanel();
			JFrame frame = new JFrame();
			frame.add(controlPanel);
			frame.setUndecorated(true);

			JLabel titr=new JLabel("Veuillez patienter ....");
			titr.setFont( titr.getFont().deriveFont(Font.BOLD|Font.ITALIC) );
			// progressBar.setStringPainted(true);
			UIManager.put("nimbusOrange", (new Color(70,130,180)));


			titr.setFont(police2);
			controlPanel.add(titr);
			//controlPanel.add(progressBar);
			controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));

			//	progressBar.setPreferredSize( new Dimension (200, 25));
			frame.setSize(300, 70);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			//	"C:\\GCOBAR\\CODE\\fichesuiveuse"+code_jtext.getText()+".pdf"
			// JasperReport jasperReport = (JasperReport) JRLoader.loadObject("C:\\GCOBAR\\CODE\\reportC.jrxml");
			JasperDesign jasperDesign = JRXmlLoader.load(model);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

//- Param�tres � envoyer au rapport
			Map<String, Object> parameters = new HashMap<String, Object>();
//parameters.put("2200000004");
		/*	System.out.println("serial"+object.toString());*/
			parameters.put("serial",object);

//- Execution du rapport
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, CConnect.getInstance(bdd));

//- Cr�ation du rapport au format PDF

			JasperExportManager.exportReportToPdfFile(jasperPrint, parcour );
			controlPanel.remove(titr);
			frame.setVisible(false);
			int reponse = JOptionPane.showConfirmDialog(
					null, "Operation terminée. Voulez-vous ouvrir le pdf?",
					"Confirmation",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (reponse==JOptionPane.YES_OPTION){
				try {
					Desktop.getDesktop().open(new File(parcour));
				} catch (IOException p) {
//TODO Auto-generated catch block
					p.printStackTrace();
				}
			}
		} catch (JRException p) {
//TODO Auto-generated catch block
			p.printStackTrace();
		}
	}

	public static void closePdf(){
		try {
			Runtime.getRuntime().exec("cmd /c taskkill /f /im acrord32.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void imprimer(String param,ArrayList<String> object,String bdd,String parcour,String model){
	try{
		 JPanel controlPanel = new JPanel();
		 JFrame frame = new JFrame();
		 frame.add(controlPanel);
			frame.setUndecorated(true);
			
         JLabel titr=new JLabel("Veuillez patienter ....");
         titr.setFont( titr.getFont().deriveFont(Font.BOLD|Font.ITALIC) );
        // progressBar.setStringPainted(true);
         UIManager.put("nimbusOrange", (new Color(70,130,180)));
         
        
         titr.setFont(police2);
			controlPanel.add(titr);
			//controlPanel.add(progressBar);
			controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0,0));

		//	progressBar.setPreferredSize( new Dimension (200, 25));
			frame.setSize(300, 70);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		 
	//	"C:\\GCOBAR\\CODE\\fichesuiveuse"+code_jtext.getText()+".pdf"
  	  // JasperReport jasperReport = (JasperReport) JRLoader.loadObject("C:\\GCOBAR\\CODE\\reportC.jrxml");
			 JasperDesign jasperDesign = JRXmlLoader.load(model);
			 JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

//- Param?tres ? envoyer au rapport
Map<String, Object> parameters = new HashMap<String, Object>();
//parameters.put("2200000004");
parameters.put(param,object);
System.out.println("parameters"+parameters);
//- Execution du rapport
JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, CConnect.getInstance(bdd));

//- Cr?ation du rapport au format PDF

JasperExportManager.exportReportToPdfFile(jasperPrint, parcour );
controlPanel.remove(titr);
frame.setVisible(false);
int reponse = JOptionPane.showConfirmDialog(
    null, "Operation terminée. Voulez-vous ouvrir le pdf?",
    "Confirmation",
    JOptionPane.YES_NO_OPTION,
    JOptionPane.QUESTION_MESSAGE);
	if (reponse==JOptionPane.YES_OPTION){
try {
Desktop.getDesktop().open(new File(parcour));
} catch (IOException p) {
//TODO Auto-generated catch block
p.printStackTrace();
}
	}
} catch (JRException p) {
//TODO Auto-generated catch block
p.printStackTrace();
}	              	  
    }
	
	}


@SuppressWarnings({ "serial", "rawtypes" })
class ButtonComboBoxRenderer extends BasicComboBoxRenderer implements ListCellRenderer
{
   public ButtonComboBoxRenderer() {
      super();
   }
    
   public Component getListCellRendererComponent( JList list,
           Object value, int index, boolean isSelected, boolean cellHasFocus) {
      if (isSelected) {
          setBackground(list.getSelectionBackground());
          setForeground(list.getSelectionForeground());
      }
      else {
          setBackground(list.getBackground());
          setForeground(list.getForeground());
      }
  
      setFont(list.getFont());
      if (value instanceof Icon) {
         setIcon((Icon)value);
      }
      if (value instanceof JButton) {
         return (Component) value;
      }
      else {
         setText((value == null) ? "" : value.toString());
      }
  
      return this;
  } 
}

class ButtonComboBoxListener implements ActionListener {
   JComboBox<Object> combobox;
   JFrame frame;
  
   ButtonComboBoxListener(JFrame frame, JComboBox<Object> combobox) {
      this.frame = frame;
      this.combobox = combobox;
     // combobox.setSelectedIndex(0);
   }
      
   public void actionPerformed(ActionEvent e) {
      Object selectedItem = combobox.getSelectedItem();
      if (selectedItem instanceof JButton) {
         ((JButton) selectedItem).doClick();
      }
   }
   
   
   
}