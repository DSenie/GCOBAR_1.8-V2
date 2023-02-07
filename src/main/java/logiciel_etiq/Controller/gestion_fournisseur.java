package logiciel_etiq.Controller;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;


public class gestion_fournisseur {
	public String nom,code;
	private ArrayList<String> resultat= new ArrayList<>() ;


	private boolean existe_fournisseur(String code, String nom) {
		String query = "SELECT * FROM fournisseur where nom_fourniss='"+ nom + "' and code_fourniss<>'"+ code + "' ";
		if (CConnect.Requete(query).size() >= 1) {
			JOptionPane.showMessageDialog(null, "Ce fournisseur existe déjà");
			return true;
		}
		return false;

	}

	public ArrayList<String> select_fournisseur_code(){
		String query5 = "SELECT code_fourniss,nom_fourniss FROM fournisseur" ;
		if(CConnect.Requete(query5).size()>1){
			resultat=CConnect.Requete(query5);
		}
		return resultat;

	}


	public String afficher_code_fourniss()
	{   String code;
		NumberFormat formatter = new DecimalFormat("00");
		String query2 = "SELECT max(code_fourniss) FROM fournisseur";
		ArrayList<String> list_afficher=CConnect.Requete(query2);
		if(list_afficher.get(0)!=null){
			int cz=Integer.parseInt(list_afficher.get(0).substring(2))+1;
			code="FO"+formatter.format(cz);
		}
		else code="FO01";

		return code;
	}

	public void delete_fournisseur(String code, String intitule){

		int reponse = JOptionPane.showConfirmDialog(null,
				"Voulez-vous vraiment supprimer "+intitule+" ",
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (reponse== JOptionPane.YES_OPTION){
			String update2= "delete from fournisseur where code_fourniss='"+code+"'";
			CConnect.Insert(update2);
			JOptionPane.showMessageDialog(null,"Le fournisseur a été bien supprimé");

		}}




	public void setupdate_fourniss(String code,String designation){
        if(!existe_fournisseur( code,designation)) {
			String update = "update  fournisseur set nom_fourniss='" + designation + "' where code_fourniss='" + code + "'";
			CConnect.Insert(update);
			JOptionPane.showMessageDialog(null, "Le fournisseur a été bien modifier");
		}
	}


	public void ajouter_fourniss(String code,String intitule)
	{
	    if(!existe_fournisseur(code,intitule)) {
		String insert = "insert into fournisseur(code_fourniss,nom_fourniss) values('" + code + "','" + intitule + "')";
		CConnect.Insert(insert);
		JOptionPane.showMessageDialog(null,"Le fournisseur a été bien ajouté");

	}
	}










	public boolean select_fournisseur_jtext(String four)
	{
		 String query = "SELECT nom_fourniss,code_fourniss FROM fournisseur where nom_fourniss='"+four+"'";
		ArrayList <String>list_fourniss=CConnect.Requete(query);
		  if( list_fourniss.size()>=1){
			  code= list_fourniss.get(1);
			  nom= list_fourniss.get(0);
		      	return true;
		    }

		    return false;
	}



	public ArrayList<String> select(){
		String query5 = "SELECT  code_fourniss,nom_fourniss FROM fournisseur" ;
		return CConnect.Requete(query5);
	}
	

		

}
