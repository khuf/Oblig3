package no.uib.info233.v2017.khu010.oblig3.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.Component;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;

public class Gladiators extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelContainer;
	private JTextField textField;
	private GameMaster gm;
	
	public Gladiators(GameMaster gameMaster) {
		gm = gameMaster;
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
		CardLayout lt = new CardLayout();
		mainMenuPanel.setLayout(lt);
		JPanel panel = new JPanel();
		panelContainer.add(mainMenuPanel, "MainMenuPanel");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.YELLOW);
		mainMenuPanel.add("mainMenu", panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		mainMenuPanel.add("gamePanel", panel_2);
		panel_2.setLayout(null);
		
		JButton btnSinglePlayer = new JButton("Play singleplayer");
		btnSinglePlayer.setBounds(209, 170, 117, 29);
		btnSinglePlayer.addActionListener(e -> {
			System.out.println("Singleplayer button clicked");
			lt.show(mainMenuPanel, "gamePanel");
			//gm.startSinglePlayer();
			gm.getGame().getGameState().currentPositionProperty().set(50);
			//System.out.println(lt.);
			//gm.startSinglePlayer();
		});
		panel_1.add(btnSinglePlayer);
		
		JButton btnMultiPlayer = new JButton("Play multiplayer");
		btnMultiPlayer.addActionListener(e -> {
			System.out.println("Multiplayer button was clicked");
			//gm.startMultiPlayer();
		});
		btnMultiPlayer.setBounds(209, 125, 117, 29);
		panel_1.add(btnMultiPlayer);
		
		textField = new JTextField();
		textField.setBounds(200, 87, 139, 26);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(139, 92, 61, 16);
		panel_1.add(lblName);
		
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setBounds(30, 30, 49, 16);
		panel_2.add(lblPlayer);
		
		JLabel label = new JLabel("Player 1");
		label.setBounds(415, 30, 49, 16);
		panel_2.add(label);
		
		JLabel lblRoundNumber = new JLabel("0");
		lblRoundNumber.setBounds(136, 68, 153, 19);
		
		// add listener for stringProperty
        // to be called when stringProperty changed
        gm.getGame().getGameState().currentPositionProperty().addListener((observable, oldValue, newValue) -> {
        	lblRoundNumber.setText(newValue.toString());
        });
        
        
		
		panel_2.add(lblRoundNumber);
		this.setResizable(false);
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
