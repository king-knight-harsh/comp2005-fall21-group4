//import necessary documents
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//Simple GUI class for the selection of the color palette
public class ColorSelect extends JFrame implements ActionListener{
	
	//Class variable for the further use
	private JLabel informationLabel;
	private int numberOfHumanPlayers;
	private Dimension displayDimension;
	private JButton normalModeButton, colorBlindButtonOne, colorBlindButtonTwo;	
	private String playerOneName, playerTwoName, playerThreeName, playerFourName;
	private ImageIcon ColorModeIcon;
	private int colorMode;
	private int botDifficultyLevel;
	
	/*
	 * Constructor for the ColorSelect
	 * @param numberOfHumanPlayers - Integer value to store the human players
	 * @param  player1 - String to store the name of the player
	 * @param  player2 - String to store the name of the player
	 * @param  player3 - String to store the name of the player
	 * @param  player4 - String to store the name of the player
	 * @param botDifficultyLevel - Integer value for the AI difficulty level(0 for easy || 1 for Hard)
	 */
	ColorSelect(int numberOfHumanPlayers, String player1,String player2,String player3,String player4, int botDifficultyLevel){
		this.numberOfHumanPlayers = numberOfHumanPlayers;
		this.playerOneName=player1;
		this.playerTwoName=player2;
		this.playerThreeName=player3;
		this.playerFourName=player4;
		this.botDifficultyLevel = botDifficultyLevel;
		this.setLayout();
	}
	
	//Method to set the GUI for the color select class
	private void setLayout() {	
		this.setSize(670,400);
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
	
	/*
	 * Method to set the information label
	 * @return informationLabel - JLabel customize label to ask for the color palette
	 */
	private JLabel setInformationLabel() {
		informationLabel = new JLabel("COLOUR PALETTE",SwingConstants.CENTER);
		informationLabel.setBounds(130,30,400,100);
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));
		informationLabel.setForeground(Color.decode("#fcc729"));
		informationLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#fcc729"), 4));
		return informationLabel;
	}
	//Method to setting the display to the center
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}

	//Method to get the image for the frame
	private ImageIcon getColorModeIcon() {
		ImageIcon ColorModeIcon = new ImageIcon(getClass().getResource("images/colorBlindPalette.png"));
		return ColorModeIcon;
	}
	
	/*
	 * Method to set the JButton for the normal color mode
	 * @param normalModeButton -JButton customize button for normal color palette
	 */
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
	/*
	 * Method to set the JButton for the color blind mode one(red-green)
	 * @param colorBlindButtonOne -JButton customize button for color blind mode one
	 */
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
	/*
	 * Method to set the JButton for the color blind mode two(blue-yellow)
	 * @param colorBlindButtonTwo -JButton customize button for color blind mode two
	 */	
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
	
	//Action performed method to capture the correct selected button
	@Override
	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		Object selected = aevt.getSource();
		//Conditional block for the normal Mode button
		if (selected == this.normalModeButton) {
			this.colorMode = 0;
			this.dispose();	
			MainGameNormalMode mainGameNormalMode= new MainGameNormalMode(numberOfHumanPlayers,this.playerOneName,this.playerTwoName,this.playerThreeName,this.playerFourName, this.botDifficultyLevel, this.colorMode);
		}
		//Conditional block for the color blind mode one button
		if(selected == this.colorBlindButtonOne) {
			Color colorOne = new Color(1,48,162);
			Color colorTwo = new Color(98,91,7);
			Color colorThree = new Color(111,130,206);
			Color colorFour = new Color(220,201,1);
			this.dispose();	
			MainGameColorBlindMode mainGameColorBlindModeTwo= new MainGameColorBlindMode(numberOfHumanPlayers,this.playerOneName,this.playerTwoName,
					this.playerThreeName,this.playerFourName, this.botDifficultyLevel,1, colorOne, colorTwo, colorThree,colorFour);
		}
		//Conditional block for the color blind mode Two button
		if(selected == this.colorBlindButtonTwo) {
			Color colorOne = new Color(19,165,198);
			Color colorTwo = new Color(181,95,122);
			Color colorThree = new Color(254,41,107);
			Color colorFour = new Color(251,170,189);			
			this.dispose();	
			MainGameColorBlindMode mainGameColorBlindModeTwo= new MainGameColorBlindMode(numberOfHumanPlayers,this.playerOneName,this.playerTwoName,
					this.playerThreeName,this.playerFourName, this.botDifficultyLevel,1,colorOne, colorTwo, colorThree,colorFour);
			 
		}
	}

}

