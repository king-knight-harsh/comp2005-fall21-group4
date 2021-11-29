/*
 * GUI Component where the actual logic of playing the game 
 * is implemented. it extends GameDisplay.
 * Also implements ActionListener, MouseListener, MouseMotionListener
 * which handles User Input.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MainGameColorBlindModeTwo extends GameDisplay implements ActionListener{
	
	private JLabel informationLabel;
	private int numberOfHumanPlayers;	
	private String playerOneName, playerTwoName, playerThreeName, playerFourName;
	private int colorMode;
	private int botDifficultyLevel;
	
	private JPanel topPanel,infoPanel;
	private panelLayout playerOnePanel,playerTwoPanel,playerThreePanel, playerFourPanel;
	private JLabel playerOneInfo,playerTwoInfo,playerThreeInfo,playerFourInfo; 
	private JLabel playerOneCaptured,playerTwoCaptured,playerThreeCaptured,playerFourCaptured; 
	private JLabel playerOneReserved,playerTwoReserved,playerThreeReserved,playerFourReserved;

	
	private int currentTurn;
	private int firstButtonXCoordinate;
    private int firstButtonYCoordinate;
	
	private Color nameOfTheColor;
	
	private Random randomTurn;
	
	private Object selected =null;
	private String pieceColor;
	private JButton deSelectButton;
	private Color colorOne,colorTwo,colorThree,colorFour;
	
	
	private int playerOneCaptureCounter=0,playerTwoCaptureCounter=0,playerThreeCaptureCounter=0,playerFourCaptureCounter=0;
	private int playerOneReserveCounter=1,playerTwoReserveCounter=1,playerThreeReserveCounter=0,playerFourReserveCounter=0;
	
	
	
	/*
	 * Constructor with public visibility.
	 * @param numberOfHumanPlayers - Integer that stores the number of human players 
	 * 			who will play the game.
	 * @param player1 - String that stores the name of the first player.
	 * @param player2 - String that stores the name of the second player.
	 * @param player3 - String that stores the name of the third player.
	 * @param player4 - String that stores the name of the fourth player.
	 * @param botDifficultyLevel - Integer that stores the AI Difficulty level(i.e, "EASY" or "HARD") slected by the user.
	 * @param colorMode - Integer that stores the Color Mode selected by the User. (i.e, "Normal" or "ColorBlind")
	 */
	
	MainGameColorBlindModeTwo(int numberOfHumanPlayers, String player1,String player2,String player3,String player4, int botDifficultyLevel, int colorMode){
		this.numberOfHumanPlayers = numberOfHumanPlayers;
		this.colorOne = new Color(19,165,198);
		this.colorTwo = new Color(181,95,122);
		this.colorThree = new Color(254,41,107);
		this.colorFour = new Color(251,170,189);
		if (player1!=null && player1.isEmpty() ==false) {
			this.playerOneName=player1.toUpperCase();
		}
		
		else if(player1==null || player1.isEmpty()){
			this.playerOneName="PLAYER 1";
		}
		
		if (player2!=null && player2.isEmpty() ==false) {
			this.playerTwoName=player2.toUpperCase();
		}
		
		else if(player2==null || player2.isEmpty()){
			this.playerTwoName="PLAYER 2";
		}
		
		if (player3!=null && player3.isEmpty() ==false) {
			this.playerThreeName=player3.toUpperCase();
		}
		
		else if(player3==null || player3.isEmpty()){
			this.playerThreeName="PLAYER 3";
		}
		if (player4!=null&& player4.isEmpty() ==false) {
			this.playerFourName=player4.toUpperCase();
		}
		
		else if(player4==null || player4.isEmpty()){
			this.playerFourName="PLAYER 4";
		}		
		
		this.botDifficultyLevel = botDifficultyLevel;
		this.colorMode = colorMode;
		getContentPane().add(this.getTopPanel(),BorderLayout.NORTH);
		getContentPane().add(this.getInfoPanel(),BorderLayout.SOUTH);
		this.setPiecesOnBoard();
		
	}
	
	//This private method returns the name of first player.
	private String getPlayerOneName() {
		return this.playerOneName;
	}
	

	//Sets the top panel layout.
	public JPanel getTopPanel() {
		topPanel =  new JPanel();
		topPanel.setLayout(new FlowLayout());	
		
		topPanel.add(this.getPlayerOnePanel());
		topPanel.add(this.getPlayerTwoPanel());
		topPanel.add(this.getDeSelectButton());
		topPanel.add(this.getPlayerThreePanel());
		topPanel.add(this.getPlayerFourPanel());
		

		return topPanel;
	}
	
	//Gets the specifications required to build the info panel.
	public JPanel getInfoPanel() {
		infoPanel = new JPanel();
		infoPanel.setLayout(new FlowLayout());
		informationLabel = new  JLabel();		
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));
		informationLabel.setForeground(Color.black);		
		informationLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 5,5));		
		informationLabel.setPreferredSize(new Dimension(350,50));
		this.getFirstTurn();		
		infoPanel.add(this.informationLabel);		
		return infoPanel;
	}
	
	//Randomly selects a player's turn and keeps passing the turn to the next player.
	private int getFirstTurn() {
		randomTurn = new Random();
		currentTurn = randomTurn.nextInt(4);
		currentTurn+=1;
		if (currentTurn == 1){
			this.setInformationLabel("TURN : PLAYER 1");
		}
		else if(currentTurn == 2){
			this.setInformationLabel("TURN : PLAYER 2");
		}
		else if(currentTurn == 3){
			this.setInformationLabel("TURN : PLAYER 3");
		}
		
		else if(currentTurn == 4){
			this.setInformationLabel("TURN : PLAYER 4");
		}
		return currentTurn;
	}
	
	/*
	 * Sets the value of the label as the parameter passed.
	 * @param text - String to store the value that describes the label. 
	 */
	private void setInformationLabel(String text) {
		this.informationLabel.setText(text);
		
	}
	
	/*
	 * Sets the dimensions, coordinates and other properties for the "Unclick" button.
	 * Uses ActionListener so that the an action is performed when user clicks the button.
	 */
	private JButton getDeSelectButton() {
		deSelectButton= new panelLayout(7,7);
		deSelectButton.setText("UNCLICK");
		deSelectButton.setFont(new Font("TimesRoman", Font.BOLD, 16));
		deSelectButton.setPreferredSize(new Dimension(150,50));
		deSelectButton.setBackground(Color.white);
		deSelectButton.setForeground(Color.BLACK);
		deSelectButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		deSelectButton.setFocusable(false);
		deSelectButton.addActionListener(this);
		return deSelectButton; 
	}
	

	//Sets the layout for the Captured button which tells how any pieces of other players are captured by Player One.
	private JLabel getPlayerOneCaptureCounter() {
		playerOneCaptured = new JLabel("CAPTURED : " + this.playerOneCaptureCounter);
		playerOneCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerOneCaptured.setForeground(Color.black);
		return playerOneCaptured;
	}
	
	/*
	 * Updates the value of the Captured Pieces by Player One.
	 * @param text - String to store the number of Reserved pieces for Player One.
	 */
	private void setPlayerOneCaptureCounter(String text) {
		this.playerOneCaptured.setText(text + Integer.toString(this.playerOneCaptureCounter));
	}
	
	//Sets the layout for the Reserved Button which tells how many pieces of player one is reserved.
	private JLabel getPlayerOneReserveCounter() {
		playerOneReserved = new JLabel("RESERVED : " + this.playerOneReserveCounter);
		playerOneReserved.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerOneReserved.setForeground(Color.black);
		return playerOneReserved;
	}
	
	/*
	 * Updates the value of Reserved Pieces.
	 * @param text - String to store the number of Reserved pieces for Player One.
	 */
	private void setPlayerOneReserveCounter(String text) {
		this.playerOneReserved.setText(text + Integer.toString(this.playerOneReserveCounter));
	}
	
	//Sets the layout for the Captured button which tells how any pieces of other players are captured by Player Two.
	private JLabel getPlayerTwoCaptureCounter() {
		playerTwoCaptured = new JLabel("CAPTURED : " + this.playerTwoCaptureCounter);
		playerTwoCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerTwoCaptured.setForeground(Color.black);
		return playerTwoCaptured;
	}
	
	/*
	 * Updates the value of the Captured Pieces by Player One.
	 * @param text - String to store the number of Captured pieces by Player Two.
	 */
	private void setPlayerTwoCaptureCounter(String text) {
		this.playerTwoCaptured.setText(text + Integer.toString(this.playerTwoCaptureCounter));
	}
	
	//Sets the layout for the Reserved Button which tells how many pieces of player Two is reserved.
	private JLabel getPlayerTwoReserveCounter() {
		playerTwoReserved = new JLabel("RESERVED : " + this.playerTwoReserveCounter);
		playerTwoReserved.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerTwoReserved.setForeground(Color.black);
		return playerTwoReserved;
	}
	
	/*
	 * Updates the value of Reserved Pieces.
	 * @param text - String to store the number of Reserved pieces for Player Two.
	 */
	private void setPlayerTwoReserveCounter(String text) {
		this.playerTwoReserved.setText(text + Integer.toString(this.playerTwoReserveCounter));
	}
	
	//Sets the layout for the Captured button which tells how any pieces of other players are captured by Player Three.
	private JLabel getPlayerThreeCaptureCounter() {
		playerThreeCaptured = new JLabel("CAPTURED : " + this.playerThreeCaptureCounter);
		playerThreeCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerThreeCaptured.setForeground(Color.black);
		return playerThreeCaptured;
	}
	
	/*
	 * Updates the value of the Captured Pieces by Player Three.
	 * @param text - String to store the number of Reserved pieces for Player Three.
	 */
	private void setPlayerThreeCaptureCounter(String text) {
		this.playerThreeCaptured.setText(text + Integer.toString(this.playerThreeCaptureCounter));
	}
	
	//Sets the layout for the Reserved Button which tells how many pieces of player Three is reserved.
	private JLabel getPlayerThreeReserveCounter() {
		playerThreeReserved = new JLabel("RESERVED : " + this.playerThreeReserveCounter);
		playerThreeReserved.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerThreeReserved.setForeground(Color.black);
		return playerThreeReserved;
	}
	
	/*
	 * Updates the value of Reserved Pieces.
	 * @param text - String to store the number of Reserved pieces for Player Three.
	 */
	private void setPlayerThreeReserveCounter(String text) {
		this.playerThreeReserved.setText(text + Integer.toString(this.playerThreeReserveCounter));
	}
	
	//Sets the layout for the Captured button which tells how any pieces of other players are captured by Player Four.
	private JLabel getPlayerFourCaptureCounter() {
		playerFourCaptured = new JLabel("CAPTURED : " + this.playerFourCaptureCounter);
		playerFourCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerFourCaptured.setForeground(Color.black);
		return playerFourCaptured;
	}
	
	/*
	 * Updates the value of the Captured Pieces by Player Four.
	 * @param text - String to store the number of Reserved pieces for Player Four.
	 */
	private void setPlayerFourCaptureCounter(String text) {
		this.playerFourCaptured.setText(text + Integer.toString(this.playerFourCaptureCounter));
	}
	
	//Sets the layout for the Reserved Button which tells how many pieces of player Four is reserved.
	private JLabel getPlayerFourReserveCounter() {
		playerFourReserved = new JLabel("RESERVED : " + this.playerFourReserveCounter);
		playerFourReserved.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerFourReserved.setForeground(Color.black);
		return playerFourReserved;
	}
	
	/*
	 * Updates the value of Reserved Pieces.
	 * @param text - String to store the number of Reserved pieces for Player Four.
	 */
	private void setPlayerFourReserveCounter(String text) {
		this.playerFourReserved.setText(text + Integer.toString(this.playerFourReserveCounter));
	}
	
	//Sets the layout for Player one panel.
	public panelLayout getPlayerOnePanel() {
		playerOnePanel =new panelLayout(0,0);
		playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.Y_AXIS));
		playerOnePanel.setPreferredSize(new Dimension(150,70));
		playerOnePanel.setBackground(Color.white);
		
		
		playerOneInfo = new JLabel();
		playerOneInfo.setText(String.valueOf(this.playerOneName));
		
		playerOneInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerOneInfo.setForeground(Color.black);
		playerOneInfo.setVisible(true);
		
		
		
		playerOnePanel.add(playerOneInfo);
		playerOnePanel.add(this.getPlayerOneCaptureCounter());
		playerOnePanel.add(this.getPlayerOneReserveCounter());
		playerOnePanel.setBorder(BorderFactory.createLineBorder(colorOne, 3));
		playerOnePanel.addActionListener(this);
		
		return playerOnePanel;
	}
	
	//Sets the layout for Player Two panel.
	public panelLayout getPlayerTwoPanel() {
		playerTwoPanel =new panelLayout(0,1);
		playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.Y_AXIS));
		playerTwoPanel.setPreferredSize(new Dimension(150,70));
		playerTwoPanel.setBackground(Color.white);
		
		playerTwoInfo = new JLabel();
		playerTwoInfo.setText(String.valueOf(this.playerTwoName));
		playerTwoInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerTwoInfo.setForeground(Color.black);
		
		
		playerTwoPanel.add(playerTwoInfo);
		playerTwoPanel.add(this.getPlayerTwoCaptureCounter());
		playerTwoPanel.add(this.getPlayerTwoReserveCounter());
		playerTwoPanel.setBorder(BorderFactory.createLineBorder(colorTwo, 3));
		playerTwoPanel.addActionListener(this);
	
		return playerTwoPanel;
	}
	
	//Sets the layout for Player Three panel.
	public panelLayout getPlayerThreePanel() {
		playerThreePanel =new panelLayout(0,6);
		playerThreePanel.setLayout(new BoxLayout(playerThreePanel, BoxLayout.Y_AXIS));
		playerThreePanel.setPreferredSize(new Dimension(150,70));
		playerThreePanel.setBackground(Color.white);
		
		playerThreeInfo = new JLabel();
		playerThreeInfo.setText(String.valueOf(this.playerThreeName));
		playerThreeInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerThreeInfo.setForeground(Color.black);
		
		
		
		playerThreePanel.add(playerThreeInfo);
		playerThreePanel.add(this.getPlayerThreeCaptureCounter());
		playerThreePanel.add(this.getPlayerThreeReserveCounter());
		playerThreePanel.setBorder(BorderFactory.createLineBorder(colorThree, 3));
		playerThreePanel.addActionListener(this);
		
		return playerThreePanel;
	}
	
	//Sets the layout for Player Four panel.
	public panelLayout getPlayerFourPanel() {
		playerFourPanel =new panelLayout(0,7);
		playerFourPanel.setLayout(new BoxLayout(playerFourPanel, BoxLayout.Y_AXIS));
		playerFourPanel.setPreferredSize(new Dimension(150,70));
		playerFourPanel.setBackground(Color.white);
		
		playerFourInfo = new JLabel();
		playerFourInfo.setText(String.valueOf(this.playerFourName));
		playerFourInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerFourInfo.setForeground(Color.black);
		
		
		playerFourPanel.add(playerFourInfo);
		playerFourPanel.add(this.getPlayerFourCaptureCounter());
		playerFourPanel.add(this.getPlayerFourReserveCounter());
		playerFourPanel.setBorder(BorderFactory.createLineBorder(colorFour, 3));
		playerFourPanel.addActionListener(this);
		
		return playerFourPanel;
	}
	
	
	//Setup the game board using four different coloured pieces.
	public void setPiecesOnBoard() {
		super.panelArray[0][2].setBackground(colorOne);
		super.panelArray[0][3].setBackground(colorOne);
		super.panelArray[0][4].setBackground(colorTwo);
		super.panelArray[0][5].setBackground(colorFour);
		((panelLayout) super.panelArray[0][2]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[0][3]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[0][4]).getStackForPiece().add("2");
		((panelLayout) super.panelArray[0][5]).getStackForPiece().add("4");
		
		super.panelArray[1][1].setBackground(colorFour);
		super.panelArray[1][2].setBackground(colorFour);
		super.panelArray[1][3].setBackground(colorFour);
		super.panelArray[1][4].setBackground(colorTwo);
		super.panelArray[1][5].setBackground(colorFour);
		super.panelArray[1][6].setBackground(colorTwo);
		((panelLayout) super.panelArray[1][1]).getStackForPiece().add("4");
		((panelLayout) super.panelArray[1][2]).getStackForPiece().add("4");
		((panelLayout) super.panelArray[1][3]).getStackForPiece().add("4");
		((panelLayout) super.panelArray[1][4]).getStackForPiece().add("2");
		((panelLayout) super.panelArray[1][5]).getStackForPiece().add("4");
		((panelLayout) super.panelArray[1][6]).getStackForPiece().add("2");
		
		super.panelArray[2][0].setBackground(colorOne);
		super.panelArray[2][1].setBackground(colorOne);
		super.panelArray[2][2].setBackground(colorOne);
		super.panelArray[2][3].setBackground(colorOne);
		super.panelArray[2][4].setBackground(colorTwo);
		super.panelArray[2][5].setBackground(colorFour);
		super.panelArray[2][6].setBackground(colorTwo);
		super.panelArray[2][7].setBackground(colorFour);
		((panelLayout) super.panelArray[2][0]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[2][1]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[2][2]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[2][3]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[2][4]).getStackForPiece().add("2");
		((panelLayout) super.panelArray[2][5]).getStackForPiece().add("4");
		((panelLayout) super.panelArray[2][6]).getStackForPiece().add("2");
		((panelLayout) super.panelArray[2][7]).getStackForPiece().add("4");
		
		
		
		super.panelArray[3][0].setBackground(colorFour);
		super.panelArray[3][1].setBackground(colorFour);
		super.panelArray[3][2].setBackground(colorFour);
		super.panelArray[3][3].setBackground(colorFour);
		super.panelArray[3][4].setBackground(colorTwo);
		super.panelArray[3][5].setBackground(colorFour);
		super.panelArray[3][6].setBackground(colorTwo);
		super.panelArray[3][7].setBackground(colorFour);
		((panelLayout) super.panelArray[3][0]).getStackForPiece().add("4");
		((panelLayout) super.panelArray[3][1]).getStackForPiece().add("4");
		((panelLayout) super.panelArray[3][2]).getStackForPiece().add("4");
		((panelLayout) super.panelArray[3][3]).getStackForPiece().add("4");
		((panelLayout) super.panelArray[3][4]).getStackForPiece().add("2");
		((panelLayout) super.panelArray[3][5]).getStackForPiece().add("4");
		((panelLayout) super.panelArray[3][6]).getStackForPiece().add("2");
		((panelLayout) super.panelArray[3][7]).getStackForPiece().add("4");
		
		
		super.panelArray[4][0].setBackground(colorThree);
		super.panelArray[4][1].setBackground(colorOne);
		super.panelArray[4][2].setBackground(colorThree);
		super.panelArray[4][3].setBackground(colorOne);
		super.panelArray[4][4].setBackground(colorThree);
		super.panelArray[4][5].setBackground(colorThree);
		super.panelArray[4][6].setBackground(colorThree);
		super.panelArray[4][7].setBackground(colorThree);
		((panelLayout) super.panelArray[4][0]).getStackForPiece().add("3");
		((panelLayout) super.panelArray[4][1]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[4][2]).getStackForPiece().add("3");
		((panelLayout) super.panelArray[4][3]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[4][4]).getStackForPiece().add("3");
		((panelLayout) super.panelArray[4][5]).getStackForPiece().add("3");
		((panelLayout) super.panelArray[4][6]).getStackForPiece().add("3");
		((panelLayout) super.panelArray[4][7]).getStackForPiece().add("3");
		
		super.panelArray[5][0].setBackground(colorThree);
		super.panelArray[5][1].setBackground(colorOne);
		super.panelArray[5][2].setBackground(colorThree);
		super.panelArray[5][3].setBackground(colorOne);
		super.panelArray[5][4].setBackground(colorTwo);
		super.panelArray[5][5].setBackground(colorTwo);
		super.panelArray[5][6].setBackground(colorTwo);
		super.panelArray[5][7].setBackground(colorTwo);
		((panelLayout) super.panelArray[5][0]).getStackForPiece().add("3");
		((panelLayout) super.panelArray[5][1]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[5][2]).getStackForPiece().add("3");
		((panelLayout) super.panelArray[5][3]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[5][4]).getStackForPiece().add("2");
		((panelLayout) super.panelArray[5][5]).getStackForPiece().add("2");
		((panelLayout) super.panelArray[5][6]).getStackForPiece().add("2");
		((panelLayout) super.panelArray[5][7]).getStackForPiece().add("2");
		
		
		super.panelArray[6][1].setBackground(colorOne);
		super.panelArray[6][2].setBackground(colorThree);
		super.panelArray[6][3].setBackground(colorOne);
		super.panelArray[6][4].setBackground(colorThree);
		super.panelArray[6][5].setBackground(colorThree);
		super.panelArray[6][6].setBackground(colorThree);
		((panelLayout) super.panelArray[6][1]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[6][2]).getStackForPiece().add("3");
		((panelLayout) super.panelArray[6][3]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[6][4]).getStackForPiece().add("3");
		((panelLayout) super.panelArray[6][5]).getStackForPiece().add("3");
		((panelLayout) super.panelArray[6][6]).getStackForPiece().add("3");
		
		
		
		super.panelArray[7][2].setBackground(colorThree);
		super.panelArray[7][3].setBackground(colorOne);
		super.panelArray[7][4].setBackground(colorTwo);
		super.panelArray[7][5].setBackground(colorTwo);
		((panelLayout) super.panelArray[7][2]).getStackForPiece().add("3");
		((panelLayout) super.panelArray[7][3]).getStackForPiece().add("1");
		((panelLayout) super.panelArray[7][4]).getStackForPiece().add("2");
		((panelLayout) super.panelArray[7][5]).getStackForPiece().add("2");	
		
		for (int row=0; row<8;row++) {
			for(int column=0; column<8;column++) {
				String textStack = ((panelLayout)super.panelArray[row][column]).getStackForPiece().toString();
				((panelLayout)super.panelArray[row][column]).setText(textStack);
				((panelLayout)super.panelArray[row][column]).setForeground(Color.white);
				((panelLayout)super.panelArray[row][column]).setFocusable(false);
				((panelLayout)super.panelArray[row][column]).setFont(new Font("Arial", Font.BOLD, 12));
			}
		}
		
		
	}
	
	
	/*
	 * This private method checks if the move made by the player is valid or not,
	 * if not then opens a dialog box asking the player to make a valid move.
	 * @param selected2 - Object to store the co-ordinates of the selected piece.
	 * @param lengthofTheStack - Integer to store the number of pieces in a stack.
	 */
	private boolean checkValidMove(Object selected2, int lengthOfTheStack) {
		// Calculating the distance for the xCoordinate
		int xCordinateDistance = ((panelLayout) selected2).getXCoordinate() - ((panelLayout) selected).getXCoordinate();
		// Calculating the distance for the yCoordinate
		int yCordinateDistance = ((panelLayout) selected2).getYCoordinate() - ((panelLayout) selected).getYCoordinate();
		// Absolute distance as the sum of xCoordinate and yCoordinate.
		int distanceBetweenBtn = Math.abs(xCordinateDistance) + Math.abs(yCordinateDistance); 
		
		if(distanceBetweenBtn<=lengthOfTheStack){
			return true;
			
		}
		else {
			JOptionPane.showMessageDialog(null,"Please do a valid move","Invalid Move ",JOptionPane.PLAIN_MESSAGE,this.getWelcomeIcon());
			this.removeHighlight();
			selected=null;
			return false;
		}
		
		
	}
	
	
	/*
	 * This method returns the distance between initial position of the piece
	 * and the final position of the piece when a move is played.
	 * @param selected2 - Object to store the co-ordinates of the selected piece.
	 * @param lengthoftheStack - Integer to store the number of pieces in a stack.
	 */
	private int getDistanceBetweenMove(Object selected2, int lengthOfTheStack) {
		// Calculating the distance for the xCoordinate
				int xCordinateDistance = ((panelLayout) selected2).getXCoordinate() - ((panelLayout) selected).getXCoordinate();
				// Calculating the distance for the yCoordinate
				int yCordinateDistance = ((panelLayout) selected2).getYCoordinate() - ((panelLayout) selected).getYCoordinate();
				// Absolute distance as the sum of xCoordinate and yCoordinate.
				int distanceBetweenBtn = Math.abs(xCordinateDistance) + Math.abs(yCordinateDistance); 
				
				return distanceBetweenBtn;
	}
	
	
	/*
	 * Checks if the piece that is captured is of the player or other players,
	 * then it adds the piece to the reserved pile or captured pile respectively.
	 * @param buttonXCoordinate - Integer to store the x-coordinate of the selected button.
	 * @param buttonYCoordinate - Integer to store the y-coordinate of the selected button.
	 */
	private void checkCapturedPiece(int buttonXCoordinate, int buttonYCoordinate ) {
		int stackLength =((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().size();
		
		if (stackLength>5) {
				if(((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().get(stackLength-1)=="1") {
					int counter=0;
					while(counter<(stackLength-5)) {
						String removedPiece = ((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().remove(0);
						if (removedPiece=="1") {
							this.playerOneReserveCounter+=1;
							counter++;
						}
						else {
							this.playerOneCaptureCounter+=1;
							counter++;
						}
						
					}
					
					
				}
				else if(((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().get(stackLength-1)=="2") {
					int counter=0;
					while(counter<(stackLength-5)) {
						String removedPiece = ((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().remove(0);
						if (removedPiece=="2") {
							this.playerTwoReserveCounter+=1;
							counter++;
						}
						else {
							this.playerTwoCaptureCounter+=1;
							counter++;
						}
					}
					
				
				}
				else if(((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().get(stackLength-1)=="3") {
					int counter=0;
					while(counter<(stackLength-5)) {
						String removedPiece = ((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().remove(0);
						if (removedPiece=="3") {
							this.playerThreeReserveCounter+=1;
							counter++;
						}
						else {
							this.playerThreeCaptureCounter+=1;
							counter++;
						}
					}
					
					
				}
				else if(((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().get(stackLength-1)=="4") {
					int counter=0;
					while(counter<(stackLength-5)) {
						String removedPiece = ((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().remove(0);
						if (removedPiece=="4") {
							this.playerFourReserveCounter+=1;
							counter++;
						}
						else {
							this.playerFourCaptureCounter+=1;
							counter++;
						}
					}
					
				}
				
				this.setPlayerOneCaptureCounter("CAPTURED : ");
				this.setPlayerTwoCaptureCounter("CAPTURED : ");
				this.setPlayerThreeCaptureCounter("CAPTURED : ");
				this.setPlayerFourCaptureCounter("CAPTURED : ");
				
				this.setPlayerOneReserveCounter("RESERVED : ");
				this.setPlayerTwoReserveCounter("RESERVED : ");
				this.setPlayerThreeReserveCounter("RESERVED : ");
				this.setPlayerFourReserveCounter("RESERVED : ");
			}
		
	}

	/*
	 * Moves the plie to one piece distance with a valid move
	 * and updates the number of pieces in each pile.
	 * @param selected - Object to store the co-ordinates of the initial position of the selected piece.
	 * @param selected2 - object to store the Co-ordinates of the final position of the selected piece.
	 */
	public void moveOnePiece(int firstButtonXCoordinate,int firstButtonYCoordinate, int secondButtonXCoordinate, int secondButtonYCoordinate) {
        int moveDistance = 1;
		for(int stackColor=0;stackColor<moveDistance;stackColor++) {
    		int stackSize = ((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size()-1;
        	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(stackSize);		            	
            ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
            
        }
	}
	
	/*
	 * Moves the pile  to two piece distance with a valid move
	 * and updates the number of pieces in each pile.
	 * @param selected - Object to store the co-ordinates of the initial position of the selected piece.
	 * @param selected2 - object to store the Co-ordinates of the final position of the selected piece.
	 */
	public void moveTwoPiece(int firstButtonXCoordinate,int firstButtonYCoordinate, int secondButtonXCoordinate, int secondButtonYCoordinate) {
        int moveDistance = 2;
        int stackSize = ((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size();
    	if(stackSize == 2) {
    		for(int stackColor=0;stackColor<moveDistance;stackColor++) {
            	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(0);		            	
                ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
                
            }
    	}
    	else if(stackSize == 3) {
    		for(int stackColor=0;stackColor<moveDistance;stackColor++) {
            	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(1);		            	
                ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
                
            }
    	}
    	else if(stackSize == 4) {
    		for(int stackColor=0;stackColor<moveDistance;stackColor++) {
            	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(2);		            	
                ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
                
            }
    	}
    	else if(stackSize == 5) {
    		for(int stackColor=0;stackColor<moveDistance;stackColor++) {
            	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(3);		            	
                ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
                
            }
    	}
	}
	
	/*
	 * Moves the pile  to three piece distance with a valid move
	 * and updates the number of pieces in each pile.
	 * @param selected - Object to store the co-ordinates of the initial position of the selected piece.
	 * @param selected2 - object to store the Co-ordinates of the final position of the selected piece.
	 */
	public void moveThreePiece(int firstButtonXCoordinate,int firstButtonYCoordinate, int secondButtonXCoordinate, int secondButtonYCoordinate) {
		
        int moveDistance = 3;
        int stackSize = ((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size();
    	if(stackSize == 3) {
    		for(int stackColor=0;stackColor<moveDistance;stackColor++) {
            	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(0);		            	
                ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
                
            }
    	}
    	else if(stackSize == 4) {
    		for(int stackColor=0;stackColor<moveDistance;stackColor++) {
            	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(1);		            	
                ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
                
            }
    	}
    	else if(stackSize == 5) {
    		for(int stackColor=0;stackColor<moveDistance;stackColor++) {
            	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(2);		            	
                ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
                
            }
    	}
	}
	
	/*
	 * Moves the pile  to four piece distance with a valid move
	 * and updates the number of pieces in each pile.
	 * @param selected - Object to store the co-ordinates of the initial position of the selected piece.
	 * @param selected2 - object to store the Co-ordinates of the final position of the selected piece.
	 */
	public void moveFourPiece(int firstButtonXCoordinate,int firstButtonYCoordinate, int secondButtonXCoordinate, int secondButtonYCoordinate) {
        int moveDistance = 4;
        int stackSize = ((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size();
    	if(stackSize == 4) {
    		for(int stackColor=0;stackColor<moveDistance;stackColor++) {
            	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(0);		            	
                ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
                
            }
    	}
    	else if(stackSize == 5) {
    		for(int stackColor=0;stackColor<moveDistance;stackColor++) {
            	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(1);		            	
                ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
                
            }
    	}
	}
	
	/*
	 * Moves the pile  to five piece distance with a valid move
	 * and updates the number of pieces in each pile.
	 * @param selected - Object to store the co-ordinates of the initial position of the selected piece.
	 * @param selected2 - object to store the Co-ordinates of the final position of the selected piece.
	 */
	public void moveFivePiece(int firstButtonXCoordinate,int firstButtonYCoordinate, int secondButtonXCoordinate, int secondButtonYCoordinate) {
		
        int moveDistance = 5;
        for(int stackColor=0;stackColor<moveDistance;stackColor++) {
    		int stackSize = ((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size()-1;
        	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(0);		            	
            ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
            
        }
	}
	
	/*
	 * Highlights the pieces selected by the player during their turn.
	 */
	private void setHighlight(){
		if (selected == null) {
			return;
		}
		else {
			firstButtonXCoordinate = ((panelLayout) selected).getXCoordinate();
            firstButtonYCoordinate = ((panelLayout) selected).getYCoordinate();
			nameOfTheColor = panelArray[firstButtonXCoordinate][firstButtonYCoordinate].getBackground();
			if (nameOfTheColor.equals(colorTwo)) {
				panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.decode("#FCE3E9"));
			}
			else if (nameOfTheColor.equals(colorOne)) {
				panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.decode("#B1D9F0"));
			}
			else if (nameOfTheColor.equals(colorFour)) {
				panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.decode("#B1D9F0"));
			}
			else if (nameOfTheColor.equals(colorThree)) {
				panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.decode("#B1D9F0"));
			}
			
		}
	}
	
	/*
	 * Removes the higlight from the the pieces selected by the player during their turn.
	 */
	private void removeHighlight(){
		if (selected == null) {
			return;
		}
		else {
			if (this.currentTurn ==1 ) {
            	panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(colorOne);
            }
            else if (this.currentTurn==2) {
            	panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(colorTwo);
            }
            else if (this.currentTurn ==3 ) {
            	panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(colorThree);
            }
            else {
            	panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(colorFour);
            	}
            }
		}
	
	
	/*
	 * Method to implement the color change logic for the pieces
	 * @param x - Integer x-coordinate of the first selected piece
	 * @param y - Integer y-coordinate of the first selected piece
	 * @param x2 - Integer x-coordinate of the second selected piece
	 * @param y2 - Integer y-coordinate of the second selected piece
	 */
	 
	private void botColorChangeLogic(int x, int y, int x2, int y2) {
		if(pieceColor=="B") {
        	((panelLayout)super.panelArray[x2][y2]).setBackground(Color.blue);
        }
        else if(pieceColor=="R") {
        	((panelLayout)super.panelArray[x2][y2]).setBackground(Color.red);
        }
        else if(pieceColor=="G") {
        	((panelLayout)super.panelArray[x2][y2]).setBackground(Color.green);
        }
        else if(pieceColor=="Y") {
        	((panelLayout)super.panelArray[x2][y2]).setBackground(Color.yellow);
        }
        
        
        if(((panelLayout)super.panelArray[x][y]).getStackForPiece().isEmpty()) {
        	((panelLayout)super.panelArray[x][y]).setBackground(Color.white);
        }
        else if(((panelLayout)super.panelArray[x][y]).getStackForPiece().isEmpty()==false) {
        	int newStackSize = ((panelLayout)super.panelArray[x][y]).getStackForPiece().size()-1;
        	pieceColor = ((panelLayout) super.panelArray[x][y]).getStackForPiece().get(newStackSize);
        	if(pieceColor=="B") {
            	((panelLayout)super.panelArray[x][y]).setBackground(Color.blue);
            }
            else if(pieceColor=="R") {
            	((panelLayout)super.panelArray[x][y]).setBackground(Color.red);
            }
            else if(pieceColor=="G") {
            	((panelLayout)super.panelArray[x][y]).setBackground(Color.green);
            }
            else if(pieceColor=="Y") {
            	((panelLayout)super.panelArray[x][y]).setBackground(Color.yellow);
            }
        }
	}
	
	
	/*
	 * Implements the functioning of the Easy AI Difficulty level . 
	 * Once the turn of the human players is done, the AI players 
	 * take their turn. 
	 */
	private void easyAI() {
		if(playerTwoReserveCounter>0 && currentTurn == 2) {
			int x=0;
			int y=0;
			Random randomx;
			Random randomy; 
			randomx = new Random();
			x = randomx.nextInt(7);
			randomy = new Random();
			y = randomy.nextInt(7);			
			this.playFromReservePlayerTwo(x, y);
			this.checkCapturedPiece(x, y);
		}
		if(playerThreeReserveCounter>0 && currentTurn == 3) {
			int x=0;
			int y=0;
			Random randomx;
			Random randomy; 
			randomx = new Random();
			x = randomx.nextInt(7);
			randomy = new Random();
			y = randomy.nextInt(7);			
			this.playFromReservePlayerThree(x, y);
			this.checkCapturedPiece(x, y);
		}
		else if(playerFourReserveCounter>0 && currentTurn == 4) {
			int x=0;
			int y=0;
			Random randomx;
			Random randomy; 
			randomx = new Random();
			x = randomx.nextInt(7);
			randomy = new Random();
			y = randomy.nextInt(7);			
			this.playFromReservePlayerFour(x, y);
			this.checkCapturedPiece(x, y);
		}
		else {
			boolean check = false;
 			boolean colorPieceAvailable = false;
			int x=0;
			int y=0;
			int x2=0;
			int y2=0;
			Random randomx;
			Random randomy;
			while (check == false) {
				randomx = new Random();
				x = randomx.nextInt(7);
				randomy = new Random();
				y = randomy.nextInt(7);
		            if (this.currentTurn==2) {
		            	check = true;
		            	if (panelArray[x][y].getBackground()!= Color.red) {
		            		for (int row=0; row<8;row++){
		    					for(int column=0; column<8;column++) {
		    						if((Color)super.panelArray[row][column].getBackground() == Color.red){
		    							colorPieceAvailable = true;
		    							x=row;
		    							y=column;
		    							check=true;
		    							break;
		    						}						
		    					}
		    					if(colorPieceAvailable == true) {
		    						break;
		    					}
		    				} 
		            		 
		            	}
		            }
		            else if (this.currentTurn ==3 ) {
		            	check = true;
		            	if (panelArray[x][y].getBackground()!= Color.green) {
		            		for (int row=0; row<8;row++){
		    					for(int column=0; column<8;column++) {
		    						if((Color)super.panelArray[row][column].getBackground() == Color.green){
		    							colorPieceAvailable = true;
		    							x=row;
		    							y=column;
		    							check=true;
		    							break;
		    						}						
		    					}
		    					if(colorPieceAvailable == true) {
		    						break;
		    					}
		    				}
		            	}
		            }
		            else if (this.currentTurn==4) {
		            	check = true;
		            	if (panelArray[x][y].getBackground()!= Color.yellow) {
		            		for (int row=0; row<8;row++){
		    					for(int column=0; column<8;column++) {
		    						if((Color)super.panelArray[row][column].getBackground() == Color.yellow){
		    							colorPieceAvailable = true;
		    							x=row;
		    							y=column;
		    							check=true;
		    							break;
		    						}						
		    					}
		    					if(colorPieceAvailable == true) {
		    						break;
		    					}
		    				}
		            	}
		            }
			}
			
			
			
			if ((x == 0 && y ==1)|| x == 0) {
				x2=x+1;
				y2=y;
				this.moveOnePiece(x, y, x2, y2);
			}
			else if ((x == 0 && y ==5)||(y == 7)) {
				x2=x+1;
				y2=y;
				this.moveOnePiece(x, y, x2, y2);
			}
			else if ((x == 1 && y ==1)||(x == 1 && y ==6)) {
				x2=x+1;
				y2=y;
				this.moveOnePiece(x, y, x2, y2);
			}
			else if ((x == 6 && y ==1) || (x == 6 && y ==6) || x==7 ) {
				x2=x-1;
				y2=y2;
				this.moveOnePiece(x, y, x2, y2);
			}
			else if (y == 0) {
				x2 = x;
				y2 = y+1;
				this.moveOnePiece(x, y, x2, y2);
			}
			else {
				Random randomNumber = new Random();
				int number=randomNumber.nextInt(1);
				if(number==0) {
					x2=x+1;
					y2=y;
					this.moveOnePiece(x, y, x2, y2);
				}
				else {
					x2=x;
					y2=y+1;
					this.moveOnePiece(x, y, x2, y2);
				}
			}

			this.botColorChangeLogic(x, y, x2, y2);
			this.checkCapturedPiece(x2, y2);
			this.setStackText(x, y, x2, y2);
			
			int firstTurn = currentTurn;
            
            if(currentTurn==4) {
            	currentTurn=1;
            	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
            }
            else {
            	currentTurn++;
            	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
            }				                
            
            this.checkGameWinner(currentTurn);
            this.checkGameWinner(currentTurn);
            this.checkGameWinner(currentTurn);
            if(currentTurn==firstTurn) {
            	for (int row=0; row<8;row++){
        			for(int column=0; column<8;column++) {				        	
        				panelArray[row][column].setEnabled(false);
        			}
        		}
            	this.setInformationLabel("PLAYER " + currentTurn + " WON!");
            }
			
		}
	 			
			}
	
	/*
	 *Implements the funtioning of the Hard AI Difficulty level . 
	 */
	private void hardAI() {
		boolean check = false;
		int x=0;
		int y=0;
		int x2=0;
		int y2=0;
		Random randomx;
		Random randomy;
		
		while (check == false) {
			randomx = new Random();
			x = randomx.nextInt(7);
			randomy = new Random();
			y = randomy.nextInt(7);
	            if (this.currentTurn==2) {
	            	check = true;
	            	if (panelArray[x][y].getBackground()!= colorTwo) {
	            		check = false;
	            	}
	            }
	            else if (this.currentTurn ==3 ) {
	            	check = true;
	            	if (panelArray[x][y].getBackground()!= colorThree) {
	            		check = false;
	            	}
	            }
	            else if (this.currentTurn==4) {
	            	check = true;
	            	if (panelArray[x][y].getBackground()!= colorFour) {
	            		check = false;
	            	}
	            }
		}
	
		if(((panelLayout) super.panelArray[x][y]).getStackForPiece().size()==1){
			if (x == 0 && y ==1) {
				x2 = x + 1;
				y2 = y;	
			}
			else if (x == 0 && y ==5) {
				x2 = x ;
				y2 = y-1;	
			}
			else if (x == 1 && y ==1) {
				x2 = x +1;
				y2 = y;	
			}
			else if (x == 1 && y ==6) {
				x2 = x+1;
				y2 = y;	
			}
			else if (x == 6 && y ==1) {
				x2 = x -1;
				y2 = y;	
			}
			else if (x == 6 && y ==6) {
				x2 = x-1;
				y2 = y;	
			}
			else if (x == 0) {
				x2 = x + 1;
				y2 = y;
			}
			else if (y == 7) {
				x2 = x;
				y2 = y-1;				
			}
			else if (y == 0) {
				x2 = x;
				y2 = y+1;				
			}
			else if(x==7) {
				x2 = x - 1;
				y2 = y;
			}
			else {
				x2 = x + 1;
				y2 = y ;
			}
			
		}
		else if(((panelLayout) super.panelArray[x][y]).getStackForPiece().size()==2){
			
			if (x == 0 && y ==1) {
				x2 = x + 2;
				y2 = y;	
			}
			else if (x == 0 && y ==5) {
				x2 = x ;
				y2 = y-2;	
			}
			else if (x == 1 && y ==1) {
				x2 = x +2;
				y2 = y;	
			}
			else if (x == 1 && y ==6) {
				x2 = x+2;
				y2 = y;	
			}
			else if (x == 6 && y ==1) {
				x2 = x -2;
				y2 = y;	
			}
			else if (x == 6 && y ==6) {
				x2 = x-2;
				y2 = y;	
			}
			else if (x == 0) {
				x2 = x + 2;
				y2 = y;
			}
			else if (y == 7) {
				x2 = x;
				y2 = y-2;				
			}
			else if (y == 0) {
				x2 = x;
				y2 = y+2;				
			}
			else if(x==7) {
				x2 = x - 2;
				y2 = y;
			}
			else {
				x2 = x + 1;
				y2 = y + 1;
			}
		}
		else if(((panelLayout) super.panelArray[x][y]).getStackForPiece().size()==3){
			
			if (x == 0 && y ==1) {
				x2 = x + 3;
				y2 = y;	
			}
			else if (x == 0 && y ==5) {
				x2 = x ;
				y2 = y-3;	
			}
			else if (x == 1 && y ==1) {
				x2 = x +3;
				y2 = y;	
			}
			else if (x == 1 && y ==6) {
				x2 = x+3;
				y2 = y;	
			}
			else if (x == 6 && y ==1) {
				x2 = x -3;
				y2 = y;	
			}
			else if (x == 6 && y ==6) {
				x2 = x-3;
				y2 = y;	
			}
			else if (x == 0) {
				x2 = x + 3;
				y2 = y;
			}
			else if (y == 7) {
				x2 = x;
				y2 = y-3;				
			}
			else if (y == 0) {
				x2 = x;
				y2 = y+3;				
			}
			else if(x==7) {
				x2 = x - 3;
				y2 = y;
			}
			else {
				x2 = x + 2;
				y2 = y + 1;
			}
		}
		else if(((panelLayout) super.panelArray[x][y]).getStackForPiece().size()==4){
			if (x == 0 && y ==1) {
				x2 = x + 4;
				y2 = y;	
			}
			else if (x == 0 && y ==5) {
				x2 = x ;
				y2 = y-4;	
			}
			else if (x == 1 && y ==1) {
				x2 = x +4;
				y2 = y;	
			}
			else if (x == 1 && y ==6) {
				x2 = x+4;
				y2 = y;	
			}
			else if (x == 6 && y ==1) {
				x2 = x -4;
				y2 = y;	
			}
			else if (x == 6 && y ==6) {
				x2 = x-4;
				y2 = y;	
			}
			else if (x == 0) {
				x2 = x + 4;
				y2 = y;
			}
			else if (y == 7) {
				x2 = x;
				y2 = y-4;				
			}
			else if (y == 0) {
				x2 = x;
				y2 = y+4;				
			}
			else if(x==7) {
				x2 = x - 4;
				y2 = y;
			}
			else {
				x2 = x + 2;
				y2 = y +2;
			}
		}
		else if(((panelLayout) super.panelArray[x][y]).getStackForPiece().size()==5){
			if (x == 0 && y ==1) {
				x2 = x + 5;
				y2 = y;	
			}
			else if (x == 0 && y ==5) {
				x2 = x ;
				y2 = y-5;	
			}
			else if (x == 1 && y ==1) {
				x2 = x +5;
				y2 = y;	
			}
			else if (x == 1 && y ==6) {
				x2 = x+5;
				y2 = y;	
			}
			else if (x == 6 && y ==1) {
				x2 = x -5;
				y2 = y;	
			}
			else if (x == 6 && y ==6) {
				x2 = x-5;
				y2 = y;	
			}
			else if (x == 0) {
				x2 = x + 5;
				y2 = y;
			}
			else if (y == 7) {
				x2 = x;
				y2 = y-5;				
			}
			else if (y == 0) {
				x2 = x;
				y2 = y+5;				
			}
			else if(x==7) {
				x2 = x - 5;
				y2 = y;
			}
			else {
				x2 = x + 2;
				y2 = y +3;
				if (x2>=7) {
					x2=x-2;
				}
				if(y2>=7) {
					y2=y-3;
				}
			}
		}
		 int moveDistance = 1;
			for(int stackColor=0;stackColor<moveDistance;stackColor++) {
	    		int stackSize = ((panelLayout)super.panelArray[x][y]).getStackForPiece().size()-1;
	        	pieceColor = ((panelLayout) super.panelArray[x][y]).getStackForPiece().remove(stackSize);		            	
	            ((panelLayout)super.panelArray[x2][y2]).getStackForPiece().add(pieceColor);
	            
	            
	            
	            if(pieceColor=="1") {
	            	((panelLayout)super.panelArray[x2][y2]).setBackground(colorOne);
	            }
	            else if(pieceColor=="2") {
	            	((panelLayout)super.panelArray[x2][y2]).setBackground(colorTwo);
	            }
	            else if(pieceColor=="3") {
	            	((panelLayout)super.panelArray[x2][y2]).setBackground(colorThree);
	            }
	            else if(pieceColor=="4") {
	            	((panelLayout)super.panelArray[x2][y2]).setBackground(colorFour);
	            }
	            
	            
	            if(((panelLayout)super.panelArray[x][y]).getStackForPiece().isEmpty()) {
	            	((panelLayout)super.panelArray[x][y]).setBackground(Color.white);
	            }
	            else if(((panelLayout)super.panelArray[x][y]).getStackForPiece().isEmpty()==false) {
	            	int newStackSize = ((panelLayout)super.panelArray[x][y]).getStackForPiece().size()-1;
		        	pieceColor = ((panelLayout) super.panelArray[x][y]).getStackForPiece().get(newStackSize);
		        	if(pieceColor=="1") {
		            	((panelLayout)super.panelArray[x][y]).setBackground(colorOne);
		            }
		            else if(pieceColor=="2") {
		            	((panelLayout)super.panelArray[x][y]).setBackground(colorTwo);
		            }
		            else if(pieceColor=="3") {
		            	((panelLayout)super.panelArray[x][y]).setBackground(colorThree);
		            }
		            else if(pieceColor=="4") {
		            	((panelLayout)super.panelArray[x][y]).setBackground(colorFour);
		            }
	            }
	            
			}
    
		this.checkCapturedPiece(x2, y2);
		this.setStackText(x, y, x2, y2);
		
		if(currentTurn==4) {
        	currentTurn=1;
        	this.setInformationLabel("TURN : PLAYER "+ currentTurn);
        }
        else {
        	currentTurn++;
        	this.setInformationLabel("TURN : PLAYER "+ currentTurn);
        }
		if ((currentTurn - numberOfHumanPlayers) > 0 && botDifficultyLevel == 1) {
			hardAI();
			
		}
	}
	
	/*
	 * Sets the colour and style of the text to display the pieces in a pile on the pieces.
	 * @param firstButtonXCoordinate - Integer to store x co-ordinate of the first Button selected.
	 * @param firstButtonYCoordinate - Integer to store y co-ordinate of the first Button selected.
	 * @param secondButtonXCoordinate - Integer to store x co-ordinate of the second Button selected.
	 * @param secondButtonYCoordinate - Integer to store y co-ordinate of the second Button selected.
	 */
	private void setStackText(int firstButtonXCoordinate, int firstButtonYCoordinate,int secondButtonXCoordinate ,int secondButtonYCoordinate ) {
		String textFirstButton = ((panelLayout)super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().toString();
    	String textSecondButton = ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString();
    	
    	panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setText(textFirstButton);
    	panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setFocusable(false);
    	
    	if(panelArray[firstButtonXCoordinate][firstButtonYCoordinate].getBackground()==colorTwo || panelArray[firstButtonXCoordinate][firstButtonYCoordinate].getBackground()==colorOne) {
    		panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setForeground(Color.white);
    	}
    	else {
    		panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setForeground(Color.black);
    	}
    					               
    	panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setFont(new Font("Arial", Font.BOLD, 10));
    	
    	
    	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setText(textSecondButton);
    	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setFocusable(false);
    	if(panelArray[secondButtonXCoordinate][secondButtonYCoordinate].getBackground()==colorTwo || panelArray[secondButtonXCoordinate][secondButtonYCoordinate].getBackground()==colorOne) {
    		panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setForeground(Color.white);
    	}
    	else {
    		panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setForeground(Color.black);
    	}
        panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setFont(new Font("Arial", Font.BOLD, 10));	
        
	}
	
	
	public void getPopUp() {
		//To close the pop for the game
	}
	
	
	/*
	 * Checks at each turn, if the players have any of their pieces left to make a move.
	 * @param currentTurn - Integer to store the number corresponding to current turn's player.
	 */
	public void checkGameWinner(int currentTurn) {
		
		this.currentTurn=currentTurn;
		boolean colorPieceAvailable = false;
		if (this.currentTurn==1) {			
			if(playerOneReserveCounter>0) {
				colorPieceAvailable = true;
			}
			else {
			
				for (int row=0; row<8;row++){
					for(int column=0; column<8;column++) {
						if((Color)super.panelArray[row][column].getBackground() == colorOne){
							colorPieceAvailable = true;
							break;
						}						
					}
					if(colorPieceAvailable == true) {
						break;
					}
				}	
			}
			
			if (colorPieceAvailable==false) {
				if(this.currentTurn==4) {
		        	this.currentTurn=1;
		        	this.setInformationLabel("TURN : PLAYER "+ this.currentTurn);
		        }
		        else {
		        	this.currentTurn++;
		        	this.setInformationLabel("TURN : PLAYER "+ this.currentTurn);
		        }
			}
			
		}
		else if(this.currentTurn==2) {
			if(playerTwoReserveCounter>0) {
				colorPieceAvailable = true;
			}
			else {
			
				for (int row=0; row<8;row++){
					for(int column=0; column<8;column++) {
						if((Color)super.panelArray[row][column].getBackground() == colorTwo){
							colorPieceAvailable = true;
							break;
						}						
					}
					if(colorPieceAvailable == true) {
						break;
					}
				}	
			}
			
			if (colorPieceAvailable==false) {
				if(this.currentTurn==4) {
		        	this.currentTurn=1;
		        	this.setInformationLabel("TURN : PLAYER "+ this.currentTurn);
		        }
		        else {
		        	this.currentTurn++;
		        	this.setInformationLabel("TURN : PLAYER "+ this.currentTurn);
		        }
			}
		}
		else if(this.currentTurn==3) {
			if(playerThreeReserveCounter>0) {
				colorPieceAvailable = true;
			}
			else {
			
				for (int row=0; row<8;row++){
					for(int column=0; column<8;column++) {
						if((Color)super.panelArray[row][column].getBackground() == colorThree){
							colorPieceAvailable = true;
							break;
						}						
					}
					if(colorPieceAvailable == true) {
						break;
					}
				}	
			}
			
			if (colorPieceAvailable==false) {
				if(this.currentTurn==4) {
		        	this.currentTurn=1;
		        	this.setInformationLabel("TURN : PLAYER "+ this.currentTurn);
		        }
		        else {
		        	this.currentTurn++;
		        	this.setInformationLabel("TURN : PLAYER "+ this.currentTurn);
		        }
			}
		}
		else if(this.currentTurn==4) {
			if(playerFourReserveCounter>0) {
				colorPieceAvailable = true;
			}
			else {
			
				for (int row=0; row<8;row++){
					for(int column=0; column<8;column++) {
						if((Color)super.panelArray[row][column].getBackground() == colorFour){
							colorPieceAvailable = true;
							break;
						}						
					}
					if(colorPieceAvailable == true) {
						break;
					}
				}	
			}
			
			if (colorPieceAvailable==false) {
				if(this.currentTurn==4) {
		        	this.currentTurn=1;
		        	this.setInformationLabel("TURN : PLAYER "+ this.currentTurn);
		        }
		        else {
		        	this.currentTurn++;
		        	this.setInformationLabel("TURN : PLAYER "+ this.currentTurn);
		        }
			}
		}
		selected=null;
	}
	
	/*
	 * Enables Player One to use the reserved piece/s again in the game.
	 * Checks if there are any more possibles moves.If not,declares a winner.
	 * @param secondButtonXCoordinate - Integer to store the x-co-ordinate of the selected position 
	 *                                  to move the reserved piece to.
	 *                                  
	 * @param secondButtonYCoordinate - Integer to store the y-co-ordinate of the selected position 
	 *                                  to move the reserved piece to.
	 */
	private void playFromReservePlayerOne(int secondButtonXCoordinate,int secondButtonYCoordinate) {
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add("1");
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setBackground(colorOne);
		String textStack = ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString();
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(textStack);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setForeground(Color.white);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFocusable(false);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFont(new Font("Arial", Font.BOLD, 10));
		this.playerOneReserveCounter-=1;
		this.setPlayerOneReserveCounter("RESERVED : ");

        int firstTurn = currentTurn;
        
        if(currentTurn==4) {
        	currentTurn=1;
        	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
        }
        else {
        	currentTurn++;
        	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
        }				                
        
        this.checkGameWinner(currentTurn);
        this.checkGameWinner(currentTurn);
        this.checkGameWinner(currentTurn);
        if(currentTurn==firstTurn) {
        	for (int row=0; row<8;row++){
    			for(int column=0; column<8;column++) {				        	
    				panelArray[row][column].setEnabled(false);
    			}
    		}
        	this.setInformationLabel("PLAYER " + currentTurn + " WON!");
        }
		selected = null;
	}
	
	
	/*
	 * Enables Player Two to use the reserved piece/s again in the game. 
	 * Checks if there are any more possibles moves.If not,declares a winner.
	 * @param secondButtonXCoordinate - Integer to store the x-co-ordinate of the selected position 
	 *                                  to move the reserved piece to.
	 *                                  
	 * @param secondButtonYCoordinate - Integer to store the y-co-ordinate of the selected position 
	 *                                  to move the reserved piece to.
	 */
	private void playFromReservePlayerTwo(int secondButtonXCoordinate,int secondButtonYCoordinate) {
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add("2");
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setBackground(colorTwo);
		String textStack = ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString();
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(textStack);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setForeground(Color.white);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFocusable(false);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFont(new Font("Arial", Font.BOLD, 10));
		this.playerTwoReserveCounter-=1;
		this.setPlayerTwoReserveCounter("RESERVED : ");

        int firstTurn = currentTurn;
        
        if(currentTurn==4) {
        	currentTurn=1;
        	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
        }
        else {
        	currentTurn++;
        	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
        }				                
        
        this.checkGameWinner(currentTurn);
        this.checkGameWinner(currentTurn);
        this.checkGameWinner(currentTurn);
        if(currentTurn==firstTurn) {
        	for (int row=0; row<8;row++){
    			for(int column=0; column<8;column++) {				        	
    				panelArray[row][column].setEnabled(false);
    			}
    		}
        	this.setInformationLabel("PLAYER " + currentTurn + " WON!");
        }
		selected = null;
	} 

	
	/*
	 * Enables Player Three to use the reserved piece/s again in the game. 
	 * Checks if there are any more possibles moves.If not,declares a winner.
	 * @param secondButtonXCoordinate - Integer to store the x-coordinate of the selected position 
	 *                                  to move the reserved piece to.
	 *                                  
	 * @param secondButtonYCoordinate - Integer to store the y-coordinate of the selected position 
	 *                                  to move the reserved piece to.
	 */
	private void playFromReservePlayerThree(int secondButtonXCoordinate,int secondButtonYCoordinate) {
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add("3");
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setBackground(colorThree);
		String textStack = ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString();
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(textStack);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setForeground(Color.black);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFocusable(false);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFont(new Font("Arial", Font.BOLD, 10));
		this.playerThreeReserveCounter-=1;
		this.setPlayerThreeReserveCounter("RESERVED : ");
		

        int firstTurn = currentTurn;
        
        if(currentTurn==4) {
        	currentTurn=1;
        	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
        }
        else {
        	currentTurn++;
        	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
        }				                
        
        this.checkGameWinner(currentTurn);
        this.checkGameWinner(currentTurn);
        this.checkGameWinner(currentTurn);
        if(currentTurn==firstTurn) {
        	for (int row=0; row<8;row++){
    			for(int column=0; column<8;column++) {				        	
    				panelArray[row][column].setEnabled(false);
    			}
    		}
        	this.setInformationLabel("PLAYER " + currentTurn + " WON!");
        }
		selected = null;
	}

	
	/*
	 * Enables Player Four to use the reserved piece/s again in the game. 
	 * Checks if there are any more possibles moves.If not,declares a winner.
	 * @param secondButtonXCoordinate - Integer to store the x-co-ordinate of the selected position 
	 *                                  to move the reserved piece to.
	 *                                  
	 * @param secondButtonYCoordinate - Integer to store the y-co-ordinate of the selected position 
	 *                                  to move the reserved piece to.
	 */
	private void playFromReservePlayerFour(int secondButtonXCoordinate,int secondButtonYCoordinate) {
		
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add("4");
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setBackground(colorFour);
		String textStack = ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString();
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(textStack);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setForeground(Color.black);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFocusable(false);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFont(new Font("Arial", Font.BOLD, 10));
		this.playerFourReserveCounter-=1;
		this.setPlayerFourReserveCounter("RESERVED : ");	            				

        int firstTurn = currentTurn;
        
        if(currentTurn==4) {
        	currentTurn=1;
        	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
        }
        else {
        	currentTurn++;
        	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
        }				                
        
        this.checkGameWinner(currentTurn);
        this.checkGameWinner(currentTurn);
        this.checkGameWinner(currentTurn);
        if(currentTurn==firstTurn) {
        	for (int row=0; row<8;row++){
    			for(int column=0; column<8;column++) {				        	
    				panelArray[row][column].setEnabled(false);
    			}
    		}
        	this.setInformationLabel("PLAYER " + currentTurn + " WON!");
        }
		selected = null;
	}
	
	
	//ActionListeners to respond to the UserInput.
	@Override
	public void actionPerformed(ActionEvent aevt) {
		
		
		Object selected3 = aevt.getSource();
		Object selected4 = aevt.getSource();
		if (selected3!=ruleBook && selected3!=exitGame && selected3!=newGame && selected3!=saveGame && selected3!=loadGame ) {
			
			if (selected != null) {
				
	            Object selected2 = aevt.getSource();
	           
	            if (panelArray[((panelLayout) selected2).getXCoordinate()][((panelLayout) selected2).getYCoordinate()]!=panelArray[7][7] ) {	            
	            	
		            if (panelArray[firstButtonXCoordinate][firstButtonYCoordinate]!=panelArray[((panelLayout) selected2).getXCoordinate()][((panelLayout) selected2).getYCoordinate()]) {
		            	if (selected == this.playerOnePanel && selected2!=this.playerTwoPanel && selected2!=this.playerThreePanel && selected2!=this.playerFourPanel) {
		            			if (this.playerOneReserveCounter>0) {
		            				int secondButtonXCoordinate = ((panelLayout) selected2).getXCoordinate();
		            				int secondButtonYCoordinate = ((panelLayout) selected2).getYCoordinate();
		            				this.playFromReservePlayerOne(secondButtonXCoordinate, secondButtonYCoordinate);
		            				this.checkCapturedPiece(secondButtonXCoordinate,secondButtonYCoordinate);
		            				
		            			}
		            			else {
		            				JOptionPane.showMessageDialog(null,"NO RESERVE PIECE AVAILABLE","ATTENTION",JOptionPane.PLAIN_MESSAGE,this.getWelcomeIcon());
		            				selected = null;
		            			}
		            	}
		            	
		            	else if (selected == this.playerTwoPanel && selected2!=this.playerOnePanel && selected2!=this.playerThreePanel && selected2!=this.playerFourPanel) {
	            			if (this.playerTwoReserveCounter>0) {
	            				int secondButtonXCoordinate = ((panelLayout) selected2).getXCoordinate();
	            				int secondButtonYCoordinate = ((panelLayout) selected2).getYCoordinate();
	            				this.playFromReservePlayerTwo(secondButtonXCoordinate, secondButtonYCoordinate);
	            				this.checkCapturedPiece(secondButtonXCoordinate,secondButtonYCoordinate);
	            			}
	            			else {
	            				JOptionPane.showMessageDialog(null,"NO RESERVE PIECE AVAILABLE","ATTENTION",JOptionPane.PLAIN_MESSAGE,this.getWelcomeIcon());
	            				selected = null;
	            			}
		            	}
		            	
		            	else if (selected == this.playerThreePanel && selected2!=this.playerTwoPanel && selected2!=this.playerOnePanel && selected2!=this.playerFourPanel) {
	            			if (this.playerThreeReserveCounter>0) {
	            				int secondButtonXCoordinate = ((panelLayout) selected2).getXCoordinate();
	            				int secondButtonYCoordinate = ((panelLayout) selected2).getYCoordinate();
	            				this.playFromReservePlayerThree(secondButtonXCoordinate, secondButtonYCoordinate);
	            				this.checkCapturedPiece(secondButtonXCoordinate,secondButtonYCoordinate);
	            			}
	            			else {
	            				JOptionPane.showMessageDialog(null,"NO RESERVE PIECE AVAILABLE","ATTENTION",JOptionPane.PLAIN_MESSAGE,this.getWelcomeIcon());
	            				selected = null;
	            			}
		            	}
		            	
		            	else if (selected == this.playerFourPanel && selected2!=this.playerTwoPanel && selected2!=this.playerThreePanel && selected2!=this.playerOnePanel) {
	            			if (this.playerFourReserveCounter>0) {
	            				int secondButtonXCoordinate = ((panelLayout) selected2).getXCoordinate();
	            				int secondButtonYCoordinate = ((panelLayout) selected2).getYCoordinate();
	            				this.playFromReservePlayerFour(secondButtonXCoordinate, secondButtonYCoordinate);
	            			}
	            			else {
	            				JOptionPane.showMessageDialog(null,"NO RESERVE PIECE AVAILABLE","ATTENTION",JOptionPane.PLAIN_MESSAGE,this.getWelcomeIcon());
	            				selected = null;
	            			}
		            	}
		            	
		            	else if(this.checkValidMove(selected2,((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size())==true) {
		            		
		            		int secondButtonXCoordinate = ((panelLayout) selected2).getXCoordinate();
			                int secondButtonYCoordinate = ((panelLayout) selected2).getYCoordinate();
				                
				                
				                int moveDistance = this.getDistanceBetweenMove(selected2,((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size());
				                
				                if (moveDistance==1){
				                	this.moveOnePiece(firstButtonXCoordinate, firstButtonYCoordinate,secondButtonXCoordinate,secondButtonYCoordinate);
				                	this.checkCapturedPiece(secondButtonXCoordinate,secondButtonYCoordinate);
				                }
				                else if (moveDistance==2){
				                	this.moveTwoPiece(firstButtonXCoordinate, firstButtonYCoordinate,secondButtonXCoordinate,secondButtonYCoordinate);		                	
				                	this.checkCapturedPiece(secondButtonXCoordinate,secondButtonYCoordinate);
				                }
				                else if (moveDistance==3){
				                	this.moveThreePiece(firstButtonXCoordinate, firstButtonYCoordinate,secondButtonXCoordinate,secondButtonYCoordinate);
				                	this.checkCapturedPiece(secondButtonXCoordinate,secondButtonYCoordinate);
				                }
				                else if (moveDistance==4){
				                	this.moveFourPiece(firstButtonXCoordinate, firstButtonYCoordinate,secondButtonXCoordinate,secondButtonYCoordinate);
				                	this.checkCapturedPiece(secondButtonXCoordinate,secondButtonYCoordinate);
				                }
				                else if (moveDistance==5){
				                	this.moveFivePiece(firstButtonXCoordinate, firstButtonYCoordinate,secondButtonXCoordinate,secondButtonYCoordinate);
				                	this.checkCapturedPiece(secondButtonXCoordinate,secondButtonYCoordinate);
				                }
				                
				                                
				                 				                
				                int stackSize = ((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size();
				                
				                if(stackSize==0) {
				                	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setBackground(nameOfTheColor); 
				                	panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setBackground(Color.white);
				                	
				                }
				                else {
					                if (((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackSize-1)=="1"){
					                	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setBackground(nameOfTheColor); 
					                	 panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setBackground(colorOne);
					                	
					                	 
					                }
					                else if(((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackSize-1)=="2"){
					                	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setBackground(nameOfTheColor); 
					                	panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setBackground(colorTwo);
					                	
					                }
					                else if(((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackSize-1)=="3"){
					                	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setBackground(nameOfTheColor); 
					                	 panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setBackground(colorThree);
					                	 
					                }
					                else if (((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackSize-1)=="4"){
					                	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setBackground(nameOfTheColor); 
					                	 panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setBackground(colorFour);
					                	 
					                }
				                }
				                
				                this.setStackText(firstButtonXCoordinate, firstButtonYCoordinate, secondButtonXCoordinate, secondButtonYCoordinate);
				                selected = null;
				                
				                int firstTurn = currentTurn;
				                
				                if(currentTurn==4) {
				                	currentTurn=1;
				                	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
				                }
				                else {
				                	currentTurn++;
				                	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
				                }				                
				                
				                this.checkGameWinner(currentTurn);
				                this.checkGameWinner(currentTurn);
				                this.checkGameWinner(currentTurn);
				                if(currentTurn==firstTurn) {
				                	for (int row=0; row<8;row++){
					        			for(int column=0; column<8;column++) {				        	
					        				panelArray[row][column].setEnabled(false);
					        			}
					        		}
				                	this.setInformationLabel("PLAYER " + currentTurn + " WON!");
				                }
				               				                
			            	}		            	
		            }
	            }
	            else {
	            	this.removeHighlight();
	            	selected=null;
	            }
	            
	        }
	        
	        
	        
	        
	        else{
	            selected = aevt.getSource();
	            if (selected== playerOnePanel && this.currentTurn==1 ) {
		           }
		        else if (selected== playerTwoPanel && this.currentTurn==2 ) {
		           	}
		        else if (selected== playerThreePanel && this.currentTurn==3) {
		           	}
		        else if (selected== playerFourPanel && this.currentTurn==4) {
		           	}
	            else if (this.currentTurn ==1 ) {
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= colorOne) {
	            		selected=null;
	            	}
	            }
	            else if (this.currentTurn==2) {
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= colorTwo) {
	            		
	            		selected=null;
	            	}
	            }
	            else if (this.currentTurn ==3 ) {
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= colorThree) {
	            		
	            		selected=null;
	            	}
	            }
	            else if (this.currentTurn==4) {
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= colorFour) {
	            		
	            		selected=null;
	            	}
	            }
	           
	            
	            	this.setHighlight();
	        }
}
		
		else {
			
		  if(selected3==super.ruleBook) {
			  try {
					Desktop.getDesktop().open(new java.io.File("ruleBook.pdf"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 		}
 		
         else if (selected3==super.exitGame) {
 			System.exit(0);
 		}
 		
         else if (selected3== super.newGame) {
 			this.dispose();
 			PlayerSelector playerSelectorWindow = new PlayerSelector();
 			playerSelectorWindow.setLayout();
 		}
 		
         else if(selected3 == super.saveGame) {
 			//this.dispose();
 			SaveAndExit saveAndExit = new SaveAndExit();			
 		}
 		
         else if(selected3 == super.loadGame) {
 			LoadGame loadGame = new LoadGame();
 			}
		  
         
		}
		
		
		
		if ((currentTurn - numberOfHumanPlayers) > 0 && botDifficultyLevel == 0) {
			easyAI();
			
		}
		
		if ((currentTurn - numberOfHumanPlayers) > 0 && botDifficultyLevel == 1) {
			hardAI();
			
		}
 	}        
}