package logiciel_etiq.Controller;

import java.sql.*;
import java.util.ArrayList;

public class CConnect {
	

	private static Connection con;

	private CConnect(){


        String url = "jdbc:sqlite:C:/GCOBAR/IEsqllite.db";

		  try {
			  Class.forName("org.sqlite.JDBC").newInstance();
			  con = DriverManager.getConnection(url);
		  } 
		  
		  catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
		   e1.printStackTrace(); 
		  }
	}
	public static Connection getInstance(){
		if(con == null){
			new CConnect();
		}
		return con;	
	}	
//*************************************************************Requete Select****************************************************************************//
	public static  ArrayList <String>Requete(String Pquery)  {
		ResultSet results; 
		ArrayList<String> presultat = new ArrayList<>();
	     try {
	         java.sql.Statement stmt = CConnect.getInstance().createStatement();
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

	         encore = results.next();
	         }

	         results.close();
	         }

	         catch(Exception ignored){
		         }
        return presultat ;
	}

//*************************************************************Requete Update ****************************************************************************//
		public static void  Insert(String Pquery)  {

		     try { Statement stmt = CConnect.getInstance().createStatement();

		 stmt.executeUpdate(Pquery); 
       	    stmt.close();
       	}
        catch (SQLException e) { 
       	   
        e.printStackTrace(); 
} 
		     }
		
//***********************************************************************Fermeture de la connection***************************************************************//

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