package no.uib.info233.v2017.khu010.oblig3;

/**
 * A singleton GameMaster class.
 * @author knu010
 * @version 0.0.1 (06.04.2017).
 */
public class GameMaster {
	
	//Static ensures it belongs to the class.
	private static GameMaster instance = null;
	private static Player p1;
	private static Player p2;
	private static double currentPosition = 1.00;
	
	private GameMaster() {
	}
	
	/*
	 * Returns an instance of this class
	 */
	public static GameMaster getInstance() {
		if (instance == null) {
			instance = new GameMaster();
		}
		return instance;
	}
	
	public void setPlayers(Player player1, Player player2) {
		
	}
	
	public void startGame() {
		p1.makeNextMove(currentPosition, yourEnergy, opponentEnergy))
	}
	
	public void listenToPlayerMove(Player player, int move) {
		
	}
	
	public void evaluateTurn() {
		
	}
	
	public void updateRanking() {
		
	}
	
	

}
