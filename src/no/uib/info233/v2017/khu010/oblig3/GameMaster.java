package no.uib.info233.v2017.khu010.oblig3;

/**
 * A singleton GameMaster class.
 * @author knu010 && xeq003
 * @version 0.0.1 (06.04.2017).
 */
public class GameMaster {
	
	//Static ensures it belongs to the class rather than an instance of this class.
	private static GameMaster gameMaster = new GameMaster();
	private Player bottomPlayer, topPlayer;
	private int topMove, bottomMove;
	private int currentPosition = 0;
	private float topPlayerScore, bottomPlayerScore;
	private SQLconnector server = new SQLconnector();
	
	private GameMaster() {}
	
	/**
	 * @return the GameMaster instance
	 */
	public static GameMaster getGameMaster() {
		return gameMaster;
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
	 * Starts the game by sending a request to both players to come up 
	 * with their next move.
	 */
	public void startGame() {
		System.out.println("Game is starting!");
		//continue to to rounds as long as one of the players have energy and they have not reached one of the endzones
		while ((bottomPlayer.getEnergy() != 0 || topPlayer.getEnergy() != 0) && Math.abs(currentPosition) != 3)
		{
			evaluateTurn();
		}
		System.out.println("Game over. End circle = " + currentPosition);
		updateRanking();
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
	 * 
	 */
	//use the information submitted via . listenToPlayerMove  to identify who won and update the players on the state of the game 
	// either by running  player.makeNextMove  (if the game has not yet ended), or  player.gameOver
	// (in case the game has come to an end). If the game came to an end, also run  .updateRanking()
	public void evaluateTurn() {
		//System.out.println("topMove: " + topMove);
		//System.out.println("bottomMove: " + bottomMove);
		int topEnergy = topPlayer.getEnergy();
		int bottomEnergy = bottomPlayer.getEnergy();
		
		topPlayer.makeNextMove(currentPosition, topEnergy, bottomEnergy);
		bottomPlayer.makeNextMove(currentPosition, bottomEnergy, topEnergy);

		if (topMove == bottomMove) { 
			System.out.println("the round ended in a tie");
		} else if(topMove > bottomMove) {
			System.out.println(topPlayer + " won by " + (topMove - bottomMove) + " points and pushed bottom player 1 circle back");
			currentPosition -= 1;
		} else {
			System.out.println(bottomPlayer + " won by " + (bottomMove - topMove) + " points and pushed top player 1 circle back");
			currentPosition += 1;
		}
		topMove = bottomMove = 0;
		
		//game over
		if (topPlayer.getEnergy() == 0 && bottomPlayer.getEnergy() == 0){
			updateRanking();
			topPlayer.gameOver(topPlayerScore);
			bottomPlayer.gameOver(bottomPlayerScore);
		}
	}
	
	private float calculateScores() {
		float bonus = 0.5f;
		int endPos = Math.abs(currentPosition);
		if (endPos > 0){bonus += 0.25;}
		if (endPos > 1){bonus += 0.25;}
		if (endPos > 2){bonus += 1.0;}
		
		if (currentPosition > 0){ //bottom won
			topPlayerScore -= bonus;
			bottomPlayerScore += bonus;
		} else if (currentPosition < 0){ //top won
			topPlayerScore += bonus;
			bottomPlayerScore -= bonus;
		} else {
			topPlayerScore = bonus;
			bottomPlayerScore = bonus;
		}
		
		return (topPlayerScore + bottomPlayerScore);
	}
	
	//update the player rankings in the ranking table. This table is to be stored in a remote (mySQL) database. 
	//Use the table named “ranking”, with columns “player” (VARCHAR128) and “score” (FLOAT). 
	//You will be given the credentials required to connect to your group’s database from your seminar leader.
	public boolean updateRanking() {
		if (calculateScores() == 1) {
		return (server.addScore(topPlayer.getName(), topPlayerScore) &&
				server.addScore(bottomPlayer.getName(), bottomPlayerScore));
		}
		return false;
	}
	
	/**
	 * Checks wether the game has ended by checking if either player is in a
	 * winning position or if both players are out of energy.
	 * @return
	 */
	private boolean isFinnished() {
		boolean hasWon = Math.abs(currentPosition) == 3;
		boolean hasNoEnergy = topPlayer.getEnergy() == 0 && bottomPlayer.getEnergy() == 0;
		
		return (hasWon || hasNoEnergy);
	}
	
	@Override
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
}