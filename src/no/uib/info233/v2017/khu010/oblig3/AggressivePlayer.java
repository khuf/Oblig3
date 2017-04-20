package no.uib.info233.v2017.khu010.oblig3;

import java.util.Random;

/**
 * Class representing an aggressive player.
 * An aggressive player will try to win the match
 * within 3 rounds.
 * @author khuf010 && xeq003
 * @version 0.0.1 (08.04.2017).
 */
public class AggressivePlayer extends Player {

	/**
	 * Creates an aggressive player with a specified name.
	 * @param name of the player
	 * @param goal the players goal destination.
	 */
	public AggressivePlayer(String name, int goal) {
		super(name, goal);
	} 
	
	/**
	 * Makes a move based on the current position, player and opponents energy level.
	 * @param currentPosition the position where the robots fight.
	 * @param yourEnergy the energy level of this robot.
	 * @param opponentEnergy the energy level of the enemy.
	 */
	public int makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		
		int energyToUse = 0;
		Random rng = new Random();
		
		if (getEnergy() >= 15) {
		//Use energy between 5 and 15 (inclusive).
		energyToUse = rng.nextInt(15-5+1) + 5;
		}
		else {
			energyToUse = rng.nextInt(getEnergy());
		}
		getGameMaster().listenToPlayerMove(this, energyToUse);
		return energyToUse;
	}
}