package no.uib.info233.v2017.khu010.oblig3;

/**
 * A singleton GameMaster class.
 * @author knu010
 * @version 0.0.1 (06.04.2017).
 */
public class GameMaster {
	
	//Static ensures it belongs to the class rather than an instance of this class.
	private static GameMaster gameMaster = null;
	private Player bottomPlayer; //fights from negative to positive
	private Player topPlayer; //fights from positive to negative
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
	//This is done by running  player.  for each player.
	public void startGame() {
		System.out.println("Game is starting!");
		//do rounds while both players have energy and currentposition is not endzones
		while ((bottomPlayer.getEnergy() != 0 || topPlayer.getEnergy() != 0) && currentPosition != 3 && currentPosition != -3)
		{
			System.out.println(bottomPlayer.getEnergy() != 0 || topPlayer.getEnergy() != 0);
			bottomPlayer.makeNextMove(currentPosition, bottomPlayer.getEnergy(), topPlayer.getEnergy());
			topPlayer.makeNextMove(currentPosition, topPlayer.getEnergy(), bottomPlayer.getEnergy());
			evaluateTurn();
		}
		System.out.println("Game over. End circle = " + currentPosition);
	}
	
	//each player uses this method to communicate how much energy he wants to use in the current turn. 
	//Treat all invalid inputs (values other than the energy currently available to the player) as equal to 0. 
	//If both players made a call to this method during the current round, run  evaluateTurn()
	public void listenToPlayerMove(Player player, int move) {
		if (move < 0) { move = 0; }
		if (player == bottomPlayer){
			bottomMove = move;
		} else {
			topMove = -move;
		}
	}
	
	//use the information submitted via . listenToPlayerMove  to identify who won and update the players on the state of the game 
	// either by running  player.makeNextMove  (if the game has not yet ended), or  player.gameOver
	// (in case the game has come to an end). If the game came to an end, also run  .updateRanking()
	public void evaluateTurn() {
		if (topMove != 0 && bottomMove != 0) {
			int result = topMove + bottomMove;
			if (result < 0){
				//top won
				System.out.println("top player won");
				currentPosition -= 1;
			} else if (result == 0){ 
				//tie
				System.out.println("the game is a tie");
			} else {
				//bottom won
				System.out.println("bottom player won");
				currentPosition += 1;
			}
			topMove = bottomMove = 0;
			System.out.println("result: " + result);
		}
	}
	
	//update the player rankings in the ranking table. This table is to be stored in a remote (mySQL) database. 
	//Use the table named “ranking”, with columns “player” (VARCHAR128) and “score” (FLOAT). 
	//You will be given the credentials required to connect to your group’s database from your seminar leader.
	public void updateRanking() {
		
	}
}
