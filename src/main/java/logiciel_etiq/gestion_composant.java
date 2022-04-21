package logiciel_etiq;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_composant {
	
	
	 String mat1,k="";
	 ArrayList<String>  m;
	 int l=0;
	  String CODE_ORIGINE,INDICE,CODE_ENIE,DESIGNATION,COEFF,Position;
		String bdd;
	 public void  selectcomp(){
		 
	    String query = "SELECT CODE_ORIGINE,INDICE,CODE_ENIE,DESIGNATION,COEFF,Position FROM composant"; 
	    if(CConnect.Requete(query, bdd).size()>1){
	    

	    CODE_ORIGINE=(String) CConnect.Requete(query,bdd).get(0);
	    INDICE=(String) CConnect.Requete(query,bdd).get(1);
	    CODE_ENIE=(String) CConnect.Requete(query,bdd).get(2);
	    DESIGNATION=(String) CConnect.Requete(query,bdd).get(3);
	    COEFF=(String) CConnect.Requete(query,bdd).get(4);
	    Position=(String) CConnect.Requete(query,bdd).get(5);
	    }
	    
 }
	 
	 
	 public ArrayList<String> select_comp(String des_code){
		    ArrayList <String>resultat= new ArrayList<String>() ;
		   	    String query5 = "SELECT CODE_ORIGINE,INDICE,CODE_ENIE,DESIGNATION,COEFF,Position FROM composant where CODE_ORIGINE='"+des_code+"' or DESIGNATION like '%"+des_code+"%'" ; 
			    if(CConnect.Requete(query5, bdd).size()>1){
 

		        resultat=CConnect.Requete(query5,bdd);
			    }
		        return resultat;        
		       
		}
	 

public void ajouter_composant(String CODE_ORIGINE,int INDICE,String CODE_ENIE,String DESIGNATION,int COEFF,String Position )
{
	 String query = "SELECT * FROM composant where CODE_ORIGINE="+CODE_ORIGINE+""; 
	    if(CConnect.Requete(query, bdd).size()>1){
	      	JOptionPane.showMessageDialog(null,"Ce composant  existe déjà.");
	       
	    }
	    else{
	    	try {	
	    	  String insert = "insert into composant(CODE_ORIGINE,INDICE,CODE_ENIE,DESIGNATION,COEFF,Position)   values('"+CODE_ORIGINE+"',"+INDICE+",'"+CODE_ENIE+"','"+DESIGNATION+"', "+COEFF+",'"+Position+"')"; 
	    	CConnect.Insert(insert,bdd);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
	    	
			e.printStackTrace();
		}
		       
		     }  
			
}


public void update_composant(String CODE_ORIGINE,int INDICE,String CODE_ENIE,String DESIGNATION,int COEFF,String Position ){
	

	  String update= "update composant set CODE_ORIGINE='"+CODE_ORIGINE+"', INDICE="+INDICE+",  "
  	  		+ "DESIGNATION='"+DESIGNATION+"', COEFF="+COEFF+", Position='"+Position+"'" 
  	  		+ "where CODE_ENIE='"+CODE_ENIE+"'"; 
  	  CConnect.Insert(update,bdd);
	  
}

public void delete_composant(String CODE_ENIE){
	  int reponse = JOptionPane.showConfirmDialog(null,
	            "Voulez-vous vraiment supprimer "+CODE_ENIE+" ",
	            "Confirmation",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE);
	if (reponse== JOptionPane.YES_OPTION){
	String update= "delete from composant where CODE_ENIE='"+CODE_ENIE+"'"; 
	  CConnect.Insert(update,bdd);
	}
	//  setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);	
}





}
