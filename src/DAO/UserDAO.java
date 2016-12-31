package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
				
				String sql = "{call ADDUSER(?,?,?,?,?,?, ?)}"; 
				CallableStatement call = connect.prepareCall(sql); 
				
				call.setString(1, "anonymous");
				call.setString(2,obj.getPassword());
				call.setString(3,  obj.getName());
				call.setString(4,  obj.getFirstName());
				
				long date = obj.getBirthDate().toEpochDay();
				call.setLong(5, date);

				call.setString(6,obj.getMail());
				call.setDouble(7,  obj.getCapital());
				
				if(call.execute()) 
				    check = true;
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
			 * don't need
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
			 * don't need
			 */
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}


	public boolean find(User obj){
		boolean check = false;
		try{
			String sql = "{call FINDUSER(?,?,?)}";
			CallableStatement call = connect.prepareCall(sql, 
					ResultSet.TYPE_FORWARD_ONLY, 
					ResultSet.CONCUR_READ_ONLY);

			call.setString(1,obj.getMail());
			call.setString(2, obj.getPassword());
			call.registerOutParameter(3, OracleTypes.CURSOR); //REF CURSOR

			call.execute();
			ResultSet result = ((OracleCallableStatement)call).getCursor(3);



			//traitement des informations 
			while(result.next()){ 
				check = true;
			} 
			result.close();
			result = null;
			call.close();
			call = null;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return check;
	}

	public ArrayList<User> find (Object obj){
		User u = (User)obj;
		String mail = u.getMail();
		String passwd = u.getPassword();
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
