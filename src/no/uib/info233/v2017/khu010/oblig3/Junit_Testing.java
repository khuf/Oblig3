package no.uib.info233.v2017.khu010.oblig3;

import org.junit.*;
import static org.junit.Assert.*;

public class Junit_Testing {
	
	private GameMaster master;
	private DefensivePlayer defRobot;
	private AggressivePlayer agrRobot;
	
	/**
	 * Prepares a game for testing.
	 * A game master with two players are
	 * created.
	 */
	@Before
	public void setUp() {
		master = GameMaster.getGameMaster();
		defRobot = new DefensivePlayer("defRobot", 3);
		agrRobot = new AggressivePlayer("agrRobot", -3);
		master.setPlayers(defRobot, agrRobot);
		master.startGame();
	}
	
	/**
	 * Checks if the score can be successfully synchronized with the 
	 * database.
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
		defRobot.useEnergy(100);
		agrRobot.useEnergy(100);
		assertEquals(0, defRobot.makeNextMove(0, 0, 20));
		assertEquals(0, agrRobot.makeNextMove(0, 0, 20));
	}

}
