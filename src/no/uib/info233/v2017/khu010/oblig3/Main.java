package no.uib.info233.v2017.khu010.oblig3;

import no.uib.info233.v2017.khu010.oblig3.game.*;
import no.uib.info233.v2017.khu010.oblig3.gui.*;

/*
 * 	Entry point for the robot game.
 *  @author knu010 && xeq003
 * 	@version 0.1.1 (15.05.2017).
 */
public class Main {
	
	public static void main(String[] args) {

		//Starts the application
		RobotGame game = new RobotGame(new GameMaster());
		game.setVisible(true);
	}

}
