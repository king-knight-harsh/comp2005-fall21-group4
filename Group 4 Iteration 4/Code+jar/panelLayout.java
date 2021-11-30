
//Imported component
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;


public class panelLayout extends JButton {

	
	// Location in the grid
	private int xCoordinate;
	private int yCoordinate;
	private ArrayList<String> stackOfPieces;
	
	
	
	
	//Constructor takes the x and y coordinate of this square
	public panelLayout(int xCordinate, int yCoordinate){
		super();
		this.xCoordinate = xCordinate;
		this.yCoordinate = yCoordinate;
		stackOfPieces = new ArrayList<>();
		
		
	}
	
	//Implements Arraylist to store the pieces of a pile.
	public ArrayList<String> getStackForPiece() {
		return this.stackOfPieces;
	}
	
	
	//simple getter method to return xCoordinate and yCoordinate
	public int getXCoordinate()	{return xCoordinate;}
	public int getYCoordinate()  {return yCoordinate;}
	//simple setter method to set xCoordinate and yCoordinate
	public void setXcordinate(int value) {xCoordinate = value;}
	public void setYCoordinate(int value) {yCoordinate = value;}
	
	
	
	
}
