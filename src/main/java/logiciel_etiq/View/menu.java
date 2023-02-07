package logiciel_etiq.View;

import logiciel_etiq.View.Etiquette.Composent.etiquette_composant;
import logiciel_etiq.View.Etiquette.Composent.list_etq_composants;
import logiciel_etiq.View.Etiquette.Phone.etiquette_emballage_portable;
import logiciel_etiq.View.Etiquette.Phone.etiquette_portable_forn;
import logiciel_etiq.View.Etiquette.Phone.list_emballage_portable;
import logiciel_etiq.View.Etiquette.Phone.list_etq_portable;
import logiciel_etiq.View.Etiquette.TPE.etiquette_embalage_tpe;
import logiciel_etiq.View.Etiquette.TPE.etiquette_tpe;
import logiciel_etiq.View.Etiquette.TPE.list_emballage_tpe;
import logiciel_etiq.View.Etiquette.TPE.list_etq_tpe;
import logiciel_etiq.View.Etiquette.TV.etiquette_tv;
import logiciel_etiq.View.Etiquette.TV.list_etq_tv;
import logiciel_etiq.View.Etiquette.TV.netoyer_etq_tv;
import logiciel_etiq.View.Etiquette.Tablette.*;
import logiciel_etiq.View.Etiquette.autre_etiquette;
import logiciel_etiq.View.article.article;
import logiciel_etiq.View.article.famille_article;
import logiciel_etiq.View.article.list_article;
import logiciel_etiq.View.chaine.Chaine;
import logiciel_etiq.View.fournisseur.fournisseur;
import logiciel_etiq.View.fournisseur.list_fourniss;
import logiciel_etiq.View.produit.list_produit;
import logiciel_etiq.View.produit.produit;
import logiciel_etiq.View.reference_gener.codification_gener;
import logiciel_etiq.View.reference_gener.codification_refer;
import logiciel_etiq.View.reference_gener.recap_codification;
import logiciel_etiq.View.user.connection_user;
import logiciel_etiq.View.user.list_user;

import javax.swing.*;
import java.awt.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static logiciel_etiq.View.generale.*;


public class menu extends JFrame{

    public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {

                menu frame = new menu("7");
                frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	///**
	// * Create the frame.
	// */
	
	
	menu(final String logi_prio) {


         JMenu gestion_utilisateur = new JMenu("Gestion des Utilisateurs  ");
         JMenuItem edit_user = new JMenuItem("Edition");
         JMenuItem list_user = new JMenuItem("Liste Utilisateur");





         JMenu gestion_chaine = new JMenu("Chaine");
         JMenuItem edit_chaine = new JMenuItem("Edition");




        JMenu gestion_fournisseur = new JMenu("Fournisseur  ");
        JMenuItem edit_fournisseur = new JMenuItem("Edition");
        JMenuItem list_fournisseur = new JMenuItem("Liste Fournisseurs");




        JMenu gestion_codification= new JMenu("Codification  ");
        JMenuItem edit_reference = new JMenuItem("Code réference");
        JMenuItem edit_famille = new JMenuItem("Code Famille");
        JMenuItem recherche_code = new JMenuItem("Recherche");





         JMenu gestion_article = new JMenu("Article");
         JMenuItem famille_art= new  JMenuItem("Type article");
         JMenuItem article_edit= new  JMenuItem("Edition article");
         JMenuItem list_art= new  JMenuItem("Recherche article");



        JMenu gestion_produit = new JMenu("Produits");
        JMenuItem edit_produit= new  JMenuItem("Edition Produit");
        JMenuItem list_prod= new  JMenuItem("Liste Produit");






        JMenu imp = new JMenu("Imprimer Etiquette");


        JMenu televisseur= new  JMenu("Téléviseur");
        JMenuItem imp_etiq_tv = new JMenuItem("Etiquette TV");
        JMenuItem netoyer = new JMenuItem("Nettoyer");
        JMenuItem list_imp_tv = new JMenuItem("Liste Etiquette Téliviseur");



         JMenu tpe= new  JMenu("TPE");
         JMenuItem imp_etiq_tpe = new JMenuItem("Etiquette TPE");
         JMenuItem liste_etiq_tpe = new JMenuItem("Liste Etiquette TPE");

         JMenuItem imp_emballage_tpe = new JMenuItem("Emballage TPE");
        JMenuItem imp_emballage_tpe_liste = new JMenuItem("Liste Emballage TPE");



         JMenu tablette= new  JMenu("Tablette");

         JMenuItem etq_tablette = new JMenuItem("Etiquette  Tablette");
         JMenuItem list_tablette = new JMenuItem("Listes Etiquette  Tablette");
         JMenuItem etq_emballege_tablette = new JMenuItem("Etiquette Emballage Tablette");
         JMenuItem list_etq_emballege_tablette = new JMenuItem("Listes Etiquette Emballage");
         JMenuItem etq_tablette_chariot = new JMenuItem("Etiquette Chariot");
         JMenuItem list_etq_tablette_chariot = new JMenuItem("List Etiquette Chariot");


        JMenu portable= new  JMenu("Portable ");

        JMenuItem etq_portable = new JMenuItem("Etiquette Portable");
        JMenuItem list_portable = new JMenuItem("Liste Etiquette Portable");


        JMenuItem etq_emballege_portable = new JMenuItem("Etiquett Emballage Portable");
        JMenuItem list_emballege_portable = new JMenuItem("Liste Emballage Portable");


        JMenu composant= new  JMenu("Composant ");

        JMenuItem etq_composant = new JMenuItem("Etiquette Composant");
        JMenuItem list_composant = new JMenuItem("Listes Etiquette Composant");


        JMenuItem autre_etiquette= new  JMenuItem("Autre Etiquette ");


        this.addWindowListener( new WindowAdapter(){
		        public void windowClosing(WindowEvent e){

		   int reponse = JOptionPane.showConfirmDialog(
	                null, "Voulez-vous quitter définitivement l'application ?",
	                "Confirmation",
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE);

	  	if (reponse==JOptionPane.YES_OPTION){
	  		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    dispose();
	     

	  	}
	  	else if (reponse==JOptionPane.NO_OPTION){
	     	   setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	  }
	  	else{
	     	   setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	  }}});
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	    setIconImage(img);


        styles("Nimbus");
        SwingUtilities.updateComponentTreeUI(this);




//        /***************************  gestion des  chaine *********************/
        edit_chaine.addActionListener(
                e -> {
                    Chaine chaine=  new  Chaine(logi_prio);
                    chaine.setVisible(true);
                    dispose();
                });


      //  /***************************  gestion des  utilisauer *********************/
		edit_user.addActionListener(
                e -> {
                    connection_user conuse=  new  connection_user(logi_prio);
                    conuse.setVisible(true);
                    dispose();
                });

		list_user.addActionListener(
                e -> {
                    list_user luse=  new  list_user(logi_prio);

                    luse.setVisible(true);
                    dispose();
                });






        //  /***************************  gestion des  fournisseur *********************/
        edit_fournisseur.addActionListener(
                e -> {
                    fournisseur forn=  new  fournisseur(logi_prio);
                    forn.setVisible(true);
                    dispose();
                });

        list_fournisseur.addActionListener(
                e -> {
                    list_fourniss list_f=  new list_fourniss(logi_prio);

                    list_f.setVisible(true);
                    dispose();
                });





        //  /***************************  gestion des  codification *********************/
        edit_famille.addActionListener(
                e -> {
                    codification_gener famille=  new  codification_gener(logi_prio);
                    famille.setVisible(true);
                    dispose();
                });

        edit_reference.addActionListener(
                e -> {
                    codification_refer refere=  new codification_refer(logi_prio);

                    refere.setVisible(true);
                    dispose();
                });

        recherche_code.addActionListener(
                e -> {
                    recap_codification recap_code=  new recap_codification(logi_prio);
                    recap_code.setVisible(true);
                    dispose();
                });






        ///µ**************************gestion article ******************************/
        famille_art.addActionListener(
                e -> {
                    famille_article type=  new  famille_article(logi_prio);
                    type.setVisible(true);
                    dispose();
                });

        article_edit.addActionListener(
                e -> {
                    article art=  new article(logi_prio);

                    art.setVisible(true);
                    dispose();
                });

        list_art.addActionListener(
                e -> {
                    list_article liste_art=  new list_article(logi_prio);
                    liste_art.setVisible(true);
                    dispose();
                });





        ///µ**************************gestion produit ******************************/
        edit_produit.addActionListener(
                e -> {
                    produit prod=  new  produit(logi_prio);
                    prod.setVisible(true);
                    dispose();
                });

        list_prod.addActionListener(
                e -> {
                    list_produit list_pr=  new list_produit(logi_prio);
                    list_pr.setVisible(true);
                    dispose();
                });





        /////************************gestion etiquette TV
        imp_etiq_tv.addActionListener(
                e -> {
                    etiquette_tv etq_tv=  new etiquette_tv(logi_prio);
                    etq_tv.setVisible(true);
                    dispose();
                });

        netoyer.addActionListener(
                e -> {
                    netoyer_etq_tv net=  new netoyer_etq_tv(logi_prio);
                    net.setVisible(true);
                    dispose();
                });


        list_imp_tv.addActionListener(
                e -> {
                    list_etq_tv list_tv=  new list_etq_tv(logi_prio);
                    list_tv.setVisible(true);
                    dispose();
                });




        /////************************gestion etiquette TPE

        imp_etiq_tpe.addActionListener(
                e -> {
                    etiquette_tpe list_tv=  new etiquette_tpe (logi_prio);
                    list_tv.setVisible(true);
                    dispose();
                });

        liste_etiq_tpe.addActionListener(
                e -> {
                    list_etq_tpe list_tpe=  new list_etq_tpe (logi_prio);
                    list_tpe.setVisible(true);
                    dispose();
                });


        imp_emballage_tpe.addActionListener(
                e -> {
                    etiquette_embalage_tpe etq_embalage=  new etiquette_embalage_tpe (logi_prio);
                    etq_embalage.setVisible(true);
                    dispose();
                });


        imp_emballage_tpe_liste.addActionListener(
                e -> {
                    list_emballage_tpe etq_embalage=  new list_emballage_tpe (logi_prio);
                    etq_embalage.setVisible(true);
                    dispose();
                });




        /////************************gestion etiquette Tablette

        etq_tablette.addActionListener(
                e -> {
                    etiquette_tablette etq_tab=  new etiquette_tablette (logi_prio);
                    etq_tab.setVisible(true);
                    dispose();
                });


        list_tablette.addActionListener(
                e -> {
                    list_etq_tablette etq_tab=  new list_etq_tablette (logi_prio);
                    etq_tab.setVisible(true);
                    dispose();
                });


        etq_emballege_tablette.addActionListener(
                e -> {
                    etiquette_embalage_tablette emb_tablette=  new etiquette_embalage_tablette (logi_prio);
                    emb_tablette.setVisible(true);
                    dispose();
                });


        list_etq_emballege_tablette.addActionListener(
                e -> {
                    list_emballage_tablette etq_chariot=  new list_emballage_tablette (logi_prio);
                    etq_chariot.setVisible(true);
                    dispose();
                });


        etq_tablette_chariot.addActionListener(
                e -> {
                    etiquette_chariot etq_chariot=  new etiquette_chariot (logi_prio);
                    etq_chariot.setVisible(true);
                    dispose();
                });


        list_etq_tablette_chariot.addActionListener(
                e -> {
                    list_etq_chariot etq_chariot=  new list_etq_chariot (logi_prio);
                    etq_chariot.setVisible(true);
                    dispose();
                });



        ///////*******************etiquette composant*************************/



        etq_composant.addActionListener(
                e -> {
                    etiquette_composant etq_compo=  new etiquette_composant (logi_prio);
                    etq_compo.setVisible(true);
                    dispose();
                });


        list_composant.addActionListener(
                e -> {
                    list_etq_composants etq_chariot=  new list_etq_composants (logi_prio);
                    etq_chariot.setVisible(true);
                    dispose();
                });


      ////*********************************etiquette portable******************************/


        etq_portable.addActionListener(
                e -> {
                    etiquette_portable_forn etq_phone=  new etiquette_portable_forn (logi_prio);
                    etq_phone.setVisible(true);
                    dispose();
                });



        etq_portable.addActionListener(
                e -> {
                    etiquette_portable_forn etq_phone=  new etiquette_portable_forn (logi_prio);
                    etq_phone.setVisible(true);
                    dispose();
                });

        list_portable.addActionListener(
                e -> {
                    list_etq_portable etq_phone=  new list_etq_portable (logi_prio);
                    etq_phone.setVisible(true);
                    dispose();
                });




        etq_emballege_portable.addActionListener(
                e -> {
                    etiquette_emballage_portable etq_phone=  new etiquette_emballage_portable (logi_prio);
                    etq_phone.setVisible(true);
                    dispose();
                });


        list_emballege_portable.addActionListener(
                e -> {
                    list_emballage_portable etq_phone=  new list_emballage_portable (logi_prio);
                    etq_phone.setVisible(true);
                    dispose();
                });



        ////////////*************************autre etiquette*********************************/
        autre_etiquette.addActionListener(
                e -> {
                    logiciel_etiq.View.Etiquette.autre_etiquette etq_phone=  new autre_etiquette (logi_prio);
                    etq_phone.setVisible(true);
                    dispose();
                });


        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setPreferredSize(new Dimension(0, 40));
        Font font= new Font("Arial",  Font.ITALIC, 12);

        gestion_utilisateur.add(edit_user);
		gestion_utilisateur.add(list_user);
        jMenuBar.add(gestion_utilisateur);
        gestion_utilisateur.setFont(font);


        gestion_chaine.add(edit_chaine);
        jMenuBar.add(gestion_chaine);
        gestion_chaine.setFont(font);


        gestion_fournisseur.add(edit_fournisseur);
        gestion_fournisseur.add(list_fournisseur);
        jMenuBar.add(gestion_fournisseur);
        gestion_fournisseur.setFont(font);


        gestion_codification.add(edit_reference);
        gestion_codification.add(edit_famille);
        gestion_codification.add(recherche_code);
        jMenuBar.add(gestion_codification);
        gestion_codification.setFont(font);


        gestion_article.add(famille_art);
        gestion_article.add(article_edit);
        gestion_article.add(list_art);
        jMenuBar.add(gestion_article);
        gestion_article.setFont(font);



        gestion_produit.add(edit_produit);
        gestion_produit.add(list_prod);
        jMenuBar.add(gestion_produit);
        gestion_produit.setFont(font);





        imp.add(televisseur);
        imp.add(tpe);
        imp.add(tablette);
        imp.add(portable);
        imp.add(composant);
        imp.add(autre_etiquette);
        televisseur.add(imp_etiq_tv);
        televisseur.add(netoyer);
        televisseur.add(list_imp_tv);


        tpe.add(imp_etiq_tpe);
        tpe.add(liste_etiq_tpe);
        tpe.add(imp_emballage_tpe);
        tpe.add(imp_emballage_tpe_liste);




        tablette.add(etq_tablette);
        tablette.add(list_tablette);
        tablette.add(etq_emballege_tablette);
        tablette.add(list_etq_emballege_tablette);
        tablette.add(etq_tablette_chariot);
        tablette.add(list_etq_tablette_chariot);




        portable.add(etq_portable);
        portable.add(list_portable);
        portable.add(etq_emballege_portable);
        portable.add(list_emballege_portable);


        composant.add(etq_composant);
        composant.add(list_composant);


        jMenuBar.add(imp);

        imp.setFont(font);











        JLabel msg = new JLabel("<HTML> Bienvenue " + logi_prio + ", <br> veuillez entrer tout les information nécessaire.</HTML>");
		  setTitle("Gestion de production");
          setSize(1000, 600);
          setLocationRelativeTo(null);
          setVisible(true);




          setJMenuBar(jMenuBar);

        JPanel panel = generale.generalPanle();


         Font police2 = new Font(" Time New Roman", Font.BOLD|Font.ITALIC,27);



        msg.setFont(police2);
   		 Color c2 = new Color(255,255,255);//(redValue,greenValue,BlueValue)


   		 msg.setForeground(c2);
   		 msg.setBounds ( 140 , 180 , 500 , 100 ) ;
        	panel.add(msg);
        	panel.setLayout(null);
          panel.setBackground(new Color(104, 111, 140));
          panel.setOpaque(true);
          this.getContentPane().add(panel);
          this.setLayout(new BorderLayout());
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setContentPane(panel);

	}




}
		
