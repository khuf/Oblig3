package no.uib.info233.v2017.khu010.oblig3;

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
		int energyToUse;
		if (getEnergy() > 20){
			energyToUse = 20;
		} else {
			energyToUse = getEnergy();
		}
		
		getGameMaster().listenToPlayerMove(this, energyToUse);
		return energyToUse;
	}
	
}
