package no.uib.info233.v2017.khu010.oblig3.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import no.uib.info233.v2017.khu010.oblig3.game.GameMaster;
import no.uib.info233.v2017.khu010.oblig3.game.GameState;

public class Gladiators extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Gladiators() {
		setUp();
	}
	
	private void setUp() {
		//Sets the size of the window
		this.setSize(500, 300);
		//Sets the default behavior of the windows exit button
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Sets the window layout
		this.setLayout(new BorderLayout());
		
		createMenuToolBar();
		createMainMenu();
		
		this.setVisible(true);
	}
	
	/**
	 * Creates the menu strip at the top of the window.
	 */
	private void createMenuToolBar() {
		JMenu menu = new JMenu("File");
		JMenuItem hostGame = new JMenuItem("Host Game");
		JMenuItem saveGame = new JMenuItem("Save Game");
		JMenuItem loadGame = new JMenuItem("Load Game");
		

		
		menu.add(hostGame);
		menu.add(saveGame);
		menu.add(loadGame);
	
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}
	
	private void createMainMenu() {
		this.getContentPane().add(new MainMenuPanel());
	}
}
