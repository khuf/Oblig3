package no.uib.info233.v2017.khu010.oblig3;

/**
 * A singleton GameMaster class.
 * @author knu010 && xeq003
 * @version 0.0.1 (06.04.2017).
 */
public class GameMaster {
	
	//Static ensures it belongs to the class rather than an instance of this class.
	private static GameMaster gameMaster = new GameMaster();
	private SQLconnector server = new SQLconnector();

	//Game settings
	private Player bottomPlayer, topPlayer;
	private int topMove, bottomMove;
	private int currentPosition = 0;
	private int movesMade = 0;
	private int roundNumber = 1;
	private float[] points = {-1.0f, 0f, 0.25f, 0.5f, 0.75f, 1.0f, 2.0f};
	
	
	private GameMaster() {}
	
	/**
	 * Returns an instance of the Game Master.
	 * @return the GameMaster instance
	 */
	public static GameMaster getGameMaster() {return gameMaster;}
	
	/**
	 * Registers the players to the game master.
	 * @param player1 the topPlayer
	 * @param player2 the bottomPlayer
	 */
	public void setPlayers(Player player1, Player player2) {
		player1.registerGameMaster(this);
		player2.registerGameMaster(this);
		this.bottomPlayer = player1;
		this.topPlayer = player2;
	}
	
	/**
	 * Starts the game by sending a request to both players to come up 
	 * with their next move.
	 */
	public void startGame() {

		System.out.println("The two players meet each other in the middle circle and prepares to fight");
		topPlayer.makeNextMove(currentPosition, topPlayer.getEnergy(), bottomPlayer.getEnergy());
		bottomPlayer.makeNextMove(currentPosition, topPlayer.getEnergy(), bottomPlayer.getEnergy());
	}
	
	/**
	 * Listens to the players moves and evaluates the round once
	 * both players have made their move.
	 * @param player The player that does a move
	 * @param move The move that the player chose.
	 */
	public void listenToPlayerMove(Player player, int move) {
		if (player.equals(topPlayer)) {topMove = move; movesMade++;}
		if (player.equals(bottomPlayer)) {bottomMove = move; movesMade++;}
		
		if (movesMade == 2) {
			topMove = topPlayer.useEnergy(topMove);
			bottomMove = bottomPlayer.useEnergy(bottomMove);
			movesMade = 0;
			evaluateTurn();
		}
	}
	
	/**
	 * Evaluates the current round and continues the game if neither player is
	 * in a winning position and at least one them has energy to fight.
	 * Otherwise the game ends and both players are notified of their score.
	 */
	public void evaluateTurn() {
		
		//moves currentPosition in the winning players direction
		movePlayers();
		
		if (isFinnished()) {
			
			updateRanking();
			bottomPlayer.gameOver(getScore(bottomPlayer));
			topPlayer.gameOver(getScore(topPlayer));
			this.roundNumber = 1;
			
		} else {

			
			//Make next move
			topPlayer.makeNextMove(currentPosition, topPlayer.getEnergy(), bottomPlayer.getEnergy());
			bottomPlayer.makeNextMove(currentPosition, bottomPlayer.getEnergy(), topPlayer.getEnergy());
		}
	}
	
	/**
	 * Checks whether the game has ended by checking if either player is in a
	 * winning position or if both players are out of energy.
	 * @return true if the game has ended. Otherwise, false.
	 */
	private boolean isFinnished() {
		boolean hasWon = Math.abs(currentPosition) == 3;
		boolean hasNoEnergy = topPlayer.getEnergy() == 0 && bottomPlayer.getEnergy() == 0;
		return (hasWon || hasNoEnergy);
	}
	
	private void movePlayers() {
		if (topMove < bottomMove) { currentPosition++; } 
		else if (topMove > bottomMove) { currentPosition--;}
		
		printStatus();
		roundNumber++;
	}
	
	/**
	 * Prints the status of the current game to the console.
	 * This includes information such as the leading player if the
	 * game is ongoing or a game over message if the game has ended.
	 */
	private void printStatus() {

		Player leadingPlayer = getLeadingPlayer();
		String status = "";
		
		System.out.println("Round #" + roundNumber);
		System.out.println(topPlayer.getName() + ": " + topMove + "\n" + bottomPlayer.getName() + ": " + bottomMove);
		
		if (isFinnished()){
			status = "Game over" + "\n" + "Finishing position:" + currentPosition;
		} else {
			
			if (leadingPlayer != null) {
				status = leadingPlayer.getName() + " is in the lead";
			} else {
				status =  "Players are tied";
			}
		}
		System.out.println(status + "\n");
	}
	
	/**
	 * Gets the leading player in an ongoing game.
	 * @return the leading player.
	 */
	private Player getLeadingPlayer() {		
		if (currentPosition < 0) { return topPlayer; } 
		else if (currentPosition > 0) { return bottomPlayer; } 
		return null;
	}
	
	private float getScore(Player player){
		
		if (player == null) {return 0;}
		int position = Math.abs(currentPosition);
		
		if ( player.equals(getLeadingPlayer()) ){
			return points[3 + position];
		} else {
			return points[3 - position];
		}
	}

	/**
	 * Updates the ranking table with new scores from a finished game.
	 * @return status of completed sync to server
	 */
	public boolean updateRanking() {
		boolean syncStatus = (
				server.addScore(topPlayer.getName(), getScore(topPlayer)) &&
				server.addScore(bottomPlayer.getName(), getScore(bottomPlayer)) 
				);
		return syncStatus;
	}
	
	@Override
	public String toString() {
		String result = "";
		
		if (!isFinnished()) {
			result = "Current standings:\n" + topPlayer.getName() + ": " + getScore(topPlayer) + "\n" +
					bottomPlayer.getName() + ": " + getScore(bottomPlayer);
		}
		else {
			if (getScore(topPlayer) == getScore(bottomPlayer)) {
				result =  "Game has ended in a tie";
			}
			else {
				float points = Math.abs(getScore(topPlayer) - getScore(bottomPlayer));
				result = getLeadingPlayer() + " has won and recieved " + points + " point(s)";
			}
		}
		return result;
	}
}