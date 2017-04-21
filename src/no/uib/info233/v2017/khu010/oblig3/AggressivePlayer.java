package no.uib.info233.v2017.khu010.oblig3;

import java.util.Random;


/**
 * Class representing an aggressive player.
 * An aggressive player will try to win the match
 * within 3 rounds.
 * @author knu010 && xeq003
 * @version 0.3.8 (21.04.2017).
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
		boolean hasEnergy = getEnergy() > 0;
		Random rng = new Random();

		if (hasEnergy) {

			// If its 1 step from winning; use remaining energy
			if (distanceFromGoal == 1) {
				energyToUse = getEnergy();
			} 
			// uses the minimal energy possible if opponent is empty
			else if (opponentEnergy == 0) {
				energyToUse = 1;
			}
			// default energyUse, use between 20 and 30 (inclusive).
			else if (yourEnergy >= 30){
				energyToUse = rng.nextInt(30 - 20 + 1) + 20;
			} 
			//Uses rest of energy if almost empty
			else {
				energyToUse = yourEnergy;
			}
		} 
		getGameMaster().listenToPlayerMove(this, energyToUse);
		return energyToUse;
	}
}
