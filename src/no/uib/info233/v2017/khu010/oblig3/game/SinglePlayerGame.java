package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.players.*;
import java.util.Random;

/**
 * A class representing a single player game
 * @author khu010
 * @version 0.0.1 (14.05.2017)
 *
 */
public class SinglePlayerGame extends Game {
	
	/**
	 * Creates a single player game against a random opponent.
	 * @param playerName
	 */
	public SinglePlayerGame(String playerName) {
		setPlayer(new HumanPlayer(playerName, 3));
		setRandomOpponent();
	}
	
	/**
	 * Sets the human player
	 * @param player
	 */
	public void setPlayer(Player player) {
		getGameState().setPlayerA(player);
	}
	
	/**
	 * Assigns a random bot to the game.
	 */
	public void setRandomOpponent() {
		Random rng = new Random();
		String name = "Bot" + rng.nextInt(30);
		if (rng.nextBoolean()) {
			getGameState().setPlayerB(new DefensivePlayer(name, -3));
		}
		getGameState().setPlayerB(new AggressivePlayer(name, -3));
	}

	/**
	 * Runs the game in a loop. A game ends when either player is in a winning position
	 * or if both have run out of energy.
	 */
	@Override
	public void runGame() {
		while (!getGameState().isFinnished()) {
			if (performMoves()) {
				evaluateTurn();
			}
		}
		System.out.println(this);
	}
	
	/**
	 * Sends a request to both player to come up with their next move by calling
	 * the method requestMoves which returns a boolean value when both players
	 * have made their move.
	 * @return true if both players came up with valid moves, otherwise, false.
	 */
	@Override
	public boolean performMoves() {
		/*boolean validMoves = false;
		
		Player p1 = getGameState().getPlayerA();
		Player p2 = getGameState().getPlayerB();
		int moveB = p2.makeNextMove(getGameState().getCurrentPosition(), p1.getEnergy());
		
		if (getGameState().setPlayerAMove(moveA) && getGameState().setPlayerBMove(moveB) ); {
			validMoves = true;
		}
		
		return validMoves;*/
	}
	
	//THIS METHOD NEEDS TO BE CLEANED UP...
	@Override
	public void evaluateTurn() {
		Player p1 = getGameState().getPlayerA();
		Player p2 = getGameState().getPlayerB();
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
