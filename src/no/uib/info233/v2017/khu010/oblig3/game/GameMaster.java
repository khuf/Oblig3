package no.uib.info233.v2017.khu010.oblig3.game;

import java.util.Map;


import no.uib.info233.v2017.khu010.oblig3.interfaces.GameManagerInterface;
import no.uib.info233.v2017.khu010.oblig3.interfaces.PlayerControllerInterface;
import no.uib.info233.v2017.khu010.oblig3.players.Player;
import no.uib.info233.v2017.khu010.oblig3.sql.SQLManager;
import no.uib.info233.v2017.khu010.oblig3.game.MultiPlayerGame;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * This is the class that the GUI will interact with. The GUI will interact with
 * the GameState class through this class (getGameState()).
 * A singleton GameMaster class.
 * @author knu010 && xeq003
 * @version 0.3.8 (21.04.2017).
 */
public class GameMaster implements GameManagerInterface {
	
	//private SQLManager server = new SQLManager();

	private Game game;
	
	private Map<Integer, String> gameList;
	
	public GameMaster() {
		game = new SinglePlayerGame("Bob");
	}
	
	/**
	 * Starts the game by sending a request to both players to come up 
	 * with their next move.
	 */
	public void startSinglePlayer() {
		game = new SinglePlayerGame("Bob");
		game.runGame();
	}
	
	/**
	 * Starts a multiplayer game...
	 */
	public void startMultiPlayer() {
		
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
		//findOpenGames();
	}
	
	private void updateRanking() {
		
	}
	
	public void registerPlayers(Player playerA, Player playerB) {
		//.setPlayers(playerA, playerB);
	}	
	
	public Game getGame() {
		return game;
	}
}