
/*
 * GUI component to load the game that was saved earlier.
 * extends JFrame and uses Swing.
 * Implements ActionListener to respond to user action.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadGame extends JFrame implements ActionListener {
	private JLabel informationLabel;
	private JButton slotOne, slotTwo, slotThree, slotFour;
	private Dimension displayDimension;
	private ImageIcon loadImageIcon;
	
	//Constructor.
	LoadGame(){
		this.initialize();
		
	}
	
	
	/*
	 * Sets Initial layout of the screen for the Load Game Menu with
	 * Four slots of saved games.
	 */
	private void initialize() {
				
		this.setSize(500, 650);
		this.setLayout(null);
		this.setDisplayToCenter();
		this.setTitle("STORAGE");
		this.setIconImage(this.getLoadImageIcon().getImage());
		
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.decode("#337def"));
		
		getContentPane().add(this.setInformationLabel());
		getContentPane().add(this.setLoadSlotOne());
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	
	}
	
	
	//Sets the font and style for the text on "LOAD GAME" Screen.
	private JLabel setInformationLabel() {
		informationLabel = new JLabel("SELECT SLOT");
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 56));
		informationLabel.setBounds(40,0,560,100);
		informationLabel.setForeground(Color.decode("#fcc729"));
		return informationLabel;
	}
	
	
	//Sets the ocation of the Display screen to the center.
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}
	
	
	//Sets the layout for the Slot One button.
	private JButton setLoadSlotOne(){
		slotOne = new JButton("SLOT 1");
		slotOne.setFont(new Font("TimesRoman", Font.BOLD, 40));
		slotOne.setBackground(Color.decode("#fcc729"));
		slotOne.setForeground(Color.decode("#337def"));
		slotOne.addActionListener(this);
		slotOne.setFocusable(false);		
		slotOne.setBounds(80, 100, 300, 100);
		return slotOne;
	}	

	//gets the Image Icon for the "LOAD GAME" button.
	private ImageIcon getLoadImageIcon() {
		loadImageIcon = new ImageIcon(getClass().getResource("images/load.png"));
		return loadImageIcon;
	}
	
	
	//Method that reads the positions of the game pieces from the file.
	public static void readFromFile() {
		File txtFile = new File("save/text.txt");
		try {
			Scanner sc = new Scanner(txtFile);
			
			while(sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	//ActionListener to handle User Input.
	@Override
	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		
		Object selected = aevt.getSource();
		if(selected == this.slotOne) {
			this.dispose();
			this.readFromFile();
		}
		if(selected == this.slotTwo) {
			this.dispose();
		}
		
		if(selected == slotThree) {
			this.dispose();
		}
		if(selected == slotFour) {
			this.dispose();
		}
		
		
	}

}
