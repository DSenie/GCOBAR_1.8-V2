package logiciel_etiq;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_fournisseur {
	protected String bdd;
	protected boolean exist;	
	protected boolean ex;
	protected String nom,code;
    public void fournisseur(String four)
	{ ArrayList <String>list_fourniss= new ArrayList<String>() ;
		 String query = "SELECT nom_fourniss,code_fourniss FROM fournisseur where nom_fourniss='"+four+"'"; 
		 list_fourniss=CConnect.Requete(query, bdd);
		  if( list_fourniss.size()>=1){
		      	ex=true;
		      	nom= list_fourniss.get(0);
		      	code= list_fourniss.get(1);
		    }
		    else{
		    	ex=false;
		    }
		    
	}
    public String afficher_code_fourniss()
	{   String code;
	ArrayList <String>list_afficher= new ArrayList<String>() ;
	NumberFormat formatter = new DecimalFormat("00");


		   String query2 = "SELECT max(code_fourniss) FROM fournisseur"; 
		   list_afficher=CConnect.Requete(query2, bdd);
		   if(list_afficher.get(0)!=null){
	      int cz=Integer.parseInt(list_afficher.get(0).substring(2))+1;
	       code="FO"+formatter.format(cz);
		   }
		   else code="FO01";
		   
	return code;
	}
	public ArrayList<String> select_fournisseur_code(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT code_fourniss,nom_fourniss FROM fournisseur" ; 
		if(CConnect.Requete(query5, bdd).size()>1){

		resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;        

		}
	
	public void delete_fournisseur(String code, String intitule){

		int reponse = JOptionPane.showConfirmDialog(null,
		"Voulez-vous vraiment supprimer "+intitule+" ",
		"Confirmation",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE);
		if (reponse== JOptionPane.YES_OPTION){
		String update2= "delete from fournisseur where code_fourniss='"+code+"'"; 
		CConnect.Insert(update2,bdd);
		JOptionPane.showMessageDialog(null,"Le fournisseur a �t� bien supprim�");

		}}
	public void setupdate_fourniss(String code,String designation){

		String update= "update  fournisseur set nom_fourniss='"+designation+"' where code_fourniss='"+code+"'"; 
		CConnect.Insert(update,bdd);


		}
	public boolean ajouter_fourniss(String code,String intitule)
	{
	exist=false;
	String query = "SELECT * FROM fournisseur where code_fourniss='"+code+"'"; 

	if(CConnect.Requete(query, bdd).size()>=1){
	JOptionPane.showMessageDialog(null,"Ce fournisseur existe d�j�");
	exist=true;
	return exist;
	}
	else{

	try {	
	String insert = "insert into fournisseur(code_fourniss,nom_fourniss) values('"+code+"','"+intitule+"')"; 

	CConnect.Insert(insert,bdd);

	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	return exist;
	}  

	}
	
	public ArrayList<String> select(){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	    String query5 = "SELECT  code_fourniss,nom_fourniss FROM fournisseur" ; 
	        resultat=CConnect.Requete(query5,bdd);
	        return resultat;        
	}
	
               	
	
	
	
	
	
		public  ArrayList<String>  recherche(String code,String rech)
		{	 
			ArrayList <String>resultat= new ArrayList<String>() ;
			if(!rech.equals("")&&code.equals("")){	 
        				 String query = "select code_ref,"
                     	 		+ "designation "
                     	 		+ " from reference where  ";
        				 String query1 = "select code_gener,designation "
                      	 		+ " from categ_composant where  ";
        			 query=query.concat(" code_ref like '%"+rech+"%' or designation like '%"+rech+"%'");
        			query1=query1.concat(" code_gener like '%"+rech+"%' or designation like '%"+rech+"%'");
        			resultat.addAll(CConnect.Requete(query,bdd));
        			resultat.addAll( CConnect.Requete(query1,bdd));
        		       
return resultat;
        			 }
             
			 
			 
	         if(rech.equals("")&&code.equals("Code r�f�rence")){	 
	        	 String query2 = "select code_ref,"
              	 		+ "designation "
              	 		+ " from reference ";
     			resultat=CConnect.Requete(query2,bdd);
     			return resultat;
			 }
	         
	         if(rech.equals("")&&code.equals("Code famille")){	 
	        	 String query3 = "select code_gener,designation "
                      	 		+ " from categ_composant";
	     			resultat=CConnect.Requete(query3,bdd);
	     			return resultat;
			 }
	         
	         if(!rech.equals("")&&code.equals("Code famille")){	 
				 
	        	 String query4 = "select code_gener,designation "
                      	 		+ " from categ_composant where ";
     			query4=query4.concat(" code_gener like '%"+rech+"%' or designation like '%"+rech+"%'");
	     			resultat=CConnect.Requete(query4,bdd);
	     			return resultat;
	     			}
	         
           if(!rech.equals("")&&code.equals("Code r�f�rence")){	 
        	 //  System.out.println("hhhhhhh"+rech);
        	   String query5 = "select code_ref,"
             	 		+ "designation "
             	 		+ " from reference where ";
       	 query5=query5.concat(" code_ref like '%"+rech+"%' or designation like '%"+rech+"%'");
       	resultat=CConnect.Requete(query5,bdd);
			return resultat;
			 }
	         
	      
	    
           
	       
	     //  resultat=CConnect.Requete(query,bdd);
	       //  System.out.println(resultat);
	       return resultat;
			 }
			
		

}
