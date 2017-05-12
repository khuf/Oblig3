package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.players.*;

public class MultiPlayerGame extends Game {
	
	private String playerAId;
	private String playerBId;
	private String game_id;
	
	public MultiPlayerGame(String playerName, int goal) {
		super(new HumanPlayer(playerName, goal));
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
}
