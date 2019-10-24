package logiciel_etiq;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_profils {
	//protected String bdd;
	public String Chemin = "c:\\GCOBAR\\";
	public  String bdd = Chemin+Utilitaire.InitBdd()+".accdb";
	protected boolean exist,ex;
	String poste="",edit_poste="",list_poste="",chaine="",edit_chaine="",list_chaine="",chang_chaine="",zone="",
	  		personelle="",edit_personelle="",list_personelle=""
	  	,user="",edit_user="",list_user="",profil="",
	  	code="",code_ref="",code_gener="",code_rech="",
	  	article="",edit_article="",type="",imprime="",etiquette="",fiche="",list_fiche="",fourniss="",edit_fourniss=""
	  	,list_fourniss="";
	
	 public ArrayList<String> select_profils(){
		    ArrayList <String>resultat= new ArrayList<String>() ;
		   	 String query5 = "SELECT code_prof,nom_profils FROM profils" ; 
			 if(CConnect.Requete(query5, bdd).size()>1){
		        resultat=CConnect.Requete(query5,bdd);
			                                           }
			
		        return resultat;        
		}
	 
	public void ajouter_profils(String poste,String edit_poste,String list_poste,
			String chaine,String edit_chaine,String list_chaine,String chang_chaine,
			String zone,
			String personelle,String edit_personelle,String list_personelle,
			String user,String edit_user,String list_user,String profil,
			String code,String code_ref,String code_gener,String code_rech,
			String article,String edit_article,String type,String imp,String etq,String fiche,String list_fiche,String nom_p,String fourni,String edit_fourni,String list_fourni)
	{
		
		 String query = "SELECT * FROM profils where nom_profils='"+nom_p+"'"; 
		    if(CConnect.Requete(query, bdd).size()>1){
		    	exist=true;
		      JOptionPane.showMessageDialog(null,"Ce nom de profils existe d�j�.");
		       	
		    }
		    else{ 
		    	exist=false;
  String insert = "insert into profils"
  		+ "(poste,edit_poste,list_poste,"
  		+ "chaine,edit_chaine,list_chaine,chang_chaine"
  		+ ",zone,"
  		+ "personelle,edit_personelle,list_personelle"
  		+ ",user,edit_user,list_user,profil,"
  		+ "code,code_ref,code_gener,code_rech,"
  		+ "article,edit_article,type,imprime,etiquette,fiche,list_fiche,nom_profils,fourniss,edit_fourniss,list_fourniss"
		    		+ ") "
		 + " values ('"+poste+"','"+edit_poste+"','"+list_poste+"',"
		 		+ "'"+chaine+"','"+edit_chaine+"','"+list_chaine+"','"+chang_chaine+"',"
		 				+ "'"+zone+"',"
		 						+ "'"+personelle+"','"+edit_personelle+"','"+list_personelle+"'"
		 								+ ",'"+user+"','"+edit_user+"','"+list_user+"','"+profil+"',"
		 										+ "'"+code+"','"+code_ref+"','"+code_gener+"','"+code_rech+"',"
		    				+ "'"+article+"','"+edit_article+"','"+type+"',"
		    						+ "'"+imp+"','"+etq+"','"+fiche+"','"+list_fiche+"','"+nom_p+"','"+fourni+"','"+edit_fourni+"','"+list_fourni+"')"; 
		    	CConnect.Insert(insert,bdd);
		    }	       
	}
	
	
	
	public void modif_profils(String poste,String edit_poste,String list_poste,
			String chaine,String edit_chaine,String list_chaine,String chang_chaine,
			String zone,
			String personelle,String edit_personelle,String list_personelle,
			String user,String edit_user,String list_user,String profil,
			String code,String code_ref,String code_gener,String code_rech,
			String article,String edit_article,String type,String imp,String etq,String fiche,String list_fiche,String nom_p,String fourni,String edit_fourni,String list_fourni)
	{
		
		 
		    	
		    	
		    	  String update= "update profils set poste='"+poste+"', edit_poste='"+edit_poste+"', list_poste='"+list_poste+"', "
			    	  		+ "chaine='"+chaine+"', edit_chaine='"+edit_chaine+"', list_chaine='"+list_chaine+"',chang_chaine='"+chang_chaine+"'"
			    	  				+ ",zone='"+zone+"',personelle='"+personelle+"',edit_personelle='"+edit_personelle+"',list_personelle='"+list_personelle+"',"
			    	  						+ "user='"+user+"',edit_user='"+edit_user+"',list_user='"+list_user+"',profil='"+profil+"',code='"+code+"'"
			    	  								+ ",code_ref='"+code_ref+"',code_gener='"+code_gener+"',code_rech='"+code_rech+"'"
			    	  			+ ",article='"+article+"',edit_article='"+edit_article+"',type='"+type+"',imprime='"+imp+"',etiquette='"+etq+"'"
             +",fiche='"+fiche+"',list_fiche='"+list_fiche+"',fourniss='"+fourni+"',edit_fourniss='"+edit_fourni+"',list_fourniss='"+list_fourni+"'"
			    	  		+ "where nom_profils='"+nom_p+"'"; 
 
		    	CConnect.Insert(update,bdd);
		           
	}
	
	
	public void selection(String nom_profils){
		  ArrayList <String>prof= new ArrayList<String>() ;
	
		 String query = "SELECT * FROM profils where nom_profils='"+nom_profils+"'"; 
		 prof=CConnect.Requete(query, bdd);
		 System.out.println(prof);
		    if(prof.size()>=1){
		    	
		    	ex=true;
		    	 poste=prof.get(0);
		    	 edit_poste=prof.get(1);
		    	 list_poste=prof.get(2);
		    	 chaine=prof.get(3);
		    	 edit_chaine=prof.get(4);
		    	 list_chaine=prof.get(5);
		    	 chang_chaine=prof.get(6);
		    	 zone=prof.get(7);  
			    personelle=prof.get(8);
			    edit_personelle=prof.get(9);
			    list_personelle=prof.get(10);
			  	user=prof.get(11);
			  	edit_user=prof.get(12);
			  	list_user=prof.get(13);
			  	profil=prof.get(14);
			  	code=prof.get(15);
			  	code_ref=prof.get(16);
			  	code_gener=prof.get(17);
			  	code_rech=prof.get(18);
			  	article=prof.get(19);
			  	edit_article=prof.get(20);
			  	type=prof.get(21);
			  	imprime=prof.get(22);
			  	etiquette=prof.get(23);
			  	fiche=prof.get(24);
			  	list_fiche=prof.get(25);
			  	fourniss=prof.get(28);
			//  	System.out.println("eeeeee"+fourniss);
			  	edit_fourniss=prof.get(29);
			  	list_fourniss=prof.get(30);	
		    }
		    else{ex=false;}
	}
}
