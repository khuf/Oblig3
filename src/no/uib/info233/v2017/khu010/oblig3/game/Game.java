package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.Utility;
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
	private boolean moveMade = false;
	private boolean isHost = false;
	
	/**
	 * Creates a fresh game with no players set.
	 */
	public Game() {
		gameState = new GameState();
	}
	
	public Game(GameState gameState) {
		setGameState(gameState);
	}
	
	public Player getPlayerA() {
		return gameState.getPlayerA();
	}
	
	public Player getPlayerB() {
		return gameState.getPlayerB();
	}
	
	public void setPlayerA(Player playerA) {
		gameState.setPlayerA(playerA);
	}
	
	public void setPlayerB(Player playerB) {
		gameState.setPlayerB(playerB);
	}
	
	/**
	 * Sets the move of player A and ensures that the specified move is a valid move, i.e. 
	 * player A can afford the move.
	 * @param move
	 */
	public boolean setPlayerAMove(int move) {
		boolean result = false;
		if (Utility.isValidMove(getPlayerA(), move)) {
			gameState.setPlayerAMove(move);
			result = true;
		}
		return result;
	}
	
	/**
	 * Sets the move of player B and ensures that the specified move is a valid move, i.e. 
	 * player B can afford the move.
	 * @param move
	 */
	public boolean setPlayerBMove(int move) {
		boolean result = false;
		if (Utility.isValidMove(getPlayerB(), move)) {
			gameState.setPlayerAMove(move);
			result = true;
		}
		return result;
	}
	
	/**
	 * Checks whether the game is in a winning position.
	 * This has been moved from Game to GameState because the thread that is
	 * observing changes in the database, uses this method to determine when the thread
	 * should end.
	 * @return true for a finished game, otherwise it returns false.
	 */
	public synchronized boolean isFinnished() {
		return !playersHasEnergy() || Math.abs(gameState.getCurrentPosition()) == 3;
	}
	
	/**
	 * Checks if either player is out of energy.
	 * @return true/false if either of the players are out of energy.
	 */
	private boolean playersHasEnergy() {
		return getPlayerA().getEnergy() > 0 && getPlayerB().getEnergy() > 0;
	}
	
	abstract public void runGame();
	
	abstract public boolean performMoves();
	
	abstract public void evaluateTurn();
	
	public void setMoveMade(boolean result) {
		moveMade = result;
	}
	
	public void setIsHost(boolean isHost) {
		this.isHost = isHost;
	}
	
	public boolean getIsHost() {
		return isHost;
	}
	
	public void setGameState(GameState gameState) throws IllegalArgumentException {
		if (gameState != null) {
			this.gameState = gameState;
		}
		else {
			throw new IllegalArgumentException("The GameState cannot be null");
		}
	}
	
	public GameState getGameState() {
		return gameState;
	}
}
