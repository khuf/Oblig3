package no.uib.info233.v2017.khu010.oblig3.players;

import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, int goal) {
		super(name, goal);
	}

	@Override
	public boolean makeNextMove(int currentPosition, int opponentEnergy) {
		
		return getGameMaster().listenToPlayerMove(this, 0);
	}
}
