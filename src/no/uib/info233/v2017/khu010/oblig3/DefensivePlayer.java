package no.uib.info233.v2017.khu010.oblig3;

public class DefensivePlayer extends Player{

	public DefensivePlayer(String name) {
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
