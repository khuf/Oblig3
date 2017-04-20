package no.uib.info233.v2017.khu010.oblig3;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("nextMove");
		GameMaster master = GameMaster.getGameMaster();
		master.setPlayers(new DefensivePlayer("defRobot", 3), new AggressivePlayer("agrRobot", -3));
		master.startGame();

	}

}
