import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorSelect extends JFrame implements ActionListener{
	
	private JLabel informationLabel;
	private int numberOfHumanPlayers;
	private Dimension displayDimension;
	private JButton normalModeButton, colorBlindButtonOne, colorBlindButtonTwo;	
	private String playerOneName, playerTwoName, playerThreeName, playerFourName;
	private ImageIcon ColorModeIcon;
	private int colorMode;
	private int botDifficultyLevel;
	
	ColorSelect(int numberOfHumanPlayers, String player1,String player2,String player3,String player4, int botDifficultyLevel){
		this.numberOfHumanPlayers = numberOfHumanPlayers;
		this.playerOneName=player1;
		this.playerTwoName=player2;
		this.playerThreeName=player3;
		this.playerFourName=player4;
		this.botDifficultyLevel = botDifficultyLevel;
		this.setLayout();
	}
	
	private void setLayout() {	
		
		
		this.setSize(650,400);
		this.setLayout(null);
		this.setDisplayToCenter();
		this.setIconImage(this.getColorModeIcon().getImage());
		this.setTitle("COLOR PALETTE");
		
		getContentPane().setBackground(Color.decode("#337def"));
		getContentPane().add(this.setInformationLabel());
		getContentPane().add(this.setNormalColorModeButton());
		getContentPane().add(this.setColorBlindModeButtonOne());
		getContentPane().add(this.setColorBlindModeButtonTwo());
				
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setResizable(false);		
		this.setVisible(true);
	}
	
	private JLabel setInformationLabel() {
		informationLabel = new JLabel("COLOUR PALETTE",SwingConstants.CENTER);
		informationLabel.setBounds(130,30,400,100);
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));
		informationLabel.setForeground(Color.decode("#fcc729"));
		informationLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#fcc729"), 4));
		return informationLabel;
	}
	
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}

	
	private ImageIcon getColorModeIcon() {
		ImageIcon ColorModeIcon = new ImageIcon(getClass().getResource("images/colorBlindPalette.png"));
		return ColorModeIcon;
	}
	
	public JButton setNormalColorModeButton() {
		normalModeButton = new JButton("NORMAL MODE");
		normalModeButton.setFont(new Font("TimesRoman", Font.BOLD, 24));		
		normalModeButton.setBounds(20, 180, 300, 50);
		normalModeButton.setBackground(Color.decode("#fcc729"));
		normalModeButton.setForeground(Color.decode("#337def"));
		normalModeButton.setFocusable(false);
		normalModeButton.addActionListener(this);
		return normalModeButton;		
	}
	public JButton setColorBlindModeButtonOne() {
		colorBlindButtonOne = new JButton("RED-GREEN BLIND");
		colorBlindButtonOne.setFont(new Font("TimesRoman", Font.BOLD, 24));		
		colorBlindButtonOne.setBounds(330, 180, 300, 50);
		colorBlindButtonOne.setBackground(Color.decode("#fcc729"));
		colorBlindButtonOne.setForeground(Color.decode("#337def"));
		colorBlindButtonOne.setFocusable(false);
		colorBlindButtonOne.addActionListener(this);
		return colorBlindButtonOne;
	}
	
	
	public JButton setColorBlindModeButtonTwo() {
		colorBlindButtonTwo = new JButton("BLUE-YELLOW BLIND");
		colorBlindButtonTwo.setFont(new Font("TimesRoman", Font.BOLD, 24));		
		colorBlindButtonTwo.setBounds(180, 240, 300, 50);
		colorBlindButtonTwo.setBackground(Color.decode("#fcc729"));
		colorBlindButtonTwo.setForeground(Color.decode("#337def"));
		colorBlindButtonTwo.setFocusable(false);
		colorBlindButtonTwo.addActionListener(this);
		return colorBlindButtonTwo;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		Object selected = aevt.getSource();
		if (selected == this.normalModeButton) {
			this.colorMode = 0;
			this.dispose();	
			MainGameNormalMode mainGameNormalMode= new MainGameNormalMode(numberOfHumanPlayers,this.playerOneName,this.playerTwoName,this.playerThreeName,this.playerFourName, this.botDifficultyLevel, this.colorMode);
		}
		
		if(selected == this.colorBlindButtonOne) {
			this.colorMode = 1;
			this.dispose();	
			MainGameColorBlindModeOne mainGameColorBlindModeOne= new MainGameColorBlindModeOne(numberOfHumanPlayers,this.playerOneName,this.playerTwoName,this.playerThreeName,this.playerFourName, this.botDifficultyLevel, this.colorMode);
			 
		}
		
		if(selected == this.colorBlindButtonTwo) {
			this.colorMode = 2;
			this.dispose();	
			MainGameColorBlindModeTwo mainGameColorBlindModeOne= new MainGameColorBlindModeTwo(numberOfHumanPlayers,this.playerOneName,this.playerTwoName,this.playerThreeName,this.playerFourName, this.botDifficultyLevel, this.colorMode);
			 
		}
	}

}
