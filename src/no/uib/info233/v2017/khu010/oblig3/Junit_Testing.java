package no.uib.info233.v2017.khu010.oblig3;

import static org.junit.Assert.*;

import org.junit.Test;

public class Junit_Testing {
	
	GameMaster master;
	
	@Test
	public void testScoreCalculation(){
		master = GameMaster.getGameMaster();
		master.setPlayers(new DefensivePlayer("defRobot"), new AggressivePlayer("agrRobot"));
		master.startGame();
		assertTrue(master.updateRanking());
	}

}
