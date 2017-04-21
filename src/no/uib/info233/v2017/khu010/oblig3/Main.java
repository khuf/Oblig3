package no.uib.info233.v2017.khu010.oblig3;


/*
 * 	Entry point for game
 *  @author knu010 && xeq003
 * 	@version 0.3.8 (21.04.2017).
 */
public class Main {
	
	public static void main(String[] args) {
		GameMaster master = GameMaster.getGameMaster();
		master.setPlayers(new DefensivePlayer("defRobot", 3), new AggressivePlayer("agrRobot", -3));
		//master.setPlayers(new DefensivePlayer("defRobot", 3), new DefensivePlayer("defRobot2", -3));
		//master.setPlayers(new AggressivePlayer("agrRobot", 3), new AggressivePlayer("agrRobot2", -3));
		master.startGame();

	}

}
