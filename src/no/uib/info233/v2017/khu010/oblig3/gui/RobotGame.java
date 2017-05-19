package no.uib.info233.v2017.khu010.oblig3.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import no.uib.info233.v2017.khu010.oblig3.game.GameMaster;
import no.uib.info233.v2017.khu010.oblig3.game.GameState;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class RobotGame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldPlayerName;
	private JTextField textFieldAttackPoints;
	private JTextField textFieldAttackPointsSP;
	private GameMaster gm;
	public RobotGame(GameMaster gm) {
		
		this.gm = gm;
		
		
		JPanel panelContainer = new JPanel();
		getContentPane().add(panelContainer, BorderLayout.CENTER);
		CardLayout lt = new CardLayout(0, 0);
		panelContainer.setLayout(lt);
		
		JPanel mainMenu = new JPanel();
		panelContainer.add(mainMenu, "mainMenuPanel");
		mainMenu.setLayout(null);
		
		JButton btnStartSinglePlayer = new JButton("Start Single Player");
		btnStartSinglePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lt.show(panelContainer, "singlePlayerPanel");
				gm.getGame().getGameState().getPlayerA().setName(textFieldPlayerName.getText());
			}
		});
		btnStartSinglePlayer.setBounds(162, 137, 138, 29);
		mainMenu.add(btnStartSinglePlayer);
		
		JButton btnStartMultiplayer = new JButton("Start Multi Player");
		btnStartMultiplayer.setBounds(162, 169, 138, 29);
		mainMenu.add(btnStartMultiplayer);
		
		textFieldPlayerName = new JTextField();
		textFieldPlayerName.setBounds(162, 102, 138, 26);
		mainMenu.add(textFieldPlayerName);
		textFieldPlayerName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(123, 107, 36, 16);
		mainMenu.add(lblName);
		
		JLabel lblRobotGame = new JLabel("Robot game");
		lblRobotGame.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		lblRobotGame.setBounds(86, 30, 309, 60);
		mainMenu.add(lblRobotGame);
		
		JPanel multiPlayerGame = new JPanel();
		panelContainer.add(multiPlayerGame, "multiPlayerPanel");
		multiPlayerGame.setLayout(null);
		
		JLabel lblPlayer1 = new JLabel("Player1");
		lblPlayer1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblPlayer1.setBounds(155, 49, 100, 31);
		multiPlayerGame.add(lblPlayer1);
		
		JLabel lblPlayer2 = new JLabel("Player2");
		lblPlayer2.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblPlayer2.setBounds(354, 51, 90, 27);
		multiPlayerGame.add(lblPlayer2);
		
		JLabel lblNewLabel = new JLabel("0");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 46));
		lblNewLabel.setBounds(285, 45, 34, 46);
		multiPlayerGame.add(lblNewLabel);
		
		textFieldAttackPoints = new JTextField();
		textFieldAttackPoints.setBounds(261, 216, 84, 26);
		multiPlayerGame.add(textFieldAttackPoints);
		textFieldAttackPoints.setColumns(10);
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.setBounds(245, 243, 117, 29);
		multiPlayerGame.add(btnAttack);
		
		JLabel label = new JLabel("100");
		label.setBounds(177, 92, 61, 16);
		multiPlayerGame.add(label);
		
		JLabel label_1 = new JLabel("100");
		label_1.setBounds(364, 92, 61, 16);
		multiPlayerGame.add(label_1);
		
		JPanel singlePlayerGame = new JPanel();
		singlePlayerGame.setLayout(null);
		panelContainer.add(singlePlayerGame, "singlePlayerPanel");
		
		JLabel lblPlayerNameSP = new JLabel("Player1");
		lblPlayerNameSP.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		gm.getGame().getGameState().getPlayerA().nameProperty().addListener((observable, oldValue, newValue) -> {
        	lblPlayerNameSP.setText(newValue.toString());
        });
		lblPlayerNameSP.setBounds(155, 49, 100, 31);
	
        
		singlePlayerGame.add(lblPlayerNameSP);
		
		JLabel lblEnemyPlayerSP = new JLabel("Player2");
		lblEnemyPlayerSP.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblEnemyPlayerSP.setBounds(354, 51, 90, 27);
		gm.getGame().getGameState().getPlayerB().nameProperty().addListener((observable, oldValue, newValue) -> {
        	lblEnemyPlayerSP.setText(newValue.toString());
        });
		singlePlayerGame.add(lblEnemyPlayerSP);
		
		JLabel lblGamePositionSP = new JLabel("0");
		lblGamePositionSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamePositionSP.setFont(new Font("Lucida Grande", Font.PLAIN, 46));
		lblGamePositionSP.setBounds(245, 36, 97, 72);
		gm.getGame().getGameState().currentPositionProperty().addListener((observable, oldValue, newValue) -> {
        	lblGamePositionSP.setText(newValue.toString());
        	System.out.println(newValue.toString());
        });
		singlePlayerGame.add(lblGamePositionSP);
		
		textFieldAttackPointsSP = new JTextField();
		textFieldAttackPointsSP.setColumns(10);
		textFieldAttackPointsSP.setBounds(261, 216, 84, 26);
		singlePlayerGame.add(textFieldAttackPointsSP);
		
		JButton btnAttackSP = new JButton("Attack");
		btnAttackSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
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
				}
			}
		});
		btnAttackSP.setBounds(245, 243, 117, 29);
		singlePlayerGame.add(btnAttackSP);
		
		JLabel lblEnergyPlayerSP = new JLabel("100");
		lblEnergyPlayerSP.setBounds(177, 92, 61, 16);
		gm.getGame().getGameState().getPlayerA().energyProperty().addListener((observable, oldValue, newValue) -> {
        	lblEnergyPlayerSP.setText(newValue.toString());
        });
		singlePlayerGame.add(lblEnergyPlayerSP);
		
		JLabel lblEnergyEnemySP = new JLabel("100");
		lblEnergyEnemySP.setBounds(364, 92, 61, 16);
		gm.getGame().getGameState().getPlayerB().energyProperty().addListener((observable, oldValue, newValue) -> {
        	lblEnergyEnemySP.setText(newValue.toString());
        });
		singlePlayerGame.add(lblEnergyEnemySP);
		
		this.setSize(500, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
