package logiciel_etiq;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_outil {
	protected String bdd;
	protected boolean exist;	
	protected boolean ex;
	protected String nom,code;
	
	
	
	   public String afficher_code_outil()
		{   String code;
		ArrayList <String>list_afficher= new ArrayList<String>() ;
		NumberFormat formatter = new DecimalFormat("00");


			   String query2 = "SELECT max(code_outil) FROM outillage"; 
			   list_afficher=CConnect.Requete(query2, bdd);
			   if(list_afficher.get(0)!=null){
		      int cz=Integer.parseInt(list_afficher.get(0).substring(2))+1;
		       code="OT"+formatter.format(cz);
			   }
			   else code="OT01";
			   
		return code;
		}
	   
	   
	   public ArrayList<String> select_outil_code(){
			ArrayList <String>resultat= new ArrayList<String>() ;
			String query5 = "SELECT code_outil,intitule FROM outillage" ; 
			if(CConnect.Requete(query5, bdd).size()>1){

			resultat=CConnect.Requete(query5,bdd);
			}
			return resultat;        

			}
	   
	   
    public void outillage(String outil)
	{ ArrayList <String>list_outil= new ArrayList<String>() ;
		 String query = "SELECT code_outil,intitule FROM outillage where intitule='"+outil+"'"; 
		 list_outil=CConnect.Requete(query, bdd);
		  if( list_outil.size()>=1){
		      	ex=true;
		      	nom= list_outil.get(0);
		      	code= list_outil.get(1);
		    }
		    else{
		    	ex=false;
		    }
		    
	}
 
	
	
	public void delete_outillage(String code, String intitule){

		int reponse = JOptionPane.showConfirmDialog(null,
		"Voulez-vous vraiment supprimer "+intitule+" ",
		"Confirmation",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE);
		if (reponse== JOptionPane.YES_OPTION){
		String update2= "delete from outillage where code_outil='"+code+"'"; 
		CConnect.Insert(update2,bdd);
		JOptionPane.showMessageDialog(null,"L'outil a été bien supprimé");

		}}
	
	public void setupdate_outil(String code,String designation){

		String update= "update  outillage set intitule='"+designation+"' where code_outil='"+code+"'"; 
		CConnect.Insert(update,bdd);


		}
	public boolean ajouter_outil(String code,String intitule)
	{
	exist=false;
	String query = "SELECT * FROM outillage where code_outil='"+code+"'"; 

	if(CConnect.Requete(query, bdd).size()>=1){
	JOptionPane.showMessageDialog(null,"Cet outil existe déjà");
	exist=true;
	return exist;
	}
	else{

	try {	
	String insert = "insert into outillage(code_outil,intitule) values('"+code+"','"+intitule+"')"; 

	CConnect.Insert(insert,bdd);

	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	return exist;
	}  

	}
	
}