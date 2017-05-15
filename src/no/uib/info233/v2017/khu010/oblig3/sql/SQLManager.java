package no.uib.info233.v2017.khu010.oblig3.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

import no.uib.info233.v2017.khu010.oblig3.game.GameState;
import no.uib.info233.v2017.khu010.oblig3.game.MultiPlayerGame;
import no.uib.info233.v2017.khu010.oblig3.interfaces.SQLManagerInterface;
import no.uib.info233.v2017.khu010.oblig3.players.AggressivePlayer;
import no.uib.info233.v2017.khu010.oblig3.players.HumanPlayer;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

/**
 * Handles the SQL connection for updating
 * new scores in the game.
 * @author knu010 && xeq003
 * @version 0.3.8 (21.04.2017).
 *
 */
public class SQLManager implements SQLManagerInterface{
	
    private static Connection con = null;
    private static Statement stmt = null;

    //private String url = "jdbc:mysql://wildboy.uib.no/Khuna";
    //private String user = "Khuna";
    //private String password = "\"'mr{6)9m5wHfS3*";
    
	private static String url = "jdbc:mysql://localhost:8889/barinfo";
    private static String user = "root";
    private static String password = "root";
    
    private GameState gamestate;
   
    
	public static void main(String[] args) {
		
		SQLManager server = new SQLManager();
		HashMap <String, String> opengames = server.findOpenGames();
		System.out.println(opengames.size());
			
	}
    
    private SQLManager() { connect(); }
    
    private void connect(){
    	try {        	
            //create connection
            this.con = DriverManager.getConnection(url, user, password);
            
            //create statement from connection
            this.stmt = con.createStatement();
            
    	} catch (SQLException ex){
    		System.out.println("connection failed");
    	}
    	
    }

	@Override
	//creates a random id which is 10 characters long
	public String createRandomPlayerID() {
		String randomID = "";
		
		for (int i = 0; i < 10; i++){
			//create a new random
			Random r = new Random();
			//add new character to id from random
			randomID += (char)(r.nextInt(26) + 'a');
		}
		return randomID;
	}

	@Override
	public boolean hostOnlineGame(GameState gamestate) {
		
		Player hostplayer = gamestate.getPlayerA();
		
		try {
			//Create query
    		String insertQuery = "INSERT INTO open_games " +
    				"(player_1, player_1_random, player_2, player_2_random) VALUES (?, ?, 'NULL', 'NULL')";
    		PreparedStatement pst = con.prepareStatement(insertQuery);
    		
    		//set hostplayers random id
    		String hostplayerID = createRandomPlayerID();
    		
    		//Set values
    		pst.setString(1, hostplayer.getName());
    		pst.setString(2, hostplayerID);
    		
    		//Execute update
    		pst.executeUpdate();
    		
			return true;
			
    	} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return false;
		
	}

	@Override
	public String getOpponent() {
		try {
    		String selectOpponentQuery = "SELECT `player_2`, `player_2_random` FROM `open_games` WHERE player_1_random = ? LIMIT 1";
    		//create a statement which gets all open games
    		PreparedStatement pst = con.prepareStatement(selectOpponentQuery);
    		//search for games where you are host
    		pst.setString(1, this.gamestate.getHostID());
    		//execute query and save results to rs
    		ResultSet rs = pst.executeQuery();
    		
    		//return player 2 name or NULL
    		return rs.getString("player_2");

    	} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		
		return "NULL";
	}

	@Override
	public int getOpponentMove() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	//used when this player is hosting a game online
	public void newRound(String gameID) {
		//if (move_number == "NULL") {
		//	game_position = 0;
		//}
		
		//set player_1_move && player_2_move to NULL
		
		//increase move_number by 1
		
	}

	@Override
	public void saveGame(GameState gamestate) {
		try {
        	//create an SQL insert query
        	String insertQuery = "INSERT INTO `ranking`(`player`, `score`) VALUES (?, ?)";
        	//prepare statement
        	
            PreparedStatement pst = con.prepareStatement(insertQuery);
            //set statement values for player a
            pst.setString(1, "playera");
            pst.setFloat(2, 2.0f);
            //execute update for player a
            pst.executeUpdate();
            
            //set statement values for player b
            pst.setString(1, "playerb");
            pst.setFloat(2, -1.0f);
            //execute update for player b
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
		
	}

	@Override
	public void endGame(String gameID) {
		//delete every game_in_progress where game_id = 
		
	}

	@Override
	//returns a hashmap with opponent ids as keys, opponent names as values
	public HashMap<String, String> findOpenGames() {
    	try {
    		
    		//create a statement which gets all open games
    		PreparedStatement pst = con.prepareStatement("select * from open_games");
    		//execute query and save results to rs
    		ResultSet rs = pst.executeQuery();
    		
    		//create a hashmap to save open games
    		HashMap<String, String> results = new HashMap<String, String>();
    		
    		//select next result as long as there is one
			while (rs.next()) {
				//confirms the selected game has an open spot
				if (rs.getString("player_2").equals("NULL")) {
					//add result to result hashmap
					System.out.println(rs.getString("player_1"));
					results.put(rs.getString("player_1_random"), rs.getString("player_1"));
				}
			}
			return results;

    	} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return null;
	}

	@Override
	public String joinOnlineGame(String playername, String opponentID) {
		String playerid = null;
		try {
			String updateQuery = "UPDATE `open_games` WHERE `player_1_random` = ? "
					+ "AND `player_2` IS NULL AND `player_2_random` IS NULL"
					+ "SET `player_2` = ?, `player_2_random` = ?, ";
			
			PreparedStatement pst = con.prepareStatement(updateQuery);
			//player_1_random (host)
			pst.setString(1, opponentID);
			//player_2 name (player)
			pst.setString(2, playername);
			
			//create id for our player
			playerid = createRandomPlayerID();
			//player_2_random (player id)
			pst.setString(3, playerid);

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		//return game id
		return opponentID + playerid;
	}

	@Override
	public void sendMove() {
		// TODO Auto-generated method stub
		
	}	
	
}
