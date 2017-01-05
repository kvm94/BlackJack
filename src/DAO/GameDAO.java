package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import Beans.*;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class GameDAO extends DAO<Game>{

	//[start]Constructor
	
	public GameDAO(Connection conn){
		super(conn);
	}

	//[end]Constructor

	//[start]Methods
	
	public boolean create(Game obj){		
		boolean check = false;

		try{
			String sql = "{call ADDGAME(?, ?, ?, ?)}";
			CallableStatement call = connect.prepareCall(sql);

			long date = obj.getDate().toEpochDay();
			call.setLong(1, date);
			call.setInt(2, obj.getNbrTurns());
			call.setInt(3, obj.getResultGame());
			call.setInt(4, obj.getUser().getId());

			if(call.execute()) 
			    check = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return check;
	}


	public boolean delete(Game obj){
		boolean check = false;

		try{
			/**
			 * TODO: ADD stored procedure
			 * Don't need.
			 */
		}
		catch (Exception e){
			e.printStackTrace();  
		}
		return check;
	}

	public boolean update(Game obj){

		boolean check = false;

		try{
			String sql = "{call UPDATEGAME(?, ?, ?, ?, ?)}";
			CallableStatement call = connect.prepareCall(sql);

			long date = obj.getDate().toEpochDay();
			call.setLong(1, date);
			call.setInt(2, obj.getNbrTurns());
			call.setInt(3, obj.getResultGame());
			call.setInt(4, obj.getUser().getId());
			call.setInt(5, obj.getId());

			if(call.execute()) 
			    check = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return check;
	}

	public Game find(int id){
		Game game = new Game();
		try{
			String sql = "{call FINDGAME(?, ?)}";
			CallableStatement call = connect.prepareCall(sql, 
					ResultSet.TYPE_FORWARD_ONLY, 
					ResultSet.CONCUR_READ_ONLY);

			call.setInt(1, id);
			call.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR

			call.execute();
			ResultSet result = ((OracleCallableStatement)call).getCursor(2);
			
			while(result.next()){
				game.setId(result.getInt("id_game"));
				long date = result.getInt("id_game");
				game.setDate(LocalDate.ofEpochDay(date));
				game.setNbrTurns(result.getInt("nbr_turns"));
				game.setResultGame(result.getInt("result_game"));				
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return game;
	}
	
	public ArrayList<Game> find(Object obj){
		User u = (User)obj;
		ArrayList<Game> games = new ArrayList<Game>();
		try{
			String sql = "{call FINDGAMEBYUSER(?, ?)}";
			CallableStatement call = connect.prepareCall(sql, 
					ResultSet.TYPE_FORWARD_ONLY, 
					ResultSet.CONCUR_READ_ONLY);

			call.setInt(1, u.getId());
			call.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR

			call.execute();
			ResultSet result = ((OracleCallableStatement)call).getCursor(2);
			
			while(result.next()){
				Game game = new Game();
				
				game.setId(result.getInt("id_game"));
				long date = result.getInt("date_game");
				game.setDate(LocalDate.ofEpochDay(date));
				game.setNbrTurns(result.getInt("nbr_turns"));
				game.setResultGame(result.getInt("result_game"));
				game.setUser(u);
				
				games.add(game);
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return games;
	}
	
	public int getId(Game game){
		int id= 0;
		try{
			String sql = "{call GETIDGAME(?,?,?,?,?)}";
			CallableStatement call = connect.prepareCall(sql);

			
			
			long date = game.getDate().toEpochDay();
			call.setLong(1, date);
			call.setInt(2, game.getNbrTurns());
			call.setInt(3, game.getResultGame());
			call.setInt(4, game.getUser().getId());
			
			call.registerOutParameter(5, java.sql.Types.INTEGER); 

			call.execute();
			
			id = call.getInt(5); 
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	
	
	//[end]Methods
	
}
