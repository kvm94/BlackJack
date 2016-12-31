package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import Beans.*;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class TransactionDAO extends DAO<Transaction>{

	//[start]Constructor
	
	public TransactionDAO(Connection conn){
		super(conn);
	}

	//[end]Constructor

	//[start]Methods
	
	public boolean create(Transaction obj){		
		boolean check = false;

		try{
			String sql = "{call ADDTRANSACTION(?, ?, ?)}";
			CallableStatement call = connect.prepareCall(sql);

			long date = obj.getDate().toEpochDay();
			call.setLong(1, date);
			call.setInt(2,  obj.getAmount());
			call.setInt(3,  obj.getUser().getId());

			if(call.execute()) 
			    check = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return check;
	}


	public boolean delete(Transaction obj){
		boolean check = false;

		try{
			/**
			 * TODO: ADD stored procedure
			 * don't need
			 */
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	public boolean update(Transaction obj){
		boolean check = false;

		try{
			/**
			 * TODO: ADD stored procedure
			 * don't need
			 */
		
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	public ArrayList<Transaction> find(Object user){
		User u = (User)user;
		ArrayList<Transaction> transacts =  new ArrayList<Transaction>();
		try{
			String sql = "{call FINDTRANSACTIONBYUSER(?, ?)}";
			CallableStatement call = connect.prepareCall(sql, 
					ResultSet.TYPE_FORWARD_ONLY, 
					ResultSet.CONCUR_READ_ONLY);

			call.setInt(1, u.getId());
			call.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR

			call.execute();
			ResultSet result = ((OracleCallableStatement)call).getCursor(2);
			
			while(result.next()){
				Transaction tmp = new Transaction();
				
				tmp.setId(result.getInt("id_transaction"));
				long date = result.getInt("date_transaction");
				tmp.setDate(LocalDate.ofEpochDay(date));
				tmp.setAmount(result.getInt("amount"));
				tmp.setUser(u);
				
				transacts.add(tmp);
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return transacts;
	}
	
	
	//[end]Methods
	
}
