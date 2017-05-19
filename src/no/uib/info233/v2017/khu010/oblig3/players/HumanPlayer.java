package no.uib.info233.v2017.khu010.oblig3.players;

import no.uib.info233.v2017.khu010.oblig3.Utility;
import no.uib.info233.v2017.khu010.oblig3.interfaces.PlayerControllerInterface;
import no.uib.info233.v2017.khu010.oblig3.sql.SQLManager;

public class HumanPlayer extends Player {
	
	//private PlayerControllerInterface controller = new SQLManager();
	
	public HumanPlayer(String name, int goal) {
		super(name, goal);
	}

	@Override
	public int makeNextMove(int move) { 
		int energyToUse = 0;
		
		if (Utility.isValidMove(this, move)) {
			energyToUse = move;
			//controller.sendMove(getName(), move);
		}
		return energyToUse;
	}
	
	public int makeNextMove(int curr, int opp) {
		throw new UnsupportedOperationException();
	}
}
