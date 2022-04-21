package logiciel_etiq;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class profils extends JFrame implements ActionListener{
	private static final String    CTRL_J                = "CTRL+J";
	  final  Font police2 = new Font("Comic Sans MS", Font.BOLD|Font.ITALIC,18);

		public void actionPerformed(ActionEvent e) {
	    	 
	     
	            // gestion de l'action
	           
	             if (e.getActionCommand().equals(CTRL_J)){
	            	 
	            		LineBorder border = new LineBorder(Color.white, 1, true);
	        			TitledBorder titl2 = new TitledBorder(border, "Edition",
	        					TitledBorder.DEFAULT_POSITION,
	        					TitledBorder.DEFAULT_POSITION, police2, Color.white);
	        			pan.setBorder(titl2);
	        	
	     	
	     			nom_p.setText("");

	     			but_sauv.setVisible(true);
	     			but_modif.setVisible(false);
	     			retour.setVisible(false);
	     			valid_ajou.setVisible(false);
	     			valid_modif.setVisible(false);
	     			poste.setSelected(false);
	     			edit_poste.setSelected(false);
	     			list_poste.setSelected(false);
	     			chaine.setSelected(false);
	     			edit_chaine.setSelected(false);
	     		    list_chaine.setSelected(false);
	     			chang_chaine.setSelected(false);
	     		    zone.setSelected(false);
	     		    perso.setSelected(false);		
	     			edit_perso.setSelected(false);
	     			list_perso.setSelected(false);						
	     			user.setSelected(false);
	     			edit_user.setSelected(false);
	     			list_user.setSelected(false);
	     			profil_user.setSelected(false);			
	     			code.setSelected(false);
	     			code_ref.setSelected(false);
	     			code_gener.setSelected(false);
	     			rech.setSelected(false);		
	     			article.setSelected(false);
	     			edit_article.setSelected(false);
	     			type.setSelected(false);
	     			imprime.setSelected(false);
	     			fiche.setSelected(false);
	     			list_fiche.setSelected(false);
	     			fourniss.setSelected(false);
	     			edit_fourniss.setSelected(false);   
	     			etq.setSelected(false);
	     			list_fourniss.setSelected(false);
//	     			poste_comb.enable();
//	     			chaine_comb.enable();
//	     			article_comb.enable();
	             }}
   String msg="";

	private static final long serialVersionUID = 1L;
	//private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	
	private JLabel profils_lab = new JLabel("Nom de Profils");
	private JPanel profils_lab_pan = new JPanel();
	private JPanel profils_pan = new JPanel();
	private JPanel profils_txt_pan = new JPanel();
	private JTextField nom_p=new JTextField();
	
	
	
	private JPanel poste_pan = new JPanel();
	private JPanel poste_check_pan = new JPanel();
	private JCheckBox poste = new JCheckBox("Poste");
	private JCheckBox edit_poste = new JCheckBox("Edition poste");
	private JCheckBox list_poste = new JCheckBox("Liste poste");

	private JPanel zone_lab_pan = new JPanel();
	private JPanel zone_pan = new JPanel();
	private JPanel zone_check_pan = new JPanel();
	private JCheckBox zone = new JCheckBox("zone");
	
	private JPanel chaine_pan = new JPanel();
	private JPanel chaine_check_pan = new JPanel();
	private JCheckBox chaine = new JCheckBox("Chaine");
	private JCheckBox edit_chaine = new JCheckBox("Edition chaine");
	private JCheckBox chang_chaine = new JCheckBox("Changement chaine");
	private JCheckBox list_chaine = new JCheckBox("Recherche");
	
	private JPanel personelle_pan = new JPanel();
	private JPanel personelle_check_pan = new JPanel();
	private JCheckBox perso = new JCheckBox("personelle");
	private JCheckBox edit_perso = new JCheckBox("Edition personelle");
	private JCheckBox list_perso = new JCheckBox("List personelle");

	
	private JPanel fourniss_pan = new JPanel();
	private JPanel fourniss_check_pan = new JPanel();
	private JCheckBox fourniss = new JCheckBox("fournisseur");
	private JCheckBox edit_fourniss = new JCheckBox("Edition fournisseur");
	private JCheckBox list_fourniss = new JCheckBox("List fournisseur");

	
	private JPanel user_pan = new JPanel();
	private JPanel user_check_pan = new JPanel();
	private JCheckBox user = new JCheckBox("user");
	private JCheckBox edit_user = new JCheckBox("Edition Utilisateurs");
	private JCheckBox list_user = new JCheckBox("List Utilisateurs");
	private JCheckBox profil_user = new JCheckBox("Profils Utilisateurs");

	private JPanel code_check_pan = new JPanel();	
	private JPanel code_pan = new JPanel();
	private JCheckBox code = new JCheckBox("Codification");
	private JCheckBox code_ref = new JCheckBox("Code réfèrence");
	private JCheckBox code_gener = new JCheckBox("Code génerique");
	private JCheckBox rech = new JCheckBox("Recharche");

	
	private JPanel article_pan = new JPanel();
	private JPanel article_check_pan = new JPanel();
	private JCheckBox article = new JCheckBox("Article");
	private JCheckBox edit_article = new JCheckBox("Edition article");
	private JCheckBox type = new JCheckBox("Type article");
	
	
	private JPanel imp_pan = new JPanel();
	private JPanel imp_check_pan = new JPanel();
	private JCheckBox imprime = new JCheckBox("imprime");
	private JCheckBox etq = new JCheckBox("Etiquette");
	private JCheckBox fiche = new JCheckBox("Fiche Suiveuse");
	private JCheckBox list_fiche = new JCheckBox("Liste Fiche");

	
	  JPanel pan= new JPanel();

	
	JPanel pan_but=new JPanel();
	JPanel pan_form=new JPanel();
	JPanel pan_nom=new JPanel();

	private JButton but_sauv = new JButton("Ajouter");
	private JButton but_modif = new JButton("Modifier");
	private JButton valid_ajou = new JButton("Valider");
	private JButton valid_modif = new JButton("Valider");
	private JButton retour = new JButton("Retour");
	private gestion_profils prof=new gestion_profils();
	  protected String p,c,z,pers,us,cd,cd_f,cd_g,art,t,im,et,fi,lfi,eart;
	  protected String lp,ep;
	  protected String ec,lc,ch;
	  protected String epers,lpers;
	  protected String eus,lus,cd_rech;
	  protected String four,efour,lfour,profil;
	profils(final String log) {
		final menu fr = new menu(log);
		fr.setVisible(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
		setIconImage(img);
		selectioncomb.windows(this, log);
		composant(log);
	}

	public void composant(final String logi_prio) {
		 JPanel panel= new JPanel(){   
		  		/** * */
	private static final long serialVersionUID = 1L;
				public void paintComponent(Graphics g){
		  			 ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
		             img.paintIcon(this, g,0, 0);
 }};
 panel.registerKeyboardAction(this, CTRL_J, KeyStroke.getKeyStroke(
         KeyEvent.VK_J, Event.CTRL_MASK),
         JComponent.WHEN_IN_FOCUSED_WINDOW);
 final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,17);
 final Font police= new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,13);
 
	but_sauv.setVisible(true);
	but_modif.setVisible(false);
	retour.setVisible(false);
	valid_ajou.setVisible(false);
	valid_modif.setVisible(false);
	if (!selectioncomb.prv.contains("profils"))
		selectioncomb.prv.add("profils");
	LineBorder border = new LineBorder(Color.white, 1, true);
	TitledBorder titl2 = new TitledBorder(border, "Autorisation des Profils",
			TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
			police2, Color.white);
	pan.setBorder(titl2);
	
		TitledBorder titlp = new TitledBorder(border, "Poste",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		
		poste_pan.setBorder(titlp);
		
		TitledBorder titlz = new TitledBorder(border, "Zone",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		zone_pan.setBorder(titlz);
		
		TitledBorder titlc = new TitledBorder(border, "Chaine",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		chaine_pan.setBorder(titlc);
		
		TitledBorder titlpers = new TitledBorder(border, "Personelle",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		personelle_pan.setBorder(titlpers);
		
		TitledBorder titlfour = new TitledBorder(border, "Fournisseur",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		fourniss_pan.setBorder(titlfour);
		
		TitledBorder titluse = new TitledBorder(border, "Utilisateurs",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		user_pan.setBorder(titluse);
		
		TitledBorder titlcode = new TitledBorder(border, "Codification",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		code_pan.setBorder(titlcode);
		
		TitledBorder titlart = new TitledBorder(border, "Article",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		article_pan.setBorder(titlart);
		
		TitledBorder titlimp = new TitledBorder(border, "Imprimé",
				TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
				police2, Color.white);
		imp_pan.setBorder(titlimp);
		
	but_sauv.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LineBorder border = new LineBorder(Color.white, 1, true);
			TitledBorder titl2 = new TitledBorder(border, "Ajouter",
					TitledBorder.DEFAULT_POSITION,
					TitledBorder.DEFAULT_POSITION, police2, Color.white);
			pan.setBorder(titl2);
			but_sauv.setVisible(false);
			but_modif.setVisible(false);
			retour.setVisible(true);
			valid_ajou.setVisible(true);
			valid_modif.setVisible(false);
		}});
	
	but_modif.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LineBorder border = new LineBorder(Color.white, 1, true);
			TitledBorder titl2 = new TitledBorder(border, "Modifier",
					TitledBorder.DEFAULT_POSITION,
					TitledBorder.DEFAULT_POSITION, police2, Color.white);
			pan.setBorder(titl2);
			but_sauv.setVisible(false);
			but_modif.setVisible(false);
			retour.setVisible(true);
			valid_ajou.setVisible(false);
			valid_modif.setVisible(true);
		}});
	      nom_p.addActionListener(
	             new ActionListener() { 
	             public void actionPerformed(ActionEvent e) {
	            	 prof.selection(nom_p.getText());
	            	 
	            	 if(prof.ex==true){
	            	// System.out.println("rrrrrrrrrrrr"+prof.poste);
	            	if (prof.poste.equals("TRUE")){poste.setSelected(true); }
	     			if (prof.edit_poste.equals("TRUE")){edit_poste.setSelected(true); }
	     			if (prof.list_poste.equals("TRUE")){list_poste.setSelected(true); }
	     			if (prof.chaine.equals("TRUE")){chaine.setSelected(true); }
	     			if (prof.edit_chaine.equals("TRUE")){edit_chaine.setSelected(true); }
	     			else {edit_chaine.setSelected(false); }
	     			if (prof.list_chaine.equals("TRUE")){list_chaine.setSelected(true); }
	     			if (prof.chang_chaine.equals("TRUE")){chang_chaine.setSelected(true); }
	     			else{chang_chaine.setSelected(false); }
	     			if (prof.zone.equals("TRUE")){zone.setSelected(true); }
	     			else{zone.setSelected(false); }

	     			if (prof.personelle.equals("TRUE")){perso.setSelected(true); }
	     			else{perso.setSelected(false); }
	     			if (prof.edit_personelle.equals("TRUE")){edit_perso.setSelected(true); }
	     			else{edit_perso.setSelected(false); }
	     			if (prof.list_personelle.equals("TRUE")){list_perso.setSelected(true); }
	     			else{list_perso.setSelected(false); }
	     			if (prof.user.equals("TRUE")){user.setSelected(true); }
	     			else{user.setSelected(false); }
	     			if (prof.edit_user.equals("TRUE")){edit_user.setSelected(true); }
	     			else{edit_user.setSelected(false); }
	     			if (prof.list_user.equals("TRUE")){list_user.setSelected(true); }
	     			else{list_user.setSelected(false); }
	     			if (prof.profil.equals("TRUE")){profil_user.setSelected(true); }
	     			else{profil_user.setSelected(false); }
	     			if (prof.code.equals("TRUE")){code.setSelected(true); }
	     			else{code.setSelected(false); }
	     			if (prof.code_ref.equals("TRUE")){code_ref.setSelected(true); }
	     			else{code_ref.setSelected(false); }
	     			if (prof.code_gener.equals("TRUE")){code_gener.setSelected(true); }
	     			else{code_gener.setSelected(false); }
	     			if (prof.code_rech.equals("TRUE")){rech.setSelected(true); }
	     			else{rech.setSelected(false); }
	     			if (prof.article.equals("TRUE")){article.setSelected(true); }
	     			else{article.setSelected(false); }
	     			if (prof.edit_article.equals("TRUE")){edit_article.setSelected(true); }
	     			else{edit_article.setSelected(false); }
	     			if (prof.type.equals("TRUE")){type.setSelected(true); }
	     			else{type.setSelected(false); }
	     			if (prof.imprime.equals("TRUE")){imprime.setSelected(true); }
	     			else{imprime.setSelected(false); }
	     			if (prof.etiquette.equals("TRUE")){etq.setSelected(true); }
	     			else{etq.setSelected(false); }
	     			if (prof.fiche.equals("TRUE")){fiche.setSelected(true); }
	     			else{fiche.setSelected(false); }
	     			if (prof.list_fiche.equals("TRUE")){list_fiche.setSelected(true); }
	     			else{list_fiche.setSelected(false); }
	     			if (prof.fourniss.equals("TRUE")){fourniss.setSelected(true); }
	     			else{fourniss.setSelected(false); }
	     			if (prof.edit_fourniss.equals("TRUE")){edit_fourniss.setSelected(true); }
	     			else{edit_fourniss.setSelected(false); }
	     			if (prof.list_fourniss.equals("TRUE")){list_fourniss.setSelected(true); }
	     			else{list_fourniss.setSelected(false); }
	     			  JOptionPane.showMessageDialog(null,"Cep Profils exist deja");
	     			but_sauv.setVisible(false);
	     			but_modif.setVisible(true);
	     			valid_modif.setVisible(false);
	     			valid_ajou.setVisible(false);
	     			retour.setVisible(false);
	            	 }
	            	 else{
	            	
	 	     		        edit_chaine.setSelected(false); 
	            			chang_chaine.setSelected(false); 
	    	     			list_chaine.setSelected(false); 
	    	     			chaine.setSelected(false); 
	    	     			list_poste.setSelected(false); 
	    	     			edit_poste.setSelected(false); 
	    	     			poste.setSelected(false);
	    	     			zone.setSelected(false); 
	    	     			perso.setSelected(false); 
	    	     			edit_perso.setSelected(false); 
	    		     	    list_perso.setSelected(false); 
	    		     		user.setSelected(false); 
	    		     		edit_user.setSelected(false); 
	    		     		list_user.setSelected(false); 
	    		           profil_user.setSelected(false); 
	    			code.setSelected(false); 
	     			code_ref.setSelected(false); 
	     			code_gener.setSelected(false); 
	     			rech.setSelected(false); 
	     			article.setSelected(false);
	     			edit_article.setSelected(false); 
	     			type.setSelected(false); 
	     			imprime.setSelected(false); 
	     			etq.setSelected(false); 
	     			fiche.setSelected(false); 
	     			list_fiche.setSelected(false); 
	     			fourniss.setSelected(false); 
	     			edit_fourniss.setSelected(false);
	     			list_fourniss.setSelected(false); 
	     			
	     			but_sauv.setVisible(true);
	     			but_modif.setVisible(false);
	     			valid_modif.setVisible(false);
	     			valid_ajou.setVisible(false);
	     			retour.setVisible(false);
	     			
	     		
	            	 }
	     				
	     		
	     		
	     			
	     		
	            	 
	             }});
	retour.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LineBorder border = new LineBorder(Color.white, 1, true);
			TitledBorder titl2 = new TitledBorder(border, "Edition",
					TitledBorder.DEFAULT_POSITION,
					TitledBorder.DEFAULT_POSITION, police2, Color.white);
			pan.setBorder(titl2);
			nom_p.setText("");
			but_sauv.setVisible(true);
			but_modif.setVisible(false);
			retour.setVisible(false);
			valid_ajou.setVisible(false);
			valid_modif.setVisible(false);
			poste.setSelected(false);
			edit_poste.setSelected(false);
			list_poste.setSelected(false);
			chaine.setSelected(false);
			edit_chaine.setSelected(false);
		    list_chaine.setSelected(false);
			chang_chaine.setSelected(false);
		    zone.setSelected(false);
		    perso.setSelected(false);		
			edit_perso.setSelected(false);
			list_perso.setSelected(false);						
			user.setSelected(false);
			edit_user.setSelected(false);
			list_user.setSelected(false);
			profil_user.setSelected(false);			
			code.setSelected(false);
			code_ref.setSelected(false);
			code_gener.setSelected(false);
			rech.setSelected(false);		
			article.setSelected(false);
			edit_article.setSelected(false);
			type.setSelected(false);
			imprime.setSelected(false);
			fiche.setSelected(false);
			list_fiche.setSelected(false);
			fourniss.setSelected(false);
			edit_fourniss.setSelected(false);   
			list_fourniss.setSelected(false);
 			etq.setSelected(false);

//			poste_comb.enable();
//			chaine_comb.enable();
//			article_comb.enable();

		}
	});
	
	valid_modif.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int i=0;
			msg="";
			if (poste.isSelected()){ p="1"; i=i+1;}   
       		else { p="0";  }
			if (edit_poste.isSelected()){ ep="1";i=i+1; }   
       		else { ep="0";  }
			if (list_poste.isSelected()){ lp="1"; i=i+1;}   
       		else { lp="0";  }
			if (chaine.isSelected()){ c="1";i=i+1; }   
       		else { c="0";  }
			if (edit_chaine.isSelected()){ ec="1";i=i+1; }   
       		else { ec="0";  }
			if (list_chaine.isSelected()){ lc="1";i=i+1; }   
       		else { lc="0";  }
			if (chang_chaine.isSelected()){ch="1";i=i+1; }   
       		else { ch="0";  }
			if (zone.isSelected()){ z="1"; i=i+1;}   
       		else { z="0";  }
			if (perso.isSelected()){ pers="1"; i=i+1;}   
       		else { pers="0";  }			
			if (edit_perso.isSelected()){ epers="1"; i=i+1;}   
       		else { epers="0";  }
			if (list_perso.isSelected()){ lpers="1"; i=i+1;}   
       		else { lpers="0";  }						
			if (user.isSelected()){ us="1";i=i+1; }   
       		else { us="0";  }
			if (edit_user.isSelected()){ eus="1";i=i+1; }   
       		else { eus="0";  }
			if (list_user.isSelected()){ lus="1";i=i+1; }   
       		else { lus="0";  }
			if (profil_user.isSelected()){ profil="1"; i=i+1;}   
       		else { profil="0";  }			
			if (code.isSelected()){ cd="1";i=i+1; }   
       		else { cd="0";  }
			if (code_ref.isSelected()){ cd_f="1"; i=i+1;}   
       		else { cd_f="0";  }
			if (code_gener.isSelected()){ cd_g="1"; i=i+1;}   
       		else { cd_g="0";  }
			if (rech.isSelected()){ cd_rech="1";i=i+1; }   
       		else { cd_rech="0";  }			
			if (article.isSelected()){ art="1"; i=i+1;}   
       		else { art="0";  }
			if (edit_article.isSelected()){ eart="1";i=i+1; }   
       		else { eart="0";  }
			if (type.isSelected()){ t="1";i=i+1; }   
       		else { t="0";  }
			if (imprime.isSelected()){ im="1";i=i+1; }   
       		else { im="0";  }
			if (etq.isSelected()){ et="1"; i=i+1;}   
       		else { et="0";  }
			if (fiche.isSelected()){ fi="1"; i=i+1;}   
       		else { fi="0";  }
			if (list_fiche.isSelected()){ lfi="1"; i=i+1;}   
       		else { lfi="0";  }
			if (fourniss.isSelected()){ four="1";i=i+1; }   
       		else { four="0";  }
			if (edit_fourniss.isSelected()){ efour="1"; i=i+1;}   
       		else { efour="0";  }
			if (list_fourniss.isSelected()){ lfour="1"; i=i+1;}   
       		else { lfour="0";  }
			prof.selection(nom_p.getText());
			if(i<2){msg+="veuillez cocher au moins 2 case \n";}
			else if(nom_p.getText().equals("")) {msg+="Veuillez remplir le nom du profil \n";}
			else if(prof.ex==false){msg+="le nom du profil ne peut pas étre modifié \n";}
			  if(msg.equals("")){
			   prof.modif_profils(p,ep,lp,c,ec,lc,ch,z,pers,epers,lpers,us,eus,lus,profil,
					cd,cd_f,cd_g,cd_rech,
					art,eart,t,
					im,et,fi,lfi,nom_p.getText(),four,efour,lfour);
			  if(prof.exist==false)
			 JOptionPane.showMessageDialog(null,"Le profils a été bien modifiée");
			  }
			  else{JOptionPane.showMessageDialog(null,msg);}
			
		}});
	
	valid_ajou.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int i=0;
			msg="";
			if (poste.isSelected()){ p="1"; i=i+1;}   
       		else { p="0";  }
			if (edit_poste.isSelected()){ ep="1";i=i+1; }   
       		else { ep="0";  }
			if (list_poste.isSelected()){ lp="1"; i=i+1;}   
       		else { lp="0";  }
			if (chaine.isSelected()){ c="1";i=i+1; }   
       		else { c="0";  }
			if (edit_chaine.isSelected()){ ec="1";i=i+1; }   
       		else { ec="0";  }
			if (list_chaine.isSelected()){ lc="1";i=i+1; }   
       		else { lc="0";  }
			if (chang_chaine.isSelected()){ch="1";i=i+1; }   
       		else { ch="0";  }
			if (zone.isSelected()){ z="1"; i=i+1;}   
       		else { z="0";  }
			if (perso.isSelected()){ pers="1"; i=i+1;}   
       		else { pers="0";  }			
			if (edit_perso.isSelected()){ epers="1"; i=i+1;}   
       		else { epers="0";  }
			if (list_perso.isSelected()){ lpers="1"; i=i+1;}   
       		else { lpers="0";  }						
			if (user.isSelected()){ us="1";i=i+1; }   
       		else { us="0";  }
			if (edit_user.isSelected()){ eus="1";i=i+1; }   
       		else { eus="0";  }
			if (list_user.isSelected()){ lus="1";i=i+1; }   
       		else { lus="0";  }
			if (profil_user.isSelected()){ profil="1"; i=i+1;}   
       		else { profil="0";  }			
			if (code.isSelected()){ cd="1";i=i+1; }   
       		else { cd="0";  }
			if (code_ref.isSelected()){ cd_f="1"; i=i+1;}   
       		else { cd_f="0";  }
			if (code_gener.isSelected()){ cd_g="1"; i=i+1;}   
       		else { cd_g="0";  }
			if (rech.isSelected()){ cd_rech="1";i=i+1; }   
       		else { cd_rech="0";  }			
			if (article.isSelected()){ art="1"; i=i+1;}   
       		else { art="0";  }
			if (edit_article.isSelected()){ eart="1";i=i+1; }   
       		else { eart="0";  }
			if (type.isSelected()){ t="1";i=i+1; }   
       		else { t="0";  }
			if (imprime.isSelected()){ im="1";i=i+1; }   
       		else { im="0";  }
			if (etq.isSelected()){ et="1"; i=i+1;}   
       		else { et="0";  }
			if (fiche.isSelected()){ fi="1"; i=i+1;}   
       		else { fi="0";  }
			if (list_fiche.isSelected()){ lfi="1"; i=i+1;}   
       		else { lfi="0";  }
			if (fourniss.isSelected()){ four="1";i=i+1; }   
       		else { four="0";  }
			if (edit_fourniss.isSelected()){ efour="1"; i=i+1;}   
       		else { efour="0";  }
			if (list_fourniss.isSelected()){ lfour="1"; i=i+1;}   
       		else { lfour="0";  }
			if(i<2){msg+="veuillez cocher au moins 2 case \n";}
			else if(nom_p.getText().equals("")) {msg+="Veuillez remplir le nom du profil \n";}
			  if(msg.equals("")){
			prof.ajouter_profils(p,ep,lp,c,ec,lc,ch,z,pers,epers,lpers,us,eus,lus,profil,
					cd,cd_f,cd_g,cd_rech,
					art,eart,t,
					im,et,fi,lfi,nom_p.getText(),four,efour,lfour);
			  if(prof.exist==false)
			 JOptionPane.showMessageDialog(null,"Le profils a été bien ajoutée");
			  }
			  else{JOptionPane.showMessageDialog(null,msg);}
			
		}});
		generale.styles("Nimbus");
		SwingUtilities.updateComponentTreeUI(this);
		
		profils_lab.setForeground(Color.white);
		poste.setForeground(Color.white);
		edit_poste.setForeground(Color.white);
		list_poste.setForeground(Color.white);
		zone.setForeground(Color.white);
		chaine.setForeground(Color.white);
		edit_chaine.setForeground(Color.white);
		list_chaine.setForeground(Color.white);
		chang_chaine.setForeground(Color.white);
		perso.setForeground(Color.white);
		list_perso.setForeground(Color.white);
		edit_perso.setForeground(Color.white);
		fourniss.setForeground(Color.white);
		list_fourniss.setForeground(Color.white);
		edit_fourniss.setForeground(Color.white);
		user.setForeground(Color.white);
		list_user.setForeground(Color.white);
		edit_user.setForeground(Color.white);
		code.setForeground(Color.white);
		code_ref.setForeground(Color.white);
		code_gener.setForeground(Color.white);
		rech.setForeground(Color.white);
		profil_user.setForeground(Color.white);
		
		article.setForeground(Color.white);
		edit_article.setForeground(Color.white);
		type.setForeground(Color.white);
		imprime.setForeground(Color.white);
		etq.setForeground(Color.white);
		fiche.setForeground(Color.white);
		list_fiche.setForeground(Color.white);
		profil_user.setForeground(Color.white);
		
		profil_user.setFont(police);
		article.setFont(police);
		edit_article.setFont(police);
		type.setFont(police);
		imprime.setFont(police);
		etq.setFont(police);
		fiche.setFont(police);
		list_fiche.setFont(police);
		code.setFont(police);
		code_ref.setFont(police);
		code_gener.setFont(police);
		rech.setFont(police);
		
		user.setFont(police);
		list_user.setFont(police);
		edit_user.setFont(police);
		perso.setFont(police);
		list_perso.setFont(police);
		edit_perso.setFont(police);
		fourniss.setFont(police);
		list_fourniss.setFont(police);
		edit_fourniss.setFont(police);
		chang_chaine.setFont(police);
		profils_lab.setFont(police2);
		poste.setFont(police);
		edit_poste.setFont(police);
		list_poste.setFont(police);
		zone.setFont(police);
		chaine.setFont(police);
		edit_chaine.setFont(police);
		list_chaine.setFont(police);
		
		profils_lab_pan.add(profils_lab);
		profils_txt_pan.add(nom_p);
		profils_pan.add(profils_lab_pan);
		profils_pan.add(profils_txt_pan);
		
		poste_check_pan.add(poste);
		poste_check_pan.add(edit_poste);
		poste_check_pan.add(list_poste);
		poste_pan.add(poste_check_pan);
		
		
		
		chaine_check_pan.add(chaine);
		chaine_check_pan.add(edit_chaine);
		chaine_check_pan.add(chang_chaine);
		chaine_check_pan.add(list_chaine);

		chaine_pan.add(chaine_check_pan);
		
		
		personelle_check_pan.add(perso);
		personelle_check_pan.add(edit_perso);
		personelle_check_pan.add(list_perso);

		personelle_pan.add(personelle_check_pan);
		
		
		fourniss_check_pan.add(fourniss);
		fourniss_check_pan.add(edit_fourniss);
		fourniss_check_pan.add(list_fourniss);
		fourniss_pan.add(fourniss_check_pan);
		
		
		user_check_pan.add(user);
		user_check_pan.add(edit_user);
		user_check_pan.add(list_user);

		user_pan.add(user_check_pan);
		
		code_check_pan.add(code);
		code_check_pan.add(code_ref);
		code_check_pan.add(code_gener);
		code_check_pan.add(rech);
		code_check_pan.add(profil_user);
		code_pan.add(code_check_pan);
		
	
		
		
		article_check_pan.add(article);
		article_check_pan.add(edit_article);
		article_check_pan.add(type);

		article_pan.add(article_check_pan);
		
	

		imp_check_pan.add(imprime);
		imp_check_pan.add(etq);
		imp_check_pan.add(fiche);
		imp_check_pan.add(list_fiche);

		
		imp_pan.add(imp_check_pan);
		
		zone_check_pan.add(zone);
		zone_pan.add(zone_check_pan);
		
		pan_but.add(but_sauv);
		pan_but.add(but_modif);
		pan_but.add(retour);

		pan_but.add(valid_ajou);
		pan_but.add(valid_modif);
		
		pan_nom.add(profils_pan);
		pan.add(pan_nom);
		pan_form.add(poste_pan);
		pan_form.add(zone_pan);
		pan_form.add(chaine_pan);
		pan_form.add(personelle_pan);
		pan_form.add(user_pan);
		pan_form.add(fourniss_pan);
		pan_form.add(code_pan);
		pan_form.add(article_pan);
		pan_form.add(imp_pan);
		pan_form.add(zone_pan);


		pan.add(pan_form);
		pan.add(pan_but);
		panel.add(pan);
		
		profils_lab_pan.setOpaque(false);
		profils_txt_pan.setOpaque(false);
		profils_pan.setOpaque(false);
		fourniss_pan.setOpaque(false);
		fourniss_check_pan.setOpaque(false);

		poste_check_pan.setOpaque(false);
		poste_pan.setOpaque(false);
		pan_but.setOpaque(false);

		zone_lab_pan.setOpaque(false);
		zone_check_pan.setOpaque(false);
		zone_pan.setOpaque(false);
		
		chaine_check_pan.setOpaque(false);
		chaine_pan.setOpaque(false);		
		
		personelle_check_pan.setOpaque(false);
		personelle_pan.setOpaque(false);
		
		user_check_pan.setOpaque(false);
		user_pan.setOpaque(false);
		user_pan.setOpaque(false);
		
		code_check_pan.setOpaque(false);
		code_pan.setOpaque(false);
		
		
		
	
		article_check_pan.setOpaque(false);
		article_pan.setOpaque(false);
		
		
		imp_check_pan.setOpaque(false);
		imp_pan.setOpaque(false);
		
		
		pan_form.setOpaque(false);
		pan.setOpaque(false);
		
		pan_nom.setOpaque(false);

		pan_form.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));

//		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
//
//		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//		pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));
//
//		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));
//
//		but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
//		but_sauv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
//
//		retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
//		imp_etq.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
//		valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
//		valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
//
//		but_modif.setPreferredSize(new Dimension(120, 33));
//		but_sauv.setPreferredSize(new Dimension(120, 33));
//		imp_etq.setPreferredSize(new Dimension(170, 33));
//		retour.setPreferredSize(new Dimension(120, 33));
//		valid_modif.setPreferredSize(new Dimension(120, 33));
//		valid_ajou.setPreferredSize(new Dimension(120, 33));
//
//		article_comb.setPreferredSize(new Dimension(210, 30));
//		chaine_comb.setPreferredSize(new Dimension(210, 30));
//		poste_comb.setPreferredSize(new Dimension(210, 30));
//		code_jtext.setPreferredSize(new Dimension(210, 30));
//		cont_jtext.setPreferredSize(new Dimension(210, 30));
		retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		valid_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_sauv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		but_modif.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		
		valid_ajou.setPreferredSize(new Dimension(120, 33));
		valid_modif.setPreferredSize(new Dimension(120, 33));
		but_modif.setPreferredSize(new Dimension(120, 33));
		but_sauv.setPreferredSize(new Dimension(120, 33));
		retour.setPreferredSize(new Dimension(120, 33));
		
		
		valid_modif.setPreferredSize(new Dimension(120, 33));
		valid_ajou.setPreferredSize(new Dimension(120, 33));
		nom_p.setPreferredSize(new Dimension(210, 30));
		poste_pan.setLayout(new BoxLayout(poste_pan, BoxLayout.Y_AXIS));
		zone_pan.setLayout(new BoxLayout(zone_pan, BoxLayout.X_AXIS));
		chaine_pan.setLayout(new BoxLayout(chaine_pan, BoxLayout.X_AXIS));
		code_pan.setLayout(new BoxLayout(code_pan, BoxLayout.X_AXIS));
 
		personelle_pan.setLayout(new BoxLayout(personelle_pan, BoxLayout.X_AXIS));
		article_pan.setLayout(new BoxLayout(article_pan, BoxLayout.X_AXIS));
		
		imp_pan.setLayout(new BoxLayout(imp_pan, BoxLayout.X_AXIS));
	
		fourniss_pan.setLayout(new BoxLayout(fourniss_pan, BoxLayout.X_AXIS));
		user_pan.setLayout(new BoxLayout(user_pan, BoxLayout.X_AXIS));
		pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));
		poste_check_pan.setLayout(new BoxLayout(poste_check_pan, BoxLayout.Y_AXIS));
		zone_check_pan.setLayout(new BoxLayout(zone_check_pan, BoxLayout.Y_AXIS));
		chaine_check_pan.setLayout(new BoxLayout(chaine_check_pan, BoxLayout.Y_AXIS));
		personelle_check_pan.setLayout(new BoxLayout(personelle_check_pan, BoxLayout.Y_AXIS));
		fourniss_check_pan.setLayout(new BoxLayout(fourniss_check_pan, BoxLayout.Y_AXIS));
		user_check_pan.setLayout(new BoxLayout(user_check_pan, BoxLayout.Y_AXIS));
		code_check_pan.setLayout(new BoxLayout(code_check_pan, BoxLayout.Y_AXIS));
		imp_check_pan.setLayout(new BoxLayout(imp_check_pan, BoxLayout.Y_AXIS));
		article_check_pan.setLayout(new BoxLayout(article_check_pan, BoxLayout.Y_AXIS));
//		pan_radio_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
//		pan_radio_bg.setLayout(new FlowLayout(FlowLayout.LEFT));
//		pan_radio_lab.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0));
//		pan_radio_bg.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//
//		pan_article_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
//		pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
//		pan_article_lab
//				.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 0));
//		pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//
//		pan_poste_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
//		pan_poste_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
//		pan_poste_lab.setBorder(BorderFactory.createEmptyBorder(0, 220, 0, 0));
//		pan_poste_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//
//		pan_chaine_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
//		pan_chaine_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
//		pan_chaine_lab.setBorder(BorderFactory.createEmptyBorder(0, 215, 0, 0));
//		pan_chaine_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//
//		pan_code_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
//		pan_code_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
//		pan_code_lab.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, 0));
//		pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//
//		pan_cont_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
//		pan_cont_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
//		pan_cont_lab.setBorder(BorderFactory.createEmptyBorder(0, 270, 0, 0));
//		pan_cont_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		pan_form.setLayout(new GridLayout(3, 3, 30, 20)); 
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		setTitle("imprime");
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

					new profils("7");
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}





	


}
