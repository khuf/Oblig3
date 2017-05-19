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
	
	public void performMove(int move) {
		GameState state = getGameState();
		state.setPlayerBMove(state.getPlayerB().makeNextMove(state.currentPositionProperty().get(), state.getPlayerA().getEnergy()));
		state.setPlayerAMove(move);
		
		if (state.getMovesMade() == 2) {
			evaluateTurn();
		}
	}
	
	/**
	 * Evaluates the players moves and moves the currentPosition in the direction
	 * of the player that won the round.
	 */
	@Override
	public void evaluateTurn() {
		GameState state = getGameState();
		Player p1 = getGameState().getPlayerA();
		Player p2 = getGameState().getPlayerB();
		
		if (state.getPlayerAMove() > state.getPlayerBMove()) {
			state.incrementCurrentPositionByOne();
		}
		else if (state.getPlayerAMove() < state.getPlayerBMove()) {
			state.decrementCurrentPositionbyOne();
		}

		p2.useEnergy(getGameState().getPlayerBMove());
		p1.useEnergy(getGameState().getPlayerAMove());
		state.resetMovesCounter();
	}
}
