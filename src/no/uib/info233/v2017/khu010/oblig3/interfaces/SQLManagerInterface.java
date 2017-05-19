package no.uib.info233.v2017.khu010.oblig3.interfaces;

import java.util.HashMap;
import java.util.Map;

import no.uib.info233.v2017.khu010.oblig3.game.GameState;
import no.uib.info233.v2017.khu010.oblig3.game.MultiPlayerGame;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

public interface SQLManagerInterface {
	
	//create random player id 10 characters long
	String createRandomPlayerID();
	
	//returns gamestate of game_in_progress
	GameState getGameState();
	
	//sends move to selected mpgame
	boolean sendMove(int move);
	
	//-------------hosting-------------
	
	//requires a gamestate where player a is set
	//saves gamestate to local variable
	//creates a new game in open_games
	//starts a new dblistener for getOpponent && getOpponentMove
	boolean hostOnlineGame();
	
	//run every 2 seconds in another thread
	//checks if your hosted game in open_games has an opponent
	//returns opponents name, else returns null
	String getOpponent();
	
	//checks if opponent has send his move
	boolean hasOpponentMove();
	
	//uses a gamestate to create a new game
	//moves game from open_games to games_in_progress
	boolean startGame();
	
	//creates a new instance of the game in games_in_progress
	//uses the updated gamestate
	void newRound();
	
	//ends an online game session by removing the game from games_in_progress
	//also calls saveGame
	void endGame();
	
	//saves the results to saved_games
	void saveGame();
	
	//-------------joining-------------
	
	//returns a hashmap containing player names and ids who hosts open games;
	Map<String, String> findOpenGames();
	
	//joins a game from open_games
	//sets our players name as playername
	//returns gameID
	String joinOnlineGame(String playername, String hostID);

}
