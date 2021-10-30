import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class GameDisplay extends JFrame implements ActionListener{

	private JPanel[][] panelArray ;	
	private JMenuBar menuBar;
	private JButton piece;
	private JPanel middlePanel;
	private JLabel informationLabel;	
	private JMenu setting,colourPalette;	
	private JMenuItem newGame,loadGame,saveGame,exitGame, ruleBook;
	private JMenuItem normalMode,colourBlindMode;		
	private ImageIcon gameIcon, newGameIcon, loadIcon, saveIcon, exitIcon,ruleBookIcon, normalColorPaletteIcon, colorBlindPaletteIcon, botIcon, welcomeIcon;	
	
	private Dimension displayDimension;
	
	/*
	 * Constructor with public visibility
	 * @param numberOfRow - Integer to store the number of rows in the GridLayout
	 * @param numberOfColumn - Integer to store the number of columns in the GridLayout
	 */
	public GameDisplay(){
		
		this.setLayout();//Initiating the initial view of the game
		JOptionPane.showMessageDialog(null,"CLICK ON SETTINGS TO BEGIN ","WELCOME TO GAME ",JOptionPane.PLAIN_MESSAGE,this.getWelcomeIcon());
		
		
	}	
	
	
	public void setLayout() {
		
 		
		this.setTitle("Focus");
		this.setIconImage(this.getGameIcon().getImage());
		this.setSize(1000,1000);
		this.setDisplayToCenter();
		
		
		this.setForeground(Color.cyan);
		this.setMenuBar();
		
		getContentPane().add(this.getMiddlePanel(),BorderLayout.CENTER);
		
		this.disableButton();
		
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	
	}
	
	public JPanel getMiddlePanel() {
		middlePanel =new JPanel();
		middlePanel.setLayout(new GridLayout(8,8,4,4));
		middlePanel.setSize(800,800);
		// Two demensional array of JButton to store xCoordinate and yCoordinate
		panelArray = new JPanel[8][8];

			//Loop to create the button (numberOfRows * numberOfColumns)
		for (int row=0; row<8;row++){
			for(int column=0; column<8;column++) {
				panelArray[row][column] = new panelLayout(row, column);
				panelArray[row][column].setBackground(Color.WHITE);
				middlePanel.add(panelArray[row][column]);
			}
		}
			
		return middlePanel;				
		
	}
	
	
	public void setButton() {
		for (int row=0; row<8;row++){
			for(int column=0; column<8;column++) {
				piece = new JButton();
				piece.setFocusable(false);				
				piece.setPreferredSize(new Dimension(80,80));
				piece.setBackground(Color.red);
				panelArray[row][column].add(piece, CENTER_ALIGNMENT);
			}
		}
		
		
	}
	
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
	
	public void setMenuBar(){
		menuBar = new JMenuBar();
		
		
		setting = new JMenu("Setting");
		colourPalette = new JMenu("Colour Palette");	
		
		newGame = new JMenuItem("New Game  ");
		loadGame =new JMenuItem("Load Game ");
		saveGame =new JMenuItem("Save Game ");
		ruleBook = new JMenuItem("Rule Book");
		exitGame =new JMenuItem("Exit     ");
		
		normalMode = new JMenuItem("Normal Mode  ");
		colourBlindMode =new JMenuItem("Colour Blind Mode ");
		
		menuBar.add(setting);
		setting.add(newGame);
		setting.add(loadGame);
		setting.add(saveGame);
		setting.add(ruleBook);
		setting.add(exitGame);
		
				
		menuBar.add(colourPalette);
		colourPalette.add(normalMode);
		colourPalette.add(colourBlindMode);
			
		
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
	
	private void setDisplayToCenter() {
		displayDimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(displayDimension.width/2-this.getSize().width/2, displayDimension.height/2-this.getSize().height/2);
	}
	
	public ImageIcon getGameIcon() {
		gameIcon = new ImageIcon(getClass().getResource(("images/board.png")));
		return gameIcon;
	}
	
	public ImageIcon getNewGameIcon() {
		newGameIcon = new ImageIcon(getClass().getResource(("images/new.png")));
		return newGameIcon;
	}
	
	public ImageIcon getLoadIcon() {
		loadIcon = new ImageIcon(getClass().getResource("images/load.png"));
		return loadIcon;
	}
	
	public ImageIcon getWelcomeIcon() {
		welcomeIcon = new ImageIcon(getClass().getResource("images/namaste.png"));
		return welcomeIcon;
	}
	
	public ImageIcon getSaveIcon() {
		saveIcon = new ImageIcon(getClass().getResource("images/save.png"));
		return saveIcon;
	}
	
	public ImageIcon getExitIcon() {
		exitIcon = new ImageIcon(getClass().getResource("images/exit.png"));
		return exitIcon;
	}
	
	public ImageIcon getNormalColorPaletteIcon() {
		normalColorPaletteIcon = new ImageIcon(getClass().getResource("images/normalPalette.png"));
		return normalColorPaletteIcon;
	}
	
	public ImageIcon getColorBlindPaletteIcon() {
		colorBlindPaletteIcon = new ImageIcon(getClass().getResource("images/colorBlindPalette.png"));
		return colorBlindPaletteIcon;
	}
	
	public ImageIcon getRuleBookIcon() {
		ruleBookIcon = new ImageIcon(getClass().getResource("images/book.png"));
		return ruleBookIcon;
	}
	
	public ImageIcon getBotIcon() {
		botIcon = new ImageIcon(getClass().getResource("images/robot.png"));
		return botIcon;
	}
	

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
	

	
	
}
