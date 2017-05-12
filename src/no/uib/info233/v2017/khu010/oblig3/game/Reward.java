package no.uib.info233.v2017.khu010.oblig3.game;

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
