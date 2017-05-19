package no.uib.info233.v2017.khu010.oblig4.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import no.uib.info233.v2017.khu010.oblig4.game.DBListener;
import no.uib.info233.v2017.khu010.oblig4.game.GameState;
import no.uib.info233.v2017.khu010.oblig4.game.MultiPlayerGame;
import no.uib.info233.v2017.khu010.oblig4.game.Reward;
import no.uib.info233.v2017.khu010.oblig4.interfaces.PlayerControllerInterface;
import no.uib.info233.v2017.khu010.oblig4.interfaces.SQLManagerInterface;
import no.uib.info233.v2017.khu010.oblig4.players.HumanPlayer;

import java.sql.Statement;

/**
 * Handles the SQL connection
 * Synchronizes database with game and vice versa
 * @author knu010 og xeq003
 * @version 0.3.8 (21.04.2017).
 *
 */
public class SQLManager implements SQLManagerInterface, PlayerControllerInterface {
	
    private Connection con = null;
    private Statement stmt;

    //private String url = "jdbc:mysql://wildboy.uib.no/Khuna";
    //private String user = "Khuna";
    //private String password = "\"'mr{6)9m5wHfS3*";
    
	private static String url = "jdbc:mysql://localhost:8889/barinfo";
    private static String user = "root";
    private static String password = "root";
    
    private static MultiPlayerGame mpgame;
    private boolean hosting;
    
	public static void main(String[] args) {
		//create a new multiplayergame with a testplater
		mpgame = new MultiPlayerGame("testplayer", -3);
		//create a sqlmanager for this multiplayergame
		SQLManager server = new SQLManager(mpgame);
		//host this game online
		server.hostOnlineGame();
		
		//search for games online
		Map<String, String> opengames = server.findOpenGames();
		//save an opponets id our testopponent wants to join
		String joinid = opengames.get("testplayer");
		//join game selected id as player named testopponent
		String gameid = server.joinOnlineGame("testopponent", joinid);
		
		server.newRound();
		server.newRound();
		
		server.endGame();
	}

    public SQLManager(MultiPlayerGame mpgame) { 
    	this.mpgame = mpgame;
    	if (mpgame.getGameState().getPlayerA() == null){
    		this.hosting = false;
    	} else {
    		this.hosting = true;
    	}
    	connect(); 
    }
    
    /**
     * Establishes a connection to the database.
     * @throws SQLException if the connection fails.
     */
    private void connect(){
    	try {        	
            //create connection
            this.con = DriverManager.getConnection(url, user, password);
            
            //create statement from connection
            this.stmt = con.createStatement();
            
    	} catch (SQLException ex){
    		System.out.println("Failed to connect to the database, " + ex.toString());
    	}
    	
    }
    
	/**
	 * Creates a 'pseudo' random id for the player...
	 * @return a random string
	 */
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

	/**
	 * Gets the game state of a game that the user is joining.
	 * Only searches for games_in_progress
	 * @return game state of a joined game
	 */
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
    			int gamePosition = rs.getInt("game_position");
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
    			gamestate.setMovesMade(moveNumber);
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
    		
    		//creates a new dblistener where we are host
    		DBListener listener = new DBListener(this, this.mpgame, true);
    		
			return true;
			
    	} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return false;
	}
	
	/**
	 * Starts a game of multiplayer by creating an entry to the games in progress table
	 * and deleting the pending game request from open games.
	 * @return true if the game starts successfully, false otherwise.
	 */
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
	
	/**
	 * Gets the name of the opponent in an open game, i.e. a game waiting
	 * for another player to join (open_games).
	 * @return name of the opponent or null if opponent player doesn't exist.
	 */
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

	//used when this player is hosting a game online
	//increases round number and resets player moves
	public void newRound() {
		
		try {
			String updateQuery = "UPDATE `games_in_progress` "
					+ "SET `player_1_move` = 0, `player_2_move` = 0, `move_number` = ? "
					+ "WHERE `game_id` = ? LIMIT 1";
			
			PreparedStatement pst = con.prepareStatement(updateQuery);
			//increase moves made
			int movesmade = this.mpgame.getGameState().getMovesMade() + 1;
			pst.setInt(1, movesmade);
			//set game id
			pst.setString(2, this.mpgame.getGameID());

			pst.executeUpdate();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	/**
	 * Ends the game by removing all entries in games in progress with the current 
	 * game's game id.
	 */
	@Override
	public void endGame() {
		GameState gamestate = this.mpgame.getGameState();
		String gameID = this.mpgame.getGameID();
		String deleteQuery = "DELETE FROM `games_in_progress` WHERE `game_id` = ?";
    	String insertQueryRanking = "INSERT INTO `ranking`(`player`, `score`) VALUES (?, ?)";
    	String insertQuerySaved = "INSERT INTO `saved_games`(game_id, player_1, player_2, game_position, "
    			+ "player_1_energy, player_2_energy) VALUES (?, ?, ?, ?, ?, ?)";
    	Reward rewards = gamestate.getPlayerRewards();
		
		try {
			//delete game from games_in_progress
			PreparedStatement pst = con.prepareStatement(deleteQuery);
			pst.setString(1, gameID);
			pst.executeUpdate();
			
        	//save rewards to ranking table
            pst = con.prepareStatement(insertQueryRanking);
            //set statement values for player a
            pst.setString(1, gamestate.getPlayerA().getName());
            pst.setFloat(2, rewards.getPlayerAScore());
            //execute update for player a
            pst.executeUpdate();
            
            //set statement values for player b
            pst.setString(1, gamestate.getPlayerB().getName());
            pst.setFloat(2, rewards.getPlayerBScore());
            //execute update for player b
            pst.executeUpdate();
            
            //save game to saved_games
            pst = con.prepareStatement(insertQuerySaved);
			pst.setString(1, gameID);
			pst.setString(2, gamestate.getPlayerA().getName());
			pst.setString(3, gamestate.getPlayerB().getName());
			pst.setInt(4, gamestate.getCurrentPosition());
			pst.setInt(5, gamestate.getPlayerA().getEnergy());
			pst.setInt(6, gamestate.getPlayerB().getEnergy());
			pst.executeUpdate();
			
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	/**
	 * Returns a HashMap of all open games with player id as key and player name as value.
	 * @return 
	 */
	@Override
	public Map<String, String> findOpenGames() {
    	try {
    		
    		//create a statement which gets all open games
    		PreparedStatement pst = con.prepareStatement("SELECT * FROM open_games WHERE player_2 = 'NULL'");
    		//execute query and save results to rs
    		ResultSet rs = pst.executeQuery();
    		
    		//create a hashmap to save open games
    		Map<String, String> results = new HashMap<String, String>();
    		
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

	/**
	 * Updates a game in progress with a new player move.
	 * @param move the players move
	 */
	public boolean sendMove(int move) {
		String sqlString = "UPDATE `games_in_progress` SET ? = ? WHERE `game_id` = ? ORDER BY move_number DESC LIMIT 1";
		String playerToSet;
		if (hosting) {
			playerToSet = "player_1_move";
		} else {
			playerToSet = "player_2_move";
		}
		
		try {
			PreparedStatement pst = con.prepareStatement(sqlString);
			pst.setString(1, playerToSet);
			pst.setInt(2, move);
			pst.setString(3, mpgame.getGameID());
			pst.executeUpdate();
			return true;
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return false;
	}
}
