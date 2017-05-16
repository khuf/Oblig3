package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.sql.SQLManager;

/**
 * This class listens for changes in sqlManager
 * Starts game when opponent found
 * 
 * @author knuhuf 
 *
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
		boolean gameHasStarted;
		if (isHost){
			gameHasStarted = false;
		} else {
			gameHasStarted = true;
		}
		
		while (!mpgame.getGameState().isFinnished()) {
			try {
				System.out.println("Checking database for changes...");
				
				if (isHost) {
					if (gameHasStarted && server.hasOpponentMove()){
						if (mpgame.performMoves()) {
							mpgame.evaluateTurn();
						}
					} else if (!gameHasStarted){
						if (server.getOpponent() != null) {
							gameHasStarted = server.startGame();
						}
					}
				} else {
					String joinedGameID = 
					mpgame.setGameState(server.getGameState());
				}
				
				
				
				if (gameHasStarted) {
					if (isHost){
						if (server.hasOpponentMove()){
							
						}
					} else {
						
					}
					
				} else if (server.getOpponent() != null) {
					GameHasStarted = server.startGame();
				} else {
					System.out.println("waiting for opponent");
				}
				
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
