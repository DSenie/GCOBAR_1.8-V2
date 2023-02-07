package logiciel_etiq.Controller;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

import static logiciel_etiq.View.generale.date_traiter;


public class gestion_produit {

	public String code, desin, dimension, fournisseur, quantite, type, datelc, datelanc, Ncontrat;

	private ArrayList<String> resultat = new ArrayList<>();

	public ArrayList<String> select_produit_type() {
		String query5 = "SELECT distinct type FROM produit";
		if (CConnect.Requete(query5).size() > 1) {
			resultat = CConnect.Requete(query5);
		}
		return resultat;
	}

	public ArrayList<String> select_produit_code() {
		String query5 = "SELECT code_produit,designation FROM produit";
		if (CConnect.Requete(query5).size() > 1) {
			resultat = CConnect.Requete(query5);

		}

		return resultat;
	}

	public boolean select_produit(String code_p) {
		System.out.println(code_p);
		String query5 = "SELECT code_produit,designation,dimension,date_lc,date_lancement,type" +
				", f.code_fourniss,f.nom_fourniss , qte_produire, Ncontrat  " +
				" FROM produit,fournisseur f  where code_produit='" + code_p + "' and  f.code_fourniss=produit.fournisseur ";

		ArrayList<String> resultat = CConnect.Requete(query5);
		if (resultat.size() >= 1) {
			code = resultat.get(0);
			desin = resultat.get(1);
			dimension = resultat.get(2);
			fournisseur = resultat.get(6) + " " + resultat.get(7);
			datelc = resultat.get(3);
			datelanc = resultat.get(4);
			quantite = resultat.get(8);
			Ncontrat = resultat.get(9);
			type = resultat.get(5);
			return true;
		}
		return false;
	}

	public boolean existe_produit(String code, String des) {
		String query = "SELECT * FROM produit where designation='" + des + "' and code_produit<>'" + code + "' ";
		return CConnect.Requete(query).size() != 0;
	}

	public void ajouter_produit(String code, String designation, String dimension, String fournisseur,
								Date date_lc, Date date_lancement, int qte_produire, String type,
								String Ncontrat) {
			String insert2 = "insert into produit"
					+ " (code_produit,designation,dimension,fournisseur,date_lc,date_lancement"
					+ ",qte_produire,type,Ncontrat) values('" + code + "','" + designation + "','" + dimension + "','" + fournisseur + "',"
					+ "'" + date_traiter(date_lc) + "', '" + date_traiter(date_lancement) + "', " + qte_produire + ",'" + type + "','" + Ncontrat + "')";
			CConnect.Insert(insert2);
			JOptionPane.showMessageDialog(null, "Le produit a ete bien ajouter");

	}


	public void update_produit(String code, String designation, String dimension, String fournisseur,
							   Date date_lc, Date date_lancement, int qte_produire, String type, String code1, String Ncontrat) {

			String insert2 = "update produit set date_lc='" + date_traiter(date_lc) + "',date_lancement='" + date_traiter(date_lancement) + "'"
					+ ",designation='" + designation + "' , dimension='" + dimension + "',"
					+ "fournisseur='" + fournisseur + "',qte_produire=" + qte_produire + ""
					+ " ,type='" + type + "',code_produit='" + code1 + "',Ncontrat='" + Ncontrat + "'"
					+ " where code_produit='" + code + "' ";
			CConnect.Insert(insert2);
			JOptionPane.showMessageDialog(null, "Le produit a ete bien modifier");


	}


	public void delete_produit(String code, String designation) {
		int reponse = JOptionPane.showConfirmDialog(null,
				"Voulez-vous vraiment supprimer " + designation + "",
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (reponse == JOptionPane.YES_OPTION) {
			String update2 = "delete  from  produit where code_produit='" + code + "'";
			CConnect.Insert(update2);
			JOptionPane.showMessageDialog(null, "Le produit a ete bien supprimer");
		}
	}


	/////****************************liste Produits		***********************/
	//
	public ArrayList select_all_produit() {

		String query5 = "SELECT code_produit||' '||designation,dimension,date_lc,date_lancement,type" +
				", f.code_fourniss||' '||f.nom_fourniss , qte_produire, Ncontrat  " +
				" FROM produit,fournisseur f  where f.code_fourniss=produit.fournisseur ";
		return  CConnect.Requete(query5);
	}

}

