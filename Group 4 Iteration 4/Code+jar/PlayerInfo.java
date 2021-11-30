//Importing the required files
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// GUI for getting the names of the players
public class PlayerInfo extends JFrame implements ActionListener{
	private int numberOfHumans;
	private Dimension displayDimension;
	private ImageIcon playerIcon;
	private JLabel playerOneInfo,playerTwoInfo,playerThreeInfo,playerFourInfo, informationLabel;
	private JButton submitButtonPlayerOne,submitButtonPlayerTwo,submitButtonPlayerThree,submitButtonPlayerFour;
	private JTextField playerOneName, playerTwoName, playerThreeName, playerFourName;

	/*
	 * Constructor for the getting the name of the players
	 * @param numberOfHumanPlayer - Integer number of human players
	 * @param numberOfBots - Integer bot difficulty level of the game
	 */
	PlayerInfo(int numberOfHumanPlayer, int numberOfBots){
		this.numberOfHumans= numberOfHumanPlayer;
		
		this.setLayout(null);
		
		this.setIconImage(this.getPlayerIcon().getImage());
		this.setTitle("Player Information");		
		getContentPane().setBackground(Color.decode("#337def"));
		
		if(numberOfHumans==1) {
			this.setPlayerOneInfo();
		}
		else if(numberOfHumans==2) {
			this.setPlayerTwoInfo();
		}
		else if(numberOfHumans==3) {
			this.setPlayerThreeInfo();
		}
		else if(numberOfHumans==4) {
			this.setPlayerFourInfo();
		}		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setResizable(false);		
		this.setVisible(true);
	}
	
	/*
	 * Method to get the player's information
	 * @return playerOneInfo - JLabel containing the information related to playerOne
	 */
	private JLabel getPlayerOneInfo() {
		playerOneInfo = new JLabel("NAME OF PLAYER 1:");
		playerOneInfo.setFont(new Font("TimesRoman", Font.BOLD, 32));
		playerOneInfo.setBounds(10,60,560,100);
		playerOneInfo.setForeground(Color.decode("#fcc729"));
		return playerOneInfo;
	} 
	/*
	 * Method to get the player's information
	 * @return playerTwoInfo - JLabel containing the information related to playerTwo
	 */
	private JLabel getPlayerTwoInfo() {
		playerTwoInfo = new JLabel("NAME OF PLAYER 2:");		
		playerTwoInfo.setFont(new Font("TimesRoman", Font.BOLD, 32));
		playerTwoInfo.setBounds(10,120,560,100);
		playerTwoInfo.setForeground(Color.decode("#fcc729"));
		return playerTwoInfo;
	}
	/*
	 * Method to get the player's information
	 * @return playerThreeInfo - JLabel containing the information related to playerOnThree
	 */
	private JLabel getPlayerThreeInfo() {
		playerThreeInfo = new JLabel("NAME OF PLAYER 3:");
		playerThreeInfo.setFont(new Font("TimesRoman", Font.BOLD, 32));
		playerThreeInfo.setBounds(10,180,560,100);
		playerThreeInfo.setForeground(Color.decode("#fcc729"));
		return playerThreeInfo;
	}
	/*
	 * Method to get the player's information
	 * @return playerFourInfo - JLabel containing the information related to playerFour
	 */
	private JLabel getPlayerFourInfo() {
		playerFourInfo = new JLabel("NAME OF PLAYER 4:");
		playerFourInfo.setFont(new Font("TimesRoman", Font.BOLD, 32));
		playerFourInfo.setBounds(10,240,560,100);
		playerFourInfo.setForeground(Color.decode("#fcc729"));
		return playerFourInfo;
		
	}
	
	/*
	 * Method to set the text for the player
	 * @return playerOneName - JTextField text for the name of the player
	 */
	
	private JTextField setPlayerOneNameTxt() {
		playerOneName = new JTextField();
		playerOneName.setBounds(340,90,130,40);
		playerOneName.setBackground(Color.decode("#fcc729"));
		playerOneName.setForeground(Color.decode("#337def"));
		playerOneName.setFont(new Font("TimesRoman", Font.BOLD, 26));
		return playerOneName;
	}
	/*
	 * Method to set the text for the player
	 * @return playerTwoName - JTextField text for the name of the player
	 */
	private JTextField setPlayerTwoNameTxt() {
		playerTwoName = new JTextField();
		playerTwoName.setBounds(340,150,130,40);
		playerTwoName.setBackground(Color.decode("#fcc729"));
		playerTwoName.setForeground(Color.decode("#337def"));
		playerTwoName.setFont(new Font("TimesRoman", Font.BOLD, 26));
		return playerTwoName;
	}
	/*
	 * Method to set the text for the player
	 * @return playerThreeName - JTextField text for the name of the player
	 */
	private JTextField setPlayerThreeNameTxt() {
		playerThreeName = new JTextField();
		playerThreeName.setBounds(340,210,130,40);
		playerThreeName.setBackground(Color.decode("#fcc729"));
		playerThreeName.setForeground(Color.decode("#337def"));
		playerThreeName.setFont(new Font("TimesRoman", Font.BOLD, 26));
		return playerThreeName;
	}
	/*
	 * Method to set the text for the player
	 * @return playerFourName - JTextField text for the name of the player
	 */
	private JTextField setPlayerFourNameTxt() {
		playerFourName = new JTextField();
		playerFourName.setBounds(340,270,130,40);
		playerFourName.setBackground(Color.decode("#fcc729"));
		playerFourName.setForeground(Color.decode("#337def"));
		playerFourName.setFont(new Font("TimesRoman", Font.BOLD, 26));
		return playerFourName;
	}
	/*
	 * Method to set the information label for the game
	 * @return informationLabel - JLabel customize label for asking the information from the players
	 */
	private JLabel setInformationLabel() {
		informationLabel = new JLabel("Player Information",SwingConstants.CENTER);
		informationLabel.setBounds(90,10,300,50);
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));
		informationLabel.setForeground(Color.decode("#fcc729"));
		informationLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#fcc729"), 4));
		return informationLabel;
	}
	
	/*
	 * Method to set the button to submit the details for One human player
	 * @return submitButtonPlayerOne - JButton for the submit action
	 */
	public JButton setSubmitButtonPlayerOne() {
		submitButtonPlayerOne = new JButton("SUBMIT");
		submitButtonPlayerOne.setFont(new Font("TimesRoman", Font.BOLD, 20));
		submitButtonPlayerOne.setBackground(Color.decode("#fcc729"));
		submitButtonPlayerOne.setForeground(Color.decode("#337def"));
		submitButtonPlayerOne.addActionListener(this);
		submitButtonPlayerOne.setFocusable(false);		
		submitButtonPlayerOne.setBounds(165, 150, 150, 50);
		return submitButtonPlayerOne;
	}
	/*
	 * Method to set the button to submit the details for two human player
	 * @return submitButtonPlayerTwo - JButton for the submit action
	 */
	public JButton setSubmitButtonPlayerTwo() {
		submitButtonPlayerTwo = new JButton("SUBMIT");
		submitButtonPlayerTwo.setFont(new Font("TimesRoman", Font.BOLD, 20));
		submitButtonPlayerTwo.setBackground(Color.decode("#fcc729"));
		submitButtonPlayerTwo.setForeground(Color.decode("#337def"));
		submitButtonPlayerTwo.addActionListener(this);
		submitButtonPlayerTwo.setFocusable(false);		
		submitButtonPlayerTwo.setBounds(165, 220, 150, 50);
		return submitButtonPlayerTwo;
	}
	/*
	 * Method to set the button to submit the details for three human player
	 * @return submitButtonPlayerThree - JButton for the submit action
	 */
	public JButton setSubmitButtonPlayerThree() {
		submitButtonPlayerThree = new JButton("SUBMIT");
		submitButtonPlayerThree.setFont(new Font("TimesRoman", Font.BOLD, 20));
		submitButtonPlayerThree.setBackground(Color.decode("#fcc729"));
		submitButtonPlayerThree.setForeground(Color.decode("#337def"));
		submitButtonPlayerThree.addActionListener(this);
		submitButtonPlayerThree.setFocusable(false);		
		submitButtonPlayerThree.setBounds(165, 270, 150, 50);		
		return submitButtonPlayerThree;
	}
	/*
	 * Method to set the button to submit the details for four human player
	 * @return submitButtonPlayerFour - JButton for the submit action
	 */
	public JButton setSubmitButtonPlayerFour() {
		submitButtonPlayerFour = new JButton("SUBMIT");
		submitButtonPlayerFour.setFont(new Font("TimesRoman", Font.BOLD, 20));
		submitButtonPlayerFour.setBackground(Color.decode("#fcc729"));
		submitButtonPlayerFour.setForeground(Color.decode("#337def"));
		submitButtonPlayerFour.addActionListener(this);
		submitButtonPlayerFour.setFocusable(false);		
		submitButtonPlayerFour.setBounds(165, 350, 150, 50);
		return submitButtonPlayerFour;
	}
	
	// method to set the information related to the player one
	private void setPlayerOneInfo() {
		this.setSize(500,250);
		this.setDisplayToCenter();		
		
		getContentPane().add(this.setInformationLabel());
		getContentPane().add(this.getPlayerOneInfo());		
		getContentPane().add(this.setPlayerOneNameTxt());
		getContentPane().add(playerOneInfo);
		getContentPane().add(this.setSubmitButtonPlayerOne());		
		
	}
	// method to set the information related to the player two
	private void setPlayerTwoInfo() {
		this.setSize(500,350);
		this.setDisplayToCenter();
		
		getContentPane().add(this.setInformationLabel());
		getContentPane().add(this.getPlayerOneInfo());
		getContentPane().add(this.getPlayerTwoInfo());
		getContentPane().add(this.setPlayerOneNameTxt());
		getContentPane().add(this.setPlayerTwoNameTxt());
		getContentPane().add(playerOneInfo);
		getContentPane().add(playerTwoInfo);
		getContentPane().add(this.setSubmitButtonPlayerTwo());	
		
	}
	// method to set the information related to the player Three
	private void setPlayerThreeInfo() {
		this.setSize(500,390);
		this.setDisplayToCenter();
		
		getContentPane().add(this.setInformationLabel());
		getContentPane().add(this.getPlayerOneInfo());
		getContentPane().add(this.getPlayerTwoInfo());	
		getContentPane().add(this.getPlayerThreeInfo());
		getContentPane().add(this.setPlayerOneNameTxt());
		getContentPane().add(this.setPlayerTwoNameTxt());
		getContentPane().add(this.setPlayerThreeNameTxt());
		getContentPane().add(playerOneInfo);
		getContentPane().add(playerTwoInfo);
		getContentPane().add(playerThreeInfo);
		getContentPane().add(this.setSubmitButtonPlayerThree());		
		
	}
	// method to set the information related to the player Four
	private void setPlayerFourInfo() {
		this.setSize(500,470);
		this.setDisplayToCenter();
		
		getContentPane().add(this.setInformationLabel());
		getContentPane().add(this.getPlayerOneInfo());
		getContentPane().add(this.getPlayerTwoInfo());	
		getContentPane().add(this.getPlayerThreeInfo());
		getContentPane().add(this.getPlayerFourInfo());
		getContentPane().add(this.setPlayerOneNameTxt());
		getContentPane().add(this.setPlayerTwoNameTxt());
		getContentPane().add(this.setPlayerThreeNameTxt());
		getContentPane().add(this.setPlayerFourNameTxt());
		
		getContentPane().add(this.setInformationLabel());
		getContentPane().add(playerOneInfo);
		getContentPane().add(playerTwoInfo);
		getContentPane().add(playerThreeInfo);
		getContentPane().add(playerFourInfo);
		getContentPane().add(this.setSubmitButtonPlayerFour());	
		
	}
	// method to get the image for the frame
	private ImageIcon getPlayerIcon() {
		playerIcon = new ImageIcon(getClass().getResource("images/user.png"));
		return playerIcon;
		
	}
	//Method to set display to the center
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}
	
	//Action performed method 
	@Override
	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		Object selected = aevt.getSource();
		
		if (selected == submitButtonPlayerOne) {
			
			if(playerOneName.getText().isEmpty()!=true) {
				this.dispose();
				BotLevel botlevel = new BotLevel(this.numberOfHumans, playerOneName.getText(), null, null, null);
			}
			
			else {
            		JOptionPane.showMessageDialog(null,"PLEASE ENTER A NAME IN THE FIELD","REQUIRED FIELD* ",JOptionPane.PLAIN_MESSAGE);
            	}
			
		}
		
		if (selected == submitButtonPlayerTwo) {
			if(playerOneName.getText().isEmpty()!=true && playerTwoName.getText().isEmpty()!=true) {
				this.dispose();
				BotLevel botlevel = new BotLevel(this.numberOfHumans, playerOneName.getText(),  playerTwoName.getText(), null, null);
			}
			else {
				JOptionPane.showMessageDialog(null,"PLEASE ENTER A NAME IN THE FIELD","REQUIRED FIELD* ",JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		if (selected == submitButtonPlayerThree) {
			if(playerOneName.getText().isEmpty()!=true && playerTwoName.getText().isEmpty()!=true && playerThreeName.getText().isEmpty()!=true) {
				this.dispose();
				BotLevel botlevel = new BotLevel(this.numberOfHumans, playerOneName.getText(), playerTwoName.getText(), playerThreeName.getText(), null);
			}
			else {
				JOptionPane.showMessageDialog(null,"PLEASE ENTER A NAME IN THE FIELD","REQUIRED FIELD* ",JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		if (selected == submitButtonPlayerFour) {
			if(playerOneName.getText().isEmpty()!=true && playerTwoName.getText().isEmpty()!=true && playerThreeName.getText().isEmpty()!=true && playerFourName.getText().isEmpty()!=true) {
			int botDifficultyLevel =0;
			this.dispose();
			ColorSelect colorSelect = new ColorSelect(this.numberOfHumans, playerOneName.getText(), playerTwoName.getText(), playerThreeName.getText(),playerFourName.getText(), botDifficultyLevel);
			}
			else {
				JOptionPane.showMessageDialog(null,"PLEASE ENTER A NAME IN THE FIELD","REQUIRED FIELD* ",JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		
		
	}
	
}
