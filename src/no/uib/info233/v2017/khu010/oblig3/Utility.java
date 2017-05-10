package no.uib.info233.v2017.khu010.oblig3;

import no.uib.info233.v2017.khu010.oblig3.players.Player;

public class Utility {
	
	public static boolean isValidMove(Player player, int move) {
		boolean result = false;
		
		if (player != null) {
			if (player.getEnergy() >= move) {
				result = true;
			}
		}
		return result;
	}
}
