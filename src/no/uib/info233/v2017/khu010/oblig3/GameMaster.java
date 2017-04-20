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
	private float topPlayerScore, bottomPlayerScore;
	private int movesMade = 0;
	private int roundNumber = 0;
	
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
	 * both players have made its move.
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
		printStatus();
		roundNumber++;
		if (isFinnished()) {
			updateRanking();
			bottomPlayer.gameOver(bottomPlayerScore);
			topPlayer.gameOver(topPlayerScore);
			System.out.println("Finishing round: " + roundNumber++);
			System.out.println("Finishing position: " + currentPosition);
		} else {
			
			if (topMove > bottomMove) {
				currentPosition++;
			} else {
				currentPosition--;
			}
			
			//Make next move
			topPlayer.makeNextMove(currentPosition, topPlayer.getEnergy(), bottomPlayer.getEnergy());
			bottomPlayer.makeNextMove(currentPosition, bottomPlayer.getEnergy(), topPlayer.getEnergy());
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
	
	private void printStatus() {

		Player leadingPlayer = getLeadingPlayer();
		String status = "";
		if (roundNumber == 0){
			status = "Game is starting";
		} else if (isFinnished()){
			status = "Game over";
		} else if (leadingPlayer != null) {
			status = "Round #" + roundNumber + "\n" + leadingPlayer.getName() + " is in the lead";
		} else {
			status = "Round #" + roundNumber + "\nPlayers are tied";
		}
		System.out.println(status);
	}
	
	private Player getLeadingPlayer() {		
		if (currentPosition < 0){
			return topPlayer;
		} else if (currentPosition > 0){
			return bottomPlayer;
		} else return null;
	}
	
	/**
	 * Calculates the score for each player from a finished game.
	 * @return sum of both players score (1).
	 */
	private void calculateScores() {
		topPlayerScore = 0.5f;
		bottomPlayerScore = 0.5f;

		float bonus = 0;
		int endPos = Math.abs(currentPosition);

		if (endPos > 0){bonus += 0.25;}
		if (endPos > 1){bonus += 0.25;}
		if (endPos > 2){bonus += 1.0;}

		//It's a tie
		if (getLeadingPlayer() == null) {
			topPlayerScore = bonus;
			bottomPlayerScore = bonus;
		}
		//Top won
		else if (getLeadingPlayer().equals(topPlayer)) {
			topPlayerScore += bonus;
			bottomPlayerScore -= bonus;
		}
		//Bottom won
		else {
			topPlayerScore -= bonus;
			bottomPlayerScore += bonus;
		}
	}
	
	/**
	 * Updates the ranking table with new scores from a finished game.
	 * @return
	 */
	public boolean updateRanking() {
		calculateScores();
		return true;
		/*
		 * return (server.addScore(topPlayer.getName(), topPlayerScore) &&
				server.addScore(bottomPlayer.getName(), bottomPlayerScore));
		 */
	}
	
	@Override
	public String toString() {
		String result = "";
		
		if (!isFinnished()) {
			result = "Current standings:\n" + topPlayer.getName() + ": " + topPlayerScore + "\n" +
					bottomPlayer.getName() + ": " + bottomPlayerScore;
		}
		else {
			if (topPlayerScore == bottomPlayerScore) {
				result =  "Game has ended in a tie";
			}
			else {
				float points = Math.abs(topPlayerScore - bottomPlayerScore);
				result = getLeadingPlayer() + " has won and recieved " + points + " point(s)";
			}
		}
		return result;
	}
}