package no.uib.info233.v2017.khu010.oblig3;

import no.uib.info233.v2017.khu010.oblig3.players.Player;

public class Utility {
	
	static boolean debug = true;
	
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
