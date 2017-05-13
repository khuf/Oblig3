package no.uib.info233.v2017.khu010.oblig3.interfaces;

import java.util.HashMap;

import no.uib.info233.v2017.khu010.oblig3.game.GameState;

public interface SQLManagerInterface {
	
	//establishes a connection to the database. only needs to be run once
	void connect();
	
	//create random player id 10 characters long
	int createRandomPlayerID();
	
	//-------------hosting-------------
	
	//creates a new game in open_games
	void hostOnlineGame();
	
	//checks if your hosted game in open_games has an opponent
	boolean hasOpponent();
	
	//checks every 2 seconds if opponent has sent his move to database
	//returns -1 if no move is set yet
	int getOpponentMove();
	
	//updates round result to games_in_progress
	void updateRoundResult(GameState newState);
	
	//increased the move_number
	void newRound();
	
	//saves the results to saved_gaves
	void saveGame();
	
	//ends an online game session by removing the game from games_in_progress
	void endGame();
	
	//-------------joining-------------
	
	//returns a hashmap containing player names and ids who hosts open games;
	HashMap<String, String> findOpenGames();
	
	//joins a game from open_games
	//returns the game id
	String joinOnlineGame(String opponentID);
	
	//sends your move to the current game you´re playing
	void sendMove();
}
