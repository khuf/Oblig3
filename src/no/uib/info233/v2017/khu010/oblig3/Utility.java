package no.uib.info233.v2017.khu010.oblig3;

import no.uib.info233.v2017.khu010.oblig3.game.GameState;
import no.uib.info233.v2017.khu010.oblig3.game.MultiPlayerGame;
import no.uib.info233.v2017.khu010.oblig3.players.Player;
import org.apache.commons.lang3.RandomStringUtils;

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
	
	public static boolean updateGameState(GameState oldState, GameState latestGameState) {
		boolean result = false;
		if (oldState != null && latestGameState != null) {
			if (validatePlayer(oldState.getPlayerA(), latestGameState.getPlayerA(), latestGameState.getPlayerAMove())) {
				if (validatePlayer(oldState.getPlayerB(), latestGameState.getPlayerB(), latestGameState.getPlayerBMove())) {
					if (validateMoveNumber(oldState.getRoundNumber(), latestGameState.getRoundNumber())) {
						result = true;
					}
				}
			}
		}
		return result;
	}
	
	private static boolean validatePlayer(Player oldValue, Player newValue, int move) {
		boolean result = false;
		
		if (oldValue != null && newValue != null) {
			if (oldValue.getName().equals(newValue.getName())) {
				if (oldValue.getEnergy() == newValue.getEnergy() - move) {
					result = true;
				}
			}
		}
		return result;
	}
	
	private static boolean validateMoveNumber(int oldValue, int newValue) {
		return oldValue == newValue || oldValue+1 == newValue;
	}
	
	public static String createRandomString(int n) {
		return RandomStringUtils.randomAscii(n);
	}
	
	public static void debug(Object printme) {
		if (debug){
			System.out.println(printme.toString());
		}
		
	}
}
