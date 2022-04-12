package logiciel_etiq;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_famille_article {
	protected String bdd;
	protected boolean exist,ex;	
	protected String code,des;
    public void type(String poste)
	{  ArrayList <String>list_type= new ArrayList<String>() ;
		 String query = "SELECT designation,code_famille FROM famille_article where designation='"+poste+"'"; 
		 list_type=CConnect.Requete(query, bdd);
		  if( list_type.size()>=1){
		      	ex=true;
		      	des=list_type.get(0);
		      	code=list_type.get(1);
		    }
		    else{
		    	ex=false;
		    }
		    
	}
///////////////////////////////la selection des poste pour la jcombobox/////////////////////////////////////////////////////////////////////////////
public ArrayList<String> select_artfamille_code(){
ArrayList <String>resultat= new ArrayList<String>() ;
String query5 = "SELECT code_famille,designation FROM famille_article" ; 
if(CConnect.Requete(query5, bdd).size()>1){

resultat=CConnect.Requete(query5,bdd);
}
return resultat;        


}


public String afficher_code_artfamille()
{  
	 ArrayList <String>list_code_famille= new ArrayList<String>() ;
	 String code;

	 NumberFormat formatter = new DecimalFormat("00");
   

	 	   String query2 = "SELECT max(code_famille) FROM famille_article"; 
	 	  list_code_famille=CConnect.Requete(query2, bdd);
	 	   if(list_code_famille.get(0)!=null){
	       int cz=Integer.parseInt(list_code_famille.get(0).substring(1))+1;
	        code="F"+formatter.format(cz);
	 	   }
	 	   else code="F01";
	 return code;

	 
}

////////////////////////////////supprime refernce::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
public void delete_artfamille(String code, String intitule){

int reponse = JOptionPane.showConfirmDialog(null,
"Voulez-vous vraiment supprimer "+intitule+" ",
"Confirmation",
JOptionPane.YES_NO_OPTION,
JOptionPane.QUESTION_MESSAGE);
if (reponse== JOptionPane.YES_OPTION){
String update2= "delete from famille_article where code_famille='"+code+"'"; 
CConnect.Insert(update2,bdd);
JOptionPane.showMessageDialog(null,"Le type article a été bien supprimé");

}}

////////////////////////////////////////////////Modifier reference/////////////////////////////////////////////////////////////////////////////
public void setupdate_artfamille(String code,String designation){

String update= "update  famille_article set designation='"+designation+"' where code_famille='"+code+"'"; 
CConnect.Insert(update,bdd);


}

//////////////////////////////////////////////ajouter les poste///////////////////////////////////////////////////////////////////////////////////////	

public boolean ajouter_artfamille(String code,String designation)
{
exist=false;
String query = "SELECT * FROM famille_article where code_famille='"+code+"'"; 

if(CConnect.Requete(query, bdd).size()>=1){
JOptionPane.showMessageDialog(null,"Ce type article existe déjà");
exist=true;
return exist;
}
else{

try {	
String insert = "insert into famille_article(code_famille,designation) values('"+code+"','"+designation+"')"; 

CConnect.Insert(insert,bdd);

} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return exist;
}  

}
}
