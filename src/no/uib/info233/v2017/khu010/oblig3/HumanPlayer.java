package no.uib.info233.v2017.khu010.oblig3;

import java.util.Scanner;

/**
 * A class representing a human
 * controlled player.
 * @author khuf010 && xeq003
 * @version 0.0.1 (04.04.2017).
 *
 */
public class HumanPlayer extends Player {

	/**
	 * Creates a player with the specified name and goal position.
	 * @param name
	 * @param goal
	 */
	public HumanPlayer(String name, int goal) {
		super(name, goal);
	}

	/**
	 * Makes a move based on the current position, player and opponent energy level.
	 * @param currentPosition the position where the robots fight.
	 * @param yourEnergy the energy level of this robot.
	 * @param opponentEnergy the energy level of the enemy.
	 */
	@Override
	void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		int energyToUse = 0;
		System.out.println("Current position: " + currentPosition + "\n" +
	"Your energy: " + yourEnergy + "\n" + "Opponents Energy: " + opponentEnergy + "\n");
		
		boolean notUsed = true;
		
		while(notUsed) {
		Scanner sc = new Scanner(System.in);
		
		energyToUse = sc.nextInt();
		
		if (energyToUse <= getEnergy()) {
				notUsed = false;
				sc.close();
			}
		}
		
		getGameMaster().listenToPlayerMove(this, energyToUse);
	}
}
