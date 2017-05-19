package no.uib.info233.v2017.khu010.oblig3.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import no.uib.info233.v2017.khu010.oblig3.game.GameMaster;
import no.uib.info233.v2017.khu010.oblig3.game.GameState;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JList;

public class RobotGame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelContainer;
	private CardLayout lt;
	private JPanel mainMenu;
	private JPanel singlePlayerGamePanel;
	private JPanel multiPlayerGamePanel;
	private JTextField textFieldPlayerName;
	private JTextField textFieldAttackPointsMP;
	private JTextField textFieldAttackPointsSP;
	private GameMaster gm;
	public RobotGame(GameMaster gm) {
		
		this.gm = gm;
		
		
		panelContainer = new JPanel();
		getContentPane().add(panelContainer, BorderLayout.CENTER);
		lt = new CardLayout(0, 0);
		panelContainer.setLayout(lt);
		
		createMainMenuPanel();
		createSinglePlayerPanel();
		createMultiPlayerPanel();
		
		this.setSize(500, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Creates the main menu panel that consists of a logo and two buttons giving
	 * the user the ability to start a game, either single player or multiplayer.
	 */
	private void createMainMenuPanel() {
		mainMenu = new JPanel();
		panelContainer.add(mainMenu, "mainMenuPanel");
		mainMenu.setLayout(null);
		
		//Creates and adds the game logo to the main menu.
		JLabel lblRobotGame = new JLabel("Robot game");
		lblRobotGame.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		lblRobotGame.setBounds(86, 30, 309, 60);
		mainMenu.add(lblRobotGame);
		
		//Creates a text field enabling the user to choose his name
		textFieldPlayerName = new JTextField();
		textFieldPlayerName.setBounds(162, 102, 138, 26);
		mainMenu.add(textFieldPlayerName);
		textFieldPlayerName.setColumns(10);
		
		//A label that pairs with the textfield above.
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(123, 107, 36, 16);
		mainMenu.add(lblName);
		
		/*Creates a button to start single player mode and assigns an action listener
		 * that responds to a button click by changing panel.
		 */
		JButton btnStartSinglePlayer = new JButton("Start Single Player");
		btnStartSinglePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lt.show(panelContainer, "singlePlayerPanel");
				gm.getSinglePlayerGameState().getPlayerA().setName(textFieldPlayerName.getText());
			}
		});
		btnStartSinglePlayer.setBounds(162, 137, 138, 29);
		mainMenu.add(btnStartSinglePlayer);
		
		/*Creates a button to start single player mode and assigns an action listener
		 * that responds to a button click by changing panel.
		 */
		JButton btnStartMultiPlayer = new JButton("Start Multi Player");
		btnStartMultiPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lt.show(panelContainer, "multiPlayerPanel");
				//gm.getMultiPlayerGameState().getPlayerA().setName(textFieldPlayerName.getText());
			}
		});
		btnStartMultiPlayer.setBounds(162, 169, 138, 29);
		mainMenu.add(btnStartMultiPlayer);
	}
	
	/**
	 * Creates the single player panel. This consists of
	 * labels and buttons that shows the current state of the game, such as
	 * current round number, energy levels and player names.
	 */
	private void createSinglePlayerPanel() {
		singlePlayerGamePanel = new JPanel();
		singlePlayerGamePanel.setLayout(null);
		panelContainer.add(singlePlayerGamePanel, "singlePlayerPanel");

		JLabel lblPlayerNameSP = new JLabel("Player1");
		lblPlayerNameSP.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		gm.getSinglePlayerGameState().getPlayerA().nameProperty().addListener((observable, oldValue, newValue) -> {
			lblPlayerNameSP.setText(newValue.toString());
		});
		lblPlayerNameSP.setBounds(155, 49, 100, 31);

		singlePlayerGamePanel.add(lblPlayerNameSP);

		JLabel lblEnemyPlayerSP = new JLabel("Player2");
		lblEnemyPlayerSP.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblEnemyPlayerSP.setBounds(354, 51, 90, 27);
		gm.getSinglePlayerGameState().getPlayerB().nameProperty().addListener((observable, oldValue, newValue) -> {
			lblEnemyPlayerSP.setText(newValue.toString());
		});
		singlePlayerGamePanel.add(lblEnemyPlayerSP);

		JLabel lblGamePositionSP = new JLabel("0");
		lblGamePositionSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamePositionSP.setFont(new Font("Lucida Grande", Font.PLAIN, 46));
		lblGamePositionSP.setBounds(245, 36, 97, 72);
		gm.getSinglePlayerGameState().currentPositionProperty().addListener((observable, oldValue, newValue) -> {
			lblGamePositionSP.setText(newValue.toString());
		});
		singlePlayerGamePanel.add(lblGamePositionSP);

		textFieldAttackPointsSP = new JTextField();
		textFieldAttackPointsSP.setColumns(10);
		textFieldAttackPointsSP.setBounds(261, 216, 84, 26);
		singlePlayerGamePanel.add(textFieldAttackPointsSP);

		/**
		 * Creates an attack button that responds to user clicks by performing
		 * a move for the player.
		 */
		JButton btnAttackSP = new JButton("Attack");
		btnAttackSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
<<<<<<< HEAD
					 String points = textFieldAttackPointsSP.getText();
				     int energy = Integer.parseInt(points);
				     	
				     if (!gm.getSinglePlayerGame().getGameState().isFinnished()) {
				    	 gm.getSinglePlayerGame().performMove(energy);
				     } else {
				    	 float playerAScore = gm.getSinglePlayerGame().getGameState().getPlayerRewards().getPlayerAScore();
				    	 float playerBScore = gm.getSinglePlayerGame().getGameState().getPlayerRewards().getPlayerBScore();
				    	 JOptionPane.showMessageDialog(new JFrame(), "Player was awarded " +  playerAScore + " while enemy "
								+ "player got " + playerBScore, "Game finished", 
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(new JFrame(),
						    "Please enter a number instead",
						    "Invalid move",
						    JOptionPane.ERROR_MESSAGE);
=======
					String points = textFieldAttackPointsSP.getText();
					int energy = Integer.parseInt(points);
					GameState state = gm.getSinglePlayerGameState();

					if (!state.isFinnished()) {
						gm.getSinglePlayerGame().performMove(energy);
					}
					if (state.isFinnished()) {
						float playerAScore = state.getPlayerRewards().getPlayerAScore();
						float playerBScore = state.getPlayerRewards().getPlayerBScore();
						JOptionPane.showMessageDialog(new JFrame(),
								"Player was awarded " + playerAScore + " while enemy " + "player got " + playerBScore,
								"Game finished", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(new JFrame(), "Please enter a number instead", "Invalid move",
							JOptionPane.ERROR_MESSAGE);
>>>>>>> origin/oblig4-branch
				}
			}
		});
		btnAttackSP.setBounds(245, 243, 117, 29);
		singlePlayerGamePanel.add(btnAttackSP);

		/*Creates a label for the players energy level and assigns
		 * a listener to the players energy property that
		 * updates the label when the players energy changes.
		 */
		JLabel lblEnergyPlayerSP = new JLabel("100");
		lblEnergyPlayerSP.setBounds(177, 92, 61, 16);
		gm.getSinglePlayerGameState().getPlayerA().energyProperty().addListener((observable, oldValue, newValue) -> {
			lblEnergyPlayerSP.setText(newValue.toString());
		});
		singlePlayerGamePanel.add(lblEnergyPlayerSP);
		
		/*Creates a label for the players energy level and assigns
		 * a listener to the players energy property that
		 * updates the label when the players energy changes.
		 */
		JLabel lblEnergyPlayer2SP = new JLabel("100");
		lblEnergyPlayer2SP.setBounds(364, 92, 61, 16);
		gm.getSinglePlayerGameState().getPlayerB().energyProperty().addListener((observable, oldValue, newValue) -> {
        	lblEnergyPlayer2SP.setText(newValue.toString());
        });
		singlePlayerGamePanel.add(lblEnergyPlayer2SP);
	}
	
	/**
	 * Creates the single player panel. This consists of
	 * labels and buttons that shows the current state of the game, such as
	 * current round number, energy levels and player names.
	 */
	private void createMultiPlayerPanel() {
		multiPlayerGamePanel = new JPanel();
		panelContainer.add(multiPlayerGamePanel, "multiPlayerPanel");
		multiPlayerGamePanel.setLayout(null);
		
		JLabel lblPlayer1MP = new JLabel("Player1");
		lblPlayer1MP.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblPlayer1MP.setBounds(155, 49, 100, 31);
		multiPlayerGamePanel.add(lblPlayer1MP);
		
		JLabel lblPlayer2MP = new JLabel("Player2");
		lblPlayer2MP.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblPlayer2MP.setBounds(354, 51, 90, 27);
		multiPlayerGamePanel.add(lblPlayer2MP);
		
		JLabel lblGamePositionMP = new JLabel("0");
		lblGamePositionMP.setFont(new Font("Lucida Grande", Font.PLAIN, 46));
		lblGamePositionMP.setBounds(285, 45, 34, 46);
		multiPlayerGamePanel.add(lblGamePositionMP);
		
		textFieldAttackPointsMP = new JTextField();
		textFieldAttackPointsMP.setBounds(261, 216, 84, 26);
		multiPlayerGamePanel.add(textFieldAttackPointsMP);
		textFieldAttackPointsMP.setColumns(10);
		
		JButton btnAttackMP = new JButton("Attack");
		btnAttackMP.setBounds(245, 243, 117, 29);
		multiPlayerGamePanel.add(btnAttackMP);
		
		JLabel lblEnergyPlayer1MP = new JLabel("100");
		lblEnergyPlayer1MP.setBounds(177, 92, 61, 16);
		multiPlayerGamePanel.add(lblEnergyPlayer1MP);
		
		JLabel lblEnergyPlayer2MP = new JLabel("100");
		lblEnergyPlayer2MP.setBounds(364, 92, 61, 16);
		multiPlayerGamePanel.add(lblEnergyPlayer2MP);
		
		JPanel panelAvailableGamesMP = new JPanel();
		panelAvailableGamesMP.setBackground(Color.WHITE);
		panelAvailableGamesMP.setBounds(6, 49, 137, 154);
		multiPlayerGamePanel.add(panelAvailableGamesMP);
		
		JList listAvailableGamesMP = new JList();
		panelAvailableGamesMP.add(listAvailableGamesMP);
		
		JLabel lblAvailableGamesMP = new JLabel("Available games");
		lblAvailableGamesMP.setBounds(6, 21, 137, 16);
		multiPlayerGamePanel.add(lblAvailableGamesMP);
		
		JButton btnJoinGameMP = new JButton("Join game");
		btnJoinGameMP.setBounds(16, 213, 117, 29);
		multiPlayerGamePanel.add(btnJoinGameMP);
		
		JButton btnHostGameMP = new JButton("Host game");
		btnHostGameMP.setBounds(16, 243, 117, 29);
		multiPlayerGamePanel.add(btnHostGameMP);
	}
}
