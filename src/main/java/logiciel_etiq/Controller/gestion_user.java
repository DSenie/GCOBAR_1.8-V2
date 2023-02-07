package logiciel_etiq.Controller;


import javax.swing.*;
import java.util.ArrayList;


public class gestion_user {
	public String matricule;
	public String nom;
	public String prenom;
	public String login;
	public String pass;


	public void selection(String matri) {

		String query = "SELECT matricule,nom,prenom,login,password FROM utilisateur where matricule='" + matri + "'";
		ArrayList<String> list_user = CConnect.Requete(query);
		if (list_user.size() > 1) {
			matricule =  list_user.get(0);
			nom =  list_user.get(1);
			prenom = list_user.get(2);
			login =  list_user.get(3);
			pass =  list_user.get(4);
		}


	}


	public void setupdate(String matri, String nom, String prenom, String login, String password) {
		String update = "update utilisateur set nom='" + nom.toLowerCase() + "', prenom='" + prenom.toLowerCase() + "', login='" + login + "', "
				+ "password='" + password + "' "
				+ "where matricule='" + matri + "'";
		CConnect.Insert(update);
		JOptionPane.showMessageDialog(null, "" + nom + " " + prenom + " a été bien modifié");

	}


	public void setinsert(String matri, String nom, String prenom, String login, String password) {
		String query = "SELECT matricule,priorite FROM utilisateur where matricule='" + matri + "'";
		if (CConnect.Requete(query).size() > 1) {
			JOptionPane.showMessageDialog(null, "Cette personne existe déjà.");

		} else {
			String insert = "insert into utilisateur(matricule,nom,prenom,login,password) values('" + matri + "','" + nom.toLowerCase() + "','" + prenom.toLowerCase() + "'"
					+ ",'" + login + "','" + password + "')";
			CConnect.Insert(insert);
			JOptionPane.showMessageDialog(null, "" + nom + " " + prenom + " a été bien ajouté");
		}
	}


	public void setdelete(String matri, String nom, String prenom) {
		int reponse = JOptionPane.showConfirmDialog(null,
				"Voulez-vous vraiment supprimer " + nom + " " + prenom + " ",
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (reponse == JOptionPane.YES_OPTION) {
			String update = "delete from utilisateur where matricule=" + matri + "";
			CConnect.Insert(update);
			JOptionPane.showMessageDialog(null, "" + nom + " " + prenom + " a été bien supprimé");

		}
	}




	public ArrayList<String> select(){
		String query5 = "SELECT matricule,nom,prenom,login FROM utilisateur ";
		return CConnect.Requete(query5);
	}




}