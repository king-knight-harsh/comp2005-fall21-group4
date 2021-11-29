import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadGame extends JFrame implements ActionListener {
	private JLabel informationLabel;
	private JButton slotOne, slotTwo, slotThree, slotFour;
	private Dimension displayDimension;
	private ImageIcon loadImageIcon;
	
	LoadGame(){
		this.initialize();
		
	}
	
	
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
	
	private JLabel setInformationLabel() {
		informationLabel = new JLabel("Are you sure you want to load 
		the last saved game? Any unsaved progress will be deleted.");
		informationLabel.setFont(new Font("TimesRoman", Font.BOLD, 56));
		informationLabel.setBounds(30,40,560,100);
		informationLabel.setForeground(Color.decode("#fcc729"));
		return informationLabel;
	}
	
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}
	
	private JButton setLoadSYes(){
		slotYes = new JButton("Yes");
		slotYes.setFont(new Font("TimesRoman", Font.BOLD, 16));
		slotYes.setBackground(Color.decode("#fcc729"));
		slotYes.setForeground(Color.decode("#337def"));
		slotYes.addActionListener(this);
		slotYes.setFocusable(false);		
		slotYes.setBounds(80, 100, 300, 100);
		return slotYes;
	}

	
	private JButton setLoadNo(){
		slotNo = new JButton("No");
		slotNo.setFont(new Font("TimesRoman", Font.BOLD, 16));
		slotNo.setBackground(Color.decode("#fcc729"));
		slotNo.setForeground(Color.decode("#337def"));
		slotNo.addActionListener(this);
		slotNo.setFocusable(false);		
		slotNo.setBounds(80, 220, 300, 100);
		return slotNo;
	}
	

	private ImageIcon getLoadImageIcon() {
		loadImageIcon = new ImageIcon(getClass().getResource("images/load.png"));
		return loadImageIcon;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		
		Object selected = aevt.getSource();
		if(selected == this.slotYes) {
			this.dispose();
			MainGameNormalMode.readFromFile();
		}
		if(selected == this.slotNo) {
			this.dispose();
		}
		/*
		if(selected == slotThree) {
			this.dispose();
		}
		if(selected == slotFour) {
			this.dispose();
		}
		*/
		
	}

}
