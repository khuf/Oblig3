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
 * @author xeq003
 * @version 0.0.1 (14.04.2017)
 *
 */
public class SQLconnector {
	
    Connection con = null;
    PreparedStatement pst = null;

    String url = "jdbc:mysql://localhost:8889/barInfo";
    String user = "root";
    String password = "root";
	
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
            Logger lgr = Logger.getLogger(SQLconnector.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
	}
}
