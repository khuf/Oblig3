package no.uib.info233.v2017.khu010.oblig3.game;

import no.uib.info233.v2017.khu010.oblig3.players.*;

/**
 * This is the class representing a singel player game. 
 * @author knuhuf
 *
 */
public class SinglePlayerGame extends Game {
	
	//Tempoary constructor...
	public SinglePlayerGame() {
		setPlayers(new AggressivePlayer("John", 3), new DefensivePlayer("Bob", -3));
	}

	@Override
	public boolean setPlayers(Player playerA, Player playerB) {
		boolean result = false;
		getGameState().setPlayerA(playerA);
		getGameState().setPlayerB(playerB);
		return result;
	}

	@Override
	public void runGame() {
		while (!getGameState().isFinnished()) {
			if (performMoves()) {
				evaluateTurn();
			}
		}
		System.out.println(getGameState().getCurrentPosition());
		System.out.println("Game was finnished");
		
	}

	@Override
	public boolean performMoves() {
		boolean result = false;
		
		if (getGameState().requestMoves()) {
			evaluateTurn();
		}
		
		return result;
	}
	
	//THIS METHOD NEEDS TO BE CLEANED UP...
	@Override
	public void evaluateTurn() {
		Player p1 = getGameState().getPlayerA();
		Player p2 = getGameState().getPlayerB();
		if (getGameState().getPlayerAMove() > getGameState().getPlayerBMove()) {
			getGameState().currentPositionProperty().set(getGameState().getCurrentPosition() + 1);
			p1.useEnergy(getGameState().getPlayerAMove());
		}
		else if (getGameState().getPlayerBMove() > getGameState().getPlayerAMove()) {
			p2.useEnergy(getGameState().getPlayerBMove());
			getGameState().currentPositionProperty().set(getGameState().getCurrentPosition() - 1);
		}
		System.out.println(p1.getName() + " made the move " + getGameState().getPlayerAMove() + " with " + p1.getEnergy());
		System.out.println(p2.getName() + " made the move " + getGameState().getPlayerBMove() + " with " + p2.getEnergy());
		
	}

}
