package no.uib.info233.v2017.khu010.oblig3;

abstract public class Player {
	
	private String name;
	private GameMaster gameMaster;
	private int energy;
	
	
	public Player(String name) {
		this.name = name;
		this.energy = 100;
	}
	
	public void registerGameMaster(GameMaster gameMaster) throws IllegalArgumentException {
		if (gameMaster == null) {
			throw new IllegalArgumentException("Gamemaster has not been instanciated");
		}
		this.gameMaster = gameMaster;
	}
	
	//Figure out how much energy the player wants to spend based on the current state of the game. 
	//Call gameMaster.listenToPlayerMove to inform the gameMaster about the players choice.
	//gets inputs from gameMaster
	public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {}
	
	//Informs the player that the game has come to an and and how many points he earned in this game.
	public void gameOver(float earnedPoints){
		System.out.println(name + " got " + earnedPoints + " points");
	}
	
	/**
	 * Returns the Players energy level
	 * @return energy level
	 */
	public int getEnergy(){
		return energy;
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public String getName(){
		return name;
	}

	public String toString(){
		return this.name + ", of type " + this.getClass().getSimpleName();
	}
	/**
	 * Returns the game master instance
	 * @return
	 */
	public GameMaster getGameMaster() {
		return gameMaster;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
