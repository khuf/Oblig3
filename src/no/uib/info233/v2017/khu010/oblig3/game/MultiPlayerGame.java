package no.uib.info233.v2017.khu010.oblig3.game;

import org.apache.commons.lang3.RandomStringUtils;

import no.uib.info233.v2017.khu010.oblig3.players.*;
import no.uib.info233.v2017.khu010.oblig3.sql.SQLManager;

public class MultiPlayerGame extends Game {
	

	private SQLManager manager = new SQLManager();
	private boolean bothMovesMade = false;
	
	public MultiPlayerGame(String playerName, int goal, boolean isHost) {
		if (isHost == true) {
			setPlayerA(new HumanPlayer(playerName, 3));
			setPlayerAId(RandomStringUtils.randomAscii(10));
			setIsHost(true);
		}
		else {
			setPlayerB(new HumanPlayer(playerName, -3));
			setPlayerBId(RandomStringUtils.randomAscii(10));
		}
	}
	
	public void setPlayerAId(String playerId) {
		getGameState().setPlayerAId(playerId);
	}
	
	public void setPlayerBId(String playerId) {
		getGameState().setplayerBId(playerId);
	}
	
	public String getPlayerAId() {
		return getGameState().getPlayerAId();
	}
	
	public String getPlayerBId() {
		return getGameState().getPlayerBId();
	}
	
	public String getGameId() {
		return getGameState().getGameID();
	}

	@Override
	public void runGame(){
		while(!isFinnished()) {
			setGameState(manager.getGameInProgress(getGameState().getGameID()));
			setIsBothMovesMade(manager.isBothMovesMade(getGameId()));

			if (getIsHost()) {
				if (bothMovesMade) {
					System.out.println("Both moves were made....");
				}
			}
			else {
				if (getGameState().getPlayerBMove() == -1) {
					System.out.println("You should perform a move...");
				}
			}
			try {
				Thread.sleep(2000);
			}
			catch (InterruptedException ex) {
				System.out.println(ex.toString());
			}
		}
	}

	@Override
	public boolean performMoves() {
		boolean result = false;
		System.out.println("from perform move");
		if (getGameState().requestMoves()) {
			result = true;
		}
		
		return result;
	}

	@Override
	public void evaluateTurn() {
		// TODO Auto-generated method stub
		
	}
	
	public void setIsBothMovesMade(boolean result) {
		bothMovesMade = result;
	}
}
