package no.uib.info233.v2017.khu010.oblig3.players;

import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, int goal) {
		super(name, goal);
	}

	@Override
	public int makeNextMove() {
		int energyToUse = 0;
		
		Scanner sc = new Scanner(System.in);
		
		energyToUse = sc.nextInt();
		
		sc.close();
		
		return energyToUse;
	}
	
	public int makeNextMove(int curr, int opp) {
		return 0;
	}
}
