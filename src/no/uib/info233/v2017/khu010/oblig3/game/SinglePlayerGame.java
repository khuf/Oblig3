package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.players.*;
import java.util.Random;

/**
 * This is the class representing a singel player game. 
 * @author knuhuf
 *
 */
public class SinglePlayerGame extends Game {
	
	/**
	 * Creates a singel player game against a random opponent.
	 * @param playerName
	 */
	public SinglePlayerGame(String playerName) {
		setPlayerA(new HumanPlayer(playerName, 3));
	}
	
	/**
	 * Runs the game in a loop. A game ends when either player is in a winning position
	 * or if both have run out of energy.
	 */
	@Override
	public void runGame() {
		while (!isFinnished()) {
			if (performMoves()) {
				System.out.println("lldl");
				evaluateTurn();
			}
		}
		System.out.println(this);
	}
	
	/**
	 * Sends a request to both player to come up with their next move by calling
	 * the method requestMoves which returns a boolean value when both players
	 * have made their move.
	 */
	@Override
	public boolean performMoves() {
		boolean result = false;
		System.out.println("from perform move");
		if (getGameState().requestMoves()) {
			result = true;
		}
		
		return result;
	}
	
	//THIS METHOD NEEDS TO BE CLEANED UP...
	@Override
	public void evaluateTurn() {
		Player p1 = getPlayerA();
		Player p2 = getPlayerB();
		if (getGameState().getPlayerAMove() > getGameState().getPlayerBMove()) {
			getGameState().currentPositionProperty().set(getGameState().getCurrentPosition() + 1);
		}
		else if (getGameState().getPlayerBMove() > getGameState().getPlayerAMove()) {
			getGameState().currentPositionProperty().set(getGameState().getCurrentPosition() - 1);
		}
		p2.useEnergy(getGameState().getPlayerBMove());
		p1.useEnergy(getGameState().getPlayerAMove());
		System.out.println(p1.getName() + " made the move " + getGameState().getPlayerAMove() + " with " + p1.getEnergy());
		System.out.println(p2.getName() + " made the move " + getGameState().getPlayerBMove() + " with " + p2.getEnergy());
		
	}

}
