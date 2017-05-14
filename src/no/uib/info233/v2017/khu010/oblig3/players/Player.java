package no.uib.info233.v2017.khu010.oblig3.players;

import no.uib.info233.v2017.khu010.oblig3.interfaces.PlayerControllerInterface;

/**
 * Base class for a Player.
 * @author knu010 && xeq003
 * @version 0.3.8 (21.04.2017).
 */
abstract public class Player {
	
	private String name;
	private PlayerControllerInterface gameMaster;
	private int energy;
	private int goal;
	private int move;
	private boolean debug = true;
	
	/**
	 * Creates a Player with a specified name
	 * @param name
	 */
	public Player(String name, int goal) {
		this.name = name;
		this.energy = 100;
		this.goal = goal;
	}
	
	/**
	 * Registers the Game Master for the Player.
	 * @param gameMaster
	 * @throws IllegalArgumentException if the GM has not been instantiated.
	 */
	public void registerGameMaster(PlayerControllerInterface controller) throws IllegalArgumentException {
		if (controller == null) {
			throw new IllegalArgumentException("Gamemaster has not been instanciated");
		}
		this.gameMaster = controller;
	}
	
	public PlayerControllerInterface getGameMaster() {
		return gameMaster;
	}
	
	private void setMove(int move) {
		this.move = move;
	}
	
	/**
	 * Figures out how much energy to spend this round and forwards the
	 * information to the game master.
	 * @param currentPosition Where the robots are currently fighting.
	 * @param yourEnergy Player's current energy level
	 * @param opponentEnergy Opponents current energy level.
	 * @return energy to spend this round.
	 */
	public abstract boolean makeNextMove(int currentPosition, int opponentEnergy);
	
	/**
	 * Informs the player that the game has come to an end and how 
	 * much points was earned this round.
	 * @param earnedPoints the points earned by the player
	 */
	public void gameOver(float points){
		String result = "";
		if (points > 0) {
			result = " won ";
		} else if (points < 0) {
			result = " lost ";
		} else result = " got ";
		points = Math.abs(points);
		System.out.println(this.name + result + points + " points");
	}
	
	/**
	 * Uses a specified amount of energy from the player
	 * @param energyUsage how much energy to use
	 * @return Energy used
	 */
	public int useEnergy(int energyUsage) {
		int result = 0;
		if (energyUsage < 0) {energyUsage = 0;};
		if (this.energy >= energyUsage) {
			this.energy -= energyUsage;
			result = energyUsage;
		}
		return result;
	}
	
	/**
	 * Returns the energy level of the player
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
	 * Returns the name of the player
	 * @return name of the player
	 */
	public String getName(){
		return name;
	}
	
	public int getGoal() {
		return this.goal;
	}
	
	/**
	 * Returns a string representation of the Player.
	 * @return A string representation of the player
	 */
	public String toString(){
		return this.name + ", of type " + this.getClass().getSimpleName();
	}

	/**
	 * Generates a hashcode representing the player
	 * @return hashcode representation of player
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + energy;
		result = prime * result + goal;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Checks if this player is equal to the specified object.
	 * @return true if the objects are equal. Otherwise, false.
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
		if (goal != other.goal)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public void debug(String s) {
		System.out.println(s);
	}
	
}
