package no.uib.info233.v2017.khu010.oblig3;

abstract public class Player {
	
	private String name;
	private GameMaster gameMaster;
	
	
	public Player(String name) {
		this.name = name;
	}
	
	public void registerGameMaster(GameMaster gameMaster) throws IllegalArgumentException {
		if (gameMaster == null) {
			throw new IllegalArgumentException("Gamemaster has not been instanciated");
		}
		this.gameMaster = gameMaster;
	}
	
	//Figure out how much energy the player wants to spend based on the current state of the game. 
	//Call gameMaster.listenToPlayerMove  to inform the gameMaster about the players choice.
	public int makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		return 0;
	}
	
	//Informs the player that the game has come to an and and how many points he earned in this game.
	public void gameOver(float earnedPoints){
		System.out.println("you lost... you got x points");
	}
}
