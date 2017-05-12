package no.uib.info233.v2017.khu010.oblig3.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainMenuPanel() {
		super(new BorderLayout());
		JButton SPButton = new JButton("SingelPlayer");
		JButton MPButton = new JButton("Multiplayer");
		this.add(SPButton, BorderLayout.LINE_START);
		this.add(MPButton, BorderLayout.PAGE_END);
	}
}
