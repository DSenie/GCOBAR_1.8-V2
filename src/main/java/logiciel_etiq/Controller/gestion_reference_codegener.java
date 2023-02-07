package logiciel_etiq.Controller;

import javax.swing.*;
import java.util.ArrayList;


public class gestion_reference_codegener {

	 public String des, desc,code_ref,code_gener;



	///*****************************************code gener*************************/

    private ArrayList <String>resultat= new ArrayList<>() ;

    public ArrayList<String> select_famille_code(){
        String query5 = "SELECT code_gener,designation FROM categ_composant" ;
        if(CConnect.Requete(query5).size()>1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;

    }


    public void delete_famille(String code, String intitule){

        int reponse = JOptionPane.showConfirmDialog(null,
                "Voulez-vous vraiment supprimer "+intitule+" ",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (reponse== JOptionPane.YES_OPTION){
            String update2= "delete from categ_composant where code_gener='"+code+"'";
            CConnect.Insert(update2);
            JOptionPane.showMessageDialog(null,"La famille a été bien supprimé");
        }}


    private boolean existe_famille(String code, String designation) {
        String query = "SELECT * FROM categ_composant where designation='"+ designation + "' and code_gener<>'"+ code + "' ";
        if (CConnect.Requete(query).size() >= 1) {
            JOptionPane.showMessageDialog(null, "Cette famille existe déjà");
            return true;
        }
        return false;
    }


    public void setupdate_famille(String code,String designation){
        if(!existe_famille( code,designation)) {
            String update = "UPDATE categ_composant" +
                    " SET designation = '" + designation + "'" +
                    "WHERE code_gener='" + code + "'" +
                    " AND NOT EXISTS (SELECT designation FROM categ_composant WHERE designation='" + designation + "')";
            CConnect.Insert(update);
            JOptionPane.showMessageDialog(null, "La famille a été bien modifié");
        }

    }



    public void ajouter_famille(String code,String designation)
    {
        if(!existe_famille( code,designation)) {
                String insert = "insert into categ_composant(code_gener,designation) values('"+code+"','"+designation+"')";
                CConnect.Insert(insert);
            JOptionPane.showMessageDialog(null,"La familla a été bien ajouté");

        }
    }


    public boolean famille(String des,String code)
    {
        String query = "SELECT code_gener,designation FROM categ_composant where designation='"+des+"' or code_gener='"+code+"'";
        ArrayList <String>list_gener=CConnect.Requete(query);
        if(list_gener.size()>=1){
            desc=list_gener.get(1);
            code_gener=list_gener.get(0);
            return true;
        }
        else{
            return false;
        }

    }








////*******************************reference ******************




    private boolean existe_reference(String code, String designation) {
        String query = "SELECT * FROM reference where designation='"+ designation + "' and code_ref<>'"+ code + "' ";
        if (CConnect.Requete(query).size() >= 1) {
            JOptionPane.showMessageDialog(null, "Cette réfèrence existe déjà");
            return true;
        }
        return false;
    }


    public ArrayList<String> select_reference_code(){
        String query5 = "SELECT code_ref,designation FROM reference" ;
        if(CConnect.Requete(query5).size()>1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;

    }



    public void delete_reference(String code, String intitule){

    int reponse = JOptionPane.showConfirmDialog(null,
            "Voulez-vous vraiment supprimer "+intitule+" ",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
    if (reponse== JOptionPane.YES_OPTION){
        String update2= "delete from reference where code_ref='"+code+"'";
        CConnect.Insert(update2);
        JOptionPane.showMessageDialog(null,"La réfèrence a été bien supprimé");

    }}


    public void setupdate_reference(String code,String designation){
        if(!existe_reference( code, designation)) {
            String update = "UPDATE reference" +
                    " SET designation = '" + designation + "'" +
                    "WHERE code_ref='" + code + "'" +
                    " AND NOT EXISTS (SELECT designation FROM reference WHERE designation='" + designation + "')";
            CConnect.Insert(update);
            JOptionPane.showMessageDialog(null,"La réfèrence a été bien modifier");


        }


    }




    public void ajouter_reference(String reference,String designation)
    {

        if(!existe_reference( reference, designation)) {
                String insert = "insert into reference(code_ref,designation) values('"+reference+"','"+designation+"')";
                CConnect.Insert(insert);
            JOptionPane.showMessageDialog(null,"La réfèrence a été bien ajouter");

        }

    }



    public boolean reference(String refer, String code_r)
    {
        String query = "SELECT designation,code_ref FROM reference where designation='"+refer+"' or code_ref="+code_r+"";
        ArrayList<String> list_refer =CConnect.Requete(query);
        if(list_refer.size()>=1){
            des=list_refer.get(0);
            code_ref=list_refer.get(1);

            return true;
        }
        else{
            return false;
        }

    }












    
/////////////////////////////// Rempli la table recap /////////////////////////////////////////////////////////////////////////////


	public  ArrayList<String>  recherche(String code,String rech)
	{
		if(!rech.equals("")&&code.equals("")){
			String query = "select code_ref,"
					+ "designation "
					+ " from reference where code_ref like '%"+rech+"%' or designation like '%"+rech+"%' ";

			String query1 = "select code_gener,designation "
					+ " from categ_composant where   code_gener like '%"+rech+"%' or designation like '%"+rech+"%'";

			resultat.addAll(CConnect.Requete(query));
			resultat.addAll( CConnect.Requete(query1));

			return resultat;
		}

		if(code.equals("Code réfèrence")){
			String query2 = "select code_ref,designation from reference ";
			if(!rech.equals("")){
                query2=query2.concat(" where code_ref like '%"+rech+"%' or designation like '%"+rech+"%'");
                }
           resultat=CConnect.Requete(query2);
		   return resultat;
		}

		if(code.equals("Code famille")){
			String query3 = "select code_gener,designation  from categ_composant ";
            if(!rech.equals("")){
                query3=query3.concat(" where code_gener like '%"+rech+"%' or designation like '%"+rech+"%'");
            }
			resultat=CConnect.Requete(query3);
			return resultat;
		}
		return resultat;
	}


}
