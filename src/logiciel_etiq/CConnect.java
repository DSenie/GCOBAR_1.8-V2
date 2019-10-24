package logiciel_etiq;

import java.sql.*;
import java.util.ArrayList;

public class CConnect {
	
	//private static CConnect single = new CConnect();

	private static Connection con;
	//private static String bdd;
	private static String Chemin = "c:\\GCOBAR\\";
	 static String bdd = Chemin + Utilitaire.InitBdd() + ".accdb";
	private String password;
	private String user="admin";
	CConnect(){
	System.out.println("fff "+bdd);
	/*	try {
		 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		  } 
	
		catch (ClassNotFoundException e1) { 
		   e1.printStackTrace(); 
		  } */
		   
		  String url = "jdbc:odbc:connect;DBQ="+bdd; 
		   System.out.println("La MaBdd ta3 CConnect eeeeeeeeesssssssssssttttt====  "+url);
		  
		  password="dsenie2015";
		  try { 
		   con = DriverManager.getConnection(url,user,password); 
		  // con.commit();// c'est ici que l'on valide la transaction
		   //con.setAutoCommit(true);

		  } 
		  
		  catch (SQLException e1) { 
		   e1.printStackTrace(); 
		  } 
		  
//System.out.println("la connection a votre base de donnees est etablie");

}
	public static Connection getInstance(String Nbdd){
		bdd=Nbdd;
		if(con == null){
			new CConnect();
		}
		return con;	
	}	
//*************************************************************Requete Select****************************************************************************//
	public static  ArrayList <String>Requete(String Pquery,String Nbdd)  {
		ResultSet results; 
		ArrayList<String> presultat = new ArrayList <String>();
		//System.out.println("La BDD cconnect SELECT "+Nbdd);
	     try { 
	         java.sql.Statement stmt = CConnect.getInstance(Nbdd).createStatement();
	         results = stmt.executeQuery(Pquery);
	         ResultSetMetaData rsmd = results.getMetaData();
	         int nCols = rsmd.getColumnCount();
	         boolean encore = results.next();
	         while(encore)
	         {

	         for(int i = 1; i <= nCols; i++)
	         {

	        	 presultat.add(results.getString(i));
	         }

	         //System.out.print("tien   " +presultat);
	         encore = results.next();
	         }

	         results.close();
	         }

	         catch(Exception e1){
		         //System.out.println("Erreur : " + e1.getMessage());
		         }
        return presultat ;
	}
	//*************************************************************Requete1 Select****************************************************************************//
		public static ArrayList<String> Requete1(String Pquery,String Nbdd )  {
			ResultSet results;
			ArrayList<String> presultat = new ArrayList<String>();
		     try {
		         java.sql.Statement stmt = CConnect.getInstance(Nbdd).createStatement();
		         results = stmt.executeQuery(Pquery); 
		         ResultSetMetaData rsmd = results.getMetaData(); 
		         int nCols = rsmd.getColumnCount(); 
		         boolean encore = results.next(); 
		         String Ligne;
		         
		         while(encore) 
		         {   
		         Ligne ="";
		         for(int i = 1; i <= nCols; i++) 
		         { 
		        	 Ligne=Ligne+results.getString(i)+",";
		         } 
		       
	        	 presultat.add(Ligne); 
		         //System.out.print(presultat);
		         encore = results.next(); 
		         } 
		    
		         results.close(); 
		         }
		         
		         catch(Exception e1){ 
			         //System.out.println("Erreur : " + e1.getMessage()); 
			         }
		     
		   
	        return presultat ;	
		}
//*************************************************************Requete Update ****************************************************************************//
		public static void  Insert(String Pquery,String Nbdd)  {
			//ResultSet results; 
			//ArrayList<String> presultat = new ArrayList<String>();
		     try { Statement stmt = CConnect.getInstance(Nbdd).createStatement(); 
       	  
       	 //int nbMaj = <-------|
		          //           |
		          //           |
		 stmt.executeUpdate(Pquery); 
       	    stmt.close();
       	// System.out.println("mise � jour  " +nbMaj+ "  effectu�e"); 
       	} 
        catch (SQLException e) { 
       	   
        e.printStackTrace(); 
} 
		     }
		
//***********************************************************************Fermeture de la connection***************************************************************//
	   /*public static Connection close(){
		     if(con!=null){
		           try {con.close();} 
		           catch (SQLException e) {// TODO Auto-generated catch block
				                           e.printStackTrace();
			                                }
			       return con;
		}
		return con;
	}*/
		   public static Connection close(){
			     if(con!=null){con=null;
			           /*try {con.close();} 
			           catch (SQLException e) {// TODO Auto-generated catch block
					                           e.printStackTrace();
				                                }*/
				       return con;
			}
			return con;
		}
	
}