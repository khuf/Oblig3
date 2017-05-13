package no.uib.info233.v2017.khu010.oblig3.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainMenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainMenuPanel() {
		super(new GridLayout());
		JButton SPButton = new JButton("SingelPlayer");
		JButton MPButton = new JButton("Multiplayer");
		JTextField nameField = new JTextField();
		this.add(nameField);
		this.add(SPButton);
		this.add(MPButton);
	}
}
