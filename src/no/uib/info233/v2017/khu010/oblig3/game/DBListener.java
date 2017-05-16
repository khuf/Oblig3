package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.sql.SQLManager;

/**
 * This class listens for changes in the database and
 * updates the gamestate accordingly. 
 * @author knuhuf 
 *
 */
public class DBListener implements Runnable {
	
	private GameState gameState;
	//private SQLManager manager = SQLManager();
	
	public DBListener(GameState state) {
		gameState = state;
	}

	/**
	 * This method runs in a loop untill the game has finished. 
	 */
	@Override
	public void run() {
		while (!gameState.isFinnished()) {
		try {
			System.out.println("Checking database for changes...");
			
			if (gameState.getPlayerBMove() != SQLManager.getGameState().getPlayerBMove()) { 
				//Update move...
			}
			//Alternative
			//gameState.setPlayerB(SQLManager.getGameState().getPlayerBMove());
			
			//if gameHasStarted ()
				//getOpponentMove()
			//else
				// if (getOpponent() != null) {
					//GameHasStarted = startGame();
			
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}
