package no.uib.info233.v2017.khu010.oblig3;

public class DefensivePlayer extends Player{

	public DefensivePlayer(String name) {
		super(name);
	} 
	
	public int makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		
		int useEnergy;
		if (getEnergy() > 20){
			useEnergy = 20;
		} else {
			useEnergy = getEnergy();
		}
		
		//System.out.println("energy: " + this.energy + " useEnergy: " + useEnergy);
		setEnergy(getEnergy() - useEnergy);
		getGameMaster().listenToPlayerMove(this, useEnergy);
		return useEnergy;
	}
	
}
