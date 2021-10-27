import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class GameDisplay extends JFrame implements ActionListener{

	private JButton[][] buttonArray ;
	private JButton newGameButton;
	private JButton treasureLocation;
	
	private JMenuBar menuBar;
	
	private JFrame frame;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel eastPanel;
	private JPanel westPanel;
	private JPanel middlePanel;
	private JLabel informationLabel;
	
	private JMenu setting,colourPalette;	
	private JMenuItem newGame,loadGame,saveGame,exitGame, ruleBook;
	private JMenuItem normalMode,colourBlindMode;	
	
	private ImageIcon gameIcon, newGameIcon, loadIcon, saveIcon, exitIcon,ruleBookIcon, normalColorPaletteIcon, colorBlindPaletteIcon;
	
	private String playerNumber;
	/*
	 * Constructor with public visibility
	 * @param numberOfRow - Integer to store the number of rows in the GridLayout
	 * @param numberOfColumn - Integer to store the number of columns in the GridLayout
	 */
	public GameDisplay(){
		
		this.setLayout();//Initiating the initial view of the game
		
	}	
	
	
	public void setLayout() {
		
 		this.setSize(1000,1000);
		this.setTitle("   Focus   ");
		this.setIconImage(this.getGameIcon().getImage());
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
		buttonArray = new JButton[8][8];

			//Loop to create the button (numberOfRows * numberOfColumns)
		for (int row=0; row<8;row++){
			for(int column=0; column<8;column++) {
				buttonArray[row][column] = new buttonLayout(row, column);
				buttonArray[row][column].setBackground(Color.WHITE);
				middlePanel.add(buttonArray[row][column]);
			}
		}
			
		return middlePanel;				
		
	}
	
	public void disableButton() {
		
		buttonArray[0][0].setVisible(false);
		buttonArray[0][1].setVisible(false);		
		buttonArray[0][6].setVisible(false);
		buttonArray[0][7].setVisible(false);
		
		buttonArray[1][0].setVisible(false);
		buttonArray[1][7].setVisible(false);
		
		buttonArray[6][0].setVisible(false);
		buttonArray[6][7].setVisible(false);
		
		
		buttonArray[7][0].setVisible(false);
		buttonArray[7][1].setVisible(false);		
		buttonArray[7][6].setVisible(false);
		buttonArray[7][7].setVisible(false);
		
		
		
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
		
		newGame.setMnemonic(KeyEvent.VK_N);
		loadGame.setMnemonic(KeyEvent.VK_L);
		saveGame.setMnemonic(KeyEvent.VK_S);
		ruleBook.setMnemonic(KeyEvent.VK_R);
		exitGame.setMnemonic(KeyEvent.VK_E);
		
		newGame.setIcon(this.getNewGameIcon());
		loadGame.setIcon(this.getLoadIcon());
		saveGame.setIcon(this.getSaveIcon());
		ruleBook.setIcon(this.getRuleBookIcon());
		exitGame.setIcon(this.getExitIcon());
		
		normalMode.addActionListener(this);
		colourBlindMode.addActionListener(this);
		
		normalMode.setMnemonic(KeyEvent.VK_1);
		colourBlindMode.setMnemonic(KeyEvent.VK_2);
		
		normalMode.setIcon(this.getNormalColorPaletteIcon());
		colourBlindMode.setIcon(this.getColorBlindPaletteIcon());
		
		
		this.setJMenuBar(menuBar);
		
		
	}
	
	public ImageIcon getGameIcon() {
		gameIcon = new ImageIcon("images/board.png");
		return gameIcon;
	}
	
	public ImageIcon getNewGameIcon() {
		newGameIcon = new ImageIcon("images/new.png");
		return newGameIcon;
	}
	
	public ImageIcon getLoadIcon() {
		loadIcon = new ImageIcon("images/load.png");
		return loadIcon;
	}
	
	public ImageIcon getSaveIcon() {
		saveIcon = new ImageIcon("images/save.png");
		return saveIcon;
	}
	
	public ImageIcon getExitIcon() {
		exitIcon = new ImageIcon("images/exit.png");
		return exitIcon;
	}
	
	public ImageIcon getNormalColorPaletteIcon() {
		normalColorPaletteIcon = new ImageIcon("images/normalPalette.png");
		return normalColorPaletteIcon;
	}
	
	public ImageIcon getColorBlindPaletteIcon() {
		colorBlindPaletteIcon = new ImageIcon("images/colorBlindPalette.png");
		return colorBlindPaletteIcon;
	}
	
	public ImageIcon getRuleBookIcon() {
		ruleBookIcon = new ImageIcon("images/book.png");
		return ruleBookIcon;
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
	}
	

	
	
}
