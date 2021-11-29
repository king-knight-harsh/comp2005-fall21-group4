/*The main window of the GUI
 * Extends JFrame - we can add our own components
 * Implements ActionListener,MouseMotionListener and MouseListener
 * in order to handle user Input
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GameDisplay extends JFrame implements ActionListener,MouseMotionListener,MouseListener{

	public JButton[][] panelArray ;	
	private JMenuBar menuBar;
	private JButton piece;
	private JPanel middlePanel;
	
	private JMenu action,colourPalette;	
	public JMenuItem newGame,loadGame,saveGame,exitGame, ruleBook;
	private JMenuItem normalMode,colourBlindMode;		
	private ImageIcon gameIcon, newGameIcon, loadIcon, saveIcon, exitIcon,ruleBookIcon, normalColorPaletteIcon, colorBlindPaletteIcon, botIcon, welcomeIcon;	
	private Dimension displayDimension;
	
 
	//Constructor with public Visibility
	public GameDisplay(){
		
		this.setLayout();//Initiating the initial view of the game
		this.getPopUp();
		
		
		
	}	
	
	//opens a dialog box with information on how to get started with the game.
	public void getPopUp() {
		JOptionPane.showMessageDialog(null,"CLICK ON ACTION'S TO BEGIN ","WELCOME TO GAME ",JOptionPane.PLAIN_MESSAGE,this.getWelcomeIcon());
	}
	
	//Sets the layout of the game.
	public void setLayout() {
		
 		
		this.setTitle("Focus");
		this.setIconImage(this.getGameIcon().getImage());
		this.setSize(900,900);
		this.setDisplayToCenter();
		
		
		this.setMenuBar();
		
		getContentPane().add(this.getMiddlePanel(),BorderLayout.CENTER);
		
		this.disableButton();
		
		 
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.setVisible(true);
	
	}
	
	
	
	
	protected JButton[][] getPanelArray(){
		return panelArray;
	}
	
	
	//Sets the layout for the middle panel of the JFrame and add components to it.
	public JPanel getMiddlePanel() {
		
		middlePanel =new JPanel();
		middlePanel.setLayout(new GridLayout(8,8,4,4));
		middlePanel.setSize(600,600);
		// Two demensional array of JButton to store xCoordinate and yCoordinate
		panelArray = new JButton[8][8];

			//Loop to create the button (numberOfRows * numberOfColumns)
		for (int row=0; row<8;row++){
			for(int column=0; column<8;column++) {
				panelArray[row][column] = new panelLayout(row, column);
				panelArray[row][column].setBackground(Color.WHITE);
				panelArray[row][column].addActionListener(this);
				panelArray[row][column].addMouseListener(this);
				panelArray[row][column].addMouseMotionListener(this);
				middlePanel.add(panelArray[row][column]);
				
			}
		}
		
		return middlePanel;				
		
	}
	
	
	
	//Sets the pieces (buttons) at proper position in the game. 
	public void setButton() {
		for (int row=0; row<8;row++){
			for(int column=0; column<8;column++) {
				piece = new JButton();
				piece.setFocusable(false);				
				piece.setPreferredSize(new Dimension(60,60));
				piece.setBackground(Color.red);
				panelArray[row][column].add(piece, CENTER_ALIGNMENT);
			}
		}
		
		
	}
	
	//Disables the buttons that are not required.
	private void disableButton() {
		
		panelArray[0][0].setVisible(false);
		panelArray[0][1].setVisible(false);		
		panelArray[0][6].setVisible(false);
		panelArray[0][7].setVisible(false);
		
		panelArray[1][0].setVisible(false);
		panelArray[1][7].setVisible(false);
		
		panelArray[6][0].setVisible(false);
		panelArray[6][7].setVisible(false);
		
		
		panelArray[7][0].setVisible(false);
		panelArray[7][1].setVisible(false);		
		panelArray[7][6].setVisible(false);
		panelArray[7][7].setVisible(false);
		
		
		
	}
	
	/*Creates a Menu Bar and adds Mutliple Menu items with Action Listener to it, so that certain
	 * Actions are performed upon clicking each Menu item. Also, adds the shortcut keys to access 
	 * Menu Items Without using the Mouse.
	 */
	public void setMenuBar(){
		menuBar = new JMenuBar();
		
		
		action = new JMenu("ACTION");
		this.colourPalette = new JMenu("Colour Palette");	
		
		newGame = new JMenuItem("New Game  ");
		loadGame =new JMenuItem("Load Game ");
		saveGame =new JMenuItem("Save Game ");
		ruleBook = new JMenuItem("Rule Book");
		exitGame =new JMenuItem("Exit     ");
		
		normalMode = new JMenuItem("Normal Mode  ");
		colourBlindMode =new JMenuItem("Colour Blind Mode ");
		
		menuBar.add(action);
		action.add(newGame);
		action.add(loadGame);
		action.add(saveGame);
		action.add(ruleBook);
		action.add(exitGame);
		
		
		newGame.addActionListener(this);
		loadGame.addActionListener(this);
		saveGame.addActionListener(this);
		ruleBook.addActionListener(this);
		exitGame.addActionListener(this);
		
		newGame.setMnemonic(KeyEvent.VK_N);  // press N for new game
		loadGame.setMnemonic(KeyEvent.VK_L); // press L for load game
		saveGame.setMnemonic(KeyEvent.VK_S); // press S to save game
		ruleBook.setMnemonic(KeyEvent.VK_R); // press R for rule book
		exitGame.setMnemonic(KeyEvent.VK_E); // press E to exit the game
		
		newGame.setIcon(this.getNewGameIcon());
		loadGame.setIcon(this.getLoadIcon());
		saveGame.setIcon(this.getSaveIcon());
		ruleBook.setIcon(this.getRuleBookIcon());
		exitGame.setIcon(this.getExitIcon());
		
		normalMode.addActionListener(this);
		colourBlindMode.addActionListener(this);
		
		normalMode.setMnemonic(KeyEvent.VK_1); // Press 1 for normal colour palette
		colourBlindMode.setMnemonic(KeyEvent.VK_2); //Press 2 for colour blind palette
		
		normalMode.setIcon(this.getNormalColorPaletteIcon());
		colourBlindMode.setIcon(this.getColorBlindPaletteIcon());
		
		//setBotDifficulty.addActionListener(this);
		//setBotDifficulty.setIcon(this.getBotIcon());
		
		this.setJMenuBar(menuBar);
		
		
	}
	
	//Sets the dimensions correctly so that the GUI is always at the centre.
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}
	
	//Gets the image for the Board game.
	public ImageIcon getGameIcon() {
		gameIcon = new ImageIcon(getClass().getResource(("images/board.png")));
		return gameIcon;
	}
	
	//Gets the image to be placed as an icon in front "New Game" Menu Item.
	public ImageIcon getNewGameIcon() {
		newGameIcon = new ImageIcon(getClass().getResource(("images/new.png")));
		return newGameIcon;
	}
	
	//Gets the image to be placed as an icon in front of the "Load Game" Menu Item.
	public ImageIcon getLoadIcon() {
		loadIcon = new ImageIcon(getClass().getResource("images/load.png"));
		return loadIcon;
	}
	
	
	//Gets the image to be placed as an icon in the Dialog-box.
	public ImageIcon getWelcomeIcon() {
		welcomeIcon = new ImageIcon(getClass().getResource("images/namaste.png"));
		return welcomeIcon;
	}
	
	//Gets the image to be placed as an icon in front of the "Save Game" Menu item.
	public ImageIcon getSaveIcon() {
		saveIcon = new ImageIcon(getClass().getResource("images/save.png"));
		return saveIcon;
	}
	
	
	//Gets the image to be placed as an icon in front of the "Exit" Menu item.
	public ImageIcon getExitIcon() {
		exitIcon = new ImageIcon(getClass().getResource("images/exit.png"));
		return exitIcon;
	}
	
	/*Gets the image to be placed as an icon on the dialogbox that 
	 * Asks the user if they want Normal Mode or ColorBlind Mode
	 */
	public ImageIcon getNormalColorPaletteIcon() {
		normalColorPaletteIcon = new ImageIcon(getClass().getResource("images/normalPalette.png"));
		return normalColorPaletteIcon;
	}
	
	/*Gets the image to be placed as an icon on the selecting
	 * Colour Mode Dialog Box.
	 */
	public ImageIcon getColorBlindPaletteIcon() {
		colorBlindPaletteIcon = new ImageIcon(getClass().getResource("images/colorBlindPalette.png"));
		return colorBlindPaletteIcon;
	}
	
	//Gets the image to be placed as an icon in front of the "Rulebook" Menu Item.
	public ImageIcon getRuleBookIcon() {
		ruleBookIcon = new ImageIcon(getClass().getResource("images/book.png"));
		return ruleBookIcon;
	}
	
	/*Gets the image to be placed as  an icon
	 * on the selecting AI difficulty level dialog-box.
	 */
	public ImageIcon getBotIcon() {
		botIcon = new ImageIcon(getClass().getResource("images/robot.png"));
		return botIcon;
	}
	
    
	//ActionListener for when the Menu items are clicked.
	@Override
	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		Object selected = aevt.getSource();
		
		if(selected==ruleBook) {
			try {
				Desktop.getDesktop().open(new java.io.File("ruleBook.pdf"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (selected==exitGame) {
			System.exit(0);
		}
		
		if (selected == newGame) {
			this.dispose();
			PlayerSelector playerSelectorWindow = new PlayerSelector();
			playerSelectorWindow.setLayout();
		}
		
		if(selected == saveGame) {
			//this.dispose();
			SaveAndExit saveAndExit = new SaveAndExit();			
		}
		
		if(selected == loadGame) {
			LoadGame loadGame = new LoadGame();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
