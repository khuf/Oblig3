package no.uib.info233.v2017.khu010.oblig3;

import java.util.HashMap;
import no.uib.info233.v2017.khu010.oblig3.interfaces.PlayerControllerInterface;
import no.uib.info233.v2017.khu010.oblig3.players.Player;

import java.util.Map;

public class GameState implements PlayerControllerInterface {
	
	private Player playerA;
	private Player playerB;
	private int playerAMove, playerBMove = -1;
	private int currentPosition;
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
		playerA.makeNextMove(currentPosition, playerB.getEnergy());
		playerB.makeNextMove(currentPosition, playerA.getEnergy());

		return true;
	}
	
	public int getCurrentPosition() {
		return currentPosition;
	}
	
	private void setPlayerAMove(int move) {
		playerAMove = 0;
		if (Utility.isValidMove(playerA, move)) {
			playerAMove = move;
		}
	}
	
	private void setPlayerBMove(int move) {
		playerBMove = 0;
		if (Utility.isValidMove(playerB, move)) {
			playerBMove = move;
		}
	}
	
	public Player evaluateMoves() {
		Player result = null;
		System.out.println("Player A move: " + playerAMove);
		System.out.println("Player B move: " + playerBMove);
		if (playerAMove > playerBMove) {
			System.out.println(playerAMove + " " + playerBMove);
			currentPosition++;
			result = playerA;
		}
		else if (playerBMove > playerAMove) {
			currentPosition--;
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
