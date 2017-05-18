package no.uib.info233.v2017.khu010.oblig3.game;

import java.util.HashMap;

import no.uib.info233.v2017.khu010.oblig3.Utility;
import no.uib.info233.v2017.khu010.oblig3.interfaces.PlayerControllerInterface;
import no.uib.info233.v2017.khu010.oblig3.players.HumanPlayer;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import javafx.beans.property.SimpleIntegerProperty;

public class GameState {
	
	private SimpleIntegerProperty playerAMove = new SimpleIntegerProperty(0);
	private SimpleIntegerProperty playerBMove = new SimpleIntegerProperty(0);
	private SimpleIntegerProperty currentPosition = new SimpleIntegerProperty(0);
	private Player playerA;
	private Player playerB;
	private String playerAId;
	private String playerBId;
	private String gameId;
	private int moveNumber;
	private Map<Integer, Reward> scores;
	
	public GameState() {
		System.out.println("Yes");
		createScoreboard();
	}

	public void createScoreboard() {
		scores = new HashMap<>();
		
		scores.put(0, new Reward(0.5F, 0.5F));
		scores.put(1, new Reward(0.75F, 0.25F));
		scores.put(2, new Reward(1.0F, 0.0F));
		scores.put(3, new Reward(2.0F, -1.0F));
		scores.put(-1, new Reward(0.25F, 0.75F));
		scores.put(-2, new Reward(0.0F, 1.0F));
		scores.put(-3, new Reward(-1.0F, 2.0F));
	}
		
	/**
	 * Retrieves the reward for both of the players.
	 * @return Reward representing both players reward.
	 */
	public Reward getPlayerRewards() {
		return scores.get(currentPosition);
	}
	
	/**
	 * @return the latest move from Player A.
	 */
	public int getPlayerAMove() {
		return playerAMove.get();
	}
	
	/**
	 * @return the latest move from Player B
	 */
	public int getPlayerBMove() {
		return playerBMove.get();
	}
	
	public void setCurrentPosition(int currPos) {
		currentPosition.set(currPos);
	}
	
	/**
	 * @return the current round.
	 */
	public int getCurrentPosition() {
		return currentPosition.get();
	}
	
	/**
	 * Retrieves a property that a view can observe for changes.
	 * @return
	 */
	public SimpleIntegerProperty currentPositionProperty() {
		return currentPosition;
	}
	
	/**
	 * Retrieves a property that a view can observe for changes.
	 * @return
	 */
	public SimpleIntegerProperty playerAMoveProperty() {
		return playerAMove;
	}
	
	/**
	 * Retrieves a property that a view can observe for changes.
	 * @return
	 */
	public SimpleIntegerProperty playerBMoveProperty() {
		return playerBMove;
	}
	
	public void setPlayerAMove(int move) {
		playerAMove.set(move);
	}
	
	public void setPlayerBMove(int move) {
		playerBMove.set(move);
	}
	public boolean requestMoves() {
		
		
		return false;
	}
	
	
	public String getGameID() {
		return this.gameId;
	}
	
	public void setMoveNumber(int moveNumber) {
		this.moveNumber = moveNumber;
	}
	
	public int getMoveNumber() {
		return moveNumber;
	}
	
	public void setGameId(String gameId) {
		this.gameId = gameId;
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
	
	public void setPlayerA(Player playerA) {
		this.playerA = playerA;
	}
	
	/**
	 * @return PlayerA
	 */
	public Player getPlayerA() {
		return playerA;
	}
	
	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
	}
	
	public void setPlayerB(String name, int energy) {
		playerB = new HumanPlayer(name, energy, -3);
	}
	
	public void setPlayerA(String name, int energy) {
		playerA = new HumanPlayer(name, energy, 3);
	}
	
	/**
	 * @return PlayerB
	 */
	public Player getPlayerB() {
		return playerB;
	}
}
