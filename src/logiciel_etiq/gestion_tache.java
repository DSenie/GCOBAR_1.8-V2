package logiciel_etiq;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_tache {
	public String bdd;
	public boolean exist,ex;	
	public String intitul,code;
	
	public int select_profils_ncode(String process){
   	    String query5 = "SELECT Ncode FROM profils_prod where  intitule='"+process+"'"; 
   	    int ncode = 0;
	   
	    if(CConnect.Requete(query5, bdd).size()>=1){
	    	 ncode=Integer.parseInt(CConnect.Requete(query5, bdd).get(0));
	    	return ncode;}
	    else return 0;
          }
	
	public boolean select_poste_profils(String process,String poste ){
   	    String query5 = "SELECT * FROM feuille_process,profils_prod"
   	    		+ " where feuille_process.profils=Ncode and intitule='"+process+"'"
   	    		+ " and feuille_process.poste='"+poste+"'  "; 
   	    //System.out.println(CConnect.Requete(query5, bdd));
	    if(CConnect.Requete(query5, bdd).size()>=1){
	       // System.out.println("eeee");

        return true;
	    }
	    else{
	    return false;
	    }
}
	
	public String[] select_outil(String code,String profil,boolean existe,int num_poste,int profil_p){
     //   ArrayList <String>resultat= new ArrayList<String>() ;
        String outil[] = {  };
        String query5;
       
       // String[] str = { "plastic", "paper", "use", "throw" };
        query5="select outillage from feuille_process where feuille_process.poste='"+code+"' "
		  		+ "and feuille_process.num_poste="+num_poste+" "
		  		+ "and feuille_process.profils= "+profil_p+"";
        if(CConnect.Requete(query5,bdd).size()!=0){
        outil=CConnect.Requete(query5,bdd).get(0).split(",");
       System.out.println("rrrrrrrrrrrrrr"+outil.length);
      
       for(int j=0;j<outil.length;j++){
    	   System.out.println("ggggggggggggg"+outil[j]);}
       // resultat.add(arg0);
        }
        return outil;
	}
	public ArrayList<String> select_tache(String code,String profil,boolean existe,int num_poste,int profil_p){
        ArrayList <String>resultat= new ArrayList<String>() ;
        String query5;
        //System.out.println(existe);
 
	  if(existe==false){
        query5  = "SELECT  tache.code_tache,intitule FROM poste_tache,tache where poste_tache.code_tache=tache.code_tache and poste_tache.code_poste='"+code+"'" ;  
	  
	  }else{

		  query5="SELECT tache_process.code_tache,tache.intitule,article.code_article,article.designation,"
		  		+ " temp_n,marge,temp_t FROM tache_process,tache,feuille_process,article"
		  		+ " where tache_process.code_tache=tache.code_tache and feuille_process.code=tache_process.code   "
		  		+ " and article.code_article=tache_process.code_article and feuille_process.poste='"+code+"' "
		  		+ "and feuille_process.num_poste="+num_poste+" "
		  		+ "and feuille_process.profils= "+profil_p+"";
	  }
       resultat=CConnect.Requete(query5,bdd);  
      // System.out.println(resultat);
        return resultat;        
}

	
	public ArrayList<String> select_article(String code,String profil,int num_poste,int profil_p){
        ArrayList <String>resultat= new ArrayList<String>() ;
        String query5;
        //System.out.println(existe);
        System.out.println("eeeeee"+resultat);
	 
        query5  = "SELECT article.code_article,designation ,code_fournisseur,coeff,position  "
        		+ " FROM article_tache,article,feuille_process,article_produit "
        		+ " where article.code_article=article_tache.code_article and feuille_process.code=article_tache.code"
        		+ " and  feuille_process.poste='"+code+"' "
		  		+ "and feuille_process.num_poste="+num_poste+" "
		  		+ "and feuille_process.profils= "+profil_p+""
		  				+ " and article_produit.code_article=article.code_article";  
	
	  
       resultat=CConnect.Requete(query5,bdd);  
       System.out.println("eeeeee"+resultat);
        return resultat;        
}


	public ArrayList<String> select_observation(String code,String profil,int num_poste,int profil_p){
        ArrayList <String>resultat= new ArrayList<String>() ;
        String query5;
        //System.out.println(existe);

	 
        query5  = "SELECT observation  FROM tache_observation,feuille_process"
        		+ " where  feuille_process.code=tache_observation.code"
        		+ " and feuille_process.poste='"+code+"' "
		  		+ "and feuille_process.num_poste="+num_poste+"  "
		  		+ "and feuille_process.profils= "+profil_p+"";  
	  
	  
       resultat=CConnect.Requete(query5,bdd);  
     
        return resultat;        
}
	public String afficher_code_tache()
	{  
	ArrayList <String>list_code= new ArrayList<String>() ;
    String code;
    NumberFormat formatter = new DecimalFormat("00");
	String query2 = "SELECT max(code_tache) FROM tache"; 
	list_code=CConnect.Requete(query2, bdd);
    if(list_code.get(0)!=null){
	int cz=Integer.parseInt(list_code.get(0).substring(1))+1;
	     code="T"+formatter.format(cz);
		                                          }
	else code="T01";
	return code;
	}
	
	public ArrayList<String> select_tache_code(){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	    String query5 = "SELECT code_tache,intitule FROM tache" ; 
	   		//System.out.println(CConnect.Requete(query5, bdd).size());
		    if(CConnect.Requete(query5, bdd).size()>1){
	        resultat=CConnect.Requete(query5,bdd);
		    }
		   
	        return resultat;        
	       
	}
	public void tache(String tache)
	{
		ArrayList <String>list_tache= new ArrayList<String>() ;
		 String query = "SELECT intitule,code_tache FROM tache where intitule='"+tache+"'"; 
		 list_tache=CConnect.Requete(query, bdd);
		  if(list_tache.size()>=1){
		      	ex=true;
		      	intitul=list_tache.get(0);
		      	code=list_tache.get(1);
		    }
		    else{
		    	ex=false;
		    }
		    
	}
	
	public void delete_tache(String code, String intitule){
		  int reponse = JOptionPane.showConfirmDialog(null,
	            "Voulez-vous vraiment supprimer "+intitule+" ",
	            "Confirmation",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE);
	if (reponse== JOptionPane.YES_OPTION){
		String update2= "delete from tache where code_tache='"+code+"'"; 
		CConnect.Insert(update2,bdd);
	  	JOptionPane.showMessageDialog(null,"La Tache a été bien supprimé");

	}}
	
	public void setupdate_tache(String code,String Intitule){
		  String update= "update  tache set intitule='"+Intitule+"' where code_tache='"+code+"'"; 
	  	  CConnect.Insert(update,bdd);
	}
	
	public boolean ajouter_tache(String tache,String intitule)
	{
		exist=false;
		String query = "SELECT * FROM tache where code_tache='"+tache+"' "; 
        if(CConnect.Requete(query, bdd).size()>=1){
		      	JOptionPane.showMessageDialog(null,"Cette Tache existe déjà");
		      	exist=true;
		      	return exist;
		    }
		    else{
		       try {	
		    	 String insert = "insert into tache(code_tache,intitule) values('"+tache+"','"+intitule+"')"; 
		    	 CConnect.Insert(insert,bdd);
		    	
		    } catch (Exception e) {
				e.printStackTrace();
			}
			       return exist;
			     }  
				
	}
	
	
	public ArrayList<String> select_article_tache(String code,String produit){
	    ArrayList <String>resultat= new ArrayList<String>() ;
	   	    String query5 = "SELECT code_fournisseur,coeff,position FROM article,article_produit,produit where "
	   	    		+ "article.code_article='"+code+"' and article.code_article=article_produit.code_article and"
	   	    				+ " article_produit.model=produit.code_produit and code_produit='"+produit+"'" ; 
	   	//	System.out.println(CConnect.Requete(query5, bdd).size());
		    if(CConnect.Requete(query5, bdd).size()>=1){
	        resultat=CConnect.Requete(query5,bdd);
		    }
		   
	        return resultat;        
	       
	}
	
	
	public void delete_article(int code_p ){
		if(code_p!=0){
			String insert="delete from article_tache where code="+code_p+" ";
			 CConnect.Insert(insert,bdd);
	      }
	}
	public void insert_article_process(int code_p ,String code_article){	
	
		String insert_article= " insert into article_tache (code_article,code) "
				+ " values('"+code_article+"',"+code_p+")";
		  CConnect.Insert(insert_article,bdd);
	  }
	
	
	public void delete_observation(int code){
		if(code!=0){
			String insert="delete from tache_observation where code="+code+" ";
			 CConnect.Insert(insert,bdd);
	      }
	}
	public void insert_observation_process(int code,String observation ){
		
		String insert_article= " insert into tache_observation (code,observation) "
				+ " values("+code+",'"+observation+"')";
		  CConnect.Insert(insert_article,bdd);
	  }
	
	public void delete_tache_process(String code_poste,int code_profils,int num_poste){
		 ArrayList <String>resultat= new ArrayList<String>() ;
		  String select= " select code from feuille_process where  "
		  		+ "  poste='"+code_poste+"' and "
		  		+ " profils="+code_profils+" and num_poste="+num_poste+" ";
		  resultat=CConnect.Requete(select,bdd);
		  if(resultat.size()>1){
		  String delete= " delete from tache_process where code="+resultat.get(0)+""; 
	  	  CConnect.Insert(delete,bdd);
		  }
	}
	public void insert_feuille_process(int process,String code_poste,String outillage,int num_poste){
		 String insert;
	String select2= " select  (profils) from feuille_process where profils="+process+" "
			+ "and poste='"+code_poste+"' and num_poste="+num_poste+"";
	     if(CConnect.Requete(select2,bdd).size()<1){
     insert= " insert into feuille_process(profils,poste,outillage,num_poste) "
			+ " values("+process+",'"+code_poste+"', '"+outillage+"',"+num_poste+")"; 
	  
	     }
	     else{
	    	  insert= " update feuille_process set outillage= '"+outillage+"' where profils="+process+" "
			+ "and poste='"+code_poste+"' and num_poste="+num_poste+" ";
	    	// System.out.println("hhhhhhhh");
	     }
	     CConnect.Insert(insert,bdd);
	  }
	  
	
	public void delete_feuille_process(int process,int code,String code_poste,int num_poste){
		
		String delete= "delete from feuille_process where profils="+process+" "
			+ "and poste='"+code_poste+"' and num_poste="+num_poste+"   ";
		String delete_t= "delete from tache_process where code="+code+"";
		String delete_o= "delete from tache_observation where code="+code+"";
		String delete_a= "delete from article_tache where code="+code+"";
		CConnect.Insert(delete,bdd);
		CConnect.Insert(delete_t,bdd);
		CConnect.Insert(delete_o,bdd);
		CConnect.Insert(delete_a,bdd);
	}

	
	
	public int code_tache(int process,String code_poste,int num_poste ){
		
		int resultat=0;
		String select= " select  (code) from feuille_process where profils="+process+" "
				+ "and poste='"+code_poste+"'  and num_poste="+num_poste+" ";
		//  System.out.println(process+" "+code_poste+" "+date_jours+" "+num_poste);	
		  resultat=Integer.parseInt(CConnect.Requete(select,bdd).get(0));
		 return resultat;
	}
	public void delete_tache(int code_tach){
		if(code_tach!=0){
			String insert="delete from tache_process where code="+code_tach+" ";
			 CConnect.Insert(insert,bdd);
	      }
		
	}
	public void insert_tache_process(int profils,int code_tach,String code_poste,String code_tache,String code_article,float temp_n,float temp_t,String marg
			,int num_poste){
		
		String insert= " insert into tache_process(code,code_tache,code_article,temp_n,temp_t,marge) "
				+ " values("+code_tach+",'"+code_tache+"','"+code_article+"',	"+temp_n+","+temp_t+", '"+marg+"')"; 
		  CConnect.Insert(insert,bdd);
	  }
}
