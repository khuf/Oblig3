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
			
			// If we're 1 step from winning; use remaining energy
			if (distanceFromGoal == 1) {
				energyToUse = getEnergy();
			} else  if (distanceFromGoal > 3 && yourEnergy > opponentEnergy){
				//energyToUse = yourEnergy/distanceFromGoal;
				energyToUse = (opponentEnergy / enemyDistanceFromGoal) + 1;
			} else if (yourEnergy >= 20) {
				// Use energy between 25 and 20 (inclusive).
				energyToUse = rng.nextInt(25 - 20 + 1) + 20;
				debug("def using max 25");
			} else if (opponentEnergy <= 0) {
				energyToUse = yourEnergy/distanceFromGoal;
			} else {
				debug("def using remaining");
				energyToUse = yourEnergy;
			}
		}
		
		getGameMaster().listenToPlayerMove(this, energyToUse);
		return energyToUse;
	}
}