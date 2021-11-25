import javax.swing.JButton;
/*
 *  A GUI component
 *
 *  A simple extension of JPanel which records its
 *  coordinates in xcoordinate and ycoordinate.
 *  
 *  @author Harsh Sharma - 201961844
 *  @version 09/23/2021 - 2.30 p.m.
 */
public class buttonLayout extends JButton {
	
	// Class Variable for location in grid
	private int xCordinate;
	private int yCordinate;
	
	/*
	 * Constructor with public visibility
	 * @param xCordinate - Integer value which holds the xCoordinate
	 * @param yCordinate - Integer value which holds the yCoordinate
	 */
	public buttonLayout(int xCordinate, int yCordinate){
		super();
		this.xCordinate = xCordinate;
		this.yCordinate = yCordinate;
		
		
	}
	
	//Simple Getter method to return the xCordinate and yCordinate
	public int getXcordinate()	{return xCordinate;}
	public int getYcordinate()  {return yCordinate;}
	
	//Simple setter method to set the value of xCordinate and yCordinate
	public void setXcordinate(int value) {xCordinate = value;}
	public void setYcordinate(int value) {yCordinate = value;}
	
	


	
}

