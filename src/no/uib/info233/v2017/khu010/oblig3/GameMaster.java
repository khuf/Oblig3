package no.uib.info233.v2017.khu010.oblig3;

/**
 * A singleton GameMaster class.
 * @author knu010
 * @version 0.0.1 (06.04.2017).
 */
public class GameMaster {
	
	//Static ensures it belongs to the class rather than an instance of this class.
	private static GameMaster gameMaster = null;
	private Player bottomPlayer, topPlayer;
	private int topMove, bottomMove;
	private int currentPosition = 0;
	
	private GameMaster() {}
	
	/**
	 * Retrieves the GameMaster instance and creates one if needed.
	 * @return the GameMaster instance
	 */
	public static GameMaster getGameMaster() {
		if (gameMaster == null) {
			gameMaster = new GameMaster();
		}
		return gameMaster;
	}
	
	//assign the players that are going to play against each other.
	public void setPlayers(Player player1, Player player2) {
		this.bottomPlayer = player1;
		this.topPlayer = player2;
	}
	
	//sends a message to each of the players to come up with their next move. 
	//This is done by running  player.makeNextMove  for each player.
	public void startGame() {
		System.out.println("Game is starting!");
		//continue to to rounds as long as one of the players have energy and they have not reached one of the endzones
		while ((bottomPlayer.getEnergy() != 0 || topPlayer.getEnergy() != 0) && currentPosition != 3 && currentPosition != -3)
		{
			evaluateTurn();
		}
		System.out.println("Game over. End circle = " + currentPosition);
		updateRanking();
	}
	
	//each player uses this method to communicate how much energy he wants to use in the current turn. 
	//Treat all invalid inputs (values other than the energy currently available to the player) as equal to 0. 
	//If both players made a call to this method during the current round, run evaluateTurn()
	public void listenToPlayerMove(Player player, int move) {
		if (move < 0) { move = 0; }
		if (player == bottomPlayer){
			bottomMove = move;
		} else {
			topMove = move;
		}
	}
	
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
			String topType = topPlayer.getClass().getSimpleName();
			System.out.println(topType + " won by " + (topMove - bottomMove) + " points and pushed bottom player 1 circle back");
			currentPosition -= 1;
		} else {
			String bottomType = bottomPlayer.getClass().getSimpleName();
			System.out.println(bottomType + " won by " + (bottomMove - topMove) + " points and pushed top player 1 circle back");
			currentPosition += 1;
		}
		topMove = bottomMove = 0;
		if (topPlayer.getEnergy() == 0 && bottomPlayer.getEnergy() == 0){
			topPlayer.gameOver(calculateScore(topPlayer));
			bottomPlayer.gameOver(calculateScore(bottomPlayer));
		}
	}
	
	public float calculateScore(Player player) {
		float bonus = 0.5f;
		int endPos = Math.abs(currentPosition);
		if (endPos > 0){bonus += 0.25;}
		if (endPos > 1){bonus += 0.25;}
		if (endPos > 2){bonus += 1.0;}
		
		float topScore = 0;
		float bottomScore = 0;
		
		if (currentPosition > 0){ //bottom won
			topScore -= bonus;
			bottomScore += bonus;
		} else if (currentPosition < 0){ //top won
			topScore += bonus;
			bottomScore -= bonus;
		} else {
			topScore = bonus;
			bottomScore = bonus;
		}
		
		if (player == topPlayer){
			return topScore;
		} else {
			return bottomScore;
		}
	}
	
	//update the player rankings in the ranking table. This table is to be stored in a remote (mySQL) database. 
	//Use the table named “ranking”, with columns “player” (VARCHAR128) and “score” (FLOAT). 
	//You will be given the credentials required to connect to your group’s database from your seminar leader.
	public void updateRanking() {
		//send data to sql here
	}
}