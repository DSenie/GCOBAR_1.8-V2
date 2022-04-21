package logiciel_etiq;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class gestion_article {
	protected String bdd="C:\\GCOBAR\\IE.accdb";;
	protected boolean exist,ex,exist_des=false;	
	protected float pu=0;
	protected int coeff=1;
	protected int indice=0;
	  String  designation,entreprise="",model=" ",software,ingenieur,Ncontrat,date_deplacement,quantite,description,designation_ref,code_ref,code_gener,designation_gener,
	  code_famille,designation_famille,code_article,code_fourniss,nom_fourniss,code_fourniss1,code_fourniss2;

	public ArrayList<String> select_article_code(String entrepr ){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT code_article,designation FROM article where entreprise='"+entrepr+"'  " ; 
		if(CConnect.Requete(query5, bdd).size()>=1){
		resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;        
		}
	
	public ArrayList<String> select_article_codenomo(String entrepr){
		ArrayList <String>resultat= new ArrayList<String>() ;
	//	System.out.println("gggggg"+entrepr);
		String query5;
		
		 query5 = "SELECT code_article,designation FROM article where entreprise='"+entrepr+"' ";
		if(CConnect.Requete(query5, bdd).size()>=1){
		resultat=CConnect.Requete(query5,bdd);
		}
	//	System.out.print(resultat);
		return resultat;        

		}
	
	
	 public ArrayList<String> model_imp(){
		    ArrayList <String>resultat= new ArrayList<String>() ;
		    String query = "SELECT * FROM model_imp"; 
		    resultat=CConnect.Requete(query,bdd);
		    return resultat;
		
	 }
	 
	 
	 
	
	 public void article(String designation ){

		    String query = "SELECT code_article,designation FROM article where  designation='"+designation+"'"; 
		  //  System.out.println("reereeeee"+ CConnect.Requete(query, bdd).size());
		    if(CConnect.Requete(query, bdd).size()>=1){
		      ex=true;

		    }
		    else ex =false;
	 }
		    
	
	public String afficher_code_produit(String cod_gener, String ref)
	{  
		String code;


	NumberFormat formatter = new DecimalFormat("000");
//		   String query2 = "SELECT  r.max FROM ("
//     +" SELECT  code_gener,MAX(code_article) as max"+
//    "  FROM article  GROUP BY code_gener)  r INNER JOIN article t ON t.code_gener = r.code_gener AND t.code_article = r.max";
	
		   String query2 ="SELECT  max(code_article) FROM article where code_gener='"+cod_gener+"' and  code_ref='"+ref+"' ";
		   
		   if(CConnect.Requete(query2, bdd).get(0)!=null){ 
	    	int cz=Integer.parseInt(CConnect.Requete(query2,bdd).get(0).substring(5))+1;
	         code=formatter.format(cz);
		   }
		   else code="001";
	return code;
  	}
	  public void  selection(String code){
		 ArrayList<String> list_article=new ArrayList<String>();

		    String query = "SELECT designation,entreprise,software,ingenieur,date_deplacement,description,code_ref,code_gener,code_article"
		    		+ " , code_famille,coeff,indice,model FROM article where code_article='"+code+"' or designation='"+code+"'"; 
		    
		    list_article=CConnect.Requete(query, bdd);
		 //   System.out.println(code+" "+list_article.size()+" "+list_article);
		    if(list_article.size()>=1){
		    designation=(String) list_article.get(0);
		    entreprise=(String) list_article.get(1);
		   
		    software=(String) list_article.get(2);
		    ingenieur=(String) list_article.get(3);
		   
		    date_deplacement=(String) list_article.get(4);
		    description=(String) list_article.get(5);
		    code_ref=(String) list_article.get(6);
		    code_gener=(String) list_article.get(7);
		    code_article=(String) list_article.get(8);
		    code_famille=(String) list_article.get(9);
		   
		    coeff=Integer.parseInt(list_article.get(10));
		    if(list_article.get(11)!=null)
		    indice=Integer.parseInt(list_article.get(11));
		    
		   //if(list_article.get(12)!=null)
		     model=list_article.get(12);
             exist_des=false;

		    String query2 = "SELECT designation FROM reference where code_ref="+code_ref+"";
		    ArrayList<String> list_refer=new ArrayList<String>();
		    list_refer=CConnect.Requete(query2, bdd);
		    if(list_refer.size()>=1){
		    designation_ref=(String) list_refer.get(0);
		    }
		    
		    
		    String query3 = "SELECT designation FROM categ_composant where code_gener='"+code_gener+"'";
		    ArrayList<String> list_gener=new ArrayList<String>();
		    list_gener=CConnect.Requete(query3, bdd);
		    if(list_gener.size()>=1){
		    designation_gener=(String) list_gener.get(0);
		    }
		    
		    String query4 = "SELECT designation FROM famille_article where code_famille='"+code_famille+"'";
		    ArrayList<String> list_famil=new ArrayList<String>();
		    list_famil=CConnect.Requete(query4, bdd);
		    if(list_famil.size()>=1){
		    designation_famille=(String) list_famil.get(0);
		    }
		    
		    String query5 = "SELECT nom_fourniss FROM fournisseur where code_fourniss='"+code_fourniss+"'";
		    ArrayList<String> list_fourniss=new ArrayList<String>();
		    list_fourniss=CConnect.Requete(query5, bdd);
		    if(list_fourniss.size()>=1){
		    nom_fourniss=(String) list_fourniss.get(0);
		    }
		    
		    }
		    else{
		    	exist_des=true;
		    }
		    
	  }
	  
	  public void delete_produit(String code){
			try {	
				  String update2= "delete  from  article_produit where code_article='"+code+"' "; 
		    		CConnect.Insert(update2,bdd);	    	
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  } 
	  
	  public void delete_nomoclature(String code){
			try {	
				  String update2= "delete  from  nomoclature where code_article='"+code+"'"; 
		    		CConnect.Insert(update2,bdd);	    	
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  } 
	  public void update_nomoclature(String codep,String coden,int qte){
			try {	
				  
				  String insert2 = "insert into nomoclature"
		    				+ " (code_article,code_nomo_art,quantite) values('"+codep+"','"+coden+"',"+qte+")"; 


	    	     	CConnect.Insert(insert2,bdd);
	    	
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  }
	  
	  public void ajouter_nomoclature(String codep,String coden,int qte){
			try {	
	    		String insert2 = "insert into nomoclature"
	    				+ " (code_article,code_nomo_art,quantite) values('"+codep+"','"+coden+"',"+qte+")"; 
	    		CConnect.Insert(insert2,bdd);
	    	
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  }
	  
	  public void ajouter_prod_fourniss(String code_art,String codef,String codep,String model,float pu,int qte,String position){
			try {	
	    		String insert2 = "insert into article_produit"
	    				+ " (code_article,code_fournisseur,codep_fourniss,pu,quantite,model,position)"
	    				+ " values('"+code_art+"','"+codef+"','"+codep+"',"+pu+","+qte+",'"+model+"','"+position+"')"; 
	    		CConnect.Insert(insert2,bdd);
	    	
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  }
	  
	  public void setinsert(String designation,String entreprise,String software,String ingenieur,String date_deplacement,
			 String description,String code_ref,String code_gener,String code_article,String code_famille,
			  int coef,int indice,String model ){

		    String query = "SELECT code_article,designation FROM article where code_article='"+code_article+"' or designation='"+designation+"'"; 
		    
		    if(CConnect.Requete(query, bdd).size()>1){
		      JOptionPane.showMessageDialog(null,"Cette article existe déjà.");
		    //  System.out.println("ghghghghg");
		    }
		    
		  
		    else{
		    	designation=designation.replace("'", "''");
		    	code_ref=code_ref.replace("'", "''");
		    	code_famille=code_famille.replace("'", "''");
		    	software=software.replace("'", "''");
		    	ingenieur=ingenieur.replace("'", "''");
		    	description=description.replace("'", "''");
    	    	  String insert = "insert into article (code_article,designation,entreprise,code_ref,code_gener,code_famille,"
    	    	  		+ "software,ingenieur,date_deplacement,description,coeff,indice,model)"
		    	  		+ " values('"+code_article+"','"+designation+"','"+entreprise+"','"+code_ref+"','"+code_gener+"','"+code_famille+"',"
		    	    + "'"+software+"','"+ingenieur+"','"+date_deplacement+"','"+description+"',"+coef+","
		    	    		+ ""+indice+",'"+model+"')"; 
		    	CConnect.Insert(insert,bdd);
		        
		        JOptionPane.showMessageDialog(null,""+designation+" a été bien ajouté");

			     }  
			
			
			} 
	  
	  
	  public void setupdate(String designation,String entreprise,String software,String ingenieur,String date_deplacement,
			  String description,String code_ref,String code_gener,String code_article,String code_famille,
			int coef,int indice,String model){
		  String query = "SELECT designation FROM article where  designation='"+designation+"'"; 
		  
		    if(CConnect.Requete(query, bdd).size()>1){
		      JOptionPane.showMessageDialog(null,"Cette article existe déjà.");
		      
		    }
		    
		  
		    else{
		    	designation=designation.replace("'", "''");
		    	code_ref=code_ref.replace("'", "''");
		    	code_famille=code_famille.replace("'", "''");
		    	software=software.replace("'", "''");
		    	ingenieur=ingenieur.replace("'", "''");
		    	description=description.replace("'", "''");
		    	System.out.println(indice+"hghjgjhg");

		    	//System.out.println(designation);
		    	String update= "update article set designation='"+designation+"'"
		    			+ ",entreprise='"+entreprise+"',code_ref='"+code_ref+"'"
		    			+ " ,code_gener='"+code_gener+"',code_famille='"+code_famille+"',"
		    				+ "software='"+software+"',ingenieur='"+ingenieur+"',date_deplacement='"+date_deplacement+"'"
		    		+ ",description='"+description+"', coeff="+coef+" "
		    				+ " , indice="+indice+",model='"+model+"' "
		    				//	+ ""
		    					+ "where code_article='"+code_article+"'"; 

		    	
		    	CConnect.Insert(update,bdd);	 
		        JOptionPane.showMessageDialog(null,""+designation+" a été bien modifié");
		    }
			} 
		public boolean setdelete(String code_article,String designation){
			
			
			  int reponse = JOptionPane.showConfirmDialog(null,
		              "Voulez-vous vraiment supprimer "+designation+"",
		              "Confirmation",
		              JOptionPane.YES_NO_OPTION,
		              JOptionPane.QUESTION_MESSAGE);
		if (reponse== JOptionPane.YES_OPTION){
			
			 String update= "delete  from  article where code_article='"+code_article+"'"; 
			  CConnect.Insert(update,bdd);
			  
			  String update2= "delete  from  nomoclature where code_article='"+code_article+"'"; 
			  CConnect.Insert(update2,bdd);
		        JOptionPane.showMessageDialog(null,""+designation+" a été bien supprimé");

			  return true;
			//  setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);	
		}

		return false;
		}
		
		public ArrayList<String> select_nomo(String code){
		    ArrayList <String>resultat= new ArrayList<String>() ;
//		   	    String query5 = "SELECT zone.Code_Zone,zone.Designation_Zone,zone.description,Poste.Code_Poste,Poste.Intitule,Zone_Post.Code_Zone,Zone_Post.Code_Poste,Zone_Post.Nbr_Poste,FROM zone,Zone_Post,Poste"
//		   	    		+ "where zone.Code_Zone='"+num+"' and Poste.Code_Poste=Zone_Post.Code_Poste and zone.Code_Zone=Zone_Post.Code_Zone  " ; 
		    
			    String query5 = "SELECT nomoclature.code_nomo_art,article.designation,nomoclature.quantite FROM article,nomoclature "
			    		+ "where  nomoclature.code_article='"+code+"' and article.code_article=nomoclature.code_nomo_art " ;  
		     	resultat=CConnect.Requete(query5,bdd);
		     //   System.out.println(code+"dnjfkldfj "+resultat);  
		        return resultat;        
		}
		
		
		public ArrayList<String> select_produi_art(String code){
		    ArrayList <String>resultat= new ArrayList<String>() ;
//		   	    String query5 = "SELECT zone.Code_Zone,zone.Designation_Zone,zone.description,Poste.Code_Poste,Poste.Intitule,Zone_Post.Code_Zone,Zone_Post.Code_Poste,Zone_Post.Nbr_Poste,FROM zone,Zone_Post,Poste"
//		   	    		+ "where zone.Code_Zone='"+num+"' and Poste.Code_Poste=Zone_Post.Code_Poste and zone.Code_Zone=Zone_Post.Code_Zone  " ; 
		    
			    String query5 = "SELECT code_fournisseur,codep_fourniss,fournisseur.nom_fourniss "
			    		+ ",article_produit.model,position,pu,quantite"
			    		+ " FROM article,article_produit,fournisseur "
			    		+ "where  article_produit.code_article='"+code+"' and article.code_article=article_produit.code_article"
			    				+ " and fournisseur.code_fourniss=article_produit.codep_fourniss " ;  
		     	resultat=CConnect.Requete(query5,bdd);
		     //   System.out.println(code+"dnjfkldfj "+resultat);  
		        return resultat;        
		}
		
		public ArrayList<String> select_model(){
		    ArrayList <String>resultat= new ArrayList<String>() ;
			    String query5 = "SELECT distinct code_produit "
			    		+ " FROM produit ";  
		     	resultat=CConnect.Requete(query5,bdd);
		        System.out.println("dnjfkldfj "+resultat);  
		        return resultat;        
		}
		
		
		public ArrayList<String> select_produit_article(String code){
		    ArrayList <String>resultat= new ArrayList<String>() ;
//		   	    String query5 = "SELECT zone.Code_Zone,zone.Designation_Zone,zone.description,Poste.Code_Poste,Poste.Intitule,Zone_Post.Code_Zone,Zone_Post.Code_Poste,Zone_Post.Nbr_Poste,FROM zone,Zone_Post,Poste"
//		   	    		+ "where zone.Code_Zone='"+num+"' and Poste.Code_Poste=Zone_Post.Code_Poste and zone.Code_Zone=Zone_Post.Code_Zone  " ; 
		    
			    String query5 = "SELECT article.code_article,designation ,code_fournisseur,codep_fourniss,fournisseur.nom_fourniss "
			    		+ ",article_produit.model,position,pu,quantite"
			    		+ " FROM article,article_produit,fournisseur "
			    		+ "where  article_produit.code_article='"+code+"' and article.code_article=article_produit.code_article"
			    				+ " and fournisseur.code_fourniss=article_produit.codep_fourniss " ;  
		     	resultat=CConnect.Requete(query5,bdd);
		       System.out.println(code+"dnjfkldfj "+resultat);  
		        return resultat;  
		        
		}
		public ArrayList<String> select_art_produit(String code){
		    ArrayList <String>resultat= new ArrayList<String>() ;
//		   	    String query5 = "SELECT zone.Code_Zone,zone.Designation_Zone,zone.description,Poste.Code_Poste,Poste.Intitule,Zone_Post.Code_Zone,Zone_Post.Code_Poste,Zone_Post.Nbr_Poste,FROM zone,Zone_Post,Poste"
//		   	    		+ "where zone.Code_Zone='"+num+"' and Poste.Code_Poste=Zone_Post.Code_Poste and zone.Code_Zone=Zone_Post.Code_Zone  " ; 
		    
			    String query5 = "SELECT article.code_article,designation ,code_fournisseur,codep_fourniss,fournisseur.nom_fourniss "
			    		+ ",article_produit.model,position,pu,quantite"
			    		+ " FROM article,article_produit,fournisseur "
			    		+ "where  article_produit.code_fournisseur='"+code+"' and article.code_article=article_produit.code_article"
			    				+ " and fournisseur.code_fourniss=article_produit.codep_fourniss " ;  
		     	resultat=CConnect.Requete(query5,bdd);
		       System.out.println(code+"dnjfkldfj "+resultat);  
		        return resultat;  
		        
		}
		
}
