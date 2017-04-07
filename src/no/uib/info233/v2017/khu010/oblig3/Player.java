package no.uib.info233.v2017.khu010.oblig3;

public class Player {
	
	GameMaster gameMaster;
	
	
	public Player(String name) {
		
	}
	
	public void registerGameMaster(GameMaster gameMaster) {
		this.gameMaster = gameMaster;
	}
	
	public int makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		return 0;
	}
}
