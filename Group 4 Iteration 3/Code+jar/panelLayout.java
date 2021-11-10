//Imported component
import javax.swing.JButton;
import javax.swing.JPanel;


public class panelLayout extends JPanel {

	
	// Location in the grid
	private int xCoordinate;
	private int yCoordinate;
	
	
	
	//Constructor takes the x and y coordinate of this square
	public panelLayout(int xCordinate, int yCoordinate){
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
