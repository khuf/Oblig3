package no.uib.info233.v2017.khu010.oblig3;

public class DefensivePlayer extends Player{

	public DefensivePlayer(String name) {
		super(name);
	} 
	
	public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		
		int useEnergy;
		if (this.energy > 20){
			useEnergy = 20;
		} else {
			useEnergy = this.energy;
		}
		
		//System.out.println("energy: " + this.energy + " useEnergy: " + useEnergy);
		this.energy -= useEnergy;
		gameMaster.listenToPlayerMove(this, useEnergy);
	}
	
}
