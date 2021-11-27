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

public class MainGameNormalMode extends GameDisplay implements ActionListener, MouseListener,MouseMotionListener{
	
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
	private JButton redPiece, yellowPiece, greenPiece, bluePiece;
	private String pieceColor;
	private JButton deSelectButton;
	
	private int playerOneCaptureCounter=0,playerTwoCaptureCounter=0,playerThreeCaptureCounter=0,playerFourCaptureCounter=0;
	private int playerOneReserveCounter=0,playerTwoReserveCounter=0,playerThreeReserveCounter=0,playerFourReserveCounter=0;
	
	
	
	MainGameNormalMode(int numberOfHumanPlayers, String player1,String player2,String player3,String player4, int botDifficultyLevel, int colorMode){
		this.numberOfHumanPlayers = numberOfHumanPlayers;
		
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
	
	private String getPlayerOneName() {
		return this.playerOneName;
	}
	

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
	
	private void setInformationLabel(String text) {
		this.informationLabel.setText(text);
		
	}
	
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
	

	private JLabel getPlayerOneCaptureCounter() {
		playerOneCaptured = new JLabel("CAPTURED : " + this.playerOneCaptureCounter);
		playerOneCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerOneCaptured.setForeground(Color.black);
		return playerOneCaptured;
	}
	
	private void setPlayerOneCaptureCounter(String text) {
		this.playerOneCaptured.setText(text + Integer.toString(this.playerOneCaptureCounter));
	}
	
	private JLabel getPlayerOneReserveCounter() {
		playerOneReserved = new JLabel("RESERVED : " + this.playerOneReserveCounter);
		playerOneReserved.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerOneReserved.setForeground(Color.black);
		return playerOneReserved;
	}
	
	private void setPlayerOneReserveCounter(String text) {
		this.playerOneReserved.setText(text + Integer.toString(this.playerOneReserveCounter));
	}
	
	private JLabel getPlayerTwoCaptureCounter() {
		playerTwoCaptured = new JLabel("CAPTURED : " + this.playerTwoCaptureCounter);
		playerTwoCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerTwoCaptured.setForeground(Color.black);
		return playerTwoCaptured;
	}
	
	private void setPlayerTwoCaptureCounter(String text) {
		this.playerTwoCaptured.setText(text + Integer.toString(this.playerTwoCaptureCounter));
	}
	
	private JLabel getPlayerTwoReserveCounter() {
		playerTwoReserved = new JLabel("RESERVED : " + this.playerTwoReserveCounter);
		playerTwoReserved.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerTwoReserved.setForeground(Color.black);
		return playerTwoReserved;
	}
	
	private void setPlayerTwoReserveCounter(String text) {
		this.playerTwoReserved.setText(text + Integer.toString(this.playerTwoReserveCounter));
	}
	
	private JLabel getPlayerThreeCaptureCounter() {
		playerThreeCaptured = new JLabel("CAPTURED : " + this.playerThreeCaptureCounter);
		playerThreeCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerThreeCaptured.setForeground(Color.black);
		return playerThreeCaptured;
	}
	
	private void setPlayerThreeCaptureCounter(String text) {
		this.playerThreeCaptured.setText(text + Integer.toString(this.playerThreeCaptureCounter));
	}
	
	private JLabel getPlayerThreeReserveCounter() {
		playerThreeReserved = new JLabel("RESERVED : " + this.playerThreeReserveCounter);
		playerThreeReserved.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerThreeReserved.setForeground(Color.black);
		return playerThreeReserved;
	}
	
	private void setPlayerThreeReserveCounter(String text) {
		this.playerThreeReserved.setText(text + Integer.toString(this.playerThreeReserveCounter));
	}
	
	private JLabel getPlayerFourCaptureCounter() {
		playerFourCaptured = new JLabel("CAPTURED : " + this.playerFourCaptureCounter);
		playerFourCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerFourCaptured.setForeground(Color.black);
		return playerFourCaptured;
	}
	
	private void setPlayerFourCaptureCounter(String text) {
		this.playerFourCaptured.setText(text + Integer.toString(this.playerFourCaptureCounter));
	}
	
	private JLabel getPlayerFourReserveCounter() {
		playerFourReserved = new JLabel("RESERVED : " + this.playerFourReserveCounter);
		playerFourReserved.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerFourReserved.setForeground(Color.black);
		return playerFourReserved;
	}
	
	private void setPlayerFourReserveCounter(String text) {
		this.playerFourReserved.setText(text + Integer.toString(this.playerFourReserveCounter));
	}
	
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
	
	
	public void setPiecesOnBoard() {
		super.panelArray[0][2].setBackground(Color.BLUE);
		super.panelArray[0][3].setBackground(Color.BLUE);
		super.panelArray[0][4].setBackground(Color.red);
		super.panelArray[0][5].setBackground(Color.yellow);
		((panelLayout) super.panelArray[0][2]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[0][3]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[0][4]).getStackForPiece().add("R");
		((panelLayout) super.panelArray[0][5]).getStackForPiece().add("Y");
		
		super.panelArray[1][1].setBackground(Color.yellow);
		super.panelArray[1][2].setBackground(Color.yellow);
		super.panelArray[1][3].setBackground(Color.yellow);
		super.panelArray[1][4].setBackground(Color.red);
		super.panelArray[1][5].setBackground(Color.yellow);
		super.panelArray[1][6].setBackground(Color.red);
		((panelLayout) super.panelArray[1][1]).getStackForPiece().add("Y");
		((panelLayout) super.panelArray[1][2]).getStackForPiece().add("Y");
		((panelLayout) super.panelArray[1][3]).getStackForPiece().add("Y");
		((panelLayout) super.panelArray[1][4]).getStackForPiece().add("R");
		((panelLayout) super.panelArray[1][5]).getStackForPiece().add("Y");
		((panelLayout) super.panelArray[1][6]).getStackForPiece().add("R");
		
		super.panelArray[2][0].setBackground(Color.BLUE);
		super.panelArray[2][1].setBackground(Color.BLUE);
		super.panelArray[2][2].setBackground(Color.BLUE);
		super.panelArray[2][3].setBackground(Color.BLUE);
		super.panelArray[2][4].setBackground(Color.red);
		super.panelArray[2][5].setBackground(Color.yellow);
		super.panelArray[2][6].setBackground(Color.red);
		super.panelArray[2][7].setBackground(Color.yellow);
		((panelLayout) super.panelArray[2][0]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[2][1]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[2][2]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[2][3]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[2][4]).getStackForPiece().add("R");
		((panelLayout) super.panelArray[2][5]).getStackForPiece().add("Y");
		((panelLayout) super.panelArray[2][6]).getStackForPiece().add("R");
		((panelLayout) super.panelArray[2][7]).getStackForPiece().add("Y");
		
		
		
		super.panelArray[3][0].setBackground(Color.yellow);
		super.panelArray[3][1].setBackground(Color.yellow);
		super.panelArray[3][2].setBackground(Color.yellow);
		super.panelArray[3][3].setBackground(Color.yellow);
		super.panelArray[3][4].setBackground(Color.red);
		super.panelArray[3][5].setBackground(Color.yellow);
		super.panelArray[3][6].setBackground(Color.red);
		super.panelArray[3][7].setBackground(Color.yellow);
		((panelLayout) super.panelArray[3][0]).getStackForPiece().add("Y");
		((panelLayout) super.panelArray[3][1]).getStackForPiece().add("Y");
		((panelLayout) super.panelArray[3][2]).getStackForPiece().add("Y");
		((panelLayout) super.panelArray[3][3]).getStackForPiece().add("Y");
		((panelLayout) super.panelArray[3][4]).getStackForPiece().add("R");
		((panelLayout) super.panelArray[3][5]).getStackForPiece().add("Y");
		((panelLayout) super.panelArray[3][6]).getStackForPiece().add("R");
		((panelLayout) super.panelArray[3][7]).getStackForPiece().add("Y");
		
		
		super.panelArray[4][0].setBackground(Color.green);
		super.panelArray[4][1].setBackground(Color.BLUE);
		super.panelArray[4][2].setBackground(Color.green);
		super.panelArray[4][3].setBackground(Color.BLUE);
		super.panelArray[4][4].setBackground(Color.green);
		super.panelArray[4][5].setBackground(Color.green);
		super.panelArray[4][6].setBackground(Color.green);
		super.panelArray[4][7].setBackground(Color.green);
		((panelLayout) super.panelArray[4][0]).getStackForPiece().add("G");
		((panelLayout) super.panelArray[4][1]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[4][2]).getStackForPiece().add("G");
		((panelLayout) super.panelArray[4][3]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[4][4]).getStackForPiece().add("G");
		((panelLayout) super.panelArray[4][5]).getStackForPiece().add("G");
		((panelLayout) super.panelArray[4][6]).getStackForPiece().add("G");
		((panelLayout) super.panelArray[4][7]).getStackForPiece().add("G");
		
		super.panelArray[5][0].setBackground(Color.green);
		super.panelArray[5][1].setBackground(Color.BLUE);
		super.panelArray[5][2].setBackground(Color.green);
		super.panelArray[5][3].setBackground(Color.BLUE);
		super.panelArray[5][4].setBackground(Color.red);
		super.panelArray[5][5].setBackground(Color.red);
		super.panelArray[5][6].setBackground(Color.red);
		super.panelArray[5][7].setBackground(Color.red);
		((panelLayout) super.panelArray[5][0]).getStackForPiece().add("G");
		((panelLayout) super.panelArray[5][1]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[5][2]).getStackForPiece().add("G");
		((panelLayout) super.panelArray[5][3]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[5][4]).getStackForPiece().add("R");
		((panelLayout) super.panelArray[5][5]).getStackForPiece().add("R");
		((panelLayout) super.panelArray[5][6]).getStackForPiece().add("R");
		((panelLayout) super.panelArray[5][7]).getStackForPiece().add("R");
		
		
		super.panelArray[6][1].setBackground(Color.BLUE);
		super.panelArray[6][2].setBackground(Color.green);
		super.panelArray[6][3].setBackground(Color.BLUE);
		super.panelArray[6][4].setBackground(Color.green);
		super.panelArray[6][5].setBackground(Color.green);
		super.panelArray[6][6].setBackground(Color.green);
		((panelLayout) super.panelArray[6][1]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[6][2]).getStackForPiece().add("G");
		((panelLayout) super.panelArray[6][3]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[6][4]).getStackForPiece().add("G");
		((panelLayout) super.panelArray[6][5]).getStackForPiece().add("G");
		((panelLayout) super.panelArray[6][6]).getStackForPiece().add("G");
		
		
		
		super.panelArray[7][2].setBackground(Color.green);
		super.panelArray[7][3].setBackground(Color.BLUE);
		super.panelArray[7][4].setBackground(Color.red);
		super.panelArray[7][5].setBackground(Color.red);
		((panelLayout) super.panelArray[7][2]).getStackForPiece().add("G");
		((panelLayout) super.panelArray[7][3]).getStackForPiece().add("B");
		((panelLayout) super.panelArray[7][4]).getStackForPiece().add("R");
		((panelLayout) super.panelArray[7][5]).getStackForPiece().add("R");	
		
	}
	
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
	
	private int getDistanceBetweenMove(Object selected2, int lengthOfTheStack) {
		// Calculating the distance for the xCoordinate
				int xCordinateDistance = ((panelLayout) selected2).getXCoordinate() - ((panelLayout) selected).getXCoordinate();
				// Calculating the distance for the yCoordinate
				int yCordinateDistance = ((panelLayout) selected2).getYCoordinate() - ((panelLayout) selected).getYCoordinate();
				// Absolute distance as the sum of xCoordinate and yCoordinate.
				int distanceBetweenBtn = Math.abs(xCordinateDistance) + Math.abs(yCordinateDistance); 
				
				return distanceBetweenBtn;
	}
	
	private void checkCapturedPiece(int buttonXCoordinate, int buttonYCoordinate ) {
		int stackLength =((panelLayout)super.panelArray[buttonXCoordinate][buttonYCoordinate]).getStackForPiece().size();
		System.out.println(stackLength);
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

	public void moveOnePiece(int firstButtonXCoordinate,int firstButtonYCoordinate, int secondButtonXCoordinate, int secondButtonYCoordinate) {
        int moveDistance = 1;
		for(int stackColor=0;stackColor<moveDistance;stackColor++) {
    		int stackSize = ((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size()-1;
        	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(stackSize);		            	
            ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
            
        }
	}
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
	public void moveFivePiece(int firstButtonXCoordinate,int firstButtonYCoordinate, int secondButtonXCoordinate, int secondButtonYCoordinate) {
		
        int moveDistance = 5;
        for(int stackColor=0;stackColor<moveDistance;stackColor++) {
    		int stackSize = ((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size()-1;
        	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(0);		            	
            ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor);		                
            
        }
	}
	
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
	
	private void easyAI() {
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
		            	if (panelArray[x][y].getBackground()!= Color.red) {
		            		check = false;
		            	}
		            }
		            else if (this.currentTurn ==3 ) {
		            	check = true;
		            	if (panelArray[x][y].getBackground()!= Color.green) {
		            		check = false;
		            	}
		            }
		            else if (this.currentTurn==4) {
		            	check = true;
		            	if (panelArray[x][y].getBackground()!= Color.yellow) {
		            		check = false;
		            	}
		            }
			}
			
			System.out.println(x);
			System.out.println(y);
			System.out.println("second part");
			
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

	        int moveDistance = 1;
			for(int stackColor=0;stackColor<moveDistance;stackColor++) {
	    		int stackSize = ((panelLayout)super.panelArray[x][y]).getStackForPiece().size()-1;
	        	pieceColor = ((panelLayout) super.panelArray[x][y]).getStackForPiece().remove(stackSize);		            	
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
			if ((currentTurn - numberOfHumanPlayers) > 0 && botDifficultyLevel == 0) {
				easyAI();
				
			}
		}
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
	            	if (panelArray[x][y].getBackground()!= Color.red) {
	            		check = false;
	            	}
	            }
	            else if (this.currentTurn ==3 ) {
	            	check = true;
	            	if (panelArray[x][y].getBackground()!= Color.green) {
	            		check = false;
	            	}
	            }
	            else if (this.currentTurn==4) {
	            	check = true;
	            	if (panelArray[x][y].getBackground()!= Color.yellow) {
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
	
	
	public void getPopUp() {
		//To close the pop for the game
	}
	
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
	
	
	
	@Override
	public void actionPerformed(ActionEvent aevt) {
		
		
		Object selected3 = aevt.getSource();
		if (selected3!=ruleBook && selected3!=exitGame && selected3!=newGame && selected3!=saveGame && selected3!=loadGame ) {
			if (selected != null) {
	            Object selected2 = aevt.getSource();
	           
	            if (panelArray[((panelLayout) selected2).getXCoordinate()][((panelLayout) selected2).getYCoordinate()]!=panelArray[7][7] ) {	            
		            
		            if (panelArray[firstButtonXCoordinate][firstButtonYCoordinate]!=panelArray[((panelLayout) selected2).getXCoordinate()][((panelLayout) selected2).getYCoordinate()]) {
		            	
		            	
		            	if(this.checkValidMove(selected2,((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size())==true) {
		            		
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
				                System.out.println("current turn is " + currentTurn);
				                
				                
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
	            if (this.currentTurn ==1 ) {
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= Color.blue ) {
	            		selected=null;
	            	}
	            }
	            else if (this.currentTurn==2) {
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= Color.red ) {
	            		selected=null;
	            	}
	            }
	            else if (this.currentTurn ==3 ) {
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= Color.green ) {
	            		selected=null;
	            	}
	            }
	            else if (this.currentTurn==4) {
	            	if (panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].getBackground()!= Color.yellow ) {
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
        
        
	
		
		
		
		
	

	@Override
	public void mouseClicked(MouseEvent mevt) {
		
		// TODO Auto-generated method stub
		Object newSelection = mevt.getSource();
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent aevt) {
		// TODO Auto-generated method stub
		Object selected = aevt.getSource();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}