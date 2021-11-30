//importing the library
import java.awt.Color;
import java.io.*;
import javax.swing.JButton;

//Class for handling loading and saving of the game
public class ResourceManager {
	MainGameNormalMode stack ;
	/* public visibility method for saving the game
	 * @param  pathToFile - String absolute path to the file
	 */
    public static void saveGame(String pathToFile) {
        //Save Stacks
        try{
        	
            FileOutputStream fileOut = new FileOutputStream(pathToFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(MainGameNormalMode.getStackForGame());
            out.writeObject(MainGameNormalMode.getNumberOfHumanPlayer());
            out.writeObject(MainGameNormalMode.getCurrentTurn());
            out.flush();
            out.close();
            fileOut.close();
        }catch (IOException e) {
            e.printStackTrace();
            }
        }
        
    /* public visibility method for load the game
	 * @param  pathToFile - String absolute path to the file
	 */
    public static void loadGame(String pathToFile) {
        //Load Players
        try{
            FileInputStream fileIn = new FileInputStream(pathToFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            JButton[][] stacks = (JButton[][]) in.readObject();
            int numberOfHumanPlayer = (Integer) in.readObject();
            int currentTurn = (Integer) in.readObject();
        	//Loop to create the button (numberOfRows * numberOfColumns)   
            LoadGame loadGame = new LoadGame(stacks, numberOfHumanPlayer, currentTurn);
            in.close();
            fileIn.close();
            
        }catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
}