package no.uib.info233.v2017.khu010.oblig3.interfaces;

import java.util.HashMap;

import no.uib.info233.v2017.khu010.oblig3.game.GameState;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

public interface SQLManagerInterface {
	
	//create random player id 10 characters long
	String createRandomPlayerID();
	
	//-------------hosting-------------
	
	//creates a new game in open_games
	//returns hostplayer´s random player id 
	String hostOnlineGame(Player hostplayer);
	
	//run every 2 seconds in another thread
	//checks if your hosted game in open_games has an opponent
	boolean hasOpponent();
	
	//run every 2 seconds in another thread
	//checks if opponent has sent his move to database
	//returns -1 if no move is set yet
	int getOpponentMove();
	
	//creates a new instance of the game in games_in_progress
	void newRound();
	
	//saves the results to saved_games
	void saveGame(GameState gamestate);
	
	//ends an online game session by removing the game from games_in_progress
	void endGame();
	
	//-------------joining-------------
	
	//returns a hashmap containing player names and ids who hosts open games;
	HashMap<String, String> findOpenGames();
	
	//joins a game from open_games
	//returns the game id
	String joinOnlineGame(String playername, String opponentID);
	
	//sends your move to the current game you´re playing
	void sendMove();
}
