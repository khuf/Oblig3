package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.Utility;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

/**
 * This is the base class for a game.
 * @author khu010
 * @version 0.0.2 (16.05.2017)
 *
 */
abstract public class Game {
	
	private GameState gameState;
	
	/**
	 * Creates a game state.
	 */
	public Game() {
		gameState = new GameState();
	}
	
	/**
	 * Creates a game with a specified game state
	 * @param gameState representing the current state of a game
	 */
	public Game(GameState gameState) {
		setGameState(gameState);
	}
	
	/**
	 * Creates a game with the specified player as the first player.
	 * @param playerA
	 */
	public Game(Player playerA) {
		gameState = new GameState();
		gameState.setPlayerA(playerA);
	}
	
	/**
	 * The user uses this method to perform his move.
	 * @param move energy spent
	 */
	abstract public void performMove(int move);
	
	/**
	 * Evaluates the current round. This method should be called when
	 * both players have submitted their move.
	 */
	abstract public void evaluateTurn();
	
	/**
	 * @return current game state
	 */
	public GameState getGameState() {
		return gameState;
	}
	
	/**
	 * Checks whether the game is in a winning position.
	 * This has been moved from Game to GameState because the thread that is
	 * observing changes in the database, uses this method to determine when the thread
	 * should end.
	 * @return true for a finished game, otherwise it returns false.
	 */
	public boolean isFinnished() {
		return !playersHasEnergy() || Math.abs(gameState.getCurrentPosition()) == 3;
	}
	
	/**
	 * Checks if either player is out of energy.
	 * @return true/false if either of the players are out of energy.
	 */
	private boolean playersHasEnergy() {
		return gameState.getPlayerA().getEnergy() > 0 && gameState.getPlayerB().getEnergy() > 0;
	}
	
	abstract public void runGame();
	
	
	public void setGameState(GameState gameState) throws IllegalArgumentException {
		if (gameState != null) {
			this.gameState = gameState;
		}
		else {
			throw new IllegalArgumentException("The GameState cannot be null");
		}
	}
}
