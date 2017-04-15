package no.uib.info233.v2017.khu010.oblig3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLconnector {
	
    Connection con = null;
    PreparedStatement pst = null;

    String url = "jdbc:mysql://localhost:8889/barInfo";
    String user = "root";
    String password = "root";
	
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
