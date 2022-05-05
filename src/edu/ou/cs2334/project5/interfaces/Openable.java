package edu.ou.cs2334.project5.interfaces;

import java.io.File;
import java.io.IOException;
/**
 * This is an interface that is implemented by an object set to open files
 * @author gariy
 *
 */
public interface Openable {
	/**
	 * This is the method that is to open the file. 
	 * It should be implemented by the object that implements this interface
	 * @param file this is the file to be opened
	 * @throws IOException this exception is thrown if there is any error in accessing the file specified
	 */
	void open(File file) throws IOException;
}
