import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorSelect extends JFrame implements ActionListener{
	
	private JLabel informationLabel;
	private int numberOfHumanPlayers;
	private Dimension displayDimension;
	private JButton normalModeButton, colorBlindButton;	
	private String playerOneName, playerTwoName, playerThreeName, playerFourName;
	private ImageIcon ColorModeIcon;
	private int colorMode;
	private int botDifficultyLevel;
	
	ColorSelect(int numberOfHumanPlayers, String player1,String player2,String player3,String player4, int botDifficultyLevel){
		this.numberOfHumanPlayers = numberOfHumanPlayers;
		this.playerOneName=player1;
		this.playerTwoName=player2;
		this.playerThreeName=player3;
		this.playerFourName=player4;
		this.botDifficultyLevel = botDifficultyLevel;
		this.setLayout();
	}
	
	private void setLayout() {	
		
		
		this.setSize(520,300);
		this.setLayout(null);
		this.setDisplayToCenter();
		this.setIconImage(this.getColorModeIcon().getImage());
		this.setTitle("COLOR PALETTE");
		
		getContentPane().setBackground(Color.decode("#337def"));
		getContentPane().add(this.setInformationLabel());
		getContentPane().add(this.setNormalColorModeButton());
		getContentPane().add(this.setColorBlindModeButton());
				
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setResizable(false);		
		this.setVisible(true);
	}
	
	private JLabel setInformationLabel() {
		informationLabel = new JLabel("COLOUR PALETTE",SwingConstants.CENTER);
		informationLabel.setBounds(55,30,400,100);
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));
		informationLabel.setForeground(Color.decode("#fcc729"));
		informationLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#fcc729"), 4));
		return informationLabel;
	}
	
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}

	
	private ImageIcon getColorModeIcon() {
		ImageIcon ColorModeIcon = new ImageIcon(getClass().getResource("images/colorBlindPalette.png"));
		return ColorModeIcon;
	}
	
	public JButton setNormalColorModeButton() {
		normalModeButton = new JButton("NORMAL MODE");
		normalModeButton.setFont(new Font("TimesRoman", Font.BOLD, 24));		
		normalModeButton.setBounds(20, 180, 230, 50);
		normalModeButton.setBackground(Color.decode("#fcc729"));
		normalModeButton.setForeground(Color.decode("#337def"));
		normalModeButton.setFocusable(false);
		normalModeButton.addActionListener(this);
		return normalModeButton;		
	}
	public JButton setColorBlindModeButton() {
		colorBlindButton = new JButton("COLOR BLIND");
		colorBlindButton.setFont(new Font("TimesRoman", Font.BOLD, 24));		
		colorBlindButton.setBounds(260, 180, 230, 50);
		colorBlindButton.setBackground(Color.decode("#fcc729"));
		colorBlindButton.setForeground(Color.decode("#337def"));
		colorBlindButton.setFocusable(false);
		colorBlindButton.addActionListener(this);
		return colorBlindButton;
	}

	@Override
	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		Object selected = aevt.getSource();
		if (selected == this.normalModeButton) {
			this.colorMode = 0;
			this.dispose();	
			MainGameNormalMode mainGameNormalMode= new MainGameNormalMode(numberOfHumanPlayers,this.playerOneName,this.playerTwoName,this.playerThreeName,this.playerFourName, this.botDifficultyLevel, this.colorMode);
		}
		
		if(selected == this.colorBlindButton) {
			this.colorMode = 1;
			this.dispose();	
			//MainGameColorBlindMode mainGameColorBlindMode= new MainGameColorBlindMode(numberOfHumanPlayers,this.playerOneName,this.playerTwoName,this.playerThreeName,this.playerFourName, this.botDifficultyLevel, this.colorMode);
			 
		}
	}

}

