package logiciel_etiq;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_reference {
	protected String bdd;
	protected boolean exist;	
	protected boolean ex;
	protected String des,code;
	
    public boolean reference(String refer)
	{
   ArrayList<String> list_refer=new ArrayList<String>();

		 String query = "SELECT designation,code_ref FROM reference where designation='"+refer+"'"; 
		 list_refer=CConnect.Requete(query, bdd);
		  if(list_refer.size()>=1){
		      	//ex=true;
		      	des=list_refer.get(0);
		      	code=list_refer.get(1);
		      	return true;
		    }
		    else{
		    //n	ex=false;
		      	return false;

		    }
		    
	}
    
///////////////////////////////la selection des poste pour la jcombobox/////////////////////////////////////////////////////////////////////////////
public ArrayList<String> select_reference_code(){
ArrayList <String>resultat= new ArrayList<String>() ;
String query5 = "SELECT code_ref,designation FROM reference" ; 
if(CConnect.Requete(query5, bdd).size()>1){
resultat=CConnect.Requete(query5,bdd);
}
return resultat;        

}

public String afficher_code_reference()
{  
	String code;

NumberFormat formatter = new DecimalFormat("0");

	   String query2 = "SELECT max(code_ref) FROM reference"; 
	   ArrayList<String> list_refer=new ArrayList<String>();
	   list_refer=CConnect.Requete(query2, bdd);
	   if(list_refer.get(0)!=null){ 

      int cz=Integer.parseInt(list_refer.get(0))+2;
       code=formatter.format(cz);
	   }
	   else code="1";
return code;
}

////////////////////////////////supprime refernce::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
public void delete_reference(String code, String intitule){

int reponse = JOptionPane.showConfirmDialog(null,
"Voulez-vous vraiment supprimer "+intitule+" ",
"Confirmation",
JOptionPane.YES_NO_OPTION,
JOptionPane.QUESTION_MESSAGE);
if (reponse== JOptionPane.YES_OPTION){
String update2= "delete from reference where code_ref="+code+""; 
CConnect.Insert(update2,bdd);
JOptionPane.showMessageDialog(null,"La réfèrence a été bien supprimé");

}}

////////////////////////////////////////////////Modifier reference/////////////////////////////////////////////////////////////////////////////
public void setupdate_reference(String code,String designation){

String update= "update  reference set designation='"+designation+"' where code_ref="+code+""; 
CConnect.Insert(update,bdd);


}

//////////////////////////////////////////////ajouter les poste///////////////////////////////////////////////////////////////////////////////////////	

public boolean ajouter_reference(String reference,String designation)
{
exist=false;
String query = "SELECT * FROM reference where code_ref='"+reference+"'"; 

if(CConnect.Requete(query, bdd).size()>=1){
JOptionPane.showMessageDialog(null,"Cette réfèrence existe déjà");
exist=true;
return exist;
}
else{

try {	
String insert = "insert into reference(code_ref,designation) values("+reference+",'"+designation+"')"; 

CConnect.Insert(insert,bdd);

} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return exist;
}  

}
}
