package logiciel_etiq;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_chaine {
	 protected String desc;
	 protected String desig;
	 protected String code_chaine;
	 protected boolean exist;
	 protected boolean exist_des=false;	
     protected String bdd;
     
     
 	/*************************** pour remplir le JCOmbobx zone *******************/
     
	 public ArrayList<String> select_zone_code(){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	 String query5 = "SELECT Code_Zone,Designation_Zone FROM zone" ; 
		 if(CConnect.Requete(query5, bdd).size()>1){
	        resultat=CConnect.Requete(query5,bdd);
		                                           }
	        return resultat;        
	}
	
	/*************************** pour remplir le tableau *******************/
	 public ArrayList<String> select_chaine(String code){
	        ArrayList <String>resultat= new ArrayList<String>() ;  
		    String query5 = "SELECT zone.Code_Zone,zone.Designation_Zone FROM zone,Chaine_Zone where zone.Code_Zone=Chaine_Zone.Code_Zone and Chaine_Zone.Code_Chaine='"+code+"' " ;  
		    resultat=CConnect.Requete(query5,bdd);      
	        return resultat;        
	                                                        }

/*************************** pour verifier si la chaine existe deja *******************/

	    public boolean select_chaine_jtext(String code){ 
	     ArrayList<String> list_chaine=new ArrayList<String>();

	   	   String query5 = "SELECT designation_chaine,description,code_chaine FROM Chaine where code_chaine='"+code+"' or designation_chaine='"+code+"' " ;  
	   	list_chaine=CConnect.Requete(query5, bdd);
	   	   if(list_chaine.size()>=1){
	   	      exist_des=false;
	          desig=list_chaine.get(0);      
	          desc=list_chaine.get(1);
	          code_chaine=list_chaine.get(2);
	          
	   	                                             }
	   else{
	   	exist_des=true;
	   }
	   	return exist_des;
	   	}

	    
	    
	public String afficher_codechaine()
	{   
		 ArrayList<String> list_chaine=new ArrayList<String>();
    String code;
	NumberFormat formatter = new DecimalFormat("00");
    String query2 = "SELECT max(code_chaine) FROM Chaine"; 
    list_chaine=CConnect.Requete(query2, bdd);
	if(list_chaine.get(0)!=null){
	  int cz=Integer.parseInt(list_chaine.get(0).substring(1))+1;
	  code="C"+formatter.format(cz);
		                                          }
    else code="C01";
	return code;
	}
	
	public ArrayList<String> select_chaine_code(){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	String query5 = "SELECT code_chaine,designation_chaine FROM Chaine" ; 
	   	
		 if(CConnect.Requete(query5, bdd).size()>1){
	        resultat=CConnect.Requete(query5,bdd);
		                                           }
	        return resultat;        
	}
	

	public void setdelete_chaine(String code,String des){
		  int reponse = JOptionPane.showConfirmDialog(null,
	            "Voulez-vous vraiment supprimer "+des+"?",
	            "Confirmation",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE);
	if (reponse== JOptionPane.YES_OPTION){
		String update= "delete from Chaine where code_chaine='"+code+"'";
		String update1= "delete from Chaine_Zone where Code_Chaine='"+code+"'";
		CConnect.Insert(update,bdd);
		CConnect.Insert(update1,bdd);

		JOptionPane.showMessageDialog(null,"La Chaine a �t� bien supprim�e");
	}
	}
	
	

public void ajouter_chaine(String chaine,String desig,String des)
{
	 String query = "SELECT * FROM Chaine where code_chaine='"+chaine+"' OR designation_chaine='"+chaine+"' "; 
      if(CConnect.Requete(query, bdd).size()>=1){
	      	JOptionPane.showMessageDialog(null,"Cette Chaine  existe d�j�.");
	    }
	  else{
	     try {	
	    	String insert = "insert into Chaine(code_chaine,designation_chaine,description) values('"+chaine+"','"+desig+"','"+des+"')"; 
	    	CConnect.Insert(insert,bdd);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		     }  
}


	public void ajouter_chaine_zone(String chaine,String zone)
	{
	   try {	
		  String insert2 = "insert into Chaine_Zone (Code_Chaine,Code_Zone) values('"+chaine+"','"+zone+"')"; 
		  CConnect.Insert(insert2,bdd);
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public void setupdate_chaine(String code,String design,String desc){
		  String update= "update  Chaine set designation_chaine='"+design+"', description='"+desc+"' where Code_Chaine='"+code+"'"; 
	  	  CConnect.Insert(update,bdd);
	}
	
	public void setupdate_chaine_tab_delete(String code){
		String update2= "delete from Chaine_Zone where Code_Chaine='"+code+"'"; 
		CConnect.Insert(update2,bdd);
	}
	
	public void setupdate_chaine_tab_ajou(String code,String code_z){
		String insert2 = "insert into Chaine_Zone (Code_Chaine,Code_Zone) values('"+code+"','"+code_z+"')"; 
	 	CConnect.Insert(insert2,bdd);
	}

}
