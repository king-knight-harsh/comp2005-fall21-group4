/*
 * GUI component to enable users to save the game and 
 * Continue it later. Extends JFrame and implements ActionListener to 
 * handle User Input,java.io.serializable to store the game,uses Swing.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class SaveAndExit extends JFrame implements ActionListener,java.io.Serializable {
	private static final long serialVersionUID =1L;

	private Dimension displayDimension;
	private JLabel informationLabel;
	private JButton saveAndQuitButton, quitButton, backButton; 
	private ImageIcon saveIcon,optionIcon;
	

	//Constructor with public visibility.
	public SaveAndExit() {
		initialize();
	}
	
	
	//Sets the layout for the Save and Exit Screen.
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
	
	
	//Sets the font and style of the text on the Save and Exit Screen.
	
	private JLabel getInformationLabel() {
		informationLabel = new JLabel("Select the appropriate Quit option -");
		informationLabel.setIcon(this.getOptionIcon());
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 32));
		informationLabel.setBounds(30,40,560,100);
		informationLabel.setForeground(Color.decode("#fcc729"));
		return informationLabel;
		
	}
	
	
	//Sets the font,style and functionality to the "Save and Exit" button.
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
	
	//Sets the font,style and functionality to the "Quit" button.
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
	
	//Sets the font,style and functionality to the "Back" button.
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
	
	//Returns Image Icon for the "Save and Exit" button.
	private ImageIcon getSaveImageIcon() {
		saveIcon = new ImageIcon(getClass().getResource("images/saveForQuitDialogBox.png"));
		return saveIcon;
	}

	//Returns Image Icon for the Options.
	private ImageIcon getOptionIcon() {
		optionIcon = new ImageIcon(getClass().getResource("images/option.png"));
		
		return optionIcon;
	}
	
	//Sets the Display screen location to the center.
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}
	
	//Creates a file to save the game.
	public static void CreateFile() {
		File txtFile = new File("save/testTxtFile.txt");
		
		try {
			txtFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Adds the location of each piece to the file.
	public static void WriteToFile() {
		File txtFile = new File("save/testTxtFile.txt");
		
		try {
			PrintWriter pw = new PrintWriter(txtFile);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//ActionListener to handle User Input.
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
			JFileChooser fileChooser = new JFileChooser();
			// select file to open
			int response = fileChooser.showSaveDialog(null);
			
			if (response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsoluteFile().toString());
				ResourceManager manager = new ResourceManager();
				manager.saveGame(fileChooser.getSelectedFile().getAbsoluteFile().toString());
			}							
			System.exit(0);
		}
		
	}
        
}