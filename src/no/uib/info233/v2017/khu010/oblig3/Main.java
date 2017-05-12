package no.uib.info233.v2017.khu010.oblig3;

import no.uib.info233.v2017.khu010.oblig3.players.AggressivePlayer;
import no.uib.info233.v2017.khu010.oblig3.game.GameState;
import no.uib.info233.v2017.khu010.oblig3.gui.*;
import no.uib.info233.v2017.khu010.oblig3.players.DefensivePlayer;
import no.uib.info233.v2017.khu010.oblig3.players.*;

/*
 * 	Entry point for game
 *  @author knu010 && xeq003
 * 	@version 0.3.8 (21.04.2017).
 */
public class Main {
	
	public static void main(String[] args) {
		/*GameMaster master = new GameMaster();
		//Player player = new HumanPlayer("John", 3);
		//new DefensivePlayer("defRobot", 3)
		master.registerPlayers(new DefensivePlayer("defRobot", 3), new AggressivePlayer("agrRobot", -3));
		//master.setPlayers(new DefensivePlayer("defRobot", 3), new DefensivePlayer("defRobot2", -3));
		//master.setPlayers(new AggressivePlayer("agrRobot", 3), new AggressivePlayer("agrRobot2", -3));
		master.startGame();

		Utility.debug("hei");*/
		GameState state = new GameState();
		Gladiators game = new Gladiators(state);
	}

}
