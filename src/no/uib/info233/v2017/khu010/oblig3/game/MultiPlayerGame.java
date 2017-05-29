package no.uib.info233.v2017.khu010.oblig3.game;

import org.apache.commons.lang3.RandomStringUtils;

import no.uib.info233.v2017.khu010.oblig3.Utility;
import no.uib.info233.v2017.khu010.oblig3.players.*;
import no.uib.info233.v2017.khu010.oblig3.sql.SQLManager;

/**
 * A class representing a multi player game.
 * Each assigned player has a random string of characters
 * used to uniquely identify him, associated with him.
 * @author khu010
 * @version 0.0.1 (19.05.2017)
 */
public class MultiPlayerGame extends Game {
	
	//This is the random string that will be generated for both players in an online game.
	private String playerAId;
	private String playerBId;
	//Game id is a concatenation of playerAId and playerBId.
	private String game_id;
	private boolean isHost;
	
	/**
	 * Creates a game with the specified player name and goal.
	 * @param playerName name of the player.
	 * @param goal 3 if the player is hosting. -3 if he has joined a game.
	 */
	public MultiPlayerGame(String playerName, int goal, boolean isHost) {
		super(new HumanPlayer(playerName, goal));
		this.isHost = isHost;
		
		if (isHost) {
			setPlayerAId(Utility.createRandomString(10));
		}
	}
	
	/**
	 * Sets the gameId.
	 * @param id
	 */
	public void setGameID(String id) {
		this.game_id = id;
	}
	
	/**
	 * @return game id.
	 */
	public String getGameID() {
		return this.game_id;
	}
	
	/**
	 * Sets the player id of player A. We need to verify
	 * that the specified string is of length 10.
	 * @param playerId used to identify player A.
	 */
	public void setPlayerAId(String playerId) {
		if (playerId.length() == 10) {
			playerAId = playerId;
		}
	}
	
	/**
	 * Sets the player id of player B. We need to verify
	 * that the specified string is of length 10.
	 * @param playerId used to identify player B.
	 */
	public void setplayerBId(String playerId) {
		if (playerId.length() == 10) {
			playerBId = playerId;
		}
	}
	
	/**
	 * Returns the identification string for player A.
	 * @return
	 */
	public String getPlayerAId() {
		return playerAId;
	}
	
	/**
	 * Returns the identification string for player B.
	 * @return
	 */
	public String getPlayerBId() {
		return playerBId;
	}

	@Override
	public void evaluateTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void performMove(int move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runGame() {
		// TODO Auto-generated method stub
		
	}
}
