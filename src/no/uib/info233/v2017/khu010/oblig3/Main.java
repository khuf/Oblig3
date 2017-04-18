package no.uib.info233.v2017.khu010.oblig3;

import khu010.AggressivePlayer;
import khu010.GameMaster;
import khu010.HumanPlayer;

public class Main {
	public static void main(String args[]) {
		GameMaster gm = GameMaster.getGameMaster();
		gm.setPlayers(new HumanPlayer("John", 3), new AggressivePlayer("Bob", -3));
		
		System.out.println(gm.toString());
		gm.startGame();
	}
}

