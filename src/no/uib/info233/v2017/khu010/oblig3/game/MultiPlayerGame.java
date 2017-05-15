package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.players.*;

public class MultiPlayerGame extends Game {
	
	//This is the random string that will be generated for both players in an online game.
	private String playerAId;
	private String playerBId;
	private String game_id;
	//This is the class that listens for changes to the game in another thread every
	//2 seconds.
	//The thread should be started in the runGame metod below...
	private DBListener listener;
	
	public MultiPlayerGame(String playerName, int goal) {
		//creates a new Game, sets new player as player A
		super(new HumanPlayer(playerName, goal));
	}
	
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
	public void runGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean performMoves() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void evaluateTurn() {
		// TODO Auto-generated method stub
		
	}
}
