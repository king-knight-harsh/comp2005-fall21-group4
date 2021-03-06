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
	
	private JPanel topPanel,infoPanel, playerOnePanel,playerTwoPanel,playerThreePanel, playerFourPanel;
	private JLabel playerOneInfo,playerTwoInfo,playerThreeInfo,playerFourInfo; 
	private JLabel playerOneCaptured,playerTwoCaptured,playerThreeCaptured,playerFourCaptured;  
	private JPanel eastPanel;
	
	private int currentTurn;
	
	private Random randomTurn;
	
	private Object selected =null;
	private JButton redPiece, yellowPiece, greenPiece, bluePiece;
	private String pieceColor;
	private JButton deSelectButton;
	
	private int playerOneCaptureCounter=0,playerTwoCaptureCounter=0,playerThreeCaptureCounter=0,playerFourCaptureCounter=0;
	
	
	
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
	
	private JLabel getPlayerTwoCaptureCounter() {
		playerTwoCaptured = new JLabel("CAPTURED : " + this.playerTwoCaptureCounter);
		playerTwoCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerTwoCaptured.setForeground(Color.black);
		return playerTwoCaptured;
	}
	
	private void setPlayerTwoCaptureCounter(String text) {
		this.playerTwoCaptured.setText(text + Integer.toString(this.playerTwoCaptureCounter));
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
	
	private JLabel getPlayerFourCaptureCounter() {
		playerFourCaptured = new JLabel("CAPTURED : " + this.playerFourCaptureCounter);
		playerFourCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerFourCaptured.setForeground(Color.black);
		return playerFourCaptured;
	}
	
	private void setPlayerFourCaptureCounter(String text) {
		this.playerFourCaptured.setText(text + Integer.toString(this.playerFourCaptureCounter));
	}
	
	public JPanel getPlayerOnePanel() {
		playerOnePanel =new JPanel();
		playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.Y_AXIS));
		playerOnePanel.setPreferredSize(new Dimension(150,50));
		
		
		playerOneInfo = new JLabel();
		playerOneInfo.setText(String.valueOf(this.playerOneName));
		
		playerOneInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerOneInfo.setForeground(Color.black);
		playerOneInfo.setVisible(true);
		
		
		
		playerOnePanel.add(playerOneInfo);
		playerOnePanel.add(this.getPlayerOneCaptureCounter());
		playerOnePanel.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		
		return playerOnePanel;
	}
	public JPanel getPlayerTwoPanel() {
		playerTwoPanel =new JPanel();
		playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.Y_AXIS));
		playerTwoPanel.setPreferredSize(new Dimension(150,50));
		
		playerTwoInfo = new JLabel();
		playerTwoInfo.setText(String.valueOf(this.playerTwoName));
		playerTwoInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerTwoInfo.setForeground(Color.black);
		
		
		playerTwoPanel.add(playerTwoInfo);
		playerTwoPanel.add(this.getPlayerTwoCaptureCounter());
		playerTwoPanel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		
	
		return playerTwoPanel;
	}
	public JPanel getPlayerThreePanel() {
		playerThreePanel =new JPanel();
		playerThreePanel.setLayout(new BoxLayout(playerThreePanel, BoxLayout.Y_AXIS));
		playerThreePanel.setPreferredSize(new Dimension(150,50));
		
		playerThreeInfo = new JLabel();
		playerThreeInfo.setText(String.valueOf(this.playerThreeName));
		playerThreeInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerThreeInfo.setForeground(Color.black);
		
		
		
		playerThreePanel.add(playerThreeInfo);
		playerThreePanel.add(this.getPlayerThreeCaptureCounter());
		playerThreePanel.setBorder(BorderFactory.createLineBorder(Color.green, 3));
		
		return playerThreePanel;
	}
	public JPanel getPlayerFourPanel() {
		playerFourPanel =new JPanel();
		playerFourPanel.setLayout(new BoxLayout(playerFourPanel, BoxLayout.Y_AXIS));
		playerFourPanel.setPreferredSize(new Dimension(150,50));
		
		playerFourInfo = new JLabel();
		playerFourInfo.setText(String.valueOf(this.playerFourName));
		playerFourInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerFourInfo.setForeground(Color.black);
		
		
		playerFourPanel.add(playerFourInfo);
		playerFourPanel.add(this.getPlayerFourCaptureCounter());
		playerFourPanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
		
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
			selected=null;
			return false;
		}
		
		
	}
	
	
	private void checkCapturedPiece(Object selected) {
		int stackLength =((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size();
		if (stackLength>=6) {
				if(((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackLength-1)=="B") {
					int counter=0;
					while(counter<(stackLength-5)) {
						((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().remove(0);
						this.playerOneCaptureCounter+=1;
						counter++;
					}
					
					
				}
				else if(((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackLength-1)=="R") {
					int counter=0;
					while(counter<(stackLength-5)) {
						((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().remove(0);
						this.playerTwoCaptureCounter+=1;
						counter++;
					}
					
				
				}
				else if(((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackLength-1)=="G") {
					int counter=0;
					while(counter<(stackLength-5)) {
						((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().remove(0);
						this.playerThreeCaptureCounter+=1;
						counter++;
					}
					
					
				}
				else if(((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().get(stackLength-1)=="Y") {
					int counter=0;
					while(counter<(stackLength-5)) {
						((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().remove(0);
						this.playerFourCaptureCounter+=1;
						counter++;
					}
					
				}
				
				this.setPlayerOneCaptureCounter("CAPTURED : ");
				this.setPlayerTwoCaptureCounter("CAPTURED : ");
				this.setPlayerThreeCaptureCounter("CAPTURED : ");
				this.setPlayerFourCaptureCounter("CAPTURED : ");
			}
		
	}
	
	
	
	
	
	public void getPopUp() {
		//To close the pop for the game
	}
	@Override
	public void actionPerformed(ActionEvent aevt) {
		
		
		Object selected3 = aevt.getSource();
		if (selected3!=ruleBook && selected3!=exitGame && selected3!=newGame && selected3!=saveGame && selected3!=loadGame ) {
			if (selected != null) {
	            Object selected2 = aevt.getSource();
	           
	            if (panelArray[((panelLayout) selected2).getXCoordinate()][((panelLayout) selected2).getYCoordinate()]!=panelArray[7][7] ) {
	            	 
		            int firstButtonXCoordinate = ((panelLayout) selected).getXCoordinate();
		            int firstButtonYCoordinate = ((panelLayout) selected).getYCoordinate();
		            
		            
		            if (panelArray[firstButtonXCoordinate][firstButtonYCoordinate]!=panelArray[((panelLayout) selected2).getXCoordinate()][((panelLayout) selected2).getYCoordinate()]) {
		            	
		            	
		            	if(this.checkValidMove(selected2,((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size())==true) {
		            		
				            	int secondButtonXCoordinate = ((panelLayout) selected2).getXCoordinate();
				                int secondButtonYCoordinate = ((panelLayout) selected2).getYCoordinate();
				                Color nameOfTheColor = panelArray[firstButtonXCoordinate][firstButtonYCoordinate].getBackground();	
				                
				                for(int stackColor=0;stackColor<=((panelLayout)super.panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()]).getStackForPiece().size();stackColor++) {
				                	pieceColor = ((panelLayout) super.panelArray[firstButtonXCoordinate][firstButtonYCoordinate]).getStackForPiece().remove(0);		            	
					                ((panelLayout)super.panelArray[secondButtonXCoordinate][secondButtonYCoordinate]).getStackForPiece().add(pieceColor); 
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
				                
				                this.checkCapturedPiece(selected2);
				                selected = null;
				                
				                if(currentTurn==4) {
				                	currentTurn=1;
				                	this.setInformationLabel("TURN : PLAYER "+ currentTurn);
				                }
				                else {
				                	currentTurn++;
				                	this.setInformationLabel("TURN : PLAYER "+ currentTurn);
				                }
			            	}		            	
		            }
	            }
	            else {
	            	selected=null;
	            }
	            
	        }
	        
	        
	        
	        
	        else{
	            selected = aevt.getSource();
	            if (this.currentTurn ==1 ) {
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
	            
	           
	            
	            
	        }
}
		
		else {
			
		  if(selected3==super.ruleBook) {
 			try {
 				File ruleBookPdf = new File("src/ruleBook.pdf");
 				Desktop.getDesktop().open(ruleBookPdf);
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