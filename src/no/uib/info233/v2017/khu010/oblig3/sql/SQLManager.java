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
    
    private static MultiPlayerGame mpgame;
   
    
	public static void main(String[] args) {
		
		mpgame = new MultiPlayerGame("THismeTHo", -3);

		SQLManager server = new SQLManager();
		//HashMap <String, String> opengames = server.findOpenGames();
		
		//server.hostOnlineGame(new MultiPlayerGame("THismeTHo", -3));
		System.out.println(server.getOpponent());
			
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
	public boolean hostOnlineGame(MultiPlayerGame mpgame) {
		//save mpgame locally
		this.mpgame = mpgame;
		
		try {
			//Create query
    		String insertQuery = "INSERT INTO open_games " +
    				"(player_1, player_1_random, player_2, player_2_random) VALUES (?, ?, 'NULL', 'NULL')";
    		PreparedStatement pst = con.prepareStatement(insertQuery);
    		
    		//player_1
    		pst.setString(1, mpgame.getGameState().getPlayerA().getName());
    		//player_1_random
    		String player1ID = createRandomPlayerID();
    		pst.setString(2, player1ID);
    		
    		//Execute update
    		pst.executeUpdate();
    		
    		//update mpgame
    		this.mpgame.setPlayerAId(player1ID);
    		
			return true;
			
    	} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return false;
	}
	
	//uses a gamestate to create a new game
	//moves game from open_games to games_in_progress
	//returns game_id
	public String startGame() {

		String gameID = this.mpgame.getPlayerAId() + this.mpgame.getPlayerBId();
		
		
		//delete * from open_games where player_1_random = 
		
		return "s";
	}

	@Override
	public String getOpponent() {
		try {
    		String selectOpponentQuery = "SELECT `player_2`, `player_2_random` FROM `open_games` WHERE player_1_random = ? LIMIT 1";
    		//create a statement which gets all open games
    		PreparedStatement pst = con.prepareStatement(selectOpponentQuery);
    		//search for games where you are host
    		pst.setString(1, this.mpgame.getPlayerAId());
    		//execute query and save results to rs
    		ResultSet rs = pst.executeQuery();
    		
    		String opponentName = rs.getString("player_2");
    		String opponentID = rs.getString("player_2_random");
    		if (opponentID != "NULL")
    		{
    			//updates mpgame
    			this.mpgame.setplayerBId(opponentID);
    			this.mpgame.getGameState().setPlayerB(new HumanPlayer(opponentName, 3));
    			//returns opponents name
    			return opponentName;
    		} else {
    			System.out.println("No opponent found yet");
    		}

    	} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return "NULL";
	}

	@Override
	public boolean hasOpponentMove() {
		try {
    		String selectOpponentQuery = "SELECT `player_2_move` FROM `games_in_progress` WHERE `game_id` = ? LIMIT 1";
    		//create a statement which gets all open games
    		PreparedStatement pst = con.prepareStatement(selectOpponentQuery);
    		//search for games where you are host
    		pst.setString(1, this.mpgame.getGameID());
    		//execute query and save results to rs
    		ResultSet rs = pst.executeQuery();
    		
    		//save opponents move locally
    		int opponentMove = rs.getInt("player_2_move");
    		//return true if its not 0
    		if (opponentMove != 0) {
    			return true;
    		}

    	} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return false;
	}

	@Override
	//used when this player is hosting a game online
	public void newRound() {
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
