package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectToOracle {
    private static Connection snglConnection = null;

    /**
     * Se connecte � la base de donn�es ORACLE.
     * @throws Exception 
     */
    private ConnectToOracle() throws Exception {
        try {
        	
        	Class.forName("oracle.jdbc.driver.OracleDriver");

            snglConnection = DriverManager.getConnection("jdbc:oracle:thin:@char-oracle11.condorcet.be:1521:xe","exa3","blackj");
        }
        catch(NoClassDefFoundError e){
        	throw new Exception("Impossible de trouver le driver pour la base de donn�e!");

        } catch (SQLException e) {
        	//throw new Exception("Impossible de se connecter �  la base de donn�e.");
        	System.out.println(e);
        }

        if (snglConnection == null) {
        	throw new Exception("La base de donn�e est innaccessible.");
        }
    }

    /**
     * D�structeur qui ferme la connexion � la base de donn�es.
     */
    public void finalize()
    {
      disconnect();
    }
    
    /**
     * R�cup�re l'instance de la classe pour le Singleton.
     * @return La connexion.
     * @throws Exception 
     */
    public static Connection getInstance() throws Exception {
        if (snglConnection == null) {
        	try
        	{
                new ConnectToOracle();
        	}
        	catch(Exception ex){
        		throw ex;
        	}
        }

        return snglConnection;
    }
    
    /**
     * Se d�connecte de la base de donn�es.
     * @return Tur si la base de donn�es � bien �t� d�connect�.
     */
    static public boolean disconnect()

    {
      try
      {
        if (snglConnection != null) {
          snglConnection.close();
        }
        System.out.println("Disconnection done!");
        return true;
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      return false;
    }
}

