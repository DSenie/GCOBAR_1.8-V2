package logiciel_etiq;

import java.util.ArrayList;

public class gestion_recap {
	 ArrayList<String>  m;
		boolean exist,existe;
	String bdd;
	public  ArrayList<String>  recherche(String chaine,String zone,String poste,String rech)
	{	    ArrayList <String>resultat= new ArrayList<String>() ;

//		 String query = "select personelle.Matricule,personelle.nom,personelle.prenom,personelle.num_tel,personelle.type from personelle,Chaine,zone,poste"
//		 		+ " where personelle.N_chaine=Chaine.code_chaine and personelle.N_chaine='"+chaine+"' "
//		 				+ " and  personelle.zone=zone.Code_Zone and personelle.zone='"+zone+"'"
//		 						+ " and   personelle.poste=Poste.Code_Poste and personelle.poste='"+poste+"' "
//		 								+ "and  personelle.Matricule like '%"+rech+"%' or  personelle.nom like '%"+rech+"%' or personelle.prenom like '%"+rech+"%'"
//		 										+ "or personelle.num_tel like '%"+rech+"%' or personelle.type like '%"+rech+"%'";
//		 if(rech.equals("")&&(chaine.equals("---S�lectionner")||zone.equals("---S�lectionner")||(poste.equals("---S�lectionner")))){
//			query= query.replace("  personelle.Matricule like '%"+rech+"%' or  personelle.nom like '%"+rech+"%' or personelle.prenom like '%"+rech+"%'"
//						+ "or personelle.num_tel like '%"+rech+"%' or personelle.type like '%"+rech+"%'", "");
//		 }else 
//			
//		 if(chaine.equals("---S�lectionner")){
//			 query= query.replace(" personelle.N_chaine=Chaine.code_chaine and personelle.N_chaine='"+chaine+"' "
//		 				+ " and", "");
//		 }
//		 if(zone.equals("---S�lectionner")){
//			 query= query.replace(" personelle.zone=zone.Code_Zone and personelle.zone='"+zone+"'"
//		 						+ " and", "");}
//			 else if(!zone.equals("---S�lectionner")&&(poste.equals("---S�lectionner"))){
//				 
//			 }
//		 
//		 if(poste.equals("---S�lectionner")){
//			 query= query.replace("personelle.poste=Poste.Code_Poste and personelle.poste='"+poste+"' "
//		 								+ "and ", "");
//		 }
		
		 String query = "select Matricule,nom,prenom,num_tel,type from personelle where ";
		 if(!rech.equals("")&&chaine.equals("---S�lectionner")&&zone.equals("---S�lectionner")&&(poste.equals("---S�lectionner"))){	 
			 
			 query=query.concat(" Matricule like '%"+rech+"%' or  nom like '%"+rech+"%' or prenom like '%"+rech+"%'"
	 										+ " or num_tel like '%"+rech+"%' or type like '%"+rech+"%'");
		 }
		 
		 
         if(rech.equals("")&&!chaine.equals("---S�lectionner")&&zone.equals("---S�lectionner")&&poste.equals("---S�lectionner")){	 
			
			 query=query.concat(" N_chaine='"+chaine+"'");
			 //System.out.println("jjjjjjjjjjj"+query);
		 }
         
       if(rech.equals("")&&chaine.equals("---S�lectionner")&&!zone.equals("---S�lectionner")&&poste.equals("---S�lectionner")){	 
			 
			 query=query.concat(" zone='"+zone+"'");
		 }
         
       if(rech.equals("")&&chaine.equals("---S�lectionner")&&zone.equals("---S�lectionner")&&!poste.equals("---S�lectionner")){	 
			 
			 query=query.concat("   poste='"+poste+"'");
		 }
       
       if(!rech.equals("")&&!chaine.equals("---S�lectionner")&&zone.equals("---S�lectionner")&&poste.equals("---S�lectionner")){	 
			 
			 query=query.concat(" Matricule like '%"+rech+"%' or  nom like '%"+rech+"%' or prenom like '%"+rech+"%'"
	 										+ " or num_tel like '%"+rech+"%' or type like '%"+rech+"%' and "
	 												+ "  N_chaine='"+chaine+"'");
		 }
       
       if(!rech.equals("")&&chaine.equals("---S�lectionner")&&!zone.equals("---S�lectionner")&&poste.equals("---S�lectionner")){	 
			 
			 query=query.concat(" Matricule like '%"+rech+"%' or  nom like '%"+rech+"%' or prenom like '%"+rech+"%'"
	 										+ " or num_tel like '%"+rech+"%' or type like '%"+rech+"%' and"
	 												+ "  zone='"+zone+"'");
		 }
       
       if(!rech.equals("")&&chaine.equals("---S�lectionner")&&zone.equals("---S�lectionner")&&!poste.equals("---S�lectionner")){	 
			 
			 query=query.concat("  Matricule like '%"+rech+"%' or  nom like '%"+rech+"%' or prenom like '%"+rech+"%'"
	 										+ " or num_tel like '%"+rech+"%' or type like '%"+rech+"%' "
	 												+ " and  poste='"+poste+"'");
		 }
       
       
       if(rech.equals("")&&!chaine.equals("---S�lectionner")&&!zone.equals("---S�lectionner")&&poste.equals("---S�lectionner")){	 
			 
			 query=query.concat("  N_chaine='"+chaine+"' and zone='"+zone+"'");
		 }
       
       if(rech.equals("")&&!chaine.equals("---S�lectionner")&&zone.equals("---S�lectionner")&&!poste.equals("---S�lectionner")){	 
			 
			 query=query.concat("  N_chaine='"+chaine+"' and"
			 		+ "  poste='"+poste+"'");
		 }
       
       if(rech.equals("")&&chaine.equals("---S�lectionner")&&!zone.equals("---S�lectionner")&&!poste.equals("---S�lectionner")){	 
			 
			 query=query.concat("  zone='"+zone+"' and"
			 		+ "  poste='"+poste+"'");
		 }
       
       if(!rech.equals("")&&!chaine.equals("---S�lectionner")&&!zone.equals("---S�lectionner")&&poste.equals("---S�lectionner")){	 
			 
			 query=query.concat("  Matricule like '%"+rech+"%' or  nom like '%"+rech+"%' or prenom like '%"+rech+"%'"
	 										+ " or num_tel like '%"+rech+"%' or type like '%"+rech+"%' "
	 												+ " and zone='"+zone+"' and"
			 		+ " N_chaine='"+chaine+"'");
		 }
       if(!rech.equals("")&&!chaine.equals("---S�lectionner")&&zone.equals("---S�lectionner")&&!poste.equals("---S�lectionner")){	 
			 
			 query=query.concat("  Matricule like '%"+rech+"%' or  nom like '%"+rech+"%' or prenom like '%"+rech+"%'"
	 										+ " or num_tel like '%"+rech+"%' or type like '%"+rech+"%' "
	 												+ " and poste='"+poste+"' and"
			 		+ "  N_chaine='"+chaine+"'");
		 }
       
       if(!rech.equals("")&&chaine.equals("---S�lectionner")&&!zone.equals("---S�lectionner")&&!poste.equals("---S�lectionner")){	 
			 
			 query=query.concat("  Matricule like '%"+rech+"%' or  nom like '%"+rech+"%' or prenom like '%"+rech+"%'"
	 										+ " or num_tel like '%"+rech+"%' or type like '%"+rech+"%' "
	 												+ " and poste='"+poste+"' and"
			 		+ "  zone='"+zone+"'");
		 }
       
       if(rech.equals("")&&!chaine.equals("---S�lectionner")&&!zone.equals("---S�lectionner")&&!poste.equals("---S�lectionner")){	 
			 
			 query=query.concat(" N_chaine='"+chaine+"' "
	 												+ "  and poste='"+poste+"' and"
			 		+ "  zone='"+zone+"'");
		 }
       
       if(!rech.equals("")&&!chaine.equals("---S�lectionner")&&!zone.equals("---S�lectionner")&&!poste.equals("---S�lectionner")){	 
			 
			 query=query.concat("Matricule like '%"+rech+"%' or  nom like '%"+rech+"%' or prenom like '%"+rech+"%'"
	 										+ " or num_tel like '%"+rech+"%' or type like '%"+rech+"%' and   N_chaine='"+chaine+"' "
	 												+ " and poste='"+poste+"' and"
			 		                               + " zone='"+zone+"'");
		 }
       
       resultat=CConnect.Requete(query,bdd);
      //   System.out.println(resultat);
       return resultat;
		 }
		
		
	}

