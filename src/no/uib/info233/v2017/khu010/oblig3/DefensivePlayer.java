package no.uib.info233.v2017.khu010.oblig3;

import java.util.Random;

/**
 * A class representing a defensive minded player.
 * A defensive player makes his choice...
 * @author knu010 && xeq003
 * @version 0.3.8 (21.04.2017).
 *
 */
public class DefensivePlayer extends Player{

	/**
	 * Creates a defensive player with a specified name and goal.
	 * @param name of the player
	 * @param goal the players goal destination
	 */
	public DefensivePlayer(String name, int goal) {
		super(name, goal);
	}
	
	public int makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		int energyToUse = 0;
		int distanceFromGoal = Math.abs(getGoal() - currentPosition);
		int enemyDistanceFromGoal = Math.abs((getGoal() * -1) - currentPosition);
		Random rng = new Random();
		boolean hasEnergy = getEnergy() > 0;

		if (hasEnergy) {

			// Tries to trick opponent to drain his energy first round
			if (yourEnergy == 100 && opponentEnergy == 100) {
				if (rng.nextBoolean()) {
					energyToUse = 0;
				} else {
					energyToUse = rng.nextInt(35 - 15 + 1) + 15;
				}
			// uses the minimal energy possible if opponent is empty
			} else if (opponentEnergy == 0) {
				energyToUse = 1;
			}
			// If its 1 step from losing; use remaining energy
			else if (distanceFromGoal == 5) {
				energyToUse = yourEnergy;
			}
			// If its 2 steps from losing but has more energy than opponent
			else if (distanceFromGoal == 4 && yourEnergy > opponentEnergy) {
				energyToUse = (opponentEnergy / enemyDistanceFromGoal) + 1;
			}
			// default energyUse, use between 20 and 25 (inclusive).
			else if (yourEnergy >= 20) {
				energyToUse = rng.nextInt(25 - 20 + 1) + 20;
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