package logiciel_etiq.View.article;

import logiciel_etiq.Controller.gestion_article_famille;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import logiciel_etiq.View.tableau.Tableau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class list_article extends generale {


	private  Object [] entete={"Code/designation  article","Fournisseur","Code Produit","Prix","Modèle","Quantite","Position"};
	private  final Tableau tab=new Tableau(entete);
	private jcombo article_comb=new jcombo(list_comb);
	private   final JPanel paneltext =new JPanel();
	private	final JPanel pan=new JPanel();
	private	final JPanel pan1=new JPanel();
	private	final JPanel pan2=new JPanel();
	private	final JPanel pan3=new JPanel();
    private JPanel pan_codepro=new JPanel();

	private JPanel pan_codepro_jtext=new JPanel();

    private JTextField codeprod=new JTextField();
	private JLabel count=new JLabel();
	private  JScrollPane p=new JScrollPane(tab);

	 private ArrayList <String>list_article ;
	private static gestion_article_famille ref_art=new gestion_article_famille();




		    private JPanel pan_article=new JPanel();
			private JPanel pan_article_comb=new JPanel();


			public list_article(final String log){
				composant(log);
			}

		   public void composant(String log){

			JPanel panel = generalPanle();
			initFrame("Liste des articles", panel, this, log,"Liste des articles");


			replirArtCode(article_comb);

			labelStyleTable(count);
			StyleTable(this, tab);



			count.setText("Nombre Total : " +tab.table.getRowCount());

			article_comb.addActionListener(
			 e -> {


			removeTabRow(tab);

			if(article_comb.getItemCount()>1){
			 String[] parts = String.valueOf(article_comb.getSelectedItem()).split(" ");
			 String part1 = parts[0];
			 list_article=ref_art.select_produit_article(part1);
			 }

			remplirTab(list_article, 7,  tab,0);
			count.setText("Nombre Total : " +tab.table.getRowCount());


			 });
  	 
  	 
  	 
  	codeprod.addActionListener(
			e -> {


				removeTabRow(tab);

				list_article=ref_art.select_art_produit(codeprod.getText());
				remplirTab(list_article, 7,  tab,0);
			});





			actionMouseJtextField(codeprod);
			actionKeyJtextFieldSorter(codeprod, tab,  count) ;






	 ////************************ Add component to panel *************/

	   		 panel.add(paneltext);
	   		 panel.add(p);
	   		 panel.add(pan);

			 pan_article.add(pan_article_comb);
			 pan_article_comb.add(article_comb);
		     pan_codepro.add(pan_codepro_jtext);
		     pan_codepro_jtext.add(codeprod);

			 paneltext.add(pan_codepro);
			 paneltext.add(pan_article);
			 paneltext.add(pan3);

			 pan.add(count);
			 pan.add(pan1);



		////************************size component *************/
	    article_comb.setPreferredSize(new Dimension(200,30));
	    tab.setPreferredSize(new Dimension(40, 400));
	    paneltext.setPreferredSize(new Dimension(40, 90));
	    codeprod.setPreferredSize(new Dimension(200,30));

	    ////************************visibilite de panel*************/
		panel.setOpaque(false);
	    pan_article.setOpaque(false);
	    pan.setOpaque(false);
	    pan_article_comb.setOpaque(false);
	    paneltext.setOpaque(false);
	    pan1.setOpaque(false);
	    pan2.setOpaque(false);
	    pan3.setOpaque(false); 
	    pan_codepro.setOpaque(false);
	    pan_codepro_jtext.setOpaque(false);


	    ////************************layout de panel*************/
	    pan2.setLayout(null);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
		paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));



	    ////************************bordure de panel*************/
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    pan1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    pan_article.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 20));
	    pan_codepro.setBorder(BorderFactory.createEmptyBorder(10, -60, 20, 20));



    setContentPane(panel);
  
}

}












