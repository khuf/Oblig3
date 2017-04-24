package no.uib.info233.v2017.khu010.oblig3;

public class PlayerController {
	
	private GameMaster gameMaster;
	
	public PlayerController(GameMaster gameMaster) {
		this.gameMaster = gameMaster;
	}
	
	public boolean performMove(Player player) {
		boolean result = false;
		
		if (player != null) {
			result = gameMaster.makeNextMove(Player player);
		}
		return result;
	}

}
