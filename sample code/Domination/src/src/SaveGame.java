import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;



public class SaveGame extends JFrame implements ActionListener,java.io.Serializable {
	private static final long serialVersionUID =1L;

	private Dimension displayDimension;
	private JLabel informationLabel;
	private JButton saveButton, noButton; 
	private ImageIcon saveIcon,optionIcon;
	

	
	public SaveGame() {
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
		getContentPane().add(this.setYesButton());
		getContentPane().add(this.setNoButton());
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	
	}
	

	private JLabel getInformationLabel() {
		informationLabel = new JLabel("Are you sure you want to save the currently saved game? The last saved game will be overwritten.");
		informationLabel.setIcon(this.getOptionIcon());
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 56));
		informationLabel.setBounds(30,40,560,100);
		informationLabel.setForeground(Color.decode("#fcc729"));
		return informationLabel;
		
	}
	
	
	private JButton setYesButton() {
		saveButton = new JButton("Yes");
		saveButton.setFont(new Font("TimesRoman", Font.BOLD, 16));
		saveButton.setBackground(Color.decode("#fcc729"));
		saveButton.setForeground(Color.decode("#337def"));
		saveButton.addActionListener(this);
		saveButton.setFocusable(false);		
		saveButton.setBounds(80, 100, 300, 100);
		return saveButton;
		
	}
	
	private JButton setNoButton() {
		noButton = new JButton("No");
		noButton.setFont(new Font("TimesRoman", Font.BOLD, 16));
		noButton.setBackground(Color.decode("#fcc729"));
		noButton.setForeground(Color.decode("#337def"));
		noButton.addActionListener(this);
		noButton.setFocusable(false);
		noButton.setBounds(80, 220, 300, 100);
		return noButton;
		
	}
	
	private ImageIcon getSaveImageIcon() {
		saveIcon = new ImageIcon(getClass().getResource("images/saveForQuitDialogBox.png"));
		return saveIcon;
	}

	private ImageIcon getOptionIcon() {
		optionIcon = new ImageIcon(getClass().getResource("images/option.png"));
		
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
		if(selected == noButton) {
			this.dispose();
		}
		
		if(selected == saveButton) {
			this.dispose();
			
			MainGameNormalMode.WriteToFile();
		}
		
	}
        
}