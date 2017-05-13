package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.players.Player;

/**
 * This is the base class for a game. The class can not be instanciated
 * and is extended by both SingelPlayerGame and MultiPlayerGame.
 * It implements a couple of methods that are common for both singel player
 * and multiplayer.
 * @author knuhuf
 *
 */
abstract public class Game {
	
	private GameState gameState;
	
	/**
	 * Creates a fresh game with no players set.
	 */
	public Game() {
		gameState = new GameState();
	}
	
	public Game(GameState gameState) {
		setGameState(gameState);
	}
	
	public Game(Player playerA) {
		gameState = new GameState();
		gameState.setPlayerA(playerA);
	}
	
	abstract public void runGame();
	
	abstract public boolean performMoves();
	
	abstract public void evaluateTurn();
	
	public void setGameState(GameState gameState) throws IllegalArgumentException {
		if (gameState != null) {
			this.gameState = gameState;
		}
		throw new IllegalArgumentException("The GameState cannot be null");
	}
	
	public boolean setPlayers(Player playerA, Player playerB) {
		boolean result = false;
		
		if (playerA != null && playerB != null) {
			gameState.setPlayerA(playerA);
			gameState.setPlayerB(playerB);
			result = true;
		}
		return result;
	}
	
	public GameState getGameState() {
		return gameState;
	}
}
