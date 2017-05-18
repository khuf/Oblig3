package no.uib.info233.v2017.khu010.oblig3.interfaces;

import java.util.Map;

import no.uib.info233.v2017.khu010.oblig3.game.GameState;

public interface GameManagerInterface {
	
	void loadGame(GameState state);
	
	void hostGame(String playerName);
	
	void saveGameState();
	
	void joinOnlineGame(String playerName, String opponentId);
	
	Map<String, String> listOnlineGames();
}
