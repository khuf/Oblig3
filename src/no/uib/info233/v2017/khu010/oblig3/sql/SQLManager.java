package no.uib.info233.v2017.khu010.oblig3.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

import no.uib.info233.v2017.khu010.oblig3.game.GameState;
import no.uib.info233.v2017.khu010.oblig3.game.MultiPlayerGame;
import no.uib.info233.v2017.khu010.oblig3.interfaces.PlayerControllerInterface;
import no.uib.info233.v2017.khu010.oblig3.interfaces.SQLManagerInterface;
import no.uib.info233.v2017.khu010.oblig3.players.AggressivePlayer;
import no.uib.info233.v2017.khu010.oblig3.players.HumanPlayer;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

/**
 * Handles the SQL connection
 * Syncs database with game and vice versa
 * @author knu010 && xeq003
 * @version 0.3.8 (21.04.2017).
 *
 */
public class SQLManager implements SQLManagerInterface, PlayerControllerInterface {
	
    private static Connection con = null;
    private Statement stmt;

    //private String url = "jdbc:mysql://wildboy.uib.no/Khuna";
    //private String user = "Khuna";
    //private String password = "\"'mr{6)9m5wHfS3*";
    
	private static String url = "jdbc:mysql://localhost:8889/barinfo";
    private static String user = "root";
    private static String password = "root";
    
    private static MultiPlayerGame mpgame;
    private static boolean hosting;
    
	public static void main(String[] args) {
		
		mpgame = new MultiPlayerGame("THismeTHo4", -3);
		
		SQLManager server = new SQLManager();
		server.hostOnlineGame(mpgame);
		HashMap <String, String> opengames = server.findOpenGames();
		String joinid = opengames.get("THismeTHo4");
		server.joinOnlineGame("nigguh", joinid);
		server.getOpponent();
		server.startGame();
		GameState gstat = server.getGameState(mpgame.getGameID());
	}
    
    private SQLManager(MultiPlayerGame mpgame) { 
    	this.mpgame = mpgame;
    	if (mpgame.getGameState().getPlayerA() == null){
    		this.hosting = false;
    	} else {
    		this.hosting = true;
    	}
    	connect(); 
    }
    
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

	//only use if you are joining a game!
	public GameState getGameState() {
		//create new gamestate
		GameState gamestate = null;
		String gameID = mpgame.getGameID();
		try {
			
    		String selectGameInProgress = "SELECT * FROM `games_in_progress` WHERE `game_id` = ? LIMIT 1";
    		//create a statement which gets all open games
    		PreparedStatement pst = con.prepareStatement(selectGameInProgress);

    		//search for games where you are host
    		pst.setString(1, gameID);
    		//execute query and save results to rs

    		ResultSet rs = pst.executeQuery();

    		if (rs.next()){
    			//save results from sql server
    			String player1Name = rs.getString("player_1");
    			String player2Name = rs.getString("player_2");
    			int gamePosition = rs.getInt("game_position")
    			int player1energy = rs.getInt("player_1_energy");
    			int player2energy = rs.getInt("player_2_energy");
    			int player1move = rs.getInt("player_1_move");
    			int player2move = rs.getInt("player_2_move");
    			int moveNumber = rs.getInt("move_number");
    			
    			//update local gamestate with data from sqlserver
    			gamestate = new GameState();
    			//create new players with same names
    			//host always tries to go to spot number 3
    			gamestate.setPlayerA(new HumanPlayer(player1Name, 3));
    			//joining player always tries to go to spot -3
    			gamestate.setPlayerB(new HumanPlayer(player2Name, -3));
    			//set new energy for player a
    			gamestate.getPlayerA().useEnergy(100 - player1energy);
    			//set updated energy for player b
    			gamestate.getPlayerB().useEnergy(100 - player2energy);
    			//update playermoves
    			gamestate.setPlayerAMove(player1move);
    			gamestate.setPlayerBMove(player2move);
    			//update moves made
    			gamestate.SetMovesMade(moveNumber);
    			//update game position
    			gamestate.currentPositionProperty().setValue(gamePosition);
    			
    		} else {
    			System.out.println("could not find game with this id");
    		}
    	} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return gamestate;
	}
	
	//method for hosting online game
	public boolean hostOnlineGame() {
		
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
	public boolean startGame() {

		GameState gamestate = this.mpgame.getGameState();
		
		String hostID = this.mpgame.getPlayerAId();
		String gameID = hostID + this.mpgame.getPlayerBId();
		
		String deleteQuery = "DELETE FROM `open_games` WHERE player_1_random = ?";
		
		String insertQuery = "INSERT INTO games_in_progress "
				+ "(game_id, player_1, player_2, game_position, player_1_energy, player_2_energy, player_1_move, player_2_move, move_number) "
				+ "VALUES (?, ?, ?, 0, 100, 100, 0, 0, 0)";
		try {
			//delete game from open_games
			PreparedStatement pst = con.prepareStatement(deleteQuery);
			pst.setString(1, hostID);
			pst.executeUpdate();
			
			//create game in games_in_progress
			pst = con.prepareStatement(insertQuery);
			pst.setString(1, gameID);
			pst.setString(2, gamestate.getPlayerA().getName());
			pst.setString(3, gamestate.getPlayerB().getName());
			//SEVERE: Column 'player_1_move' cannot be null
			pst.executeUpdate();
			
			//save game id to mpgame
			this.mpgame.setGameID(gameID);
			
			System.out.println("Game #" + gameID + " has started");
			return true;
			
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return false;
	}
	
	//run every 2 seconds in another thread
	//checks if your hosted game in open_games has an opponent
	//returns opponents name, else returns null
	public String getOpponent() {
		System.out.println("Checking if opponent has joined");
		try {
    		String selectOpponentQuery = "SELECT `player_2`, `player_2_random` FROM `open_games` WHERE player_1_random = ? LIMIT 1";
    		//create a statement which gets all open games
    		PreparedStatement pst = con.prepareStatement(selectOpponentQuery);

    		//search for games where you are host
    		pst.setString(1, this.mpgame.getPlayerAId());
    		//execute query and save results to rs
    		ResultSet rs = pst.executeQuery();

    		if (rs.next()){
    			String opponentName = rs.getString("player_2");
        		String opponentID = rs.getString("player_2_random");

        		if (opponentName.equals("NULL")){
        			System.out.println("No opponent found yet");
        			return null;
        		}
    			//updates mpgame
    			this.mpgame.setplayerBId(opponentID);
    			this.mpgame.getGameState().setPlayerB(new HumanPlayer(opponentName, 3));
    			
    			System.out.println("Opponent " + opponentName + " has joined");
    			//returns opponents name
    			return opponentName;
    		} else {
    			System.out.println("No games found where you are host");
    		}
    	} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return null;
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
    			if (this.hosting){
    				this.mpgame.getGameState().setPlayerBMove(opponentMove);
    			} else {
    				this.mpgame.getGameState().setPlayerAMove(opponentMove);
    			}
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
	public void saveGame() {
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
	public void endGame() {
		//delete every game_in_progress where game_id = 
		
	}

	@Override
	//returns a hashmap with opponent names as keys, opponent id´s as values
	public HashMap<String, String> findOpenGames() {
    	try {
    		
    		//create a statement which gets all open games
    		PreparedStatement pst = con.prepareStatement("SELECT * FROM open_games WHERE player_2 = 'NULL'");
    		//execute query and save results to rs
    		ResultSet rs = pst.executeQuery();
    		
    		//create a hashmap to save open games
    		HashMap<String, String> results = new HashMap<String, String>();
    		
    		//select next result as long as there is one
			while (rs.next()) {
				//confirms the selected game has an open spot
				if (rs.getString("player_2").equals("NULL")) {
					//add result to result hashmap
					results.put(rs.getString("player_1"), rs.getString("player_1_random"));
				}
			}
			return results;

    	} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return null;
	}

	
	//returns game id
	public String joinOnlineGame(String playername, String opponentID) {
		//create id for our player
		String playerid = createRandomPlayerID();
		try {
			String updateQuery = "UPDATE `open_games` "
					+ "SET `player_2` = ?, `player_2_random` = ? "
					+ "WHERE `player_1_random` = ? "
					+ "AND `player_2` = 'NULL' "
					+ "AND `player_2_random` = 'NULL'";
			
			PreparedStatement pst = con.prepareStatement(updateQuery);
			//player_2 name (player)
			pst.setString(1, playername);
			//player_2_random (player id)
			pst.setString(2, playerid);
			//player_1_random (host)
			pst.setString(3, opponentID);
			
			pst.executeUpdate();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		//return game id
		return opponentID + playerid;
	}

	@Override
	public void endGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMove(int move, String gameId) {
		String sqlString = "UPDATE `games_in_progress` SET ? = ? WHERE `game_id` = ? ORDER BY move_number DESC LIMIT 1";
		String playerToSet = "";
		if (hosting) {
			playerToSet = "player_1_move";
		}
		else {
			playerToSet = "player_2_move";
		}
		try {
			con = DriverManager.getConnection(url, user, password);
			PreparedStatement pst = con.prepareStatement(sqlString);
			pst.setString(1, playerToSet);
			pst.setInt(2, move);
			pst.setString(3, gameId);
			pst.executeUpdate();
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
}
