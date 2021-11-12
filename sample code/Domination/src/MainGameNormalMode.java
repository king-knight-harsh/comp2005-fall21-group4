import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
	
	private Random randomTurn;
	private int moveCounter;
	
	private JButton redPiece, yellowPiece, greenPiece, bluePiece;
	
	
	
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
		topPanel.add(this.getPlayerThreePanel());
		topPanel.add(this.getPlayerFourPanel());

		return topPanel;
	}
	
	public JPanel getInfoPanel() {
		infoPanel = new JPanel();
		informationLabel = new  JLabel();		
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 16));
		informationLabel.setForeground(Color.black);
		this.getFirstTurn();
		infoPanel.add(this.informationLabel);
		return infoPanel;
	}
	
	private void getFirstTurn() {
		randomTurn = new Random();
		int firstTurn = randomTurn.nextInt(4);
		firstTurn+=1;
		if (firstTurn == 1){
			this.setInformationLabel("TURN : PLAYER 1");
		}
		else if(firstTurn == 2){
			this.setInformationLabel("TURN : PLAYER 2");
		}
		else if(firstTurn == 3){
			this.setInformationLabel("TURN : PLAYER 3");
		}
		
		else if(firstTurn == 4){
			this.setInformationLabel("TURN : PLAYER 4");
		}		
	}
	
	private void setInformationLabel(String text) {
		this.informationLabel.setText(text);
		
	}
	

	public JPanel getPlayerOnePanel() {
		playerOnePanel =new JPanel();
		playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.Y_AXIS));
		
		
		
		playerOneInfo = new JLabel();
		playerOneInfo.setText(String.valueOf(this.playerOneName));
		
		playerOneInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerOneInfo.setForeground(Color.black);
		playerOneInfo.setVisible(true);
		
		playerOneCaptured = new JLabel("Captured : 0");
		playerOneCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerOneCaptured.setForeground(Color.black);
		
		playerOnePanel.add(playerOneInfo);
		playerOnePanel.add(playerOneCaptured);
		playerOnePanel.setBorder(BorderFactory.createLineBorder(Color.blue, 4));
		
		return playerOnePanel;
	}
	public JPanel getPlayerTwoPanel() {
		playerTwoPanel =new JPanel();
		playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.Y_AXIS));
		
		playerTwoInfo = new JLabel();
		playerTwoInfo.setText(String.valueOf(this.playerTwoName));
		playerTwoInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerTwoInfo.setForeground(Color.black);
		
		playerTwoCaptured = new JLabel("Captured : 0");
		playerTwoCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerTwoCaptured.setForeground(Color.black);
		
		playerTwoPanel.add(playerTwoInfo);
		playerTwoPanel.add(playerTwoCaptured);
		playerTwoPanel.setBorder(BorderFactory.createLineBorder(Color.red, 4));
		
	
		return playerTwoPanel;
	}
	public JPanel getPlayerThreePanel() {
		playerThreePanel =new JPanel();
		playerThreePanel.setLayout(new BoxLayout(playerThreePanel, BoxLayout.Y_AXIS));
		
		
		playerThreeInfo = new JLabel();
		playerThreeInfo.setText(String.valueOf(this.playerThreeName));
		playerThreeInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerThreeInfo.setForeground(Color.black);
		playerThreeCaptured = new JLabel("Captured : 0");
		playerThreeCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerThreeCaptured.setForeground(Color.black);
		
		playerThreePanel.add(playerThreeInfo);
		playerThreePanel.add(playerThreeCaptured);
		playerThreePanel.setBorder(BorderFactory.createLineBorder(Color.green, 4));
		
		return playerThreePanel;
	}
	public JPanel getPlayerFourPanel() {
		playerFourPanel =new JPanel();
		playerFourPanel.setLayout(new BoxLayout(playerFourPanel, BoxLayout.Y_AXIS));
		
		
		playerFourInfo = new JLabel();
		playerFourInfo.setText(String.valueOf(this.playerFourName));
		playerFourInfo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerFourInfo.setForeground(Color.black);
		
		playerFourCaptured = new JLabel("Captured : 0");
		playerFourCaptured.setFont(new Font("TimesRoman", Font.BOLD, 16));
		playerFourCaptured.setForeground(Color.black);
		
		playerFourPanel.add(playerFourInfo);
		playerFourPanel.add(playerFourCaptured);
		playerFourPanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 4));
		
		return playerFourPanel;
	}
	
	
	public void setPiecesOnBoard() {
		super.panelArray[0][2].setBackground(Color.BLUE);
		super.panelArray[0][3].setBackground(Color.BLUE);
		super.panelArray[0][4].setBackground(Color.red);
		super.panelArray[0][5].setBackground(Color.yellow);
		
		super.panelArray[1][1].setBackground(Color.yellow);
		super.panelArray[1][2].setBackground(Color.yellow);
		super.panelArray[1][3].setBackground(Color.yellow);
		super.panelArray[1][4].setBackground(Color.red);
		super.panelArray[1][5].setBackground(Color.yellow);
		super.panelArray[1][6].setBackground(Color.red);
		
		super.panelArray[2][0].setBackground(Color.BLUE);
		super.panelArray[2][1].setBackground(Color.BLUE);
		super.panelArray[2][2].setBackground(Color.BLUE);
		super.panelArray[2][3].setBackground(Color.BLUE);
		super.panelArray[2][4].setBackground(Color.red);
		super.panelArray[2][5].setBackground(Color.yellow);
		super.panelArray[2][6].setBackground(Color.red);
		super.panelArray[2][7].setBackground(Color.yellow);
		
		
		super.panelArray[3][0].setBackground(Color.yellow);
		super.panelArray[3][1].setBackground(Color.yellow);
		super.panelArray[3][2].setBackground(Color.yellow);
		super.panelArray[3][3].setBackground(Color.yellow);
		super.panelArray[3][4].setBackground(Color.red);
		super.panelArray[3][5].setBackground(Color.yellow);
		super.panelArray[3][6].setBackground(Color.red);
		super.panelArray[3][7].setBackground(Color.yellow);
		
		super.panelArray[4][0].setBackground(Color.green);
		super.panelArray[4][1].setBackground(Color.BLUE);
		super.panelArray[4][2].setBackground(Color.green);
		super.panelArray[4][3].setBackground(Color.BLUE);
		super.panelArray[4][4].setBackground(Color.green);
		super.panelArray[4][5].setBackground(Color.green);
		super.panelArray[4][6].setBackground(Color.green);
		super.panelArray[4][7].setBackground(Color.green);
		
		
		super.panelArray[5][0].setBackground(Color.green);
		super.panelArray[5][1].setBackground(Color.BLUE);
		super.panelArray[5][2].setBackground(Color.green);
		super.panelArray[5][3].setBackground(Color.BLUE);
		super.panelArray[5][4].setBackground(Color.red);
		super.panelArray[5][5].setBackground(Color.red);
		super.panelArray[5][6].setBackground(Color.red);
		super.panelArray[5][7].setBackground(Color.red);
		
		super.panelArray[6][1].setBackground(Color.BLUE);
		super.panelArray[6][2].setBackground(Color.green);
		super.panelArray[6][3].setBackground(Color.BLUE);
		super.panelArray[6][4].setBackground(Color.green);
		super.panelArray[6][5].setBackground(Color.green);
		super.panelArray[6][6].setBackground(Color.green);
		
		super.panelArray[7][2].setBackground(Color.green);
		super.panelArray[7][3].setBackground(Color.BLUE);
		super.panelArray[7][4].setBackground(Color.red);
		super.panelArray[7][5].setBackground(Color.red);
		
		
		
	}
	
	
	private void moveThePiece(Object selected, Color color, int oldXCoordinate, int oldYCoordinate) {
		System.out.println("inside the mve mthd");
		moveCounter =1;
		if (((panelLayout) selected).getXCoordinate()!=oldXCoordinate && ((panelLayout) selected).getYCoordinate()!=oldYCoordinate) {
			System.out.println("inside the if");
			panelArray[((panelLayout) selected).getXCoordinate()][((panelLayout) selected).getYCoordinate()].setBackground(color);
			
		}
		
		
	}
	
	
	
	
	public void getPopUp() {
		//To close the pop for the game
	}
	
	@Override
	public void actionPerformed(ActionEvent aevt) {
		Object selected = aevt.getSource();		
		moveCounter=0;
		while(moveCounter<=0) {
			System.out.println("inside loop");
			int xCoordinate = ((panelLayout) selected).getXCoordinate();
			int yCoordinate = ((panelLayout) selected).getYCoordinate();
			Color nameOfTheColor = panelArray[xCoordinate][yCoordinate].getBackground();
			panelArray[xCoordinate][yCoordinate].setBackground(Color.white);
			this.moveThePiece(selected,nameOfTheColor,xCoordinate, yCoordinate);
		}
		
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent mevt) {
		
		// TODO Auto-generated method stub
		
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