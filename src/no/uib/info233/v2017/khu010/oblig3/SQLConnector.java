package no.uib.info233.v2017.khu010.oblig3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the SQL connection for updating
 * new scores in the game.
 * @author knu010 && xeq003
 * @version 0.3.8 (21.04.2017).
 *
 */
public class SQLConnector {
	
	private static SQLConnector server = new SQLConnector();
	
    private Connection con = null;
    private PreparedStatement pst = null;

    private String url = "jdbc:mysql://wildboy.uib.no/Khuna";
    private String user = "Khuna";
    private String password = "\"'mr{6)9m5wHfS3*";
    
    private SQLConnector() {}
    
    public static SQLConnector getConnection() {
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
            Logger lgr = Logger.getLogger(SQLConnector.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
	}
}
