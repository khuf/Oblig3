package no.uib.info233.v2017.khu010.oblig3.interfaces;

/**
 * An interface supporting operations
 * that players should be able to perform.
 * @author knu010
 * @version 0.0.1
 */
public interface PlayerControllerInterface {
	
	/**
	 * Used by players to submit their move to the datbaase.
	 * @param move
	 */
	void sendMove(int move);
}
