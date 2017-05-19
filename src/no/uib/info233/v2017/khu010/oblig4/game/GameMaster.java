package no.uib.info233.v2017.khu010.oblig4.game;

import java.util.Map;

import no.uib.info233.v2017.khu010.oblig4.game.MultiPlayerGame;
import no.uib.info233.v2017.khu010.oblig4.interfaces.GameManagerInterface;

/**
 * This is the class that the GUI will interact with. The GUI will interact with
 * the GameState class through this class (getGameState()).
 * @author knu010 && xeq003
 * @version 0.0.3 (15.05.2017).
 */
public class GameMaster implements GameManagerInterface {
	
	//private SQLManager server = new SQLManager();

	private SinglePlayerGame spGame;
	
	private MultiPlayerGame mpGame;
	
	private Map<String, String> gameList;
	
	/**
	 * Creates an instance of a single player game
	 */
	public GameMaster() {
		spGame = new SinglePlayerGame("Bob");
	}

	@Override
	public void loadGame(GameState state) throws IllegalArgumentException {
		if (state == null) {
			throw new IllegalArgumentException("Game cannot be loaded. Invalid game state");
		}
		//Set gamestate to game
	}

	@Override
	public void hostGame(String playerName) {
		//multiPlayer = new MultiPlayerGame(playerName, 3);
		//multiPlayer.setPlayerAId(RandomStringUtils.random(10));
		
		
	}

	@Override
	public void saveGameState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listOnlineGames() {
		 
	}
	
	private void updateRanking() {
		
	}
	
	/**
	 * @return an instance of a single player game
	 */
	public SinglePlayerGame getSinglePlayerGame() {
		return spGame;
	}
	
	/**
	 * @return an instance of a game state
	 */
	public GameState getSinglePlayerGameState() {
		return spGame.getGameState();
	}
	
	/**
	 * @return an instance of a game state
	 */
	public GameState getMultiPlayerGameState() {
		return mpGame.getGameState();
	}
	
	/**
	 * @return a map of available online games as a map with
	 * player id as key and player name as value.
	 */
	public Map<String, String> getGameList() {
		return gameList;
	}
}