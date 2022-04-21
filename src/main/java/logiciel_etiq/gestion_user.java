package logiciel_etiq;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_user {
	 protected String mat1,k="";
	 int code;
	 protected int l=0;
	 int code_p;
	 protected String nom,prenom,login,pass, prio,role,nom_p,prof;
	// protected String bdd;
	 private static String Chemin = "c:\\GCOBAR\\";
	  String bdd = Chemin + Utilitaire.InitBdd() + ".accdb";
	 protected String desc;
	 protected String desig;
	 protected String code_z;
	 protected boolean exist;
	 protected boolean exist_des=false;	
	 public ArrayList<String>  selection_profils(String matri){
		   ArrayList<String> list_pr=new ArrayList<String>();
		   ArrayList<String> resultat=new ArrayList<String>();
		  String query = "SELECT profils FROM utilisateur where login='"+matri+"'";
		  list_pr=CConnect.Requete(query, bdd);
		    if(list_pr.size()>=1){
		    code=Integer.parseInt(list_pr.get(0));
		  //  System.out.println(code);
		    String query2 = "SELECT poste,"
			 		+ "chaine"
			 		+ ",zone,personelle,profils.utilisateur,code,code_ref,code_gener,article,type"
			 		+ ",imprime,etiquette,fiche,fourniss,"
			 		+ "edit_poste,list_poste,edit_chaine,list_chaine,chang_chaine,edit_personelle,list_personelle,edit_user,list_user,code_rech"
			 		+ ",edit_fourniss,list_fourniss,edit_article ,list_fiche,profil,zone_edit,zone_list "
			 		+ "FROM profils,utilisateur where utilisateur.profils=profils.code_prof  and utilisateur.profils="+code;
		 resultat=CConnect.Requete(query2, bdd);
		 //  System.out.println(resultat);
		  
	 }
		    return resultat;
	 
	 }

	 public void  selection(String matri){
		    ArrayList<String> list_user=new ArrayList<String>();
		    ArrayList<String> list_prof=new ArrayList<String>();

		    String query = "SELECT matricule,nom,prenom,login,password,priorite,role,profils FROM utilisateur where matricule='"+matri+"'";
		    list_user=CConnect.Requete(query, bdd);
		    if(list_user.size()>1){
		    k=(String) list_user.get(0);
		    l=Integer.parseInt(k);
		    nom=(String) list_user.get(1);
		    prenom=(String) list_user.get(2);
		    login=(String) list_user.get(3);
		    pass=(String) list_user.get(4);
		    prio=(String) list_user.get(5);
		    role=(String) list_user.get(6);
		    prof=(String) list_user.get(7);
		    String query1 = "SELECT code_prof,nom_profils FROM profils where code_prof="+prof+""; 
		    list_prof=CConnect.Requete(query1, bdd);
		    code_p=Integer.parseInt(list_prof.get(0));
		    nom_p=(String) list_prof.get(1);
		    }
		    }
	 
	public void setinsert(String matri,String nom,String prenom,String login,String password,String prio,String role,int code_p){
    String query = "SELECT matricule,priorite FROM utilisateur where matricule='"+matri+"'";
    if(CConnect.Requete(query, bdd).size()>1){
      JOptionPane.showMessageDialog(null,"Cette personne existe déjà.");
       	
    }
    else{
    	  String insert = "insert into utilisateur(matricule,nom,prenom,login,password,priorite,role,profils) values('"+matri+"','"+nom.toLowerCase()+"','"+prenom.toLowerCase()+"'"
    	    + ",'"+login+"','"+password+"',"+prio+",'"+role+"',"+code_p+")";
    	CConnect.Insert(insert,bdd);
        JOptionPane.showMessageDialog(null,""+nom+" "+prenom+" a été bien ajouté");
	     }  
	}
	
	
	public void setupdate(String matri,String nom,String prenom,String login,String password,String prio,String role){
		  String update= "update utilisateur set nom='"+nom.toLowerCase()+"', prenom='"+prenom.toLowerCase()+"', login='"+login+"', "
	    	  		+ "password='"+password+"', priorite="+prio+", role='"+role+"'"
	    	  		+ "where matricule='"+matri+"'"; 
	    	  CConnect.Insert(update,bdd);
	    	  JOptionPane.showMessageDialog(null,""+nom+" "+prenom+" a été bien modifié");
		  
}
	
public boolean setdelete(String matri,String nom,String prenom){		
	  int reponse = JOptionPane.showConfirmDialog(null,
              "Voulez-vous vraiment supprimer "+nom+" "+prenom+" ",
              "Confirmation",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE);
if (reponse== JOptionPane.YES_OPTION){
	
	String update= "delete from utilisateur where matricule='"+matri+"'";
	  CConnect.Insert(update,bdd);
	  return true;
	//  setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);	
}
return false;
}


public boolean admin(String login){
	
	boolean vrai=false;


	String update= "select priorite from utilisateur where login='"+login+"'";
	ArrayList result=CConnect.Requete(update,bdd);
	if(result.size()!=0){

		if(result.get(0).equals("TRUE")) vrai=true;
		else vrai=false;


	}
	/*JOptionPane.showMessageDialog(null,""+nom+" "+prenom+" a �t� bien modifi�");
	try {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	} catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	}

	String url = "jdbc:ucanaccess://" + bdd + ";jackcessOpener=logiciel_etiq.CryptCodecOpener";

	System.out.println("La MaBdd ta3 CConnect eeeeeeeeesssssssssssttttt====  "+url);




      String user="admin";
	  String password="dsenie2015";

       
    Connection conn;
	try {
		conn = DriverManager.getConnection(url, user, password);
	    Statement state = conn.createStatement();
	    ResultSet result = state.executeQuery("select priorite from utilisateur where login='"+login+"'");

	    while(result.next()){
	    	String prio= result.getString("priorite") ;
	    
	         if(prio.equals("TRUE")) vrai=true;
	         else vrai=false;

	        
	    }	         result.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	     return false ;  

	}*/

	return vrai;
       
    //Cr�ation d'un objet Statement
    //L'objet ResultSet contient le r�sultat de la requ�te SQL
   
	  
}

public ArrayList<String> select(){
    ArrayList <String>resultat= new ArrayList<String>() ;
   	    String query5 = "SELECT matricule,nom,prenom,login,priorite,role FROM utilisateur where matricule<>'00000'";
        resultat=CConnect.Requete(query5,bdd);
      //System.out.println(resultat.size());  
        return resultat;        
}
  
          

public void ajouter_zone(String zone,String desig,String des)
{
	    
	    String insert = "insert into zone(Code_Zone,Designation_Zone,Desciption) values('"+zone+"','"+desig+"','"+des+"')"; 
	    	CConnect.Insert(insert,bdd);
		       
}

public void ajouter_zone_poste(String zone,String post)
{
	    
	    		String insert2 = "insert into Zone_Post (Code_Zone,Code_Poste,Nbr_Poste) values('"+zone+"','"+post+"','0')"; 
	    		CConnect.Insert(insert2,bdd);
}



public String afficher_codezone()
{   String code;
NumberFormat formatter = new DecimalFormat("00");
ArrayList<String> list_user=new ArrayList<String>();


	   String query2 = "SELECT max(Code_Zone) FROM zone"; 
	   list_user=CConnect.Requete(query2, bdd);
	   if(list_user.get(0)!=null){
      int cz=Integer.parseInt(list_user.get(0).substring(1))+1;
       code="Z"+formatter.format(cz);
	   }
	   else code="Z01";
       return code;
}


public ArrayList<String> select_zone_code(){
    ArrayList <String>resultat= new ArrayList<String>() ;
   	    String query5 = "SELECT Code_Zone,Designation_Zone FROM zone" ; 
	    if(CConnect.Requete(query5, bdd).size()>1){
        resultat=CConnect.Requete(query5,bdd);
	    }
        return resultat;              
}

//public ArrayList<String> select_poste_code(){
//    ArrayList <String>resultat= new ArrayList<String>() ;
//   	    String query5 = "SELECT Code_Poste,Intitule FROM Poste" ; 
//	    if(CConnect.Requete(query5, bdd).size()>1){
//
//		      //System.out.println(resultat.size()+"hyh"+(String) CConnect.Requete(query5,bdd).get(0));  
//
//        resultat=CConnect.Requete(query5,bdd);
//	    }
//	    else{
//	    	resultat.add(" ");
//	    	resultat.add(" ");	    
//	    }
//        return resultat;        
//       
//}

//public ArrayList<String> select_chaine_code(){
//    ArrayList <String>resultat= new ArrayList<String>() ;
//   	    String query5 = "SELECT code_chaine,designation_chaine FROM Chaine" ; 
//	    if(CConnect.Requete(query5, bdd).size()>1){
//		      //System.out.println(resultat.size()+"hyh"+(String) CConnect.Requete(query5,bdd).get(0));  
//        resultat=CConnect.Requete(query5,bdd);
//	    }
//        return resultat;        
//       
//}



public String select_poste_num(String  num,String code_z){
	String numo ="";
	ArrayList <String>list_user= new ArrayList<String>() ;
   	    String query5 = "SELECT Nbr_Poste FROM Poste,zone,Zone_Post where Zone_Post.Code_Poste='"+num+"' and Zone_Post.Code_Zone='"+code_z+"'  and Poste.Code_Poste=Zone_Post.Code_Poste and zone.Code_Zone=Zone_Post.Code_Zone " ;  
   	 list_user=CConnect.Requete(query5, bdd);
   	 
   	    if(list_user.size()>=1){
		      //System.out.println(resultat.size()+"hyh"+(String) CConnect.Requete(query5,bdd).get(0));  
        numo=list_user.get(0);
	    }
        return numo;        
       
}





public ArrayList<String> select_zone(String code){
        ArrayList <String>resultat= new ArrayList<String>() ;
	    String query5 = "SELECT Poste.Code_Poste,Intitule FROM Poste,Zone_Post where Poste.Code_Poste=Zone_Post.Code_Poste and Zone_Post.Code_Zone='"+code+"' " ;  
	    resultat=CConnect.Requete(query5,bdd);  
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



public boolean setdelete_zone(String code,String des){
	  int reponse = JOptionPane.showConfirmDialog(null,
            "Voulez-vous vraiment supprimer "+des+" ",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
if (reponse== JOptionPane.YES_OPTION){
	
	String update= "delete from zone where Code_Zone='"+code+"'";
	String update2= "delete from Zone_Post where Code_Zone='"+code+"'"; 
	CConnect.Insert(update,bdd);
	  CConnect.Insert(update2,bdd);
	  JOptionPane.showMessageDialog(null,"La zone a été bien supprimée");
	  return true;
}
return false;}


public void setupdate_zone(String code,String design,String desc){
	  String update= "update  zone set Designation_Zone='"+design+"', Desciption='"+desc+"' where Code_Zone='"+code+"'"; 
  	  CConnect.Insert(update,bdd);

	  
}

public void setupdate_zone_tab_delete(String code){
	String update2= "delete from Zone_Post where Code_Zone='"+code+"'"; 
	CConnect.Insert(update2,bdd);
	  
}

public void setupdate_zone_tab_ajou(String code,String code_p){
	String insert2 = "insert into Zone_Post (Code_Zone,Code_Poste,Nbr_Poste) values('"+code+"','"+code_p+"','0')"; 
 	CConnect.Insert(insert2,bdd);
}



public ArrayList<String> select_permanant(String poste){
    ArrayList <String>resultat= new ArrayList<String>() ;
   	    String query5 = "SELECT Matricule,nom,prenom FROM personelle where type='Permanant' and poste='"+poste+"'" ; 
	    if(CConnect.Requete(query5, bdd).size()>=1){
        resultat=CConnect.Requete(query5,bdd);
	    }
	    else{
	    	resultat.add(" ");
	    	resultat.add(" ");
	    	resultat.add(" ");
	    }
        return resultat;        
       
}

public ArrayList<String> select_volant(String zone){
    ArrayList <String>resultat= new ArrayList<String>() ;
   	    String query5 = "SELECT Matricule,nom,prenom FROM personelle where type='Volant' and zone='"+zone+"'" ; 
	    if(CConnect.Requete(query5, bdd).size()>1){

		     // System.out.println(resultat.size()+"hyh"+(String) CConnect.Requete(query5,bdd).get(0));  

        resultat=CConnect.Requete(query5,bdd);
	    }
        return resultat;        
       
}

}
  

