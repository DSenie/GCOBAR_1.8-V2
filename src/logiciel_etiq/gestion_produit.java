package logiciel_etiq;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_produit {
	 protected String bdd;
	 public String code;
	 public String desin;
	 public String dimension;
	 public String fournisseur;
	 public String quantite;
	 public String type;
	 public String process;
	 public String datelc;
	 public String datelanc;
	 public String model;
	 public String Ncontrat;

	 public String profil;
     
	public ArrayList<String> select_produit_code(){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	    String query5 = "SELECT code_produit,designation FROM produit" ; 
		    if(CConnect.Requete(query5, bdd).size()>1){
	        resultat=CConnect.Requete(query5,bdd);
	        
		    }
		    else{
		    	   
		    }
	        return resultat;        
	}
	
	public ArrayList<String> select_produit_type(){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	    String query5 = "SELECT type FROM type_produit" ; 
		    if(CConnect.Requete(query5, bdd).size()>1){
	        resultat=CConnect.Requete(query5,bdd);
		    }
		    else{
		    	   
		    }
	        return resultat;        
	}
	 
	public void select_produit(String code_p ){
		System.out.println(code_p);
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT code_produit,designation,dimension,Format(date_lc,'dd/MM/yy'),Format(date_lancement,'dd/MM/yy'),type,"
				+ " f.code_fourniss,f.nom_fourniss, qte_produire,p.intitule,Ncontrat  FROM produit,fournisseur f, profils_prod p"
				+ " where code_produit='"+code_p+"' and f.code_fourniss=produit.fournisseur and p.Ncode=produit.code_profil " ; 
		resultat=CConnect.Requete(query5,bdd);
		System.out.println(CConnect.Requete(query5,bdd));
		if(resultat.size()>=1){
			System.out.println(resultat.get(0));
		code= resultat.get(0);
		desin= resultat.get(1);
		dimension= resultat.get(2);
		fournisseur= resultat.get(6)+" "+ resultat.get(7);
		datelc= resultat.get(3);
		datelanc =resultat.get(4) ;
		quantite=resultat.get(8);
	//	model =resultat.get(5);
		Ncontrat=resultat.get(10);
		profil=resultat.get(9);
		type=resultat.get(5);
		}else if(resultat.size()==0){
			JOptionPane.showMessageDialog(null, "Ce produit n'exist pas ");
		}
		
		
	}
	
	
	public ArrayList<String> select_profil_code(){ 
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	    String query5 = "SELECT distinct(code_profil)"
	   	    		+ " FROM produit "
	   	    		+ " " ; 
		    if(CConnect.Requete(query5, bdd).size()>1){

	        resultat=CConnect.Requete(query5,bdd);
		    }
		    else{		    	

		    	resultat.add(" ");
		    }
	        return resultat;        
	       
	}
	
	
	
	public void delete_produit(String code){
		try {	
			  String update2= "delete  from  produit where code_produit='"+code+"'"; 
	    		CConnect.Insert(update2,bdd);	    	
    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  } 
  public void update_produit(String code,String designation,String dimension,String fournisseur,
		 String  date_lc,String date_lancement,int qte_produire,int code_profil,String type,String code1,String Ncontrat){
		try {	
			  
			System.out.println("eeeeee"+fournisseur);
			  String insert2 = "update produit set date_lc=Format('"+date_lc+"','dd/MM/yy'),date_lancement=Format('"+date_lancement+"','dd/MM/yy')"
			  		+ ",designation='"+designation+"' , dimension='"+dimension+"',"
			  				+ "fournisseur='"+fournisseur+"',qte_produire="+qte_produire+","
			  						+ "code_profil="+code_profil+",type='"+type+"',code_produit='"+code1+"',Ncontrat='"+Ncontrat+"'"
			  								+ " where code_produit='"+code+"' "; 


    	     	CConnect.Insert(insert2,bdd);
    	
    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
  
  public ArrayList<String> select_profils(){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	 String query5 = "SELECT intitule FROM profils_prod" ; 
		 if(CConnect.Requete(query5, bdd).size()>1){
	        resultat=CConnect.Requete(query5,bdd);
		                                           }
		 System.out.println(resultat);
	        return resultat;        
	}
  
  public void insertion_profils(int code,String intitule){
	   	 String query5 = "insert into profils_prod(Ncode,intitule) values("+code+",'"+intitule+"')" ; 
		 CConnect.Insert(query5, bdd);
	}
  
  
  public void ajouter_produit(String code,String designation,String dimension,String fournisseur,
			 String  date_lc,String date_lancement,int qte_produire,int code_profil,String type,
			 String Ncontrat){
		try {	
    		String insert2 = "insert into produit"
    				+ " (code_produit,designation,dimension,fournisseur,date_lc,date_lancement"
    				+ ",qte_produire,code_profil,type,Ncontrat) values('"+code+"','"+designation+"','"+dimension+"','"+fournisseur+"',"
    						+ "'"+date_lc+"', '"+date_lancement+"', "+qte_produire+", "+code_profil+",'"+type+"','"+Ncontrat+"')"; 
    		CConnect.Insert(insert2,bdd);
    	
    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
	
}
