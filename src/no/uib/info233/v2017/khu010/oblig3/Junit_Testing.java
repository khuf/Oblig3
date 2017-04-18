package no.uib.info233.v2017.khu010.oblig3;

import org.junit.*;
import static org.junit.Assert.*;

public class Junit_Testing {
	
	GameMaster master;
	DefensivePlayer defRobot;
	AggressivePlayer agrRobot;
	
	/**
	 * Prepares a game for testing.
	 * A game master with two players are
	 * created.
	 */
	@Before
	public void setUp() {
		master = GameMaster.getGameMaster();
		defRobot = new DefensivePlayer("defRobot");
		agrRobot = new AggressivePlayer("agrRobot");
		master.setPlayers(defRobot, agrRobot);
		master.startGame();
	}
	
	/**
	 * 
	 */
	@Test
	public void testScoreCalculation(){
		assertTrue(master.updateRanking());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void registerNullGameMaster() {
		defRobot.registerGameMaster(null);
	}
	
	public void NoDamageWhenNoEnergy(){
		defRobot.setEnergy(100);
		agrRobot.setEnergy(100);
		assertEquals(0, defRobot.makeNextMove(0, 0, 20));
		assertEquals(0, agrRobot.makeNextMove(0, 0, 20));
	}

}
