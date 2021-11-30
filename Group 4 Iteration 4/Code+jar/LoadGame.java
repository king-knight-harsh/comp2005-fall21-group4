
/*
 * GUI component to load the game that was saved earlier.
 * extends JFrame and uses Swing.
 * Implements ActionListener to respond to user action.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class LoadGame extends GameDisplay implements ActionListener {
	private JLabel informationLabel;
	private int numberOfHumanPlayers;	
	private String playerOneName="PLAYER 1", playerTwoName="PLAYER 2", playerThreeName="PLAYER 3", playerFourName="PLAYER 4" ;
	private int colorMode;
	private int botDifficultyLevel;
	private JPanel topPanel,infoPanel, bottomPanel;
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
	private JButton deSelectButton, botButton;

	private int playerOneCaptureCounter=0,playerTwoCaptureCounter=0,playerThreeCaptureCounter=0,playerFourCaptureCounter=0;
	private int playerOneReserveCounter=0,playerTwoReserveCounter=0,playerThreeReserveCounter=0,playerFourReserveCounter=0;
	private boolean playerTwoBot, playerThreeBot, playerFourBot;


	/*
	 * Constructor with public visibility.
	 * @param numberOfHumanPlayers - Integer that stores the number of human players 
	 * 			who will play the game.
	 * @param player1 - String that stores the name of the first player.
	 * @param player2 - String that stores the name of the second player.
	 * @param player3 - String that stores the name of the third player.
	 * @param player4 - String that stores the name of the fourth player.
	 * @param botDifficultyLevel - Integer that stores the AI Difficulty level(i.e, "EASY" or "HARD") selected by the user.
	 * @param colorMode - Integer that stores the Color Mode selected by the User. (i.e, "Normal" or "ColorBlind")
	 */

	LoadGame(JButton[][] stack,int numberOfHumanPlayer, int currentTurn){
		this.numberOfHumanPlayers = numberOfHumanPlayers;
		this.numberOfHumanPlayers=numberOfHumanPlayer;
		this.currentTurn=currentTurn;
		

		getContentPane().add(this.getTopPanel(),BorderLayout.NORTH);
		getContentPane().add(this.getBottomPanel(),BorderLayout.SOUTH);
		this.setPiecesOnBoard( stack);

	}
	/* Sets the top panel layout.
	 * @return topPanel - JPanel customize panel for the GUI
	 */
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
	/* Sets the bottom panel layout.
	 * @return bottomPanel - JPanel customize panel for the GUI
	 */
	public JPanel getBottomPanel() {
		bottomPanel =  new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(this.getInfoPanel());
		if (numberOfHumanPlayers<4) {
			bottomPanel.add(this.getBotButton());
			
		}
		
		return bottomPanel;
	}

	/* Gets the specifications required to build the info panel.
	*  @return infoPanel - JPanel customize panel to string info about the game stats
	*/
	public JPanel getInfoPanel() {
		infoPanel = new JPanel();
		infoPanel.setLayout(new FlowLayout());
		informationLabel = new  JLabel();		
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));
		informationLabel.setForeground(Color.black);		
		informationLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 5,5));		
		informationLabel.setPreferredSize(new Dimension(350,50));
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
		infoPanel.add(this.informationLabel);		
		return infoPanel;
	}
	


	/*
	 * Sets the value of the label as the parameter passed.
	 * @param text - String to store the value that describes the label. 
	 */
	private void setInformationLabel(String text) {
		this.informationLabel.setText(text);

	}

	private void checkBotButtonColor(int currentTurn) {
		if (currentTurn ==1) {
			botButton.setEnabled(false);
			botButton.setText("Human Turn");
			botButton.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		}
		else if (currentTurn==2) {
			botButton.setEnabled(true);
			botButton.setText("Bot Turn");
			botButton.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		}
		else if (currentTurn==3) {
			botButton.setEnabled(true);
			botButton.setText("Bot Turn");
			botButton.setBorder(BorderFactory.createLineBorder(Color.green, 3));
		}
		else if (currentTurn==4) {
			botButton.setEnabled(true); 
			botButton.setText("Bot Turn");
			botButton.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
		}
	}

	/*
	 * Sets the dimensions, coordinates and other properties for the "BOT" button.
	 * Uses ActionListener so that the an action is performed when user clicks the button.
	 * @return botButton - JButton for moving pieces for the AI.
	 */
	private JButton getBotButton() {
		botButton= new panelLayout(7,7);
		botButton.setText("BOT TURN");
		botButton.setFont(new Font("TimesRoman", Font.BOLD, 16));
		botButton.setPreferredSize(new Dimension(150,50));
		botButton.setBackground(Color.WHITE);
		botButton.setForeground(Color.BLACK);		
		this.checkBotButtonColor(this.currentTurn);
		botButton.setFocusable(false);
		botButton.addActionListener(this);
		return botButton; 
	}

	/*
	 * Sets the dimensions, coordinates and other properties for the "Unclick" button.
	 * Uses ActionListener so that the an action is performed when user clicks the button.
	 * @return deSelectButton - JButton to de-select the present selected button
	 */
	private JButton getDeSelectButton() {
		deSelectButton= new panelLayout(7,7);
		deSelectButton.setText("UNCLICK");
		deSelectButton.setFont(new Font("TimesRoman", Font.BOLD, 16));
		deSelectButton.setPreferredSize(new Dimension(150,50));
		deSelectButton.setBackground(Color.orange);
		deSelectButton.setForeground(Color.BLACK);
		deSelectButton.setFocusable(false);
		deSelectButton.addActionListener(this);
		return deSelectButton; 
	}
	

	/*Sets the layout for the Captured button which tells how any pieces of other players are captured by Player One.
	 *@return  playerOneCaptured - JLabel containing the value of the player one captured pieces
	 */
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
	
	/*Sets the layout for the Reserved Button which tells how many pieces of player one is reserved.
	* @return playerOneReserved - JLabel containing the value of the player one reserved pieces
	*/
	private JLabel getPlayerOneReserveCounter() {
		playerOneReserved = new JLabel("RESERVED : " + this.playerOneReserveCounter);
		playerOneReserved.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerOneReserved.setForeground(Color.black);
		return playerOneReserved;
	}
	
	public void setPiecesOnBoard(JButton[][] stack) {
		
		for (int row=0; row<8;row++) {
			for(int column=0; column<8;column++) {
				(super.panelArray[row][column]).setBackground(stack[row][column].getBackground());				
				((panelLayout)super.panelArray[row][column]).addActionListener(this);	
				(super.panelArray[row][column]).setText(((panelLayout)stack[row][column]).getStackForPiece().toString());
				((panelLayout)super.panelArray[row][column]).setForeground(Color.black);
				((panelLayout)super.panelArray[row][column]).setFocusable(false);
				((panelLayout)super.panelArray[row][column]).setFont(new Font("Arial", Font.BOLD, 12));					
			}
		}
	}
	
	
	
	/*
	 * Updates the value of Reserved Pieces.
	 * @param text - String to store the number of Reserved pieces for Player One.
	 */
	private void setPlayerOneReserveCounter(String text) {
		this.playerOneReserved.setText(text + Integer.toString(this.playerOneReserveCounter));
	}
	
	/*Sets the layout for the Captured button which tells how any pieces of other players are captured by Player two.
	 *@return  playerTwoCaptured - JLabel containing the value of the player two captured pieces
	 */
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
	
	/*Sets the layout for the Reserved Button which tells how many pieces of player two is reserved.
	* @return playerTwoReserved - JLabel containing the value of the player Two reserved pieces
	*/
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
	
	/*Sets the layout for the Captured button which tells how any pieces of other players are captured by Player Three.
	 *@return  playerThreeCaptured - JLabel containing the value of the player three captured pieces
	 */
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
	
	/*Sets the layout for the Reserved Button which tells how many pieces of player Three is reserved.
	* @return playerThreeReserved - JLabel containing the value of the player Three reserved pieces
	*/
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
	
	/*Sets the layout for the Captured button which tells how any pieces of other players are captured by Player Four.
	 *@return  playerFourCaptured - JLabel containing the value of the player Four captured pieces
	 */
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
	/*
	 * Sets the layout for the Reserved Button which tells how many pieces of player four is reserved.
	 * @return playerFourReserved - JLabel containing the value of the player four reserved pieces
	 */
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
	/*
	 * Getter Method to get the player one panel
	 * @return playerOnePanel - panelLayout button with information related to player one 
	 */
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
		playerOnePanel.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		playerOnePanel.addActionListener(this);

		return playerOnePanel;
	}
	/*
	 * Getter Method to get the player two panel
	 * @return playerTwoPanel - panelLayout button with information related to player two 
	 */
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
		playerTwoPanel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		playerTwoPanel.addActionListener(this);

		return playerTwoPanel;
	}

	/*
	 * Getter Method to get the player three panel
	 * @return playerThreePanel - panelLayout button with information related to player three 
	 */
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
		playerThreePanel.setBorder(BorderFactory.createLineBorder(Color.green, 3));
		playerThreePanel.addActionListener(this);

		return playerThreePanel;
	}

	/*
	 * Getter Method to get the player four panel
	 * @return playerOnFourPanel - panelLayout button with information related to player four 
	 */
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
		playerFourPanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
		playerFourPanel.addActionListener(this);

		return playerFourPanel;
	}


	/*Setup the game board using four different colored pieces./*
	 * This private method checks if the move made by the player is valid or not,
	 * if not then opens a dialog box asking the player to make a valid move.
	 * @param selected2 - Object to store the coordinates of the selected piece.
	 * @param lengthofTheStack - Integer to store the number of pieces in a stack.
	 * @return true/false - boolean value if it satisfy the condition or not. 
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
		//small GUI frame to inform user of the illegal moves
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
	 * @param selected2 - Object to store the coordinates of the selected piece.
	 * @param lengthoftheStack - Integer to store the number of pieces in a stack.
	 * @return distanceBetweenBtn - Integer distance between the pieces.
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
				if(((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().get(stackLength-1)=="B") {
					int counter=0;
					while(counter<(stackLength-5)) {
						String removedPiece = ((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().remove(0);
						if (removedPiece=="B") {
							this.playerOneReserveCounter+=1;
							counter++;
						}
						else {
							this.playerOneCaptureCounter+=1;
							counter++;
						}
					}
				}
				// conditional block if top color is Red
				else if(((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().get(stackLength-1)=="R") {
					int counter=0;
					while(counter<(stackLength-5)) {
						String removedPiece = ((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().remove(0);
						if (removedPiece=="R") {
							this.playerTwoReserveCounter+=1;
							counter++;
						}
						else {
							this.playerTwoCaptureCounter+=1;
							counter++;
						}
					}
				}
				// conditional block if top color is green
				else if(((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().get(stackLength-1)=="G") {
					int counter=0;
					while(counter<(stackLength-5)) {
						String removedPiece = ((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().remove(0);
						if (removedPiece=="G") {
							this.playerThreeReserveCounter+=1;
							counter++;
						}
						else {
							this.playerThreeCaptureCounter+=1;
							counter++;
						}
					}
				}
				// conditional block if top color is Yellow
				else if(((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().get(stackLength-1)=="Y") {
					int counter=0;
					while(counter<(stackLength-5)) {
						String removedPiece = ((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().remove(0);
						if (removedPiece=="Y") {
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
	 * Moves the pile to one piece distance with a valid move
	 * and updates the number of pieces in each pile.
	 * @param selected - Object to store the coordinates of the initial position of the selected piece.
	 * @param selected2 - object to store the coordinates of the final position of the selected piece.
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
	 * Moves the pile to two piece distance with a valid move
	 * and updates the number of pieces in each pile.
	 * @param selected - Object to store the coordinates of the initial position of the selected piece.
	 * @param selected2 - object to store the coordinates of the final position of the selected piece.
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
	 * Moves the pile to three piece distance with a valid move
	 * and updates the number of pieces in each pile.
	 * @param selected - Object to store the coordinates of the initial position of the selected piece.
	 * @param selected2 - object to store the coordinates of the final position of the selected piece.
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
	 * Moves the pile to four piece distance with a valid move
	 * and updates the number of pieces in each pile.
	 * @param selected - Object to store the coordinates of the initial position of the selected piece.
	 * @param selected2 - object to store the coordinates of the final position of the selected piece.
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
	 * Moves the pile to five piece distance with a valid move
	 * and updates the number of pieces in each pile.
	 * @param selected - Object to store the coordinates of the initial position of the selected piece.
	 * @param selected2 - object to store the coordinates of the final position of the selected piece.
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
			if (nameOfTheColor.equals(Color.red)) {
				panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.decode("#FA8072"));
			}
			else if (nameOfTheColor.equals(Color.blue)) {
				panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.decode("#0096FF"));
			}
			else if (nameOfTheColor.equals(Color.YELLOW)) {
				panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.decode("#FFFFA1"));
			}
			else if (nameOfTheColor.equals(Color.green)) {
				panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.decode("#90EE90"));
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
           	panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.blue);
           }
           else if (this.currentTurn==2) {
           	panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.red);
           }
           else if (this.currentTurn ==3 ) {
           	panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.green);
           }
           else {
           	panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(Color.yellow);
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
	 * Method to check game winner
	 * @param currentTurn - Integer present turn in the game
	 */
	private void checkWin(int currentTurn) {
		boolean checkWinnerMode =false;
		//conditional block to see if the sum of the playerOne's reserve and capture piece>=10
       if (currentTurn==1) {
       	if((this.playerOneCaptureCounter+this.playerOneReserveCounter)>=10) {
       		for (int row=0; row<8;row++){
       			for(int column=0; column<8;column++) {				        	
       				panelArray[row][column].setEnabled(false);
       			}
       		}
           	this.setInformationLabel("PLAYER " + currentTurn + " WON!");
           	checkWinnerMode=true;
       	}
       }
     //conditional block to see if the sum of the playerTwo's reserve and capture piece>=10
       else if (currentTurn==2) {
       	if((this.playerTwoCaptureCounter+this.playerTwoReserveCounter)>=10) {
       		for (int row=0; row<8;row++){
       			for(int column=0; column<8;column++) {				        	
       				panelArray[row][column].setEnabled(false);
       			}
       		}
           	this.setInformationLabel("PLAYER " + currentTurn + " WON!");
           	checkWinnerMode=true;
       	}
       }
     //conditional block to see if the sum of the playerThree's reserve and capture piece>=10
       else if (currentTurn==3) {
       	if((this.playerThreeCaptureCounter+this.playerThreeReserveCounter)>=10) {
       		for (int row=0; row<8;row++){
       			for(int column=0; column<8;column++) {				        	
       				panelArray[row][column].setEnabled(false);
       			}
       		}
           	this.setInformationLabel("PLAYER " + currentTurn + " WON!");
           	checkWinnerMode=true;
       	}
       }
     //conditional block to see if the sum of the playerFour's reserve and capture piece>=10
       else if(currentTurn==4) {
       	if((this.playerFourCaptureCounter+this.playerFourReserveCounter)>=10) {
       		for (int row=0; row<8;row++){
       			for(int column=0; column<8;column++) {				        	
       				panelArray[row][column].setEnabled(false);
       			}
       		}
           	this.setInformationLabel("PLAYER " + currentTurn + " WON!");
           	checkWinnerMode=true;
       	}
       }
       //Normal conditional check the game winner
       if (checkWinnerMode==false) {
       	int firstTurn = currentTurn;
           
           if(currentTurn==4) {
           	currentTurn=1;
           	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
           }
           else {
           	currentTurn++;
           	this.setInformationLabel("TURN : PLAYER "+ currentTurn);				                	
           }				                
           
           this.checkTurnStatus(currentTurn);
           this.checkTurnStatus(currentTurn);
           this.checkTurnStatus(currentTurn);
           if(currentTurn==firstTurn) {
           	for (int row=0; row<8;row++){
       			for(int column=0; column<8;column++) {				        	
       				panelArray[row][column].setEnabled(false);
       			}
       		}
           	this.setInformationLabel("PLAYER " + currentTurn + " WON!");
           }
           if ((currentTurn - numberOfHumanPlayers) > 0) {
           	this.botButton.setEnabled(true);
           	this.checkBotButtonColor(this.currentTurn);
           }
       }
	}

	/*
	 * Implements the functioning of the Easy AI Difficulty level . 
	 * Once the turn of the human players is done, the AI players 
	 * take their turn. 
	 */
	private void easyAI() {
		//Conditional block if the reserve piece is available for the AI player
		if(playerTwoReserveCounter>0 && currentTurn == 2) {
			int x=0;
			int y=0;
			Random randomx;
			Random randomy; 
			randomx = new Random();
			x = randomx.nextInt(7);
			randomy = new Random();
			y = randomy.nextInt(7);
			if((x==0 && y==0) || (x==0 && y==1) || (x==0 && y==6) || (x==0 && y==7)) {
				x+=2;
			}
			if ((x==7 && y==0) || (x==7 && y==1) || (x==7 && y==6) || (x==7 && y==7)) {
				x-=2;
			}
			if((x==1 && y==0) || (x==1 && y==7)) {
				x+=1;
			}
			if((x==6 && y==0) || (x==6 && y==7)) {
				x-=1;
			}
			
			this.playFromReservePlayerTwo(x, y);
			this.checkCapturedPiece(x, y);
			((panelLayout)super.panelArray[x][y]).setText(((panelLayout)super.panelArray[x][y]).getStackForPiece().toString());
			this.checkBotButtonColor(this.currentTurn);
		}
		//Conditional block if the reserve piece is available for the AI player
		else if(playerThreeReserveCounter>0 && currentTurn == 3) {
			int x=0;
			int y=0;
			Random randomx;
			Random randomy; 
			randomx = new Random();
			x = randomx.nextInt(7);
			randomy = new Random();
			y = randomy.nextInt(7);	
			if((x==0 && y==0) || (x==0 && y==1) || (x==0 && y==6) || (x==0 && y==7)) {
				x+=2;
			}
			if ((x==7 && y==0) || (x==7 && y==1) || (x==7 && y==6) || (x==7 && y==7)) {
				x-=2;
			}
			if((x==1 && y==0) || (x==1 && y==7)) {
				x+=1;
			}
			if((x==6 && y==0) || (x==6 && y==7)) {
				x-=1;
			}
			this.playFromReservePlayerThree(x, y);
			this.checkCapturedPiece(x, y);
			((panelLayout)super.panelArray[x][y]).setText(((panelLayout)super.panelArray[x][y]).getStackForPiece().toString());
			this.checkBotButtonColor(this.currentTurn);
		}
		//Conditional block if the reserve piece is available for the AI player
		else if(playerFourReserveCounter>0 && currentTurn == 4) {
			int x=0;
			int y=0;
			Random randomx;
			Random randomy; 
			randomx = new Random();
			x = randomx.nextInt(7);
			randomy = new Random();
			y = randomy.nextInt(7);	
			if((x==0 && y==0) || (x==0 && y==1) || (x==0 && y==6) || (x==0 && y==7)) {
				x+=2;
			}
			if ((x==7 && y==0) || (x==7 && y==1) || (x==7 && y==6) || (x==7 && y==7)) {
				x-=2;
			}
			if((x==1 && y==0) || (x==1 && y==7)) {
				x+=1;
			}
			if((x==6 && y==0) || (x==6 && y==7)) {
				x-=1;
			}
			this.playFromReservePlayerFour(x, y);
			this.checkCapturedPiece(x, y);
			((panelLayout)super.panelArray[x][y]).setText(((panelLayout)super.panelArray[x][y]).getStackForPiece().toString());
			this.checkBotButtonColor(this.currentTurn);
		}
		//Conditional block when no reserve piece is available
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
		            	//conditional block to find the color if randomly selected coordinate is not right
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
		          //conditional block to find the color if randomly selected coordinate is not right
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
		          //conditional block to find the color if randomly selected coordinate is not right
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
			
			
			//Moves for the AI Player
			if ((x == 0 && y ==1)|| x == 0) {
				x2=x+1;
				y2=y;
				
			}
			else if ((x == 0 && y ==5)||(y == 7)) {
				x2=x;
				y2=y-1;
			}
			else if ((x == 1 && y ==1)||(x == 1 && y ==6)) {
				x2=x+1;
				y2=y;
				
			}
			else if ((x == 6 && y ==1) || (x == 6 && y ==6) || x==7 ) {
				x2=x-1;
				y2=y;
			}
			else if (y == 0) {
				x2 = x;
				y2 = y+1;
				
			}
			else {
				Random randomNumber = new Random();
				int number=randomNumber.nextInt(1);
				if(number==0) {
					x2=x+1;
					y2=y;
					if(x2>7) {
						x2=x-1;
					}
					if(y2>7) {
						y2=y-1;
					}
					
				}
				else {
					x2=x;
					y2=y+1;
					if(x2>7) {
						x2=x-1;
					}
					if(y2>7) {
						y2=y-1;
					}
				}
			}
			//shifting the pieces from x, y to x2, y2.
			int stackSize = ((panelLayout)super.panelArray[x][y]).getStackForPiece().size()-1;
			pieceColor = ((panelLayout) super.panelArray[x][y]).getStackForPiece().remove(stackSize);		            	
           ((panelLayout)super.panelArray[x2][y2]).getStackForPiece().add(pieceColor);

           //checks for the smooth functionality
			this.botColorChangeLogic(x, y, x2, y2);
			this.checkCapturedPiece(x2, y2);
			this.setStackText(x, y, x2, y2);
			this.checkWin(this.currentTurn);
           this.checkBotButtonColor(this.currentTurn);
			
		}
	 			
			}
	
	/*
	 *Implements the functioning of the Hard AI Difficulty level . 
	 */
	private void hardAI() {
		//Conditional block to play from reserve
		if(playerTwoReserveCounter>0 && currentTurn == 2) {
			int x= 0;
			int y=0;
			int currentStackSize=0;
   		for (int row=0; row<8;row++){
				for(int column=0; column<8;column++) {
					if((Color)super.panelArray[row][column].getBackground() == Color.red && ((panelLayout)super.panelArray[row][column]).getStackForPiece().size()>currentStackSize){
						x=row;
						y=column;
						currentStackSize=((panelLayout)super.panelArray[row][column]).getStackForPiece().size();
					}						
				}
   		}
			this.playFromReservePlayerTwo(x, y);
			this.checkCapturedPiece(x, y);
			((panelLayout)super.panelArray[x][y]).setText(((panelLayout)super.panelArray[x][y]).getStackForPiece().toString());
			this.checkBotButtonColor(this.currentTurn);
		}
		//Conditional block to play from reserve
		else if(playerThreeReserveCounter>0 && currentTurn == 3) {
			int x= 0;
			int y=0;
			int currentStackSize=0;
   		for (int row=0; row<8;row++){
				for(int column=0; column<8;column++) {
					if((Color)super.panelArray[row][column].getBackground() == Color.green && ((panelLayout)super.panelArray[row][column]).getStackForPiece().size()>currentStackSize){
						x=row;
						y=column;
						currentStackSize=((panelLayout)super.panelArray[row][column]).getStackForPiece().size();
					}						
				}
   		}
			this.playFromReservePlayerThree(x, y);
			this.checkCapturedPiece(x, y);
			((panelLayout)super.panelArray[x][y]).setText(((panelLayout)super.panelArray[x][y]).getStackForPiece().toString());
			this.checkBotButtonColor(this.currentTurn);
		}
		//Conditional block to play from reserve
		else if(playerFourReserveCounter>0 && currentTurn == 4) {
			int x= 0;
			int y=0;
			int currentStackSize=0;
   		for (int row=0; row<8;row++){
				for(int column=0; column<8;column++) {
					if((Color)super.panelArray[row][column].getBackground() == Color.yellow && ((panelLayout)super.panelArray[row][column]).getStackForPiece().size()>currentStackSize){
						x=row;
						y=column;
						currentStackSize=((panelLayout)super.panelArray[row][column]).getStackForPiece().size();
					}						
				}
   		}
			this.playFromReservePlayerFour(x, y);
			this.checkCapturedPiece(x, y);
			((panelLayout)super.panelArray[x][y]).setText(((panelLayout)super.panelArray[x][y]).getStackForPiece().toString());
			this.checkBotButtonColor(this.currentTurn);
		}
		//Conditional block when no reserve piece is available
		else {
		
			boolean check = false;
			int moveSize=0;
			int x=0;
			int y=0;
			int x2=0;
			int y2=0;
			
			
			if (currentTurn == 2) {
				
					int currentStackSize=0;
	        		for (int row=0; row<8;row++){
						for(int column=0; column<8;column++) {
							if((Color)super.panelArray[row][column].getBackground() == Color.red && ((panelLayout)super.panelArray[row][column]).getStackForPiece().size()>currentStackSize){
								moveSize = ((panelLayout)super.panelArray[row][column]).getStackForPiece().size();
								x=row;
								y=column;
								currentStackSize=((panelLayout)super.panelArray[row][column]).getStackForPiece().size();
							}						
						}
	        		}				
			}
			
			else if(currentTurn == 3){
				
					int currentStackSize=0;
	        		for (int row=0; row<8;row++){
						for(int column=0; column<8;column++) {
							if((Color)super.panelArray[row][column].getBackground() == Color.green && ((panelLayout)super.panelArray[row][column]).getStackForPiece().size()>currentStackSize){	
								moveSize = ((panelLayout)super.panelArray[row][column]).getStackForPiece().size();
								x=row;
								y=column;
								currentStackSize=((panelLayout)super.panelArray[row][column]).getStackForPiece().size();
							}						
						}
	        		}
			}
			else if (currentTurn==4){
				
					int currentStackSize=0;
	        		for (int row=0; row<8;row++){
						for(int column=0; column<8;column++) {
							if((Color)super.panelArray[row][column].getBackground() == Color.yellow && ((panelLayout)super.panelArray[row][column]).getStackForPiece().size()>currentStackSize){
								moveSize = ((panelLayout)super.panelArray[row][column]).getStackForPiece().size();
								x=row;
								y=column;
								currentStackSize=((panelLayout)super.panelArray[row][column]).getStackForPiece().size();
							}						
						}
	        		}				
			}
			
			//Conditional block for move of one length 
			if (moveSize==1) {
				if ((x == 0 && y ==1) ||(x == 1 && y ==1) || (x == 1 && y ==6) || (x == 0)) {
					x2 = x + 1;
					y2 = y;	
				}
				else if ((x == 0 && y ==5) || (y == 7)) {
					x2 = x ;
					y2 = y-1;
				}
				else if ((x == 6 && y ==1)||(x == 6 && y ==6) || (x==7)) {
					x2 = x -1;
					y2 = y;
				}
				else if (y == 0) {
					x2 = x;
					y2 = y+1;				
				}
				else {
					x2 = x + 1;
					y2 = y ;
				}
			}
			//Conditional block for move of two length 
			else if(moveSize==2){
				if ((x == 0 && y ==1)||(x == 1 && y ==1) || (x == 1 && y ==6) || (x == 0)) {
					x2 = x + 2;
					y2 = y;	
				}
				else if ((x == 0 && y ==5) || (y == 7)) {
					x2 = x ;
					y2 = y-2;	
				}
				else if ((x == 6 && y ==1)||(x == 6 && y ==6)|| (x==7)) {
					x2 = x -2;
					y2 = y;	
				}
				else if (y == 0) {
					x2 = x;
					y2 = y+2;	
				}
				
				else {
					x2 = x + 1;
					y2 = y + 1;
				}
			}
			//Conditional block for move of Three length 
			else if(moveSize==3) {
				if ((x == 0 && y ==1)|| (x == 1 && y ==1) || (x == 1 && y ==6) || (x == 0)) {
					x2 = x + 3;
					y2 = y;	
				}
				else if ((x == 0 && y ==5) || (y == 7)) {
					x2 = x ;
					y2 = y-3;	
				}
				else if ((x == 6 && y ==1)||(x == 6 && y ==6) || (x==7)) {
					x2 = x -3;
					y2 = y;	
				}
				else if (y == 0) {
					x2 = x;
					y2 = y+3;
				}
				else {
					x2 = x + 2;
					y2 = y + 1;
					if (x2>=7) {
						x2=x-2;
					}
					if(y2>=7) {
						y2=y-1;
					}
				}
			}
			//Conditional block for move of Four length 
			else if(moveSize==4) {
				if ((x == 0 && y ==1) || (x == 1 && y ==1) || (x == 1 && y ==6) || (x == 0)) {
					x2 = x + 4;
					y2 = y;	
				}
				else if ((x == 0 && y ==5) || (y == 7) ) {
					x2 = x ;
					y2 = y-4;	
				}
				else if ((x == 6 && y ==1) || (x == 6 && y ==6) || (x==7)) {
					x2 = x -4;
					y2 = y;	
				}
				else if (y == 0) {
					x2 = x;
					y2 = y+4;	
				}
				else {
					x2 = x + 2;
					y2 = y +2;
					if (x2>=7) {
						x2=x-2;
					}
					if(y2>=7) {
						y2=y-2;
					}
				}		
			}
			//Conditional block for move of Five length 
			else if(moveSize==5) {
				if ((x == 0 && y ==1) || (x == 1 && y ==1) || (x == 1 && y ==6) || (x == 0)) {
					x2 = x + 5;
					y2 = y;	
				}
				else if ((x == 0 && y ==5) || (y == 7)) {
					x2 = x ;
					y2 = y-5;
				}
				else if ((x == 6 && y ==1)||(x == 6 && y ==6) || (x==7)) {
					
					x2 = x -5;
					y2 = y;
					
				}
				else if (y == 0) {
					x2 = x;
					y2 = y+5;
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
			//loop for moving the stack or piece and logic for color change
			for(int stackColor=0;stackColor<moveSize;stackColor++) {
	        	pieceColor = ((panelLayout) super.panelArray[x][y]).getStackForPiece().remove(0);		            	
	            ((panelLayout)super.panelArray[x2][y2]).getStackForPiece().add(pieceColor);
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
			// checks for the smooth functionality of the code
			this.checkCapturedPiece(x2, y2);
			this.setStackText(x, y, x2, y2);
			this.checkWin(this.currentTurn);
	        this.checkBotButtonColor(this.currentTurn);
		}
	}
	/*
	 * Sets the color and style of the text to display the pieces in a pile on the pieces.
	 * @param firstButtonXCoordinate - Integer to store x coordinate of the first Button selected.
	 * @param firstButtonYCoordinate - Integer to store y coordinate of the first Button selected.
	 * @param secondButtonXCoordinate - Integer to store x coordinate of the second Button selected.
	 * @param secondButtonYCoordinate - Integer to store y coordinate of the second Button selected.
	 */
	private void setStackText(int firstButtonXCoordinate, int firstButtonYCoordinate,int secondButtonXCoordinate ,int secondButtonYCoordinate ) {
		String textFirstButton = ((panelLayout)super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().toString();
   	String textSecondButton = ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString();

   	panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setText(textFirstButton);
   	panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setFocusable(false);

   	if(panelArray[firstButtonXCoordinate][firstButtonYCoordinate].getBackground()==Color.red || panelArray[firstButtonXCoordinate][firstButtonYCoordinate].getBackground()==Color.blue) {
   		panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setForeground(Color.white);
   	}
   	else {
   		panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setForeground(Color.black);
   	}

   	panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setFont(new Font("Arial", Font.BOLD, 10));


   	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setText(textSecondButton);
   	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setFocusable(false);
   	if(panelArray[secondButtonXCoordinate][secondButtonYCoordinate].getBackground()==Color.red || panelArray[secondButtonXCoordinate][secondButtonYCoordinate].getBackground()==Color.blue) {
   		panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setForeground(Color.white);
   	}
   	else {
   		panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setForeground(Color.black);
   	}
       panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setFont(new Font("Arial", Font.BOLD, 10));	

	}

	//Method overloading to disable the pop up when player enter's the game
	public void getPopUp() {
		//To close the pop for the game
	}
	

	/*
	 * Checks at each turn, if the players have any of their pieces left to make a move.
	 * @param currentTurn - Integer to store the number corresponding to current turn's player.
	 */
	public void checkTurnStatus(int currentTurn) {

		this.currentTurn=currentTurn;
		boolean colorPieceAvailable = false;
		if (this.currentTurn==1) {			
			if(playerOneReserveCounter>0) {
				colorPieceAvailable = true;
			}
			else {

				for (int row=0; row<8;row++){
					for(int column=0; column<8;column++) {
						if((Color)super.panelArray[row][column].getBackground() == Color.blue){
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
						if((Color)super.panelArray[row][column].getBackground() == Color.red){
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
						if((Color)super.panelArray[row][column].getBackground() == Color.green){
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
						if((Color)super.panelArray[row][column].getBackground() == Color.yellow){
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
	 * @param secondButtonXCoordinate - Integer to store the x-coordinate of the selected position 
	 *                                  to move the reserved piece to.
	 *                                  
	 * @param secondButtonYCoordinate - Integer to store the y-coordinate of the selected position 
	 *                                  to move the reserved piece to.
	 */
	private void playFromReservePlayerOne(int secondButtonXCoordinate,int secondButtonYCoordinate) {
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add("B");
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setBackground(Color.blue);
		String textStack = ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString();
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(textStack);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setForeground(Color.white);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFocusable(false);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFont(new Font("Arial", Font.BOLD, 10));
		this.playerOneReserveCounter-=1;
		this.setPlayerOneReserveCounter("RESERVED : ");
		this.checkWin(this.currentTurn);
		selected = null;
	}
	
	
	/*
	 * Enables Player Two to use the reserved piece/s again in the game. 
	 * Checks if there are any more possibles moves.If not,declares a winner.
	 * @param secondButtonXCoordinate - Integer to store the x-coordinate of the selected position 
	 *                                  to move the reserved piece to.
	 *                                  
	 * @param secondButtonYCoordinate - Integer to store the y-coordinate of the selected position 
	 *                                  to move the reserved piece to.
	 */
	private void playFromReservePlayerTwo(int secondButtonXCoordinate,int secondButtonYCoordinate) {
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add("R");
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setBackground(Color.red);
		String textStack = ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString();
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(textStack);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setForeground(Color.white);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFocusable(false);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFont(new Font("Arial", Font.BOLD, 10));
		this.playerTwoReserveCounter-=1;
		this.setPlayerTwoReserveCounter("RESERVED : ");
		this.checkWin(this.currentTurn);
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
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add("G");
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setBackground(Color.green);
		String textStack = ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString();
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(textStack);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setForeground(Color.black);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFocusable(false);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFont(new Font("Arial", Font.BOLD, 10));
		this.playerThreeReserveCounter-=1;
		this.setPlayerThreeReserveCounter("RESERVED : ");
		this.checkWin(this.currentTurn);
		selected = null;
	}

	
	/*
	 * Enables Player Four to use the reserved piece/s again in the game. 
	 * Checks if there are any more possibles moves.If not,declares a winner.
	 * @param secondButtonXCoordinate - Integer to store the x-coordinate of the selected position 
	 *                                  to move the reserved piece to.
	 *                                  
	 * @param secondButtonYCoordinate - Integer to store the y-coordinate of the selected position 
	 *                                  to move the reserved piece to.
	 */
	private void playFromReservePlayerFour(int secondButtonXCoordinate,int secondButtonYCoordinate) {
		
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add("Y");
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setBackground(Color.yellow);
		String textStack = ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString();
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(textStack);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setForeground(Color.black);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFocusable(false);
		((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setFont(new Font("Arial", Font.BOLD, 10));
		this.playerFourReserveCounter-=1;
		this.setPlayerFourReserveCounter("RESERVED : ");		
		this.checkWin(this.currentTurn);
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
	            				((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString());

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
           				((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString());
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
           				((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString());
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
           				this.checkCapturedPiece(secondButtonXCoordinate,secondButtonYCoordinate);
           				((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).setText(((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().toString());
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
					                if (((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackSize-1)=="B"){
					                	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setBackground(nameOfTheColor); 
					                	 panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setBackground(Color.blue);


					                }
					                else if(((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackSize-1)=="R"){
					                	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setBackground(nameOfTheColor); 
					                	panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setBackground(Color.red);

					                }
					                else if(((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackSize-1)=="G"){
					                	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setBackground(nameOfTheColor); 
					                	 panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setBackground(Color.green);

					                }
					                else if (((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackSize-1)=="Y"){
					                	panelArray[secondButtonXCoordinate][secondButtonYCoordinate].setBackground(nameOfTheColor); 
					                	 panelArray[firstButtonXCoordinate][firstButtonYCoordinate].setBackground(Color.yellow);

					                }
				                }

				                this.setStackText(firstButtonXCoordinate, firstButtonYCoordinate, secondButtonXCoordinate, secondButtonYCoordinate);
				                selected = null;
				                this.checkWin(this.currentTurn);	                
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
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= Color.blue) {
	            		selected=null;
	            	}
	            }
	            else if (this.currentTurn==2) {
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= Color.red) {

	            		selected=null;
	            	}
	            }
	            else if (this.currentTurn ==3 ) {
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= Color.green) {

	            		selected=null;
	            	}
	            }
	            else if (this.currentTurn==4) {
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= Color.yellow) {

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
	
	         if (selected3==super.exitGame) {
	 			System.exit(0);
	 		}
	
	        if (selected3== super.newGame) {
	 			this.dispose();
	 			PlayerSelector playerSelectorWindow = new PlayerSelector();
	 			playerSelectorWindow.setLayout();
	 		}
	
	        if(selected3 == super.saveGame) {
	 			//this.dispose();
	 			SaveAndExit saveAndExit = new SaveAndExit();
	 			
	 		}
	
	        if(selected3 == super.loadGame) {
	        	JFileChooser fileChooser = new JFileChooser();
				// select file to open
				int response = fileChooser.showOpenDialog(null);
				
				if (response == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile().getAbsoluteFile().toString());
					ResourceManager manager = new ResourceManager();
					manager.loadGame(fileChooser.getSelectedFile().getAbsoluteFile().toString());
				}
			}         
		}


       if(selected4 == this.botButton) {
      	 if ((currentTurn - numberOfHumanPlayers) > 0 && botDifficultyLevel == 0) {
   			easyAI();

   		}

   		if ((currentTurn - numberOfHumanPlayers) > 0 && botDifficultyLevel == 1) {
   			hardAI();

   		}
       }
	}        

}
