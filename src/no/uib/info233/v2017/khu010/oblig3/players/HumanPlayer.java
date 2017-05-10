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
	public int makeNextMove(int currentPosition, int opponentEnergy) {
		JDialog dialog = new JDialog();
		dialog.setSize(300, 500);
		dialog.setTitle("Make move");
		 NumberFormat format = NumberFormat.getIntegerInstance();
		 NumberFormatter formatter = new NumberFormatter(format);
		    formatter.setValueClass(Integer.class);
		    formatter.setMinimum(0);
		    formatter.setMaximum(Integer.MAX_VALUE);
		    formatter.setAllowsInvalid(false);
		    // If you want the value to be committed on each keystroke instead of focus lost
		    formatter.setCommitsOnValidEdit(true);
		    JFormattedTextField field = new JFormattedTextField(formatter);
		    field.setValue(0);
		    dialog.add(field);
		    //dialog.add(new JButton("Ok"));
		    dialog.setVisible(true);
		    
		    getGameMaster().listenToPlayerMove(this, (int) field.getValue());
		return (int) field.getValue();
	}

}
