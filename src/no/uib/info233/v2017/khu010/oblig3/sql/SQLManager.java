package no.uib.info233.v2017.khu010.oblig3.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

import no.uib.info233.v2017.khu010.oblig3.game.MultiPlayerGame;

import no.uib.info233.v2017.khu010.oblig3.players.Player;

import java.sql.Statement;

/**
 * Handles the SQL connection for updating
 * new scores in the game.
 * @author knu010 && xeq003
 * @version 0.3.8 (21.04.2017).
 *
 */
public class SQLManager {
	
    private static Connection con = null;
    private static Statement stmt = null;
    
    private static SQLManager server = new SQLManager();

    //private String url = "jdbc:mysql://wildboy.uib.no/Khuna";
    //private String user = "Khuna";
    //private String password = "\"'mr{6)9m5wHfS3*";
    

    
	public static void main(String[] args) {
		
		try {
			con = connect();
		} catch (Exception e) {
			System.out.println("connection failed");
		}
		server.hostGame(new Player("john"));
			
	}
    
    private SQLManager() { connect(); }
    
    private static Connection connect(){
    	try {
    		String url = "jdbc:mysql://localhost:8889/barinfo";
            String user = "root";
            String password = "root";
        	
            //create connection
            con = DriverManager.getConnection(url, user, password);
            
            //create statement from connection
            stmt = con.createStatement();
            
            //return connection
            return con;
            
    	} catch (SQLException ex){
    		System.out.println("connection failed");
    		return null;
    	}
    	
    }
    
    public boolean hostGame(Player player) { 
    	try {
    		String insertQuery = "INSERT INTO open_games " +
    	"(player_1, player_1_random, player_2, player_2_random) VALUES (?, ?, ?, ?)";
    		PreparedStatement pst = con.prepareStatement(insertQuery);
    		
    		//Set values
    		pst.setString(1, player.getName());
    		pst.setString(2, "random1234");
    		pst.setString(3, null);
    		pst.setString(4, null);
    		
    		//Execute update
    		pst.executeUpdate();
    		
			return true;
			
    	} catch (Exception e){
    		System.out.println("Could not connect because: " + e);
    		
    		return false;
    	}
	}
    
  	public ResultSet read() {

    	try {
    		
    		PreparedStatement pst = con.prepareStatement("select * from open_games");
    		ResultSet rs = pst.executeQuery();
    		

			while (rs.next()) {
				System.out.println(rs.getString(2));
				String player = rs.getString("player");
				String score = rs.getString("score");

				System.out.println("userid : " + player);
				System.out.println("username : " + score);

			}
			return rs;
    	} catch (Exception e){
    		System.out.println("Could not connect because: " + e);
    		return null;
    	}
    	
    	//clear ranking table
    	//stmt.executeUpdate("DELETE FROM ranking");
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
            //pst = con.prepareStatement("INSERT INTO `ranking`(`player`, `score`) VALUES (?, ?)");
        	System.out.println(con);
        	Statement stmt = con.createStatement();
            System.out.println(rs);
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
	
<<<<<<< Updated upstream
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
=======
	public static boolean hostGame(String playerName) {
		return false;
    }
>>>>>>> Stashed changes
	
	
}
