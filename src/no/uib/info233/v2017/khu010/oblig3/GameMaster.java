package no.uib.info233.v2017.khu010.oblig3;

import java.util.HashMap;
import java.util.Map;




/**
 * A singleton GameMaster class.
 * @author knu010 && xeq003
 * @version 0.0.2 (14.04.2017).
 */
public class GameMaster {
	
	//Static ensures it belongs to the class rather than an instance of this class.
	private static GameMaster gameMaster = new GameMaster();
	private int currentPosition = 0;
	private static Map<Integer, Point> scoreBoard;
	private SQLconnector server = new SQLconnector();
	
	private Player bottomPlayer, topPlayer;
	private int bottomMove, topMove = -1;
	
	private GameMaster() {}
	
	/**
	 * Retrieves the GameMaster instance and creates one if needed.
	 * @return the GameMaster instance
	 */
	public static GameMaster getGameMaster() {
		initializeScoreboard();
		return gameMaster;
	}
	
	/**
	 * Creates the scoreboard for the game using a hashmap where
	 * the key corresponds with the position in which the game has ended.
	 */
	private static void initializeScoreboard() {
		scoreBoard = new HashMap<>();
		
		scoreBoard.put(-3, new Point(-1F, 2F));
		scoreBoard.put(-2, new Point(0F, 1F));
		scoreBoard.put(-1, new Point(0.25F, 0.75F));
		scoreBoard.put(0, new Point(0.5F, 0.5F));
		scoreBoard.put(1, new Point(0.75F, 0.25F));
		scoreBoard.put(2, new Point(1F, 0F));
		scoreBoard.put(3, new Point(2F, -1F));
	}
	
	/**
	 * Registers the players to the game master.
	 * @param player1
	 * @param player2
	 */
	public void setPlayers(Player player1, Player player2) {
		player1.registerGameMaster(this);
		player2.registerGameMaster(this);
		this.bottomPlayer = player1;
		this.topPlayer = player2;
	}
	
	/**
	 * Starts a game by requesting both players to come up with a move.
	 */
	public void startGame() {
			topPlayer.makeNextMove(currentPosition, topPlayer.getEnergy(), bottomPlayer.getEnergy());
			bottomPlayer.makeNextMove(currentPosition, bottomPlayer.getEnergy(), topPlayer.getEnergy());
	}
	
	/**
	 * Listens to the players moves and evaluates the round once
	 * both players have made its move.
	 * @param player The player that does a move
	 * @param move The move that the player chose.
	 */
	public void listenToPlayerMove(Player player, int move) {
		if (player.equals(topPlayer)) {
			topMove = move;
		}
		else if (player.equals(bottomPlayer)) {
			bottomMove = move;
		}
		
		if (topMove != -1 && bottomMove != -1) {
			topPlayer.useEnergy(topMove);
			bottomPlayer.useEnergy(bottomMove);
			evaluateTurn();
		}
	}
	
	/**
	 * Checks whether the game has ended by checking if either player is in a
	 * winning position or if both players are out of energy.
	 * @return
	 */
	private boolean isFinnished() {
		boolean hasWon = Math.abs(currentPosition) == 3;
		boolean hasNoEnergy = topPlayer.getEnergy() == 0 && bottomPlayer.getEnergy() == 0;
		
		return (hasWon || hasNoEnergy);
	}
	
	/**
	 * Creates a string representation of the current state of the game.
	 * @return a string representing the game's current state.
	 */
	public String toString() {
		String result = "";
		float topPlayerScore = scoreBoard.get(currentPosition).getPointA();
		float bottomPlayerScore = scoreBoard.get(currentPosition).getPointB();
		
		if (!isFinnished()) {
			result = "Current standings:\n" + topPlayer.getName() + ": " + topPlayerScore + "\n" +
					bottomPlayer.getName() + ": " + bottomPlayerScore;
		}
		else {
			if (topPlayerScore == bottomPlayerScore) {
				result =  "Game has ended in a tie";
			}
			else {
				String winner = topPlayerScore > bottomPlayerScore ? topPlayer.getName() : bottomPlayer.getName();
				float points = Math.abs(topPlayerScore - bottomPlayerScore);
				result = winner + " has won by " + points + " points";
			}
		}
		return result;
	}
	
	/**
	 * Evaluates the current round and continues the game if neither player is
	 * in a winning position and at least one them has energy to fight.
	 * Otherwise the game ends and both players are notified of their score.
	 */
	public void evaluateTurn() {

		if (isFinnished()){
			topPlayer.gameOver(scoreBoard.get(currentPosition).getPointA());
			bottomPlayer.gameOver(scoreBoard.get(currentPosition).getPointB());
			System.out.println(gameMaster);
		}
		else if (topMove != bottomMove) {
			if (topMove > bottomMove) {
				currentPosition++;
			}
			else {
				currentPosition--;
			}
			
			//Reset moves
			topMove = -1;
			bottomMove = -1;
			
			//Make next move
			topPlayer.makeNextMove(currentPosition, topPlayer.getEnergy(), bottomPlayer.getEnergy());
			bottomPlayer.makeNextMove(currentPosition, bottomPlayer.getEnergy(), topPlayer.getEnergy());
		}			
	}
	
	/**
	 * Updates the ranking table with scores from the finished
	 * game.
	 * @return true if the update was successful. Otherwise, false.
	 */
	public boolean updateRanking() {
		float topPlayerScore = scoreBoard.get(currentPosition).getPointA();
		float bottomPlayerScore = scoreBoard.get(currentPosition).getPointB();
		
		return (server.addScore(topPlayer.getName(), topPlayerScore) &&
				server.addScore(bottomPlayer.getName(), bottomPlayerScore));
	}
}