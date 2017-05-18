package no.uib.info233.v2017.khu010.oblig3.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;

public class RobotGame extends JFrame {
	public RobotGame() {
		
		JPanel panelContainer = new JPanel();
		getContentPane().add(panelContainer, BorderLayout.CENTER);
		panelContainer.setLayout(new CardLayout(0, 0));
		
		JPanel mainMenu = new JPanel();
		panelContainer.add(mainMenu, "name_15206657889291");
		mainMenu.setLayout(null);
		
		JButton btnStartSinglePlayer = new JButton("Start Single Player");
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
		panelContainer.add(multiPlayerGame, "name_15809579097389");
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
		panelContainer.add(singlePlayerGame, "name_15825645783547");
		singlePlayerGame.setLayout(null);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldPlayerName;
	private JTextField textFieldAttackPoints;
}
