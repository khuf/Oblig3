package no.uib.info233.v2017.khu010.oblig3.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gladiators extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameMaster gameMaster;
	
	public Gladiators() {
		this.setSize(500, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		setUp();
		this.setVisible(true);
	}
	
	private void setUp() {
		JPanel panel = new JPanel();
		JPanel multiPlayerPanel = new JPanel();
		JButton hostButton = new JButton("Host game");
		JButton loadButton = new JButton("Load game");
		JButton saveButton = new JButton("Save game");
		JTextField field = new JTextField();
		gameMaster.
		panel.add(hostButton);
		panel.add(saveButton);
		panel.add(loadButton);
		this.getContentPane().add(panel, BorderLayout.PAGE_END);
	}
	
	private void createGameControls() {
		JButton hostButton = new JButton("Host game");
		JButton loadButton = new JButton("Load game");
		this.getLayout().addLayoutComponent("hostButton", hostButton);
		this.getLayout().addLayoutComponent("loadButton", loadButton);
	}
}
