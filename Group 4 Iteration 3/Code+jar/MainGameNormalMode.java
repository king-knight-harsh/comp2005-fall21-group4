import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainGameNormalMode extends GameDisplay implements MouseListener{
	
	private JLabel informationLabel;
	private int numberOfHumanPlayers;	
	private String playerOneName, playerTwoName, playerThreeName, playerFourName;
	private int colorMode;
	private int botDifficultyLevel;
	
	private JPanel topPanel,infoPanel, playerOnePanel,playerTwoPanel,playerThreePanel, playerFourPanel;
	private JLabel playerOneInfo,playerTwoInfo,playerThreeInfo,playerFourInfo; 
	private JLabel playerOneCaptured,playerTwoCaptured,playerThreeCaptured,playerFourCaptured;  
	private JPanel eastPanel;
	
	private JButton redPiece, yellowPiece, greenPiece, bluePiece;
	
	
	
	MainGameNormalMode(int numberOfHumanPlayers, String player1,String player2,String player3,String player4, int botDifficultyLevel, int colorMode){
		this.numberOfHumanPlayers = numberOfHumanPlayers;
		this.playerOneName=player1;
		this.playerTwoName=player2;
		this.playerThreeName=player3;
		this.playerFourName=player4;
		this.botDifficultyLevel = botDifficultyLevel;
		this.colorMode = colorMode;
		
	}
	
	private String getPlayerOneName() {
		return this.playerOneName;
	}
	
	public void setLayout() {
		super.setLayout();
		getContentPane().add(this.getTopPanel(),BorderLayout.NORTH);
		getContentPane().add(this.getInfoPanel(),BorderLayout.SOUTH);
		this.setPiecesOnBoard();
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
		informationLabel = new  JLabel("PLAYER TURN : 1");		
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 24));
		informationLabel.setForeground(Color.black);
		
		infoPanel.add(this.informationLabel);
		return infoPanel;
	}
	
	
	
	
	public JPanel getPlayerOnePanel() {
		playerOnePanel =new JPanel();
		playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.Y_AXIS));
		
		playerOneInfo = new JLabel("PLAYER 1");
		
		playerOneInfo.setFont(new Font("TimesRoman", Font.BOLD, 24));
		playerOneInfo.setForeground(Color.black);
		playerOneInfo.setVisible(true);
		
		playerOneCaptured = new JLabel("Captured : 0");
		playerOneCaptured.setFont(new Font("TimesRoman", Font.BOLD, 24));
		playerOneCaptured.setForeground(Color.black);
		
		playerOnePanel.add(playerOneInfo);
		playerOnePanel.add(playerOneCaptured);
		
		return playerOnePanel;
	}
	public JPanel getPlayerTwoPanel() {
		playerTwoPanel =new JPanel();
		playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.Y_AXIS));
		
		playerTwoInfo = new JLabel("PLAYER 2");
		playerTwoInfo.setFont(new Font("TimesRoman", Font.BOLD, 24));
		playerTwoInfo.setForeground(Color.black);
		
		playerTwoCaptured = new JLabel("Captured : 0");
		playerTwoCaptured.setFont(new Font("TimesRoman", Font.BOLD, 24));
		playerTwoCaptured.setForeground(Color.black);
		
		playerTwoPanel.add(playerTwoInfo);
		playerTwoPanel.add(playerTwoCaptured);
		
	
		return playerTwoPanel;
	}
	public JPanel getPlayerThreePanel() {
		playerThreePanel =new JPanel();
		playerThreePanel.setLayout(new BoxLayout(playerThreePanel, BoxLayout.Y_AXIS));
		
		
		playerThreeInfo = new JLabel("PLAYER 3");
		playerThreeInfo.setFont(new Font("TimesRoman", Font.BOLD, 24));
		playerThreeInfo.setForeground(Color.black);
		
		playerThreeCaptured = new JLabel("Captured : 0");
		playerThreeCaptured.setFont(new Font("TimesRoman", Font.BOLD, 24));
		playerThreeCaptured.setForeground(Color.black);
		
		playerThreePanel.add(playerThreeInfo);
		playerThreePanel.add(playerThreeCaptured);
		
		return playerThreePanel;
	}
	public JPanel getPlayerFourPanel() {
		playerFourPanel =new JPanel();
		playerFourPanel.setLayout(new BoxLayout(playerFourPanel, BoxLayout.Y_AXIS));
		
		
		playerFourInfo = new JLabel("PLAYER 4");
		playerFourInfo.setFont(new Font("TimesRoman", Font.BOLD, 24));
		playerFourInfo.setForeground(Color.black);
		
		playerFourCaptured = new JLabel("Captured : 0");
		playerFourCaptured.setFont(new Font("TimesRoman", Font.BOLD, 24));
		playerFourCaptured.setForeground(Color.black);
		
		playerFourPanel.add(playerFourInfo);
		playerFourPanel.add(playerFourCaptured);
		
		
		return playerFourPanel;
	}
	
	
	public void setPiecesOnBoard() {
		super.panelArray[0][2].add(this.getBluePiece());
		super.panelArray[0][3].add(this.getBluePiece());
		super.panelArray[0][4].add(this.getRedPiece());
		super.panelArray[0][5].add(this.getYellowPiece());
		
		super.panelArray[1][1].add(this.getYellowPiece());
		super.panelArray[1][2].add(this.getYellowPiece());
		super.panelArray[1][3].add(this.getYellowPiece());
		super.panelArray[1][4].add(this.getRedPiece());
		super.panelArray[1][5].add(this.getYellowPiece());
		super.panelArray[1][6].add(this.getRedPiece());
		
		super.panelArray[2][0].add(this.getBluePiece());
		super.panelArray[2][1].add(this.getBluePiece());
		super.panelArray[2][2].add(this.getBluePiece());
		super.panelArray[2][3].add(this.getBluePiece());
		super.panelArray[2][4].add(this.getRedPiece());
		super.panelArray[2][5].add(this.getYellowPiece());
		super.panelArray[2][6].add(this.getRedPiece());
		super.panelArray[2][7].add(this.getYellowPiece());
		
		
		super.panelArray[3][0].add(this.getYellowPiece());
		super.panelArray[3][1].add(this.getYellowPiece());
		super.panelArray[3][2].add(this.getYellowPiece());
		super.panelArray[3][3].add(this.getYellowPiece());
		super.panelArray[3][4].add(this.getRedPiece());
		super.panelArray[3][5].add(this.getYellowPiece());
		super.panelArray[3][6].add(this.getRedPiece());
		super.panelArray[3][7].add(this.getYellowPiece());
		
		super.panelArray[4][0].add(this.getGreenPiece());
		super.panelArray[4][1].add(this.getBluePiece());
		super.panelArray[4][2].add(this.getGreenPiece());
		super.panelArray[4][3].add(this.getBluePiece());
		super.panelArray[4][4].add(this.getGreenPiece());
		super.panelArray[4][5].add(this.getGreenPiece());
		super.panelArray[4][6].add(this.getGreenPiece());
		super.panelArray[4][7].add(this.getGreenPiece());
		
		
		super.panelArray[5][0].add(this.getGreenPiece());
		super.panelArray[5][1].add(this.getBluePiece());
		super.panelArray[5][2].add(this.getGreenPiece());
		super.panelArray[5][3].add(this.getBluePiece());
		super.panelArray[5][4].add(this.getRedPiece());
		super.panelArray[5][5].add(this.getRedPiece());
		super.panelArray[5][6].add(this.getRedPiece());
		super.panelArray[5][7].add(this.getRedPiece());
		
		super.panelArray[6][1].add(this.getBluePiece());
		super.panelArray[6][2].add(this.getGreenPiece());
		super.panelArray[6][3].add(this.getBluePiece());
		super.panelArray[6][4].add(this.getGreenPiece());
		super.panelArray[6][5].add(this.getGreenPiece());
		super.panelArray[6][6].add(this.getGreenPiece());
		
		super.panelArray[7][2].add(this.getGreenPiece());
		super.panelArray[7][3].add(this.getBluePiece());
		super.panelArray[7][4].add(this.getRedPiece());
		super.panelArray[7][5].add(this.getRedPiece());
		
		
		
	}
	
	private JButton getRedPiece() {
		redPiece = new JButton();
		redPiece.setBackground(Color.red);
		redPiece.setPreferredSize(new Dimension(100,90));
		
		return redPiece;
	}
	
	private JButton getYellowPiece() {
		yellowPiece = new JButton();
		yellowPiece.setBackground(Color.yellow);
		yellowPiece.setPreferredSize(new Dimension(100,90));
		return yellowPiece;
	}
	private JButton getBluePiece() {
		bluePiece = new JButton();
		bluePiece.setBackground(Color.BLUE);
		bluePiece.setPreferredSize(new Dimension(100,90));
		return bluePiece;
	}
	private JButton getGreenPiece() {
		greenPiece = new JButton();
		greenPiece.setBackground(Color.green);
		greenPiece.setPreferredSize(new Dimension(100,90));
		return greenPiece;
	}
	
	
	
	public void getPopUp() {
		//To close the pop for the game
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	
	
	
	
}
