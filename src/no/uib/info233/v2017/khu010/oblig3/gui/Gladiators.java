package no.uib.info233.v2017.khu010.oblig3.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

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
import no.uib.info233.v2017.khu010.oblig3.interfaces.SQLManagerInterface;
import no.uib.info233.v2017.khu010.oblig3.sql.SQLManager;


import java.awt.Font;

public class Gladiators extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelContainer;
	private JTextField textField;
	private GameMaster gm;
	//private SQLManagerInterface manager = new SQLManager();
	
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
		
		JPanel panel_2 = new JPanel();
		mainMenuPanel.add("gamePanel", panel_2);
		panel_2.setLayout(null);
		
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setBounds(297, 30, 49, 16);
		panel_2.add(lblPlayer);
		
		JLabel label = new JLabel("Player 1");
		label.setBounds(415, 30, 49, 16);
		panel_2.add(label);
		
		JLabel lblRoundNumber = new JLabel("0");
		lblRoundNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 56));
		lblRoundNumber.setBounds(181, 100, 49, 59);
		
		// add listener for stringProperty
        // to be called when stringProperty changed
        gm.getGame().getGameState().currentPositionProperty().addListener((observable, oldValue, newValue) -> {
        	lblRoundNumber.setText(newValue.toString());
        	System.out.println("Label was changed from: " + oldValue.toString() + " to " + newValue.toString());
        });
        
        
		
		panel_2.add(lblRoundNumber);
		String[] arr = {"Hello", "Test"};

		
		
		JButton btnJoinGame = new JButton("Join game");
		btnJoinGame.setBounds(34, 192, 91, 29);
		panel_2.add(btnJoinGame);
		
		JButton btnHostGame = new JButton("Host game");
		btnHostGame.setBounds(19, 221, 119, 29);
		panel_2.add(btnHostGame);
		
		JLabel lblAvailableGames = new JLabel("Available games");
		lblAvailableGames.setBounds(6, 16, 119, 16);
		panel_2.add(lblAvailableGames);
		
		JLabel lblRoundNumber_1 = new JLabel("Position");
		lblRoundNumber_1.setBounds(167, 72, 60, 16);
		panel_2.add(lblRoundNumber_1);
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//gm.getGame().setMoveMade(true);
				if (true/*gm.getGame().getIsHost()*/) {
					//manager.setPlayerAMove(gm.getGame().getGameState().getGameID(), 10);
				}
				else {
					//manager.setPlayerBMove(gm.getGame().getGameState().getGameID(), 10);
				}
			}
		});
		btnAttack.setBounds(331, 221, 117, 29);
		panel_2.add(btnAttack);
		
		JLabel lblEnergy = new JLabel("Energy:");
		lblEnergy.setBounds(239, 57, 61, 16);
		panel_2.add(lblEnergy);
		
		JLabel label_1 = new JLabel("100");
		label_1.setBounds(297, 58, 61, 16);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("100");
		label_2.setBounds(415, 58, 61, 16);
		panel_2.add(label_2);
		
		JLabel lblMove = new JLabel("Move");
		lblMove.setBounds(239, 197, 61, 16);
		panel_2.add(lblMove);
		
		JLabel label_3 = new JLabel("0");
		label_3.setBounds(297, 197, 61, 16);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("0");
		label_4.setBounds(415, 197, 61, 16);
		panel_2.add(label_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(6, 41, 132, 151);
		panel_3.setSize(130, 155);
		panel_2.add(panel_3);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.YELLOW);
		mainMenuPanel.add("mainMenu", panel_1);
		panel_1.setLayout(null);
		
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
