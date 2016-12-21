package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectToOracle {
    private static Connection snglConnection = null;

    /**
     * Se connecte à la base de données SQLITE du dossier /data.
     * @throws Exception 
     */
    private ConnectToOracle() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");

            snglConnection = DriverManager.getConnection("jdbc:oracle:thin:@char-oracle11.condorcet.be:1521:exa3","exa3","blackj");
        } catch (ClassNotFoundException e) {
            throw new Exception("Impossible de trouver le driver pour la base de donnée!");
        } catch (SQLException e) {
        	throw new Exception("Impossible de se connecter à  la base de donnée.");
        }

        if (snglConnection == null) {
        	throw new Exception("La base de donnée est innaccessible.");
        }
    }

    /**
     * Déstructeur qui ferme la connexion à la base de données.
     */
    public void finalize()
    {
      disconnect();
    }
    
    /**
     * Récupère l'instance de la classe pour le Singleton.
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
     * Se déconnecte de la base de données.
     * @return Tur si la base de données à bien été déconnecté.
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

