package no.uib.info233.v2017.khu010.oblig4.interfaces;

import no.uib.info233.v2017.khu010.oblig4.game.GameState;

/**
 * An interface with methods used to
 * manager a game.
 * @author knu010
 * @version 0.0.1
 */
public interface GameManagerInterface {
	
	void loadGame(GameState state);
	
	void hostGame(String playerName);
	
	void saveGameState();
	
	void listOnlineGames();

}
