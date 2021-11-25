import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;



/*
 *  The main window of the gui.
 *  Extends JFrame - so we can add our own components.
 *  Implements ActionListener for handling user input.
 *  Implements MouseListener handling user input .
 *  
 *  @author Harsh Sharma - 201961844
 *  @version 09/23/2021 - 2.30 p.m.
 *  
 */
public class GameDisplay extends JFrame implements ActionListener,MouseListener {
	
	// Private Class variables
	private JButton[][] buttonArray ;
	private JButton newGameButton;
	private JButton treasureLocation;
	
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JLabel informationLabel;
	
	private Random random;
	
	private ImageIcon treasureIcon;
	
	private boolean playerOneTurn;
	private boolean checkNewGameButton = false;	
	
	private int randomRow;
	private int randomColumn;	
	private int numberOfRow;
	private int numberOfColumn;	
	
	private String playerNumber;
	
	/*
	 * Constructor with public visibility
	 * @param numberOfRow - Integer to store the number of rows in the GridLayout
	 * @param numberOfColumn - Integer to store the number of columns in the GridLayout
	 */
	public GameDisplay(int numberOfRow,  int numberOfColumn){
		this.numberOfRow = numberOfRow;
		this.numberOfColumn = numberOfColumn;
		this.setLayout();//Initiating the initial view of the game
		
	}	
	
	public void setLayout() {
		this.setSize(600,600);
		
		//Creating the panels
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());	
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(5,5,10,10));
		bottomPanel.setSize(500,500);
		
		// Creating the components for each panel and add them to it
		
		// For the top panel:		
		informationLabel = new JLabel("Find the treasure. Click 'New Game' to begin");
		newGameButton = new JButton("New Game");
		newGameButton.addMouseListener(this);
		newGameButton.setFocusable(false);

		
		topPanel.add(newGameButton);		
		topPanel.add(informationLabel);
		
		
		// For bottom panel 
		
		// Two demensional array of JButton to store xCoordinate and yCoordinate
		buttonArray = new JButton[this.getNumberOfRows()][this.getNumberOfColumn()];

		//Loop to create the button (numberOfRows * numberOfColumns)
		for (int row=0; row<this.getNumberOfRows();row++){
			for(int column=0; column<this.getNumberOfColumn();column++) {
				buttonArray[row][column] = new buttonLayout(row, column);
				buttonArray[row][column].setBackground(Color.WHITE);
				bottomPanel.add(buttonArray[row][column]);
			}
		}
		
		//Setting the title of the GUI Frame
		this.setTitle("   Treasure Game   ");
		//Setting the Icon for the frame 
		this.setIconImage(this.getImageIcon().getImage());
		
		//setting the Container layout as borderLayout
		getContentPane().setLayout(new BorderLayout());
		//setting the location of the topPanel and bottomPanel
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(bottomPanel, BorderLayout.CENTER);
		
		
		
		//setting the defualt closing functionality
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Setting the resizability of the frame to false
		this.setResizable(false);
		//Setting the visibility of the frame to true
		this.setVisible(true);
	
	}
	/* Method to getting the imageIcon for the display and treasure button
	* @return this.treasureIcon - ImageIcon store the image of the treasure
	*/
	public ImageIcon getImageIcon() {
		this.treasureIcon = new ImageIcon("treasure.png");
		return this.treasureIcon;
	}
	
	/*
	 * Getter method to get the number of the rows of grid
	 * @return numberOfRow - int store the number of the rows in the grid
	 */	
	public int getNumberOfRows() {
		return numberOfRow;
	}
	
	 /* Getter method to get the number of the columns of grid
	 * @return numberOfColumn - int store the number of the columns in the grid
	 */ 
	public int getNumberOfColumn() {
		return numberOfColumn;
	}
	
	/*
	 * Setter Method to set the information label to provided text
	 * @param text - String to reflect on the information label
	 */	
	public void setInformationLabel(String text) {
		informationLabel.setText(text);
	}
	
	/*
	 * Getter Method to get the two demensional array storing button of the grid 
	 * @return buttonArray - Two demensional JButton array 
	 */
	public JButton[][] getButtonArray() {
		return buttonArray;
	}
	
	/*
	 * Getter method to get the random location of the treasure in the grid
	 * Location is randomly generated everytime the method is called
	 * @return treasureLocation - JButton store the location of the treasure in the grid 
	 */
	public JButton getTreasureLocation(){
		random = new Random();
		randomRow = random.nextInt(this.getNumberOfRows());
		randomColumn = random.nextInt(this.getNumberOfColumn());		
		this.treasureLocation = buttonArray[randomRow][randomColumn];
		return this.treasureLocation;
	}
	
	/*
	 * Method to initialize the game once somebody clicks
	 * on the new Game button on the top Panel
	 * 
	 * create the random location for the treasure, initiate buttons with action listener 
	 * functionality and add it to the grid
	 * 
	 */
	public void initializeGame() {
		
		this.getTreasureLocation();		
		
		buttonArray = new JButton[this.getNumberOfRows()][this.getNumberOfColumn()];	
		for (int row=0; row<this.getNumberOfRows();row++){
			for(int column=0; column<this.getNumberOfColumn();column++) {
				buttonArray[row][column] = new buttonLayout(row, column);
				buttonArray[row][column].setBackground(Color.WHITE);
				buttonArray[row][column].addActionListener(this);
				bottomPanel.add(buttonArray[row][column]);
			}
		}		
		getContentPane().add(bottomPanel);
		
		
	}
	
	/*
	 * Method to alter between the players turn 
	 * Use RandomPlayer class to generate the players
	 * Depending on the player's turn changes the top panel's 
	 * information panel text.
	 */
	public boolean firstTurn() {
		Random turnSelector = new Random();
		int firstTurn = turnSelector.nextInt(2);
		
		//Conditional blocks for deciding and setting the information label
		//for the game depending on the player
		if (firstTurn==0) {			
			this.setInformationLabel(" Player 1 turn");
			return playerOneTurn =true;
		}
		else {
			this.setInformationLabel(" Player 2 turn");
			return playerOneTurn =false;
			
		}
	}
	
	/*
	 * Method to check for the win if button containing treasure is selected
	 * Finishes up the Game if the right button is selected and reflects the number
	 * of the player on the information label at the end
	 * Changes the button containing the treasure to color of the player
	 * and set the icon to treasure.png
	 * Disable the the bottomPanel after somebody is won the game 
	 * @param selected - Object tells the button selected in the grid
	 * @param playerNumber - String containing the playernumber who last clicked the button
	 *  
	 */	
	public void checkForWin(Object selected, String playerNumber) {
		
		this.playerNumber = playerNumber;
		
		
		if ((((buttonLayout) this.treasureLocation).getXcordinate() == ((buttonLayout) selected).getXcordinate()) && (((buttonLayout) this.treasureLocation).getYcordinate()==((buttonLayout) selected).getYcordinate())){
			String label = "Player "+ this.playerNumber  +" Won!!!!";
			//((buttonLayout)selected).setText("X");
			buttonArray[((buttonLayout) selected).getXcordinate()][((buttonLayout) selected).getYcordinate()].setIcon(this.getImageIcon());
			this.informationLabel.setText(label);
			for (int row=0; row<5;row++){
				for(int column=0; column<5;column++) {
					buttonArray[row][column].setEnabled(false);
				}
			}
		}
		
	}
	
	/*
	 * Mehtod to calculate the distance between the treasure button and 
	 * the selected buttoon
	 * @param selected - Object containing the last selected button
	 * @return distanceFromTreasure - Integer containing the absolute distance from the treasure button
	 */	
	public int getDistanceFromTreasure(Object selected) {
		// Calculating the distance for the xCoordinate
		int xCordinateDistance = ((buttonLayout) selected).getXcordinate() - ((buttonLayout) treasureLocation).getXcordinate();
		// Calculating the distance for the yCoordinate
		int yCordinateDistance = ((buttonLayout) selected).getYcordinate() - ((buttonLayout) treasureLocation).getYcordinate();
		// Absolute distance as the sum of xCoordinate and yCoordinate.
		int distanceFromTreasure = Math.abs(xCordinateDistance) + Math.abs(yCordinateDistance); 
		return distanceFromTreasure;
		
	}
	
	/*
	 * Method gameStart contains the main logic for the game
	 * Runs for max (numberOfRow * numberOfColumn) times
	 * @param selected - Object contains the last selected button
	 */
	public void gameStart(Object selected) {
		
		JButton selectedButton =buttonArray[((buttonLayout) selected).getXcordinate()][((buttonLayout) selected).getYcordinate()];
		
		for (int numberOfTries=0;numberOfTries<(this.getNumberOfRows() * this.getNumberOfColumn()) ;numberOfTries++) {
			
			//Conditional block for player one turn
			if (playerOneTurn) {
				// check if selected button is treasure or not
				boolean checkForTreasure =(((buttonLayout) this.treasureLocation).getXcordinate() != ((buttonLayout) selected).getXcordinate()) && (((buttonLayout) this.treasureLocation).getYcordinate() !=((buttonLayout) selected).getYcordinate());
				
				//Conditional block once somebody selectes the previously unselected button in the bottom panel
				if(selected instanceof buttonLayout && selectedButton.getText()=="" ){
					// Conditional block if the selected button is not treasue
					// Sets the color, tell the distance from the treasure, set text font, set text color, set focusable 
					// Sets informational lable changes the turn to the next player					
					if(checkForTreasure) {						
						selectedButton.setText(Integer.toString(getDistanceFromTreasure(selected)));
						selectedButton.setBackground(new Color(0,255,255));
						selectedButton.setFont(new Font("MV Boli", Font.BOLD, 48));
						selectedButton.setForeground(Color.black);
						selectedButton.setFocusable(false);
						this.playerOneTurn = false;
						this.setInformationLabel("Player 2 turn");
					}
					// Conditional block if the selected button is the treasue
					// Sets the color, tell the distance from the treasure, set text font, set text color, set focusable 
					// Sets informational lable changes the turn to the next player
					// Calls the checkForWin method to close the game
					else {
						
						selectedButton.setBackground(new Color(0,255,255));
						if (this.getDistanceFromTreasure(selectedButton)!=0) {
							selectedButton.setText(Integer.toString(getDistanceFromTreasure(selected)));
							selectedButton.setFont(new Font("MV Boli", Font.BOLD, 48));
						}
						
						selectedButton.setFocusable(false);
						this.playerOneTurn = false;
						this.setInformationLabel("Player 2 turn");
						this.checkForWin(selected,"One");
						
						
					}
					
				}
			}
			//Conditional block for the player two turn
			else {
				// check if selected button is treasure or not
				boolean checkForTreasure = (((buttonLayout) this.treasureLocation).getXcordinate() != ((buttonLayout) selected).getXcordinate()) && (((buttonLayout) this.treasureLocation).getYcordinate() !=((buttonLayout) selected).getYcordinate());
				
				//Conditional block once somebody selectes the previously unselected button in the bottom panel
				if(selected instanceof buttonLayout && selectedButton.getText()==""){
					
					// Conditional block if the selected button is not treasue
					// Sets the color, tell the distance from the treasure, set text font, set text color, set focusable 
					// Sets informational lable changes the turn to the next player
					if(checkForTreasure) {						
						selectedButton.setBackground(Color.YELLOW);					
						selectedButton.setText(Integer.toString(getDistanceFromTreasure(selected)));						
						selectedButton.setFont(new Font("MV Boli", Font.BOLD, 48));
						selectedButton.setForeground(Color.black);
						selectedButton.setFocusable(false);
						this.playerOneTurn = true;
						this.setInformationLabel("Player 1 turn");
					}
					// Conditional block if the selected button is the treasue
					// Sets the color, tell the distance from the treasure, set text font, set text color, set focusable 
					// Sets informational lable changes the turn to the next player
					// Calls the checkForWin method to close the game
					else {
						
						if (this.getDistanceFromTreasure(selectedButton)!=0) {
							selectedButton.setText(Integer.toString(getDistanceFromTreasure(selected)));
							selectedButton.setFont(new Font("MV Boli", Font.BOLD, 48));
							}
						
						selectedButton.setBackground(Color.YELLOW);
						selectedButton.setFocusable(false);
						this.playerOneTurn = true;
						this.setInformationLabel("Player 1 turn");
						this.checkForWin(selected,"Two");
						
					}
					
				}
			}
			
			
		}
	}
	
	/*
	 * Mehtod to remove all button and bottomPanel from bottomPanel and container respectively\
	 */
	public void removeButton() {		
		bottomPanel.removeAll();
		bottomPanel.revalidate();
		getContentPane().remove(bottomPanel);
	}
	/*
	 * Method to make sure player click on new Game to play the game
	 * @return this.checkNewGameButton - Boolean changes to true when somebody clicks on new Game Button
	 */
	
	public boolean pressedNewGameButton() {
		return this.checkNewGameButton=true;
	}
	/*
	 * Method to initiate a new game when somebody clicks on the new Game button.
	 */
	public void newGameButtonFunctionality() {		
		this.removeButton();
		this.firstTurn();		
		this.initializeGame();		
	}
	
	
	/*
	 * Action Listener for the bottom panel
	 */
	@Override
	public void actionPerformed(ActionEvent aevt) {
		Object selected = aevt.getSource();
		if(this.checkNewGameButton){
			this.gameStart(selected);
	
			}
		
		}
	
	/*
	 * Mouse Listener for the top panel
	 */
	@Override
	public void mousePressed(MouseEvent mevt) {
		
		if (mevt.getSource()==newGameButton) {
			this.pressedNewGameButton();
			this.newGameButtonFunctionality();
		}
	}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0){}
	public void mouseExited(MouseEvent arg0) {}	
	public void mouseReleased(MouseEvent arg0) {}

	
}


	
	
		
		
		
		


