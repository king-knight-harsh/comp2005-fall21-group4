/*
 * This Component manages to save and load the game into/from a file.
 */

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ResourceManager {
	
	public static void save(Serializable data, String fileName) throws Exception{
		try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))){
			oos.writeObject(data);
		}
	}
	
    /**
     * Saves serializable objects to file
     *
     * @param path Path to file
     */
    public static void save(String path, MainGameNormalMode file) throws IOException, ClassNotFoundException {

    	// Create byte buffers
        ByteArrayOutputStream byte_stream = new ByteArrayOutputStream();
        ObjectOutputStream object_stream = new ObjectOutputStream(byte_stream);
        String FILE_EXTENSION =".txt";
        // Convert each object into bytes
//        for (Serializable item : file) {
//            object_stream.writeObject(item);
//        }
        object_stream.flush();

        // Write bytes to file
        FileOutputStream out = new FileOutputStream(path + FILE_EXTENSION);
        // Todo: Convert byte array to base64
        out.write(byte_stream.toByteArray());
        out.close();
    }
}
