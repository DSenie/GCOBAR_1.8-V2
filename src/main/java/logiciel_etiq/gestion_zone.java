package logiciel_etiq;

import java.util.ArrayList;


public class gestion_zone {
    String bdd;
	 protected String desc;
	 protected String desig;
	 protected String code_z;
	 protected boolean exist;

	 public void setupdate_zone_tab_delete(String code,int profil){

			String update2= "delete from Zone_Post where Code_Zone='"+code+"' and code_profil="+profil+""; 
			CConnect.Insert(update2,bdd);
		}
	 
	 public void setupdate_zone_tab_ajou(String code,int Nbr_Poste,String code_p,int profil){
		    
			String insert2 = "insert into Zone_Post (Code_Zone,Code_Poste,Nbr_Poste,code_profil) values('"+code+"','"+code_p+"',"+Nbr_Poste+","+profil+")"; 
		 	CConnect.Insert(insert2,bdd);
		}

	 
	 public String select_poste_num(String  num,String code_z,String profils){
			String numo ="";
			ArrayList <String>list_user= new ArrayList<String>() ;
		   	    String query5 = "SELECT Nbr_Poste FROM Poste,zone,Zone_Post, profils_prod"
		   	    		+ " where Zone_Post.Code_Poste='"+num+"' and Zone_Post.Code_Zone='"+code_z+"' and profils_prod.intitule='"+profils+"'  "
		   	    				+ "and Poste.Code_Poste=Zone_Post.Code_Poste and zone.Code_Zone=Zone_Post.Code_Zone "
		   	    				+ " and Ncode=code_profil" ;  
		   	 list_user=CConnect.Requete(query5, bdd);
		   	 
		   	    if(list_user.size()>=1){
				      //System.out.println(resultat.size()+"hyh"+(String) CConnect.Requete(query5,bdd).get(0));  
		        numo=list_user.get(0);
			    }
		        return numo;        
		       
		}
	 
	public ArrayList<String> select_zone_code(){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	    String query5 = "SELECT  distinct Code_Zone,Designation_Zone FROM zone" ; 
			if(CConnect.Requete(query5, bdd).size()>1){
	        resultat=CConnect.Requete(query5,bdd);
		    }
	        return resultat;              
	}
	
	public ArrayList <String> select_produit_profil(String code){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	    String query5 = "SELECT intitule,code_profil FROM produit,profils_prod where code_profil=Ncode and code_produit='"+code+"'" ; 
		    if(CConnect.Requete(query5, bdd).size()>=1){
	        resultat=CConnect.Requete(query5,bdd);
	        
		    }
		    else{
		    	   
		    }
	        return resultat;        
	}
	
	public boolean select_zone_profils(String intitule,String zone){
	   	    String query5 = "SELECT * FROM Zone_Post,profils_prod where code_profil=Ncode and intitule='"+intitule+"'"
	   	    		+ " and Zone_Post.Code_Zone='"+zone+"'"; 
	   	   // System.out.println(CConnect.Requete(query5, bdd));
		    if(CConnect.Requete(query5, bdd).size()>=1){
	        return true;
		    }
		    else{
		    return false;
		    }
	}
//	public  ArrayList<String> select_poste_zone(String profil, String Zone){
//		 ArrayList <String>resultat= new ArrayList<String>() ;
//		  String query5 ="select Code_Poste ,Intitule ,Nbr_Poste from Zone_Post ,Poste where  Zone_Post.Code_Poste=Poste.Code_Poste "
//		  		+ "and code_profil="+profil+" and Code_Zone='"+Zone+"'";
//		
//		
//		return resultat;
//	}
	
	
	public ArrayList<String> select_zone(String code,String profil,boolean existe){
        ArrayList <String>resultat= new ArrayList<String>() ;
        String query5;
        //System.out.println(existe);

	  if(existe==false){
		 
        query5  = "SELECT  distinct Poste.Code_Poste,Intitule, Nbr_Poste FROM Poste,Zone_Post where Poste.Code_Poste=Zone_Post.Code_Poste and Zone_Post.Code_Zone='"+code+"' and Nbr_Poste='0'" ;  
	 
        
	  }else{
		  
		 query5 = "SELECT Poste.Code_Poste,Poste.Intitule,Nbr_Poste FROM Poste,Zone_Post,profils_prod where Poste.Code_Poste=Zone_Post.Code_Poste and Zone_Post.Code_Zone='"+code+"' and profils_prod.Ncode=Zone_Post.code_profil and profils_prod.intitule='"+profil+"' " ; 
		
	  }
       resultat=CConnect.Requete(query5,bdd);  
     //  System.out.println(resultat);
        return resultat;        
}

	
	
	public boolean select_zone_jtext(String code){
		ArrayList <String>list_user= new ArrayList<String>() ;
	String query5 = "SELECT Designation_Zone,Desciption,Code_Zone FROM zone where Code_Zone='"+code+"' or Designation_Zone='"+code+"'  " ;  
	list_user=CConnect.Requete(query5, bdd);
	//System.out.println(CConnect.Requete(query5, bdd).size());
	if(list_user.size()>=1){
		 desig=list_user.get(0);      
		 desc=list_user.get(1);
		 code_z=list_user.get(2);
		 return false;
	}
	else{
		return true;
	}}

}

