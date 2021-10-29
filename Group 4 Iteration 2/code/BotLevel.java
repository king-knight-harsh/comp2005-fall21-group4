import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BotLevel extends JFrame implements ActionListener {
	
	
	private JLabel informationLabel;
	private int numberOfHumanPlayers;
	private Dimension displayDimension;
	private JButton easyLevelButton, hardLevelButton;	
	private String playerOneName, playerTwoName, playerThreeName, playerFourName;
	private ImageIcon BotIcon;
	
	
	BotLevel(int numberOfHumanPlayers, String player1,String player2,String player3,String player4){
		this.numberOfHumanPlayers = numberOfHumanPlayers;
		this.playerOneName=player1;
		this.playerTwoName=player2;
		this.playerThreeName=player3;
		this.playerFourName=player4;
		this.setLayout();
	}
	
	
	
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
	
	private JLabel setInformationLabel() {
		informationLabel = new JLabel("BOT DIFFICULTY",SwingConstants.CENTER);
		informationLabel.setBounds(40,30,300,100);
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));
		informationLabel.setForeground(Color.decode("#fcc729"));
		informationLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#fcc729"), 4));
		return informationLabel;
	}
	
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}

	
	private ImageIcon getBotIcon() {
		ImageIcon botIcon = new ImageIcon("images/robot.png");
		return botIcon;
	}
	
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

	@Override
	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		Object selected = aevt.getSource();
		if (selected == this.easyLevelButton) {
			this.dispose();			
		}
		
		if(selected == this.hardLevelButton) {
			this.dispose();			
		}
	}

}
