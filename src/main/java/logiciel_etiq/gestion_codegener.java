package logiciel_etiq;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_codegener {
	String bdd;
    boolean exist,ex;	
    String code_f,desc;
    
    public boolean famille(String des,String code)
	{
        ArrayList <String>list_gener= new ArrayList<String>() ;
		 String query = "SELECT code_gener,designation FROM categ_composant where designation='"+des+"' or code_gener='"+code+"'";
		 list_gener=CConnect.Requete(query, bdd);
		  if(list_gener.size()>=1){
		      //	ex=true;
		      	desc=list_gener.get(1);
		      	code_f=list_gener.get(0);
		      //	System.out.println(des+" "+code);
		      	return true;
		    }
		    else{
		    	return false;
		    	//ex=false;
		    }
		    
	}
///////////////////////////////la selection des poste pour la jcombobox/////////////////////////////////////////////////////////////////////////////
public ArrayList<String> select_famille_code(){
ArrayList <String>resultat= new ArrayList<String>() ;
String query5 = "SELECT code_gener,designation FROM categ_composant" ; 
if(CConnect.Requete(query5, bdd).size()>1){
resultat=CConnect.Requete(query5,bdd);
}
return resultat;        

}


////////////////////////////////supprime refernce::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
public void delete_famille(String code, String intitule){

int reponse = JOptionPane.showConfirmDialog(null,
"Voulez-vous vraiment supprimer "+intitule+" ",
"Confirmation",
JOptionPane.YES_NO_OPTION,
JOptionPane.QUESTION_MESSAGE);
if (reponse== JOptionPane.YES_OPTION){
String update2= "delete from categ_composant where code_gener='"+code+"'"; 
CConnect.Insert(update2,bdd);
JOptionPane.showMessageDialog(null,"La famille a été bien supprimé");

}}

////////////////////////////////////////////////Modifier reference/////////////////////////////////////////////////////////////////////////////
public void setupdate_famille(String code,String designation){

String update= "update  categ_composant set designation='"+designation+"' where code_gener='"+code+"'"; 
CConnect.Insert(update,bdd);


}

//////////////////////////////////////////////ajouter les poste///////////////////////////////////////////////////////////////////////////////////////	

public boolean ajouter_famille(String code,String designation)
{
exist=false;
String query = "SELECT * FROM categ_composant where code_gener='"+code+"'"; 

if(CConnect.Requete(query, bdd).size()>=1){
JOptionPane.showMessageDialog(null,"Cette famille existe déjà");
exist=true;
return exist;
}
else{

try {	
String insert = "insert into categ_composant(code_gener,designation) values('"+code+"','"+designation+"')"; 

CConnect.Insert(insert,bdd);

} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return exist;
}  

}
}
