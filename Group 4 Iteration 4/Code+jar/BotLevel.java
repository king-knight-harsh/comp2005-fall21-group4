//required imports for the file
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Gui comment for selecting the number of human and AI players
public class BotLevel extends JFrame implements ActionListener {
	
	// private class variable for further use
	private JLabel informationLabel;
	private int numberOfHumanPlayers;
	private Dimension displayDimension;
	private JButton easyLevelButton, hardLevelButton;	
	private String playerOneName, playerTwoName, playerThreeName, playerFourName;
	private ImageIcon BotIcon;
	private int botDifficultyLevel;
	
	/*
	 * Constructor for the Bot level class
	 * @param numberOfHumanPlayers - Integer value to store the human players
	 * @param  player1 - String to store the name of the player
	 * @param  player2 - String to store the name of the player
	 * @param  player3 - String to store the name of the player
	 * @param  player4 - String to store the name of the player
	 * 
	 */
	BotLevel(int numberOfHumanPlayers, String player1,String player2,String player3,String player4){
		this.numberOfHumanPlayers = numberOfHumanPlayers;
		this.playerOneName=player1;
		this.playerTwoName=player2;
		this.playerThreeName=player3;
		this.playerFourName=player4;
		this.setLayout();
	}
	
	
	//Method to set the layout of the GUI
	public void setLayout() {	
		
		
		this.setSize(400,300);
		this.setLayout(null);
		this.setDisplayToCenter();
		this.setIconImage(this.getBotIcon().getImage());
		this.setTitle("AI DIFFICULTY");
		
		getContentPane().setBackground(Color.decode("#337def"));
		getContentPane().add(this.setInformationLabel());
		getContentPane().add(this.setEasylevelButton());
		getContentPane().add(this.setHardLevelButton());
				
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setResizable(false);		
		this.setVisible(true);
	}
	
	/*
	 * Method to set the information label
	 * @return informationLabel - JLabel customize label to ask the AIs difficulty level
	 */
	private JLabel setInformationLabel() {
		informationLabel = new JLabel("BOT DIFFICULTY",SwingConstants.CENTER);
		informationLabel.setBounds(40,30,300,100);
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
	private ImageIcon getBotIcon() {
		ImageIcon botIcon = new ImageIcon(getClass().getResource("images/robot.png"));
		return botIcon;
	}
	
	/*
	 * Method to set the easyLevelButton
	 * @return easyLevelButton - JButton customize button for getting input for easy AI level
	 */
	public JButton setEasylevelButton() {
		easyLevelButton = new JButton("EASY");
		easyLevelButton.setFont(new Font("TimesRoman", Font.BOLD, 32));		
		easyLevelButton.setBounds(30, 180, 150, 50);
		easyLevelButton.setBackground(Color.decode("#fcc729"));
		easyLevelButton.setForeground(Color.decode("#337def"));
		easyLevelButton.setFocusable(false);
		easyLevelButton.addActionListener(this);
		return easyLevelButton;		
	}
	/*
	 * Method to set the hardLevelButton
	 * @return hardLevelButton - JButton customize button for getting input for HARD AI level
	 */
	public JButton setHardLevelButton() {
		hardLevelButton = new JButton("HARD");
		hardLevelButton.setFont(new Font("TimesRoman", Font.BOLD, 32));		
		hardLevelButton.setBounds(210, 180, 150, 50);
		hardLevelButton.setBackground(Color.decode("#fcc729"));
		hardLevelButton.setForeground(Color.decode("#337def"));
		hardLevelButton.setFocusable(false);
		hardLevelButton.addActionListener(this);
		return hardLevelButton;
	}
	
	// Method to get the action perform for the user

	@Override
	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		Object selected = aevt.getSource();
		//Conditional block for the easy level button
		if (selected == this.easyLevelButton) {
			this.botDifficultyLevel=0;
			this.dispose();
			ColorSelect colorSelect = new ColorSelect(numberOfHumanPlayers,this.playerOneName,this.playerTwoName,this.playerThreeName,this.playerFourName, this.botDifficultyLevel);
		}
		//conditional block for the normal level button
		if(selected == this.hardLevelButton) {
			this.botDifficultyLevel=1;
			this.dispose();			
			ColorSelect colorSelect = new ColorSelect(numberOfHumanPlayers,this.playerOneName,this.playerTwoName,this.playerThreeName,this.playerFourName, this.botDifficultyLevel);
		}
	}

}
