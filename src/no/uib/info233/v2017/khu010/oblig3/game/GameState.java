package no.uib.info233.v2017.khu010.oblig3.game;

import java.util.HashMap;

import no.uib.info233.v2017.khu010.oblig3.Utility;
import no.uib.info233.v2017.khu010.oblig3.interfaces.PlayerControllerInterface;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

import java.util.Map;

import javafx.beans.property.SimpleIntegerProperty;

public class GameState implements PlayerControllerInterface {
	
	private Player playerA;
	private Player playerB;
	private SimpleIntegerProperty playerAMove = new SimpleIntegerProperty(-1);
	private SimpleIntegerProperty playerBMove = new SimpleIntegerProperty(-1);
	private SimpleIntegerProperty currentPosition = new SimpleIntegerProperty(0);
	private Map<Integer, Reward> scores;
	
	public GameState() {
		createScoreboard();
	}
	public GameState(Player firstPlayer, Player secondPlayer) {
		playerA = firstPlayer;
		playerB = secondPlayer;
		createScoreboard();
	}
	
	public void createScoreboard() {
		scores = new HashMap<>();
		
		scores.put(0, new Reward(0.5F, 0.5F));
		scores.put(1, new Reward(0.75F, 0.25F));
		scores.put(2, new Reward(1.0F, 0.0F));
		scores.put(3, new Reward(2.0F, -1.0F));
		scores.put(-1, new Reward(0.25F, 0.75F));
		scores.put(-2, new Reward(0.0F, 1.0F));
		scores.put(-3, new Reward(-1.0F, 2.0F));
	}
	
	public void setPlayerA(Player playerA) {
		this.playerA = playerA;
		this.playerA.registerGameMaster(this);
	}
	
	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
		this.playerB.registerGameMaster(this);
	}
	
	public float getPlayerAReward() {
		return scores.get(currentPosition).getPlayerAScore();
	}
	
	public float getPlayerBReward() {
		return scores.get(currentPosition).getPlayerBScore();
	}
	
	public Player getPlayerA() {
		return playerA;
	}
	
	public Player getPlayerB() {
		return playerB;
	}
	
	public boolean requestMoves() {
		return true;
	}
	
	/**
	 * @return the latest move from Player A.
	 */
	public int getPlayerAMove() {
		return playerAMove.get();
	}
	
	/**
	 * @return the latest move from Player B
	 */
	public int getPlayerBMove() {
		return playerBMove.get();
	}
	
	/**
	 * @return the current round.
	 */
	public int getCurrentPosition() {
		return currentPosition.get();
	}
	
	public SimpleIntegerProperty currentPositionProperty() {
		return currentPosition;
	}
	
	public SimpleIntegerProperty playerAMoveProperty() {
		return playerAMove;
	}
	
	public SimpleIntegerProperty playerBMoveProperty() {
		return playerBMove;
	}
	
	private void setPlayerAMove(int move) {
		playerAMove.set(0);
		if (Utility.isValidMove(playerA, move)) {
			playerAMove.set(move);
		}
	}
	
	private void setPlayerBMove(int move) {
		playerBMove.set(0);
		if (Utility.isValidMove(playerB, move)) {
			playerBMove.set(move);
		}
	}
	
	public Player evaluateMoves() {
		Player result = null;
		System.out.println("Player A move: " + playerAMove);
		System.out.println("Player B move: " + playerBMove);
		if (playerAMove.get() > playerBMove.get()) {
			System.out.println(playerAMove + " " + playerBMove);
			currentPosition.set(currentPosition.get() + 1);;
			result = playerA;
		}
		else if (playerBMove.get() > playerAMove.get()) {
			currentPosition.set(currentPosition.get() -1 );;
			result = playerB;
		}
		return result;
	}
	
	@Override
	public void listenToPlayerMove(Player player, int move) {
		if (player != null) {
			if (player.equals(playerA)) {
				setPlayerAMove(move);
			}
			else {
				setPlayerBMove(move);
			}
		}
	}
}
