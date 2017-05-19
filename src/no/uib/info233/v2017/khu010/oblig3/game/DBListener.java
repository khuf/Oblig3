package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.sql.SQLManager;

/**
 * This class listens for changes in sqlManager
 * Starts game when opponent found
 * 
 * @author knu010 && xeq003
 * @version 0.0.2 (15.05.2017)
 */
public class DBListener implements Runnable {
	
	private MultiPlayerGame mpgame;
	private SQLManager server;
	private boolean isHost;
	
	public DBListener(SQLManager server, MultiPlayerGame mpgame, boolean isHost) {
		this.server = server;
		this.mpgame = mpgame;
		this.isHost = isHost;
	}

	/**
	 * This method runs in a loop until the game has finished. 
	 */
	@Override
	public void run() {
		boolean gameHasStarted = false;
		System.out.println("Checking database for changes...");
		GameState gamestate = mpgame.getGameState();
		//GUI.setGameState ?
		while (!gamestate.isFinnished() && server.getOpponent() != null) {
			try {
				if (isHost){
					//start game if it has not started yet
					if (!gameHasStarted) {
						gameHasStarted = server.startGame();
					}
					//check if opponent has sent his move and both moves are valid 
					if (server.hasOpponentMove()) {
						mpgame.evaluateTurn();
					}
				}
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
