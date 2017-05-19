package no.uib.info233.v2017.khu010.oblig3.players;

import no.uib.info233.v2017.khu010.oblig3.Utility;

/**
 * A class representing a human player.
 * @author knu010
 * @version 0.0.1
 */
public class HumanPlayer extends Player {
	
	/**
	 * Creates a human player with the specified name and goal position.
	 * @param name of the player
	 * @param goal position.
	 */
	public HumanPlayer(String name, int goal) {
		super(name, goal);
	}
	
	/**
	 * Used by the GUI to perform a move. The
	 * game then proceeds to submit the move through the sendMove() method.
	 */
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
