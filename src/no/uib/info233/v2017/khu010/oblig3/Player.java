package no.uib.info233.v2017.khu010.oblig3;

import khu010.GameMaster;
import khu010.Player;

/**
 * Base class for a Player.
 * @author khuf010 && xeq003
 * @version 0.0.1 (11.04.2017)
 */
abstract public class Player {
	
	private String name;
	private int goal;
	private GameMaster gameMaster;
	private int energy;
	
	/**
	 * Creates a Player with a specified name
	 * @param name
	 */
	public Player(String name, int goal) {
		this.name = name;
		this.goal = goal;
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
	
	/**
	 * Figures out how much energy the player wants to spend based on the
	 * current state of the game, i.e currentPosition and energy level for both
	 * himself and his opponent.
	 * @param currentPosition
	 * @param yourEnergy
	 * @param opponentEnergy
	 */
	abstract void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy);
	
	/**
	 * Informs the player that the game has come to an end and how many 
	 * points he scored in the game.
	 * @param earnedPoints
	 */
	public void gameOver(float earnedPoints){
		System.out.println(name + " got " + earnedPoints + " points");
		System.out.println("Energy: " + getEnergy());
	}
	
	/**
	 * Uses the specified energy if available to the player.
	 * @param energy level
	 */
	public int useEnergy(int energyLevel) {
		int used = 0;
		if (this.energy >= energyLevel) {
			this.energy = this.energy - energyLevel;
			used = energy;
		}
		return used;
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
	 * Returns the players goal position
	 * @return
	 */
	public int getGoal() {
		return goal;
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
	

	/** Generates a hashcode for this object.
	 * @return a hashcode representing the object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + energy;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/** 
	 * Checks for equality between this and the specified object.
	 * @return true/false, whether the specified object is equal or not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (energy != other.energy)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
