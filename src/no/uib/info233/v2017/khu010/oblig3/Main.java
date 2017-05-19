package no.uib.info233.v2017.khu010.oblig3;

import no.uib.info233.v2017.khu010.oblig3.players.AggressivePlayer;
import no.uib.info233.v2017.khu010.oblig3.game.*;
import no.uib.info233.v2017.khu010.oblig3.gui.*;
import no.uib.info233.v2017.khu010.oblig3.players.DefensivePlayer;
import no.uib.info233.v2017.khu010.oblig3.players.*;

/*
 * 	Entry point for game
 *  @author knu010 && xeq003
 * 	@version 0.1.1 (12.05.2017).
 */
public class Main {
	
	public static void main(String[] args) {
		//Gladiators game = new Gladiators(new GameMaster());
		GameMaster gm = new GameMaster();
		//gm.startSinglePlayer();
		RobotGame game = new RobotGame(gm);
	}

}
