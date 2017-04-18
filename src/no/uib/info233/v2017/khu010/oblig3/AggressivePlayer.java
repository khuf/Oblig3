package no.uib.info233.v2017.khu010.oblig3;

import java.util.Random;

import khu010.Player;

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
	 */
	public AggressivePlayer(String name, int goal) {
		super(name);
	} 
	
	/**
	 * Makes a move based on the current position, player and opponent energy level.
	 * @param currentPosition the position where the robots fight.
	 * @param yourEnergy the energy level of this robot.
	 * @param opponentEnergy the energy level of the enemy.
	 */
	public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		int energyToUse = 0;
		Random rng = new Random();
		
		if (getEnergy() >= 15) {
		energyToUse = rng.nextInt(15);
		}
		else {
			energyToUse = rng.nextInt(getEnergy());
		}
		getGameMaster().listenToPlayerMove(this, energyToUse);
	}
}