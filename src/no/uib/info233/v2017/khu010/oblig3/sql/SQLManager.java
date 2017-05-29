package no.uib.info233.v2017.khu010.oblig3.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ListModel;
import javax.swing.DefaultListModel;

import java.sql.Statement;

import no.uib.info233.v2017.khu010.oblig3.Utility;
import no.uib.info233.v2017.khu010.oblig3.game.GameState;
import no.uib.info233.v2017.khu010.oblig3.game.MultiPlayerGame;
import no.uib.info233.v2017.khu010.oblig3.interfaces.SQLManagerInterface;
import no.uib.info233.v2017.khu010.oblig3.players.AggressivePlayer;
import no.uib.info233.v2017.khu010.oblig3.players.HumanPlayer;
import no.uib.info233.v2017.khu010.oblig3.players.OnlinePlayer;
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
    private static PreparedStatement pst = null;

    //private String url = "jdbc:mysql://wildboy.uib.no/Khuna";
    //private String user = "Khuna";
    //private String password = "\"'mr{6)9m5wHfS3*";
    
	private static String url = "jdbc:mysql://localhost:3306/233lab";
    private static String user = "root";
    private static String password = "";
    
	@Override
	public boolean createOpenGame(String playerName, String playerId) {
		String sqlString = "INSERT INTO `open_games` (`player_1`, `player_1_random`) VALUES (?, ?)";
		boolean result = false;
		try {
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(sqlString);
			pst.setString(1, playerName);
			pst.setString(2, playerId);
			result = pst.executeUpdate() > 0;
			System.out.println(result);
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return result;
	}
	
	@Override
	public boolean waitForOpponent(String playerId) {
		boolean result = false;
		int numberOfQueries = 0;
		while (numberOfQueries != 5 && result == false) {
		try {
			try {
				ResultSet rs = getOpenGame(playerId);
				
				while (rs.next()) {
					if (rs.getString(3) != null && rs.getString(4) != null) {
						result = true;
						System.out.println("Found opponent");
					}
				}
				System.out.println("Waiting for opponent");
				numberOfQueries++;
				Thread.sleep(5000);
			}
			catch (SQLException ex) {
				System.out.println(ex.toString());
			}
		}
		catch (InterruptedException ex) {
			
		}
		}
		return result;
	}
	
	@Override
	public String createGameInProgress(String playerId) {
		String result = "";
		String sqlString = "INSERT INTO `games_in_progress` (`game_id`, `player_1`, "
				+ "`player_2`, `game_position`, `player_1_energy`, `player_2_energy`, "
				+ "`move_number`) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			ResultSet rs = getOpenGame(playerId);
			boolean entryAdded = false;
			
			while (rs.next()) {
				con = DriverManager.getConnection(url, user, password);
				pst = con.prepareStatement(sqlString);
				pst.setString(1, rs.getString(2) + rs.getString(4));
				pst.setString(2, rs.getString(1));
				pst.setString(3, rs.getString(3));
				pst.setInt(4, 0);
				pst.setInt(5, 100);
				pst.setInt(6, 100);
				pst.setInt(7, 1);
				entryAdded = pst.executeUpdate() > 0;
				if (entryAdded == true) {
					result = rs.getString(2) + rs.getString(4);
				}
			}
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return result;
	}
	
	@Override
	public GameState getGameInProgress(String gameId) {
		GameState result = null;
		String sqlString = "SELECT * FROM `games_in_progress` WHERE `game_id` = ? ORDER BY `move_number` DESC LIMIT 1";
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(sqlString);
			pst.setString(1, gameId);
			System.out.println(gameId);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = new GameState();
				result.setCurrentPosition(rs.getInt(4));
				result.setPlayerA(rs.getString(2), rs.getInt(5));
				result.setPlayerB(rs.getString(3), rs.getInt(6));
				result.setPlayerAMove(rs.getInt(7));
				result.setPlayerBMove(rs.getInt(8));
				result.setRoundNumber(rs.getInt(9));
			}
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return result;
	}
	
	private ResultSet getOpenGame(String playerId) {
		String sqlString = "SELECT * FROM `open_games` WHERE `player_1_random` = ?";
		ResultSet result = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(sqlString);
			pst.setString(1, playerId);
			result = pst.executeQuery();
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return result;
	}

	@Override
	public void removeOpenGame(String playerId) {
		String sqlString = "DELETE FROM `open_games` WHERE `player_1_random` = ?";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(sqlString);
			pst.setString(1, playerId);
			pst.executeUpdate();
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}

	@Override
	public boolean isBothMovesMade(String gameId) {
		String sqlString = "SELECT * FROM `games_in_progress` WHERE `game_id` = ? AND `player_1_move` IS NULL AND `player_2_move` IS NULL ORDER BY move_number DESC LIMIT 1";
		boolean result = false;
		try {
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(sqlString);
			pst.setString(1, gameId);
			if (!pst.executeQuery().isBeforeFirst()) {
				result = true;
			}
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return result;
	}

	@Override
	public void setPlayerAMove(String gameId, int move) {
		String sqlString = "UPDATE `games_in_progress` SET `player_1_move` = ? WHERE `game_id` = ? ORDER BY move_number DESC LIMIT 1";

		try {
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(sqlString);
			pst.setInt(1, move);
			pst.setString(2, gameId);
			pst.executeUpdate();
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}  
	
	@Override
	public void setPlayerBMove(String gameId, int move) {
		String sqlString = "UPDATE `games_in_progress` SET `player_2_move` = ? WHERE `game_id` = ? ORDER BY move_number DESC LIMIT 1";

		try {
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(sqlString);
			pst.setInt(1, move);
			pst.setString(2, gameId);
			pst.executeUpdate();
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}

	@Override
	public DefaultListModel<OnlinePlayer> getOpenGames() {
		DefaultListModel<OnlinePlayer> list = new DefaultListModel<>();
		String sqlString = "SELECT `player_1`, `player_1_random` FROM `open_games` WHERE `player_2` IS NULL";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			
			while (rs.next()) {
				String name = rs.getString(1);
				String playerId = rs.getString(2);
				OnlinePlayer player = new OnlinePlayer(name, playerId, 3);
				list.addElement(player);
			}
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return list;
	}

	@Override
	public String joinOpenGame(String playerName, String enemyPlayerId) {
		String sqlString = "UPDATE `open_games` SET `player_2` = ?, `player_2_random` = ? WHERE `player_1_random` = ?";
		String result = "";
		
		try {
			boolean joinedGame = false;
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(sqlString);
			pst.setString(1, playerName);
			pst.setString(2, Utility.createRandomString(10));
			pst.setString(3, enemyPlayerId);
			joinedGame = pst.executeUpdate() > 0;
			
			if (joinedGame) {
				ResultSet rs = getOpenGame(enemyPlayerId);
				while (rs.next()) {
					result += rs.getString(2);
					result += rs.getString(4);
				}
			}
		}
		catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return result;
	}   
}
