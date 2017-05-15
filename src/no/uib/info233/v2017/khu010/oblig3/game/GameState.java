package no.uib.info233.v2017.khu010.oblig3.game;

import java.util.HashMap;

import no.uib.info233.v2017.khu010.oblig3.Utility;
import no.uib.info233.v2017.khu010.oblig3.interfaces.PlayerControllerInterface;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

import java.util.Map;

import javafx.beans.property.SimpleIntegerProperty;

public class GameState {
	
	private Player playerA;
	private Player playerB;
	private SimpleIntegerProperty playerAMove = new SimpleIntegerProperty(0);
	private SimpleIntegerProperty playerBMove = new SimpleIntegerProperty(0);
	private SimpleIntegerProperty currentPosition = new SimpleIntegerProperty(0);
	private Map<Integer, Reward> scores;
	
	public GameState() {
		System.out.println("Yes");
		createScoreboard();
	}
	public GameState(Player firstPlayer, Player secondPlayer) {
		playerA = firstPlayer;
		playerB = secondPlayer;
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
	
	public void setPlayerA(Player playerA) {
		this.playerA = playerA;
		//this.playerA.registerGameMaster(this);
	}
	
	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
		//this.playerB.registerGameMaster(this);
	}
	
	/**
	 * Checks whether the game is in a winning position.
	 * This has been moved from Game to GameState because the thread that is
	 * observing changes in the database, uses this method to determine when the thread
	 * should end.
	 * @return true for a finished game, otherwise it returns false.
	 */
	public boolean isFinnished() {
		return playersHasEnergy() || Math.abs(getCurrentPosition()) == 3;
	}
	
	/**
	 * Checks if either player is out of energy.
	 * @return true/false if either of the players are out of energy.
	 */
	private boolean playersHasEnergy() {
		return playerA.getEnergy() > 0 && playerB.getEnergy() > 0;
	}
	
	/**
	 * Retrieves the reward for both of the players.
	 * @return Reward representing both players reward.
	 */
	public Reward getPlayerRewards() {
		return scores.get(currentPosition);
	}
	
	/**
	 * @return Player A
	 */
	public Player getPlayerA() {
		return playerA;
	}
	
	/**
	 * @return Player B
	 */
	public Player getPlayerB() {
		return playerB;
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
	
	/**
	 * Sets the move of player A and ensures that the specified move is a valid move, i.e. 
	 * player A can afford the move.
	 * @param move
	 */
	private boolean setPlayerAMove(int move) {
		boolean result = false;
		if (Utility.isValidMove(playerA, move)) {
			playerAMove.set(move);
			result = true;
		}
		return result;
	}
	
	/**
	 * Sets the move of player B and ensures that the specified move is a valid move, i.e. 
	 * player B can afford the move.
	 * @param move
	 */
	private boolean setPlayerBMove(int move) {
		boolean result = false;
		if (Utility.isValidMove(playerB, move)) {
			playerBMove.set(move);
			result = true;
		}
		return result;
	}
	
	public boolean requestMoves() {
		
		int moveA = playerA.makeNextMove();
		int moveB = playerB.makeNextMove(currentPosition.get(), playerA.getEnergy());
		
		return setPlayerAMove(moveA) && setPlayerBMove(moveB);
	}
}
