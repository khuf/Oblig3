package no.uib.info233.v2017.khu010.oblig3;

public class Main {
	
	public static void main(String[] args) {
		
		GameMaster master = GameMaster.getGameMaster();
		
		AggressivePlayer robot1 = new AggressivePlayer("robot1");
		robot1.registerGameMaster(master);
		DefensivePlayer robot2 = new DefensivePlayer("robot2");
		robot2.registerGameMaster(master);
		master.setPlayers(robot1, robot2);
		master.startGame();

	}

}
