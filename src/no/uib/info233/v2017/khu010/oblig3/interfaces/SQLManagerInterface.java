package no.uib.info233.v2017.khu010.oblig3.interfaces;

import java.util.HashMap;

import no.uib.info233.v2017.khu010.oblig3.game.GameState;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

public interface SQLManagerInterface {
	
	//create random player id 10 characters long
	String createRandomPlayerID();
	
	//-------------hosting-------------
	
	//requires a gamestate where player a is set
	//saves gamestate to local variable
	//creates a new game in open_games
	//starts a new dblistener for getOpponent
	boolean hostOnlineGame(GameState gamestate);
	
	//run every 2 seconds in another thread
	//checks if your hosted game in open_games has an opponent
	//returns opponents name, else returns null
	String getOpponent();
	
	//uses a gamestate to create a new game
	//moves game from open_games to games_in_progress
	//returns game_id
	String startGame();
	
	//run every 2 seconds in another thread
	//checks if opponent has sent his move to database
	//returns -1 if no move is set yet
	int getOpponentMove();
	
	//creates a new instance of the game in games_in_progress
	void newRound();
	
	//ends an online game session by removing the game from games_in_progress
	//also calls saveGame
	void endGame();
	
	//saves the results to saved_games
	void saveGame(GameState gamestate);
	
	//-------------joining-------------
	
	//returns a hashmap containing player names and ids who hosts open games;
	HashMap<String, String> findOpenGames();
	
	//joins a game from open_games
	//sets our players name as playername
	void joinOnlineGame(String playername, String hostID);
	
	//sends your move to the current game you´re playing
	void sendMove();
}
