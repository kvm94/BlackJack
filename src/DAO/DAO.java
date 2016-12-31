package DAO;

import java.sql.Connection;
import java.util.ArrayList;


public abstract class DAO<T> {
	protected Connection connect = null;
	
	public DAO(Connection conn){
		this.connect = conn;
	}
	
	public abstract boolean create(T obj) throws Exception;
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract ArrayList<T> find(Object obj);
	
}

