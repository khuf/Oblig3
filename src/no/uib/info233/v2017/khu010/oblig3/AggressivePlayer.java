package no.uib.info233.v2017.khu010.oblig3;

/*
 * This player always tries to win within 3 rounds
 */

public class AggressivePlayer extends Player {

	public AggressivePlayer(String name) {
		super(name);
	} 
	
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

