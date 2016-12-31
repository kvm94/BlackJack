package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import Beans.*;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class TurnDAO extends DAO<Turn>{

	//[start]Constructor
	
	public TurnDAO(Connection conn){
		super(conn);
	}

	//[end]Constructor

	//[start]Methods
	
	public boolean create(Turn obj){		
		boolean check = false;

		try{
			String sql = "{call ADDTURN(?, ?, ?, ?, ?)}";
			CallableStatement call = connect.prepareCall(sql);

			call.setInt(1, obj.isWin());
			call.setInt(2, obj.getCroupierScore());
			call.setInt(3, obj.getUserScore());
			call.setDouble(4, obj.getBet());
			call.setInt(5,  obj.getIdGame());

			if(call.execute()) 
			    check = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return check;
	}


	public boolean delete(Turn obj){
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

	public boolean update(Turn obj){
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

	public ArrayList<Turn> find(Object game){
		ArrayList<Turn> turns = new ArrayList<Turn>();
		Game g = (Game)game;
		try{
			String sql = "{call FINDTURNBYGAME(?, ?)}";
			CallableStatement call = connect.prepareCall(sql, 
					ResultSet.TYPE_FORWARD_ONLY, 
					ResultSet.CONCUR_READ_ONLY);

			call.setInt(1, g.getId());
			call.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR

			call.execute();
			ResultSet result = ((OracleCallableStatement)call).getCursor(2);
			
			while(result.next()){
				Turn tmp = new Turn();
				
				tmp.setId(result.getInt("id_turn"));
				tmp.setWin(result.getInt("win"));
				tmp.setCroupierScore(result.getInt("croupier_score"));
				tmp.setUserScore(result.getInt("user_score"));
				tmp.setBet(result.getDouble("bet"));
				tmp.setIdGame(g.getId());
				
				turns.add(tmp);
			}	
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return turns;
	}
	
	
	
	//[end]Methods
	
}
