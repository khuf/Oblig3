package no.uib.info233.v2017.khu010.oblig3;

import no.uib.info233.v2017.khu010.oblig3.game.GameState;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

/**
 * A utility class to perform various "cheat" checks
 * for an online game.
 * @author knu010
 * @version 0.0.2 (19.05.2017)
 */
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
	
	/**
	 * Validates the new game state. Performs checks to make sure
	 * that a player has not been substituted or the enemy player
	 * has made multiple moves in a row.
	 * @param oldState
	 * @param latestGameState
	 * @return true if the validation succeeds or false if it fails.
	 */
	public static boolean validateNewGameState(GameState oldState, GameState latestGameState) {
		boolean result = false;
		if (oldState != null && latestGameState != null) {
			if (validatePlayer(oldState.getPlayerA(), latestGameState.getPlayerA(), latestGameState.getPlayerAMove())) {
				if (validatePlayer(oldState.getPlayerB(), latestGameState.getPlayerB(), latestGameState.getPlayerBMove())) {
					if (validateRoundNumber(oldState.getRoundNumber(), latestGameState.getRoundNumber())) {
						result = true;
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Checks that a player has performed a valid move. That is, he has not
	 * used more energy than is available to him.
	 * @param oldValue Player from the previous game state
	 * @param newValue Player from the new game state.
	 * @param move the players latest move.
	 * @return true if the check succeeds or false if it fails.
	 */
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
	
	/**
	 * A check that makes sure that a round has not been skipped. This will fail
	 * if an enemy player has made multiple moves.
	 * @param oldValue the old round number
	 * @param newValue the latest round number
	 * @return true if the check succeeds or false if it fails.
	 */
	private static boolean validateRoundNumber(int oldValue, int newValue) {
		return oldValue == newValue || oldValue+1 == newValue;
	}
	
	/**
	 * Prints out the objects string representation.
	 * @param printme
	 */
	public static void debug(Object printme) {
		if (debug){
			System.out.println(printme.toString());
		}
		
	}
}
