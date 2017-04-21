package no.uib.info233.v2017.khu010.oblig3;

import java.util.Random;


//import khu010.Player;

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
		int distanceFromGoal = Math.abs(getGoal() - currentPosition);
		int enemyDistanceFromGoal = Math.abs((getGoal() * -1) - currentPosition);
		boolean hasEnergy = getEnergy() > 0;
		Random rng = new Random();

		if (hasEnergy) {
			
			// If we're 1 step from winning; use remaining energy
			if (distanceFromGoal == 1) {
				energyToUse = getEnergy();
			} else if (yourEnergy >= 30) {
				// Use energy between 30 and 20 (inclusive).
				energyToUse = rng.nextInt(30 - 20 + 1) + 20;

			} else if (opponentEnergy <= 0) {
				energyToUse = yourEnergy/distanceFromGoal;
			} else {
				debug("Agressive player is pinned into a corner and used all his energy");
				energyToUse = yourEnergy;
			}
		}
		getGameMaster().listenToPlayerMove(this, energyToUse);
		return energyToUse;
	}
}