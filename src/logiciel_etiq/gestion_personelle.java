package logiciel_etiq;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_personelle {
	
    public String bdd;
    public String Matricule,nom,prenom,num_tel,N_chaine,poste,type,zone,des,intitule,des_chaine;
    public int l;
    
	  public void  selection(String matri){
			ArrayList <String>list_person= new ArrayList<String>() ;
			ArrayList <String>list_person2= new ArrayList<String>() ;
			ArrayList <String>list_person3= new ArrayList<String>() ;
			ArrayList <String>list_person4= new ArrayList<String>() ;
		    String query = "SELECT Matricule,nom,prenom,num_tel,N_chaine,poste,type,zone FROM personelle where Matricule='"+matri+"'"; 
		    list_person=CConnect.Requete(query, bdd);
		    if(list_person.size()>=1){
		    Matricule=(String) list_person.get(0);
		    l=Integer.parseInt(Matricule);
		    nom=(String) list_person.get(1);
		    prenom=(String) list_person.get(2);
		    num_tel=(String) list_person.get(3);
		    N_chaine=(String)list_person.get(4);
		    poste=(String) list_person.get(5);
		    type=(String) list_person.get(6);
		    zone=(String) list_person.get(7);
		    String query2 = "SELECT Designation_Zone FROM zone where Code_Zone='"+zone+"'";
		    list_person2=CConnect.Requete(query2, bdd);
		    if(list_person2.size()>=1){
		    des=(String) list_person2.get(0);
		    }
		    
		    String query3 = "SELECT Intitule FROM Poste where Code_Poste='"+poste+"'"; 
		    list_person3=CConnect.Requete(query3, bdd);
		    if(list_person3.size()>=1){
		    intitule=(String) list_person3.get(0);}
		   
		    String query4= "SELECT designation_chaine FROM Chaine where Code_Chaine='"+N_chaine+"'"; 
		    list_person4=CConnect.Requete(query4, bdd);
		    if(list_person4.size()>=1){
		    des_chaine=(String) list_person4.get(0);}
		    
		    }
		    
	 }
	  
	public void setinsert(String matri,String nom,String prenom,String num_tel,String n_chaine,String poste,String type,String zone){

	    String query = "SELECT Matricule FROM personelle where Matricule='"+matri+"'"; 
	    if(CConnect.Requete(query, bdd).size()>1){
	      JOptionPane.showMessageDialog(null,"Cette personne existe déjà.");
	      
	    }
	    else{
	    	  String insert = "insert into personelle (Matricule,nom,prenom,num_tel,N_chaine,poste,type,zone)"
	    	  		+ " values('"+matri+"','"+nom.toLowerCase()+"','"+prenom.toLowerCase()+"'"
	    	    + ",'"+num_tel+"','"+n_chaine+"','"+poste+"','"+type+"','"+zone+"')"; 
	    	CConnect.Insert(insert,bdd);
	        
	        JOptionPane.showMessageDialog(null,""+nom+" "+prenom+" a été bien ajouté");

		     }  
		
		
		} 
	

	public void setupdate(String matri,String nom,String prenom,String num_tel,String n_chaine,String poste,String type,String zone){
		
		  String update= "update personelle set nom='"+nom.toLowerCase()+"', prenom='"+prenom.toLowerCase()+"', num_tel='"+num_tel+"', "
	    	  		+ "N_chaine='"+n_chaine+"', poste='"+poste+"', type='"+type+"', zone='"+zone+"'"
	    	  		+ "where Matricule='"+matri+"'"; 
	    	  CConnect.Insert(update,bdd);
	    	  JOptionPane.showMessageDialog(null,""+nom+" "+prenom+" a été bien modifié");
		  
}

	public boolean setdelete(String matri,String nom_prenom){
		  int reponse = JOptionPane.showConfirmDialog(null,
	              "Voulez-vous vraiment supprimer "+nom_prenom+"",
	              "Confirmation",
	              JOptionPane.YES_NO_OPTION,
	              JOptionPane.QUESTION_MESSAGE);
	if (reponse== JOptionPane.YES_OPTION){
		
		String update= "delete  from  personelle where Matricule='"+matri+"'"; 
		  CConnect.Insert(update,bdd);
		  return true;
	}

	return false;

	 
	}
	
	public ArrayList<String> select(){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	    String query5 = "SELECT pers.poste,Poste.Intitule,pers.Matricule, pers.nom, pers.prenom, pers.num_tel, pers.N_chaine,  pers.type, pers.zone,zone.Designation_Zone, Chaine.designation_chaine"
	   	    		+ " FROM personelle as pers,zone,Poste,Chaine where Poste.Code_Poste=pers.poste and zone.Code_Zone=pers.zone and Chaine.code_chaine=pers.N_chaine"; 
	        resultat=CConnect.Requete(query5,bdd);	     
	       // System.out.println("gggggg"+resultat);
	        return resultat;        
	       
	}
	  
}
