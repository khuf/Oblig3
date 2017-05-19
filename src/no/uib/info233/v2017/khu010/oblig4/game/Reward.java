package no.uib.info233.v2017.khu010.oblig4.game;

/**
 * This is the class that the client use to retrieve the points for
 * each player after the game has finished. 
 * @author khu010
 * @version 0.0.1 (12.05.2017)
 */
public class Reward {
	
	float playerAScore;
	float playerBScore;
	
	/**
	 * Creates a reward with both players score.
	 * @param playerAScore
	 * @param playerBScore
	 */
	public Reward(float playerAScore, float playerBScore) {
		this.playerAScore = playerAScore;
		this.playerBScore = playerBScore;
	}
	
	/**
	 * @return score of player A.
	 */
	public float getPlayerAScore() {
		return playerAScore;
	}
	
	/**
	 * @return score of player B.
	 */
	public float getPlayerBScore() {
		return playerBScore;
	}
}
