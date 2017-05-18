package no.uib.info233.v2017.khu010.oblig3.interfaces;


import java.util.Map;

import no.uib.info233.v2017.khu010.oblig3.game.GameState;
import no.uib.info233.v2017.khu010.oblig3.game.MultiPlayerGame;

public interface SQLManagerInterface {
	boolean createOpenGame(String playerName, String playerId);
	
	boolean waitForOpponent(String playerId);
	
	String createGameInProgress(String playerId);
	
	void removeOpenGame(String playerId);
	
	GameState getGameInProgress(String gameId);
	
	boolean isBothMovesMade(String gameId);
	
	void setPlayerAMove(String gameId, int move);
	
	void setPlayerBMove(String gameId, int move);
}
