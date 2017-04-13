package no.uib.info233.v2017.khu010.oblig3;

/*
 * This player always tries to win within 4 rounds
 */

public class AggressivePlayer extends Player {

	public AggressivePlayer(String name) {
		super(name);
	} 
	
	public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		
		int useEnergy;
		if (this.energy > 50){
			useEnergy = 50;
		} else {
			useEnergy = this.energy;
		}
		
		gameMaster.listenToPlayerMove(this, useEnergy);
		this.energy -= useEnergy;
	}
}

