package logiciel_etiq.Controller;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;


public class gestion_article_famille {

	public String code_type,des_type;
	public static String  designation="";
	public static String date_deplacement;
	public static String description="";
	public static String designation_ref;
	public static String code_ref;
	public static String code_gener;
	public static String designation_gener;
	public static String code_famille;
	public static String designation_famille;
	public static String code_article;
	public static String model;

	public static int coeff=1,indice=0;
	private ArrayList <String> resultat= new ArrayList<>() ;

	/////////////////////////////// type de l'article/////////////////////////////////////////////////////////////////////////////
	public ArrayList<String> select_artfamille_code(){
		String query5 = "SELECT code_famille,designation FROM famille_article" ;
		if(CConnect.Requete(query5).size()>1){

			resultat=CConnect.Requete(query5);
		}
		return resultat;
	}


	public String afficher_code_artfamille()
	{
		String code;

		NumberFormat formatter = new DecimalFormat("00");
		String query2 = "SELECT max(code_famille) FROM famille_article";
		ArrayList <String>list_code_famille=CConnect.Requete(query2);
		if(list_code_famille.get(0)!=null){
			int cz=Integer.parseInt(list_code_famille.get(0).substring(1))+1;
			code="F"+formatter.format(cz);
		}
		else code="F01";
		return code;
	}

	public void delete_artfamille(String code, String intitule){

		int reponse = JOptionPane.showConfirmDialog(null,
				"Voulez-vous vraiment supprimer "+intitule+" ",
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (reponse== JOptionPane.YES_OPTION){
			String update2= "delete from famille_article where code_famille='"+code+"'";
			CConnect.Insert(update2);
			JOptionPane.showMessageDialog(null,"Le type article a été bien supprimé");

		}}

	private boolean existe_artfamille(String code, String des) {
		String query = "SELECT * FROM famille_article where designation='"+ des + "' and code_famille<>'"+ code + "' ";
		if (CConnect.Requete(query).size() >= 1) {
			JOptionPane.showMessageDialog(null, "Ce type article existe déjà");
			return true;
		}
		return false;

	}

	public void setupdate_artfamille(String code,String designation){
		if(!existe_artfamille( code,designation)) {
			String update = "update  famille_article set designation='" + designation + "' where code_famille='" + code + "'";
			CConnect.Insert(update);
			JOptionPane.showMessageDialog(null, "Le type article a été bien modifier");
		}
	}

//////////////////////////////////////////////ajouter les famille///////////////////////////////////////////////////////////////////////////////////////

	public void ajouter_artfamille(String code,String designation)
	{
		if(!existe_artfamille(code,designation)) {
			String insert = "insert into famille_article(code_famille,designation) values('"+code+"','"+designation+"')";
			CConnect.Insert(insert);
			JOptionPane.showMessageDialog(null,"Le type article a été bien ajouté");
		}

	}


	public boolean type(String poste)
	{
		String query = "SELECT designation,code_famille FROM famille_article where designation='"+poste+"'";
		ArrayList <String>list_type=CConnect.Requete(query);
		if( list_type.size()>=1){
			code_type=list_type.get(1);

			des_type=list_type.get(0);
			return true;
		}
		return false;

	}








	///**************************article*********************************/
	public ArrayList<String> select_article_code( ){
		String query5 = "SELECT code_article,designation FROM article " ;
		if(CConnect.Requete(query5).size()>=1){
			resultat=CConnect.Requete(query5);
		}
		return resultat;
	}

	public ArrayList<String> select_art_produit(String code){

		String query5 = "SELECT article.code_article||' '||designation ,code_fournisseur,codep_fourniss||' '||fournisseur.nom_fourniss "
				+ ",article_produit.model,position,pu,quantite"
				+ " FROM article,article_produit,fournisseur "
				+ "where  article_produit.code_fournisseur='"+code+"' and article.code_article=article_produit.code_article"
				+ " and fournisseur.code_fourniss=article_produit.codep_fourniss " ;
		return CConnect.Requete(query5);

	}


	public ArrayList<String> model_imp(){
		String query = "SELECT * FROM model_imp";
		return CConnect.Requete(query);
	}


	public void setdelete(String code_article,String designation){
		int reponse = JOptionPane.showConfirmDialog(null,
				"Voulez-vous vraiment supprimer "+designation+"",
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (reponse== JOptionPane.YES_OPTION){

			String update= "delete  from  article where code_article='"+code_article+"'";
			CConnect.Insert(update);
			delete_nomoclature(code_article);
			delete_produit(code_article);
			JOptionPane.showMessageDialog(null,""+designation+" a été bien supprimé");
		}

	}



	public void setupdate(String designation,String date_deplacement,
						  String description,String code_ref,String code_gener,String code_article,String code_famille,
						  int coef,int indice,String model){



			designation=designation.replace("'", "''");
			description=description.replace("'", "''");

			String update= "update article set designation='"+designation+"'"
					+ ",code_ref='"+code_ref+"'"
					+ " ,code_gener='"+code_gener+"',code_famille='"+code_famille+"',"
					+ " date_deplacement='"+date_deplacement+"'"
					+ ",description='"+description+"', coeff="+coef+" "
					+ " , indice="+indice+",model='"+model+"' "
					+ "where code_article='"+code_article+"'";


			CConnect.Insert(update);
			JOptionPane.showMessageDialog(null,""+designation+" a été bien modifié");


	}


	public void delete_nomoclature(String code){
			String update2= "delete  from  nomoclature where code_article='"+code+"'";
			CConnect.Insert(update2);
	}






	public void delete_produit(String code){
			String update2= "delete  from  article_produit where code_article='"+code+"' ";
			CConnect.Insert(update2);
	}





   public static boolean exist_article(String code, String des){
	   String query = "SELECT code_article,designation FROM article where code_article<>'"+code+"' and designation='"+des+"'";
	   System.out.println(query);
	   return CConnect.Requete(query).size() !=0;
   }

	public void update_nomoclature(String codep,String coden,int qte){
			String insert2 = "insert into nomoclature"
					+ " (code_article,code_nomo_art,quantite) values('" + codep + "','" + coden + "'," + qte + ")";
			CConnect.Insert(insert2);

	}



	public void ajouter_prod_fourniss(String code_art,String codef,String model,float pu,int qte,String position){
			String insert2 = "insert into article_produit"
					+ " (code_article,code_fournisseur,pu,quantite,model,position)"
					+ " values('" + code_art + "','" + codef + "'," + pu + "," + qte + ",'" + model + "','" + position + "')";
			CConnect.Insert(insert2);


	}


	public void setinsert(String designation,String date_deplacement,
						  String description,String code_ref,String code_gener,String code_article,String code_famille,
						  int coef,int indice,String model ){


			designation=designation.replace("'", "''");
			description=description.replace("'", "''");

			String insert = "insert into article (code_article,designation,code_ref,code_gener,code_famille,"
					+ "date_deplacement,description,coeff,indice,model)"
					+ " values('"+code_article+"','"+designation+"','"+code_ref+"','"+code_gener+"','"+code_famille+"',"
					+ "'"+date_deplacement+"','"+description+"',"+coef+","
					+ ""+indice+",'"+model+"')";
			CConnect.Insert(insert);

			JOptionPane.showMessageDialog(null,""+designation+" a été bien ajouté");



	}



	public static boolean  selection(String code){

		String query = "SELECT designation,date_deplacement,description,code_ref,code_gener,code_article"
				+ " , code_famille,coeff,indice,model FROM article where code_article='"+code+"' or designation='"+code+"'";

		ArrayList<String> list_article=CConnect.Requete(query);
		if(list_article.size()>=1){
			designation= list_article.get(0);
			date_deplacement= list_article.get(1);
			description= list_article.get(2);
			code_ref= list_article.get(3);
			code_article= list_article.get(5);
			code_famille= list_article.get(6);
			code_gener= list_article.get(4);

			coeff=Integer.parseInt(list_article.get(7));
			if(list_article.get(8)!=null)
				indice=Integer.parseInt(list_article.get(8));

			if(list_article.get(9)!=null)
			model =list_article.get(9);


			String query2 = "SELECT designation FROM reference where code_ref="+code_ref+"";
			ArrayList<String> list_refer=CConnect.Requete(query2);
			if(list_refer.size()>=1){
				designation_ref= list_refer.get(0);
			}


			String query3 = "SELECT designation FROM categ_composant where code_gener='"+code_gener+"'";
			ArrayList<String> list_gener=CConnect.Requete(query3);
			if(list_gener.size()>=1){
				designation_gener= list_gener.get(0);
			}

			String query4 = "SELECT designation FROM famille_article where code_famille='"+code_famille+"'";
			ArrayList<String> list_famil=CConnect.Requete(query4);
			if(list_famil.size()>=1){
				designation_famille= list_famil.get(0);
			}


			return true;

		}
		return false;

	}





	public ArrayList<String> select_produi_art(String code){

		String query5 = "SELECT code_fournisseur "
				+ ",article_produit.model,position,pu,quantite"
				+ " FROM article,article_produit "
				+ "where  article_produit.code_article='"+code+"' and article.code_article=article_produit.code_article"
				+ " " ;

		return CConnect.Requete(query5);
	}



	public ArrayList<String> select_nomo(String code){
		String query5 = "SELECT nomoclature.code_nomo_art||' '||article.designation ,nomoclature.quantite FROM article,nomoclature "
				+ "where  nomoclature.code_article='"+code+"' and article.code_article=nomoclature.code_nomo_art " ;
		return CConnect.Requete(query5);
	}

	public ArrayList<String> select_model(){
		String query5 = "SELECT distinct code_produit "
				+ " FROM produit ";
		return CConnect.Requete(query5);
	}








		
	///********************* LISTE ARTICLE
		public ArrayList<String> select_produit_article(String code){

			    String query5 = "SELECT article.code_article||' '||article.designation ,code_fourniss||' '||fournisseur.nom_fourniss, code_fournisseur,  "
			    		+ "pu, article_produit.model,quantite,position"
			    		+ " FROM article,article_produit,fournisseur,produit "
			    		+ "where  article_produit.code_article='"+code+"' and article.code_article=article_produit.code_article"
			    				+ " and fournisseur.code_fourniss=produit.fournisseur  and article_produit.model=produit.code_produit " ;
		        return CConnect.Requete(query5);

		}

}

