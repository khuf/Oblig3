package no.uib.info233.v2017.khu010.oblig3.game;

/**
 * This is the class that the client use to retrieve the points for
 * each player after the game has finished. 
 * @author knuhuf
 *
 */
public class Reward {
	
	float playerAScore;
	float playerBScore;
	
	public Reward(float playerAScore, float playerBScore) {
		this.playerAScore = playerAScore;
		this.playerBScore = playerBScore;
	}
	
	public float getPlayerAScore() {
		return playerAScore;
	}
	
	public float getPlayerBScore() {
		return playerBScore;
	}

}
