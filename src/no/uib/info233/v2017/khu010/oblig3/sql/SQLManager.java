package no.uib.info233.v2017.khu010.oblig3.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

import no.uib.info233.v2017.khu010.oblig3.game.MultiPlayerGame;

/**
 * Handles the SQL connection for updating
 * new scores in the game.
 * @author knu010 && xeq003
 * @version 0.3.8 (21.04.2017).
 *
 */
public class SQLManager {
	
	private static SQLManager server = new SQLManager();
	
    private Connection con = null;
    private PreparedStatement pst = null;

    private String url = "jdbc:mysql://wildboy.uib.no/Khuna";
    private String user = "Khuna";
    private String password = "\"'mr{6)9m5wHfS3*";
    
    private SQLManager() {}
    
    public static SQLManager getConnection() {
    	return server;
    }
	
    /**
     * Updates the ranking table with the new score from
     * a finished game.
     * @param playerName The player's name.
     * @param playerScore The player's score
     * @return true if the update was successful. Otherwise, false.
     */
	public boolean addScore(String playerName, float playerScore){

        try {
    		con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement("INSERT INTO `ranking`(`player`, `score`) VALUES (?, ?)");
            pst.setString(1, playerName);
            pst.setFloat(2, playerScore);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
	}
	
	/**
	 * Creates an entry for an open game in the database in which other players
	 * can join.
	 * @param game the game that is being added.
	 * @return true if the insertion was successful, false otherwise.
	 */
	public boolean hostGame(MultiPlayerGame game) {
		boolean result = false;
		
		try { 
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("INSERT INTO `open_games` (`player_1`, `player_1_random`) VALUES (?, ?)");
			pst.setString(1, game.getGameState().getPlayerA().getName());
			pst.setString(2, game.getPlayerAId());
			pst.executeUpdate();
			result = true;
		}
		catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return result;
	}
	
	/**
	 * Joins an open multiplayer game against a player if the game
	 * is still available.
	 * @param game
	 * @param enemyPlayer
	 * @return
	 */
	public MultiPlayerGame joinGame(MultiPlayerGame game, String enemyPlayer) {
		MultiPlayerGame result = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("UPDATE `open_games` SET `player_2` = ?, `player_2_random` = ?, WHERE `player_1` = ? AND `player_2` IS NULL AND `player_2_random` IS NULL");
			pst.setString(1, game.getGameState().getPlayerB().getName());
			pst.setString(2, game.getPlayerBId());
			pst.setString(3, enemyPlayer);
			
			if (pst.executeUpdate() == 1) {
				Statement stmt = con.createStatement();
				String query = "SELECT `player_1`, `player_1_random` FROM `open_games` WHERE `player_1` = '" + enemyPlayer + "'";
				ResultSet rs = stmt.executeQuery(query);
				game.setplayerBId(rs.getString(0));
				game.getGameState().getPlayerB().setName(enemyPlayer);
			}

		}
		catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		
		return result;
	}
	
	
}
