
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
	 * Sets linitial layout of the screen for the Load Game Menu with
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
		getContentPane().add(this.setLoadSlotTwo());
		getContentPane().add(this.setLoadSlotThree());
		getContentPane().add(this.setLoadSlotFour());
		
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
	
	//Sets the layout for the Slot Two button.
	private JButton setLoadSlotTwo(){
		slotTwo = new JButton("SLOT 2");
		slotTwo.setFont(new Font("TimesRoman", Font.BOLD, 40));
		slotTwo.setBackground(Color.decode("#fcc729"));
		slotTwo.setForeground(Color.decode("#337def"));
		slotTwo.addActionListener(this);
		slotTwo.setFocusable(false);		
		slotTwo.setBounds(80, 220, 300, 100);
		return slotTwo;
	}
	
	//Sets the layout for the Slot Three button.
	private JButton setLoadSlotThree(){
		slotThree = new JButton("SLOT 3");		
		slotThree.setFont(new Font("TimesRoman", Font.BOLD, 40));
		slotThree.setBackground(Color.decode("#fcc729"));
		slotThree.setForeground(Color.decode("#337def"));
		slotThree.addActionListener(this);
		slotThree.setFocusable(false);		
		slotThree.setBounds(80, 340, 300, 100);
		return slotThree;
	}
	
	//Sets the layout for the Slot Four button.
	private JButton setLoadSlotFour(){
		slotFour = new JButton("SLOT 4");
		slotFour.setFont(new Font("TimesRoman", Font.BOLD, 40));
		slotFour.setBackground(Color.decode("#fcc729"));
		slotFour.setForeground(Color.decode("#337def"));
		slotFour.addActionListener(this);
		slotFour.setFocusable(false);		
		slotFour.setBounds(80, 460, 300, 100);
		return slotFour;
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
