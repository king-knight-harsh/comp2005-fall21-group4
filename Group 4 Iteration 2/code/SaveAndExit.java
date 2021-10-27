  
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class SaveAndExit extends JFrame implements ActionListener {

	private JFrame frame;
	private JLabel informationLabel;
	private JButton saveAndQuitButton, quitButton, backButton; 
	

	
	public SaveAndExit() {
		initialize();
	}
	
	
	private void initialize() {
		
		this.setBackground(Color.white);
		this.setSize(500,500);
		
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		
		getContentPane().add(this.getInformationLabel());
		getContentPane().add(this.getSaveAndQuitButton());
		getContentPane().add(this.getQuitButton());
		getContentPane().add(this.getBackButton());
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	
	}
	
	
	
	
	private JLabel getInformationLabel() {
		informationLabel = new JLabel("Do you want to quit");
		informationLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		informationLabel.setBounds(0,0,100,100);
		informationLabel.setForeground(Color.black);
		return informationLabel;
		
	}
	
	
	private JButton getSaveAndQuitButton() {
		saveAndQuitButton = new JButton("Save and Quit");
		saveAndQuitButton.setFont(new Font("Jokerman", Font.BOLD, 12));
		saveAndQuitButton.setBackground(Color.white);
		saveAndQuitButton.addActionListener(this);
		return saveAndQuitButton;
		
	}
	
	private JButton getQuitButton() {
		quitButton = new JButton("Quit without Saving");
		quitButton.setFont(new Font("Jokerman", Font.BOLD, 12));
		quitButton.setBackground(Color.white);
		quitButton.addActionListener(this);
		return quitButton;
		
	}
	
	private JButton getBackButton() {
		backButton = new JButton("Back");
		backButton.setFont(new Font("Jokerman", Font.BOLD, 12));
		backButton.setBackground(Color.white);
		backButton.addActionListener(this);
		return backButton;
		
	}
	
	
	
	
//	private void SaveAndQuit() {
//                JButton btnSaveAndQuit = new JButton("Save and Quit");
//                btnSaveAndQuit.setFont(new Font("Jokerman", Font.BOLD, 11));
//                btnSaveAndQuit.setBackground(new Color(255, 255,255));
//                btnSaveAndQuit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//				
//				
//			}
//		});
//		btnSaveAndQuit.setBounds(50, 160, 150, 40);
//		
//		JButton btnQuitNoSave = new JButton("Quit Without Saving");
//		btnQuitNoSave.setFont(new Font("Jokerman", Font.BOLD, 11));
//                btnQuitNoSave.setBackground(new Color(255, 255,255));
//		btnQuitNoSave.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//				
//			}
//		});
//		btnQuitNoSave.setBounds(220, 160, 150, 40);
//		
//		JButton btnCancel = new JButton("Go Back!");
//		btnCancel.setFont(new Font("Jokerman", Font.BOLD, 11));
//                btnCancel.setBackground(new Color(255, 255,255));
//		btnCancel.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.dispose();
//				
//			}
//		});
//		btnCancel.setBounds(380, 160, 150, 40);
//		
//		JLabel lblSure = new JLabel("Are you sure you want to quit?");
//		lblSure.setFont(new Font("Jokerman", Font.PLAIN, 15));
//		lblSure.setBounds(150, 75, 500, 40);
//                
//		frame.getContentPane().setLayout(null);
//		frame.getContentPane().add(btnSaveAndQuit);
//		frame.getContentPane().add(btnQuitNoSave);
//		frame.getContentPane().add(btnCancel);
//		frame.getContentPane().add(lblSure);
//				
//	
//	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
        
}