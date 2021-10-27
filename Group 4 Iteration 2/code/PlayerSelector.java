import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class PlayerSelector extends JFrame{
	
	JFrame playerSelectorFrame;
	JLabel informationLabel;
	JPanel panel;
	int numberOfHumanPlayers, numberOfBots;
	Border border;
	
	public PlayerSelector() {
		// TODO Auto-generated constructor stub
		border = BorderFactory.createLineBorder(Color.BLACK, 5);
		panel = new JPanel();
		
		
		informationLabel = new JLabel("<html>Select the Number <br>of Human Player:</html>");
		informationLabel.setBounds(0,0,100,100);
		informationLabel.setFont(new Font(null, Font.BOLD, 25));		
		informationLabel.setBorder(border);
		
		panel.add(informationLabel);
		this.add(panel);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	public int getNumberofHumanPlayers() {
		return numberOfHumanPlayers;
	}
	
	public int getNumberOfBots(){
		return (4-numberOfHumanPlayers);
	}

}
