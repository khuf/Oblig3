package no.uib.info233.v2017.khu010.oblig3;

/**
 * Class representing an aggressive player.
 * An aggressive player will try to win the match
 * within 3 rounds.
 */
public class AggressivePlayer extends Player {

	/**
	 * Creates an aggressive player with a specified name.
	 * @param name of the player
	 */
	public AggressivePlayer(String name) {
		super(name);
	} 
	
	/**
	 * Makes a move based on the current position, player and opponent energy level.
	 */
	public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		
		int useEnergy;
		if (getEnergy() > 40){
			useEnergy = 40;
		} else {
			useEnergy = getEnergy();
		}
		
		//System.out.println("energy: " + this.energy + " useEnergy: " + useEnergy);
		setEnergy(getEnergy() - useEnergy);
		getGameMaster().listenToPlayerMove(this, useEnergy);
	}
}

