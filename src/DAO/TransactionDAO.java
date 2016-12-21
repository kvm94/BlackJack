package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.*;

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
			
			if(!find(obj)){
				/**
				 * TODO: ADD stored procedure
				 */
				/*PreparedStatement statement = connect.prepareStatement(
						"INSERT INTO Accreditation (categorie,sport) VALUES(?,?)");
				statement.setInt(1,obj.getCat().getValue());
				statement.setInt(2,obj.getSport().getValue());

				statement.executeUpdate();
				
				check = true;
				*/
			}
			
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}


	public boolean delete(Transaction obj){
		boolean check = false;

		try{
			/**
			 * TODO: ADD stored procedure
			 */
			/*
			PreparedStatement statement = connect.prepareStatement(
					"DELETE FROM Accreditation WHERE id_accreditation= ?");
			statement.setInt(1,obj.getId());

			statement.executeUpdate();

			check = true;
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
			 */
			/*
			PreparedStatement statement = connect.prepareStatement(
					"UPDATE Accreditation set categorie =? ,sport = ? WHERE id_accreditation = " + obj.getId());
			statement.setInt(1,obj.getCat().getValue());
			statement.setInt(2,obj.getSport().getValue());

			statement.executeUpdate();
			check = true;
			*/
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	public Transaction find(int id){
		Transaction transaction = new Transaction();
		try{
			/**
			 * TODO: ADD stored procedure
			 */
			/*
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Accreditation WHERE id_accreditation = " + id);
			
			while(result.next()){
				accreditation.setId(result.getInt("id_accreditation"));
				
			}	
			*/
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return transaction;
	}
	
	public boolean find(Transaction obj){
		boolean check = false;
		try{
			/**
			 * TODO: ADD stored procedure
			 */
			/*
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Accreditation WHERE categorie = " 
					+ obj.getCat().getValue()
					+ " and sport = " + obj.getSport().getValue());
			
			while(result.next()){
				check = true;
			}	
			*/
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return check;
	}
	
	
	//[end]Methods
	
}
