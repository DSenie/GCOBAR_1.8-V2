package logiciel_etiq;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_poste {
	public String bdd;
	public boolean exist,ex;	
	public String intitul,code,desc;
	
	public  int  select_profil(String code_produit)
	{  
	int profil=0 ;
	String query2 = "SELECT code_profil FROM produit where code_produit='"+code_produit+"'"; 
   if(CConnect.Requete(query2, bdd).size()>=1)
	   profil=Integer.parseInt(CConnect.Requete(query2, bdd).get(0));
	return profil;
	}
	
	
	
	public String afficher_code_poste()
	{  
	ArrayList <String>list_code= new ArrayList<String>() ;
    String code;
    NumberFormat formatter = new DecimalFormat("00");
	String query2 = "SELECT max(Code_Poste) FROM Poste"; 
	list_code=CConnect.Requete(query2, bdd);
    if(list_code.get(0)!=null){
	int cz=Integer.parseInt(list_code.get(0).substring(1))+1;
	     code="P"+formatter.format(cz);
		                                          }
	else code="P01";
	return code;
	}
	
	
	public  int  afficher_numero_poste(String code_poste,int profils)
	{  
	int list_numero_poste=0 ;
	String query2 = "SELECT Nbr_Poste FROM Zone_Post where Code_Poste='"+code_poste+"' and code_profil="+profils+""; 
   if(CConnect.Requete(query2, bdd).size()>=1)
	list_numero_poste=Integer.parseInt(CConnect.Requete(query2, bdd).get(0));
	return list_numero_poste;
	}
	
	public  int  afficher_numero_poste(String code_poste,String code_prod)
	{  
	int list_numero_poste=0 ;
	
	String query2 = "SELECT Nbr_Poste FROM Zone_Post where Code_Poste='"+code_poste+"' and code_profil in"
			+ " (select code_profil from produit where code_produit='"+code_prod+"' "; 
   if(CConnect.Requete(query2, bdd).size()>=1)
	list_numero_poste=Integer.parseInt(CConnect.Requete(query2, bdd).get(0));
	return list_numero_poste;
	}
	
	public ArrayList<String> select(){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	    String query5 = "SELECT  Code_Poste,Intitule FROM Poste" ; 
	    resultat=CConnect.Requete(query5,bdd);
	    return resultat;        
	}
	
	
	 public boolean select_poste_jtext(String code){ 
	     ArrayList<String> list_chaine=new ArrayList<String>();

	   	   String query5 = "SELECT Intitule,description,Code_Poste FROM Poste where Code_Poste='"+code+"' or Intitule='"+code+"' " ;  
	   	list_chaine=CConnect.Requete(query5, bdd);
	   	   if(list_chaine.size()>=1){
	   	      ex=false;
	          intitul=list_chaine.get(0);      
	          desc=list_chaine.get(1);
	          code=list_chaine.get(2);
	          
	   	                                             }
	   else{
	   	ex=true;
	   }
	   	return ex;
	   	}
	
	
	 public ArrayList<String> select_poste(String code){
	        ArrayList <String>resultat= new ArrayList<String>() ;  
		    String query5 = "SELECT tache.code_tache,tache.intitule FROM tache,poste_tache where tache.code_tache=poste_tache.code_tache and poste_tache.code_poste='"+code+"' " ;  
		    resultat=CConnect.Requete(query5,bdd);      
	        return resultat;        
	                                                        }
//////////////////////////////////////////////ajouter les poste///////////////////////////////////////////////////////////////////////////////////////	
	
	public boolean ajouter_poste(String poste,String intitule,String desc)
	{
		exist=false;
		String query = "SELECT * FROM Poste where Code_Poste='"+poste+"' "; 
        if(CConnect.Requete(query, bdd).size()>=1){
		      	JOptionPane.showMessageDialog(null,"Ce Poste existe d�j�");
		      	exist=true;
		      	return exist;
		    }
		    else{
		       try {	
		    	 String insert = "insert into Poste(Code_Poste,Intitule,description) values('"+poste+"','"+intitule+"','"+desc+"')"; 
		    	 CConnect.Insert(insert,bdd);
		    	
		    } catch (Exception e) {
				e.printStackTrace();
			}
			       return exist;
			     }  
				
	}
	public void ajouter_poste_tache(String poste,String tache)
	{
	   try {	
		  String insert2 = "insert into poste_tache (code_poste,code_tache) values('"+poste+"','"+tache+"')"; 
		  CConnect.Insert(insert2,bdd);
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
////////////////////////////////////////////////Modifier Poste/////////////////////////////////////////////////////////////////////////////
	public void setupdate_poste(String code,String design,String desc){
		  String update= "update  Poste set Intitule='"+design+"', description='"+desc+"' where Code_Poste='"+code+"'"; 
	  	  CConnect.Insert(update,bdd);
	}
	
	
	
	////////////////////////////////supprime Pooste::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//	public void delete_Poste(String code, String intitule){
//		  int reponse = JOptionPane.showConfirmDialog(null,
//	            "Voulez-vous vraiment supprimer "+intitule+" ",
//	            "Confirmation",
//	            JOptionPane.YES_NO_OPTION,
//	            JOptionPane.QUESTION_MESSAGE);
//	if (reponse== JOptionPane.YES_OPTION){
//		String update2= "delete from Poste where Code_Poste='"+code+"'"; 
//		CConnect.Insert(update2,bdd);
//	  	JOptionPane.showMessageDialog(null,"Le Poste a �t� bien supprim�");
//
//	}}
	
	
	public void setdelete_poste(String code,String des){
		  int reponse = JOptionPane.showConfirmDialog(null,
	            "Voulez-vous vraiment supprimer "+des+"?",
	            "Confirmation",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE);
	if (reponse== JOptionPane.YES_OPTION){
		String update= "delete from Poste where Code_Poste='"+code+"'";
		String update1= "delete from poste_tache where code_poste='"+code+"'";
		CConnect.Insert(update,bdd);
		CConnect.Insert(update1,bdd);

		JOptionPane.showMessageDialog(null,"Le Poste a �t� bien supprim�e");
	}
	}
	
	
	
	public void setupdate_poste_tab_delete(String code){
		String update2= "delete from poste_tache where code_poste='"+code+"'"; 
		CConnect.Insert(update2,bdd);
	}
	
	public void setupdate_poste_tab_ajou(String code,String code_t){
		String insert2 = "insert into poste_tache (code_poste,code_tache) values('"+code+"','"+code_t+"')"; 
	 	CConnect.Insert(insert2,bdd);
	}
	///////////////////////////////la selection des poste pour la jcombobox/////////////////////////////////////////////////////////////////////////////
	public ArrayList<String> select_poste_code(){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	    String query5 = "SELECT Code_Poste,Intitule FROM Poste" ; 
		    if(CConnect.Requete(query5, bdd).size()>1){
	        resultat=CConnect.Requete(query5,bdd);
		    }
		    else{
		    	resultat.add(" ");
		    	resultat.add(" ");	    
		    }
	        return resultat;        
	       
	}
	

	

	
}