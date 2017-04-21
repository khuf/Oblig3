package no.uib.info233.v2017.khu010.oblig3;

import java.util.Random;

/**
 * A class representing a defensive minded player.
 * A defensive player makes his choice...
 * @author khu010 && xeq003
 * @version 0.0.2 (14.04.2017)
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
			if (distanceFromGoal == 1) {
				energyToUse = yourEnergy;
			}
			else if (distanceFromGoal == 3 && yourEnergy > 80) {
				// Use energy between 5 and 15 (inclusive).
				energyToUse = rng.nextInt(35 - 23 + 1) + 23;
			}
			else if (yourEnergy > opponentEnergy) {
				//energyToUse = 20;
				int min = opponentEnergy/enemyDistanceFromGoal;
				energyToUse = rng.nextInt(yourEnergy - min + 1) + min;
			} 
		}
		getGameMaster().listenToPlayerMove(this, energyToUse);
		return energyToUse;
	}
}