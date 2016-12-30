package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import Beans.*;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class UserDAO extends DAO<User>{

	//[start]Constructor

	public UserDAO(Connection conn){
		super(conn);
	}

	//[end]Constructor

	//[start]Methods

	public boolean create(User obj){		
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


	public boolean delete(User obj){
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

	public boolean update(User obj){
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

	public User find(int id){
		User user = new User();
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
		return user;
	}

	public boolean find(User obj){
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

	public ArrayList<User> find (String mail, String passwd){
		ArrayList<User> users = new ArrayList<User>();
		try{

			String sql = "{call FINDUSER(?,?,?)}";
			CallableStatement call = connect.prepareCall(sql, 
					ResultSet.TYPE_FORWARD_ONLY, 
					ResultSet.CONCUR_READ_ONLY);

			call.setString(1,mail);
			call.setString(2, passwd);
			call.registerOutParameter(3, OracleTypes.CURSOR); //REF CURSOR

			call.execute();
			ResultSet result = ((OracleCallableStatement)call).getCursor(3);



			//traitement des informations 
			while(result.next()){ 
				User user = new User();

				int date = result.getInt("BIRTH_DATE");

				user.setBirthDate(LocalDate.ofEpochDay(date));
				user.setCapital(result.getInt("CAPITAL"));
				user.setFirstName(result.getString("FIRST_NAME"));
				user.setId(result.getInt("ID_USER"));
				user.setName(result.getString("NAME"));
				user.setMail(result.getString("MAIL"));

				users.add(user);
			} 
			result.close();
			result = null;
			call.close();
			call = null;


		}
		catch(Exception e){
			e.printStackTrace();
		}
		return users;
	}


	//[end]Methods

}
