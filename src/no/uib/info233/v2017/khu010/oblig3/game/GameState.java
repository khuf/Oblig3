package no.uib.info233.v2017.khu010.oblig3.game;

import java.util.HashMap;

import no.uib.info233.v2017.khu010.oblig3.Utility;
import no.uib.info233.v2017.khu010.oblig3.interfaces.PlayerControllerInterface;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

import java.util.Map;

import javafx.beans.property.SimpleIntegerProperty;

public class GameState implements PlayerControllerInterface {
	
	private Player playerA;
	private Player playerB;
	private SimpleIntegerProperty playerAMove = new SimpleIntegerProperty(-1);
	private SimpleIntegerProperty playerBMove = new SimpleIntegerProperty(-1);
	private SimpleIntegerProperty currentPosition = new SimpleIntegerProperty(0);
	private Map<Integer, Reward> scores;
	
	public GameState() {
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
		this.playerA.registerGameMaster(this);
	}
	
	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
		this.playerB.registerGameMaster(this);
	}
	
	/**
	 * Checks whether the game is in a winning position.
	 * This has been moved from Game to GameState because the thread that is
	 * observing changes in the database, uses this method to determine when the thread
	 * should end.
	 * @return true for a finished game, otherwise it returns false.
	 */
	public boolean isFinnished() {
		return getCurrentPosition() == 3 || getCurrentPosition() == -3;
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
	private void setPlayerAMove(int move) {
		playerAMove.set(0);
		if (Utility.isValidMove(playerA, move)) {
			playerAMove.set(move);
		}
	}
	
	/**
	 * Sets the move of player B and ensures that the specified move is a valid move, i.e. 
	 * player B can afford the move.
	 * @param move
	 */
	private void setPlayerBMove(int move) {
		playerBMove.set(0);
		if (Utility.isValidMove(playerB, move)) {
			playerBMove.set(move);
		}
	}
	
	@Override
	public void listenToPlayerMove(Player player, int move) {
		if (player != null) {
			if (player.equals(playerA)) {
				setPlayerAMove(move);
			}
			else {
				setPlayerBMove(move);
			}
		}
	}
}
