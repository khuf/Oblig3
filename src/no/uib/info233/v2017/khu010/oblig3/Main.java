package no.uib.info233.v2017.khu010.oblig3;

public class Main {
	public static void main(String args[]) {
		GameMaster gm = GameMaster.getGameMaster();
		gm.setPlayers(new AggressivePlayer("John", 3), new AggressivePlayer("Bob", -3));
		
		System.out.println(gm.toString());
		gm.startGame();
	}
}

