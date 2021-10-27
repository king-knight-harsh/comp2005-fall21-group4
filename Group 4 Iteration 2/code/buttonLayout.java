//Imported component
import javax.swing.JButton;
/*
 * GUI Component
 * 
 * an extension of the JButton class which record its 
 * coordinate in xCordinate and yCoordinate.
 * 
 * @author Harsh Sharma
 */

public class buttonLayout extends JButton {

	
	// Location in the grid
	private int xCoordinate;
	private int yCoordinate;
	
	
	
	//Constructor takes the x and y coordinate of this square
	public buttonLayout(int xCordinate, int yCoordinate){
		super();
		this.xCoordinate = xCordinate;
		this.yCoordinate = yCoordinate;
		
		
	}
	
	//simple getter method to return xCoordinate and yCoordinate
	public int getXcoordinate()	{return xCoordinate;}
	public int getyCoordinate()  {return yCoordinate;}
	//simple setter method to set xCoordinate and yCoordinate
	public void setXcordinate(int value) {xCoordinate = value;}
	public void setyCoordinate(int value) {yCoordinate = value;}
	
	


	
}
