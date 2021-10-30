import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SaveAndExit extends JFrame implements ActionListener {

	private Dimension displayDimension;
	private JLabel informationLabel;
	private JButton saveAndQuitButton, quitButton, backButton; 
	private ImageIcon saveIcon,optionIcon;
	

	
	public SaveAndExit() {
		initialize();
	}
	
	
	private void initialize() {
		
		
		
		this.setIconImage(this.getSaveImageIcon().getImage());
		this.setLayout(null);
		this.setSize(600,300);
		this.setTitle("SAVE GAME");
		this.setDisplayToCenter();
		
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.decode("#337def"));
		
		getContentPane().add(this.getInformationLabel());
		getContentPane().add(this.getSaveAndQuitButton());
		getContentPane().add(this.getQuitButton());
		getContentPane().add(this.getBackButton());
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	
	}
	
	
	
	
	private JLabel getInformationLabel() {
		informationLabel = new JLabel("Select the appropriate Quit option -");
		informationLabel.setIcon(this.getOptionIcon());
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));
		informationLabel.setBounds(30,40,560,100);
		informationLabel.setForeground(Color.decode("#fcc729"));
		return informationLabel;
		
	}
	
	
	private JButton getSaveAndQuitButton() {
		saveAndQuitButton = new JButton("Save and Quit");
		saveAndQuitButton.setFont(new Font("TimesRoman", Font.BOLD, 16));
		saveAndQuitButton.setBackground(Color.decode("#fcc729"));
		saveAndQuitButton.setForeground(Color.decode("#337def"));
		saveAndQuitButton.addActionListener(this);
		saveAndQuitButton.setFocusable(false);		
		saveAndQuitButton.setBounds(60, 160, 130, 40);
		return saveAndQuitButton;
		
	}
	
	private JButton getQuitButton() {
		quitButton = new JButton("Quit without Saving");
		quitButton.setFont(new Font("TimesRoman", Font.BOLD, 16));
		quitButton.setBackground(Color.decode("#fcc729"));
		quitButton.setForeground(Color.decode("#337def"));
		quitButton.addActionListener(this);
		quitButton.setFocusable(false);
		quitButton.setBounds(210,160,170,40);
		return quitButton;
		
	}
	
	private JButton getBackButton() {
		backButton = new JButton("Back");
		backButton.setFont(new Font("TimesRoman", Font.BOLD, 16));
		backButton.setBackground(Color.decode("#fcc729"));
		backButton.setForeground(Color.decode("#337def"));
		backButton.addActionListener(this);
		backButton.setFocusable(false);
		backButton.setBounds(400, 160, 120, 40);
		return backButton;
		
	}
	
	private ImageIcon getSaveImageIcon() {
		saveIcon = new ImageIcon("images/saveForQuitDialogBox.png");
		return saveIcon;
	}

	private ImageIcon getOptionIcon() {
		optionIcon = new ImageIcon("images/option.png");
		
		return optionIcon;
	}
	
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}

	@Override
	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		Object selected = aevt.getSource();
		if(selected == backButton) {
			this.dispose();
		}
		if(selected == quitButton) {
			System.exit(0);
		}
		
		if(selected == saveAndQuitButton) {
			this.dispose();
			LoadGame savegame = new LoadGame();
		}
		
	}
        
}