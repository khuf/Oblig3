package no.uib.info233.v2017.khu010.oblig3.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.Component;

public class Gladiators extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelContainer;
	
	public Gladiators() {
		setUp();
	}
	
	private void setUp() {
		//Sets the size of the window
		this.setSize(500, 300);
		//Sets the default behavior of the windows exit button
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Sets the window layout
		panelContainer = new JPanel();
		panelContainer.setLayout(new CardLayout());
		
		
		createMenuToolBar();
		createMainMenu();
		
		getContentPane().add(panelContainer);
		
		JPanel mainMenuPanel = new JPanel();
		panelContainer.add(mainMenuPanel, "name_196939946554603");
		mainMenuPanel.setLayout(new BorderLayout(0, 0));
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
	}
	
	private void createGamePanel() {
		
	}
}
