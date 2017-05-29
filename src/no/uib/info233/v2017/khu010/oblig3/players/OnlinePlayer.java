package no.uib.info233.v2017.khu010.oblig3.players;

public class OnlinePlayer extends Player {
	
	private String playerId;
	
	public OnlinePlayer(String name, String playerId, int goal) {
		super(name, goal);
		setPlayerId(playerId);
	}

	@Override
	public int makeNextMove(int currentPosition, int opponentEnergy) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int makeNextMove(int move) {
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return getName() + " - " + playerId;
	}
	
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	
	public String getPlayerId() {
		return playerId;
	}
}
