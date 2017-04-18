package no.uib.info233.v2017.khu010.oblig3;

/**
 * Base class for a Player.
 * @author knuhuf && ....
 * @version 0.0.1 (11.04.2017)
 */
abstract public class Player {
	
	private String name;
	private GameMaster gameMaster;
	private int energy;
	
	/**
	 * Creates a Player with a specified name
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
		this.energy = 100;
	}
	
	/**
	 * Registers the Game Master for the Player.
	 * @param gameMaster
	 * @throws IllegalArgumentException
	 */
	public void registerGameMaster(GameMaster gameMaster) throws IllegalArgumentException {
		if (gameMaster == null) {
			throw new IllegalArgumentException("Gamemaster has not been instanciated");
		}
		this.gameMaster = gameMaster;
	}
	
	//Figure out how much energy the player wants to spend based on the current state of the game. 
	//Call gameMaster.listenToPlayerMove to inform the gameMaster about the players choice.
	//gets inputs from gameMaster
	abstract int makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy);
	
	//Informs the player that the game has come to an and and how many points he earned in this game.
	public void gameOver(float earnedPoints){
		System.out.println(name + " got " + earnedPoints + " points");
	}
	
	/**
	 * Sets the energy level of the player
	 * @param energy level
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	/**
	 * Returns the Players energy level
	 * @return energy level
	 */
	public int getEnergy(){
		return energy;
	}
	
	/**
	 * Sets the name of the player
	 * @param name of player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the Player's name
	 * @return name of the player
	 */
	public String getName(){
		return name;
	}

	/**
	 * Returns the game master instance
	 * @return
	 */
	public GameMaster getGameMaster() {
		return gameMaster;
	}
	
	/**
	 * Returns a string representation of the Player.
	 * @return A string representation of the player
	 */
	public String toString(){
		return this.name + ", of type " + this.getClass().getSimpleName();
	}
}
