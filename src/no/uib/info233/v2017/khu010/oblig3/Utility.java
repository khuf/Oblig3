package no.uib.info233.v2017.khu010.oblig3;

import no.uib.info233.v2017.khu010.oblig3.players.Player;

public class Utility {
	
	static boolean debug = true;
	
	/**
	 * Validates if the specified player is trying to perform
	 * a valid move.
	 * @param player an instance of a player
	 * @param move the move to validate
	 * @return true if the move was valid, otherwise, false.
	 */
	public static boolean isValidMove(Player player, int move) {
		boolean result = false;
		
		if (player != null) {
			if (player.getEnergy() >= move) {
				result = true;
			}
		}
		return result;
	}
	
	public static void debug(Object printme) {
		if (debug){
			System.out.println(printme.toString());
		}
		
	}
}
