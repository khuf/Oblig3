package no.uib.info233.v2017.khu010.oblig3.game;

import java.util.Map;

import no.uib.info233.v2017.khu010.oblig3.interfaces.GameManagerInterface;
import no.uib.info233.v2017.khu010.oblig3.interfaces.PlayerControllerInterface;
import no.uib.info233.v2017.khu010.oblig3.players.Player;
import no.uib.info233.v2017.khu010.oblig3.sql.SQLManager;

/**
 * A singleton GameMaster class.
 * @author knu010 && xeq003
 * @version 0.3.8 (21.04.2017).
 */
public class GameMaster implements GameManagerInterface {
	
	private SQLManager server = SQLManager.getConnection();

	private Game game;
	
	private Map<Integer, Game> gameList;
	
	public GameMaster() {
		game = new Game();
	}
	
	/**
	 * Starts the game by sending a request to both players to come up 
	 * with their next move.
	 */
	public void startGame() {
		while (!game.isFinnished()) {
			game.performMoves();
		}
		System.out.println(game.isFinnished());
		//Update ranking....
	}

	@Override
	public void loadGame(GameState state) throws IllegalArgumentException {
		if (state == null) {
			throw new IllegalArgumentException("Game cannot be loaded. Invalid game state");
		}
		game.setGameState(state);
	}

	@Override
	public void hostGame(GameState state) {
		
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
	
	public void registerPlayers(Player playerA, Player playerB) {
		game.setPlayers(playerA, playerB);
	}
	
}