package no.uib.info233.v2017.khu010.oblig4.game;

import no.uib.info233.v2017.khu010.oblig4.players.Player;

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
	 * Sets the current game state.
	 * Used to synchronize the game state from the database.
	 * @param gameState state of the game
	 * @throws IllegalArgumentException whenever an invalid game state is tried to be set.
	 */
	public void setGameState(GameState gameState) throws IllegalArgumentException {
		if (gameState != null) {
			this.gameState = gameState;
		}
		throw new IllegalArgumentException("The GameState cannot be null");
	}
	
	/**
	 * @return current game state
	 */
	public GameState getGameState() {
		return gameState;
	}
}
