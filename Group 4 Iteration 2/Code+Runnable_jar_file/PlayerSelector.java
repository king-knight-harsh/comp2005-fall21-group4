import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class PlayerSelector extends JFrame implements ActionListener{
	
	private JFrame playerSelectorFrame;
	private JLabel informationLabel;
	private int numberOfHumanPlayers, numberOfBots;
	private Dimension displayDimension;
	private JButton playerOne, playerTwo, playerThree, playerFour;
	private ImageIcon newIcon;
	
	
	public PlayerSelector() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void setLayout() {
		
	
		
		this.setSize(400,350);
		this.setLayout(null);
		this.setDisplayToCenter();
		this.setIconImage(this.getNewIcon().getImage());
		this.setTitle("New Game");
		
		getContentPane().setBackground(Color.decode("#337def"));
		getContentPane().add(this.setInformationLabel());
		getContentPane().add(this.setPlayerOneButton());
		getContentPane().add(this.setPlayerTwoButton());
		getContentPane().add(this.setPlayerThreeButton());
		getContentPane().add(this.setPlayerFourButton());
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setResizable(false);		
		this.setVisible(true);
	}
	
	private JLabel setInformationLabel() {
		informationLabel = new JLabel("<html>Select the Number <br>of Human Player:</html>",SwingConstants.CENTER);
		informationLabel.setBounds(40,30,300,100);
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));
		informationLabel.setForeground(Color.decode("#fcc729"));
		informationLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#fcc729"), 4));
		return informationLabel;
	}
	
	public JButton setPlayerOneButton() {
		playerOne = new JButton("1");
		playerOne.setFont(new Font("TimesRoman", Font.BOLD, 32));		
		playerOne.setBounds(60, 180, 50, 50);
		playerOne.setBackground(Color.decode("#fcc729"));
		playerOne.setForeground(Color.decode("#337def"));
		playerOne.setFocusable(false);
		playerOne.addActionListener(this);
		return playerOne;		
	}
	public JButton setPlayerTwoButton() {
		playerTwo = new JButton("2");
		playerTwo.setFont(new Font("TimesRoman", Font.BOLD, 32));		
		playerTwo.setBounds(130, 180, 50, 50);
		playerTwo.setBackground(Color.decode("#fcc729"));
		playerTwo.setForeground(Color.decode("#337def"));
		playerTwo.setFocusable(false);
		playerTwo.addActionListener(this);
		return playerTwo;		
	}
	public JButton setPlayerThreeButton() {
		playerThree = new JButton("3");
		playerThree.setFont(new Font("TimesRoman", Font.BOLD, 32));		
		playerThree.setBounds(200, 180, 50, 50);
		playerThree.setBackground(Color.decode("#fcc729"));
		playerThree.setForeground(Color.decode("#337def"));
		playerThree.setFocusable(false);
		playerThree.addActionListener(this);
		return playerThree;		
	}
	public JButton setPlayerFourButton() {
		playerFour = new JButton("4");
		playerFour.setFont(new Font("TimesRoman", Font.BOLD, 32));		
		playerFour.setBounds(270, 180, 50, 50);
		playerFour.setBackground(Color.decode("#fcc729"));
		playerFour.setForeground(Color.decode("#337def"));
		playerFour.setFocusable(false);
		playerFour.addActionListener(this);
		return playerFour;		
	}
	
	public int getNumberofHumanPlayers() {
		return numberOfHumanPlayers;
	}
	
	public int getNumberOfBots(){
		numberOfBots = 4-numberOfHumanPlayers;
		return numberOfBots;
	}
	
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}

	private ImageIcon getNewIcon() {
		newIcon = new ImageIcon("images/new.png");
		return newIcon;
	}

	@Override
	public void actionPerformed(ActionEvent aevt) {
		Object selected = aevt.getSource();
		if (selected == this.playerOne) {
			this.numberOfHumanPlayers = 1;
			this.dispose();
			PlayerInfo playerInfoWindow = new PlayerInfo(this.getNumberofHumanPlayers(),this.getNumberOfBots()); 
		}
		if (selected == this.playerTwo) {
			this.numberOfHumanPlayers = 2;
			this.dispose();
			PlayerInfo playerInfoWindow = new PlayerInfo(this.getNumberofHumanPlayers(),this.getNumberOfBots()); 
		}
		if (selected == this.playerThree) {
			this.numberOfHumanPlayers = 3;
			this.dispose();
			PlayerInfo playerInfoWindow = new PlayerInfo(this.getNumberofHumanPlayers(),this.getNumberOfBots()); 
		}
		if (selected == this.playerFour) {
			this.numberOfHumanPlayers = 4;
			this.dispose();
			PlayerInfo playerInfoWindow = new PlayerInfo(this.getNumberofHumanPlayers(),this.getNumberOfBots()); 
		}
		
		
	}
}
