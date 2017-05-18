package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.sql.SQLManager;

/**
 * This class listens for changes in the database and
 * updates the gamestate accordingly. 
 * @author knuhuf 
 *
 */
public class DBListener implements Runnable {
	
	private MultiPlayerGame game;
	private SQLManager manager = new SQLManager();
	
	public DBListener(MultiPlayerGame game) {
		this.game = game;
	}

	/**
	 * This method runs in a loop untill the game has finished. 
	 */
	@Override
	public void run() {
		while (!game.isFinnished()) {
		try {
			System.out.println("Updating gamestate...");
			game.setGameState(manager.getGameInProgress(game.getGameState().getGameID()));
			game.setIsBothMovesMade(manager.isBothMovesMade(game.getGameId()));
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}
