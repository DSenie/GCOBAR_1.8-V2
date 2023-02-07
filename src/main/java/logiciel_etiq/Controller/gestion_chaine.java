package logiciel_etiq.Controller;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;


public class gestion_chaine {
	public String desig;
	public String code_chaine;


	public ArrayList<String> select_chaine_code() {
		ArrayList<String> resultat = null;
		String query5 = "SELECT code_chaine,designation_chaine FROM Chaine";
		if (CConnect.Requete(query5).size() > 1) {
			resultat = CConnect.Requete(query5);
		}
		return resultat;
	}


	public String afficher_code_chaine() {
		String code;
		NumberFormat formatter = new DecimalFormat("00");
		String query2 = "SELECT max(code_chaine) FROM Chaine";
		ArrayList<String> list_chaine = CConnect.Requete(query2);
		if (list_chaine.get(0) != null) {
			int cz = Integer.parseInt(list_chaine.get(0).substring(1)) + 1;
			code = "C" + formatter.format(cz);
		} else code = "C01";
		return code;
	}


	/*************************** pour verifier si la chaine existe deja *******************/

	public boolean select_chaine_jtext(String code) {

		String query5 = "SELECT designation_chaine,code_chaine FROM Chaine where code_chaine='" + code + "' or designation_chaine='" + code + "' ";
		ArrayList<String> list_chaine = CConnect.Requete(query5);
		if (list_chaine.size() >= 1) {
			desig = list_chaine.get(0);
			code_chaine = list_chaine.get(1);
			return true;
		}
			return  false;

	}


	public void setdelete_chaine(String code, String des) {
		int reponse = JOptionPane.showConfirmDialog(null,
				"Voulez-vous vraiment supprimer " + des + "?",
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (reponse == JOptionPane.YES_OPTION) {
			String update = "delete from Chaine where code_chaine='" + code + "'";
			CConnect.Insert(update);

			JOptionPane.showMessageDialog(null, "La Chaine a été bien supprimée");
		}
	}



	private boolean existe_chaine(String code, String nom) {
		String query = "SELECT * FROM Chaine where designation_chaine='"+ nom + "' and Code_Chaine<>'"+ code + "' ";
		if (CConnect.Requete(query).size() >= 1) {
			JOptionPane.showMessageDialog(null, "Cette chaine existe déjà");
			return true;
		}
		return false;

	}
	public void setupdate_chaine(String code, String design) {
		if(!existe_chaine(code, design)) {
			String update = "update  Chaine set designation_chaine='" + design + "' where Code_Chaine='" + code + "'";
			CConnect.Insert(update);
			JOptionPane.showMessageDialog(null,"La chaine a été bien modifier");

		}
	}


	public void ajouter_chaine(String chaine, String desig) {
		if(!existe_chaine(chaine, desig)) {
				String insert = "insert into Chaine(code_chaine,designation_chaine) values('" + chaine + "','" + desig + "')";
				CConnect.Insert(insert);
			JOptionPane.showMessageDialog(null,"La chaine a été bien ajouter");


		}
	}

}