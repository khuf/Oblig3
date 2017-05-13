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
	//private SQLManager manager = SQLManager.getConnection();
	
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}
