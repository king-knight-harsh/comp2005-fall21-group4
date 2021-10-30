import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayerInfo extends JFrame implements ActionListener{
	private int numberOfHumans;
	private Dimension displayDimension;
	private ImageIcon playerIcon;
	private JLabel playerOneInfo,playerTwoInfo,playerThreeInfo,playerFourInfo, informationLabel;
	private JButton submitButtonPlayerOne,submitButtonPlayerTwo,submitButtonPlayerThree,submitButtonPlayerFour;
	private JTextField playerOneName, playerTwoName, playerThreeName, playerFourName;

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
	
	private JLabel getPlayerOneInfo() {
		playerOneInfo = new JLabel("NAME OF PLAYER 1:");
		playerOneInfo.setFont(new Font("TimesRoman", Font.BOLD, 32));
		playerOneInfo.setBounds(10,60,560,100);
		playerOneInfo.setForeground(Color.decode("#fcc729"));
		return playerOneInfo;
	} 
	
	private JLabel getPlayerTwoInfo() {
		playerTwoInfo = new JLabel("NAME OF PLAYER 2:");		
		playerTwoInfo.setFont(new Font("TimesRoman", Font.BOLD, 32));
		playerTwoInfo.setBounds(10,120,560,100);
		playerTwoInfo.setForeground(Color.decode("#fcc729"));
		return playerTwoInfo;
	}
	private JLabel getPlayerThreeInfo() {
		playerThreeInfo = new JLabel("NAME OF PLAYER 3:");
		playerThreeInfo.setFont(new Font("TimesRoman", Font.BOLD, 32));
		playerThreeInfo.setBounds(10,180,560,100);
		playerThreeInfo.setForeground(Color.decode("#fcc729"));
		return playerThreeInfo;
	}
	
	private JLabel getPlayerFourInfo() {
		playerFourInfo = new JLabel("NAME OF PLAYER 4:");
		playerFourInfo.setFont(new Font("TimesRoman", Font.BOLD, 32));
		playerFourInfo.setBounds(10,240,560,100);
		playerFourInfo.setForeground(Color.decode("#fcc729"));
		return playerFourInfo;
		
	}
	
	private JTextField setPlayerOneNameTxt() {
		playerOneName = new JTextField();
		playerOneName.setBounds(340,90,130,40);
		playerOneName.setBackground(Color.decode("#fcc729"));
		playerOneName.setForeground(Color.decode("#337def"));
		playerOneName.setFont(new Font("TimesRoman", Font.BOLD, 26));
		return playerOneName;
	}
	
	private JTextField setPlayerTwoNameTxt() {
		playerTwoName = new JTextField();
		playerTwoName.setBounds(340,150,130,40);
		playerTwoName.setBackground(Color.decode("#fcc729"));
		playerTwoName.setForeground(Color.decode("#337def"));
		playerTwoName.setFont(new Font("TimesRoman", Font.BOLD, 26));
		return playerTwoName;
	}
	
	private JTextField setPlayerThreeNameTxt() {
		playerThreeName = new JTextField();
		playerThreeName.setBounds(340,210,130,40);
		playerThreeName.setBackground(Color.decode("#fcc729"));
		playerThreeName.setForeground(Color.decode("#337def"));
		playerThreeName.setFont(new Font("TimesRoman", Font.BOLD, 26));
		return playerThreeName;
	}
	
	private JTextField setPlayerFourNameTxt() {
		playerFourName = new JTextField();
		playerFourName.setBounds(340,270,130,40);
		playerFourName.setBackground(Color.decode("#fcc729"));
		playerFourName.setForeground(Color.decode("#337def"));
		playerFourName.setFont(new Font("TimesRoman", Font.BOLD, 26));
		return playerFourName;
	}
	
	private JLabel setInformationLabel() {
		informationLabel = new JLabel("Player Information",SwingConstants.CENTER);
		informationLabel.setBounds(90,10,300,50);
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));
		informationLabel.setForeground(Color.decode("#fcc729"));
		informationLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#fcc729"), 4));
		return informationLabel;
	}
	
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
	
	private void setPlayerOneInfo() {
		this.setSize(500,250);
		this.setDisplayToCenter();		
		
		getContentPane().add(this.setInformationLabel());
		getContentPane().add(this.getPlayerOneInfo());		
		getContentPane().add(this.setPlayerOneNameTxt());
		
			
		
		getContentPane().add(playerOneInfo);
		getContentPane().add(this.setSubmitButtonPlayerOne());		
		
	}
	
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
	
	private ImageIcon getPlayerIcon() {
		playerIcon = new ImageIcon("images/user.png");
		return playerIcon;
		
	}
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}

	@Override
	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		Object selected = aevt.getSource();
		
		if (selected == submitButtonPlayerOne) {
			
			this.dispose();
			BotLevel botlevel = new BotLevel(this.numberOfHumans, playerOneName.getText(), null, null, null);
		}
		
		if (selected == submitButtonPlayerTwo) {
			this.dispose();
			BotLevel botlevel = new BotLevel(this.numberOfHumans, playerOneName.getText(),  playerTwoName.getText(), null, null);
		}
		
		if (selected == submitButtonPlayerThree) {
			this.dispose();
			BotLevel botlevel = new BotLevel(this.numberOfHumans, playerOneName.getText(), playerTwoName.getText(), playerThreeName.getText(), null);
		}
		
		if (selected == submitButtonPlayerFour) {
			
			this.dispose();
			
		}
		
		
		
	}
	
}
