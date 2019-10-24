package logiciel_etiq;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class gestion_changement {
	public boolean exist;
		public boolean existe;
		private String bdd;

	public boolean ajouter_changement(String chaine,String zone,String poste,String perm,String vol, String date,String heure_d,String heure_f)
	{
		exist=false;
		
		String query = "SELECT * FROM changement_chaine where code_chaine='"+chaine+"' and code_zone='"+zone+"' and code_poste='"+poste+"' and code_permanant='"+perm+"' and code_volant='"+vol+"'"
		 + " and date_change=Format('"+date+"','dd/MM/yy') and heure_debut='"+heure_d+"' and heure_fin='"+heure_f+"'"; 
		
		if(CConnect.Requete(query, bdd).size()>=1){
		    JOptionPane.showMessageDialog(null,"Ce changement est d�j� effectu�.");
		    exist=true;
		    return exist;
		    }
		    else{
		     try {	
		    	String insert = "insert into changement_chaine(code_chaine,code_zone,code_poste,code_permanant,code_volant,date_change,heure_debut,heure_fin ) "
		    	 + "values('"+chaine+"','"+zone+"','"+poste+"','"+perm+"','"+vol+"','"+date+"','"+heure_d+"','"+heure_f+"')"; 
		    	CConnect.Insert(insert,bdd);
		    } catch (Exception e) {
				e.printStackTrace();
			}
			       return exist;
			     }  	}
	
	
	
	public ArrayList<String> select_chaine_zone_date(String chaine,String zone,String date){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	    ArrayList <String>resultat1= new ArrayList<String>() ;
	    ArrayList <String>resultat2= new ArrayList<String>() ;
        String query5 = "SELECT changement_chaine.code_poste,Poste.Intitule,changement_chaine.code_permanant"
		    		+ ",personelle.nom,personelle.prenom,"
		    		+ "changement_chaine.heure_debut,changement_chaine.heure_fin "
		    		+ " from changement_chaine,Poste,zone,Chaine,personelle "
		    		+ "where changement_chaine.code_poste=Poste.Code_Poste "
		    		+ "and changement_chaine.code_permanant=personelle.Matricule "
		    		+ "and  changement_chaine.code_chaine=Chaine.code_chaine "
		    		+ "and  changement_chaine.code_zone=zone.Code_Zone "
		    		+ "and changement_chaine.code_chaine='"+chaine+"' "
		    		+ "and changement_chaine.code_zone='"+zone+"' "
		    		+ "and changement_chaine.date_change=Format('"+date+"','dd/MM/yy')"
		    		;
		    
		    String query = "SELECT changement_chaine.code_volant,personelle.nom,personelle.prenom"
		    		+ " from changement_chaine,Poste,zone,Chaine,personelle "
		    		+ "where changement_chaine.code_poste=Poste.Code_Poste "
		    		+ "and  changement_chaine.code_volant=personelle.Matricule "
		    		+ "and  changement_chaine.code_chaine=Chaine.code_chaine "
		    		+ "and  changement_chaine.code_zone=zone.Code_Zone "
		    		+ "and changement_chaine.code_chaine='"+chaine+"' "
		    		+ "and changement_chaine.code_zone='"+zone+"' "
		    	+ "and changement_chaine.date_change=Format('"+date+"','dd/MM/yy')"
		    		;
		    	
		    		
	     
		    resultat1=CConnect.Requete(query5,bdd);
		    resultat2=CConnect.Requete(query,bdd);	 
		   // System.out.println("eeeeeee"+resultat1);
            resultat.addAll(resultat1);
            resultat.addAll(resultat2);
	        return resultat;        
	       
	}
	
	public void Update_changement(String chaine,String zone,String poste,String perm,String vol, String date,String heure_d,String heure_f){	
		
	    String query ="delete from  changement_chaine where code_chaine='"+chaine+"' "
		 		 		+ "and code_zone='"+zone+"' and date_change=Format('"+date+"','dd/MM/yy')";
		 CConnect.Requete(query,bdd);
		 String insert = "insert into changement_chaine(code_chaine,code_zone,code_poste,code_permanant,code_volant,date_change,heure_debut,heure_fin ) "
		 + "values('"+chaine+"','"+zone+"','"+poste+"','"+perm+"','"+vol+"','"+date+"','"+heure_d+"','"+heure_f+"')"; 
		  CConnect.Insert(insert,bdd);
	}
	
	public void  delete_changement(String chaine,String zone,String date){
		
		 String query ="delete from  changement_chaine where code_chaine='"+chaine+"' "
		 		+ "and code_zone='"+zone+"' and date_change=Format('"+date+"','dd/MM/yy')";
		 CConnect.Requete(query,bdd);
		 
	}
	
	public boolean select_chang(String chaine,String zone,String date){
		//existe=false;
		String query1 = "SELECT * FROM changement_chaine where code_chaine='"+chaine+"' and code_zone='"+zone+"' and date_change=Format('"+date+"','dd/MM/yy')";
		 if(CConnect.Requete(query1, bdd).size()==0){
		      //existe=true;
		      return true;
		    }
	      	return false;
	}
}
