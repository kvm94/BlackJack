package DAO;

import java.sql.Connection;
import Beans.*;

public class DAOFactory extends AbstractDAOFactory{
	protected static Connection conn = null;
	
	public DAOFactory() throws Exception{
		try{
			if(conn == null){
				ConnectToOracle.getInstance();
			}
		}
		catch(Exception ex){
			throw ex;
		}
	}

	public DAO<Game> getGameDAO(){
		return new GameDAO(conn);
	}
	
	public DAO<Transaction> getTransactionDAO(){
		return new TransactionDAO(conn);
	}
	
	public DAO<Turn> getTurnDAO(){
		return new TurnDAO(conn);
	}
	
	public DAO<User> getUserDAO(){
		return new UserDAO(conn);
	}
	
}
