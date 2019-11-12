package logiciel_etiq;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class menu extends JFrame{
	/****/
	private static final long serialVersionUID = 1L;
	private JMenuBar jMenuBar = new JMenuBar();
	private JMenu fiche = new JMenu("Fichier");
	
	
	
	private JMenu tache = new JMenu("Tache");
	private  JMenuItem edit_tache= new  JMenuItem("Edition des Taches");
	private  JMenuItem list_tache= new  JMenuItem("Liste des Taches");
	
	
	private JMenu poste = new JMenu("Poste");
	private  JMenuItem edit_poste= new  JMenuItem("Edition des Postes");
	private  JMenuItem list_poste= new  JMenuItem("Liste des Postes");
	private  JMenuItem edit_comp= new  JMenuItem("Edition des Composants");
	
	private  JMenuItem open= new  JMenuItem("ouvrire");
	private JMenu util = new JMenu("Gestion des Utilisateurs  ");
    private JMenuItem edit = new JMenuItem("Edition");
    private JMenuItem edit1 = new JMenuItem("Liste Utilisateur");
	private JMenuItem prod = new JMenu("Production ");
	private JMenuItem ptem = new JMenuItem("Chaine  ");
	private JMenuItem ptem1 = new JMenuItem("Produit ");
	private JMenu fourniss = new JMenu("Fournisseur  ");
    private JMenuItem fourniss_edit = new JMenuItem("Edition fournisseur");
    private JMenuItem fourniss_consu = new JMenuItem("Liste fournisseur");
    
    private JMenu imp = new JMenu("Imprime");
   // private JMenuItem imp_cod = new JMenuItem("Fiche Suiveuse");
    private JMenuItem imp_etiq = new JMenuItem("Etiquette");
    private JMenuItem net = new JMenuItem("Notoyer etiquette");
    private JMenuItem list_imp = new JMenuItem("Liste Fiche Suiveuse");
    private JMenuItem imp_phon = new JMenuItem("Etiquette Portable");
    private JMenuItem list_port = new JMenuItem("Liste etiquette portable");
    private JMenuItem port_fourn = new JMenuItem("etiquette portable fournisseur");
    private JMenuItem list_port_fourn = new JMenuItem("List etiquette portable fournisseur");

    private JMenuItem imp_phon_embalage = new JMenuItem("Etiquette Emballage");
    private JMenuItem imp_phon_embalage_list = new JMenuItem("Liste Etiquette Emballage");
    
    private JMenuItem etiquette_embalage_tpe = new JMenuItem("Etiquette Emballage TPE");
    private JMenuItem etiquette_caution_tpe = new JMenuItem("Etiquette Caution TPE");
    private JMenuItem etiquette_tpe = new JMenuItem("Etiquette  TPE");

    
    private JMenuItem list_tpe = new JMenuItem("Liste  Emballage TPE");
    private JMenuItem list_etq_tpe = new JMenuItem("Liste Etiquette TPE");
    private JMenuItem list_palette = new JMenuItem("Liste Palette");

    
    private  JMenu televisseur= new  JMenu("Etiquette Téléviseur");
    private  JMenu portable= new  JMenu("Etiquette Portable");
    private  JMenu tpe= new  JMenu("Etiquette TPE");
    private  JMenu composant= new  JMenu("Etiquette Composant");
    private  JMenu palette= new  JMenu("Etiquette Palette");
    private  JMenu recherche= new  JMenu("Recherche");

    private JMenuItem rech_detail = new JMenuItem("Recherche detaillé");

    
    private JMenuItem etiquette_composant = new JMenuItem("Etiquette Composant");
    private JMenuItem list_composant = new JMenuItem("List Composant");

    
	private JMenu perso = new JMenu("Personelle  ");
    private JMenuItem perso_edit = new JMenuItem("Edition Personelles");
    private JMenuItem perso_consu = new JMenuItem("Liste Personelles");
    
 	private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";    
 	
	private JMenu zone = new JMenu(" Zones  ");
    private JMenuItem zon_edit = new JMenuItem("Edition");
    private JMenuItem zon_consu = new JMenuItem("Liste Zones");
	private JMenu chaine = new JMenu(" Chaine  ");
    private JMenuItem chai_edit = new JMenuItem("Edition");
    private JMenuItem chang_chaine = new JMenuItem("Changement Chaine");
    private JMenuItem list_chaine = new JMenuItem("Recherche");
    
    private JMenu codification = new JMenu(" Codification ");
	private  JMenuItem cod_ref= new  JMenuItem("Code réfèrence");
	private  JMenuItem cod_gener= new  JMenuItem("Code Famille");
	private  JMenuItem rech= new  JMenuItem("Recherche");

	
	    private JMenu article = new JMenu("Article");
		private  JMenuItem famille_art= new  JMenuItem("Type article");
		private  JMenuItem article_edit= new  JMenuItem("Edition article");
		private  JMenuItem imp_bom= new  JMenuItem("Imprimer BOM");
		private  JMenuItem imp_process= new  JMenuItem("Imprimer fiche processe");
		private  JMenuItem imp_effect= new  JMenuItem("Imprimer effective produit");
		private  JMenuItem list_art= new  JMenuItem("Recherche article");
		
		
		    private JMenu lanc_prod = new JMenu("Lancement produits");
			private  JMenuItem prod_a= new  JMenuItem("Produit a produire");
			private  JMenuItem prod_z= new  JMenuItem("Zone Process");
			private  JMenuItem prod_t= new  JMenuItem("tache Process");
			private  JMenuItem prod_c= new  JMenuItem("Chaine Process");
			private  JMenuItem prod_o= new  JMenuItem("Edition Otillage");



    private JMenuItem palette_tpe = new JMenuItem("Palette  TPE");
    private JMenuItem list_palette_portable = new JMenuItem("List Palette Portable");
			
		    private JMenuItem imp_palette = new JMenuItem("Etiquette palette");

		 private JMenu prof = new JMenu("Profils");
		 private JMenuItem edit_prof = new JMenuItem("Editions Profils");
		 
		  ArrayList<String> resultat=new ArrayList<String>();
    JPanel panel;
    JLabel msg;
 	String MonSigle ;
    String MonExercice,cde;
    public String Chemin = "c:\\GCOBAR\\";
	public  String bdd = Chemin+Utilitaire.InitBdd()+".accdb";	
    static gestion_user use=new gestion_user();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {				 

					menu frame = new menu("7");
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public menu(final String logi_prio ) {
		  this.addWindowListener(new WindowAdapter(){
		        public void windowClosing(WindowEvent e){

		   new File("C:\\GCOBAR\\monfichier.txt").delete();
		   int reponse = JOptionPane.showConfirmDialog(
	                null, "Voulez-vous quitter définitivement l'application ?",
	                "Confirmation",
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE);

	  	if (reponse==JOptionPane.YES_OPTION){
	  		// System.out.println(prv.size()-1+"avant"+prv.get(prv.size()-1)+"    "+prv.toString());
	  		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    dispose();
	     
	           
//			zone fra = new zone("7");
//			fra.setVisible(true);
			//set default close operation

			 
	  	}
	  	else if (reponse==JOptionPane.NO_OPTION){
	     	   setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);	  }
	  	else{
	     	   setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);	  }}});
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
	    setIconImage(img);


	   try{
	   		UIManager.setLookAndFeel(laf);
	   		SwingUtilities.updateComponentTreeUI(this);

	   	}catch(Exception e1){

	   		e1.printStackTrace();
	   	}

	   if( !selectioncomb.prv.contains("menu"))
	   selectioncomb.prv.add("menu");
	   msg=new JLabel("<HTML> Bienvenue "+logi_prio +", <br> veuillez entrer tout les information nécessaire.</HTML>");
		  setTitle("Gestion de production");
          setSize(1000, 600);
          setLocationRelativeTo(null);
          setVisible(true);

          resultat=use.selection_profils(logi_prio);
         // System.out.println(resultat);
        //  jMenuBar.add(fiche);

          fiche.add(open);

          if(resultat.get(28).equals("1"))
          jMenuBar.add(prof);
          prof.add(edit_prof);

        //  jMenuBar.add(tache);

          tache.add(edit_tache);
          tache.add(list_tache);
//          if(resultat.get(0).equals("1"))
//          jMenuBar.add(poste);

//          if(resultat.get(2).equals("1"))
//          jMenuBar.add(zone);

//          if(resultat.get(1).equals("1"))
//          jMenuBar.add(chaine);



//  		  if(resultat.get(3).equals("1"))
//          jMenuBar.add(perso);

        // jMenuBar.add(compo);
         // jMenuBar.add(prod);

//  		if(resultat.get(14).equals("1"))
//          poste.add(edit_poste);
//  		if(resultat.get(15).equals("1"))
//          poste.add(list_poste);


         // compo.add(edit_comp);
//        if(resultat.get(29).equals("1"))
//          zone.add(zon_edit);
//        if(resultat.get(30).equals("1"))
//          zone.add(zon_consu);

//          if(resultat.get(16).equals("1"))
//          chaine.add(chai_edit);
//
//          if(resultat.get(18).equals("1"))
//          chaine.add(chang_chaine);
//          if(resultat.get(17).equals("1"))
//          chaine.add(list_chaine);
//          if(resultat.get(19).equals("1"))
//          perso.add(perso_edit);
//          if(resultat.get(20).equals("1"))
//          perso.add(perso_consu);
          //jMenuBar.add(prfini);


          if(use.admin(logi_prio)==true){
        if(resultat.get(4).equals("1"))
          jMenuBar.add(util);
        if(resultat.get(21).equals("1"))
          util.add(edit);
        if(resultat.get(22).equals("1"))
          util.add(edit1);
          }
          if(resultat.get(13).equals("1"))
          jMenuBar.add(fourniss);

          if(resultat.get(24).equals("1"))
          fourniss.add(fourniss_edit);
          if(resultat.get(25).equals("1"))
          fourniss.add(fourniss_consu);

  		  if(resultat.get(5).equals("1"))
          jMenuBar.add(codification);
          if(resultat.get(6).equals("1"))
          codification.add(cod_ref);
          if(resultat.get(7).equals("1"))
          codification.add(cod_gener);

          if(resultat.get(23).equals("1"))
          codification.add(rech);
          if(resultat.get(8).equals("1"))
          jMenuBar.add(article);

          if(resultat.get(9).equals("1"))
          article.add(famille_art);

          if(resultat.get(26).equals("1"))
          article.add(article_edit);
          if(resultat.get(10).equals("1"))
          jMenuBar.add(imp);
//          if(resultat.get(11).equals("1"))
//          imp.add(imp_etiq);
         // imp.add(imp_process);
//          if(resultat.get(12).equals("1"))
//          imp.add(imp_cod);
//          if(resultat.get(27).equals("1"))
//          imp.add(list_imp);

        //  imp.add(net);
          jMenuBar.add(lanc_prod);
          lanc_prod.add(prod_a);
          //lanc_prod.add(prod_z);
         // lanc_prod.add(prod_t);
         // lanc_prod.add(prod_o);

          imp.add(televisseur);
          imp.add(portable);
          imp.add(tpe);
          imp.add(composant);
      //    imp.add(palette);
          imp.add(recherche);
          recherche.add(rech_detail);

          composant.add(etiquette_composant);
          composant.add(list_composant);

          //Ajout d'un séparateur
          imp.addSeparator();




          televisseur.add(imp_etiq);
          televisseur.add(list_imp);
          televisseur.add(net);


          portable.add(imp_phon);
          portable.add(list_port);
          portable.add(port_fourn);
          portable.add(list_port_fourn);
          portable.add(imp_phon_embalage);
          portable.add(imp_phon_embalage_list);


          tpe.add(etiquette_embalage_tpe);
          tpe.add(list_tpe);
          tpe.add(etiquette_tpe);
          tpe.add(list_etq_tpe);


        //  palette.add(imp_palette);
        //  palette.add(list_palette);

          tpe.add(etiquette_caution_tpe);
        //  imp.add(imp_bom);
         // imp.add(imp_effect);
          article.add(list_art);




        portable.add(list_palette_portable);
        tpe.add(palette_tpe);


        rech_detail.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  Recherche_general caut=  new  Recherche_general(logi_prio);
                	  caut.setVisible(true);
                  dispose();
                  } });
        palette_tpe.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        list_palette_tpe pal=  new  list_palette_tpe(logi_prio);
                        pal.setVisible(true);
                        dispose();
                    } });


        list_palette_portable.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        list_palette_portable phonpallete;
                        phonpallete = new  list_palette_portable(logi_prio);
                        phonpallete.setVisible(true);
                        dispose();
                    } });

          imp_palette.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  etiquette_palette caut=  new  etiquette_palette(logi_prio);
                	  caut.setVisible(true);
                  dispose();
                  } });

          list_palette.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	 list_etqpalette caut=  new  list_etqpalette(logi_prio);
                	  caut.setVisible(true);
                  dispose();
                  } });


          etiquette_caution_tpe.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  caution caut=  new  caution(logi_prio);
                	  caut.setVisible(true);
                  dispose();
                  } });

          etiquette_composant.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  etiquette_composant composant=  new  etiquette_composant(logi_prio);
                	  composant.setVisible(true);
                  dispose();
                  } });

          etiquette_tpe.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  etiquette_tpe ttpe=  new  etiquette_tpe(logi_prio);
                	  composant.setVisible(true);
                  dispose();
                  } });


          list_etq_tpe.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	 list_etq_tpe list_tpe;
					try {
						list_tpe = new  list_etq_tpe(logi_prio);
	                	 list_tpe.setVisible(true);

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                  dispose();
                  } });


          list_composant.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	 list_composant list_composant=  new  list_composant(logi_prio);
                	 list_composant.setVisible(true);
                  dispose();
                  } });



          list_tpe.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	 list_tpe list_tpe;
					try {
						list_tpe = new  list_tpe(logi_prio);
	                	 list_tpe.setVisible(true);

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                  dispose();
                  } });


          //lanc_prod.add(prod_c);
          //imp.disable();

          port_fourn.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  etiquette_portable_forn phon=  new  etiquette_portable_forn(logi_prio);
                  phon.setVisible(true);
                  dispose();
                  } });

          etiquette_embalage_tpe.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  etiquette_embalage_tpe tpe=  new  etiquette_embalage_tpe(logi_prio);
                  tpe.setVisible(true);
                  dispose();
                  } });


          list_port_fourn.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  list_etq_emei phon;
					try {
						phon = new  list_etq_emei(logi_prio);
		                  phon.setVisible(true);

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                  dispose();
                  } });



          imp_phon_embalage.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  etiquette_embalage phon=  new  etiquette_embalage(logi_prio);
                  phon.setVisible(true);
                  dispose();
                  } });

          imp_phon_embalage_list.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  list_etqemballage phon;
					try {
						phon = new  list_etqemballage(logi_prio);
		                  phon.setVisible(true);

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                  dispose();
                  } });


          imp_phon.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  etiquette_phone phon=  new  etiquette_phone(logi_prio);
                  phon.setVisible(true);
                  dispose();
                  } });




          list_port.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  list_portable phon=  new  list_portable(logi_prio);
                  phon.setVisible(true);
                  dispose();
                  } });


          imp_effect.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  imp_zone_produit art=  new  imp_zone_produit(logi_prio);

                  art.setVisible(true);
                  dispose();
                  } });


          net.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  netoyer netoy=  new  netoyer(logi_prio);
                  netoy.setVisible(true);
                  dispose();
                  } });

          list_art.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  list_article art=  new  list_article(logi_prio);

                  art.setVisible(true);
                  dispose();
                  } });

          prod_o.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  outillage outil=  new  outillage(logi_prio);

                  outil.setVisible(true);
                  dispose();
                  } });
          imp_process.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  imp_process bom=  new  imp_process(logi_prio);

                  bom.setVisible(true);
                  dispose();
                  } });

          imp_bom.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  imprimer_bom bom=  new  imprimer_bom(logi_prio);

                  bom.setVisible(true);
                  dispose();
                  } });


          edit_tache.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  tache tac=  new  tache(logi_prio);

               	  tac.setVisible(true);
                  dispose();
                  } });


          prod_a.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                   produit prod=  new  produit(logi_prio);
                   prod.setVisible(true);
                  dispose();
                  } });

          prod_t.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                   process_tache prod_t=  new  process_tache(logi_prio);
                   prod_t.setVisible(true);
                  dispose();
                  } });

          prod_z.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                   zone_process prod_z=  new  zone_process(logi_prio);
                   prod_z.setVisible(true);
                  dispose();
                  } });

          prod_c.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  // chaine_process prod_c=  new  chaine_process(logi_prio);
                   //prod_c.setVisible(true);
                  dispose();
                  } });

          edit_prof.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  profils four=  new  profils(logi_prio);

               	  four.setVisible(true);
                  dispose();
                  } });



          fourniss_edit.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                  fournisseur four=  new  fournisseur(logi_prio);

               	  four.setVisible(true);
                  dispose();
                  } });

          rech.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  recap_codification recap=  new  recap_codification(logi_prio);

                   	  recap.setVisible(true);
                	  dispose();
                	  }
                  });
          fourniss_consu.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  list_fourniss lfou=  new  list_fourniss(logi_prio);

                   	  lfou.setVisible(true);
                	  dispose();
                	 }
                  });
//
//         imp_cod.addActionListener(
//                  new ActionListener() {
//                  public void actionPerformed(ActionEvent e) {
//                	  imprime fiche=  new  imprime(logi_prio);
//                   	  fiche.setVisible(true);
//                	  dispose();
//                	  }
//                  });
         imp_etiq.addActionListener(
                 new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                	 etiquette etq=  new  etiquette(logi_prio);
                  	 etq.setVisible(true);
               	  dispose();
               	 }
                 });

         list_imp.addActionListener(
                 new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                	 list_fiche lfiche;
					try {
						lfiche = new  list_fiche(logi_prio);
	               	     lfiche.setVisible(true);

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
               	    dispose();
               	 }
                 });

          cod_ref.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  codification_refer ref=  new  codification_refer(logi_prio);

                   	  ref.setVisible(true);
                	  dispose();
                	  }
                  });

          cod_gener.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {

                	  codification_gener ref=  new  codification_gener(logi_prio);

                   	  ref.setVisible(true);

                	  dispose();
                  }
                  });
          famille_art.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  famille_article famill=  new  famille_article(logi_prio);

                	  famill.setVisible(true);
                	  dispose();}
                  });
         article_edit.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  article art=  new  article(logi_prio);

                	  art.setVisible(true);
                	  dispose();
                  }
                  });

          list_chaine.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  recap recp=  new  recap(logi_prio);

                	  recp.setVisible(true);
                	  dispose();
                	}
                  });

          chai_edit.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  Chaine chaine=  new  Chaine(logi_prio);

                	  chaine.setVisible(true);
                	  dispose();
                	  }
                  });
          chang_chaine.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  changement_chaine chang_chaine=  new  changement_chaine(logi_prio);

                	  chang_chaine.setVisible(true);
                	  dispose();
                	  }
                  });
          perso_consu.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  list_personelle lperso=  new  list_personelle(logi_prio);

                	  lperso.setVisible(true);
                	  dispose();
                	  }
                  });

          list_poste.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  list_poste lposte=  new  list_poste(logi_prio);

                	  lposte.setVisible(true);
                	  dispose();
                	 }
                  });
          perso_edit.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  personelle perso=  new  personelle(logi_prio);

                	  perso.setVisible(true);
                	  dispose();
                	 }
                  });
          edit.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  connection_user conuse=  new  connection_user(logi_prio);
                	  conuse.setVisible(true);
                	  dispose();
                	 }
                  });

          edit_comp.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	//  new composants(logi_prio1).setVisible(true);
                	  dispose();}
                  });

          edit_poste.addActionListener(
                  new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                	  poste1 post=  new  poste1(logi_prio);

                	  post.setVisible(true);
                	  dispose();
                	  }
                  });

	 	 edit1.addActionListener(
                 new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                list_user luse=  new  list_user(logi_prio);

               	luse.setVisible(true);
               	  dispose();
                	}
                 });



	 	 zon_edit.addActionListener(
                 new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                   zone zone=  new  zone(logi_prio);
                 //  selectioncomb.varbool=true;
                   zone.setVisible(true);
                   dispose();
              }
                 });

	 	zon_consu.addActionListener(
                new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	 zone_liste zcons=  new  zone_liste(logi_prio);
                	 zcons.setVisible(true);
               	      dispose();
               }
                });

          prod.add(ptem);
          prod.add(ptem1);
          fiche.add(open);

          //fiche.setIcon("data/i" );
           open.addActionListener(
                 new ActionListener() {public void actionPerformed(ActionEvent e) {
                	 menuOuvrirActionPerformed(e);
                	 }});
          setJMenuBar(jMenuBar);
      	JPanel panel = new JPanel(){
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){
            ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
            img.paintIcon(this, g,0, 0);
			//g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
}  };


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




	 private void menuOuvrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOuvrirActionPerformed
		  String Chemin = "c:\\GCOBAR\\";
			 String baseName = Chemin+Utilitaire.InitBdd()+".accdb";	
 	     	 CConnect.getInstance(bdd);        	

	 	    JFileChooser open1 = new JFileChooser();
	 	    open1.setDialogTitle("Ouvrir la base...");
	 	    open1.setCurrentDirectory(new File("C:/IE"));
	 	    int retour = open1.showOpenDialog(this);
	 	    if(retour == JFileChooser.APPROVE_OPTION)
	 	    {	 	    CConnect.close();

	 	        baseName = open1.getSelectedFile().getAbsolutePath();
	 	        baseName = baseName.replace("\\", "\\\\");
	 	        bdd = baseName;
	 	     
	 	     	 
	 	     	 CConnect.getInstance(bdd);        	
	 		          String query = "SELECT Cde,Sigle, Exercice FROM Unite ";
	 		        cde = CConnect.Requete(query,bdd).get(0);
	 		        MonSigle = CConnect.Requete(query,bdd).get(1);
	 		        MonExercice  = CConnect.Requete(query,bdd).get(2);
	 	   
	 	    }
	 	}
}
		
 