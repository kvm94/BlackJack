package DAO;

import Beans.*;

public abstract class AbstractDAOFactory {
	public static final int DAO_FACTORY = 0;
	public static final int XML_DAO_FACTORY = 1;
	
	public abstract DAO<User> 						getUserDAO();
	public abstract DAO<Game> 						getGameDAO();
	public abstract DAO<Turn> 						getTurnDAO();
	public abstract DAO<Transaction>				getTransactionDAO();
	
	public static AbstractDAOFactory getFactory(int type) throws Exception{
		switch(type){
		case DAO_FACTORY:
			return new DAOFactory();
			default:
				return null;
		}
	}
}